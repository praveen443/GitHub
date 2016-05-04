package core.interfaces.exception;

import org.openqa.selenium.WebDriver;

import utils.AutomationException;

public class ElementNotVisibleException extends AutomationException{
		private static final long serialVersionUID = 3407361723082329697L;
	
	
		public ElementNotVisibleException(String message){
			super(message );
		}
		public ElementNotVisibleException( WebDriver driver){
			super(driver );
		}
		public ElementNotVisibleException(String message, WebDriver driver){
			super(message, driver );
		}

}

