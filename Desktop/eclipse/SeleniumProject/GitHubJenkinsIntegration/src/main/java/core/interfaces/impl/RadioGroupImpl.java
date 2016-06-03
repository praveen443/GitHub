package core.interfaces.impl;

//package com.disney.composite.core.interfaces.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.naming.directory.NoSuchAttributeException;

import org.apache.commons.collections.list.FixedSizeList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import core.interfaces.RadioGroup;
import utils.TestReporter;

/**
 * Wrapper around a WebElement for the Select class in Selenium.
 */
public class RadioGroupImpl extends ElementImpl implements RadioGroup {
    private Select innerSelect;
    private java.util.Date date= new java.util.Date();
	private List<WebElement> radioButtons = null;
	private int numberOfRadioButtons;
	private int currentIndex;
	//private Element radGroup;
	private List<String> stringOptions;
	private List<String> stringValues;
	private int numberOfOptions;
	private String selectedOption;
    
	/**
	 * Wraps a WebElement as an Element with radioGroup functionality.
	 * @param element - element to wrap up
	 * @throws NoSuchAttributeException
	 */
	public RadioGroupImpl(final WebElement element) {
		super(element);
		//radGroup = new ElementImpl(this.element);
		getElementLocator();
		this.radioButtons = element.findElements(By.tagName("input"));
		if(radioButtons.size() == 0){
			Field privateDriverField = null;
			WebDriver driver = null;
			try {
				privateDriverField = element.getClass().getDeclaredField("parent");
				privateDriverField.setAccessible(true);
				driver = (WebDriver) privateDriverField.get(element);
			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}
			radioButtons = driver.findElements(new ElementImpl(element).getElementLocator());
		}
		getNumberOfRadioButtons();
		getAllOptions();
		getAllValues();
		//TestReporter.assertNotEquals(radioButtons.size(), 0,"No radio buttons were found for the element [" + element+ "].");
		currentIndex = getCurrentIndex();
	}	

    
	/**
	 * @summary - Defines the number of radio buttons in the group by the 
	 * 		number of 'input' tags found in the wrapped object
	 */
	private void setNumberOfRadioButtons() {
		numberOfRadioButtons = radioButtons.size();
	}

	/**
	 * @summary - Defines the number of radio buttons and return the integer count
	 */
	@Override
	public int getNumberOfRadioButtons() {
		setNumberOfRadioButtons();
		return numberOfRadioButtons;
	}

	/**
	 * @summary - Sets the current index for this instance, selects the radio button 
	 * 		by index and sets the selected option for this instance
	 */
	@Override
	public void selectByIndex(int index) {
		currentIndex = index;
		try{
                    radioButtons.get(currentIndex).click();
                    if (!radioButtons.get(currentIndex).isSelected()) radioButtons.get(currentIndex).click();
		    }catch(RuntimeException rte){
			TestReporter.interfaceLog("Select option <b> [ " + currentIndex + " ] </b> from the radio group [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]", true);
		        throw rte;
		    }
		TestReporter.interfaceLog("Select option <b> [ " + currentIndex + " ] </b> from the radio group [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]", true);
		
		setSelectedOption();
	}

	/**
	 * @summary - Defines and returns a List<String> of all options for the radio group
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllOptions() {
		stringOptions = FixedSizeList.decorate(Arrays.asList(new String[radioButtons.size()]));
		int loopCounter = 0;

		for (WebElement option : radioButtons) {
			stringOptions.set(loopCounter, option.getAttribute("value"));
			loopCounter++;
		}

		return stringOptions;
	}
	
	/*
	 * @summary - Defines and returns a List<String> of all options for the radio group
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> getAllValues() {
		stringValues = FixedSizeList.decorate(Arrays.asList(new String[radioButtons.size()]));
		int loopCounter = 0;

		for (WebElement option : radioButtons) {
			stringValues.set(loopCounter, option.getText());
			loopCounter++;
		}

		return stringValues;
	}
	
	/**
	 * @summary - Defines a List<String> of all options for this instance as well as the 
	 * 		number of options for the radio group 
	 */
	
