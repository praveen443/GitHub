// ********************************************************************************************************************
//  PURPOSE     : Created this method to call the browserType to be launched for all the TestCases. 
//  AUTHOR      : Praveen Varma.
//  Created Date: 14-08-2015.
// ********************************************************************************************************************

package Actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import Utility.Constant;

public class SelectBrowser 
{	
	
	public static WebDriver browserHandling(String browser) 
	{        		   
		   WebDriver driver = null;
		   
		   //Check if parameter passed from TestNG is 'firefox'				 
		   if(browser.equalsIgnoreCase("IE")){		   
			   System.setProperty("webdriver.ie.driver",Constant.IEDriverPath);		 
			   driver = new InternetExplorerDriver();
			   //System.out.println("Opened the "+ browser +" browser.");
		  	         
		    }else if(browser.equalsIgnoreCase("chrome")){		        		          
		    	System.setProperty("webdriver.chrome.driver",Constant.chromeDriverPath);
		        driver = new ChromeDriver(); 		          
		        //System.out.println("Opened the "+ Constant.browser +" browser.");
		           
		    }else if(browser.equalsIgnoreCase("firefox")){		         	         
		    	driver = new FirefoxDriver();
		        //System.out.println("Opened the "+ browser +" browser.");		        
		   }
		   
		return driver;		     		  
	  }
	
}

	 
		 
	
	


		
		
		
	

