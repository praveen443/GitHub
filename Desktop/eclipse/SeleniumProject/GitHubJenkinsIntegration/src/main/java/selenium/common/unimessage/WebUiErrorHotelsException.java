package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorHotelsException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorHotelsException() {
        super("Hotels Error");
    }
    
    public WebUiErrorHotelsException(String message) {
        super("Hotels Error: " + message);
    }
    
    public WebUiErrorHotelsException(String message, Throwable cause) {
        super("Hotels Error: " + message, cause);
    }
    
    public WebUiErrorHotelsException(WebDriver driver) {
        super("Hotels Error: ", driver);
    }
    
    public WebUiErrorHotelsException(String message, WebDriver driver) {
        super("Hotels Error: " + message, driver);
    }
    
    public WebUiErrorHotelsException(String message, Throwable cause, WebDriver driver) {
        super("Hotels Error: " + message, cause, driver);
    }
}


