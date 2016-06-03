package apps.dreams.AccommodationWrapUp;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;
import utils.date.DateTimeConversion;
import apps.alc.pleaseWait.PleaseWait;
import apps.dreams.GuestSearchWindow.GuestSearch;
import core.FrameHandler;
import core.WindowHandler;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.RadioGroup;
import core.interfaces.Textbox;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;

/**
 * @summary Contains the methods & objects for the Dreams UI content frame for
 *          accommodation wrapup
 * @version Created 09/10/2014
 * @author Waightstill W. Avery
 */
public class AccommodationWrapUpContentFrame {
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	// *******************************************
	// *** Accommodation WrapUp Frame Elements ***
	// *******************************************
	@FindBy(id = "AddBut")
	private Button btnAddGuest;

	@FindBy(name = "b_CodedRemarks")
	private Button btnCodedRemarks;

	// 02/06/2016
	@FindBy(name = "profileTypeName")
	private Listbox lstProfileType;

	@FindBy(name = "selectedProfileAssocs")
	private Listbox lstSelectedProfile;

	@FindBy(name = "routings")
	private Listbox lstRoutings;

	@FindBy(xpath = "//*[@name= 'b_LookUp1']  [@value='Add']")
	private Button btnCodedRemarksAdd;

	@FindBy(xpath = "//*[@name= 'b_LookUp1']  [@value='Ok']")
	private Button btnCodedRemarksOk;

	@FindBy(xpath = ("//div[@id= 'CodedRemarks']/table/tbody/tr[3]/td[3]"))
	private Element eleCommentType;

	@FindBy(xpath=("//div[@id= 'CodedRemarks']/table/tbody/tr[3]/td[4]"))
	private  Element eleCommentDesc;

