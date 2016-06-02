package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HighlightElement {
	//public static WebDriver driver;
	
	public static void highlight(WebDriver driver,WebElement element){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].style.border='4px solid red'", element);
	}
	
	public static void highlightElement(WebDriver driver,WebElement element) {
		  String originalStyle = element.getAttribute("style");
		  JavascriptExecutor js = (JavascriptExecutor) driver;
		  js.executeScript("arguments[0].style.border='4px solid red'", element);
		  try {
		    Thread.sleep(3000);
		  } catch (InterruptedException e) {}
		  js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
		}

}
