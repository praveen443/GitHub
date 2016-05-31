package TestCases;


import org.testng.annotations.Test;

import Utility.Constants;
import Utility.Randomness;
import Utility.TestReporter;

public class UtilityClassesExamples {
	//Used the below statement to get the OS name
	public static final String OS_Name = System.getProperty("os.name","ERROR").toLowerCase();

	@Test
	public void testUtilities(){

		//To generate the random string
		String randomString  = Randomness.randomString(4);
		TestReporter.logStep(randomString);
		
		//To generate the random Number
		String randomNumber = Randomness.randomNumber(4);
		TestReporter.logStep(randomNumber);
		
		//To generate the random AlphaNumeric value
		String randomAlphaNumeric = Randomness.randomAlphaNummeric(4);
		TestReporter.logStep(randomAlphaNumeric);
		
		//To generate the Current Date and Time value
		String generateCurrentDateAndTime = Randomness.generateCurDateTime();
		TestReporter.logStep(generateCurrentDateAndTime);
		
		//To get the Operating System name
		String getOSname = Constants.OS_NAME;
		TestReporter.logStep(getOSname);
		
		/*int getWebDriverDefaultTestTimeOut = WebDriverSetUp.getDefaultTestTimeout();
		TestReporter.logStep("WebDriver Default Test Timeout value : "+getWebDriverDefaultTestTimeOut);*/
	}
}
