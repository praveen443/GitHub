package apps.lilo.reservationDetails;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Element;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
/**
 * 
 * @summary Contains the page methods & objects for the navigation tabs
 * 			at the top of the reservation (Room, guest, media, folio activity, billing options
 * 			, misc, history, 
 * @version Created 9/18/2014
 * @author Jessica Marshall
 */
public class ReservationTabsPage {
	// ******************************
	// *** ReservationTabs Fields ***
	// ******************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// ********************************
	// *** ReservationTabs Elements ***
	// ********************************
	// Room tab
	@FindBy(id = "roomTabPanel_lbl")
	private Element eleRoomTab;
	
	// guest tab
	@FindBy(id = "viewGuestTabPanel_lbl")
	private Element eleGuestTab;
	
	// media tab
	@FindBy(id = "mediaPanel_lbl")
	private Element eleMediaTab;
	
	// folio tab
	@FindBy(id = "viewFolioTab_lbl")
	private Element eleFolioTab;
	
	// billing options tab
	@FindBy(id = "billingOptionsTabPanel_lbl")
	private Element eleBillingOptionsTab;
	
	// misc tab
	@FindBy(id = "billingOptionsTabPanel_lbl")
	private Element eleMiscTab;
	
	// misc tab
	@FindBy(id = "historyTab_lbl")
	private Element eleHistoryTab;
	
	// *********************
	// ** Build page area **
	// *********************

	public ReservationTabsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	private ReservationTabsPage initialize(WebDriver driver) {
		return ElementFactory.initElements(driver,
				ReservationTabsPage.class);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, eleRoomTab); 
	}

	// **************************************
	// *** Main CheckIn Page Interactions ***
	// **************************************

	public void clickRoomTab() {
		eleRoomTab.syncVisible(driver, 60, false);
		initialize(driver);
		eleRoomTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickGuestTab() {
		eleGuestTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickMediaTab() {
		eleMediaTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickFolioTab() {
		
		eleFolioTab.syncVisible(driver);
		initialize(driver);
		eleFolioTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickBillingOptionsTab() {
		eleBillingOptionsTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickMiscTab() {
		eleMiscTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickHistoryTab() {
		eleHistoryTab.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}
