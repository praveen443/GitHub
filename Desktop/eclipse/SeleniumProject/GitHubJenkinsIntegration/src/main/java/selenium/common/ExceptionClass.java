package selenium.common;

/***********************************************************************************************************************
 * FileName - Exception.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Author: tkessler $
 * $Revision: #4 $
 * $Change: 1302967 $
 * $Date: 2013/04/01 $
 ***********************************************************************************************************************/

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import selenium.common.exception.AutomationException;

/**
 * No longer a singleton because it does not hold any information.
 * <p>
 * 
 * SAP - 09/02/14 - IMHO, Please be discouraged from using this class. Raising
 * exceptions with this class will not help reveal any useful information about
 * why the test failed. Selenium web-driver exceptions are more than sufficient
 * since it typically includes the cause e.g. xpath. Also, it would be much
 * better to repackage the exception by appending details to the original
 * exception or appending the cause to the new exception.<br>
 * 
 * @see Utils#handleException(WebDriver, Exception)
 * @see AutomationException#AutomationException(String, Throwable, WebDriver)
 * 
 * @author kaiy001
 */
@Deprecated
public class ExceptionClass {

    public ExceptionClass() {
    }

    /**
     * When throwing IllegalStateException
     * 
     * @deprecated Don't use this, instead throw an exception and handle it.
     * @param message
     *            Message to output
     * @param driver
     *            Driver that is being used
     * @throws IllegalStateException
     *             Can catch if you want test to not fail
     */
    @Deprecated
    public static void throwIllegalStateException(String message, WebDriver driver) throws IllegalStateException {

        StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();
        String stackTrace = "";
        for (int i = 0; i < stackTraceArray.length; i++) {
            stackTrace += "\t" + stackTraceArray[i].toString() + Constants.LINE_SEPARATOR;
        }

        Log.errorLog(driver).severe("(" + Log.lookupTestName(driver) + ") " + "Error Location: "
                                            + driver.getCurrentUrl() + Constants.LINE_SEPARATOR
                                            + Constants.LINE_SEPARATOR + "Screenshot of error: "
                                            + takeScreenshot(driver, String.valueOf(System.currentTimeMillis()))
                                            + Constants.LINE_SEPARATOR + message + Constants.LINE_SEPARATOR
                                            + stackTrace);
        System.err.println("Error Location: " + driver.getCurrentUrl() + Constants.LINE_SEPARATOR + message);

        throw new IllegalStateException("Error Location: " + driver.getCurrentUrl() + Constants.LINE_SEPARATOR
                                        + message);
    }

    /**
     * @deprecated This method ruins the stacktrace that is thrown back out
     * to TestNG.  Do not use this, if you need to repackage or append 
     * to an existing exception, use 
     * {@link Utils#repackageException(Exception, String)}
     * 
     * @param locator The locator strategy used, with the key used
     * @param driver Driver that is being used
     * @param nsee NoSuchElementException to log the stack trace
     * @throws NoSuchElementException Can catch if you want test to not fail
     */
    @Deprecated
    public static void throwNoSuchElementException(String locator, WebDriver driver, NoSuchElementException nsee) throws NoSuchElementException {

        StackTraceElement[] stackTraceArray = nsee.getStackTrace();
        String stackTrace = "";
        for (int i = 0; i < stackTraceArray.length; i++) {
            stackTrace += "\t" + stackTraceArray[i].toString() + Constants.LINE_SEPARATOR;
        }

        Log.errorLog(driver)
                .severe("(" + Log.lookupTestName(driver) + ") " + "Error Location: " + driver.getCurrentUrl()
                                + Constants.LINE_SEPARATOR + Constants.LINE_SEPARATOR + "Screenshot of error: "
                                + takeScreenshot(driver, String.valueOf(System.currentTimeMillis()))
                                + Constants.LINE_SEPARATOR
                                + nsee.getMessage().replaceAll("\n", Constants.LINE_SEPARATOR) + stackTrace);
        System.err.println("Error Location: " + driver.getCurrentUrl() + "\nUnable to locate element: " + locator);

        throw new NoSuchElementException("Error Location: " + driver.getCurrentUrl() + "\nUnable to locate element: "
                                         + locator);
    }

