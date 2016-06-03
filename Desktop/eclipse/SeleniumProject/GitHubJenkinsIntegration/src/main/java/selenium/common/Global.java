/*package selenium.common;

*//***********************************************************************************************************************
 * FileName - Global.java
 * 
 * (c) Disney. All rights reserved.
 * 
 * $Authors: 
 * Thomas.Kessler@disney.com, Tigran.Khanpapyan@disney.com, Jarad.Medbery@disney.com, 
 * Michael.Yardley@disney.com, Son.Huy.Pham@disney.com $   
 * $Revision: #1 $
 * $Change: 715510 $
 * $Date: November  19, 2014 $
 ***********************************************************************************************************************//*

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import selenium.engine.aboutme.AboutMeEngine;
import selenium.engine.account.AccountEngine;
import selenium.engine.accountsettings.AccountSettingsEngine;
import selenium.engine.activities.ActivitiesEngine;
import selenium.engine.calendars.CalendarsEngine;
import selenium.engine.cart.CartEngine;
import selenium.engine.checkout.CheckoutEngine;
import selenium.engine.claimreservation.ClaimReservationEngine;
import selenium.engine.common.CommonEngine;
import selenium.engine.communicationpreferences.CommunicationPreferencesEngine;
import selenium.engine.contactinformation.ContactInformationEngine;
import selenium.engine.debug.DebugEngine;
import selenium.engine.dine.DineEngine;
import selenium.engine.familyfriends.FamilyFriendsEngine;
import selenium.engine.fixedtoolbar.FixedToolBarEngine;
import selenium.engine.globalnavigationbar.GlobalNavigationBarEngine;
import selenium.engine.homepage.HomePageEngine;
import selenium.engine.itinerary.ItineraryEngine;
import selenium.engine.magicbands.MagicBandsEngine;
import selenium.engine.memorymaker.MemoryMakerEngine;
import selenium.engine.myreservation.MyReservationEngine;
import selenium.engine.onlinecheckin.OnlineCheckInEngine;
import selenium.engine.passport.PassportEngine;
import selenium.engine.paymentmethod.PaymentMethodEngine;
import selenium.engine.plan.PlanEngine;
import selenium.engine.profile.ProfileEngine;
import selenium.engine.resort.ResortEngine;
import selenium.engine.tickets.TicketEngine;
import selenium.engine.visacard.VisaCardEngine;

*//**
 * Singleton class to access Engine's variables
 *//*
public class Global {

	public AboutMeEngine aboutMeEngine; 
	public AccountEngine accountEngine;
	public AccountSettingsEngine accountSettingsEngine;
	public ActivitiesEngine activitiesEngine;
	public CartEngine cartEngine;
	public CheckoutEngine checkoutEngine;
	public ClaimReservationEngine claimReservationEngine;
	public CommonEngine commonEngine;
	public CommunicationPreferencesEngine communicationPreferencesEngine;
	public ContactInformationEngine contactInformationEngine;
	public DebugEngine debugEngine;
	public DineEngine dineEngine;
	public FamilyFriendsEngine familyFriendsEngine;
	public FixedToolBarEngine fixedToolBarEngine;
	public GlobalNavigationBarEngine globalNavigationBarEngine;
	public HomePageEngine homePageEngine;
	public ItineraryEngine itineraryEngine;
	public MagicBandsEngine magicBandsEngine;
	public MyReservationEngine myReservationEngine;
	public OnlineCheckInEngine onlineCheckInEngine;
	public PassportEngine passportEngine;
	public PaymentMethodEngine paymentMethodEngine;
	public PlanEngine planEngine;
	public ProfileEngine profileEngine;
	public ResortEngine resortEngine;
	public TicketEngine ticketEngine;
	public MemoryMakerEngine memoryMakerEngine;
	public CalendarsEngine calendarsEngine;
	public VisaCardEngine visaCardEngine;

	public boolean signedIn;
	
	private Global(WebDriver driver, 
		AboutMeEngine aboutMeEngine, 
		AccountEngine accountEngine,
		AccountSettingsEngine accountSettingsEngine,
		ActivitiesEngine activitiesEngine, 
		CartEngine cartEngine,
		CheckoutEngine checkoutEngine, 
		ClaimReservationEngine claimReservationEngine,
		CommonEngine commonEngine,
		CommunicationPreferencesEngine communicationPreferencesEngine,
		ContactInformationEngine contactInformationEngine,
		DebugEngine debugEngine, 
		DineEngine dineEngine, 
		FamilyFriendsEngine familyFriendsEngine,
		FixedToolBarEngine fixedToolBarEngine,
		GlobalNavigationBarEngine globalNavigationBarEngine,
		HomePageEngine homePageEngine,
		ItineraryEngine itineraryEngine, 
		MagicBandsEngine magicBandsEngine,
		MyReservationEngine myReservationEngine,
		OnlineCheckInEngine onlineCheckInEngine,
		PassportEngine passportEngine,
		PaymentMethodEngine paymentMethodEngine,
		PlanEngine planEngine,
		ProfileEngine profileEngine, 
		ResortEngine resortEngine,
		TicketEngine ticketEngine,
		MemoryMakerEngine memoryMakerEngine,
		CalendarsEngine calendarsEngine,
		VisaCardEngine visaCardEngine) {
			this.aboutMeEngine = aboutMeEngine;
			this.accountEngine = accountEngine;
			this.accountSettingsEngine = accountSettingsEngine;
			this.activitiesEngine = activitiesEngine;
			this.cartEngine = cartEngine;
			this.checkoutEngine = checkoutEngine;
			this.claimReservationEngine = claimReservationEngine;
			this.commonEngine = commonEngine;
			this.communicationPreferencesEngine = communicationPreferencesEngine;
			this.contactInformationEngine = contactInformationEngine;
			this.debugEngine = debugEngine;
			this.dineEngine = dineEngine;
			this.familyFriendsEngine = familyFriendsEngine;
			this.fixedToolBarEngine = fixedToolBarEngine;
			this.globalNavigationBarEngine = globalNavigationBarEngine;
			this.homePageEngine = homePageEngine;
			this.itineraryEngine = itineraryEngine;
			this.magicBandsEngine = magicBandsEngine;
			this.myReservationEngine = myReservationEngine;
			this.passportEngine = passportEngine;
			this.paymentMethodEngine = paymentMethodEngine;
			this.planEngine = planEngine;
			this.profileEngine = profileEngine;
			this.onlineCheckInEngine = onlineCheckInEngine;
			this.resortEngine = resortEngine;
			this.ticketEngine = ticketEngine;
			this.memoryMakerEngine = memoryMakerEngine;
			this.calendarsEngine = calendarsEngine;
			this.visaCardEngine = visaCardEngine;
			
			this.signedIn = false;
		}

	public static void setGlobalClass(WebDriver driver,
			AboutMeEngine aboutMeEngine, 
			AccountEngine accountEngine, 
			AccountSettingsEngine accountSettingsEngine,
			ActivitiesEngine activitiesEngine,
			CartEngine cartEngine, 
			CheckoutEngine checkoutEngine, 
			ClaimReservationEngine claimReservationEngine,
			CommonEngine commonEngine, 
			CommunicationPreferencesEngine communicationPreferencesEngine,
			ContactInformationEngine contactInformationEngine,
			DebugEngine debugEngine,
			DineEngine dineEngine,
			FamilyFriendsEngine familyFriendsEngine,
			FixedToolBarEngine fixedToolBarEngine, 
			GlobalNavigationBarEngine globalNavigationBarEngine,
			HomePageEngine homePageEngine, 
			ItineraryEngine itineraryEngine, 
			MagicBandsEngine magicBandsEngine,
			MyReservationEngine myReservationEngine,
			OnlineCheckInEngine onlineCheckInEngine,
			PassportEngine passportEngine,
			PaymentMethodEngine paymentMethodEngine,
			PlanEngine planEngine,
			ProfileEngine profileEngine, 
			ResortEngine resortEngine,
			TicketEngine ticketEngine,
			MemoryMakerEngine memoryMakerEngine,
			CalendarsEngine calendarsEngine,
			VisaCardEngine visaCardEngine) {

		global.put(driver.toString(), new Global(driver, 
				aboutMeEngine, 
				accountEngine,
				accountSettingsEngine,
				activitiesEngine,
				cartEngine,
				checkoutEngine,
				claimReservationEngine,
				commonEngine,
				communicationPreferencesEngine,
				contactInformationEngine,
				debugEngine,
				dineEngine, 
				familyFriendsEngine,
				fixedToolBarEngine, 
				globalNavigationBarEngine, 
				homePageEngine, 
				itineraryEngine, 
				magicBandsEngine,
				myReservationEngine,
				onlineCheckInEngine,
				passportEngine,
				paymentMethodEngine,
				planEngine, 
				profileEngine, 
				resortEngine,
				ticketEngine,
				memoryMakerEngine,
				calendarsEngine,
				visaCardEngine));
	}

	public static Global getGlobalObject(WebDriver driver) {
		return global.get(driver.toString());
	}

	// private static Global global;
	private static Map<String, Global> global = new HashMap<String, Global>();
}

*/