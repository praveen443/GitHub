package selenium.common;

/*********************************************************************************************************************** *
 * FileName - Constants.java
 *
 * (c) Disney. All rights reserved.
 *
 * $Authors: 
 * Thomas.Kessler@disney.com, Tigran.Khanpapyan@disney.com, Jarad.Medbery@disney.com, 
 * Michael.Yardley@disney.com, Son.Huy.Pham@disney.com $   
 * $Revision: #1 $
 * $Change: 715510 $
 * $Date: November  19, 2014 $
 **********************************************************************************************************************/


import java.io.File;
import java.util.Calendar;

/**
 * A slight misnomer, this class holds global variables in addition to 
 * constants.  Many of these though, would have greatly benefited from being
 * defined as an enumeration instead (SAP, IMO).
 */
public class Constants {

	public static final String SELENIUM_RC_HOST_PROPERTY = "selenium.rc_host";
    public static final String SELENIUM_RC_PORT_PROPERTY = "selenium.rc_port";
    public static final String SELENIUM_RC_TYPE_PROPERTY = "selenium.rc_type";
    public static final String SELENIUM_BASE_URL_PROPERTY = "selenium.base_url";
    public static final String SELENIUM_GRID_PROPERTY = "selenium.grid";
    public static final String SELENIUM_PROFILE_PROPERTY = "firefox.profile";
    public static final String SELENIUM_FIREFOX_FIREBUG_NETEXPORT_PROPERTY = "firefox.firebug.netexport";
    public static final String SELENIUM_LIGHT_DARK_PROPERTY = "selenium.light";
    public static final String SELENIUM_ENABLE_DEBUG_MODULE_PROPERTY = "selenium.enable_debug_module";
    public static final String SELENIUM_XML_FILESET_PROPERTY = "selenium.xmlfileset";
    public static final String SELENIUM_USER_USED_PROPERTY = "selenium.userUsed";
    public static final String SELENIUM_TEST_ENVIRONMENT_PROPERTY = "selenium.test_environment";
    public static final String SELENIUM_CAPTURE_INFO_SCREENSHOTS_PROPERTY = "selenium.capture_info_screenshots";
    public static final String SELENIUM_DB_USER_PROPERTY = "selenium.retrieve_db_user";
    /** Not yet used - values should be something like, json, sql, rest */
    public static final String SELENIUM_RETRIEVE_USER_FROM_PROPERTY = "selenium.retrieve_user_from";
    public static final String SELENIUM_DEFAULT_TIMEOUT_PROPERTY = "selenium.default_timeout";
    public static final String SELENIUM_ELEMENT_TIMEOUT_PROPERTY = "selenium.element_timeout";
    public static final String SELENIUM_PAGE_TIMEOUT_PROPERTY = "selenium.page_timeout";
    public static final String SELENIUM_IS_INTERNATIONAL_PROPERTY = "selenium.is_international";
    public static final String SELENIUM_PROD_DAYS_OFFSET_PROPERTY = "selenium.prod_days_offset";
    public static final String SELENIUM_PROD_MONTH_OFFSET_PROPERTY = "selenium.prod_month_offset";
    
    public static final String TESTNG_FAILED_XML_FILE = "testng-failed.xml";
    
    final static public int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
    
    /** Sure hope this test doesn't span over mid-night where the date will technically be different. */
    final static public int CURRENT_MONTH = Calendar.getInstance().get(Calendar.MONTH);
    
    /** Sure hope this test doesn't span over mid-night where the date will technically be different. */
    final static public int CURRENT_DAY = Calendar.getInstance().get(Calendar.DATE);
    
    /** 
     * In SL/Prod finding resort-room for "less than 3 days" is problematic, almost impossible,
     * not to have test fail, we are bumping checkIn-checkOut dates
     */
    final static public int prodDaysOffset = Integer.getInteger("SELENIUM_PROD_DAYS_OFFSET_PROPERTY", 150);
    
