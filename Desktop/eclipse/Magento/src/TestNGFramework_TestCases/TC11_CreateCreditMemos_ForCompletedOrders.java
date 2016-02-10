/*package TestNGFramework_TestCases;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Actions.SelectBrowser;
import Actions.TakeSScreenshot;
import PageObjects.Home_Page;
import PageObjects.NewCreditMemoForOrder_Page;
import PageObjects.OrdersPage;
import Utility.Constant;
import Utility.GenericFunctions;

public class TC11_CreateCreditMemos_ForCompletedOrders extends Actions.Application
{
   //Calling this method to select the browser by passing the constant variable as an string argument. 
   WebDriver driver = SelectBrowser.browserHandling(Constant.browser);

   @BeforeTest
   public void LaunchURL() throws Throwable 
   {	
		Reporter.log("<------- Started the TestCase : TC11_CreateCreditMemos_ForCompletedOrders ------->");
		System.out.println("<------- Started the TestCase : TC11_CreateCreditMemos_ForCompletedOrders ------->");
		Reporter.log("Opened the "+ Constant.browser +" browser.");
		System.out.println("Opened the "+ Constant.browser +" browser.");

		//Launch the Application URL
		//Application.launchURL(driver, Constant.AppURL);		 
   }
 
   @Test
   public void CreateCreditMemosForCompletedOrders() throws InterruptedException 
   {			 
	    //Login into the Magento Application
	   // Application.login(driver, Constant.Username, Constant.Password);
	     
	    //Handle the Incoming message Pop-Up window
	    Magento_ReusableFunctions.HandlePopUpWindow(driver);
	     
	    //Verify the user login
	    Magento_ReusableFunctions.Verify_UserLogin(driver);  
	   
	   //Mouse-Hover on the Sales tab in the HomePage.
	    Home_Page.TextLink_SalesTab(driver);
	  
	    //Click on Orders link under the sales tab in the topmenu
	    Home_Page.TextLink_Orders(driver).click();
			
	    //Coding part
	    Thread.sleep(7000);
	        
	    //Click on the Credit Memo button.
	    OrdersPage.Button_CreditMemo(driver).click();
	        
	    //Assigned the variable to store the random 2-digit integer number.
	    String AdjustmentRefundValue = GenericFunctions.Random_2digitInteger();       
	        
	    //Enter the Random value in the "Adjustment Refund" edit-box
	    NewCreditMemoForOrder_Page.InputBox_AdjustmentRefund(driver).sendKeys(AdjustmentRefundValue);
	    Reporter.log("Enterted the value in 'Adjustment Refund' edit-box is : "+AdjustmentRefundValue);
	        
	    //Assigned the variable to store the random 2-digit integer number.
	    //String AdjustmentFee = GenericFunctions.Generate_2digitRandomInt_InSpecificRange();
	        
	    //Enter the Random value in "Adjustment Fee" edit-box
	    NewCreditMemoForOrder_Page.InputBox_AdjustmentFee(driver).sendKeys(AdjustmentRefundValue);
	        
	    //Click on the Refund-Online button
	    NewCreditMemoForOrder_Page.Button_RefundOnline(driver).click();      
	             
	    //Calling method to Verify the valid message after clicking on the Re-fund online button
	    Magento_ReusableFunctions.Verify_CreditMemoOutputMesage(driver);
	        	
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
  	       TakeSScreenshot.onTestFailure(result, driver);		 
	    }
	   
	    //Close the browser
	    DriverObject.closeBrowser(driver);
	    Reporter.log("<-------- Ended the TestCase: TC11_CreateCreditMemos_ForCompletedOrders -------->");
   }
  
}
*/