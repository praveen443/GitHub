package selenium.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import selenium.common.exception.automation.AutomationInitializationException;
import selenium.common.exception.automation.WebDriverInitializationException;

/**
 * Technically, this WRAPS an instance of WebDriver but there's no need to 
 * hold on to an instance of this class once it creates a WebDriver instance. 
 * In other words, don't be fooled by the name.
 * 
 * TODO: Rename to WebDriverWrapper, SeleniumWebDriver, or WdprWebDriver instead 
 * since this DOES not wrap an instance of Selenium.
 * 
 * @author SonHuy.Pham@Disney.com
 */
public class SeleniumWrapper {
    
    public static final String DEFAULT_BROWSER_TYPE = "firefox";
    public static final String DRIVER_DIR = Constants.CURRENT_DIR + "drivers" + Constants.DIR_SEPARATOR;
    
    WebDriverConfiguration configuration = new WebDriverConfiguration();
    private InheritableThreadLocal<WebDriver> seleniumThread = new InheritableThreadLocal<WebDriver>();
    
    public SeleniumWrapper() {
    }
    
    public SeleniumWrapper(WebDriverConfiguration configuration) {
        if(configuration != null) {
            this.configuration = configuration;
        }
    }
    
    /**
     * Convenience routine that also initializes the logger and maximizes the 
     * screen automatically.
     * 
     * @param testName
     * @return
     * @throws Exception
     */
    public WebDriver initialize(String testName) throws Exception {
        
        WebDriver driver = null;
        
        try {
            driver = initialize();
            Log.getDefaultLogger().info(driver.toString());
            
        } catch (Exception ex) {
            throw new WebDriverInitializationException(
                    "Web Driver failed to initialize", ex);
        }
        
        Log.initialize(testName, driver);
        Log.log(driver).info(this.toString());
        Log.log(driver).info(Constants.toStr());
        
        // We're going to replace defaults with the cleaner logger format.
        Log.replaceLogFormatter(driver);
        Log.replaceErrorLogFormatter(driver);
        
        // By default, let's avoid logging all web elements.
        //Utils.setLogWebElements(false);
        
        SeleniumWrapper.maximizeBrowserWindow(driver);
        
        Log.log(driver).info("Starting " + testName + "...");
        return driver;
    }
    
    public static void maximizeBrowserWindow(WebDriver driver) throws Exception {
        Properties property = new Properties();
        String path;
        path = new java.io.File(".").getCanonicalPath();
        FileInputStream inputStream = new FileInputStream(path + Constants.DIR_SEPARATOR + "config.properties");
        property.load(inputStream);
        boolean maximize = "true".equals(property.getProperty("maximize"));
        if (maximize) {
            driver.manage().window().maximize();
        }
    }
    
