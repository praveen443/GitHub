package apps.lilo.processingYourRequest;

import org.openqa.selenium.WebDriver;

// create two classes to allow static methods and simpler consectutive calls
public class ProcessingYourRequest {
	public  void WaitForProcessRequest(WebDriver driver){
		//new ProcessingYourRequestPopup().initialize(driver);
		new ProcessingYourRequestPopup(driver).popupIsVisible();
	}
	
	public  void WaitForProcessRequest(WebDriver driver, int timeout){
		//new ProcessingYourRequestPopup().initialize(driver);
		new ProcessingYourRequestPopup(driver).popupIsVisible(timeout);
	}
}
