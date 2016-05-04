package apps.alc.Fulfillment;


import java.util.List;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import apps.alc.pleaseWait.PleaseWait;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Lalitha Banda
 * @version 01/28/2016 Lalitha Banda - original
 */
public class FulfillmentSearchTab{
	//*******************
	//	Page Class Fields
	//*******************
	//Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	//*********************
	//	Page Class Elements
	//*********************

	//go click on save button 
	@FindBy(id = "searchForReservationsForm:srchCommandButton")
	private Button btnSearch ;

	//go and select facility
	@FindBy(id = "searchForReservationsForm:selectedEventSearchReservationID2")
	private Listbox lstFacility;

	//go and select status
	@FindBy(id = "searchForReservationsForm:selectedSearchResStatusID")
	private Listbox lstStatus;

	//go and select status
	@FindBy(id = "searchForReservationsForm:searchForReservationsFromDateInput")
	private Textbox txtDateFrom;

	//go and select status
	@FindBy(id = "searchForReservationsForm:searchForReservationsToDateInput")
	private Textbox txtDateTo;

	//go and select facility
	@FindBy(id = "searchForReservationsForm:searchForReservationsTimeSelectOneMenuID")
	private Listbox lstTimeFrom;

	//go and select status
	@FindBy(id = "searchForReservationsForm:searchForReservationsTimeSelectOneMenuID1")
	private Listbox lstTimeTo;

	//go click on sav etable Assignments
	@FindBy(id = "assignTablesForm:saveTableAssignmentsButton")
	private Button btnSaveTable ;

	//
	@FindBy(id = "assignTablesForm:assignTablesDetailsTable:tb")
	private Element eleAssignTable ;
	
	//Success message
	@FindBy(id = ".//*[@id='assignTablesForm:assignTablesErrorPanel']/span")
	private Element eleSuccessMsg ;	
	
	//*********************
	//** Build page area **
	//*********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Lalitha Banda
	 * @version 1/29/2016	 YourNameHere - original
	 * @param  driver
	 */
	public FulfillmentSearchTab(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.ALC_MASTER_DATA_PATH);
	}

	/**
	 * @summary Method to initialize all proxy elements for this page
	 * @author Lalitha Banda
	 * @version 1/29/2016	 YourNameHere - original
	 * @return an instance of this page with the proxy elements initialized
	 */
	public  FulfillmentSearchTab initialize() {
		return ElementFactory.initElements(driver, this.getClass());	        
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Lalitha Banda
	 * @version 1/29/2016	 YourNameHere - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnSearch); 
	}  

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Lalitha Banda
	 * @version 1/29/2016	 YourNameHere - original
	 * @param element - Element to be used to determine if the page is loaded
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded(Element element){
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element); 
	}  

	//********************
	//	Page Class Methods
	//********************
	public void clickSearch(){
		initialize();
		pageLoaded(btnSearch);
		btnSearch.highlight(driver);
		btnSearch.click();
	}

	public void clickSaveTable(){
		initialize();
		pageLoaded(btnSaveTable);
		btnSaveTable.highlight(driver);
   	}

	/**
	 * 
	 * @summary Method to enter search criteria for a Fulfillment
	 * @version Created 1/29/2016
	 * @author Lalitha Banda
	 * @param scenario
	 *            - string, used to determine datatable row
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void enterFulfillmentSearch(String scenario) {
		datatable.setVirtualtablePage("Fulfillment");
		datatable.setVirtualtableScenario(scenario);
		pageLoaded();

		String facility = datatable.getDataParameter("Facility");
		String status = datatable.getDataParameter("Status");
		String date = datatable.getDataParameter("DaysOut");
		String numberOfNights = datatable.getDataParameter("NoOfNights");
		String timeFrom = datatable.getDataParameter("TimeFrom").replace("\"", "");
		String timeTo = datatable.getDataParameter("TimeTo").replace("\"", "");
		String search = datatable.getDataParameter("Search");

		int span = Integer.parseInt(date) + Integer.parseInt(numberOfNights);

		DateTimeConversion convertDate = new DateTimeConversion();
		String dateFrom = convertDate.ConvertToDateMMDDYY(date);
		String dateTo = convertDate.ConvertToDateMMDDYY(String.valueOf(span));

		lstFacility.select(facility);
		lstStatus.select(status);
		//dateTime
		dateTimeDay(dateFrom,dateTo,timeFrom,timeTo);
		//Search Reservation
		if(search.equalsIgnoreCase("true")){
			clickSearch();	
		}
	}

	/**
	 * 
	 * @summary Method to enter date, time and day of the week
	 * @version Created 1/29/2016
	 * @author Lalitha Banda
	 * @param dateFrom
	 *            - string, used to enter a start date for the search
	 * @param dateTo
	 *            - string, used to enter an end date for the search
	 * @param timeFrom
	 *            - string, used to select the earliest time the guest can
	 *            accommodate
	 * @param timeTo
	 *            - string, used to select the latest time the guest can
	 *            accommodate
	 * @param day
	 *            - string, preferred day of the week for the guest
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void dateTimeDay(String dateFrom, String dateTo, String timeFrom, String timeTo) {
		txtDateFrom.set(dateFrom);
		txtDateTo.set(dateTo);
		pageLoaded(lstTimeFrom);
		lstTimeFrom.select(timeFrom);
		pageLoaded(lstTimeTo);
		lstTimeTo.select(timeTo);
	}

	//Fulfillment Updates Tab //
	/**
	 * 
	 * @summary Method to enter date, time and day of the week
	 * @version Created 1/29/2016
	 * @author Lalitha Banda
	 * @param guestStatus
	 *            - string, used to select a guest status
	 * @param tableNo
	 *            - string, used to enter an table No
	 * @throws NA
	 * @return NA
	 * 
	 */

	public void modifyReservation(String ActualResNumber,String scenario){
		datatable.setVirtualtablePage("Fulfillment");
		datatable.setVirtualtableScenario(scenario);

		String guestStatus = datatable.getDataParameter("GuestStatus");
		String tableNo = datatable.getDataParameter("TableNo");
		String saveTable = datatable.getDataParameter("SaveTable");

		PleaseWait.WaitForPleaseWait(driver);
		btnSaveTable.highlight(driver);
		pageLoaded(btnSaveTable);
		eleAssignTable.highlight(driver);
		pageLoaded(eleAssignTable);
		
		// Reading the webTable 
		WebElement mytable = driver.findElement(By.id("assignTablesForm:assignTablesDetailsTable:tb"));
		// Reading table rows
		List<WebElement> rows_table = mytable.findElements(By.tagName("tr"));
		//Fetching rows
		for(int row =0;row<rows_table.size();row++){
			//Grabbing TR text 
			String ExpectedResNumber = rows_table.get(row).getText();
			if(ExpectedResNumber.contains(ActualResNumber)){
				// Assigning Guest status 
				new Select(driver.findElement(By.id("assignTablesForm:assignTablesDetailsTable:"+row+":gueststatus1"))).selectByVisibleText(guestStatus);
				// Assigning Table No
				driver.findElement(By.id("assignTablesForm:assignTablesDetailsTable:"+row+":table")).clear();
				driver.findElement(By.id("assignTablesForm:assignTablesDetailsTable:"+row+":table")).sendKeys(tableNo);
				break;
			}
		}
		
		if(saveTable.equalsIgnoreCase("true")){
			clickSaveTable();
		}
		
		//Print Success message 
		TestReporter.log(eleSuccessMsg.getText().toUpperCase());
	}
}

