package core.interfaces.exception;

//package com.disney.composite.core.interfaces.exception;

import org.openqa.selenium.WebDriver;

import utils.AutomationException;

public class ElementNotDisabledException extends AutomationException{
		private static final long serialVersionUID = 3407361723082329697L;
	
	
		public ElementNotDisabledException(String message){
			super(message );
		}
		public ElementNotDisabledException( WebDriver driver){
			super(driver );
		}
		public ElementNotDisabledException(String message, WebDriver driver){
			super(message, driver );
		}

}
