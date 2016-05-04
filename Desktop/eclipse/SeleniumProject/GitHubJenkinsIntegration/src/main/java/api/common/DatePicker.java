package api.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePicker {
	
	public String setStartDate() throws Exception{
	
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		String startDate = format.format(cal.getTime());
		System.out.println(startDate);
		
		return startDate;
	}	
	public String setEndDate() throws Exception{
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 14);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");		
		String endDate = format.format(cal.getTime());
		System.out.println(endDate);
		
		return endDate;
		
	}

}

