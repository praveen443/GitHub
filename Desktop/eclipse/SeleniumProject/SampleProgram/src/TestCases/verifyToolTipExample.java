package TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import Utils.TestReporter;

public class verifyToolTipExample {

  public WebDriver driver;
  public static final String appURL="http://www.seleniumhq.org/";
  public static final String actualToolTipText = "Return to Selenium home page";
  
  @BeforeMethod
  public void launchWebPage() {
	  TestReporter.log("Launch the Firefox browser.");
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  TestReporter.log("Launch the Application.");
	  driver.navigate().to(appURL);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void verifyToolTipText() {
	  //Here the tooltip text is captured by using selenium getAttribute() method.
	  WebElement eleToolTipText = driver.findElement(By.cssSelector("#header>h1 a"));
	  String getToolTipText = eleToolTipText.getAttribute("title");
	  TestReporter.log("Captured tooltip text is : " + getToolTipText);
	  
	  //verifying Captured tooltip text
	  TestReporter.log("Verifying the Captured tooltip text.");
	  Assert.assertEquals(getToolTipText, actualToolTipText);
  }
  
  @AfterMethod
  public void closeBrowser() {
	  if(driver!=null){
		  TestReporter.log("Closing the browser instance.");
		  driver.quit();
	  }
  }

}
