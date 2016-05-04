package core.angular;

import org.openqa.selenium.JavascriptExecutor;

import core.WebDriverSetup;

/*
 * Original Code from https://github.com/paul-hammant/ngWebDriver
 */

public class WaitForAngularRequestsToFinish extends WebDriverSetup {

    public static void waitForAngularRequestsToFinish(JavascriptExecutor driver) {
    	//if(!WebDriverSetup.browser.equalsIgnoreCase("IE")){   		    	
    		driver.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
    				"angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    	//}else{
    	//	driver.executeAsyncScript("var callback = arguments[arguments.length - 1];" +
    	//			"angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    	//}
    }
}
