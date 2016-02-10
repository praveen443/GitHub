package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import Actions.DriverObject;
import PageObjects.Home_Page;
import Utility.Constant;

public class sample extends DriverObject {

		public static void main(String[] args) 
		{
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
					
			//Calling function to handle the Incoming Message pop-up window after the user login
			//Magento_ReusableFunctions.PopUpWindow_IncomingMessage(driver);
			
			//Mouse-Hover on the Sales tab in the HomePage.
		    Home_Page.TextLink_SalesTab(driver);
		    Reporter.log("Click on sales tab in the topmenu.");
		  
		    //Click on the Gift Wrapping link under the sales tab in the topmenu
		   //Home_Page.Textlink_GiftWrapping(driver).click();
		   //Gift Wrapping
		   
		    //Reporter.log("Click on the Gift Wrapping link under the 'sales tab' in the topmenu");	    
		   
		    //Calling method to verify the Manage Gift Wrapping Page Navigation.
		    //Magento_ReusableFunctions.Verify_ManageGiftWrappingPageNavigation(driver);
		    
		   /* //Click on the "Add Gift Wrapping" button available in the Manage Gift Wrapping Page.
		   // ManageGiftWrapping_Page.Button_AddGiftWrapping(driver).click();
		    Reporter.log("Click on the Manage Gift Wrapping Page.");
		    
		    //Assigned the variable to store the random String in the Gift Wrapping design edit-box
		    String RandomString_GiftWrappingDesign = GenericFunctions.string_GiftWrappingDesign();
		    
		    //Enter the random String in the Gift Wrapping design edit-box
		    NewGiftWrapping_Page.InputBox_GiftWrappingDesign(driver).sendKeys(RandomString_GiftWrappingDesign);	   
		   
		    //Calling method to select the random value from the WebType list in the Manage Gift Wrapping Page
		    Magento_ReusableFunctions.selectWebSiteType(driver);
		 
		    //Calling method to select the random value from the status dropdown list.
		    Magento_ReusableFunctions.selectStatusFrom_Dropdownlist(driver);	  
		  
		    //Assigned variable to enter the 2 digit random number in the Price field
	        String PriceValue = GenericFunctions.Random_PriceValue();       
	             
	        //Enter the value in the Price field
	        NewGiftWrapping_Page.InputBox_Price(driver).sendKeys(PriceValue);
	        
	        //Click on the Save button
	        NewGiftWrapping_Page.Button_Save(driver).click();
	        
	        //Calling this method to Verify the valid text message after saving the Gift Wrapping details   
	        //Magento_ReusableFunctions.Verify_GiftWrappingTextMessage(driver);
	        
	        //Click on LogOut text-link after Sign-in	
	        //Home_Page.TextLink_LogOut(driver).click();
	      	Reporter.log("Click on the Log-Out text link.");
	      		
	      	//Calling method to verify whether the user log-out.
	      	Magento_ReusableFunctions.Verify_UserLogOut(driver);
	      	
	      	//Close the browser
	      	driver.close();
	      	Reporter.log("Closed the browser");*/
	      		
		}
}
