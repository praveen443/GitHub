package BasicJavaPrograms;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.CheckboxesHandling;
import Utils.HighlightElement;
import Utils.Sleeper;

public class highlightElement_Example {
	public static WebDriver driver;
	public static String redditAppURL = "https://www.reddit.com/";
	public static HighlightElement ele = new HighlightElement();

	public static void main(String[] args) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(redditAppURL);
		
		try{
			//Wait for the checkbox element to be visible
			WebElement checkBox = driver.findElement(By.cssSelector("#rem-login-main"));
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkBox));
			//checkBox.click();
			//HighlightElement name = new HighlightElement();
			ele.highlightElement(driver, checkBox);
			Sleeper.sleep(3000);
			CheckboxesHandling.selectTheCheckBox(checkBox);
		}catch(ElementNotVisibleException e){
			System.out.println(e.getStackTrace());
		}
		
		if(driver!=null){
			driver.quit();
		}
	}

}
