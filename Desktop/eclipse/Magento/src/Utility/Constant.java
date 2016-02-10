//***************************************************************************************************
//Purpose      : Constants Variables are declared as public static, 
//               so that they can be called in any other methods without instantiate the class.
//Author       : Praveen varma
//Created Date : 10-08-2015
//***************************************************************************************************

package Utility;

/**
 * @summary : Constant Variables are declared as final, so that they cannot be changed during the execution.
 * @author  : Praveen varma
 * @version : Created 11-12-2015
 */

//Constant Variables are declared as final, so that they cannot be changed during the execution.		
//Declared as 'public', so that it can be used in other classes of this project
//Declared as 'static', so that we do not need to instantiate a class object
//Declared as 'final', so that the value of this variable can be changed
// 'String' & 'int' are the data type for storing a type of value	

public class Constant{
	
   //Used to launch the browserType
   public static final String browser = "firefox"; 
   
   //Used for applying the implicit wait
   public static final int WaitTime = 10;           
   
   //Used for the Login - (Ref-TC1)
   public static final String AppURL = "http://enterprise-admin.user.magentotrial.com/index.php/admin/";
   public static final String Username = "admin";
   public static final String Password = "123123a";
   
   //Used for validating the Customer Name (Ref - TC27)
   public static final String ValidCustomerName = "Clay Lock"; 
   
   //Used for adding the new attribute (Ref - TC32)
   public static final String Admin = "admin";
   public static final String English = "Language";
   
   //Used for creating a new customer - (Ref-TC2)
   public static final String FirstName = "Jhonny";  // Used in the "TC3_Create New order for New Customer"
   public static final String StreetAddress = "204-Denmark street,Chicago";
   
   //Used to read the data from excel sheet - (Ref - TC4)
   public static final String FileName = "\\TestData\\Magento_TestData.xlsx";
   public static final String SheetName = "SearchWithOrderNo";
   
   //Used to read the object repository properties file
   public static final String PropertyFileName = "\\src\\ObjectRepository\\Objects.Properties";
   
   //Used to place the screenshot into the destination folder
   //public static final String FilePath = System.getProperty("User.dir")+Constant.filePath;
   public static final String filePath = "\\Screenshots";
   public static String FilePath = System.getProperty("user.dir")+Constant.filePath;
   
   //Used for initialising the Chrome browser
   public static final String chromeDriverPath = "C:\\Users\\praveen\\Documents\\Selenium_DriverSoftwares\\chromedriver.exe";
   public static final String IEDriverPath = "C:\\Users\\praveen\\Documents\\Selenium_DriverSoftwares\\IEDriverServer.exe";

}
