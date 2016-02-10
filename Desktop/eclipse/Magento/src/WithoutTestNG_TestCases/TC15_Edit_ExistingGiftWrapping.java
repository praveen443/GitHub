package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import Actions.Magento_ReusableFunctions;
import PageObjects.Home_Page;
import PageObjects.ManageGiftWrapping_Page;
import PageObjects.NewGiftWrapping_Page;
import Utility.Constant;
import Utility.GenericFunctions;

public class TC15_Edit_ExistingGiftWrapping 
{

	//public static Object string;

	public static void main(String[] args) throws InterruptedException 
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
		
        //Login into the Magento Application by Passing the Constant Variables as arguments to Execute method
		//Magento_Login.Execute(driver,Constant.Username,Constant.Password);		
		
		//Implicitly waits for 5 sec's until the webpage loads
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Calling method to verify whether the user login is successfull.
		//Magento_ReusableFunctions.Verify_UserLogin(driver);
		
		//Calling function to handle the Incoming Message pop-up window after the user login
		Magento_ReusableFunctions.HandlePopUpWindow(driver);
		
		//Mouse-Hover on the Sales tab in the HomePage.
	    Home_Page.TextLink_SalesTab(driver);
	  
	    //Click on the Gift Wrapping link under the sales tab in the topmenu
	    Home_Page.Textlink_GiftWrapping(driver).click();	    	    
	   
	    //Calling method to verify the Manage Gift Wrapping Page Navigation.
	    Magento_ReusableFunctions.Verify_ManageGiftWrappingPageNavigation(driver);
	    
	    //Click on any random 'edit' link from the webtable
	    ManageGiftWrapping_Page.Randomtextlink_Edit(driver).click();
	    
	    //Calling method to verify whether the user is navigated to the 'Edit gift wrapping Design' Page.
	    Magento_ReusableFunctions.Verify_EditgiftWrappingPageNavigation(driver);
	    
	    //Clear the text in the 'Gift Wrapping Design'  edit-box
	    NewGiftWrapping_Page.InputBox_GiftWrappingDesign(driver).clear();	   
	    
	    //Assigned the variable to store the random String in the Gift Wrapping design edit-box
	    String RandomString_GiftWrappingDesign = GenericFunctions.string_GiftWrappingDesign();
	    
	    //Enter the random String in the Gift Wrapping design edit-box
	    NewGiftWrapping_Page.InputBox_GiftWrappingDesign(driver).sendKeys(RandomString_GiftWrappingDesign);
	    Reporter.log("Entered the design name as : "+RandomString_GiftWrappingDesign);	    
	    
	    //Click on the Save button
        NewGiftWrapping_Page.Button_Save(driver).click();        
        
        //Calling this method to Verify the valid text message after saving the Gift Wrapping details   
        //Magento_ReusableFunctions.Verify_GiftWrappingTextMessage(driver);
        Reporter.log("The gift wrapping design is created with the name  : "+RandomString_GiftWrappingDesign);
       
        //Click on LogOut text-link after Sign-in	
        //Home_Page.TextLink_LogOut(driver).click();
      	Reporter.log("Click on the Log-Out text link.");
      		
      	//Calling method to verify whether the user log-out.
      	Magento_ReusableFunctions.Verify_UserLogOut(driver);
      	
      	//Close the browser
      	driver.close();
      	Reporter.log("Closed the browser");
	}

}

