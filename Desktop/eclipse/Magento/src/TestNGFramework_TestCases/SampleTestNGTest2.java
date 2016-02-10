package TestNGFramework_TestCases;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;
import Actions.TakeSScreenshot;

public class SampleTestNGTest2 extends DriverObject
{
	@Test
	public void addGiftWrapping() throws Exception
	{				
		  Reporter.log("<---------- Started the TestCase : TC12_AddGiftWrapping ---------->");
	      System.out.println("<---------- Started the TestCase : TC12_AddGiftWrapping ---------->");	
		    
		  /* //Initialize the pageElements
		  @SuppressWarnings("unused")
		  Properties PageElements = super.pageFactory(driver);*/
		    
	      //Open the browser
		  super.openBrowser();
			
		  //Launch the URL
		  Application.launchURL();
			
		  //Login into the Magento Application
		  Application.login();
		     
		  //Handle the Incoming message Pop-Up window
		  Magento_ReusableFunctions.HandlePopUpWindow(driver);
		   
		  //Verify the user login
		  Magento_ReusableFunctions.Verify_UserLogin(driver);
		   
		  //Calling this method to add the New gift wrapping 
		  Application.addgiftWrapping(driver); 
		   
		  //Log-Out from the application
		  Application.logOut(driver);
		     
		  //Verify the User-LogOut
		  Magento_ReusableFunctions.Verify_UserLogOut(driver);		    
   
	}
					 	  
	@AfterMethod
	public void CloseBrowser(ITestResult result) throws Exception
	{
	      //If the Testcase fail then only it enters to if condition block           
	      //.getStatus will return Test "Pass" or "Fail"    
	            
	      if(ITestResult.FAILURE == result.getStatus())
	      {
	         //Take Screenshot on TestFailure.	
			 TakeSScreenshot.onTestFailure(result);			
	      }
	    		    
	      //Close the browser
	      DriverObject.closeBrowser();
	      Reporter.log("<-------- Ended the TestCase: TC12_AddGiftWrapping -------->");
	      System.out.println("<-------- Ended the TestCase: TC12_AddGiftWrapping -------->");	     
	}	  
 
}

