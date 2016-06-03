package core.interfaces.impl;

//package com.disney.composite.core.interfaces.impl;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import core.WebDriverSetup;
import core.interfaces.Button;
import selenium.common.Constants;
import utils.Mouse;
import utils.TestReporter;

/**
 * Wraps a label on a html form with some behavior.
 */
public class ButtonImpl extends ElementImpl implements Button {
	private java.util.Date date= new java.util.Date();
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public ButtonImpl(WebElement element) {
        super(element);
    }
    
    @Override
    public void click() {

    	TestReporter.log("Click Button [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");

    	getWrappedElement().click();
    	//JavascriptExecutor jse = (JavascriptExecutor)WebDriverSetup.driver;
    	//jse.executeScript("arguments[0].click();", element );
 
    }
    @Override
    public void submit() {

    	TestReporter.log("Click Button [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");
    	try{
    		getWrappedDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        	getWrappedElement().submit();
        	        	
    	}catch(NoSuchElementException nsee){
    		getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
    		getWrappedElement().click();
    	}
    	getWrappedDriver().manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
    	//JavascriptExecutor jse = (JavascriptExecutor)WebDriverSetup.driver;
    	//jse.executeScript("arguments[0].click();", element );
 
    }
    
    @Override
    public void jsClick(WebDriver driver){

    	TestReporter.log("jsClick Button [ <b>@FindBy: " + getElementLocatorInfo()  + " </b>]");

    	JavascriptExecutor jse = (JavascriptExecutor)driver;
    	jse.executeScript("arguments[0].click();", element );
    }
    
    public void sendEnter() {
    	element.sendKeys("n");
    }
    
    public void mouseClick(){
    	Point xy = element.getLocation();
    //	new Mouse(WebDriverSetup.driver).click(xy.getX(), xy.getY());
    }
   

//    @Override
//    public String getFor() {
//        return getWrappedElement().getAttribute("for");
//    }
}
