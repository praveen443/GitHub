package selenium.exception;

import java.lang.reflect.Constructor;
import org.openqa.selenium.WebDriver;

import selenium.common.Constants;
import selenium.common.Utils;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class AutomationException extends Exception {

    private static final long serialVersionUID = 1L;

    public AutomationException() {
        super("QA Automation");
    }
    
    public AutomationException(String message) {
        super("QA Automation: " + message);
    }
    
    public AutomationException(String message, Throwable cause) {
        super("QA Automation: " + message, cause);
    }
    
    public AutomationException(WebDriver driver) {
        super(generateExceptionMsg(driver, ""));
    }
    
    public AutomationException(String message, WebDriver driver) {
        super(generateExceptionMsg(driver, message));
    }
    
    public AutomationException(String message, Throwable cause, WebDriver driver) {
        super(generateExceptionMsg(driver, message), cause);
    }
    
    /**
     * Will attempt to include the Conversation Id into the exception message
     * to enable us to extract that information directly from Bamboo reports
     * to then ultimately query logs (automated).
     * 
     * @param driver
     * @param message
     * @return
     */
    private static String generateExceptionMsg(WebDriver driver, String message) {
        
        if(driver == null) {
            return "QA Automation";
        }
        
        String msg = "QA Automation: " + (message == null ? "" : message);
        
        String url = driver.getCurrentUrl();
        
        if(url != null && !url.isEmpty()) {
            msg = msg.concat(Constants.NEW_LINE).concat(url);
        }
        
        Utils utils = Utils.getInstance(driver);
        if(utils != null) {
            String conversationId = utils.getConversationId();
            
            if(conversationId != null && !conversationId.isEmpty()) {
                msg = msg.concat(Constants.NEW_LINE).concat("[[ConvoId:").concat(conversationId).concat("]]");
            }
        }
        
        return msg;
    }
    
    public <T extends AutomationException> void assertEquals(WebDriver driver, boolean value, Class<T> exceptionClass) throws Exception {
        
        if(value) {
            return;
        }
        
        Constructor<?> list[] = exceptionClass.getClass().getConstructors();

        for(int i = 0; i < list.length; ++i) {

            Constructor<?> construct = list[i];
            Class<?> params[] = construct.getParameterTypes();

            // We're targeting the default Exception constructor that also
            // allows the caller to pass in the cause of the exception.
            // 
            // If it turns out that the exception doesn't have a cause, 
            // we'll just use the other default constructor that only takes
            // a string.

            if(params.length == 1 
                    && params[0].isAssignableFrom(WebDriver.class)) {

                @SuppressWarnings("unchecked")
                T exception = (T) construct.newInstance(driver);
                throw exception;
            } 
        }

        T exception = exceptionClass.newInstance();
        throw exception;
    }
}