	private void setNumberOfOptions() {
		getAllOptions();
		numberOfOptions = stringOptions.size();
	}

	/**
	 * @summary - Defines the number of options and return the integer count
	 */
	@Override
	public int getNumberOfOptions() {
		setNumberOfOptions();
		return numberOfOptions;
	}

	/**
	 * @summary - Defines all options for this instance, selects an option by the string parameter 
	 * 		and sets the selected option for this instance
	 */
	@Override
	public void selectByOption(String option) {
		getAllOptions();
		for (int loopCounter = 0; loopCounter < stringOptions.size(); loopCounter++) {
			if (stringOptions.get(loopCounter).trim()
					.equalsIgnoreCase(option.trim())) {
				currentIndex = loopCounter;

				    try{
	                    radioButtons.get(currentIndex).click();
	                    if (!radioButtons.get(currentIndex).isSelected()) radioButtons.get(currentIndex).click();
				    }catch(RuntimeException rte){
				        TestReporter.interfaceLog("Select option <b> [ " + option + " ] </b> from the radio group [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]", true);
				        throw rte;
				    }
				    TestReporter.interfaceLog("Select option <b> [ " + option + " ] </b> from the radio group [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
				
				getSelectedOption();
				break;
			}
		}
		setSelectedOption();
	}
	

	/**
	 * @summary - Defines all options for this instance, selects an option by the string parameter 
	 * 		and sets the selected option for this instance
	 */
	public void selectByVisibleText(String text) {
		getAllValues();
		for (int loopCounter = 0; loopCounter < stringValues.size(); loopCounter++) {
			if (stringValues.get(loopCounter).trim()
					.equalsIgnoreCase(text.trim())) {
				currentIndex = loopCounter;

				    try{

	                    radioButtons.get(currentIndex).click();
	                    if (!radioButtons.get(currentIndex).isSelected()) radioButtons.get(currentIndex).click(); }catch(RuntimeException rte){
				        TestReporter.interfaceLog("Select option <b> [ " + text + " ] </b> from the radio group [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]", true);
				        throw rte;
				    }
				    TestReporter.interfaceLog("Select option <b> [ " + text + " ] </b> from the radio group [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
				
				getSelectedOption();
				break;
			}
		}
		setSelectedOption();
	}
	
	/**
	 * @summary - Defines a List<String> of all options for this instance and defines the currently 
	 * 		selected option, if any
	 */
	
	private void setSelectedOption() {
		getAllOptions();
		selectedOption = stringOptions.get(currentIndex).toString();
	}

	/**
	 * @summary - Defines a List<String> of all options for this instance and defines the currently 
	 * 		selected option, if any and returns the String value of the selected option
	 */
	@Override
	public String getSelectedOption() {
		setSelectedOption();
		return this.selectedOption;
	}

	/**
	 * @summary - Returns the integer index of the currently selected radio button
	 */
	@Override
	public int getSelectedIndex() {
		return currentIndex;
	}

	/**
	 * @summary - Loops through all radio buttons for one that possesses an attribute 
	 * 		that indicates the button is selected.
	 * NOTE: Within the method, the field "String[] attributes" is a string array for possible values 
	 * 		that could indicate radio button  is selected/checked. This array can be appended with new 
	 * 		attributes that indicate an option is selected/checked.
	 */
	private int getCurrentIndex() {
		String[] attributes = { "checked" };
		int loopCounter = 0;
		int attributeLoopCounter = 0;
		int index = -1;
		String checked = null;
		
		for (loopCounter = 0; loopCounter < numberOfRadioButtons; loopCounter++) {
			for (attributeLoopCounter = 0; attributeLoopCounter < attributes.length; attributeLoopCounter++) {
				if (!(radioButtons.get(loopCounter).getAttribute(attributes[attributeLoopCounter]) == null)) {
					checked = radioButtons.get(loopCounter).getAttribute(attributes[attributeLoopCounter]);
					if (checked.equalsIgnoreCase("true")) {
						index = loopCounter;
						break;

					}
				}
			}
			if(checked != null){
				break;
			}
		}
		return index;
	}
}