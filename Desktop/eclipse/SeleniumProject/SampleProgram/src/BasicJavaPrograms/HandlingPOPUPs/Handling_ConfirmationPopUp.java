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

public class Handling_ConfirmationPopUp {
	public static WebDriver driver;
	public static String confirmationPOPUPHTMLfile = "C:\\Users\\praveen\\Desktop\\PromptPopUps.html";
	public static  HighlightElement ele = new HighlightElement();
	public static String userName = "Varma";
	
    @SuppressWarnings("static-access")
	public static void main(String[] args) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		TestReporter.log("Launch the Confirmation-PopUp HTML file.");
		driver.get(confirmationPOPUPHTMLfile);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement btnClickOnMe = driver.findElement(By.cssSelector("button[onclick='promptFunction()']"));
		ele.highlight(driver, btnClickOnMe);
		btnClickOnMe.click();
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
		TestReporter.log("Switch to the Confirmation-PopUp and set the UserName as -"+ userName +" .");
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(userName);
		TestReporter.log("Accept the confirmation message.");
		alert.accept();
		
		WebElement eleConfirmationText = driver.findElement(By.cssSelector("#promptdemo"));
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(eleConfirmationText));
		ele.highlight(driver, eleConfirmationText);
		
		String getConfirmationText = eleConfirmationText.getText();
		TestReporter.log("Confirmation message is : "+ getConfirmationText);
		Assert.assertTrue(getConfirmationText.contains(userName));
		
		if(driver!=null) 
			TestReporter.log("Close the current browser instance.");
			driver.quit();
	}
}
