package com.oranth.applicationmarket.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Encoder;

public class DateUtil {

//	private static Logger log = Logger.getLogger(Tool.class);

	/**
	 * 获取time
	 *
	 * @return
	 */
	public static synchronized Long getTime() {
		Date d = new Date();
		return d.getTime();
	}

	/**
	 *
	 * @return
	 */
	public static synchronized String getyyyyMMddHHmmssSSSSS(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
		return sdf.format(d);
	}

	//获取带分时间
	public static String getyyyyMMddHHmm(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(d);
	}
	//获取带毫秒时间
	public static String getyyyyMMddHHmmss(){
		Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	//获取带毫秒时间戳
	public static  String getyyyyMMddHHmmssSSS(){
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			return sdf.format(d);
	}
	//获取日期
	public static String getyyyyMMdd(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(d);
	}
	//获取日期
	public static String getyyyy_MM_dd(){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}
	
	//前一天
	public static String getq_yyyy_MM_dd(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l=System.currentTimeMillis()-1000l*60*60*24;
		return sdf.format(l);
	}
	
	//前一月
	public static String getqy_yyyy_MM_dd(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l=System.currentTimeMillis()-(1000l*60*60*24*30);
		return sdf.format(l);
	}
	//前一周
	public static String getq_zy_yyyy_MM_dd(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long l=System.currentTimeMillis()-1000l*60*60*24*7;
		return sdf.format(l);
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	//获取uuid
	public static String getUUID()
	  {
	    String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
	    return uuid;
	  }

	/**
	 * 当周周一
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getMonday(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
		String weekhand = sdf.format(cal.getTime());
		return weekhand;
	}

	/**
	 * 每月一号
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getMonthDayMin(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.DAY_OF_MONTH, 1);
		String weekhand = sdf.format(cal.getTime());
		return weekhand;
	}
	/**
	 * 每月最后一天
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getMonthDayMax(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		int maxCurrentMonthDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);//当前月的天数
		cal.set(cal.DAY_OF_MONTH, maxCurrentMonthDay);
		String weekhand = sdf.format(cal.getTime());
		return weekhand;
	}
//DAY_OF_YEAR


	/**
	 * 每年一天
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getYearDayMin(String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.DAY_OF_YEAR,1);
		String weekhand = sdf.format(cal.getTime());
		return weekhand;
	}
}
