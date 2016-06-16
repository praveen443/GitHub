package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @summary: In Java, you can get the current date time via following two classes -
 * 1.Date , and 2.Calendar
 * @author Praveen varma, @version: Created 16-06-2016
 */
public class SimpleDateAndCalendar {

	//In Java, you can get the current date time via following two classes – Date and Calendar
	/**
	 * @summary: To get the current date based on the String paramter format used.
	 * @author praveen varma, @version 16-06-2016
	 * @param dateFormat, @return
	 */
	public static String getTodaysDate(String dateFormat){
		SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		String getTodaysDate = simpleDate.format(date);
		System.out.println("Current Date: " + getTodaysDate);
		return getTodaysDate;
	}
	
	/**
	 * @summary: To get the current date based on the String paramter format used.
	 * @author praveen varma, @version 16-06-2016
	 * @param timeFormat, @return
	 */
	public static String getCurrentTime(String timeFormat){
		DateFormat dateFormat = new SimpleDateFormat(timeFormat);
		Calendar cal = Calendar.getInstance();
		String getPresentTime = dateFormat.format(cal.getTime());
		System.out.println("Current Time: " + dateFormat.format(cal.getTime())); //Ex: 16:00:22
		return getPresentTime;
	}
}
