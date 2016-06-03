package selenium.common.enums;

import selenium.common.Constants;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public enum DlrUrl implements WdprUrl {
    
    ACTIVITIES(Constants.BASE_URL + "/activities/"),
    ATTRACTIONS(Constants.BASE_URL + "/attractions/"),
    CART(Constants.BASE_URL + "/cart/"),
    CHECKOUT_CONFIRMATION(Constants.BASE_URL + "/checkout/confirmation/"),
    CHECKOUT_DELIVERY(Constants.BASE_URL + "/checkout/delivery/"),
    CHECKOUT_GUESTS(Constants.BASE_URL + "/checkout/guests/"),
    CHECKOUT_INSURANCE(Constants.BASE_URL + "/checkout/insurance/"),
    CHECKOUT_PAYMENT(Constants.BASE_URL + "/checkout/payment/"),
    CHECKOUT_REVIEW(Constants.BASE_URL + "/checkout/review/"),
    DEBUG_SESSION_DATA(Constants.BASE_URL + "/debug/session-data/"),
    DEBUG_DASHBOARD(Constants.BASE_URL + "/debug/dashboard/"),
    DINING(Constants.BASE_URL  + "/dining/"),
    DINING_RESERVATION_BILLING_INFORMATION(Constants.BASE_URL  + "/dining-reservation/billing-information/"),
    DINING_RESERVATION_REVIEW(Constants.BASE_URL   + "/dining-reservation/review/"),
    DINING_RESERVATION_CONFIRMATION(Constants.BASE_URL + "/dining-reservation/confirmation/"),
    ENTERTAINMENT(Constants.BASE_URL + "/entertainment/"),
    EVENTS_TOURS(Constants.BASE_URL + "/events-tours/"),
    HELP(Constants.BASE_URL + "/help/"),
    HOME_PAGE(Constants.BASE_URL + "/"),
    HOTELS(Constants.BASE_URL + "/hotels/"),
    HOTELS_GOOD_NEIGHBOR(Constants.BASE_URL + "/hotels/good-neighbor/"),
    PASSES(Constants.BASE_URL + "/passes/"),
    PASSES_TODAY(Constants.BASE_URL + "/passes/today/"),
    RESORT_ADD_ON_TICKET(Constants.BASE_URL + "/resort-add-ons/tickets/"),
    RECREATION(Constants.BASE_URL + "/recreation/"),
    SHOPS(Constants.BASE_URL + "/shops/"),
    TICKETS(Constants.BASE_URL + "/tickets/"),
    TICKETS_EVENTS(Constants.BASE_URL + "/tickets/events/"),
    TICKETS_PARKING(Constants.BASE_URL + "/tickets/parking/"),
    TICKETS_SOUTHERN_CALIFORNIA_CITYPASS(Constants.BASE_URL + "/tickets/southern-california-citypass/"),
    UNKNOWN("INVALID_URL");
    
    private String url = null;
    
    private DlrUrl(String url) {
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
        return "DlrUrl [" + this.name() + " | " + this.url + "]";
    }
}

