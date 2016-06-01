package Utility;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.WebDriver;

public class Mouse {
	public Mouse(WebDriver driver)  {
		  try {
			new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
