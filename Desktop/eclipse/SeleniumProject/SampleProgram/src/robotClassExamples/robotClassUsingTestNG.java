package robotClassExamples;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class robotClassUsingTestNG {
  @Test
  public void robotClassExamples() throws AWTException {
		  //First of all create the object of the Robot Class as following:
	  	  //1 .keyPress()
		  Robot robot=new Robot();
		  //This will press Escape key on keyboard.
		  robot.keyPress(KeyEvent.VK_ESCAPE); 

		  //2 .keyRelease()
		  //This will release the CAPS_LOCK key.
		  robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
		  
		  //3 .mousePress()
		  robot.mousePress(InputEvent.BUTTON1_MASK); 

		  //This will press Left mouse button.

		  //4 .mouseRelease()
		  robot.mouseRelease(InputEvent.BUTTON1_MASK); 

		  //This will release Left mouse button.

		  //5 .mouseMove()

		  // Object coordinates;
		  // robot.mouseMove(((Object) coordinates).getX(), ((Object) coordinates).getY()); 

		  //This will move the mouse pointer to X and Y co-ordinates.
		        WebDriver driver=new FirefoxDriver();        
		        Robot robot1=null;        
		        driver.get("http://www.makemytrip.com");
		        driver.manage().window().maximize();
		        driver.findElement(By.xpath(".//*[@id='ssologinlink']")).click();
		        driver.findElement(By.xpath(".//*[@id='username']")).sendKeys("username@gmail.com");
		        driver.findElement(By.xpath(".//*[@id='password_text']")).sendKeys("password");        
		        try {
		            robot1=new Robot();
		        } catch (AWTException e) {
		            e.printStackTrace();
		        }
		        //Keyboard Activity Using Robot Class
		        robot1.keyPress(KeyEvent.VK_ENTER);
		    }
  
  @BeforeMethod
  public void beforeMethod() {
  
  }

  @AfterMethod
  public void afterMethod() {
  
  }

}
