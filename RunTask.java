import java.util.Timer;

public class RunTask {
	
	private static Timer timer;
	
	
	public static void main(String[] args) {
		timer = new Timer();
		timer.schedule(new ExchangeClient(), 1000 * 13 * 60);
	}
	
}