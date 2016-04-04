package Actions;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class AlertHandling extends Application {
	
  // Check if alert exists
  public static void checkAlert(WebDriver driver) {
 	    try {
 	    	
 	        WebDriverWait wait = new WebDriverWait(driver, 3);
 	        wait.until(ExpectedConditions.alertIsPresent());
 	        System.out.println("<-- Alert Message is displayed. -->");
 	        Reporter.log("<-- Alert Message is displayed. -->");
 	        
 	        Alert alert = driver.switchTo().alert();
 	        
 	        //get the alert text message
 	        String getAlertMessage = alert.getText();
 	        System.out.println("Alert text message is : "+getAlertMessage);
 	        Reporter.log("Alert text message is : "+getAlertMessage); 
 	       
 	        //Click on 'OK' button (or) Close the alert message by Accept it.
 	        alert.accept();
 	        System.out.println("Click on 'OK'.");
 	        Reporter.log("Click on 'OK'.");
 	        
 	    } catch (Exception e) { 	    	
 	        System.out.println(e.getStackTrace());
 	    }
 	}

}
