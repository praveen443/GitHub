package JavaDateClass;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import org.testng.annotations.Test;

public class DateClassExamples {
 
  //Example of getting current date in Java.
  @Test(priority=1,enabled=true,groups={"TestNG Tests"})
  public void getCurrentDate() {
	/*  //In java 8, you can use LocalDate class.
	 LocalDate today = LocalDate.now();
	 System.out.println("Today's Local date : " + today);
	 
	 LocalTime time = LocalTime.now();
	 System.out.println("local time now : " + time);*/
	 
	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 Date date = new Date();
	 String currentDate = String.valueOf(dateFormat.format(date));
	 System.out.println("Current Date and Time is : "+currentDate);
	 
  }
}
