package core.interfaces.impl.internal;

import core.WebDriverSetup;
import core.interfaces.Element;
import selenium.common.Constants;
import selenium.common.SeleniumWrapper;
import utils.TestReporter;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static core.interfaces.impl.internal.ImplementedByProcessor.getWrapperClass;

/**
 * Replaces DefaultLocatingElementHandler. Simply opens it up to descendants of the WebElement interface, and other
 * mix-ins of WebElement and Locatable, etc. Saves the wrapping type for calling the constructor of the wrapped classes.
 */
public class ElementHandler  implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<?> wrappingType;
    private final WebDriver driver;

    /**
     * Generates a handler to retrieve the WebElement from a locator for a given WebElement interface descendant.
     *
     * @param interfaceType Interface wrapping this class. It contains a reference the the implementation.
     * @param locator       Element locator that finds the element on a page.
     * @param <T>           type of the interface
     */
    public <T> ElementHandler(Class<T> interfaceType, ElementLocator locator) {
        this.locator = locator;
        if (!Element.class.isAssignableFrom(interfaceType)) {
            throw new RuntimeException("interface not assignable to Element.");
        }

        this.wrappingType = getWrapperClass(interfaceType);
        this.driver = null;
    }

    public <T> ElementHandler(Class<T> interfaceType, ElementLocator locator, WebDriver driver) {
        this.locator = locator;
        if (!Element.class.isAssignableFrom(interfaceType)) {
            throw new RuntimeException("interface not assignable to Element.");
        }

        this.wrappingType = getWrapperClass(interfaceType);
        this.driver = driver;
    }
    
    @SuppressWarnings("rawtypes")
	@Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
    	WebElement element = null;
    	
    	try{      
    		if(driver != null) driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        	element = locator.findElement();
        	if(driver != null) driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
        }catch(WebDriverException | ClassCastException e){
        	try{
        		Field privateBy = null;
        	
	        	privateBy = locator.getClass().getDeclaredField("by");
	        	privateBy.setAccessible(true);
	        	By by = (By) privateBy.get(locator);
	        	TestReporter.log("Unable to interact with element [ " + by.toString() + " ] as it was not present in the DOM");
	        	if(driver != null) driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
	        	if(method.getName().contains("get")) return "";
        	}catch (Exception ee){}
        	return false;
        }
    	
        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }
        
        if ("getWrappedDriver".equals(method.getName())) {
            return driver;
        }
    	if(driver != null) driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
          Constructor cons = wrappingType.getConstructor(WebElement.class);
        Object thing = cons.newInstance(element);
     	if(driver != null) driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT, TimeUnit.SECONDS);
         try {
            return method.invoke(wrappingType.cast(thing), objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        } catch (WebDriverException e){
        	if(method.getName().contains("get")) return "";
        	return false;
        }
    }
}

