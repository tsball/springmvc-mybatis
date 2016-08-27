package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springmvc.mapper.UserMapper;
import com.springmvc.model.po.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = null;  
		  
        try {
        	User user = userMapper.findByName(username);
        	
        	// build a user, and use for validation with form submit
        	userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), true, true, true, true, getAuthorities(1));  
  
        } catch (Exception e) {  
            throw new UsernameNotFoundException("Error in retrieving user");
        }
  
        return userDetails; 
	}
	
    /** 
     * 获得访问角色权限 
     *  
     * @param access 
     * @return 
     */  
    private Collection<GrantedAuthority> getAuthorities(Integer access) {  
  
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);  
  
        // 所有的用户默认拥有ROLE_USER权限  
        // authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        // 如果参数access为1.则拥有ROLE_ADMIN权限  
        if (access.compareTo(1) == 0) {  
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));  
        }  
  
        return authList;
    }  

}
