package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Login_Page 
{
 
   private static WebElement Element = null;
  
   public static WebElement TextBox_UserName(WebDriver driver)
   {
	   //Enter the UserName
	   Element = driver.findElement(By.id("username"));
	   return Element;	   
   }
   
   public static WebElement TextBox_Password(WebDriver driver)
   {
	   //Enter the Password
	   Element = driver.findElement(By.id("login"));
	   return Element;	   
   }
   
   public static WebElement Login_Button(WebDriver driver)
   {
	   //Click on Login button
	   Element = driver.findElement(By.className("form-button"));
	   return Element;	   
   }
	
}
