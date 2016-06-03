package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorDiningRsvpException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorDiningRsvpException() {
        super("Dining Rsvp Error");
    }
    
    public WebUiErrorDiningRsvpException(String message) {
        super("Dining Rsvp Error: " + message);
    }
    
    public WebUiErrorDiningRsvpException(String message, Throwable cause) {
        super("Dining Rsvp Error: " + message, cause);
    }
    
    public WebUiErrorDiningRsvpException(WebDriver driver) {
        super("Dining Rsvp Error: ", driver);
    }
    
    public WebUiErrorDiningRsvpException(String message, WebDriver driver) {
        super("Dining Rsvp Error: " + message, driver);
    }
    
    public WebUiErrorDiningRsvpException(String message, Throwable cause, WebDriver driver) {
        super("Dining Rsvp Error: " + message, cause, driver);
    }
}


