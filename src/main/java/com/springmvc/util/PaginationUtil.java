package com.springmvc.util;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PaginationUtil {
	
	@SuppressWarnings("rawtypes")
	public static String getHtml(Long totalCount, Integer numPerPage, HttpServletRequest request) {
		//如果没有记录，则不分页
		if(totalCount == 0) {
			return "";
		}
		
		
		String baseUrl = "";
		Integer currPageNum = 1;
		
		StringBuffer urlBuffer = new StringBuffer();
		Map paramsMap = request.getParameterMap();
		Iterator it = paramsMap.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			
			if("p".equals(entry.getKey())) {
				if(entry.getValue() != null) {
					String[] values = (String[]) entry.getValue();
					currPageNum = Integer.parseInt(values[0]);
				}
			} else {
				String[] values = (String[]) entry.getValue();
				if(urlBuffer.length() >0) urlBuffer.append("&");
				urlBuffer.append(entry.getKey() + "=" + values[0]);
			}
		}
		
		//获取总页数
		Integer totalPages = (int) Math.ceil((double) totalCount / numPerPage);
		
		//获取需要显示的页码
		Integer currentPage = 1;
		if(!StringUtil.isEmpty(request.getParameter("p"))) {
			currentPage = Integer.parseInt(request.getParameter("p"));
		}
		int startShowPageNum = (int)(currentPage / 10) + 1;
		
		StringBuffer strBuffer = new StringBuffer();
		
		strBuffer.append("<p class='pagingation'>");
		
		strBuffer.append("<a class='first_page' href='"+ buildUrl(baseUrl, urlBuffer.toString(), 1) +"'>首页</a>");
		if(currPageNum > 1) {
			strBuffer.append("<a class='prev_page' href='"+ buildUrl(baseUrl, urlBuffer.toString(), currPageNum-1) +"'>上一页</a>");
		} else {
			strBuffer.append("<a class='prev_page disabled' href='"+ buildUrl(baseUrl, urlBuffer.toString(), 1) +"'>上一页</a>");
		}
		
		for(int i=startShowPageNum; i<startShowPageNum+10 && i<totalPages+1; i++) {
			if(currentPage == i) {
				strBuffer.append("<a class='page_shown select' href=\""+ buildUrl(baseUrl, urlBuffer.toString(), i) +"\">"+ (i) +"</a>");
			} else {
				strBuffer.append("<a class='page_shown' href=\""+ buildUrl(baseUrl, urlBuffer.toString(), i) +"\">"+ (i) +"</a>");
			}
				
		}
		
		if(currentPage < totalPages) {
			strBuffer.append("<a class='next_page' href='"+ buildUrl(baseUrl, urlBuffer.toString(), currPageNum+1) +"'>下一页</a>");
		} else {
			strBuffer.append("<a class='next_page disabled' href='"+ buildUrl(baseUrl, urlBuffer.toString(), totalPages) +"'>下一页</a>");
		}
		strBuffer.append("<a class='last_page' href='"+ buildUrl(baseUrl, urlBuffer.toString(), totalPages) +"'>尾页</a>");
		
		strBuffer.append("</p>");
		
		return strBuffer.toString();
	}
	
	private static String buildUrl(String baseUrl, String paramStr, Integer pageNum) {
		String url = null;
		if(paramStr.length() > 0) {
			url = baseUrl + "?" + paramStr.toString() + "&p=" + pageNum;
		} else {
			url = baseUrl + "?p=" + pageNum;
		}
		return url;
	}
	
}
