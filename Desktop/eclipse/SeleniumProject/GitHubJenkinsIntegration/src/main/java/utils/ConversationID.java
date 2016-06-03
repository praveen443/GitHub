package utils;

//package com.disney.utils;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

public class ConversationID {
	
	public static String getConversationIdFromCurrentWindow(WebDriver driver) {
		return getConversationIdFromCurrentWindow(driver, getCurrentApplication(driver)) ;
	}
	
	public static String getConversationIdFromCurrentWindow(WebDriver driver, String application) {
		String convoId = "";
		
		String title = "";

		StopWatch watch = new StopWatch();
		watch.start();
		do {
			try {
				title = driver.getTitle();
			} catch (NoSuchWindowException e) {

			}
		}while(title.isEmpty());
		watch.stop();
		watch.reset();
		//TestReporter.assertFalse(title.isEmpty(), "The title was not found");

		//TestReporter.log("PAGE TITLE:" + title);
		try{			
			switch (application.toLowerCase()) {
			case "lilo":
				convoId = title.split("-")[1];
				break;
				
			case "alc":
				String convoParts[] = title.split(" ");
				convoId = convoParts[2] + " - " + convoParts[4];
				break;
				
			case "dreams":
				convoId = title.split(" ")[8].split(",")[0];
				break;
			case "dvc":
				convoId = title.split(" ")[7];
				break;
			case "passport":
				convoId = title.replace("GBTS - Passport", "");
				break;
			case "sbc":
				convoId = title.substring(title.indexOf("Session")+8, title.length());
				break;
			case "mc":
				convoId = title.substring(title.indexOf("Session")+8, title.length());
				break;
			case "paymentui":
				convoId = title.split(" - ")[2];
				break;
			default:
				break;
			}
		}catch(ArrayIndexOutOfBoundsException aioobe){
			throw aioobe;
		}
		return convoId.trim();
	}
	
	public static String getCurrentApplication(WebDriver driver){
		String title = driver.getTitle().toLowerCase();
		String application = "";
		if(title.contains("dvc member")) application = "dvc";
		else if(title.contains("disney reservation entry and management")) application = "dreams";
		else if(title.contains("a la carte")) application = "alc";
		else if(title.contains("lilo")) application = "lilo";
		else if(title.contains("magical celebrations")) application = "mc";
		else if(title.contains("passport")) application = "passport";
		else if(title.contains("sbc version")) application = "sbc";
		else if(title.contains("apply payment")) application = "paymentui";
		
	//	if(application.isEmpty()) throw new RuntimeException("Failed to get application from window title");
		return application;
	}
}