	@FindBy(name = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles[1].displayPartyId")
	private  Listbox lstGuest;

	@FindBy(name = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles[1].role")
	private  Listbox lStPartyRole;

	@FindBy(name = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles[1].purposeOfVisit")
	private  Listbox lstPurposeOfVisit ;

	@FindBy(name = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles["+1+"].displayPartyId")
	private  Listbox lstGuestOne;

	@FindBy(name = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles["+2+"].displayPartyId")
	private  Listbox lstGuestName ;

	@FindBy(xpath = "//*[@value='Tickets']")
	private RadioGroup chkTickets;

	@FindBy(xpath = "//*[@value='Both']")
	private RadioGroup chkBoth;

	@FindBy(xpath = "//*[@id='firstLayer']/table[10]/tbody/tr/td[3]/div/input")
	private Button btnAccommodationNew;

	@FindBy(name = "b_Yes")
	private Button btnYes;

	@FindBy(name = "Ok")
	private Button btnOk;

	@FindBy(name = "individualTO.lastName")
	private Textbox txtLastName;

	@FindBy(name = "individualTO.firstName")
	private Textbox txtFirstName;

	@FindBy(name = "selectedSalutation")
	private Listbox lstTitle;

	@FindBy(name = "addressTO.zipCode")
	private Textbox txtZip;

	@FindBy(name = "b_Edit")
	private Button btnEdit;

	@FindBy(partialLinkText = "Accommodation Summary")
	private Link lnkAccomSummary;

	@FindBy(id = "wdwNights")
	private Textbox txtWDWNights;

	@FindBy(id = "wdwDepartureDate")
	private Textbox txtWDWDepartureDate;

	@FindBy(id = "wdwArrivalDate")
	private Textbox txtWDWArrivalDate;

	@FindBy(partialLinkText = "No")
	private Link SharedNo;

	@FindBy(id = "LkUp1But")
	private Button btnSearch;

	@FindBy(name = "shareSearchCriteria.travelPlanSegmentId")
	private Textbox txtReservationNumber;

	@FindBy(name = "accommodationSearchList[0].selected")
	private Checkbox Checkselected;

	@FindBy(id="Add")
	private Button btnAdd;

	@FindBy(name="b_confirmAll")
	private Button btnConfirm;

	@FindBy(xpath=("//*[@id='Accommodation Summary']/table/tbody/tr/td/table[2]/tbody/tr/td"))
	private  Element eleReservation;

	@FindBy(xpath=("//*[@id='Accommodation Summary']/table/tbody/tr/td/table[4]/tbody/tr/td[2]"))
	private  Element eleResort;

	@FindBy(xpath=("//*[@id='Accommodation Summary']/table/tbody/tr/td/table[5]/tbody/tr[2]/td[4]/div"))
	private  Element eleBooked;

	@FindBy(xpath=("//*[@id='Accommodation Summary']/table/tbody/tr/td/table[3]/tbody/tr/td[2]"))
	private  Element txtAcommSummary;

	@FindBy(name = "individualTO.middleName")
	private Textbox txtMiddleName;

	@FindBy(id = "addressLine1")
	private Textbox txtAddressLine1;

	@FindBy(id = "city")
	private Textbox txtCity;

	@FindBy(id = "state")
	private Textbox txtState;

	@FindBy(name = "selectedPhoneLocatorType")
	private Listbox lstPhoneType;

	@FindBy(id = "number")
	private Textbox txtPhoneNumber;

	@FindBy(name = "selectedEmailLocatorType")
	private Listbox lstEmailType;

	@FindBy(id = "emailAddress")
	private Listbox lstEmailAddress;

	@FindBy(name = "emailTO.address")
	private Textbox txtEmailAddress;

	@FindBy(name = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].accomModificationContactName")
	private Textbox txtChangeName;

	@FindBy(name = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].accomModificationReason")
	private Listbox lstChangeReason;

	// *********************
	// ** Build page area **
	// *********************

	/**
	 * 
	 * @summary Constructor to initialize the frame
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public AccommodationWrapUpContentFrame(WebDriver driver) {
		this.driver = driver;
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	private AccommodationWrapUpContentFrame initialize() {
		FrameHandler.findAndSwitchToFrame(driver, "contentFrame");
		return ElementFactory.initElements(driver, this.getClass());
	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnAddGuest);
	}

	public boolean pageLoadedAccomSummary() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				lnkAccomSummary);
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	// ***********************************************
	// *** Accommodation WrapUp Frame Interactions ***
	// ***********************************************

	private void clickAddGuest() {
		pageLoaded(btnAddGuest);
		btnAddGuest.sendKeys(Keys.ENTER);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * 
	 * @summary Method to add guests to a reservation from the accommodation
	 *          wrap-up screen.
	 * @version Created 09/10/2014
	 * @author Waightstill W Avery
	 * @param scenario
	 *            - key for determining the party mix to add
	 * @throws Exception
	 *             surrounding datatable access
	 * @return NA
	 * 
	 */
	@SuppressWarnings("unused")
	public void addGuests(String scenario) throws Exception {
		pageLoaded();
		initialize();
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		int numberOfAdults = Integer.parseInt(datatable
				.getDataParameter("NumberOfAdults"));
		int numberOfChildren = Integer.parseInt(datatable
				.getDataParameter("NumberOfChildren"));
		int addGuestCounter;
		String guestSearchWindowName = "Guest Search";
		/*
		 * Adult data
		 */
		String[] adultTitle = datatable.getDataParameter("AdultTitle.").split(
				";");
		String[] adultFirstName = datatable.getDataParameter("AdultFirstName")
				.split(";");
		String[] adultMiddleName = datatable
				.getDataParameter("AdultMiddleName").split(";");
		String[] adultLastName = datatable.getDataParameter("AdultLastName")
				.split(";");
		String[] adultZip = datatable.getDataParameter("AdultZip").split(";");
		String[] adultAddress = datatable.getDataParameter("AdultAddress")
				.split(";");
		String[] adultCity = datatable.getDataParameter("AdultCity").split(";");
		String[] adultState = datatable.getDataParameter("AdultState").split(
				";");
		String[] adultPhoneType = datatable.getDataParameter("AdultPhoneType")
				.split(";");
		String[] adultPhoneNumber = datatable.getDataParameter(
				"AdultPhoneNumber").split(";");
		String[] adultEmailType = datatable.getDataParameter("AdultEmailType")
				.split(";");
		String[] adultEmail = datatable.getDataParameter("AdultEmail").split(
				";");
		/*
		 * Child data
		 */
		String[] childAge = datatable.getDataParameter("ChildAge").split(";");
		String[] childFirstName = datatable.getDataParameter("ChildFirstName")
				.split(";");
		String[] childMiddleName = datatable
				.getDataParameter("ChildMiddleName").split(";");
		String[] childLastName = datatable.getDataParameter("ChildLastName")
				.split(";");
		String[] childZip = datatable.getDataParameter("ChildZip").split(";");

		if (numberOfAdults > 0) {
			// Add adult guests
			for (addGuestCounter = 0; addGuestCounter < numberOfAdults; addGuestCounter++) {
				clickAddGuest();
				GuestSearchWindow.swapToApplication(driver);
				GuestSearchWindow guestSearchWindow = new GuestSearchWindow(
						driver);
				guestSearchWindow.addAdultGuest(adultTitle[addGuestCounter],
						adultFirstName[addGuestCounter],
						adultMiddleName[addGuestCounter],
						adultLastName[addGuestCounter],
						adultZip[addGuestCounter],
						adultAddress[addGuestCounter],
						adultCity[addGuestCounter],
						adultState[addGuestCounter],
						adultPhoneType[addGuestCounter],
						adultPhoneNumber[addGuestCounter],
						adultEmailType[addGuestCounter],
						adultEmail[addGuestCounter]);
			}
		} else {
			Reporter.log("The number of adults is zero. No adults will be added to this reservation");
		}
	}

	public void clickOkButton() {
		pageLoaded(btnOk);
		btnOk.highlight(driver);
		btnOk.click();
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 3);

	}

	/**
	 * @summary Method to click Coded Remarks button
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickCodedRemarks() {
		pageLoaded(btnCodedRemarks);
		btnCodedRemarks.highlight(driver);
		btnCodedRemarks.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Method to enter Coded Comments details
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */
	public void enterCodedCommentsInfo(String scenario) {

		datatable.setVirtualtablePage("CodedRemarks");
		datatable.setVirtualtableScenario(scenario);

		String commentType = datatable.getDataParameter("CommentType");
		String commentDesc = datatable.getDataParameter("CommentDescription");
		String routings = datatable.getDataParameter("Routings");
		String AddRoutings = datatable.getDataParameter("AddRoutings");

		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Coded Comments");
		pageLoaded(lstProfileType);
		lstProfileType.select(commentType);
		//PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		new PageLoaded().isDomComplete(driver);

		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Coded Comments");
		//PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		pageLoaded(lstSelectedProfile);
		lstSelectedProfile.select(commentDesc);
		//PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Coded Comments");

		if (AddRoutings.equalsIgnoreCase("true")) {
			pageLoaded(lstRoutings);
			lstRoutings.select(routings);
		}
		//PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Coded Comments");

		clickAdd();
		clickOk();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	public void clickonYesButton() {
		WindowHandler.waitUntilWindowExistsTitleContains(driver, "WARNING");
		clickYes();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation Entry and Management System");
	}

	public void clickYes() {
		pageLoaded(btnYes);
		btnYes.click();
	}

	/**
	 * @summary Method to click Add button in Coded Comments window
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickAdd() {
		// PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		try {
			WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
			pageLoaded(btnCodedRemarksAdd);
		} catch (Exception e) {
			Sleeper.sleep(2000);
			new PageLoaded().isDomComplete(driver);
			pageLoaded(btnCodedRemarksAdd);
		}
		btnCodedRemarksAdd.highlight(driver);
		pageLoaded(btnCodedRemarksAdd);
		btnCodedRemarksAdd.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Method to click OK button in Coded Comments window
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return NA
	 */
	public void clickOk() {
		// PleaseWait.WaitForPleaseWaitWithTimeout(driver);
		try {
			pageLoaded(btnCodedRemarksOk);
		} catch (Exception e) {
			Sleeper.sleep(2000);
			pageLoaded(btnCodedRemarksOk);
		}
		btnCodedRemarksOk.highlight(driver);
		pageLoaded(btnCodedRemarksOk);
		btnCodedRemarksOk.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Method to get the Comment Type
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return commentType as String
	 */
	public String getCommentType() {
		initialize();
		eleCommentType.highlight(driver);
		String commentType = eleCommentType.getText();
		TestReporter.log("Comment Type : " + commentType);
		return commentType;
	}

	/**
	 * @summary Method to get the Comment Description
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param NA
	 * @throws NA
	 * @return commentDesc as String
	 */
	public String getCommentDesc() {
		initialize();
		eleCommentDesc.highlight(driver);
		String commentDesc = eleCommentDesc.getText();
		TestReporter.log("Comment Description : " + commentDesc);
		return commentDesc;
	}

	/**
	 * @summary Method to validate the comment type and comment description
	 * @version Created 02/08/2016
	 * @author Marella Satish
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */
	public void validateCodeComments(String scenario) {

		datatable.setVirtualtablePage("CodedRemarks");
		datatable.setVirtualtableScenario(scenario);

		String expectedCommentType = datatable.getDataParameter("CommentType");
		String expectedCommentDesc = datatable
				.getDataParameter("CommentDescription");

		String actualCommentType = getCommentType();
		String actualCommentDesc = getCommentDesc();

		TestReporter.assertEquals(expectedCommentType, actualCommentType,
				"The expected comment type[ " + expectedCommentType
				+ " ] is not same as actual comment type[ "
				+ actualCommentType + " ]");
		TestReporter.assertEquals(expectedCommentDesc, actualCommentDesc,
				"The expected comment description[ " + expectedCommentDesc
				+ " ] is not same as actual comment description[ "
				+ actualCommentDesc + " ]");
	}

	public void clickEdit() {
		pageLoaded(btnEdit);
		btnEdit.highlight(driver);
		btnEdit.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	/**
	 * @summary Method to edit DOryAccommodationUI page
	 * @version Created 03/19/2016
	 * @author CHinagudaba Pawan
	 * @throws NA
	 * @return NA
	 */
	public void editAccommodationUI() {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"DoryAccommodationEditUI");
		chkBoth.highlight(driver);
		chkBoth.jsClick(driver);
		btnOk.jsClick(driver);
		PleaseWait.WaitForPleaseWait(driver);
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 1);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Disney Reservation");
	}

	/**
	 * @summary Method to click accommodation summary link
	 * @version Created 03/08/2016
	 * @author Chinagudaba Pawan
	 * @throws NA
	 * @return NA
	 */

	public void clickaccommodationSummary(){
		pageLoaded(lnkAccomSummary);
		lnkAccomSummary.syncVisible(driver);
		lnkAccomSummary.highlight(driver);
		lnkAccomSummary.click();
	}


	/**
	 * @summary Method to verifyArrivalDate & Deptdate and no.of nights
	 * @version Created 03/13/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable scenario
	 * @Param
	 * @throws NA
	 * @return NA
	 */
	public void clickAccommodatioNew() {
		pageLoaded(btnAccommodationNew);
		btnAccommodationNew.syncVisible(driver);
		btnAccommodationNew.highlight(driver);
		btnAccommodationNew.click();
	}

	public void verifyArrivalDate(String scenario,String actualAcomMessageText){
		datatable.setVirtualtablePage("DiscoveryTravelPlan");
		datatable.setVirtualtableScenario(scenario);
		initialize();
		/**
		 * @summary Method to verifyArrivalDate & Deptdate and no.of nights
		 * @version Created 03/13/2016
		 * @author Chinagudaba Pawan
		 * @param Datatable
		 *            scenario
		 * @Param
		 * @throws NA
		 * @return NA
		 */
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberNights");
		System.out.println(daysOut+":"+numberNights);
		TestReporter.assertTrue(actualAcomMessageText.contains(numberNights), "Number of nigths printed does not match with expected!!!");
		String ArrivalDate = DateTimeConversion.getDaysOut(daysOut,"MM/dd/yyyy");
		TestReporter.assertTrue(actualAcomMessageText.contains(ArrivalDate), "Arrival Date printed is not correct!!!");
		String DeptDate = DateTimeConversion.getDaysOut(numberNights,"MM/dd/yyyy");
		// added - lalitha
		if(!daysOut.equals("0")){
			String DeptDateNEW = DateTimeConversion.getDaysOut(String.valueOf(Integer.parseInt(numberNights)+Integer.parseInt(daysOut)),"MM/dd/yyyy");
			TestReporter.assertTrue(actualAcomMessageText.contains(DeptDateNEW), "Departure Date printed is not correct!!!");
		}else{
			TestReporter.assertTrue(actualAcomMessageText.contains(DeptDate), "Departure Date printed is not correct!!!");
		}
	}
	/**
	 * @summary Method to verifyPackageCode
	 * @version Created 03/13/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable scenario
	 * @param 
	 * @throws NA
	 * @return NA
	 */
	/*public void verifyPackageCode(String scenario, String actualPackageCode){
		datatable.setVirtualtablePage("DiscoveryPackages");
		datatable.setVirtualtableScenario(scenario);
		String daysOut = datatable.getDataParameter("DaysOut");
		String numberNights = datatable.getDataParameter("NumberNights");

		String exptectedPackageCode = datatable.getDataParameter("PackageCode");
		String ArrivalDate = DateTimeConversion.getDaysOut(daysOut,	"MM/dd/yyyy");
		String DeptDate = DateTimeConversion.getDaysOut(numberNights,"MM/dd/yyyy");

		TestReporter.assertTrue(actualPackageCode.contains(exptectedPackageCode), "Package Code printed is not correct!!!");
		TestReporter.assertTrue(actualAcomMessageText.contains(ArrivalDate),
				"Arrival Date printed is not correct!!!");
		TestReporter.assertTrue(actualAcomMessageText.contains(DeptDate),
				"Departure Date printed is not correct!!!");
		TestReporter.assertTrue(actualAcomMessageText.contains(numberNights),
				"Number of nigths printed does not match with expected!!!");

	}*/

	/**
	 * @summary Method to verifyResortName
	 * @version Created 03/13/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable scenario
	 * @throws NA
	 * @return NA
	 */

	public void verifyResortName(String scenario, String actualResort){
		datatable.setVirtualtablePage("DiscoveryAccommodations");
		datatable.setVirtualtableScenario(scenario);

		String expectedResortName = datatable.getDataParameter("Resort");

		TestReporter.assertTrue(actualResort.trim().contains(expectedResortName), "Resort name printed is not correct!!!");
	}

	/**
	 * @summary Method to verifyaccomodationSummary (resort,booked,reservation,
	 * 						arrival&dept dates,no.of nights,packagecode elements)
	 * @summary Method to verifyPackageCode
	 * @version Created 03/13/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable
	 *            scenario
	 * @param
	 * @throws NA
	 * @return NA
	 */
	public void verifyPackageCode(String scenario, String actualPackageCode) {
		datatable.setVirtualtablePage("DiscoveryPackages");
		datatable.setVirtualtableScenario(scenario);

		String exptectedPackageCode = datatable.getDataParameter("PackageCode");

		TestReporter.assertTrue(
				actualPackageCode.contains(exptectedPackageCode),
				"Package Code printed is not correct!!!");

	}


	/**
	 * @summary Method to verifyaccomodationSummary (resort,booked,reservation,
	 *          arrival&dept dates,no.of nights,packagecode elements)
	 * @version Created 03/13/2016
	 * @author Chinagudaba Pawan
	 * @throws NA
	 * @return NA
	 */

	public void verifyaccomodationSummary(String travelPlan,
			String accommodations, String packagecode, String reservationNumber) {
		
		
		String resort = eleResort.getText().trim();
		verifyResortName(accommodations, resort);

		String booked = eleBooked.getText();
		TestReporter.assertTrue(booked.equalsIgnoreCase("Booked"),
				"Element booked printed is not correct!!!");

		String actualreservationnumber = eleReservation.getText().trim();
		TestReporter.assertTrue(
				actualreservationnumber.contains(reservationNumber),
				"Reservation number printed is not correct!!!");

		String getActualAcomMessageText = txtAcommSummary.getText();
		String AcommSummary = txtAcommSummary.getText();
		TestReporter.log("Accommodation Summary Text: " + AcommSummary);

		// verifying Arrivaldate,Deptdate and no of nights
		verifyArrivalDate(travelPlan, getActualAcomMessageText);

		// verifying the Packagecode
		verifyPackageCode(packagecode, getActualAcomMessageText);
	}
	
	

	public void verifyaccomodationSummaryNoPackage(String travelPlan,
			String accommodations, String reservationNumber) {
		
		String resort = eleResort.getText().trim();
		verifyResortName(accommodations, resort);

		String booked = eleBooked.getText();
		TestReporter.assertTrue(booked.equalsIgnoreCase("Booked"),
				"Element booked printed is not correct!!!");

		String actualreservationnumber = eleReservation.getText().trim();
		TestReporter.assertTrue(
				actualreservationnumber.contains(reservationNumber),
				"Reservation number printed is not correct!!!");

		String getActualAcomMessageText = txtAcommSummary.getText();
		String AcommSummary = txtAcommSummary.getText();
		TestReporter.log("Accommodation Summary Text: " + AcommSummary);

		// verifying Arrivaldate,Deptdate and no of nights
		verifyArrivalDate(travelPlan, getActualAcomMessageText);

		
	}

	
	/**
	 * @summary Method to add Guest button
	 * @version Created 02/29/2016
	 * @author Sowmya ch
	 * @param N
	 *            /A
	 * @throws NA
	 * @return NA
	 */


	// clicking on addGuests
	public void click_AddGuest() {
		clickAddGuest();
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler
		.waitUntilWindowExistsTitleContains(driver, "Guest Search");
	}
	/**
	 * @summary Method to Select Guest list
	 * @version Created 02/29/2016
	 * @author Sowmya ch
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */

	public void selectGuestDetails(String scenario) {
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		String guest = datatable.getDataParameter("GuestName");
		String partyRole = datatable.getDataParameter("PartyRole");
		String purposeOfVisit = datatable.getDataParameter("purposeofvisit");

		pageLoaded(lstGuest);
		lstGuest.select(guest);
		lStPartyRole.select(partyRole);
		lstPurposeOfVisit.select(purposeOfVisit);
	}

	/**
	 * @summary Method to add multiple guest information 
	 * @version Created 03/11/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable scenario
	 * @summary Method to add multiple guest information
	 * @version Created 03/11/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */
	public void addGuestInformation(String scenario){
		pageLoaded();
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		int numberOfAdults = Integer.parseInt(datatable.getDataParameter("NumberOfAdults"));
		//		int numberOfChildren = Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		// int numberOfChildren =
		// Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		int addGuestCounter;
		// String guestSearchWindowName = "Guest Search";
		/*
		 * Adult data
		 */
		String[] adultTitle = datatable.getDataParameter("AdultTitle").split(
				";");
		String[] adultFirstName = datatable.getDataParameter("AdultFirstName")
				.split(";");
		String[] adultMiddleName = datatable
				.getDataParameter("AdultMiddleName").split(";");
		String[] adultLastName = datatable.getDataParameter("AdultLastName")
				.split(";");
		String[] adultZip = datatable.getDataParameter("AdultZip").split(";");
		String[] adultAddress = datatable.getDataParameter("AdultAddress")
				.split(";");
		String[] adultCity = datatable.getDataParameter("AdultCity").split(";");
		String[] adultState = datatable.getDataParameter("AdultState").split(
				";");
		String[] adultPhoneType = datatable.getDataParameter("AdultPhoneType")
				.split(";");
		String[] adultPhoneNumber = datatable.getDataParameter(
				"AdultPhoneNumber").split(";");
		String[] adultEmailType = datatable.getDataParameter("AdultEmailType")
				.split(";");
		String[] adultEmail = datatable.getDataParameter("AdultEmail").split(
				";");
		/*
		 * Child data
		 */
		//		String[] childAge = datatable.getDataParameter("ChildAge").split(";");
		//		String[] childFirstName = datatable.getDataParameter("ChildFirstName").split(";");
		//		String[] childMiddleName = datatable.getDataParameter("ChildMiddleName").split(";");
		//		String[] childLastName = datatable.getDataParameter("ChildLastName").split(";");
		//		String[] childZip = datatable.getDataParameter("ChildZip").split(";");
		// String[] childAge =
		// datatable.getDataParameter("ChildAge").split(";");
		// String[] childFirstName =
		// datatable.getDataParameter("ChildFirstName").split(";");
		// String[] childMiddleName =
		// datatable.getDataParameter("ChildMiddleName").split(";");
		// String[] childLastName =
		// datatable.getDataParameter("ChildLastName").split(";");
		// String[] childZip =
		// datatable.getDataParameter("ChildZip").split(";");
		System.out.println(numberOfAdults);

		if (numberOfAdults > 0) {

			// Add adult guests
			for (addGuestCounter = 0; addGuestCounter < numberOfAdults; addGuestCounter++) {

				click_AddGuest();
				GuestSearch guestSearch = new GuestSearch(driver);
				TestReporter.assertTrue(guestSearch.pageLoaded(),
						"Guest search window is not loaded.");
				TestReporter.logStep("Adding guest information");
				guestSearch.clickNew();

				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver,"Party");
				WindowHandler.waitUntilWindowExistsTitleContains(driver,
						"Party");

				txtLastName.set(adultLastName[addGuestCounter]);
				try {
					txtMiddleName.set(adultMiddleName[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				txtFirstName.set(adultFirstName[addGuestCounter]);
				lstTitle.select(adultTitle[addGuestCounter]);
				try{txtAddressLine1.set(adultAddress[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}
				try{txtZip.set(adultZip[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}
				try{txtCity.set(adultCity[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}
				try{txtState.set(adultState[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}
				try{txtPhoneNumber.set(adultPhoneNumber[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}
				try{lstPhoneType.select(adultPhoneType[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}
				try{lstEmailType.select(adultEmailType[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}
				try{txtEmailAddress.set(adultEmail[addGuestCounter]);}catch(ArrayIndexOutOfBoundsException e){}				

				/*clickOkButton();
				clickonYesButton();		*/			
				try {
					txtAddressLine1.set(adultAddress[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					txtZip.set(adultZip[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					txtCity.set(adultCity[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					txtState.set(adultState[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					txtPhoneNumber.set(adultPhoneNumber[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					lstPhoneType.select(adultPhoneType[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					lstEmailType.select(adultEmailType[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				try {
					txtEmailAddress.set(adultEmail[addGuestCounter]);
				} catch (ArrayIndexOutOfBoundsException e) {
				}

				clickOkButton();
				clickonYesButton();
			}
		} else {
			Reporter.log("The number of adults is zero. No adults will be added to this reservation");
		}
	}




	public void add_GuestInformation(String scenario) {
		pageLoaded();
		initialize();
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		int numberOfAdults = Integer.parseInt(datatable.getDataParameter("NumberOfAdults"));
//		int numberOfChildren = Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		int addGuestCounter;
		//String guestSearchWindowName = "Guest Search";
		/*
		 * Adult data
		 */
		String[] adultTitle = datatable.getDataParameter("AdultTitle").split(";");
		String[] adultFirstName = datatable.getDataParameter("AdultFirstName").split(";");
//		String[] adultMiddleName = datatable.getDataParameter("AdultMiddleName").split(";");
		String[] adultLastName = datatable.getDataParameter("AdultLastName").split(";");
		String[] adultZip = datatable.getDataParameter("AdultZip").split(";");
//		String[] adultAddress = datatable.getDataParameter("AdultAddress").split(";");
//		String[] adultCity = datatable.getDataParameter("AdultCity").split(";");
//		String[] adultState = datatable.getDataParameter("AdultState").split(";");
//		String[] adultPhoneType = datatable.getDataParameter("AdultPhoneType").split(";");
//		String[] adultPhoneNumber = datatable.getDataParameter("AdultPhoneNumber").split(";");
//		String[] adultEmailType = datatable.getDataParameter("AdultEmailType").split(";");
//		String[] adultEmail = datatable.getDataParameter("AdultEmail").split(";");
		/*
		 * Child data
		 */
//		String[] childAge = datatable.getDataParameter("ChildAge").split(";");
//		String[] childFirstName = datatable.getDataParameter("ChildFirstName").split(";");
//		String[] childMiddleName = datatable.getDataParameter("ChildMiddleName").split(";");
//		String[] childLastName = datatable.getDataParameter("ChildLastName").split(";");
//		String[] childZip = datatable.getDataParameter("ChildZip").split(";");
		System.out.println(numberOfAdults);

		if (numberOfAdults > 0) {

			// Add adult guests
			for (addGuestCounter = 0; addGuestCounter < numberOfAdults; addGuestCounter++) {

				click_AddGuest();
				GuestSearch guestSearch = new GuestSearch(driver);
				TestReporter.assertTrue(guestSearch.pageLoaded(),
						"Guest search window is not loaded.");
				TestReporter.logStep("Adding guest information");
				guestSearch.clickNew();

				WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
				WindowHandler.waitUntilWindowExistsTitleContains(driver,"Party");
//				GuestSearchWindow GuestSearchWindow = new GuestSearchWindow(driver);

				txtLastName.set(adultLastName[addGuestCounter]);
				txtFirstName.set(adultFirstName[addGuestCounter]);
				lstTitle.select(adultTitle[addGuestCounter]);
				txtZip.set(adultZip[addGuestCounter]);


				clickOkButton();
				clickonYesButton();

			}
		} else {
			Reporter.log("The number of adults is zero. No adults will be added to this reservation");
		}
	}
	/**
	 * @summary Method to Select Guest list if more than one guest need to add
	 * @version Created 03/12/2016
	 * @author Chinagudaba Pawan
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */
	public void selectGuestInfo(String scenario) {
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		int numberOfAdults = Integer.parseInt(datatable.getDataParameter("NumberOfAdults"));
		//		int numberOfChildren = Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		// int numberOfChildren =
		// Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		int addGuestCounter;

		String[] adultFirstName = datatable.getDataParameter("AdultFirstName").split(";");
		String[] adultLastName = datatable.getDataParameter("AdultLastName").split(";");
		String[] partyRole = datatable.getDataParameter("AdultRole").split(";");
		String[] purposeOfVisit = datatable.getDataParameter("AdultPurposeOfVisit").split(";");


		if (numberOfAdults > 0) {
			// Add adult guests
			for (addGuestCounter = 0; addGuestCounter < numberOfAdults; addGuestCounter++) {
				String GuestName = adultFirstName[addGuestCounter] + " "
						+ adultLastName[addGuestCounter];
				String actualGuestName = lstGuestName.getText();
				if (addGuestCounter == 0) {
					lstGuestOne.select(GuestName);
				} else {
					lstGuestName.select(GuestName);
					verifyGuestWebList(scenario,actualGuestName);
					lStPartyRole.select(partyRole[addGuestCounter]);
					lstPurposeOfVisit.select(purposeOfVisit[addGuestCounter]);

				}	
				verifyGuestWebList(scenario, actualGuestName);
				lStPartyRole.select(partyRole[addGuestCounter]);
				lstPurposeOfVisit.select(purposeOfVisit[addGuestCounter]);

			}
		} else {
			Reporter.log("The number of adults is zero. No guests will be added to this reservation");
		}
	}



	public void verifyGuestWebList(String scenario, String actualGuestName) {
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		//		int numberOfAdults = Integer.parseInt(datatable.getDataParameter("NumberOfAdults"));
		//		int numberOfChildren = Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		// int numberOfAdults =
		// Integer.parseInt(datatable.getDataParameter("NumberOfAdults"));
		// int numberOfChildren =
		// Integer.parseInt(datatable.getDataParameter("NumberOfChildren"));
		int addGuestCounter = 0;

		String[] adultFirstName = datatable.getDataParameter("AdultFirstName").split(";");
		String[] adultLastName = datatable.getDataParameter("AdultLastName").split(";");
		//		String[] partyRole = datatable.getDataParameter("AdultRole").split(";");
		//		String[] purposeOfVisit = datatable.getDataParameter("AdultPurposeOfVisit").split(";");
		String GuestName = adultFirstName[addGuestCounter] +" "+ adultLastName[addGuestCounter];

		// String[] partyRole =
		// datatable.getDataParameter("AdultRole").split(";");
		// String[] purposeOfVisit =
		// datatable.getDataParameter("AdultPurposeOfVisit").split(";");

		TestReporter.assertTrue(actualGuestName.contains(GuestName),
				"Guest name printed is not correct!!!");

	}



	/**
	 * @summary Method to Select multiple Guest list
	 * @version Created 03/15/2016
	 * @author Lalitha Banda
	 * @param Datatable
	 *            scenario
	 * @throws NA
	 * @return NA
	 */
	//Selecting Guest details
	public void selectMultipleGuestDetails(String scenario) {
		datatable.setVirtualtablePage("AccomWrapUp");
		datatable.setVirtualtableScenario(scenario);

		String[] guestArray = datatable.getDataParameter("GuestName")
				.split(";");
		String[] partyRoleArray = datatable.getDataParameter("PartyRole")
				.split(";");
		String[] purposeOfVisitArray = datatable.getDataParameter(
				"purposeofvisit").split(";");
		pageLoaded();

		for (int i = 0; i < guestArray.length;) {
			String nameOfguest = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles["
					+ i + "].displayPartyId";
			Select guestName = new Select(driver.findElement(By
					.name(nameOfguest)));
			guestName.selectByVisibleText(guestArray[i]);

			// Verify Guest Name
			TestReporter.assertTrue(guestName.getFirstSelectedOption()
					.getText().equalsIgnoreCase(guestArray[i]),
					"Guest Name doesnt verified with Actual value : "
							+ guestName.getFirstSelectedOption().getText()
							+ "Expected Value :" + guestArray[i]);

			for (int j = 0; j < partyRoleArray.length;) {
				String nameOfPartyRole = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles["
						+ j + "].role";
				Select partyrole = new Select(driver.findElement(By
						.name(nameOfPartyRole)));
				partyrole.selectByVisibleText(partyRoleArray[j]);

				// Verify Role Name
				TestReporter.assertTrue(partyrole.getFirstSelectedOption()
						.getText().equalsIgnoreCase(partyRoleArray[j]),
						"Role Name doesnt verified with Actual value : "
								+ partyrole.getFirstSelectedOption().getText()
								+ "Expected Value :" + partyRoleArray[j]);

				for (int k = 0; k < purposeOfVisitArray.length;) {
					String nameOfPurposeofVisit = "travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles["
							+ k + "].purposeOfVisit";
					Select purposeOfvisit = new Select(driver.findElement(By
							.name(nameOfPurposeofVisit)));
					purposeOfvisit.selectByVisibleText(purposeOfVisitArray[k]);

					// Verify Purpose Of Visit
					TestReporter.assertTrue(purposeOfvisit
							.getFirstSelectedOption().getText()
							.equalsIgnoreCase(purposeOfVisitArray[k]),
							"purpose Of Visit doesnt verified with Actual value : "
									+ purposeOfvisit.getFirstSelectedOption()
									.getText() + "Expected Value :"
									+ purposeOfVisitArray[k]);
					k++;
				}
				j++;
			}
			i++;
		}
	}

	/**
	 * @summary Method to Select Shared number link in Accommodation Lookup page
	 * @version Created 02/29/2016
	 * @author Sowmya ch
	 * @param  NA
	 * @throws NA
	 * @return NA
	 */

	// Method to click SharedNUmber Link
	public void clickSharedNoLink() {
		pageLoaded(SharedNo);
		SharedNo.highlight(driver);
		SharedNo.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	// Method to click ShareSearch button

	public void clickShareSearchButton() {
		pageLoaded(btnSearch);
		btnSearch.highlight(driver);
		btnSearch.click();
		PleaseWait.WaitForPleaseWait(driver);
	}

	// method to set Reservation number
	public void SetReservationNum(String reservationNum) {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Accommodation Lookup");
		txtReservationNumber.highlight(driver);
		txtReservationNumber.set(reservationNum);
	}

	// method to click 'SelectReservation' checkbox on

	public void SelectReservationCheckBox() {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Accommodation Lookup");
		Checkselected.highlight(driver);
		Checkselected.check();
	}

	// method to click add to sharelist button
	public void AddToShareList() {
		btnAdd.highlight(driver);
		btnAdd.click();
	}

	// Method to click confirm all button
	public void ClickConfirmAll() {
		WindowHandler.waitUntilNumberOfWindowsAre(driver, 2);
		WindowHandler.waitUntilWindowExistsTitleContains(driver,
				"Accommodation Lookup");
		btnConfirm.highlight(driver);
		btnConfirm.click();
	}

	/**
	 * @summary Method to Click on New button of KTTW Tickets
	 * @version Created 03/23/2016
	 * @author Sabitha Adama
	 */
	public void setModifiedNameAndTickets(String scenario){
		PleaseWait.WaitForPleaseWait(driver);
		datatable.setVirtualtablePage("Tickets");
		datatable.setVirtualtableScenario(scenario);
		int numberOfAdults = Integer.parseInt(datatable.getDataParameter("NumberOfAdults"));
		System.out.println("Number of adults: "+numberOfAdults);
		int SelectTickets;
		String Change_Name = datatable.getDataParameter("ChangeName");
		String Change_Reason = datatable
				.getDataParameter("ChangeReason");
		String[] Ticktes = datatable.getDataParameter("SelectTickets").split(";");

		initialize();
		txtChangeName.highlight(driver);
		txtChangeName.safeSet(Change_Name);
		lstChangeReason.select(Change_Reason);

		if (numberOfAdults > 0) {
			// Add tickets
			for (SelectTickets = 0; SelectTickets < numberOfAdults; SelectTickets++) {
				String locator="travelPlanTO.travelPlanSegmentTOList[0].accommodationTOList[0].roomType.partyRoles["+SelectTickets+"].displayTicketCode";	
				Select ticketValue  = new Select (driver.findElement(By.name(locator)));
				ticketValue.selectByVisibleText(Ticktes[SelectTickets]);

			}
		}

	}

}


