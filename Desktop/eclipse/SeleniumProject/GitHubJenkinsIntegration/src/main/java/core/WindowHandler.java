package core;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.common.Constants;
import utils.ExtendedExpectedConditions;
import utils.Sleeper;
import utils.TestReporter;

public class WindowHandler {
	private String currentWindow;

	public void setCurrentWindow(WebDriver driver) {
		currentWindow = driver.getWindowHandle(); // get the current window
													// handle
	}

	public WebDriver swapToWindow(WebDriver driver, String title) {
		int windowCount = 0;
		boolean dreamsLogin = false;
		boolean dreamsGuestSearch = false;
		boolean alcLogin = false;

		if (title.toLowerCase() == "parent") {
			driver.switchTo().window(currentWindow); // switch back to the
														// original window
			return driver;
		} else if (title.toLowerCase().equalsIgnoreCase("dreams")) {
			title = "DISNEY RESERVATION ENTRY AND MANAGEMENT SYSTEM";
			dreamsLogin = true;
		} else if (title.toLowerCase().equalsIgnoreCase("alc")) {
			title = "a la carte";
			alcLogin = true;
		}

		for (String winHandle : driver.getWindowHandles()) {
			try {
				// switch focus of WebDriver to the next found window handle
				// (that's your newly opened window)
				driver.switchTo().window(winHandle);

				if (driver.getTitle().toUpperCase().contains(title.toUpperCase()) && dreamsLogin) {
					dreamsLogin = false;
					return driver;
				} else {
					if (driver.getTitle().toUpperCase().contains(title.toUpperCase())) {
						return driver;
					}
				}
			} catch (NoSuchWindowException | NullPointerException e) {

			}
		}
		return driver;
	}

	public void waitUntilWindowExists(WebDriver driver, String window) {
		int time = 0;
		boolean found = false;
		while (!found)
			for (String winHandle : driver.getWindowHandles()) {
				try {
					// switch focus of WebDriver to the next found window handle
					// (that's your newly opened window)
					driver.switchTo().window(winHandle); 
					if (driver.getTitle().toUpperCase().contains(window.toUpperCase())) {
						found = true;
						break;
					}
				} catch (NoSuchWindowException | NullPointerException e) {

				}
			}
		Sleeper.sleep(1000);
		time++;

		if (time == WebDriverSetup.getDefaultTestTimeout())
			TestReporter.assertTrue(found, "The new window ["+window+"] was not found after ["+String.valueOf(WebDriverSetup.getDefaultTestTimeout())+"] seconds.");
	}

	public WebDriver swapToParentWindow(WebDriver driver) {
		driver.switchTo().window(currentWindow); // switch back to the original
													// window
		return driver;
	}

	public static void ieKiller() throws Exception {
		final String KILL = "taskkill /IM ";
		String processName = "IEDriverServer.exe"; // IE process
		Runtime.getRuntime().exec(KILL + processName);
		Thread.sleep(3000); // Allow OS to kill the process
	}

	/**
	 * @summary Swaps to a window with a specified title after waiting a
	 *          specified number of seconds for the window to display. Pass in a
	 *          number of seconds (1,2 etc), and the title of the window you
	 *          wish to switch to. Do not need to pass in the whole title, can
	 *          be a part of it such as "Lilo". Can also pass in a null value
	 *          ("") if the window does not have a title. Returns a true if the
	 *          window was found & switched to and false if not
	 * @version Created 10/29/2014
	 * @author Jessica Marshall
	 * @param driver,
	 *            title, numOfWaitSeconds
	 * @throws NA
	 * @return true/false
	 */
	public static boolean swapToWindowWithTimeout(WebDriver driver, String title, int numOfWaitSeconds) {
		int count = 0;
		// wait for the window count to be greater than 1
		while (driver.getWindowHandles().size() == 1) {
			Sleeper.sleep(1000);

			if (count > (numOfWaitSeconds))
				return false;
			count++;
		}
		try{
				
			for (String winHandle : driver.getWindowHandles()) {
	
				driver.switchTo().window(winHandle);
				// code to handle if the title of the window you expect to switch to
				// is null
				//System.out.println(driver.getTitle());
				//System.out.println(driver.getCurrentUrl());
				if (title.equals("")) {
					if (driver.getTitle().toUpperCase().equals(""))
						return true;
				}
				// code to handle a non null tile of the winodw you wish to switch
				// to
				if (driver.getTitle().toUpperCase().contains(title.toUpperCase())) {
					return true;
				}
			}
		}catch(NoSuchWindowException e){}
		
		return false;
	}

