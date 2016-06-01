package Utils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import Utils.TestReporter;
import Utils.date.SimpleDate;


//public class TestReporter {

	/*public static void logStep(String step){
		Reporter.log("<br/><b><font size = 4>Step: " + step + "</font></b><br/>");
		System.out.println("Cus logStep: "+step);
	}
	
	public static void logScenario(){
		Reporter.log("<br/><b><font size = 4>Data Scenario: " + Datatable.getCurrentScenario() + "</font></b><br/>");
	}
	
	public static void logScenario(String scenario){
		Reporter.log("<br/><br><font size = 4>Data Scenario: "+ scenario +"</font></br><br/>");
		System.out.println("Cus logScenario: " + scenario);
	}
	
	public static void log(String message){
		Reporter.log(new Timestamp(new java.util.Date().getTime())+" :: "+ message + "<br />");
		System.out.println(new Timestamp(new java.util.Date().getTime())+" :: "+ message);
	}

	
	public static void logStep(Integer step) {
		Reporter.log("<br/><b><font size = 4>Step: " + step + "</font></b><br/>");
		System.out.println("Cus logStep: "+step);
		
	}*/
//}
public class TestReporter {
	private static boolean printToConsole = false;
	//SimpleDateFormat SimpleDate = new Simple

	private static String getTimestamp() {
		return SimpleDate.getTimestamp().toString() + " :: ";
	}

	private static String trimHtml(String log) {
		return log.replaceAll("<[^>]*>", "");
	}

	public static void setPrintToConsole(boolean printToConsole) {
		TestReporter.printToConsole = printToConsole;
	}

	public static boolean getPrintToConsole() {
		return printToConsole;
	}

	public static void logStep(String step) {
		Reporter.log("<br/><b><font size = 4>-------------------------------------------------------------------------------</font></b><br/>");
		Reporter.log("<br/><b><font size = 4>Step: " + step + "");
		Reporter.log("<br/><b><font size = 4>-------------------------------------------------------------------------------</font></b><br/>");
		if(getPrintToConsole()) System.out.println(trimHtml(step));
	}
	
	public static void logVideo(String sessionId) {
		Reporter.log("<br/><b><font size = 4>-------------------------------------------------------------------------------</font></b><br/>");
		Reporter.log("<br/><b><font size = 4>Video for session: " +sessionId + "</font></b><br/>");
		Reporter.log("<a href='http://dnhprpptstw701.disid.disney.com:3000/download_video/" + sessionId +".mp4' font size = 4 target='_blank'>Video Link</a>");
		Reporter.log("<br/><b><font size = 4>-------------------------------------------------------------------------------</font></b><br/>");		
	}

/*	public static void logScenario() {
		Reporter.log("<br/><b><font size = 4>Data Scenario: " + Datatable.getCurrentScenario() + "</font></b><br/>");
	}*/
	public static void logStep(Integer step) {
		Reporter.log("<br/><b><font size = 4>Step: " + step + "</font></b><br/>");
		System.out.println("Cus logStep: "+step);
		
	}
	
	public static void logScenario(String scenario) {
		Reporter.log("<br/><b><font size = 4>Data Scenario: " + scenario + "</font></b><br/>");
		if(getPrintToConsole()) System.out.println(trimHtml(scenario));
	}

	public static void log(String message) {
		Reporter.log(new Timestamp(new java.util.Date().getTime()) + " :: " + message + "<br />");
		if(getPrintToConsole()) System.out.println(getTimestamp() + trimHtml(message.trim()));
	}

	public static void logNoHtmlTrim(String message) {
		Reporter.log(getTimestamp() + " :: " + message + "<br />");
		if(getPrintToConsole()) System.out.println(getTimestamp() + message.trim());
	}

	public static void logFailure(String message) {
		Reporter.log(getTimestamp() + " <font size = 2 color=\"red\"><b><u> FAILURE: " + message + "</font></u></b><br />");
		if(getPrintToConsole()) System.out.println(getTimestamp() + trimHtml(message.trim()));
	}

