/*package WithoutTestNG_TestCases;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import Actions.Magento_Login;
import Actions.Magento_ReusableFunctions;
import PageObjects.Home_Page;
import Utility.Constant;

public class TC1_Magento_LoginAndLogOut 
{
	public static WebDriver driver;
	
   public static void main(String[] args) throws Exception 
   {				 
	
     try {   
    	
    	    //Creates a new instance of a webdriver
    	    FirefoxDriver driver = new FirefoxDriver();	
            Reporter.log("Opened the firefox browser.");
    	    
    	    //Maximize the browser window
    	    driver.manage().window().maximize();
    	    Reporter.log("Maximized the browser window.");
    	    
		    //Launch the Application URL
		    driver.get(Constant.AppURL);
		    Reporter.log("Application URL is launched successfully");
		    
		    //Waits for 15 sec's until the webpage loads
		    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
	        //Launch the Magento app and login into the website by Passing the Constant Variables as arguments to Execute method
			//Magento_Login.Execute(driver,Constant.Username,Constant.Password);
			Reporter.log("Login into the Magento Application.");
			 
			//Implicitly waits for 5 sec's until the webpage loads
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					
			//*Created Re-usable function to handle the Incoming Message pop-up window after the user login
			Magento_ReusableFunctions.PopUpWindow_IncomingMessage(driver);
			
			//Click on LogOut text-link after Sign-in	
			Home_Page.TextLink_LogOut(driver).click();
			Reporter.log("Click on the Log-Out text link.");
		
			//Close the browser
			driver.close();
			Reporter.log("Closed the browser");
			
       } catch (Exception e) 
            {
		       e.printStackTrace();
		       
    	       //Calling method to take the screenshot for the failed testcase
		       
            }
 
    }
		
}
	

*/