
/***********************************************************************************************************************
 * FileName - Log.java
 * 
 * (c) Disney. All rights reserved.
 * 
 * $Author: kaiy001 $ $Revision: #1 $ $Change: 715510 $ $Date: Aug 27, 2012 $
 ***********************************************************************************************************************/

package selenium.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import selenium.common.exception.LogInitializationException;

/**
 * A Selenium Web-Driver friendly class that helps setup standard logging. 
 * In particular this class takes into account the unique web-driver instance 
 * and the test associated with that instance.
 * 
 * SAP - 8/11/14 - Updates to clean up the code base and to also prevent
 * swallowing exceptions which end up hiding errors when used with TestNG.
 * 
 * TODO: Will also need to investigate the viability of logging to TestNg.
 * 
 * @author kaiy001, SonHuy.Pham@Disney.com
 */
public class Log {

    final static public String FOLDER_PATH = Log.initializeLogFolder();
    final private static Logger defaultLogger = Log.initializeDefaultLogger();
    
    // To keep track of driver-logger for each test
    private static Map<String, Logger> logNormalDriverLogger = new HashMap<String, Logger>();
    private static Map<String, Logger> logErrorDriverLogger = new HashMap<String, Logger>();
    private static Map<String, Log> logInstances = new HashMap<String, Log>();
    
    private Map<String, Logger> normalLoggers = new HashMap<String, Logger>();
    private Map<String, Logger> errorLoggers = new HashMap<String, Logger>();
    private String testName = "";
    private boolean isUsingStdOut = false;
    private boolean isUsingStdErr = false;

