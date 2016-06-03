package selenium.common.pageObject;

/**
 * Helps enforce the prototype-creation design pattern.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public interface PageObjectPrototype {
    
    public <T extends PageObject> T newInstance() throws Exception;
}


