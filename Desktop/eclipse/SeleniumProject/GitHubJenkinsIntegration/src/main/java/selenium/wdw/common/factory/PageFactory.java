package selenium.wdw.common.factory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import selenium.common.Log;
import selenium.common.Utils;
import selenium.common.exception.automation.PageObjectNotRegisteredException;
import selenium.common.pageobject.PageObject;
import selenium.common.pageobject.PageObjectCreator;
import selenium.wdw.common.pageobject.GenericPageObject;

/**
 * TODO: MOVE BOILER PLATE INTO AN ABSTRACT CLASS.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class PageFactory implements PageObjectCreator {

    static private Map<String, PageFactory> instances = new HashMap<String, PageFactory>();
    private Map<String, PageObject> pageObjects = new HashMap<String, PageObject>();
    private WebDriver driver;
    private boolean snapshotOnCreation = false;
    
    private PageFactory() {
    }

    /**
     * Singleton instances are organized based on the driver instance passed in.
     * 
     * @param driver
     * @return
     */
    static public PageFactory getInstance(WebDriver driver) {

        PageFactory instance = null;
        String key = driver.toString();

        if (instances.containsKey(key)) {
            instance = instances.get(key);
        } else {
            instance = new PageFactory();
            instances.put(key, instance);
        }

        // Take the time here to sync/copy the driver instance.
        if (instance.driver == null || !instance.driver.equals(driver)) {
            instance.driver = driver;
        }

        return instance;
    }
    
    /**
     * Meant for Page Object instances to register with the Factory. This allows
     * the factory to create objects somewhat blindly/agnostic - it creates Page
     * Objects with only the abstract classes and interfaces.
     * 
     * @param pageObject
     * @return Returns true if the object was added, false if it already existed
     */
    @Override
    public <T extends PageObject> boolean registerPageObject(T pageObject) {

        boolean isSuccessful = false;

        if (!pageObjects.containsKey(pageObject.getPageUrl())) {
            pageObjects.put(pageObject.getPageUrl(), pageObject);
            isSuccessful = true;
        }

        // Handling Page Object variation-points due to REST-ful URL structures.
        if(pageObject.getAltPageUrls() != null) {
            for(String s : pageObject.getAltPageUrls()) {
                if (!pageObjects.containsKey(s)) {
                    pageObjects.put(s, pageObject);
                }
            }
        }
        
        return isSuccessful;
    }

    /**
     * Based on the driver's current URL, this method will create a PageObject
     * class.  Page Objects are required to register explicitly or implicitly
     * using the framework's Registrar classes.
     * 
     * @return A derived class of PageObject, as specified by T
     * @throws Exception Throws if there is no matching PageObject for the currently loaded URL
     */
    @Override
    public <T extends PageObject> T create() throws Exception {
        String url = driver.getCurrentUrl();
        return create(url);
    }
    
    /**
     * Based on the driver's current URL, this method will create a PageObject
     * class.  Page Objects are required to register explicitly or implicitly
     * using the framework's Registrar classes.
     * 
     * @param url
     * @return A derived class of PageObject, as specified by T
     * @throws Exception Throws if there is no matching PageObject for the currently loaded URL
     */
    @Override
    public <T extends PageObject> T create(String url) throws Exception {
        
        // In some cases, a page may have parameters passed to it so let's just
        // ignore that stuff.
        
        url = (url.contains("?")) ? url.substring(0, url.indexOf('?')) : url;
        
        T page = null;

        if (pageObjects.containsKey(url)) {
            page = pageObjects.get(url).newInstance();
            Log.log(driver).info("Creating Page Object: " + page + " " + page.getPageKey());
        } else {
            Log.log(driver).warning("Unable to find matching Page Object for: " + url);
            // Instead supporting unregistered/undefined Page Objects, we're 
            // going to throw an exception to let the user know that things 
            // will go wrong (let them know now- rather than later).
            throw new PageObjectNotRegisteredException(driver);
            
            // Note: Do not suppress casting warnings.
            // @SuppressWarnings("unchecked")
            //GenericPageObject genericPage = new GenericPageObject(driver);
            //genericPage.setPageUrl(url);
            //Log.log(driver).info("Creating Generic Page Object: " + url);
            // We'll let Java throw the cast exception error if the generic
            // page doesn't match. It should jive well with the idea that the
            // user should already know what to expect.
            //page = (T) genericPage;
        }
        
        if(snapshotOnCreation) {
            Utils.captureScreenshot(driver, "Page", true);
        }
        
        return page;
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
    @Override
    public PageObject createPageObject() throws Exception {
        String url = driver.getCurrentUrl();
        return createPageObject(url);
    }

    /**
     * Attempts to create a Page Object, if none can be instatiated - it will
     * return a Generic Page Object.
     * 
     * @param url
     * @return An instance of a PageObject
     * @throws Exception
     */
    @Override
    public PageObject createPageObject(String url) throws Exception {
        PageObject page;
        
        // In some cases, a page may have parameters passed to it so let's just
        // ignore that stuff.
        
        url = (url.contains("?")) ? url.substring(0, url.indexOf('?')) : url;
        
        try {
            page = create(url);
            
        } catch(PageObjectNotRegisteredException ex) {
            GenericPageObject genericPage = new GenericPageObject(driver);
            genericPage.setPageUrl(url);
            Log.log(driver).info("Created Page Object: " + genericPage + " " + genericPage.getPageKey());
            page = genericPage;
        }
        
        if(snapshotOnCreation) {
            Utils.captureScreenshot(driver, "Page", true);
        }
        
        return page;
    }
    
    @Override
    public boolean isSnapshotOnCreation() {
        return snapshotOnCreation;
    }
    
    @Override
    public void setSnapshotOnCreation(boolean snapshotOnCreation) {
        this.snapshotOnCreation = snapshotOnCreation;
    }
}

