package selenium.common.exception;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com 
 */
public class TestCaseFailedException extends AutomationException {
    
    private static final long serialVersionUID = 1L;
    WebDriver driver = null;
    
    public TestCaseFailedException() {
        super("Failed Test Case/Step");
    }
    
    public TestCaseFailedException(Throwable cause) {
        super("Failed Test Case/Step", cause);
    }
    
    public TestCaseFailedException(String message) {
        super("Failed Test Case/Step: " + message);
    }
    
    public TestCaseFailedException(String message, Throwable cause) {
        super("Failed Test Case/Step: " + message, cause);
    }
    
    public TestCaseFailedException(WebDriver driver) {
        super("Failed Test Case/Step: ", driver);
        this.driver = driver;
    }
    
    public TestCaseFailedException(WebDriver driver, Throwable cause) {
        super("Failed Test Case/Step: ", cause, driver);
        this.driver = driver;
    }
    
    public TestCaseFailedException(String message, WebDriver driver) {
        super("Failed Test Case/Step: " + message, driver);
        this.driver = driver;
    }
    
    public TestCaseFailedException(String message, Throwable cause, WebDriver driver) {
        super("Failed Test Case/Step: " + message, cause, driver);
        this.driver = driver;
    }
    
    public <T extends AutomationException> void assertEquals(int lhs, int rhs) throws Exception {
        super.assertEquals(driver, (lhs == rhs), this.getClass());
    }
    
    public <T extends AutomationException> void assertEquals(String lhs, String rhs) throws Exception {
        if(lhs == null || rhs == null) {
            super.assertEquals(driver, false, this.getClass());
        }
        super.assertEquals(driver, lhs.equals(rhs), this.getClass());
    }
    
    public <T extends AutomationException> void assertEquals(boolean value) throws Exception {
        super.assertEquals(driver, value, this.getClass());
    }
}


