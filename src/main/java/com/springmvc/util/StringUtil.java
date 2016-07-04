package com.springmvc.util;

public class StringUtil {
	
	public static boolean isEmpty(String value) {
		if(value == null || "".equals(value) || "".equals(value.trim())) {
			return true;
		}
		return false;
	}
	
}
