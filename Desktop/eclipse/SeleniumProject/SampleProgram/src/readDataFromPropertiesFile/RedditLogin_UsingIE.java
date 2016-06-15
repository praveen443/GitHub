package readDataFromPropertiesFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import testResources.ConstantInput.constants;
import Utils.HighlightElement;
import Utils.Sleeper;
import Utils.TestReporter;

public class RedditLogin_UsingIE {
  
	private WebDriver driver;
	public Properties prop = null;
	HighlightElement ele = new HighlightElement();
	
	/*@BeforeMethod
	public void setUp() throws IOException{
		System.setProperty("webdriver.ie.driver", constants.IE_32bit_DriverPath);
		TestReporter.log("Launch the IE browser.");
		driver = new InternetExplorerDriver();
		TestReporter.log("Maximize the browser window.");
		driver.manage().window().maximize();
	}*/
	
	@SuppressWarnings("static-access")
	@Test
	public void redditUserLogin_UsingIE(){
		  File file = new File(constants.propertiesFilePath);
		  
		  FileInputStream fileInput = null;
		  try {
			  fileInput = new FileInputStream(file);
		  } catch (FileNotFoundException e) {
			  e.printStackTrace();
		  }
		  Properties prop = new Properties();
		
		  //load properties file
		  try {
			  prop.load(fileInput);
		  } catch (IOException e) {
		  	  e.printStackTrace();
		  }
		
		  System.setProperty("webdriver.ie.driver", constants.IE_32bit_DriverPath);
		  TestReporter.log("Launch the IE browser.");
		  driver = new InternetExplorerDriver();
		  TestReporter.log("Maximize the browser window.");
		  driver.manage().window().maximize();
			
		  //Launch the URL.
		  TestReporter.log("Navigate to Reddit application with the URL - " + prop.getProperty("URL") );
		  driver.get(prop.getProperty("URL"));
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  //set username.
		  TestReporter.log("Enter the username - "+ prop.getProperty("userName"));
		  WebElement txtUserName =  driver.findElement(By.xpath("//input[@name='user']"));
		  ele.highlightElement(driver, txtUserName);
		  txtUserName.sendKeys(prop.getProperty("userName"));
		  
		  //set password.
		  TestReporter.log("Enter the password - "+ prop.getProperty("password"));
		  WebElement txtPassword =  driver.findElement(By.xpath("//input[@name='passwd']"));
		  ele.highlightElement(driver, txtPassword);
		  txtPassword.sendKeys(prop.getProperty("password"));
		  
		  //Click on Log-Out link.
		  TestReporter.log("Click on Login button.");
		  WebElement btnLogin =  driver.findElement(By.xpath("//button[@class='btn'][@type='submit']"));
		  ele.highlightElement(driver, btnLogin);
		  btnLogin.click();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  Sleeper.sleep(5000);
		  /*try{
			  WebDriverWait wait=new WebDriverWait(driver, 15);
			  wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".user>a")));
		  }catch(ElementNotVisibleException e){
			  e.printStackTrace();
		  }*/
		  WebElement lnkUserName = driver.findElement(By.cssSelector(".user>a"));
		  ele.highlightElement(driver, lnkUserName);
		  String txtUserNameAfterLogin =  lnkUserName.getText();
		  System.out.println(txtUserNameAfterLogin);
		  
		  //Verifying that the user login is successfull.
		  TestReporter.log("Verifying the UserName after login.");
		  Assert.assertEquals(txtUserNameAfterLogin,prop.getProperty("userName"), "Home page is not loaded.");
		 
		  //Click on log-out link.
		  TestReporter.log("Click on Log-Out link.");
		  WebElement lnkLogOut = driver.findElement(By.linkText("logout"));
		  ele.highlightElement(driver, lnkLogOut);
		  lnkLogOut.click();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  WebElement btnLogin_LoginPage = driver.findElement(By.cssSelector("button[class='btn']"));
		  //Verifying that the user logged-out successfully.
		  TestReporter.log("Verifying that the user logged-out successfully.");
		  Assert.assertTrue(btnLogin_LoginPage.isDisplayed(),"Login page is not loaded.");

  }
	 
  /*@AfterMethod
  public void closeBrowser(){
	  TestReporter.log("Close the browser instance.");
	  if(driver!=null) driver.quit(); 
  }
	*/
}

