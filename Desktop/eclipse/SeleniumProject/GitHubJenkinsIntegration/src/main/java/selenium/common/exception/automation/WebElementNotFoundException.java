package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class WebElementNotFoundException extends AutomationException {

    private static final long serialVersionUID = 1L;

    public WebElementNotFoundException() {
        super("Web element(s) not found");
    }
    
    public WebElementNotFoundException(String message) {
        super("Web element(s) not found: " + message);
    }
    
    public WebElementNotFoundException(String message, Throwable cause) {
        super("Web element(s) not found: " + message, cause);
    }
    
    public WebElementNotFoundException(WebDriver driver) {
        super("Web element(s) not found: ", driver);
    }
    
    public WebElementNotFoundException(String message, WebDriver driver) {
        super("Web element(s) not found: " + message, driver);
    }
    
    public WebElementNotFoundException(String message, Throwable cause, WebDriver driver) {
        super("Web element(s) not found: " + message, cause, driver);
    }
}


