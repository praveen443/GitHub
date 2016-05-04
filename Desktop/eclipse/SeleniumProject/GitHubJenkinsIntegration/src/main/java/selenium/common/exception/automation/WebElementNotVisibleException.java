package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class WebElementNotVisibleException extends AutomationException {

    private static final long serialVersionUID = 1L;

    public WebElementNotVisibleException() {
        super("Web element(s) not found");
    }
    
    public WebElementNotVisibleException(String message) {
        super("Web element(s) not found: " + message);
    }
    
    public WebElementNotVisibleException(String message, Throwable cause) {
        super("Web element(s) not found: " + message, cause);
    }
    
    public WebElementNotVisibleException(WebDriver driver) {
        super("Web element(s) not found: ", driver);
    }
    
    public WebElementNotVisibleException(String message, WebDriver driver) {
        super("Web element(s) not found: " + message, driver);
    }
    
    public WebElementNotVisibleException(String message, Throwable cause, WebDriver driver) {
        super("Web element(s) not found: " + message, cause, driver);
    }
}


