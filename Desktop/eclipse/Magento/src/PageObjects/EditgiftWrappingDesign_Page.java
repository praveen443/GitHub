package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditgiftWrappingDesign_Page 
{
	//Driver is being passed as an Argument so that Selenium is able to locate the element on the browser (driver).
    //Element is returned, so that an Action can be performed on it.
	//Method is declared as Public Static, so that it can be called in any other method without instantiate the class.

	private static WebElement Element = null;
	
	public static WebElement Text_EditGiftWrapping(WebDriver driver)
	{
		//To locate the Edit Gift Wrapping  text 
		Element = driver.findElement(By.xpath("//h3[@class='icon-head head-adminhtml-giftwrapping']"));
		return Element;		
	}

}
