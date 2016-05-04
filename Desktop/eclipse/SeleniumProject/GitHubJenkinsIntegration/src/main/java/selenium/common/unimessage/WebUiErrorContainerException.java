package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorContainerException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorContainerException() {
        super("Error Container Exception");
    }
    
    public WebUiErrorContainerException(String message) {
        super("Error Container Exception: " + message);
    }
    
    public WebUiErrorContainerException(String message, Throwable cause) {
        super("Error Container Exception: " + message, cause);
    }
    
    public WebUiErrorContainerException(WebDriver driver) {
        super("Error Container Exception: ", driver);
    }
    
    public WebUiErrorContainerException(String message, WebDriver driver) {
        super("Error Container Exception: " + message, driver);
    }
    
    public WebUiErrorContainerException(String message, Throwable cause, WebDriver driver) {
        super("Error Container Exception: " + message, cause, driver);
    }
}


