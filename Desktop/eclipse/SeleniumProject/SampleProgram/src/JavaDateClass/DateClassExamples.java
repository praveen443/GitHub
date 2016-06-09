package JavaDateClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.testng.annotations.Test;

public class DateClassExamples {
 
  //Example of getting current date in Java.
  @Test(priority=1,enabled=true,groups={"TestNG Tests"})
  public void getCurrentDate() throws ParseException {
	 //In java 8, you can use LocalDate class.
	 LocalDate today = LocalDate.now();
	 System.out.println("Today's Local date : " + today);
	 
	 LocalTime time = LocalTime.now();
	 System.out.println("local time now : " + time);
	 
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
	 SimpleDateFormat dtFormat = new SimpleDateFormat("dd-MM-yyyy");
	 String date2 = dtFormat.format(new Date());
	 System.out.println("Formatting Date to String representation : " + date2);
	 
	 //*******************************************
	 //Convert Calendar to Date
	 //*******************************************
	 Calendar calendar = Calendar.getInstance();
	 Date date3 =  calendar.getTime();
	 System.out.println("Convert Calendar to Date : "+date3);
	 
	 //*******************************************
	 //Convert Date to Calendar
	 //*******************************************
	 SimpleDateFormat smpdft = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	 String dateInputAsString = "27-04-2016 10:22:56";
	 Date date4 = smpdft.parse(dateInputAsString);
	  
	 Calendar calender = Calendar.getInstance();
	 calender.setTime(date4);
	 
	 //*******************************************
	 //Compare two dates
	 //*******************************************
	 Date dt = new Date();
	 Date dte = new Date();
	 int comparision = dt.compareTo(dte);
	 System.out.println(comparision);
	 
	 
	 //**************************************************************
	 //In Java8, u can get different time units using localDate class
	 //**************************************************************
	 LocalDate localDate= LocalDate.now();
	 int getYear = localDate.getYear();
	 System.out.println("Current year is : "+ getYear);
	 
	 int getMonth = localDate.getMonthValue();
	 System.out.println("Current month is : "+ getMonth);
	 
	 int getDayOfMonth = localDate.getDayOfMonth();
	 System.out.println("Current Day of month : "+ getDayOfMonth);
	 
	 //Below code is to Compare two dates using localdate class.
	 LocalDate actualDate = LocalDate.now();
	 LocalDate expectedDate = LocalDate.of(2016, 06, 9);
	 if(actualDate.equals(expectedDate)){
	     System.out.printf("Actual (or) Current date is - %s and expectedDate is - %s. Both actual and expected dates matches.", actualDate, expectedDate);
	 }
	 
	 //The methods to get the year, month, day of month, hour etc. are deprecated. 
	 //If you need to get or set the year, month, day of month etc. use a java.util.Calendar instead. 
	 Calendar calendar1 = new GregorianCalendar();
	 
	 int year       = calendar1.get(Calendar.YEAR);
	 System.out.println(year);
	 int month      = calendar1.get(Calendar.MONTH); 
	 System.out.println(month);
	 int dayOfMonth = calendar1.get(Calendar.DAY_OF_MONTH); // Jan = 0, not 1
	 System.out.println(dayOfMonth);
	 int dayOfWeek  = calendar1.get(Calendar.DAY_OF_WEEK);
	 System.out.println(dayOfWeek);
	 int weekOfYear = calendar1.get(Calendar.WEEK_OF_YEAR);
	 System.out.println(weekOfYear);
	 int weekOfMonth= calendar1.get(Calendar.WEEK_OF_MONTH);
	 System.out.println(weekOfMonth);
	  
	 int hour       = calendar1.get(Calendar.HOUR);        // 12 hour clock
	 System.out.println(hour);
	 int hourOfDay  = calendar1.get(Calendar.HOUR_OF_DAY); // 24 hour clock
	 System.out.println(hourOfDay);
	 int minute     = calendar1.get(Calendar.MINUTE);
	 System.out.println(minute);
	 int second     = calendar1.get(Calendar.SECOND);
	 System.out.println(second);
	 int millisecond= calendar1.get(Calendar.MILLISECOND);
	 System.out.println(millisecond);
  }
  
}
