package Utils;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandler {
	private WebDriver driver;

	/**
	 * @summary: To get the main window handle.
	 * @author: Praveen Namburi, @version: Created 15-06-2016
	 * @return
	 */
	public String getMainWindowHandle(){
		return driver.getWindowHandle();
	}
	
	/**
	 * @summary: To get the current window title.
	 * @author: Praveen Namburi, @version: Created 15-06-2016
	 * @return
	 */
	public String getCurrentWindowTitle(){
		return driver.getTitle();
	}
	
	/**
	 * @summary: To close all the other windows except the main window.
	 * @author: Praveen Namburi, @version: Created 15-06-2016
	 */
	public static boolean closeAllOtherWindows(WebDriver driver,String openWindowHandle){
		//boolean switchedToMainWindow;
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}
		
		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
		
}
