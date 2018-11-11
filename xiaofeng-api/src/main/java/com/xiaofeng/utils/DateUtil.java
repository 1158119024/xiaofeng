
package com.xiaofeng.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * 日期处理工具
 */
public class DateUtil {

    /* 
     * mode(模式) (受次影响的是getStringOfDate()方法)
     * 第一种,返回正常的date的字符串(默认的)
     * 第二种,返回date的当天的开始时间
     * 第三种,返回date的当天的结束时间
     */
    public final static String defaultMode = "normalOfDate";

    public final static String beginOfDatefMode = "beginOfDate";

    public final static String endOfDateMode = "endOfDate";

    /**
     * 一天的毫秒数
     */
    public final static long DAY_MILLISECOND = 24 * 60 * 60 * 1000;

    /**
     * 默认的日期格式字符串 yyyy-MM-dd
     */
    public final static String exportXlsDateCreateTimeFormat = "yyyy-MM-dd HH:mm";//创建时间，由于后台几乎所有的创建时间都是用这种形式展示，故设一常量
    public final static String exportXlsDateFormat = "yyyy.MM.dd";//导出xls文件的文件名中时间部分格式
    public final static String defaultDatePatternStr = "yyyy-MM-dd";
    public final static String chineseDatePatternStr = "yyyy年MM月dd日";
    /**
     * 默认的日期时间格式字符串 yyyy-MM-dd HH:mm:ss
     */
    public final static String defaultDateTimePatternStr = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认的长日期时间格式字符串 yyyy-MM-dd HH:mm:ss:SSS
     */
    public final static String defaultLongDateTimePatternStr = "yyyy-MM-dd HH:mm:ss:SSS";

    public enum DatePart {
        /**
         * 年
         */
        yy,
        /**
         * 月
         */
        MM,
        /**
         * 日
         */
        dd,
        /**
         * 时
         */
        HH,
        /**
         * 分
         */
        mm,
        /**
         * 秒
         */
        ss
    }

    /**
     * 把Date.gettime()的值还原回Date
     * @param timeString
     * @return
     */
    public static Date getByTime(String timeString) {
        Date date = null;
        if (!StringUtils.isEmpty(timeString))
            date = new Date(Long.valueOf(timeString));
        else
            date = new Date();
        return date;
    }

