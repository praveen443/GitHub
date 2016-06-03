package toyota;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class getTheCarsText {
	private WebDriver driver;
	
	@Test(priority=1,enabled=true)
	public void getCarNames(){
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to("http://www.toyota.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@AfterMethod
	public void closeBrowser(){
		if(driver!=null) driver.quit();
	}

}
