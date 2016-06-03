package TestCases;


import org.testng.annotations.Test;

import Utils.Constants;
import Utils.Randomness;
import Utils.TestReporter;

public class UtilityClassesExamples {
	//Used the below statement to get the OS name
	public static final String OS_Name = System.getProperty("os.name","ERROR").toLowerCase();

	@Test(priority=1,enabled=true,groups={"TestNG Tests"})
	public void testUtilities(){
		TestReporter.log("<-------- Started the TestCase - UtilityClassesExamples. -------->");
		//To generate the random string
		String randomString  = Randomness.randomString(4);
		TestReporter.log(randomString);
		
		//To generate the random Number
		String randomNumber = Randomness.randomNumber(4);
		TestReporter.log(randomNumber);
		
		//To generate the random AlphaNumeric value
		String randomAlphaNumeric = Randomness.randomAlphaNummeric(4);
		TestReporter.log(randomAlphaNumeric);
		
		//To generate the Current Date and Time value
		String generateCurrentDateAndTime = Randomness.generateCurDateTime();
		TestReporter.log(generateCurrentDateAndTime);
		
		//To get the Operating System name
		String getOSname = Constants.OS_NAME;
		TestReporter.log(getOSname);
		
		TestReporter.log("<-------- Ended the TestCase execution - UtilityClassesExamples. -------->");
		
		/*int getWebDriverDefaultTestTimeOut = WebDriverSetUp.getDefaultTestTimeout();
		TestReporter.logStep("WebDriver Default Test Timeout value : "+getWebDriverDefaultTestTimeOut);*/
	}
}
