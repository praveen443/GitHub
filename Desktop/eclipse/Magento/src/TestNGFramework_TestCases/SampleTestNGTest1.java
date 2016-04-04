package TestNGFramework_TestCases;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Actions.Application;
import Actions.DriverObject;
import Actions.Magento_ReusableFunctions;


public class SampleTestNGTest1 extends DriverObject{

@Test
public void loginAndLogOut() throws Exception{				
	    System.out.println("<------- Started the TestCase : TC1_LoginAndLogOut ------->");		  	  
	    Reporter.log("<------- Started the TestCase : TC1_LoginAndLogOut ------->");	
	    
	    //Initialize the pageElements
		//Properties PageElements = super.pageFactory(driver);
	    
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
	     
	    //Log-Out from the application
	    Application.logOut(driver);
	     
	    //Verify the User-LogOut
	    Magento_ReusableFunctions.Verify_UserLogOut(driver);
	    
	}
				 	  
	@AfterMethod
    public void CloseBrowser(ITestResult result) throws Exception{
		
        //If the Testcase fail then only it enters to if condition block           
        //.getStatus will return Test "Pass" or "Fail"    
            
        if(ITestResult.FAILURE == result.getStatus())
    	{
        	//Take Screenshot on TestFailure.	
					
    	}
    		    
    	//Close the browser
    	DriverObject.closeBrowser();
    	Reporter.log("<-------- Ended the TestCase: TC1_LoginAndLogOut -------->"); 
    	System.out.println("<-------- Ended the TestCase: TC1_LoginAndLogOut -------->");         
    }	  
 
}


