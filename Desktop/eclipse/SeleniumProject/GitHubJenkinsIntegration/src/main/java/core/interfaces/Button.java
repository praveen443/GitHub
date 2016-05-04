package core.interfaces;

import org.openqa.selenium.WebDriver;

import core.interfaces.impl.ButtonImpl;
import core.interfaces.impl.internal.ImplementedBy;


/**
 * Interface that wraps a WebElement in Button functionality. 
 */
@ImplementedBy(ButtonImpl.class)
public interface Button extends Element {
	public void click();
	public void jsClick(WebDriver driver);
	public void mouseClick() ;
}
