package core.interfaces.impl;

//package com.disney.composite.core.interfaces.impl;

import java.sql.Timestamp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import core.WebDriverSetup;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import utils.TestReporter;

/**
 * Wrapper class like Select that wraps basic checkbox functionality.
 */ 
public class CheckboxImpl extends ElementImpl implements Checkbox {
	private java.util.Date date= new java.util.Date();
	private java.util.Date dateAfter= new java.util.Date();
    /**
     * Wraps a WebElement with checkbox functionality.
     *
     * @param element to wrap up
     */
    public CheckboxImpl(WebElement element) {
        super(element);
    }

    public void toggle() {
        getWrappedElement().click();
    	//JavascriptExecutor executor = (JavascriptExecutor)WebDriverSetup.driver;
    	//executor.executeScript("arguments[0].click();", element);

    }

    public void jsToggle(WebDriver driver) {
        //getWrappedElement().click();
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	executor.executeScript("arguments[0].click();", element);
    }

    public void check() {
        if (!isChecked()) {
            TestReporter.log(" Checking the Checkbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            TestReporter.log(" Unchecking the Checkbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");
            toggle();
        }
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }
    
    public void checkValidate(WebDriver driver){
    	Element obj = new ElementImpl(getWrappedElement());
    	obj.syncEnabled(driver);
        if (!isChecked()) {
            TestReporter.log(" Checking the Checkbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");
            toggle();
        }
        
        if (!isChecked()) {
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Checkbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>] was not checked successfully.");
            throw new RuntimeException("Checkbox [ @FindBy: " + getElementLocatorInfo()  + " ] was not checked successfully.");           
        }else{
        	dateAfter= new java.util.Date();
        	Reporter.log(new Timestamp(dateAfter.getTime()) + " :: VALIDATED the Checkbox was <b> CHECKED </b> successfully."); 
        }
    }   
    

    public void uncheckValidate(WebDriver driver){
    	Element obj = new ElementImpl(getWrappedElement());
    	obj.syncEnabled(driver);
        if (isChecked()) {
            TestReporter.log(" Checking the Checkbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");
            toggle();
        }
        
        if (isChecked()) {
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Checkbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>] was not checked successfully.");
            throw new RuntimeException("Checkbox [ @FindBy: " + getElementLocatorInfo()  + " ] was not checked successfully.");
           
        }else{
        	dateAfter= new java.util.Date();
        	TestReporter.log(" VALIDATED the Checkbox was <b> CHECKED </b> successfully."); 
        }
    }   
}
