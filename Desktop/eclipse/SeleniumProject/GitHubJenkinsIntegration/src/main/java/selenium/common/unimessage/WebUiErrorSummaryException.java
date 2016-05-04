package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorSummaryException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorSummaryException() {
        super("Error Summary Exception");
    }
    
    public WebUiErrorSummaryException(String message) {
        super("Error Summary Exception: " + message);
    }
    
    public WebUiErrorSummaryException(String message, Throwable cause) {
        super("Error Summary Exception: " + message, cause);
    }
    
    public WebUiErrorSummaryException(WebDriver driver) {
        super("Error Summary Exception: ", driver);
    }
    
    public WebUiErrorSummaryException(String message, WebDriver driver) {
        super("Error Summary Exception: " + message, driver);
    }
    
    public WebUiErrorSummaryException(String message, Throwable cause, WebDriver driver) {
        super("Error Summary Exception: " + message, cause, driver);
    }
}


