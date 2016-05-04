package apps.lilo.viewRoomHistory;

import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;


/**
 * @summary : Contains the elements and methods to interact with the LILO View room history page.         
 * @version : Created 11/30/2015
 * @author  : Praveen Namburi
 */

public class ViewroomHistoryPage {

	// **********************************
	// *** ViewroomHistoryPage fields ***
	// **********************************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// **********************************************************
	// *** ViewRoomResults and view Room HistoryPage Elements ***
	// **********************************************************

	//--------------------------------------
	// View Room Results page elements
	//--------------------------------------
	//Object for the editbox Room Number
	@FindBy(id = "viewRoomResultFormId:roomNumberId")
	private Textbox txtRoomNumber;

	//Object for the link ViewHistory
	@FindBy(id = "viewRoomResultFormId:viewHistoryButtonId")
	private Link lnkViewHistory;

	//Object for Link Find
	@FindBy(id = "viewRoomResultFormId:findRoomDeatilsButtonId")
	private Link lnkFind;

	//RoomNumber column
	@FindBy(id="viewRoomResultFormId:displayRoomHistoryResponseViewList:0:j_id47")
	private Element eleRoomnumberINtable;

	//----------------------------------------
	// View Room History details page elements
	//----------------------------------------
	//Link Close
	@FindBy(id = "viewRoomHistoryResultFormId:bottomcloseButtonId")
	private Link lnkClose;

	//Link Find in View Room History
	@FindBy(id = "viewRoomHistoryResultFormId:findRoomHistButtonId")
	private Link lnkFindInViewRoomHistory;

	@FindBy(id = "viewRoomHistoryResultFormId:viewRoomHistoryViewListId")
	private Webtable tblviewRoomHistoryViewList;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 11/30/2015
	 * @author  Praveen Namburi
	 * @param   driver
	 * @throws  NA
	 * @return  NA
	 * 
	 */
	public ViewroomHistoryPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public boolean viewRoomHistoryDetailsPageIsLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkClose);

	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lnkViewHistory);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public boolean pageLoaded(Checkbox checkbox) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, checkbox);
	}

	public ViewroomHistoryPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// **********************************************
	// *** Main ViewRoomHistory Page Interactions ***
	// **********************************************

	/**
	 * @summary : Method to find the Room Status and navigate to View Room History page
	 * @author  : Praveen Namburi
	 * @version : CReated 11/30/2015
	 * @param   : RoomNumber
	 */
	public void findRoomStatusAndNavigateToViewRoomHistoryPage(String RoomNumber){

		initialize();
		pageLoaded(txtRoomNumber);
		txtRoomNumber.safeSet(RoomNumber);

		//Click on link-text Find
		pageLoaded(lnkFind);
		lnkFind.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		pageLoaded(eleRoomnumberINtable);
		eleRoomnumberINtable.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		//Click on ViewRoom Histoy link.
		clickViewroomHistory();
	}

	/**
	 * @Summary: Methiod to click on View Room History
	 * @author : Praveen Namburi
	 * @version: Created 11/30/2015
	 */
	public void clickViewroomHistory(){
		pageLoaded(lnkViewHistory);
		lnkViewHistory.highlight(driver);
		lnkViewHistory.jsClick(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to close the View History page by clicking on the close button
	 * @author  : Praveen Namburi
	 * @version : Created 12/1/2015
	 */
	public void closeViewHistoryPage(){
		pageLoaded(lnkClose);
		lnkClose.highlight(driver);
		lnkClose.jsClick(driver);

		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * @summary : Method to verify the newly updated room status details
	 * 			  and close the  View Room History page.
	 * @author  : praveen Namburi
	 * @version : Created 11/30/2015
	 * @return  : NA
	 */
	public void verifyTheRoomStatusDetails(String FirstUpdatedRoomStatus,String SecondUpdatedRoomStatus){

		initialize();

		//Click on texlink Find in ViewRoom History page
		pageLoaded(lnkFindInViewRoomHistory);
		lnkFindInViewRoomHistory.jsClick(driver);			
		new ProcessingYourRequest().WaitForProcessRequest(driver);

		pageLoaded(tblviewRoomHistoryViewList);

		// To locate rows of table.
		List<WebElement> rows = tblviewRoomHistoryViewList.findElements(By.tagName("tr"));

		//get the count of rows from table
		int rows_table = rows.size();
		TestReporter.logStep("Total no. of rows in the table : "+rows_table);
		
		//Iterating and Validating only the first two records.
		//Because, the newly updated values will be placed in the first and second row in the table.
		for(int row = 1;row<=2;row++){

			TestReporter.logStep("Record : "+ rows.get(row).getText().trim());
			
			if(rows.get(row).getText().contains(FirstUpdatedRoomStatus)){				
			     
				 TestReporter.assertTrue(rows.get(row).getText().contains(FirstUpdatedRoomStatus), "First updated room status details are not as per expected value");

			}else if(rows.get(row).getText().contains(SecondUpdatedRoomStatus)){
				
				 TestReporter.assertTrue(rows.get(row).getText().contains(SecondUpdatedRoomStatus), "Second updated room status details are not as per expected value");
			}

		}

		//Click on Close button.
		closeViewHistoryPage();
	}	
}