package com.springmvc.util;

public class NumericUtil {
	
	public static final Integer ZERO_INT = Integer.parseInt("0");
	
	//Integer的默认值 或者 0
	public static boolean isZeroOrNull(Integer num) {
		return num == null || ZERO_INT.equals(num);
	}
	
}
