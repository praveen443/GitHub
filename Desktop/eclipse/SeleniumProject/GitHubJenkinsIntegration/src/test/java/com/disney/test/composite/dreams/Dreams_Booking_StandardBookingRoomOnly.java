package com.disney.test.composite.dreams;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import apps.dreams.AccommodationWrapUp.AccommodationWrapUpContentFrame;
import apps.dreams.DiscoveryPage.DiscoveryPageContentFrame;
import apps.dreams.GuestSearchWindow.GuestPartyWindow;
import apps.dreams.GuestSearchWindow.GuestSearch;
import apps.dreams.HeaderFrame.HeaderFrame;
import apps.dreams.LeftFrame.LeftFrame;
import apps.dreams.OfferPage.OfferPageContentFrame;
import apps.dreams.ReservationPage.ReservationPageContentFrame;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;
import utils.dataFactory.ResortInfo;
import utils.dataFactory.ResortInfo.ResortColumns;

/**
 * @Summary: Dreams_Booking_StandardBookingRoomOnly
 * @Precondition: N/A
 * @Author: Sowmya ch
 * @Version: 02/19/2016
 */
public class Dreams_Booking_StandardBookingRoomOnly {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private String convoID;
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private Map<String, String> convos = new HashMap<String, String>();
	private String TPS_ID;
	private String locationId;
	private String testName;

