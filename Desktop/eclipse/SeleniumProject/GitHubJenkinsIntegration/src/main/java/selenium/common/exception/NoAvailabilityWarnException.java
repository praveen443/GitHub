package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class NoAvailabilityWarnException extends NoAvailabilityException {

    private static final long serialVersionUID = 1L;

    public NoAvailabilityWarnException() {
        super("No availability warning");
    }
    
    public NoAvailabilityWarnException(String message) {
        super("No availability warning: " + message);
    }
    
    public NoAvailabilityWarnException(String message, Throwable cause) {
        super("No availability warning: " + message, cause);
    }
    
    public NoAvailabilityWarnException(WebDriver driver) {
        super("No availability warning: ", driver);
    }
    
    public NoAvailabilityWarnException(String message, WebDriver driver) {
        super("No availability warning: " + message, driver);
    }
    
    public NoAvailabilityWarnException(String message, Throwable cause, WebDriver driver) {
        super("No availability warning: " + message, cause, driver);
    }
}