    /**
     * The meat of this class.  Initializes a number of objects and is the 
     * recommended focal point for any sort of web-driver related variations.
     * Allows the user to modify capabilities freely or override the driver 
     * instance.
     * 
     * @return
     * @throws Exception
     */
    public WebDriver initialize() throws Exception {
        Log.getDefaultLogger().info(configuration.toString());
        WebDriver driver = null;
        DriverType driverType = configuration.getDriverType();
        
        // Any other tweaks and adjustments to DesiredCapabilities and/or 
        // browser profile directories should be set here in the switch
        // statement below.
        //
        // Also, defining your own WebDriver instance will also skip the 
        // default instantiation process.
        String preferedArchitecture = configuration.getPreferedArchitecture();
        //if (preferedArchitecture != null && preferedArchitecture.length() > 0) preferArchitecture = true;
        
        
        switch(driverType) {
            
            case CHROME:
                if (configuration.isLocal()) {
                    File file = driverType.determineDriverLocation();
                    if(!file.exists()) {
                        throw new AutomationInitializationException(
                                "Chrome driver does not exist or is not executable: " 
                                        + file.toString());
                    }
                    String driverPath = file.getAbsolutePath();
                    System.setProperty("webdriver.chrome.driver", driverPath);
                    
                    // This helps get rid of the warnings within Chrome when
                    // it runs in "test-mode".  Don't yet know if there's a 
                    // work around for remote drivers on the grid.
                    
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("test-type");
                    DesiredCapabilities capabilities = configuration.getDesiredCapabilities();
                    capabilities.setCapability("chrome.binary", driverPath);
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                }
                break;
                
            case FIREFOX:
                if(configuration.isUsingBrowserProfile()) {
                    File profileDirectory = new File(DRIVER_DIR
                            + "firefox" + Constants.DIR_SEPARATOR 
                            + "firefox_profile" + Constants.DIR_SEPARATOR);
                    
                    FirefoxProfile profile = new FirefoxProfile(profileDirectory);
                    DesiredCapabilities capabilities = configuration.getDesiredCapabilities();
                    
                    if(configuration.isUsingGrid()) {
                        
                        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                        
                    } else if (configuration.isUsingFirebugNetExport()) {
                        
                        // This implicitly is for local runs only.
                        
                        capabilities = setupFirebugAndNetexport(capabilities, profile);
                        
                        // We'll throw an error here since we don't have full
                        // support for Firebug in Firefox 30 and Selenium 2.42.
                        
                        throw new AutomationInitializationException("Firebug is not fully supported in 2.42");
                            
                    } else {
                        
                        // Don't see why we can't just set the profile via the
                        // capabilities API.  Will definitely need 
                        // investigation.
                        // capabilities.setCapability(FirefoxDriver.PROFILE, profile);
                        
                        driver = new FirefoxDriver(profile);
                    }
                }
                break;
                
            case HTML_UNIT:
                break;
                
            case INTERNET_EXPLORER:
                if (configuration.isLocal()) {
                    File file = driverType.determineDriverLocation(preferedArchitecture);
                    if(!file.exists() || !file.canExecute()) {
                        throw new AutomationInitializationException(
                                "Internet Explorer driver does not exist or is not executable: "
                                        + file.toString());
                    }
                    System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                    
                    // http://jimevansmusic.blogspot.in/2012/08/youre-doing-it-wrong-protected-mode-and.html
                    // http://stackoverflow.com/questions/21324529/how-can-i-open-my-application-in-internet-explorer-11-0-in-selenium-web-driver
                    
                    DesiredCapabilities capabilities = configuration.getDesiredCapabilities();
                 //   capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                  //  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
              //    capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);

                    capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
                //    InternetExplorerDriverService service = new InternetExplorerDriverService.Builder().withLogFile(new File("C:\\temp\\seleniumLog.txt")).withLogLevel(InternetExplorerDriverLogLevel.TRACE).build();
                 //   driver = new InternetExplorerDriver(service, capabilities);
                } else {
                    
                    DesiredCapabilities capabilities = configuration.getDesiredCapabilities();
                    capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                    capabilities.setCapability("ignoreZoomSetting", true);
               //     capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
                    capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
                  //  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    capabilities.setPlatform(Platform.WINDOWS);
                    
                    // TODO, FIXME: Figure out how to launch the driver!
                    //File file = new File("D:\\apps\\prod\\IEDriverServer.exe");
                    //System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                    //InternetExplorerDriverService service = new InternetExplorerDriverService.Builder().withLogFile(new File("path-to-file")).withLogLevel(InternetExplorerDriverLogLevel.TRACE).build();
                    //driver = new InternetExplorerDriver(service, capabilities);
                }
                break;
                
            case SAFARI:
                break;
                
            case PHANTOM_JS:
                
                // We don't explicitly support PhantomJS so let it fall down
                // and raise an exception to the let the user know.
                //
                // capabilities.setCapability("takesScreenshot", true);
                // capabilities.setCapability("acceptSslCerts", true);
                // String defaultLocation = "/usr/bin/phantomjs";
                // if (browserString.contains("windows")) {
                //     defaultLocation = "C:\\apps\\prod\\phantomjs-1.9.1-windows\\phantomjs.exe";
                // }
                // capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, 
                //     System.getProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, defaultLocation));
                
            case UNKNOWN:
            default:
                throw new AutomationInitializationException(driverType.getName() + " is not supported");
        }
        
        if(driver == null) {
            
            // If it wasn't overridden and manually created in the switch 
            // statement above, then go ahead and create the web driver via 
            // normal means.
            
            driver = driverType.createWebDriver(configuration);
        }

        // Adding a shutdown hook to ensure that Selenium is shutdown.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown();
            }
        });
        seleniumThread.set(driver);
        return driver;
    }

    public void shutdown() {
        try {
            WebDriver driver = seleniumThread.get();
            if(driver != null) {
                driver.close();
                driver.quit();
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        seleniumThread.remove();
        seleniumThread.set(null);
    }

    /**
     * Setup Firebug and netExport extension for HAR file output. Used for
     * testing analytics.  
     * 
     * TODO, FIXME: With the upgrade to Selenium 2.42, this needs to target a 
     * different version of firebug. 
     * 
     * @author tkhanpap
     * @param capabilities
     * @param profile
     * @return
     * @throws Exception
     */
    public static DesiredCapabilities setupFirebugAndNetexport(DesiredCapabilities capabilities, FirefoxProfile profile) throws Exception {
        
        String path = Constants.CURRENT_DIR;
        
        // Define output directory
        String outputDir = (path + "captured_network_traffic");
        Log.getDefaultLogger().info("Set outputDir to 0654: " + outputDir);

        File firebug = new File(path + "//src//main//resources//firebug-1.10.6.xpi");
        File netExport = new File(path + "//src//main//resources//netExport-0.9b3.xpi");

        // File harFilePath = new File(outputDir);
        // String outHarFilePath = harFilePath.getAbsolutePath();
        // Log.getDefaultLogger().info("Set outHarFilePath to 0626: " + outHarFilePath);

        try {
            profile.addExtension(firebug);
            profile.addExtension(netExport);
        } catch (IOException e) {
            // FIXME: ISN'T this an error that shouldn't be swallowed?
            e.printStackTrace();
        }

        profile.setPreference("app.update.enabled", false);

        // Setting Firebug preferences
        Log.getDefaultLogger().info("Setting Firebug preferences ... ");
        profile.setPreference("extensions.firebug.currentVersion", "2.0");
        profile.setPreference("extensions.firebug.addonBarOpened", true);
        profile.setPreference("extensions.firebug.console.enableSites", true);
        profile.setPreference("extensions.firebug.script.enableSites", true);
        profile.setPreference("extensions.firebug.net.enableSites", true);
        profile.setPreference("extensions.firebug.previousPlacement", 1);
        profile.setPreference("extensions.firebug.allPagesActivation", "on");
        profile.setPreference("extensions.firebug.onByDefault", true);
        profile.setPreference("extensions.firebug.defaultPanelName", "net");
        // Analytics call end up in Images filter as beacon call
        // NOTE: Later version (current 1.12.6) of FBug renamed to
        // "netFilterCategories"
        profile.setPreference("extensions.firebug.netFilterCategory", "image");

        // Setting netExport preferences
        Log.getDefaultLogger().info("Setting netExport preferences ... ");
        profile.setPreference("extensions.firebug.netexport.alwaysEnableAutoExport", true);
        profile.setPreference("extensions.firebug.netexport.autoExportToFile", true);
        profile.setPreference("extensions.firebug.netexport.Automation", true);
        profile.setPreference("extensions.firebug.netexport.showPreview", false);
        profile.setPreference("extensions.firebug.netexport.defaultLogDir", outputDir);
        // profile.setPreference("extensions.firebug.netexport.defaultLogDir", "C:\\apps\\");

        capabilities.setPlatform(org.openqa.selenium.Platform.ANY);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        
        return capabilities;
    }

    @Override
    public String toString() {
        String result;
        if(seleniumThread == null) {
            result = super.toString() + "[ Not Initialized ]";
        } else {
            result = super.toString() 
                    + Constants.NEW_LINE + "\t[" + (getWebDriver() == null ? null : getWebDriver().toString()) + "]"
                    + Constants.NEW_LINE + "\t" + configuration.toString();
        }
        return result;
    }

    public WebDriverConfiguration getConfiguration() {
        return configuration;
    }
    
    public void setSeleniumConfiguration(WebDriverConfiguration configuration) {
        if(configuration != null) {
            this.configuration = configuration;
        }
    }
    
    protected InheritableThreadLocal<WebDriver> getSeleniumThread() {
        return seleniumThread;
    }

    public WebDriver getWebDriver() {
        return (seleniumThread == null ? null : seleniumThread.get());
    }
    
    /**
     * A fancy enumeration type that helps sets defaults during initialization.
     * Implements a factory pattern for retrieving the enumeration in addition
     * to the web driver and desired capabilities instance.
     * 
     * @author SonHuy.Pham@Disney.com
     */
    static public enum DriverType {

        CHROME("chrome", 
                "chrome", 
                DesiredCapabilities.chrome(),
                ChromeDriver.class),
                
        FIREFOX("firefox", 
                "firefox", 
                DesiredCapabilities.firefox(),
                FirefoxDriver.class),
                
        HTML_UNIT("htmlunit", 
                "htmlunit", 
                DesiredCapabilities.htmlUnit(),
                HtmlUnitDriver.class),
                
        INTERNET_EXPLORER("iexplore", 
                "internet explorer", 
                DesiredCapabilities.internetExplorer(),
                InternetExplorerDriver.class),
                
        PHANTOM_JS("phantomjs", 
                "phantomjs", 
                DesiredCapabilities.phantomjs(),
                null /* PhantomJSDriver */ ), 
                
        SAFARI("safari", 
                "safari", 
                new DesiredCapabilities(),
                SafariDriver.class),
                
        UNKNOWN("unknown", 
                "unknown browser", 
                new DesiredCapabilities(),
                null);

        private String name;
        private String browserName;
        private DesiredCapabilities capabilities;
        private Class<? extends WebDriver> webDriverClass;
        
        DriverType(String name, String browserName, DesiredCapabilities capabilities, Class<? extends WebDriver> webDriverClass) {
            this.name = name.trim().toLowerCase();
            this.browserName = browserName;
            this.capabilities = capabilities;
            this.webDriverClass = webDriverClass;
        }

        public boolean contains(String name) {
            return this.name.contains(name);
        }
        
        public String getName() {
            return name;
        }
        
        public Class<? extends WebDriver> getWebDriverClass() {
            return webDriverClass;
        }
        
        /**
         * This returns a new instance of capabilities, uninitialized with 
         * just the defaults.
         * @see {@link SeleniumWrapper.WebDriverConfiguration#getDesiredCapabilities()}
         * @warning Don't use this to configure the desired capabilities.
         * @return
         * @throws Exception
         */
        public DesiredCapabilities createDesiredCapabilities() {
            // If this throw an exception - it's gotta be pretty fatal so a 
            // run-time exception makes sense.
            try {
                DesiredCapabilities desiredCapabilities = capabilities.getClass().newInstance();
                desiredCapabilities.setJavascriptEnabled(true);
                
                if(browserName != null && !browserName.isEmpty()) {
                    desiredCapabilities.setBrowserName(browserName);
                }
                return desiredCapabilities;
                
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        
        /**
         * Only supports Windoze, Mac and assumes Linux/*nix.  Defaults to 
         * windows since that's what currently runs on the grid.
         * 
         * @return The expected path to the driver.
         */
        public File determineDriverLocation() throws Exception {
        	return determineDriverLocation("");
        }
        
        /**
         * Only supports Windoze, Mac and assumes Linux/*nix.  Defaults to 
         * windows since that's what currently runs on the grid.
         * @param preferedArchitecture specify the driver type to use based on this architecture.
         * @return The expected path to the driver.
         */
        public File determineDriverLocation(String preferedArchitecture) throws Exception {
            String arch = (Constants.ARCH_TYPE.contains("64") ? "x64" : "x32");
            if (preferedArchitecture != null && preferedArchitecture.length() > 1) {
            	arch = preferedArchitecture;
            }
            String osName = Constants.OS_NAME;
            osName = osName.contains("win") ? "win" 
                    : osName.contains("mac") ? "mac" 
                    : osName.contains("nux") ? "linux" : "win";
            
            switch(this) {
                case CHROME:
                {
                    if(osName.contains("linux")) {
                        osName.concat(arch);
                    }
                    String filename = osName + appendExeSuffix("_chromedriver");
                    return new File(DRIVER_DIR + "chrome" + Constants.DIR_SEPARATOR + filename);
                }
                case INTERNET_EXPLORER:
                {
                    if(osName.contains("win")) {
                        osName = osName.concat(arch).replace("x", "");
                    }
                    String filename = osName + appendExeSuffix("_IEDriverServer");
                    return new File(DRIVER_DIR + "ie" + Constants.DIR_SEPARATOR + filename);
                }
                default:
                    throw new AutomationInitializationException(
                            "This driver type does not have any associated driver files: " + this.toString());
            }
        }
        
        private String appendArchSuffix(String str) {
            return str + (Constants.ARCH_TYPE.contains("64") ? "x64" : "x32");
        }
        
        private String appendExeSuffix(String str) {
            return str + (Constants.OS_NAME.contains("win") ? ".exe" : "");
        }
        
        public WebDriver createWebDriver(WebDriverConfiguration config) throws Exception {
            if(config.usingGrid) {
                return createRemoteWebDriver(config);
            } else if(webDriverClass != null) {
                Constructor<?> constructors[] = webDriverClass.getConstructors();
                for(int i = 0; i < constructors.length; ++i) {
                    Class<?> params[] = constructors[i].getParameterTypes();
                    if(params != null && params.length == 1 && params[0].isAssignableFrom(Capabilities.class)) {
                        return (WebDriver)constructors[i].newInstance(config.getDesiredCapabilities());
                    }
                }
            }
            throw new AutomationInitializationException(
                    "Unable to construct an instance of web driver for " + this.toString());
        }
        
        public WebDriver createRemoteWebDriver(WebDriverConfiguration config) throws Exception{
            URL url = new URL("http", config.getRemoteHost(), config.getRemotePort(), "/wd/hub");
        	// URL url = new URL("http", "dnhprpptstw701.disid.disney.com", config.getRemotePort(), "/wd/hub");
            return new RemoteWebDriver(url, config.getDesiredCapabilities());
        }
        
        @Override
        public String toString() {
            return super.toString()
                    + "[" + name + "-" + Constants.OS_NAME + "] "
                    + capabilities == null ? "" : capabilities.toString();
        }
        
        /**
         * The preferred way to instantiate the enumeration object.  This will
         * automatically peek at the environment (system properties) and create
         * one from expected parameters i.e. selenium.rc_type or will default
         * to DEFAULT_BROWSER_TYPE.
         * 
         * @return DriverType based on the system properties.
         */
        public static DriverType create() {
            return fromString(System.getProperty(Constants.SELENIUM_RC_TYPE_PROPERTY, 
                                                 SeleniumWrapper.DEFAULT_BROWSER_TYPE));
        }
        
        public static DriverType fromString(String text) {
            if (text != null) {
                text = text.replaceAll("\\s", "").toLowerCase();
                for (DriverType b : DriverType.values()) {
                    if (text.equals(b.getName())) {
                        return b;
                    }
                }
            }
            return UNKNOWN;
        }
    };
    
    /**
     * Encapsulates configuration parameters and helps coordinate the flow in
     * setting and getting values.  Standard stuff.
     * 
     * @author SonHuy.Pham@Disney.com
     */
    static public class WebDriverConfiguration {
        
        private String baseUrl = System.getProperty(Constants.SELENIUM_BASE_URL_PROPERTY, "ERROR");
        private Boolean usingBrowserProfile = Boolean.getBoolean(Constants.SELENIUM_PROFILE_PROPERTY);
        private Boolean usingFirebugNetExport = Boolean.getBoolean(Constants.SELENIUM_FIREFOX_FIREBUG_NETEXPORT_PROPERTY);
        private Boolean usingGrid = Boolean.getBoolean(Constants.SELENIUM_GRID_PROPERTY);
        private String remoteHost = System.getProperty(Constants.SELENIUM_RC_HOST_PROPERTY);
        private Integer remotePort = Integer.valueOf(System.getProperty(Constants.SELENIUM_RC_PORT_PROPERTY));
        private DriverType driverType = DriverType.create();
        private DesiredCapabilities desiredCapabilities = driverType.createDesiredCapabilities();
        /*
         * Only use if you want to overwrite default architecture type. 
         * For IE use x64 or x32.
         */
        private String preferedArchitecture = "x32";
        
        public WebDriverConfiguration() {
        }

        @Override
        public String toString() {
            return super.toString()
                    + Constants.NEW_LINE + "\t[Type: " + driverType.toString() + "]"
                    + Constants.NEW_LINE + "\t[Capabilities: " + desiredCapabilities.toString() + "]"
                    + Constants.NEW_LINE + "\t[Base Url: " + baseUrl + "]"
                    + Constants.NEW_LINE + "\t[Grid: " + usingGrid + "]"
                    + Constants.NEW_LINE + "\t[Remote Host: " + remoteHost + "]"
                    + Constants.NEW_LINE + "\t[Remote Port: " + remotePort + "]"
                    + Constants.NEW_LINE + "\t[Profile: " + usingBrowserProfile + "]"
                    + Constants.NEW_LINE + "\t[Firebug Netexport: " + usingFirebugNetExport + "]";
        }
        
        

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public DriverType getDriverType() {
            return driverType;
        }

        public void setDriverType(DriverType driverType) {
            this.driverType = driverType;
        }

        public DesiredCapabilities getDesiredCapabilities() {
            return desiredCapabilities;
        }
        
        public void setDesiredCapabilities(DesiredCapabilities desiredCapabilities) {
            this.desiredCapabilities = desiredCapabilities;
        }
        
        public Boolean getUsingBrowserProfile() {
            return usingBrowserProfile;
        }

        public boolean isUsingBrowserProfile() {
            return usingBrowserProfile.booleanValue();
        }

        public void setUsingBrowserProfile(Boolean usingBrowserProfile) {
            this.usingBrowserProfile = usingBrowserProfile;
        }

        public Boolean getUsingFirebugNetExport() {
            return usingFirebugNetExport;
        }

        public boolean isUsingFirebugNetExport() {
            return usingFirebugNetExport.booleanValue();
        }

        public void setUsingFirebugNetExport(Boolean usingFirebugNetExport) {
            this.usingFirebugNetExport = usingFirebugNetExport;
        }

        public Boolean getUsingGrid() {
            return usingGrid;
        }
        
        public boolean isUsingGrid() {
            return usingGrid.booleanValue();
        }

        public boolean isLocal() {
            return !isUsingGrid();
        }
        
        public void setUsingGrid(Boolean usingGrid) {
            this.usingGrid = usingGrid;
        }

        public String getRemoteHost() {
            return remoteHost;
        }

        public void setRemoteHost(String remoteHost) {
            this.remoteHost = remoteHost;
        }

        public Integer getRemotePort() {
            return remotePort;
        }

        public void setRemotePort(Integer remotePort) {
            this.remotePort = remotePort;
        }

		/**
		 * Get the preferedArchitectureType.  Only get and set if you know you want a driver
		 * based on an archeture type other than the computur running the test.  This may cause 
		 * problems. 
		 * @author shiec014
		 * @return the preferedArchitecture
		 */
		public String getPreferedArchitecture() {
			return preferedArchitecture;
		}

		/**
		 * Get the preferedArchitectureType.  Only get and set if you know you want a driver
		 * based on an archeture type other than the computur running the test.  This may cause 
		 * problems. For IE use x64 or x32.
		 * @author shiec014
		 * @param preferedArchitecture the preferedArchitecture to set
		 */
		public void setPreferedArchitecture(String archType) {
			this.preferedArchitecture = archType;
		}
    }
}


