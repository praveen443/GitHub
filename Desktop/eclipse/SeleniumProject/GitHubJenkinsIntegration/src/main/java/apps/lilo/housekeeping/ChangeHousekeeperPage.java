package apps.lilo.housekeeping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import apps.lilo.bankIn.BankInPage;
import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the Change housekeeper pop up
 * @version Created 10/2/2014
 * @author Jessica Marshall
 */
public class ChangeHousekeeperPage {
	//
	// Page Fields
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// *************************************
	// *** Page Elements ***
	// *************************************

	// Housekeeper list table
	@FindBy(id = "housekeeperForm:HousKeeperTable:tb")
	private Webtable tblHousekeeperList;
	
	// Assign
	@FindBy(id = "housekeeperForm:j_id252")
	private Webtable btnAssign;
	
	// Close
	@FindBy(id = "housekeeperForm:j_id254")
	private Webtable btnClose;
	
	// *********************
	// ** Build page area **
	// *********************
	
	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/2/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ChangeHousekeeperPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/2/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public ChangeHousekeeperPage initialize() {
		return ElementFactory.initElements(driver,
				ChangeHousekeeperPage.class);
	}

	/**
	 * @summary Constructor to initialize the page
	 * @version Created 10/2/2014
	 * @author Jessica Marshall
	 * @param driver
	 * @throws NA
	 * @return NA
	 */
	public boolean loadPage() {
		return new PageLoaded().isPageHTMLLoaded(BankInPage.class, driver, btnAssign); 
	}

	// *****************************************
	// ***Housekeeping Links Page Interactions ***
	// *****************************************
	
	public void clickAssignButton() {
		btnAssign.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickCloseButton() {
		btnClose.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * @summary Clicks the row in the list of housekeepers by the row number passed in
	 * @version Created 10/2/2014
	 * @author Jessica Marshall
	 * @param rowNum
	 * @throws NA
	 * @return NA
	 */
	public void clickHousekeeperRowByRow(int rowNum){
		tblHousekeeperList.clickCell(driver, rowNum, 1);
	}
	
	/**
	 * @summary Clicks the row in the list of housekeepers by the name of the housekeeper passed in
	 * @version Created 10/2/2014
	 * @author Jessica Marshall
	 * @param housekeeperName
	 * @throws NA
	 * @return NA
	 */
	public void clickHousekeeperRowByName(String housekeeperName){
		int rowNum = 0;
		rowNum = tblHousekeeperList.getRowWithCellText(driver, housekeeperName, 1);
		tblHousekeeperList.clickCell(driver, rowNum, 1);
	}
	
	/**
	 * @summary Returns the name of the selected housekeeper by the row number passed in
	 * @version Created 10/2/2014
	 * @author Jessica Marshall
	 * @param rowNum
	 * @throws NA
	 * @return Housekeeper name
	 */
	public String getSelectedHousekeeper(int rowNum){
		return tblHousekeeperList.getCellData(driver, rowNum, 1).trim();
	}
	

}

