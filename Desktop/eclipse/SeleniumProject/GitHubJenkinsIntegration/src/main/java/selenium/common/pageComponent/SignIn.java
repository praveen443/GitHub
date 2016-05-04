package selenium.common.pagecomponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.common.Utils;
import selenium.common.XPath;
import selenium.common.exception.AutomationException;
import selenium.common.pagecomponent.info.SignInInfo;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class SignIn extends PageComponent {
    
    WebElement email;
    WebElement key;
    WebElement signInButton;
    WebElement cancelButton;
    WebElement createAccountLink;
    
    public SignIn(WebDriver driver) throws Exception {
        
        super(driver);
        
        email = waitForVisibleElement("//input[@id='loginPageUsername']");
        
        key = waitForVisibleElement("//input[@id='loginPagePassword']");
        
        signInButton = waitForVisibleElement("//*[@id='loginPageSubmitButton']");
        
        cancelButton = waitForVisibleElement("//*[@id='loginPageCancelButton']/..");
        
        // Special handling required for cart vs others.
        createAccountLink = waitForVisibleElement("//*[@id='signInBottomInformationContainer']" + 
                                                  "//*[contains(@class,'createAccount') or contains(text(),'Create an account')]");
    }
    
    /**
     * Should be able to handle cart, login, and dining login prompts.
     * 
     * @param info
     * @throws Exception
     */
    public void inputSignInInfo(SignInInfo info) throws Exception {
        
        if(info == null ||
            info.getEmail() == null || info.getKey() == null || 
            info.getEmail().isEmpty() || info.getKey().isEmpty()) {
            
            throw new AutomationException(
                    "Failed to populate sign-in information, SignInInfo is invalid",
                    driver);
            
        }
        
        email.sendKeys(info.getEmail());
        key.sendKeys(info.getKey());
    }
    
    public void clickSignInButton() throws Exception {
        Utils.moveToAndClick(driver, signInButton);
        XPath.waitForElementDisappear(driver, "//*[@id='loginPageSubmitButton']");
    }
    
    public void clickCancelButton() throws Exception {
        Utils.moveToAndClick(driver, cancelButton);
    }
    
    public void clickCreateAccountLink() throws Exception {
        Utils.moveToAndClick(driver, createAccountLink);
    }
    
    public void clickContinueWithoutSigningIn() throws Exception  {
        WebElement element = waitForElement(
                "//div[@id='signInBottomInformationContainer']" +
                "//a[contains(text(),'Continue without signing in')]");
        Utils.moveToAndClick(driver, element);
    }
}
