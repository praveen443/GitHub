package apps.lilo.roomingList;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.LinkImpl;
import core.interfaces.impl.TextboxImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * This class contains elements and element interactions for a given page of a
 * web application
 * 
 * @author Waightstill W very
 * @version 11/23/2015 Waightstill W very - original
 */
public class RoomingListPage {
	// *******************
	// Page Class Fields
	// *******************
	// Declare a local WebDriver to be used by class method
	private WebDriver driver;
	private Datatable datatable = new Datatable();
	private List<WebElement> rows;
	private String userName;
	private String time;
	private String resNum;

	// *********************
	// Page Class Elements
	// *********************
	@FindBy(id = "SearchRLT:createTemplate")
	private Textbox btnNew;
	
	@FindBy(id = "SearchRLT:templateName")
	private Textbox txtTemplateName;
	
	@FindBy(id = "SearchRLT:searchRoomingList")
	private Button btnSearch;
	
	@FindBy(id = "SearchRLT:templateDetailsTable:tb")
	private Webtable tblTemplateDetails;
	
	@FindBy(id = "SearchRLT:deleteTemplate")
	private Button btnDelete;
	
	@FindBy(id = "delConForm")
	private Element eleDeleteConfirmationForm;
	
	@FindBy(id = "newRLTForm:availableFieldTable:tb")
	private Webtable tblAvailableFields;
	
	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[2]/table/tbody/tr[2]/td/form/table/tbody/tr[1]/td[1]/table/tbody/tr[1]/td/table/tbody/tr[3]/td[2]/input[1]")
	private Button btnAddTo;
	
	@FindBy(css = "input[alt='Save']")
	private Button btnSave;
	
	@FindBy(id = "SearchRLT:manualRoomingList")
	private Button btnManualRoomingList;
	
	@FindBy(id = "autoRoomingExcelForm:summaryList")
	private Webtable tblRoomBookingFields;
	
	@FindBy(id = "autoRoomingExcelForm:validateID")
	private Button btnValidate;
	
	@FindBy(id = "autoRoomingExcelForm:summaryList:0:errorId")
	private Link lnkErrorMessage;
	
	@FindBy(id = "validateErrorForm:flatDetailViewList")
	private Element eleErrorForm;
	
	@FindBy(id = "validateErrorForm:closeButtonID")
	private Element eleClose;
	
	@FindBy(id = "autoRoomingExcelForm:bookID")
	private Button btnBook;
	
	@FindBy(id = "header1Form:userName")
	private Element eleUserName;
	
	@FindBy(id = "resListSummaryForm:reservationListSummaryTableID:tb")
	private Webtable tblReservationProcessingStatusResults;
	
	@FindBy(id="resListSummaryForm:refreshButtonID")
	private Button btnRefresh;
	
	@FindBy(id = "roomingListFailureForm:summaryByGroupTableID")
	private Webtable tblRoomLIstFailures;
	
	@FindBy(id = "roomingListErrorForm:flatDetailViewList:tb")
	private Webtable tblRoomingListError;
	
	@FindBy(id = "newRLTForm:templateNamePp1")
	private Textbox txtNewTemplateName;
	
	@FindBy(id = "roomingListErrorForm:closeButtonID")
	private Button btnErrorClose;
	
	@FindBy(id = "roomingListFailureForm:closePassedPopupButtonID")
	private Button btnFailuresClose;
	
	@FindBy(id="roomingListSuccessForm:flatDetailViewList")
	private Webtable tblRoomingLIstSuccess;
	
	@FindBy(id = "roomingListSuccessForm:closePassedPopupButtonID")
	private Button btnSuccessClose;
	
	// *********************
	// ** Build page area **
	// *********************
	/**
	 * @summary Constructor to initialize the page
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @param driver
	 */
	public RoomingListPage(WebDriver driver) {
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
	public RoomingListPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	/**
	 * @summary Method to determine if a page is loaded
	 * @author Waightstill W very
	 * @version 11/23/2015 Waightstill W very - original
	 * @return Boolean, true if the page is loaded, false otherwise
	 */
	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnNew);
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
	 * Deletes any existing templates with a given name and creates a rooming list template
	 * @param scenario - page class scenario to use
	 */
	public void createRoomingListTemplate(String scenario){
		datatable.setVirtualtablePage("RoomingList");
		datatable.setVirtualtableScenario(scenario);
		
		String templateName = datatable.getDataParameter("TemplateName");
		
		deleteExistingTemplate(templateName);
		
		clickNew();
		setTemplateName(templateName);
		addGroupBlockCodeField();
		clickSave();
	}
	
