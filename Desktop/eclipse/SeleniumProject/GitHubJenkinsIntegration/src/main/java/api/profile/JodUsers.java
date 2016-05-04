package api.profile;

/***********************************************************************************************************************
 * FileName - JodUsers.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Author: Jarad Medbery, Michael.Yardley@disney.com $
 * $Revision: #1 $
 * $Change: 715510 $
 * $Date: November  19, 2014 $
 *
 * *** Class Description ***
 * JodUsers - JOD (Jarad on demand)
 * This class handles methods to create various user types
 *
 ***********************************************************************************************************************/

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.WebDriver;

import api.common.Credentials;
import api.restServices.CredentialRequestBuilder;
import api.restServices.CredentialRequestBuilder.CredentialRequestType;
import api.restServices.ProfileRequestBuilder;
import api.restServices.ProfileRequestBuilder.ProfileRequestType;
import api.restServices.core.SessionData;
import selenium.common.Log;
import selenium.common.enums.Environment;
import selenium.common.exception.HttpResponseCodeErrorException;

public class JodUsers {

    /**
     * Creates a random user based on defaults set within SessionData.
     * 
     * @see {@link api.restServices.core.SessionData}
     * @param driver
     * @return email address, i.e. user-id
     * @throws Exception
     */
    public static String createFloridian(WebDriver driver) throws Exception {
        SessionData sessionData = new SessionData();
        sessionData.setUserId(SessionData.generateRandomUsername());
        sessionData.setUserPw("password1");
        
        createUser(driver, sessionData);
        
        return sessionData.getUserId();
    }

    /**
     * A convenience routine for creating a user with a valid California address.
     * The user name is a "randomly" created but not guaranteed to be unique.
     * 
     * @see {@link api.restServices.core.SessionData}
     * @param driver
     * @return email address, i.e. user-id
     * @throws Exception
     */
    public static String createCalifornian(WebDriver driver, SessionData sessionData) throws Exception {
      
        sessionData.setUserId(SessionData.generateRandomUsername());
        sessionData.setUserPw("password1");
        sessionData.setAddress1("1025 Grand Central");
        sessionData.setAddressCity("Glendale");
        sessionData.setAddressZip("91202");
        sessionData.setAddressState("CA");
        
        createUser(driver, sessionData);
        Log.log(driver).info("User's Email Address: " + sessionData.getUserId());
        
        return sessionData.getUserId();
    }
    
    public static void createUser(SessionData sessionData) throws Exception {
        createUser(null, sessionData);
    }
    
    /**
     * Inspired and repackaged code from the original Rest API work, this will 
     * create a user and allow the caller to re-configure certain attributes,
     * primarily driven by JSON "templates".
     * 
     * @param driver
     * @param sessionData
     * @throws Exception
     */
    public static void createUser(WebDriver driver, SessionData sessionData) throws Exception {

        HttpResponse response = CredentialRequestType.USER_TOKEN_STAGE.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        String token = CredentialRequestBuilder.extractToken(response);
        sessionData.setToken(token);
        
        response = ProfileRequestType.CREATE_ACCOUNT.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        String swid = ProfileRequestBuilder.extractSwid(response);
        sessionData.setSwid(swid);
        
        response = ProfileRequestType.LOGIN_USER_STAGE.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        String userToken = CredentialRequestBuilder.extractToken(response);
        sessionData.setToken(userToken);
        
        response = ProfileRequestType.UPDATE_ADDRESS.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        response = ProfileRequestType.UPDATE_GUEST_PREFERENCE.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        response = ProfileRequestType.UPDATE_BILLING_ADDRESS.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        response = ProfileRequestType.UPDATE_SECURITY_QUESTIONS.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        response = ProfileRequestType.UPDATE_SUBSCRIPTION.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        response = ProfileRequestType.UPDATE_TERMS_01.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        response = ProfileRequestType.UPDATE_TERMS_02.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
        
        response = ProfileRequestType.UPDATE_TERMS_03.generateAndSendRequest(sessionData);
        checkHttpResponse(driver, response);
        Log.log(driver).info(sessionData.getHttpRequest().toString());
    }
    
    /**
     * Should move this out to some Http utils class at some point.
     * Expects all responses to at a minimum be less than 400 (very forgiving 
     * IMO).
     * 
     * @param driver
     * @param response
     * @throws Exception
     */
    public static void checkHttpResponse(WebDriver driver, HttpResponse response) throws Exception {
        if(response.getStatusLine().getStatusCode() >= 400) {
            String errorMsg = "Detected status code [" + response.getStatusLine().getStatusCode() + "] " +
                              EntityUtils.toString(response.getEntity());
            
            // SAP: Was previously commented out - not sure if the check is 
            // problematic.  Will re-introduce into the wild for now.
            
            throw new HttpResponseCodeErrorException(errorMsg);
        }
        Log.log(driver).info(response.toString());
    }
    
