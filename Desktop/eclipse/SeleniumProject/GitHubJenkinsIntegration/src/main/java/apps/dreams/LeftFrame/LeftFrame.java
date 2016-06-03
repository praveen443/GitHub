package apps.dreams.LeftFrame;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.dreams.PleaseWait.PleaseWait;
import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the methods & objects for the left frame in Dreams UI
 * @version Created 09/10/2014
 * @author Waightstill W. Avery
 */
public class LeftFrame {
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	//***************************
	//*** Left Frame Elements ***
	//***************************
	@FindBy(partialLinkText = "Discovery")
	private Link lnkDiscovery;

	@FindBy(partialLinkText = "TravelPlan Search")
	private Link lnkTravelPlanSearch;

	@FindBy(partialLinkText = "Group Search")
	private Link lnkGroupSearch;

	@FindBy(partialLinkText = "Individual Posting")
	private Link lnkIndividualPosting;

	@FindBy(partialLinkText = "Payment Search")
	private Link lnkPaymentSearch;

	@FindBy(partialLinkText = "Packaging")
	private Link lnkPackaging;

	@FindBy(partialLinkText = "Organization")
	private Link lnkOrganization;

	@FindBy(partialLinkText = "Launch Availability")
	private Link lnkLaunchAvailability;

	@FindBy(partialLinkText = "CheckLog")
	private Link lnkCheckLog;

	@FindBy(partialLinkText = "Configuration")
	private Link lnkConfiguration;

	@FindBy(partialLinkText = "Rooming List")
	private Link lnkRoomingList;

	@FindBy(partialLinkText = "Res List Summary")
	private Link lnkResListSummary;

	//*********************
	//** Build page area **
	//*********************

	/**
	 * 
	 * @summary Constructor to initialize the frame
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param  driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public LeftFrame(WebDriver driver){
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "leftFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkDiscovery);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public LeftFrame initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	//*******************************
	//*** Left Frame Interactions ***
	//*******************************
	public void clickDiscovery(){
		pageLoaded(lnkDiscovery);
		//lnkDiscovery.jsClick(driver);
		lnkDiscovery.click();
		PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		try{
			try{
				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2, 3);
			}catch(TimeoutException te){}
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
			WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
			new PageLoaded().isDomComplete(driver);
			new PageLoaded().isDomInteractive(driver);
		}catch(Exception e){
			Sleeper.sleep(2000);
		}
	}

	public void clickTravelPlanSearch(){
		initialize();
		pageLoaded(lnkTravelPlanSearch);
		lnkTravelPlanSearch.highlight(driver);
		lnkTravelPlanSearch.jsClick(driver);
		//lnkTravelPlanSearch.click();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,"Disney Reservation Entry and Management System");
		
	}

	public void clickGroupSearch(){
		try{
			pageLoaded(lnkGroupSearch);
		}catch(Exception e){
			e.printStackTrace();
		}
		lnkGroupSearch.syncVisible(driver);
		lnkGroupSearch.syncEnabled(driver);
		lnkGroupSearch.highlight(driver);
//    	lnkGroupSearch.jsClick(driver);
		lnkGroupSearch.focus(driver);
//		lnkGroupSearch.click();
		lnkGroupSearch.sendKeys(Keys.ENTER);
		
//		((JavascriptExecutor) driver).executeScript("arguments[0].mouseOver();arguments[0].click()", driver.findElement(By.partialLinkText("Group Search")));
		PleaseWait.WaitForPleaseWait(driver); 
	}

	public void clickIndividualPosting(){
		initialize();
		pageLoaded(lnkIndividualPosting);
		lnkIndividualPosting.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickPaymentSearch(){
		initialize();
		pageLoaded(lnkPaymentSearch);
		lnkPaymentSearch.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickPackaging(){
		initialize();
		pageLoaded(lnkPackaging);
		lnkPackaging.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickOrganization(){
		initialize();
		pageLoaded(lnkOrganization);
		lnkOrganization.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickLaunchAvailability(){
		initialize();
		pageLoaded(lnkLaunchAvailability);
		lnkLaunchAvailability.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickCheckLog(){
		initialize();
		pageLoaded(lnkCheckLog);
		lnkCheckLog.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickConfiguration(){
		initialize();
		pageLoaded(lnkConfiguration);
		lnkConfiguration.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickRoomingList(){
		initialize();
		pageLoaded(lnkRoomingList);
		lnkRoomingList.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	public void clickResListSummary(){
		initialize();
		pageLoaded(lnkResListSummary);
		lnkResListSummary.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary method to validate enable status
	 * @version Created 03/14/2016
	 * @author Sabitha Adama
	 */
	public void validateLeftPanelEnabledOrDisabled() {
		List<WebElement> LeftPanel_Links=driver.findElements(By.tagName("a"));
		System.out.println("all links : "+LeftPanel_Links.size());
		for (WebElement webElement : LeftPanel_Links) {
			boolean isEnabled = true;
			//Validate link color to verify the disable status.
			if(webElement.getAttribute("style").contains("gray")){
				isEnabled = false;
				TestReporter.assertFalse(isEnabled, webElement.getText().trim()+" link is disabled");
				TestReporter.log("Left navigation panel is disabled");
			}
			else{
				TestReporter.assertTrue(isEnabled, webElement.getText().trim()+" link is enabled");
				TestReporter.log("Left navigation panel is enabled");
			}
		}
	}
}
