package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorPageMsgException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorPageMsgException() {
        super("Error Page Msg Exception");
    }
    
    public WebUiErrorPageMsgException(String message) {
        super("Error Page Msg Exception: " + message);
    }
    
    public WebUiErrorPageMsgException(String message, Throwable cause) {
        super("Error Page Msg Exception: " + message, cause);
    }
    
    public WebUiErrorPageMsgException(WebDriver driver) {
        super("Error Page Msg Exception: ", driver);
    }
    
    public WebUiErrorPageMsgException(String message, WebDriver driver) {
        super("Error Page Msg Exception: " + message, driver);
    }
    
    public WebUiErrorPageMsgException(String message, Throwable cause, WebDriver driver) {
        super("Error Page Msg Exception: " + message, cause, driver);
    }
}


