package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorDiningException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorDiningException() {
        super("Dining Error");
    }
    
    public WebUiErrorDiningException(String message) {
        super("Dining Error: " + message);
    }
    
    public WebUiErrorDiningException(String message, Throwable cause) {
        super("Dining Error: " + message, cause);
    }
    
    public WebUiErrorDiningException(WebDriver driver) {
        super("Dining Error: ", driver);
    }
    
    public WebUiErrorDiningException(String message, WebDriver driver) {
        super("Dining Error: " + message, driver);
    }
    
    public WebUiErrorDiningException(String message, Throwable cause, WebDriver driver) {
        super("Dining Error: " + message, cause, driver);
    }
}


