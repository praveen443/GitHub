/*package WithoutTestNG_TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;

import Actions.Magento_Login;
import Actions.Magento_ReusableFunctions;
import PageObjects.Home_Page;
import PageObjects.NewCreditMemoForOrder_Page;
import PageObjects.OrdersPage;
import Utility.Constant;
import Utility.GenericFunctions;

public class TC11_CreateCreditMemos_ForCompletedOrders 
{
	public static WebDriver driver;
	
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
		
        //Launch the Magento app and login into the website by Passing the Constant Variables as arguments to Execute method
		//Magento_Login.Execute(driver,Constant.Username,Constant.Password);
		Reporter.log("Login into the Magento Application.");
		 
		//Implicitly waits for 5 sec's until the webpage loads
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
		//*Created Re-usable function to handle the Incoming Message pop-up window after the user login
		Magento_ReusableFunctions.PopUpWindow_IncomingMessage(driver);
		
		//Mouse-Hover on the Sales tab in the HomePage.
	    Home_Page.TextLink_SalesTab(driver);
	    Reporter.log("Click on sales tab in the topmenu.");
		
		//Click on Orders link under the sales tab in the topmenu
		Home_Page.TextLink_Orders(driver).click();
		Reporter.log("Click on the 'Orders' Link under the 'sales' tab in the homepage.");
		
		//Coding part
		Thread.sleep(7000);
        
        //Click on the Credit Memo button.
        OrdersPage.Button_CreditMemo(driver).click();
        
        //Assigned the variable to store the random 2-digit integer number.
        String AdjustmentRefundValue = GenericFunctions.Random_2digitInteger();       
        
        //Enter the Random value in the "Adjustment Refund" edit-box
        NewCreditMemoForOrder_Page.InputBox_AdjustmentRefund(driver).sendKeys(AdjustmentRefundValue);
        
        //Assigned the variable to store the random 2-digit integer number.
        //String AdjustmentFee = GenericFunctions.Generate_2digitRandomInt_InSpecificRange();
        
        //Enter the Random value in "Adjustment Fee" edit-box
        NewCreditMemoForOrder_Page.InputBox_AdjustmentFee(driver).sendKeys(AdjustmentRefundValue);
        
        //Click on the Refund-Online button
        NewCreditMemoForOrder_Page.Button_RefundOnline(driver).click();      
             
        //Calling method to Verify the valid message after clicking on the Re-fund online button
        Magento_ReusableFunctions.Verify_CreditMemoOutputMesage(driver);
        	
		//Click on LogOut text-link after Sign-in	
		Home_Page.TextLink_LogOut(driver).click();
		Reporter.log("Click on the Log-Out text link.");
	
		//Close the browser
		driver.close();
		Reporter.log("Closed the browser");
	}

}
*/