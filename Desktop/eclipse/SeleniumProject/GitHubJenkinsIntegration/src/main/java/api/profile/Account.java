package api.profile;

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

import api.common.Credentials;
import selenium.common.enums.Environment;

public class Account {

    public String userId;
    public JSONObject jObj;
    private String swid;
    private String userToken;
    private String confId;
    private int retCode;
    public Credentials credentials;
    public String convoId = "HELPMEPLEASE";

    public Account() {
    }

    public Account(Credentials credentials) {
        this.credentials = credentials;
    }

    public void createUserName() throws Exception {
        String randomFirst = Long.toHexString(Double.doubleToLongBits(Math.random()));
        String randomSecond = Long.toHexString(Double.doubleToLongBits(Math.random()));
        String randomThird = Long.toHexString(Double.doubleToLongBits(Math.random()));

        String userName = (randomFirst + randomSecond + randomThird + "@here.com");

        this.userId = userName;

    }

    public void setUserName(String userName) {
        this.userId = userName;
    }

    public String getUserName() throws Exception {
        System.out.println("User name:" + this.userId);
        return this.userId;
    }

    public String getSwid() {

        System.out.println("getSwid: " + swid);
        return swid;

    }

    public String getToken() {

        return this.userToken;
    }

    public int getretCode() {

        return retCode;
    }

