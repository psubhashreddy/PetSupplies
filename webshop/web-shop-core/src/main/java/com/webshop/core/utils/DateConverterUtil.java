/**
 * 
 */
package com.webshop.core.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * This is a util class for date converter from one type to other
 * 
 * @author speddyre
 * @date 5th June 2015
 */
public final class DateConverterUtil
{

   /**
    * Not to allow to instantiate it from outside
    */
   private DateConverterUtil()
   {

   }

   /**
    * This method is used to convert the current java.util.Date to java.sql.TimeStamp
    * 
    * @return TimeStamp
    */
   public static Timestamp dateToTimeStamp()
   {
      Date utilDate = new Date();
      Calendar cal = Calendar.getInstance();
      cal.setTime(utilDate);
      cal.set(Calendar.MILLISECOND, 0);
      return new Timestamp(utilDate.getTime());

   }

   public static Timestamp getDateAfterDays(int noOfDays)
   {
      Date utilDate = new Date();
      GregorianCalendar gregCalendar = new GregorianCalendar();
      gregCalendar.setTime(utilDate);
      gregCalendar.add(Calendar.DATE, noOfDays);
      Date date = gregCalendar.getTime();
      return new Timestamp(date.getTime());
   }

}
