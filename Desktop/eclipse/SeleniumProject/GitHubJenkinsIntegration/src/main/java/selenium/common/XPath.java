/*package selenium.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


*//**
 * Many of these methods were originally a part of the Utils class.  For some
 * support for Internet Explorer, these have been moved out to help organize
 * XPath support vs Css support.
 * 
 * @author SonHuy.Pham@Disney.com
 *//*
public class XPath {
    
    *//**
     * Prefer {@link #waitForElement(WebDriver, String)} instead.
     * @param driver
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static WebElement find(WebDriver driver, String xpath) throws Exception {
        return XPath.waitForElement(driver, xpath, 1);
    }
    
    *//**
     * Prefer {@link #waitAndClick(WebDriver, String)} instead.
     * @param driver
     * @param xpath
     * @throws Exception
     *//*
    public static void findAndClick(WebDriver driver, String xpath) throws Exception {
        WebElement element = XPath.find(driver, xpath);
        Utils.moveToAndClick(driver, element);
    }
    
    public static void waitAndClick(WebDriver driver, String xpath) throws Exception {
        WebElement element = XPath.waitForVisibleElement(driver, xpath);
        Utils.moveToAndClick(driver, element);
    }
    
    public static void waitAndClick(WebDriver driver, String xpath, long timeout) throws Exception {
        WebElement element = XPath.waitForVisibleElement(driver, xpath, timeout);
        Utils.moveToAndClick(driver, element);
    }
    
    *//**
     * Finds a single element regardless of visibility.  Default timeout is 
     * GLOBAL_DRIVER_TIMEOUT, the usual 60 seconds.
     * 
     * @param driver
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static WebElement waitForElement(WebDriver driver, String xpath) throws Exception {
        return XPath.waitForElement(driver, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * Finds a single element regardless of visibility.
     * 
     * @param driver
     * @param xpath
     * @param timeout
     * @return
     * @throws Exception
     *//*
    public static WebElement waitForElement(WebDriver driver, String xpath, long timeout) throws Exception {
        return Utils.waitForElement(driver, By.xpath(xpath), timeout);
    }

    *//**
     * Waits and finds a single visible element specified by xpath.  Default
     * wait is 60 seconds.
     * 
     * @param driver
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static WebElement waitForVisibleElement(WebDriver driver, String xpath) throws Exception {
        return XPath.waitForVisibleElement(driver, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * Waits and finds a single visible element specified by xpath.
     * 
     * @param driver
     * @param xpath
     * @param timeout
     * @return
     * @throws Exception
     *//*
    public static WebElement waitForVisibleElement(WebDriver driver, String xpath, long timeout) throws Exception {
        return Utils.waitForVisibleElement(driver, By.xpath(xpath), timeout);
    }

    *//**
     * Wait and find an element relative to a parent element.  Visibility not
     * taken into account.  Waits for 60 seconds by default.
     * 
     * @param driver
     * @param parent
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static WebElement waitForElement(WebDriver driver, WebElement parent, String xpath) throws Exception {
        return XPath.waitForElement(driver, parent, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * Wait and find an element relative to a parent element.  Visibility is 
     * not taken into account.
     * 
     * @param driver
     * @param parent
     * @param xpath
     * @param timeout
     * @return
     * @throws Exception
     *//*
    public static WebElement waitForElement(WebDriver driver, WebElement parent, String xpath, long timeout) throws Exception {
        return Utils.waitForElement(driver, parent, By.xpath(xpath), timeout);
    }

    *//**
     * @see {@link XPath#waitForElementExtinct(WebDriver, String, long)}
     * 
     * @param driver
     * @param xpath
     * @throws Exception
     *//*
    public static void waitForElementExtinct(WebDriver driver, String xpath) throws Exception {
        XPath.waitForElementExtinct(driver, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * Unlike {@link XPath#waitForElementDisappear(WebDriver, String)}, this will 
     * ensure that the elements identified by the xpath expression are not
     * contained in the DOM at all.
     * 
     * @param driver
     * @param xpath
     * @param timeout
     * @throws Exception
     *//*
    public static void waitForElementExtinct(WebDriver driver, String xpath, long timeout) throws Exception {
        Utils.waitForElementExtinct(driver, By.xpath(xpath), timeout);
    }

    *//**
     * Waits until all elements located by xpath is gone or is no longer 
     * displayed on the DOM.  Waits for 60 second by default.
     * 
     * @param driver
     * @param xpath
     * @throws Exception
     *//*
    public static void waitForElementDisappear(WebDriver driver, String xpath) throws Exception {
        XPath.waitForElementDisappear(driver, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * Waits until all elements located by xpath is gone or is no longer 
     * displayed on the DOM.
     * 
     * @param driver
     * @param xpath
     * @param timeout
     * @throws Exception
     *//*
    public static void waitForElementDisappear(WebDriver driver, String xpath, long timeout) throws Exception {
        Utils.waitForElementDisappear(driver, By.xpath(xpath), timeout);
    }

    *//**
     * @see XPath#waitForElements(WebDriver, String, long)
     * @param driver
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static List<WebElement> waitForElements(WebDriver driver, String xpath) throws Exception {
        return XPath.waitForElements(driver, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * Returns a list of web elements specified by the xpath expression.
     * Elements do not need to be visible in the browser and also differs from
     * {@link XPath#waitForAnyElements(WebDriver, String, long)} because it will
     * throw {@link WebElementNotFoundException} if an exception is raised.
     * 
     * @param driver
     * @param xpath
     * @param timeout
     * @return
     * @throws Exception
     *//*
    public static List<WebElement> waitForElements(WebDriver driver, String xpath, long timeout) throws Exception {
        return Utils.waitForElements(driver, By.xpath(xpath), timeout);
    }

    *//**
     * @see XPath#waitForAnyElements(WebDriver, String, long)
     * @param driver
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static List<WebElement> waitForAnyElements(WebDriver driver, String xpath) throws Exception {
        return XPath.waitForAnyElements(driver, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * This will not throw any exceptions that occur as a result of waiting 
     * for elements to be found.  Will return a list of elements regardless  
     * of visibility.
     * 
     * @param driver
     * @param xpath
     * @param timeout
     * @return
     * @throws Exception
     *//*
    public static List<WebElement> waitForAnyElements(WebDriver driver, String xpath, long timeout) throws Exception {
        return Utils.waitForAnyElements(driver, By.xpath(xpath), timeout);
    }

    *//**
     * @see XPath#waitForAnyElements(WebDriver, WebElement, String, long)
     * @param driver
     * @param parent
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static List<WebElement> waitForAnyElements(WebDriver driver, WebElement parent, String xpath) throws Exception {
        return XPath.waitForAnyElements(driver, parent, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * Waits for any elements that matches the xpath expression relative 
     * to the parent element.  Does not check for visibility nor does it throw
     * as a result of finding an empty set.
     * 
     * @param driver
     * @param parent
     * @param xpath
     * @param timeout
     * @return
     * @throws Exception
     *//*
    public static List<WebElement> waitForAnyElements(WebDriver driver, WebElement parent, String xpath, long timeout) throws Exception {
        return Utils.waitForAnyElements(driver, parent, By.xpath(xpath), timeout);
    }

    *//**
     * @see XPath#waitForVisibleElements(WebDriver, String, long)
     * @param driver
     * @param xpath
     * @return
     * @throws Exception
     *//*
    public static List<WebElement> waitForVisibleElements(WebDriver driver, String xpath) throws Exception {
        return XPath.waitForVisibleElements(driver, xpath, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    *//**
     * This will return a list of elements that are visible.  In some cases, 
     * some elements returned may be stale, usually resulting from AJAX.
     * 
     * @param driver
     * @param xpath
     * @param timeout
     * @return
     * @throws Exception Throws if nothing is found - for a less strict 
     * version of this call, see {@link waitForAnyElements}
     *//*
    public static List<WebElement> waitForVisibleElements(WebDriver driver, String xpath, long timeout) throws Exception {
        return Utils.waitForVisibleElements(driver, By.xpath(xpath), timeout);
    }

    *//**
     * Scroll page down to specified element
     * @param driver 
     * @param xpath The expression of the element to scroll down to
     * @throws Exception
     *//*
    public static void scrollTo(WebDriver driver, String xpath) throws Exception {
        WebElement element = waitForElement(driver, xpath);
        Utils.scrollToElement(driver, element);
    }

}


*/