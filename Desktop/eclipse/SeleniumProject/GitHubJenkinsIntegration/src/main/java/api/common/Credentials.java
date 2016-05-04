package api.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import api.restServices.core.SessionData;

/**
 * Setup for basic API tests. Generates AUTHz token ID needed to move through API methods.
 * Getters and Setters to grab Token.
 * 
 * @author Jarad.Medbery@Disney.com
 */
public class Credentials extends SessionData {
	
	public JSONObject jObj;
	
	public int retCode;
	
	public Credentials() {
	}

	public void setToken() throws Exception{
		
	    //Method to make request call to stg.authorization site to get token ID. 	
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpPost httpPost = new HttpPost("https://stg.authorization.go.com/token");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
		
		String bodyRequest = "grant_type=client_credentials&client_id=WDPRO-NGE.PEPCOM-STAGE&client_secret=E2050034-0C95-11E1-872D-1BB84724019B&scope=RETURN_ALL_CLIENT_SCOPES";
		HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity());

		System.out.println(result);
		
		// Pattern matcher to parse tokenID from the response.	
		Pattern pattern = Pattern.compile("\":\"(.*?)\",\"");
		Matcher matcher = pattern.matcher(result);
		
		if (matcher.find()) {
		    
			result = matcher.group(1);
			
			// Assign token value.
			this.token = result;
			
			System.out.println("Token ID :" + result);
			
		} else {
			this.token = "YougotnoToken";
		}
	}
	
	public void setTokenProd() throws Exception{
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpPost httpPost = new HttpPost("https://authorization.go.com/token");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
		
		String bodyRequest = "client_id=WDPRO-NGE.PEPCOM-PROD&scope=RETURN_ALL_CLIENT_SCOPES&grant_type=password&username=jarad.medbery%40disney.com&password=111111";
		HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
		httpPost.setEntity(entity);
		HttpResponse response = client.execute(httpPost);
		String result = EntityUtils.toString(response.getEntity());

		System.out.println(result);
		
		// Pattern matcher to parse tokenID from the response.	
		Pattern pattern = Pattern.compile("\":\"(.*?)\",\"");
		Matcher matcher = pattern.matcher(result);
		if (matcher.find()) {
			result = matcher.group(1);
			// Assign token value.
			this.token = result;
			
			System.out.println("Token ID :" + result);			
			
		}
			else {
				this.token = "YougotnoToken";
			}
			
		}
	
	public void configureServerInstance() throws Exception{
		
		String myUrl = "http://68.71.213.88/pepinstances/json";

		HttpClient client = HttpClientBuilder.create().build();
		System.out.println("putDeliveryOption myUrl: " + myUrl);
		HttpPost httpPost = new HttpPost(myUrl);

		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("X-Conversation-Id", "BeepBeep123456");
		httpPost.addHeader("Content-Type", "application/json");
		httpPost.addHeader("X-API-Version", "1");
		httpPost.addHeader("Accept", "application/json;apiversion=1");
		httpPost.addHeader("Authorization", "BEARER " + token);
		httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

		String bodyRequest = "";

		HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
		httpPost.setEntity(entity);
		System.out.println("Getting instance version: "
				+ EntityUtils.toString(entity));

		HttpResponse response = client.execute(httpPost);

		retCode = response.getStatusLine().getStatusCode();

		HttpEntity eResponse = response.getEntity();
		String responseBody = EntityUtils.toString(eResponse);
		jObj = new JSONObject(responseBody);
		System.out.println("Instance result: " + responseBody);

		JSONObject lampStack = jObj.getJSONObject("LAMPSTACK");
		System.out.println(jObj.getJSONObject("LAMPSTACK"));
		System.out.println(lampStack.getString("LIVE INSTANCE"));
		String liveInstance = (lampStack.getString("LIVE INSTANCE"));
		System.out.println("This is the value of live Instance: "+liveInstance);
		
		if ((lampStack.getString("LIVE INSTANCE")).equals("A")) {
			
		    setServerInstance("A");
		    
		} else if ((lampStack.getString("LIVE INSTANCE")).equals("B")) {
			
		    setServerInstance("B");

		} else {
		    
		    setServerInstance("B");
		}
	}
	
	public String setUserToken(String userName) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://stg.authorization.go.com/token");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "BeepBeep1234");

        String bodyRequest = "grant_type=password&username="
                             + userName
                             + "&password=111111&client_id=WDPRO-NGE.PEPCOM-STAGE&client_secret=E2050034-0C95-11E1-872D-1BB84724019B&scope=RETURN_ALL_CLIENT_SCOPES";
        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());

        System.out.println(result);

        Pattern pattern = Pattern.compile("\":\"(.*?)\",\"");
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(1);

            this.token = result;

            System.out.println("Token ID :" + result);

        } else {
            this.token = "YougotnoToken";
        }
        
        return result;
	}
	
	public void setUserTokenProd(String userName) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://authorization.go.com/token");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", "BeepBeep1234");

        String bodyRequest = "client_id=WDPRO-NGE.PEPCOM-PROD&scope=RETURN_ALL_CLIENT_SCOPES&grant_type=password&username="
                             + userId + "&password=111111";
        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());

        System.out.println(result);

        Pattern pattern = Pattern.compile("\":\"(.*?)\",\"");
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(1);

            this.token = result;

            System.out.println("Token ID :" + result);

        } else {
            this.token = "YougotnoToken";
        }
    }
		
	public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }
	
	}

