package api.booking;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import api.cart.CreateCart;
import api.common.Credentials;
import api.restServices.core.SessionData;

/**
 * Warning, this class is not thread-safe.
 */
public class Order {

    /** Warning, static member variable */
    static String orderId;
    /** Warning, static member variable */
    static String itemId;
    /** Warning, static member variable */
    static String confId;
    /** Warning, static member variable */
    public static String env = "stage";
    /** Warning, static member variable */
    private static int retCode;
    /** Warning, static member variable */
    public static JSONObject jObj;
    /** Warning, static member variable */
    public static String termsID;

    public String getOrderId() {

        System.out.println("getOrderId orderID: " + orderId);
        return Order.orderId;
    }

    public String getItemId() {

        System.out.println("getOredrId ItemID: " + itemId);
        return Order.itemId;

    }

    //
    // added 15jun14 mss
    //
    // takes a token from credentials, a cartId from createCart and a swid
    // from???
    //
    public String createOrder(String url4, String urlRequest, String token, SessionData sessionData) throws Exception {

    	System.out.println(sessionData.getSwid());
    	
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url4);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPost.addHeader("Authorization", "BEARER " + token);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
        
        String bodyRequest = urlRequest;

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("createOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);
        retCode = response.getStatusLine().getStatusCode();

        String result = EntityUtils.toString(response.getEntity());

        jObj = new JSONObject(result);
        System.out.println("createOrder Response:" + result);

        String resultorderId = result;
        String resultitemId = result;

        Pattern pattern = Pattern.compile("booking-service/orders/(.*?)\"");
        Matcher matcher = pattern.matcher(resultorderId);
        if (matcher.find()) {
            resultorderId = matcher.group(1);
        }

        Order.orderId = resultorderId;

        Pattern pattern2 = Pattern.compile("/items/(.*?)\"");
        Matcher matcher2 = pattern2.matcher(resultitemId);
        if (matcher2.find()) {
            resultitemId = matcher2.group(1);
        }

        resultitemId = resultitemId.replace("/room-requests", "");
        Order.itemId = resultitemId;
        
        return resultorderId;

    }

