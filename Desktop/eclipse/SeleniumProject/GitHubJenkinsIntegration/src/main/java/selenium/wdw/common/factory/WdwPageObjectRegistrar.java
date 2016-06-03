package selenium.wdw.common.factory;

import org.openqa.selenium.WebDriver;

import selenium.common.pageobject.PageObjectRegistrar;
import selenium.wdw.common.pageobject.WdwPageObject;

/**
 * An intermediate abstract-class that provides additional filtering within
 * the class registration process.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public abstract class WdwPageObjectRegistrar extends PageObjectRegistrar {

    public static final String WDW_ROOT_PACKAGE = DEFAULT_ROOT_PACKAGE + ".wdw";

    public WdwPageObjectRegistrar() {
        // We're going to narrow down the scope of the searches to improve 
        // start-up time - previously seen up to 900 milliseconds to load.
        super(WDW_ROOT_PACKAGE);
    }

    protected WdwPageObjectRegistrar(String packagePath) {
        super(packagePath);
    }

    /**
     * Helps add an extra guard by filtering out by WdwPageObject class type.
     */
    @Override
    public void registerAllClasses(WebDriver driver) throws Exception {
        registerAllClasses(WdwPageObject.class, driver);
    }
}