	/**
	 * Grabs the username to be used in later validations
	 */
	private void getUserName(){
		pageLoaded(eleUserName);
		eleUserName.highlight(driver);
		userName = eleUserName.getText();
	}
	
	/**
	 * Determines the number of templates that have a given name
	 * @return - template name for which to search
	 */
	private int getNumberOfTemplates(){
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try{
			rows = tblTemplateDetails.findElements(By.xpath("tr"));
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
			return rows.size();
		}catch(Exception e){
			driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
			return 0;
		}
	}
	
	/**
	 * Delete all templates with a given name
	 * @param templateName - template name for which to search
	 */
	private void deleteExistingTemplate(String templateName){
		initialize();
		pageLoaded(txtTemplateName);
		txtTemplateName.syncVisible(driver);
		txtTemplateName.safeSet(templateName);		
		
		btnSearch.highlight(driver);
		btnSearch.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		for(int row = 0; row < getNumberOfTemplates(); row++){
			WebElement nextRow = rows.get(row);
			if(nextRow.findElement(By.xpath("td[1]")).getText().contains(templateName)){
				nextRow.click();
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				clickDelete();
			}
		}
	}
	
	/**
	 * Click delete and confirms deletion
	 */
	private void clickDelete(){
		initialize();
		pageLoaded(btnDelete);
		btnDelete.jsClick(driver);
		
		Sleeper.sleep(1000);
		
		initialize();
		pageLoaded(eleDeleteConfirmationForm);
		eleDeleteConfirmationForm.highlight(driver);
		List<WebElement> buttons = eleDeleteConfirmationForm.findElements(By.cssSelector("input[id^='delConForm']"));
		for(WebElement button : buttons){
			if(button.getAttribute("value").equalsIgnoreCase("yes")){
				Element btnYes = new ElementImpl(button);
				btnYes.highlight(driver);
				btnYes.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				break;
			}
		}
	}
	
