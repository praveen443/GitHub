package core.interfaces.impl.internal;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.WebDriverSetup;
import core.interfaces.Element;
import selenium.common.Constants;
import utils.Sleeper;
import utils.TestReporter;

public class PageLoaded {	
	private WebDriver driver = null;
	private Class clazz = null;
	private int timeout = 0;
	StopWatch stopwatch = new StopWatch();

	public PageLoaded(){
		this.timeout = WebDriverSetup.getDefaultTestTimeout();
	}
	
	public PageLoaded(WebDriver driver){
	    this.driver = driver;
		this.timeout = WebDriverSetup.getDefaultTestTimeout();
	}
	private void initialize() {
	    ElementFactory.initElements(driver, clazz);	        
	}
	
	public boolean isPageHTMLLoaded(Class clazz, WebDriver driver, Element obj){
		this.driver = driver;
		this.clazz = clazz;		
		int timeout = 20;
		int count = 0;

		//WebDriverWait wait = new WebDriverWait(driver, 1);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
		try{
			// && wait.until(ExpectedConditions.elementToBeClickable(obj)) != null
			while(!obj.elementWired()){
				if (count == timeout){
					break;
				}else{
					Sleeper.sleep(1000);
					count++;
					initialize();
				}
			}
		}catch( Exception e){}
		
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.MILLISECONDS);
		if (count < timeout){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This waits for a specified element on the page to be found on the page by the driver
	 * Uses the default test time out set by WebDriverSetup
	 * 
	 * @param	class	the class calling this method - used so can initialize the page class repeatedly
	 * @param 	driver	The webDriver
	 * @param	obj		The element you are waiting to display on the page
	 * @version	10/16/2014
	 * @author 	Justin Phlegar
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isElementLoaded(Class clazz, Element obj){
		this.clazz = clazz;		
		int count = 0;
		
		//set the timeout for looking for an element to 1 second as we are doing a loop and then refreshing the elements
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		try{
			
			while(!obj.elementWired()){
				if (count == this.timeout){
					break;
				}else{
					count++;
					initialize();
				}
			}
		}catch( NullPointerException | NoSuchElementException |StaleElementReferenceException e){
			// do nothing
		}
		
		//set the timeout for looking for an element back to the default timeout
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		
		if (count < this.timeout){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * This waits for a specified element on the page to be found on the page by the driver
	 * Uses the default test time out set by WebDriverSetup
	 * 
	 * @param	class	the class calling this method - used so can initialize the page class repeatedly
	 * @param 	driver	The webDriver
	 * @param	obj		The element you are waiting to display on the page
	 * @version	10/16/2014
	 * @author 	Justin Phlegar
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isElementLoaded(Class clazz, WebDriver driver, Element obj){
		this.driver = driver;
		this.clazz = clazz;		
		return isElementLoaded(clazz, obj);	
	}
	
	/**
	 * Overloaded method where you can specify the timeout 
	 * This waits for a specified element on the page to be found on the page by the driver
	 * 
	 * 
	 * @param	class	the class calling this method - used so can initialize the page class repeatedly
	 * @param 	driver	The webDriver
	 * @param	obj		The element you are waiting to display on the page
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isElementLoaded(Class clazz, WebDriver driver, Element obj, int timeout){
		this.timeout = timeout;
		return isElementLoaded(clazz, driver, obj);
	}
	
	/**
	 * This uses the HTML DOM readyState property to wait until a page is finished loading.  It will wait for
	 * the ready state to be either 'interactive' or 'complete'.  
	 * 
	 * @param	class	the class calling this method - used so can initialize the page class repeatedly
	 * @param 	driver	The webDriver
	 * @param	obj		The element you are waiting to display on the page
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isDomInteractive(){
		int count = 0;
		Object obj = null;

		do {
			//this returns a boolean
			obj = ((JavascriptExecutor) driver).executeScript(
                    "var result = document.readyState; return (result == 'complete' || result == 'interactive');");
			if (count == this.timeout)
				break;
			else{
				Sleeper.sleep(1000);
				count++;
			}
		} while (obj.equals(false));
		
		
		if (count < this.timeout*2){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This uses the HTML DOM readyState property to wait until a page is finished loading.  It will wait for
	 * the ready state to be either 'interactive' or 'complete'.  
	 * 
	 * @param	class	the class calling this method - used so can initialize the page class repeatedly
	 * @param 	driver	The webDriver
	 * @param	obj		The element you are waiting to display on the page
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isDomInteractive(WebDriver driver){
		this.driver = driver;
		return isDomInteractive();
	}
	
	/**
	 * Overloaded method - gives option of specifying a timeout.
	 * This uses the HTML DOM readyState property to wait until a page is finished loading.  It will wait for
	 * the ready state to be either 'interactive' or 'complete'.  
	 * 
	 * @param 	driver	The webDriver
	 * @param	timeout	Integer value of number seconds to wait for a page to finish loaded before quiting
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isDomInteractive(WebDriver driver, int timeout){
		this.timeout = timeout;
		return isDomInteractive(driver);
	}
	
	/**
	 * This uses protractor method to wait until a page is ready - notifyWhenNoOutstandingRequests
	 * 
	 * @param 	driver	The webDriver
	 * @version	10/16/2014
	 * @author 	Justin Phlegar
	 * 
	 */
	public void isAngularComplete() {
	    try{
		((JavascriptExecutor) driver).executeAsyncScript("var callback = arguments[arguments.length - 1];" +
    				"angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
	    }catch (WebDriverException wde){
		TestReporter.logFailure("Unable to perform Angular sync. This is most likely because the $browser service is not injected within the Angular Controller. Performing a IsDomComplete instead");
		isDomComplete();
	    }

    }
	

	/**
	 * A more strict version of isDomInteractive.  
	 * This uses the HTML DOM readyState property to wait until a page is finished loading.  It will wait for
	 * the ready state to be 'complete'.  
	 * 
	 * @param	class	the class calling this method - used so can initialize the page class repeatedly
	 * @param 	driver	The webDriver
	 * @param	obj		The element you are waiting to display on the page
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isDomComplete(){
		int count = 0;
		Object obj = null;
		
		do {
			//this returns a boolean
			obj = ((JavascriptExecutor) driver).executeScript(
                    "var result = document.readyState; return (result == 'complete');");
			if (count == this.timeout)
				return false;
			else{
				Sleeper.sleep(1000);
				count++;
			}
		} while (obj.equals(false));
		return true;
	}
	/**
	 * A more strict version of isDomInteractive.  
	 * This uses the HTML DOM readyState property to wait until a page is finished loading.  It will wait for
	 * the ready state to be 'complete'.  
	 * 
	 * @param	class	the class calling this method - used so can initialize the page class repeatedly
	 * @param 	driver	The webDriver
	 * @param	obj		The element you are waiting to display on the page
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isDomComplete(WebDriver driver){
		this.driver = driver;
		return isDomComplete();
	}
	
	/**
	 * Overloaded method - gives option of specifying a timeout.
	 * A more strict version of isDomInteractive
	 * This uses the HTML DOM readyState property to wait until a page is finished loading.  It will wait for
	 * the ready state to be 'complete'.  
	 * 
	 * @param 	driver	The webDriver
	 * @param	timeout	Integer value of number seconds to wait for a page to finish loaded before quiting
	 * @version	12/16/2014
	 * @author 	Jessica Marshall
	 * @return 	False if the element is not found after the timeout, true if is found
	 */
	public boolean isDomComplete(WebDriver driver, int timeout){
		this.timeout = timeout;
		return isDomComplete(driver);
	}
	
	public boolean urlToContain(WebDriver driver, String url){
		try{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.urlContains(url));
		}catch(TimeoutException te) {
			return false;
		}
		return true;
	}
}


