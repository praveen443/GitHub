package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class NoDiningAvailabilityException extends NoAvailabilityException {

    private static final long serialVersionUID = 1L;

    public NoDiningAvailabilityException() {
        super("Dining not available");
    }
    
    public NoDiningAvailabilityException(String message) {
        super("Dining not available: " + message);
    }
    
    public NoDiningAvailabilityException(String message, Throwable cause) {
        super("Dining not available: " + message, cause);
    }
    
    public NoDiningAvailabilityException(WebDriver driver) {
        super("Dining not available: ", driver);
    }
    
    public NoDiningAvailabilityException(String message, WebDriver driver) {
        super("Dining not available: " + message, driver);
    }
    
    public NoDiningAvailabilityException(String message, Throwable cause, WebDriver driver) {
        super("Dining not available: " + message, cause, driver);
    }
}


