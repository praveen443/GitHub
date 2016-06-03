package core.interfaces.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.WebDriverSetup;
import core.interfaces.Element;
import core.interfaces.exception.ElementNotDisabledException;
import core.interfaces.exception.ElementNotEnabledException;
import core.interfaces.exception.ElementNotHiddenException;
import core.interfaces.exception.ElementNotVisibleException;
import core.interfaces.exception.TextInElementNotPresentException;
import selenium.common.Constants;
import utils.Sleeper;
import utils.TestReporter;
import org.apache.commons.lang3.time.StopWatch;

/**
 * An implementation of the Element interface. Delegates its work to an
 * underlying WebElement instance for custom functionality.
 */
public class ElementImpl implements Element {

	protected WebElement element;
	protected java.util.Date date = new java.util.Date();
	protected java.util.Date dateAfter = new java.util.Date();

    private StopWatch stopwatch = new StopWatch();
	public ElementImpl(final WebElement element) {
		this.element = element;
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#click()
	 */
	public void click() {
		element.click();
		TestReporter.log(" Clicked [ <b>@FindBy: " + getElementLocatorInfo() + " </b>]");
	}

	public void jsClick(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);arguments[0].click();", element);
		TestReporter.log( " Clicked [ <b>@FindBy: " + getElementLocatorInfo() + " </b>]");
	}

	public void focus(WebDriver driver) {
		new Actions(driver).moveToElement(element).build().perform();
	}

