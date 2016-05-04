package selenium.common.pageComponent;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface Page {
    
    public String getPageUrl();
    
    public List<String> getAltPageUrls();
    
    public String getPageKey() throws Exception;
    
    public List<WebElement> getHeadingElements();
}

