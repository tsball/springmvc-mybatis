package com.springmvc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class DaoUtil {

	/**
	 * 当查询到的对象没有找到记录时，作为正常结果返回null
	 */
	@Deprecated //直接在CustomJdbcTemplate实现该辅助类的功能
	public static Map<String, Object> queryForMap(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		try {
			return jdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}// catch (IncorrectResultSizeDataAccessException e) {}
	}
	
	/**
	 * 当查询到的对象没有找到记录时，作为正常结果返回null
	 */
	@Deprecated //直接在CustomJdbcTemplate实现该辅助类的功能
	public static <T> T queryForObject(JdbcTemplate jdbcTemplate, Class<T> elementType, String sql, Object... args) {
		try {
			return jdbcTemplate.queryForObject(sql,  
	                   ParameterizedBeanPropertyRowMapper.newInstance(elementType),  
	                   args);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}// catch (IncorrectResultSizeDataAccessException e) {}
	}
	
	/**
	 * 获取列表，如果不存在，则返回空列表
	 */
	@Deprecated //直接在CustomJdbcTemplate实现该辅助类的功能
	public static List<Map<String, Object>> queryForList(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		try {
			return jdbcTemplate.queryForList(sql, args);
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<Map<String, Object>>();
		}
	}
	
	/**
	 * 获取列表，如果不存在，则返回空列表
	 */
	@Deprecated //直接在CustomJdbcTemplate实现该辅助类的功能
	public static <T> List<T> queryForList(JdbcTemplate jdbcTemplate, Class<T> elementType, String sql, Object... args) {
		try {
			return jdbcTemplate.query(sql,  
	                   ParameterizedBeanPropertyRowMapper.newInstance(elementType),  
	                   args); 
		} catch (EmptyResultDataAccessException e) {
			return new ArrayList<T>();
		}
	}
	
	public static <T> List<T> queryForValList(JdbcTemplate jdbcTemplate, String sql, Object... args) {
//		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//		List<T> targetList = new ArrayList<T>(list.size());
//		for(Map<String, Object> map : list) {
//			Object val = map.values().toArray()[0];
//			targetList.add((T) val);
//		}
//		return targetList;
		
		List<T> result = jdbcTemplate.query(sql, new SingleColumnRowMapper<T>());
		return result;
	}
	
	/**
	 * 获取select sql(只返回一行一列)语句的返回结果。
	 * 与C#的ExecuteScalar(...)对应，意为获取第一行第一列的数据，如果没有，则为null
	 */
	@Deprecated//推荐使用下方的同名方法
	public static Object queryScalar(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		return jdbcTemplate.queryForObject(sql, Object.class, args);
	}
	
	/**
	 * 获取select sql(只返回一行一列)语句的返回结果。
	 * 与C#的ExecuteScalar(...)对应，意为获取第一行第一列的数据，如果没有，则为null
	 */
	public static <T> T queryScalar(JdbcTemplate jdbcTemplate, Class<T> elementType, String sql, Object... args) {
		return jdbcTemplate.queryForObject(sql, elementType, args);
	}

	/**
	 * 获取记录数
	 * 
	 * @param sql
	 *            格式：select count(id) from table where ....
	 */
	public static long count(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		Number number = jdbcTemplate.queryForObject(sql, args, Long.class);
		return (number != null ? number.longValue() : 0);

	}
	
	/**
	 * 判断记录是否存在
	 * 
	 * @param sql
	 *            格式：select id from ... limit 1
	 */
	public static boolean isExist(JdbcTemplate jdbcTemplate, String sql, Object... args) {
		try {
			jdbcTemplate.queryForMap(sql, args);

			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}

	/**
	 * 获取表ID=>NAME的hashmap .key 为 id, value 为 name
	 * 
	 * @return
	 */
	public static Map<Integer, String> getEntriesMap(JdbcTemplate jdbcTemplate, String tableName) {
		String querySql = "select t.id, t.name from " + tableName + " t";

		List<Map<String, Object>> rowMapList = jdbcTemplate.queryForList(querySql);
		Map<Integer, String> entriesMap = new HashMap<Integer, String>();

		for (Map<String, Object> rowMap : rowMapList) {
			entriesMap.put((Integer) rowMap.get("id"), (String) rowMap.get("name"));
		}

		return entriesMap;
	}
	
	/**
	 * 保存并返回自增长的ID
	 */
	@Deprecated //直接在CustomJdbcTemplate实现该辅助类的功能
	public static Integer saveReturnGeneratedKey(JdbcTemplate jdbcTemplate, final String sql, final Object... args) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				PreparedStatementUtil.setValues(ps, args);
				return ps;
			}
		}, keyHolder);
		
		Integer id = keyHolder.getKey().intValue();
		return id;
	}
	/**
	 * 保存及更新
	 */
	@Deprecated //直接在CustomJdbcTemplate实现该辅助类的功能
	public static Integer update(JdbcTemplate jdbcTemplate, String sql, final Object... args) {
		return jdbcTemplate.update(sql, args);
	}
	
}
