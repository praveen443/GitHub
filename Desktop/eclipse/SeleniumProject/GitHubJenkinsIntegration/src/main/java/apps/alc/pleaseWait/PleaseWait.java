package apps.alc.pleaseWait;

import org.openqa.selenium.WebDriver;

import selenium.common.Constants;

public class PleaseWait {
	public static void WaitForPleaseWait(WebDriver driver){
		PleaseWaitWindow.windowIsVisible(driver, (int) Constants.ELEMENT_TIMEOUT);
	}
	
	public static void WaitForPleaseWait(WebDriver driver, int timeout){
		PleaseWaitWindow.windowIsVisible(driver, timeout);
	}
	
	public static void WaitForPleaseWait(WebDriver driver, int timeout, boolean fail){
		PleaseWaitWindow.windowIsVisible(driver, timeout, fail);
	}

	public static void WaitForPleaseWaitWithTimeout(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
}
