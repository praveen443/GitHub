/*package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import Actions.Magento_ReusableFunctions;
import PageObjects.Home_Page;
import PageObjects.ManageGiftWrapping_Page;
import PageObjects.OrdersPage;
import Utility.Constant;

public class TC13_ChangetheStatusOf_GiftWrapping 
{

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
		
        //Login into the Magento Application by Passing the Constant Variables as arguments to Execute method
		//Magento_Login.Execute(driver,Constant.Username,Constant.Password);		
		
		//Implicitly waits for 5 sec's until the webpage loads
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Calling method to verify whether the user login is successfull.
		//Magento_ReusableFunctions.Verify_UserLogin(driver);
		
		//Calling function to handle the Incoming Message pop-up window after the user login
		Magento_ReusableFunctions.PopUpWindow_IncomingMessage(driver);
		
		//Mouse-Hover on the Sales tab in the HomePage.
	    Home_Page.TextLink_SalesTab(driver);
	  
	    //Click on the Gift Wrapping link under the sales tab in the topmenu
	    Home_Page.Textlink_GiftWrapping(driver).click();	    	    
	   
	    //Calling method to verify the Manage Gift Wrapping Page Navigation.
	    Magento_ReusableFunctions.Verify_ManageGiftWrappingPageNavigation(driver);
	    
	    //Select the option from the Status dropdown list
	    Magento_ReusableFunctions.selectStatusFromDropdownlist1(driver);
	    
	    //Click on search button available in the page.
	    OrdersPage.Button_Search(driver).click();
	    
	    //Click on "Select All" link available in the page.
	    ManageGiftWrapping_Page.TextLink_SelectAll(driver).click();
	    
	    //Select the Option 'Change status' in the "Actions" dropdown.
	    Magento_ReusableFunctions.selectOptionFromActionsDropdownlist(driver);
	    
	    //Verifying whether the status dropdown list is displayed after selecting the Change Status option.
	    Magento_ReusableFunctions.Verify_StatusDropdownIdentification(driver);
	    
	    //Select Disabled/Enabled status from the dropdown.
	    Magento_ReusableFunctions.selectStatusFromDropdownlist2(driver);
	    
	    //Click on Submit button available in the Manage Gift Wrapping page.
	    ManageGiftWrapping_Page.Button_Submit(driver).click();	    
	   
	    //Verifying the total no. of records that have been updated.   
	    Magento_ReusableFunctions.Verify_TotalNumOfUpdatedRecords(driver);
	    
	    //Click on LogOut text-link after Sign-in	
        Home_Page.TextLink_LogOut(driver).click();
      		
      	//Calling method to verify whether the user log-out.
      	Magento_ReusableFunctions.Verify_UserLogOut(driver);
      	
      	//Close the browser
      	driver.close();
      	Reporter.log("Closed the browser");
	}

}



*/