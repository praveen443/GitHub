package selenium.common;

/***********************************************************************************************************************
 * FileName - WebDriverHelper.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Author: kaiy001 $ $Revision: #1 $ $Change: 715510 $ $Date: Jul 19, 2012 $
 ***********************************************************************************************************************/

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelper {

    private WebDriver driver;
    private boolean usingVerbosePrintouts = false;
    
    public WebDriverHelper(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean isUsingVerbosePrintouts() {
        return usingVerbosePrintouts;
    }
    
    public void setUsingVerbosePrintouts(boolean usingVerbosePrintouts) {
        this.usingVerbosePrintouts = usingVerbosePrintouts;
    }
    
    /**
     * Waits for an element without a timeout option
     * 
     * @deprecated
     * @param method
     *            Locator Strategy
     * @param key
     *            Locator Strategy string
     * @return The WebElement of the found element, null if not found
     * @throws Exception
     * @throws IllegalLocatorException
     *             If the element could not be located or was not visible
     * @throws IllegalLocatorException
     *             If illegal locator used
     */
    @Deprecated
    public WebElement find(int method, String key) throws Exception {
        WebElement output = null;
        String locator = "";

        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Looking for " + key + "... ("
                                     + driver.getCurrentUrl() + ")");
        }

        try {
            switch (method) {
                case Constants.CSS_SELECTOR:
                    locator = "By.cssSelector: " + key;
                    output = driver.findElement(By.cssSelector(key));
                    break;
                case Constants.XPATH:
                    locator = "By.xpath: " + key;
                    output = driver.findElement(By.xpath(key));
                    break;
                case Constants.ID:
                    locator = "By.id: " + key;
                    output = driver.findElement(By.id(key));
                    break;
                case Constants.CLASS_NAME:
                    locator = "By.className: " + key;
                    output = driver.findElement(By.className(key));
                    break;
                case Constants.LINK_TEXT:
                    locator = "By.linkText: " + key;
                    output = driver.findElement(By.linkText(key));
                    break;
                case Constants.PARTIAL_LINK_TEXT:
                    locator = "By.partialLinkText: " + key;
                    output = driver.findElement(By.partialLinkText(key));
                    break;
                case Constants.NAME:
                    locator = "By.name: " + key;
                    output = driver.findElement(By.name(key));
                    break;
                case Constants.TAG_NAME:
                    locator = "By.tagName: " + key;
                    output = driver.findElement(By.tagName(key));
                    break;
                default:
                    output = null;
                    break;
            }
        } catch (NoSuchElementException nsee) {
            ExceptionClass.throwNoSuchElementException(locator, driver, nsee);
        } catch (IllegalStateException ile) {
            ExceptionClass.throwIllegalLocatorException(driver, ile);
        }

        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Found " + key);
        }
        return output;
    }

    /**
     * Waits for an element without a timeout option
     * @deprecated
     * @param method
     *            Locator Strategy
     * @param key
     *            Locator Strategy string
     * @return The list of WebElements of the found element, null if not found
     * @throws Exception
     * @throws IllegalLocatorException
     *             If the element could not be located or was not visible
     * @throws IllegalLocatorException
     *             If illegal locator used
     */
    @Deprecated
    public List<WebElement> finds(int method, String key) throws Exception {
        List<WebElement> output = null;
        String locator = "";

        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Looking for " + key + "... ("
                                     + driver.getCurrentUrl() + ")");
        }

        try {
            switch (method) {
                case Constants.CSS_SELECTOR:
                    locator = "By.cssSelector: " + key;
                    output = driver.findElements(By.cssSelector(key));
                    break;
                case Constants.XPATH:
                    locator = "By.xpath: " + key;
                    output = driver.findElements(By.xpath(key));
                    break;
                case Constants.ID:
                    locator = "By.id: " + key;
                    output = driver.findElements(By.id(key));
                    break;
                case Constants.CLASS_NAME:
                    locator = "By.className: " + key;
                    output = driver.findElements(By.className(key));
                    break;
                case Constants.LINK_TEXT:
                    locator = "By.linkText: " + key;
                    output = driver.findElements(By.linkText(key));
                    break;
                case Constants.PARTIAL_LINK_TEXT:
                    locator = "By.partialLinkText: " + key;
                    output = driver.findElements(By.partialLinkText(key));
                    break;
                case Constants.NAME:
                    locator = "By.name: " + key;
                    output = driver.findElements(By.name(key));
                    break;
                case Constants.TAG_NAME:
                    locator = "By.tagName: " + key;
                    output = driver.findElements(By.tagName(key));
                    break;
                default:
                    output = null;
                    break;
            }
        } catch (NoSuchElementException nsee) {
            ExceptionClass.throwNoSuchElementException(locator, driver, nsee);
        } catch (IllegalStateException ile) {
            ExceptionClass.throwIllegalLocatorException(driver, ile);
        }

        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Found " + key);
        }
        return output;
    }

    /**
     * Waits for an element without a timeout option
     * @deprecated
     * @param element
     *            WebElement to find element from
     * @param method
     *            Locator Strategy
     * @param key
     *            Locator Strategy string
     * @return The list of WebElements of the found element, null if not found
     * @throws Exception
     * @throws IllegalLocatorException
     *             If the element could not be located or was not visible
     * @throws IllegalLocatorException
     *             If illegal locator used
     */
    @Deprecated
    public List<WebElement> finds(WebElement element, int method, String key) throws Exception {
        List<WebElement> output = null;
        String locator = "";

        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Looking for " + key + "... ("
                                     + driver.getCurrentUrl() + ")");
        }

        try {
            switch (method) {
                case Constants.CSS_SELECTOR:
                    locator = "By.cssSelector: " + key;
                    output = element.findElements(By.cssSelector(key));
                    break;
                case Constants.XPATH:
                    locator = "By.xpath: " + key;
                    output = element.findElements(By.xpath(key));
                    break;
                case Constants.ID:
                    locator = "By.id: " + key;
                    output = element.findElements(By.id(key));
                    break;
                case Constants.CLASS_NAME:
                    locator = "By.className: " + key;
                    output = element.findElements(By.className(key));
                    break;
                case Constants.LINK_TEXT:
                    locator = "By.linkText: " + key;
                    output = element.findElements(By.linkText(key));
                    break;
                case Constants.PARTIAL_LINK_TEXT:
                    locator = "By.partialLinkText: " + key;
                    output = element.findElements(By.partialLinkText(key));
                    break;
                case Constants.NAME:
                    locator = "By.name: " + key;
                    output = element.findElements(By.name(key));
                    break;
                case Constants.TAG_NAME:
                    locator = "By.tagName: " + key;
                    output = element.findElements(By.tagName(key));
                    break;
                default:
                    output = null;
                    break;
            }
        } catch (NoSuchElementException nsee) {
            ExceptionClass.throwNoSuchElementException(locator, driver, nsee);
        } catch (IllegalStateException ile) {
            ExceptionClass.throwIllegalLocatorException(driver, ile);
        }

        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Found " + key);
        }
        return output;
    }

    /**
     * Waits for an element with a timeout option
     *
     * @param method
     *            Locator Strategy
     * @param key
     *            Locator Strategy string
     * @param timeout
     *            Number of seconds for timeout
     * @return The WebElement of the found element, null if not found
     * @throws Exception
     * @throws IllegalLocatorException
     *             If illegal locator used
     */
    public WebElement waitAndFind(int method, String key, long timeout) throws Exception {
        WebElement output = null;
        
        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Looking for " + key + "... ("
                                     + driver.getCurrentUrl() + ")");
        }

        switch (method) {
            case Constants.CSS_SELECTOR:
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector(key)));
                break;
            case Constants.XPATH:
                // This is for repeated finds
                // output = waitForElementByXpath(driver, locator, timeout, key);
                
                //Utils.waitForDomInteractive(driver);
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath(key)));
                break;
            case Constants.ID:
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.id(key)));
                break;
            case Constants.CLASS_NAME:
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.className(key)));
                break;
            case Constants.LINK_TEXT:
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.linkText(key)));
                break;
            case Constants.PARTIAL_LINK_TEXT:
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.partialLinkText(key)));
                break;
            case Constants.NAME:
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.name(key)));
                break;
            case Constants.TAG_NAME:
                output = (new WebDriverWait(driver, timeout)).until(ExpectedConditions
                        .visibilityOfElementLocated(By.tagName(key)));
                break;
            default:
                output = null;
                break;
        }

        if(usingVerbosePrintouts) {
            Log.log(driver).info("(" + Log.lookupTestName(driver) + ") " + "Found " + key);
        }
        return output;
    }
}

