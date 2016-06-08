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
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;

import testResources.ConstantInput.constants;
import Utils.HighlightElement;
import Utils.Sleeper;
import Utils.TestReporter;

public class RedditLogin_UsingChrome {
  
	private WebDriver driver;
	public Properties prop = null;
	@SuppressWarnings("unused")
	private String testName;
	HighlightElement ele = new HighlightElement();
	
	/*@BeforeMethod
	public void setUp() throws IOException{
		
		 * Grab the @Test method name and use it to define the test name
		 
		String testName = new Object() {}.getClass().getEnclosingMethod().getName();
		TestReporter.logStep("TestCase Name: " + testName);
		System.setProperty("webdriver.chrome.driver", constants.chromeDriverPath);
		TestReporter.log("Launch the Chrome browser.");
		driver = new ChromeDriver();
		TestReporter.log("Maximize the browser window.");
		driver.manage().window().maximize();
	}*/
	
	@SuppressWarnings("static-access")
	@Test
	public void redditUserLogin_UsingChrome(){
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
			
		 String testName = new Object() {}.getClass().getEnclosingMethod().getName();
		 TestReporter.logStep("TestCase Name: " + testName);
		
		 System.setProperty("webdriver.chrome.driver", constants.chromeDriverPath);
		 TestReporter.log("Launch the Chrome browser.");
		 driver = new ChromeDriver();
		 TestReporter.log("Maximize the browser window.");
		 driver.manage().window().maximize();
		
		 //Launch the URL.
		 TestReporter.log("Navigate to Reddit application with the URL - " + prop.getProperty("URL") );
		 driver.get(prop.getProperty("URL"));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		 //set username.
		 TestReporter.log("Enter the username - "+ prop.getProperty("userName"));
		 WebElement txtUserName =  driver.findElement(By.cssSelector("input[name='user']"));
		 ele.highlightElement(driver, txtUserName);
		 txtUserName.sendKeys(prop.getProperty("userName"));
		  
		 //set password.
		 TestReporter.log("Enter the password - "+ prop.getProperty("password"));
		 WebElement txtPassword =  driver.findElement(By.cssSelector("input[name='passwd']"));
		 ele.highlightElement(driver, txtPassword);
		 txtPassword.sendKeys(prop.getProperty("password"));
		  
		 //Click on Log-Out link.
		 TestReporter.log("Click on Login button.");
		 WebElement btnLogin =  driver.findElement(By.cssSelector("button[class='btn']"));
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
	 
  @AfterMethod
  public void tearDown(){
		 TestReporter.log("Close the browser instance.");
		 if(driver!=null) driver.quit(); 
  }
	
}

