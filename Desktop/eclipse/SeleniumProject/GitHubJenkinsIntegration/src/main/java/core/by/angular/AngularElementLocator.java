package core.by.angular;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import core.WebDriverSetup;
import core.angular.ByAngular;

public class AngularElementLocator implements ElementLocator {
	  private final WebDriver driver;
	  private final FindByNG ngLocator;
	  private static ByAngular ng;
		
	  
	  public AngularElementLocator(final WebDriver driver, final Field field) {
	    this.driver =  driver;
	    this.ngLocator = field.getAnnotation(FindByNG.class);
	    ng = new ByAngular(driver);
	  }
	  
	  //@Override
	  public WebElement findElement() {
		  RemoteWebElement element = null;
		  if (!ngLocator.ngModel().toString().isEmpty()){
			  element = (RemoteWebElement) driver.findElement(ng.model(ngLocator.ngModel()));
		  }else if(!ngLocator.ngRepeater().toString().isEmpty()){
			  element = (RemoteWebElement) driver.findElement(ng.repeater(ngLocator.ngRepeater()));
		  }else if(!ngLocator.ngButtonText().toString().isEmpty()){
			  element = (RemoteWebElement) driver.findElement(ng.buttonText(ngLocator.ngButtonText()));
		  }
		 // return element.findElement(ng.model(ngLocator.ngModel()));
		  return element;
	  }
	  
	  @SuppressWarnings("unchecked")// @Override
	  public List<WebElement> findElements() {
		  
		  List<WebElement> elements = null;
		  if (!ngLocator.ngModel().toString().isEmpty()){
			  elements = driver.findElements(ng.model(ngLocator.ngModel()));
		  }else if(!ngLocator.ngRepeater().toString().isEmpty()){
			  elements = driver.findElements(ng.repeater(ngLocator.ngRepeater()));
		  }else if(!ngLocator.ngButtonText().toString().isEmpty()){
			  elements = driver.findElements(ng.buttonText(ngLocator.ngButtonText()));

		  }
		  
		  return elements;
	  }
}
