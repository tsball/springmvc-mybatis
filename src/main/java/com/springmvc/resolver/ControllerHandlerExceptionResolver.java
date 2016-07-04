/*******************************************************************
 * Copyright (c) 2013 珠海比特讯科技有限公司. All rights reserved.
 * （1）软件的版权将受到法律保护，不允许非经授权的使用；
 * （2）未经版权所有人授权，任何人不得修改、复制和传播软件源代码、图片及其它资源等；
 * （3）未经版权所有人授权，不允许对软件进行修改；
 * （4）未经版权所有人授权，不允许在该软件的基础上开发新的软件；
 * （5）珠海比特讯科技有限公司拥有最终解释权
 *******************************************************************/
package com.springmvc.resolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.controller.admin.UserAwareController;
import com.springmvc.util.DwzUtil;
import com.springmvc.util.UploadUtil;

/**
 * 控制器异常处理
 */
public class ControllerHandlerExceptionResolver implements HandlerExceptionResolver { //extends SimpleMappingExceptionResolver {
	
	private String dwzErrorViewName;
	private String viewExceptionKey;
	
	public String getDwzErrorViewName() {
		return dwzErrorViewName;
	}

	public void setDwzErrorViewName(String dwzErrorViewName) {
		this.dwzErrorViewName = dwzErrorViewName;
	}

	public String getViewExceptionKey() {
		return viewExceptionKey;
	}

	public void setViewExceptionKey(String viewExceptionKey) {
		this.viewExceptionKey = viewExceptionKey;
	}


	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			
			if(handlerMethod.getBean() instanceof UserAwareController) { //dwz后台页面请求异常(需验证登录部分UserAwareController都使用dwz异常处理 )
				if(handlerMethod.getMethod().getReturnType() == ModelAndView.class) { //View (可有都返回Json的形式)
					ModelAndView mv = new ModelAndView(dwzErrorViewName);
					
					mv.addObject(viewExceptionKey, ex);
					return mv;
					
				} else { //Json
					Map<String, Object> map = new HashMap<String, Object>();
					map.putAll(DwzUtil.buildFailResult(ex.getMessage()));
					ex.printStackTrace();
					try {
						UploadUtil.printJsonInfo(response, map);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return new ModelAndView();
					
				}
				
			} else { //其他页面请求异常
				return null;
				
			}
		}
		
		return null;
	}
	
}
