package fileUploading;

import java.awt.Robot;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testResources.ConstantInput.constants;
import Utils.Sleeper;
import Utils.TestReporter;

public class uploadFile_Using_SendKeysMethod {

	public static void main(String[] args) throws IOException {
		 System.setProperty("webdriver.chrome.driver", constants.chromeDriverPath);
		 TestReporter.log("Launch the Chrome browser.");
		 WebDriver driver = new ChromeDriver();
		 TestReporter.log("Maximize the browser window.");
		 driver.manage().window().maximize();
		
		 //Launch the URL.
		 driver.get("https://www.freepdfconvert.com/");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		 WebElement element = driver.findElement(By.id("clientUpload"));
		 element.sendKeys(constants.filePath1);
		 
		/* WebElement element1 = driver.findElement(By.id("clientUpload"));
		 Actions actions = new Actions(driver);
		 actions.moveToElement(element1).click().perform();
		 
	     Sleeper.sleep(5000);*/
	     
	     /*Runtime.getRuntime().exec("C:\\Users\\praveen\\Desktop\\AutoITFileUpload.exe");*/
	     
		 /*WebElement btnConvert= driver.findElement(By.xpath("//input[@id='convertButton'][@value='Convert']"));
		 Actions actions = new Actions(driver);
		 actions.moveToElement(btnConvert).click().perform();*/
		 
		 
		 
		 
		 
		 
		 
		 
		 
		// Assume driver is a valid WebDriver instance that
		// has been properly instantiated elsewhere.
		/*WebElement btnConvertToFile = driver.findElement(By.className("btn-group open"));
		btnConvertToFile.click();*/
		/*JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", btnConvertToFile);*/
		 long varA = (245*1500);
		 long varB = (244*1500);
		 long varSum1 = (varA-varB)-600;
		 System.out.println("Output1: " + varSum1);
		 
		 long varC = (1054*3000);
		 long varD = (1042*3000);
		 long varSum2 = (varC-varD)-16000;
		 System.out.println("Output2: " + varSum2);
	     
		 long varSumFinal = varSum1+varSum2;
		 System.out.println("Total sum assured for one person: " + varSumFinal);
		 
		 long varSumAssured = ((varSumFinal)*20);
		 System.out.println("Total sum assured for 20 persons: "+ varSumAssured);
		 
		 long varSumTaxDeduction = ((varSumAssured)-(1900*20));
		 System.out.println("Total sum Received per month for 20 persons: " + varSumTaxDeduction);
		 
		 long varFinalLumpSumAmount = ((varSumTaxDeduction)*6);
		 System.out.println("Total Sum amount for half-yearly: " + varFinalLumpSumAmount);
	}

}
