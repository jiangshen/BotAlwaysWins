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

/**
 *
 * @author atamarkin2
 */
public class ExchangeClient {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            //Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
            Socket socket = new Socket("codebb.cloudapp.net", 17429);
            PrintWriter pout = new PrintWriter(socket.getOutputStream());
            BufferedReader bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            try {
                pout.println("TheBot Iguess");
                // for (int i = 4; i < args.length; i++) {
                //     pout.println(args[i]);
                // }
                pout.println("MY_CASH");
                pout.println("CLOSE_CONNECTION");
                pout.flush();
                String line;
                while ((line = bin.readLine()) != null) {
                    System.out.println(line);
                }
                pout.close();
                bin.close();
            }
            catch (Exception e) {
                socket.close();
            }
            
        }
        catch (Exception e) {
            System.out.println("Could not connect or something");
        }
        
    }

}
