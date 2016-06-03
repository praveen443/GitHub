package apps.lilo.reservationListSummary;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W very
 * @version 11/23/2015 Waightstill W very - original
 */
public class ReservationListSummaryPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private List<WebElement> searchResultRows;
	private Datatable datatable = new Datatable();

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "resListSummaryForm:massOptionDropDownID")
	private Textbox lstMassOption;
	
	@FindBy(id = "resListSummaryForm:fromDateIDInput")
	private Textbox txtFromDate;
	
	@FindBy(id = "resListSummaryForm:searchButtonID")
	private Button btnSearch;
	
	@FindBy(id = "resListSummaryForm:toDateIDInput")
	private Textbox txtToDate;
	
	@FindBy(id = "resListSummaryForm:reservationListSummaryTableID")
	private Webtable tblReservationListSummaryTable;
	
	@FindBy(id = "roomingListSuccessForm:flatDetailViewList:tb")
	private Webtable tblSuccesses;
	
	@FindBy(id = "roomingListFailureForm:summaryByGroupTableID")
	private Webtable tblFailures;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @param driver
	 */
	public ReservationListSummaryPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ReservationListSummaryPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, lstMassOption);
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @param element
	 *            - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	// ********************
	// Page Class Methods
	// ********************
	
	/**
	 * Enters a date range and searches for reservations, then proceeds to validate the presence of successful and failed bookings
	 * @param scenario - page class scenario to use
	 */
	public void searchForReservationByDate(String scenario){
		datatable.setVirtualtablePage("ReservationListSummary");
		datatable.setVirtualtableScenario(scenario);
		
		String fromDate = datatable.getDataParameter("FromDate");
		String toDate = datatable.getDataParameter("ToDate");
		String value = datatable.getDataParameter("searchType");
		TestReporter.log("From Date: " + fromDate);
		TestReporter.log("To Date: " + toDate);
		TestReporter.log("Success or Failure: " + value);
		
		setFromDate(fromDate);
		setToDate(toDate);		
		
		clickSearch();
		
		TestReporter.assertTrue(reservationsReturned(), "No reservations were found in the search.");
		
		searchForSuccessOrFailure(value);
	}
	
	/**
	 * Clicks the Search button
	 */
	private void clickSearch(){
		initialize();
		pageLoaded(btnSearch);
		btnSearch.highlight(driver);
		btnSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * Sets the "From" date
	 * @param date - "From" date in MM/dd/yyyy format
	 */
	private void setFromDate(String date){
		if(date.equalsIgnoreCase("current")){
			date = DateTimeConversion.formatDate("MM/dd/yyyy", "0");
		}
		
		initialize();
		pageLoaded(txtFromDate);
		txtFromDate.safeSet(date);
	}
	
	/**
	 * Sets the "To" date
	 * @param date - "o" date in MM/dd/yyyy format
	 */
	private void setToDate(String date){
		if(date.equalsIgnoreCase("current")){
			date = DateTimeConversion.formatDate("MM/dd/yyyy", "0");
		}
		
		initialize();
		pageLoaded(txtToDate);
		txtToDate.safeSet(date);
	}
	
	/**
	 * Finds the column index for a given value
	 * @param value - Name of the column for which to search
	 * @return - index of the column
	 */
	private int columnNumberByHeader(String value){
		int columnNumber = 0;
		boolean headerFound = false;
		List<WebElement> headers = tblReservationListSummaryTable.findElements(By.xpath("thead/tr/th"));
		
		for(WebElement header : headers){
			new ElementImpl(header).highlight(driver);
			columnNumber++;
			if(header.getText().equalsIgnoreCase(value)){
				headerFound = true;
				break;
			}
		}
		TestReporter.assertTrue(headerFound, "The header ["+value+"] was not found.");
		return columnNumber;
	}
	
	/**
	 * Determines if any reservations were returned
	 * @return boolean true if reservations are returned, false otherwise
	 */
	private boolean reservationsReturned(){
		boolean reservationsFound = false;
		
		try{
			searchResultRows = tblReservationListSummaryTable.findElements(By.xpath("tbody/tr"));
			if(searchResultRows.size() > 0){
				reservationsFound = true;
			}
		}catch(Exception e){
			
		}
		
		return reservationsFound;
	}
	
	/**
	 * Searches for successful or failed bookings
	 * @param value - used to determine if successes or failures are to be searched for
	 */
	private void searchForSuccessOrFailure(String value){
		initialize();
		initialize();
		//Increment the count by 1 as the 
		int columnNumber = columnNumberByHeader(value);
		boolean valueFound = false;
		String tableContents;
		
		for(WebElement row : searchResultRows){
			WebElement link = null;
			new ElementImpl(row).highlight(driver);
			try{
				link = row.findElement(By.xpath("td["+columnNumber+"]/span/a"));
				String text = link.getText();
				if(!text.equalsIgnoreCase("0")){
					valueFound = true;
					new ElementImpl(link).jsClick(driver);
					new ProcessingYourRequest().WaitForProcessRequest(driver);
					break;
				}
			}catch(NoSuchElementException | NullPointerException e){
				
			}
		}
		
		initialize();
		if(value.equalsIgnoreCase("success")){
			pageLoaded(tblSuccesses);
			tableContents = tblSuccesses.getText();
		}else{
			TestReporter.assertTrue(valueFound, "No reservations were found to exists under the type ["+value+"].");
			pageLoaded(tblFailures);
			tableContents = tblFailures.getText();
		}
		TestReporter.log("The table for value ["+value+"] was found to contain the following:\n" + tableContents);
	}
}
