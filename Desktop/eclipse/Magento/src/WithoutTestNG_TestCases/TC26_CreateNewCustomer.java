/*package WithoutTestNG_TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import Actions.Application;
import Actions.Magento_ReusableFunctions;
import PageObjects.Properties;
import Utility.Constant;
import Utility.GenericFunctions;

public class TC26_CreateNewCustomer {
	
 public static WebElement Element;
 
  public static void main(String[] args) throws InterruptedException {
   
	  WebDriver driver = new FirefoxDriver();

	  //Calling Objects From POM
	  PageFactory.initElements(driver, Properties.class);
	  Properties PageElements = new Properties(driver);
	   
	  //Launch the Application URL
	  //Application.launchURL(driver, Constant.AppURL);
	
	  //Login into the Magento Application
	  //Application.login(driver, Constant.Username, Constant.Password);
	     
	  //Handle the Incoming message Pop-Up window
	  Magento_ReusableFunctions.HandlePopUpWindow(driver);
	     
	  //Verify the user login
	  Magento_ReusableFunctions.Verify_UserLogin(driver);
	  
	  //Mouse-hover on the 'Customers' Tab
	  Actions action = new Actions(driver);
	  Element   = driver.findElement(By.partialLinkText("Customers"));
	  action.moveToElement(Element).build().perform();
	  Reporter.log("Mouse-hover on the Customers tab in the topmenu.");
	  
	  //Click on 'Manage Customers' link.
	  PageElements.TextLink_ManageCustomers.click();
	  System.out.println("Click on 'Manage Customers' link.");
	  
	  //Calling this method to verify the Manage Customers Page navigation.
	  Magento_ReusableFunctions.Verify_ManageCustomerspageNavigation(driver);
	  
	  //Click on 'Add New Customer' button
	  PageElements.Button_AddNewCustomer.click();
	  System.out.println("Click on 'Add New Customer' button");
	  
	  //Calling this method to verify the New Customer Page navigation.
	  Magento_ReusableFunctions.Verify_NewCustomerPageNavigation(driver);
	  
	  System.out.println("**** Fill the Account details ****");
	  // ****** To fill the Account Information details  ******
	  //Select the option from the 'Associate to Website *' dropdown list
	  Magento_ReusableFunctions.selectAssociateValue(driver);
	  	   
	  //Select the option from the 'Group' dropdown list 
	  Magento_ReusableFunctions.SelectGroupValue(driver);
	  
      //Enter the FirstName
	  PageElements.InputBox_AccountFirstName.sendKeys(Constant.FirstName);
      System.out.println("Enter the First Name : "+Constant.FirstName);	  
	  
	  //Enter the LastName
	  PageElements.InputBox_AccountLastName.sendKeys(GenericFunctions.string_LastNameGeneration());
  
	  //Enter the E-mail Id
	  PageElements.InputBox_AccountEmail.sendKeys(GenericFunctions.string_EmailID());
	
	  //Enter the Password
	  PageElements.InputBox_AccountPassword.sendKeys(GenericFunctions.string_RandomPassword());
	
	  //Click on Save Customer button
	  PageElements.Button_SaveCustomer.click();
	  
	  //Verify the message after creating the new customer
	  if(PageElements.ValidTextMessage.isDisplayed()){
		  String getValidTextMessage = PageElements.ValidTextMessage.getText();
		  System.out.println("Output Message is : "+getValidTextMessage); 		  
	  }
	  else{
		  System.out.println("<-----  Failed to create the New Customer. ----->");
	  }
   
	  //Log-Out from the application
	  Application.logOut(driver);
	     
	  //Verify the User-LogOut
	  Magento_ReusableFunctions.Verify_UserLogOut(driver);    
	 
	  //Close the browser
	  driver.close();	
	  
    }
  
}


*/