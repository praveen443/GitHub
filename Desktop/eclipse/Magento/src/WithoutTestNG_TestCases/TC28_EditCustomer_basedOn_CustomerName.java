/*package WithoutTestNG_TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Utility.Constant;

public class TC28_EditCustomer_basedOn_CustomerName 
{

  public static WebElement Element;

  public static void main(String[] args) throws InterruptedException 
  {
	
	//Open the firefox browser.	
	WebDriver driver = new FirefoxDriver();

	//Maximize the nrowser window
	DriverObject.maximizeBrowserWindow(driver);
		   
	//Launch the Application URL
	Application.launchURL(driver, Constant.AppURL);
		
	//Login into the Magento Application
	//Application.login(driver, Constant.Username, Constant.Password);
		     
	//Handle the Incoming message Pop-Up window
	Magento_ReusableFunctions.HandlePopUpWindow(driver);
		     
	//Verify the user login
	Magento_ReusableFunctions.Verify_UserLogin(driver);
	
	//Calling method to edit the customer based on the customer name.
	Application.editCustomerbasedOnCustomerName(driver);
		  
	//Log-Out from the Application.
	Application.logOut(driver);
		  
	//Verify the User Log-Out.
	Magento_ReusableFunctions.Verify_UserLogOut(driver);
		  
	//Close the browser.
    DriverObject.closeBrowser(driver);
		  
  }
	
}
*/