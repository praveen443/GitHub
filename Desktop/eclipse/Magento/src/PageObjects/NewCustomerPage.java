package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewCustomerPage {
	
	private static WebElement Element = null;
	
	//To locate the First Name field in the New Customer Page 
	public static WebElement  InputBox_AccountFirstName(WebDriver driver)
	{
		Element = driver.findElement(By.id("_accountfirstname"));
		return Element;
	}
	
	//To locate the Last Name field in the New Customer Page 
	public static WebElement InputBox_AccountLastName(WebDriver driver)
	{
		Element = driver.findElement(By.id("_accountlastname"));
		return Element;
	}
	
	//To locate the Email id field in the New Customer Page 
	public static WebElement InputBox_AccountEmail(WebDriver driver)
	{
		Element = driver.findElement(By.id("_accountemail"));
		return Element;
	}
	
	//To locate the Associate dropdown field in the New Customer Page 
	public static WebElement dropdown_AssociateValue(WebDriver driver)
	{
		Element = driver.findElement(By.id("_accountwebsite_id"));
		return Element;
	}
	
	//To locate the dropdown field - 'Group' in the New Customer Page 
	public static WebElement dropdown_GroupValue(WebDriver driver)
	{
		Element = driver.findElement(By.id("_accountgroup_id"));
		return Element;
	}

}
