package Utility;

public class Sleeper {
		
	public static void sleep(long milliSeconds){
			try {
				Thread.sleep(milliSeconds);
			} catch (InterruptedException e) {
				
			}
	}
}
