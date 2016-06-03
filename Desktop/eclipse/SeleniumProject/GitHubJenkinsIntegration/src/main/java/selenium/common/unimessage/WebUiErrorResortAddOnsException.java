package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorResortAddOnsException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorResortAddOnsException() {
        super("Resort Add-Ons Error");
    }
    
    public WebUiErrorResortAddOnsException(String message) {
        super("Resort Add-Ons Error: " + message);
    }
    
    public WebUiErrorResortAddOnsException(String message, Throwable cause) {
        super("Resort Add-Ons Error: " + message, cause);
    }
    
    public WebUiErrorResortAddOnsException(WebDriver driver) {
        super("Resort Add-Ons Error: ", driver);
    }
    
    public WebUiErrorResortAddOnsException(String message, WebDriver driver) {
        super("Resort Add-Ons Error: " + message, driver);
    }
    
    public WebUiErrorResortAddOnsException(String message, Throwable cause, WebDriver driver) {
        super("Resort Add-Ons Error: " + message, cause, driver);
    }
}


