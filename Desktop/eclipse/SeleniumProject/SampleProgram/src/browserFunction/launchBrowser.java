package browserFunction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import testResources.ConstantInput.constants;
import Utils.TestReporter;

public class launchBrowser {
	@SuppressWarnings("unused")
	private WebDriver driver;
	private static String testName;
	//Constructor to initialize the page.
	/*public launchBrowser(WebDriver driver) {
		this.driver=driver;
	}*/

	public static void getBrowserType(WebDriver driver,String browserType,String appURL){
	
		switch (browserType.toLowerCase()) {
		case "firefox":
			  redditUserLogin_UsingFirefox(driver,appURL);
			break;
			
		case "chrome":
			  redditUserLogin_UsingChrome(driver,appURL);
			break;
			
		case "ie":
		case "iexplorer":
			redditUserLogin_UsingIE(driver,appURL);
			break;

		default:
			break;
		}
		
	}
	
	/**
	 * @summary: Method to login to reddit application using Chrome browser.
	 * @author: Praveen namburi, @version: Created 13-06-2016. 
	 * @param driver
	 */
	private static void redditUserLogin_UsingChrome(WebDriver driver,String appURL){
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
			
		 testName = new Object() {}.getClass().getEnclosingMethod().getName();
		 TestReporter.logStep("TestCase Name: " + testName);
		
		 System.setProperty("webdriver.chrome.driver", constants.chromeDriverPath);
		 TestReporter.log("Launch the Chrome browser.");
		 driver = new ChromeDriver();
		 TestReporter.log("Maximize the browser window.");
		 driver.manage().window().maximize();
		
		 //Launch the URL.
		 TestReporter.log("Navigate to Reddit application with the URL - " + prop.getProperty("URL") );
		 driver.get(prop.getProperty(appURL));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
	
	/**
	 * @summary: Method to login to reddit application using Firefox browser.
	 * @author: Praveen namburi, @version: Created 13-06-2016. 
	 * @param driver
	 */
	private static void redditUserLogin_UsingFirefox(WebDriver driver,String appURL){
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
		
		 /*
		  * Grab the @Test method name and use it to define the test name
		  */
		 testName = new Object() {}.getClass().getEnclosingMethod().getName();
		 String testName = new Object() {}.getClass().getEnclosingMethod().getName();
		 TestReporter.logStep("TestCase Name: " + testName);
			
		 TestReporter.log("Launch the Firefox browser.");
		 driver = new FirefoxDriver();
		 TestReporter.log("Maximize the browser window.");
		 driver.manage().window().maximize();
				
		 //Launch the URL.
		 TestReporter.log("Navigate to Reddit application with the URL - " + prop.getProperty("URL") );
		 driver.get(prop.getProperty(appURL));
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}
	
	/**
	 * @summary: Method to login to reddit application using IE browser.
	 * @author: Praveen namburi, @version: Created 13-06-2016. 
	 * @param driver
	 */
	private static void redditUserLogin_UsingIE(WebDriver driver,String appURL){
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
		
			//To get the test-case name.
			testName = new Object() {}.getClass().getEnclosingMethod().getName();
			TestReporter.logStep("TestCase Name: " + testName);
			
			System.setProperty("webdriver.ie.driver", constants.IE_32bit_DriverPath);
			TestReporter.log("Launch the IE browser.");
			driver = new InternetExplorerDriver();
			TestReporter.log("Maximize the browser window.");
			driver.manage().window().maximize();
				
			//Launch the URL.
			TestReporter.log("Navigate to Reddit application with the URL - " + prop.getProperty("URL") );
			driver.get(prop.getProperty(appURL));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
	}
	
}
