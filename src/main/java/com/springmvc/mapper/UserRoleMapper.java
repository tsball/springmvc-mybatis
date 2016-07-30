package com.springmvc.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.springmvc.model.po.Role;
import com.springmvc.model.po.User;

public interface UserRoleMapper extends Mapper<User> {

	List<Role> getRolesByUserId(Integer userId);
	
}