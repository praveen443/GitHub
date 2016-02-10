//-----------------------------------------------------------------------------------------------------
//******************************* Generic Functions ***************************************************
//-----------------------------------------------------------------------------------------------------
package Utility;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Reporter;

public class GenericFunctions 
{
	
//----------------------------------------------------------------------------------------------------
//Method-1   : RandomCitySelection_FromArrayList (Ref:- TC3).
//PURPOSE    : Created this method to select the random city name from the array list.
//Author     : Praveen varma.
//CreatedDate: 01-09-2015.
//----------------------------------------------------------------------------------------------------

	public static String RandomCitySelection_FromArrayList()
	{
		//Array list containing distinct city values
     	String[] City = {"California","Chicago","Africa","NewYork","SiliconValley","Virginia","Texas","Paris"};

		//Get the count of cities
		int CountOfCities = new Random().nextInt(City.length);
			
		//Select the random value from the array list 'City'
		String random = (City[CountOfCities]);
		Reporter.log("Selected City is : "+random);
		System.out.println("Selected City is : "+random);
	
		return random;
	}
    
//----------------------------------------------------------------------------------------------------
// End of Method-1
//----------------------------------------------------------------------------------------------------
	
//----------------------------------------------------------------------------------------------------
//Method-2   : string_LastNameGeneration (Ref:- TC3).
//PURPOSE    : Created this method to generate the random string for the name.
//Author     : Praveen varma.
//CreatedDate: 01-09-2015.
//----------------------------------------------------------------------------------------------------
	   
	
	public static String string_LastNameGeneration() 
	{
		//For generating the 4-characters alphabetic random string
	    String generatedString = RandomStringUtils.randomAlphabetic(4);
	    
	    //To print the randomly generated 4-characters string in the console
	    Reporter.log("Entered the Last name : "+generatedString);
	    System.out.println("Entered the Last name : "+generatedString);
	    
	    //To return the method type string
	    return generatedString;
	}
	
//----------------------------------------------------------------------------------------------------
// End of Method-2
//----------------------------------------------------------------------------------------------------
	

//----------------------------------------------------------------------------------------------------
//Method-3   : RndPhoneNumGenerator (Ref:- TC3).
//PURPOSE    : Created this method to generate the random 10-digit phone number.
//Author     : Praveen varma.
//CreatedDate: 01-09-2015.
//----------------------------------------------------------------------------------------------------
	
	public static String RndPhoneNumGenerator() 
	{
		String randomPhoneNumGeneartor = RandomStringUtils.randomNumeric(10);
		Reporter.log("Entered the PhoneNumber : "+randomPhoneNumGeneartor);
		System.out.println("Entered the PhoneNumber : "+randomPhoneNumGeneartor);  // RandomStringUtils.randomNumeric(10);
		 
		return randomPhoneNumGeneartor;		
	}
	
//----------------------------------------------------------------------------------------------------
// End of Method-3
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
//Method-4   : RndZipCodeGeneartor (Ref:- TC3).
//PURPOSE    : Created this method to generate the random 6-digit number.
//Author     : Praveen varma.
//CreatedDate: 01-09-2015.
//----------------------------------------------------------------------------------------------------
	
	public static String RndZipCodeGeneartor() 
	{
		String RandomZipCodeGeneartor = RandomStringUtils.randomNumeric(6);
		Reporter.log("Entered the ZipCode : "+RandomZipCodeGeneartor);
		System.out.println("Entered the ZipCode : "+RandomZipCodeGeneartor);
        
		return RandomZipCodeGeneartor;
	}
	
//----------------------------------------------------------------------------------------------------
// End of Method-4
//----------------------------------------------------------------------------------------------------
	

//----------------------------------------------------------------------------------------------------
//Method-5   : GetCurrentWorkingDirectory (Ref:- "TakeScreenShot.java" file in the Utility Package).
//PURPOSE    : Created this method to generate the random 6-digit number.
//Author     : Praveen varma.
//CreatedDate: 01-09-2015.
//----------------------------------------------------------------------------------------------------
	
