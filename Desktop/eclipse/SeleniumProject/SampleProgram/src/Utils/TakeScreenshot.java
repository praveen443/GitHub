package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import testResources.ConstantInput.constants;

public class TakeScreenshot {
	
	/*public static void captureScreenshot(WebDriver driver,String screenshotName) 
	{
		try 
		{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			FileUtils.copyFile(scrFile, new File("C:\\Users\\praveen\\Desktop\\eclipse\\SeleniumProject\\SampleProgram\\Screenshots\\"+screenshotName+".png"));
			System.out.println("Placed screenshot with method name - " +"'" +screenshotName +"'"+ " in the following path - "+System.getProperty("user.dir")+"\\Screenshots\\");
		} 
		catch (Exception e){
		    System.out.println("Exception while taking screenshot "+e.getMessage());
		} 
	}
	*/
	
	public static void takeScreenShot(WebDriver driver,String methodName) {
    	//The below method will save the screenshot in the project root directory 
    	//under Screenshots folder with test method name.
        try {
        	  //get the driver
        	  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(scrFile, new File(constants.screenshotFilePath+methodName+"_"+SimpleDateAndCalendar.getTodaysDate("dd-MM-yyyy")+ "_" +SimpleDateAndCalendar.getCurrentTime("HH-mm-ss")+".png"));
			  System.out.println("*** Placed screen shot in "+ constants.screenshotFilePath +" ***");
		} catch (IOException e) {
			  System.out.println("Exception while taking screenshot "+e.getMessage());
		}
        
	}
	
}
