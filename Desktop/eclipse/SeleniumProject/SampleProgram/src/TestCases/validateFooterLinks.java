package TestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import Utils.TestReporter;


public class validateFooterLinks {
  public WebDriver driver;
  
  //WebElement eleFooterLinks = driver.findElement(By.cssSelector("div[id$='block-28']>div>div>div>ul>li a"));  
  public String appURL = "https://www.seleniumeasy.com/";
  
  @BeforeTest
  public void launchWebPage() {
	  //TestReporter.log("Launch the Firefox browser.");
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  @Test
  public void verifyFooterLinks() {
	  //TestReporter.log("Launch the Application.");
	  driver.navigate().to(appURL);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
	  
	  //WebElement eleFooterLinks = driver.findElement(By.cssSelector("div[id$='block-28']>div>div>div>ul>li a"));
	  
	  List<WebElement> eleFooterLinks = driver.findElements(By.cssSelector("div[id$='block-28']>div>div>div>ul>li a"));
	  @SuppressWarnings("unused")
	  int footerlinks = eleFooterLinks.size();
	  
	  for (int i=0;i<=eleFooterLinks.size();i++){
		   System.out.println(eleFooterLinks.get(i).getText());
	  }
	  
  }
  
  @AfterTest
  public void closeBrowser() {
	  if(driver!=null){
		 // TestReporter.log("Closing the browser instance.");
		  driver.quit();
	  }
  }
  
}
