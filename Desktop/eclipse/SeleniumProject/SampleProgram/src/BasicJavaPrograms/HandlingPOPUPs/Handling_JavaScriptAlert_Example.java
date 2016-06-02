package BasicJavaPrograms.HandlingPOPUPs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.HighlightElement;
import Utils.Sleeper;
import Utils.TestReporter;

public class Handling_JavaScriptAlert_Example {
	public static WebDriver driver;
	public static String javaScriptAlertHTMLfile = "C:\\Users\\praveen\\Desktop\\javaScriptAlerts.html";
	public static HighlightElement ele = new HighlightElement();

    public static void main(String[] args) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		TestReporter.log("Launch the HTML file.");
		driver.get(javaScriptAlertHTMLfile);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement btnClickOnMe = driver.findElement(By.cssSelector("html>body>fieldset>button"));
		TestReporter.log("Click on button to view the alert window.");
		btnClickOnMe.click();
		TestReporter.log("Switch to alert message and accept");
		Alert alert = driver.switchTo().alert();	
		alert.accept();
		
		Sleeper.sleep(2000);
		TestReporter.log("Close the browser instance.");
		if(driver!=null){
			driver.quit();
		}
	}

}