	//To get the current directory of the system
	public static void GetCurrentWorkingDirectory()
	{
		//Here in this program,
		//we are getting the current directory of the system with the help of getproperty() method as shown in below
		String CurrentDirectory = System.getProperty("User.dir");
		System.out.println("Current working directory is : "+CurrentDirectory);		
	}

//----------------------------------------------------------------------------------------------------
// End of Method-5
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
//Method-6   : PickRandomOrderNumber (Ref:- TC4).
//PURPOSE    : Created this method to pick the random order number from the arraylist.
//Author     : Praveen varma.
//CreatedDate: 01-09-2015.
//----------------------------------------------------------------------------------------------------
	
	 //Created function to pick the random order number from the arraylist (Ref - TC4)
	 public static String PickRandomOrderNumber()
	 {			
		//Array list containing distinct city values
	    String[] RandomOrderNumber = {"100000201","100000202","100000203","100000204","100000209","100000211",
	     			                  "100000205","100000206","100000207","100000208","100000210","100000212"};

		//Get the count of OrderNumbers
		int CountOfOrderNumbers = new Random().nextInt(RandomOrderNumber.length);
				
		//Select the random Order number from the array list 'City'
		String random = (RandomOrderNumber[CountOfOrderNumbers]);
		Reporter.log("Selected Random OrderNumber is : "+random);
		System.out.println("Selected Random OrderNumber is : "+random);
		
		return random;		
	 }
	 
//----------------------------------------------------------------------------------------------------
// End of Method-6
//----------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------
//Method-7   : Random_2digitInteger (Ref:- TC11).
//PURPOSE    : Created this method to generate the random 2-digit integer.
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//----------------------------------------------------------------------------------------------------
	 
	//Created this function to generate the random two digit integer (Ref - TC: 11)
	public static String Random_2digitInteger() 
	{
		String randomNumber = RandomStringUtils.randomNumeric(2);
		System.out.println("Entered the 2-digit Number : "+randomNumber); 
		
		return randomNumber;			
	}	
	 
//----------------------------------------------------------------------------------------------------
// End of Method-7
//----------------------------------------------------------------------------------------------------

	 
//----------------------------------------------------------------------------------------------------
//Method-8   : Generate_2digitRandomInt_InSpecificRange (Ref:- TC11).
//PURPOSE    : Created this method to generate the random 2-digit integer within a specified range.
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//----------------------------------------------------------------------------------------------------
		 
		/* //Created this function to generate the random two digit integer (Ref - TC: 11)
		 public static String Generate_2digitRandomInt_InSpecificRange() 
		 {
			 
			 Random r = new Random();
			 int i = r.nextInt(30 - 2) + 2;
			 
			 int min = 65;
			 int max = 80;

			 Random r = new Random();
			 int i1 = r.nextInt(max - min + 1) + min;
			 
			 System.out.println("Generated the 2-digit random number is : "+i1);
			return Generate_2digitRandomInt_InSpecificRange();			
		 }	
		 */
//----------------------------------------------------------------------------------------------------
// End of Method-8
//----------------------------------------------------------------------------------------------------

	 
//----------------------------------------------------------------------------------------------------
//Method-9   : string_LastNameGeneration (Ref:- TC12).
//PURPOSE    : Created this method to generate the random string for the Gift Wrapping Design value
//Author     : Praveen varma.
//CreatedDate: 01-09-2015.
//----------------------------------------------------------------------------------------------------
	 
