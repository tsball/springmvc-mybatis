package com.springmvc.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.constants.CommonConst;
import com.springmvc.service.IUserService;

@Controller("adminUserController")
@RequestMapping("/admin/user")
public class UserController extends UserAwareController {
	
	@Resource 
	IUserService userService;
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(CommonConst.ADMIN_TEMPLATE_PREFIX + "user/index");
		
		return mv;
	}
	
}
