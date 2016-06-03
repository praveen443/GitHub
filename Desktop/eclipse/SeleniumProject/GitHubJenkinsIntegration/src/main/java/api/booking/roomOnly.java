package api.booking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import api.availability.availabilityRoomsGroup;
import api.cart.CreateCart;
import api.common.Credentials;
import api.common.DatePicker;
import api.restServices.core.SessionData;
import selenium.common.Log;
import selenium.common.enums.Environment;

public class roomOnly {
		

   	public static String bookRoomOnly(WebDriver driver, SessionData sessionData) throws Exception {
   	
   	
   	String orderID = "";
   	String confID = "";
   	String itemID = "";
    String arriveDate = "";
  	String departDate = "";
   	JSONObject jObj = null;
   	String availId = "" ;
   	String expirationDT = "" ;
   	String cartId= "" ;
   	String credToken = "";
   
   	   
   		
   	DatePicker date = new DatePicker();
		
	arriveDate = date.setStartDate();
	departDate = date.setEndDate();
	
	sessionData.setArriveDate(arriveDate);
	sessionData.setDepartDate(departDate);
		
	Credentials credentials = new Credentials();
	
	if (Environment.isStage() || Environment.isLatest() || Environment.isShadow()) {
		Log.log(driver).info("Lower environment going to set the token now.");
		credentials.setUserToken(sessionData.getUserId());
		credToken = credentials.getToken();
	} else {
		Log.log(driver).info("Upper environment going to set the token now.");
		credentials.setUserTokenProd(sessionData.getUserId());
		credToken = credentials.getToken();
	}
	
	sessionData.setToken(credToken);
	
	String url = "https://"+credentials.getBaseUrl()+"/availability-service/grouped-resort-availability";
	
	
	
	// getting Auth token value for test
	//tokenId = credentials.getToken();
	
	availabilityRoomsGroup avail = new availabilityRoomsGroup() ;
	
	avail.getGroupedResortAvailability(url, sessionData.getToken(), sessionData.getArriveDate(), sessionData.getDepartDate(), sessionData.getStoreId());
	
	assertEquals("Check http return",200,avail.getretCode()) ;
	
	assertNull("jObj should now be null",jObj) ;
	jObj = avail.getjOBJ() ;
	assertNotNull("jObj should not be null",jObj) ;
	
	//
	// other validations on the returned JSON here
	//
	
	availId = jObj.getString("availabilityId") ;
	expirationDT = jObj.getString("expirationDatetime") ;
	System.out.println("WDW Availability Id: " + availId + " until: " + expirationDT);
	
	jObj= null ;

	CreateCart cart = new CreateCart();
	
	String url2 = "https://"+credentials.getBaseUrl()+"/cart-service/scopes/wdw/carts";
	
	cart.setCartId(url2, sessionData.getToken());
	assertEquals(200,cart.getretCode()) ;
	
	cartId = cart.getCartId();
	assertFalse(cartId == "YougotnoCartID") ;
	assertEquals(200,cart.getretCode());
	sessionData.setCartId(cartId);
	
	String url3 = "https://"+credentials.getBaseUrl()+"/cart-service/scopes/wdw/carts/"+cartId+"";
	
	System.out.println("This is the cart ID"+cartId);
	cart.updateCartRoom(sessionData.getToken(), url3, availId, cartId, arriveDate, departDate);
	
	Order order = new Order() ;
	
	String url4 = "https://"+credentials.getBaseUrl()+"/booking-service/orders";
	String urlRequest = "cart-link=https://"+credentials.getBaseUrl()+"/carts/" + sessionData.getCartId()
            + "&swid=" + sessionData.getSwid();
	order.createOrder(url4,urlRequest,sessionData.getToken(),sessionData) ;
	assertEquals(200,order.getretCode()) ;
	
	orderID = order.getOrderId() ;
	itemID = order.getItemId();
	
	sessionData.setOrderId(orderID);
	
	sessionData.setAvailabilityId(availId);
	
	order.getOrderItems(sessionData.getToken(), order.getItemsLink()) ;
	assertEquals(200,order.getretCode()) ;
	
	String payment = "1470.39";
	order.addCreditCard(sessionData.getToken(),order.getAddPaymentLink(),payment) ;
	assertEquals(200,order.getretCode()) ;

	order.getOrder(sessionData.getToken(), order.getOrderLink()) ;
	assertEquals(200,order.getretCode()) ;
	
	order.getOrderItems(sessionData.getToken(), order.getItemsLink()) ;
	assertEquals(200,order.getretCode()) ;
	
	
	order.getOrder(sessionData.getToken(), order.getOrderLink()) ;
	assertEquals(200,order.getretCode()) ;
	
	order.getTermsID() ;
	assertEquals(200,order.getretCode()) ;
	
	String url5 = "https://"+credentials.getBaseUrl()+"/booking-service/orders/" + sessionData.getOrderId()
        + "/items/" + itemID + "/participants";
	
	order.setParticipants(sessionData.getToken(), sessionData.getSwid(), url5);
	assertEquals(200,order.getretCode()) ;
	
	String url6 = "https://"+credentials.getBaseUrl()+"/booking-service/orders/" + orderID
            + "/?fields=shippingAddress,shippingPhone,shippingEmail,shipToName";
	
	order.putShippingOption(sessionData.getToken(), url6);
	assertEquals(200,order.getretCode());
	
	confID = order.submitOrder(sessionData.getToken(), order.getSubmitLink()) ;
	assertEquals(200,order.getretCode()) ;
	
	return confID;
   	}
}
