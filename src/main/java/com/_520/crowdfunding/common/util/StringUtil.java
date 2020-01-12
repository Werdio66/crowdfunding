package com._520.crowdfunding.common.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static boolean isEmpty(String s) {
		// s == null | s.equals(""); //位与,逻辑与区别,非空字符串放置在前面,避免空指针
		return s == null || "".equals(s);
	}

	public static boolean isNotEmpty(String s) {
		return !isEmpty(s); 
	}


	/**
	 * 将字符串以指定分割符分开，并转换为int类型加到list中
	 *
	 * @param str   分割的字符串
	 * @param split 分隔符
	 * @return int类型的集合
	 */
	public static List<Integer> getIntegerListByStr(String str, String split) {
		List<Integer> list = new ArrayList<>();
		String[] strs = str.split(split);
		for (String s : strs) {
			list.add(Integer.valueOf(s));
		}

		return list;
	}
}
