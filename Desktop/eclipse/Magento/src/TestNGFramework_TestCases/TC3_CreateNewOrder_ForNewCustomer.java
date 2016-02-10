//package TestNGFramework_TestCases;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import Actions.Application;
//import Actions.DriverObject;
//import Actions.Magento_ReusableFunctions;
//import Actions.SelectBrowser;
//import Actions.TakeSScreenshot;
//import PageObjects.Home_Page;
//import Utility.Constant;
//
//public class TC3_CreateNewOrder_ForNewCustomer extends DriverObject
//{
// 
//   //Calling this method to select the browser by passing the constant variable as an string argument. 
//   WebDriver driver = SelectBrowser.browserHandling(Constant.browser);
//
//   @BeforeTest
//   public void LaunchURL() throws Throwable 
//   {	
//	  Reporter.log("<------- Started the TestCase : TC3_CreateNewOrder_ForNewCustomer ------->");
//	  System.out.println("<------- Started the TestCase : TC3_CreateNewOrder_ForNewCustomer ------->");
//	  Reporter.log("Opened the "+ Constant.browser +" browser.");
//	  System.out.println("Opened the "+ Constant.browser +" browser.");
//	
//	  //Launch the Application URL
//	  //Application.launchURL(driver, Constant.AppURL);
//	 
//   }
//
//   @Test
//   public void CreateNewOrderForNewCustomer() 
//   {	
//	  //Login into the Magento Application
//      //Application.login(driver, Constant.Username, Constant.Password);
//   
//      //Handle the Incoming message Pop-Up window
//      Magento_ReusableFunctions.HandlePopUpWindow(driver);
//   
//      //Verify the user login
//      Magento_ReusableFunctions.Verify_UserLogin(driver);
//   
//      //***** Business Logic *****
//      
//      //Mouse-Hover on the Sales tab in the HomePage.
//	  Home_Page.TextLink_SalesTab(driver);
//	  
//      //Log-Out from the application
//      Application.logOut(driver);
//   
//      //Verify the User-LogOut
//      Magento_ReusableFunctions.Verify_UserLogOut(driver);    
//   }
//  
//   @AfterMethod
//   public void CloseTheBrowser(ITestResult result) throws Exception 
//   {
//	   //Takes the screenshot only if the test fails
//	   if(ITestResult.FAILURE == result.getStatus())
//	   {
//		  TakeSScreenshot.onTestFailure(result, driver);		 
//	   }
//	    
//	   //Close the browser
//	   DriverObject.closeBrowser(driver);
//	   Reporter.log("<-------- Ended the TestCase: TC3_CreateNewOrder_ForNewCustomer -------->");
//   }
// 
//}