    /** Used to quickly alter check-in-date in production since availability can be difficult. */
    final static public int prodMonthOffset = Integer.getInteger("SELENIUM_PROD_MONTH_OFFSET_PROPERTY", 3);
    
    /** 
     * true = Light (Default), false = Dark
     * TODO, FIXME: Fix and be clear about what this is.
     */
    final static public String LIGHT_DARK_OVERRIDE = System.getProperty(SELENIUM_LIGHT_DARK_PROPERTY, "");
    final static public boolean IS_LIGHT_DARK_OVERRIDE = Boolean.getBoolean(SELENIUM_LIGHT_DARK_PROPERTY);
    
    /** The base URL we'll be working with, e.g. wdw-stage.disney.go.com, dlr-latest.etc */
    final static public String BASE_URL = System.getProperty(SELENIUM_BASE_URL_PROPERTY, "");
    
    /** A shortcut for lower-cased version of BASE_URL */
    final static public String BASE_URL_LC = BASE_URL.toLowerCase();
    
    /** To better detect the target environment instead of trying to use BASE_URL and LIGHT_DARK_OVERRIDE. */  
    final static public String TEST_ENVIRONMENT = System.getProperty(SELENIUM_TEST_ENVIRONMENT_PROPERTY, "UNKNOWN");
    
    /** Detects if we're running against the test-ng generated file for re-runs/retries. */
    final public static boolean TESTNG_RETRY_ATTEMPT = System.getProperty(SELENIUM_XML_FILESET_PROPERTY, "").equals(TESTNG_FAILED_XML_FILE);
    
    /** True if we're retrying a test or if the application is explicitly told configured so */ 
    public static boolean ENABLE_DEBUG_MODULE = TESTNG_RETRY_ATTEMPT || Boolean.getBoolean(SELENIUM_ENABLE_DEBUG_MODULE_PROPERTY);
    
    /** Suggests if the test should be taking screenshots for informational purposes. */
    final static public boolean IS_CAPTURE_INFO_SCREENSHOTS = Boolean.getBoolean(SELENIUM_CAPTURE_INFO_SCREENSHOTS_PROPERTY);
    
    /** Indicates if we're targeting the grid */
    public final static boolean SELENIUM_GRID = Boolean.getBoolean(SELENIUM_GRID_PROPERTY);
    
    /**
     * Checkout selenium.retrieve_db_user, true means use DB.  
     * The default should be the data-base though... this needs to be cleaned up.
     */
    final static public boolean USE_DB_USER = Boolean.getBoolean(SELENIUM_DB_USER_PROPERTY);
    
    // DB connect info - NAP7
    final static public String nap7_dbServer = "jdbc:mysql://qn7prmydb03/";
    final static public String nap7_dbName = "WDPROQA";
    final static public String nap7_dbUsername = "wdproqa_user";
    final static public String nap7_dbPassword = "wdproqa_password";
    
    // DB connect info - Noho
    final static public String noho_dbServer = "jdbc:mysql://dnhprmydb01.wdig.com/";
    final static public String noho_dbName = "WDPROQA";
    final static public String noho_dbUsername = "dash";
    final static public String noho_dbPassword = "dash1";
    
    // DB user info
    final static public String DB_ENVIROMENT = BASE_URL;
    final static public boolean DB_ANNUAL_PASSHOLDER = false;
    final static public boolean DB_PREMIUM_PASSHOLDER = false;
    /** This needs to be renamed to something less confusing */
    final static public boolean DB_USER_USED = Boolean.getBoolean(SELENIUM_USER_USED_PROPERTY);
    final static public boolean DB_USER_ACTIVE = true;
    
    /** The global system property line.separator */
    final static public String LINE_SEPARATOR = System.getProperty("line.separator", "\n");
    
    /** An alias for the global system property line.separator */
    final static public String NEW_LINE = LINE_SEPARATOR;
    
