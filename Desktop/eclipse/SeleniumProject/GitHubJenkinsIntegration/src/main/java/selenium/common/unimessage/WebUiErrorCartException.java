package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorCartException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorCartException() {
        super("Cart Error");
    }
    
    public WebUiErrorCartException(String message) {
        super("Cart Error: " + message);
    }
    
    public WebUiErrorCartException(String message, Throwable cause) {
        super("Cart Error: " + message, cause);
    }
    
    public WebUiErrorCartException(WebDriver driver) {
        super("Cart Error: ", driver);
    }
    
    public WebUiErrorCartException(String message, WebDriver driver) {
        super("Cart Error: " + message, driver);
    }
    
    public WebUiErrorCartException(String message, Throwable cause, WebDriver driver) {
        super("Cart Error: " + message, cause, driver);
    }
}


