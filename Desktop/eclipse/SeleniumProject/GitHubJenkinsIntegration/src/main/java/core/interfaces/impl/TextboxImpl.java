package core.interfaces.impl;

//package com.disney.composite.core.interfaces.impl;

import java.sql.Timestamp;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import core.WebDriverSetup;
import core.interfaces.Element;
import core.interfaces.Textbox;
import utils.Base64Coder;
import utils.TestReporter;

/**
 * TextInput  wrapper.
 */
public class TextboxImpl extends ElementImpl implements Textbox {
    private WebElement element;
    private java.util.Date date= new java.util.Date();
    private java.util.Date dateAfter= new java.util.Date();
	/**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public TextboxImpl(WebElement element) {
    	super(element);
    }

    @Override
    public void clear() {
    	TestReporter.log(" Clear text from Textbox [<b>@FindBy: " + getElementLocatorInfo()  + " </b>]");
    	getWrappedElement().clear();
    }

    @Override
    public void set(String text) {
    	
        if (text == null) text = "";
        if (!text.isEmpty()){
        //	JavascriptExecutor executor = (JavascriptExecutor)WebDriverSetup.driver; 
        //	executor.executeScript("arguments[0].scrollIntoView(true);arguments[0].click();", element);
        	if (text.equalsIgnoreCase("<blank>") || text.equalsIgnoreCase("(blank)")){
        		TestReporter.log(" Request to blank sent. Clearing Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
        		getWrappedElement().clear();
        	}else{
	        	TestReporter.log(" Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
	        	getWrappedElement().clear();
	        	getWrappedElement().sendKeys(text);
        	}
        }else{
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
        }
    }
    
    public void set(WebDriver driver, String text) {
        if (text == null) text = "";
        if (!text.isEmpty()){
        	TestReporter.log(" Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
            JavascriptExecutor executor = (JavascriptExecutor)driver; 
            executor.executeScript("arguments[0].scrollIntoView(true);arguments[0].click();", element);
            getWrappedElement().clear();
        	getWrappedElement().sendKeys(text);   
        }else{
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
        }
    }

    public void safeSetCreditCard(String text) {
        if (text == null) text = "";
        if (!text.isEmpty()){
        	TestReporter.log(" Send Keys [ <b>" + "************" + text.toString().substring(text.length()-4, text.length()) + "</b> ] to Textbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
        	getWrappedElement().click();        	
        	getWrappedElement().sendKeys(Keys.CONTROL + "a");
        	getWrappedElement().sendKeys(text);
        	getWrappedElement().sendKeys(Keys.TAB);
        }else{
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	}
    }
    
    public void safeSet(String text){
        if (text == null) text = "";
    	if (!text.isEmpty()){
    		if (text.equalsIgnoreCase("<blank>") || text.equalsIgnoreCase("(blank)")){
        		TestReporter.log(" Request to blank sent. Clearing Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
        		getWrappedElement().clear();
        	}else{
        		TestReporter.log(" Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
            	getWrappedElement().click();        	
            	getWrappedElement().sendKeys(Keys.CONTROL + "a");
            	getWrappedElement().sendKeys(text);
            	getWrappedElement().sendKeys(Keys.TAB);
        	}
        	
        }else{
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	}
    }
    
    public void safeSetClear(String text){
        if (text == null) text = "";
    	TestReporter.log(" Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	getWrappedElement().click();        	
    	getWrappedElement().sendKeys(Keys.CONTROL + "a");
    	//Determine if a blank amount is to be entered. If not, set the amount
    	if(text.isEmpty()){
    		getWrappedElement().sendKeys(Keys.DELETE);
    	}else{
        	getWrappedElement().sendKeys(text);
    	}
    	getWrappedElement().sendKeys(Keys.TAB);
    }
    

    public void setSecure(String text) {
        if (text == null) text = "";
        if (!text.isEmpty()){
        	TestReporter.log(" Send encoded text [ <b>" + text.toString() + "</b> ] to Textbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
        	//getWrappedElement().click();        	        	
        	getWrappedElement().sendKeys(Base64Coder.decodeString(text).toString());
        }else{
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	}
    }
    
    public void safeSetSecure(String text) {
        if (text == null) text = "";
        if (!text.isEmpty()){
        	TestReporter.log(" Send encoded text [ <b>" + text.toString() + "</b> ] to Textbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
        	//getWrappedElement().click();
        	getWrappedElement().click();        	
        	getWrappedElement().sendKeys(Keys.CONTROL + "a");
        	getWrappedElement().sendKeys(Base64Coder.decodeString(text).toString());
        	getWrappedElement().sendKeys(Keys.TAB);
        }else{
        	dateAfter= new java.util.Date();
        	TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	}
    }
    
    public void setValidate( WebDriver driver, String text){
        if (text == null) text = "";
    	if(!text.isEmpty()){
    		TestReporter.log(" Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    		Element obj = new ElementImpl(getWrappedElement());
    		obj.syncEnabled(driver);
    		getWrappedElement().clear();
    		getWrappedElement().sendKeys(text);
    		obj.syncTextInElement(driver, text, 3, true);
    		dateAfter= new java.util.Date();
    		TestReporter.log(" VALIDATED [ <b>" + text.toString() + "</b> ] was entered in the textbox."); 
    	}else{
    		dateAfter= new java.util.Date();
    		TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	}
    }
    
    public void safeSetValidate(WebDriver driver, String text){
        if (text == null) text = "";
    	if(!text.isEmpty()){
    		TestReporter.log(" Send Keys [ <b>" + text.toString() + "</b> ] to Textbox [  <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    		Element obj = new ElementImpl(getWrappedElement());
    		obj.syncEnabled(driver);
    		getWrappedElement().click();        	
        	getWrappedElement().sendKeys(Keys.CONTROL + "a");
        	getWrappedElement().sendKeys(text);
        	getWrappedElement().sendKeys(Keys.TAB);
    		obj.syncTextInElement(driver, text, 3, true);
    		dateAfter= new java.util.Date();
    		TestReporter.log(" VALIDATED [ <b>" + text.toString() + "</b> ] was entered in the textbox."); 
    	}else{
    		dateAfter= new java.util.Date();
    		TestReporter.log(" Skipping input to Textbox [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	}
    }
    /**
     * Gets the value of an input field.
     * @return String with the value of the field.
     */
    @Override
    public String getText() {
        return getWrappedElement().getAttribute("value");
    }
}