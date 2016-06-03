package utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

public class ExtendedExpectedConditions {
	/**
    * Loop through windows for duration to see if it finds a title with exact queried text
    * @param title - Exact title of the Window to find
    * @return true once a window is found with exact title
    */
   public static ExpectedCondition<Boolean> findWindowWithTitleAndSwitchToIt(final String title){
	   return new ExpectedCondition<Boolean>() {
		      @Override
		      public Boolean apply(WebDriver driver) {
		    	  try{
			    	  for(String handle : driver.getWindowHandles()){
			   	       	driver.switchTo().window(handle);
			   	       	if(driver.getTitle().equals(title)) return true;
			    	  }
		    	  }catch (WebDriverException e) {
		              return null;
		          }
		   	   
		    	  return false;
		      }

		      @Override
		      public String toString() {
		    	  return String.format("text ('%s') to be present in window", title);
		      }
		  };
   }
   
   /**
    * Loop through windows for duration to see if it finds a title that contains queried text
    * @param title - Partial title of the Window to find
    * @return WindowExistsWithTitle - Expected Condition to find window with title part
    */
   public static ExpectedCondition<Boolean> findWindowContainsTitleAndSwitchToIt(final String title){
	   return new ExpectedCondition<Boolean>() {
		      @Override
		      public Boolean apply(WebDriver driver) {
		    	  try{
			    	  for(String handle : driver.getWindowHandles()){
			    		  driver.switchTo().window(handle);
			    		  if(driver.getTitle().contains(title)) return true;
			    	  }
	    	  }catch (WebDriverException e) {
	              return false;
	          }
		   	   return false;
		      }

		      @Override
		      public String toString() {
		    	  return String.format("text ('%s') to be present in window", title);
		      }
		  };
   }
  
  
  /**
   * Loop through windows for duration to see if it finds a title with the matching regex pattern
   * @param regex - Regex of title of the Window to find
   * @return WindowExistsWithTitle - Expected Condition to find window with title regex
   */
  public static ExpectedCondition<Boolean> findWindowMatchesTitleAndSwitchToIt(final String regex){
	  return new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	    	  try{
		    	  for(String handle : driver.getWindowHandles()){
		   	       driver.switchTo().window(handle);
		   	       if(driver.getTitle().matches(regex)) return true;
		   	   		}
	    	  }catch (WebDriverException e) {
	              return null;
	          }
	   	   return false;
	      }

	      @Override
	      public String toString() {
	    	  return String.format("regex ('%s') to be in window", regex);
	      }
	  };
  }
  
  /**
   * An expectation for checking if the given text is present in the specified element.
   *
   * @param element the WebElement
   * @param text to be present in the element
   * @return true once the element contains the given text
   */
  public static ExpectedCondition<Boolean> textToMatchInElement(
      final WebElement element, final String regex) {

    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        try {
          String elementText = element.getText();
          return elementText.matches(regex);
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return String.format("regex pattern ('%s') to be match in element %s", regex, element);
      }
    };
  }
  

  /**
   * An expectation for checking if the given text is present in the specified
   * elements value attribute.
   *
   * @param element the WebElement
   * @param attribute to search in
   * @param text to be present in the element's value attribute
   * @return true once the element's value attribute contains the given text
   */
  public static ExpectedCondition<Boolean> textToBePresentInElementAttribute(
      final WebElement element, final String attribute, final String text) {

    return new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver driver) {
        try {
          String elementText = element.getAttribute(attribute);
          if (elementText != null) {
            return elementText.contains(text);
          } else {
            return false;
          }
        } catch (StaleElementReferenceException e) {
          return null;
        }
      }

      @Override
      public String toString() {
        return String.format("text ('%s') to be the value attribute ('%s') in element %s", text, attribute, element);
      }
    };
  }
  /**
   * An expectation for checking if the given regex matches the given element attribute.
   *
   * @param webelement to retrieve attribute
   * @param attribute to retrieve
   * @param text to be present in the attribute found by the locator
   * @return true once the regex matches given text in element attribute
   */
  public static ExpectedCondition<Boolean> textToMatchInElementAttribute(
	      final WebElement element, final String attribute, final String regex) {

	    return new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	          String elementText = element.getAttribute(attribute);
	          if (elementText != null) {
	            return elementText.matches(regex);
	          } else {
	            return false;
	          }
	        } catch (StaleElementReferenceException e) {
	          return null;
	        }
	      }

	      @Override
	      public String toString() {
	        return String.format("attribute ('%s') to  match regex pattern ('%s') in element %s", attribute, regex, element);
	      }
	    };
	  }
  
  public static ExpectedCondition<Boolean> textToBePresentInElementCssValue(
	      final WebElement element, final String attribute, final String text) {

	    return new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	        try {
	          String elementText = element.getCssValue(attribute);
	          if (elementText != null) {
	            return elementText.contains(text);
	          } else {
	            return false;
	          }
	        } catch (StaleElementReferenceException e) {
	          return null;
	        }
	      }

	      @Override
	      public String toString() {
	        return String.format("text ('%s') to be the css value ('%s') in element %s", text, attribute, element);
	      }
	    };
	  }
  
  public static ExpectedCondition<Boolean> numberOfListboxOptionsToBeGreaterThan(
	      final Select element, final int numberOfOptions) {

	    return new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	          int currentNumberOfOptions = element.getOptions().size();
	          if (currentNumberOfOptions > numberOfOptions) {
	            return true;
	          } else {
	            return false;
	          }
	      }

	      @Override
	      public String toString() {
	        return String.format("number of options was not greater than ('%s') in element %s", numberOfOptions, element);
	      }
	    };
	  }
  

  public static ExpectedCondition<Boolean> numberOfListboxOptionsToBeLessThan(
	      final Select element, final int numberOfOptions) {

	    return new ExpectedCondition<Boolean>() {
	      @Override
	      public Boolean apply(WebDriver driver) {
	          int currentNumberOfOptions = element.getOptions().size();
	          if (currentNumberOfOptions < numberOfOptions) {
	            return true;
	          } else {
	            return false;
	          }
	      }

	      @Override
	      public String toString() {
	        return String.format("number of options was not less than ('%s') in element %s", numberOfOptions, element);
	      }
	    };
	  }
}


