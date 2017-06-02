package team.union.nonbusiness.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String yyMMdd = "yy-MM-dd";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String HHmmss = "HH:mm:ss";
    public static final String HHmm = "HH:mm";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static final String yyMMddHHmmss = "yy-MM-dd HH:mm:ss";

    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd）。
     */
    public final static String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";

    /**
     * Java能支持的最小日期字符串（yyyy-MM-dd HH:mm:ss:SS）。
     */
    public final static String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";

    /**
     * Java能支持的最小的Timestamp。
     */
    public final static Timestamp JAVA_MIN_TIMESTAMP = convertStrToTimestamp(JAVA_MIN_LONG_DATE_STR);

    /**
     * 把字符串转换为Timestamp类型，对于短日期格式，自动把时间设为系统当前时间。
     * @return Timestamp
     * @see #convertStrToTimestamp(String,boolean)
     */
    public static Timestamp convertStrToTimestamp(String dateStr) {
        return convertStrToTimestamp(dateStr, false);
    }

    /**
     * 把字符串转换为Timestamp类型，对于短日期格式，自动时间设为0。
     * @return Timestamp
     * @see #convertStrToTimestamp(String,boolean)
     */
    public static Timestamp convertStrToTimestampZero(String dateStr) {
        return convertStrToTimestamp(dateStr, true);
    }

    public static String nDayAfter() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 2, 23, 59, 59);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String mydate = simpleDateFormat.format(cal.getTime());
        return mydate;

    }

    public static String tomorrowDayAfter() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1, 23, 59, 59);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String mydate = simpleDateFormat.format(cal.getTime());
        return mydate;

    }

    public static String dafTomorrowDayAfter() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 3, 23, 59, 59);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String mydate = simpleDateFormat.format(cal.getTime());
        return mydate;

    }

    public static String currentDate() {
        return getCurrDateStr(yyyyMMddHHmmss);
    }

    /**
     * 把字符串转换为Timestamp类型。
     * @param dateStr - 日期字符串，只支持"yyyy-MM-dd"和"yyyy-MM-dd HH:mm:ss:SS"两种格式。
     * 如果为"yyyy-MM-dd"，系统会自动取得当前时间补上。
     * @param addZeroTime - 当日期字符串为"yyyy-MM-dd"这样的格式时，addZeroTime为true表示
     * 用0来设置HH:mm:ss:SS，否则用当前Time来设置。
     * @return Timestamp
     */
    private static Timestamp convertStrToTimestamp(String dateStr, boolean addZeroTime) {
        if (dateStr == null) {
            return null;
        }

        String dStr = dateStr.trim();
        if (dStr.indexOf(" ") == -1) {
            if (addZeroTime) {
                dStr = dStr + " 00:00:00:00";
            } else {
                dStr = dStr + " " + getCurrDateStr(DateUtil.HHmmss);
            }
        }

        Date utilDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(yyyyMMddHHmmss);

        try {
            utilDate = simpleDateFormat.parse(dStr);
        } catch (Exception ex) {
            throw new RuntimeException("DateUtil.convertStrToTimestamp(): " + ex.getMessage());
        }

        return new Timestamp(utilDate.getTime());
    }

    /**
     * 得到系统当前时间的Timestamp对象
     * @return  系统当前时间的Timestamp对象
     */
    public static Timestamp getCurrTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * <p>
     * 取得当前日期，并将其转换成格式为"dateFormat"的字符串
     * 例子：假如当前日期是 2003-09-24 9:19:10，则：
     * <pre>
     * getCurrDateStr("yyyyMMdd")="20030924"
     * getCurrDateStr("yyyy-MM-dd")="2003-09-24"
     * getCurrDateStr("yyyy-MM-dd HH:mm:ss")="2003-09-24 09:19:10"
     * </pre>
     * </p>
     * @param dateFormat String 日期格式字符串
     * @return String
     */
    public static String getCurrDateStr(String dateFormat) {
        return convertDateToStr(new Date(), dateFormat);
    }

    /**
     * 将日期类型转换成指定格式的日期字符串
     * @param date 待转换的日期
     * @param dateFormat 日期格式字符串
     * @return String
     */
    public static String convertDateToStr(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * 将指定格式的字符串转换成星期
     * @param dateFormat 待转换的日期字符串的日期格式字符串
     * @return Date
     */
    public static String convertStrToWeek(String dateStr, String dateFormat) {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        }
        return convertDateToStr(convertStrToDate(dateStr, dateFormat), "E");
    }

    /**
     * 将指定格式的字符串转换成日期类型
     * @param dateFormat 日期格式字符串
     * @return Date
     */
    public static Date convertStrToDate(String dateStr, String dateFormat) {
        if (dateStr == null || dateStr.equals("")) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("DateUtil.convertStrToDate():" + e.getMessage());
        }
    }

    public static java.sql.Date convertStrToSqlDate(String s) {
        return convertToSqlDate(convertStrToDate(s, yyyyMMdd));
    }

    /**
     * 把java.util.Date转换为java.sql.Date。
     * @param date
     * @return
     */
    public static java.sql.Date convertToSqlDate(Date date) {
        if (date == null) {
            return null;
        }

        String dateStr = convertDateToStr(date, yyyyMMdd);
        return java.sql.Date.valueOf(dateStr);
    }

    public static java.sql.Date convertToSqlDate() {
        Date date = new Date();
        String dateStr = convertDateToStr(date, yyyyMMdd);
        return java.sql.Date.valueOf(dateStr);
    }

    /**
     * 计算两个日期之间的相隔的年、月、日。注意：只有计算相隔天数是准确的，相隔年和月都是
     * 近似值，按一年365天，一月30天计算，忽略闰年和闰月的差别。
     * @param datepart 两位的格式字符串，yy表示年，MM表示月，dd表示日
     * @param startdate 开始日期
     * @param enddate 结束日期
     * @return double 如果enddate>startdate，返回一个大于0的实数，否则返回一个小于等于0的实数
     */
    public static double dateDiff(String datepart, Date startdate, Date enddate) {
        if (datepart == null || datepart.equals("")) {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
        }

        double distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(enddate.getTime() - startdate.getTime());
        if (datepart.equals("yy")) {
            distance = distance / 365;
        } else if (datepart.equals("MM")) {
            distance = distance / 30;
        } else if (datepart.equals("dd")) {
            distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);
        } else if (datepart.equals("ss")) { // 得到秒
            distance = (enddate.getTime() - startdate.getTime()) / (1000);
        } else if (datepart.equals("mm")) {
            distance = (enddate.getTime() - startdate.getTime()) / (1000) / 60.0;
        } else {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
        }
        return distance;
    }

    /**
     * 计算两个日期之间的相隔的天数（忽略时分秒和毫秒）
     * @param startdate 开始日期
     * @param enddate 结束日期
     * @return long 返回正整数
     */
    public static long dateDiffForDay(Date startdate, Date enddate) {
        startdate = convertStrToDate(convertDateToStr(startdate, yyyyMMdd), yyyyMMdd);
        enddate = convertStrToDate(convertDateToStr(enddate, yyyyMMdd), yyyyMMdd);
        long distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);
        return Math.abs(distance);
    }

    public static double dateDiff(String datepart, Timestamp startdate, Timestamp enddate) {
        if (datepart == null || datepart.equals("")) {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
        }

        double distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(enddate.getTime() - startdate.getTime());
        if (datepart.equals("yy")) {
            distance = distance / 365;
        } else if (datepart.equals("MM")) {
            distance = distance / 30;
        } else if (datepart.equals("dd")) {
            distance = (enddate.getTime() - startdate.getTime()) / (60 * 60 * 24 * 1000);
        } else if ("hh".equals(datepart)) {
            distance = (enddate.getTime() - startdate.getTime()) * 1.0 / (1000 * 60 * 60);
        } else if (datepart.equals("ss")) { // 得到秒
            distance = (enddate.getTime() - startdate.getTime()) / (1000);
        } else if (datepart.equals("mm")) {
            distance = (enddate.getTime() - startdate.getTime()) / (1000) / 60.0;
        } else {
            throw new IllegalArgumentException("DateUtil.dateDiff()方法非法参数值：" + datepart);
        }
        return distance;
    }

    public static int getCurrentWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 取当前日期的年份里面的周数
        int currentWeekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
        return currentWeekOfYear;
    }

    /**
     * 把日期对象加减年、月、日后得到新的日期对象
     * @param number 加减因子
     * @param date 需要加减年、月、日的日期对象
     * @return Date 新的日期对象
     */
    public static Date addDate(String datepart, int number, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (datepart.equals("yy")) {
            cal.add(Calendar.YEAR, number);
        } else if (datepart.equals("MM")) {
            cal.add(Calendar.MONTH, number);
        } else if (datepart.equals("dd")) {
            cal.add(Calendar.DATE, number);
        } else if (datepart.equals("hh")) {
            cal.add(Calendar.HOUR_OF_DAY, number);
        } else if (datepart.equals("mm")) {
            cal.add(Calendar.MINUTE, number);
        } else {
            throw new IllegalArgumentException("DateUtil.addDate()方法非法参数值：" + datepart);
        }

        return cal.getTime();
    }

    // 取当天最后时刻
    public static Date todayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return cal.getTime();
    }

    // 取前一天最后时刻
    public static Date yestodayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 1, 23, 59, 59);
        return cal.getTime();
    }
    
    // 取前一天开始时刻
    public static Date yestodayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 2,  23, 59, 59);
        return cal.getTime();
    }
    
    // 取前一天开始时刻
    public static Date yestodayDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 1);
        return cal.getTime();
    }
    

    /**
     * 时间转换为下一天的yyyymmdd格式
     * @return
     * @author nickey
     */
    public static String convertToNextDay() {
    	Date date = new Date(System.currentTimeMillis()-1000*3600*24);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
    }

    /**
	 * 校验日期   格式不对就抛出异常
	 * @param date
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date checkDate(String date,String format) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        Date d = df.parse(date);
        if(!date.equals(df.format(d))){
        	throw new Exception("日期格式错误");
        }
        return d;
	}
	
	/** 
	* 获得指定日期的前9天 
	* @param specifiedDay 
	* @return 
	* @throws Exception 
	*/ 
	public static String getSpecifiedDayBefore(String specifiedDay){ 
	
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day-9);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	} 

	/**
	 * 获取当月的第一天
	 * @return
	 * @author	yinyao
	 * @Date	2016-1-3 下午3:12:28
	 */
	public static String getMonthFirstDay(String formatStr){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String datestr = format.format(cal.getTime()).toString();
		return datestr;
	}
	
    /**
     * 测试
     * @param args
     */
    public static void main(String args[]) {
    	String dateString="2014-12-10";
        System.out.println(getCurrTimestamp());
        System.out.println(convertStrToDate("2014-12-10 15:30:00","yyyy-MM-dd HH:mm").getTime());
        System.out.println(convertStrToDate("2014-12-10 15:31:15","yyyy-MM-dd HH:mm").getTime());
    }

}
