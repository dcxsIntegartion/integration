package team.union.sys_sp.httpClient;


/**
 * @ClassName: DateConverter 
 * @Description:
 * @author zhubin
 * @date Apr 21, 2016 3:05:41 PM 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * 
 * 日期转换对象
 */
public class DateConverter {
	
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式化对象.
	 */
	private static SimpleDateFormat df = new SimpleDateFormat();

	/**
	 * 日期对象转成字符串
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static String convert(Date value, String format) {
		String strDate = "";
		if (value == null) {
			return null;
		} else if ((value instanceof Date) && format != null
				&& !format.equals("")) {
			df.applyPattern(format);
			strDate = df.format(value);
		}
		return strDate;
	}
	
	public static String convert(Date value) {
		return convert(value,YYYYMMDDHHMMSS);
	}
	
	public static Date parse(String value) {
		return parse(value,YYYYMMDDHHMMSS);
	}
	
	/**
	 * 字符串对象转成时间
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static Date parse(String value, String format) {
		Date date = null;
		if (value == null) {
			return null;
		} else if ((value instanceof String) && format != null
				&& !format.equals("")) {
			df.applyPattern(format);
			try {
				date = df.parse(value);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	
	/**
	 * 计算两个日期相差多少天
	 * @param nowDate
	 * @param subDate
	 * @return
	 */
	public static Long getSubDate(Date subDate, Date nowDate) {
		long quot = 0;
		try {
			nowDate = parse(convert(nowDate,YYYY_MM_DD),YYYY_MM_DD);
			subDate = parse(convert(subDate,YYYY_MM_DD),YYYY_MM_DD);
			quot = nowDate.getTime() - subDate.getTime();
			quot = quot/1000/60/60/24;
			return quot;
		} catch(Exception e) {
			return 0L;
		}
	}
	
	/**
	 * 获得最早的时间
	 * @param date
	 * @return
	 */
	public static Date getEarlyDate(String date) {
		return getEarlyDate(parse(date, YYYY_MM_DD));
	}
	
	/**
	 * 获得最早的时间
	 * @param date
	 * @return
	 */
	public static Date getEarlyDate(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.set(java.util.Calendar.HOUR_OF_DAY, 0);
		c.set(java.util.Calendar.MINUTE, 0);
		c.set(java.util.Calendar.SECOND, 0);
		return c.getTime();
	}
	
	/**
	 * 获得最晚的时间
	 * @param date
	 * @return
	 */
	public static Date getLatestDate(String date) {
		return getLatestDate(parse(date,YYYY_MM_DD));
	}
	
	/**
	 * 获得最晚的时间
	 * @param date
	 * @return
	 */
	public static Date getLatestDate(Date date) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.set(java.util.Calendar.HOUR_OF_DAY, 23);
		c.set(java.util.Calendar.MINUTE, 59);
		c.set(java.util.Calendar.SECOND, 59);
		return c.getTime();
	}
	
}