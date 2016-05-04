package apps.lilo.reservationDetails;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

public class CleaningSchedulePage {
	// *******************************
	// *** CleaningSchedule Fields ***
	// *******************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// Apply 
	@FindBy(id = "cleaningScheduleMainForm:applyCleaningScheduleId")
	private Button btnApply;
	
	// Close
	@FindBy(id = "cleaningScheduleMainForm:cancelCleaningSchdButtonId")
	private Button btnClose;
	
	// Cleaning schedule table
	@FindBy(id = "cleaningScheduleMainForm:cleaningScheduleList:tb")
	private Webtable tblCleaning;
	
	// Cleaning schedule applied pop up success
	@FindBy(id = "alertPopupModalPanelCDiv")
	private Element eleAppliedPopUp;
	
	// Cleaning schedule applied pop up ok 
	@FindBy(id = "alertPopupForm:okBtnId")
	private Button btnAppliedPopUpOk;

	// *********************
	// ** Build page area **
	// *********************
	
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public CleaningSchedulePage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public CleaningSchedulePage initialize() {
		return ElementFactory.initElements(driver,
				this.getClass());
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/10/2014
	 * @author Justin Phlegar
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnClose); 
	}

	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	
	public void clickApply(){
		btnApply.syncEnabled(driver);
		btnApply.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickClose(){
		btnClose.syncEnabled(driver);
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickAppliedPopUpOk(){
		btnAppliedPopUpOk.syncVisible(driver);
		btnAppliedPopUpOk.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}	
}
