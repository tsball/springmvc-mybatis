package com.springmvc.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomJdbcTemplate extends JdbcTemplate {
	
	public CustomJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	/**
	 * 当查询到的对象没有找到记录时，作为正常结果返回null
	 */
	@Override
	public Map<String, Object> queryForMap(String sql, Object... args) {
		try {
			return queryForObject(sql, args, getColumnMapRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}// catch (IncorrectResultSizeDataAccessException e) {}
	}
	
	/**
	 * 当查询到的对象没有找到记录时，作为正常结果返回null
	 */
	public <T> T queryForObject(Class<T> elementType, String sql, Object... args) {
		try {
			// return queryForObject(sql,  
	        //           ParameterizedBeanPropertyRowMapper.newInstance(elementType),  
	        //           args);
			return null;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}// catch (IncorrectResultSizeDataAccessException e) {}
	}
	
	/**
	 * 获取列表，如果不存在，则返回空列表
	 */
	public List<Map<String, Object>> queryForList(String sql, Object... args) {
		try {
			return queryForList(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Map<String, Object>>();
		}
	}
	
	/**
	 * 获取列表，如果不存在，则返回空列表
	 */
	public <T> List<T> queryForList(Class<T> elementType, String sql, Object... args) {
		try {
			// return query(sql,  
	        //           ParameterizedBeanPropertyRowMapper.newInstance(elementType),  
	        //           args); 
			return null;
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<T>();
		}
	}
}
