package utils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebDriver;


public class Sleeper {
	//Create a static stopwatch object
	//private StopWatch stopwatch = new StopWatch();
	
	/**
	 * This method will pause test execution for an amount of time defined by the user
	 * @param millis - amount of time for which to wait, in milliseconds
	 */
	public static void sleep(long millis){
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		do{}while(stopwatch.getTime() < millis);
		stopwatch.stop();
		stopwatch.reset();
	}
	
	/**
	 * This method uses Selenium's implicitlyWait() method to pause execution for an amount of time defined by the user
	 * @param driver - current WebDriver
	 * @param timeout - amount of time for which to wait
	 * @param timeUnit - unit of time to be used for the wait
	 */
	public static void sleepImplicitly(WebDriver driver, long timeout, TimeUnit timeUnit){
		driver.manage().timeouts().implicitlyWait(timeout, timeUnit);
	}
}

