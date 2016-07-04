package com.springmvc.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;

public class PreparedStatementUtil {
	
	public static void setValues(PreparedStatement ps, Object... args) throws SQLException {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				doSetValue(ps, i + 1, arg);
			}
		}
	}
	
	public static void setArrayValues(PreparedStatement ps, Object[] args) throws SQLException {
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				Object arg = args[i];
				doSetValue(ps, i + 1, arg);
			}
		}
	}
	
	/**
	 * Set the value for prepared statements specified parameter index using the passed in value.
	 * This method can be overridden by sub-classes if needed.
	 * @param ps the PreparedStatement
	 * @param parameterPosition index of the parameter position
	 * @param argValue the value to set
	 * @throws SQLException
	 */
	private static void doSetValue(PreparedStatement ps, int parameterPosition, Object argValue) throws SQLException {
		if (argValue instanceof SqlParameterValue) {
			SqlParameterValue paramValue = (SqlParameterValue) argValue;
			StatementCreatorUtils.setParameterValue(ps, parameterPosition, paramValue, paramValue.getValue());
		}
		else {
			StatementCreatorUtils.setParameterValue(ps, parameterPosition, SqlTypeValue.TYPE_UNKNOWN, argValue);
		}
	}
	
}