	/**
	 * Click the new template button
	 */
	private void clickNew(){
		initialize();
		pageLoaded(btnNew);
		btnNew.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * Add the Group Blick Code parameter from the list of available parameters
	 */
	private void addGroupBlockCodeField(){
		initialize();
		pageLoaded(tblAvailableFields);
		rows = tblAvailableFields.findElements(By.xpath("tr"));
		
		for(WebElement row : rows){
			if(row.getText().contains("Group Block Code")){
				row.click();
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				clickAddTo();
				break;
			}
		}
	}
	
	/**
	 * Sets the name for the new template
	 * @param templateName - name of the new template
	 */
	private void setTemplateName(String templateName){
		initialize();
		pageLoaded(txtNewTemplateName);
		txtNewTemplateName.safeSet(templateName);
	}
	
	/**
	 * Clicks the button to add a selected parameter
	 */
	private void clickAddTo(){
		initialize();
		pageLoaded(btnAddTo);
		btnAddTo.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * Clicks save to save the new template
	 */
	private void clickSave(){
		initialize();
		pageLoaded(btnSave);
		btnSave.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		time = DateTimeConversion.formatDate("dd-MM-YY hh:mm", 0);
		String[] timePart = time.split("-");
		timePart[1] = DateTimeConversion.getMonthAsString(Integer.parseInt(timePart[1])).substring(0, 3);
		time = timePart[0] + "-" + timePart[1] + "-" + timePart[2];
	}
	
	/**
	 * Uses a template to enter the Manual Rooming List page
	 * @param scenario - page class scenario to use
	 */
	public void useTemplateToEnterManualRoomingList(String scenario){
		datatable.setVirtualtablePage("RoomingList");
		datatable.setVirtualtableScenario(scenario);
		
		String templateName = datatable.getDataParameter("TemplateName");
		
		pageLoaded(tblTemplateDetails);
		rows = tblTemplateDetails.findElements(By.xpath("tr"));
		for(WebElement row : rows){
			if(row.getText().contains(templateName)){
				Element templateRow = new ElementImpl(row);
				templateRow.scrollIntoView(driver);
				templateRow.highlight(driver);
				templateRow.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				break;
			}
		}
		
		pageLoaded(btnManualRoomingList);
		btnManualRoomingList.jsClick(driver);
	}
	
	/**
	 * Sets the values for the booking, validates then books the reservation
	 * @param scenario - page class scenario to use
	 */
	public void bookUsingTemplate(String scenario){
		datatable.setVirtualtablePage("RoomingList");
		datatable.setVirtualtableScenario(scenario);
		
		String arrivalDate = datatable.getDataParameter("FromDate");
		String departureDate = datatable.getDataParameter("ToDate");
		String resortCode = datatable.getDataParameter("ResortCode");
		String guestFirstName = datatable.getDataParameter("FirstName");
		String guestLastName = datatable.getDataParameter("LastName");
		String roomTypeCode = datatable.getDataParameter("RoomTypeCode");
		String blockCode = datatable.getDataParameter("BlockCode");
		String packageCode = datatable.getDataParameter("PackageCode");
		String guestTitle = datatable.getDataParameter("Title");
		String guestSuffix = datatable.getDataParameter("Suffix");
//		String numberOfAdults = datatable.getDataParameter("NumAdults");
//		String numberOfChildren = datatable.getDataParameter("NumChild");
		String successOrFailure = datatable.getDataParameter("SuccessOrFailure");
		
		String[][] columnHeaders = {
				{"Guest Arrival Date", "Guest Departure Date","Guest-Primary Last Name",
					"Guest-Primary First Name", "Resort Code", "Package Code",
					"Room Type Code", "Guest-Primary Title", "Guest-Primary Suffix", 
					"Group Block Code"},
				{"", "", "", "", "", "", "", "", "", ""},
				{arrivalDate, departureDate, guestLastName, guestFirstName, resortCode,
					packageCode, roomTypeCode, guestTitle, guestSuffix, blockCode}
		};
		
		//Grab the position of each column header
		List<WebElement> headers = tblRoomBookingFields.findElements(By.xpath("thead/tr/th"));
		for(int header = 0; header < headers.size(); header++){
			for(int columnHeader = 0; columnHeader < columnHeaders[0].length; columnHeader++){
				if(headers.get(header).getText().contains(columnHeaders[0][columnHeader])){
					columnHeaders[1][columnHeader] = String.valueOf(header + 1);
				}
			}
		}
		
		//Handle date values for arrival and departure
		if(columnHeaders[2][0].equalsIgnoreCase("current")){
			columnHeaders[2][0] = DateTimeConversion.getDaysOut("0", "MM/dd/YYYY");
		}
		columnHeaders[2][1] = DateTimeConversion.getDaysOut(columnHeaders[2][1], "MM/dd/YYYY");
		
		for(int i = 0; i < columnHeaders[0].length; i++){
			System.out.println("Column: " + columnHeaders[0][i]);
			System.out.println("Column Index: " + columnHeaders[1][i]);
			System.out.println("Value: " + columnHeaders[2][i]);
		}
		
		//Enter the values using the column position of the header
		for(int values = 0; values < columnHeaders[0].length; values++){
			new TextboxImpl(tblRoomBookingFields.findElement(By.xpath("tbody/tr/td["+columnHeaders[1][values]+"]/input"))).safeSet(columnHeaders[2][values]);;
		}
		
		clickValidate();
		handleWarningsAndErrors(successOrFailure);
		clickBook();
	}
	
	/**
	 * Sets the values for the booking, validates then books the reservation
	 * @param scenario - page class scenario to use
	 */
	public void bookUsingTemplate(String environment, String groupType, String scenario){
		datatable.setVirtualtablePage("RoomingList");
		datatable.setVirtualtableScenario(scenario);

		time = DateTimeConversion.formatDate("dd-MM-YY hh:mm", 0);
		String[] timePart = time.split("-");
		timePart[1] = DateTimeConversion.getMonthAsString(Integer.parseInt(timePart[1])).substring(0, 3);
		time = timePart[0] + "-" + timePart[1] + "-" + timePart[2];
		
		String arrivalDate = datatable.getDataParameter("FromDate");
		String departureDate = datatable.getDataParameter("ToDate");
		String resortCode = datatable.getDataParameter("ResortCode");
		String guestFirstName = datatable.getDataParameter("FirstName");
		String guestLastName = datatable.getDataParameter("LastName");
		String roomTypeCode = datatable.getDataParameter("RoomTypeCode");
		String blockCode = datatable.getDataParameter("BlockCode");
		String packageCode = datatable.getDataParameter("PackageCode");
		String guestTitle = datatable.getDataParameter("Title");
		String guestSuffix = datatable.getDataParameter("Suffix");
		String numberOfAdults = datatable.getDataParameter("NumAdults");
		String numberOfChildren = datatable.getDataParameter("NumChild");
		
		if(resortCode.equalsIgnoreCase("RETRIEVE"))resortCode = retrieveGroupValue(groupType+" Group "+environment, "ResortCode");
		if(roomTypeCode.equalsIgnoreCase("RETRIEVE"))roomTypeCode = retrieveGroupValue(groupType+" Group "+environment, "RoomTypeCode");
		if(blockCode.equalsIgnoreCase("RETRIEVE"))blockCode = retrieveGroupValue(groupType+" Group "+environment, "GroupNumber");
		if(packageCode.equalsIgnoreCase("RETRIEVE"))packageCode = retrieveGroupValue(groupType+" Group "+environment, "PackageCode");
		if(numberOfChildren.isEmpty())numberOfChildren="0";		
		
		datatable.setVirtualtablePage("RoomingList");
		datatable.setVirtualtableScenario(scenario);
		
		String[][] columnHeaders = {
				{"Guest Arrival Date", "Guest Departure Date","Guest-Primary Last Name",
					"Guest-Primary First Name", "Resort Code", "Package Code",
					"Room Type Code", "Guest-Primary Title", "Guest-Primary Suffix", 
					"Group Block Code", "Guest Count Adult", "Guest Count Child"},
				{"", "", "", "", "", "", "", "", "", "", "", ""},
				{arrivalDate, departureDate, guestLastName, guestFirstName, resortCode,
					packageCode, roomTypeCode, guestTitle, guestSuffix, blockCode,
					numberOfAdults, numberOfChildren}
		};
		
		//Grab the position of each column header
		pageLoaded(tblRoomBookingFields);
		new PageLoaded().isDomComplete(driver);
		tblRoomBookingFields.syncVisible(driver);
		List<WebElement> headers = tblRoomBookingFields.findElements(By.xpath("thead/tr/th"));
		for(int header = 0; header < headers.size(); header++){
			for(int columnHeader = 0; columnHeader < columnHeaders[0].length; columnHeader++){
				if(headers.get(header).getText().contains(columnHeaders[0][columnHeader])){
					columnHeaders[1][columnHeader] = String.valueOf(header + 1);
				}
			}
		}
		
		//Handle date values for arrival and departure
		if(columnHeaders[2][0].equalsIgnoreCase("current")){
			columnHeaders[2][0] = DateTimeConversion.getDaysOut("0", "MM/dd/YYYY");
		}
		columnHeaders[2][1] = DateTimeConversion.getDaysOut(columnHeaders[2][1], "MM/dd/YYYY");
		
		for(int i = 0; i < columnHeaders[0].length; i++){
			System.out.println("Column: " + columnHeaders[0][i]);
			System.out.println("Column Index: " + columnHeaders[1][i]);
			System.out.println("Value: " + columnHeaders[2][i]);
		}
		
		//Enter the values using the column position of the header
		for(int values = 0; values < columnHeaders[0].length; values++){
			new TextboxImpl(tblRoomBookingFields.findElement(By.xpath("tbody/tr/td["+columnHeaders[1][values]+"]/input"))).safeSet(columnHeaders[2][values]);;
		}
		
		clickValidate();
		clickBook();
	}
	
	private String retrieveGroupValue(String scenario, String parameter){
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("GroupNumbers");
		datatable.setVirtualtableScenario(scenario);
		String value = datatable.getDataParameter(parameter);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		
		return value; 
	}
	
	/**
	 * Clicks the cliadate button for the booking
	 */
	private void clickValidate(){
		pageLoaded(btnValidate);
		btnValidate.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * Handles possible warnings/errors
	 * NOTE: This functionality was pulled directly from UFT
	 * @param value - success or failure, used to compare actual and expected behavior
	 */
	private void handleWarningsAndErrors(String value){
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		initialize();
		if(lnkErrorMessage.syncVisible(driver, 5, false)){
			lnkErrorMessage.highlight(driver);
			lnkErrorMessage.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			
			initialize();
			if(eleErrorForm.syncVisible(driver, 5, false)){
				eleClose.highlight(driver);
				eleClose.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				if(value.equalsIgnoreCase("success")){
					TestReporter.assertTrue(false, "The validation process failed when a successful process was expected.");
				}else if(value.equalsIgnoreCase("failure")){
					TestReporter.assertTrue(true, "The validation process failed as expected.");
				}
			}
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}
	
	/**
	 * Clicks the book button to book the reservation
	 */
	private void clickBook(){
		pageLoaded(btnBook);
		btnBook.highlight(driver);
		btnBook.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * Validates an expected error message
	 * @param scenario - page class scenario to use
	 */
	public void validateError(String scenario){
		datatable.setVirtualtablePage("RoomingList");
		datatable.setVirtualtableScenario(scenario);
		
		String arrivalDate = datatable.getDataParameter("FromDate");
		String departureDate = datatable.getDataParameter("ToDate");
		String resortCode = datatable.getDataParameter("ResortCode");
		String guestFirstName = datatable.getDataParameter("FirstName");
		String guestLastName = datatable.getDataParameter("LastName");
		String roomTypeCode = datatable.getDataParameter("RoomTypeCode");
		String errorMessage = datatable.getDataParameter("ErrorMessage");
		String groupCode = datatable.getDataParameter("BlockCode");
		
		String[][] columnHeaders = {
				{"Last Name", "First Name","Resort", "Room type", "Arrival", "Departure"},
				{"", "", "", "", "", "", "", "", "", ""},
				{guestLastName, guestFirstName, resortCode, roomTypeCode, arrivalDate, departureDate}
		};
		
		//Handle the date conversion
		if(columnHeaders[2][4].equalsIgnoreCase("current")){
			columnHeaders[2][4] = DateTimeConversion.getDaysOut("0", "MM dd, YYYY");
		}
		columnHeaders[2][5] = DateTimeConversion.getDaysOut(columnHeaders[2][5], "MM dd, YYYY");
		
		String[] dateParts = columnHeaders[2][4].split(" ");
		dateParts[0] = DateTimeConversion.getMonthAsString(Integer.parseInt(dateParts[0]));
		columnHeaders[2][4] = dateParts[0].substring(0, 3) + " " + dateParts[1] + " " + dateParts[2];
		
		dateParts = columnHeaders[2][5].split(" ");
		dateParts[0] = DateTimeConversion.getMonthAsString(Integer.parseInt(dateParts[0]));
		columnHeaders[2][5] = dateParts[0].substring(0, 3) + " " + dateParts[1] + " " + dateParts[2];
		
		/*
		 * Get the username and compile the expected result name NOTE: It is
		 * possible that a different name can exist for a given test. Therefore,
		 * two possible result names are generated and searched for
		 */
		getUserName();
		String userName1 = "RL-" + userName.replace(":", "").trim() + "-" + time;
		String userName2 = "RL-" + userName.replace(":", "").trim().replace("user", "use") + "-" + time;
		
		//Search for the first result name. If it is not found, use the second name
		int rowNum = -1;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try{
			rowNum = tblReservationProcessingStatusResults.getRowThatContainsCellText(driver, userName1, 1);	
		}catch(NoSuchElementException e){
			
		}
		if(rowNum == 0){
			rowNum = tblReservationProcessingStatusResults.getRowThatContainsCellText(driver, userName2, 1);
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		
		TestReporter.assertFalse(rowNum == 0, "The usernames ["+userName1+"] or ["+userName2+"] were not found in the table.");
		
		//Click refresh until the booking is "Complete"
		Element row = new ElementImpl(tblReservationProcessingStatusResults.findElement(By.xpath("tr["+rowNum+"]")));
		row.scrollIntoView(driver);
		do{
			initialize();
			pageLoaded(btnRefresh);
			btnRefresh.jsClick(driver);
			Sleeper.sleep(500);
			initialize();
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}while(!tblReservationProcessingStatusResults.findElement(By.xpath("tr["+rowNum+"]")).findElement(By.xpath("td["+3+"]")).getText().equalsIgnoreCase("Completed"));

		//Locate the failure link in the row and click it
		initialize();
		row = new ElementImpl(tblReservationProcessingStatusResults.findElement(By.xpath("tr["+rowNum+"]")));
		Link failure = new LinkImpl(row.findElement(By.xpath("td[6]/span/a")));
		failure.highlight(driver);
		failure.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		//Grab the position of each column header
		List<WebElement> headers = tblRoomLIstFailures.findElements(By.xpath("thead/tr/th"));
		for(int header = 0; header < headers.size(); header++){
			for(int columnHeader = 0; columnHeader < columnHeaders[0].length; columnHeader++){
				if(headers.get(header).getText().contains(columnHeaders[0][columnHeader])){
					columnHeaders[1][columnHeader] = String.valueOf(header + 1);
					break;
				}
			}
		}
		
		//Validate the values in each column
		List<WebElement> rows = tblRoomLIstFailures.findElements(By.xpath("tbody/tr"));
		//Validate the group code in the first row
		TestReporter.assertTrue(rows.get(0).getText().contains(groupCode), "The group code ["+groupCode+"] was not displayed on the failure form.");
		//Iterate through each column and validate the data
		for(int value = 0; value < columnHeaders.length; value++){
			boolean valueFound = false;
			if(rows.get(1).findElement(By.xpath("td["+columnHeaders[1][value]+"]")).getText().contains(columnHeaders[2][value])){
				valueFound = true;
			}
			TestReporter.assertTrue(valueFound, "The value ["+columnHeaders[value][2]+"] was found as expected.");
		}
		
		//Click on the error link and verify the error message
		Link lnkError = new LinkImpl(rows.get(1).findElement(By.xpath("td[3]/a")));
		lnkError.scrollIntoView(driver);
		lnkError.highlight(driver);
		lnkError.jsClick(driver);
		
		initialize();
		pageLoaded(tblRoomingListError);
		tblRoomingListError.scrollIntoView(driver);
		tblRoomingListError.syncVisible(driver);
		//Validate the error text
		TestReporter.assertTrue(tblRoomingListError.getText().equalsIgnoreCase(errorMessage), "The actual error message ["+tblRoomingListError.getText()+"] did not match that which was expected ["+errorMessage+"].");
		//Close the error
		closeError();
		//Close the failures
		closeFailures();
	}
	
	/**
	 * Validates an expected error message
	 * @param scenario - page class scenario to use
	 */
	public void validateSuccess(String scenario, String groupType, String environment){
		datatable.setVirtualtablePage("RoomingList");
		datatable.setVirtualtableScenario(scenario);
		
		String arrivalDate = datatable.getDataParameter("FromDate");
		String departureDate = datatable.getDataParameter("ToDate");
		String resortCode = datatable.getDataParameter("ResortCode");
		String guestFirstName = datatable.getDataParameter("FirstName");
		String guestLastName = datatable.getDataParameter("LastName");
		String roomTypeCode = datatable.getDataParameter("RoomTypeCode");
		String groupCode = datatable.getDataParameter("BlockCode");

		
		if(resortCode.equalsIgnoreCase("RETRIEVE"))resortCode = retrieveGroupValue(groupType+" Group "+environment, "ResortCode");
		if(roomTypeCode.equalsIgnoreCase("RETRIEVE"))roomTypeCode = retrieveGroupValue(groupType+" Group "+environment, "RoomTypeCode");
		if(groupCode.equalsIgnoreCase("RETRIEVE"))groupCode = retrieveGroupValue(groupType+" Group "+environment, "GroupNumber");

		String[][] columnHeaders = {
				{"Last Name", "First Name","Resort", "Room type", "Arrival", "Departure"},
				{"", "", "", "", "", "", "", "", "", ""},
				{guestLastName, guestFirstName, resortCode, roomTypeCode, arrivalDate, departureDate}
		};
		
		//Handle the date conversion
		if(columnHeaders[2][4].equalsIgnoreCase("current")){
			columnHeaders[2][4] = DateTimeConversion.getDaysOut("0", "MM dd, YYYY");
		}
		columnHeaders[2][5] = DateTimeConversion.getDaysOut(columnHeaders[2][5], "MM dd, YYYY");
		
		String[] dateParts = columnHeaders[2][4].split(" ");
		dateParts[0] = DateTimeConversion.getMonthAsString(Integer.parseInt(dateParts[0]));
		columnHeaders[2][4] = dateParts[0].substring(0, 3) + " " + dateParts[1] + " " + dateParts[2];
		
		dateParts = columnHeaders[2][5].split(" ");
		dateParts[0] = DateTimeConversion.getMonthAsString(Integer.parseInt(dateParts[0]));
		columnHeaders[2][5] = dateParts[0].substring(0, 3) + " " + dateParts[1] + " " + dateParts[2];

		String userName1 = "user" + "-" + time;
		String userName2 = "use" + "-" + time;
		
		pageLoaded(tblReservationProcessingStatusResults);
		new PageLoaded().isDomComplete(driver);
		tblReservationProcessingStatusResults.syncVisible(driver);
		
		//Search for the first result name. If it is not found, use the second name
		int rowNum = -1;
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		try{
			rowNum = tblReservationProcessingStatusResults.getRowThatContainsCellText(driver, userName1, 1);	
		}catch(NoSuchElementException e){
			
		}
		if(rowNum == 0){
			rowNum = tblReservationProcessingStatusResults.getRowThatContainsCellText(driver, userName2, 1);
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
		
		TestReporter.assertFalse(rowNum == 0, "The usernames ["+userName1+"] or ["+userName2+"] were not found in the table.");
		
		//Click refresh until the booking is "Complete"
		Element row = new ElementImpl(tblReservationProcessingStatusResults.findElement(By.xpath("tr["+rowNum+"]")));
		row.scrollIntoView(driver);
		do{
			pageLoaded(btnRefresh);
			btnRefresh.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
			initialize();
		}while(!tblReservationProcessingStatusResults.findElement(By.xpath("tr["+rowNum+"]")).findElement(By.xpath("td["+3+"]")).getText().equalsIgnoreCase("Completed"));

		//Locate the success link in the row and click it
		row = new ElementImpl(tblReservationProcessingStatusResults.findElement(By.xpath("tr["+rowNum+"]")));
		Link success = new LinkImpl(row.findElement(By.xpath("td[5]/span/a")));
		success.highlight(driver);
		success.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		
		//Grab the position of each column header
		List<WebElement> headers = tblRoomingLIstSuccess.findElements(By.xpath("thead/tr/th"));
		for(int header = 0; header < headers.size(); header++){
			for(int columnHeader = 0; columnHeader < columnHeaders[0].length; columnHeader++){
				if(headers.get(header).getText().contains(columnHeaders[0][columnHeader])){
					columnHeaders[1][columnHeader] = String.valueOf(header + 1);
					break;
				}
			}
		}
		
		//Validate the values in each column
		List<WebElement> rows = tblRoomingLIstSuccess.findElements(By.xpath("tbody/tr"));
		//Validate the group code in the first row
		TestReporter.assertTrue(rows.get(0).getText().contains(groupCode), "The group code ["+groupCode+"] was not displayed on the failure form.");
		//Iterate through each column and validate the data
		for(int value = 0; value < columnHeaders.length; value++){
			boolean valueFound = false;
			if(rows.get(0).findElement(By.xpath("td["+columnHeaders[1][value]+"]")).getText().contains(columnHeaders[2][value])){
				valueFound = true;
			}
			TestReporter.assertTrue(valueFound, "The value ["+columnHeaders[value][2]+"] was found as expected.");
		}
		
		setReservationnumber();
		
		//Close the Success window
		closeSuccess();
	}
	
	private void setReservationnumber(){
		int headerIndex = -1;
		int headerCounter = 1;
		List<WebElement> headers = tblRoomingLIstSuccess.findElements(By.xpath("thead/tr/th"));
		for(WebElement header : headers){
			if(header.getText().equalsIgnoreCase("Res Number")){
				headerIndex = headerCounter;
				break;
			}else{
				headerCounter++;
			}
		}
		
		List<WebElement> rows = tblRoomingLIstSuccess.findElements(By.xpath("tbody/tr"));
		resNum = rows.get(0).findElement(By.xpath("td["+headerIndex+"]")).getText();
	}
	
	public String getReservationNumber(){
		return resNum;
	}
	
	/**
	 * Clicks the close button on the error popup
	 */
	private void closeError(){
		initialize();
		pageLoaded(btnErrorClose);
		btnErrorClose.highlight(driver);
		btnErrorClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * Clicks close on the failures popup
	 */
	private void closeFailures(){
		pageLoaded(btnFailuresClose);
		btnFailuresClose.highlight(driver);
		btnFailuresClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
	
	/**
	 * Clicks close on the failures popup
	 */
	private void closeSuccess(){
		pageLoaded(btnSuccessClose);
		btnSuccessClose.highlight(driver);
		btnSuccessClose.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}
}

