package Utils;

import org.apache.commons.lang.time.StopWatch;

public class Sleeper {
		
	/**
	* This method will pause test execution for an amount of time defined by the user
	* @param millis - amount of time for which to wait, in milliseconds
	*/
	public static void sleep(long milliseconds){
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		do {
			
		} while (stopwatch.getTime() < milliseconds);
		stopwatch.stop();
		stopwatch.reset();
	}

}
