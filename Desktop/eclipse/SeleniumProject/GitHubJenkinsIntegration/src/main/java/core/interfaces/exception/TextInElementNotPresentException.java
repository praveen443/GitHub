package core.interfaces.exception;

import org.openqa.selenium.WebDriver;

import utils.AutomationException;

public class TextInElementNotPresentException extends AutomationException{
		private static final long serialVersionUID = 3407361723082329697L;
	
	
		public TextInElementNotPresentException(String message){
			super(message );
		}
		public TextInElementNotPresentException( WebDriver driver){
			super(driver );
		}
		public TextInElementNotPresentException(String message, WebDriver driver){
			super(message, driver );
		}

}

