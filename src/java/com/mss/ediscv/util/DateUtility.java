/*
 ******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.util
 *
 * Date    : Mar 11, 2013 1:43:29 PM
 *
 * Author  : Nagireddy seerapu <nseerapu@miraclesoft.com>
 *
 * File    : DateUtility.java
 *

 * *****************************************************************************
 */
package com.mss.ediscv.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

 import java.text.SimpleDateFormat;
 import java.util.Date;
import java.util.StringTokenizer;
/*
 * This is date Utility class. will provide instances of current time stamp and 
 * used for date format changing.  
 */
public class DateUtility {

	private static DateUtility _instance;

	private DateUtility() {

	}

	/**
	 * @return An instance of the DataServiceLocator class
	 * @throws ServiceLocatorException
	 */
	public static DateUtility getInstance() throws ServiceLocatorException {
		try {
			if (_instance == null) {
				_instance = new DateUtility();
			}
		} catch (Exception ex) {
			throw new ServiceLocatorException(ex);
		}
		return _instance;
	}

	public String formatToDB2TS(String srcTS) {

		StringBuffer targetTS = new StringBuffer();
		// Source TS format should be MM-dd-yyyy HH:mm:ss.SSS
		// DB2: 2010-04-29 18:11:22.70334(Micro seconds)

		int milliSec = Integer.parseInt(srcTS.substring(srcTS.indexOf(".") + 1,
				srcTS.length()));
		targetTS.append(srcTS.substring(6, 10));
		targetTS.append("-" + srcTS.substring(0, 5));
		targetTS.append(" " + srcTS.substring(11, srcTS.indexOf(".")));
		targetTS.append("." + (milliSec));
		return targetTS.toString();
	}

	public String formatToDB2BatchTS(String srcTS) {

		StringBuffer targetTS = new StringBuffer();
		// Source TS format should be dd-MM-yyyy HH:mm:ss
		// DB2: 2010-04-29 18:11:22(Without seconds)

		targetTS.append(srcTS.substring(6, 10));
		targetTS.append("-" + srcTS.substring(0, 2));
		targetTS.append("-" + srcTS.substring(3, 5));
		targetTS.append(" " + srcTS.substring(11, srcTS.length()));
		return targetTS.toString();
	}

	public String formatToDB2TS1(String srcTS) {

		StringBuffer targetTS = new StringBuffer();
		// Source TS format should be MM-dd-yyyy HH:mm:ss.SSS
		// DB2: 2010-04-29 18:11:22.70334(Micro seconds)

		int milliSec = Integer.parseInt(srcTS.substring(srcTS.indexOf(".") + 1,
				srcTS.length()));
		targetTS.append(srcTS.substring(6, 10));
		targetTS.append("-" + srcTS.substring(0, 2));
		targetTS.append("-" + srcTS.substring(3, 5));
		targetTS.append(" " + srcTS.substring(11, srcTS.indexOf(".")));
		targetTS.append("." + (milliSec));
		return targetTS.toString();
	}

	/*
	 * public Timestamp convertDateToDB2TS(){ //current date :Mon Jul 05
	 * 19:08:48 EDT 2010 //DB2: 2010-07-05 18:11:22.70334(Micro seconds) Date
	 * mydate=new Date(); }
	 */

	public String formatToCCTS(String srcTS) {

		StringBuffer targetTS = new StringBuffer();
		// DB2: 2010-04-29 18:11:22.70334(Micro seconds)
		// Traget TS format should be MM-dd-yyyy HH:mm:ss.SSS

		int microSec = Integer.parseInt(srcTS.substring(srcTS.indexOf(".") + 1,
				srcTS.length()));
		targetTS.append(srcTS.substring(5, 10));
		targetTS.append("-" + srcTS.substring(0, 4));
		targetTS.append(" " + srcTS.substring(11, srcTS.indexOf(".")));
		targetTS.append("." + (microSec / 1000));
		return targetTS.toString();
	}

	public String formatToDB2Date(String srcDate) {
		StringBuffer targetDate = new StringBuffer();
		// Source Date format should be MM-dd-yyyy 02-16-2010 30-08-2010
		// DB2:2006-10-03
		targetDate.append(srcDate.substring(6, 10));
		targetDate.append("-" + srcDate.substring(0, 2));
		targetDate.append("-" + srcDate.substring(3, 5));
		return targetDate.toString();
	}

	public String formatToCCDate(String srcDate) {
		StringBuffer targetDate = new StringBuffer();
		// DB2:2006-10-03
		// Target Date format should be MM-dd-yyyy

		targetDate.append(srcDate.substring(8, 10));
		targetDate.append("-" + srcDate.substring(5, 7));
		targetDate.append("-" + srcDate.substring(0, 4));
		return targetDate.toString();
	}