	public void focusClick(WebDriver driver) {
		new Actions(driver).moveToElement(element).click().perform();
		TestReporter.log(" Focus Clicked [ <b>@FindBy: " + getElementLocatorInfo()
				+ " </b>]");
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#getLocation()
	 */
	public Point getLocation() {
		return element.getLocation();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#submit()
	 */
	public void submit() {
		element.submit();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#getAttribute()
	 */
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#getCssValue()
	 */
	public String getCssValue(String propertyName) {
		return element.getCssValue(propertyName);
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#getSize()
	 */
	public Dimension getSize() {
		return element.getSize();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#findElements()
	 */
	public List<WebElement> findElements(By by) {
		return element.findElements(by);
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#getText()
	 */
	public String getText() {
		return element.getText();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#getTagName()
	 */
	public String getTagName() {
		return element.getTagName();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#findElement()
	 */
	public WebElement findElement(By by) {
		return element.findElement(by);
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#isEnabled()
	 */
	public boolean isEnabled() {
		return element.isEnabled();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#isDisplayed()
	 */
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	public boolean isSelected() {
		return element.isSelected();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#clear()
	 */
	public void clear() {
		element.clear();
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#sendKeys()
	 */
	public void sendKeys(CharSequence... keysToSend) {
		if (keysToSend.toString() != "") {
			element.sendKeys(keysToSend);
			TestReporter.log(" Send Keys [ <b>" + keysToSend.toString()
					+ "</b> ] to Textbox [ <b>@FindBy: " + getElementLocatorInfo() + " </b> ]");
		}
	}

	/**
	 * 
	 * @see org.openqa.selenium.WebElement#getWrappedElement()
	 */
	public WebElement getWrappedElement() {
		return element;
	}

	/**
	 * 
	 * @see org.openqa.selenium.internal.Locatable#getCoordinates();
	 */
	public Coordinates getCoordinates() {
		return ((Locatable) element).getCoordinates();
	}
	@Override
	public WebDriver getWrappedDriver() {
	
			Field privateStringField = null;
			try {
				privateStringField = element.getClass().getDeclaredField("parent");
				privateStringField.setAccessible(true);
				return (WebDriver) privateStringField.get(element);
			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}
	
		return null;
	}
	@Override
	public boolean elementWired() {
		return (element != null);
	}

	/**
	 *
	 * Used in conjunction with WebObjectPresent to determine if the desired
	 * element is present in the DOM Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not present within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncPresent(WebDriver driver) {
		boolean found = false;
		double loopTimeout = 0;
		By locator = getElementLocator();
		loopTimeout = WebDriverSetup.getDefaultTestTimeout() * 10;
		TestReporter.log("<i> Syncing to element [ <b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be <b>PRESENT</b> in DOM within [ <b>" + WebDriverSetup.getDefaultTestTimeout()
				+ "</b>] seconds.</i>");
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {

			if (webElementPresent(driver, locator)) {
				found = true;
				break;
			}
			try {
				Sleeper.sleep(100);

			} catch (Exception e) {
			}

		}

		if (!found) {
			dateAfter = new java.util.Date();
			TestReporter.log("<i>Element [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] is not <b>PRESENT</b> on the page after [ <b>"
					+ (dateAfter.getTime() - date.getTime()) / 1000.0 + "</b>] seconds.</i>");
			throw new RuntimeException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] is not PRESENT on the page after [ "
							+ (dateAfter.getTime() - date.getTime()) / 1000.0 + " ] seconds.");
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectPresent to determine if the desired
	 * element is present in the DOM Will loop for the time out passed in
	 * parameter timeout If object is not present within the time, throw an
	 * error
	 * 
	 * @author Justin
	 */
	public boolean syncPresent(WebDriver driver, int timeout) {
		boolean found = false;
		double loopTimeout = 0;
		By locator = getElementLocator();
		loopTimeout = timeout * 10;
		TestReporter.log("::<i> Syncing to element [ <b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be <b>PRESENT</b> in DOM within [<b> " + timeout + " <b/>] seconds.</i>");

		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {

			if (webElementPresent(driver, locator)) {
				found = true;
				break;
			}
			try {
				Sleeper.sleep(100);

			} catch (Exception e) {
			}

		}

		if (!found) {
			dateAfter = new java.util.Date();
			TestReporter.log("<i>Element [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] is not <b>PRESENT</b> on the page after [</b>"
					+ (dateAfter.getTime() - date.getTime()) / 1000.0 + "</b>] seconds.</i>");
			throw new RuntimeException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] is not PRESENT on the page after [ "
							+ (dateAfter.getTime() - date.getTime()) / 1000.0 + " ] seconds.");
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectPresent to determine if the desired
	 * element is present in the DOM Will loop for the time out passed in
	 * parameter timeout If object is not present within the time, handle error
	 * based on returnError
	 * 
	 * @author Justin
	 */
	public boolean syncPresent(WebDriver driver, int timeout, boolean returnError) {
		boolean found = false;
		long timeLapse;
		By locator = getElementLocator();
		TestReporter.log("<i> Syncing to element [ <b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be <b>PRESENT</b> in DOM within [<b> " + timeout + " <b/>] seconds.</i>");
		StopWatch stopwatch = new StopWatch();

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		stopwatch.start();
		do {
			if (!webElementPresent(driver, locator)) {
				found = true;
				break;
			}

		} while (stopwatch.getTime() / 1000.0 < (long) timeout);
		stopwatch.stop();
		timeLapse = stopwatch.getTime();
		stopwatch.reset();
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		if (!found && returnError) {
			dateAfter = new java.util.Date();
			TestReporter.log("<i>Element [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] is not <b>PRESENT</b> on the page after [<b> "
					+  (timeLapse) / 1000.0 + "</b>] seconds.</i>");
			throw new RuntimeException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] is not PRESENT on the page after [ "
							+  (timeLapse) / 1000.0  + " ] seconds.");
		}
	
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not visible within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncVisible(WebDriver driver) {
		return syncVisible(driver, WebDriverSetup.getDefaultTestTimeout(), true);
	}

	/**
	 * 
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out passed in the
	 * variable timeout If object is not visible within the time, throw an error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncVisible(WebDriver driver, int timeout) {
		return syncVisible(driver, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out passed in the
	 * variable timeout If object is not visible within the time, handle the
	 * error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	public boolean syncVisible(WebDriver driver, int timeout, boolean returnError) {
		boolean found = false;
		long timeLapse = 0;
		StopWatch stopwatch = new StopWatch();
		TestReporter.log("<i>Syncing to element [<b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be <b>VISIBLE</b> within [<b> " + timeout + "</b>] seconds.</i>");
		getWrappedDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		stopwatch.start();
		do {
		    if (webElementVisible(driver, element)) {
			found = true;
			break;
		    }
		   
		} while (stopwatch.getTime() / 1000.0 < (long) timeout);
		stopwatch.stop();
		getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		timeLapse = stopwatch.getTime();
		stopwatch.reset();

		if (!found && returnError) {
			TestReporter.log("<i>Element [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] is not <b>VISIBLE</b> on the page after [<b> "
					+  (timeLapse) / 1000.0 +  "</b>] seconds.</i>");
			throw new ElementNotVisibleException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] is not VISIBLE on the page after [ "
							+ (timeLapse) / 1000.0 +  " ] seconds.", driver);
		}
		return found;
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is hidden from the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not visible within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncHidden(WebDriver driver) {
			return syncHidden(driver , WebDriverSetup.getDefaultTestTimeout() , true);
		}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is hidden from the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not visible within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncHidden(WebDriver driver, int timeout) {
		return syncHidden(driver,timeout,true); 
	}

	/**
	 * Used in conjunction with WebObjectVisible to determine if the desired
	 * element is visible on the screen Will loop for the time out passed in the
	 * variable timeout If object is not visible within the time, handle the
	 * error based on the boolean
	 * 
	 * @author Justin
	 */
	public boolean syncHidden(WebDriver driver, int timeout, boolean returnError) {
		boolean found = false;
		long loopTimeout = 0;
		long timeLapse;
		StopWatch stopwatch = new StopWatch();

		TestReporter.log("<i>Syncing to element [<b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be <b>HIDDEN</b> within [ <b>" + timeout + "</b> ] seconds.</i> With LoopCount "
				+ loopTimeout);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		stopwatch.start();
		do {
			if (!webElementVisible(driver, element)) {
				found = true;
				break;
			}

		} while (stopwatch.getTime() / 1000.0 < (long) timeout);
		stopwatch.stop();
		timeLapse = stopwatch.getTime();
		stopwatch.reset();
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		if (!found && returnError) {
			TestReporter.log("<i>Element [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] is not <b>HIDDEN</b> on the page after [ "
					+  (timeLapse) / 1000.0 + " ] seconds.</i>");
			throw new ElementNotHiddenException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] is not HIDDEN on the page after [ <b>"
							+  (timeLapse) / 1000.0 +  "<b>/ ] seconds.", driver);
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is enabled on the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not enabled within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncEnabled(WebDriver driver) {
		return syncEnabled(driver, WebDriverSetup.getDefaultTestTimeout() , true);
	}

	/**
	 * 
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is enabled on the screen Will loop for the time out passed in the
	 * variable timeout If object is not enabled within the time, throw an error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncEnabled(WebDriver driver, int timeout) {
		return syncEnabled(driver, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is enabled on the screen Will loop for the time out passed in the
	 * variable timeout If object is not enabled within the time, handle the
	 * error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	public boolean syncEnabled(WebDriver driver, int timeout, boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;
		long timeLapse;
		StopWatch stopwatch = new StopWatch();
		TestReporter.log("<i>Syncing to element [<b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be <b>ENABLED</b> within [ <b>" + timeout + "</b> ] seconds.</i>");

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		stopwatch.start();
		do {
			if (webElementEnabled(driver, element)) {
				found = true;
				break;
			}

		} while (stopwatch.getTime() / 1000.0 < (long) timeout);
		stopwatch.stop();
		timeLapse = stopwatch.getTime();
		stopwatch.reset();
		driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
	//	driver.setElementTimeout(currentTimeout, TimeUnit.SECONDS);
		if (!found && returnError) {
			TestReporter.interfaceLog("<i>Element [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] is not <b>ENABLED</b> on the page after [<b> "
					+ (timeLapse) / 1000.0 + " <b/>] seconds.</i>");
			throw new ElementNotEnabledException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] is not ENABLED on the page after [ "
							+ (timeLapse) / 1000.0 + " ] seconds.", driver);
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectEnabled to determine if the desired
	 * element is disabled on the screen Will loop for the time out listed in
	 * org.orasi.chameleon.CONSTANT.TIMEOUT If object is not disabled within the
	 * time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncDisabled(WebDriver driver) {
		return syncDisabled(driver,  WebDriverSetup.getDefaultTestTimeout(), true);

	}

	/**
	 * 
	 * Used in conjunction with WebObjectDisabled to determine if the desired
	 * element is disabled on the screen Will loop for the time out passed in
	 * the variable timeout If object is not disabled within the time, throw an
	 * error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncDisabled(WebDriver driver, int timeout) {
		return syncDisabled(driver,timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectDisabled to determine if the desired
	 * element is disabled on the screen Will loop for the time out passed in
	 * the variable timeout If object is not disabled within the time, handle
	 * the error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	public boolean syncDisabled(WebDriver driver, int timeout, boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;
		long timeLapse;

		StopWatch stopwatch = new StopWatch();
		//loopTimeout = Integer.valueOf(timeout) * 10;
		TestReporter.log("<i>Syncing to element [<b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be <b>DISABLED</b> within [ <b>" + timeout + "</b> ] seconds.</i>");
		getWrappedDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		stopwatch.start();
		do {
			if (!webElementEnabled(driver, element)) {
				found = true;
				break;
			}

		} while (stopwatch.getTime() / 1000.0 < (long) timeout);
		stopwatch.stop();
		timeLapse = stopwatch.getTime();
		stopwatch.reset();
		getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
		if (!found && returnError) {
			TestReporter.log("<i>Element [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] is not <b>DISABLED</b> on the page after [<b> "
					+ (timeLapse) / 1000.0 + " <b/>] seconds.</i>");
			throw new ElementNotDisabledException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] is not DISABLED on the page after [ "
					+ (timeLapse) / 1000.0 + " ] seconds.", driver);
		}
		return found;
	}

	/**
	 *
	 * Used in conjunction with WebObjectText Present to determine if the
	 * desired text is present in the desired element Will loop for the time out
	 * listed in org.orasi.chameleon.CONSTANT.TIMEOUT If text is not present
	 * within the time, throw an error
	 * 
	 * @author Justin
	 */
	public boolean syncTextInElement(WebDriver driver, String text) {
		return syncTextInElement(driver, text, WebDriverSetup.getDefaultTestTimeout() , true);
	}

	/**
	 * 
	 * Used in conjunction with WebObjectText Present to determine if the
	 * desired text is present in the desired element Will loop for the time out
	 * passed in the variable timeout If text is not present within the time,
	 * throw an error
	 * 
	 * @author Justin
	 * 
	 */
	public boolean syncTextInElement(WebDriver driver, String text, int timeout) {
		return syncTextInElement(driver, text, timeout, true);
	}

	/**
	 * Used in conjunction with WebObjectText Present to determine if the
	 * desired text is present in the desired element Will loop for the time out
	 * passed in the variable timeout If text is not present within the time,
	 * handle the error based on the boolean
	 *
	 * @author Justin
	 *
	 */
	public boolean syncTextInElement(WebDriver driver, String text, int timeout, boolean returnError) {
		boolean found = false;
		double loopTimeout = 0;
		long timeLapse = 0;

		loopTimeout = Integer.valueOf(timeout) * 10;
		TestReporter.log("<i>Syncing to text in element [<b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to be displayed within [ <b>" + timeout + "</b> ] seconds.</i>");

		StopWatch stopwatch = new StopWatch();
		getWrappedDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		stopwatch.start();
		do {
			if (webElementTextPresent(driver, element,text)) {
				found = true;
				break;
			}

		} while (stopwatch.getTime() / 1000.0 < (long) timeout);
		stopwatch.stop();
		timeLapse = stopwatch.getTime();
		stopwatch.reset();
		getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);

		if (!found && returnError) {
			dateAfter = new java.util.Date();
			TestReporter.log("<i>Element [<b>@FindBy: " + getElementLocatorInfo() + " </b>] did not contain the text [ "
					+ text + " ] after [ <b>" + (timeLapse) / 1000.0 + "</b> ] seconds.</i>");
			throw new TextInElementNotPresentException(
					"Element [ @FindBy: " + getElementLocatorInfo() + " ] did not contain the text [ " + text
							+ " ] after [ " + (timeLapse) / 1000.0 + " ] seconds.", driver);
		}
		return found;
	}

	/**
	 * Use WebDriver Wait to determine if object is present in the DOM or not
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param locator
	 *            {@link By} object to search for
	 * @return TRUE if element is currently present in the DOM, FALSE if the
	 *         element is not present in the DOM
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean webElementPresent(WebDriver driver, By locator) {
		Wait wait = new WebDriverWait(driver, 0);

		try {
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
		} catch (NoSuchElementException | ClassCastException | StaleElementReferenceException | TimeoutException e) {
			return false;
		}

	}

	/**
	 * Use WebDriver Wait to determine if object is visible on the screen or not
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param element
	 *            Element to search for
	 * @return TRUE if element is currently visible on the screen, FALSE if the
	 *         element is not visible on the screen
	 */
	private boolean webElementVisible(WebDriver driver, WebElement element) {
		try {
		    Point location = element.getLocation();

		    Dimension size = element.getSize();
		    if ((location.getX() > 0 & location.getY() > 0) | (size.getHeight() > 0 & size.getWidth() > 0)) {
				if (element.getAttribute("hidden") != null)
                    if (element.getAttribute("hidden").toLowerCase().equals("true")) return false;
				if (element.getAttribute("type") != null) {
					if (element.getAttribute("type").equals("hidden"))return false;
				}else if(element.getCssValue("display") != null){
					if(element.getCssValue("display").equals("none")) return false;
				}
				return true;
			} else {
				return false;
			}

		} catch (WebDriverException | ClassCastException | NullPointerException e) {
		    return false;
		}
	}

	/**
	 * Use WebDriver Wait to determine if object is enabled on the screen or not
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param element
	 *            Element to search for
	 * @return TRUE if element is currently enabled on the screen, FALSE if the
	 *         element is not enabled on the screen
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean webElementEnabled(WebDriver driver, WebElement element) {
		Wait wait = new WebDriverWait(driver, 0);

		try {
			return wait.until(ExpectedConditions.elementToBeClickable(element)) != null;
		} catch (NoSuchElementException | ClassCastException | StaleElementReferenceException | TimeoutException e) {
			return false;
		}

	}

	/**
	 * Use WebDriver Wait to determine if object contains the expected text
	 * 
	 * @author Justin
	 * @param driver
	 *            Main WebDriver
	 * @param element
	 *            Element to search for
	 * @param text
	 *            The text to search for
	 * @return TRUE if element is currently visible on the screen, FALSE if the
	 *         element is not visible on the screen
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean webElementTextPresent(WebDriver driver, WebElement element, String text) {
		Wait wait = new WebDriverWait(driver, 0);
		try {

			if (wait.until(ExpectedConditions.textToBePresentInElement(element, text)) != null) {
				return true;
			} else if (wait.until(ExpectedConditions.textToBePresentInElementValue(element, text)) != null) {
				return true;
			} else {
				return false;
			}
		} catch (WebDriverException | ClassCastException e) {
			try {
				if (wait.until(ExpectedConditions.textToBePresentInElementValue(element, text)) != null) {
					return true;
				} else {
					return false;
				}
			} catch (WebDriverException | ClassCastException  e2) {
				return false;
			}
		}
	}

	/**
	 * Get the By Locator object used to create this element
	 * 
	 * @author Justin
	 * @return {@link By} Return the By object to reuse
	 */
	public By getElementLocator() {
		By by = null;
		String locator = "";
		int startPosition = 0;
		try {
			startPosition = element.toString().lastIndexOf("->") + 3;
			locator = element.toString().substring(startPosition, element.toString().lastIndexOf(": "));
			locator = locator.trim();
			switch (locator) {
			case "className":
			case "class name":
				by = new ByClassName(getElementIdentifier());
				break;
			case "cssSelector":
				by = By.cssSelector(getElementIdentifier());
				break;
			case "id":
				by = By.id(getElementIdentifier());
				break;
			case "linkText":
				by = By.linkText(getElementIdentifier());
				break;
			case "name":
				by = By.name(getElementIdentifier());
				break;
			case "tagName":
				by = By.tagName(getElementIdentifier());
				break;
			case "xpath":
				by = By.xpath(getElementIdentifier());
				break;
			}
			return by;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getElementIdentifier() {
		String locator = "";
		int startPosition = 0;

		startPosition = element.toString().lastIndexOf(": ") + 2;
		locator = element.toString().substring(startPosition, element.toString().lastIndexOf("]"));

		return locator.trim();
	}

	/**
	 * Get the By Locator object used to create this element
	 * 
	 * @author Justin
	 * @return {@link By} Return the By object to reuse
	 */
	private String getElementLocatorAsString() {
		int startPosition = 0;
		String locator = "";
		
		startPosition = element.toString().lastIndexOf("->") + 3;
		locator = element.toString().substring(startPosition, element.toString().lastIndexOf(":"));

		locator = locator.trim();
		return locator;
	}

	public String getElementLocatorInfo() {
		return getElementLocatorAsString() + " = " + getElementIdentifier();
	}

	public void highlight(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", this);
	}

	public void mouseHover(WebDriver driver) {
		new Actions(driver).moveToElement(element).build().perform();
	}
    
	/**
	 * Uses an element's location and size to locate the center of the element on the page and performs a click using JavaScript
	 * @author Waits Avery - 10/28/2015 - Original
	 * @param driver - current web driver
	 */
	public void coordinateClick(WebDriver driver){
		Element element = new ElementImpl(getWrappedElement());
		int xPos = element.getCoordinates().inViewPort().x;
		int yPos = element.getCoordinates().inViewPort().y;
		int width = element.getSize().width;
		int height = element.getSize().height;
		float xMidpoint = (float)xPos + width/2;
		float yMidpoint = (float)yPos - height/2;
		
		String javaScript = "var element = document.elementFromPoint("+xMidpoint+","+yMidpoint+");element.click()";
		// Create a JS executor
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript);
	}
	
	/**
	 * Scrolls an element into view so it can be seen on the screen
	 * @author Waits Avery - 10/28/2015 - Original
	 * @param driver - current web driver
	 */
    public void scrollIntoView(WebDriver driver){

        JavascriptExecutor executor = (JavascriptExecutor)driver; 
        executor.executeScript("arguments[0].scrollIntoView(true)", element);
      //  Reporter.log(new Timestamp(date.getTime()) + " :: Scrolled into view [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");
    }

	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList getAllAttributes(WebDriver driver) {

		return (ArrayList) ((JavascriptExecutor)driver).executeScript(
				"var s = []; var attrs = arguments[0].attributes; for (var l = 0; l < attrs.length; ++l) { var a = attrs[l]; s.push(a.name + ':' + a.value); } ; return s;",
				getWrappedElement());
	}
}