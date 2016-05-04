package selenium.common.enums;

/**
 * An interface for all Walt Disney Parks and Resorts' URLs.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public interface WdprUrl {
    
    public String getUrl();

    public boolean equalTo(WdprUrl url);
    
    public boolean equalTo(String url);

    public boolean contains(String url);

    public boolean isValid();
}

