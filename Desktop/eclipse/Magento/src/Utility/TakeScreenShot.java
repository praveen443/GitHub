package Utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreenShot
{
	
	public static void onTestFailure(WebDriver driver,String sTestMethodName) throws Exception 
	{		     
	      String CurrentDir = System.getProperty("user.dir");
	      	      
		  String fileName = CurrentDir+"\\Screenshots\\"+sTestMethodName+"().png";//filename
     
		  try {
			     File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			     FileUtils.copyFile(scrFile, new File(fileName ));
		         System.out.println("*** Placed screen shot in "+fileName+" ***");
		        
		      }catch (Exception e){
                 System.out.println("---> Exception while taking the screenshot : "+e.getMessage());
                 throw new Exception();
		      }  		
    }
	 
}
  








