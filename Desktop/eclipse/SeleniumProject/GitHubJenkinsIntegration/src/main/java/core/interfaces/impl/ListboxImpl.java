package core.interfaces.impl;

//package com.disney.composite.core.interfaces.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.common.Constants;
import utils.ExtendedExpectedConditions;
import utils.Sleeper;
import utils.TestReporter;
import core.WebDriverSetup;
import core.interfaces.Listbox;
import core.interfaces.exception.OptionNotInListboxException;

/**
 * Wrapper around a WebElement for the Select class in Selenium.
 */
public class ListboxImpl extends ElementImpl implements Listbox {
    private Select innerSelect;
    private java.util.Date date= new java.util.Date();
    /**
     * Wraps a WebElement with checkbox functionality.
     *
     * @param element to wrap up
     */
    public ListboxImpl(WebElement element) {
        super(element);
        try{
            this.innerSelect = new Select(element);
		}catch(WebDriverException e){
			By locator = new ElementImpl(element).getElementLocator();
			this.innerSelect = new Select(getWrappedDriver().findElement(locator));
		}
    }

  
    /**
     * Wraps Selenium's method.
     *
     * @param text visible text to select
     * @see org.openqa.selenium.support.ui.Select#selectByVisibleText(String)
     */
    @Override
    public void select(String text) {
    	getWrappedDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    	if (!text.isEmpty()){
        	try{
        		TestReporter.log(" Select option [ <b>" + text.toString() + "</b> ] from Listbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b>]<br />");
        		getElement(getWrappedDriver()).selectByVisibleText(text);  
        		getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        	}catch (NoSuchElementException e){
        		
        		String optionList = "";
        		List<WebElement> optionsList= innerSelect.getOptions();
        		for(WebElement option : optionsList){
        			optionList += option.getText() + " | ";
        		}
        		TestReporter.log(" The value of <b>[ " + text + "</b> ] was not found in Listbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b>]. Acceptable values are " + optionList +" ]<br />");
        		getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        		throw new OptionNotInListboxException("The value of [ " + text + " ] was not found in Listbox [  @FindBy: " + getElementLocatorInfo()  + " ]. Acceptable values are " + optionList , getWrappedDriver());
        	}       	
        }else{
        	TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]<br />");
        }

        
    }
    
    public void selectOptionTextContains(String value){
    	getWrappedDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    	String textToSelect = null;;
    	String optionList = "";
    	if(!value.isEmpty()){
    		List<WebElement> options = getWrappedElement().findElements(By.tagName("option"));
    		for(WebElement option : options){
    			optionList += option.getText() + " | ";
    			if(option.getText().contains(value.trim())){
    				textToSelect = option.getText();
    				break;
    			}
    		}
    		
    		if (textToSelect == null) throw new OptionNotInListboxException("The value of [ " + value + " ] was not found in Listbox [  @FindBy: " + getElementLocatorInfo()  + " ]. Acceptable values are " + optionList , getWrappedDriver());
    		TestReporter.assertNotNull(textToSelect, "No option was found that contains the text ["+value+"].");
    		getElement(getWrappedDriver()).selectByVisibleText(textToSelect);
    		getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
    	}else{
    		TestReporter.log(" Skipping input to Listbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]<br />");
    	}
    }

    /**
     * Wraps Selenium's method.
     *
     * @see org.openqa.selenium.support.ui.Select#deselectAll()
     */
    public void deselectAll() { 
    	getElement(getWrappedDriver()).deselectAll();
    }

    /**
     * Wraps Selenium's method.
     *
     * @return list of all options in the select.
     * @see org.openqa.selenium.support.ui.Select#getOptions()
     */
    public List<WebElement> getOptions() {
   		return getElement(getWrappedDriver()).getOptions();
    }

    /**
     * Wraps Selenium's method.
     *
     * @param text text to deselect by visible text
     * @see org.openqa.selenium.support.ui.Select#deselectByVisibleText(String)
     */
    public void deselectByVisibleText(String text) {
    	getElement(getWrappedDriver()).deselectByVisibleText(text);
    }


    /**
     * Wraps Selenium's method.
     *
     * @return WebElement of the first selected option.
     * @see org.openqa.selenium.support.ui.Select#getFirstSelectedOption()
     */
    public WebElement getFirstSelectedOption() {
        return getElement(getWrappedDriver()).getFirstSelectedOption();
    }
    
    /**
     * 
     * @see org.openqa.selenium.WebElement#isSelected()
     */
    public boolean isSelected() {
        return ((WebElement) innerSelect).isSelected();
    }

    public boolean syncNumberOfOptionsGreaterThan(WebDriver driver, int optionsCount){
    	boolean found = false;
		double loopTimeout = 0;
		By locator = new ElementImpl(element).getElementLocator();
		loopTimeout = WebDriverSetup.getDefaultTestTimeout() * 10;
		TestReporter.log("<i>Syncing for Listbox [ <b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to have <b>" +optionsCount + "</b> or more options within [ <b>" + WebDriverSetup.getDefaultTestTimeout()
				+ "</b> ] seconds.</i>");
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {
			
			if (listboxContainsOptionsGreaterThan(driver, getElement(getWrappedDriver()), optionsCount)) {
				found = true;
				break;
			}
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				innerSelect = new Select(driver.findElement(locator));
				Sleeper.sleep(100);

			} catch (Exception e) {
			}

		}

		if (!found) {
			dateAfter = new java.util.Date();
			TestReporter.log("<i>Listbox [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] does not have <b>" +optionsCount +"</b> after [ <b>"
					+ (dateAfter.getTime() - date.getTime()) / 1000.0 + "</b>] seconds.</i>");
			throw new RuntimeException(
					"Listbox [@FindBy: " + getElementLocatorInfo()
					+ " ] does not have " +optionsCount +" after [ "
							+ (dateAfter.getTime() - date.getTime()) / 1000.0 + " ] seconds.");
		}
		return found;
    	
    }
    
    public boolean syncNumberOfOptionsGreaterThan(WebDriver driver, int optionsCount, int timeout, boolean returnError){
    	boolean found = false;
		double loopTimeout = 0;
		By locator = new ElementImpl(element).getElementLocator();
		loopTimeout = timeout * 2;
		TestReporter.log("<i>Syncing for Listbox [ <b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to have <b>" +optionsCount + "</b> or more options within [ <b>" + WebDriverSetup.getDefaultTestTimeout()
				+ "</b> ] seconds.</i>");
		for (double seconds = 0; seconds < loopTimeout; seconds++) {
			
			if (listboxContainsOptionsGreaterThan(driver, getElement(getWrappedDriver()), optionsCount)) {
				found = true;
				break;
			}
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				innerSelect = new Select(driver.findElement(locator));
				Sleeper.sleep(500);

			} catch (Exception e) {
			}

		}

		if (!found) {
			dateAfter = new java.util.Date();
			TestReporter.log("<i>Listbox [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] does not have <b>" +optionsCount +"</b> after [ <b>"
					+ (dateAfter.getTime() - date.getTime()) / 1000.0 + "</b>] seconds.</i>");
			if(returnError)
			throw new RuntimeException(
					"Listbox [@FindBy: " + getElementLocatorInfo()
					+ " ] does not have " +optionsCount +" after [ "
							+ (dateAfter.getTime() - date.getTime()) / 1000.0 + " ] seconds.");
		}
		return found;
    	
    }
    
    public boolean syncNumberOfOptionsLessThan(WebDriver driver, int optionsCount){
    	boolean found = false;
		double loopTimeout = 0;
		By locator = new ElementImpl(element).getElementLocator();
		loopTimeout = WebDriverSetup.getDefaultTestTimeout() * 10;
		TestReporter.log("<i> Syncing to element [ <b>@FindBy: " + getElementLocatorInfo()
				+ "</b> ] to have <b>" +optionsCount + "</b> or less options in[ <b>" + WebDriverSetup.getDefaultTestTimeout()
				+ "</b>] seconds.</i>");
		for (double seconds = 0; seconds < loopTimeout; seconds += 1) {
			
			if (listboxContainsOptionsLessThan(driver, getElement(getWrappedDriver()), optionsCount)) {
				found = true;
				break;
			}
			innerSelect = new Select(driver.findElement(locator));
			try {
				Sleeper.sleep(100);

			} catch (Exception e) {
			}

		}

		if (!found) {
			dateAfter = new java.util.Date();
			TestReporter.log("<i>Listbox [<b>@FindBy: " + getElementLocatorInfo()
					+ " </b>] does not have <b>" +optionsCount +"</b> after [ <b>"
					+ (dateAfter.getTime() - date.getTime()) / 1000.0 + "</b>] seconds.</i>");
			throw new RuntimeException(
					"Listbox [@FindBy: " + getElementLocatorInfo()
					+ " ] does not have " +optionsCount +" after [ "
							+ (dateAfter.getTime() - date.getTime()) / 1000.0 + " ] seconds.");
		}
		return found;
    	
    }
    

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean listboxContainsOptionsLessThan(WebDriver driver, Select element, int options) {
		Wait wait = new WebDriverWait(driver, 0);	
		try {
			return wait.until(ExtendedExpectedConditions.numberOfListboxOptionsToBeLessThan(element, options)) != null;
		} catch (NoSuchElementException | ClassCastException | StaleElementReferenceException | TimeoutException e) {
			return false;
		}
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean listboxContainsOptionsGreaterThan(WebDriver driver, Select element, int options) {
		Wait wait = new WebDriverWait(driver, 0);	
		try {
			return wait.until(ExtendedExpectedConditions.numberOfListboxOptionsToBeGreaterThan(element, options)) != null;
		} catch (NoSuchElementException | ClassCastException | StaleElementReferenceException | TimeoutException e) {
			return false;
		}
	}
	
	private Select getElement(WebDriver driver){
		try{
			innerSelect.isMultiple();
		}catch(WebDriverException e){
			By locator = new ElementImpl(element).getElementLocator();
			innerSelect = new Select(driver.findElement(locator));
		}
		
		return innerSelect;
		
	}
}