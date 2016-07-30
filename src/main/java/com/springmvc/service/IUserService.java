package com.springmvc.service;

import java.util.List;

import com.springmvc.model.dto.UserDto;
import com.springmvc.model.po.User;

public interface IUserService extends IBasicService<User> {
	
	List<UserDto> getAll();
	
	User getUser(Integer userId);
	
	void saveFailTest(User user);
	
}