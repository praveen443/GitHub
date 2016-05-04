package core.interfaces;

//package com.disney.composite.core.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindBy;

import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ImplementedBy;


/**
 * wraps a web element interface with extra functionality. Anything added here will be added to all descendants.
 */
@ImplementedBy(ElementImpl.class)
public interface Element extends WebElement, WrapsElement, Locatable {
    
    /**
     * @author Justin     
     * @see main.java.com.disney.core.interfaces.impl.ElementImpl#clear()
     */
    void clear();
    
    /**
     * @author Justin
     * 
     */
    void focus(WebDriver driver);
    //void focus();
    
    /**
     * @author Justin
     * @see org.openqa.selenium.WebElement#click()
     */
    void click();	
    void jsClick(WebDriver driver);
    /**
     * @author Justin\
     * @see org.openqa.selenium.WebElement#click() , 
     */
    void focusClick(WebDriver driver);
    //void focusClick();

    /**
     * @author Justin 
     * @param keysToSend an array of characters or string literals
     * @see main.java.com.disney.core.interfaces.impl.ElementImpl#sendKeys(java.lang.CharSequence...)
     */
    void sendKeys(CharSequence... keysToSend);
    
    /**
     * @author Justin
     * @see org.openqa.selenium.WebElement#submit
     */
    void submit();

    /**
     * @author Justin
     * @param by Search for specified {@link By} location and return it's {@link WebElement}
     * @return {@link WebElement}
     * @see main.java.com.disney.core.interfaces.impl.ElementImpl#findElement()
     */
    WebElement findElement(By by);

    /**
     * @author Justin
     * @param by Search for specified {@link By} location and return all elements found in a {@link List}
     * @return {@link List} 
     * @see main.java.com.disney.core.interfaces.impl.ElementImpl#findElements()
     */
    List<WebElement> findElements(By by);
    

    /**
     * @author Justin
     * @param name Search for specified attribute and return it's value
     * @return {@link String} Value of specified attribute
     * @see main.java.com.disney.core.interfaces.impl.ElementImpl#getAttribute()
     */    
    String getAttribute(String name);

    /**
     * @author Justin
     * @param propertyName Search for specified property and return it's value
     * @return {@link String} Value of specified property
     * @see main.java.com.disney.core.interfaces.impl.ElementImpl#getCssValue()
     */
   String getCssValue(String propertyName);


   /**
    * @return {@link Coordinates} 
    * @see org.orasi.chameleon.interfaces.impl.ElementImpl#getCoordinates();
    */
  Coordinates getCoordinates();

   /**
    * @author Justin
    * @return {@link Point} Return x and y location
    * @see main.java.com.disney.core.interfaces.impl.ElementImpl#getLocation()
    */
   Point getLocation();
   
   /**
    * @author Justin
    * @return {@link Dimension} Return height and width of element
    * @see main.java.com.disney.core.interfaces.impl.ElementImpl#getSize()
    */
   Dimension getSize();

   /**
    * @author Justin
    * @return {@link String} Text value in element
    * @see main.java.com.disney.core.interfaces.impl.ElementImpl#getText()
    */
   String getText();

   /**
    * @author Justin
    * @return {@link String} Tag value in element
    * @see main.java.com.disney.core.interfaces.impl.ElementImpl#getTagName()
    */
   String getTagName();
   
   /**
    * @author Justin
    * @return {@link Boolean} Return TRUE if element is enabled, FALSE if it is not 
    * @see main.java.com.disney.core.interfaces.impl.ElementImpl#isEnabled()
    */
   boolean isEnabled();
   
   /**
    * @author Justin
    * @return {@link Boolean} Return TRUE if element is Displayed, FALSE if it is not  
    * @see main.java.com.disney.core.interfaces.impl.ElementImpl#isDisplayed()
    */
   boolean isDisplayed();
   
    /**
     * Returns true when the inner element is ready to be used.
     * @author Justin
     * @return boolean true for an initialized WebElement, or false if we were somehow passed a null WebElement.
     */
    boolean elementWired();

    /**
     * Used in conjunction with WebObjectPresent to determine if the desired element is built in the DOM
     * Will loop for the number of seconds listed in org.orasi.chameleon.CONSTANTS.TIMEOUT
     * If element is not present in the DOM within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     * @return 
     */
    boolean syncPresent(WebDriver driver);

    /**
     * Used in conjunction with WebObjectPresent to determine if the desired element is built in the DOM
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If element is not present in the DOM within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     */
    //boolean syncPresent(WebDriver driver, int timeout);

    /**
     * Used in conjunction with WebObjectPresent to determine if the desired element is built in the DOM
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not visible within the time, handle the error based on the boolean
     * @author Justin
     * @param driver Main WebDriver
     */
    boolean syncPresent(WebDriver driver, int timeout, boolean returnError);

    /**     
     * Used in conjunction with WebObjectVisible to determine if the desired element is visible on the screen 
     * Will loop for the number of seconds listed in org.orasi.chameleon.CONSTANTS.TIMEOUT
     * If object is not visible within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     */
    boolean syncVisible(WebDriver driver);
    
    /**
     * Used in conjunction with WebObjectVisible to determine if the desired element is visible on the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not visible within the time, throw an error 
     * @author Justin
     * @param driver Main WebDriver
     * @param Will loop for the amount of seconds passed in the variable TIMEOUT
     */
   // boolean syncVisible(WebDriver driver, int timeout);
    
