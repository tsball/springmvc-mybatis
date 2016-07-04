package com.springmvc.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.springmvc.util.date.DateTimeFormat;


/**
 * DateTimeUtil的几个基本原则：
 * 1. 计算主要使用Calendar
 * 2. java.util.Date 已经过时，一般使用Calendar替换。Calendar作为形参时，如果需要重新计算，一般情况需要另起一个Calendar(通过{@link Calendar#clone()})
 * 3. java.sql.Date, java.sql.Timestamp主要用户sql操作
 * 
 */
public class DateTimeUtil {

	private final static int MIN_YEAR = 1970;
	
	/********************************** 获取当前时间 ************************************/
	
	public static Timestamp getCurrTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 包含日期 + 时间部分
	 */
	public static Calendar getDateTime() {
		return Calendar.getInstance();
	}
	
	/**
	 * 不含时间部分
	 */
	public static Calendar getDate() {
		Calendar cal = Calendar.getInstance();
	    cal.setLenient(false);
		cal.set(Calendar.HOUR_OF_DAY, 0); //set 0与clear最后的getTime稍微不同
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static Date getSqlDate() {
		Calendar cal = Calendar.getInstance();
	    cal.setLenient(false);
		cal.set(Calendar.HOUR_OF_DAY, 0); //set 0与clear最后的getTime稍微不同
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTimeInMillis());
	}
	
	/**************************************** 日期 与 日期间的互转 **************************************/
	public static java.sql.Date toSqlDate(java.util.Calendar cal) {
		return new java.sql.Date(cal.getTime().getTime());
	}
	