	@DataProvider(name = "DRMS_Booking_StandardBookingRoomOnly")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"Standard_BookingRoomOnly");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Define TestNG parameters to the test case
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "runLocation", "browserUnderTest", "environment" })
	public void setup(String runLocation, String browserUnderTest,
			String environment) {
		this.application = "Dreams";
		this.runLocation = runLocation;
		this.browserUnderTest = browserUnderTest;
		this.environment = environment;
		dt.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	// Used when running multiple @Tests in cuonjuction with drivers hash map so
	// the class will close the correct driver
	@AfterMethod(alwaysRun = true)
	public synchronized void closeSession(ITestResult test) {
		WebDriver driver = drivers.get(testName);
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "DRMS_Booking_StandardBookingRoomOnly", description = "Standard_BookingRoomOnly", groups = {
			"regression", "dreams" })
	public void StandardPackageBooking(String testScenario, String role,
			String resort, String guestDetails, String travelplan,
			String accommodations, String partyMix, String packagecode,
			String offers, String guestInfo, String guestSelect, String payment){
		TestReporter.logScenario(testScenario);

		// Get Test name for additional reporting
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		// Initialize SeleniumWrapper to WDPRO integration
		WebDriverSetup setup = new WebDriverSetup();
		WebDriver driver = setup.initialize(application, browserUnderTest,
				runLocation, environment, "32");

		// Add new driver to drivers collection
		drivers.put(testName, driver);

		// Launch App with info sent during driver initialization
		TestReporter.logStep("Launch Dreams application");
		setup.launchApplication(driver);

		// Login to Hub Page
		TestReporter.logStep("Log into Hub Page");
		HubLoginPage HubPage = new HubLoginPage(driver);
		TestReporter.assertTrue(HubPage.pageLoaded(),
				"Verify Hub Page is displayed");
		HubPage.login(application, role);
		convoID = HubPage.getConversationId(application, environment);
		convos.put("book", convoID);

		// Navigate to left frame and click on Discovery link from Dreams Home
		// page
		TestReporter
				.logStep("Click on Discover link froms Dreams home page-Left Frame");
		LeftFrame leftFrame = new LeftFrame(driver);
		TestReporter.assertTrue(leftFrame.pageLoaded(),
				"Verify the left frame is displayed");
		leftFrame.clickDiscovery();

		// Enter the discovery information from discovery page
		TestReporter
				.logStep("Entering the required Discovery information (Guest,TravelPlan,PartyMix,"
						+ "Accommodations,Package) from Discovery page");
		DiscoveryPageContentFrame discoverContent = new DiscoveryPageContentFrame(
				driver);
		TestReporter.assertTrue(discoverContent.pageLoaded(),
				"The Discovery Page was not loaded.");
		discoverContent.clickNewPrimaryGuest();

		// Enter search Criteria
		TestReporter.logStep("Add A Primary Guest");
		GuestSearch guestsearch = new GuestSearch(driver);
		TestReporter.assertTrue(guestsearch.pageLoaded(),
				"The Guest Search Page was not loaded.");
		guestsearch.addNewPrimaryGuest(guestDetails);

		// Enter the new guest details
		TestReporter.logStep("Enter Guest Details");
		GuestPartyWindow gpw = new GuestPartyWindow(driver);
		TestReporter.assertTrue(gpw.pageLoaded(),
				"The Guest Party Page was not loaded.");
		gpw.enterPrimaryGuestInfo(guestDetails);

		// Enter accommodation details in discovery page
		TestReporter.assertTrue(discoverContent.pageLoaded(),
				"The Discovery Page was not loaded.");
		TestReporter.logStep("Travel plan details entry ");
		discoverContent.enterTravelPlan(travelplan);
		TestReporter.logStep("partyMix details entry ");
		discoverContent.enterPartyMix(partyMix);
		TestReporter.logStep("NoAdults details entry ");
		discoverContent.verifyNoAdults(partyMix);
		discoverContent.enterAccommodations(accommodations);

		// provide package code
		locationId = ResortInfo.getResortInfo(ResortColumns.RESORT_NAME,
				resort, ResortColumns.TRANSACTION_CENTER_ID);
		TestReporter.logStep("PackageCode details entry ");
		discoverContent.enterPackageCode(packagecode, "0", "WDW", "WHOLESALE",
				"*DWSL", locationId, "", "ANN Room Only");

		// Clicks on the Continue link from Dreams app Header Frame
		TestReporter.logStep("Click on Continue from the Header frame");
		HeaderFrame header = new HeaderFrame(driver);
		TestReporter.assertTrue(header.pageLoaded(),
				"The header frame was not loaded.");
		header.clickContinue();

		// Entering the reservation details from offer page
		TestReporter
				.logStep("Entering the required reservation details from offers page");
		OfferPageContentFrame offerPage = new OfferPageContentFrame(driver);
		TestReporter.assertTrue(offerPage.pageLoaded(),
				"The Offers page  was not displayed.");
		TestReporter.logStep("Offers page");
		offerPage.enterReservationDetails(offers);
		offerPage.selectOffer(offers);

		// Clicks on the Continue link from Dream app Header Frame
		TestReporter.logStep("Click on Continue from the Header frame");
		TestReporter.assertTrue(header.pageLoaded(),
				"The header frame was not loaded.");
		header.clickContinue();

		// Entering the Guest details
		AccommodationWrapUpContentFrame wrapUpContentFrame = new AccommodationWrapUpContentFrame(
				driver);
		wrapUpContentFrame.click_AddGuest();
		GuestSearch guestSearch = new GuestSearch(driver);
		TestReporter.assertTrue(guestSearch.pageLoaded(),
				"Guest search window is not loaded.");
		TestReporter.logStep("Adding guest information");
		guestSearch.clickNew();
		gpw.enterAndupdatePrimaryGuestPartyInfo(guestInfo);

		// Selecting guest information
		TestReporter.assertTrue(wrapUpContentFrame.pageLoaded(),
				"wrapUpContentFrame window is not loaded.");
		TestReporter.logStep("Selecting guest information");
		wrapUpContentFrame.selectGuestDetails(guestSelect);
		header.clickContinue();

		// Clicking DepositAndBillingSummary
		ReservationPageContentFrame rpc = new ReservationPageContentFrame(
				driver);
		TestReporter.assertTrue(rpc.pageLoaded(),
				"Verify ReservationPageContentFrame is active");
		TestReporter.logStep("Clicking Deposit and Billing Summary link");
		rpc.clickDepositAndBillingSummary();

		// Selecting Set settlement cash
		TestReporter.logStep("Set settlement ");
		ReservationPageContentFrame reservationPageContentFrame = new ReservationPageContentFrame(
				driver);
		TestReporter.assertTrue(reservationPageContentFrame.pageLoaded(),
				"Verify ReservationPageContentFrame is active");
		reservationPageContentFrame.setSettlement(payment);
		TPS_ID = reservationPageContentFrame.getReservationNumber();
		TestReporter.log("Reservation Number: " + TPS_ID);
	}
}
