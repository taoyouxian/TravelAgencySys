package cn.edu.ruc.iir.ws.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 把Long型时间转换成datatime类型
	 * 
	 * @param time
	 * @return
	 */
	public static String formatTime(Long time) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(time));
	}

	public static String formatTime(Date time) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(time);
	}
}
