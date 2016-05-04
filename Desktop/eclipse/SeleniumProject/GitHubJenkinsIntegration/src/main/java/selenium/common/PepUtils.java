/*package selenium.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import selenium.common.exception.AutomationException;
import selenium.common.exception.FormRegexValidationException;
import selenium.common.exception.FormValidationException;
import selenium.common.exception.TermsOfUseUpdateException;
import selenium.common.exception.TestCaseFailedException;
import selenium.common.exception.UnexpectedPageUrlException;
import selenium.common.exception.uimessage.Http404Exception;
import selenium.common.exception.uimessage.Http500Exception;
import selenium.common.exception.uimessage.WebUiBlankPageException;
import selenium.common.exception.uimessage.WebUiErrorCartException;
import selenium.common.exception.uimessage.WebUiErrorCheckoutException;
import selenium.common.exception.uimessage.WebUiErrorConfirmationException;
import selenium.common.exception.uimessage.WebUiErrorContainerException;
import selenium.common.exception.uimessage.WebUiErrorDiningException;
import selenium.common.exception.uimessage.WebUiErrorDiningRsvpException;
import selenium.common.exception.uimessage.WebUiErrorException;
import selenium.common.exception.uimessage.WebUiErrorHotelsException;
import selenium.common.exception.uimessage.WebUiErrorPageMsgException;
import selenium.common.exception.uimessage.WebUiErrorResortAddOnsException;
import selenium.common.exception.uimessage.WebUiErrorReviewException;
import selenium.common.exception.uimessage.WebUiErrorSummaryException;
import selenium.common.exception.uimessage.WebUiErrorTicketsException;
import selenium.common.exception.uimessage.WebUiMissingProfileException;
import selenium.common.exception.uimessage.WebUiNoAvailabilityException;
import selenium.common.exception.uimessage.WebUiPageErrorException;
import selenium.common.exception.uimessage.WebUiPartialServiceException;
import selenium.common.exception.uimessage.WebUiTicketSystemErrorException;
import selenium.common.pageobject.PageObject;

public class PepUtils extends Utils {
    
    *//**
     * Searches through the current page for any UI errors that is rendered
     * on the browser.  This is very PEP specific.
     * 
     * @param driver
     * @return
     *//*
    public static List<Exception> checkWebUiErrors(WebDriver driver) {
        return PepUtils.checkWebUiErrors(driver, null, null);
    }

    *//**
     * TODO: class=profileMissing is something that'll need to be added
     * Might also need to break out the checks if this becomes a performance
     * bottle-neck.
     * 
     * @param driver 
     * @param exception 
     * @param pageObject 
     * @return
     *//*
    public static List<Exception> checkWebUiErrors(WebDriver driver, Exception exception, PageObject pageObject) {
        
        if(driver == null) {
            Log.getDefaultLogger().severe("Web Driver is not set or initialized");
            return new LinkedList<Exception>();
        }
        
        String url = pageObject != null ? pageObject.getPageUrl() : driver.getCurrentUrl();
        
        List<Exception> exceptions = new ArrayList<Exception>();
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'availabilityErrorContainer')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected Availability Error");
                
                if(exception != null) {
                    exceptions.add(new WebUiNoAvailabilityException(url + ": " + msg, 
                                                                    exception, 
                                                                    driver));
                } else {
                    exceptions.add(new WebUiNoAvailabilityException(url + ": " + msg, 
                                                                    driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'ErrorContainer')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected Error Container");
                
                if(exception != null) {
                    exceptions.add(new WebUiErrorContainerException(url + ": " + msg, 
                                                                    exception, 
                                                                    driver));
                } else {
                    exceptions.add(new WebUiErrorContainerException(url + ": " + msg, 
                                                                    driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[@id='listErrorContainer']"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected Partial Service Failure");
                
                if(exception != null) {
                    exceptions.add(new WebUiPartialServiceException(url + ": " + msg, 
                                                                    exception, 
                                                                    driver));
                } else {
                    exceptions.add(new WebUiPartialServiceException(url + ": " + msg, 
                                                                    driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'partialServiceFailureContainer')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected Partial Service Failure");
                
                if(exception != null) {
                    exceptions.add(new WebUiPartialServiceException(url + ": " + msg, 
                                                                    exception, 
                                                                    driver));
                } else {
                    exceptions.add(new WebUiPartialServiceException(url + ": " + msg, 
                                                                    driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'pageLevelError') or contains(@class,'errorsSummaryBox')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected Error Summary");
                
                if(exception != null) {
                    exceptions.add(new WebUiErrorSummaryException(url + ": " + msg, 
                                                                  exception, 
                                                                  driver));
                } else {
                    exceptions.add(new WebUiErrorSummaryException(url + ": " + msg, 
                                                                  driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'page-error')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected Error Message(s)");
                
                if(exception != null) {
                    exceptions.add(new WebUiPageErrorException(url + ": " + msg, 
                                                               exception, 
                                                               driver));
                } else {
                    exceptions.add(new WebUiPageErrorException(url + ": " + msg, 
                                                               driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[@id='error-system-container']"+
                    "//*[contains(@class,'error-404-container')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected 404 page");
                
                if(exception != null) {
                    exceptions.add(new Http404Exception(url + ": " + msg, 
                                                        exception, 
                                                        driver));
                } else {
                    exceptions.add(new Http404Exception(url + ": " + msg, 
                                                        driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[@id='error-system-container']"+
                    "//*[contains(@class,'error-500-container')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected 500 page");
                
                if(exception != null) {
                    exceptions.add(new Http500Exception(url + ": " + msg, 
                                                        exception, 
                                                        driver));
                } else {
                    exceptions.add(new Http500Exception(url + ": " + msg, 
                                                        driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[@id='error-system-container']"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected HTTP error page");
                
                if(exception != null) {
                    exceptions.add(new WebUiErrorException(url + ": " + msg, 
                                                           exception, 
                                                           driver));
                } else {
                    exceptions.add(new WebUiErrorException(url + ": " + msg, 
                                                           driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = null;
            elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'errorMsgTitle') or contains(@class,'errorPageMsg')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                String msg = Utils.extractText(elements);
                
                Log.log(driver).warning("Detected Error Page Msg");
                
                if(exception != null) {
                    exceptions.add(new WebUiErrorPageMsgException(url + ": " + msg, 
                                                                  exception, 
                                                                  driver));
                } else {
                    exceptions.add(new WebUiErrorPageMsgException(url + ": " + msg, 
                                                                  driver));
                }
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            
            // If we're on the registration page, check and flag this test as
            // needing additional TLC to update this user's TOU.
            
            if(driver.getCurrentUrl().contains("registration/index/swid")) {
                
                List<WebElement> elements = driver.findElements(By.xpath(
                        "//*[contains(text(),'The sign-in process for Disney accounts has changed')]"));
                
                List<WebElement> touText = driver.findElements(By.xpath(
                        "//*[contains(text(),'Terms of Use')]"));
                
                if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements) &&
                        !touText.isEmpty() && Utils.hasDisplayedElement(driver, touText)) {
                    
                    Log.log(driver).warning("Detected Terms of Use update page");
                    
                    // We're only going to report this as an error if there's
                    // another exception associated with it.  This is to ensure
                    // that we don't mistakenly think this page itself indicates
                    // an error.
                    
                    if(exception != null) {
                        exceptions.add(new TermsOfUseUpdateException("", 
                                                                     exception, 
                                                                     driver));
                    }
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
                
            List<WebElement> elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'profileMissing')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                Log.log(driver).warning("Detected missing-profile error message");
                
                String msg = Utils.extractText(elements);
                
                if(exception != null) {
                    exceptions.add(new WebUiMissingProfileException(url + ": " + msg, 
                                                                    exception, 
                                                                    driver));
                } else {
                    exceptions.add(new WebUiMissingProfileException(url + ": " + msg, 
                                                                    driver));
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            
            List<WebElement> elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'ticketsSystemErrorMessage')]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                Log.log(driver).warning("Detected maintenance error message");
                
                String msg = Utils.extractText(elements);
                
                if(exception != null) {
                    exceptions.add(new WebUiTicketSystemErrorException(url + ": " + msg, 
                                                                       exception, 
                                                                       driver));
                } else {
                    exceptions.add(new WebUiTicketSystemErrorException(url + ": " + msg, 
                                                                       driver));
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'regex') and (" + 
                    "contains(@class,'Invalid') or " + 
                    "contains(@class,'NotMatch') or " +
                    "contains(@class,'Errorous'))]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                Log.log(driver).warning("Detected RegEx validation error messages");
                
                String msg = Utils.extractText(elements);
                
                if(exception != null) {
                    exceptions.add(new FormRegexValidationException(url + ": " + msg, 
                                                                    exception, 
                                                                    driver));
                } else {
                    exceptions.add(new FormRegexValidationException(url + ": " + msg, 
                                                                    driver));
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            List<WebElement> elements = driver.findElements(By.xpath(
                    "//*[contains(@class,'errorsSummaryBox')]" + 
                    "//ul[contains(@class,'error')]" + 
                    "/li[not(contains(@class,'hide'))]"));
            
            if(!elements.isEmpty() && Utils.hasDisplayedElement(driver, elements)) {
                
                Log.log(driver).warning("Detected form error messages");
                
                String msg = Utils.extractText(elements);
                
                if(exception != null) {
                    exceptions.add(new FormValidationException(url + ": " + msg, 
                                                               exception, 
                                                               driver));
                } else {
                    exceptions.add(new FormValidationException(url + ": " + msg, 
                                                               driver));
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            if(Utils.checkForBlankPage(driver, false)) {
                if(exception != null) {
                    exceptions.add(new WebUiBlankPageException(driver, exception));
                } else {
                    exceptions.add(new WebUiBlankPageException(driver));
                }
            }
            
        } catch (Exception ex) {
            Utils.handleExceptionWarning(driver, ex, false, false);
        }
        
        return exceptions;
    }

    *//**
     * This checks  the state of the browser/driver to repackage it into some
     * thing that helps to identify the error (more easily).  This is for  
     * PEPCOM tests.
     * 
     * @param driver
     * @param throwable
     * @throws Exception 
     *//*
    public static void analyzeAndThrowException(WebDriver driver, Throwable throwable) throws Exception {
        
        if(throwable instanceof AssertionError) {
            
            Log.log(driver).severe(throwable.getMessage());
            
            StackTraceElement[] st = throwable.getStackTrace();
            String msg = throwable.getMessage();
            
            if(msg != null && !msg.isEmpty()) {
                throwable = new TestCaseFailedException(msg, throwable);
            } else {
                throwable = new TestCaseFailedException(throwable);
            }
            
            if(st != null) {
                throwable.setStackTrace(st);
            }
        }
        
        if(throwable instanceof Exception) {
            Exception ex = (Exception)throwable;
            Exception exception = ex;
            String castExceptionClass = null;
            
            if(exception instanceof ClassCastException) {
                
                // Cast errors are *typically* due to the flow from page to page 
                // (using the PageFactory).  Instead of reporting some technical 
                // exception, repackage it into something that is easier to 
                // understand.
                
                castExceptionClass = exception.getMessage();
                
                // This is a step that helps map back to an exception that will 
                // inform the user about the exact phase in which the error 
                // occurred, e.g. CartError, PurchaseError, FinderError.
                
                castExceptionClass = castExceptionClass.contains(".") ? 
                                     castExceptionClass.substring(castExceptionClass.lastIndexOf("."))
                                     .replaceAll("\\s", "")
                                     .replaceAll(".", ""): 
                                     null;
                                     
                exception = new UnexpectedPageUrlException(driver, ex);
            }
            
            if(!(exception instanceof WebUiErrorException)) {
                
                // Here, we're only interested in exceptions that aren't Web Ui related
                // because we're about to search the current page for an error message.
                
                List<Exception> exceptions = checkWebUiErrors(driver, ex, null);
                if(exceptions != null && !exceptions.isEmpty()) {
                    exception = exceptions.get(0);
                    
                    // Based on the Page-Factory and Factory creation pattern, 
                    // this takes exploits the commonly encountered error of an
                    // incorrect java-cast when pages are unexpectedly redirected  
                    // to an error message of some sort.
                    
                    if(castExceptionClass != null && !castExceptionClass.isEmpty()) {
                        boolean isRepackaged = true;
                        if(castExceptionClass.startsWith("Checkout")) {
                            exception = Utils.repackageException(ex, WebUiErrorCheckoutException.class);
                        } else if(castExceptionClass.startsWith("Cart")) {
                            exception = Utils.repackageException(ex, WebUiErrorCartException.class);
                        } else if(castExceptionClass.startsWith("DiningReservation")) {
                            exception = Utils.repackageException(ex, WebUiErrorDiningRsvpException.class);
                        } else if(castExceptionClass.startsWith("Dining")) {
                            exception = Utils.repackageException(ex, WebUiErrorDiningException.class);
                        } else if(castExceptionClass.startsWith("Hotels")) {
                            exception = Utils.repackageException(ex, WebUiErrorHotelsException.class);
                        } else if(castExceptionClass.startsWith("Tickets")) {
                            exception = Utils.repackageException(ex, WebUiErrorTicketsException.class);
                        } else if(castExceptionClass.startsWith("ResortAddOns")) {
                            exception = Utils.repackageException(ex, WebUiErrorResortAddOnsException.class);
                        } else {
                            isRepackaged = true;
                        }
                        
                        if(isRepackaged) {
                            
                            // If the exception has been repackaged, let's check 
                            // further to see if it's a within the review or 
                            // confirmation steps. 
                            
                            if(castExceptionClass.endsWith("Review")) {
                                exception = Utils.repackageException(ex, WebUiErrorReviewException.class);
                            } else if(castExceptionClass.endsWith("Confirmation")) {
                                exception = Utils.repackageException(ex, WebUiErrorConfirmationException.class);
                            }
                        }
                    }
                }
            }
            
            if(!(exception instanceof AutomationException)) {
                
                // We're "re-creating" exceptions and appending (hopefully) useful
                // information like the conversation ID and the web driver's 
                // current URL.
                
                exception = Utils.repackageException(driver, exception);
            }
            
            Utils.handleException(driver, exception);
            throw exception;
        }
        
        RuntimeException dirtyException = new RuntimeException(throwable);
        Utils.captureScreenshot(driver, "ThrowableError");
        throw dirtyException;
    }

}


*/