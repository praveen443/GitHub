package TestNGFramework_TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Actions.SelectBrowser;
import Actions.TakeSScreenshot;
import Utility.Constant;

public class TC30_ValidateCustomerResetFilter 
{
 
	//Calling this method to select the browser by passing the constant variable as an string argument. 
	WebDriver driver = SelectBrowser.browserHandling(Constant.browser);

	@BeforeTest
	public void LaunchURL() throws Throwable 
	{	
		Reporter.log("<----- Started the TestCase : TC30_ValidateCustomerResetFilter ----->");
		System.out.println("<----- Started the TestCase : TC30_ValidateCustomerResetFilter ----->");
		Reporter.log("Opened the "+ Constant.browser +" browser.");
		System.out.println("Opened the "+ Constant.browser +" browser.");
		
		//Launch the Application URL
	    Application.launchURL();		 
	}
		  
	@Test
	public void ValidateCustomerResetFilter() throws InterruptedException 
	{	
		 //Login into the Magento Application
		 //Application.login(driver, Constant.Username, Constant.Password);
		     
		 //Handle the Incoming message Pop-Up window
		 Magento_ReusableFunctions.HandlePopUpWindow(driver);
		     
		 //Verify the user login
		 Magento_ReusableFunctions.Verify_UserLogin(driver);
		   
		 //Calling method to validate the 'Customer reset filter'
		 Application.validateTheCustomerResetFilter(driver);
		   
		 //Log-Out from the application
		 Application.logOut(driver);
		     
		 //Verify the User-LogOut
		 Magento_ReusableFunctions.Verify_UserLogOut(driver);    
   }
		    
	@AfterMethod
	public void CloseTheBrowser(ITestResult result) throws Exception 
	{
		 //Takes the screenshot only if the test fails
		 if(ITestResult.FAILURE == result.getStatus())
		 {
			TakeSScreenshot.onTestFailure(result);		 
		 }
			    
		 //Close the browser
		 DriverObject.closeBrowser();
		 Reporter.log("<------ Ended the TestCase: TC30_ValidateCustomerResetFilter ------>");
	}	
		
 }

