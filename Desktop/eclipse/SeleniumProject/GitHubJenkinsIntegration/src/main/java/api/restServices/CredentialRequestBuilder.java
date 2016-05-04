package api.restServices;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.util.EntityUtils;

import api.restServices.CredentialRequestBuilder.CredentialRequestType;
import api.restServices.core.RequestBuilder;
import api.restServices.core.RequestType;
import api.restServices.core.SessionData;
import selenium.common.Constants;
import selenium.common.Utils;

public class CredentialRequestBuilder extends RequestBuilder<CredentialRequestType> {
    
    public static enum CredentialRequestType implements RequestType {
        
        USER_TOKEN_STAGE("https://stg.authorization.go.com/token", HttpPost.class),
        USER_TOKEN_PROD("https://authorization.go.com/token", HttpPost.class);
        
        private String url = null;
        private String filename = Utils.toCamelCaseLowerCaseFirst(this.name()) + ".tmpl";
        /** Allows for configuring Get or Post request types. */
        private Class<? extends HttpRequestBase> httpClass = null;
        /** A short cut to the enclosing class for syntax sugar. */
        private Class<? extends RequestBuilder<?>> builder = CredentialRequestBuilder.class;
        
        private CredentialRequestType(String url, Class<? extends HttpRequestBase> httpClass) {
            this.url = url;
            this.httpClass = httpClass;
        }
        
        @Override
        public String getFilename() {
            return filename;
        }
        
        @Override
        public String getFilepath() {
            return Constants.CURRENT_DIR + "template/credential/" + filename;
        }
        
        @Override
        public String getUrl() {
            return url;
        }
        
        @Override
        public Class<? extends HttpRequestBase> getHttpClass() {
            return httpClass;
        }
        
        @Override
        public HeaderType getHeaderType() {
            return HeaderType.BASIC_CONVO;
        }
        
        /**
         * Boiler-plate stuff to simplify sending requests.
         * 
         * @return
         * @throws Exception
         */
        @Override
        public HttpResponse generateAndSendRequest(SessionData sessionData) throws Exception {
            return RequestBuilder.generateAndSendRequest(builder, this, sessionData);
        }
    }
    
    public CredentialRequestBuilder(CredentialRequestType requestType) {
        super(requestType);
    }
    
    @Override
    protected RequestBuilder<?> createHeader(HttpRequestBase request) throws Exception {
        return super.createHeader(request);
    }
    
    /**
     * TODO This should go into some Web Service Utils class for better 
     * organization.
     * 
     * @param response
     * @return
     * @throws Exception
     */
    public static String extractToken(HttpResponse response) throws Exception {
        String result = "";
        String responseStr = EntityUtils.toString(response.getEntity());
        Pattern pattern = Pattern.compile("\":\"(.*?)\",\"");
        Matcher matcher = pattern.matcher(responseStr);
        if (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }
}


