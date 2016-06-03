package apps.lilo.processingYourRequest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.WebDriverSetup;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import utils.Sleeper;


public class ProcessingYourRequestPopup {
	private WebDriver driver;
	
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ProcessingYourRequestPopup(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
	}
	public ProcessingYourRequestPopup initialize() {
	    return ElementFactory.initElements(driver, ProcessingYourRequestPopup.class);	        
	}
	
	//Create Link object for Manage Arrivals-Arrival Tab
    @FindBy(id="processingDisneyLogo")
    private Element eleProcessingRequest;
    
    public  void popupIsVisible() {
    	Sleeper.sleep(500);
    	initialize();
    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	eleProcessingRequest.syncHidden(driver,20, true);
    	driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
    }
    
    public  void popupIsVisible(int timeout) {
    	Sleeper.sleep(500);
    	initialize();
    	driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	eleProcessingRequest.syncHidden(driver,timeout, true);
    	driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
    }
}
