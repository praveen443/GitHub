package selenium.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.common.exception.automation.WebElementNotFoundException;

/**
 * Due to expanded browser support for Internet Explorer, many XPath
 * methods are now broken out into a CSS version that should help not only
 * with IE8 but also promote a faster alternative to XPath.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class Css {

    /**
     * Prefer {@link #waitForElement(WebDriver, String)} instead.
     * @param driver
     * @param css
     * @return
     * @throws Exception
     */
    public static WebElement find(WebDriver driver, String css) throws Exception {
        return Css.waitForElement(driver, css, 1);
    }
    
    /**
     * Prefer {@link #waitAndClick(WebDriver, String)} instead.
     * @param driver
     * @param css
     * @throws Exception
     */
    public static void findAndClick(WebDriver driver, String css) throws Exception {
        WebElement element = Css.find(driver, css);
        Utils.moveToAndClick(driver, element);
    }
    
    public static void waitAndClick(WebDriver driver, String css) throws Exception {
        WebElement element = Css.waitForVisibleElement(driver, css);
        Utils.moveToAndClick(driver, element);
    }
    
    public static void waitAndClick(WebDriver driver, String css, long timeout) throws Exception {
        WebElement element = Css.waitForVisibleElement(driver, css, timeout);
        Utils.moveToAndClick(driver, element);
    }
    /**
     * Finds a single element regardless of visibility.  Default timeout is 
     * GLOBAL_DRIVER_TIMEOUT, the usual 60 seconds.
     * 
     * @param driver
     * @param css
     * @return
     * @throws Exception
     */
    public static WebElement waitForElement(WebDriver driver, String css) throws Exception {
        return waitForElement(driver, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }

    
    /**
     * Finds a single element regardless of visibility.
     * 
     * @param driver
     * @param css
     * @param timeout
     * @return
     * @throws Exception
     */
    public static WebElement waitForElement(WebDriver driver, String css, long timeout) throws Exception {
        return Utils.waitForElement(driver, By.cssSelector(css), timeout);
    }
    
    /**
     * Waits and finds a single visible element specified by css.  Default
     * wait is 60 seconds.
     * 
     * @param driver
     * @param css
     * @return
     * @throws Exception
     */
    public static WebElement waitForVisibleElement(WebDriver driver, String css) throws Exception {
        return waitForVisibleElement(driver, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * Waits and finds a single visible element specified by css.
     * 
     * @param driver
     * @param css
     * @param timeout
     * @return
     * @throws Exception
     */
    public static WebElement waitForVisibleElement(WebDriver driver, String css, long timeout) throws Exception {
        return Utils.waitForVisibleElement(driver, By.cssSelector(css), timeout);
    }
    
    /**
     * Wait and find an element relative to a parent element.  Visibility not
     * taken into account.  Waits for 60 seconds by default.
     * 
     * @param driver
     * @param parent
     * @param css
     * @return
     * @throws Exception
     */
    public static WebElement waitForElement(WebDriver driver, WebElement parent, String css) throws Exception {
        return waitForElement(driver, parent, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * Wait and find an element relative to a parent element.  Visibility is 
     * not taken into account.
     * 
     * @param driver
     * @param parent
     * @param css
     * @param timeout
     * @return
     * @throws Exception
     */
    public static WebElement waitForElement(WebDriver driver, WebElement parent, String css, long timeout) throws Exception {
        return Utils.waitForElement(driver, parent, By.cssSelector(css), timeout);
    }
    
    /**
     * @see {@link #waitForElementExtinct(WebDriver, String, long)}
     * 
     * @param driver
     * @param css
     * @throws Exception
     */
    public static void waitForElementExtinct(WebDriver driver, String css) throws Exception {
        waitForElementExtinct(driver, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * Unlike {@link #waitForElementDisappear(WebDriver, String)}, this will 
     * ensure that the elements identified by the css expression are not
     * contained in the DOM at all.
     * 
     * @param driver
     * @param css
     * @param timeout
     * @throws Exception
     */
    public static void waitForElementExtinct(WebDriver driver, String css, long timeout) throws Exception {
        Utils.waitForElementExtinct(driver, By.cssSelector(css), timeout);
    }
    
    /**
     * Waits until all elements located by css is gone or is no longer 
     * displayed on the DOM.  Waits for 60 second by default.
     * 
     * @param driver
     * @param css
     * @throws Exception
     */
    public static void waitForElementDisappear(WebDriver driver, String css) throws Exception {
        waitForElementDisappear(driver, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * Waits until all elements located by css is gone or is no longer 
     * displayed on the DOM.
     * 
     * @param driver
     * @param css
     * @param timeout
     * @throws Exception
     */
    public static void waitForElementDisappear(WebDriver driver, String css, long timeout) throws Exception {
        Utils.waitForElementDisappear(driver, By.cssSelector(css), timeout);
    }
    
    /**
     * @see #waitForElements(WebDriver, String, long)
     * @param driver
     * @param css
     * @return
     * @throws Exception
     */
    public static List<WebElement> waitForElements(WebDriver driver, String css) throws Exception {
        return waitForElements(driver, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * Returns a list of web elements specified by the css expression.
     * Elements do not need to be visible in the browser and also differs from
     * {@link #waitForAnyElements(WebDriver, String, long)} because it will
     * throw {@link WebElementNotFoundException} if an exception is raised.
     * 
     * @param driver
     * @param css
     * @param timeout
     * @return
     * @throws Exception
     */
    public static List<WebElement> waitForElements(WebDriver driver, String css, long timeout) throws Exception {
        return Utils.waitForElements(driver, By.cssSelector(css), timeout);
    }
    
    /**
     * @see #waitForAnyElements(WebDriver, String, long)
     * @param driver
     * @param css
     * @return
     * @throws Exception
     */
    public static List<WebElement> waitForAnyElements(WebDriver driver, String css) throws Exception {
        return waitForAnyElements(driver, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * This will not throw any exceptions that occur as a result of waiting 
     * for elements to be found.  Will return a list of elements regardless  
     * of visibility.
     * 
     * @param driver
     * @param css
     * @param timeout
     * @return
     * @throws Exception
     */
    public static List<WebElement> waitForAnyElements(WebDriver driver, String css, long timeout) throws Exception {
        return Utils.waitForAnyElements(driver, By.cssSelector(css), timeout);
    }
    
    /**
     * @see #waitForAnyElements(WebDriver, WebElement, String, long)
     * @param driver
     * @param parent
     * @param css
     * @return
     * @throws Exception
     */
    public static List<WebElement> waitForAnyElements(WebDriver driver, WebElement parent, String css) throws Exception {
        return waitForAnyElements(driver, parent, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * Waits for any elements that matches the css expression relative 
     * to the parent element.  Does not check for visibility nor does it throw
     * as a result of finding an empty set.
     * 
     * @param driver
     * @param parent
     * @param css
     * @param timeout
     * @return
     * @throws Exception
     */
    public static List<WebElement> waitForAnyElements(WebDriver driver, WebElement parent, String css, long timeout) throws Exception {
        return Utils.waitForAnyElements(driver, parent, By.cssSelector(css), timeout);
    }
    
    /**
     * @see #waitForVisibleElements(WebDriver, String, long)
     * @param driver
     * @param css
     * @return
     * @throws Exception
     */
    public static List<WebElement> waitForVisibleElements(WebDriver driver, String css) throws Exception {
        return waitForVisibleElements(driver, css, Constants.GLOBAL_DRIVER_TIMEOUT);
    }
    
    /**
     * This will return a list of elements that are visible.  In some cases, 
     * some elements returned may be stale, usually resulting from AJAX.
     * 
     * @param driver
     * @param css
     * @param timeout
     * @return
     * @throws Exception Throws if nothing is found - for a less strict 
     * version of this call, see {@link #waitForAnyElements(WebDriver, String)}
     */
    public static List<WebElement> waitForVisibleElements(WebDriver driver, String css, long timeout) throws Exception {
        return Utils.waitForVisibleElements(driver, By.cssSelector(css), timeout);
    }
    
    /**
     * Scroll page down to specified element
     * @param driver 
     * @param css The expression of the element to scroll down to
     * @throws Exception
     */
    public static void scrollTo(WebDriver driver, String css) throws Exception {
        WebElement element = waitForElement(driver, css);
        Utils.scrollToElement(driver, element);
    }
}

