package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNewOrder_Page 
{
	 private static WebElement Element = null;
		
		public static WebElement htmlTable_CreateNewOrder(WebDriver driver)
		{
			Element = driver.findElement(By.xpath("html/body/div[1]/div[3]/div/form/div[3]/div/div[2]/div/div/div/table/tbody"));
			return Element;
		}
		
		//Click on the English Radio button
		public static WebElement RadioButton_English(WebDriver driver)
		{
			Element = driver.findElement(By.id("store_1"));
			return Element;
		}
		
		//Click on the AddProducts button
		public static WebElement AddProductsButton(WebDriver driver)
		{
			Element = driver.findElement(By.xpath(".//*[@id='order-items']//span[text()='Add Products']"));
			return Element;
		}
}
