package com.springmvc.util;

import java.util.List;

import org.springframework.validation.ObjectError;

public class SpringFormUtil {
	
	public static String getErrorUlLiHtml(List<ObjectError> errorList) {
		StringBuffer sbfError = new StringBuffer();
		sbfError.append("<ul class=\"error\">");
		for (ObjectError error : errorList) {
			sbfError.append("<li>").append(error.getDefaultMessage())
					.append("</li>");
		}
		sbfError.append("</ul>");
		return sbfError.toString();
	}

}
