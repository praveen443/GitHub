package selenium.common.date;

/***********************************************************************************************************************
 * FileName - DatePicker.java
 * 
 * (c) Disney. All rights reserved.
 * 
 * $Author: kaiy001 $ $Revision: #1 $ $Change: 715510 $ $Date: Aug 31, 2012 $
 ***********************************************************************************************************************/


import java.util.Calendar;

import selenium.common.Constants;
import selenium.common.Log;
import selenium.common.enums.Environment;

/**
 * Class to select date.
 * 
 * @see Try to use {@link SimpleDate} as an alternative to this class.
 * 
 * @author kaiy001
 */
public class DatePicker {

    /**
     * Structure to store a date.
     * 
     * Huy - Should be expanded via sub-classing/composite to include room options
     * and other similar data that always gets bunched together.  Should also be a 
     * bean instead of a POJO?
     * 
     * @author kaiy001
     */
    static public class DateStruct {
        public int month;
        public int day;
        public int year;
    }
    
    private Calendar calendar = Calendar.getInstance();

    public DatePicker() {
    }

    /**
     * Returns date that is daysFromToday days from today
     * 
     * @param daysFromToday
     *            Days from today
     * @return Date in the DateStruct structure
     */
    public DateStruct getDate(int daysFromToday) {

        // We are having availability issue in SL/Prod if try to book close to
        // today
        if (Environment.isSoftLaunch() || Environment.isProduction()) {
            // We have only 8 months of availability, if our original (JSON)
            // checkin date is already far,
            // like 120 days, then we go over limit
            if (daysFromToday < 60) {
                int previousValue = daysFromToday;
                daysFromToday = daysFromToday + Constants.prodDaysOffset;
                
                Log.getDefaultLogger().info("Increasing checkIn-Out date offset from " + 
                        previousValue + " by " + Constants.prodDaysOffset + " to " + daysFromToday);
            }
        }

        calendar.add(Calendar.DATE, daysFromToday);

        DateStruct date = new DateStruct();
        date.month = calendar.get(Calendar.MONTH) + 1;
        date.day = calendar.get(Calendar.DATE);
        date.year = calendar.get(Calendar.YEAR);
        return date;
    }

    public int daysOfYear(int daysFromToday) {
        int daysInYear = 0;
        daysInYear = calendar.get(Calendar.DAY_OF_YEAR) + daysFromToday;
        return daysInYear;
    }

    /**
     * Returns date that is ahead by the value passed from monthsFromToday
     * 
     * @param monthsFromToday
     * @return Date in the DateStruct structure
     */
    public DateStruct advanceDateByMonth(int monthsFromToday) {
        calendar.add(Calendar.MONTH, monthsFromToday + 1);
        DateStruct date = new DateStruct();
        date.month = calendar.get(Calendar.MONTH);
        date.day = calendar.get(Calendar.DATE);
        date.year = calendar.get(Calendar.YEAR);
        return date;
    }
}
