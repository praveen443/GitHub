package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class ManageGiftWrapping_Page 
{

	//Driver is being passed as an Argument so that Selenium is able to locate the element on the browser (driver).
    //Element is returned, so that an Action can be performed on it.
	//Method is declared as Public Static, so that it can be called in any other method without instantiate the class.

	private static WebElement Element = null;
	
	public static WebElement ManageGiftWrappingText(WebDriver driver){
		
		//Used to locate the 'ManageGiftWrapping' text in the Manage Gift Wrapping Page.
		Element = driver.findElement(By.xpath("//h3[text()='Manage Gift Wrapping']"));
		return Element;		
	}
	
	public static WebElement Button_AddGiftWrapping(WebDriver driver){
		
		//Used to locate the "Add Gift Wrapping" button available in the Manage Gift Wrapping Page.
		Element =  driver.findElement(By.xpath("//button[@title='Add Gift Wrapping']"));
		Reporter.log("Click on the 'Add Gift Wrapping' button.");
		return Element;		
	}
	
	public static WebElement TextLink_SelectAll(WebDriver driver){
		
		//Click on "Select All" link available in the page.
		Element =  driver.findElement(By.partialLinkText("Select All"));
		Reporter.log("Click on 'Select All' link available in the page.");
		System.out.println("Click on 'Select All' link available in the page.");
		return Element;		
	}
	
	public static WebElement Button_Submit(WebDriver driver){
		
		//Click on Submit button available in the page.
		Element =  driver.findElement(By.xpath("//button[@title='Submit']"));
		Reporter.log("Click on Submit button in the Manage Gift Wrapping page.");
		System.out.println("Click on submit button");
		return Element;		
	}
	
	public static WebElement dropdown_Actions(WebDriver driver){
		
		//Creating the object for the Actions dropdown list.  
		Element =  driver.findElement(By.id("giftwrappingGrid_massaction-select"));
		return Element;		
	}
	
	public static WebElement dropdown2_Status(WebDriver driver){
		
		//Creating the object for the Second Status dropdown list.    
		Element =  driver.findElement(By.xpath("//select[@id='visibility'][@name='status']"));
		return Element;		
	}
	
	public static WebElement dropdown1_Status(WebDriver driver){
		
		//Creating the object for the first Status dropdown list.  
		Element =  driver.findElement(By.id("giftwrappingGrid_filter_status"));
		return Element;		
	}
	
	public static WebElement Randomtextlink_Edit(WebDriver driver){
		
		///Click on any random 'edit' link from the webtable  
		Element =  driver.findElement(By.xpath(".//*[@id='giftwrappingGrid_table']/tbody/tr[1]"));
		Reporter.log("Click on any random 'edit' link from the webtable");
		return Element;		
	}
	
	public static WebElement getValidText(WebDriver driver) { //(Ref: TC14)
		
		//Get the valid text message after deleting the records. 
		Element =  driver.findElement(By.xpath("//li[@class='success-msg']"));		
		return Element;		
	}
	
	public static WebElement getEntireWebTable(WebDriver driver) {  //(Ref: TC14)
		
		//Get the entire table
		Element =  driver.findElement(By.xpath(".//*[@id='giftwrappingGrid_table']/tbody"));		
		return Element;		
	}
}
