package apps.lilo.tabNavigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
* @summary Contains the methods to interact with the LILO UI tabs
* @version Created 10/03/2014
* @author Waightstill W. Avery
*/
public class TabNavigation {
	// ****************************
	// *** TabNavigation Fields ***
	// ****************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//******************************
	//*** Main viewReservation Page Elements ***
	//******************************
	@FindBy(id = "roomTabPanel_lbl")
	private Label lblRoomTab;
	
	@FindBy(id = "viewGuestTabPanel_lbl")
	private Label lblGuestTab;
	
	@FindBy(id = "mediaPanel_lbl")
	private Label lblMediaTab;

	@FindBy(id = "viewFolioTab_lbl")
	private Label lblFolioActivityTab;

	@FindBy(id = "billingOptionsTabPanel_lbl")
	private Label lblBillingOptionsTab;

	@FindBy(id = "miscellaneous_lbl")
	private Label lblMiscAddOnsTab;

	@FindBy(id = "historyTab_lbl")
	private Label lblHistoryTab;
	

	//*********************
    //** Build page area **
    //*********************

    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 10/03/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */
	public TabNavigation(WebDriver driver){
		this.driver = driver;	
		ElementFactory.initElements(driver, this);  
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lblRoomTab);
	}
	
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}
	
	private TabNavigation initialize() {
	    return ElementFactory.initElements(driver, TabNavigation.class);	        
	}
		     
    //**************************************
  	//*** Main CheckIn Page Interactions ***
  	//**************************************

    /**
    * 
    * @summary Methods to navigate to the individual LILO UI tabs for a reservation
    * @version Created 10/03/2014
    * @author Waightstill W Avery
    * @param  NA
    * @throws NA
    * @return NA
    * 
    */
	
	public void clickRoomTab(){
		lblRoomTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickGuestTab(){
		lblGuestTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickMediaTab(){
		initialize();
		lblMediaTab.syncVisible(driver);
		lblMediaTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver, 60);
	}
	
	public void clickFolioActivityTab(){
		initialize();
		lblFolioActivityTab.syncVisible(driver);
		lblFolioActivityTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickBillingOptionsTab(){
		initialize();
		pageLoaded(lblBillingOptionsTab);
		lblBillingOptionsTab.syncVisible(driver);
		lblBillingOptionsTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickMiscAddOnsTab(){
		lblMiscAddOnsTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickHistoryTab(){
		lblHistoryTab.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