    /**
     * When throwing TimeoutException
     * 
     * @deprecated
     * @param locator
     *            The locator strategy used, with the key used
     * @param driver
     *            Driver that is being used
     * @param te
     *            TimeoutException to log the stack trace
     * @throws TimeoutException
     *             Can catch if you want test to not fail
     */
    @Deprecated
    public static void throwTimeoutException(String locator, WebDriver driver, TimeoutException te) throws TimeoutException {

        StackTraceElement[] stackTraceArray = te.getStackTrace();
        String stackTrace = "";
        for (int i = 0; i < stackTraceArray.length; i++) {
            stackTrace += "\t" + stackTraceArray[i].toString() + Constants.LINE_SEPARATOR;
        }

        Log.errorLog(driver).severe("(" + Log.lookupTestName(driver) + ") " + "Error Location: "
                                            + driver.getCurrentUrl() + Constants.LINE_SEPARATOR
                                            + Constants.LINE_SEPARATOR + "Screenshot of error: "
                                            + takeScreenshot(driver, String.valueOf(System.currentTimeMillis()))
                                            + Constants.LINE_SEPARATOR
                                            + te.getMessage().replaceAll("\n", Constants.LINE_SEPARATOR) + stackTrace);
        System.err.println("Error Location: " + driver.getCurrentUrl() + "\nTimed out after "
                           + Constants.GLOBAL_DRIVER_TIMEOUT + " seconds waiting for visibility of element located by "
                           + locator);

        throw new TimeoutException("Error Location: " + driver.getCurrentUrl() + "\nTimed out after "
                                   + Constants.GLOBAL_DRIVER_TIMEOUT
                                   + " seconds waiting for visibility of element located by " + locator);
    }

    /**
     * When throwing InvalidElementStateException
     *
     * @deprecated
     * @param locator
     *            The locator strategy used, with the key used
     * @param driver
     *            Driver that is being used
     * @param iese
     *            InvalidElementStateException to log the stack trace
     * @throws InvalidElementStateException
     *             Can catch if you want test to not fail
     */
    @Deprecated
    public static void throwInvalidElementStateException(String locator, WebDriver driver,
                                                         InvalidElementStateException iese) throws InvalidElementStateException {

        StackTraceElement[] stackTraceArray = iese.getStackTrace();
        String stackTrace = "";
        for (int i = 0; i < stackTraceArray.length; i++) {
            stackTrace += "\t" + stackTraceArray[i].toString() + Constants.LINE_SEPARATOR;
        }

        Log.errorLog(driver)
                .severe("(" + Log.lookupTestName(driver) + ") " + "Error Location: " + driver.getCurrentUrl()
                                + Constants.LINE_SEPARATOR + Constants.LINE_SEPARATOR + "Screenshot of error: "
                                + takeScreenshot(driver, String.valueOf(System.currentTimeMillis()))
                                + Constants.LINE_SEPARATOR
                                + iese.getMessage().replaceAll("\n", Constants.LINE_SEPARATOR) + stackTrace);
        System.err.println("Element is disabled and so may not be used for actions (" + locator + ")\nError Location: "
                           + driver.getCurrentUrl());

        throw new InvalidElementStateException("Element is disabled and so may not be used for actions (" + locator
                                               + ")\nError Location: " + driver.getCurrentUrl());

    }

    /**
     * When throwing IllegalLocatorException
     *
     * @deprecated
     * @param driver
     *            Driver that is being used
     * @param ile
     *            IllegalLocatorException to log the stack trace
     * @throws Exception
     * @throws IllegalLocatorException
     *             Can catch if you want test to not fail
     */
    @Deprecated
    public static void throwIllegalLocatorException(WebDriver driver, IllegalStateException ile) throws Exception {

        StackTraceElement[] stackTraceArray = ile.getStackTrace();
        String stackTrace = "";
        for (int i = 0; i < stackTraceArray.length; i++) {
            stackTrace += "\t" + stackTraceArray[i].toString() + Constants.LINE_SEPARATOR;
        }

        Log.errorLog(driver).severe("(" + Log.lookupTestName(driver) + ") " + "Error Location: "
                                            + driver.getCurrentUrl() + Constants.LINE_SEPARATOR
                                            + Constants.LINE_SEPARATOR + "Screenshot of error: "
                                            + takeScreenshot(driver, String.valueOf(System.currentTimeMillis()))
                                            + Constants.LINE_SEPARATOR
                                            + ile.getMessage().replaceAll("\n", Constants.LINE_SEPARATOR) + stackTrace);
        System.err
                .println("Error Location: "
                         + driver.getCurrentUrl()
                         + "\nCompound class names are not supported. Consider searching for one class name and filtering the results.");

        throw new Exception(
                            "Error Location: "
                                    + driver.getCurrentUrl()
                                    + "\nCompound class names are not supported. Consider searching for one class name and filtering the results.");
    }

    /**
     * Take screenshot of driver's current state. This is awkward and frankly
     * clumsy to use.
     * 
     * @deprecated migrate to Utils.captureScreenshot
     * @see {@link Utils#captureScreenshot(WebDriver, String)}
     * @see {@link Utils#captureScreenshot(WebDriver, String, boolean)}
     * @param driver
     *            The WebDriver to use
     * @param name
     *            The name of the screenshot (use System.currentTimeMillis())
     */
    @Deprecated
    public static String takeScreenshot(WebDriver driver, String name) {

        String strSeleniumGrid = System.getProperty("selenium.grid");

        // This is for remote-driver only
        if ("true".equalsIgnoreCase(strSeleniumGrid))
            driver = new Augmenter().augment(driver);

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String fileName = Log.FOLDER_PATH + name + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            Log.log(driver).severe(e.getMessage());
        }

        return fileName;
    }
}


