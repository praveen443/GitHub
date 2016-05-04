package core.interfaces;

import core.interfaces.impl.ListboxImpl;
import core.interfaces.impl.internal.ImplementedBy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Interface for a select element.
 */ 
@ImplementedBy(ListboxImpl.class)
public interface Listbox extends Element {


    /**
     * Wraps Selenium's method.
     *
     * @param value the value to select.
     * @see org.openqa.selenium.support.ui.Select#selectByVisibleText(String)
     */
    void select(String value);


    /**
     * Wraps Selenium's method.
     *
     * @see org.openqa.selenium.support.ui.Select#deselectAll()
     */
    void deselectAll();


    /**
     * Wraps Selenium's method.
     *
     * @param text text to deselect by visible text
     * @see org.openqa.selenium.support.ui.Select#deselectByVisibleText(String)
     */
    void deselectByVisibleText(String text);
    
    WebElement getFirstSelectedOption();

    List<WebElement> getOptions();
    
    /**
     * @author Justin
     * @return {@link boolean} TRUE if element is currently select 
     * @see org.openqa.selenium.WebElement#isSelected()
     */
    boolean isSelected();

    void selectOptionTextContains(String value);
    boolean syncNumberOfOptionsGreaterThan(WebDriver driver, int optionsCount);
    boolean syncNumberOfOptionsGreaterThan(WebDriver driver, int optionsCount, int timeout, boolean returnError);
  //  boolean syncNumberOfOptionsLessThan(WebDriver driver, int optionsCount);
}
