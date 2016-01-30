/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author atamarkin2
 */
public class ExchangeClient extends TimerTask {
	private static Socket socket;
	private static PrintWriter pout;
	private static BufferedReader bin;
	private static Map<String, Stock> stocks;
	private static double askDifference;
	private static Timer timer;
	

    /**
     * @param args the command line arguments
     * @return 
     */
    public void run() {
        try {
            socket = new Socket("codebb.cloudapp.net", 17429);
            pout = new PrintWriter(socket.getOutputStream());
            bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stocks = new HashMap<String, Stock>();
            
            System.out.println("Check");
            
            askDifference = 0.02;
            timer = new Timer();
            //timer.schedule(new RunTask(), 1000 * 3);
            
            //Login
            pout.println("TheBot Iguess");
            
            
            //MAIN BLOCK HERE
            try {
            	
            	
            	for (int i = 0; i < 5 * 600; i++) {
            		update();
            		System.out.println(findBest().getSymbol());
            		buyMaximum(findBest());
            		//System.out.println("Coming");
            		Thread.sleep(500);
            	}
            	
            	//System.out.println("Closing");
            	
            	
            	//Closing stuff
            	pout.close();
                bin.close();
            	pout.println();
                pout.println("CLOSE_CONNECTION");
                
            }
            catch (Exception e) {
                System.out.println("Socket broke or something");
                socket.close();
            }
            
        }
        catch (Exception e) {
            System.out.println("Could not connect or something");
        }
        
    }
    
    public static void testing() throws IOException {
    	
//        pout.println("MY_CASH");
//        pout.println("SECURITIES");
//
//        pout.flush();
//        System.out.println(bin.readLine());
//        String line;
//        while ((line = bin.readLine()) != null) {
//            System.out.println(line);
//        }
        
    }
    
    public static void update() throws IOException {
    	pout.println("SECURITIES");
    	
    	pout.flush();
    	String line = bin.readLine();
    	//System.out.println(line);
    	String[] inputs = line.split(" ");
    	for (int i = 1; i < inputs.length; i += 4) {
    		Stock curr = new Stock();
    		curr.updateValues(inputs[i], Double.parseDouble(inputs[i + 1]), Double.parseDouble(inputs[i + 2]), Double.parseDouble(inputs[i + 3]));
    		stocks.put(inputs[i], curr);
    	}
    	
    	for (String s : stocks.keySet()) {
    		pout.println("ORDERS " + s);
    		
    		pout.flush();
    		line = bin.readLine();
    		inputs = line.split(" ");
    		double dBid = 0;
    		double dAsk = Double.MAX_VALUE;
    		for (int i = 1; i < inputs.length; i += 4) {
    			if (inputs[i].equals("BID")) {
    				double dCurr = Double.parseDouble(inputs[i + 2]);
    				if (dCurr > dBid) {
    					dBid = dCurr;
    				}
    			} else {
    				double dCurr = Double.parseDouble(inputs[i + 2]);
    				if (dCurr < dAsk) {
    					dAsk = dCurr;
    				}
    			}
    		}
    		
//    		System.out.println(line);
//    		System.out.println("Ask: " + dAsk);
//    		System.out.println("Bid: " + dBid);
    		Stock curr = stocks.get(s);
    		stocks.get(s).setAsk(dAsk);
    		stocks.get(s).setBuy(dBid);
    		stocks.get(s).update();
    		System.out.println(curr.getSymbol());
    		System.out.println("EPS: " + stocks.get(s).getEPS() + " Vol: " + curr.getVol());
    		
    		System.out.println(getMoney());
    		
    	}
    	findBest();
    }
    
    public static Stock findBest() throws IOException {
    	//To buy something
    	//MAKE SURE TO CLEAR
    	
    	//FIND BEST THING HERE
    	double dEPS = Double.MIN_VALUE;
    	Stock oBest = null;
    	for (String s : stocks.keySet()) {
    		Stock curr = stocks.get(s);
    		if (curr.getEPS() > dEPS) {
    			dEPS = curr.getEPS();
    			oBest = curr;
    		}
    	}
    	
    	//System.out.println(oBest.getSymbol());
    	
    	return oBest;

    	
    }
    
    public static double getMoney() throws IOException {
    	pout.println("MY_CASH");
    	pout.flush();
    	String line = bin.readLine();
    	String[] inputs = line.split(" ");
    	
    	return Double.parseDouble(inputs[1]);
    }
    
    public static void buyMaximum(Stock stock) throws IOException {
    	double dAskPrice = stock.getAsk() + askDifference;
    	int nAmount = (int) (getMoney() / dAskPrice);
    	
    	//System.out.println("BID " + stock.getSymbol() + " " + dAskPrice + " " + nAmount);
    	pout.println("BID " + stock.getSymbol() + " " + dAskPrice + " " + nAmount);
    	pout.flush();
    	System.out.println(bin.readLine());
    	System.out.println("I think we just bought " + nAmount + " " + stock.getSymbol() + " at " + dAskPrice);
    }

}




//GAME PLAN
