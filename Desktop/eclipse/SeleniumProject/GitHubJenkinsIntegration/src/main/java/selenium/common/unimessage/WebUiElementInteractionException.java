package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.automation.WebElementNotDisappearedException;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class WebUiElementInteractionException extends WebElementNotDisappearedException {

    private static final long serialVersionUID = 1L;

    public WebUiElementInteractionException() {
        super("Failure during element interaction");
    }
    
    public WebUiElementInteractionException(String message) {
        super("Failure during element interaction: " + message);
    }
    
    public WebUiElementInteractionException(String message, Throwable cause) {
        super("Failure during element interaction: " + message, cause);
    }
    
    public WebUiElementInteractionException(WebDriver driver) {
        super("Failure during element interaction: ", driver);
    }
    
    public WebUiElementInteractionException(String message, WebDriver driver) {
        super("Failure during element interaction: " + message, driver);
    }
    
    public WebUiElementInteractionException(String message, Throwable cause, WebDriver driver) {
        super("Failure during element interaction: " + message, cause, driver);
    }
}


