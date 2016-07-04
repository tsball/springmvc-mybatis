package com.springmvc.util;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class SpringJdbcUtil {
	
	public static BasicDataSource getDataSource(String serverIp, String dbName,
			String dbUserName, String dbPassword) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + serverIp + ":3306/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername(dbUserName);
		dataSource.setPassword(dbPassword);

		return dataSource;
	}

	public static JdbcTemplate getJdbcTemplate(String serverIp, String dbName,
			String dbUserName, String dbPassword) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + serverIp + ":3306/" + dbName
				+ "?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername(dbUserName);
		dataSource.setPassword(dbPassword);

		return new JdbcTemplate(dataSource);
	}

	public static JdbcTemplate getJdbcTemplate(DataSource datasource) {
		return new JdbcTemplate(datasource);
	}
	
}
