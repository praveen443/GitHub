package JavaDateClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.testng.annotations.Test;

public class DateClassExamples {
 
  //Example of getting current date in Java.
  @Test(priority=1,enabled=true,groups={"TestNG Tests"})
  public void getCurrentDate() throws ParseException {
	/*  //In java 8, you can use LocalDate class.
	 LocalDate today = LocalDate.now();
	 System.out.println("Today's Local date : " + today);
	 
	 LocalTime time = LocalTime.now();
	 System.out.println("local time now : " + time);*/
	 
	 //********************************************
	 //Conert Date to String
	 //Example of parsing Date to String object.
	 //********************************************
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Date date = new Date();
	 String currentDate = String.valueOf(dateFormat.format(date));
	 System.out.println("Current Date and Time is : "+currentDate);
	 
	 //********************************************
	 //Parse String to Date
	 //Example of parsing String to Date object.
	 //********************************************
	 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	 String dateInString = "09-06-2016 10:20:56";
	 Date date1 = sdf.parse(dateInString);
	 System.out.println("Parsing String to Date object : "+date1);
	 
	 //****************************************************
	 //Example of formatting Date to String representation
	 //****************************************************
	 SimpleDateFormat dtFormat = new SimpleDateFormat();
	 String date2 = dtFormat.format(new Date());
	 System.out.println("Formatting Date to String representation : " + date2);
	 
  
  }
}
