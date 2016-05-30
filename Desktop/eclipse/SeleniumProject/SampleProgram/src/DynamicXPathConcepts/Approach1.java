package DynamicXPathConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Approach1 {
	public static WebDriver driver=null;
	public final static String appURL = "http://demosite.center/wordpress/wp-login.php";
	
	public static void main(String[] args){
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//Identifying the element using xpath approach1.
		WebElement txtUserLogin = driver.findElement(By.xpath("//input[@id='user_login']"));
		System.out.println(txtUserLogin.isDisplayed());
		
		//Identifying the element using cssSelector.
		WebElement txtBoxUserName = driver.findElement(By.cssSelector("input[id='user_login']"));
		System.out.println(txtBoxUserName.isDisplayed());
		
		//Identifying the element texbox using  approach2.
		WebElement txtBoxUserName1=driver.findElement(By.xpath("//input[@id='rememberme'][@type='checkbox']"));
		System.out.println(txtBoxUserName1.isDisplayed());
		
		//Identifying the element wordpress	image link using cssSelector.
		WebElement lnkWordPressImg = driver.findElement(By.cssSelector("a[title='Powered by WordPress']"));
		System.out.println(lnkWordPressImg.isDisplayed());
		
		//Locating the element using cssSelector for the link with href attribute
		WebElement lnkLostYourPassword = driver.findElement(By.cssSelector("a[href='http://demosite.center/wordpress/wp-login.php?action=lostpassword']"));
		lnkLostYourPassword.isDisplayed();
		System.out.println(lnkLostYourPassword.isDisplayed());
		
		//Close the current instance browser.
		driver.close();

	}
}