	// This method will return the current timestamp in DB2 accepted format
	public String getCurrentDB2TS() throws ServiceLocatorException {

		// DB2 timestamp pattern yyyy-MM-dd HH:mm:ss.SSSSSS
		String strFormat = "yyyy-MM-dd HH:mm:ss.SSSSSS";
		DateFormat myDateFormat = new SimpleDateFormat(strFormat);
		Calendar cal = new GregorianCalendar();
		java.util.Date now = cal.getTime();
		long time = now.getTime();
		java.sql.Date date = new java.sql.Date(time);
		return myDateFormat.format(date);
	}

        public Timestamp getCurrentDB2Timestamp() throws ServiceLocatorException {

		// DB2 timestamp pattern yyyy-MM-dd HH:mm:ss.SSSSSS
		String strFormat = "yyyy-MM-dd HH:mm:ss.SSSSSS";
		DateFormat myDateFormat = new SimpleDateFormat(strFormat);
		Calendar cal = new GregorianCalendar();
		java.util.Date now = cal.getTime();
		long time = now.getTime();
		//java.sql.Date date = new java.sql.Date(time);
		//return myDateFormat.format(date);
                return new Timestamp(time);
	}
        
	/*
	 * public static void main(String args[]){ Timestamp s; String
	 * y="2010-06-09"; Date z; z=formatToDB2Date(y);
	 * 
	 * }
	 */
        
        
        /*
         * mysql date format for searchnig
         */
        
        public String convertStringToMySQLDate(String dateString){
        SimpleDateFormat sdfInput =new SimpleDateFormat("MM/dd/yyyy") ;
        SimpleDateFormat sdfOutput =new SimpleDateFormat("yyyy-MM-dd") ;
        java.util.Date date=null;
        try {
            date = sdfInput.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        //System.out.println(  sdfOutput.format(  date  )   ) ;
        return sdfOutput.format(  date  ) ;
    }
        
        /**
         * @param viewDate
         * @return 
         * 
         * INPUT : MM/dd/yyyy HH:MM
         * OUTPUT : yyy-MM-dd HH:MM:ss.o
         */
        
      public String DateViewToDBCompare(String viewDate)
        {
           // String input = "MM/dd/yyyy HH:mm";
        StringTokenizer st = new StringTokenizer(viewDate);
        String date = st.nextToken();
        String time = st.nextToken();
        st=new StringTokenizer(date,"/");
        String month = st.nextToken();
        String date1 = st.nextToken();
        String year = st.nextToken();
        String covertedDate = year+"-"+month+"-"+date1;
        String ConvertedDateTime = covertedDate+" "+time+":00.0";
            
                return ConvertedDateTime;
            
        }
        
    /**
     *  PM / AM Date Conversion
     * 
     */    
        public String DateTo12HourceFormat(String datein24){
             String dateString = "datein24";
             String datein12 = "";
        
        String str[] = dateString.split(" ");
        int hour = Integer.parseInt(str[1].substring(0, 2));
        int i=0;
        if(hour>12) {
           // System.out.println("hii");
            hour = hour - 12;
            i++;
        }
        String changedTime = str[1].substring(2, str[1].length());
        if(hour<10)
            changedTime = "0"+hour+changedTime;
        else
            changedTime = hour+changedTime;
        if(i==0)
            changedTime = changedTime+" AM";
        else
            changedTime = changedTime+" PM";
        datein12 = str[0]+" "+changedTime;
       // System.out.println("Actual Time-->"+dateString);
        //System.out.println("Changed DateTime-->"+datein12);
        
        return datein12;
        }         
         
        
        /**
         * 
         * to get the current data and time
         */
        public String fromDate() {
            long DAY_IN_MS = 1000 * 60 * 60 * 24;
            Date d = new Date(System.currentTimeMillis() - (2 * DAY_IN_MS));
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
            return sdf.format(d);
        }
        public String toDate() {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
            return sdf.format(d);
        }
          /**
         * @param viewDate
         * @return 
         * 
         * INPUT : MM/dd/yyyy HH:MM
         * OUTPUT : month or date
         */
        
      public int monthYear(String viewDate,String dateormonth)
        {
             System.out.println("hi------------------->"+viewDate);
         System.out.println("hi-------------"+dateormonth);
           // String input = "MM/dd/yyyy HH:mm";
        StringTokenizer st = new StringTokenizer(viewDate);
        String date = st.nextToken();
        String time = st.nextToken();
        st=new StringTokenizer(date,"/");
        int month =Integer.parseInt( st.nextToken());
        String date1 = st.nextToken();
        int year = Integer.parseInt( st.nextToken());
        //String covertedDate = year+"-"+month+"-"+date1;
        int ConvertedDateTime = 0;
          if(dateormonth.equalsIgnoreCase("month"))
          {
              ConvertedDateTime=month;
          }
          if(dateormonth.equalsIgnoreCase("year"))
          {
              ConvertedDateTime=year;
          }
                return ConvertedDateTime;
            
        }
      
      public String getCurrentMySqlDateTime1(){
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        return simpleDateFormat.format(calendar.getTime()).toString();
    }
}

