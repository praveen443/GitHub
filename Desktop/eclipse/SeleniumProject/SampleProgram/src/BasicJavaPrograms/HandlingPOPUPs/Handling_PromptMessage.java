package BasicJavaPrograms.HandlingPOPUPs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utils.HighlightElement;
import Utils.TestReporter;

public class Handling_PromptMessage {

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

	}

}