    /**
     * Used in conjunction with WebObjectVisible to determine if the desired element is visible on the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not visible within the time, handle the error based on the boolean
     * @author Justin
     * @param driver Main WebDriver
     * @param Will loop for the amount of seconds passed in the variable TIMEOUT
     * @param returnError true to throw and error if object is not visible on screen, false if error is not to be thrown
     */
    boolean syncVisible(WebDriver driver, int timeout, boolean returnError);
    
    /**     
     * Used in conjunction with WebObjectVisible to determine if the desired element is hidden from the screen 
     * Will loop for the number of seconds listed in org.orasi.chameleon.CONSTANT.TIMEOUT
     * If object is not hidden within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     */
    boolean syncHidden(WebDriver driver);
    
    /**
     * Used in conjunction with WebObjectVisible to determine if the desired element is hidden from the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not hidden within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     * @param returnError true to throw an error if object is not visible on screen, false if error is not to be thrown
     */
    //boolean syncHidden(WebDriver driver, int timeout);
    
    /**
     * Used in conjunction with WebObjectVisible to determine if the desired element is hidden on the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is still hidden within the time, handle the error based on the boolean
     * @author Justin
     * @param driver Main WebDriver
     * @param timeout Signify a timeout to be used (10 will wait for ten seconds)
     * @param returnError true to throw and error if object is not visible on screen, false if error is not to be thrown
     */
    boolean syncHidden(WebDriver driver,int timeout, boolean returnError);
    
    /**     
     * Used in conjunction with WebObjectEnabled to determine if the desired element is enabled on the screen 
     * Will loop for the number of seconds listed in org.orasi.chameleon.CONSTANTS.TIMEOUT
     * If object is not visible within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     */
    boolean syncEnabled(WebDriver driver);
    
    /**
     * Used in conjunction with WebObjectEnabled to determine if the desired element is enabled on the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not visible within the time, throw an error 
     * @author Justin
     * @param driver Main WebDriver
     * @param Will loop for the amount of seconds passed in the variable TIMEOUT
     */
    //boolean syncEnabled(WebDriver driver, int timeout);
    
    /**
     * Used in conjunction with WebObjectEnabled to determine if the desired element is enabled on the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not visible within the time, handle the error based on the boolean
     * @author Justin 
     * @param driver Main WebDriver
     * @param Will loop for the amount of seconds passed in the variable TIMEOUT
     * @param returnError true to throw and error if object is not visible on screen, false if error is not to be thrown
     */
    boolean syncEnabled(WebDriver driver, int timeout, boolean returnError);
    
    /**     
     * Used in conjunction with WebObjectEnabled to determine if the desired element is disabled from the screen 
     * Will loop for the number of seconds listed in org.orasi.chameleon.CONSTANT.TIMEOUT
     * If object is not disabled within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     */
    boolean syncDisabled(WebDriver driver);
    
    /**
     * Used in conjunction with WebObjectDisabled to determine if the desired element is disabled from the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not disabled within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     * @param timeout Signify a timeout to be used (10 will wait for ten seconds)
     */
    //boolean syncDisabled(WebDriver driver, int timeout);
    
    /**
     * Used in conjunction with WebObjectDisabled to determine if the desired element is disabled  on the screen 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is still disabled within the time, handle the error based on the boolean
     * @author Justin
     * @param driver Main WebDriver
     * @param timeout Signify a timeout to be used (10 will wait for ten seconds)
     * @param returnError true to throw and error if object is not visible on screen, false if error is not to be thrown
     */
    boolean syncDisabled(WebDriver driver,int timeout, boolean returnError);
    
    /**
     * Used in conjunction with WebObjectTextPresentto determine if the desired text is displayed in the specified element 
     * Will loop for the number of seconds listed in org.orasi.chameleon.CONSTANT.TIMEOUT
     * If object is not disabled within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     * @param text Text to search for
     */
    boolean syncTextInElement(WebDriver driver, String text);  
    
    /**
     * Used in conjunction with WebObjectTextPresentto determine if the desired text is displayed in the specified element 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is not disabled within the time, throw an error
     * @author Justin
     * @param driver Main WebDriver
     * @param text Text to search for
     *  @param timeout Signify a timeout to be used (10 will wait for ten seconds)
     */
    //boolean syncTextInElement(WebDriver driver, String text, int timeout) ;
 
    /**
     * Used in conjunction with WebObjectTextPresentto determine if the desired text is displayed in the specified element 
     * Will loop for the amount of seconds passed in the variable TIMEOUT
     * If object is still disabled within the time, handle the error based on the boolean
     * @author Justin
     * @param driver Main WebDriver
     * @param text Text to search for
     * @param timeout Signify a timeout to be used (10 will wait for ten seconds)
     * @param returnError true to throw and error if object is not visible on screen, false if error is not to be thrown
     */
    boolean syncTextInElement(WebDriver driver, String text,int timeout, boolean returnError);
    

    
    /**
     * @author Justin
     * @return locator type of element that was used to create element using {@link FindBy}
     */
    By getElementLocator();
    
    
    /**
     * @author Justin
     * @return {@link By} locator value of element that was used to create element using {@link FindBy}
     */
    String getElementIdentifier();
    
    /**
     * @author Justin
     * @return locator identifier and the value of element that was used to create element using {@link FindBy}
     */
    String getElementLocatorInfo();
    
    /**
     * Used to highlight and element on a page
     * @author Waits
     * @param driver 
     * @return NA
     */
	public void highlight(WebDriver driver);
	
	public void mouseHover(WebDriver driver);
	
	public void coordinateClick(WebDriver driver);
	
	public void scrollIntoView(WebDriver driver);

	WebDriver getWrappedDriver();
	
	public <X> X getScreenshotAs(OutputType<X> arg0);

	ArrayList getAllAttributes(WebDriver driver);
}
