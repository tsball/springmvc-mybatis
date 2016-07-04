package com.springmvc.util;

import java.util.HashMap;
import java.util.Map;

public class DwzUtil {
	
	/**
	 * 返回成功结果给dwz框架
	 * 
	 * @param
	 * message String 成功后弹出的信息
	 * navTabId String 刷新该tab页面，如果为空，则刷新当前页面
	 * isCloseCurrent boolean 是否刷新当前页面
	 */
	public static Map<String, Object> buildSuccessResult(String message, String navTabId, boolean isCloseCurrent) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("statusCode", "200");
		resultMap.put("message", message);
		resultMap.put("callbackType", isCloseCurrent? "closeCurrent" : "forward");
		resultMap.put("forwardUrl", "");
		resultMap.put("navTabId", navTabId);
		return resultMap;
	}
	
	public static Map<String, Object> buildFailResult(String message) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("statusCode", "300");
		resultMap.put("message", message);
		return resultMap;
	}
	
	public static Map<String, Object> buildTimeoutResult() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("statusCode", "301");
		resultMap.put("message", "网站访问超时");
		return resultMap;
	}
	
}
