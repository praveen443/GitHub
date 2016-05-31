package TestCases;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import Utility.TestReporter;

public class verifyToolTipExample {

  public WebDriver driver;
  public String seleniumURL = "http://docs.seleniumhq.org";
  public String jQueryURL = "https://jqueryui.com/tooltip/";
  public String strToolTipURL = "https://www.seleniumeasy.com/selenium-tutorials/click-element-using-javascriptexecutor";
  public String actualToolTipText1 = "Return to Selenium home page";
  public String actualToolTipText2 = "We ask for your age only for statistical purposes.";
  public String actualToolTipText3 = "‹ Working with Multiple Checkboxes with Safecheck";
  
  @BeforeMethod(groups={"TestNG Tests"})
  public void launchWebPage() {
	  TestReporter.log("<-------- Started the TestCase execution - verifyToolTipExample. -------->");
	  TestReporter.log("Launch the Firefox browser.");
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }

  /*
   * @summary: By default, methods annotated by @Test are executed alphabetically. 
   *   You need to use the ‘priority‘ parameter, if you want the methods to be executed in your order.
   *   Parameters are keywords that modify the annotation’s function.
   * @author: Praveen Namburi,@version: Created 30-05-2016.
   */
  
  //prioritized to execute this method as first.
  @Test(priority=1,enabled=true,groups={"TestNG Tests"})
  public void verifyToolTipText_Case1() {
	  TestReporter.log("Launch the Application.");
	  driver.navigate().to(seleniumURL);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  
	  //Here the tooltip text is captured by using selenium getAttribute() method.
	  WebElement eleToolTipText = driver.findElement(By.cssSelector("#header>h1 a"));
	  String getToolTipText = eleToolTipText.getAttribute("title");
	  TestReporter.log("Captured tooltip text is : " + getToolTipText);
	  
	  //verifying Captured tooltip text
	  TestReporter.log("Verifying the Captured tooltip text.");
	  Assert.assertEquals(getToolTipText, actualToolTipText1);
  }
  
  //prioritized to execute this method as second.
  @Test(priority=2,enabled=true)
  public void verifyToolTipText_Case2() {
	  TestReporter.log("Launch the Application.");
	  driver.navigate().to(jQueryURL);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	  try {
			  //Here a frame exists, so we need to switch to it.
			  WebDriverWait wait = new WebDriverWait(driver, 5);
			  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(".demo-frame")));
			  
			  //title="We ask for your age only for statistical purposes."
			  //Textbox field, where we do mouse hover
			  WebElement eleTextBox = driver.findElement(By.id("age"));
			  
			  //Use action class to mouse-hover on the text-box field 
			  Actions action = new Actions(driver);
			  action.moveToElement(eleTextBox).build().perform();
			  WebElement eleToolTipText = driver.findElement(By.cssSelector(".ui-tooltip"));
			  
			  //Capture the tool tip text and assert it.
			  String getToolTipText = eleToolTipText.getText();
			  TestReporter.log("Captured tooltip text is : " + getToolTipText);
			  
			  //verifying Captured tooltip text
			  TestReporter.log("Verifying the Captured tooltip text.");
			  Assert.assertEquals(getToolTipText, actualToolTipText2);
			  
	  } catch (Exception e) {
		  	  e.printStackTrace();
	  }
  }
  
  @Test(priority=3,enabled=false)
  public void verifyToolTipText_Practice() {
	  TestReporter.log("Launch the Application.");
	  driver.navigate().to(strToolTipURL);
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	  try {
			  //title="We ask for your age only for statistical purposes."
			  //Textbox field, where we do mouse hover
			  WebElement eleTextBox = driver.findElement(By.className("prev"));
			  
			  //Use action class to mouse-hover on the text-box field 
			  Actions action = new Actions(driver);
			  action.moveToElement(eleTextBox).build().perform();
			  WebElement eleToolTipText = driver.findElement(By.cssSelector(".prev>a"));
			  
			  WebDriverWait wait = new WebDriverWait(driver, 2);
			  wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".prev>a")));
			  
			  //Capture the tool tip text and assert it.
			  String getToolTipText = eleToolTipText.getText();
			  TestReporter.log("Captured tooltip text is : " + getToolTipText);
			  
			  //verifying Captured tooltip text
			  TestReporter.log("Verifying the Captured tooltip text.");
			  Assert.assertEquals(getToolTipText, actualToolTipText3);
			  
	  } catch (Exception e) {
		  	  e.printStackTrace();
	  }
  }
  
  @AfterMethod(groups={"TestNG Tests"})
  public void closeBrowser() {
	  if(driver!=null){
		  TestReporter.log("Closing the browser instance.");
		  driver.quit();
	  }
	  TestReporter.log("<-------- Ended the TestCase execution - verifyToolTipExample. -------->");
  }

}
