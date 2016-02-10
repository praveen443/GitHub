package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ManageCustomersPage {

    private static WebElement Element = null;
	
    //Used to locate the 'Save Customer' button.
	public static WebElement  Button_SaveCustomer(WebDriver driver){
		
		Element = driver.findElement(By.xpath("//button[@title='Save Customer']"));
		return Element;
	}
	
	//Used to locate the Name field.
	public static WebElement  ValidCustomerName(WebDriver driver){
		
		Element = driver.findElement(By.id("customerGrid_filter_name"));
		return Element;
	}
		
	//Used to locate the 'Search' button.
	public static WebElement  Button_Search(WebDriver driver){
		
		Element = driver.findElement(By.xpath("//button[@title='Search']"));
		return Element;
	}
	
	//Used to get the 'valid message'.
	public static WebElement  ValidTextMessage(WebDriver driver){
		
		Element = driver.findElement(By.xpath("//li[@class='success-msg']"));
		return Element;
	}
	
}
