package com.springmvc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.springmvc.mapper.UserMapper;
import com.springmvc.model.dto.UserDto;
import com.springmvc.model.po.User;
import com.springmvc.service.IUserService;

@Service
public class UserService extends BasicService<User> implements IUserService {
	
	@Resource
	UserMapper userMapper;
	
	@Override
	public List<UserDto> getAll() {
		List<User> list = userMapper.selectAll();
		
		List<UserDto> userDtoList = Lists.transform(list, new Function<User, UserDto>(){
			@Override
			public UserDto apply(User input) {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(input, userDto);
				return userDto;
			}
		});
		return userDtoList;
	}
	
	@Override
	public User getUser(Integer userId) {
		return userMapper.findById(userId);
	}

	@Override
	@Transactional
	public void insertWithThrowExceptionLater(User user) {
		userMapper.insertSelective(user);
		throw new RuntimeException("Fail to save user "+ user.getName() +", should rollback the db transaction!!!");
	}
	
}

