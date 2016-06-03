package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorConfirmationException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorConfirmationException() {
        super("Confirmation Error");
    }
    
    public WebUiErrorConfirmationException(String message) {
        super("Confirmation Error: " + message);
    }
    
    public WebUiErrorConfirmationException(String message, Throwable cause) {
        super("Confirmation Error: " + message, cause);
    }
    
    public WebUiErrorConfirmationException(WebDriver driver) {
        super("Confirmation Error: ", driver);
    }
    
    public WebUiErrorConfirmationException(String message, WebDriver driver) {
        super("Confirmation Error: " + message, driver);
    }
    
    public WebUiErrorConfirmationException(String message, Throwable cause, WebDriver driver) {
        super("Confirmation Error: " + message, cause, driver);
    }
}


