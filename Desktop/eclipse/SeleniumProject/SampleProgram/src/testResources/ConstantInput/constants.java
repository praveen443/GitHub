package testResources.ConstantInput;

public class constants {

	
	//Driver paths
	public static final String chromeDriverPath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
	public static final String IE_32bit_DriverPath = System.getProperty("user.dir")+"\\drivers\\IEDriverServer_32_bit.exe";
	public static final String IE_64bit_DriverPath = System.getProperty("user.dir")+"\\drivers\\IEDriverServer_64_bit.exe";

	//Properties file path
	public static final String propertiesFilePath = System.getProperty("user.dir")+"\\src\\testResources\\testConfiguration\\testDataPropertiesFile.properties";

	//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"        
    public static final String dbUrl = "jdbc:mysql://localhost:3306/employeemgmt";                   

    //Database Username     
    public static final String username = "root";   
     
    //Database Password     
    public static final String password = "D1$NeY4321";             

    //Query to Execute      
    public static final String query = "SELECT * FROM Persons WHERE PersonID='2';";
    
    
    //Sample data used for testing practice
    public static final String filePath1="C:\\Users\\praveen\\Documents\\PaySlips_2016\\sampletextdoc.txt";
    
    public static final String screenshotFilePath=System.getProperty("user.dir")+"\\Screenshots\\";
}
