package com.charityfoundation.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public final static String dateFormat_Full= "yyyy-MM-dd HH:mm:ss";
	public final static String dateFormat_DateShort= "yyyy-MM-dd";
	public final static String dateFormat_DateShort2= "yyyyMMdd";
	public final static String dateFormat_DateShort3= "yyyyMMddhhmm";
	public final static String dateFormat_Year= "yyyy";	
	public final static String dateFormat_HourMinute= "HH:mm";
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat(dateFormat_Year);
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(dateFormat_DateShort2);

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(dateFormat_DateShort);

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(dateFormat_Full);
	
	private final static SimpleDateFormat sdfTimeTen = new SimpleDateFormat(dateFormat_DateShort3);
	
	/**
	 * 时间格式-yyyyMMddHHmmss
	 */
	public final static SimpleDateFormat sdfDateTimeStr = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取当前日期YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}
	
	/**
	 * 获取yyyyMMddHHmmss格式
	 * 
	 * @return
	 */
	public static String getFullDate(){
	  return sdfDateTimeStr.format(new Date());
	}
	
	

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	
	public static String getTime(Date date) {
		return sdfTime.format(date);
	}
	
	public static String getTimeTen() {
		return sdfTimeTen.format(new Date());
	}
	

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}
	
	public static boolean compareTime(String s, String e) {
		if(fomatDate1(s)==null||fomatDate1(e)==null){
			return false;
		}
		return fomatDate1(s).getTime() >=fomatDate1(e).getTime();
	}
	
	
	public static int compare(String s, String e){
		if(fomatDate(s)==null||fomatDate(e)==null){
			return 0;
		}else if(fomatDate(s).getTime() >=fomatDate(e).getTime()){
			return 1;
		}else{
			return-1;
		}
}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate1(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date fomatDate(String date, String format) {
		DateFormat fmt = new SimpleDateFormat(format);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期(全日期时间 格式)
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	
    	String dateStr = getAfterDayDate(days,Calendar.DATE,dateFormat_Full);
        
        return dateStr;
    }

    /**
     * 得到n(天/小时/月)之后的日期
     * @param days
     * @param field
     * @param format
     * @return
     */
	public static String getAfterDayDate(String days,int field,String format) {
		int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(field, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat(format);
        String dateStr = sdfd.format(date);
		return dateStr;
	}
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }
    
    /**
	 * 获取 指定格式的 当前日期字符串
	 * @param format
	 * @return
	 */
	public static String getCurrentTime(SimpleDateFormat format) {
		return format.format(new Date());
	}
	
	/**
	 * @deprecated:获取星期
	 * @return 
	 */
	public static String getWeekOfDate(Date date){
		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};  
	    Calendar cal = Calendar.getInstance();  
	    cal.setTime(date);  
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;  
	    if (w < 0)  
	        w = 0;  
	    return weekDays[w];  
	}
	
	/**
	 * 获取星期数
	 * @param date
	 * @return
	 */
	public static Integer getWeekOfDateValue(Date date){
		Calendar cal = Calendar.getInstance();  
	    cal.setTime(date);  
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;  
	    if (w < 0)  
	        w = 0;  
	    return w;  
	}
	
	/**
	 * 根据星期数获取星期
	 * @param value
	 * @return
	 */
	public static String getWeekOfDateByValue(Integer value){
		String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
		return weekDays[value];
	}
    
    public static void main(String[] args) throws ParseException {
//    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//    	
//    	System.out.println(sdf.parse(getCurrentTime(sdfOnlyHourMinutes)));
//    	int date = Calendar.getInstance().getTime().getDay(); 
    	String currentDate = DateUtil.getCurrentTime(new SimpleDateFormat(DateUtil.dateFormat_DateShort));
		Integer weekOfDateValue = DateUtil.getWeekOfDateValue(DateUtil.fomatDate(currentDate));
		String weekOfDate = DateUtil.getWeekOfDateByValue(weekOfDateValue);
//		mv.addObject("currentTime",currentDate+" "+weekOfDate);
    	System.out.println(currentDate+" "+weekOfDate);
    
    }

}
