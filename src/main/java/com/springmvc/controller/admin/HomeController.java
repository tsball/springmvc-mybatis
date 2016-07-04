package com.springmvc.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.constants.CommonConst;

@Controller("adminHome")
@RequestMapping("/admin/home")
public class HomeController extends UserAwareController {
	
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(CommonConst.ADMIN_TEMPLATE_PREFIX + "home/index");
	}
	
}