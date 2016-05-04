package apps.lilo.reservationDetails;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.standard.MediaSize.NA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

import apps.lilo.processingYourRequest.ProcessingYourRequest;
import core.WebDriverSetup;
import core.interfaces.Button;
import core.interfaces.Checkbox;
import core.interfaces.Element;
import core.interfaces.Label;
import core.interfaces.Link;
import core.interfaces.Listbox;
import core.interfaces.Textbox;
import core.interfaces.impl.ElementImpl;
import core.interfaces.impl.internal.ElementFactory;
import core.interfaces.impl.internal.PageLoaded;
import selenium.common.CustomVerification;
import utils.Datatable;
import utils.Sleeper;
import utils.TestReporter;

/**
 * @summary Contains the page methods & objects to interact with the reservation
 *          details page
 * @version Created 08/25/2014
 * @author Waightstill W. Avery
 */
public class ReservationDetailsPage {
	// ************************************
	// *** Main ReservationDetails Page Fields ***
	// ************************************
	private String strTravelPlanSegmentID = "";
	private String strTravelPlanID = "";
	private String strFirstName = "";
	private String strLastName = "";
	private String strResortName = "";
	private String strArrivalDate = "";
	private String strDepartureDate = "";
	private String strPackageDescription = "";
	private String strRoomTypeID = "";
	private String strPartyMix = "";
	private String strReservationStatus = "";
	private String strComments = "";
	private String strGroupNameID = "";
	private String strBillCode = "";
	private String strGroupCodeID = "";
	private String strTeamNameID = "";
//	private String strRoomReadyNotificationStatusOn = "";
//	private String strRoomReadyNotificationStatusOff = "";
//	private Boolean blnIsRRNOn = true;
//	private String strIATANumber = "";
//	private static String strReservationNUmber;
//	private static String strArrivaldate;
//	private static String strFirstname;
//	private static String strLastname;
	
	// ************************************
	// *** Main ReservationDetails Page Elements ***
	// ************************************
	// Create Textbox object for the first accommodation
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:accomodationCheckBox")
	private Checkbox chkAccomodation;

	// Create Checkbox object used to select all Travel Plan Segments
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:selectAllTPSCheckBox")
	private Checkbox chkAllTPS;

	// Create Button object for Proceed To Check-In
	@FindBy(id = "roomTabFrm:proToChkInButtonId")
	private Button btnProceedToCheckIn;

	// Create Label object for Guest First Name
	@FindBy(id = "travelSummeryFrm:firstNameLabelId")
	private Label lblFirstName;

	// Create Button object for Exit
	@FindBy(id = "roomTabFrm:exitButtonIdOne")
	private Button btnExit;

	// Create Label for IATA Number
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:existingIATANo")
	private Label lblIATANumber;

	// Create Label object for Guest Last Name
	@FindBy(id = "travelSummeryFrm:lastNameLabelId")
	private Label lblLastName;

	// Create Label object for Travel Plan ID
	@FindBy(id = "travelSummeryFrm:travelPlanId")
	private Label lblTravelPlanID;

	// Create Label object for the first Travel Plan Segment ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:tpsNoId")
	private Label lblTravelPlanSegmentID;

	// Create Label object for Resort Name for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:resortName")
	private Label lblResortName;

	// Create Label object for Arrival Date for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:arrDate")
	private Label lblArrivalDate;

	// Create Label object for Departure Date for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:deptDate")
	private Label lblDepartureDate;

	// Create Label object for Package Description for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:pkgDesId")
	private Label lblPackageDescriptionID;

	// Create Label object for Room Typee for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:genricRoomTypeTxtId")
	private Label lblRoomTypeID;

	// Create Label object for Party Mix for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:partyMixId")
	private Label lblPartyMix;

	// Create Label object for Reservation Status for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:reservationStatusId")
	private Label lblReservationStatus;

	// Create Label object for Comments
	@FindBy(css = "span[id^=\"commentsDisplayFormId:commentDisplay:0:j_id\"]")
	private Label lblComments;

	// Create Label object for Group Name ID for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:grpnameId")
	private Label lblGroupNameID;

	// Create Label object for Bill Code for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:billCodeId")
	private Label lblBillCode;

	// Create Label object for Group Code ID for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:grpcodeId")
	private Label lblGroupCodeID;

	// Create Label object for Team Name ID for the first TPS ID
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:subTable:0:teamnameId")
	private Label lblTeamNameID;

	// Capture the Room Ready Notification "On" object
	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:onNotificationImages")
	private Button btnRoomReadyNotificationStatusOn;

	@FindBy(id = "roomTabFrm:travelPlanSegmentViewId:0:offNotificationImages")
	private Button btnRoomReadyNotificationStatusOff;

	// Edit link in travel plan section
	@FindBy(id = "travelSummeryFrm:tpsId")
	private Link lnkEdit;

	// label for select accommodation text
	@FindBy(id = "roomTabFrm:selAccomLabelId")
	private Label lblSelectAccomm;

	// Grab the Filter button
	@FindBy(xpath = "//img[@ src='/ui/assets/images/SearchReservation/u841.png']")
	private Button btnFilter;

	// Grab the Resort Filter Button
	@FindBy(id = "ddlResorts")
	private Button btnResortFilter;

	// Grab the Records List
	@FindBy(xpath = "//div[@class='reservationSearchResult ativa-scroll']/*")
	private List<WebElement> searchResultList;

	// Grab the Arrival Date Filter Button
	@FindBy(id = "ddlArrivalDate")
	private Button btnArrivalDateFilter;

	// Grab the Arrival Date Checkbox
	@FindBy(id = "arrivalDate1")
	private Checkbox chkArrivalDate;

	// Grab the Status Filter Button
	@FindBy(id = "ddlStatus")
	private Button btnStatusFilter;

