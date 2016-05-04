package apps.dreams.PleaseWait;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import apps.alc.pleaseWait.PleaseWaitWindow;
import core.WebDriverSetup;

public class PleaseWait {

	public static void WaitForPleaseWait(WebDriver driver){
		new PleaseWaitWindow().windowIsVisible(driver, "Please Wait");
	}
	
	public static void WaitForPleaseWaitWithTimeout(WebDriver driver){
		int timeout = WebDriverSetup.getDefaultTestTimeout();
		try{
			new PleaseWaitWindow().windowIsVisibleWithTimeOut(driver, "Please Wait", timeout);
		}catch(NoSuchWindowException nswes){
			
		}
	}

}

