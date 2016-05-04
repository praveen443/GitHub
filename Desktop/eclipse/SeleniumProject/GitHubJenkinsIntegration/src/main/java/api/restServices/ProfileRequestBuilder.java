package api.restServices;

/***********************************************************************************************************************
 * FileName - ProfileRequestBuilder.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Author: SonHuy.Pham@Disney.com $
 * $Revision: #1 $
 * $Change: 715510 $
 * $Date: November  19, 2014 $
 *
 ***********************************************************************************************************************/


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import api.restServices.ProfileRequestBuilder.ProfileRequestType;
import api.restServices.core.RequestBuilder;
import api.restServices.core.RequestType;
import api.restServices.core.SessionData;
import selenium.common.Constants;
import selenium.common.Utils;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class ProfileRequestBuilder extends RequestBuilder<ProfileRequestType> {
    
    /**
     * Awkwardly broken out into a class that has a nested enum.  We're not 
     * intentionally trying to make it confusing - but, it's just one solution
     * that will help us extend and subclass request builder.
     * 
     * @author SonHuy.Pham@Disney.com
     */
    public static enum ProfileRequestType implements RequestType {
        
        /** Unlike credential's version, this will log the user in. */
        LOGIN_USER_STAGE("https://stg.authorization.go.com/token", 
                         HeaderType.BASIC_CONVO, 
                         HttpPost.class),
                         
         /** Unlike credential's version, this will log the user in. */
        LOGIN_USER_PROD("https://authorization.go.com/token", 
                        HeaderType.BASIC_CONVO, 
                        HttpPost.class),
                        
        CREATE_ACCOUNT("https://$$BASE_URL$$/profile-service/guests/account", 
                       HeaderType.CONVO_TOKEN_POOLOVERRIDE_XAPI, 
                       HttpPost.class),
                       
        UPDATE_ADDRESS("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/addresses", 
                       HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                       HttpPost.class),
                       
        UPDATE_GUEST_PREFERENCE("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/preference", 
                                HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                                HttpPost.class),
                                
        UPDATE_BILLING_ADDRESS("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/addresses", 
                               HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                               HttpPost.class),
                              
        UPDATE_SECURITY_QUESTIONS("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/security-questions", 
                                  HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                                  HttpPost.class),
                                  
        UPDATE_SUBSCRIPTION("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/subscriptions/bulk-update", 
                            HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                            HttpPost.class),
                            
        UPDATE_TERMS_01("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/legal-documents/wdis_world", 
                        HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                        HttpPost.class),
                        
        UPDATE_TERMS_02("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/legal-documents/wdis_world", 
                        HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                        HttpPost.class),
                        
        UPDATE_TERMS_03("https://$$BASE_URL$$/profile-service/guests/%7B$$SWID$$%7D/legal-documents/wdis_world", 
                        HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN_XAPI, 
                        HttpPost.class);
        
        private String url = null;
        /** The path to the template file, which should help construct the request. */
        private String filename = Utils.toCamelCaseLowerCaseFirst(this.name()) + ".tmpl";
        /** Generic headers to help aid and simplify creation. */
        private HeaderType headerType = HeaderType.BASIC_CONVO;
        /** Allows for configuring Get or Post request types. */
        private Class<? extends HttpEntityEnclosingRequestBase> httpClass = null;
        /** A short cut to the enclosing class for syntax sugar. */
        private Class<? extends RequestBuilder<?>> builder = ProfileRequestBuilder.class;
        
        private ProfileRequestType(String url, HeaderType headerType, Class<? extends HttpEntityEnclosingRequestBase> httpClass) {
            this.url = url;
            this.headerType = headerType;
            this.httpClass = httpClass;
        }
        
        public void setFilename(String filename) {
            this.filename = filename;
        }
        
        @Override
        public String getFilename() {
            return filename;
        }
        
        @Override
        public HeaderType getHeaderType() {
            return headerType;
        }
        
        /**
         * Note that this will prepend the absolute path of the current 
         * directory that the application is running from.
         * @return 
         */
        @Override
        public String getFilepath() {
            return Constants.CURRENT_DIR + "template/profile/" + filename;
        }
        
        @Override
        public String getUrl() {
            return url;
        }
        
        @Override
        public Class<? extends HttpRequestBase> getHttpClass() {
            return httpClass;
        }
        
        /**
         * Boiler-plate stuff to simplify sending requests.  Using reflection,
         * this will internally instantiate a 
         * {@link api.restServices.core.RequestBuilder}, the 
         * HttpRequest, and assign specific information from 
         * {@link api.restServices.core.SessionData}.
         * 
         * @return
         * @throws Exception
         */
        @Override
        public HttpResponse generateAndSendRequest(SessionData sessionData) throws Exception {
            return RequestBuilder.generateAndSendRequest(builder, this, sessionData);
        }
    }
    
    public ProfileRequestBuilder(ProfileRequestType requestType) {
        super(requestType);
    }
    
    public static String extractSwid(HttpResponse response) throws Exception {
        HttpEntity entity = response.getEntity();
        JSONObject jsonObject = new JSONObject(EntityUtils.toString(entity));
        /*
        if(!jsonObject.has("swid")) {
            return "";
        }
        /**/
        String swid = jsonObject.getString("swid");
        swid = swid.replace("{", "");
        swid = swid.replace("}", "");
        return swid;
    }
}

