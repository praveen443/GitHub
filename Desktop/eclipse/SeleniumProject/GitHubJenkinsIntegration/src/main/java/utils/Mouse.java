package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Mouse {
	private static Robot mouseObject = null;
	private static WebDriver driver = null;
	private static JavascriptExecutor executor = null;
	
	public Mouse(WebDriver driver)  {
		  try {
			this.mouseObject = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  this.driver = driver;
		  this.executor = (JavascriptExecutor) driver;
		}
	
    public static void click(int x, int y) {
    	String javaScriptWidth = "var myWidth = 0; " +
    					" if (typeof (window.innerWidth) == 'number') { " +
    					" myWidth = window.innerWidth; " +    					 
    					" } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {" +
    					" myWidth = document.documentElement.clientWidth; " +
    					" } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {" +
    					" myWidth = document.body.clientWidth; " +
    					" return myWidth}";
    	
    	String javaScriptHeight= "var myHeight= 0; " +
    			" if (typeof (window.innerHeight) == 'number') { " +
				" myHeight = window.innerHeight;" +
				" } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {" +				
				" myHeight = document.documentElement.clientHeight;" +
				" } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {" +
				" myHeight = document.body.clientHeight; " +
    			" return myHeight}";

    	int xPosition = 0;
    	int yPosition = 0;
    	 //Get Browser dimensions
    	  int browserWidth = driver.manage().window().getSize().width;
    	  int browserHeight = driver.manage().window().getSize().height;
    	 
    	  //Get dimensions of the window displaying the web page
    	  int pageWidth = Integer.parseInt(executor.executeScript(javaScriptWidth).toString());
    	  int pageHeight = Integer.parseInt(executor.executeScript(javaScriptHeight).toString());

    	  //Calculate the space the browser is using for toolbars
    	  int browserToolbarOffsetX = browserWidth - pageWidth;
    	  int browserToolbarOffsetY = browserHeight - pageHeight;
    	 
    	  //Calculate the correct X/Y coordinates based upon the browser furniture offset and the position of the browser on the desktop
    	  xPosition = driver.manage().window().getPosition().x + browserToolbarOffsetX + x;
    	  yPosition = driver.manage().window().getPosition().y + browserToolbarOffsetY + y;

    	  
    	  //Move the mouse to the calculated X/Y coordinates
    	  mouseObject.mouseMove(xPosition, yPosition);
    	  mouseObject.waitForIdle();
    	  mouseObject.mousePress(InputEvent.BUTTON1_MASK);
    	  mouseObject.waitForIdle();
    	  mouseObject.mouseRelease(InputEvent.BUTTON1_MASK);
    	  mouseObject.waitForIdle();
    	  
    }
    
    public static void hover(int x, int y) {
    	String javaScriptWidth = "var myWidth = 0; " +
    					" if (typeof (window.innerWidth) == 'number') { " +
    					" myWidth = window.innerWidth; " +    					 
    					" } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {" +
    					" myWidth = document.documentElement.clientWidth; " +
    					" } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {" +
    					" myWidth = document.body.clientWidth; " +
    					" return myWidth}";
    	
    	String javaScriptHeight= "var myHeight= 0; " +
    			" if (typeof (window.innerHeight) == 'number') { " +
				" myHeight = window.innerHeight;" +
				" } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {" +				
				" myHeight = document.documentElement.clientHeight;" +
				" } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {" +
				" myHeight = document.body.clientHeight; " +
    			" return myHeight}";

    	int xPosition = 0;
    	int yPosition = 0;
    	 //Get Browser dimensions
    	  int browserWidth = driver.manage().window().getSize().width;
    	  int browserHeight = driver.manage().window().getSize().height;
    	 
    	  //Get dimensions of the window displaying the web page
    	  int pageWidth = Integer.parseInt(executor.executeScript(javaScriptWidth).toString());
    	  int pageHeight = Integer.parseInt(executor.executeScript(javaScriptHeight).toString());

    	  //Calculate the space the browser is using for toolbars
    	  int browserToolbarOffsetX = browserWidth - pageWidth;
    	  int browserToolbarOffsetY = browserHeight - pageHeight;
    	 
    	  //Calculate the correct X/Y coordinates based upon the browser furniture offset and the position of the browser on the desktop
    	  xPosition = driver.manage().window().getPosition().x + browserToolbarOffsetX + x;
    	  yPosition = driver.manage().window().getPosition().y + browserToolbarOffsetY + y;

    	  
    	  //Move the mouse to the calculated X/Y coordinates
    	  mouseObject.mouseMove(xPosition, yPosition);
    	  mouseObject.waitForIdle();	  
    }
    
    public void dragAndDrop(WebElement startLocation, WebElement targetLocation){
    	String javaScriptWidth = "var myWidth = 0; " +
				" if (typeof (window.innerWidth) == 'number') { " +
				" myWidth = window.innerWidth; " +    					 
				" } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {" +
				" myWidth = document.documentElement.clientWidth; " +
				" } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {" +
				" myWidth = document.body.clientWidth; " +
				" return myWidth}";

		String javaScriptHeight= "var myHeight= 0; " +
				" if (typeof (window.innerHeight) == 'number') { " +
				" myHeight = window.innerHeight;" +
				" } else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)) {" +				
				" myHeight = document.documentElement.clientHeight;" +
				" } else if (document.body && (document.body.clientWidth || document.body.clientHeight)) {" +
				" myHeight = document.body.clientHeight; " +
				" return myHeight}";
    	int startX = new Integer(startLocation.getLocation().x);
		int startY = new Integer(startLocation.getLocation().y);

		// destination dimensions
		 
  	  //Get dimensions of the window displaying the web page
		int pageWidth = Integer.parseInt(executor.executeScript(javaScriptWidth).toString());
		int pageHeight = Integer.parseInt(executor.executeScript(javaScriptHeight).toString());


		// destination coordinates
		int destinationX = new Integer(targetLocation.getLocation().x);
		int destinationY = new Integer(targetLocation.getLocation().y+20);

		// destination dimensions
		int destinationWidth = new Integer(targetLocation.getSize().width);
		int destinationHeight = new Integer(targetLocation.getSize().height);

		// work out destination coordinates
		int endX = Math.round(destinationX + (destinationWidth / 2));
		int endY = Math.round(destinationY + (destinationHeight / 2));
		int sX = Math.round(startX + (pageWidth / 2));
		int sY = Math.round(startY + (pageHeight / 2));		

		mouseObject.mouseMove(sX, sY);
		Sleeper.sleep(1000);
		mouseObject.mousePress(InputEvent.BUTTON1_MASK);
		mouseObject.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		Sleeper.sleep(1000);
		//mouseObject.mouseMove(sX+200, sY+200);
		//Sleeper.sleep(2000);
		mouseObject.mouseMove(endX, endY);
		Sleeper.sleep(1000);
		mouseObject.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}

