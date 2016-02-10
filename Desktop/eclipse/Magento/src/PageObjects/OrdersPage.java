package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class OrdersPage 
{

    private static WebElement Element = null;
	
	public static WebElement Button_CreateNewOrder(WebDriver driver)
	{
		//Click on Create New Order button in the Orders Page.	
		Element = driver.findElement(By.xpath("//div/div[2]/table/tbody/tr/td[2]/button"));
		return Element;
	}
	
	public static WebElement EditBox_OrderNumber(WebDriver driver)
	{
		//Enter the OrderNumber in the Order# Edit-box
		Element = driver.findElement(By.xpath("//input[@id='sales_order_grid_filter_real_order_id']"));
		return Element;
	}
		
	public static WebElement Button_Search(WebDriver driver)
	{
		//Click on the Search button
		Element = driver.findElement(By.xpath("//button[@title='Search']"));
		Reporter.log("Click on the Search button");
		return Element;
	}
	
	public static WebElement VerifyText_OrdersPage(WebDriver driver)
	{
		//To identify the Orders Text in the orders Page (Ref - Magento_ReusableFunctions: CODE-8)
		Element = driver.findElement(By.xpath("//h3[@class='icon-head head-sales-order']"));
		return Element;
	}

	public static WebElement Button_CreditMemo(WebDriver driver)
	{
		//To identify the Credit Memo button
		Element = driver.findElement(By.xpath("//button[@title='Credit Memo']"));
		Reporter.log("Click on Credit Memo button.");
		
		return Element;
	}
	
}
