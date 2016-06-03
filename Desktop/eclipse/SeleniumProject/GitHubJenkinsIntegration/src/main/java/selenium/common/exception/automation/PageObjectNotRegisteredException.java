package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class PageObjectNotRegisteredException extends AutomationException {

    private static final long serialVersionUID = 1L;

    public PageObjectNotRegisteredException() {
        super("Page Object is not registered");
    }
    
    public PageObjectNotRegisteredException(String message) {
        super("Page Object is not registered: " + message);
    }
    
    public PageObjectNotRegisteredException(String message, Throwable cause) {
        super("Page Object is not registered: " + message, cause);
    }
    
    public PageObjectNotRegisteredException(WebDriver driver) {
        super("Page Object is not registered: ", driver);
    }
    
    public PageObjectNotRegisteredException(String message, WebDriver driver) {
        super("Page Object is not registered: " + message, driver);
    }
    
    public PageObjectNotRegisteredException(String message, Throwable cause, WebDriver driver) {
        super("Page Object is not registered: " + message, cause, driver);
    }
}


