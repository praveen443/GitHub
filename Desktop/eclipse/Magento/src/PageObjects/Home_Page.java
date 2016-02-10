package PageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

public class Home_Page 
{
	//Driver is being passed as an Argument so that Selenium is able to locate the element on the browser (driver).
    //Element is returned, so that an Action can be performed on it.
	//Method is declared as Public Static, so that it can be called in any other method without instantiate the class.
    
	private static WebElement Element = null;
	
	public static WebElement POPWindow(WebDriver driver)
	{
		//To locate the Incoming Message pop-up window.
		Element = driver.findElement(By.id("message-popup-window"));
		return Element;		
	}	
	
	public static WebElement Close_POPWindow(WebDriver driver)
	{
		//Close the Pop-Window
		Element = driver.findElement(By.xpath("html/body/div[1]/div[4]/div[1]/a/span"));
		return Element;		
	}	
	
	
	public static WebElement LogOutButton(WebDriver driver)
	{
		//Click on the logout text link in the homepage.
		Element = driver.findElement(By.className("link-logout"));
		Reporter.log("Click on the Log-Out text link.");
		return Element;
	}
	
	public static WebElement TextLink_SalesTab(WebDriver driver)
	{
		//Click on sales tab in the topmenu (Mouse hover operation)
		Actions action = new Actions(driver);
		Element = driver.findElement(By.xpath(".//*[@id='nav']/li[2]/a/span"));
		action.moveToElement(Element).build().perform();
		Reporter.log("Mouse-hover on the sales tab in the topmenu.");
		return Element;		
	}
	
	public static WebElement TextLink_Orders(WebDriver driver)
	{
		//Click on orderslink in the topmenu
		Element = driver.findElement(By.xpath(".//*[@id='nav']/li[2]/ul/li[1]/a/span"));
		Reporter.log("Click on the 'Orders' Link under the 'sales' tab in the homepage.");
		return Element;		
	}	
	
	public static WebElement Image_Magento(WebDriver driver)
	{
		//To locate the Image/Logo of "Magento" after the user login
		Element = driver.findElement(By.className("logo"));
		return Element;		
	}	
	
	public static WebElement Textlink_GiftWrapping(WebDriver driver)
	{
		//Click on the Gift Wrapping link under the sales tab in the topmenu
		Element = driver.findElement(By.partialLinkText("Gift Wrapping"));
		Reporter.log("Click on the Gift Wrapping link under the 'sales tab' in the topmenu");
		return Element;		
	}

}