	public static String string_GiftWrappingDesign() 
	{
		//For generating the 4-characters alphabetic random string
		String generatedString = RandomStringUtils.randomAlphabetic(4)+"_App";
		    
		//To print the randomly generated 4-characters string in the console
		Reporter.log("Entered the String Value in 'Gift Wrapping Design' Text-box is : "+generatedString);
		System.out.println("Entered the String Value in 'Gift Wrapping Design' Text-box is : "+generatedString);
		    
		//To return the method type string
		return generatedString;
    }
		
//----------------------------------------------------------------------------------------------------
// End of Method-9
//----------------------------------------------------------------------------------------------------

		
//----------------------------------------------------------------------------------------------------
//Method-10  : Random_2digitInteger (Ref:- TC11).
//PURPOSE    : Created this method to generate the random 2-digit integer.
//Author     : Praveen varma.
//CreatedDate: 02-09-2015.
//----------------------------------------------------------------------------------------------------
			 
	//Created this function to generate the random two digit integer (Ref - TC: 11)
	public static String Random_PriceValue() 
	{
		 String randomPriceValue = RandomStringUtils.randomNumeric(2);
		 //System.out.println("Price Value is entered as: "+ randomPriceValue);	
		 Reporter.log("Enter the Price Value : "+ randomPriceValue);	
		 return randomPriceValue;			
	}	
			 
//----------------------------------------------------------------------------------------------------
// End of Method-10
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
//Method-11  : string_EmailID (Ref:- TC26).
//PURPOSE    : Created this method to generate the random string for the Email ID field
//Author     : Praveen varma.
//CreatedDate: 21-09-2015.
//----------------------------------------------------------------------------------------------------
	 	 
	public static String string_EmailID() 
	{
	 	 //For generating the random Email-Id with 8 characters alphaNumeric string
	 	 String generatedString = Constant.FirstName+RandomStringUtils.randomNumeric(4)+"@yahoomail.com";
	 		    
	 	 //To print the randomly generated 4-characters string in the console
	 	 Reporter.log("Enter the Email-Id : "+generatedString);
	 	 System.out.println("Enter the Email-Id : "+generatedString);
	 		    
	 	 //To return the method type string
	 	 return generatedString;
	}
	 		
//----------------------------------------------------------------------------------------------------
// End of Method-11
//----------------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------------------
//Method-12  : string_RandomPassword (Ref:- TC26).
//PURPOSE    : Created this method to generate the random string for the Password field
//Author     : Praveen varma.
//CreatedDate: 21-09-2015.
//----------------------------------------------------------------------------------------------------
		 	 
    public static String string_RandomPassword() 
    {
		 //For generating the random string password with 6-characters  
		 String generatedString = RandomStringUtils.randomAlphabetic(6);
		 		    
		 //To print the randomly generated 6-characters string in the console
		 Reporter.log("Enter the Password : "+generatedString);
		 System.out.println("Enter the Password : "+generatedString);
		 		    
		 //To return the method type string
		 return generatedString;
	}
		 		
//----------------------------------------------------------------------------------------------------
// End of Method-12
//----------------------------------------------------------------------------------------------------
	 
//----------------------------------------------------------------------------------------------------
//Method-13  : randomAlphanumericString (Ref:- TC32).
//PURPOSE    : Created this method to generate the random 8 characters alphanumeric string 
//Author     : Praveen varma.
//CreatedDate: 28-09-2015.
//----------------------------------------------------------------------------------------------------
    
    public static String randomAlphanumericString() {
       
    	int len = 8;
        String alphaNumericString = "abcdefghijklmnopqrstuvwxyz1234567890";

        // creating the object for string builder
        StringBuilder sb = new StringBuilder(len);

        try {

            String PASSSTRING = alphaNumericString;
            // creating the object of Random class
            Random rnd = new Random();
            for (int i = 0; i < len; i++) {
                // generating random string
                sb.append(PASSSTRING.charAt(rnd.nextInt(PASSSTRING.length())));
            }
        } catch (Exception e) {
        e.printStackTrace();
        }

        // returning the random string
        return sb.toString();
    }// randomString()
    
//----------------------------------------------------------------------------------------------------
// End of Method-13
//----------------------------------------------------------------------------------------------------
    
}		


