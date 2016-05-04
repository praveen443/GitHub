package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class UnsuccessfulSignInException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public UnsuccessfulSignInException() {
        super("Failed to sign-in");
    }
    
    public UnsuccessfulSignInException(String message) {
        super("Failed to sign-in: " + message);
    }
    
    public UnsuccessfulSignInException(String message, Throwable cause) {
        super("Failed to sign-in: " + message, cause);
    }
    
    public UnsuccessfulSignInException(WebDriver driver) {
        super("Failed to sign-in: ", driver);
    }
    
    public UnsuccessfulSignInException(WebDriver driver, Throwable cause) {
        super("Failed to sign-in: ", cause, driver);
    }
    
    public UnsuccessfulSignInException(String message, WebDriver driver) {
        super("Failed to sign-in: " + message, driver);
    }
    
    public UnsuccessfulSignInException(String message, Throwable cause, WebDriver driver) {
        super("Failed to sign-in: " + message, cause, driver);
    }
}

