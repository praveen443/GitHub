package apps.lilo.massModify;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;

/**
 * 
 * @summary Contains the page methods & objects for the Mass Modify - reservation processing status page
 * @version Created 10/29/2014
 * @author Jessica Marshall
 */
public class ReservationProcessingStatusPage {
	//
	// ReservationProcessingStatus
	//
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	
	// *************************************
	// *** ReservationProcessingStatus Elements ***
	// *************************************
	// Mass option
	@FindBy(id = "resListSummaryForm:massOptionDropDownID")
	private Listbox lstMassOption;
	
	// Name
	@FindBy(id = "resListSummaryForm:processNameID")
	private Textbox txtName;
	
	// Submitted by
	@FindBy(id = "resListSummaryForm:submitedByID")
	private Textbox txtSubmittedBy;
	
	// From date
	@FindBy(id = "resListSummaryForm:fromDateIDInput")
	private Textbox txtFromDate;
	
	// To date
	@FindBy(id = "resListSummaryForm:toDateIDInput")
	private Textbox txtToDate;
	
	// Search
	@FindBy(id = "resListSummaryForm:searchButtonID")
	private Button btnSearch;
	
	// Clear
	@FindBy(id = "resListSummaryForm:ClearButtonID")
	private Button btnClear;
	
	// Reservation summary results table
	@FindBy(id = "resListSummaryForm:reservationListSummaryTableID:tb")
	private Webtable tblResSummary;
	
	// Refresh
	@FindBy(id = "resListSummaryForm:refreshButtonID")
	private Button btnRefresh;
	
	// Exit
	@FindBy(id = "resListSummaryForm:exitButtonID")
	private Textbox btnExit;	

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
	public ReservationProcessingStatusPage(WebDriver driver) {
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
	public ReservationProcessingStatusPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
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
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSearch); 
	}
	
	// *****************************************
	// ***Page Interactions ***
	// *****************************************
	

	

	public void clickSearch(){
		btnSearch.syncVisible(driver);
		btnSearch.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickClear(){
		btnClear.syncVisible(driver);
		btnClear.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	public void clickRefresh(){
		btnRefresh.syncVisible(driver);
		btnRefresh.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	
	public void clickExit(){
		btnExit.syncVisible(driver);
		btnExit.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	
	/**
	 * @summary 
	 * @version Created 10/29/2014
	 * @author 	Jessica Marshall
	 * @param 	reservationNum
	 * @throws 	NA
	 * @return 	false if the reservation number is not found in the table, otherwise true
	 */
	public boolean waitForAReservationToBeProcessed(String massModifyID){
		
		tblResSummary.syncVisible(driver);
		
		//Get all the rows in a list
		List<WebElement> rowList = tblResSummary.findElements(By.tagName("tr"));
		
		//If there are no rows, then return false
		if (rowList.isEmpty())
			return false;
		
		//Find the row that contains the unique mass modify number
		for (WebElement row:rowList){
			//Get a list of all the table data elements
			List<WebElement> cellList = row.findElements(By.tagName("td"));
			
			//if the 3rd cell has the specified reservation number, then check the checkbox
			if (cellList.get(0).getText().equalsIgnoreCase(massModifyID)){
				//The first element is the checkbox cell
				cellList.get(0).findElement(By.cssSelector("input[type='checkbox']")).click();
				return true;
			}
		}
		//Reporter.log("Travel Plan ID: " + reservationNum + " was not found in search results");
		return false;
	}
}
