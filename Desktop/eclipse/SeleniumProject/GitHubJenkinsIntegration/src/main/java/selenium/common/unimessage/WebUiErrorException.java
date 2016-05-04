package selenium.common.unimessage;

package com.disney.selenium.common.exception.uimessage;

import org.openqa.selenium.WebDriver;

import utils.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class WebUiErrorException extends AutomationException {
    
    private static final long serialVersionUID = 1L;

    public WebUiErrorException() {
        super("Web UI Error");
    }
    
    public WebUiErrorException(String message) {
        super("Web UI Error: " + message);
    }
    
    public WebUiErrorException(String message, Throwable cause) {
        super("Web UI Error: " + message, cause);
    }
    
    public WebUiErrorException(WebDriver driver) {
        super("Web UI Error: ", driver);
    }
    
    public WebUiErrorException(String message, WebDriver driver) {
        super("Web UI Error: " + message, driver);
    }
    
    public WebUiErrorException(String message, Throwable cause, WebDriver driver) {
        super("Web UI Error: " + message, cause, driver);
    }
}


