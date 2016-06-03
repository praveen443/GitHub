package api.restServices;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import api.restServices.FinderRequestBuilder.FinderRequestType;
import api.restServices.core.RequestBuilder;
import api.restServices.core.RequestType;
import api.restServices.core.SessionData;
import selenium.common.Constants;
import selenium.common.Utils;

/**
 * Allows interaction with the finder service.  
 * 
 * @see {@link FinderRequestType}
 * @author SonHuy.Pham@Disney.com
 */
public class FinderRequestBuilder extends RequestBuilder<FinderRequestType> {
    
    /**
     * Variation points are captured and configurable with place holders in 
     * the arguments used to initialize this enumeration.
     * 
     * @author SonHuy.Pham@Disney.com
     */
    public static enum FinderRequestType implements RequestType {
        
        /**
         * Looks like it'll be used to populate the global navigation bar.
         */
        LIST_ANCESTOR("https://api-stage.wdpro.starwave.com/finder-service/public/finder/list/ancestor/$$DESTINATION$$;entityType=$$ENTITY_TYPE$$;destination=$$DESTINATION$$?filters=$$FILTERS$$&date=$$SEARCH_DATE$$&region=$$REGION$$", 
                      HttpGet.class),
        /** 
         * Entity ID and entity type varies - 
         * 1. Attraction, 2. Entertainment, 3. MerchandiseFacility, 4. restaurant
         */
        DETAIL("https://api-stage.wdpro.starwave.com/finder-service/public/finder/detail/$$ENTITY_ID$$;entityType=$$ENTITY_TYPE$$;destination=$$DESTINATION$$?region=$$REGION$$",
               HttpGet.class),
               
        UNKNOWN("", HttpGet.class);
        
        private String url = null;
        private String filename = Utils.toCamelCaseLowerCaseFirst(this.name()) + ".tmpl";
        /** Generic headers to help aid and simplify creation. */
        private HeaderType headerType = HeaderType.CONVO_TOKEN_POOLOVERRIDE;
        /** Allows for configuring Get or Post request types. */
        private Class<? extends HttpRequestBase> httpClass = null;
        /** A short cut to the enclosing class for syntax sugar. */
        private Class<? extends RequestBuilder<?>> builder = FinderRequestBuilder.class;
        
        private FinderRequestType(String url, Class<? extends HttpRequestBase> httpClass) {
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
            return Constants.CURRENT_DIR + "template/finder/" + filename;
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
    
    public FinderRequestBuilder(FinderRequestType requestType) {
        super(requestType);
    }

    @Override
    protected RequestBuilder<?> createHeader(HttpRequestBase request) throws Exception {
        return super.createHeader(request);
    }
}

