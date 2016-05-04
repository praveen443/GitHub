package selenium.common.exception;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.automation.AutomationInitializationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class LogInitializationException extends AutomationInitializationException {
    
    private static final long serialVersionUID = 1L;

    public LogInitializationException() {
        super("Log Initialization Failure");
    }
    
    public LogInitializationException(String message) {
        super("Log Initialization Failure: " + message);
    }
    
    public LogInitializationException(String message, Throwable cause) {
        super("Log Initialization Failure: " + message, cause);
    }
    
    public LogInitializationException(WebDriver driver) {
        super("Log Initialization Failure: ", driver);
    }
    
    public LogInitializationException(String message, WebDriver driver) {
        super("Log Initialization Failure: " + message, driver);
    }
    
    public LogInitializationException(String message, Throwable cause, WebDriver driver) {
        super("Log Initialization Failure: " + message, cause, driver);
    }
}