    public void getOrder(String token, String myUrl) throws Exception {

        HttpClient httpclient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(myUrl);
        // "https://api-latest.wdpro.starwave.com/booking-service/orders/" +
        // cartId);
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpGet.addHeader("Authorization", "BEARER " + token);
        httpGet.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        HttpResponse response = httpclient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());
        retCode = response.getStatusLine().getStatusCode();
        System.out.println("getOrderResponse :" + result);

    }

    public void addCreditCard(String token, String myUrl, String payment) throws Exception {

        

        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Authorization", "BEARER " + token);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        String sReqBody = "{\"paymentCard\": {\"cardType\": \"CreditCard\",\"cardSubType\": \"AX\",\"cardNumber\": \"377777684182882\",\"cvv2\": \"7240\",\"expirationMonth\": \"01\",\"useCardForIncidentals\": \"false\",\"expirationYear\": \"2015\",\"billingName\": {\"firstName\": \"Bruce\",\"lastName\": \"Wayne\"},\"billingAddress\": {\"type\":\"BILLING\",\"line1\": \"666 Sunset Blvd\",\"line2\": \"\",\"city\": \"Anaheim\",\"stateOrProvince\": \"CA\",\"postalCode\": \"92808\",\"country\": \"USA\"}},\"billingPhone\":{\"type\": \"home\",\"number\": \"818-549-0000\"},\"billingEmail\": \"jarad.medbery@disney.com\",\"amount\": \""
                          +payment+ "\",\"currency\": \"USD\"}";

        HttpEntity entity = new ByteArrayEntity(sReqBody.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("addCreditCard requestLine: " + EntityUtils.toString(entity));
        HttpResponse response = httpclient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        retCode = response.getStatusLine().getStatusCode();
        System.out.println("addCreditCard response: " + result);

    }

    public void setParticipants(String token, String swid, String url5) throws Exception {

        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url5);
        
        System.out.println("https://api-" + env + ".wdpro.starwave.com/booking-service/orders/" + orderId + "/items/"
                           + itemId + "/participants");
        System.out.println("This be the itemID:" + itemId);
        
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Authorization", "BEARER " + token);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        String sReqBody = "guestInfo1%5Bprefix%5D=Mr.&guestInfo1%5BfirstName%5D=Jorge&guestInfo1%5BmiddleInitial%5D=R&guestInfo1%5BlastName%5D=Rafalowski&guestInfo1%5Bsuffix%5D=&guestInfo1%5Bgender%5D=Male&guestInfo1%5Bage%5D=18&guestInfo1%5Byear%5D=1994&guestInfo1%5Bmonth%5D=July&guestInfo1%5Bday%5D=10&guestInfo1%5BparticipantId%5D=1&guestInfo2%5Bprefix%5D=Mrs.&guestInfo2%5BfirstName%5D=Patricia&guestInfo2%5BmiddleInitial%5D=R&guestInfo2%5BlastName%5D=Lopez&guestInfo2%5Bsuffix%5D=&guestInfo2%5Bgender%5D=Female&guestInfo2%5Bage%5D=18&guestInfo2%5Byear%5D=1994&guestInfo2%5Bmonth%5D=July&guestInfo2%5Bday%5D=10&guestInfo2%5BparticipantId%5D=2";

        HttpEntity entity = new ByteArrayEntity(sReqBody.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("setParticipants: " + EntityUtils.toString(entity));
        HttpResponse response = httpclient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());
        retCode = response.getStatusLine().getStatusCode();
        System.out.println("setParticipants response: " + result);

    }

    public String submitOrder(String tokenId, String myUrl) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Accept-Language", "en-us");
        httpPost.addHeader("Authorization", "BEARER " + tokenId);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        String bodyRequest = "acceptedTermsAndConditionsId="
                             + termsID
                             + "entityType%3DtermsAndConditions&contactMe=false&email=jarad.medbery%40disney.com&confirmEmail=jarad.medbery%40disney.com";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("submitOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);

        retCode = response.getStatusLine().getStatusCode();

        HttpEntity eResponse = response.getEntity();
        String responseBody = EntityUtils.toString(eResponse);
        jObj = new JSONObject(responseBody);
        System.out.println("submitOrder result: " + responseBody);

        JSONObject subObject = jObj.getJSONObject("orderItems");
        // System.out.println("getOrderItems: " + jObj.getString("orderItems"));

        JSONArray subArray = subObject.getJSONArray("entries");

        confId = subArray.getJSONObject(0).getString("confirmationNumber").toString();

        System.out.println("Confirmation Number: " + confId);
        
        return confId;
    }

    // System.out.println("Confirmation number: " +
    // subObject.getString("confirmationNumber"));

    public String getSubmitLink() throws Exception {

        try {
            JSONObject jLinks = jObj.getJSONObject("links");

            JSONObject jHref = jLinks.getJSONObject("submit");
            System.out.println("getSubmitLink href: " + jHref.getString("href"));
            return jHref.getString("href");
        } finally {

            // return "NADA";
        }
    }

    public String getAddPaymentLink() throws Exception {

        try {
            JSONObject jLinks = jObj.getJSONObject("links");

            JSONObject jHref = jLinks.getJSONObject("addPayment");
            System.out.println("getAddPaymentLink href: " + jHref.getString("href"));
            return jHref.getString("href");
        } finally {

            // return "NADA";
        }
    }

    public String getItemsLink() throws Exception {

        try {
            JSONObject jLinks = jObj.getJSONObject("links");

            JSONObject jHref = jLinks.getJSONObject("items");
            System.out.println("getItemsLink href: " + jHref.getString("href"));
            return jHref.getString("href");
        } finally {

            // return "NADA";
        }
    }

    public String getOrderLink() throws Exception {

        try {
            JSONObject jLinks = jObj.getJSONObject("links");

            JSONObject jHref = jLinks.getJSONObject("self");
            System.out.println("getOrderLink href: " + jHref.getString("href"));
            return jHref.getString("href");
        } finally {

            // return "NADA";
        }
    }

    public String getTermsID() throws Exception {

        try {
            termsID = jObj.getString("bookingTermsAndConditions");
            System.out.println("getTermsID Terms: " + jObj.getString("bookingTermsAndConditions"));
            return termsID;
        } finally {

            // return "NADA";
        }
    }

    public int getretCode() {

        return retCode;
    }

    public void setOrderId() throws Exception {

        Credentials credentials = new Credentials();
        String token = credentials.getToken();

        CreateCart createCart = new CreateCart();
        String cartId = createCart.getCartId();

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://api-" + env + ".wdpro.starwave.com/booking-service/orders");

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPost.addHeader("Authorization", "BEARER " + token);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
        
        String bodyRequest = "cart-link=https://api-" + env + ".wdpro.starwave.com/cart-service/carts/" + cartId
                             + "&swid=DBF03C52-E176-4FBE-B03C-52E176AFBEAC";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());

        System.out.println("Order Response:" + result);

        String resultorderId = result;
        String resultitemId = result;

        Pattern pattern = Pattern.compile("booking-service/orders/(.*?)\"");
        Matcher matcher = pattern.matcher(resultorderId);
        if (matcher.find()) {
            resultorderId = matcher.group(1);
        }

        Order.orderId = resultorderId;

        Pattern pattern2 = Pattern.compile("/items/(.*?)\"");
        Matcher matcher2 = pattern2.matcher(resultitemId);
        if (matcher2.find()) {
            resultitemId = matcher2.group(1);
        }

        Order.itemId = resultitemId;
    }

    public void getOrderItems(String token, String myUrl) throws Exception {

        HttpClient httpclient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(myUrl);
        // "https://api-latest.wdpro.starwave.com/booking-service/orders/" +
        // cartId);
        httpGet.addHeader("Connection", "keep-alive");

        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpGet.addHeader("Authorization", "BEARER " + token);
        httpGet.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        HttpResponse response = httpclient.execute(httpGet);
        String result = EntityUtils.toString(response.getEntity());
        retCode = response.getStatusLine().getStatusCode();
        System.out.println("getOrderItemsResponse :" + result);
    }

    public void putDeliveryOption(String token) throws Exception {

        String myUrl = "https://api-" + env + ".wdpro.starwave.com/booking-service/orders/" + orderId
                       + "/?fields=deliveryOption";
        // String myUrl =
        // "https://api-latest.wdpro.starwave.com/booking-service/orders/"+
        // orderId +"/?fields=deliveryOption " ;

        HttpClient httpclient = HttpClientBuilder.create().build();
        System.out.println("putDeliveryOption myUrl: " + myUrl);
        HttpPut httpPut = new HttpPut(myUrl);

        httpPut.addHeader("Connection", "keep-alive");
        httpPut.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.addHeader("X-API-Version", "1");
        httpPut.addHeader("Accept", "application/json;apiversion=1");
        httpPut.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPut.addHeader("Authorization", "BEARER " + token);
        httpPut.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        // String sReqBody = "\"selectedDeliveryOption\":\"M0009\"";
        String sReqBody = "{\"selectedDeliveryOption\":\"MC004\"}";

        HttpEntity entity = new ByteArrayEntity(sReqBody.getBytes("UTF-8"));
        httpPut.setEntity(entity);
        System.out.println("putDeliveryOption requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = httpclient.execute(httpPut);
        String result = EntityUtils.toString(response.getEntity());
        retCode = response.getStatusLine().getStatusCode();
        System.out.println("putDeliveryOption result: " + result);
        System.out.println("putDeliveryOption response: " + response);
    }

    public void putShippingOption(String token, String url6) throws Exception {

        
        // String myUrl =
        // "https://api-latest.wdpro.starwave.com/booking-service/orders/"+
        // orderId +"/?fields=deliveryOption " ;

        HttpClient httpclient = HttpClientBuilder.create().build();
        System.out.println("putDeliveryOption myUrl: " + url6);
        HttpPut httpPut = new HttpPut(url6);

        httpPut.addHeader("Connection", "keep-alive");
        httpPut.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.addHeader("X-API-Version", "1");
        httpPut.addHeader("Accept", "application/json;apiversion=1");
        httpPut.addHeader("X-Conversation-Id", "TESTJARAD123");
        httpPut.addHeader("Authorization", "BEARER " + token);
        httpPut.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        // String sReqBody = "\"selectedDeliveryOption\":\"M0009\"";
        String sReqBody = "{\"shippingAddress\": {\"line1\": \"1026 Grand Central Ave\",\"line2\": \"\",\"city\": \"Glendale\",\"stateOrProvince\": \"CA\",\"postalCode\": \"91201\",\"country\": \"USA\"},\"shippingPhone\": {\"type\": \"home\",\"number\": \"123-456-7890\",\"extension\": \"\"},\"shippingEmail\": \"jarad.medbery@disney.com\",\"shipToName\": {\"prefix\": \"Mr.\",\"firstName\": \"John\",\"middleName\": \"\",\"lastName\": \"Wallace\",\"suffix\": \"\"}}";

        HttpEntity entity = new ByteArrayEntity(sReqBody.getBytes("UTF-8"));
        httpPut.setEntity(entity);
        System.out.println("putDeliveryOption requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = httpclient.execute(httpPut);
        String result = EntityUtils.toString(response.getEntity());
        retCode = response.getStatusLine().getStatusCode();
        System.out.println("putDeliveryOption result: " + result);
        System.out.println("putDeliveryOption response: " + response);
    }

}

