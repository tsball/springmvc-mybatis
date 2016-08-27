package com.springmvc.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.springmvc.model.po.User;

public interface UserMapper extends Mapper<User> {

	List<User> selectAll();
	
	User findById(Integer id);
	
	User findByName(String name);
	
}