package com.springmvc.service;

import java.util.List;

import com.springmvc.model.dto.UserDto;
import com.springmvc.model.po.User;

public interface IUserService extends IBasicService<User> {
	
	List<UserDto> getAll();
	
	void saveFailTest(User user);
	
}