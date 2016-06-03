package selenium.common.exception.automation;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.AutomationException;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class PageObjectInvalidCastException extends AutomationException {
    
    private static final long serialVersionUID = 1L;
    
    public PageObjectInvalidCastException() {
        super("Invalid cast from a Page Object class");
    }
    
    public PageObjectInvalidCastException(String message) {
        super("Invalid cast from a Page Object class: " + message);
    }
    
    public PageObjectInvalidCastException(String message, Throwable cause) {
        super("Invalid cast from a Page Object class: " + message, cause);
    }
    
    public PageObjectInvalidCastException(WebDriver driver) {
        super("Invalid cast from a Page Object class: ", driver);
    }
    
    public PageObjectInvalidCastException(String message, WebDriver driver) {
        super("Invalid cast from a Page Object class: " + message, driver);
    }
    
    public PageObjectInvalidCastException(String message, Throwable cause, WebDriver driver) {
        super("Invalid cast from a Page Object class: " + message, cause, driver);
    }
    
    public PageObjectInvalidCastException(Class<?> class1, Class<?> class2) {
        super("Invalid cast from a Page Object class: " + 
                class1.getName() + " != " + class2.getName());
    }
    
    public PageObjectInvalidCastException(Class<?> class1, Class<?> class2, Throwable cause, WebDriver driver) {
        super("Invalid cast from a Page Object class: " + 
                class1.getName() + " != " + class2.getName(), cause, driver);
    }
    
    public PageObjectInvalidCastException(Class<?> class1, Class<?> class2, WebDriver driver) {
        super("Invalid cast from a Page Object class: " + 
                class1.getName() + " != " + class2.getName(), driver);
    }
}


