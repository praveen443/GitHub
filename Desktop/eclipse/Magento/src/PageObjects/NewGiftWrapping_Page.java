package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewGiftWrapping_Page
{

	//Driver is being passed as an Argument so that Selenium is able to locate the element on the browser (driver).
    //Element is returned, so that an Action can be performed on it.
	//Method is declared as Public Static, so that it can be called in any other method without instantiate the class.

	private static WebElement Element = null;
	public static String RandomString_GiftWrappingDesign;
	
	public static WebElement InputBox_GiftWrappingDesign(WebDriver driver)
	{
		//Used to locate the Gift Wrapping design edit-box in the New Gift Wrapping Page.  (Ref -TC12)
		Element = driver.findElement(By.id("design"));
		return Element;		
	}
	
	public static WebElement SelectDropdownValue_Status(WebDriver driver)
	{
		//Used to locate the Status DropdownList in the New Gift Wrapping Page. (Ref -TC12)
		Element = driver.findElement(By.id("status"));
		return Element;		
	}
	
	public static WebElement SelectDropdownValue_Websites(WebDriver driver)
	{
		//Used to locate the Element Websites List in the New Gift Wrapping Page. (Ref -TC12)
		Element = driver.findElement(By.id("website_ids"));
		return Element;		
	}
	
	public static WebElement InputBox_Price(WebDriver driver)
	{
		//Used to locate the Element Websites List in the New Gift Wrapping Page.
		Element = driver.findElement(By.id("base_price"));
		return Element;		
	}
	
	public static WebElement Button_Save(WebDriver driver)
	{
		//Used to locate the Element Websites List in the New Gift Wrapping Page.
		Element = driver.findElement(By.xpath("//button[@title='Save']"));
		return Element;		
	}
	
}
