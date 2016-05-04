package api.cart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class CreateCart {

    private String cartId;
    /** Warning, static member variable */
    public static int retCode = 0;
    /** Warning, static member variable */
    //public static JSONObject jObj;
    public String env = "stage";

    public String getCartId() {

        return this.cartId;
    }

    public void setCartId(String url2, String token) throws Exception {

        System.out.println("setCartId Token: " + token);
        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(url2);
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-Conversation-Id", "JARADMEDBERY");
        httpPost.addHeader("Authorization", "BEARER " + token);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
        String bodyRequest = "{}";
        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);

        HttpResponse response = client.execute(httpPost);

        retCode = response.getStatusLine().getStatusCode();

        HttpEntity eResponse = response.getEntity();

        /*String responseBody = EntityUtils.toString(eResponse);
        System.out.println("setCartId responseBody: " + responseBody);
        jObj = new JSONObject(responseBody);
        System.out.println("setCartId cartId: " + jObj.getString("cartId"));

        if (jObj.optBoolean("cartId")) {
            cartId = "YougotnoCartID";
        } else {
            cartId = jObj.getString("cartId");
        }*/

        System.out.println("Cart ID : " + cartId);
    }

    public void getCart(String token, String myUrl) throws Exception {

        HttpClient httpclient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("https://api-" + env + ".wdpro.starwave.com/cart-service/carts/" + cartId);
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.addHeader("X-Conversation-Id", "BeepBeep123456");
        httpGet.addHeader("Authorization", "BEARER " + token);
        httpGet.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
        String bodyRequest = "{}";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        System.out.println("getCart requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = httpclient.execute(httpGet);
        retCode = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("getCart result:" + result);
    }

    public void getCartTest(String token) throws Exception {

        HttpClient httpclient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("https://api-" + env + ".wdpro.starwave.com/cart-service/carts/" + cartId);
        httpGet.addHeader("Content-Type", "application/json");
        httpGet.addHeader("Connection", "keep-alive");
        httpGet.addHeader("X-Conversation-Id", "BeepBeep123456");
        httpGet.addHeader("Authorization", "BEARER " + token);
        httpGet.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
        String bodyRequest = "{}";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        System.out.println("getCart requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = httpclient.execute(httpGet);
        retCode = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("getCart result:" + result);

    }

    public String getaddItemLink() throws Exception {

        try {
            //JSONObject jLinks = jObj.getJSONObject("links");

            //JSONObject jHref = jLinks.getJSONObject("addItem");
            //System.out.println("getaddItemLink href: " + jHref.getString("href"));
            //return jHref.getString("href");
        } finally {

            // return "NADA";
        }
    }

    public String getaddItemsLink() throws Exception {

        try {
            /*JSONObject jLinks = jObj.getJSONObject("links");

            JSONObject jHref = jLinks.getJSONObject("addItems");
            System.out.println("getaddItemsLink href: " + jHref.getString("href"));
            return jHref.getString("href");*/
        } finally {

            // return "NADA";
        }
    }

    public String getUpdateCartLink() throws Exception {

        try {
           /* JSONObject jLinks = jObj.getJSONObject("links");

            JSONObject jHref = jLinks.getJSONObject("updateCart");
            System.out.println("getUpdateCartLink href: " + jHref.getString("href"));
            return jHref.getString("href");*/
        } finally {

            // return "NADA";
        }
    }

    public void updateItem(String token, String myUrl) throws Exception {

        String quant = "2";
        String sReqBody = "{ \"items\" : [ { \"type\" : \"ticket\", \"components\" : "
                          + "{ \"tickets\" : { \"products\" : "
                          + "[{\"id\":\"water-park_1_A_0_0_RF_AF_SOF\",\"quantity\":" + quant
                          + ",\"bookingContextId\":\"progenstr\"}] } } } ] }";

        //
        // try and build the json object
        //
        //

        // JsonFactory f = new JsonFactory();
        // ObjectMapper mapper = new ObjectMapper();
        // JsonNode node = mapper.readTree(sReqBody) ;
        // mapper.writeValueAsString(sReqBody) ;

        //
        // end of expirament
        //

        System.out.println("updateItem sReqBody: " + sReqBody);
        // System.out.println("updateItem jReqBody: " + jReqBody ) ;

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("X-Conversation-Id", "JARADMEDBERY");
        httpPost.addHeader("Authorization", "BEARER " + token);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        HttpEntity entity = new ByteArrayEntity(sReqBody.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("updateItem requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);
        retCode = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(" updateItem result   " + result);
    }

    public void addItem(String token, String myUrl) throws Exception {

        String sReqBody = "{ \"items\" : [ { \"type\" : \"ticket\", \"components\" : "
                          + "{ \"tickets\" : { \"products\" : "
                          + "[{\"id\":\"water-park_1_A_0_0_RF_AF_SOF\",\"quantity\":1,\"bookingContextId\":\"progenstr\"}] } } } ] }";

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("X-Conversation-Id", "JARADMEDBERY");
        httpPost.addHeader("Authorization", "BEARER " + token);
        httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
        // String bodyRequest =
        // "{ \"items\" : [ { \"type\" : \"ticket\", \"components\" : { \"tickets\" : { \"products\" : [{\"id\":\"water-park_1_A_0_0_RF_AF_SOF\",\"quantity\":1,\"bookingContextId\":\"progenstr\"}] } } } ] }";

        HttpEntity entity = new ByteArrayEntity(sReqBody.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("addItem requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);
        retCode = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(" addItem result   " + result);

    }

    public void updateCartRoom(String token, String url3, String availId, String cartId, String arriveDate,
                               String departDate) throws Exception {

        String sReqBody = "{\"items\" : [ { \"packageId\" : \"IEG\", \"association\" : \"STD_GST\", \"type\" : \"vacationoffer\", \"components\" : { \"room\" : { \"availabilityId\" : \""
                          + availId
                          + "\", \"destinationId\" : \"80007798\", \"resortId\" : \"80010399\", \"roomTypeId\" : \"80011149\", \"accessible\" : false, \"partyMix\" : { \"adultCount\" : 2, \"childCount\" : 0, \"nonAdultAges\" : [] }, \"reservationDates\" : { \"start\" : \""
                          + arriveDate
                          + "\", \"end\" : \""
                          + departDate
                          + "\" }, \"prioritizeEarlyArrival\" : false } } } ] } ";

        HttpClient client = HttpClientBuilder.create().build();
        HttpPut httpPut = new HttpPut(url3);

        System.out.println("Final version of url3:" + url3);

        httpPut.addHeader("Connection", "keep-alive");
        httpPut.addHeader("X-Conversation-Id", "BeepBeep123456");
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.addHeader("X-API-Version", "1");
        httpPut.addHeader("Accept", "application/json;apiversion=1");
        httpPut.addHeader("X-Conversation-Id", "JARADMEDBERY");
        httpPut.addHeader("Authorization", "BEARER " + token);
        httpPut.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
        
        HttpEntity entity = new ByteArrayEntity(sReqBody.getBytes("UTF-8"));
        httpPut.setEntity(entity);
        System.out.println("addItem requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPut);
        retCode = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(" addItem result   " + result);
    }

    public int getretCode() {
        return retCode;
    }
}


