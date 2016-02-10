package Testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;


public class MultiBrowser 
{
  public static WebDriver driver; 
  
  public static String Url = "https://apigee.com/console/reddit";	
  
  @Parameters("browser")  
  @BeforeMethod  
  public void OpenBrowser(String browser) 
  {
	if(browser.equalsIgnoreCase("Firefox"))
	 {
		driver = new FirefoxDriver(); 
	 }
	 else if(browser.equalsIgnoreCase("Chrome"))
	 {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\praveen\\Documents\\Selenium_DriverSoftwares\\chromedriver.exe");
		driver = new ChromeDriver();
	 }	
	 else if(browser.equalsIgnoreCase("IE"))
	 {
		System.setProperty("webdriver.ie.driver", "C:\\Users\\praveen\\Documents\\Selenium_DriverSoftwares\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	 }
	    driver.manage().window().maximize();
  }
  
  @Test
  public void RedditAPI_Launch() throws InterruptedException 
  {
	  driver.get(Url);	
	  Thread.sleep(3000);
  }
  
  @AfterMethod
  public void CloseBrowser() throws Exception 
  {
	 
	  driver.quit();
  }
  
}
