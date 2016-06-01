package Utility;

import java.sql.Timestamp;
import org.testng.Reporter;

public class TestReporter {

	public static void logStep(String step){
		Reporter.log("<br/><b><font size = 4>Step: " + step + "</font></b><br/>");
		System.out.println("Cus logStep: "+step);
	}
	
	public static void logScenario(){
		Reporter.log("<br/><b><font size = 4>Data Scenario: " + Datatable.getCurrentScenario() + "</font></b><br/>");
	}
	
	public static void logScenario(String scenario){
		Reporter.log("<br/><br><font size = 4>Data Scenario: "+ scenario +"</font></br><br/>");
		System.out.println("Cus logScenario: " + scenario);
	}
	
	public static void log(String message){
		Reporter.log(new Timestamp(new java.util.Date().getTime())+" :: "+ message + "<br />");
		System.out.println(new Timestamp(new java.util.Date().getTime())+" :: "+ message);
	}

	
	public static void logStep(Integer step) {
		Reporter.log("<br/><b><font size = 4>Step: " + step + "</font></b><br/>");
		System.out.println("Cus logStep: "+step);
		
	}
}
