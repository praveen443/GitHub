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
//import Actions.TakeSScreenshot;
//import Utility.Constant;
//
//public class TC4_SearchOrder_basedOnOrderNumber extends Actions.Application{
//	
//  //Calling this method to select the browser by passing the constant variable as an string argument. 
//  WebDriver driver = Actions.SelectBrowser.browserHandling(Constant.browser);
//	 
//  @BeforeTest
//  public void LaunchBrowser_URL() throws Exception 
//  {		
//	  Reporter.log("<------ Started the TestCase: TC4_SearchOrder_BasedOnOrderNumber ------> ");
//	  System.out.println("<------ Started the TestCase: TC4_SearchOrder_BasedOnOrderNumber ------>");
//	  System.out.println("Opened the "+ Constant.browser +" browser.");
//	  Reporter.log("Opened the "+ Constant.browser +" browser.");
//
//	  //Launch the Application URL
//      //Application.launchURL(driver, Constant.AppURL);	   
//  }
//	 
//  @Test
//  public void TC4_SearchAndVerifyOrderNumber() throws Exception 
//  {	 			  
//	  //Login into the Magento Application
//	  //Application.login(driver, Constant.Username, Constant.Password);
//		     
//	  //Handle the Incoming message Pop-Up window
//	  Magento_ReusableFunctions.HandlePopUpWindow(driver);
//		     
//	  //Verify the user login
//	  Magento_ReusableFunctions.Verify_UserLogin(driver);
//		
//	  //Calling this method to Search the Order based on the Order number
//	  Application.searchOrder_basedOnOrderNumber(driver);
//		     
//	  //Log-Out from the application
//	  Application.logOut(driver);
//		     
//	  //Verify the User-LogOut
//	  Magento_ReusableFunctions.Verify_UserLogOut(driver);   
//  }
//  
//  @AfterMethod
//  public void CloseTheBrowser(ITestResult result) throws Exception 
//  {
//  	   //Takes the screenshot only if the test fails
//  	   if(ITestResult.FAILURE == result.getStatus())
//	   {
//  		  TakeSScreenshot.onTestFailure(result, driver);		 
//	   }
//	    
//	   //Close the browser
//	   DriverObject.closeBrowser(driver);
//	   Reporter.log("<-------- Ended the TestCase: TC4_SearchOrder_basedOnOrderNumber -------->");
//  }
//
//}
//
//
