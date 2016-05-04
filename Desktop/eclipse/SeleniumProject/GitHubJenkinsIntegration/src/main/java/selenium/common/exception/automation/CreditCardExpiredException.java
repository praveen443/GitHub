package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class CreditCardExpiredException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public CreditCardExpiredException() {
        super("Credit Card Expired");
    }
    
    public CreditCardExpiredException(String message) {
        super("Credit Card Expired: " + message);
    }
    
    public CreditCardExpiredException(String message, Throwable cause) {
        super("Credit Card Expired: " + message, cause);
    }
    
    public CreditCardExpiredException(WebDriver driver) {
        super("Credit Card Expired: ", driver);
    }
    
    public CreditCardExpiredException(WebDriver driver, Throwable cause) {
        super("Credit Card Expired: ", cause, driver);
    }
    
    public CreditCardExpiredException(String message, WebDriver driver) {
        super("Credit Card Expired: " + message, driver);
    }
    
    public CreditCardExpiredException(String message, Throwable cause, WebDriver driver) {
        super("Credit Card Expired: " + message, cause, driver);
    }
}


