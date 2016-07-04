package com.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.springmvc.constants.CommonConst;
import com.springmvc.constants.SessionConst;
import com.springmvc.controller.admin.UserAwareController;
import com.springmvc.model.po.User;

public class UserAwareInterceptor extends HandlerInterceptorAdapter {
	
	private String timeoutViewName;
	
	public void setTimeoutViewName(String userLoginViewName) {
		this.timeoutViewName = userLoginViewName;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object handler)
			throws Exception {
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			
			if(handlerMethod.getBean() instanceof UserAwareController) {
				User loginTeacher = (User)httpServletRequest.getSession().getAttribute(SessionConst.SESS_LOGIN_USER);
				
				if(loginTeacher == null) {
					if(handlerMethod.getBean() instanceof com.springmvc.controller.admin.HomeController) {
						httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login/");
					} else {
						httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/" + timeoutViewName + CommonConst.URL_SPRING_SUFFIX);
					}
				}
			}
		}
		
		return super.preHandle(httpServletRequest, httpServletResponse, handler);
	}
}
