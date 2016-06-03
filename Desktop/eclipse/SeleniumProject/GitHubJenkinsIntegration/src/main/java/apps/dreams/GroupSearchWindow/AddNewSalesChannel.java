package apps.dreams.GroupSearchWindow;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.Constants;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the methods & objects for the Dreams UI content frame for
 *          Add New Sales Channel.
 * @version Created 03/22/2016
 * @author Praveen Namburi
 */
public class AddNewSalesChannel {
	
	// ****************************
	// *** Content Frame Fields ***
	// ****************************	
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// ******************************
	// *** Content Frame Elements ***
	// ******************************
	
	//Button AddNewSalesChannel
	@FindBy(xpath = "//input[@value='Add New Sales Channel']")
	private Button btnAddNewSalesChannel;
		
	//Textbox NewSalesChannel
	@FindBy(name = "newSalesChannel")
	private Textbox txtNewSalesChannel;
		
	//Listbox SalesZone
	@FindBy(name = "salesZone")
	private Listbox lstSalesZone;
		
	//Listbox DistributionChannel
	@FindBy(name = "distributor")
	private Listbox lstDistributionChannel;
		
	//Listbox MemberShip
	@FindBy(name = "membership")
	private Listbox lstMemberShip;
		
	//Checkbox RSRFlag 
	@FindBy(name = "rsrFlag")
	private Checkbox chkRSRFlag;
	
	//Button Add in BlockInventory
	@FindBy(name = "search")
	private Button btnSearch;
	
	//Button Add in AddSalesChannel window
	@FindBy(name="Add")
	private Button btnAddSalesChannel;
	
	// *********************
	// ** Build page area **
	// *********************

	/**
	 * @summary Constructor to initialize the frame
	 * @version Created 03/22/2016
	 * @author Praveen Namburi
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public AddNewSalesChannel(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}
	
	private AddNewSalesChannel initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSearch);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	// ***********************************
	// *** Content Frame Interactions ***
	// ***********************************

	
	/**
	 * @summary: Method to click on 'Add New Sales Channel' button.
	 * @author: Praveen Namburi, @version: Created 3/21/2016
	 */
	public void clickAddNewSalesChannel(){
		pageLoaded(btnAddNewSalesChannel);
		btnAddNewSalesChannel.highlight(driver);
		btnAddNewSalesChannel.jsClick(driver);
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "Add Block Sales Channel");
		
		// The Sales Channel window is notoriously long to load, sometimes
		// upwards of 5 minutes. In the past, this has been raised as an issue.
		// However, it was determined that this is the behavior in Prod, was
		// subsequently noted as a Prod issue, and closed. In short, loop for at
		// least 5 minutes until the window is loaded.
		int maxMinutes = 5;
		int maxSeconds = maxMinutes * 60;
		int maxMilliSeconds = maxSeconds * 1000;
		boolean loaded = false;
		driver.manage().timeouts().setScriptTimeout(maxSeconds, TimeUnit.SECONDS);
		StopWatch sw = new StopWatch();
		sw.start();
		do{
			try {
				loaded = new PageLoaded().isDomComplete(driver);
			} catch (Exception e) {
				Sleeper.sleep(1000);
			}
		}while(!loaded && sw.getTime() < maxMilliSeconds);
		sw.stop();
		//Assert that the page loaded within the presefined amo0unt of time
		TestReporter.assertTrue(loaded, "The Sales Channel window did not load after ["+maxSeconds+"] seconds.");
		
		driver.manage().timeouts().setScriptTimeout(Constants.GLOBAL_DRIVER_TIMEOUT, TimeUnit.SECONDS);
	}
	
	/**
	 * @Summary: Method to Add NewSalesChannel.
	 * @author : Praveen Namburi, @version: Created 3/21/2016.
	 * @param scenario
	 */
	public void addNewSalesChannel(String scenario){
		datatable.setVirtualtablePage("AttritionInformation");
		datatable.setVirtualtableScenario(scenario);

		String newSalesChannel  = datatable.getDataParameter("NewSalesChannel");
		String salesZone  = datatable.getDataParameter("SalesZone");
		String distributionChannel = datatable.getDataParameter("DistributionChannel");
		String memberShip = datatable.getDataParameter("MemberShip");
		String setRSRFlag = datatable.getDataParameter("SetRSRFlag");
		
		clickAddNewSalesChannel();
		
		pageLoaded(txtNewSalesChannel);

		// The Sales Channel window is notoriously long to load, sometimes
		// upwards of 5 minutes. In the past, this has been raised as an issue.
		// However, it was determined that this is the behavior in Prod, was
		// subsequently noted as a Prod issue, and closed. In short, at this
		// point the window should have been deemed loaded, so loop until the
		// New Sales Channel textbox is visible.
		boolean isVisible = false;
		int maxMinutes = 5;
		int maxSeconds = maxMinutes * 60;
		int maxMilliseconds = maxSeconds * 1000;
		StopWatch sw = new StopWatch();
		sw.start();
		do{
			try{
				isVisible = txtNewSalesChannel.syncVisible(driver, 1, false);
			}catch(Exception e){
				try{
					initialize();
				}catch(Exception e2){}
			}
		}while(!isVisible && sw.getTime() < maxMilliseconds);
		sw.stop();
		TestReporter.assertTrue(isVisible, "The New Sales Channel textbox was not visible after ["+maxSeconds+"] seconds.");
		
		txtNewSalesChannel.highlight(driver);
		txtNewSalesChannel.safeSet(newSalesChannel);
		pageLoaded(lstSalesZone);
		lstSalesZone.select(salesZone);
		lstDistributionChannel.select(distributionChannel);
		pageLoaded(lstMemberShip);
		lstMemberShip.select(memberShip);
		//To check the RSR Flag check-box
		if(setRSRFlag.equalsIgnoreCase("True")){
			chkRSRFlag.isEnabled();
			chkRSRFlag.highlight(driver);
			chkRSRFlag.check();
		}
		
		//Click on Add button.
		pageLoaded(btnAddSalesChannel);
		btnAddSalesChannel.highlight(driver);
		btnAddSalesChannel.click();
		
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
    	WindowHandler.waitUntilWindowExistsTitleContains(driver, "Disney Reservation");
	}
	
}



