package apps.alc.pleaseWait;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.interfaces.impl.ElementImpl;
import core.interfaces.Element;
import core.interfaces.Webtable;
import utils.ExtendedExpectedConditions;

public class PleaseWaitWindow {	
	@FindBy(xpath = "//img[contains(@src, 'Processing_Window_Overlay')]") private Element elePleaseWaitImage;
	@FindBy(id = "WorkingModalPanelContentTable") private Webtable tblPleaseWait;
	@FindBy(xpath = "//div[@id='WorkingModalPanelContainer' AND  @display='none']") private Element eleContainer;
	
	public PleaseWaitWindow() {}

	public static void windowIsVisible(WebDriver driver, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Element popup = new ElementImpl(driver.findElement(By.xpath("//div[@id='WorkingModalPanelContainer']")));
		popup.syncVisible(driver, 5, false);
		
		wait.until(ExtendedExpectedConditions.textToBePresentInElementCssValue(popup, "display", "none"));
	}

	/**
	 * Added method to absorb a TimeoutException. This can be useful in
	 * situations where the Please Wait window is not consistently triggered and
	 * failure is not necessary
	 * @author Waits Avery
	 */
	public static void windowIsVisible(WebDriver driver, int timeout, boolean fail) {	
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Element popup = new ElementImpl(driver.findElement(By.xpath("//div[@id='WorkingModalPanelContainer']")));
		popup.syncVisible(driver, 5, false);
		
		try{
			wait.until(ExtendedExpectedConditions.textToBePresentInElementCssValue(popup, "display", "none"));
		}catch(TimeoutException toe){
			if(fail){
				throw new TimeoutException(toe);
			}
		}
	}

	public void windowIsVisibleWithTimeOut(WebDriver driver, String string,
			int timeout) {
		// TODO Auto-generated method stub
		
	}

	public void windowIsVisible(WebDriver driver, String string) {
		// TODO Auto-generated method stub
		
	}
}
