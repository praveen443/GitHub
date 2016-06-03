package core.interfaces;

//package com.disney.composite.core.interfaces;

import org.openqa.selenium.WebDriver;

import core.interfaces.impl.LinkImpl;
import core.interfaces.impl.internal.ImplementedBy;


/**
 * Interface that wraps a WebElement in Button functionality. 
 */
@ImplementedBy(LinkImpl.class)
public interface Link extends Element {
    
 
    public void click();
    public void jsClick(WebDriver driver);
    public void mouseClick();
}
