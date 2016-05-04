package selenium.wdw.common.pageObject;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class GenericPageObject extends WdwPageObject {
    
    protected String pageUrl = "";
    
    public GenericPageObject(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Making sure that we don't register a generic page with the Page Factory.
     */
    @Override
    public void registerPageObject() {
    }
    
    @Override
    public String getPageUrl() {
        return pageUrl;
    }
    
    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
    
    public void navigateToPageObjectUrl() {
        driver.get(pageUrl);
    }
}
