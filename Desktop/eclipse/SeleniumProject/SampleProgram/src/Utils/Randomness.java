package Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomness {

	/**
	 * @summary: Method to generate the RandomNumber based on the integer number.
	 * @author: Praveen Namburi, @version: Created 13-05-2016
	 * @param length
	 * @return
	 */
	public static String randomNumber(int length){
		 new RandomStringUtils();
	     return RandomStringUtils.randomNumeric(length);
	}

	/**
	 * @summary: Method to generate the RandomString based on the integer number.
	 * @author: Praveen Namburi, @version: Created 13-05-2016
	 * @param length
	 * @return
	 */
	public static String randomString(int lenght){
		new RandomStringUtils();
		return RandomStringUtils.randomAlphabetic(lenght);
	}
	
	/**
	 * @summary: Method to generate the Random AlphaNumneric String based on the integer number.
	 * @author: Praveen Namburi, @version: Created 13-05-2016
	 * @param length
	 * @return
	 */
	public static String randomAlphaNummeric(int length){
		new RandomStringUtils();
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	/**
	 * @summary: Method to generate the Current Date and Time.
	 * @author: Praveen Namburi, @version: Created 13-05-2016
	 * @return
	 */
	public static String generateCurrentDatetime() {
		String repDate = "";
		DateFormat dfms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); // for display with milliseconds
		Calendar cal = Calendar.getInstance();
		repDate = dfms.format(cal.getTime());

		return repDate;
	}
	
	public static String generateCurDateTime(){
		String repDate="";
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		repDate = dateFormat.format(cal.getTime());
		
		return repDate;
		
	}
}
