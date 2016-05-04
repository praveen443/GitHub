package api.restServices;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import com.disney.api.restServices.CartRequestBuilder.CartRequestType;
import com.disney.api.restServices.core.RequestBuilder;
import com.disney.api.restServices.core.RequestType;
import com.disney.api.restServices.core.SessionData;
import com.disney.selenium.common.Constants;
import com.disney.selenium.common.Utils;

public class CartRequestBuilder extends RequestBuilder<CartRequestType> {
    
    public static enum CartRequestType implements RequestType {
        
        // TODO: WDW part should be configurable ??
        SET_CART_ID("https://$$BASE_URL$$/cart-service/scopes/wdw/carts", 
                    HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                    HttpPost.class),
                    
        ADD_ITEM("", 
                 HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                 HttpPost.class),
                 
        UPDATE_ITEM("", 
                    HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                    HttpPost.class),
                    
        UPDATE_CART_ROOM("", 
                         HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                         HttpPost.class),
                         
        RETRIEVE_CART("https://$$BASE_URL$$/cart-service/carts/$$CART_ID$$", 
                      HeaderType.CONVO_TOKEN_POOLOVERRIDE, 
                      HttpPost.class);
        
        private String url = null;
        /** The path to the template file, which should help construct the request. */
        private String filename = Utils.toCamelCaseLowerCaseFirst(this.name()) + ".tmpl";
        /** Generic headers to help aid and simplify creation. */
        private HeaderType headerType = HeaderType.BASIC_CONVO;
        /** Allows for configuring Get or Post request types. */
        private Class<? extends HttpEntityEnclosingRequestBase> httpClass = null;
        /** A short cut to the enclosing class for syntax sugar. */
        private Class<? extends RequestBuilder<?>> builder = CartRequestBuilder.class;
        
        private CartRequestType(String url, HeaderType headerType, Class<? extends HttpEntityEnclosingRequestBase> httpClass) {
            this.url = url;
            this.headerType = headerType;
            this.httpClass = httpClass;
        }
        
        @Override
        public String getFilepath() {
            return Constants.CURRENT_DIR + "template/cart/" + filename;
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

        @Override
        public String getFilename() {
            return filename;
        }

        @Override
        public HeaderType getHeaderType() {
            return headerType;
        }
    }

    public CartRequestBuilder(CartRequestType requestType) {
        super(requestType);
    }
    
    @Override
    protected RequestBuilder<?> createHeader(HttpRequestBase request) throws Exception {
        return super.createHeader(request);
    }
}
