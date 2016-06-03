package core.interfaces.impl;

//package com.disney.composite.core.interfaces.impl;

import java.sql.Timestamp;

import core.WebDriverSetup;
import core.interfaces.Link;
import utils.Mouse;
import utils.TestReporter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * Wraps a label on a html form with some behavior.
 */
public class LinkImpl extends ElementImpl implements Link {
	private java.util.Date date= new java.util.Date();
    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public LinkImpl(WebElement element) {
        super(element);
    }
    
    @Override
    public void jsClick(WebDriver driver) {
    	TestReporter.log(" Click Link [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	JavascriptExecutor executor = (JavascriptExecutor)driver;
    	//executor.executeScript("arguments[0].click();", element);
    	executor.executeScript("if( document.createEvent ) {var click_ev = document.createEvent('MouseEvents'); click_ev.initEvent('click', true , true )"
				+ ";arguments[0].dispatchEvent(click_ev);} else { arguments[0].click();}", element);
    	
    }
    
    @Override
    public void click() {
    	TestReporter.log(" Click Link [ <b>@FindBy: " + getElementLocatorInfo()  + " </b> ]");
    	getWrappedElement().click();
    }
    
    public void mouseClick(){
    	Point xy = element.getLocation();
    	//new Mouse(WebDriverSetup.driver).click(xy.getX(), xy.getY())
    }
}