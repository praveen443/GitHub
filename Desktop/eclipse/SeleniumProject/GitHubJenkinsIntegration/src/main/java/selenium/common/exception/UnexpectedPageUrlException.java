package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class UnexpectedPageUrlException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public UnexpectedPageUrlException() {
        super("Unexpected Page (Url)");
    }
    
    public UnexpectedPageUrlException(String message) {
        super("Unexpected Page (Url): " + message);
    }
    
    public UnexpectedPageUrlException(String message, Throwable cause) {
        super("Unexpected Page (Url): " + message, cause);
    }
    
    public UnexpectedPageUrlException(WebDriver driver) {
        super("Unexpected Page (Url): ", driver);
    }
    
    public UnexpectedPageUrlException(WebDriver driver, Throwable cause) {
        super("Unexpected Page (Url)", cause, driver);
    }
    
    public UnexpectedPageUrlException(String message, WebDriver driver) {
        super("Unexpected Page (Url): " + message, driver);
    }
    
    public UnexpectedPageUrlException(String message, Throwable cause, WebDriver driver) {
        super("Unexpected Page (Url): " + message, cause, driver);
    }
}


