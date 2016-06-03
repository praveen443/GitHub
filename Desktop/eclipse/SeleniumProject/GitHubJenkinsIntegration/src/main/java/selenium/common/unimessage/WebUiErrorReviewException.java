package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorReviewException extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorReviewException() {
        super("Review Error");
    }
    
    public WebUiErrorReviewException(String message) {
        super("Review Error: " + message);
    }
    
    public WebUiErrorReviewException(String message, Throwable cause) {
        super("Review Error: " + message, cause);
    }
    
    public WebUiErrorReviewException(WebDriver driver) {
        super("Review Error: ", driver);
    }
    
    public WebUiErrorReviewException(String message, WebDriver driver) {
        super("Review Error: " + message, driver);
    }
    
    public WebUiErrorReviewException(String message, Throwable cause, WebDriver driver) {
        super("Review Error: " + message, cause, driver);
    }
}


