package apps.dreams.PleaseWait;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import core.WindowHandler;
import core.interfaces.impl.internal.ElementFactory;
import utils.Sleeper;

public class PleaseWaitWindow {

	public static PleaseWaitWindow initialize(WebDriver driver) {
	    return ElementFactory.initElements(driver, PleaseWaitWindow.class);	        
	}
    
    public void windowIsVisible(WebDriver driver, String window) {
    	try{
    		windowIsVisibleWithTimeOut(driver,window,20);
    	}catch(NoSuchWindowException nswe){
    		
    	}
 
    }
    
    public void windowIsVisibleWithTimeOut(WebDriver driver, String window, int timeout) {
    	boolean isVisible = false;
    	String parentWindow = driver.getTitle();
    	int timer = 0;
    	
    	do{
    		try{
    			swapToWindow(window, driver);
    			if(driver.getTitle().equalsIgnoreCase(window)){
    				isVisible = true;
    			}else{
    				isVisible = false;
    			}
    		}finally{
    			
    		}
    		Sleeper.sleep(1000);
    		timer++;
    		if(timer == timeout){
    			driver.quit();
    		}
    	}while(isVisible == true);
    	swapToWindow(parentWindow, driver);
    	while(driver.getWindowHandles().contains(window)){
    		swapToWindow(window, driver);
    		Sleeper.sleep(500);
    	}
    }

	public void swapToWindow(String app, WebDriver driver){
		//Utility.setCurrentWindow(driver);
		new WindowHandler().swapToWindow(driver, app);
	}
}

