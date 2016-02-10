package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

/*import Actions.Magento_Login;*/
import Actions.Magento_ReusableFunctions;
import PageObjects.Home_Page;
import PageObjects.ManageGiftWrapping_Page;
import PageObjects.OrdersPage;
import Utility.Constant;

public class TC14_DeleteGiftWrapping {

	public static void main(String[] args) throws InterruptedException {
		
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
	    
	    //Select Disabled/Enabled status from the dropdown.
	    Magento_ReusableFunctions.selectStatusFromDropdownlist1(driver);
    
	    //Click on search button available in the page.
	    OrdersPage.Button_Search(driver).click();
	    
	    //Click on "Select All" link available in the page.
	    ManageGiftWrapping_Page.TextLink_SelectAll(driver).click();
	   
	    //Select the option 'delete' from the Actions dropdown list.
	    Magento_ReusableFunctions.selectOptionDeleteFromActionsDropdown(driver, "Delete", "giftwrappingGrid_massaction-select");
	    
	    //Click on Submit button available in the Manage Gift Wrapping page.		  						    
	  	ManageGiftWrapping_Page.Button_Submit(driver).click();
	  	System.out.println("Click on the submit button");
	    
	   /* //Verify and count the no. of records displayed in the table.
	    if (driver.findElement(By.xpath("//td[@class='empty-text a-center'][text()='No records found.'] ")).isDisplayed()) {
			Reporter.log("Output message is : No records found. ");
			System.out.println("Output message is : No records found.");
			
		} else {
		
		//Wait until the pop-up message is displayed
		Thread.sleep(7000);
		
		//WebDriver offers the users with a very efficient way to handle these pop ups using Alert interface.
		Alert alert = driver.switchTo().alert();
		System.out.println("Pop-Up window is displayed after clicking on the submit button.");
		
		//The accept() method clicks on the “Ok” button as soon as the pop up window appears.
		alert.accept();
		System.out.println("Click on 'Ok' button in the pop-up ");
		     
		//To get the total no.of records that have been deleted.
		String getTotalNumOfDeletedRecords = driver.findElement(By.xpath("//li[@class='success-msg']")).getText();
		System.out.println("OutPut Message is : "+getTotalNumOfDeletedRecords);
		     		   
	 }*/
	    	    
	  

	}

}