    /**
     * Creates a single JOD user for the tests. (JOD - Jarad on demand) <br>
     * @return String Returns the username i.e. Email address
     * @author Michael Yardley
     */
	public static String createSingleUser(WebDriver driver) throws Exception {
		
		String tokenId = "";
	    String userName = "";
		
		// setup credentials object
		Credentials credentials = new Credentials();
		// determine environment and instance
		credentials.configureServerInstance();
		credentials.determineBaseUrl();
		
		// set tokens based on upper or lower environment. Stage, latest, shadow = lower. Prod, SL = upper 
		if (Environment.isStage() || Environment.isLatest() || Environment.isShadow()) {
			Log.log(driver).info("Lower environment going to set the token now.");
			credentials.setToken();
		} else {
			Log.log(driver).info("Upper environment going to set the token now.");
			credentials.setTokenProd();
		}
		
		// getting Auth token value for test
		tokenId = credentials.getToken();
		Log.log(driver).info("getCredentialsTest Token: " + tokenId);
		assertFalse(tokenId == "YougotnoToken");
		// set up new account object
		Account user = new Account(credentials);
		// create user name
		user.createUserName();
		// get user name for length of test
		userName = user.getUserName();
		// create new profile
		user.createAccount(tokenId);
		// get SWID for new user profile
		user.getSwid();
		
		// login with user account depending on upper or lower environment
		if (Environment.isStage() || Environment.isLatest() || Environment.isShadow()) {
			user.setUserToken();
		} else {
			user.setUserTokenProd();
		}
		
		// assert if the call went through (200 or 201 response)
		assertEquals(201,user.getretCode()) ;
		// update address for account
		user.updateAddress();
		assertEquals(200,user.getretCode()) ;
		// update security questions for the account
	    user.updateQuestions();
	    assertEquals(204,user.getretCode()) ;
	    // update email opt-in choices
	    user.updateSubscription();
	    assertEquals(200,user.getretCode()) ;
	    // select terms and conditions
	    user.updateTerms1();
	    user.updateTerms2();
	    user.updateTerms3();
		
		return userName;
	}

	
	public static String[] createUserReturnArray(WebDriver driver) throws Exception {
		
		String tokenId = "";
	    String userName = "";
	    String swid = "";
		
		// setup credentials object
		Credentials credentials = new Credentials();
		// determine environment and instance
		credentials.configureServerInstance();
		credentials.determineBaseUrl();
		
		
		// set tokens based on upper or lower environment. Stage, latest, shadow = lower. Prod, SL = upper 
		if (Environment.isStage() || Environment.isLatest() || Environment.isShadow() || Environment.isENV2() || Environment.isENV4()) {
			Log.log(driver).info("Lower environment going to set the token now.");
			credentials.setToken();
		} else {
			Log.log(driver).info("Upper environment going to set the token now.");
			credentials.setTokenProd();
		}
		
		// getting Auth token value for test
		tokenId = credentials.getToken();
		Log.log(driver).info("getCredentialsTest Token: " + tokenId);
		assertFalse(tokenId == "YougotnoToken");
		// set up new account object
		Account user = new Account(credentials);
		// create user name
		user.createUserName();
		// get user name for length of test
		userName = user.getUserName();
		// create new profile
		user.createAccount(tokenId);
		// get SWID for new user profile
		swid = user.getSwid();
		
		// login with user account depending on upper or lower environment
		if (Environment.isStage() || Environment.isLatest() || Environment.isShadow() || Environment.isENV2() || Environment.isENV4()) {
			user.setUserToken();
		} else {
			user.setUserTokenProd();
		}
		
		// assert if the call went through (200 or 201 response)
		assertEquals(201,user.getretCode()) ;
		// update address for account
		user.updateAddress();
		assertEquals(200,user.getretCode()) ;
		// update security questions for the account
	    user.updateQuestions();
	    assertEquals(204,user.getretCode()) ;
	    // update email opt-in choices
	    user.updateSubscription();
	    assertEquals(200,user.getretCode()) ;
	    // select terms and conditions
	    user.updateTerms1();
	    user.updateTerms2();
	    user.updateTerms3();
	    
	    
		
		return new String[]{userName, swid, credentials.getBaseUrl()};
	}
}




