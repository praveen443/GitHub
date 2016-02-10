package Actions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseHoverOperation {
	
	//To re-use this method for the mouse-hover operations
	public static void mouseHoverUsingxpath(String locator,WebDriver driver) throws InterruptedException 
	{
		Actions builder = new Actions(driver);
		WebElement xpathElement = driver.findElement(By.xpath(locator));
		builder.moveToElement(xpathElement).build().perform();
		driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
	}
	
	//To re-use this method for the mouse-hover operations
	public static void mouseHoverUsingPartialLinkText(String locator,WebDriver driver) throws InterruptedException 
	{
		Actions builder = new Actions(driver);
		WebElement xpathElement = driver.findElement(By.partialLinkText(locator));
		builder.moveToElement(xpathElement).build().perform();
		driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
	}


}
