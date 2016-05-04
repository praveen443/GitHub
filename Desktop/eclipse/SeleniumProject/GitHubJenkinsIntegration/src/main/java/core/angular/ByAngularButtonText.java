package core.angular;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * Original Code from https://github.com/paul-hammant/ngWebDriver
 */

public class ByAngularButtonText  extends ByAngular.BaseBy {
    private String text;

    public ByAngularButtonText(JavascriptExecutor jse, String text) {
    	super(jse);
        this.text = text;
    }

    private String makeJsBy(String oneOrAll) {    	
        return                      
				"var using = document;\n" +
				"var text = text;\n" +
				
				"var rows = [];\n" +
				"var elements = using.querySelectorAll('button, input[type=\"button\"], input[type=\"submit\"]');\n" +
				"var matches = [];\n" +
				"for (var i = 0; i < elements.length; ++i) {\n" +
				"  var element = elements[i];\n" +
				"  var elementText;\n" +
				"  if (element.tagName.toLowerCase() == 'button') {\n" +
				"    elementText = element.innerText || element.textContent;\n" +
				"  } else {\n" +
				"    elementText = element.value;\n" +
				"  }\n" +				
				" 	if(typeof String.prototype.trim !== 'function') {\n" +
				"		  String.prototype.trim = function() {\n" +
				"	    	return this.replace(/^\\s+|\\s+$/g, '');\n" + 
				"		  }\n" +
				"	}\n" +				
				" if(elementText !== null && elementText !== undefined){ \n" + 
				" 	 if (elementText.trim() == \"" + text + "\") {\n" +
				"   	 rows.push(element);\n" +
				"  	}\n" +
				"  }\n" +
				"}\n" +
				"return rows[0];";
    }

    @Override
    public WebElement findElement(SearchContext context) {
        if (context instanceof WebDriver) {
            context = null;
        }
        
        WebElement  o = (WebElement)jse.executeScript(makeJsBy("[0]"), context);
        //WebElement  o = (WebElement)jse.executeAsyncScript(makeJsBy("[0]"), context);
        
        errorIfNull(o);
        //WebElement e =(WebElement) o;
        Field privateStringField = null;
        try {
        	privateStringField = o.getClass().getDeclaredField("foundBy");
        	privateStringField.setAccessible(true);
            privateStringField.set(o, o.toString().replace("unknown locator", "button text: " + text));
		} catch (NoSuchFieldException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
      
        
        return o;
    }

    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        if (searchContext instanceof WebDriver) {
            searchContext = null;
        }
        return (List<WebElement>) jse.executeScript(makeJsBy(""), searchContext);
    }

}

