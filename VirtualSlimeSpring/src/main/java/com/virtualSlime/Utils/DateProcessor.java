package com.virtualSlime.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateProcessor {
    /**
     * Contains methods processing data of Date, Calendar or long
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final Calendar calendar = Calendar.getInstance();

    public static Date getInitialTime(){
        //return date-typed value of 1970-1-1
        calendar.set(1970, Calendar.JANUARY,1);//Note that month starts from 0 in Calendar

        return calendar.getTime();
    }

    public static Date getDateFromString(String newDate){
        //get a date obj from a string
        Date parsedNow;
        try{
            parsedNow = dateFormat.parse(newDate);
        }catch(ParseException e){
            //when parsing failed, set time to 1970-1-1
            parsedNow = getInitialTime();
        }

        return parsedNow;
    }

    public static Calendar getCalendarFromDate(Date newDate){
        calendar.setTime(newDate);

        return (Calendar)calendar.clone();
    }

    public static String getDateStringFromTimestamp(Date date){
        return dateFormat.format(date);
    }
}
