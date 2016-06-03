package selenium.common.exception;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.automation.AutomationInitializationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebDriverInitializationException extends AutomationInitializationException {
    
    private static final long serialVersionUID = 1L;

    public WebDriverInitializationException() {
        super("Web Driver Initialization Failure");
    }
    
    public WebDriverInitializationException(String message) {
        super("Web Driver Initialization Failure: " + message);
    }
    
    public WebDriverInitializationException(String message, Throwable cause) {
        super("Web Driver Initialization Failure: " + message, cause);
    }
    
    public WebDriverInitializationException(WebDriver driver) {
        super("Web Driver Initialization Failure: ", driver);
    }
    
    public WebDriverInitializationException(String message, WebDriver driver) {
        super("Web Driver Initialization Failure: " + message, driver);
    }
    
    public WebDriverInitializationException(String message, Throwable cause, WebDriver driver) {
        super("Web Driver Initialization Failure: " + message, cause, driver);
    }
}