    /**
     * A very busy constructor that initializes an essential bunch of loggers 
     * that support regular logging as well error logging.
     * 
     * @param testName
     * @param driver
     * @throws Exception
     */
    public Log(String testName, WebDriver driver) throws Exception {
        try {
            this.testName = testName;
            
            // We're using the driver to generate a generally unique key to
            // store into maps and so forth.
            String key = driver.toString();

            synchronized (logInstances) {
                if(!logInstances.containsKey(key)) {
                    logInstances.put(key, this);
                }
            }

            String loggerKey = testName + ".log";
            String fullLogPath = FOLDER_PATH + "log_" + testName + ".log";
            Logger logger = initializeLog(driver, loggerKey, fullLogPath, false);
            normalLoggers.put(testName, logger);
            
            synchronized(logNormalDriverLogger) {
                logNormalDriverLogger.put(key, logger);
            }

            loggerKey = testName + ".errorlog";
            fullLogPath = FOLDER_PATH + "error_log_" + testName + ".log";
            logger = initializeLog(driver, loggerKey, fullLogPath, true);
            errorLoggers.put(testName, logger);
            
            synchronized(logErrorDriverLogger) {
                logErrorDriverLogger.put(key, logger);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    /**
     * Initializes the logger instance based on testName and the filePath.
     * 
     * @param testName
     * @param filePath
     * @return
     * @throws Exception
     */
    private Logger initializeLog(WebDriver driver, String testName, String filePath, boolean isErrorLog) throws Exception {
        
        Logger logger = Logger.getLogger(testName);
        
        // On retries (second test run within the same thread/process), we need 
        // to check if the logger already has the formatter and presumably the 
        // file handler.
        
        if(retrieveFormatter(logger) == null) {
            File file = new File(filePath);
            FileHandler fileHandler = (file.exists() && file.isFile()) ? 
                    new FileHandler(filePath, true) : new FileHandler(filePath);
                    
            DefaultLogFormatter formatter = new DefaultLogFormatter(isErrorLog);
            formatter.setDriver(driver);
            formatter.setTestName(testName);
            fileHandler.setFormatter(formatter);
            
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            
        } else {
            // This probably won't show up anywhere on the console.
            logger.config("Log handler and formatter has already been added for " + filePath);
        }
        
        return logger;
    }
    
    /**
     * A convenience function for intialization and error handling.
     * @param testName
     * @param driver
     * @return
     * @throws Exception
     */
    public static Log initialize(String testName, WebDriver driver) throws Exception {
        try {
            // Setup the log file and initialize the logger.
            Log log = new Log(testName, driver);
            Log.log(driver).info(driver.toString());
            return log;

        } catch (Exception ex) {
            throw new LogInitializationException("Log did not initialize", ex, driver);
        }
    }

    private static String initializeLogFolder() {
        String filePath = "";
        try {
        	String currentDirectory = new java.io.File(".").getCanonicalPath();

            String propertiesFile = currentDirectory 
                    + Constants.DIR_SEPARATOR
                    + "config.properties";

            FileInputStream inputStream = new FileInputStream(propertiesFile);
            Properties property = new Properties();
            property.load(inputStream);

            // We're allowing the user to override the default log file path.

            filePath = property.getProperty("logFilePath");

            // If nothing valid was provided previously, then we'll use 
            // defaults.
            if (filePath == null || filePath.isEmpty() 
                    || filePath.toLowerCase().equals("default")) {

                filePath = currentDirectory 
                        + Constants.DIR_SEPARATOR 
                        + "log"
                        + Constants.DIR_SEPARATOR;
            }
            
            // if the directory does not exist, create it
     		File dirPath = new File(currentDirectory + Constants.DIR_SEPARATOR 
                    + filePath);
            if (!dirPath.exists()) dirPath.mkdir();

        } catch(Exception ex) {
            throw new RuntimeException(ex.toString(), ex);
        }

        return filePath;
    }

    /**
     * Initializes the "default" logger, which is no longer the built-in
     * anonymous logger provided by JUL.
     * 
     * @return
     */
    private static Logger initializeDefaultLogger() {
        try {
            Logger logger = Logger.getLogger("wdpr.default");
            Handler handler = new ConsoleHandler();
            Formatter formatter = new CompactLogFormatter(false);
            handler.setFormatter(formatter);
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.INFO);
            return logger;
        } catch(Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "[" + testName + "]";
    }

    public String getTestName() {
        return this.testName;
    }
    
    public boolean isUsingStdStreamOut() {
        return isUsingStdErr || isUsingStdOut;
    }
    
    public boolean isUsingStdOut() {
        return isUsingStdOut;
    }

    public boolean isUsingStdErr() {
        return isUsingStdErr;
    }
    
    public Map<String, Logger> getNormalLoggers() {
        return normalLoggers;
    }
    
    public Map<String, Logger> getErrorLoggers() {
        return errorLoggers;
    }
    
    /**
     * This logger is a globally scoped logger intended only to be used when
     * the web-driver has not yet been initialized or when it is unavailable
     * i.e. NULL.
     * 
     * TODO: Update so that the default QaTest will associate this with a test.
     * Meaning, it will have a LOG file to write to.
     * 
     * @return
     */
    public static final Logger getDefaultLogger() {
        return defaultLogger;
    }
    
    /**
     * A quick and dirty way to swap out the default log formatter with
     * {@link CompactLogFormatter}.  Also automatically enables output to 
     * std-out.
     * 
     * @param driver
     */
    static public void replaceLogFormatter(WebDriver driver) {
        Logger logger = Log.log(driver);
        CompactLogFormatter formatter = new CompactLogFormatter(false);
        formatter.setDriver(driver);
        Log log = Log.getLog(driver);
        if(log != null) {
            formatter.setTestName(log.getTestName());
        }
        formatter.setStdOutEnabled(true);
        replaceFormatter(logger, formatter);
        if(log != null) { 
            log.isUsingStdOut = true;
        }
    }
    
    /**
     * A quick and dirty way to swap out the default error log formatter with
     * {@link CompactLogFormatter}.
     * 
     * @param driver
     */
    static public void replaceErrorLogFormatter(WebDriver driver) {
        Logger logger = Log.errorLog(driver);
        CompactLogFormatter formatter = new CompactLogFormatter(true);
        formatter.setDriver(driver);
        Log log = Log.getLog(driver);
        if(log != null) {
            formatter.setTestName(log.getTestName());
        }
        replaceFormatter(logger, formatter);
    }
    
    /**
     * A convenience function to quickly override defaults.
     * @param driver
     * @param formatter
     */
    static public void replaceFormatter(Logger logger, DefaultLogFormatter formatter) {
        Handler[] handlers = logger.getHandlers();
        for(int i = 0; handlers != null && i < handlers.length; ++i) {
            if(handlers[i] instanceof FileHandler) {
                if(handlers[i].getFormatter() instanceof DefaultLogFormatter) {
                    handlers[i].setFormatter(formatter);
                }
            }
        }
    }
    
    /**
     * Configures routing regular messages to std-out.
     * @param driver
     * @throws Exception
     */
    static public void setLogStdOut(WebDriver driver, boolean enable) throws Exception {
        Log log = Log.getLog(driver);
        Logger logger = Log.log(driver);
        DefaultLogFormatter formatter = retrieveFormatter(logger);
        if(formatter != null) {
            formatter.setStdOutEnabled(enable);
            if(log != null) {
                log.isUsingStdOut = enable;
            }
        }
    }
    
    /**
     * A quick way to get a hold of the formatter associated with the logger.
     * @param logger
     * @return The formatter or null if no formatter was found.
     * @throws Exception
     */
    static public DefaultLogFormatter retrieveFormatter(Logger logger) throws Exception {
        Handler[] handlers = logger.getHandlers();
        for(int i = 0; handlers != null && i < handlers.length; ++i) {
            if(handlers[i] instanceof FileHandler) {
                if(handlers[i].getFormatter() instanceof DefaultLogFormatter) {
                    return (DefaultLogFormatter)handlers[i].getFormatter();
                }
            }
        }
        return null;
    }

    /**
     * @param driver
     * @return If the Log class was initialized, it will attempt to return an 
     * instance of a logger that maps to the driver.  Otherwise, it will return
     * a default (i.e. anonymous) instance of a logger.
     */
    public static Logger log(WebDriver driver) {
        if(driver != null) {
            String key = driver.toString();
            synchronized(logNormalDriverLogger) {
                if(logNormalDriverLogger.containsKey(key)) {
                    return logNormalDriverLogger.get(key);
                }
            }
        }
        return defaultLogger;
    }

    /**
     * THIS IS USED EXLUSIVELY FOR EXCEPTION HANDLING.
     * 
     * Best practice is to log all errors with {@link #log(WebDriver)} 
     * using <code>severe()</code>.
     * 
     * The error log should be used as a place to store detailed information
     * regarding errors- particularly exceptions and nothing more.
     * 
     * @param driver
     * @return If the Log class was initialized, it will attempt to return an 
     * instance of a logger that maps to the driver for logging errors.  
     * Otherwise, it will return a default (i.e. anonymous) instance of a 
     * logger.
     */
    public static Logger errorLog(WebDriver driver) {
        if(driver != null) {
            String key = driver.toString();
            synchronized(logErrorDriverLogger) {
                if(logErrorDriverLogger.containsKey(key)) {
                    return logErrorDriverLogger.get(key);
                }
            }
        }
        return defaultLogger;
    }
    
    /**
     * Normally this would somehow be a variation on a singleton - but due to
     * the way that the tests are instantiated we can't easily implement that
     * particualr design pattern.  So for now we'll do a kind of reverse-lookup 
     * to retrieve the last-known-instance. 
     * 
     * @param driver
     * @return
     */
    public static Log getLog(WebDriver driver) {
        if(driver != null) {
            String key = driver.toString();
            synchronized(logInstances) {
                if(logInstances.containsKey(key)) {
                    return logInstances.get(key); 
                }
            }
        }
        return null;
    }
    
    /**
     * Provided an instance of a WebDriver, this will return the test-name
     * registered with the Log helper class.
     * 
     * @param driver
     * @return Name of the current test associated with the driver instance.
     */
    public static String lookupTestName(WebDriver driver) {
        Log log = getLog(driver);
        if(log != null) {
            return log.getTestName();
        }
        defaultLogger.warning("DRIVER NOT FOUND FOR TEST LOOK-UP");
        return "";
    }

    /**
     * A class that overrides a few of the default implementations within
     * {@link DefaultLogFormatter}.
     * 
     * @author SonHuy.Pham@Disney.com
     */
    public static class CompactLogFormatter extends DefaultLogFormatter {
        
        public CompactLogFormatter(boolean errorFormatter) {
            super(errorFormatter);
            setTestNamePrefixEnabled(true);
        }
        
        @Override
        protected String getDate(LogRecord record) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
            return formatter.format(new Date(record.getMillis()));
        }
        
        @Override
        protected String getLogLevel(LogRecord record) {
            if(record.getLevel().equals(Level.WARNING) || record.getLevel().equals(Level.SEVERE)) {
                return record.getLevel().getLocalizedName() + " ";
            }
            return "";
        }
        
        @Override
        protected String getWhitespace(LogRecord record) {
            return "";
        }
    }
    
    /**
     * The initial implementation of a log formatter.  For a log formatter that
     * uses a slightly less "noisy" date-time-format, see 
     * {@link CompactLogFormatter}.
     * 
     * SAP - 8/11/14 - Refactored to include more options for logging as well
     * as providing an option to forward output to the console (std out, err).
     * 
     * @author kaiy001, SonHuy.Pham@Disney.com
     */
    public static class DefaultLogFormatter extends Formatter {
        
        private WebDriver driver = null;
        private boolean errorFormatter = false;
        private boolean stdOutEnabled = false;
        private boolean stdErrEnabled = false;
        private boolean testNamePrefixEnabled = false;
        private Level stdStreamLevel = Level.ALL;
        private String testName = "";
        
        public DefaultLogFormatter(boolean errorFormatter) {
            this.errorFormatter = errorFormatter;
        }
        
        public void setDriver(WebDriver driver) {
            this.driver = driver;
        }
        
        public void setStdOutEnabled(boolean stdOutEnabled) {
            this.stdOutEnabled = stdOutEnabled;
        }
        
        public void setStdErrEnabled(boolean stdErrEnabled) {
            this.stdErrEnabled = stdErrEnabled;
        }
        
        public void setTestNamePrefixEnabled(boolean testNamePrefixEnabled) {
            this.testNamePrefixEnabled = testNamePrefixEnabled;
        }
        
        public void setStdStreamLevel(Level stdStreamLevel) {
            this.stdStreamLevel = stdStreamLevel;
        }
        
        public void setTestName(String testName) {
            this.testName = testName;
        }
        
        protected String getDate(LogRecord record) {
            return (new Date(record.getMillis())).toString();
        }
        
        protected String getSource(LogRecord record) {
            String className = record.getSourceClassName();
            if(className == null || className.isEmpty()) {
                className = "UNKNOWN_CLASS";
            }
            String methodName = record.getSourceMethodName();
            if(methodName == null || methodName.isEmpty()) {
                methodName = "";
            } else {
                methodName = " " + methodName;
            }
            return "[".concat(className).concat(methodName).concat("]");
        }
        
        protected String getLogLevel(LogRecord record) {
            return record.getLevel().getLocalizedName();
        }
        
        protected String getWhitespace(LogRecord record) {
            int localizedNameSize = record.getLevel().getLocalizedName().length();
            String whitespace = ":";
            for (int i = 0; i < 11 - localizedNameSize; i++) {
                whitespace += " ";
            }
            return whitespace;
        }
        
        protected String getFormattedMessage(LogRecord record) {
            return getSource(record)
                    .concat(" ")
                    .concat(getLogLevel(record))
                    .concat(getWhitespace(record))
                    .concat(formatMessage(record));
        }
        
        @Override
        public String format(LogRecord record) {
            
            String msg = getFormattedMessage(record);
            
            StringBuilder buffer = new StringBuilder()
                .append(getDate(record))
                .append(" ")
                .append(msg).append(Constants.NEW_LINE);
            
            if (record.getThrown() != null) {
                try {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    record.getThrown().printStackTrace(pw);
                    pw.close();
                    buffer.append(sw.toString());
                } catch (Exception ex) {
                    // No-op.  Swallow exceptions for now.
                }
            }
            String result = buffer.toString();
            
            if(stdErrEnabled) {
                if(stdStreamLevel.intValue() <= record.getLevel().intValue()) {
                    System.err.println(testNamePrefixEnabled ? testName + " " + msg : msg);
                }
            } else if(stdOutEnabled) {
                if(stdStreamLevel.intValue() <= record.getLevel().intValue()) {
                    if(record.getLevel().equals(Level.SEVERE) || record.getLevel().equals(Level.WARNING)) {
                        System.err.println(testNamePrefixEnabled ? testName + " " + msg : msg);
                    } else {
                        System.out.println(testNamePrefixEnabled ? testName + " " + msg : msg);
                    }
                }
            }
            
            if(!errorFormatter 
                    && driver != null 
                    && record.getLevel() != null 
                    && record.getLevel().equals(Level.SEVERE)) {
                
                // TODO:
                // We're going to forward severe messages to the error log 
                // automatically and discourage the user from having to juggle
                // between multiple instances of a logger.
                // Log.errorLog(driver).severe(result);
            }
            //Reporter.log("hello world");
            return result;
        }
    }
}

