package selenium.common.pageComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import selenium.common.Utils;
import selenium.common.XPath;

public abstract class PageComponent {

    protected WebDriver driver;
    
    public PageComponent(WebDriver driver) {
        this.driver = driver;
    }
    
    protected WebElement waitForElement(String xpath) throws Exception {
        return XPath.waitForElement(driver, xpath);
    }
    
    protected WebElement waitForVisibleElement(String xpath) throws Exception {
        return XPath.waitForVisibleElement(driver, xpath);
    }
    
    protected Actions moveToAndClick(WebElement element) throws Exception {
        return Utils.moveToAndClick(driver, element);
    }
    
    protected Actions sendKeys(WebElement element, CharSequence keysToSend) throws Exception {
        return Utils.sendKeys(driver, element, keysToSend);
    }
}


