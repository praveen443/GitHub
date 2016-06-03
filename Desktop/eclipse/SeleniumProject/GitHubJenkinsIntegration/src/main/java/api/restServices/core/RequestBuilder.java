package api.restServices.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.seleniumhq.jetty7.server.session.JDBCSessionManager.SessionData;

import selenium.common.Log;

/**
 * An base class for building HTTP requests to test against web services.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public abstract class RequestBuilder<R extends RequestType> {
    
    /**
     * TODO: WIll probably need to use masks for this.
     * 
     * @author SonHuy.Pham@Disney.com
     */
    public static enum HeaderType {
        BASIC_CONVO,
        CONVO_TOKEN_POOLOVERRIDE,
        CONVO_TOKEN_POOLOVERRIDE_XAPI,
        CONVO_TOKEN_POOLOVERRIDE_EN,
        CONVO_TOKEN_POOLOVERRIDE_EN_XAPI,
        FINDER;
    }

    /** Broken out here because we don't care about the native separator of this OS */
    protected static String EOL_SEPARATOR = "\n";
    /** All the external data for varation points. */
    protected SessionData sessionData = new SessionData();
    protected R requestType = null;
    
    public RequestBuilder(R requestType) {
        this.requestType = requestType;
    }
    
    public void setSessionData(SessionData sessionData) {
        this.sessionData = sessionData;
    }
    
    public SessionData getSessionData() {
        return sessionData;
    }
    
    public String getUrl() {
        return sessionData.searchAndReplaceTemplate(sessionData.generateKeyValuePairs(), 
                                                    requestType.getUrl());
    }
    
    protected <T extends HttpRequestBase> RequestBuilder<?> createHeader(T request) throws Exception {
        
        switch(requestType.getHeaderType()) {
            case BASIC_CONVO:
                request.addHeader("Connection", "keep-alive");
                request.addHeader("X-Conversation-Id", sessionData.getConversationId());
                request.addHeader("Content-Type", "application/x-www-form-urlencoded");
                break;
            case CONVO_TOKEN_POOLOVERRIDE_EN_XAPI:
                request.addHeader("X-API-Version", "1");
                request.addHeader("Accept-Language", sessionData.getAcceptLanguage());
            case CONVO_TOKEN_POOLOVERRIDE_EN:
            case CONVO_TOKEN_POOLOVERRIDE_XAPI:
            case CONVO_TOKEN_POOLOVERRIDE:
                request.addHeader("Connection", "keep-alive");
                request.addHeader("X-Conversation-Id", sessionData.getConversationId());
                request.addHeader("Authorization", "BEARER " + sessionData.getToken());
                request.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", sessionData.getPoolOverride());
                request.addHeader("Content-Type", "application/json");
                request.addHeader("Accept", "application/json;apiversion=1");
                break;
            case FINDER:
                request.addHeader("X-Conversation-Id", sessionData.getConversationId());
                request.addHeader("Authorization", "BEARER " + sessionData.getToken());
                request.addHeader("X-Disney-Internal-PoolOverride-WDPROAPI", sessionData.getPoolOverride());
                request.addHeader("X-Disney-Internal-Site", sessionData.getDestination());
                request.addHeader("Content-Type", "application/json");
                request.addHeader("Accept", "application/json");
                break;
            default:
                break;
        }
        
        switch(requestType.getHeaderType()) {
            case CONVO_TOKEN_POOLOVERRIDE_EN:
                request.addHeader("Accept-Language", sessionData.getAcceptLanguage());
                break;
            case CONVO_TOKEN_POOLOVERRIDE_XAPI:
                request.addHeader("X-API-Version", "1");
                break;
            default:
                break;
        }
        
        return this;
    }
    
    protected <T extends HttpRequestBase> RequestBuilder<?> createBody(T request) throws Exception {
        StringBuilder buffer = readFile(requestType.getFilepath());
        Map<String, String> kvPairs = sessionData.generateKeyValuePairs();
        String payload = sessionData.searchAndReplaceTemplate(kvPairs, buffer.toString());
        
        if(payload.isEmpty() && !(requestType instanceof HttpGet)) {
            Log.getDefaultLogger().warning("Empty payload from file: " + 
                                           requestType.getFilepath());
        }
        
        // TODO: FIXME: DO NOT SEND UTF8 FOR MULTI-LANGUAGE.
        HttpEntity entity = new ByteArrayEntity(payload.getBytes("UTF-8"));
        
        if(request instanceof HttpEntityEnclosingRequestBase) {
            ((HttpEntityEnclosingRequestBase)request).setEntity(entity);
        }
        return this;
    }
    
    public <T extends HttpRequestBase> void create(T request) throws Exception {
        createHeader(request).createBody(request);
    }
    
    public HttpResponse generateAndSendRequest(Class<? extends HttpRequestBase> httpClass) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        Constructor<?>[] constructors =  httpClass.getConstructors();
        Constructor<?> ctor = null;
        for(int i = 0; i < constructors.length; ++i) {
            if(constructors[i].getParameterTypes().length == 1 && 
                    constructors[i].getParameterTypes()[0].isAssignableFrom(String.class)) {
                ctor = constructors[i];
            }
        }
        HttpRequestBase request = null;
        
        if(ctor == null) {
            request = httpClass.newInstance();
            request.setURI(new URI(getUrl()));
        } else {
            request = (HttpRequestBase) ctor.newInstance(getUrl());
        }
        
        create(request);
        // Saving this off, just for reference.
        sessionData.setHttpRequest(request);
        return client.execute(request);
    }
    
    protected StringBuilder readFile(String pathname) throws Exception {
        File file = new File(pathname);
        StringBuilder buffer = new StringBuilder();
        if(file.exists() && file.canRead()) {
            try(BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String line = reader.readLine();
                while (line != null) {
                    buffer.append(line);
                    line = reader.readLine();
                    if(line != null) {
                        buffer.append(EOL_SEPARATOR);
                    }
                }
            }
        } else {
            Log.getDefaultLogger().info("Unable to read template file: " + pathname);
        }
        return buffer;
    }
    
    /**
     *  A quick and dirty way to build out the request without having to 
     *  mess with instantiating various objects.  Makes it a bit more
     *  complex than the initial vision.
     *  
     * @param request
     * @throws Exception
     */
    public static <T extends RequestBuilder<?>> T create(Class<T> builder, RequestType requestType, HttpRequestBase request) throws Exception {
        @SuppressWarnings("unchecked")
        T requestBuilder = (T) builder.getConstructors()[0].newInstance(requestType);
        requestBuilder.create(request);
        return requestBuilder;
    }
    
    /**
     * Boiler-plate stuff to simplify sending requests.
     * 
     * @return
     * @throws Exception
     */
    public static <T extends RequestBuilder<?>> HttpResponse generateAndSendRequest(Class<T> builder, RequestType requestType, SessionData sessionData) throws Exception {
        RequestBuilder<?> requestBuilder = (RequestBuilder<?>) builder.getConstructors()[0].newInstance(requestType);
        requestBuilder.setSessionData(sessionData);
        return requestBuilder.generateAndSendRequest(requestType.getHttpClass());
    }
}