    /**
     * 把字符串格式化成日期类型("yyyy-MM-dd HH:mm:ss")
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date convertStr2Date(String dateStr) throws ParseException {
        return parse(dateStr, defaultDateTimePatternStr);
    }

    /**
     * 把字符串格式化成日期类型
     * @param dateStr
     * @param pattern 日期格式，为空时默认为"yyyy-MM-dd HH:mm:ss"格式
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        if (pattern == null)
            pattern = defaultDateTimePatternStr;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(dateStr);
        return date;
    }

    /**
     * 将Date类型的日期转换成"yyyy-MM-dd HH:mm:ss"类型的字符串
     * @param date
     * @return
     */
    public static String convertDate2Str(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimePatternStr);
        return simpleDateFormat.format(date);
    }

    /**
     * 将Date类型的日期转换成指定格式的字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (pattern == null)
            pattern = defaultDateTimePatternStr;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取指定日期的开始时间
     * @param date
     * @return
     */
    public static Date getBeginOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 获取指定日期的最后时间
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 999);
        return cld.getTime();
    }

    /**
    * 方法用途和描述: 取得当前时间的字符串 例如：20080221050416
    * @return
    */
    public static String getCurrentTimeStr() {
        String str = format(new Date(), null);
        str = str.trim();
        str = str.replaceAll("-", "");
        str = str.replaceAll(":", "");
        str = str.replaceAll(" ", "");
        return str;
    }
    
    /**
     * 方法用途和描述: 将时间字符串转为正常的日期，
     * 例如  
     * 20080221050416000 转为   2008-02-21 05:04:16 000
     * @return
     */
     public static String setDateToString(String date) {
    	 if (StringUtils.isEmpty(date)) {
			return "";
		 }
    	 if (date.length()>=14) {
    		 date = date.substring(0, 15);
    		 String result = date.substring(0,4)+"-"+date.substring(4,6)+"-"
				 + date.substring(6,8)+" "+date.substring(8,10)+":"
				 +date.substring(10,12)+":"+date.substring(12,14);
    		 return result;
		 }else {
			return "";
		 }
    	 
     }
    /**
     * 比较两个日期，忽略时间，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateIgnoreTime(Date date1, Date date2) {
        date1 = ignoreTime(date1);
        date2 = ignoreTime(date2);
        if (date1.after(date2))
            return 1;
        if (date1.before(date2))
            return -1;
        return 0;
    }

    /**
     * 比较两个时间，忽略分，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTimeIgnoreMinute(Date date1, Date date2) {
        date1 = ignoreMinute(date1);
        date2 = ignoreMinute(date2);
        if (date1.after(date2))
            return 1;
        if (date1.before(date2))
            return -1;
        return 0;
    }

    /**
     * 比较两个时间，忽略秒，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTimeIgnoreSecond(Date date1, Date date2) {
        date1 = ignoreSecond(date1);
        date2 = ignoreSecond(date2);
        if (date1.after(date2))
            return 1;
        if (date1.before(date2))
            return -1;
        return 0;
    }

    /**
     * 比较两个时间，忽略毫秒，如果date1 > date 2，返回1，date1 = date2，返回0，date1 < date2，返回-1
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTimeIgnoreMillisecond(Date date1, Date date2) {
        date1 = ignoreMillisecond(date1);
        date2 = ignoreMillisecond(date2);
        if (date1.after(date2))
            return 1;
        if (date1.before(date2))
            return -1;
        return 0;
    }

    /**
     * 忽略时间，把时、分、秒、毫秒都变为0
     * @param date
     * @return
     */
    public static Date ignoreTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 方法用途和描述: 忽略分，分、秒、毫秒都变为0
     * @param date
     * @return
     */
    public static Date ignoreMinute(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 方法用途和描述: 忽略秒，秒、毫秒都变为0
     * @param date
     * @return
     */
    public static Date ignoreSecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 方法用途和描述: 忽略毫秒，毫秒变为0
     * @param date
     * @return
     */
    public static Date ignoreMillisecond(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
    * 方法用途和描述: 获取本周的开始时间
    * @param weekFirstDateForm 一周的第一天由星期几开始，如果为空则取默认的星期一开始
    * @return
     */
    public static Date getCurrentWeekBeginDate(Integer weekFirstDateForm) {
        Calendar calendar = Calendar.getInstance();
        boolean isSunday = isSunday(calendar);
        if (weekFirstDateForm == null || weekFirstDateForm < Calendar.SUNDAY || weekFirstDateForm > Calendar.SATURDAY)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//默认设置从周一开始
        else
            calendar.set(Calendar.DAY_OF_WEEK, weekFirstDateForm);
        calendar.setTime(ignoreTime(calendar.getTime()));
        if (isSunday)
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - Calendar.DAY_OF_WEEK);
        return calendar.getTime();
    }

    /**
    * 方法用途和描述: 获取本周的结束时间
    * @param weekFirstDateForm 一周的第一天由星期几开始，如果为空则取默认的星期一开始 
    * @return
    */
    public static Date getCurrentWeekEndDate(Integer weekFirstDateForm) {
        Calendar calendar = Calendar.getInstance();
        boolean isSunday = isSunday(calendar);
        if (weekFirstDateForm == null || weekFirstDateForm < Calendar.SUNDAY || weekFirstDateForm > Calendar.SATURDAY)
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//设置从周一开始
        else
            calendar.set(Calendar.DAY_OF_WEEK, weekFirstDateForm);
        calendar.setTime(ignoreTime(calendar.getTime()));
        if (!isSunday)
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + Calendar.DAY_OF_WEEK);
        calendar.set(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
    * 方法用途和描述: 是否是星期天
    * @param date
    * @return
    */
    public static boolean isSunday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 方法用途和描述: 是否是星期天
     * @param calendar
     * @return
     */
    public static boolean isSunday(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss:SSS类型的日期转换成yyyy-MM-dd类型的日期对象
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date convertLongDateTimeToDate(Date date) throws ParseException {
        return parse(format(date, defaultLongDateTimePatternStr), defaultDatePatternStr);
    }

    /**
     * Function:获得两个时间点之间相差的天数
     * @param date1  开始时间点
     * @param date2  结束时间点
     * @return  具体的天数
     * @see{@link #getTwoDatesInterval(Date, Date)}
     */
    @Deprecated
    public static int getDays(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;

        if (date1 instanceof java.sql.Date) {
            try {
                date1 = convertLongDateTimeToDate(date1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (date2 instanceof java.sql.Date) {
            try {
                date2 = convertLongDateTimeToDate(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        final long times = date2.getTime() - date1.getTime();
        long days = times / DAY_MILLISECOND;
        if (days != 0)
            return (int) days;

        /* 
         * days等于0 表示的情况有：
         * 1、两个时间相等
         * 2、同一天内的两个时间
         * 3、相隔两天内的两个时间，但时间差小于24小时
         */
        if (times == 0)
            return 0;

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date2);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);
        int year2 = calendar.get(Calendar.YEAR);

        calendar.setTime(date1);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);
        int year1 = calendar.get(Calendar.YEAR);

        if (times > 0)
            if (year2 > year1)
                days = 1;
            else
                days = day2 - day1;
        else if (year2 < year1)
            days = -1;
        else
            days = day2 - day1;

        return (int) days;
    }

    /**
     * 在指定的日期上添加一天
     * @param date
     * @return
     */
    public static Date addOneDay(Date date) {
        return addDate(DatePart.dd, 1, date);
    }

    /**
     * 在指定的日期上添加指定天数
     * @param date 需要加减年、月、日的日期对象
     * @param number 加减因子
     * @return
     */
    public static Date addDays(Date date, int number) {
        return addDate(DatePart.dd, number, date);
    }

    /**
     * 把日期对象加减年、月、日、小时、分钟,秒后得到新的日期对象
     * @param datepart 年、月、日、小时、分钟、秒
     * @param number 加减因子
     * @param date 需要加减的日期对象
     * @return 新的日期对象
     */
    public static Date addDate(DatePart datepart, int number, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (DatePart.yy == datepart) {
            cal.add(Calendar.YEAR, number);
        } else if (DatePart.MM == datepart) {
            cal.add(Calendar.MONTH, number);
        } else if (DatePart.dd == datepart) {
            cal.add(Calendar.DATE, number);
        } else if (DatePart.HH == datepart) {
            cal.add(Calendar.HOUR, number);
        } else if (DatePart.mm == datepart) {
            cal.add(Calendar.MINUTE, number);
        } else if (DatePart.ss == datepart) {
            cal.add(Calendar.SECOND, number);
        }  else {
            throw new IllegalArgumentException("addDate(DatePart, int, Date)方法参数非法: " + datepart);
        }
        return cal.getTime();
    }

    /**
     * 
     * @param dateStr 格式为yy-MM
     * @return
     * @throws ParseException
     */
    public static Date getEndDateOfMonth(String dateStr) throws ParseException {

        String dateString = dateStr + "-01";
        Date date = DateUtil.parse(dateString, DateUtil.defaultDatePatternStr);
        date = addMonth(date, 1);
        Date endDateOfDay = DateUtil.getEndOfDay(date);
        Date endDateOfMonth = DateUtil.addDays(endDateOfDay, -1);
        return endDateOfMonth;
    }

    public static Date addMonth(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + i);
        return calendar.getTime();

    }

    /**
     * 
     * @param dateStr 格式为yy-mm
     * @return
     * @throws ParseException
     */
    public static Date getBeginDateOfMonth(String dateStr) throws ParseException {

        String dateString = dateStr + "-01";
        Date date = DateUtil.parse(dateString, DateUtil.defaultDatePatternStr);
        Date beginDateOfMonth = DateUtil.getBeginOfDay(date);
        return beginDateOfMonth;
    }

    public static String getStringOfDate(Date date) {
        return getStringOfDate(date, null, null);
    }

    /**
     * 
     * @param date
     * @return
     */

    public static String getStringOfDate(Date date, String pattern, String mode) {

        Date modeDate = null;

        if (pattern == null)
            pattern = DateUtil.defaultDateTimePatternStr;
        if (mode == null)
            mode = DateUtil.defaultMode;
        if (mode.equals(DateUtil.defaultMode))
            modeDate = date;
        else if (mode.equals(DateUtil.beginOfDatefMode))
            modeDate = DateUtil.getBeginOfDay(date);
        else if (mode.equals(DateUtil.endOfDateMode))
            modeDate = DateUtil.getEndOfDay(date);
        else
            throw new IllegalArgumentException("getStringOfDate(Date date,String pattern,String mode)方法参数非法: " + mode);

        Long dateTime = modeDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String DateString = sdf.format(new Date(dateTime));
        return DateString;
    }

    /**
     * 次方法是传入任意的date,任意的格式,输出的是pattern模式的date
     * @param date
     * @param pattern
     * @param mode
     * @return
     * @throws ParseException
     */
    public static Date getDate(Date date, String pattern, String mode) throws ParseException {

        Date modeDate = null;

        if (pattern == null)
            pattern = DateUtil.defaultDateTimePatternStr;
        if (mode == null)
            mode = DateUtil.defaultMode;
        if (mode.equals(DateUtil.defaultMode))
            modeDate = date;
        else if (mode.equals(DateUtil.beginOfDatefMode))
            modeDate = DateUtil.getBeginOfDay(date);
        else if (mode.equals(DateUtil.endOfDateMode))
            modeDate = DateUtil.getEndOfDay(date);
        else
            throw new IllegalArgumentException("getStringOfDate(Date date,String pattern,String mode)方法参数非法: " + mode);

        Long dateTime = modeDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateString = sdf.format(new Date(dateTime));
        return parse(dateString, pattern);
    }

    /**
     *  判断当前时间是否在指定时间闭区间之内(忽略毫秒)
     * @param validFrom
     * @param validTo
     * @return
     */
    public static boolean isDuring(Date validFrom, Date validTo) {
        if (validFrom == null || validTo == null)
            return false;
        Date current = new Date();
        if (DateUtil.compareDateTimeIgnoreMillisecond(current, validFrom) >= 0 && DateUtil.compareDateTimeIgnoreMillisecond(current, validTo) <= 0)
            return true;
        return false;
    }

    /**
     *  判断当前时间是否在指定时间闭区间之内
     * @param validFrom
     * @param validTo
     * @return
     */
    public static boolean isDuringIgnoreTime(Date validFrom, Date validTo) {
        if (validFrom == null || validTo == null)
            return false;
        Date current = new Date();
        if (DateUtil.compareDateIgnoreTime(current, validFrom) >= 0 && DateUtil.compareDateIgnoreTime(current, validTo) <= 0)
            return true;
        return false;
    }

    /**
     *  判断某个指定时间是否在指定时间闭区间之内
     * @param validFrom
     * @param validTo
     * @return
     */
    public static boolean isDuringIgnoreTime(Date specifiedDate, Date validFrom, Date validTo) {
        if (specifiedDate == null || validFrom == null || validTo == null)
            return false;
        if (DateUtil.compareDateIgnoreTime(specifiedDate, validFrom) >= 0 && DateUtil.compareDateIgnoreTime(specifiedDate, validTo) <= 0)
            return true;
        return false;
    }

    /**
     * 获取两个日期相差的天数
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getTwoDatesInterval(Date date1, Date date2) throws ParseException {
        Date begin = null, end = null;
        if (date1 instanceof java.sql.Date) {
            begin = DateUtil.convertLongDateTimeToDate(date1);
        } else {
            begin = DateUtil.ignoreTime(date1);
        }

        if (date2 instanceof java.sql.Date) {
            end = DateUtil.convertLongDateTimeToDate(date2);
        } else {
            end = DateUtil.ignoreTime(date2);
        }

        long days = (end.getTime() - begin.getTime()) / DateUtil.DAY_MILLISECOND;
        return (int) days;
    }
    
    /**
     * 获取2个时间点之间的分钟数 或者   秒数   或者   小时数
     * datePart是要获取的时分秒的参数
     * @param date1
     * @param date2
     * @param datePart
     * @return
     * @throws ParseException
     */
    public static int getTwoDatesTimeInterval(Date date1, Date date2,DatePart datePart) throws ParseException {
    	long millsecondDate1 = date1.getTime();
    	long millsecondDate2 = date2.getTime();
    	
    	long result = millsecondDate2 - millsecondDate1;
    	if (datePart ==datePart.mm) {
    		if (result!=0) {
        		result = result/60000;
    		}
		}else if (datePart ==datePart.ss) {
    		if (result!=0) {
        		result = result/1000;
    		}
		}else if (datePart ==datePart.HH) {
    		if (result!=0) {
        		result = result/3600000;
    		}
		}
    	
    	
    	return (int)result;
    }
    /**  
    * 获取两个日历的月份之差  
    *   
    * @return
    */ 
    public static int getbetweenMonths(Date begin, Date end) 
    {  
    	Calendar calendarBirth =  Calendar.getInstance();
    	calendarBirth.setTime(begin);
    	Calendar calendarNow  =  Calendar.getInstance();
    	calendarNow.setTime(end);
    	
	    return (calendarNow.get(Calendar.YEAR) - calendarBirth  
	    .get(Calendar.YEAR))* 12+ calendarNow.get(Calendar.MONTH)  
	    - calendarBirth.get(Calendar.MONTH);  
    }
    public static void main(String arg[]) throws ParseException {
        //    	 String date = "2010/10/20";
        //    	 Date date1=  DateUtil.parse(date,"yyyy/MM/dd" );
        //    	 System.out.println(DateUtil.getStringOfDate(date1,DateUtil.defaultDateTimePatternStr,null));
//        int a = 10;
//        if (a > 1) {
//            System.out.println("a1" + a);
//
//        } else if (a > 2) {
//            System.out.println("a2" + a);
//        } else
//            System.out.println("a3" + a);

//        Date date = DateUtil.parse("2011-01-19 00:00:00:000", DateUtil.defaultLongDateTimePatternStr);
//        System.out.println(date.getTime());
//
//        date = DateUtil.parse("2011-01-23 23:59:59:999", DateUtil.defaultLongDateTimePatternStr);
//        System.out.println(date.getTime());
        
    	
    	//计算2个时间之间相差的数目
        Date date1 = new Date(); 
       
        try {
			Thread.sleep(5000);
			 Date date2 = new Date();
			 
			 System.out.println(getTwoDatesTimeInterval(date1,date2,DatePart.mm));
			 
			 System.out.println(getTwoDatesTimeInterval(date1,date2,DatePart.ss));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       
    }
    
    public static Date makeTheSameTime(Date keepDate, Date keepTime){
    	keepDate = DateUtils.setHours(keepDate, (int)DateUtils.getFragmentInHours(keepTime, Calendar.DATE));
    	keepDate = DateUtils.setMinutes(keepDate, (int)DateUtils.getFragmentInMinutes(keepTime, Calendar.HOUR_OF_DAY));
    	keepDate = DateUtils.setSeconds(keepDate, (int)DateUtils.getFragmentInSeconds(keepTime, Calendar.MINUTE));
		return keepDate;
    }
}
