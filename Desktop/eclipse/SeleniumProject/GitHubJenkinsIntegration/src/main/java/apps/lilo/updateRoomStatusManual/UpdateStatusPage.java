package apps.lilo.updateRoomStatusManual;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
* @summary Contains the page methods & objects found in the Update Room Status Manual page
* @version Created 8/15/2014
* @author Justin Phlegar
*/

public class UpdateStatusPage {
	//*********************************
	//*** Update Status Page Fields ***
	//*********************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	//***********************************
	//*** Update Status Page Elements ***
	//***********************************
	
	//Submit button
	@FindBy(id = "updateRoomStatusForm:submitButtonId")
	private Link lnkSubmit;
	
	//Housekeeping Status listbox
	@FindBy(id = "updateRoomStatusForm:editTableId:0:newHousekeeperStatusId")
	private Listbox lstHousekeepingStatus;
	
	//Housekeeping Occupancy listbox
	@FindBy(id = "updateRoomStatusForm:editTableId:0:newHousekeeperOccId")
	private Listbox lstHousekeepingOccupancy;
	
	//Update Status Alert Continue button
	@FindBy(id = "alertPopupForm:continueButtonPanel1")
	private Link lnkAlertContinue;
	
	//Update Status Alert Ok button
	@FindBy(id = "alertPopupForm:okBtnId")
	private Link lnkAlertOk;
	
	//*********************
    //** Build page area **
    //*********************

    /**
    * 
    * @summary Constructor to initialize the page
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  driver
    * @throws NA
    * @return NA
    * 
    */

	public UpdateStatusPage(WebDriver driver){
	       this.driver = driver;
	       ElementFactory.initElements(driver, this);
	   	datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean pageLoaded(WebDriver driver){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkSubmit);        
	}

	public boolean pageLoaded(WebDriver driver, Element element){
	       return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);        
	}

	//***************************************
	//*** Update Status Page Interactions ***
	//***************************************

    /**
    * 
    * @summary Method to select a Housekeeping Status option
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  option - String option to select from teh list
    * @throws NA
    * @return NA
    * 
    */
	private void selectHousekeepingStatus(String option){
		lstHousekeepingStatus.select(option);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}

    /**
    * 
    * @summary Method to select a Housekeeping Occupancy option
    * @version Created 11/11/2014
    * @author Waightstill W Avery
    * @param  option - String option to select from teh list
    * @throws NA
    * @return NA
    * 
    */
	private void selectHousekeepingOccupancy(String option){
		lstHousekeepingOccupancy.select(option);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}
	
	private void clickSubmit(){
		lnkSubmit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver, lnkAlertContinue);
	}
	
	private void clickAlertContinue(){
		lnkAlertContinue.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver, lnkAlertOk);
	}
	
	private void clickAlertOk(){
		lnkAlertOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		pageLoaded(driver);
	}
	
	public void updateStatus(String hkStatus, String hkOccupancy){
		selectHousekeepingOccupancy(hkOccupancy);
		selectHousekeepingStatus(hkStatus);
		clickSubmit();
		clickAlertContinue();
		clickAlertOk();
	}
}

