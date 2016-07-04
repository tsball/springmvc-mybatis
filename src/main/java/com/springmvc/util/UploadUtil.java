package com.springmvc.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadUtil {
	
	public static String RELATIVE_PATH_PREFIX = "upload/";
	public static String PATH_SEPARATOR = "/";
	
	/**
	 * 获取上传文件的基本绝对路径
	 */
	public static String getBaseAbsolutePath(HttpServletRequest request) {
		String absolutePath = request.getSession().getServletContext().getRealPath(PATH_SEPARATOR) + RELATIVE_PATH_PREFIX;
		return absolutePath;
	}
	
	/**
	 * 根据当前时间，分配到对应的路径
	 */
	public static String getDatePath() {
		Calendar calendar = Calendar.getInstance();       
        int year = calendar.get(Calendar.YEAR);        
        int month = calendar.get(Calendar.MONTH) + 1;
		return year + PATH_SEPARATOR + month + PATH_SEPARATOR;
	}
	
	/**
	 * 根据当前时间，分配到对应的路径(线程安全)
	 */
	public static String getMillDatetimePath(Timestamp datetime) {    
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/ddHHmmssSSS/");
		String dateString = sFormat.format(datetime);
		return dateString;
	}
	
	/**
	 * 获取文件名
	 * @return
	 */
	public synchronized static String getRandFileName(String extend) {
		//UUID.randomUUID()//36位，过长
		// 获得当前时间
		DateFormat format = new SimpleDateFormat("ddHHmmssSSS");
		return format.format(DateTimeUtil.getCurrTimestamp()) + "." + extend;
	}
	
	/**
	 * 获取在页面显示时的完整路径
	 * @param request
	 * @param relativePath， 文件的相对路径（不包含）
	 * @return
	 */
	public static String getDisplayPath(HttpServletRequest request, String relativePath) {
		return request.getContextPath() + PATH_SEPARATOR + RELATIVE_PATH_PREFIX + relativePath;
	}
	
	/**
	 * 获取文件后缀
	 * @return
	 */
	public static String getFileExt(String fileName) {
		String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
		return extensionName;
	}
	
	/**
	 * 最终返回json格式的字符串（是text/html格式，并非text/json格式，不能直接返回@ResponseBody Map取代）
	 * @param response
	 * @param map
	 * @throws IOException
	 */
    public static void printInfo(HttpServletResponse response, Map<String, Object> map) throws IOException {
    	response.setContentType("text/html; charset=UTF-8");  
        response.setHeader("Cache-Control", "no-cache");
        
        PrintWriter out = response.getWriter();
        out.println(JsonUtil.toJsonString(map));  
        out.flush();  
        out.close();
    }
    
    /**
	 * 最终返回json格式的字符串（非text/json格式）
	 * @param response
	 * @param map
	 * @throws IOException
	 */
    public static void printJsonInfo(HttpServletResponse response, Map<String, Object> map) throws IOException {
    	response.setContentType("text/json; charset=UTF-8");  
        response.setHeader("Cache-Control", "no-cache");
        
        PrintWriter out = response.getWriter();
        out.println(JsonUtil.toJsonString(map));  
        out.flush();  
        out.close();
    }
	
}
