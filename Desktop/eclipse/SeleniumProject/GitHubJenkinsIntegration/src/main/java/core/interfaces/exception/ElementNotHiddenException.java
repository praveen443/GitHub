package core.interfaces.exception;

//package com.disney.composite.core.interfaces.exception;

import org.openqa.selenium.WebDriver;

import utils.AutomationException;

public class ElementNotHiddenException extends AutomationException{
		private static final long serialVersionUID = 3407361723082329697L;
	
	
		public ElementNotHiddenException(String message){
			super(message );
		}
		public ElementNotHiddenException( WebDriver driver){
			super(driver );
		}
		public ElementNotHiddenException(String message, WebDriver driver){
			super(message, driver );
		}

}

