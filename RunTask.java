import java.util.Timer;

public class RunTask {
	
	private static Timer timer;
	
	
	public static void main(String[] args) {
		System.out.println("Started!");
		timer = new Timer();
		timer.schedule(new ExchangeClient(), 1000 * 12 * 60);
	}
	
}