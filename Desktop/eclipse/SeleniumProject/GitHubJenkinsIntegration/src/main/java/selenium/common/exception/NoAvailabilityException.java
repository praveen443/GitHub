package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class NoAvailabilityException extends AutomationException {

    private static final long serialVersionUID = 1L;

    public NoAvailabilityException() {
        super("No Availability");
    }
    
    public NoAvailabilityException(String message) {
        super("No Availability: " + message);
    }
    
    public NoAvailabilityException(String message, Throwable cause) {
        super("No Availability: " + message, cause);
    }
    
    public NoAvailabilityException(WebDriver driver) {
        super("No Availability: ", driver);
    }
    
    public NoAvailabilityException(String message, WebDriver driver) {
        super("No Availability: " + message);
    }
    
    public NoAvailabilityException(String message, Throwable cause, WebDriver driver) {
        super("No Availability: " + message, cause, driver);
    }
}
