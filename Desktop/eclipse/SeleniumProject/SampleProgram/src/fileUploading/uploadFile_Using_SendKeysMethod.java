package fileUploading;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testResources.ConstantInput.constants;
import Utils.TakeScreenshot;
import Utils.TestReporter;

@Listeners(Utils.TestListener.class)
public class uploadFile_Using_SendKeysMethod {
    private String appURL="https://www.freepdfconvert.com/";
	private WebDriver driver;
	private String testCaseName;
	
	@Test(priority=1,enabled=true)
	public void uploadFile() throws IOException{
		 System.setProperty("webdriver.chrome.driver", constants.chromeDriverPath);
		 TestReporter.log("Launch the Chrome browser.");
		 driver = new ChromeDriver();
		 TestReporter.log("Maximize the browser window.");
		 driver.manage().window().maximize();
		 
		 //Launch the URL.
		 driver.get(appURL);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 try{
			 WebElement element = driver.findElement(By.id("clientUploads"));
			 element.sendKeys(constants.filePath1);
		 }catch(NoSuchElementException e){
			 System.out.println(e.getStackTrace());
		 }
		 
	}
	
	@AfterMethod
	public void closeDriverInstance(){
		if(driver!=null) driver.quit();
	}
	
	/*@AfterMethod
	public void closeDriver(ITestResult testResult) throws IOException {
		testCaseName = testResult.getName().toString().trim();
		if(testResult.getStatus() == ITestResult.FAILURE) {
			TakeScreenshot.takeScreenShot(driver, testCaseName);
		}
		driver.quit();
	}*/

}
