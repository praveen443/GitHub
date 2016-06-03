package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class PageObjectNotInitializedException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public PageObjectNotInitializedException() {
        super("Page Object is not initialized");
    }
    
    public PageObjectNotInitializedException(String message) {
        super("Page Object is not initialized: " + message);
    }
    
    public PageObjectNotInitializedException(String message, Throwable cause) {
        super("Page Object is not initialized: " + message, cause);
    }
    
    public PageObjectNotInitializedException(WebDriver driver) {
        super("Page Object is not initialized: ", driver);
    }
    
    public PageObjectNotInitializedException(String message, WebDriver driver) {
        super("Page Object is not initialized: " + message, driver);
    }
    
    public PageObjectNotInitializedException(String message, Throwable cause, WebDriver driver) {
        super("Page Object is not initialized: " + message, cause, driver);
    }
}

