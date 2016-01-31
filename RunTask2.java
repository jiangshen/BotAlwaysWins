import java.util.Timer;

public class RunTask2 {
	
	private static Timer timer;
	
	
	public static void main(String[] args) {
		System.out.println("Started!");
		timer = new Timer();
		timer.schedule(new ExchangeClient(), 200);
	}
	
}