package selenium.common.unimessage;

import org.openqa.selenium.WebDriver;

/**
 * In general, there may be a need to distinguish a 404 exception from just
 * any other.
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class Http404Exception extends WebUiErrorException {
    
    private static final long serialVersionUID = 1L;

    public Http404Exception() {
        super("Http 404 Exception");
    }
    
    public Http404Exception(String message) {
        super("Http 404 Exception: " + message);
    }
    
    public Http404Exception(String message, Throwable cause) {
        super("Http 404 Exception: " + message, cause);
    }
    
    public Http404Exception(WebDriver driver) {
        super("Http 404 Exception: ", driver);
    }
    
    public Http404Exception(String message, WebDriver driver) {
        super("Http 404 Exception: " + message, driver);
    }
    
    public Http404Exception(String message, Throwable cause, WebDriver driver) {
        super("Http 404 Exception: " + message, cause, driver);
    }
}


