package apps.lilo.createReservation;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.Webtable;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;

/**
 * 
 * @summary Contains the page methods & objects for the Resort Availbility page
 *          during the create reservation process
 * @version Created 9/15/2014
 * @author Jessica Marshall
 */

public class CreateReservationFindAvailibilityPage {
	// ************************************
	// *** Create Reservation Find Avail Page Fields ***
	// ************************************
	private int loopCount = 0;
	private int timeout = WebDriverSetup.getDefaultTestTimeout();
	private Datatable datatable = new Datatable();
	private WebDriver driver;

	// ************************************
	// *** Create Reservation Find Avail Page Elements ***
	// ************************************

	// ADA Checkbox
	@FindBy(id = "createResCriteriaForm:accessibleCheckBoxId")
	private Checkbox chkAccessible;

	// Resort name
	@FindBy(id = "createResCriteriaForm:singleSelectResortName")
	private Textbox txtResortName;

	// Add resorts
	@FindBy(id = "createResCriteriaForm:displayResortsPopupButton")
	private Button btnAddResorts;

	// Room type
	@FindBy(id = "createResCriteriaForm:singleSelectRoomTypeInput")
	private Textbox txtRoomType;

	// Arrival date
	@FindBy(id = "createResCriteriaForm:arrivalDateButtonInput")
	private Textbox txtArrivalDate;

	// Departure date
	@FindBy(id = "createResCriteriaForm:departureDateButtonInput")
	private Textbox txtDeptDate;

	// number of nights
	@FindBy(id = "createResCriteriaForm:noOfNightsInput")
	private Textbox txtNumberNights;

	// Number of adults
	@FindBy(id = "createResCriteriaForm:searchCritNoOfAdults")
	private Textbox txtNumberAdults;

	// Number of children
	@FindBy(id = "createResCriteriaForm:searchCritNoOfChildren")
	private Textbox txtNumberChildren;

	// Child age
	@FindBy(id = "createResCriteriaForm:childAges:0:childAgeInput")
	private Textbox txtChildAge;

	// number of rooms
	@FindBy(id = "createResCriteriaForm:noOfRoomsInput")
	private Textbox txtNumberRooms;

	// Affiliations
	@FindBy(id = "createResCriteriaForm:affiliationsSelect")
	private Listbox lstAffiliations;

	// Group name
	@FindBy(id = "createResCriteriaForm:createResgroupCodeName")
	private Textbox txtGroupName;

	// Add groups
	@FindBy(id = "createResCriteriaForm:searchGroupButton")
	private Button btnAddGroups;

	// Group code
	@FindBy(id = "createResCriteriaForm:createResgroupCodeID")
	private Textbox txtGroupCode;

	// RSR Checkbox
	@FindBy(id = "createResCriteriaForm:createResgroupCodeID")
	private Checkbox chkRSR;

	// Clear form
	@FindBy(id = "createResCriteriaForm:clearButton")
	private Button btnClear;

	// Find availability
	@FindBy(id = "defaultRHSPanelForm:searchOfferButton")
	private Button btnFind;

	// Cancel
	@FindBy(id = "defaultRHSPanelForm:cancelButton")
	private Button btnCancel;

	// Display Resort Popup button
	@FindBy(id = "createResCriteriaForm:displayResortsPopupButton")
	private Button btnDisplayResorts;

	// Available Resorts webtable
	@FindBy(id = "selectResortsForm:availableResortsDataTable")
	private Webtable tblAvailableResorts;

	// Selected Resorts webtable
	@FindBy(id = "selectResortsForm:selectedResortsDataTable")
	private Webtable tblSelectedResorts;

	// Select Resort Submit button
	@FindBy(id = "selectResortsForm:selectResortSubmit")
	private Link lnkSelectResortSubmit;

	// Grab the OK button
	@FindBy(id = "errorForm:okButtonId")
	private static Button btnOK;

	@FindBy(id = "PMSRErrorModalPanelHeader")
	private static Label lblDatePopupErrorHeader;

	@FindBy(css = ".scrollableContentXY > ul:nth-child(1) > li:nth-child(1)")
	private static Label lblDatePopupText;

	// *********************
	// ** Build page area **
	// *********************

	public CreateReservationFindAvailibilityPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	/*
	 * private CreateReservationFindAvailibilityPage initialize(WebDriver
	 * driver) { return ElementFactory.initElements(driver,
	 * CreateReservationFindAvailibilityPage.class); }
	 */

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, btnFind);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, element);
	}

	public boolean pageLoaded(Textbox textbox) {

		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver, txtNumberNights);
	}

	public CreateReservationFindAvailibilityPage initialize() {
		return ElementFactory.initElements(driver, this.getClass());
	}

	// *******************************************************
	// *** Create Reservation Find Avail Page Interactions ***
	// *******************************************************

	public void clickFind() {
		initialize();
		pageLoaded(btnFind);
		btnFind.syncVisible(driver);
		btnFind.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void clickCancel() {
		btnCancel.click();
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	public void enterSearchCriteria(String scenario) {
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("CreateResFindAvailibilityPage");
		datatable.setVirtualtableScenario(scenario);
		final String[] arrChildAges;
		int numChildren = Integer.parseInt(datatable.getDataParameter("NumChildren"));
		String ADA_Accessible = datatable.getDataParameter("ADA_Accessible");
		String RSRPackage = datatable.getDataParameter("RSRPackage");
		String resortName = datatable.getDataParameter("ResortName");
		String roomType = datatable.getDataParameter("RoomType");

		// Enter the search criteria
		// only check the ADA accessible if value is "ON"
		if (ADA_Accessible.equalsIgnoreCase("ON")) {
			chkAccessible.jsToggle(driver);
		}
		// Enter a different resort if not specified 'DEFAULT'
		if (!resortName.equalsIgnoreCase("DEFAULT")) {
			chooseResorts(resortName);
		}
		// Enter a different room type if not specified 'DEFAULT'
		if (!roomType.equalsIgnoreCase("DEFAULT")) {
			// TODO: after you click the > button for resorts, also pick by room
			// type?
		}

		// convertTodate is deprecated i have added getdaysout
		//String arrivalDate = convertDate.ConvertToDate(datatable.getDataParameter("DaysOut"));
		String arrivalDate = DateTimeConversion.getDaysOut(datatable.getDataParameter("DaysOut"), "MM/dd/yyyy");

		txtArrivalDate.highlight(driver);
		txtArrivalDate.safeSet(arrivalDate);
		Sleeper.sleep(2000);
		txtNumberNights.safeSet(datatable.getDataParameter("NumberNights"));
		Sleeper.sleep(3000);
		txtNumberAdults.safeSet(datatable.getDataParameter("NumAdults"));
		txtNumberChildren.safeSet(datatable.getDataParameter("NumChildren"));

		if (numChildren > 0) {
			txtChildAge.syncVisible(driver);
			arrChildAges = datatable.getDataParameter("ChildAges").split(";");
			List<WebElement> ElementList = driver
					.findElements(By.cssSelector("input[id*='createResCriteriaForm:childAges']"));
			for (WebElement element : ElementList) {
				element.sendKeys(arrChildAges[0]);
			}
		}

		txtNumberRooms.safeSet(datatable.getDataParameter("NumRooms"));
		lstAffiliations.select(datatable.getDataParameter("Affiliations"));
		txtGroupName.set(datatable.getDataParameter("GroupName"));
		txtGroupCode.set(datatable.getDataParameter("GroupCode"));
		if (RSRPackage.equalsIgnoreCase("ON")) {
			chkRSR.check();
		}
	}

	private void chooseResorts(String resortName) {
		int rowCount;

		btnClear.syncVisible(driver);
		btnClear.jsClick(driver);
		loopCount = 0;
		do {
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCount++, timeout,
					"The resort name textbox was not cleared after [" + String.valueOf(timeout) + "] seconds.");
			pageLoaded(txtResortName);
			initialize();
		} while (!txtResortName.getAttribute("title").isEmpty());

		btnDisplayResorts.jsClick(driver);
		loopCount = 0;
		do {
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCount++, timeout, "The available resorts list webtable was not displayed after ["
					+ String.valueOf(timeout) + "] seconds.");
			pageLoaded(tblAvailableResorts);
			initialize();
		} while (tblAvailableResorts == null);

		// row = tblAvailableResorts.getRowWithCellText(driver, resortName);
		// Element element = new
		// ElementImpl(driver.findElement(By.xpath("//table[@id =
		// 'selectResortsForm:availableResortsDataTable']/tbody/tr["+loopCount+"]/td[3]/input")));
		List<WebElement> list = tblAvailableResorts.findElements(By.tagName("tr"));
		rowCount = tblAvailableResorts.getRowCount();
		for (loopCount = 0; loopCount < rowCount; loopCount++) {
			if (list.get(loopCount).getText().toLowerCase().contains(resortName.toLowerCase())) {
				if (driver.findElement(By.xpath("//table[@id='selectResortsForm:availableResortsDataTable']/tbody/tr["
						+ String.valueOf(loopCount) + "]/td[1]")).getText().equalsIgnoreCase(resortName)) {
					Element element = new ElementImpl(list.get(loopCount).findElement(By.tagName("input")));
					element.scrollIntoView(driver);
					element.jsClick(driver);
					new ProcessingYourRequest().WaitForProcessRequest(driver);
					break;
				}
			}
		}

		loopCount = 0;
		do {
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCount++, timeout, "The selected resorts list webtable was not displayed after ["
					+ String.valueOf(timeout) + "] seconds.");
			pageLoaded(tblSelectedResorts);
			initialize();
		} while (tblSelectedResorts == null);
		Assert.assertEquals(tblSelectedResorts.getText().toLowerCase().contains(resortName.toLowerCase()), true,
				"The Selected Resorts webtable did not contain the resort name [" + resortName + "].");

		lnkSelectResortSubmit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		loopCount = 0;
		do {
			Sleeper.sleep(1000);
			Assert.assertNotEquals(loopCount++, timeout, "The resort name textbox was not populate with the resort ["
					+ resortName + " after [" + String.valueOf(timeout) + "] seconds.");
			pageLoaded(txtResortName);
			initialize();
		} while (txtResortName.getAttribute("title").isEmpty());
	}

	public void findOffers(String scenario) {

		pageLoaded();
		enterSearchCriteria(scenario);
		clickFind();
	}

	/**
	 * @summary : Method to Select the Resort based on the Resort name and
	 *          Submit it.
	 * @Created : Praveen Namburi
	 * @Date : 11/03/2015
	 * @return : NA
	 * @param :
	 *            resortName
	 */
	public void selectResorts(String resortName) {
		chooseResorts(resortName);
	}

	public void clickClear() {
		pageLoaded(btnClear);
		btnClear.click();
		//btnClear.jsClick(driver);

	}

	public void findAvailabilityWithDefaultSearchCriteria() {
		clickFind();
	}

	/**
	 * @summary Method to enter past date for arrivalDate field
	 * @author Marella Satish
	 * @version 11/24/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */

	public void setPastArrivalDate() {

		DateTimeConversion convertDate = new DateTimeConversion();
		String arrivalDate = convertDate.getYearsOut("-2", "MM/dd/yyyy");
		TestReporter.log("Invalid Arrival Date " + arrivalDate);
		txtArrivalDate.clear();
		txtArrivalDate.highlight(driver);
		txtArrivalDate.safeSet(arrivalDate);

	}

	public  static String setPastDeptDate() {

		String deptDate = DateTimeConversion.getDaysOut("-5", "MM/dd/yyyy");
		TestReporter.log("Dept Date " + deptDate);
		return deptDate;


	}
	public void setDeptDate(){
		txtDeptDate.clear();
		txtDeptDate.highlight(driver);
		txtDeptDate.safeSet(setPastDeptDate());

	}


	/**
	 * @summary Method to handle errors
	 * @author Marella Satish
	 * @version 11/24/2015 Marella Satish - original
	 * @param NA
	 * @return NA
	 */

	public void handleWarningsAndErrors(String ExpectedErrorMsg) {

		// Handle date error the occurs and close the error modal
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		TestReporter.log("Syncing for errors.");
		initialize();
		//		pageLoaded(lblDatePopupErrorHeader);
		boolean errorPopupFound = lblDatePopupErrorHeader.syncVisible(driver, 5, false);
		TestReporter.logStep("Error popup SyncVisible Status : " + errorPopupFound);
		if (errorPopupFound == true) {
			lblDatePopupText.highlight(driver);
			String ActualDateErrorText = lblDatePopupText.getText();
			boolean DateStatus = ActualDateErrorText.toLowerCase().contains(ExpectedErrorMsg.toLowerCase());
			TestReporter.assertTrue(DateStatus, "Actual String[" + ActualDateErrorText
					+ "] does not contains Expected string[" + ExpectedErrorMsg + "]");
			TestReporter.log("A date error popup was found with error message: " + lblDatePopupText.getText());
			btnOK.click();

		}else {
			TestReporter.log("Date entered is valid - No error popup displayed");
		}
		driver.manage().timeouts().implicitlyWait(WebDriverSetup.getDefaultTestTimeout(), TimeUnit.SECONDS);
	}


	// providing required values for input fields 
	public void numberAdults(String NumberAdults){
		txtNumberAdults.safeSet(NumberAdults);
	}
	public void numberChildrens(String NumberChildren){
		txtNumberChildren.safeSet(NumberChildren);
	}
	public void childAge(String ChildrenAges){
		txtChildAge.safeSet(ChildrenAges);
	}
	public void numberNights(String NumberNights){
		txtNumberNights.safeSet(NumberNights);
	}
	public void numberRooms(String NumberRooms){
		txtNumberRooms.safeSet(NumberRooms);
	}

	/**
	 * @summary Method to handle error messages
	 * @author Lalitha Banda
	 * @version 2/15/2016 Lalitha Banda - original
	 * @param NA
	 * @return NA
	 */
	public void handleErrorMessage_Negative(String errorMsg,String scenario){
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
		datatable.setVirtualtablePage("FindOffer_Negative_QuickBookInfo");
		datatable.setVirtualtableScenario(scenario);

		String NumberAdults = datatable.getDataParameter("NumAdults");
		String NumberChildren = datatable.getDataParameter("NumChildren");
		String NumberRooms = datatable.getDataParameter("NumRooms");
		String ChildrenAges = datatable.getDataParameter("ChildAge");
		String NumberNights = datatable.getDataParameter("NumNights");

		switch(errorMsg){
		case "Arrival Date can not be less than Current Date":
			setPastArrivalDate();
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "Departure Date must be greater than Arrival Date":
			setPastArrivalDate();
			setDeptDate();
			break;
		case "Please enter Arrival Date":
			txtArrivalDate.clear();
			setDeptDate();
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "Please enter Departure Date":
			setPastArrivalDate();
			pageLoaded(txtDeptDate);
			txtDeptDate.clear();
			txtNumberAdults.focus(driver);
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "is an incorrect value, please enter a numeric value in this field":
			if(scenario.contains("Itr6")){
				numberAdults(NumberAdults);
			}else if(scenario.contains("Itr8")){
				numberAdults(NumberAdults);
				numberChildrens(NumberChildren);
				txtNumberRooms.clear();
			}else if(scenario.contains("Itr13")){
				numberAdults(NumberAdults);
				txtNumberChildren.clear();
				txtNumberRooms.clear();
				txtNumberRooms.highlight(driver);
				numberRooms(NumberRooms);
			}else{
				numberNights(NumberNights);
				txtNumberAdults.clear();
				txtNumberChildren.clear();
				txtNumberRooms.clear();
			}
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "Please enter number of Adult Guests":
			txtNumberAdults.clear();
			numberAdults(NumberAdults);
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "Child Age must be between 1 to 17 years":
			if(scenario.contains("Itr9") || scenario.contains("Itr10")){
				numberAdults(NumberAdults);
				numberChildrens(NumberChildren);
				childAge(ChildrenAges);
			}
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "Please enter Child Ages":
			numberAdults(NumberAdults);
			numberChildrens(NumberChildren);
			childAge(ChildrenAges);
			pageLoaded();
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "IllegalArgumentException":
			numberAdults(NumberAdults);
			txtNumberChildren.clear();
			txtNumberRooms.highlight(driver);
			txtNumberRooms.clear();
			numberRooms(NumberRooms);
			findAvailabilityWithDefaultSearchCriteria();
			break;
		case "Please enter rooms less than 4" :
			txtNumberAdults.clear();
			numberAdults(NumberAdults);
			txtNumberChildren.clear();
			numberRooms(NumberRooms);
			findAvailabilityWithDefaultSearchCriteria();
		default:break;
		}
	}

}
