package Testcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class Selenium_GRID2  extends Assert
{	
	public static String Verification;
	WebDriver driver;
	String base_URL,node_URL;
	
  @BeforeMethod
  public void setUp() throws MalformedURLException
  {   
	  base_URL = "https://accounts.google.com";
      node_URL = "http://192.168.8.233:4444/wd/hub"; 
      DesiredCapabilities capability =  DesiredCapabilities.firefox();
      capability.setBrowserName("firefox");
      capability.setPlatform(Platform.VISTA);
      driver = new RemoteWebDriver(new URL(node_URL), capability);
  }
  
  @Test
  public void sampleTest () throws InterruptedException
  {
	   driver.get(base_URL);
	   driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	   Thread.sleep(3000);
	 
   /* // Verification
	   Assert.assertEquals("Welcome: Mercury Tours", driver.getTitle());*/	 	
  }
  
  @AfterMethod
  public void afterMethod()
  { 
    // Close the browser
	   driver.quit();
  }

}