	/**
	 * Use FluentWait to halt the script until the window with the desired text in the title is active
	 * to return true. If the window is not found in the default timeout, return false. 
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, title
	 * @return 	true/false
	 */
	public static boolean waitUntilWindowExistsWithTitle(WebDriver driver, String windowNameOrHandle){
		int timeout = (int) Constants.PAGE_TIMEOUT;

		
	    try{
	    	WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExtendedExpectedConditions.findWindowWithTitleAndSwitchToIt(windowNameOrHandle));
	    }catch(TimeoutException e){
	    	return false;
	    }
	    return true;
	}

	/**
	 * Use FluentWait to halt the script until the window with the desired text is contained in the title and 
	 * active to return true. If the window is not found in the default timeout, return false. 
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, title
	 * @return 	true/false
	 */
	public static boolean waitUntilWindowExistsTitleContains(WebDriver driver, String windowNameOrHandle){
		int timeout = (int)  Constants.PAGE_TIMEOUT;
		return 	waitUntilWindowExistsTitleContains(driver, windowNameOrHandle, timeout);	
	}
	public static boolean waitUntilWindowExistsTitleContains(WebDriver driver, String windowNameOrHandle, int timeout){
		try{
			if(driver.getTitle().contains(windowNameOrHandle)) return true;
		}catch (NoSuchWindowException nswe){}
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExtendedExpectedConditions.findWindowContainsTitleAndSwitchToIt(windowNameOrHandle));
	    }catch(TimeoutException e){
	    	return false;
	    }
	    return true;
	}
	/**
	 * Use FluentWait to halt the script until the window with the desired regex pattern is matched in the 
	 * title and the window is active to return true. If the window is not found in the default timeout, return false. 
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, title
	 * @return 	true/false
	 */
	public static boolean waitUntilWindowExistsTitleMatches(WebDriver driver, String regex){
		return waitUntilWindowExistsTitleMatches(driver, regex,(int) Constants.PAGE_TIMEOUT );
	}
	
	/**
	 * Use FluentWait to halt the script until the window with the desired regex pattern is matched in the 
	 * title and the window is active to return true. If the window is not found in the default timeout, return false. 
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, title, timeout
	 * @return 	true/false
	 */
	public static boolean waitUntilWindowExistsTitleMatches(WebDriver driver, String regex, int timeout){
	    try{
	    	WebDriverWait wait = new WebDriverWait(driver, timeout);
	    	wait.until(ExtendedExpectedConditions.findWindowMatchesTitleAndSwitchToIt(regex));
	    }catch( TimeoutException e){
	    	return false;
	    }
	    return true;
	}

	/**
	 * Use FluentWait to halt the script until the specified number of windows are found
	 * @version Created 01/15/2016
	 * @author 	Justin Phlegar
	 * @param 	driver, expectedNumberOfWindows
	 * @return 	true/false
	 */
	public static boolean waitUntilNumberOfWindowsAre(WebDriver driver, int expectedNumberOfWindows){
		int timeout = (int)  Constants.PAGE_TIMEOUT;
		return waitUntilNumberOfWindowsAre(driver, expectedNumberOfWindows, timeout);
	}
	
	public static boolean waitUntilNumberOfWindowsAre(WebDriver driver, int expectedNumberOfWindows,int timeout){
		try{
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
		}catch(TimeoutException e){
	//		TestReporter.log("FALSE: Syncing to number of windows: " + expectedNumberOfWindows);
			return false;
		}
	//	TestReporter.log("TRUE: Syncing to number of windows: " + expectedNumberOfWindows);
		return true;
	}
	public static boolean waitUntilUrlContains(WebDriver driver, String url, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public static boolean waitUntilUrlMatches(WebDriver driver, String url, int timeout){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.urlMatches(url));
	}
	
}
