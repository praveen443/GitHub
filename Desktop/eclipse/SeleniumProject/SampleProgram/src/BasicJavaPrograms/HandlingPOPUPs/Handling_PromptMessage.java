package BasicJavaPrograms.HandlingPOPUPs;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.HighlightElement;
import Utils.TestReporter;

public class Handling_PromptMessage {

	public static WebDriver driver;
	public static String promptPOPUPHTMLfile = "C:\\Users\\praveen\\Desktop\\PromptPopUps.html";
	public static  HighlightElement ele = new HighlightElement();
	public static String userName = "Varma";
	
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		TestReporter.log("Launch the Prompt dialog-box HTML file.");
		driver.get(promptPOPUPHTMLfile);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement btnClickOnMe = driver.findElement(By.cssSelector("button[onclick='promptFunction()']"));
		ele.highlight(driver, btnClickOnMe);
		btnClickOnMe.click();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		TestReporter.log("Switch to the Prompt message and set the UserName as -"+ userName +" .");
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(userName);
		TestReporter.log("Accept the Prompt dialog-box.");
		alert.accept();
		
		WebElement elePromptMsg = driver.findElement(By.cssSelector("#promptdemo"));
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(elePromptMsg));
		ele.highlight(driver, elePromptMsg);
		
		String getPropmtMessage = elePromptMsg.getText();
		TestReporter.log("Prompt message is : "+ getPropmtMessage);
		TestReporter.log("Verfying the Prompt message.");
		Assert.assertTrue(getPropmtMessage.contains(userName));
		
		if(driver!=null) 
			TestReporter.log("Close the current browser instance.");
			driver.quit();
	}
}
