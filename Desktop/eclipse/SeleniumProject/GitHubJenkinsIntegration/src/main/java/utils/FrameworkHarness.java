package utils;

import java.util.Map;
import java.util.logging.Logger;

import utils.AutomationException;

public class FrameworkHarness {

// Old code before global variables in Jenkins were introduced - delete after 8/2015
//	public final String MDX_GRUMPY_PREFIX = "stage";
//	public final String MDX_SLEEPY_PREFIX = "qa5";
//	public final String MDX_SNOWWHITE_PREFIX = "qa3";
//	public final String MDX_DOC_PREFIX = "lt1";
//	public final String MDX_EVILQUEEN_PREFIX = "lt2";
//
//	public final String MDX_GRUMPY_URL = "https://wdw-" + MDX_GRUMPY_PREFIX + ".disney.go.com";
//	public final String MDX_SLEEPY_URL = "https://pep-" + MDX_SLEEPY_PREFIX + ".nge.go.com";
//	public final String MDX_SNOWWHITE_URL= "https://pep-" + MDX_SNOWWHITE_PREFIX + ".nge.go.com";
//	public final String MDX_DOC_URL= "https://pep-" + MDX_DOC_PREFIX + ".nge.go.com/";
//	public final String MDX_EVILQUEEN_URL= "https://pep-" + MDX_EVILQUEEN_PREFIX + ".nge.go.com/";
///////////////////////////////////////////////////////////////
    
	public final static String SELENIUM_COMPOSITE_URL = "SELENIUM_COMPOSITE_URL";
    public final static String SELENIUM_COMPOSITE_PORT = "SELENIUM_COMPOSITE_PORT";
    public final static String BASE_URL_LOCAL = "BASE_URL_LOCAL";
    public final static String BASE_URL_GRUMPY = "BASE_URL_GRUMPY";
    public final static String BASE_URL_SLEEPY = "BASE_URL_SLEEPY";
    public final static String BASE_URL_SNOWWHITE = "BASE_URL_SNOWWHITE";
    public final static String BASE_URL_DOC = "BASE_URL_DOC";
    public final static String BASE_URL_EVILQUEEN = "BASE_URL_EVILQUEEN";
    public final static String BASE_URL_BASHFUL = "BASE_URL_BASHFUL";
	
    final private static Logger defaultLogger = Logger.getLogger("FrameworkHarness");

	
	public void hookToWDPro(String application, String environment, int timeout, String browserUnderTest, boolean grid){

	    Map<String, String> envMap = System.getenv();
	    
		System.setProperty("selenium.default_timeout", String.valueOf(timeout));
		System.setProperty("selenium.element_timeout", String.valueOf(timeout));
		System.setProperty("selenium.page_timeout", String.valueOf(timeout));		
		System.setProperty("selenium.grid", String.valueOf(grid));			
        System.setProperty("selenium.rc_host", envMap.get(SELENIUM_COMPOSITE_URL));
        System.setProperty("selenium.rc_port", envMap.get(SELENIUM_COMPOSITE_PORT));
        System.setProperty("selenium.application", String.valueOf(application));
		System.setProperty("selenium.environment", String.valueOf(environment));

		// Old code before global variables in Jenkins were introduced - delete after 8/2015
		// System.setProperty("selenium.rc_host", String.valueOf("dnhprpptst01.disid.disney.com"));
		
		
		switch(browserUnderTest.toLowerCase()){
			case "ie":
			case "iexplorer":System.setProperty("selenium.rc_type", String.valueOf("iexplore")); break;
			case "firefox":System.setProperty("selenium.rc_type", String.valueOf("firefox")); break;
			case "chrome":System.setProperty("selenium.rc_type", String.valueOf("chrome")); break;
		}

		String baseUrl = null;
		switch(environment.toLowerCase().replace(" ", "").replace("_", "")){
			case "local":
				baseUrl = envMap.get(BASE_URL_LOCAL);
				break;
			case "grumpy":
			    baseUrl = envMap.get(BASE_URL_GRUMPY);
			    
// Old code before global variables in Jenkins were introduced. Something similar was in each case below. - delete after 8/2015
//			    System.setProperty("selenium.base_url", String.valueOf(MDX_GRUMPY_URL)); 
//				System.setProperty("selenium.xbms_prefix", String.valueOf(XBMS_GRUMPY_PREFIX));
//				System.setProperty("selenium.test_environment", String.valueOf(MDX_GRUMPY_PREFIX));
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			    break;
			case "sleepy":
                baseUrl = envMap.get(BASE_URL_SLEEPY);
				break;
			case "snowwhite":
                baseUrl = envMap.get(BASE_URL_SNOWWHITE);
				break;
			case "doc":
                baseUrl = envMap.get(BASE_URL_DOC);
				break;
			case "evilqueen":
                baseUrl = envMap.get(BASE_URL_EVILQUEEN);
				break;
			case "bashful":
                baseUrl = envMap.get(BASE_URL_BASHFUL);
				break;	
			default:
				AutomationException ex =  new AutomationException(environment + " is not a valid testing environment");
				throw new RuntimeException(ex);
		}		
		
        defaultLogger.info("BaseURL: "+baseUrl);
		String prefix = baseUrl.substring(baseUrl.indexOf("-")+1, baseUrl.indexOf("."));
        defaultLogger.info("BaseURL prefix: "+prefix);
        System.setProperty("selenium.base_url", baseUrl); 
        System.setProperty("selenium.test_environment", prefix);
		
	}
}