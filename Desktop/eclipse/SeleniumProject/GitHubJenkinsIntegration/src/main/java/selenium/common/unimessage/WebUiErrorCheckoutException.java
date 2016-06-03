package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorCheckoutException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorCheckoutException() {
        super("Checkout Error");
    }
    
    public WebUiErrorCheckoutException(String message) {
        super("Checkout Error: " + message);
    }
    
    public WebUiErrorCheckoutException(String message, Throwable cause) {
        super("Checkout Error: " + message, cause);
    }
    
    public WebUiErrorCheckoutException(WebDriver driver) {
        super("Checkout Error: ", driver);
    }
    
    public WebUiErrorCheckoutException(String message, WebDriver driver) {
        super("Checkout Error: " + message, driver);
    }
    
    public WebUiErrorCheckoutException(String message, Throwable cause, WebDriver driver) {
        super("Checkout Error: " + message, cause, driver);
    }
}