    /** The OS name as returned by the system property os.name */
    final static public String OS_NAME = System.getProperty("os.name", "ERROR").toLowerCase();
    
    /** The architecture type of JRE, this does not mean that the physical machine matches */
    final static public String OS_ARCH_TYPE = System.getProperty("os.arch", "ERROR");
    
    /** The architecture type of JRE, this does not mean that the physical machine matches */
    final static public String ARCH_TYPE = System.getProperty("sun.arch.data.model", "ERROR");
    
    /** An alias for File.separator */
    final static public String DIR_SEPARATOR = File.separator;
    
    /** The current path of the project */ 
    final static public String CURRENT_DIR = determineCurrentPath();
    
    /** For International behavior */
    static public boolean IS_INTERNATIONAL = Boolean.getBoolean(SELENIUM_IS_INTERNATIONAL_PROPERTY);
    
    /** The default timeout in seconds, should be a generous default time */
    final static public long GLOBAL_DRIVER_TIMEOUT = Integer.getInteger(SELENIUM_DEFAULT_TIMEOUT_PROPERTY, 60);
    
    /** The timeout (seconds) for finding web elements on a page, shouldn't be too long */
    final static public long ELEMENT_TIMEOUT = Integer.getInteger(SELENIUM_ELEMENT_TIMEOUT_PROPERTY, 10);
    
    /** The timeout (seconds) for page/DOM/transitions, should also be a generous */
    final static public long PAGE_TIMEOUT = Integer.getInteger(SELENIUM_PAGE_TIMEOUT_PROPERTY, 60);
    
    /**
     * For general debugging. Constructs a string of values that are set via
     * properties (e.g. JVM's -D args).
     * @return str 
     */
    public static String toStr() {
        String str = "Static variables" +
                "\n\tBASE_URL [" + BASE_URL + "]" +
                "\n\tTEST_ENVIRONMENT [" + TEST_ENVIRONMENT + "]" +
                "\n\tSELENIUM_GRID [" + SELENIUM_GRID + "]" +
                "\n\tUSE_DB_USER [" + USE_DB_USER + "]" +
                "\n\tLIGHT_DARK_OVERRIDE [" + LIGHT_DARK_OVERRIDE + "]" +
                "\n\tIS_LIGHT_DARK_OVERRIDE [" + IS_LIGHT_DARK_OVERRIDE + "]" +
                "\n\tGLOBAL_DRIVER_TIMEOUT [" + GLOBAL_DRIVER_TIMEOUT + "]" +
                "\n\tELEMENT_TIMEOUT [" + ELEMENT_TIMEOUT + "]" +
                "\n\tPAGE_TIMEOUT [" + PAGE_TIMEOUT + "]" +
                "";
        return str;
    }
    
    // Locator Strategy
    final public static int CSS_SELECTOR = 0;
    final public static int XPATH = 1;
    final public static int ID = 2;
    final public static int CLASS_NAME = 3;
    final public static int LINK_TEXT = 4;
    final public static int PARTIAL_LINK_TEXT = 5;
    final public static int NAME = 6;
    final public static int TAG_NAME = 7;
    
    // MYW Ticket Options
    final public static int MYW_NO_OPTIONS = 0;
    final public static int MYW_PARK_HOPPER = 1;
    final public static int MYW_WPF = 2;
    final public static int MYW_PARK_HOPPER_AND_WPF = 3;
    
    // Continue Shopping Options
    final public static int CONTINUE_SHOPPING_RESORTS = 0;
    final public static int CONTINUE_SHOPPING_THEME_PARK_TICKETS = 1;
    final public static int CONTINUE_SHOPPING_SPECIAL_EVENTS = 2;
    final public static int CONTINUE_SHOPPING_ANNUAL_PASSES = 3;
    final public static int CONTINUE_SHOPPING_WATER_PARKS = 4;
    
