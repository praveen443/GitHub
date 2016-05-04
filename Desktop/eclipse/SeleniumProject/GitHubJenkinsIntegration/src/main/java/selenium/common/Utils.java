/*package selenium.common;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.common.enums.WdprUrl;
import selenium.common.exception.DomNotCompleteException;
import selenium.common.exception.DomNotInteractiveException;
import selenium.common.exception.automation.WebElementNotDisappearedException;
import selenium.common.exception.automation.WebElementNotFoundException;
import selenium.common.exception.automation.WebElementNotVisibleException;

*//**
 * A hodgepodge of commonly used functions. This is a meld/merge of various
 * classes and re-packaged for easier use.
 * 
 * @author SonHuy.Pham@Disney.com
 *//*
public class Utils {

	static private Map<String, Utils> instances = new HashMap<String, Utils>();
	static private Random random = new Random();
	static boolean logWebElements = true;
	static private boolean logPageSource = true;
	private static SimpleDateFormat scrShot = new SimpleDateFormat(
			"MMddyy_HHmmss");
	private static String scrShotDir = new SimpleDateFormat("MMddyy")
			.format(new Date());
	private static String strScreenshotName = "";
	public static Properties miscProps = new Properties();

	private WebDriver driver;

	*//** @see {@link #setConversationId(String)} *//*
	private String conversationId = "";

	*//**
	 * Singleton instances are organized based on the driver instance passed in.
	 * 
	 * @param driver
	 * @return The Utils instance associated with the driver or null if it does
	 *         not exist.
	 *//*
	static public Utils getInstance(WebDriver driver) {

		if (driver == null) {
			return null;
		}

		Utils instance = null;
		String key = driver.toString();

		if (instances.containsKey(key)) {
			instance = instances.get(key);
		} else {
			instance = new Utils();
			instances.put(key, instance);
		}

		// Take the time here to sync/copy the driver instance.
		if (instance.driver == null || !instance.driver.equals(driver)) {
			instance.driver = driver;
		}

		return instance;
	}

	*//**
	 * @see {@link #setConversationId(String)}
	 * 
	 * @return The unique string (GUID) for the session.
	 *//*
	public String getConversationId() {
		synchronized (conversationId) {
			return conversationId;
		}
	}

	*//**
	 * If it makes sense, use this to track the current web page session. This
	 * is, in general, a best practice that helps coordinate and track logs for
	 * a web-service oriented architecture. In practice though this is a PEP
	 * specific feature support.
	 * 
	 * @param conversationId
	 *            The unique string (GUID) for the session.
	 *//*
	public void setConversationId(String conversationId) {
		synchronized (conversationId) {
			this.conversationId = conversationId;
		}
	}

	*//**
	 * Reads file and converts to String.
	 * 
	 * @param filePath
	 *            the name of the file to open. Not sure if it can accept URLs
	 *            or just filenames. Path handling could be better, and buffer
	 *            sizes are hardcoded.
	 * 
	 * @throws IOException
	 *//*
	static String readFileAsString(String filePath) throws IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

	*//**
	 * Resets the seed in the random generator object, just in case we run into
	 * generation issues.
	 *//*
	public static void resetRandomSeed() {
		synchronized (random) {
			random = new Random();
		}
	}

	*//**
	 * Code snippet straight from stack-overflow to generate a random int.
	 * 
	 * @param min
	 * @param max
	 * @return Random integer from java.util.random
	 *//*
	public static int generateRandomNumber(int min, int max) {
		int number;
		synchronized (random) {
			// Add 1 to make it inclusive.
			number = random.nextInt((max - min) + 1) + min;
		}
		return number;
	}

	public static String toCamelCaseLowerCaseFirst(String str) {
		String[] parts = str.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		camelCaseString = camelCaseString.substring(0, 1).toLowerCase()
				+ camelCaseString.substring(1);
		return camelCaseString;
	}

	*//**
	 * Striaght from stack-overflow. Handy for converting constants to camel
	 * case.
	 * 
	 * @param str
	 * @return
	 *//*
	public static String toCamelCase(String str) {
		String[] parts = str.split("_");
		String camelCaseString = "";
		for (String part : parts) {
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return camelCaseString;
	}

	public static String toProperCase(String str) {
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1).toLowerCase();
	}

	*//**
	 * Sets the global configuration for verbose logging of web element
	 * searches.
	 * 
	 * TODO This should be scoped to an instance of Utils if at all possible.
	 * 
	 * @param logWebElements
	 *//*
	public static void setLogWebElements(boolean logWebElements) {
		Utils.logWebElements = logWebElements;
	}

	public static boolean isLogWebElements() {
		return Utils.logWebElements;
	}

	*//**
	 * Sets the global configuration for logging page source when handling an
	 * exception.
	 * 
	 * @param logPageSource
	 *//*
	public static void setLogPageSource(boolean logPageSource) {
		Utils.logPageSource = logPageSource;
	}

	public static boolean isLogPageSource() {
		return Utils.logPageSource;
	}

	*//**
	 * Grabs any text contained inside of each of these error-related elements.
	 * 
	 * @param elements
	 * @return Extracts the text and returns a formatted string from the arg.
	 *//*
	public static String extractText(List<WebElement> elements) {

		String msg = "";

		try {

			for (WebElement e : elements) {
				if (e.getText() != null && !e.getText().isEmpty()) {
					msg += "\n\t" + e.getText();
				}
			}

		} catch (Exception ex) {
			// We actually risk running into stale-elements, as always.
			ex.printStackTrace();
		}

		return msg;
	}

	*//**
	 * It's common for PEP pages to return infamous "white" pages that basically
	 * do not contain ANY data in it, particularly when system-dependencies are
	 * down. This will detect if the browser is displaying a "blank/white" page.
	 * 
	 * @param driver
	 * @throws Exception
	 *//*
	public static void checkForBlankPage(WebDriver driver) throws Exception {
		checkForBlankPage(driver, true);
	}

	*//**
	 * It's common for PEP pages to return infamous "white" pages that basically
	 * do not contain ANY data in it, particularly when system-dependencies are
	 * down. This will detect if the browser is displaying a "blank/white" page.
	 * 
	 * @param driver
	 * @param raiseException
	 * @return
	 * @throws Exception
	 *//*
	public static boolean checkForBlankPage(WebDriver driver,
			boolean raiseException) throws Exception {

		// This snippet checks if the page is blank -
		//
		// So if there isn't a body-tag or if the body doesn't have child nodes
		// then this is a blank page.

		Object result = ((JavascriptExecutor) driver)
				.executeScript("var list = document.documentElement.getElementsByTagName(\"body\");"
						+ "return ((list.length > 0) ? !list[0].hasChildNodes() : true);");

		boolean isBlankPage = false;

		if (result instanceof Boolean) {
			isBlankPage = (Boolean) result;

			if (raiseException && isBlankPage) {
				throw new WebUiBlankPageException(driver);
			}

		} else {
			// Consider throwing an exception too.
			Log.log(driver).warning(
					"JavaScript result to check for "
							+ "blank-page FAILED to return a boolean value");
		}

		return isBlankPage;
	}

	*//**
	 * Waits until DOM readyState is assigned "complete".
	 * 
	 * @param driver
	 * @throws Exception
	 *//*
	public static void waitForDomComplete(WebDriver driver) throws Exception {
		waitForDomComplete(driver, null);
	}

	public static void waitForDomComplete(WebDriver driver, String errorMsg)
			throws Exception {
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

				// Returning a boolean which will perform the DOM-ready check
				// on the browser instead of checking within Selenium.

				Object obj = ((JavascriptExecutor) driver)
						.executeScript("var result = document.readyState; return (result == 'complete');");

				// We expect a Boolean object, however there have been some
				// cases where the results just aren't making sense - so for
				// the purpose of diagnostics, we're going to report any
				// odd behaviors from the browser (firefox).

				if (obj == null) {
					Log.log(driver)
							.warning("JavascriptExecutor object is null");
					return false;

				} else if (obj instanceof Boolean) {
					return obj.equals(true);

				} else if (obj instanceof String) {
					Log.log(driver).warning(
							"JavascriptExecutor object is an UNEXPECTED type "
									+ "[" + obj.getClass().toString() + "] ["
									+ obj.toString() + "]");
					return obj.equals("true");
				}

				Log.log(driver).warning(
						"JavascriptExecutor object is NOT recognized " + "["
								+ obj.getClass().toString() + "] ["
								+ obj.toString() + "]");

				return obj.equals(true);
			}
		};

		// Note that we're only going to wait for the majority of the global
		// time-out period and then retry for the rest of the half. This is
		// an effort to mitigate issues seen with DOM ready timeouts.

		Wait<WebDriver> wait = new WebDriverWait(driver, 45);
		try {

			wait.until(condition);

		} catch (Exception ex) {

			Log.log(driver).warning(
					"Dom ready-state check is taking a "
							+ "while, will continue with a less strict check");

			try {
				condition = new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver driver) {

						// Performing a less-strict version due to time-outs
						// waiting for readyState to change to "complete".

						Object obj = ((JavascriptExecutor) driver)
								.executeScript("return document.readyState;");

						if (obj == null) {
							Log.log(driver).warning(
									"JavascriptExecutor object is null");
							return false;

						} else if (obj instanceof String) {

							if (obj.equals("complete")) {
								Log.log(driver).info(
										"DOM ready-state is complete");
								return true;

							} else if (obj.equals("interactive")) {
								Log.log(driver)
										.warning(
												"DOM ready-state is interactive, will continue anyway");
								return true;

							}

							Log.log(driver).warning(
									"Failed waiting for DOM " + "["
											+ obj.getClass().toString() + "] ["
											+ obj.toString() + "]");

							return false;
						}

						Log.log(driver).warning(
								"JavascriptExecutor object is NOT recognized "
										+ "[" + obj.getClass().toString()
										+ "] [" + obj.toString() + "]");

						return (obj.equals("complete") || obj
								.equals("interactive"));
					}
				};

				wait = new WebDriverWait(driver, 15);
				wait.until(condition);

			} catch (Exception retryException) {
				String msg = (errorMsg == null || errorMsg.isEmpty()) ? "Failed to wait for DOM readyState"
						: "Failed to wait for DOM readyState, " + errorMsg;

				throw new DomNotCompleteException(msg, retryException, driver);
			}
		}
	}

	*//**
	 * Waits until DOM readyState is assigned "complete" or "interactive", a
	 * less-strict version of {@link #waitForDomComplete(WebDriver)}.
	 * 
	 * @param driver
	 * @throws Exception
	 *//*
	public static void waitForDomInteractive(WebDriver driver) throws Exception {
		waitForDomInteractive(driver, null);
	}

	*//**
	 * Only for internal use to help ready the page for element searches.
	 *//*
	private static boolean waitForDomInteractiveNoException(WebDriver driver)
			throws Exception {
		boolean isSuccessful = false;
		try {
			waitForDomInteractive(driver, null);
			isSuccessful = false;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isSuccessful;
	}

	public static void waitForDomInteractive(WebDriver driver, String warningMsg)
			throws Exception {
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

				// Returning a boolean which will perform the DOM-ready check
				// on the browser instead of checking within Selenium.

				Object obj = ((JavascriptExecutor) driver)
						.executeScript("var result = document.readyState; return (result == 'complete' || result == 'interactive');");

				// We expect a Boolean object, however there have been some
				// cases where the results just aren't making sense - so for
				// the purpose of diagnostics, we're going to report any
				// odd behaviors from the browser (firefox).

				if (obj == null) {
					Log.log(driver)
							.warning("JavascriptExecutor object is null");
					return false;

				} else if (obj instanceof Boolean) {
					return obj.equals(true);

				} else if (obj instanceof String) {
					Log.log(driver).warning(
							"JavascriptExecutor object is an UNEXPECTED type "
									+ "[" + obj.getClass().toString() + "] ["
									+ obj.toString() + "]");
					return obj.equals("true");
				}

				Log.log(driver).warning(
						"JavascriptExecutor object is NOT recognized " + "["
								+ obj.getClass().toString() + "] ["
								+ obj.toString() + "]");

				return obj.equals(true);
			}
		};

		// Note that we're only going to wait for the majority of the global
		// time-out period and then retry for the rest of the half. This is
		// an effort to mitigate issues seen with DOM ready timeouts.

		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		try {
			wait.until(condition);

		} catch (Exception ex) {
			String msg = (warningMsg == null || warningMsg.isEmpty()) ? "Failed to wait for DOM readyState"
					: "Failed to wait for DOM readyState, " + warningMsg;

			throw new DomNotInteractiveException(msg, ex, driver);
		}
	}

	*//**
	 * PLEASE prefer the Enumeration version over this. Waits until a specified
	 * page has been loaded in the driver. Will also strip out anything
	 * (arguments) in the URL after the ? character.
	 * 
	 * @see {@link #waitForUrlLoaded(WebDriver, WdprUrl)}
	 * @param url
	 *            The expected URL that the driver should match
	 * @param driver
	 * @throws Exception
	 *//*
	public static void waitForUrlLoaded(WebDriver driver, String url)
			throws Exception {
		waitForUrlLoaded(driver, url, Constants.GLOBAL_DRIVER_TIMEOUT);
	}

	public static void waitForUrlLoaded(WebDriver driver, String url,
			long timeout) throws Exception {
		if (url.isEmpty()) {
			throw new UrlNotLoadedException("Url is empty", driver);
		}

		final String urlStr = url;

		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String driverUrl = driver.getCurrentUrl();
				int index = driverUrl.indexOf('?');
				driverUrl = (index > -1) ? driverUrl.substring(0, index)
						: driverUrl;
				return urlStr.equals(driverUrl);
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(condition);
		} catch (Exception ex) {
			throw new UrlNotLoadedException("Failed to verify driver URL: "
					+ url + " vs " + driver.getCurrentUrl(), ex, driver);
		}
	}

	public static boolean waitForUrlLoadedNoException(WebDriver driver,
			WdprUrl url) {
		return waitForUrlLoadedNoException(driver, url,
				Constants.GLOBAL_DRIVER_TIMEOUT);
	}

	public static boolean waitForUrlLoadedNoException(WebDriver driver,
			WdprUrl url, long timeout) {
		boolean isSuccessful = false;
		try {
			waitForUrlLoaded(driver, url, timeout);
			isSuccessful = true;
		} catch (Exception ex) {
			Log.log(driver).info(ex.getMessage());
		}
		return isSuccessful;
	}

	*//**
	 * Waits until a specified page has been loaded in the driver. Will also
	 * strip out anything (arguments) in the URL after the ? character.
	 * 
	 * @param url
	 *            The expected URL that the driver should match
	 * @param driver
	 * @throws Exception
	 *//*
	public static void waitForUrlLoaded(WebDriver driver, WdprUrl url)
			throws Exception {
		waitForUrlLoaded(driver, url, Constants.GLOBAL_DRIVER_TIMEOUT);
	}

	public static void waitForUrlLoaded(WebDriver driver, WdprUrl url,
			long timeout) throws Exception {
		if (!url.isValid()) {
			throw new UrlNotLoadedException(url.toString(), driver);
		}

		final String urlStr = url.getUrl();

		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String driverUrl = driver.getCurrentUrl();
				int index = driverUrl.indexOf('?');
				driverUrl = (index > -1) ? driverUrl.substring(0, index)
						: driverUrl;
				return urlStr.equals(driverUrl);
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
		try {
			wait.until(condition);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new UrlNotLoadedException("Failed to verify driver URL with "
					+ url.toString() + ". Current url is "
					+ driver.getCurrentUrl(), ex, driver);
		}
	}

	*//**
	 * PLEASE prefer the Enumeration version over this. A strict version of
	 * {@link #waitForUrlLoaded(WebDriver, String)} because it does not strip
	 * out url arguments e.g. ?.
	 * 
	 * @see {@link #waitForUrlLoaded(WebDriver, WdprUrl)}
	 * @param driver
	 * @param url
	 * @throws Exception
	 *//*
	public static void waitForUrlMatch(WebDriver driver, String url)
			throws Exception {
		if (url.isEmpty()) {
			throw new UrlNotLoadedException("Url is empty", driver);
		}

		final String urlStr = url;

		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String driverUrl = driver.getCurrentUrl();
				return urlStr.equals(driverUrl);
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver,
				Constants.GLOBAL_DRIVER_TIMEOUT);
		try {
			wait.until(condition);
		} catch (Exception ex) {
			throw new UrlNotLoadedException("Failed to verify driver URL: "
					+ url + " vs " + driver.getCurrentUrl(), ex, driver);
		}
	}

	*//**
	 * A strict version of {@link #waitForUrlLoaded(WebDriver, WdprUrl)} because
	 * it does not strip out url arguments e.g. ?.
	 * 
	 * @param driver
	 * @param url
	 *            The expected URL that the driver should match
	 * @throws Exception
	 *//*
	public static void waitForUrlMatch(WebDriver driver, WdprUrl url)
			throws Exception {
		if (!url.isValid()) {
			throw new UrlNotLoadedException(url.toString(), driver);
		}

		final String urlStr = url.getUrl();

		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				String driverUrl = driver.getCurrentUrl();
				return urlStr.equals(driverUrl);
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver,
				Constants.GLOBAL_DRIVER_TIMEOUT);
		try {
			wait.until(condition);
		} catch (Exception ex) {
			throw new UrlNotLoadedException("Failed to verify driver URL with "
					+ url.toString() + ". Current url is "
					+ driver.getCurrentUrl(), ex, driver);
		}
	}

	*//**
	 * Waits until a specified page contains the url
	 * 
	 * @param url
	 *            The expected URL after action
	 * @param driver
	 * @throws Exception
	 *//*
	public static void waitForUrlContains(WebDriver driver, String urlSegment)
			throws Exception {
		final String urlStr = urlSegment;
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.getCurrentUrl().contains(urlStr);
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver,
				Constants.GLOBAL_DRIVER_TIMEOUT);
		try {
			wait.until(condition);
		} catch (Exception ex) {
			throw new UrlNotLoadedException("Failed to verify driver URL: "
					+ urlSegment + " vs " + driver.getCurrentUrl(), ex, driver);
		}
	}

	public static void waitForUrlContains(WebDriver driver, WdprUrl url)
			throws Exception {
		final String urlStr = url.getUrl();
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.getCurrentUrl().contains(urlStr);
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver,
				Constants.GLOBAL_DRIVER_TIMEOUT);
		try {
			wait.until(condition);
		} catch (Exception ex) {
			throw new UrlNotLoadedException("Failed to verify driver URL with "
					+ url.toString() + ". Current url is "
					+ driver.getCurrentUrl(), ex, driver);
		}
	}

	public static void scrollToBottom(WebDriver driver) {
		((JavascriptExecutor) driver)
				.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
	}

	*//**
	 * A major side-effect of scrolling to an element trigger in a change in the
	 * DOM and then will void the internal cache of the Web Element.
	 * 
	 * Note: This attempts to target Chrome browsers only not FF.
	 * 
	 * @param driver
	 * @param element
	 *//*
	public static void scrollToElement(WebDriver driver, WebElement element) {

		// We're going to save a teeny-bit on overhead by only applying this to
		// chrome browsers.
		// In general, make sure that the element is within the browser's view
		// port before attempting to click.

		String driverStr = driver.toString();
		if (driverStr.contains("Chrome") && !driverStr.contains("Firefox")) {
			int yOffset = ((Locatable) element).getCoordinates().onPage()
					.getY();
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"
					+ yOffset + ");");
		}
	}

	*//**
	 * Unlike {@link Utils#scrollToElement(WebDriver, WebElement)}, this will
	 * scroll to the elment regardless of the browser type.
	 * 
	 * @param driver
	 * @param element
	 *//*
	public static void scrollTo(WebDriver driver, WebElement element) {
		int yOffset = ((Locatable) element).getCoordinates().onPage().getY();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"
				+ yOffset + ");");
	}

	*//**
	 * Eventually will play a crucial role in maintaining a uniform method for
	 * clicking on page objects across multiple browsers.
	 * 
	 * @param driver
	 * @param element
	 * @throws Exception
	 *//*
	public static Actions moveToAndClick(WebDriver driver, WebElement element)
			throws Exception {
		scrollToElement(driver, element);
		Actions actions = new Actions(driver);
		try {
			try {
				actions.moveToElement(element).click().perform();

			} catch (MoveTargetOutOfBoundsException ex) {
				Log.log(driver).warning(ex.getMessage());
				// If we're having problems moving to the element, just
				// ignore it and try to click.
				element.click();
			}
		} catch (WebDriverException ex) {
			ex.printStackTrace();
			if (ex.getMessage() != null
					&& ex.getMessage().contains("dead object")) {
				// Trying to handle the "can't access dead object error". =(.
				// https://code.google.com/p/selenium/issues/detail?id=7637
				Utils.scrollTo(driver, element);
				element.click();

			} else {
				throw ex;
			}
		}
		return actions;
	}

	*//**
	 * Sends keys to the element.
	 * 
	 * @param driver
	 * @param element
	 * @param keysToSend
	 * @return
	 * @throws Exception
	 *//*
	public static Actions sendKeys(WebDriver driver, WebElement element,
			CharSequence keysToSend) throws Exception {
		scrollToElement(driver, element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().sendKeys(keysToSend).perform();
		return actions;
	}

	*//**
	 * Sends keys but most importantly will also over-write existing any text in
	 * the element.
	 * 
	 * @param driver
	 * @param element
	 * @param keysToSend
	 * @return
	 * @throws Exception
	 *//*
	public static Actions clearAndSendKeys(WebDriver driver,
			WebElement element, CharSequence keysToSend) throws Exception {
		scrollToElement(driver, element);
		Actions actions = new Actions(driver);
		element.clear();
		actions.moveToElement(element).click().sendKeys(keysToSend).perform();
		return actions;
	}

	public static WebElement waitForElement(WebDriver driver, By locator,
			long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		ExpectedCondition<WebElement> condition = ExpectedConditions
				.presenceOfElementLocated(locator);
		WebElement element;
		try {
			element = wait.until(condition);
			if (isLogWebElements())
				Log.log(driver).info("Found: " + locator.toString());
		} catch (Exception ex) {
			throw new WebElementNotFoundException("Failed to find element: "
					+ locator.toString(), ex, driver);
		}
		return element;
	}

	public static WebElement waitForVisibleElement(WebDriver driver,
			By locator, long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		ExpectedCondition<WebElement> condition = ExpectedConditions
				.visibilityOfElementLocated(locator);
		WebElement element;
		try {
			element = wait.until(condition);
			if (Utils.logWebElements)
				Log.log(driver).info("Found: " + locator.toString());
		} catch (Exception ex) {
			throw new WebElementNotVisibleException("Failed to find element: "
					+ locator.toString(), ex, driver);
		}
		return element;
	}

	public static WebElement waitForElement(WebDriver driver,
			WebElement parent, By locator, long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		final By location = locator;
		final WebElement parentElement = parent;
		ExpectedCondition<WebElement> condition = new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return parentElement.findElement(location);
			}
		};
		Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
		WebElement element;
		try {
			element = wait.until(condition);
			if (Utils.logWebElements)
				Log.log(driver).info("Found: " + location.toString());
		} catch (Exception ex) {
			throw new WebElementNotFoundException("Failed to find element: "
					+ location.toString(), ex, driver);
		}
		return element;
	}

	public static void waitForElementExtinct(WebDriver driver, By locator,
			long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		final By location = locator;
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				List<WebElement> elements = driver.findElements(location);
				return elements.isEmpty();
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(driver, timeout).ignoring(
				NoSuchElementException.class).ignoring(
				StaleElementReferenceException.class);

		try {
			wait.until(condition);
			if (Utils.logWebElements)
				Log.log(driver).info("Extinct: " + location.toString());
		} catch (Exception ex) {
			throw new WebElementNotDisappearedException(
					"Failed to wait for element extinction: "
							+ location.toString(), ex, driver);
		}
	}

	public static void waitForElementDisappear(WebDriver driver, By locator,
			long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		final By location = locator;
		// ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath));
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				List<WebElement> elements = driver.findElements(location);
				// If the list of elements is empty or they are not visible,
				// then let's just say it has disappeared. =).
				return (elements.isEmpty() || !hasDisplayedElement(driver,
						elements));
			}
		};

		Wait<WebDriver> wait = new WebDriverWait(driver, timeout).ignoring(
				NoSuchElementException.class).ignoring(
				StaleElementReferenceException.class);

		try {
			wait.until(condition);
			if (Utils.logWebElements)
				Log.log(driver).info("Disappeared: " + location.toString());
		} catch (Exception ex) {
			throw new WebElementNotDisappearedException(
					"Failed to wait for element disappearance: "
							+ location.toString(), ex, driver);
		}
	}

	*//**
	 * Checks if all elements in the list are visible and swallows up any
	 * exceptions raised by stale elements in the DOM.
	 * 
	 * @param driver
	 * @param elements
	 * @return True if any single element is visible, otherwise false.
	 *//*
	public static <T extends WebElement> boolean hasDisplayedElement(
			WebDriver driver, List<T> elements) {
		boolean isDisplayed = false;
		for (T e : elements) {
			try {
				if (e.isDisplayed()) {
					isDisplayed = true;
					break;
				}
			} catch (StaleElementReferenceException ex) {
				Utils.handleExceptionWarning(driver, ex, false, false);
			}
		}
		return isDisplayed;
	}

	*//**
	 * Checks if all elements in the list are visible.
	 * 
	 * @param elements
	 * @return True if any single element is visible, otherwise false.
	 *//*
	public static <T extends WebElement> boolean hasDisplayedElement(
			List<T> elements) {
		boolean isDisplayed = false;
		for (T e : elements) {
			if (e.isDisplayed()) {
				isDisplayed = true;
				break;
			}
		}
		return isDisplayed;
	}

	public static List<WebElement> waitForElements(WebDriver driver,
			By locator, long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		ExpectedCondition<List<WebElement>> condition = ExpectedConditions
				.presenceOfAllElementsLocatedBy(locator);
		List<WebElement> list = null;
		try {
			list = wait.until(condition);
			if (Utils.logWebElements)
				Log.log(driver).info("Found: " + locator.toString());
		} catch (Exception ex) {
			throw new WebElementNotFoundException(locator.toString(), ex,
					driver);
		}
		return list;
	}

	public static List<WebElement> waitForAnyElements(WebDriver driver,
			By locator, long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		ExpectedCondition<List<WebElement>> condition = ExpectedConditions
				.presenceOfAllElementsLocatedBy(locator);
		List<WebElement> list = new ArrayList<WebElement>();
		try {
			list = wait.until(condition);
			if (Utils.logWebElements)
				Log.log(driver).info("Found: " + locator.toString());
		} catch (Exception ex) {
		}
		return list;
	}

	public static List<WebElement> waitForAnyElements(WebDriver driver,
			WebElement parent, By locator, long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		final By location = locator;
		final WebElement parentElement = parent;
		ExpectedCondition<List<WebElement>> condition = new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver) {
				return parentElement.findElements(location);
			}
		};
		List<WebElement> list = new ArrayList<WebElement>();
		try {
			list = wait.until(condition);
			if (Utils.logWebElements)
				Log.log(driver).info("Found: " + location.toString());
		} catch (Exception ex) {
		}
		return list;
	}

	public static List<WebElement> waitForVisibleElements(WebDriver driver,
			By locator, long timeout) throws Exception {
		waitForDomInteractiveNoException(driver);
		final By location = locator;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				List<WebElement> elements = driver.findElements(location);
				return !elements.isEmpty() && hasDisplayedElement(elements);
			}
		};
		List<WebElement> list = null;
		try {
			wait.until(condition);
			list = driver.findElements(location);
			if (Utils.logWebElements)
				Log.log(driver).info("Found: " + location.toString());
		} catch (Exception ex) {
			throw new WebElementNotVisibleException(location.toString(), ex,
					driver);
		}

		return list;
	}

	*//**
	 * Waits for a visible element - Helps avoid running into issues when trying
	 * to click on a web element.
	 * 
	 * @param driver
	 * @param element
	 * @return
	 * @throws Exception
	 *//*
	public static boolean waitForVisibleElement(WebDriver driver,
			WebElement element) throws Exception {
		final WebElement webElement = element;
		ExpectedCondition<WebElement> condition = ExpectedConditions
				.visibilityOf(webElement);
		Wait<WebDriver> wait = new WebDriverWait(driver,
				Constants.GLOBAL_DRIVER_TIMEOUT);
		boolean isSuccessful = false;
		try {
			wait.until(condition);
			isSuccessful = true;
			if (Utils.logWebElements)
				Log.log(driver).info("Displayed: " + element.toString());
		} catch (Exception ex) {
			throw new WebElementNotVisibleException(
					"Failed to wait for element visibility: "
							+ webElement.toString(), ex, driver);
		}
		return isSuccessful;
	}

	*//**
	 * Swallows exceptions, use with care. This is discouraged, please try to
	 * use wait methods instead.
	 * 
	 * @param millis
	 *            Time to sleep in milliseconds (1sec = 1000ms)
	 *//*
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	*//**
	 * Allows us to recreate an exception.
	 * 
	 * @param ex
	 * @param addition
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static <T extends Exception> Exception repackageException(
			Exception origException, Class<T> newException) {

		try {

			Constructor<?> list[] = newException.getConstructors();
			Constructor<?> constructor = null;

			for (int i = 0; i < list.length; ++i) {

				Constructor<?> construct = list[i];
				Class<?> params[] = construct.getParameterTypes();

				// We're targeting the default Exception constructor that also
				// allows the caller to pass in the cause of the exception.
				//
				// If it turns out that the exception doesn't have a cause,
				// we'll just use the other default constructor that only takes
				// a string.

				if (origException.getCause() != null && params.length == 2
						&& params[0].isAssignableFrom(String.class)
						&& params[1].isAssignableFrom(Throwable.class)) {

					T exception = (T) construct.newInstance(
							origException.getMessage(),
							origException.getCause());
					exception.setStackTrace(origException.getStackTrace());
					return exception;

				} else if (params.length == 1
						&& params[0].isAssignableFrom(String.class)) {

					constructor = construct;

					if (origException.getCause() == null) {
						break;
					}
				}
			}

			if (constructor != null) {
				T exception = (T) constructor.newInstance(origException
						.getMessage());
				exception.setStackTrace(origException.getStackTrace());
				return exception;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return origException;
	}

	public static <T extends Exception> Exception repackageException(
			WebDriver driver, T ex, String addition) {
		return repackageException(ex, addition + " "
				+ generateExceptionMsg(driver));
	}

	*//**
	 * We'll recreate any raised exceptions and append meta-data like the
	 * conversation ID, current web-driver page, and etc.
	 * 
	 * @param driver
	 * @param ex
	 * @return
	 *//*
	public static <T extends Exception> Exception repackageException(
			WebDriver driver, T ex) {
		return repackageException(ex, generateExceptionMsg(driver));
	}

	*//**
	 * Allows us to recreate an exception and append additional information.
	 * 
	 * @param ex
	 * @param addition
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static <T extends Exception> Exception repackageException(T ex,
			String addition) {

		try {

			Constructor<?> list[] = ex.getClass().getConstructors();
			Constructor<?> constructor = null;

			for (int i = 0; i < list.length; ++i) {

				Constructor<?> construct = list[i];
				Class<?> params[] = construct.getParameterTypes();

				// We're targeting the default Exception constructor that also
				// allows the caller to pass in the cause of the exception.
				//
				// If it turns out that the exception doesn't have a cause,
				// we'll just use the other default constructor that only takes
				// a string.

				if (ex.getCause() != null && params.length == 2
						&& params[0].isAssignableFrom(String.class)
						&& params[1].isAssignableFrom(Throwable.class)) {

					String msg = ex.getMessage() + ": " + addition;
					T exception = (T) construct.newInstance(msg, ex.getCause());
					exception.setStackTrace(ex.getStackTrace());
					return exception;

				} else if (params.length == 1
						&& params[0].isAssignableFrom(String.class)) {

					constructor = construct;

					if (ex.getCause() == null) {
						break;
					}
				}
			}

			if (constructor != null) {
				String msg = ex.getMessage() + ": " + addition;
				T exception = (T) constructor.newInstance(msg);
				exception.setStackTrace(ex.getStackTrace());
				return exception;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ex;
	}

	*//**
	 * Constructs a default exception message includes the conversation ID.
	 * 
	 * @see Utils#generateExceptionMsg(WebDriver, String)
	 * @param driver
	 * @return
	 *//*
	public static String generateExceptionMsg(WebDriver driver) {
		return Utils.generateExceptionMsg(driver, null);
	}

	*//**
	 * For tests that have been deteremined as passed. The screenshot is not
	 * compressed.
	 * 
	 * @param driver
	 * @return
	 *//*
	public static String captureScreenshotPass(WebDriver driver) {
		return captureScreenshot(driver, "Success", false);
	}

	*//**
	 * For tests that have not been determined as pass nor failed. The
	 * screenshot is not compressed.
	 * 
	 * @param driver
	 * @return
	 *//*
	public static String captureScreenshotDone(WebDriver driver) {
		return captureScreenshot(driver, "Done", false);
	}

	*//**
	 * A quick generic screen capture. The image will be compressed and the
	 * image description is defaulted to "Snapshot".
	 * 
	 * @param driver
	 * @return
	 *//*
	public static String captureScreenshot(WebDriver driver) {
		return captureScreenshot(driver, null, true);
	}

	*//**
	 * Takes a screenshot of driver/browser - appends useful info like timestamp
	 * and test name. By default this will compress the screen shot to conserve
	 * disk space.
	 * 
	 * @see #captureScreenshot(WebDriver, String, boolean)
	 * @param driver
	 * @param imageDescription
	 *            The description of the snapshot - this will be a part of the
	 *            PNG file name.
	 * @return
	 *//*
	public static String captureScreenshot(WebDriver driver,
			String imageDescription) {
		return captureScreenshot(driver, imageDescription, false);
	}

	*//**
	 * 
	 * @param driver
	 * @param imageDescription
	 * @param shouldCompress
	 * @return
	 *//*
	public static String captureScreenshot(WebDriver driver,
			String imageDescription, boolean shouldCompress) {

		if (imageDescription == null || imageDescription.isEmpty()) {
			imageDescription = "Snapshot";
		}

		// Remove all white-space characters.
		imageDescription = imageDescription.trim().replaceAll("\\s", "");

		String testName = Log.lookupTestName(driver);
		if (testName == null) {
			testName = "";
		}

		String filename = testName.concat("_")
				.concat(String.valueOf(System.currentTimeMillis())).concat("_")
				.concat(imageDescription);

		String strSeleniumGrid = System.getProperty("selenium.grid");
		// This is for remote-driver only
		if ("true".equalsIgnoreCase(strSeleniumGrid)) {
			driver = new Augmenter().augment(driver);
		}

		File scrFile = null;
		int attempt = 0;

		while (scrFile == null && attempt++ < 2) {
			try {
				scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
			} catch (Exception ex) {
				if (driver == null) {
					Log.log(driver)
							.warning("Screenshot failed, driver is null");
					break;
				}
				Log.log(driver).warning("Screenshot failed, attempting retry");
				Utils.sleep(250);
			}
		}

		String destPngFilename = Log.FOLDER_PATH + filename + ".png";
		File destinationPng = new File(destPngFilename);

		Integer compressionLevel = shouldCompress ? 9 : 4;

		// Here are all of the ways in which we can help reduce the file size.
		//
		// Option 1: Resize the image (scale it down).
		// Option 2: Use PNG-Tastic to compress, seems to work quite well.
		// Option 3: Using javax's ImageIO (slightly better than the default).
		// Option 4: Use Selenium's default, which is highly totally lossless.

		try {

			PngOptimizer optimizer = new PngOptimizer("error");
			optimizer.setCompressor("");

			try {

				// Resizing might take a LOT longer than expected, will need
				// to keep a close eye on this.

				if (shouldCompress) {

					// Scoped for explicitly ensuring we're dealing with the
					// image in only a limited context.

					{
						BufferedImage img = ImageIO.read(scrFile);
						BufferedImage bdest = new BufferedImage(
								(int) (img.getWidth() * 0.66),
								(int) (img.getHeight() * 0.66),
								BufferedImage.TYPE_INT_ARGB);
						Graphics2D g = bdest.createGraphics();
						g.drawImage(img, 0, 0, (int) (img.getWidth() * 0.66),
								(int) (img.getHeight() * 0.66), null);
						g.dispose();
						ImageIO.write(bdest, "png", destinationPng);

						img.flush();
						bdest.flush();

						// See notes below on garbaage collectiones.

						g = null;
						bdest = null;
						img = null;
					}

					PngImage image = new PngImage(
							destinationPng.getAbsolutePath());
					optimizer.optimize(image, destPngFilename, false,
							compressionLevel);

					// SIGH, just cause there's garbage collection doesn't mean
					// everything gets purged immediately. Set these to null
					// to indicate to the JVM we want these disposed of.

					//image = null;

				} else {

					// Even though shouldCompress is "false", we're going to
					// do some very light compression on it anyway.

					PngImage image = new PngImage(scrFile.getAbsolutePath());
					optimizer.optimize(image, destPngFilename, false,
							compressionLevel);
					image = null;
				}

				// Initially suspected Pngtastic was the memory hog, turns out
				// that ImageIO is also suspect - so we'll leave this here to
				// indicate that anyway.

				//optimizer = null;

			} catch (Exception e) {
				Log.log(driver).warning(
						"Failed to compress file, will default to ImageIO");
				BufferedImage img = ImageIO.read(scrFile);
				ImageIO.write(img, "png", destinationPng);
				img = null;
			}

		} catch (Exception ex) {
			Log.log(driver).warning(ex.getMessage());

			try {
				FileUtils.copyFile(scrFile, destinationPng);
			} catch (Exception e) {
				Log.log(driver).severe(e.getMessage());
			}
		}
		return destPngFilename;
	}

	public static void takeScreenShot(String Code, WebDriver driver,
			String page) {

		String name = System.getProperty("user.dir")
				+ "\\ScreenShots\\GateChanges";

		createDir(name, "Project Name");

		name = name + "\\GateChanges_Execution_" + scrShotDir;

		createDir(name, "Project_Execution_TimeStamp");

		name = name + "\\Product_" + Code;
		
		createDir(name, "Product Code");

		String execlog = miscProps.getProperty("execlog");

		if (execlog.equalsIgnoreCase("NOTSET")) {
			execlog = scrShot.format(new Date());
			miscProps.setProperty("execlog", execlog);
		}

		name = name + "\\" + "Log - " + miscProps.getProperty("execlog");
		
		createDir(name, "Log Name");

		

		strScreenshotName = name + "\\" + page + "_"
				+ scrShot.format(new Date()) + ".png";

		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(f, new File(strScreenshotName));
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		
		strScreenshotName = page+" Screen Shot Created: " + strScreenshotName;
		
		Log.log(driver).info(strScreenshotName);
	}

	public static void createDir(String dirName, String Message) {
		File f = new File(dirName);
		try {
			if (!f.exists()) {
				f.mkdir();
				System.out.println("Directory Created :: " + Message);
			}
		} catch (Throwable e) {
			System.out.println("Unable to create directory with " + Message);
		}
	}

	*//**
	 * Handles the exception by printing out the stack trace, driver
	 * information, conversation ID, the current page source i.e. HTML and even
	 * a snapshot to the default log directory. Verbose information is sent
	 * directly to the error log file. A less verbose version of the error is
	 * sent to the log file.
	 * 
	 * @see #handleException(WebDriver, Exception, boolean, boolean)
	 * @param driver
	 * @param ex
	 *//*
	public static void handleException(WebDriver driver, Exception ex) {
		handleException(driver, ex, true, true, false);
	}

	*//**
	 * Handles the exception by printing out a message the contains the driver
	 * information, conversation ID and the current page source i.e. HTML.
	 * Verbose information is sent directly to the error log file. A less
	 * verbose version of the error is sent to the log file.
	 * 
	 * Note: This static method is capable of handling a null driver.
	 * 
	 * @param driver
	 * @param ex
	 * @param createSnapshot
	 * @param printStackTrace
	 *            Builds stack traces into the error log.
	 * @param printCauseToLog
	 *            Builds all of the errors messages from caused-by exceptions.
	 *//*
	public static void handleException(WebDriver driver, Exception ex,
			boolean createSnapshot, boolean printStackTrace,
			boolean printCauseToLog) {

		if (ex == null) {
			Log.log(driver).severe("Exception is NULL");
			return;
		}

		StackTraceElement[] stackTrace = ex.getStackTrace();

		String logMsg = ex.getClass().getName() + ": " + ex.getMessage();
		String errorMsg = "";

		if (printStackTrace) {

			errorMsg += Constants.NEW_LINE;

			for (int i = 0; i < stackTrace.length; i++) {
				errorMsg += "\t" + stackTrace[i].toString()
						+ Constants.NEW_LINE;
			}

			Throwable cause = ex.getCause();
			while (cause != null) {
				stackTrace = cause.getStackTrace();
				errorMsg += Constants.NEW_LINE + "Caused by: "
						+ cause.getMessage() + Constants.NEW_LINE;
				for (int i = 0; i < stackTrace.length; i++) {
					errorMsg += "\t" + stackTrace[i].toString()
							+ Constants.NEW_LINE;
				}

				if (cause.getMessage() != null) {
					if (printCauseToLog) {
						logMsg += Constants.NEW_LINE + "\tCaused by: "
								+ cause.getMessage();
					}
				}
				cause = cause.getCause();
			}

		} else {
			Throwable cause = ex.getCause();
			while (cause != null) {
				if (cause.getMessage() != null) {
					if (printCauseToLog) {
						logMsg += Constants.NEW_LINE + "\tCaused by: "
								+ cause.getMessage();
					}
				}
				cause = cause.getCause();
			}
		}

		// for(Throwable t : ex.getSuppressed()) {
		// if(t == null) continue;
		// stackTrace = t.getStackTrace();
		// errorMsg += "\nSuppressed: " + t.getMessage() + Constants.NEW_LINE;
		// for (int i = 0; i < stackTrace.length; i++) {
		// errorMsg += "\t" + stackTrace[i].toString() + Constants.NEW_LINE;
		// }
		// }

		String snapshot = "";
		String imageFilename = "";

		try {
			if (createSnapshot) {
				String imageDescription = (ex.getClass().getSimpleName() == null ? "Error"
						: ex.getClass().getSimpleName());
				imageFilename = captureScreenshot(driver, imageDescription,
						false);
				snapshot = "Screenshot of error: " + imageFilename
						+ Constants.NEW_LINE;
			}

		} catch (Exception exception) {
			snapshot = "WARNING, Failed to capure screenshot ["
					+ exception.getMessage() + "]";
			Log.log(driver).warning(
					"Caught exception: " + exception.getMessage());
			exception.printStackTrace();
		}

		String currentUrl = null;
		String exMessage = null;

		try {
			currentUrl = driver.getCurrentUrl();
		} catch (Exception exception) {
			Log.log(driver).warning(
					"Caught exception: " + exception.getMessage());
		}

		try {
			exMessage = ex.getMessage().replaceAll("\n", Constants.NEW_LINE);
		} catch (Exception exception) {
			Log.log(driver).warning(
					"Caught exception: " + exception.getMessage());
		}

		String conversationId = "NONE";
		Utils utils = Utils.getInstance(driver);
		if (utils != null) {
			conversationId = utils.getConversationId();
		}

		errorMsg = "(" + (driver == null ? "NULL DRIVER" : driver.toString())
				+ ") " + "Error Location: " + currentUrl + " [[ConvoId:"
				+ conversationId + "]]" + Constants.NEW_LINE
				+ Constants.NEW_LINE + snapshot + exMessage + errorMsg;

		Log.log(driver).severe(logMsg);
		Log.errorLog(driver).severe(errorMsg);

		if (printStackTrace) {
			Log log = Log.getLog(driver);
			if (log != null) {
				// We want to avoid printing out to stream if it's already going
				// to
				// console by the regular call with the loggers.
				if (!log.isUsingStdStreamOut()) {
					ex.printStackTrace();
				}
			} else {
				// If log is null, then lets be on the safe side and print it
				// out.
				ex.printStackTrace();
			}
		}

		try {

			// Avoid recording the page source for Web UI related errors that
			// already display an error message.
			//
			// The intent of this is to provide a snapshot of the DOM (partial)
			// to discover error message containers and also provides a
			// potentially faster way of identifying x-path changes.

			if (logPageSource && !(ex instanceof WebUiErrorException)
					&& !(ex instanceof UnexpectedPageUrlException)
					&& !(ex instanceof SQLException)) {

				// Since the image files are generally regarded as unique,
				// we'll piggy-back off of that and use it to name the
				// associated html dump as well. It should be extremely easy
				// to match up in the Bamboo artifact log folder.

				if (!imageFilename.isEmpty()) {

					// Writing the page source to a file in order to prevent
					// flooding the error log.

					String htmlFilename = imageFilename.concat(".txt");
					BufferedWriter out = new BufferedWriter(new FileWriter(
							htmlFilename));
					out.write(driver.getPageSource());
					out.close();

				} else {

					// Finally, if we can't write to a file then let's just
					// stuff it into the log file.

					Log.errorLog(driver).info(
							Constants.NEW_LINE + driver.getPageSource()
									+ Constants.NEW_LINE);
				}
			}

		} catch (Exception exception) {
		}
	}

	*//**
	 * Handles an exception by recording it as a warning.
	 * 
	 * Note: This static method is capable of handling a null driver.
	 * 
	 * @see #handleException(WebDriver, Exception, boolean, boolean)
	 * @param driver
	 * @param ex
	 * @param createSnapshot
	 * @param printStackTrace
	 *//*
	public static void handleExceptionWarning(WebDriver driver, Exception ex,
			boolean createSnapshot, boolean printStackTrace) {
		if (ex == null) {
			Log.log(driver).severe("Exception is NULL");
			return;
		}

		StackTraceElement[] stackTrace = ex.getStackTrace();
		String errorMsg = "";

		if (printStackTrace) {

			errorMsg += Constants.NEW_LINE;

			for (int i = 0; i < stackTrace.length; i++) {
				errorMsg += "\t" + stackTrace[i].toString()
						+ Constants.NEW_LINE;
			}

			Throwable cause = ex.getCause();
			while (cause != null) {
				stackTrace = cause.getStackTrace();
				errorMsg += Constants.NEW_LINE + "Caused by: "
						+ cause.getMessage() + Constants.NEW_LINE;
				for (int i = 0; i < stackTrace.length; i++) {
					errorMsg += "\t" + stackTrace[i].toString()
							+ Constants.NEW_LINE;
				}
				cause = cause.getCause();
			}
		}

		// for(Throwable t : ex.getSuppressed()) {
		// if(t == null) continue;
		// stackTrace = t.getStackTrace();
		// errorMsg += "\nSuppressed: " + t.getMessage() + Constants.NEW_LINE;
		// for (int i = 0; i < stackTrace.length; i++) {
		// errorMsg += "\t" + stackTrace[i].toString() + Constants.NEW_LINE;
		// }
		// }

		String snapshot = "";

		try {
			if (createSnapshot) {
				String imageName = (ex.getClass().getSimpleName() == null ? "Warning"
						: (ex.getClass().getSimpleName() + "Warn"));

				snapshot = "Screenshot of warning: "
						+ captureScreenshot(driver, imageName, false)
						+ Constants.NEW_LINE;
			}
		} catch (Exception exception) {
			snapshot = "WARNING, Failed to capure screenshot ["
					+ exception.getMessage() + "]";
			Log.log(driver).warning(
					"Caught exception: " + exception.getMessage());
			exception.printStackTrace();
		}

		String currentUrl = null;
		String exMessage = null;

		try {
			currentUrl = driver.getCurrentUrl();
		} catch (Exception exception) {
			Log.log(driver).warning(
					"Caught exception: " + exception.getMessage());
		}

		try {
			exMessage = ex.getMessage().replaceAll("\n", Constants.NEW_LINE);
		} catch (Exception exception) {
			Log.log(driver).warning(
					"Caught exception: " + exception.getMessage());
		}

		errorMsg = "(" + (driver == null ? "NULL DRIVER" : driver.toString())
				+ ") " + "Warning Location: " + currentUrl + Constants.NEW_LINE
				+ Constants.NEW_LINE + snapshot + exMessage + errorMsg;

		Log.log(driver).warning(errorMsg);

		if (printStackTrace) {
			ex.printStackTrace();
		}
	}

	*//**
	 * Constructs a default exception message includes the conversation ID.
	 * 
	 * @param driver
	 * @param message
	 * @return
	 *//*
	public static String generateExceptionMsg(WebDriver driver, String message) {

		String msg = (message == null ? "" : message);

		if (driver != null) {
			String url = driver.getCurrentUrl();

			if (url != null && !url.isEmpty()) {
				msg = msg.concat(" ").concat(url);
			}

			Utils utils = getInstance(driver);
			if (utils != null) {
				String conversationId = utils.getConversationId();

				if (conversationId != null && !conversationId.isEmpty()) {
					msg = msg.concat(" [[ConvoId:").concat(conversationId)
							.concat("]]");
				}
			}
		}
		return msg;
	}
}
*/