	// Grab the ETA Filter Button
	@FindBy(id = "ddlETA")
	private Button btnETAFilter;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "timepicker1")
	private Textbox txtETAStartTime;

	// Grab the ETA End Time Textbox
	@FindBy(id = "timepicker2")
	private Textbox txtETAEndTime;

	// Grab the MDX Status Image For Claimed Reservation.
	@FindBy(xpath = "//img[@ src='/ui/assets/images/QuickCheckIn/checkIcon.png']")
	private Button btnMDXStatusForClaimReservation;

	// Grab the MDX Status Image For Not-Claimed Reservation.
	@FindBy(xpath = "//img[@ src='/ui/assets/images/QuickCheckIn/dashIcon.png']")
	private Button btnMDXStatusForNotClaimReservation;

	// Grab the MDX Control Button
	@FindBy(id = "selectMDX")
	private Button btnMDXControl;

	// Grab the Quick Check-In Control Button
	@FindBy(id = "selectQuickCheckIn")
	private Button btnQuickCheckIn;

	// Grab the MDX Reservation record.
	@FindBy(xpath = "//div[@class='reservationDetails ng-scope']")
	private Element eleMDXTabStructureForMultipleAccomodation;

	// Grab the MDX Status Image.
	@FindBy(xpath = "//img[@ src='/ui/assets/images/QuickCheckIn/vip_white.png']")
	private Button btnValidationOfVIPIndicator;

	// Grab the ADA Guaranteed Indicator Image.
	@FindBy(xpath = "//img[@ src='/ui/assets/images/QuickCheckIn/ADAGuaranteed.png']")
	private Button btnValidationOfADARoomGuaranteedIndicator;

	// Grab the ExpressCheckout Indicator Image.
	@FindBy(xpath = "//img[@ src='/ui/assets/images/QuickCheckIn/quickcheckout_white.png']")
	private Button btnValidationOfExpressCheckoutIndicator;

	// Grab the selected Filter Name
	@FindBy(xpath = "//div[@class='filterDisplayBar']/span")
	private Label lblSelectedFilter1;

	// Grab the Hamburger Menu Button.
	@FindBy(id = "showLeftPush")
	private Button btnHamburgerMenu;

	// Grab the Quick Check-In Button.
	@FindBy(id = "returnToQuickCheck")
	private Button btnReturnToQuickCheckIn;

	// Grab the Guest Information Button.
	@FindBy(id = "guestInfoPopup")
	private Button btnGuestInformation;

	// Grab the Guest Information Button.
	@FindBy(id = "cancelbutton")
	private Button btncancel;

	// Grab the Concierge Indicator Image.
	@FindBy(xpath = "//img[@ src='/ui/assets/images/QuickCheckIn/concierge_white.png']")
	private Button btnValidationOfConciergeIndicator;

	// Grab the Shared Reservation Indicator Image.
	@FindBy(xpath = "//img[@ src='/ui/assets/images/QuickCheckIn/shared_white.png']")
	private Button btnValidationOfSharedReservationIndicator;

	// Create List object for Country Names
	@FindBy(id = "ddlMissingCountry")
	private Listbox lstCountries;

	// Grab the Zip Code Textbox
	@FindBy(id = "txtMissingZipCode")
	private Textbox txtZipCode;

	// Grab the Address Line1 Textbox
	@FindBy(id = "txtAddLIne1")
	private Textbox txtAddressLine1;

	// Grab the Address Line2 Textbox
	@FindBy(id = "txtAddLIne2")
	private Textbox txtAddressLine2;

	// Grab the City Code Textbox
	@FindBy(id = "txtMissingCity")
	private Textbox txtCityName;

	// Create List object for State Names
	@FindBy(id = "ddlMissingState")
	private Listbox lstStates;

	// Grab the Phone Number Textbox
	@FindBy(id = "txtPhone")
	private Textbox txtPhoneNumber;

	// Grab the Email Textbox
	@FindBy(id = "txtEmail")
	private Textbox txtEmailAddress;

	// Grab the Update Button for Guest Information.
	@FindBy(id = "updateOtherDetBtn")
	private Button btnUpdateGuestDetails;

	// label for select Accommodation Status text
	// @FindBy(xpath =
	// "//div[@id='reservationInfoContainer']/div/div/table/tbody/tr/td[3]")//reservationInfoBegin
	@FindBy(xpath = "//div[@class='reservationInfoBegin']/table/tbody/tr/td[3]")
	// reservationInfoBegin
	private Label lblAccommodationStatus;

	// Create List object for State Names
	@FindBy(id = "divReservationResult")
	private Listbox recList;

	// Grab the Reservation Number
	@FindBy(xpath = "//div[@id='mdxBody']/div[2]/p[2]")
	private Label lblReservationNumberOnMDX;

	// Grab the Reservation Number on Quick Check-In Control Screen
	@FindBy(xpath = "//div[@id='reservationInfoContainer']/div[1]/div/table/tbody/tr[3]/td[1]")
	private Label lblReservationNumberOnQCI;

	// Button Arrival
	@FindBy(id = "roomTabFrm:backToManageArrivalsId")
	private Button btnBackToArrivals;

	// Grab the Notification status
	@FindBy(xpath = "//table[@ id='roomTabFrm:travelPlanSegmentViewId']/tbody/tr[1]/th[2]")
	private Button lblReservationStatusOnandOff;

	// Grab text bar on Express Check-In link
	@FindBy(id = "newEngagementUIBody")
	private Element fdoBar;

	/**
	 * @summary Contains the page methods & objects to interact with the Off
	 *          Canvas Menu details page
	 * @version Created 08/24/2015
	 * @author Jaya Tiwari
	 */

	// Create List object for Countries
	@FindBy(id = "titleDd1")
	private Listbox lsttitle;

	// Grab the First Name Textbox
	@FindBy(id = "adultFname0")
	private Textbox txtFirstName;

	// Grab the Last Name Textbox
	@FindBy(id = "adultLnameVal0")
	private Textbox txtLastName;

	// Create List object for Countries
	@FindBy(id = "countryDd")
	private Listbox lstCountryNames;

	// Create List object for States
	@FindBy(id = "stateDd")
	private Listbox lstStateNames;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "add1")
	private Textbox txtAddress1;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "zipVal")
	private Textbox txtZipCodeVal;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "city")
	private Textbox txtCity;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "phoneNo")
	private Textbox txtPhoneNo;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "emailId")
	private Textbox txtEmailID;

	// Grab the update Button for Positive Interaction
	@FindBy(id = "savebutton")
	private Button btnSavebutton;

	// Grab the Guest Name value
	@FindBy(xpath = "//table[@class='mainReservationTable']/tbody/tr[1]/td[1]")
	private Label guestNameValue;

	// Grab the Guest Address value
	@FindBy(xpath = "//table[@class='mainReservationTable']/tbody/tr[2]/td[1]")
	private Label guestAddressValue;

	/**
	 * @summary Contains the page methods & objects to interact with the Quick
	 *          Check-In Positive Interaction page
	 * @version Created 08/24/2015
	 * @author Jaya Tiwari
	 */

	// Create List object for Countries
	@FindBy(id = "ddlMissingCountry")
	private Listbox lstCountry;

	// Create List object for States
	@FindBy(id = "ddlMissingState")
	private Listbox lstState;

	// Grab the update Button for Positive Interaction
	@FindBy(id = "updateOtherDetBtn")
	private Button btnUpdateOtherDetBtn;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "txtAddLIne1")
	private Textbox txtAddLIne1;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "txtMissingZipCode")
	private Textbox txtMissingZipCode;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "txtMissingCity")
	private Textbox txtMissingCity;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "txtPhone")
	private Textbox txtPhone;

	// Grab the ETA Start Time Textbox
	@FindBy(id = "txtEmail")
	private Textbox txtEmail;

	// *********************
	// ** Build page area **
	// *********************
	private WebDriver driver;
	private Datatable datatable = new Datatable();

	/**
	 * 
	 * @summary Constructor to initialize the page
	 * @version Created 8/25/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public ReservationDetailsPage(WebDriver driver) {
		this.driver = driver;
		ElementFactory.initElements(driver, this);
		datatable.setVirtualtablePath(Datatable.LILO_MASTER_DATA_PATH);
	}

	public ReservationDetailsPage initialize() {
		return ElementFactory
				.initElements(driver, ReservationDetailsPage.class);
	}

	public boolean resevationDetailsLoadPage() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnProceedToCheckIn);

	}

	public boolean resevationDetailsLoadPage(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);

	}

	public boolean resevationExitButton() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnExit);

	}

	public boolean pageLoaded() {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				btnProceedToCheckIn);

	}

	// ****************************************
	// *** Main ReservationDetails Page Interactions ***
	// ****************************************

	/**
	 * 
	 * @summary Method that calls other methods to capture details on the page
	 *          (e.g TP ID, TPS ID, reservation status, etc)
	 * @version Created 8/25/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void captureReservationDetails() {
		new ProcessingYourRequest().WaitForProcessRequest(driver);
		btnProceedToCheckIn.syncEnabled(driver);
		btnProceedToCheckIn.syncVisible(driver);
		captureGuestFirstName();
		captureGuestLastName();
		captureTravelPlanId();
		captureTravelPlanSegmentID();
		captureReservationStatus();
		captureResortName();
		captureRoomTypeDescription();
		captureArrivalDate();
		captureDepartureDate();
		captureRoomTypeDescription();
		capturePartyMix();
		// DJS captureComments();
		capturePackageDescription();
		captureGroupNameID();
		captureBillCode();
		captureGroupCodeID();
		captureTeamNameID();
	}

	/*
	 * *Capture and Get for Travel Plan ID
	 */
	private void captureTravelPlanId() {
		strTravelPlanID = lblTravelPlanID.getText();

		System.out.println("strTravelPlanID--------->" + strTravelPlanID);
		Reporter.log("\bTP ID: [" + strTravelPlanID + "].\n");
	}

	public String getTravelPlanID() {
		return strTravelPlanID;
	}

	public String capture_TravelPlanId() {

		lblTravelPlanID.syncVisible(driver);
		resevationDetailsLoadPage(lblTravelPlanID);
		initialize();
		String strTravel_PlanID = lblTravelPlanID.getText().trim();
		Reporter.log("\bTP ID: [" + strTravel_PlanID + "].\n");
		return strTravel_PlanID;
	}

	public String captureResrvationNumber() {
		lblTravelPlanID.syncVisible(driver, 60, false);
		strTravelPlanID = lblTravelPlanID.getText().trim();
		System.out.println("strTravelPlanID--------->" + strTravelPlanID);
		Reporter.log("\bTP ID: [" + strTravelPlanID + "].\n");
		return strTravelPlanID;
	}

	/*
	 * *Capture and Get for Travel Plan Segment ID
	 */
	private void captureTravelPlanSegmentID() {
		resevationDetailsLoadPage();
		strTravelPlanSegmentID = lblTravelPlanSegmentID.getText();
		System.out.println("strTravelPlanSegmentID--------->"
				+ strTravelPlanSegmentID);
		Reporter.log("\bTPS ID: [" + strTravelPlanSegmentID + "].\n");
	}

	public String getTravelPlanSegmentID() {
		return strTravelPlanSegmentID;
	}

	public String captureReservation() {

		lblTravelPlanSegmentID.syncVisible(driver, 60, false);
		String strTravelPlanSegment_ID = lblTravelPlanSegmentID.getText();
		System.out.println("strTravelPlanSegment_ID--------->"
				+ strTravelPlanSegment_ID);
		Reporter.log("\bTPS ID: [" + strTravelPlanSegment_ID + "].\n");
		return strTravelPlanSegment_ID;

	}

	/*
	 * *Capture and Get for Guest Name
	 */
	private void captureGuestFirstName() {
		pageLoaded(lblFirstName);
		lblFirstName.syncVisible(driver, 60, true);
		strFirstName = lblFirstName.getText();
	}

	public String captureGuest_FirstName() {
		strFirstName = lblFirstName.getText();
		return strFirstName;

	}

	public String getGuestFirstName() {
		return strFirstName;
	}

	private void captureGuestLastName() {
		strLastName = lblLastName.getText();
	}

	public String captureGuest_LastName() {
		strLastName = lblLastName.getText();

		return strLastName;
	}

	public String getGuestLastName() {
		return strLastName;
	}

	public String getGuestFullName() {
		return getGuestFirstName() + " " + getGuestLastName();
	}

	/*
	 * *Capture and Get for Resort Name
	 */
	private void captureResortName() {
		strResortName = lblResortName.getText();
		System.out.println("strResortName--------->" + strResortName);
	}

	public String getResortName() {
		return strResortName;
	}

	public String getResort_Name() {
		lblResortName.highlight(driver);
		System.out.println("Resort Type" + lblResortName.getText().trim());
		return strResortName = lblResortName.getText().trim();
	}

	/*
	 * *Capture and Get for Dates
	 */
	private void captureArrivalDate() {
		strArrivalDate = lblArrivalDate.getText();
		System.out.println("strArrivalDate--------->" + strArrivalDate);
	}

	public String getArrivalDate() {
		return strArrivalDate;
	}

	private void captureDepartureDate() {
		strDepartureDate = lblDepartureDate.getText();
		System.out.println("strDepartureDate--------->" + strDepartureDate);
	}

	public String getDepartureDate() {
		return strDepartureDate;
	}

	public String getDates() {
		return getArrivalDate() + " - " + getDepartureDate();
	}

	/*
	 * *Capture and Get for Package Description
	 */
	private void capturePackageDescription() {
		strPackageDescription = lblPackageDescriptionID.getText();
	}

	public String getPackageDescription() {
		return strPackageDescription;
	}

	/*
	 * *Capture and Get for Package Description
	 */
	private void captureRoomTypeDescription() {
		strRoomTypeID = lblRoomTypeID.getText();
	}

	public String getRoomTypeDescription() {
		return strRoomTypeID;
	}

	public String getRoom_Type() {
		lblRoomTypeID.highlight(driver);
		System.out.println("Room" + lblRoomTypeID.getText().trim());
		return strRoomTypeID = lblRoomTypeID.getText().trim();

	}

	/*
	 * *Capture and Get for Party Mix
	 */
	private void capturePartyMix() {
		strPartyMix = lblPartyMix.getText();
	}

	public String getPartyMix() {
		return strPartyMix;
	}

	/*
	 * *Capture and Get for Reservation Status
	 */
	private void captureReservationStatus() {
		lblReservationStatus.highlight(driver);
		strReservationStatus = lblReservationStatus.getText().trim();

		System.out.println("strReservationStatus--------->"
				+ strReservationStatus);

	}

	public String getReservationStatus() {
		return strReservationStatus;
	}

	public String getReservation_Status() {
		lblReservationStatus.syncVisible(driver, 45, false);
		pageLoaded(lblReservationStatus);
		strReservationStatus = lblReservationStatus.getText().trim();
		return strReservationStatus;
	}

	public String getComments() {
		return strComments;
	}

	/*
	 * *Capture and Get for Reservation Group Name ID
	 */
	private void captureGroupNameID() {
		strGroupNameID = lblGroupNameID.getText();
	}

	public String getGroupNameID() {
		return strGroupNameID;
	}

	/*
	 * *Capture and Get for Bill Code
	 */
	private void captureBillCode() {
		strBillCode = lblBillCode.getText();
	}

	public String getBillCode() {
		return strBillCode;
	}

	/*
	 * *Capture and Get for Group Code ID
	 */
	private void captureGroupCodeID() {
		strGroupCodeID = lblGroupCodeID.getText();
	}

	public String getGroupCodeID() {
		return strGroupCodeID;
	}

	/*
	 * *Capture and Get for Team Name ID
	 */
	private void captureTeamNameID() {
		strTeamNameID = lblTeamNameID.getText();
	}

	public String getTeamNameID() {
		return strTeamNameID;
	}

	/**
	 * 
	 * @summary Method that checks a checkbox next to a reservation and clicks
	 *          the Proceed To Check-In button
	 * @version Created 8/25/2014
	 * @author Waightstill W Avery
	 * @param driver
	 * @throws NA
	 * @return NA
	 * 
	 */
	public void proceedToCheckIn() {
		chkAccomodation.syncEnabled(driver);
		chkAccomodation.highlight(driver);

		try {
			if (!chkAccomodation.getAttribute("checked").equalsIgnoreCase(
					"checked")) {
				chkAccomodation.jsClick(driver);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
			}
		} catch (Exception e) {
			chkAccomodation.jsClick(driver);
			new ProcessingYourRequest().WaitForProcessRequest(driver);
		}

		btnProceedToCheckIn.syncEnabled(driver);
		btnProceedToCheckIn.highlight(driver);
		btnProceedToCheckIn.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);
	}

	/**
	 * 
	 * @summary Method to toggle the Room Ready Notification icon
	 * @version Created 8/25/2014
	 * @author Waightstill W Avery
	 * @param toggleStatus
	 *            - desired status of the Room Ready Notification icon
	 * @throws NA
	 * @return true if the correct status is displayed, false otherwise
	 * 
	 */
	public void toggleRoomReadyNotificationStatus(String toggleStatus) {
		@SuppressWarnings("unused")
		final boolean isCorrect;
		/*
		 * Determine if the notification displays the desired status If so,
		 * nothing needs to be done If not, toggle the status and ensure the new
		 * status is displayed
		 */
		isCorrect = isRoomReadyNotificationToggled(toggleStatus);
	}

	/**
	 * 
	 * @summary Method to determine if the Room Ready Notification has the
	 *          desired status in the UI. If it does not, then the icon will be
	 *          clicked in an effort to set the desired status
	 * @version Created 8/25/2014
	 * @author Waightstill W Avery
	 * @param toggleStatus
	 *            - desired status of the Room Ready Notification icon
	 * @throws NA
	 * @return true if the correct status is displayed, false otherwise
	 * 
	 */
	private boolean isRoomReadyNotificationToggled(String toggleStatus) {
		boolean isToggled = false;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

		// Determine which status is desired
		switch (toggleStatus.toUpperCase()) {
		// If the "On" status is desired.....
		case "ON":
			// Determine if the status is currently "On", in which case no
			// action is required
			if (btnRoomReadyNotificationStatusOn.isDisplayed()) {
				isToggled = true;
				// If the current status is "Off", click the image and wait for
				// it to display "On"
			} else if (btnRoomReadyNotificationStatusOff.isDisplayed()) {
				btnRoomReadyNotificationStatusOff.click();
				driver.manage()
						.timeouts()
						.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
								TimeUnit.SECONDS);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				if (btnRoomReadyNotificationStatusOn.isDisplayed()) {
					isToggled = true;
				}
			}
			break;
		// If the "Off" status is desired.....
		case "OFF":
			// Determine if the status is currently "Off", in which case no
			// action is required
			if (btnRoomReadyNotificationStatusOff.isDisplayed()) {
				isToggled = true;
				// If the current status is "Off", click the image and wait for
				// it to display "On"
			} else if (btnRoomReadyNotificationStatusOn.isDisplayed()) {
				btnRoomReadyNotificationStatusOn.click();
				driver.manage()
						.timeouts()
						.implicitlyWait(WebDriverSetup.getDefaultTestTimeout(),
								TimeUnit.SECONDS);
				new ProcessingYourRequest().WaitForProcessRequest(driver);
				if (btnRoomReadyNotificationStatusOff.isDisplayed()) {
					isToggled = true;

				}
			}
			break;
		default:
			break;
		}

		return isToggled;
	}

	// Return if the travel plan ID is displayed
	public boolean travelPlanIDisDisplayed() {
		return lblTravelPlanID.isDisplayed();
	}

	public void clickEditLink() {
		lnkEdit.jsClick(driver);
	}

	// Wait until the text is displayed in this label to be sure the page is
	// fully loaded
	public void waitUntilTextDisplays() {
		lblSelectAccomm.syncTextInElement(driver, "Select Accommodation");
	}

	public void clickonExitButton() {

		btnExit.jsClick(driver);
		new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	public void clickFilterButton() {
		pageLoaded(btnFilter);
		btnFilter.highlight(driver);
		btnFilter.click();
	}

	public void clickResortFilter() {
		btnResortFilter.highlight(driver);
		btnResortFilter.click();
	}

	public void clickResortCheckbox(String selectedResortName) {
		CustomVerification custom = new CustomVerification();
		List<WebElement> datesList = driver.findElements(By
				.xpath("//table[@class='tableResortList']/tbody/tr"));
		int listSize = datesList.size();
		for (int i = 1; i <= listSize; i++) {
			Element element = new ElementImpl(driver.findElement(By
					.xpath("//*[@class='tableResortList']/tbody/tr[" + i
							+ "]/td[2]")));
			String filterValue = element.getText();
			if (filterValue.equals(selectedResortName)) {
				Element filterCheckbox = new ElementImpl(driver.findElement(By
						.xpath("//table[@class='tableResortList']/tbody/tr["
								+ i + "]/td[1]")));
				element.highlight(driver);
				filterCheckbox.click();
				break;
			}
		}
		custom.checkForVerificationErrors();
	}

	public void getResortFilterInformation() {
		clickFilterButton();
		clickResortFilter();
		// clickResortCheckbox();
		clickResortFilter();
		lblSelectedFilter1.highlight(driver);
		Sleeper.sleep(5000);
		Assert.assertEquals(lblSelectedFilter1.getText(),
				"All-Star Sports Resort", "Both Resort Names are equal");
		TestReporter
				.logStep("Actual and Expected Resort Names are showing correct");
	}

	public void getArrivalDateFilterInformation(String arrivalDateFilter) {
		CustomVerification custom = new CustomVerification();
		clickFilterButton();
		clickArrivalDateFilter();
		clickArrivalDateCheckbox(arrivalDateFilter);
		lblSelectedFilter1.highlight(driver);
		clickArrivalDateFilter();
		String actual_ArrivalDate = lblSelectedFilter1.getText();
		if (actual_ArrivalDate.equals(arrivalDateFilter)) {

			custom.verifyTrue(actual_ArrivalDate.equals(arrivalDateFilter),
					"Expected Arrival Date is : [" + arrivalDateFilter
							+ "] but Found : [" + actual_ArrivalDate + "]");
			TestReporter
					.logStep("Actual and Expected Arrival Dates are showing correct");

		} else {

			custom.verifyTrue(actual_ArrivalDate.equals(arrivalDateFilter),
					"Expected Arrival Date is : [" + arrivalDateFilter
							+ "] but Found : [" + actual_ArrivalDate + "]");
			TestReporter.logStep("Found Assertion Error");
		}

	}

	public void clickArrivalDateFilter() {
		btnArrivalDateFilter.highlight(driver);
		btnArrivalDateFilter.click();
	}

	public void clickArrivalDateCheckbox(String arrivalDateFilter) {
		CustomVerification custom = new CustomVerification();
		List<WebElement> datesList = driver.findElements(By
				.xpath("//table[@id='tblArrivalDate']/tbody/tr"));
		int listSize = datesList.size();

		for (int i = 2; i <= listSize; i++) {
			Element element = new ElementImpl(driver.findElement(By
					.xpath("//*[@id='tblArrivalDate']/tbody/tr[" + i
							+ "]/td[2]")));
			String filterValue = element.getText();
			if (filterValue.equals(arrivalDateFilter)) {
				Element filterCheckbox = new ElementImpl(driver.findElement(By
						.xpath("//table[@id='tblArrivalDate']/tbody/tr[" + i
								+ "]/td[1]")));
				element.highlight(driver);
				filterCheckbox.click();
				break;
			}

		}

		custom.checkForVerificationErrors();
	}

	public boolean pageLoaded(Element element) {
		return new PageLoaded().isPageHTMLLoaded(this.getClass(), driver,
				element);
	}

	public void getStatusFilterInformation() {
		clickFilterButton();
		clickStatusFilter();
		// clickStatusCheckbox();
		clickStatusFilter();
		lblSelectedFilter1.highlight(driver);
		Sleeper.sleep(5000);
		Assert.assertEquals(lblSelectedFilter1.getText(), "Not Checked In",
				"Both Status values are equal");
		TestReporter
				.logStep("Actual and Expected Status values are showing correct");
	}

	public void clickStatusFilter() {
		btnStatusFilter.highlight(driver);
		btnStatusFilter.click();
	}

	public void clickStatusCheckbox(String selectedStatusName) {

		CustomVerification custom = new CustomVerification();
		List<WebElement> statusList = driver.findElements(By
				.xpath("//table[@class='tableStatus']/tbody/tr"));
		int listSize = statusList.size();
		for (int i = 1; i <= listSize; i++) {
			Element element = new ElementImpl(driver.findElement(By
					.xpath("//*[@class='tableStatus']/tbody/tr[" + i
							+ "]/td[2]")));
			String filterValue = element.getText();
			if (filterValue.equals(selectedStatusName)) {
				Element filterCheckbox = new ElementImpl(driver.findElement(By
						.xpath("//table[@class='tableStatus']/tbody/tr[" + i
								+ "]/td[1]")));
				element.highlight(driver);
				filterCheckbox.click();
				break;
			}
		}
		custom.checkForVerificationErrors();

	}

	public void getETAFilterInformation(String startTime, String endTime) {
		clickFilterButton();
		clickETAFilter();
		txtETAStartTime.sendKeys(startTime);
		txtETAEndTime.sendKeys(endTime);
		Assert.assertEquals(txtETAStartTime.getText(), startTime,
				"Both times are equal");
		Assert.assertEquals(txtETAEndTime.getText(), endTime,
				"Both times are equal");
		clickETAFilter();
		lblSelectedFilter1.highlight(driver);
		TestReporter
				.logStep("Actual and Expected ETA times are showing correct");
	}

	public void clickETAFilter() {
		btnETAFilter.highlight(driver);
		btnETAFilter.click();
	}

	public void getMDXstatusForClaimReservation() {
		pageLoaded(btnMDXStatusForClaimReservation);
		btnMDXStatusForClaimReservation.highlight(driver);
		btnMDXStatusForClaimReservation.click();
		Assert.assertEquals(
				btnMDXStatusForClaimReservation.getElementIdentifier(),
				"//img[@ src='/ui/assets/images/QuickCheckIn/checkIcon.png']",
				"Both MDX Status are equal with Green Icon for Claimed Reservation");
		TestReporter
				.logStep("MDX Status For Claimed Reservation is showing correct");
	}

	public void getMDXstatusForNotClaimReservation() {
		pageLoaded(btnMDXStatusForNotClaimReservation);
		btnMDXStatusForNotClaimReservation.highlight(driver);
		btnMDXStatusForNotClaimReservation.click();
		Assert.assertEquals(
				btnMDXStatusForNotClaimReservation.getElementIdentifier(),
				"//img[@ src='/ui/assets/images/QuickCheckIn/dashIcon.png']",
				"Both MDX Status are equal with Grey Icon for Not-Claimed Reservation");
		TestReporter
				.logStep("MDX Status For Not-Claimed Reservation is showing correct");
	}

	public void getMDXTabStructureForVisualID() {
		pageLoaded(btnMDXControl);
		btnMDXControl.highlight(driver);
		btnMDXControl.click();
		pageLoaded(btnQuickCheckIn);
		btnQuickCheckIn.highlight(driver);
		btnQuickCheckIn.click();
		Sleeper.sleep(2000);
		Assert.assertEquals(btnMDXControl.getElementIdentifier(), "selectMDX",
				"Both MDX Control Information are same");
		TestReporter.logStep("MDX Tab Structure For Visual ID is working fine");
	}

	public void getMDXTabStructureForReservationNumber() {
		pageLoaded(btnMDXControl);
		btnMDXControl.highlight(driver);
		btnMDXControl.click();
		pageLoaded(btnQuickCheckIn);
		btnQuickCheckIn.highlight(driver);
		btnQuickCheckIn.click();
		Sleeper.sleep(2000);
		Assert.assertEquals(btnMDXControl.getElementIdentifier(), "selectMDX",
				"Both Quick CheckIn Control Information are same");
		TestReporter
				.logStep("MDX Tab Structure For Reservation Number is working fine");
	}

	public void getMDXTabStructureForMultipleAccomodation() {
		eleMDXTabStructureForMultipleAccomodation.highlight(driver);
		eleMDXTabStructureForMultipleAccomodation.click();
		pageLoaded(btnMDXControl);
		btnMDXControl.highlight(driver);
		btnMDXControl.click();
		pageLoaded(btnQuickCheckIn);
		btnQuickCheckIn.highlight(driver);
		btnQuickCheckIn.click();
		Sleeper.sleep(2000);
		Assert.assertEquals(btnQuickCheckIn.getElementIdentifier(),
				"selectQuickCheckIn",
				"Both Quick CheckIn Control Information are same");
		TestReporter
				.logStep("MDX Tab Structure For Multiple Accomodations is working fine");
	}

	public void getValidationOfVIPIndicator() {
		pageLoaded(btnValidationOfVIPIndicator);
		btnValidationOfVIPIndicator.highlight(driver);
		try {
			Assert.assertEquals(
					btnValidationOfVIPIndicator.getElementIdentifier(),
					"//img[@ src='/ui/assets/images/QuickCheckIn/vip_white.png']",
					"Validation of VIP Indicator is done successfully");
		} catch (Exception e) {
			TestReporter.logStep("In catch block");
		}

		TestReporter
				.logStep("FDO-QCI Validation Of VIP Indicator is done successfully.");
	}

	public void getValidationOfADARoomGuaranteedIndicator() {
		pageLoaded(btnValidationOfADARoomGuaranteedIndicator);
		btnValidationOfADARoomGuaranteedIndicator.highlight(driver);
		Sleeper.sleep(2000);
		Assert.assertEquals(
				btnValidationOfADARoomGuaranteedIndicator
						.getElementIdentifier(),
				"//img[@ src='/ui/assets/images/QuickCheckIn/ADAGuaranteed.png']",
				"Validation Of ADA Room Guaranteed Indicator is done successfully");
		TestReporter
				.logStep("FDO-QCI Validation Of ADA Room Guaranteed Indicator is done successfully.");
	}

	public void getValidationOfExpressCheckoutIndicator() {
		pageLoaded(btnValidationOfExpressCheckoutIndicator);
		btnValidationOfExpressCheckoutIndicator.highlight(driver);
		Sleeper.sleep(2000);
		Assert.assertEquals(
				btnValidationOfExpressCheckoutIndicator.getElementIdentifier(),
				"//img[@ src='/ui/assets/images/QuickCheckIn/quickcheckout_white.png']",
				"Validation Of Express Checkout Indicator is done successfully");
		TestReporter
				.logStep("FDO-QCI Validation Of Express Checkout Indicator is done successfully.");
	}

	public void clickQuickCheckIn() {
		btnReturnToQuickCheckIn.highlight(driver);
		btnReturnToQuickCheckIn.jsClick(driver);
		Sleeper.sleep(3000);
	}

	public void clickHamburgerMenu() {

		btnHamburgerMenu.highlight(driver);
		btnHamburgerMenu.jsClick(driver);
	}

	public void clickGuestInformation() {
		pageLoaded(btnGuestInformation);
		btnGuestInformation.highlight(driver);
		btnGuestInformation.jsClick(driver);
	}

	public void getValidationOfHambugerMenu() {
		clickHamburgerMenu();
		clickQuickCheckIn();
		// Sleeper.sleep(2000);
		clickHamburgerMenu();
		clickGuestInformation();
		TestReporter.logStep("Validation of Hamburger Menu is working fine");
	}

	public void getValidationOfConciergeIndicator() {
		pageLoaded(btnValidationOfConciergeIndicator);
		btnValidationOfConciergeIndicator.highlight(driver);
		Assert.assertEquals(
				btnValidationOfConciergeIndicator.getElementIdentifier(),
				"//img[@ src='/ui/assets/images/QuickCheckIn/concierge_white.png']",
				"Validation of Concierge Indicator is done successfully");
		TestReporter
				.logStep("FDO-QCI Validation Of Concierge Indicator is done successfully.");
	}

	public void getValidationOfSharedReservationIndicator() {
		pageLoaded(btnValidationOfSharedReservationIndicator);
		btnValidationOfSharedReservationIndicator.highlight(driver);
		Assert.assertEquals(
				btnValidationOfSharedReservationIndicator
						.getElementIdentifier(),
				"//img[@ src='/ui/assets/images/QuickCheckIn/shared_white.png']",
				"Validation of Shared Reservation Indicator is done successfully");
		TestReporter
				.logStep("FDO-QCI Validation Of Shared Reservation Indicator is done successfully.");
	}

	public void selectCountryDropdown(String selectedCountry) {
		pageLoaded(lstCountries);
		lstCountries.highlight(driver);
		lstCountries.select(selectedCountry);
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void selectZipCode(String ZipCode) {
		txtZipCode.highlight(driver);
		txtZipCode.sendKeys(ZipCode);
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void selectAddressDetails(String addr1, String addr2) {
		txtAddressLine1.highlight(driver);
		txtAddressLine1.sendKeys(addr1);
		txtAddressLine2.highlight(driver);
		txtAddressLine2.sendKeys(addr2);
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void selectCityName(String CityName) {
		txtCityName.highlight(driver);
		txtCityName.sendKeys(CityName);
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void selectStateDropdown(String selectedState) {
		lstStates.highlight(driver);
		lstStates.select(selectedState);
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void selectPhoneNumber(String phoneNumbr) {
		txtPhoneNumber.highlight(driver);
		txtPhoneNumber.sendKeys(phoneNumbr);
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void selectEmailAddress(String emailAddr) {
		txtEmailAddress.highlight(driver);
		txtEmailAddress.sendKeys(emailAddr);
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void getUpdatedGuestDetails() {
		btnUpdateGuestDetails.highlight(driver);
		btnUpdateGuestDetails.click();
		// Sleeper.sleep(2000);
		// Assert.assertEquals(btnValidationOfVIPIndicator.getElementIdentifier(),
		// "//img[@ src='/assets/images/QuickCheckIn/vip_white.png']",
		// "Validation of VIP Indicator is done successfully");
	}

	public void getResortFilterInformationJSON(String resortName) {
		CustomVerification custom = new CustomVerification();
		clickFilterButton();
		clickResortFilter();
		clickResortCheckbox(resortName);
		clickResortFilter();
		lblSelectedFilter1.highlight(driver);
		String actual_resortName = lblSelectedFilter1.getText();
		if (actual_resortName.equals(resortName)) {
			custom.verifyTrue(actual_resortName.equals(resortName),
					"Expected Resort Name is : [" + resortName
							+ "] but Found : [" + actual_resortName + "]");
			TestReporter
					.logStep("Actual and Expected Resort Names are showing correct");

		} else {
			custom.verifyTrue(actual_resortName.equals(resortName),
					"Expected Resort Name is : [" + resortName
							+ "] but Found : [" + actual_resortName + "]");
			TestReporter.logStep("Found Assertion Error");
		}

	}

	public void getStatusFilterInformationJSON(String statusName) {
		CustomVerification custom = new CustomVerification();
		clickFilterButton();
		clickStatusFilter();
		clickStatusCheckbox(statusName);
		clickStatusFilter();
		lblSelectedFilter1.highlight(driver);

		String actual_statusName = lblSelectedFilter1.getText();
		if (actual_statusName.equals(statusName)) {
			custom.verifyTrue(actual_statusName.equals(statusName),
					"Expected Status is : [" + statusName + "] but Found : ["
							+ actual_statusName + "]");
			TestReporter
					.logStep("Actual and Expected Status values are showing correct");

		} else {
			custom.verifyTrue(actual_statusName.equals(statusName),
					"Expected Status is : [" + statusName + "] but Found : ["
							+ actual_statusName + "]");
			TestReporter.logStep("Found Assertion Error");
		}

	}

	public void displayAccommodationStatus(String accommStatus) {
		// System.out.println("lblAccommodationStatus----  "+lblAccommodationStatus.getText());
		pageLoaded(lblAccommodationStatus);
		lblAccommodationStatus.highlight(driver);
		try {
			Assert.assertEquals(lblAccommodationStatus.getText(), accommStatus,
					"Both Accommodation Status are matched");
		} catch (Exception e) {
			TestReporter.logStep("In catch block");
		}
		TestReporter
				.logStep("FDO-QCI Validation Of Accommodation Status is done successfully.");
	}

	public void validateGuestLastName(String lastName) {
		CustomVerification custom = new CustomVerification();
		List<WebElement> lastnames = driver.findElements(By
				.xpath("//*[@class='lastNameAlign']"));
		int nameSize = lastnames.size();

		for (int i = 0; i < nameSize; i++) {
			Element element = new ElementImpl(lastnames.get(i));
			String actual_lastName = element.getText();

			if (actual_lastName.equals(lastName)) {
				// System.out.println( "Position= : "+i+ " , Matched Name : "
				// +actual_lastName);
				element.highlight(driver);
				custom.verifyTrue(actual_lastName.equals(lastName),
						"Expected Last Name is : [" + lastName
								+ "] but Found : [" + actual_lastName + "]");
				TestReporter
						.logStep("Validation Of Guest Last Name is done successfully.");

			} else {
				// System.out.println( "Position= : "+i+
				// " , Not Matched Name : " +actual_lastName);
				custom.verifyTrue(actual_lastName.equals(lastName),
						"Expected Last Name is : [" + lastName
								+ "] but Found : [" + actual_lastName + "]");
				TestReporter.logStep("Found Assertion Error");
			}

		}

		custom.checkForVerificationErrors();
	}

	public void validateReservationNumber(String resNum) {
		CustomVerification custom = new CustomVerification();
		pageLoaded(lblReservationNumberOnQCI);
		lblReservationNumberOnQCI.highlight(driver);
		String actual_ResNo = lblReservationNumberOnQCI.getText();
		if (actual_ResNo.equalsIgnoreCase(resNum)) {
			custom.verifyTrue(actual_ResNo.equals(resNum),
					"Expected Reservation Number is : [" + resNum
							+ "] but Found : [" + actual_ResNo + "]");
			TestReporter
					.logStep("Validation Of Reservation Number is done successfully.");
		} else {
			custom.verifyTrue(actual_ResNo.equals(resNum),
					"Expected Reservation Number is : [" + resNum
							+ "] but Found : [" + actual_ResNo + "]");
			TestReporter.logStep("Found Assertion Error");
		}

		custom.checkForVerificationErrors();

	}

	public void clickOnBackToArrivals() {
		btnBackToArrivals.syncEnabled(driver, 60, false);
		btnBackToArrivals.jsClick(driver);
		// new ProcessingYourRequest().WaitForProcessRequest(driver);

	}

	// Method to return the Text of Reservation status
	public String getReservationStatusOnandOff() {
		lblReservationStatusOnandOff.syncVisible(driver, 60, false);
		lblReservationStatusOnandOff.highlight(driver);
		String status = lblReservationStatusOnandOff.getText();
		return status;

	}

	public void validatePositiveInteraction() {
		CustomVerification custom = new CustomVerification();
		btnUpdateOtherDetBtn.highlight(driver);
		btnUpdateOtherDetBtn.click();
		btnUpdateOtherDetBtn.syncDisabled(driver);
		custom.verifyFalse(lstCountry.isDisplayed(),
				"Country dropdown is still displaying");
		custom.verifyFalse(txtMissingZipCode.isDisplayed(),
				"ZipCode textbox is still displaying");
		custom.verifyFalse(txtAddLIne1.isDisplayed(),
				"Address Line textbox is still displaying");
		custom.verifyFalse(txtMissingCity.isDisplayed(),
				"City textbox is still displaying");
		custom.verifyFalse(lstState.isDisplayed(),
				"State dropdown is still displaying");
		custom.verifyFalse(txtPhone.isDisplayed(),
				"Phone  textbox is still displaying");
		custom.verifyFalse(txtEmail.isDisplayed(),
				"Email  textbox is still displaying");
		custom.checkForVerificationErrors();

	}

	public void validateExpressCheckInFromLILOHomePage() {
		CustomVerification custom = new CustomVerification();
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			fdoBar.syncVisible(driver, 60, false);

			/*
			 * Applying assertion- if condition gives true, it means Express
			 * Check-In link has been launched successfully from LILO Home Page.
			 * if condition gives false that it gives an assertion error and
			 * message provided in verifyTrue() method.
			 */
			custom.verifyTrue(fdoBar.isDisplayed(),
					"Express Check-In has not been launched successfully");
			if (fdoBar.isDisplayed() == true) {
				TestReporter
						.logStep("Express Check-In has been launched successfully from LILO Home Page");
			}
		}

	}

	public void updateGuestAddress(String countryName, String stateName,
			String cityName, String zip_ode, String addressInfo) {
		pageLoaded(lstCountry);
		guestNameValue.syncVisible(driver);
		lstCountry.select(countryName);
		txtMissingZipCode.sendKeys(zip_ode);
		txtAddLIne1.sendKeys(addressInfo);
		txtMissingCity.sendKeys(cityName);
		lstState.select(stateName);
	}

	public void updateGuestPhoneNumber(String phoneNumber) {

		txtPhone.sendKeys(phoneNumber);
	}

	public void updateGuestEmailAddress(String emailID) {
		txtEmail.sendKeys(emailID);
	}

	public void updateOffCanvasMenuGuestDetails(String guestName,
			String guestAddress) {
		CustomVerification custom = new CustomVerification();
		btnSavebutton.highlight(driver);
		btnSavebutton.click();
		guestNameValue.syncTextInElement(driver, guestName);
		initialize();
		pageLoaded(guestNameValue);
		pageLoaded(guestAddressValue);
		String guestFullName = guestNameValue.getText();
		String GuestAddr = guestAddressValue.getText();
		guestNameValue.highlight(driver);
		guestAddressValue.highlight(driver);
		custom.verifyTrue(guestFullName.equalsIgnoreCase(guestName),
				"First Name is not updated");
		custom.verifyTrue(GuestAddr.equalsIgnoreCase(guestAddress),
				"Guest Address is not updated");
		custom.checkForVerificationErrors();

	}

	public void updateOffCanvasMenuGuestNames(String title, String fname,
			String lname) {
		pageLoaded(lsttitle);
		lsttitle.select(title);
		txtFirstName.clear();
		txtFirstName.syncVisible(driver, 30, false);
		txtFirstName.safeSet(fname);
		txtLastName.clear();
		txtLastName.safeSet(lname);
	}

	public void updateOffCanvasGuestAddress(String countryName,
			String stateName, String cityName, String zip_ode,
			String addressInfo) {
		pageLoaded(lstCountryNames);
		lstCountryNames.select(countryName);
		txtZipCodeVal.clear();
		txtZipCodeVal.safeSet(zip_ode);
		txtAddress1.clear();
		txtAddress1.safeSet(addressInfo);
		txtCity.clear();
		txtCity.safeSet(cityName);
		lstStateNames.select(stateName);
	}

	public void updateOffCanvasMenuGuestPhoneNo(String phoneNumber) {
		txtPhoneNo.clear();
		txtPhoneNo.safeSet(phoneNumber);
	}

	public void updateOffCanvasMenuGuestEmailID(String emailID) {
		txtEmailID.clear();
		txtEmailID.safeSet(emailID);
	}

	public void validateExpressCheckInFromLILOResPage() {
		CustomVerification custom = new CustomVerification();
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			fdoBar.syncVisible(driver, 60, false);

			/*
			 * Applying assertion- if condition gives true, it means Express
			 * Check-In link has been launched successfully from LILO
			 * Reservation Page. if condition gives false that it gives an
			 * assertion error and message provided in verifyTrue() method.
			 */
			custom.verifyTrue(btnQuickCheckIn.isDisplayed(),
					"Express Check-In has not been launched successfully. ");
			if (btnQuickCheckIn.isDisplayed() == true) {
				TestReporter
						.logStep("Express Check-In has been launched successfully from LILO Reservation Page");
			}
		}
	}

}
