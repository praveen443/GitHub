package api.restServices.core;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.seleniumhq.jetty7.server.session.JDBCSessionManager.SessionData;

/**
 * Serves as an interface for enumerations.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public interface RequestType {
    
    public String getFilepath();
    public String getUrl();
    public String getFilename();
    public RequestBuilder.HeaderType getHeaderType();
    public Class<? extends HttpRequestBase> getHttpClass();
    public HttpResponse generateAndSendRequest(SessionData sessionData) throws Exception;
}

