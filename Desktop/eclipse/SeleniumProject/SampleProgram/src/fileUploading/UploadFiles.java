package fileUploading;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Utils.HighlightElement;
import Utils.WindowHandler;
import browserFunction.launchBrowser;

public class UploadFiles {
	public WebDriver driver;
	private String getMainWindow;
	private String getWindowTitle;
	private HighlightElement ele = new HighlightElement();
	private WindowHandler winHand = new WindowHandler();
	
	//public String filePath="C:\\Users\\praveen\\Desktop\\UploadFile.exe";
	
	@SuppressWarnings("static-access")
	@Test(priority=1,enabled=true)
	@Parameters({"browserType","appURL"})
	public void uploadFileUsingSelenium(String browserType,String appURL) {
		launchBrowser getBrowser = new launchBrowser();
		getBrowser.getBrowserType(driver, browserType, appURL);

		WebElement element = driver.findElement(By.id("inputFile"));
		element.sendKeys("C:\\Users\\praveen\\Documents\\PaySlips_2016\\sampletextdoc.txt");
		
		/*winHand.closeAllOtherWindows(driver, "Main Window");
		getMainWindow = winHand.getMainWindowHandle();
		getWindowTitle = winHand.getCurrentWindowTitle();*/
		
		/*WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("inputFile")));
		WebElement element = driver.findElement(By.id("inputFile"));
		element.sendKeys("C:\\Users\\praveen\\Documents\\PaySlips_2016\\sampletextdoc.txt");
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	
		WebElement selectConvertType = driver.findElement(By.id("toExtensionSel"));
		Select select = new Select(selectConvertType);
		select.selectByVisibleText("docx");*/
		
	}
	
}