	 public static void logScreenshot(WebDriver driver, String fileLocation, String slash) {
			File file = new File("");

			try {
				file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(file, new File(fileLocation));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String jenkinsPath = System.getenv("JOB_URL");
			String jenkinsName = System.getenv("JOB_NAME");
			if(jenkinsPath != null && !jenkinsPath.isEmpty()){
				jenkinsName = jenkinsName.replace("/", "\\");
				String webFileLocation = fileLocation.replace("C:\\Jenkins\\workspace\\", jenkinsPath);
				webFileLocation = webFileLocation.replace(jenkinsName, "ws");
				TestReporter.log("Web File Location : " +webFileLocation);
				Reporter.log("<a  target='_blank' href='" + webFileLocation + "'><img src='"+ webFileLocation + "' height='200' width='300'/></a>");
			}else{
				TestReporter.log("File Location : " +fileLocation);
				Reporter.log("<a  target='_blank' href='" + fileLocation + "'> <img src='file:///" + fileLocation + "' height='200' width='300'/> </a>");
			}
	 }
	public static void interfaceLog(String message) {
		Reporter.log(getTimestamp() + message + "<br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + trimHtml(message.trim()));
	}

	public static void interfaceLog(String message, boolean failed) {
		Reporter.log(getTimestamp() + "<font size = 2 color=\"red\">" + message + "</font><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + trimHtml(message.trim()));
		if(failed)
			Assert.fail(message);
	}

	public static void assertTrue(boolean condition, String description) {
		try {
			Assert.assertTrue(condition, description);
		} catch (AssertionError failure) {
			logFailure("Assert True - " + description);
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert True - " + trimHtml(description));
			Assert.fail(description);
		}
		Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert True - " + description
				+ "</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + "Assert True - " + trimHtml(description));
	}

	public static void assertFalse(boolean condition, String description) {
		try {
			Assert.assertFalse(condition, description);
		} catch (AssertionError failure) {
			logFailure("Assert False - " + description);
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert False - " + trimHtml(description));
			Assert.fail(description);
		}
		Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert False - " + description
				+ "</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + "Assert False - " + trimHtml(description));
	}

	public static void assertEquals(Object value1, Object value2, String description) {
		try {
			Assert.assertEquals(value1, value2, description);
		} catch (AssertionError failure) {
			logFailure("Assert Equals - " + description);
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Equals - " + trimHtml(description));
			Assert.fail(description);
		}
		Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Equals - " + description
				+ "</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + "Assert Equals - " + trimHtml(description));
	}

	public static void assertNotEquals(Object value1, Object value2, String description) {
		try {
			Assert.assertNotEquals(value1, value2, description);
		} catch (AssertionError failure) {
			logFailure("Assert Not Equals - " + description);
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Not Equals - " + trimHtml(description));
			Assert.fail(description);
		}
		Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Not Equals - " + description
				+ "</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + "Assert Not Equals - " + trimHtml(description));
	}

	public static void assertGreaterThanZero(int value) {
		try {
			Assert.assertTrue(value > 0);
		} catch (AssertionError failure) {
			logFailure("Assert Greater Than Zero - " + value);
			if (getPrintToConsole())
				System.out.println(
						getTimestamp() + "Assert Greater Than Zero - Assert " + value + " is greater than zero");
			Assert.fail("Assert " + value + " is greater than zero");
		}
		Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Greater Than Zero - Assert " + value
				+ " is greater than zero</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + "Assert Greater Than Zero - Assert " + value + " is greater than zero");
	}

	public static void assertGreaterThanZero(float value) {
		assertGreaterThanZero((int) value);
	}

	public static void assertGreaterThanZero(double value) {
		assertGreaterThanZero((int) value);
	}

	public static void assertNull(Object condition, String description) {
		try {
			Assert.assertNull(condition, description);
		} catch (AssertionError failure) {
			logFailure("Assert Null - " + description);
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Null - " + trimHtml(description));
			Assert.fail(description);
		}
		Reporter.log(getTimestamp() + " <font size = 2 color=\"green\"><b><u>Assert Null - " + description
				+ "</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + "Assert Null - " + trimHtml(description));
	}

	public static void assertNotNull(Object condition, String description) {
		try {
			Assert.assertNotNull(condition, description);
		} catch (AssertionError failure) {
			logFailure("Assert Not Null - " + description);
			if (getPrintToConsole())
				System.out.println(getTimestamp() + "Assert Not Null - " + trimHtml(description));
			Assert.fail(description);
		}
		Reporter.log(getTimestamp() + "<font size = 2 color=\"green\"><b><u>Assert Not Null - " + description
				+ "</font></u></b><br />");
		if (getPrintToConsole())
			System.out.println(getTimestamp() + "Assert Not Null - " + trimHtml(description));
	}
}

