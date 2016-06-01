package BasicJavaPrograms;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class checkBoxExample {
	public static WebDriver driver;
	public static String redditAppURL = "https://www.reddit.com/";
	
	public static void main(String[] args) {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(redditAppURL);
		
		try{
			//Wait for the checkbox element to be visible
			WebElement checkBox = driver.findElement(By.cssSelector("input[id='rem-login-main']"));
			new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkBox));
			//checkBox.click();
			//selectTheCheckBox();
			
		}catch(ElementNotVisibleException e){
			System.out.println(e.getStackTrace());
		}
		
	}

}
