package TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class verifyToolTipExample {

  public WebDriver driver;
  public static final String appURL="https://www.seleniumhq.org/";
  
  @BeforeMethod
  public void launchWebPage() {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
	  driver.navigate().to(appURL);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void verifyToolTipText() {
	  
  }
  
  @AfterMethod
  public void closeBrowser() {
	  driver.quit();
  }

}
