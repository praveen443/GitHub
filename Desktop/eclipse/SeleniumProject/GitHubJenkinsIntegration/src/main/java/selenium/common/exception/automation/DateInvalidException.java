package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class DateInvalidException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public DateInvalidException() {
        super("Invalid Date");
    }
    
    public DateInvalidException(String message) {
        super("Invalid Date: " + message);
    }
    
    public DateInvalidException(String message, Throwable cause) {
        super("Invalid Date: " + message, cause);
    }
    
    public DateInvalidException(WebDriver driver) {
        super("Invalid Date: ", driver);
    }
    
    public DateInvalidException(WebDriver driver, Throwable cause) {
        super("Invalid Date: ", cause, driver);
    }
    
    public DateInvalidException(String message, WebDriver driver) {
        super("Invalid Date: " + message, driver);
    }
    
    public DateInvalidException(String message, Throwable cause, WebDriver driver) {
        super("Invalid Date: " + message, cause, driver);
    }
}

