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
import apps.dreams.travelPlanSearch.TravelPlanSearchPageMagick;
import apps.hub.HubLoginPage;
import core.WebDriverSetup;
import utils.Datatable;
import utils.TestReporter;
import utils.dataFactory.ResortInfo;
import utils.dataFactory.ResortInfo.ResortColumns;

/**
 * @Summary: Creates a CA Room Only CC Payment
 * @Precondition: N/A
 * @Author: Sabitha Adama
 * @Version: 03/19/2016
 * @Return: N/A
 */

public class Dreams_Booking_TicketsRoomOnly {
	// Defining global variables
	private String application = "Dreams";
	private String browserUnderTest = "";
	private String runLocation = "";
	private String environment = "";
	private String reservationNumber = "";
	private Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();
	private Datatable dt = new Datatable();
	private String testName;
	private String locationId;

	@DataProvider(name = "Dreams_Booking_TicketsRoomOnly")
	public Object[][] scenarios() {
		try {
			return dt.getTestScenariosByApp(application,
					"Dreams_Booking_TicketsRoomOnly");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Define TestNG parameters to the test case
	@BeforeMethod(groups = { "regression", "dreams" })
	@Parameters({ "runLocation", "browserUnderTest", "environment" })
	public void setup(String runLocation, String browserUnderTest,
			String environment) {
		this.application = "Dreams";
		this.runLocation = runLocation;
		this.browserUnderTest = browserUnderTest;
		this.environment = environment;
		dt.setVirtualtablePath(Datatable.DREAMS_MASTER_DATA_PATH);
	}

	// Used when running multiple @Tests in cunjuction with drivers hashmap so
	// the class will close the correct driver
	@AfterMethod(groups = { "regression", "dreams" })
	public synchronized void closeSession(ITestResult test) {
		WebDriver driver = drivers.get(testName);
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "Dreams_Booking_TicketsRoomOnly", description = "Room Only Guest Pays", groups = {
			"regression", "dreams" })
	public void Dreams__CA_RoomOnly_CC_OptIn(String testScenario, String role,
			String resort, String guestDetails, String travelplan,
			String partyMix, String accommodations, String packagecode,
			String taxExempt, String offers, String adultinformation,
			String GuestList, String payment, String SetTicketInfo,
			String SelectTickets, String FourDayTIcketInfo) {
		TestReporter.logScenario(testScenario);
		locationId = ResortInfo.getResortInfo(ResortColumns.RESORT_NAME,
				resort, ResortColumns.TRANSACTION_CENTER_ID);

		// Get Test name for additional reporting
		testName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		// Initialize SeleniumWrapper to WDPRO intergration
		WebDriverSetup setup = new WebDriverSetup(testName + testScenario);
		WebDriver driver = setup.initialize(application, browserUnderTest,
				runLocation, environment, "32");

		// Add new driver to drivers collection
		drivers.put(testName, driver);

		// Launch App with info sent during driver initialization
		setup.launchApplication(driver);

		TestReporter.logStep("Log into Hub Page");
		HubLoginPage HubPage = new HubLoginPage(driver);
		HubPage.login(application, role);
		HubPage.getConversationId(application, environment);

		LeftFrame leftFrame = new LeftFrame(driver);
		TestReporter.assertTrue(leftFrame.pageLoaded(),
				"Verify the left frame is displayed");
		TestReporter.logStep("Navigate to the Discovery Page");
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

		// Adding new primary Guest
		GuestSearch guestsearch = new GuestSearch(driver);
		guestsearch.addNewPrimaryGuest(guestDetails);

		// Enter guest details
		TestReporter.logStep("Enter Guest Details");
		GuestPartyWindow gpw = new GuestPartyWindow(driver);
		TestReporter.assertTrue(gpw.pageLoaded(),
				"The Guest Party Page was not loaded.");
		gpw.enterPrimaryGuestInfo(guestDetails);

		// Enter accommodation details in discovery page
		TestReporter.assertTrue(discoverContent.pageLoaded(),
				"The Discovery Page was not loaded.");
		discoverContent.enterTravelPlan(travelplan);
		discoverContent.enterPartyMix(partyMix);
		discoverContent.verifyNoAdults(partyMix);
		discoverContent.enterAccommodations(accommodations);

		// provide package code
		discoverContent.enterPackageCode(packagecode, "0", "WDW", "DRC RO", "",
				locationId, "", "");

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
		offerPage.enterReservationDetails(taxExempt);
		offerPage.selectOffer(offers);

		// Clicks on the Continue link from Dream app Header Frame
		TestReporter.logStep("Click on Continue from the Header frame");
		TestReporter.assertTrue(header.pageLoaded(),
				"The header frame was not loaded.");
		header.clickContinue();

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

		wrapUpContentFrame.selectMultipleGuestDetails(GuestList);
		header.clickContinue();

		ReservationPageContentFrame resPageContentFrame = new ReservationPageContentFrame(
				driver);
		// Clicking on Payment Process button
		TestReporter.assertTrue(resPageContentFrame.pageLoaded(),
				"The Reservation frame was not loaded.");
		resPageContentFrame.clickPaymentProcess();

		// Processing Payment
		TestReporter.logStep("Processing Payment");
		resPageContentFrame.takePayment(payment);

		leftFrame.clickTravelPlanSearch();
		// Search Reservation number in Travel Plan page
		TestReporter.logStep("Search Reservation number in Travel Plan page");
		TravelPlanSearchPageMagick searchReservationwithZpcode = new TravelPlanSearchPageMagick(
				driver);

		// Search the guest by reservation number and details
		TestReporter
				.logStep("Search the guest by reservation number and details");
		reservationNumber = resPageContentFrame.getReservationNumber();
		TestReporter.logStep("Resv Number is : " + reservationNumber);
		TestReporter.assertTrue(searchReservationwithZpcode.pageLoaded(),
				"Verify the Offers selection is displayed.");
		searchReservationwithZpcode.searchReservationwithZpcode(guestDetails,
				reservationNumber);

		// Clicking on KTTW Tickets, set ticket group and number of days
		TestReporter
				.logStep("Clicking on KTTW Tickets, set ticket group and two number of days ");
		resPageContentFrame.clickKTTWTicketsNew();
		offerPage.setTicketInfo(SetTicketInfo);
		header.clickContinue();

		// Changing the name and change reason and selecting tickets
		TestReporter.logStep("Modify name and modification reason");
		wrapUpContentFrame.setModifiedNameAndTickets(SelectTickets);
		header.clickContinue();
		resPageContentFrame.clickKTTWTickets();

		// Search the guest by reservation number and details
		TestReporter
				.logStep("Click on travel plan and Search the guest by reservation number and details");
		leftFrame.clickTravelPlanSearch();
		searchReservationwithZpcode.searchReservationwithZpcode(guestDetails,
				reservationNumber);

		// Set the current travel plan to the guests
		TestReporter
				.logStep("click on KTTW Ticktes and Set the current travel plan to the guests");
		resPageContentFrame.clickKTTWTickets();
		resPageContentFrame.setCurrentTravelPlan(SelectTickets);

		// Clicking on KTTW Tickets, set ticket group and number of days
		TestReporter
				.logStep("Clicking on KTTW Tickets, set ticket group and four number of days ");
		resPageContentFrame.clickKTTWTicketsEdit();
		offerPage.setTicketInfo(FourDayTIcketInfo);
		resPageContentFrame.clickKTTWTickets();

		TestReporter
				.logStep("Click on travel plan and Search the guest by reservation number and details");
		leftFrame.clickTravelPlanSearch();
		searchReservationwithZpcode.searchReservationwithZpcode(guestDetails,
				reservationNumber);

		TestReporter
				.logStep("click on KTTW Ticktes and Set the current travel plan to the guests");
		resPageContentFrame.clickKTTWTickets();
		resPageContentFrame.setCurrentTravelPlan(FourDayTIcketInfo);

		// Cancel the travel plan
		TestReporter.logStep("Cancel the travel plan");
		resPageContentFrame.clickKTTWTicketsCancel(SetTicketInfo);
		resPageContentFrame.clickKTTWTickets();
	}
}