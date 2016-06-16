package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import testResources.ConstantInput.constants;

public class TestListener implements ITestListener{

	private WebDriver driver;
	private String methodName;
	//private String filePath=System.getProperty("user.dir")+"/Screenshots/";

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("***** Error "+result.getName()+" test has failed *****");
    	String methodName=result.getName().toString().trim();
		takeScreenShot(methodName);
	}
	
	public void takeScreenShot(String methodName) {
	    	//get the driver
	    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        
	    	//The below method will save the screenshot in the project root directory 
	    	//under Screenshots folder with test method name.
	        try {
				FileUtils.copyFile(scrFile, new File(constants.screenshotFilePath+methodName+".png"));
				System.out.println("***Placed screen shot in "+ constants.screenshotFilePath +" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
