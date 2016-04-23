package com.akmi.jyxt.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

	
	/**
	  * @Title: getNowYear
	  * @Description: TODO(获得当年 年份)
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
	
	public static int  getNowYear()
	{
		Calendar now = Calendar.getInstance(); 
 		return now.get(Calendar.YEAR);  
	}
	
	/**
	  * @Title: getNowMonth
	  * @Description: TODO(获得当年月份)
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
	
	public static int  getNowMonth()
	{
		Calendar now = Calendar.getInstance(); 
		return now.get(Calendar.MONTH) + 1;  
	}
	

	/**
	  * @Title: getNowDay
	  * @Description: TODO(获得当年日)
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
	
	public static int  getNowDay()
	{
		Calendar now = Calendar.getInstance(); 
		return now.get(Calendar.DAY_OF_MONTH);  
	}
	
	
	

	
	/**
	  * @Title: getNowHour
	  * @Description: TODO(获得当前时)
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
	
	public static int  getNowHour()
	{
		Calendar now = Calendar.getInstance(); 
		return now.get(Calendar.DAY_OF_MONTH);  
	}
	
	/**
	  * @Title: getNowMinute
	  * @Description: TODO(获得当前分)
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
	
	public static int  getNowMinute()
	{
		Calendar now = Calendar.getInstance(); 
		return now.get(Calendar.MINUTE);  
	}
	
	
	/**
	  * @Title: getNowSECOND
	  * @Description: TODO(获得当前秒)
	  * @param @return
	  * @param @throws Exception
	  * @return int
	  * @throws
	 */
	
	public static int  getNowSECOND()
	{
		Calendar now = Calendar.getInstance(); 
		return now.get(Calendar.SECOND);  
	}
	


	/**
	  * @Title: getNowMinute
	  * @Description: TODO(获得当前毫秒)
	  * @param @return
	  * @param @throws Exception
	  * @return long
	  * @throws
	 */
	public static long  getNowMillis()
	{
		Calendar now = Calendar.getInstance(); 
		return now.getTimeInMillis();  
	}
	

	
	/**通过字符串获得日期
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateByString(String sourcedate)
	{
		return getDateByString(sourcedate,"yyyy-MM-dd 00:00:00");
	}

	
	/**通过字符串获得日期
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateByString(String sourcedate,String timeformate)
	{
		 Date retDate=null;
		 if(StringUtil.stringnotNull(sourcedate))
		 {
			 SimpleDateFormat df = new SimpleDateFormat(timeformate);
			 try {
				retDate=df.parse(sourcedate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 return retDate;
	}
	
	    /**
	     * 获得今天的日期
	     * @return
	     * @throws ParseException 
	     */
	    public static Date getTheDate() throws ParseException{
	    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			  return  df.parse(df.format(new Date()));
		 
		 }
	    
	    
	    
	    /**获得本田的格式化日期
	     * @param dateformate
	     * @return
	     * @throws ParseException
	     */
	    public static Date getNowDate(String dateformate) throws ParseException{
	    	 SimpleDateFormat df = new SimpleDateFormat(dateformate);
			  return  df.parse(df.format(new Date()));
		 
		 }
	    
	    /**获得格式化日期的字符串格式
	     * @param dateformate
	     * @return
	     * @throws ParseException
	     */
	    public static String getDateString(Date date,String dateformate) throws ParseException{
	    	 SimpleDateFormat df = new SimpleDateFormat(dateformate);
			  return  df.format(date);
		 
		 }
	    
	    
	    /** 获取本周一的日期
	     * @return
	     * @throws ParseException 
	     */
	    public static Date getWeekDate() throws ParseException
	    {
	    	Calendar cal =Calendar.getInstance();
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
	        try {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       return df.parse(df.format(cal.getTime()));
	    }
	    
	    /**获得本月的第一天
	     * @return
	     * @throws ParseException 
	     */
	    public static Date getMonthDate() throws ParseException
	    {
	    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-01 00:00:00");
			  return  df.parse(df.format(new Date()));
			
	    }
	    
	    /**
	     * 获得本年的第一天
	     * @return
	     * @throws ParseException 
	     */
	    public static Date getYearDate() throws ParseException{
		  SimpleDateFormat df = new SimpleDateFormat("yyyy-01-01 00:00:00");
		  return  df.parse(df.format(new Date()));
		 }
	    /**
	     * 获得上个月的第一天
	     * @return
	     * @throws ParseException 
	     */
	    public static Date findFristDayLastMonth() throws ParseException {
	    	
	    	
	        Calendar calendar = Calendar.getInstance();  
	        Date curDate = getMonthDate();  
	        calendar.setTime(curDate);  
	        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) - 1);  
	        return calendar.getTime();
	      
	     }
	    
	    /**
	     * 获得上个月的最后一天
	     * @return
	     * @throws ParseException 
	     */
	    public static Date findLastDayLastMonth() throws ParseException {
	        Calendar calendar = Calendar.getInstance();  
	        Date curDate = getMonthDate();  
	        calendar.setTime(curDate);  
	        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);  
	        return calendar.getTime();
	     }
	    
	    /**判断今天是周几
	     * @returnW
	     */
	    public static int getDayofWeek()
	    {
	    	  Date today = new Date();
	          Calendar c=Calendar.getInstance();
	          c.setTime(today);
	          return c.get(Calendar.DAY_OF_WEEK);
	    }
	    
	    /**
	     * @param datetype
	     * @return
	     * @throws ParseException
	     */
	    public static Map<String,Date> returnDateMap(int datetype) throws ParseException 
	    {
	    	Date startTime=null;
			Date endTime=null;
			switch(datetype)
			{
			case 1:
				startTime=DateUtil.getTheDate();
				endTime=null;
				break;
			case 2:
				startTime=DateUtil.getWeekDate();
				endTime=null;
				break;
			case 3:
				startTime=DateUtil.getMonthDate();
				endTime=null;
				break;
			case 4:
				startTime=DateUtil.findFristDayLastMonth();
				endTime=DateUtil.findLastDayLastMonth();
				break;
			case 5:
				startTime=DateUtil.getYearDate();
				endTime=null;
				break;
			default:
				startTime=null;
				endTime=null;
				break; 
			}
	    	  Map<String, Date> dateMap = new HashMap<String, Date>();
	    	  dateMap.put("startTime",startTime);
	    	  dateMap.put("endTime",endTime);
	    	  return dateMap;
	    }
	    
	    /**
	     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	     * 
	     * @param dateDate
	     * @return
	     */
	  public static String dateToStr(java.util.Date date) {
		 String dateString=null;
		 if(date!=null)
		 {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        dateString = formatter.format(date);
	     }
			 return dateString;
     }
	  
	  /**
	     * 获得当前时间
	     * @return
	     * @throws ParseException 
	     */
	    public static Timestamp getNowTimeStamp() throws ParseException{
		
	    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        Date date = new Date();  
	        String time = df1.format(date);  
	        Timestamp ts = Timestamp.valueOf(time); 
	        return ts;
		 }
	    
	    /**
	     * 根据需求格式获得当前时间
	     * @return
	     * @throws ParseException 
	     */
	    public static String getNowTime(String  timetype) throws ParseException{
	    	SimpleDateFormat df1 = new SimpleDateFormat(timetype);  
	        Date date = new Date();  
	        String time = df1.format(date);  
	        return time;
		 }
	    
	    /**
	     * 字符串转timstamp
	     * @return
	     */
	    public static Timestamp objToTimestamp(Object newsdate){
	    	  
	    	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	   Calendar cal = Calendar.getInstance();
	    	   try {
	    	    Date date = sdf.parse(newsdate.toString());
	    	    date.getTime();
	    	    cal.setTime(date);
	    	    return new Timestamp(cal.getTimeInMillis());
	    	   } catch (ParseException e) {
	    	    e.printStackTrace();
	    	   }
	    	  
	    	   cal.setTime(new Date());
	    	   return new Timestamp(cal.getTimeInMillis());
	    	}
	    
	    /**
	     * 获得今天是星期几
	     * @return
	     */
	    public static short getTodayWeek()
	    {
	    	Date today = new Date();
	        Calendar c=Calendar.getInstance();
	        c.setTime(today);
	        return (short) c.get(Calendar.DAY_OF_WEEK);
	    	
	    }
		/**获得当前时间
		 * @return
		 */
		public static String getNowDateString() { 

			  Date currentTime = new Date();
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss"); 
			  String dateString = formatter.format(currentTime);
			  return dateString;
			 }
		
		/**日期加减运算
		 * @param sourcedate
		 * @param datetype
		 * @param time
		 * @return
		 */
		public static Date DateArithmetic(Date sourcedate,int datetype,int amount)
		{
		   
		    Calendar c = Calendar.getInstance(); 
		    c.setTime(sourcedate); 
	        c.add(datetype, amount);
		    return c.getTime(); 
		}
         /**
          * 获得星期的字符串格式
         * @param date
         * @return
         */
        public static String getWeekStr(Date date)
         {
		  SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
		  return   dateFm.format(date);
         }
		
        
        
  	  /**
  	     * 时间到DateTime
  	     * @return
  	     * @throws ParseException 
  	     */
  	    public static Timestamp DatetoTimeStamp(Date date) throws ParseException{
  		
  	    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
  	        String time = df1.format(date);  
  	        Timestamp ts = Timestamp.valueOf(time); 
  	        return ts;
  		 }
}
