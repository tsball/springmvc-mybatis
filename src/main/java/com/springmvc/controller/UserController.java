package com.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.springmvc.constants.CommonConst;
import com.springmvc.model.dto.UserDto;
import com.springmvc.model.po.User;
import com.springmvc.service.IUserService;
import com.springmvc.util.DateTimeUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	IUserService userService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView listAllUsers() {
		ModelAndView mv = new ModelAndView(CommonConst.FRONT_TPL_PATH + "user/index");
		
		List<UserDto> userList = userService.getAll();
		
		mv.addObject("userList", userList);
		return mv;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> show(@PathVariable("id") Integer id) {
		User user = userService.getById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(CommonConst.FRONT_TPL_PATH + "user/add");
		
		return mv;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, final RedirectAttributes redirectAttr) {
		// add a user
		User user = new User();
		user.setName("Test");
		user.setNickname("Test Nickname");
		user.setCreateAt(DateTimeUtil.getCurrTimestamp());
		user.setUpdateAt(DateTimeUtil.getCurrTimestamp());
		userService.save(user);
		
		redirectAttr.addFlashAttribute("notice", "Created success!");
		
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, @PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView(CommonConst.FRONT_TPL_PATH + "user/edit");
		
		User user = userService.getById(id);
		
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ModelAndView update(@PathVariable("id") Integer id, final RedirectAttributes redirectAttr) {
		
		//user.setUpdateAt(DateTimeUtil.getCurrTimestamp());
		//userService.update(user);
		
		redirectAttr.addFlashAttribute("notice", "Update success!");
		
		return new ModelAndView("redirect:/user");
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("id") Integer id, final RedirectAttributes redirectAttr) {
		userService.delete(id);
		
		redirectAttr.addFlashAttribute("notice", "Delete success!");
		
		return new ModelAndView("redirect:/user");
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
