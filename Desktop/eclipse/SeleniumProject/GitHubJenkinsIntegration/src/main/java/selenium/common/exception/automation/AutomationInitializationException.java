package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class AutomationInitializationException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public AutomationInitializationException() {
        super("Initialization Failure");
    }
    
    public AutomationInitializationException(String message) {
        super("Initialization Failure: " + message);
    }
    
    public AutomationInitializationException(String message, Throwable cause) {
        super("Initialization Failure: " + message, cause);
    }
    
    public AutomationInitializationException(WebDriver driver) {
        super("Initialization Failure: ", driver);
    }
    
    public AutomationInitializationException(String message, WebDriver driver) {
        super("Initialization Failure: " + message, driver);
    }
    
    public AutomationInitializationException(String message, Throwable cause, WebDriver driver) {
        super("Initialization Failure: " + message, cause, driver);
    }
}