    public void setUserToken() throws Exception {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://stg.authorization.go.com/token");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);

        String bodyRequest = "grant_type=password&username="
                             + userId
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

            this.userToken = result;

            System.out.println("Token ID :" + result);

        } else {
            this.userToken = "YougotnoToken";
        }
    }

    public void setUserTokenProd() throws Exception {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://authorization.go.com/token");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);

        String bodyRequest = "client_id=WDPRO-NGE.PEPCOM-PROD&scope=RETURN_ALL_CLIENT_SCOPES&grant_type=password&username="
                             +userId+"&password=111111";
        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity());

        System.out.println(result);

        Pattern pattern = Pattern.compile("\":\"(.*?)\",\"");
        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(1);

            this.userToken = result;

            System.out.println("Token ID :" + result);

        } else {
            this.userToken = "YougotnoToken";
        }
    }

    public void createAccount(String token) throws Exception {

    	String myUrl;
    	
    	//env2 does not use https
    	if (Environment.isENV2())
    		myUrl = "http://"+credentials.getBaseUrl()+"/profile-service/guests/account";
    	else
    		myUrl = "https://"+credentials.getBaseUrl()+"/profile-service/guests/account";

        HttpClient client = HttpClientBuilder.create().build();
        System.out.println("putDeliveryOption myUrl: " + myUrl);
        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Authorization", "BEARER " + token);

        if (Environment.isShadow() || Environment.isLatest() || Environment.isStage()|| Environment.isENV2() || Environment.isENV4()) {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        } else if (Environment.isProduction()) {
            if (credentials.getServerInstance().equals("A")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else if (Environment.isSoftLaunch()) {
            if (credentials.getServerInstance().equals("B")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "CCCCCCCCCCCCCCCCCCCCCCCCC");
        }

        String bodyRequest = "{\"name\":{\"title\":\"Mr.\",\"firstName\":\"jarad\",\"middleName\":\"\",\"lastName\":\"medbery\",\"suffix\":\"\"},\"birthDate\":\"1976-02-05\",\"email\":\""
                             + userId
                             + "\",\"password\":\"111111\",\"passwordConfirm\":\"111111\",\"countryCode\":\"US\",\"siteCountryCode\":\"\",\"languageCode\":\"\",\"affiliateId\":\"wdis_world\",\"templateId\":\"200\",\"registrationBeaconId\":\"5634\",\"registrationBeaconKey\":\"8701375955696062161\",\"registrationCampaignParams\":[{\"campaignParamKey\":\"firstname\",\"campaignParamValue\":\"jarad\"},{\"campaignParamKey\":\"lastname\",\"campaignParamValue\":\"medbery\"}]}";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("Create Account: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);
        retCode = response.getStatusLine().getStatusCode();

        HttpEntity eResponse = response.getEntity();
        String responseBody = EntityUtils.toString(eResponse);
        jObj = new JSONObject(responseBody);
        System.out.println("Create Account result: " + responseBody);

        String userSwid = jObj.getString("swid");
        System.out.println(jObj.getString("swid"));
        String userSwidClean1 = userSwid.replace("{", "");
        String userSwidClean2 = userSwidClean1.replace("}", "");
        System.out.println("Removing curly braces from swid:" + userSwidClean2);
        swid = userSwidClean2;
    }

    public void updateAddress() throws Exception {
    	String myUrl;
    	
    	//env2 does not use https
    	if (Environment.isENV2())
    		myUrl = "http://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/addresses";
    	else
    		myUrl = "https://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/addresses";

        HttpClient client = HttpClientBuilder.create().build();
        System.out.println("updateUser myUrl: " + myUrl);

        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Accept-Language", "e");
        httpPost.addHeader("Authorization", "BEARER " + userToken);

        if (Environment.isShadow() || Environment.isLatest() || Environment.isStage() || Environment.isENV2() || Environment.isENV4()) {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        } else if (Environment.isProduction()) {
            if (credentials.getServerInstance().equals("A")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else if (Environment.isSoftLaunch()) {
            if (credentials.getServerInstance().equals("B")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "CCCCCCCCCCCCCCCCCCCCCCCCC");
        }

        String bodyRequest = "{\"line1\": \"200 Celebration Place\",    \"city\" : \"Celebration\",        \"stateOrProvince\" : \"FL\",        \"postalCode\" : \"34747\",        \"country\" : \"US\",    \"type\" :\"SHIPPING\"}";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));
        httpPost.setEntity(entity);
        System.out.println("submitOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);
        System.out.println(response);
        retCode = response.getStatusLine().getStatusCode();

        HttpEntity eResponse = response.getEntity();
        String responseBody = EntityUtils.toString(eResponse);
        jObj = new JSONObject(responseBody);
        System.out.println("submitOrder result: " + responseBody);
    }

    public void updateQuestions() throws Exception {
    	
    	String myUrl;
    	
    	//env2 does not use https
    	if (Environment.isENV2())
    		myUrl = "http://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/security-questions";
    	else
    		myUrl = "https://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/security-questions";
    	
        HttpClient client = HttpClientBuilder.create().build();
        System.out.println("updateUser myUrl: " + myUrl);

        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Accept-Language", "e");
        httpPost.addHeader("Authorization", "BEARER " + userToken);

        if (Environment.isShadow() || Environment.isLatest() || Environment.isStage() || Environment.isENV2() || Environment.isENV4()) {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        } else if (Environment.isProduction()) {
            if (credentials.getServerInstance().equals("A")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else if (Environment.isSoftLaunch()) {
            if (credentials.getServerInstance().equals("B")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "CCCCCCCCCCCCCCCCCCCCCCCCC");
        }

        String bodyRequest = "[{\"questionCode\" : \"EN_CITY_BORN\", \"answer\" : \"Orlando\"},    {\"questionCode\" : \"EN_FAVORITE_BAND\", \"answer\" :  \"Shasta\"}]";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));

        httpPost.setEntity(entity);
        System.out.println("submitOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);

        System.out.println("updateUser Response:" + response);

        retCode = response.getStatusLine().getStatusCode();
    }

    public void updateSubscription() throws Exception {
    	
    	String myUrl;
    	
    	//env2 does not use https
    	if (Environment.isENV2())
    		myUrl = "http://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/subscriptions/bulk-update";
    	else
    		myUrl = "https://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/subscriptions/bulk-update";
    	
        HttpClient client = HttpClientBuilder.create().build();
        System.out.println("updateUser myUrl: " + myUrl);

        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Accept-Language", "e");
        httpPost.addHeader("Authorization", "BEARER " + userToken);

        if (Environment.isShadow() || Environment.isLatest() || Environment.isStage() || Environment.isENV2() || Environment.isENV4()) {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        } else if (Environment.isProduction()) {
            if (credentials.getServerInstance().equals("A")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else if (Environment.isSoftLaunch()) {
            if (credentials.getServerInstance().equals("B")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "CCCCCCCCCCCCCCCCCCCCCCCCC");
        }

        String bodyRequest = "[{\"listCode\":\"WDIGFamilySites\",\"subscribe\":true},{\"listCode\":\"WDPRNews\",\"subscribe\":true}]";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));

        httpPost.setEntity(entity);
        System.out.println("submitOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);

        System.out.println("updateUser Response:" + response);

        retCode = response.getStatusLine().getStatusCode();
    }

    public void updateTerms1() throws Exception {

        // For DLR, use DisneylandResort instead of wdis_world as the affiliate
        // name.
    	
    	String myUrl;
    	
    	//env2 does not use https
    	if (Environment.isENV2())
    		myUrl = "http://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/legal-documents/wdis_world";
    	else
    		myUrl = "https://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid + "%7D/legal-documents/wdis_world";
    	
        HttpClient client = HttpClientBuilder.create().build();
        System.out.println("updateUser myUrl: " + myUrl);

        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Accept-Language", "e");
        httpPost.addHeader("Authorization", "BEARER " + userToken);

        if (Environment.isShadow() || Environment.isLatest() || Environment.isStage() || Environment.isENV2() || Environment.isENV4()) {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        } else if (Environment.isProduction()) {
            if (credentials.getServerInstance().equals("A")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else if (Environment.isSoftLaunch()) {
            if (credentials.getServerInstance().equals("B")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "CCCCCCCCCCCCCCCCCCCCCCCCC");
        }

        // For DLR, change wdis_world to DisneylandResort and also the prefix
        // in DLR-DIMG-TOU instead of WDW-DIMG-TOU.
        String bodyRequest = "{\"code\":\"WDW-DIMG-TOU\",\"languageCode\":null,\"affiliate\":\"wdis_world\",\"activeConfirm\":true} ";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));

        httpPost.setEntity(entity);
        System.out.println("submitOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);

        System.out.println("updateUser Response:" + response);

        retCode = response.getStatusLine().getStatusCode();

    }

    public void updateTerms2() throws Exception {

        // For DLR, use DisneylandResort instead of wdis_world as the
        // affiliate name.
    	
    	String myUrl;
    	
    	//env2 does not use https
    	if (Environment.isENV2())
    		myUrl = "http://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid
                       + "%7D/legal-documents/wdis_world";
    	else	
    		myUrl = "https://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid
            + "%7D/legal-documents/wdis_world";
    	
        HttpClient client = HttpClientBuilder.create().build();
        System.out.println("updateUser myUrl: " + myUrl);

        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Accept-Language", "e");
        httpPost.addHeader("Authorization", "BEARER " + userToken);

        if (Environment.isShadow() || Environment.isLatest() || Environment.isStage() || Environment.isENV2() || Environment.isENV4()) {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        } else if (Environment.isProduction()) {
            if (credentials.getServerInstance().equals("A")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else if (Environment.isSoftLaunch()) {
            if (credentials.getServerInstance().equals("B")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "CCCCCCCCCCCCCCCCCCCCCCCCC");
        }

        // For DLR, use DisneylandResort instead of wdis_world as the affiliate
        // name. Also change the prefix WDW-NGE-TOU to DLR-NGE-TOU.

        String bodyRequest = "{\"code\":\"WDW-NGE-TOU\",\"languageCode\":null,\"affiliate\":\"wdis_world\",\"activeConfirm\":true} ";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));

        httpPost.setEntity(entity);
        System.out.println("submitOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);

        System.out.println("updateUser Response:" + response);

        retCode = response.getStatusLine().getStatusCode();

    }

    public void updateTerms3() throws Exception {

        // For DLR change wdis_world to DisneylandResort as the affiliate
        // name.
    	
    	String myUrl;
    	
    	//env2 does not use https
    	if (Environment.isENV2())
    		myUrl = "http://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid
                       + "%7D/legal-documents/wdis_world";
    	else
    		myUrl = "https://" + credentials.getBaseUrl() + "/profile-service/guests/%7B" + swid
            + "%7D/legal-documents/wdis_world";
    	
        HttpClient client = HttpClientBuilder.create().build();
        System.out.println("updateUser myUrl: " + myUrl);

        HttpPost httpPost = new HttpPost(myUrl);

        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Conversation-Id", convoId);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X-API-Version", "1");
        httpPost.addHeader("Accept", "application/json;apiversion=1");
        httpPost.addHeader("Accept-Language", "e");
        httpPost.addHeader("Authorization", "BEARER " + userToken);

        if (Environment.isShadow() || Environment.isLatest() || Environment.isStage() || Environment.isENV2() || Environment.isENV4()) {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "XXXXXXXXXXXXXXXXXXXXXXXXX");

        } else if (Environment.isProduction()) {
            if (credentials.getServerInstance().equals("A")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else if (Environment.isSoftLaunch()) {
            if (credentials.getServerInstance().equals("B")) {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "AAAAAAAAAAAAAAAAAAAAAAAAA");
            } else {
                httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "BBBBBBBBBBBBBBBBBBBBBBBBB");
            }
        } else {
            httpPost.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", "CCCCCCCCCCCCCCCCCCCCCCCCC");
        }

        // Change wdis_world to DisneylandResort for the DLR version of
        // the affiliate name.

        String bodyRequest = "{\"code\":\"ppV2\",\"languageCode\":null,\"affiliate\":\"wdis_world\",\"activeConfirm\":true} ";

        HttpEntity entity = new ByteArrayEntity(bodyRequest.getBytes("UTF-8"));

        httpPost.setEntity(entity);
        System.out.println("submitOrder requestLine: " + EntityUtils.toString(entity));

        HttpResponse response = client.execute(httpPost);

        System.out.println("updateUser Response:" + response);

        retCode = response.getStatusLine().getStatusCode();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public void setSwid(String swid) {
        this.swid = swid;
    }

}

