package core.interfaces.exception;

//package com.disney.composite.core.interfaces.exception;

import org.openqa.selenium.WebDriver;
import utils.AutomationException;

public class ElementNotEnabledException extends AutomationException{
		private static final long serialVersionUID = 3407361723082329697L;
	
	
		public ElementNotEnabledException(String message){
			super(message );
		}
		public ElementNotEnabledException( WebDriver driver){
			super(driver );
		}
		public ElementNotEnabledException(String message, WebDriver driver){
			super(message, driver );
		}

}
