package com.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.springmvc.constants.CommonConst;
import com.springmvc.model.dto.UserDto;
import com.springmvc.model.po.User;
import com.springmvc.service.IUserService;
import com.springmvc.util.DateTimeUtil;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Resource
	IUserService userService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView(CommonConst.FRONT_TEMPLATE_PREFIX + "home/index");
		
		List<UserDto> userList = userService.getAll();
		
		mv.addObject("userList", userList);
		return mv;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) {
		// add a user
		User user = new User();
		user.setName("Test");
		user.setNickname("Test Nickname");
		user.setCreateAt(DateTimeUtil.getCurrTimestamp());
		user.setUpdateAt(DateTimeUtil.getCurrTimestamp());
		userService.save(user);
		
		return Maps.newHashMap();
	}
	
	@RequestMapping("/save-fail-test")
	@ResponseBody
	public Map<String, Object> saveFailTest(HttpServletRequest request) throws Exception {
		// add a user
		User user = new User();
		user.setName("Test");
		user.setNickname("Test Nickname");
		user.setCreateAt(DateTimeUtil.getCurrTimestamp());
		user.setUpdateAt(DateTimeUtil.getCurrTimestamp());
		userService.saveFailTest(user);
		
		return Maps.newHashMap();
	}
	
}