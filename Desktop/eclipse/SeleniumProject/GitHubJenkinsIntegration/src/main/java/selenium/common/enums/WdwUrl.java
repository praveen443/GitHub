package selenium.common.enums;

import selenium.common.Constants;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public enum WdwUrl implements WdprUrl {
    
    ACTIVITIES(Constants.BASE_URL + "/activities/"),
    ANNUAL_PASS(Constants.BASE_URL + "/passes/"),
    BASE_CART(Constants.BASE_URL + "/debug/cart/cartName/"),
    CART_PORTAL_DEBUG(Constants.BASE_URL + "/debug/cart-loaded/"),
    CLAIM_RESORT(Constants.BASE_URL + "/claim/resort/"),
    CLAIM_ADD_EXISTING_RES(Constants.BASE_URL + "/trip/claim/add-existing-reservation/"),
    CLAIM_WHATSNEXT(Constants.BASE_URL + "/trip/claim/whats-next/"),
    CONFIRMATION(Constants.BASE_URL + "/checkout/confirmation/"),
    COOKIE(Constants.BASE_URL + "/debug/blank/"),
    CREATE_ACCOUNT(Constants.BASE_URL + "/registration/"),
    CHECKOUT_EXPRESS(Constants.BASE_URL + "/checkout/express/"),
    DEBUG_SESSION_DATA(Constants.BASE_URL + "/debug/session-data/"),
    DEBUG_DASHBOARD(Constants.BASE_URL + "/debug/dashboard/"),
    DEBUG_LIGHT_DARK_OVERRIDE(Constants.BASE_URL + "/debug/light-dark-override/"),
    DEBUG_FEATURE_TOGGLE(Constants.BASE_URL  + "/debug/feature-toggles/"),
    DEBUG_RELEASE_TOGGLE(Constants.BASE_URL + "/debug/release-toggles/"),
    DELIVERY(Constants.BASE_URL + "/checkout/delivery/"),
    DETAILED_CART(Constants.BASE_URL + "/cart/"),
    DINE(Constants.BASE_URL  + "/dining/"),
    DINE_EVENTS(Constants.BASE_URL  + "/dining/#/dine-events/"),
    DINE_RESERVATION(Constants.BASE_URL  + "/dining-reservation/"),
    DINE_BILLING(Constants.BASE_URL  + "/dining-reservation/billing-information/"),
    DINE_REVIEW(Constants.BASE_URL   + "/dining-reservation/review/"),
    DINE_CONFORMATION(Constants.BASE_URL + "/dining-reservation/confirmation/"),
    DME(Constants.BASE_URL + "/resort-add-ons/disney-magical-express/"),
    EVENT(Constants.BASE_URL + "/tickets/events/"),
    FAQ(Constants.BASE_URL + "/faq/"),
    FAQ_DINING_PLANS_DINING_PLAN(Constants.BASE_URL + "/faq/dining-plans/dining-plan/"),
    FAQ_ROOMS_PACKAGES_SPECIAL_OFFERS(Constants.BASE_URL + "/faq/rooms-packages/special-offers/"),
    FAQ_FAST_PASS_PLUS_PRODUCT_DESCRIPTION(Constants.BASE_URL + "/faq/fast-pass-plus/product-description/"),
    FEATURE_TOGGLES(Constants.BASE_URL + "/debug/feature-toggles"),
    FORGOT_PASSWORD(Constants.BASE_URL + "/forgotpassword/"),
    GUEST_INFORMATION(Constants.BASE_URL + "/checkout/guests/"),
    GUEST_SERVICES(Constants.BASE_URL + "/guest-services/"),
    GUEST_DISABILITIES(Constants.BASE_URL + "/guest-services/guests-with-disabilities/"),
    HELP(Constants.BASE_URL + "/help/"),
    HELP_PASSHOLDERS(Constants.BASE_URL + "/help/passholders/"),
    HELP_PARKS(Constants.BASE_URL + "/help/parks/"),
    HELP_GUESTS_WITH_DISABILITIES(Constants.BASE_URL + "/help/guests-with-disabilities/"),
    HELP_MAPS(Constants.BASE_URL + "/help/maps/"),
    HELP_TICKETS(Constants.BASE_URL + "/help/tickets/"),
    HELP_DINING_PLANS(Constants.BASE_URL + "/help/dining-plans/"),
    HELP_DINING_RESERVATIONS(Constants.BASE_URL + "/help/dining-reservations/"),
    HELP_RESTAURANTS(Constants.BASE_URL + "/help/restaurants/"),
    HELP_RESORTS(Constants.BASE_URL + "/help/resorts/"),
    HELP_HOTEL_RESERVATIONS(Constants.BASE_URL + "/help/hotel-reservations/"),
    HELP_ROOMS_PACKAGES(Constants.BASE_URL + "/help/rooms-packages/"),
    HELP_FAMILY_FRIENDS(Constants.BASE_URL + "/help/family-friends/"),
    HELP_FAST_PASS_PLUS(Constants.BASE_URL + "/help/fast-pass-plus/"),
    HELP_BANDS_CARDS(Constants.BASE_URL + "/help/bands-cards/"),
    HELP_MEMORY_MAKER(Constants.BASE_URL + "/help/memory-maker/"),
    HELP_PRIVACY_LEGAL(Constants.BASE_URL + "/help/privacy-legal/"),
    HELP_PHONE(Constants.BASE_URL + "/help/phone/"),
    HELP_EMAIL(Constants.BASE_URL + "/help/email/"),
    HOME_PAGE(Constants.BASE_URL + "/"),
    Itinerary(Constants.BASE_URL + "/plan/itinerary/"),
    LINK(Constants.BASE_URL + "/link/"),
    LINK_MAGIC_BANDS(Constants.BASE_URL + "/link/magic-bands/"),
    LOGIN(Constants.BASE_URL + "/login/"),
    MY_RESERVATION(Constants.BASE_URL + "/trip/my-vacation/"),
    ONLINE_CHECKIN(Constants.BASE_URL + "/trip/online-check-in/resort-reservation/"),
    ONLINE_CHECKIN_CONFIRMATION(Constants.BASE_URL + "/trip/olci/confirmation/"),
    PARKS(Constants.BASE_URL + "/parks/"),
    PAYMENT(Constants.BASE_URL + "/checkout/payment/"),
    PERSONAL_MAGIC(Constants.BASE_URL + "/personal-magic/"),
    PLAN(Constants.BASE_URL + "/plan/"),
    PLAN_PAGE_BANDS_AND_CARDS(Constants.BASE_URL + "/plan/bands-cards/"),
    PLAN_PAGE_BANDS_AND_CARDS_ADDRESS(Constants.BASE_URL + "/plan/bands-cards/address/"),
    PLAN_PAGE_BANDS_AND_CARDS_CUSTOMIZE(Constants.BASE_URL + "/plan/bands-cards/customize/"),
    PLAN_PAGE_BANDS_AND_CARDS_SET(Constants.BASE_URL + "/plan/bands-cards/set/"),
    PLANNING_GUIDES_IN_DEPTH_ADVICE(Constants.BASE_URL + "/planning-guides/in-depth-advice/"),
    PROFILE(Constants.BASE_URL   + "/profile/"),
    PROFILE_ACCOUNT_SETTINGS(Constants.BASE_URL  + "/profile/account-settings/edit/"),
    PROFILE_COMMUNICATION_PREFERENCES(Constants.BASE_URL + "/profile/communication-preferences/edit/"),
    PROFILE_CONTACT_INFORMATION(Constants.BASE_URL   + "/profile/contact-information/edit/"),
    PROFILE_FAMILY_AND_FRIEND_ADD(Constants.BASE_URL + "/profile/family-friends/add/"),
    PROFILE_FAMILY_AND_FRIEND(Constants.BASE_URL + "/profile/family-friends/"),
    PROFILE_PAYMENT_METHODS(Constants.BASE_URL   + "/profile/payment-methods/"),
    PROFILE_PAYMENT_METHODS_ADD(Constants.BASE_URL   + "/profile/payment-methods/add/"),
    PROFILE_PERSONAL_INFORMATION_CHARACTERS(Constants.BASE_URL   + "/profile/personal-information/characters/"),
    PROFILE_PERSONAL_INFORMATION(Constants.BASE_URL  + "/profile/personal-information/edit/"),
    PURCHASE_ANNUAL_PASS(Constants.BASE_URL + "/passes/annual-pass/purchase/"),
    PURCHASE_EPCOT_AFTER4_PASS(Constants.BASE_URL + "/passes/epcot-after4-pass/purchase/"),
    PURCHASE_PREMIUM_ANNUAL_PASS(Constants.BASE_URL + "/passes/premium-annual-pass/purchase/"),
    PURCHASE_SEASONAL_PASS(Constants.BASE_URL + "/passes/seasonal-annual-pass/purchase/"),
    PURCHASE_WATERPARK_AFTER2_PASS(Constants.BASE_URL + "/passes/waterparks-after2-pass/purchase/"),
    PURCHASE_WEEKDAY_PASS(Constants.BASE_URL + "/passes/weekday-select-pass/purchase/"),
    REGISTRATION(Constants.BASE_URL + "/registration/"),
    REGISTRATION_PAGE_COMPLETE(Constants.BASE_URL    + "/registration/complete/"),
    RENEW_PASSES(Constants.BASE_URL + "/passes/renew/"),
    RESORT_ADD_ON_TICKETS(Constants.BASE_URL + "/resort-add-ons/tickets/"),
    RESORTS(Constants.BASE_URL + "/resorts/"),
    REVIEW_ORDER(Constants.BASE_URL + "/checkout/review/"),
    ROOM_AND_AMENITY_REQUESTS(Constants.BASE_URL + "/checkout/requests/"),
    SEARCH(Constants.BASE_URL + "/search/"),
    SIGN_IN(Constants.BASE_URL + "/authentication/login/"),
    SITE_HELP(Constants.BASE_URL + "/site-help/"),
    SPECIAL_EVENTS_NIGHT_OF_JOY(Constants.BASE_URL + "/events/night-of-joy/purchase/"),
    SPECIAL_EVENTS_NOT_SO_SCARY(Constants.BASE_URL + "/events/mickeys-not-so-scary-halloween-party/purchase/"),
    SPECIAL_EVENTS(Constants.BASE_URL + "/tickets/events/"),
    SPECIAL_EVENTS_VERY_MERRY_CHRISTMAS(Constants.BASE_URL + "/events/mickeys-very-merry-christmas-party/purchase/"),
    SPECIAL_OFFER(Constants.BASE_URL + "/special-offers/"),
    TICKET(Constants.BASE_URL + "/tickets/"),
    TICKET_Affiliation(Constants.BASE_URL + "/tickets/?multiAffls=FL_RESIDENT"),
    URL_PASSPORT_GEN(Constants.BASE_URL + "/debug/url-passport/"),
    VACATION_INSURANCE(Constants.BASE_URL + "/checkout/insurance/"),
    VISA_CARD(Constants.BASE_URL + "/visa-card/florida/"),
    VISA_CARD_CA(Constants.BASE_URL + "/visa-card/california/"),
    VISA_CARD_REWARDS(Constants.BASE_URL + "/visa-card/florida/rewards/"),
    VISA_CARD_CA_REWARDS(Constants.BASE_URL + "/visa-card/california/rewards/"),
    WATERPARK(Constants.BASE_URL + "/tickets/water-parks/"),
    MEMORY_MAKER(Constants.BASE_URL + "/memory-maker/"),
    CALENDARS(Constants.BASE_URL + "/calendars/"),
    UNKNOWN("INVALID_URL"),
    ANNUAL_PASS_PURCHASE (Constants.BASE_URL + "/debug/url-passport/"),
    ANNUAL_PASS_DELIVERY(Constants.BASE_URL + "/reservations/travel-agents/checkout/delivery/"),
    ANNUAL_PASS_PAYMENT(Constants.BASE_URL + "/reservations/travel-agents/checkout/payment/"), 
    ANNUAL_PURCHASE_CONFIMATION(Constants.BASE_URL + "/reservations/travel-agents/checkout/confirmation/");
    
    
    private String url = null;
    
    private WdwUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String getUrl() {
        return url;
    }
    
    @Override
    public boolean equalTo(WdprUrl url) {
        return this.url.equals(url.getUrl());
    }
    
    @Override
    public boolean equalTo(String url) {
        return this.url.equals(url);
    }
    
    @Override
    public boolean contains(String url) {
        return this.url.contains(url);
    }
    
    @Override
    public boolean isValid() {
        return !this.equals(UNKNOWN);
    }
    
    @Override
    public String toString() {
        return "WdwUrl [" + this.name() + " | " + this.url + "]";
    }
}


