package apps.lilo.housekeeping;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.bankIn.BankInPage;
import core.interfaces.Button;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the Print preview of housekeeping schedule page
 * @version Created 10/13/2014
 * @author Jessica Marshall
 */
public class PrintPreviewHousekeepingSchedulePage {
	//
	// Page Fields
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// *************************************
	// *** Page Elements ***
	// *************************************

	// Print
	@FindBy(id = "printHskSchedule:printLink")
	private Button btnPrint;
	
	// Close
	@FindBy(id = "printHskSchedule:closeLink")
	private Button btnClose;
	
	
	// Table of housekeepers
	@FindBy(id = "printHskSchedule:printDailyHskTable:0:housekeepingScheduleList:tb")
	private Webtable tblHousekeepers;
	
	// *********************
	// ** Build page area **
	// *********************
	
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public PrintPreviewHousekeepingSchedulePage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public PrintPreviewHousekeepingSchedulePage initialize() {
		return ElementFactory.initElements(driver,
				PrintPreviewHousekeepingSchedulePage.class);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 9/11/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(BankInPage.class, driver, btnPrint); 
	}
	
	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	
	public void clickPrintButton() {
		
		
		btnPrint.syncVisible(driver);
		
		//((JavascriptExecutor)driver).executeScript("document.getElementById('printoptionForm:j_id61').click()");

		//btnPrint.mouseClick();
		btnPrint.click();
	}
	
	

	public void clickClose(){
		btnClose.syncVisible(driver);
		btnClose.click();
		btnClose.syncHidden(driver);
	}
	

	
}

