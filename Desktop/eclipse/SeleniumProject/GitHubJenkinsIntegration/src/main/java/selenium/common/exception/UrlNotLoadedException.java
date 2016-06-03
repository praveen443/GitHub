package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class UrlNotLoadedException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public UrlNotLoadedException() {
        super("Url Failed to Load");
    }
    
    public UrlNotLoadedException(String message) {
        super("Url Failed to Load: " + message);
    }
    
    public UrlNotLoadedException(String message, Throwable cause) {
        super("Url Failed to Load: " + message, cause);
    }
    
    public UrlNotLoadedException(WebDriver driver) {
        super("Url Failed to Load: ", driver);
    }
    
    public UrlNotLoadedException(String message, WebDriver driver) {
        super("Url Failed to Load: " + message, driver);
    }
    
    public UrlNotLoadedException(String message, Throwable cause, WebDriver driver) {
        super("Url Failed to Load: " + message, cause, driver);
    }
}


