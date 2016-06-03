package apps.alc.existingReservation;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * This class contains elements and element interactions for a given page of a web application
 * 
 * @author Stagliano, Dennis
 * @version 2/2/2016 Stagliano, Dennis - original
 */
public class ExistingReservationVenueSearchPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	

	// *********************
	// Page Class Elements
	// *********************
	
	@FindBy(id = "searchForReservationsByVenueForm:facilityOptions")
	private Listbox lstFacility;
	
	@FindBy(id = "searchForReservationsByVenueForm:j_id970")
	private Listbox lstProduct;
	
	@FindBy(id = "searchForReservationsByVenueForm:vsearchReservationByVenueDateInput")
	private Textbox txtDateFrom;
	
	@FindBy(id = "searchForReservationsByVenueForm:vsearchByVenueToDateInput")
	private Textbox txtDateTo;
	
	@FindBy(id = "searchForReservationsByVenueForm:vsearchTimeFrom")
	private Listbox lstTimeFrom;
	
	@FindBy(id = "searchForReservationsByVenueForm:vsearchTimeTo")
	private Listbox lstTimeTo;
	
	@FindBy(id = "searchForReservationsByVenueForm:venuereservationStatusOptions")
	private Listbox lstStatus;
	
	@FindBy(id = "searchForReservationsByVenueForm:findReservationByVenueButton")
	private Button btnFind;
	
	@FindBy(id = "ReservationResultByVenueForm:srchResByVenueReset")
	private Button btnClear;
	
	@FindBy(id = "ReservationResultByVenueForm:ReservationResultsByVenueTable:n")
	private Webtable tblVenueTableResults;
	
	@FindBy(xpath = ".//*[@id='ReservationResultByVenueForm:ReservationResultsByVenueTable:n']/tbody")
	private Webtable tblVenueTableResultsXpath;
	
	@FindBy(xpath = ".//*[@id='ReservationResultByVenueForm:ReservationResultsByVenuePanel_body']/table/tbody/tr[1]/td/span[2]")
	private Label lblNumberOfResults;
	
	@FindBy(id = "ReservationResultByVenueForm:ReservationResultsByVenueTable:confNoLabel")
	private Label lblShowMore;

	@FindBy(id = "ReservationResultByVenueForm:srchResByVenueView")
	private Button btnView;
	
	@FindBy(id = "existingReservationForm:reservationDetailsInfoPanel_header")
	private Label lblVenueSummaryHeader;
	
	@FindBy(id = "existingReservationForm:productList:0:productFacilityId")
	private Label lblVenueSummaryFacilityName;
	
	@FindBy(id = "existingReservationForm:productList:0:productTime")
	private Label lblVenueSummaryProductTime;
	
	@FindBy(id = "existingReservationForm:partySize")
	private Label lblVenueSummaryPartySize;
	
	@FindBy(id = "existingReservationForm:noOfAdults")
	private Label lblVenueSummaryAdults;
	
	@FindBy(id = "existingReservationForm:noOfNonAdults")
	private Label lblVenueSummaryChildren;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Stagliano, Dennis
	 * @version 2/2/2016 Stagliano, Dennis - original
	 * @param driver
	 */
	public ExistingReservationVenueSearchPage(WebDriver driver) {
	   this.driver = driver;
	   ElementFactory.initElements(driver, this);
	   datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}
	
	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Stagliano, Dennis
	 * @version 2/2/2016 Stagliano, Dennis - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public ExistingReservationVenueSearchPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}
	/**
	 * @summary Method to determine if a page is loaded
	 * @author Stagliano, Dennis
	 * @version 2/2/2016 Stagliano, Dennis - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}
	/**
	 * @summary Method to determine if a page is loaded
	 * @author Stagliano, Dennis
	 * @version 2/2/2016 Stagliano, Dennis - original
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
	 * @summary Method to enter search criteria for Venue
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 */
	public void searchReservationByVenue(String scenario){
		
		datatable.setVirtualtablePage("searchReservationByVenue");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();
		
		String facility = datatable.getDataParameter("Facility");
		String product = datatable.getDataParameter("Product");
		String dateFrom = datatable.getDataParameter("DaysOut");
		String dateTo = datatable.getDataParameter("NumberOfNights");
		String timeFrom = datatable.getDataParameter("TimeFrom");
		String timeTo = datatable.getDataParameter("TimeTo"); 
		String status = datatable.getDataParameter("Status"); 
			
		String form = "MM/dd/yy";
		String span = Integer.toString((Integer.parseInt(dateFrom) + Integer.parseInt(dateTo)));
		dateFrom = DateTimeConversion.getDaysOut(dateFrom, form);
		dateTo = DateTimeConversion.getDaysOut(span, form);
				
		setFacility(facility);
		setProduct(product);
		setStatus(status);
		setDateTime(dateFrom, dateTo, timeFrom, timeTo);
		
		clickFind();		
	}
	/**
	 * @summary Method to get the Number of record results to be used as a counter and verify
	 * 	that there is a result
	 * @author Stagliano, Dennis
	 * @version 2/2/2016 Stagliano, Dennis - original
	 * @return integer of number of records
	 * @param NA
	 */
	public int findNumberOfResults(){
		initialize();
		
		String numResults = "";
		numResults = lblNumberOfResults.getText();
		String formattedResult = numResults.substring(numResults.indexOf("[") +1, numResults.indexOf("]")).trim();
		int numberOfRecords = 0;		
		try {
			numberOfRecords = Integer.parseInt(formattedResult.trim());
		} catch (NumberFormatException e) {
			TestReporter.logFailure("NumberFormatException: " + e.getMessage());
		} 
		return numberOfRecords;	
   }
	
	/**
	 * @summary Method to select the proper result record that has the reservation number that matches
	 * the reservation number passed to this method
	 * @author Stagliano, Dennis
	 * @version 2/2/2016 Stagliano, Dennis - original
	 * @return NA
	 * @param int resultcount for a counter, newReservation Number to use for searching
	 */
	public void selectGuestSearchRecord(int resultsCount, String newResNumber) {
		//Loop through table using resultCount as a counter
		String ixpath;
		initialize();		
		System.out.println("newres " + newResNumber);
		for(int i=0; i<resultsCount; i++){
			ixpath = ".//*[@id='ReservationResultByVenueForm:ReservationResultsByVenueTable:"+i+":confNoValue']";
			WebElement firstSpan = driver.findElement(By.xpath(ixpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstSpan);
			String Val = (String) (((JavascriptExecutor) driver).executeScript("return arguments[0].innerHTML", firstSpan));
			Val.trim();
				if(Val.equals(newResNumber)){
					TestReporter.logStep("Found Record # " + i + " Value = " + Val + " newReservation = " + newResNumber);
					selectAvoidingStalenessButton(btnView);
					break;
				}
		}	
	}

	
	/**
	 * @summary Method to click Find Button
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickFind(){
		btnFind.syncVisible(driver);
		btnFind.highlight(driver);
		btnFind.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}
	/**
	 * @summary Method to click View Button
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickView(){
		pageLoaded();
		initialize();
		//btnClear.syncVisible(driver);
		btnView.click();
		PleaseWait.WaitForPleaseWait(driver);
	}
	/**
	 * @summary Method to clear the venue search
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clearSearch(){
		btnClear.syncVisible(driver);
		btnClear.highlight(driver);
		btnClear.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Method to return the time only from time passed to it
	 *          and remove the quotes "" from the VT
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param time from VT
	 * @throws NA
	 * @return formatted time without quotes
	 */
	public String getTimeOnly(String theTime) {
		String newTime = null;
		newTime = theTime.substring(1, theTime.length()-1);
		return newTime;
	}
	/**
	 * @summary Method to set the facility value
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param facility name passed to it
	 * @throws NA
	 * @return NA
	 */
	public void setFacility(String facility){	
		if(!facility.isEmpty()){
			lstFacility.syncVisible(driver);
			lstFacility.highlight(driver);
			lstFacility.select(facility);
		}
		
	}
	/**
	 * @summary Method to set the product value
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param product name passed to it
	 * @throws NA
	 * @return NA
	 */
	public void setProduct(String product){
		if(!product.isEmpty()){
			lstProduct.syncVisible(driver);
			lstProduct.highlight(driver);
			lstProduct.select(product);
		}
		
	}
	/**
	 * @summary Method to set the status value
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param status value passed to it
	 * @throws NA
	 * @return NA
	 */
	public void setStatus(String status){
		if(!status.isEmpty()){
			lstStatus.syncVisible(driver);
			lstStatus.highlight(driver);
			lstStatus.select(status);	
		}
			
	}
	/**
	 * @summary Method to set the dates and times with am/pm for search
	 * @version Created 2/2/2016
	 * @author Stagliano, Dennis
	 * @param date, timeFrom, timeTo from VT
	 * @throws NA
	 * @return NA
	 */
	public void setDateTime(String dateFrom, String dateTo, String timeFrom, String timeTo){
		pageLoaded();    
		txtDateFrom.safeSet(dateFrom);
		txtDateTo.safeSet(dateTo);
		
		String tfrom = getTimeOnly(timeFrom);
		String tTo = getTimeOnly(timeTo);
		
		selectAvoidingStaleness(lstTimeFrom, tfrom);
		selectAvoidingStaleness(lstTimeTo, tTo);
	}
	/**
	 * @summary Method to defeat element staleness for a Listbox
	 * @version Created 2/2/2016
	 * @author Avery, Waightstill
	 * @param listbox element, value of selection
	 * @throws Exception
	 * @return NA
	 */
	private void selectAvoidingStaleness(Listbox listbox, String value) {
		if (!value.isEmpty()) {
			int counter = 0;
			boolean success = false;
			int maxTries = 10;
			do {
				try {
					Sleeper.sleep(1000);
					counter++;
					listbox.select(value);
					success = true;
				} catch (Exception e) {
					pageLoaded(listbox);
				}
			} while (!success && counter < maxTries);
		}
	}
	/**
	 * @summary Method to defeat element staleness for a Button
	 * @version Created 2/2/2016
	 * @author Avery, Waightstill
	 * @param Button element
	 * @throws Exception
	 * @return NA
	 */
	private void selectAvoidingStalenessButton(Button button) {
	//	if (!value.isEmpty()) {
			int counter = 0;
			boolean success = false;
			int maxTries = 10;
			do {
				try {
					Sleeper.sleep(1000);
					counter++;
					button.click();
					success = true;
				} catch (Exception e) {
					pageLoaded(button);
				}
			} while (!success && counter < maxTries);
		//}
	}
	/**
	 * @summary Method to defeat element staleness for a Listbox Tab action
	 * @version Created 2/2/2016
	 * @author Avery, Waightstill
	 * @param listbox element, value of selection
	 * @throws Exception
	 * @return NA
	 */
	@SuppressWarnings("unused")
	private void selectAndTab(Listbox listbox, String value) {
		int counter = 0;
		int maxTries = 10;
		boolean success = false;
		do {
			try {
				Sleeper.sleep(500);
				counter++;
				listbox.select(value);
				success = true;
			} catch (Exception e) {
				pageLoaded(listbox);
			}
		} while (!success && counter < maxTries);
		TestReporter.assertTrue(counter < maxTries, "The value [" + value + "] was not able to be selected.");

		counter = 0;
		success = false;
		do {
			try {
				Sleeper.sleep(500);
				counter++; 
				listbox.sendKeys(Keys.TAB);
				success = true;
			} catch (Exception e) {
				pageLoaded(listbox);
			}
		} while (!success && counter < maxTries);
		TestReporter.assertTrue(counter < maxTries, "The element was not able to be tabbed away from.");
	}

	


	


}

