package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class NewCreditMemoForOrder_Page 
{	
		//Driver is being passed as an Argument so that Selenium is able to locate the element on the browser (driver).
	    //Element is returned, so that an Action can be performed on it.
		//Method is declared as Public Static, so that it can be called in any other method without instantiate the class.

		private static WebElement Element = null;
		
		public static WebElement InputBox_AdjustmentRefund(WebDriver driver)
		{
			//Used to locate the "Adjustment Refund" edit-box in the 'NewCreditMemoForOrder' Page
			Element = driver.findElement(By.id("adjustment_positive"));
			return Element;		
		}
		
		public static WebElement InputBox_AdjustmentFee(WebDriver driver)
		{
			//Used to locate the "Adjustment Refund" edit-box in the 'NewCreditMemoForOrder' Page
			Element = driver.findElement(By.id("adjustment_negative"));
			return Element;		
		}
		
		public static WebElement Button_RefundOnline(WebDriver driver)
		{
			//Used to locate the "Refund Online" button in the 'NewCreditMemoForOrder' Page
			Element = driver.findElement(By.xpath("//button[@title='Refund Offline']"));
			Reporter.log("Click on the Refund-Online button");
			return Element;		
		}
	
}
