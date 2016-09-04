package com.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.springmvc.model.form.user.UserForm;
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
	public ModelAndView show(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView(CommonConst.FRONT_TPL_PATH + "user/show");
		
		User user = userService.getUser(id);
		
		mv.addObject("user", user);
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(CommonConst.FRONT_TPL_PATH + "user/add", "user", new UserForm());
		
		return mv;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, @Valid @ModelAttribute("user") UserForm form, BindingResult result, final RedirectAttributes redirectAttr) {
		
		if (result.hasErrors()) {
			redirectAttr.addFlashAttribute("notice", result.getFieldErrors());
			return new ModelAndView(CommonConst.FRONT_TPL_PATH + "user/add");
		}
		
		// encode the password in the form
		// PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	// form.setPassword(passwordEncoder.encode(form.getPassword()));
		
		// add a user
		User user = new User();
		BeanUtils.copyProperties(form, user);
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
	public ModelAndView update(@PathVariable("id") Integer id, @Valid UserForm form, BindingResult result, final RedirectAttributes redirectAttr) {
		
		if (result.hasErrors()) {
			redirectAttr.addFlashAttribute("alert", result.getFieldErrors());
			return new ModelAndView("redirect:/user/" + id + "/edit");
		}
		
		User user = new User();
		user.setId(id);
		BeanUtils.copyProperties(form, user);
		user.setUpdateAt(DateTimeUtil.getCurrTimestamp());
		userService.update(user);
		
		redirectAttr.addFlashAttribute("notice", "更新成功!");
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
		user.setName("lily");
		user.setNickname("Lily");
		user.setPassword("password");
		user.setCreateAt(DateTimeUtil.getCurrTimestamp());
		user.setUpdateAt(DateTimeUtil.getCurrTimestamp());
		userService.insertWithThrowExceptionLater(user);
		
		return Maps.newHashMap();
	}
	
}
