package com._520.crowdfunding.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 获取格式化好的当前时间
 */
public class AppDateUtils {

	/**
	 * 	默认的时间格式
	 * @return	形如：2020-01-10 14:08:19
	 */
	public static String getFormatTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 	返回自定义格式的时间
	 * @param pattern	yyyy-MM-dd HH:mm:ss
	 */
	public static String getFormatTime(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * 	返回指定时间的格式化后的字符串
	 * @param pattern	yyyy-MM-dd HH:mm:ss
	 * @param localDateTime		LocalDateTime
	 */
	public static String getFormatTime(String pattern, LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static void main(String[] args) {
		System.out.println(getFormatTime());
	}
}
