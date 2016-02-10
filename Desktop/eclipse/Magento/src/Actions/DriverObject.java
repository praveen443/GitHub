package Actions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import PageObjects.Properties;
import Utility.Constant;

public class DriverObject 
{
	
   public static WebElement Element = null;
   public static WebDriver driver;

   //<---- To instantiate the driver object  ---->
   public static  WebDriver openBrowser() 
   {        		   			   
	   //Check if parameter passed from TestNG is 'firefox'				 
	   if(Constant.browser.equalsIgnoreCase("IE")){		   
		   System.setProperty("webdriver.ie.driver",Constant.IEDriverPath);		 
		   driver = new InternetExplorerDriver();
		   System.out.println("Opened the "+ Constant.browser +" browser.");
			  	         
		}else if(Constant.browser.equalsIgnoreCase("chrome")){		        		          
			System.setProperty("webdriver.chrome.driver",Constant.chromeDriverPath);
			driver = new ChromeDriver(); 		          
			System.out.println("Opened the "+ Constant.browser +" browser.");
			           
	    }else if(Constant.browser.equalsIgnoreCase("firefox")){		         	         
			driver = new FirefoxDriver();
			System.out.println("Opened the "+ Constant.browser +" browser.");		        
	    }
			   
		return driver;
	}
	
   //<---- To Close the browesr ---->
   public static void closeBrowser() throws InterruptedException
   {
	   driver.close();
	   Thread.sleep(3000);
	   
	   Reporter.log("Closed the browser");
	   System.out.println("Closed the browser");
   }
   

   //<---- Created this method to launch the Application (Ref: All the TestCases) ---->
   public static void launchURL()    
   {			  
 	  try{
 		    driver.manage().window().maximize();
 		    Reporter.log("Maximize the browser window");
 			System.out.println("Maximize the browser window");
 		   
 			//Launch the Application URL
 		    driver.get(Constant.AppURL);
 		         
 		 } catch (Exception e){
 			     
 		   System.out.println("<----- Found exception while launching the URL----> "+e.getMessage());
         }
 	       
 	       Reporter.log("Launch the Application URL.");	
 	       System.out.println("Launch the Application URL.");
 	     
 	       //Waits for 10 sec's until the webpage loads
 	       DriverObject.waitUntilPageLoads(driver, 10);					   
 	}
    
    //<---- Created this method to Perform the login (Ref: All the TestCases) ---->
    public static void login() 
 	{	
 		try {
 			   //Calling Objects From POM
 			   PageFactory.initElements(driver, Properties.class);
 			   Properties PageElements = new Properties(driver);
 			   
 			   //Set Username
 			   PageElements.Username.sendKeys(Constant.Username);
 			   System.out.println("Enter the Username as : "+Constant.Username);
 			   Reporter.log("Enter the Username as : "+Constant.Username);
 			   
 			   //Set Password
 			   PageElements.Password.sendKeys(Constant.Password);
 			   System.out.println("Enter the Password as : "+Constant.Password);
 			   Reporter.log("Enter the Password as : "+Constant.Password);
 			   
 			   //Click on Login button
 			   PageElements.LoginButton.click();
 			   Reporter.log("Click on Login button.");
 			   System.out.println("Click on Login button.");
 			
 		} catch (Exception e) {
 			
 			  System.out.println("<----- Found exception while login into the Application ----> "+e.getMessage());
 			
 		}
 		   
 	}
    
 
    //<---- Created this method to Log-Out from the Application (Ref: All the TestCases) ---->
    public static void logOut(WebDriver driver)    
  	{	
  	     //Calling Objects From POM
  		 PageFactory.initElements(driver, Properties.class);
  		 Properties PageElements = new Properties(driver);
  					  			
  		 //Click on Login button
  		 PageElements.TextLink_LogOut.click();
  		 Reporter.log("Click on Log-Out text link.");
  		 System.out.println("Click on Log-Out text link.");
  	}
       
      	
    //<----- To maximize the browser window ----->
    public static void maximizeBrowserWindow(WebDriver driver)
    {
	     driver.manage().window().maximize();
	     Reporter.log("Maximize the browser window");
    }
   
    //<----- To Apply the implicit until the browser loads the page -----> 
    public static void waitUntilPageLoads(WebDriver driver,int key)
    {	  
	     //Waits for 10 sec's until the webpage loads
	     driver.manage().timeouts().implicitlyWait(key, TimeUnit.SECONDS);
    }
   
    //<----- To identify the WebElement using xpath ----->
    public static WebElement getElementByXpath(WebDriver driver,String key)
    {	  
	     //Waits for 10 sec's until the webpage loads
	     driver.findElement(By.xpath(key));
	     return Element;
    }
   
    //<-----  To identify the WebElement using ID ----->
    public static WebElement getElementByID(WebDriver driver,String key)
    {	  
	     //Waits for 10 sec's until the webpage loads
	     driver.findElement(By.id(key));
	     return Element;
    }
   
    //<----- To perform the Mouse hover operation using Actions class ----->
    public static WebElement mouseHoverAction(WebDriver driver,String key)
    {
         Actions action = new Actions(driver);
	     Element   = driver.findElement(By.xpath(key));
	     action.moveToElement(Element).build().perform();
	     Reporter.log("Mouse-hover on the sales tab in the topmenu.");
	     return Element;		
    }
      
    //<----- To identify the WebElement using className ----->
    public static WebElement getElementByClasName(WebDriver driver,String key)
    {
	     driver.findElement(By.className(key));  	
	     return Element;	
    }
   
    //<----- To identify the WebElement using partialLinkText ----->
    public static WebElement getElementBypartialLinkText(WebDriver driver,String key)
    {
	     driver.findElement(By.partialLinkText(key));	
  	     return Element;	
    }
   
    public static WebElement getElementsByXpath(WebDriver driver,String key)
    {
         driver.findElements(By.xpath(key));
         return Element;
    }
   
    public static Properties pageFactory(WebDriver driver)
    {
	    //Calling Objects From POM
	    PageFactory.initElements(driver, Properties.class);
	    Properties PageElements = new Properties(driver);	   
	    return PageElements;
    }
   
    //<----- To handle the Pop-Up Alert Messages ----->
    //Check if alert exists
    public static void checkAlert(WebDriver driver)
    {
    
	  try {
  	    	
  	         WebDriverWait wait = new WebDriverWait(driver, 3);
  	         wait.until(ExpectedConditions.alertIsPresent());
  	         System.out.println("<-- Alert Message is displayed. -->");
  	         Reporter.log("<-- Alert Message is displayed. -->");
  	        
  	         Alert alert = driver.switchTo().alert();
  	        
  	         //get the alert text message
  	         String getAlertMessage = alert.getText();
  	         System.out.println("Alert text message is : "+getAlertMessage);
  	         Reporter.log("Alert text message is : "+getAlertMessage); 
  	       
  	         //Clik on 'OK' button (or) Close the alert message by Accept it.
  	         alert.accept();
  	         System.out.println("Click on 'OK'.");
  	         Reporter.log("Click on 'OK'.");
  	        
  	      } catch (Exception e) { 	    	
  	         System.out.println(e.getStackTrace());
  	     }
  	}

}
