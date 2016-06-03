package api.restServices;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import api.restServices.AvailabilityRequestBuilder.AvailabilityRequestType;
import api.restServices.core.RequestBuilder;
import api.restServices.core.RequestType;
import api.restServices.core.SessionData;
import selenium.common.Constants;
import selenium.common.Utils;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class AvailabilityRequestBuilder extends RequestBuilder<AvailabilityRequestType> {
    
    /**
     * Awkwardly broken out into a class that has a nested enum.  We're not 
     * intentionally trying to make it confusing - but, it's just one solution
     * that will help us extend and subclass request builder.
     * 
     * @author SonHuy.Pham@Disney.com
     */
    public static enum AvailabilityRequestType implements RequestType {
        
        GROUPED_RESORT("https://$$BASE_URL$$/availability-service/grouped-resort-availability", HttpPost.class),
        GROUPED_ROOM_TYPE("https://$$BASE_URL$$/availability-service/grouped-resort-availability", HttpPost.class);
        
        private String url = null;
        private String filename = Utils.toCamelCaseLowerCaseFirst(this.name()) + ".tmpl";
        /** Generic headers to help aid and simplify creation. */
        private HeaderType headerType = HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN;
        /** Allows for configuring Get or Post request types. */
        private Class<? extends HttpEntityEnclosingRequestBase> httpClass = null;
        /** A short cut to the enclosing class for syntax sugar. */
        private Class<? extends RequestBuilder<?>> builder = AvailabilityRequestBuilder.class;
        
        private AvailabilityRequestType(String url, Class<? extends HttpEntityEnclosingRequestBase> httpClass) {
            this.url = url;
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
            return Constants.CURRENT_DIR + "template/availability/" + filename;
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
        public HttpResponse generateAndSendRequest(SessionData sessionData) throws Exception {
            return RequestBuilder.generateAndSendRequest(builder, this, sessionData);
        }
    }
    
    public AvailabilityRequestBuilder(AvailabilityRequestType requestType) {
        super(requestType);
    }

    @Override
    protected RequestBuilder<?> createHeader(HttpRequestBase request) throws Exception {
        return super.createHeader(request);
    }
}
