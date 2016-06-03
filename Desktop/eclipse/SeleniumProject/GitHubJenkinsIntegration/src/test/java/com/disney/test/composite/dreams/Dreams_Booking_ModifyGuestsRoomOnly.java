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
import apps.dreams.HeaderFrame.HeaderFrame;
import apps.dreams.LeftFrame.LeftFrame;
import apps.dreams.OfferPage.OfferPageContentFrame;
import apps.dreams.ReservationPage.ReservationPageContentFrame;
import apps.dreams.travelPlanSearch.TravelPlanSearchPageMagick;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;
import utils.dataFactory.ResortInfo;
import utils.dataFactory.ResortInfo.ResortColumns;

/**
* @Summary: Books a ModifyGuestsRoomOnly
* @Precondition: N/A
* @Author: Chinagudaba Pawan
* @Version: N/A
* @Return: N/A
*/
public class Dreams_Booking_ModifyGuestsRoomOnly {

	// Defining global variables
	private String application = "dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private String reservationNumber = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;

	@DataProvider(name = "bookingModifyGuestsRoomOnlyScenarios")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"BookingModifyGuestsRoomOnly");
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

	// Used when running multiple @Tests in cuonjuction with drivers hashmap so
	// the class will close the correct driver
	@AfterMethod(alwaysRun = true)
	public synchronized void closeSession(ITestResult test) {
		WebDriver driver = drivers.get(testName);
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "bookingModifyGuestsRoomOnlyScenarios", description = "BookingModifyGuestsRoomOnly", groups = {
			"regression", "dreams" })
	public void BookReservationwithGroup(String testScenario, String role,
			String resort, String travelPlan, String accommodations,
			String partyMix, String packagecode, String accomWrapUp,
			String offers, String adultinformation, String guestInfo,
			String agencyId, String payment) {
		TestReporter.logScenario(testScenario);

		// Get Test name for additional reporting
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		// Initialize SeleniumWrapper to WDPRO intergration
		// WebDriverSetup setup = new WebDriverSetup(testName + testScenario);
		WebDriverSetup setup = new WebDriverSetup();
		WebDriver driver = setup.initialize(application, browserUnderTest,
				runLocation, environment, "32");

		// Add new driver to drivers collection
		drivers.put(testName, driver);

		// Launch App with info sent during driver initialization
		setup.launchApplication(driver);

		// Log into Hub Page
		TestReporter.logStep("Log into Hub Page");
		HubLoginPage HubPage = new HubLoginPage(driver);
		HubPage.login(application, role);
		HubPage.getConversationId(application, environment);

		// Navigate to the Discovery Page
		TestReporter.logStep("Navigate to the Discovery Page");
		LeftFrame leftFrame = new LeftFrame(driver);
		TestReporter.assertTrue(leftFrame.pageLoaded(),
				"Verify the left frame is displayed");
		leftFrame.clickDiscovery();

		DiscoveryPageContentFrame discoverContent = new DiscoveryPageContentFrame(
				driver);
		TestReporter.assertEquals(discoverContent.pageLoaded(), true,
				"The Discovery Page was not loaded.");
		discoverContent.addPrimaryGuest(accomWrapUp);
		discoverContent.clickNewTravelAgency(agencyId);
		discoverContent.enterTravelPlanwithoutGroupCode(travelPlan);
		discoverContent.enterPartyMix(partyMix);
		discoverContent.verifyNoAdults(partyMix);
		discoverContent.enterAccommodations(accommodations);

		String locationId = ResortInfo.getResortInfo(ResortColumns.RESORT_NAME,
				resort, ResortColumns.TRANSACTION_CENTER_ID);
		discoverContent.enterPackageCode(packagecode, "0", "WDW", "DRC RO", "",
				locationId, "", "");

		// CLick Continue
		TestReporter.logStep("Click Continue");
		HeaderFrame header = new HeaderFrame(driver);
		TestReporter.assertTrue(header.pageLoaded(),
				"Verify the header frame is displayed");
		header.clickContinue();

		// Entering Tax Exemption information
		TestReporter.logStep("Tax Considerations");
		OfferPageContentFrame enterReservationDetails = new OfferPageContentFrame(
				driver);
		TestReporter.assertTrue(enterReservationDetails.pageLoaded(),
				"Verify the Reservation details is displayed");
		enterReservationDetails.enterReservationDetails(offers);

		// Selecting an Offer
		TestReporter.logStep("Select an Offer");
		OfferPageContentFrame offerPage = new OfferPageContentFrame(driver);
		TestReporter.assertTrue(offerPage.pageLoaded(),
				"Verify the Offers selection is displayed.");
		offerPage.selectOffer(offers);

		// CLick Continue
		TestReporter.logStep("Click Continue");
		TestReporter.assertTrue(header.pageLoaded(),
				"Verify the header frame is displayed");
		header.clickContinue();

		// Updating PrimaryGuest Information
		TestReporter.logStep("Updating PrimaryGuest Information");
		AccommodationWrapUpContentFrame wrapUpContentFrame = new AccommodationWrapUpContentFrame(
				driver);
		TestReporter.assertTrue(wrapUpContentFrame.pageLoaded(),
				"Accommodation WrapUp page not loaded ");
		wrapUpContentFrame.addGuestInformation(adultinformation);

		// Selecting GUest Information
		TestReporter.logStep("Selecting GUest Information");
		TestReporter.assertTrue(wrapUpContentFrame.pageLoaded(),
				"Accommodation WrapUp page not loaded ");
		wrapUpContentFrame.selectGuestInfo(adultinformation);

		// CLick Continue
		TestReporter.logStep("Click Continue");
		TestReporter.assertTrue(header.pageLoaded(),
				"Verify the header frame is displayed");
		header.clickContinue();

		// Payment processing
		TestReporter.logStep("Payment processing");
		ReservationPageContentFrame rpc = new ReservationPageContentFrame(
				driver);
		TestReporter.assertTrue(rpc.pageLoaded(),
				"Verify ReservationPageContentFrame is active");
		TestReporter.logStep("Clicking the Process Payment Button");
		rpc.clickPaymentProcess();
		rpc.takePayment(payment);

		reservationNumber = rpc.getReservationNumber();
		TestReporter.logStep("Resv Number is : " + reservationNumber);

		// Clicking TravelPlanSearch page
		leftFrame.clickTravelPlanSearch();

		// Search Reservation number in Travel Plan page
		TestReporter.logStep("Search Reservation number in Travel Plan page");
		TravelPlanSearchPageMagick searchReservationwithZpcode = new TravelPlanSearchPageMagick(
				driver);
		TestReporter.assertTrue(searchReservationwithZpcode.pageLoaded(),
				"Verify the reservation number is displayed.");
		searchReservationwithZpcode.searchReservationwithZpcode(accomWrapUp,
				reservationNumber);

		// Clicking Confirmation Link
		TestReporter.logStep("Clicking Confirmation Link");
		rpc.clickConfirmation();
		// Clicking Confirmation Link
		TestReporter.logStep("Clicking Edit Link");
		rpc.clickEditLink();

		// ENTEring and UPdating PRimary Guest Info
		TestReporter.logStep("ENTEring and UPdating PRimary Guest Info");
		GuestPartyWindow primaryGuest = new GuestPartyWindow(driver);
		TestReporter.assertTrue(primaryGuest.pageLoaded(),
				"Update the PrimaryGuest details ");
		primaryGuest.enterAndupdatePrimaryGuestPartyInfo(guestInfo);

		// Clicking Accommodation summary LInk
		TestReporter.logStep("Clicking Accommodation summary LInk");
		wrapUpContentFrame.clickaccommodationSummary();

		// VErifying Accommodation summary ELements in Accommodation summary
		// LInk
		TestReporter
				.logStep("VErifying Accommodation summary ELements in Accommodation summary LInk");
		wrapUpContentFrame.verifyaccomodationSummary(travelPlan,
				accommodations, packagecode, reservationNumber);

		// Clicking SelectAll Checkbox in Accommodation summary LInk
		TestReporter.logStep("Clicking SelectAll Checkbox");
		rpc.chkselectAll();

		// Clicking Edit Button in Accommodation summary LInk
		TestReporter.logStep("Clicking Edit Button");
		wrapUpContentFrame.clickEdit();

		// Entering PartyMix information
		TestReporter.logStep("Entering PartyMix information");
		discoverContent.enterPartyMix(partyMix);
		// Verifying Number of Adults
		TestReporter.logStep("Verifying Number of Adults");
		discoverContent.verifyNoAdults(partyMix);

		// Clicking Re-Discovery Button
		TestReporter.logStep("Clicking Re-Discovery Button");
		rpc.clickReDiscovery();

		// Selecting an Offer
		TestReporter.logStep("Select an Offer");
		offerPage.selectOffer(offers);

		// Click Continue
		TestReporter.logStep("Click Continue");
		TestReporter.assertTrue(header.pageLoaded(),
				"Verify the header frame is displayed");
		header.clickContinue();
		header.clickContinue();
	}
}