	public static Calendar toCalendar(java.sql.Date sqlDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date(sqlDate.getTime()));
		return cal;
	}
	
	//毫秒以下的精度会丢失
	public static Calendar toCalendar(Timestamp ts) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date(ts.getTime()));
		return cal;
	}
	
	/**************************************** 日期 与 字符串互转 **************************************/
	/**
	 * 字符串转util date。字符串只包括日期部分
	 * 
	 * @param isoDateStr. 格式请参考 {@link com.bytezon.base.util.date.DateTimeFormat#DATE}
	 * 
	 * @deprecated 使用{{@link #toCalendar(String)}取代
	 */
	@Deprecated //尽量使用Calendar替换java.util.Date. Calendar更便于国际化、计算及取值
	public static java.util.Date toDate(String isoDateStr) {
		try {
			java.util.Date date = (new SimpleDateFormat(DateTimeFormat.DATE)).parse(isoDateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 字符串转sql date
	 * 
	 * @param isoDateStr. 格式请参考 {@link com.bytezon.base.util.date.DateTimeFormat#DATE}
	 */
	public static java.sql.Date toSqlDate(String isoDateStr) {
		try {
			java.util.Date utilDate = (new SimpleDateFormat(DateTimeFormat.DATE)).parse(isoDateStr);
			return new Date(utilDate.getTime());
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	/**
	 * 字符串转Calendar
	 * 
	 * @param isoDateStr. 格式请参考 {@link com.bytezon.base.util.date.DateTimeFormat#DATE}
	 */
	public static java.util.Calendar toCalendar(String isoDateStr) {
		try {
			java.util.Date date = (new SimpleDateFormat(DateTimeFormat.DATE)).parse(isoDateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 字符串转Calendar。字符串包括日期与时间部分
	 * 
	 * @param isoDateTimeStr. 格式请参考 {@link com.bytezon.base.util.date.DateTimeFormat#DATE_TIME}
	 */
	public static java.util.Calendar toDateTime(String isoDateTimeStr) {
		try {
			java.util.Date date = (new SimpleDateFormat(DateTimeFormat.DATE_TIME)).parse(isoDateTimeStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
			
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	/**
	 * 字符串转timestamp
	 * 
	 * @param tsStr. 格式参考 :
	 * 			1. {@link com.bytezon.base.util.date.DateTimeFormat#DATE_TIME} 秒级别
	 * 			3. {@link com.bytezon.base.util.date.DateTimeFormat#TIMESTAMP} 纳秒级别
	 */
	public static java.sql.Timestamp toTimestamp(String tsStr) {
		try {
			if(tsStr.length() > DateTimeFormat.DATE_TIME.length()) {
				return new Timestamp((new SimpleDateFormat(DateTimeFormat.TIMESTAMP)).parse(tsStr).getTime());
			} else {
				return new Timestamp(toDate(tsStr).getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	/**
	 * timestamp 转日期格式
	 * 
	 * @param ts.
	 * @param containNanos. 精确到纳秒
	 */
	public static String toString(java.sql.Timestamp ts, boolean containNanos) {
		SimpleDateFormat format = null;
		
		if(!containNanos) { //秒
			format = new SimpleDateFormat(DateTimeFormat.DATE);
			
		} else { //其它（纳秒）。Timestamp的toString()默认使用 JDBC 时间戳转义格式编排时间戳。yyyy-mm-dd hh:mm:ss.fffffffff，其中 ffffffffff 指示毫微秒
			format = new SimpleDateFormat(DateTimeFormat.TIMESTAMP);
			
		}
		
		return format.format(ts);
	}
	
	/**
	 * util日期转短/长日期字符
	 * 
	 * @param dateOrDateTime.
	 * @param isDateTime. 是否长日期格式。格式参考{@link com.bytezon.base.util.date.DateTimeFormat#DATE} 或  {@link com.bytezon.base.util.date.DateTimeFormat#DATE_TIME}
	 */
	public static String toString(Calendar dateOrDateTime, boolean isDateTime) {
		SimpleDateFormat format = new SimpleDateFormat(isDateTime? DateTimeFormat.DATE_TIME : DateTimeFormat.DATE);
		return format.format(dateOrDateTime.getTime());
	}
	
	public static String toString(Calendar dateOrDateTime, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(dateOrDateTime.getTime());
	}
	
	/**
	 * sql日期转字符
	 * 
	 * @param date. 格式请参考  {@link com.bytezon.base.util.date.DateTimeFormat#DATE}
	 */
	public static String toString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DateTimeFormat.DATE);
		return format.format(date);
	}
	
	/**
	 * 时间转字符
	 * 
	 * @param time. 格式请参考  {@link com.bytezon.base.util.date.DateTimeFormat#TIME}
	 */
	public static String toString(Time time) {
		SimpleDateFormat format = new SimpleDateFormat(DateTimeFormat.TIME);
		return format.format(time);
	}
	
	/**
	 * 获取指定日期所在的星期中的某一天
	 * 
	 * @param cal2
	 *            one date in the week
	 * @param targetDayOfWeek
	 *            for example: Calendar.MONDAY/GregorianCalendar.MONDAY
	 * @return
	 */
	public static Calendar getWeekDate(Calendar cal, int targetDayOfWeek, int firstDayOfWeek) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.setFirstDayOfWeek(firstDayOfWeek);
		cal2.set(GregorianCalendar.DAY_OF_WEEK, targetDayOfWeek);
		return cal2;
	}
	
	/*********************************** 按月获取日期 **********************************/
	
	/**
	 * 获取当前月份的第一天。不含时间部分
	 * 
	 */
	public static Calendar getFirstDateOfMonth(Calendar cal) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.set(GregorianCalendar.DAY_OF_MONTH, 1);
		return cal2;
	}
	
	/**
	 * 获取当前月份的第一天。不含时间部分
	 * 
	 */
	public static Calendar getMaxDateOfMonth(Calendar cal) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.set(GregorianCalendar.DAY_OF_MONTH, 1);
		cal2.add(Calendar.MONTH, 1);    //加一个月
		cal2.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
		return cal2;
	}
	
	/*********************************** 年/月/日 ***********************************/
	
	public static Time getTime(int hours, int minutes, int seconds) {
		Calendar cal = Calendar.getInstance();
		// set Date portion to January 1, 1970
		cal.set(Calendar.YEAR, MIN_YEAR);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		cal.set(Calendar.MILLISECOND, 0);
		return new java.sql.Time(cal.getTime().getTime());
	}
	
	public static Time addMinutes(Time time, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime() + minutes * 60 * 1000);
		return new java.sql.Time(cal.getTime().getTime());
	}
	
	/**
	 * 获取Time(HH:mm)
	 */
	public static Time getCurrTimeWithOutSecond() {
		Calendar cal = Calendar.getInstance();
	    // set Date portion to January 1, 1970
	    cal.set(Calendar.YEAR, 1970);
	    cal.set(Calendar.MONTH, Calendar.JANUARY);
	    cal.set(Calendar.DATE, 1);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return new java.sql.Time(cal.getTime().getTime());
	}
	
	/**
	 * 获取Time(HH:mm:ss)
	 */
	public static Time getCurrTime() {
		Calendar cal = Calendar.getInstance();
	    // set Date portion to January 1, 1970
	    cal.set(Calendar.YEAR, MIN_YEAR);
	    cal.set(Calendar.MONTH, Calendar.JANUARY);
	    cal.set(Calendar.DATE, 1);
	    cal.set(Calendar.MILLISECOND, 0);
	    return new java.sql.Time(cal.getTime().getTime());
	}
	
	/**
	 * 获取小时数
	 */
	public static int getHours(Time time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time.getTime());
		
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	
	//增加日。如果increaseDays为负数，则时间往前推移
	public static Calendar addHours(Calendar cal, int increaseHours) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.add(Calendar.HOUR, increaseHours);
		return cal2;
	}
	
	/*********************************** 年/月/日 ***********************************/
	//返回年份
	public static int getYear(Calendar cal) {
		return cal.get(Calendar.YEAR);
	}
	
	//返回月份(从1开始到12)
	public static int getMonth(Calendar cal) {
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 返回月份(从1开始到12)
	 * 
	 * @param fillLeftZero boolean. 小于10时是否补0
	 */
	public static String getMonth(Calendar cal, boolean fillLeftZero) {
		int month = getMonth(cal);
		return fillLeftZero && month < 10 ? "0" + month : "" + month;
	}
	
	//返回日
	public static int getDayOfMonth(Calendar cal) {
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 返回日。从1开始
	 * 
	 * @param fillLeftZero boolean. 小于10时是否补0
	 */
	public static String getDayOfMonth(Calendar cal, boolean fillLeftZero) {
		int dayOfMonth = getDayOfMonth(cal);
		return fillLeftZero && dayOfMonth < 10 ? "0" + dayOfMonth : "" + dayOfMonth;
	}
	
	//增加月份。如果increaseMonths为负数，则时间往前推移
	public static Calendar addMonths(Calendar cal, int increaseMonths) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.add(Calendar.MONTH, increaseMonths);
		return cal2;
	}
	
	//增加日。如果increaseDays为负数，则时间往前推移
	public static Calendar addDays(Calendar cal, int increaseDays) {
		Calendar cal2 = (Calendar) cal.clone();
		cal2.add(Calendar.DATE, increaseDays);
		return cal2;
	}
	
	/**
	 * 取得指定年月的当月总天数
	 */
	public static int getMaxDayCountInMonth(Calendar cal){
		return cal.getActualMaximum(Calendar.DATE);
	}
}