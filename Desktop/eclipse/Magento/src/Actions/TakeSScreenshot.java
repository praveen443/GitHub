package Actions;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TakeSScreenshot extends Actions.DriverObject
{	
   public static void onTestFailure(ITestResult result) throws Exception 
   {   	 
      String workingDirectory = System.getProperty("user.dir");
      String fileName = workingDirectory + File.separator + "screenshots" + File.separator +  result.getMethod().getMethodName() + "().png";//filename
     
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      
      try {
       
    	  FileUtils.copyFile(scrFile, new File(fileName ));
    	  System.out.println("*** Placed screen shot in "+fileName+" ***");
	        
      }catch (Exception e){
         System.out.println("---> Exception while taking the screenshot : "+e.getMessage());
         throw new Exception();
      }  		
      
      Reporter.log("<a href=\"" + fileName  + "\"> #### Test Failed. Click-here for Screenshot : #### </a>");
      Reporter.setCurrentTestResult(null);        
  }
	 
}
