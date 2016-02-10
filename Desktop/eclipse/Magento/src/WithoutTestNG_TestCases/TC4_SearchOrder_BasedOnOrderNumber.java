/*package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import Actions.Magento_Login;
import Actions.Magento_ReusableFunctions;
import PageObjects.Home_Page;
import Utility.Constant;
import Utility.GenericFunctions;

public class TC4_SearchOrder_BasedOnOrderNumber 
{
	public static String ValidOrderNumber;	
	public static WebDriver driver;
	public static String PickRandomOrderNumber;

	public static void main(String[] args) throws InterruptedException 
	{			
		System.out.println("------------------ Started the TestCase: TC4_SearchOrder_BasedOnOrderNumber ------------------");			
		  
		//String ValidOrderNumber = "100000203";
		    
		//Creates a new instance of a webdriver
		FirefoxDriver driver = new FirefoxDriver();
		Reporter.log("Opened the firefox browser");	
			
		//Maximize the browser window
		driver.manage().window().maximize();
		Reporter.log("Maximized the browser window");			
			
		//Launch the Application URL
		driver.get(Constant.AppURL);
		Reporter.log("Application URL is launched successfully");
		    
		//Waits for 10 sec's until the webpage loads
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
	    //Login into the website by Passing the Constant Variables as arguments to Execute method
	//	Magento_Login.Execute(driver,Constant.Username,Constant.Password);
		Reporter.log("User Login is successfull.");
		System.out.println("User Login is successfull.");	
					
		//*Created Re-usable function to handle the Incoming Message pop-up window after the user login
		Magento_ReusableFunctions.PopUpWindow_IncomingMessage(driver);

		//Click on sales tab in the topmenu
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(".//*[@id='nav']/li[2]/a/span"));
		action.moveToElement(we).build().perform();
			
		//Click on Orders link under the sales tab in the topmenu
		Home_Page.TextLink_Orders(driver).click();
		System.out.println("Click on the 'Orders' Link under the 'sales' tab in the homepage.");
			
		String ValidOrderNumber = GenericFunctions.PickRandomOrderNumber();
			
		//Enter the OrderNumber# in the Order# edit box
		driver.findElement(By.xpath("//input[@id='sales_order_grid_filter_real_order_id']")).sendKeys(ValidOrderNumber);
		
		//System.out.println("OrderNo. entered in the Order# edit box is : "+ValidOrderNumber);
		Reporter.log("OrderNo. entered in the Order# edit box is : "+ValidOrderNumber);

		//Click on the Search button
		driver.findElement(By.xpath("//button[@title='Search']")).click();
			
		//Calling function to verify whether user is navigated to the orders page.
		Magento_ReusableFunctions.OrdersPage_Verification(driver);			
			
		//Calling method to verify Whetehr the searched OrderNumber matches with the resulted OrderNumber
		Magento_ReusableFunctions.VerifyingtheOrderNumber(driver, ValidOrderNumber);			
					
		//Click on LogOut text-link		
		Home_Page.TextLink_LogOut(driver).click();
		Reporter.log("Click on the LogOut text-link");
			
		//Verifying whether the user is logged-out successfully.
		Magento_ReusableFunctions.Verify_UserLogOut(driver);
			
		//Close the browser
		driver.close();
		Reporter.log("Closed the browser");			 	
		Reporter.log("----------------- End of the TestCase: TC4_SearchOrder_BasedOnOrderNumber ----------------- ");
			
	}
	
}

*/