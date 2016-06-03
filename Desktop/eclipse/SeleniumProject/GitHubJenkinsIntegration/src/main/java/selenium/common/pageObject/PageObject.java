package selenium.common.pageObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.common.Constants;
import selenium.common.Log;
import selenium.common.PepUtils;
import selenium.common.Utils;
import selenium.common.XPath;
import selenium.common.exception.AutomationException;
import selenium.common.exception.automation.PageObjectInvalidCastException;
import selenium.common.exception.automation.PageObjectNotInitializedException;
import selenium.common.exception.uimessage.Http404Exception;
import selenium.common.exception.uimessage.Http500Exception;
import selenium.common.exception.uimessage.WebUiErrorPageMsgException;
import selenium.common.exception.uimessage.WebUiPageErrorException;
import selenium.common.pagecomponent.Page;
import wdproqa.testng.RetryConfiguration;

/**
 * Base class for "some" page objects.  Contains commonly defined member 
 * variables, objects and methods.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public abstract class PageObject implements Page, PageObjectPrototype, PageObjectRegistrable {
    
    final protected WebDriver driver;
    private Date timeLastChecked = null;
    private boolean errors = false;
    private boolean pageLoaded = false;
    private boolean errorPageMsg = false;
    private boolean pageError = false;
    private boolean http404Page = false;
    private boolean http500Page = false;
    private boolean httpErrorPage = false;
    private String headingExpression = null;
    private String pageKeyExpression = null;
    protected long timeoutInSeconds = 60;
    
    public PageObject(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Initializes the instance and waits for an specific element to load
     * after waiting for the correct URL to be loaded
     * @param driver WebDriver object
     * @param xpath XPath for the element the page has to wait for
     */
    public PageObject(WebDriver driver, String xpath) {
    	this.driver = driver;
    	try {
    		WebDriverWait waitForURL = new WebDriverWait(driver, timeoutInSeconds);
    		ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
    	          public Boolean apply(WebDriver d) {
    	            return (d.getCurrentUrl().contains(getPageUrl()));
    	          }
	        };
	        waitForURL.until(e);
	        if(!xpath.isEmpty()){
	        	WebDriverWait waitForElement = new WebDriverWait(driver, timeoutInSeconds);
	        	waitForElement.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	        }
		} catch (TimeoutException e) {
			throw new AssertionError("TimeOut reached after "+timeoutInSeconds+" seconds waiting for "+getClass().getSimpleName()+" elements to load.");
		}catch (Exception e) {
			e.printStackTrace();
			throw new AssertionError(getClass().getSimpleName() +" web elements not loading properly");
		}
    }
    
    @Override
    public String toString() {
        
        return super.toString().
                concat("[").
                concat(this.getPageUrl()).
                concat("]");
    }
    
    /**
     * Meant to help initialize WebElements in a uniform way.  Default 
     * implementation does nothing.
     * 
     * @throws Exception
     */
    public void initialize() throws Exception {
    }
    
    /**
     * Special handling for pages that happen to allow REST-ful interactions.
     */
    @Override
    public List<String> getAltPageUrls() {
        return new LinkedList<String>();
    }
    
    @Override
    public String getPageKey() throws Exception {
        String result = "";
        try {
            result = driver.findElement(By.xpath("//meta[@name='pageKey']")).getAttribute("content");
        } catch(Exception ex) {
            Log.log(driver).warning("Unable to extract pageKey");
        }
        return result;
    }
    
    public void savePageKey() throws Exception {
        pageKeyExpression = getPageKey();
        if(pageKeyExpression.isEmpty()) {
            throw new AutomationException(
                    "Failed to save pageKey value from meta elements",
                    driver);
        }
        pageKeyExpression = "//meta[@name='pageKey' and @content=\"" + pageKeyExpression + "\"]";
    }
    
    public void waitForPageKeyChange() throws Exception {
        if(pageKeyExpression == null || pageKeyExpression.isEmpty()) {
            throw new AutomationException(
                    "Failed to pageKey change, value is not set",
                    driver);
        }
        waitForElementDissapear(pageKeyExpression);
    }
    
    @Override
    public List<WebElement> getHeadingElements() {
        List<WebElement> elements = new ArrayList<WebElement>();
        try {
            elements = XPath.waitForVisibleElements(driver, "//h1", 2);
        } catch(Exception ex) {
            // Move on to the next set of elements
        }
        if(elements.isEmpty()) {
            try {
                elements = XPath.waitForVisibleElements(driver, "//h2", 2);
            } catch(Exception ex) {
                // Move on to the next set of elements
            }
            
            if(elements.isEmpty()) {
                try {
                    elements = XPath.waitForVisibleElements(driver, "//h3", 2);
                } catch(Exception ex) {
                    // Move on to the next set of elements
                }
            }
            
            if(elements.isEmpty()) {
                try {
                    elements = XPath.waitForVisibleElements(driver, "//h4", 2);
                } catch(Exception ex) {
                    // Move on to the next set of elements
                }
            }
        }
        return elements;
    }
    
    /**
     * Generates an XPath expression to help wait detect when the page changes
     * or loads.
     * 
     * @return
     */
    public String getHeadingExpression() {
        String expression = "";
        List<WebElement> elements = getHeadingElements();
        for(WebElement element : elements) {
            String text = element.getText().trim();
            if(!text.isEmpty()) {
                expression = "//" + element.getTagName();
                expression += "[contains(text(),\"" + text + "\")]";
                break;
            }
        }
        return expression;
    }
    
    /**
     * This helpful method allows a Page Object to detect any visible e.g. h1 
     * tag and to later detect when content on a page changes (page 
     * changes/loads).
     * 
     * @see waitForHeadingChange
     * @throws Exception
     */
    public void saveHeadingExpression() throws Exception {
        headingExpression = getHeadingExpression();
        if(headingExpression.isEmpty()) {
            throw new AutomationException(
                    "Failed to save XPath expression for a Heading element",
                    driver);
        }
    }
    
    /**
     * Requires that a valid Heading element e.g. h1 has been saved.  This will
     * wait for that element to disappear, effectively waits until the page
     * content has changed.
     * 
     * @see saveHeadingExpression
     * @throws Exception
     */
    public void waitForHeadingChange() throws Exception {
        if(headingExpression == null || headingExpression.isEmpty()) {
            throw new AutomationException(
                    "Failed to wait for Heading element, expression is not set",
                    driver);
        }
        waitForElementDissapear(headingExpression);
    }
    
    /**
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends PageObject> T newInstance() throws Exception {
        // TODO: We need to check that the constructor parameters match up.
        // Take a look at the base Page Object Registrar class for a close-up.
        Object object = this.getClass().getConstructors()[0].newInstance(driver);
        checkInstanceOf(object.getClass(), this.getClass());
        return (T)object;
    }
    
    /**
     * A bit of a work-around the whole Java Generics dance to check to see
     * if class1 is associated with class2.
     * 
     * @param class1
     * @param class2
     * @throws PageObjectInvalidCastException
     */
    protected void checkInstanceOf(Class<?> class1, Class<?> class2) throws PageObjectInvalidCastException {
        if(!class1.isAssignableFrom(class2)) {
            throw new PageObjectInvalidCastException(class1, class2, driver);
        }
    }
    
    /**
     * Convenience routine to jump straight to this Page Object's URL.
     * 
     * @throws Exception
     */
    public void go() throws Exception {
        driver.get(getPageUrl());
        verifyPageObject();
    }
    
    /**
     * In general, this will go out and verify that the DOM, driver's URL, and
     * displaying page is 
     * @throws Exception
     */
    public void verifyPageObject() throws Exception {
        waitForDomComplete();
        waitForUrlLoaded();
        checkWebUiErrors();
        analyzeDebugExecutionTimes();
    }
    
    public void analyzeDebugExecutionTimes() {
        
        // TODO: Using the logger to determine if this test is retrying is 
        // a mess. Will need to clean up please.
        String testName = Log.getLog(driver) != null ? 
               Log.getLog(driver).getTestName() : "";
               
        testName = !testName.isEmpty() 
                && testName.contains("_T") 
                && testName.lastIndexOf("_T") > 0 ? 
                testName.substring(0, testName.lastIndexOf("_T")) : "";
                
        if(!(Constants.ENABLE_DEBUG_MODULE || 
                RetryConfiguration.getInstance().isTestRetrying(testName))) {
            return;
        }
        try {
            String desc;
            List<WebElement> elements;
            
            elements = XPath.waitForAnyElements(driver, 
                    "//table[@id='pepAjaxStatsContainer']" 
                    + "//td[@data-stats-error='true']/..", 1);
            
            for(WebElement e : elements) {
                desc = e.getText().replaceAll("\\s", "");
                Log.log(driver).warning("Detected debug error [" + desc + "]");
            }
            
            elements = XPath.waitForAnyElements(driver, 
                    "//table[@id='pepServerSideStatsContainer']" 
                    + "//td[@data-stats-error='true']/..", 1);
            
            for(WebElement e : elements) {
                desc = e.getText().replaceAll("\\s", "");
                Log.log(driver).warning("Detected debug error [" + desc + "]");
            }
            
        } catch(Exception ex) {
            // Swallowing exception, this is just for logging anyway.
            ex.printStackTrace();
        }
    }
    
    /**
     * The draw-back of this check is that it takes longer to time-out and 
     * perform some of the validation steps.
     */
    public void checkWebUiErrors() throws Exception {
        checkWebUiErrors(true);
    }
    
    /**
     * 
     * @param throwErrors
     * @throws Exception
     */
    public void checkWebUiErrors(boolean throwErrors) throws Exception {
        
        pageLoaded = true;
        timeLastChecked = new Date();
        
        List<Exception> exceptions = PepUtils.checkWebUiErrors(driver, null, this);
        
        if(exceptions!= null && !exceptions.isEmpty()) {
            
            errors = true;
            
            for(Exception e : exceptions) {
                
                if (e instanceof WebUiPageErrorException) {
                    pageError = true;
                    
                } else if(e instanceof WebUiErrorPageMsgException) {
                    errorPageMsg = true;
                    
                } else if(e instanceof Http404Exception) {
                    http404Page = true;
                    httpErrorPage = true;
                    
                } else if (e instanceof Http500Exception) {
                    http500Page = true;
                    httpErrorPage = true;
                    
                } else {
                    httpErrorPage = true;
                }
            }
            
            if(throwErrors) {
                throw exceptions.get(0);
            }
        }
    }
    
    public Date getTimeLastChecked() {
        return new Date(timeLastChecked.getTime());
    }
    
    /**
     * Be sure to call checkErrors() PRIOR.
     * @return
     * @throws PageObjectNotInitializedException
     */
    public boolean hasErrors() throws PageObjectNotInitializedException {
        if(timeLastChecked == null) {
            throw new PageObjectNotInitializedException(driver);
        }
        return errors;
    }
    
    /**
     * Be sure to call checkErrors() PRIOR.
     * @return
     * @throws PageObjectNotInitializedException
     */
    public boolean isPageLoaded() throws PageObjectNotInitializedException {
        if(timeLastChecked == null) {
            throw new PageObjectNotInitializedException(driver);
        }
        return pageLoaded;
    }
    
    /**
     * Be sure to call checkErrors() PRIOR.
     * @return
     * @throws PageObjectNotInitializedException
     */
    public boolean isHttp404Page() throws PageObjectNotInitializedException {
        if(timeLastChecked == null) {
            throw new PageObjectNotInitializedException(driver);
        }
        return http404Page;
    }
    
    /**
     * Be sure to call checkErrors() PRIOR.
     * @return
     * @throws PageObjectNotInitializedException
     */
    public boolean isHttp500Page() throws PageObjectNotInitializedException {
        if(timeLastChecked == null) {
            throw new PageObjectNotInitializedException(driver);
        }
        return http500Page;
    }
    
    /**
     * Be sure to call checkErrors() PRIOR.
     * @return
     * @throws PageObjectNotInitializedException
     */
    public boolean isHttpErrorPage() throws PageObjectNotInitializedException {
        if(timeLastChecked == null) {
            throw new PageObjectNotInitializedException(driver);
        }
        return httpErrorPage;
    }
    
    public boolean isPageError() throws PageObjectNotInitializedException {
        if(timeLastChecked == null) {
            throw new PageObjectNotInitializedException(driver);
        }
        return pageError;
    }
    
    public boolean isErrorPageMsg() throws PageObjectNotInitializedException {
        if(timeLastChecked == null) {
            throw new PageObjectNotInitializedException(driver);
        }
        return errorPageMsg;
    }
    
    public void goBack() throws Exception {
        driver.navigate().back();
    }
    
    /**
     * Uses JavaScript injection to determine if the document's ready-state 
     * flag has been set to "complete"
     * 
     * @throws Exception
     */
    public void waitForDomComplete() throws Exception {
        Utils.waitForDomComplete(driver);
    }
    
    /**
     * Uses JavaScript injection to determine if the document's ready-state 
     * flag has been set to "complete" or "interactive".
     * 
     * @throws Exception
     */
    public void waitForDomInteractive() throws Exception {
        Utils.waitForDomInteractive(driver);
    }
    
    /**
     * Looks at the driver's current URL and asserts that it's been loaded.
     * 
     * @throws Exception
     */
    public void waitForUrlLoaded() throws Exception {
        Utils.waitForUrlLoaded(driver, this.getPageUrl());
    }
    
    /**
     * Waits for a visible element.
     * 
     * @param xpath
     * @return
     * @throws Exception
     */
    public WebElement waitForElement(String xpath) throws Exception {
        return XPath.waitForVisibleElement(driver, xpath);
    }
    
    /**
     * Waits for a visible element and clicks on it.
     * 
     * @param xpath
     * @throws Exception
     */
    public void waitAndClickElement(String xpath) throws Exception {
        WebElement element = XPath.waitForVisibleElement(driver, xpath);
        moveToAndClick(element);
    }
    
    public void moveToAndClick(WebElement element) throws Exception {
        Utils.moveToAndClick(driver, element);
    }
    
    public void sendKeys(WebElement element, CharSequence keysToSend) throws Exception {
        Utils.sendKeys(driver, element, keysToSend);
    }
    
    public void clearAndSendKeys(WebElement element, CharSequence keysToSend) throws Exception {
        Utils.clearAndSendKeys(driver, element, keysToSend);
    }
    
    public void waitForElementDissapear(String xpath) throws Exception {
        XPath.waitForElementDisappear(driver, xpath);
    }
    
    public void handleException(Exception exception) {
        Utils.handleException(driver, exception);
    }
    
    public void handleException(Exception exception, boolean captureSnapshot) {
        Utils.handleException(driver, exception, captureSnapshot, true, false);
    }
}


