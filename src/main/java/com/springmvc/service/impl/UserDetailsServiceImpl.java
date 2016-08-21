package com.springmvc.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = null;  
		  
        try {  
        	// get user by username from db
        	String userName = "admin";
        	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        	String hashedPassword = passwordEncoder.encode("admin");
        	
        	// build a user, and use for validation with form submit
        	user = new User(userName, hashedPassword, true, true, true, true, getAuthorities(1));  
  
        } catch (Exception e) {  
            throw new UsernameNotFoundException("Error in retrieving user");  
        }  
  
        return user; 
	}
	
    /** 
     * 获得访问角色权限 
     *  
     * @param access 
     * @return 
     */  
    public Collection<GrantedAuthority> getAuthorities(Integer access) {  
  
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
