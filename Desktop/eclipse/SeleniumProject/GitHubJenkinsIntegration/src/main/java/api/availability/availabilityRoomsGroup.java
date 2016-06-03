package api.availability;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import api.common.Credentials;
import api.restServices.core.SessionData;

public class availabilityRoomsGroup {
	
	public static int retCode = 0 ;
	public static JSONObject jObj ;
	public static String availId  ;
	public Credentials credentials;
	
	public availabilityRoomsGroup() {
		
	}
	
	public availabilityRoomsGroup(Credentials credentials){
		this.credentials = credentials;
	}
	

    public void getGroupedResortAvailability(String url, String tokenUser, String arriveDate, String endDate, String storeId) throws Exception {
		
		//String storeId = "wdw" ;
		
		//String url = "https://" + credentials.getBaseUrl() + "/availability-service/grouped-resort-availability" ;
    	System.out.println("This is the token for userID hopefuly: " +tokenUser);
		System.out.println("getGroupedResortAvailability URL: " + url) ;
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpPost httpPost = new HttpPost(url) ;
	
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
		httpPost.addHeader("Accept", "application/json");
		httpPost.addHeader("Accept", "application/json;apiversion=1");
		httpPost.addHeader("Accept-Language", "en-us");		
		httpPost.addHeader("Authorization", "BEARER " + tokenUser);
		httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
		
		String bodyRequest = "{ "+
		"\"storeId\" : \"" +storeId+ "\"," +
		"\"disneyOwned\" : true, "+
		"\"checkInDate\" : \""+arriveDate+ "\",    \"checkOutDate\" : \"" +endDate+"\","+
		"\"partyMix\" : {      \"adultCount\" : 2,        \"childCount\" : 0, "+
		"\"nonAdultAges\" : []  }, "+
		"\"accessible\" : false, "+
		"\"preferredResortId\" : \"80010389;entityType=resort\", "+
		"\"components\": [   {    \"category\" : \"room\"      }       ],  "+
		"\"affiliations\": [\"STD_GST\"], "+
		"\"region\" : \"US\"   "+
		"}" ;
		
	
			
		HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
		httpPost.setEntity(entity);
		System.out.println("getGroupedResortAvailability requestLine: " + EntityUtils.toString(entity));
		
		HttpResponse response = client.execute(httpPost);
		
		retCode = response.getStatusLine().getStatusCode() ;
		
		String result = EntityUtils.toString(response.getEntity());
		
		jObj = new JSONObject(result) ;
		System.out.println("getGroupedResortAvailability returnCode: " + retCode) ;
		System.out.println("getGroupedResortAvailability result: " + response) ;
		System.out.println("getGroupedResortAvailability result1: " + jObj.toString()) ;
		
	}
    
    public void getGroupedRoomTypeAvailability(String url2, String tokenId, String storeId, String arriveDate, String departDate) throws Exception {
    	
    	//String storeId = "wdw" ;
    	
    	//String url = "https://api-latest.wdpro.starwave.com/availability-service/grouped-resort-availability" ;
    	System.out.println("getGroupedResortAvailability URL: " + url2) ;
    	HttpClient client = HttpClientBuilder.create().build();
    	
    	HttpPost httpPost = new HttpPost(url2) ;

    	httpPost.addHeader("Content-Type", "application/json");
    	httpPost.addHeader("Connection", "keep-alive");
    	httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
    	httpPost.addHeader("Accept", "application/json");
    	httpPost.addHeader("Accept", "application/json;apiversion=1");
    	httpPost.addHeader("Accept-Language", "en-us");		
    	httpPost.addHeader("Authorization", "BEARER " + tokenId);
    	httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");
    	
    	String bodyRequest = "{ "+
    	"\"storeId\" : \"" +storeId + "\"," +
    	"\"disneyOwned\" : true, "+
    	"\"resortId\" : \"80010389;entityType=resort\","+
    	"\"checkInDate\" : \""+arriveDate + "\",    \"checkOutDate\" : \"" +departDate +"\","+
    	"\"partyMix\" : {      \"adultCount\" : 2,        \"childCount\" : 1, "+
    	"\"nonAdultAges\" : [    {     \"age\" : 10   }   ]  }, "+
    	"\"accessible\" : false, "+
    	
    	
    	"\"affiliations\": [\"STD_GST\"], "+
    	"\"region\" : \"US\"   "+
    	"}" ;
    	

    		
    	HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
    	httpPost.setEntity(entity);
    	System.out.println("getGroupedResortAvailability requestLine: " + EntityUtils.toString(entity));
    	
    	HttpResponse response = client.execute(httpPost);
    	
    	retCode = response.getStatusLine().getStatusCode() ;
    	
    	String result = EntityUtils.toString(response.getEntity());
    	
    	jObj = new JSONObject(result) ;
    	System.out.println("getGroupedResortAvailability returnCode: " + retCode) ;
    	System.out.println("getGroupedResortAvailability result: " + response) ;
    	System.out.println("getGroupedResortAvailability result1: " + jObj.toString()) ;
    	
    }

    public int getretCode() {
		
		return retCode;
	}

    public JSONObject getjOBJ() {
	
	return jObj ;
    }
	
    public String getAvailId() throws Exception {
		
		availId = jObj.getString("availabilityId") ;
		return availId ;
	}
}