    // Annual Pass Options
    final public static int PREMIUM_ANNUAL_PASS = 0;
    final public static int ANNUAL_PASS = 1;
    final public static int SEASONAL_ANNUAL_PASS = 2;
    final public static int WEEKDAY_SELECT_PASS = 3;
    final public static int EPCOT_AFTER_4_PASS = 4;
    final public static int WATERPARK_AFTER_2_PASS = 5;
    
    // Special Event Options
    final public static int NIGHT_OF_JOY = 0;
    final public static int NOT_SO_SCARY = 1;
    final public static int VERY_MERRY_CHRISTMAS = 2;
    
    // Ticket Tab Options
    final static public int TAB_TICKET_THEME_PARK = 0;
    final static public int TAB_TICKET_SPECIAL_EVENTS = 1;
    final static public int TAB_TICKET_ANNUAL_PASSES = 2;
    final static public int TAB_TICKET_WATERPARK = 3;
    final static public int TAB_TICKET_PERSONAL_MAGIC = 4;
    
    // Personal Magic Experience Type
    final static public int PM_MY_MEMORY_MAKER = 0;
    final static public int PM_MY_STORY_MAKER = 1;
    final static public int PM_NO_OPTION = 2;
    
    // Delivery Method Options
    final public static int DELIVERY_PRINT_AT_HOME = 1;
    final public static int DELIVERY_WILL_CALL = 2;
    final public static int DELIVERY_US_DOMESTIC_STANDARD = 3;
    final public static int DELIVERY_US_DOMESTIC_EXPRESS = 4;
    final public static int DELIVERY_INTERNATIONAL_EXPRESS = 5;
    
    // Title Options
    final public static int TITLE_MR = 0;
    final public static int TITLE_MRS = 1;
    final public static int TITLE_MS = 2;
    final public static int TITLE_MISS = 3;
    final public static int TITLE_DR = 4;
    final public static int TITLE_PROF = 5;
    final public static int TITLE_CAPT = 6;
    final public static int TITLE_LT = 7;
    final public static int TITLE_REV = 8;
    
    // Suffix Options
    final public static int SUFFIX_I = 0;
    final public static int SUFFIX_II = 1;
    final public static int SUFFIX_III = 2;
    final public static int SUFFIX_IV = 3;
    final public static int SUFFIX_JR = 4;
    final public static int SUFFIX_SR = 5;
    final public static int SUFFIX_V = 6;
    
    // Payment Method Options
    final public static int PAYMENT_CREDIT_CARD = 0;
    final public static int PAYMENT_DISNEY_GIFT_CARD = 1;
    final public static int PAYMENT_DISNEY_REWARDS_CARD = 2;
    final public static int PAYMENT_CREDIT_CARD_DISNEY_GIFT_CARD = 3;
    final public static int PAYMENT_CREDIT_CARD_DISNEY_REWARDS_CARD = 4;
    final public static int PAYMENT_CREDIT_CARD_DISNEY_REWARDS_CARD_GIFT_CARD = 5;
    
    // Resort Tab Options
    final public static int RESORT_TAB_RESORT_OVERVIEW = 0;
    final public static int RESORT_TAB_ROOM_RATES = 1;
    final public static int RESORT_TAB_DINING = 2;
    final public static int RESORT_TAB_RECREATION = 3;
    final public static int RESORT_TAB_AMENITIES = 4;
    
    // Vacation Offer Component
    final public static int VACATION_OFFER_ROOM_COMPONENT = 0;
    final public static int VACATION_OFFER_TICKETS_COMPONENT = 1;
    final public static int VACATION_OFFER_PERSONAL_MAGIC_COMPONENT = 2;
    final public static int VACATION_OFFER_FLIGHT_COMPONENT = 3;
    final public static int VACATION_OFFER_GROUND_COMPONENT = 4;
    final public static int VACATION_OFFER_DINE_COMPONENT = 5;
    
