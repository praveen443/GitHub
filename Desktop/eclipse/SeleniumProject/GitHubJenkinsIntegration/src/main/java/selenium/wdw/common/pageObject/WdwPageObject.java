package selenium.wdw.common.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.common.Global;
import selenium.common.Utils;
import selenium.common.enums.WdwUrl;
import selenium.common.pagecomponent.GlobalFooter;
import selenium.common.pagecomponent.GlobalNavigationBar;
import selenium.common.pageobject.PageObject;
import selenium.engine.homepage.HomePage;
import selenium.wdw.common.factory.PageFactory;

/**
 * Base class for "some" page objects.  Contains commonly defined member 
 * variables, objects and methods.
 * 
 * In particular, this class represents the commonly-encountered page - that 
 * is, a page that contains a Global-Navigation-Bar and a Global-Footer section.
 * Variants of PageObjects that don't fit this template should otherwise be 
 * re-implemented using a different base class.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public abstract class WdwPageObject extends PageObject implements GlobalNavigationBar, GlobalFooter {
    
    private static boolean AUTO_REGISTER = true;
    
    public WdwPageObject(WebDriver driver) {
        super(driver);
        
        if(AUTO_REGISTER) {
            registerPageObject();
        }
    }
    
    /**
     * Usually we want to initialize the Factory and affliates by registering
     * explicitly - but for a price in performance, page-objects for WDW can
     * self-register simply upon instantiation.
     * 
     * @param shouldRegisterAutomatically
     */
    public static void setAutoRegister(boolean shouldRegisterAutomatically) {
        AUTO_REGISTER = shouldRegisterAutomatically;
    }
    
    /**
     * A convenience routine to access the Page Factory for initialization.
     */
    @Override
    public void registerPageObject() {
        PageFactory.getInstance(driver).registerPageObject(this);
    }
    
    @Override
    public WebElement retrieveNavBarElement() throws Exception {
        return driver.findElement(By.xpath(
                    "//*[@id='pageContainer']" +
                    "//*[contains(@class,'globalNavigationBarWrapper')]" +
                    "//div[@role='navigation']"));
    }
    
    /**
     * WARNING: Not implemented
     */
    @Override
    public <T extends PageObject> T clickNavBarLogo() {
        return null;
    }
    
    @Override
    public <T extends PageObject> T clickNavBarParksAndTickets() {
        return null;
    }
    
    @Override
    public <T extends PageObject> T clickNavBarPlacesToStay() {
        return null;
    }
    
    @Override
    public <T extends PageObject> T clickNavBarThingsToDo() {
        return null;
    }
    
    /**
     * Assumes that the test navigates to this sub-classes' page from the main
     * Help page - which is why this returns the Help page object.
     * 
     * @return
     * @throws Exception
     */
    @Override
    public <T extends PageObject> T clickNavBarHelp() throws Exception {
        waitAndClickElement("//*[@class='gnbCategory gnbHelp']/a");
        Utils.waitForUrlLoaded(driver, WdwUrl.HELP);
        return PageFactory.getInstance(driver).create();
    }
    
    @Override
    public <T extends PageObject> T clickNavBarCart() throws Exception {
        return null;
    }
    
    @Override
    public <T extends PageObject> T expandParksAndTicketsMenu(String linkText) throws Exception {
        WebElement element = waitForElement("//*[contains(@class,'gnbCategory gnbParksAndTickets')]/a");
        element.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        return PageFactory.getInstance(driver).create();
    }
    
    @Override
    public <T extends PageObject> T expandPlacesToStayMenu(String linkText) throws Exception {
        WebElement element = waitForElement("//*contains(@class,'gnbCategory gnbPlacesToStay')]/a");
        element.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        return PageFactory.getInstance(driver).create();
    }
    
    @Override
    public <T extends PageObject> T expandThingsToDoMenu(String linkText) throws Exception {
        WebElement element = waitForElement("//*[contains(@class,'gnbCategory gnbThingsToDo')]/a");
        element.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        return PageFactory.getInstance(driver).create();
    }
    
    @Override
    public <T extends PageObject> T expandHelpMenu(String linkText) throws Exception {
        WebElement element = waitForElement("//*[contains(@class,'gnbCategory gnbHelp')]/a");
        element.sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));
        return PageFactory.getInstance(driver).create();
    }
    
    @Override
    public WebElement retrieveFooterElement() throws Exception {
        return driver.findElement(By.xpath(
                "//div[contains(@class,'pepGlobalFooter')]" +
                "/div[contains(@class,'Footer')]"));
    }
    
    /**
     * Temporary - will need to return a page object instead.
     * @return
     */
    public HomePage clickWaltDisneyWorldLogo() throws Exception {
        waitAndClickElement("//a[contains(@class,'waltDisneyWorldLogo')]");
        Utils.waitForUrlLoaded(driver, WdwUrl.HOME_PAGE);
        return Global.getGlobalObject(driver).homePageEngine.homePage;
    }
}

