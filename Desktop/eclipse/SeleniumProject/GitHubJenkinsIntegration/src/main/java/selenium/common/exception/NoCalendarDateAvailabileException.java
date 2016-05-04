package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Michael.Yardley@Disney.com
 */
public class NoCalendarDateAvailabileException extends NoAvailabilityException {

    private static final long serialVersionUID = 1L;

    public NoCalendarDateAvailabileException() {
        super("Calendar date not available");
    }
    
    public NoCalendarDateAvailabileException(String message) {
        super("Calendar date not available: " + message);
    }
    
    public NoCalendarDateAvailabileException(String message, Throwable cause) {
        super("Calendar date not available: " + message, cause);
    }
    
    public NoCalendarDateAvailabileException(WebDriver driver) {
        super("Calendar date not available: ", driver);
    }
    
    public NoCalendarDateAvailabileException(String message, WebDriver driver) {
        super("Calendar date not available: " + message, driver);
    }
    
    public NoCalendarDateAvailabileException(String message, Throwable cause, WebDriver driver) {
        super("Calendar date not available: " + message, cause, driver);
    }
}