    // Affiliation Options
    final public static int AFFILIATION_NO_OPTION = 0;
    final public static int AFFILIATION_FLORIDA_RESIDENT = 1;
    final public static int AFFILIATION_DISNEY_VACATION_CLUB_MEMBER = 2;
    final public static int AFFILIATION_ANNUAL_PASSHOLDER = 3;
    
    // Discount Association Dropdown Options
    final public static int DISCOUNT_ASSOCIATION_STD_GUEST = 0;
    final public static int DISCOUNT_ASSOCIATION_FL_RESIDENT = 1;
    
    // DME Number Trip Option
    final public static int DME_BOTH_WAYS = 0;
    final public static int DME_TO_WALT_DISNEY_WORLD_RESORT_HOTEL = 1;
    final public static int DME_FROM_WALT_DISNEY_WORLD_RESORT_HOTEL = 2;
    
    // DME Special Request Option
    final public static int DME_SPECIAL_REQUEST_WHEELCHAIR = 0;
    final public static int DME_SPECIAL_REQUEST_SERVICE_ANIMAL = 1;
    final public static int DME_SPECIAL_REQUEST_HEARING_IMPAIRED = 2;
    final public static int DME_SPECIAL_REQUEST_VISION_IMPAIRED = 3;
    
    // Ground Transportation Options
    final public static int GROUND_TRANSPORTATION_DME = 0;
    final public static int GROUND_TRANSPORTATION_RENTAL_CAR = 1;
    final public static int GROUND_TRANSPORTATION_AIRPORT_TRANSFER_SERVICES = 2;
    
    // Dine Options
    final public static int DINE_NO_PLAN = 2;
    final public static int DINE_QUICK_SERVICE_PLAN = 4;
    final public static int DINE_DISNEY_PLAN = 7;
    final public static int DINE_DELUXE_PLAN = 10;
    final public static int DINE_PREMIUM_PLAN = 13;
    final public static int DINE_PLATINUM_PLAN = 16;
    
    // Airport Transfer Options
    final public static int AIRTRANS_NOOPTION = 0;
    final public static int AIRTRANS_STRLIMO = 1;
    final public static int AIRTRANS_TOWNCAR = 2;
    final public static int AIRTRANS_SPORTUTE = 3;
    final public static int AIRTRANS_EXECLIMO = 4;
    final public static int AIRTRANS_EXCLVAN = 5;
    final public static int AIRTRANS_AIRSHUTT = 6;
    
    // Rental Car Age
    final public static int TWENTYONE_FOUR = 1;
    final public static int TWENTYFIVE_UP = 2;
    
    // Rental Car Results
    final public static int NO_RENTAL_CAR = 1;
    final public static int ECONOMY_CAR = 2;
    final public static int COMPACT_CAR = 3;
    final public static int MIDSIZE_CAR = 4;
    final public static int FULLSIZE_CAR = 5;
    final public static int PREMIUM = 6;
    final public static int LUXURY = 7;
    final public static int CONVERTIBLE = 8;
    final public static int MINIVAN = 9;
    final public static int MIDSIZE_SUV = 10;
    final public static int STANDARD_SUV = 11;
    
    //Excel File 
    final  public static String inputfile =  "Z:\\Automation\\Lodging\\DataProvider.xls";
    final public static String outputfile = "Z:\\Automation\\Lodging\\DataProvider_output.xls";
    final public static String output_timestamp = "Z:\\Automation\\Lodging\\";		
    
	
    
    /**
     * Defaults to "./" if there's an exception of any sort.
     * @warning Exceptions are swallowed.
     * @return Constants.DIR_SEPARATOR
     */
    final private static String determineCurrentPath() {
        try {
            return (new File(".").getCanonicalPath()) + Constants.DIR_SEPARATOR; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "." + Constants.DIR_SEPARATOR;
    }

}

