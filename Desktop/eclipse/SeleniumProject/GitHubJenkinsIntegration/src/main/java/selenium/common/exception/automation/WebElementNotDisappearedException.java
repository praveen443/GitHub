package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class WebElementNotDisappearedException extends AutomationException {

    private static final long serialVersionUID = 1L;

    public WebElementNotDisappearedException() {
        super("Web element(s) not found");
    }
    
    public WebElementNotDisappearedException(String message) {
        super("Web element(s) not found: " + message);
    }
    
    public WebElementNotDisappearedException(String message, Throwable cause) {
        super("Web element(s) not found: " + message, cause);
    }
    
    public WebElementNotDisappearedException(WebDriver driver) {
        super("Web element(s) not found: ", driver);
    }
    
    public WebElementNotDisappearedException(String message, WebDriver driver) {
        super("Web element(s) not found: " + message, driver);
    }
    
    public WebElementNotDisappearedException(String message, Throwable cause, WebDriver driver) {
        super("Web element(s) not found: " + message, cause, driver);
    }
}

