package core.interfaces;

//package com.disney.composite.core.interfaces;

import org.openqa.selenium.WebDriver;

import core.interfaces.impl.CheckboxImpl;
import core.interfaces.impl.internal.ImplementedBy;


/**
 * Interface that wraps a WebElement in CheckBox functionality.
 */
@ImplementedBy(CheckboxImpl.class)
public interface Checkbox extends Element {

    /**
     * Toggle the state of the checkbox.
     */
    void toggle();
    void jsToggle(WebDriver driver);

    /**
     * Checks checkbox if unchecked.
     */
    void check();

    /**
     * Un-checks checkbox if checked.
     */
    void uncheck();

    /**
     * Check if an element is selected, and return boolean.
     *
     * @return true if check is checked, return false in other case
     */
    boolean isChecked();
    
    void checkValidate(WebDriver driver);
    void uncheckValidate(WebDriver driver);
}