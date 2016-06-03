package core.interfaces.exception;

import org.openqa.selenium.WebDriver;

import utils.AutomationException;

public class OptionNotInListboxException extends AutomationException{
		private static final long serialVersionUID = 3407361723082329697L;
	
	
		public OptionNotInListboxException(String message){
			super(message );
		}
		public OptionNotInListboxException( WebDriver driver){
			super(driver );
		}
		public OptionNotInListboxException(String message, WebDriver driver){
			super(message, driver );
		}

}

