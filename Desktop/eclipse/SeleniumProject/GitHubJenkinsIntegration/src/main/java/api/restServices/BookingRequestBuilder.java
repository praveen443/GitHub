package api.restServices;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import api.restServices.BookingRequestBuilder.BookingRequestType;
import api.restServices.core.RequestBuilder;
import api.restServices.core.RequestType;
import api.restServices.core.SessionData;
import selenium.common.Constants;
import selenium.common.Utils;

/**
 * Not complete.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class BookingRequestBuilder extends RequestBuilder<BookingRequestType> {
    
    public static enum BookingRequestType implements RequestType {
        
        CREATE_ORDER("https://$$BASE_URL$$/booking-service/orders", 
                     HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                     HttpPost.class),
                     
        RETRIEVE_ORDER("", 
                       HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                       HttpGet.class),
                       
        SET_ORDER_ID("https://$$BASE_URL$$/booking-service/orders", 
                     HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                     HttpGet.class),
                     
        ADD_CREDIT_CARD("", 
                        HeaderType.CONVO_TOKEN_POOLOVERRIDE_XAPI, 
                        HttpPost.class),
                        
        ADD_PARTICIPANTS("https://$$BASE_URL$$/booking-service/orders/$$ORDER_ID$$/items/$$ITEM_ID$$/participants", 
                         HeaderType.CONVO_TOKEN_POOLOVERRIDE_XAPI, 
                         HttpPost.class),
                         
        ADD_DELIVERY_OPTION("https://$$BASE_URL$$/booking-service/orders/$$ORDER_ID$$/?fields=deliveryOption",
                            HeaderType.CONVO_TOKEN_POOLOVERRIDE_XAPI, 
                            HttpPost.class),
                            
        ADD_SHIPPING_OPTION("https://$$BASE_URL$$/booking-service/orders/$$ORDER_ID$$/?fields=shippingAddress,shippingPhone,shippingEmail,shipToName", 
                            HeaderType.CONVO_TOKEN_POOLOVERRIDE_XAPI, 
                            HttpPost.class),
                            
        SUBMIT_ORDER("", 
                     HeaderType.CONVO_TOKEN_POOLOVERRIDE_EN, 
                     HttpPost.class);
        
        private String url = null;
        /** The path to the template file, which should help construct the request. */
        private String filename = Utils.toCamelCaseLowerCaseFirst(this.name()) + ".tmpl";
        /** Generic headers to help aid and simplify creation. */
        private HeaderType headerType = HeaderType.BASIC_CONVO;
        /** Allows for configuring Get or Post request types. */
        private Class<? extends HttpRequestBase> httpClass = null;
        /** A short cut to the enclosing class for syntax sugar. */
        private Class<? extends RequestBuilder<?>> builder = BookingRequestBuilder.class;
        
        private BookingRequestType(String url, HeaderType headerType, Class<? extends HttpRequestBase> httpClass) {
            this.url = url;
            this.headerType = headerType;
            this.httpClass = httpClass;
        }
        
        @Override
        public String getFilepath() {
            return Constants.CURRENT_DIR + "template/booking/" + filename;
        }
        
        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public String getFilename() {
            return filename;
        }

        @Override
        public Class<? extends HttpRequestBase> getHttpClass() {
            return httpClass;
        }

        @Override
        public HttpResponse generateAndSendRequest(SessionData sessionData) throws Exception {
            return RequestBuilder.generateAndSendRequest(builder, this, sessionData);
        }

        @Override
        public HeaderType getHeaderType() {
            return headerType;
        }
    }

    public BookingRequestBuilder(BookingRequestType requestType) {
        super(requestType);
    }
    
    @Override
    protected RequestBuilder<?> createHeader(HttpRequestBase request) throws Exception {
        return super.createHeader(request);
    }
}

