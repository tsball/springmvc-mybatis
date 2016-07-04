package com.springmvc.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class BaseModel {
	
	public static final Integer STATE_ENABLED = 1;
	public static final Integer STATE_DISABLED = 0;
	
	private static Map<Integer, String> statesMap;
	
	public static final  Map<Integer, String> getStatesMap() {
		if(statesMap == null) {
			statesMap = new HashMap<Integer, String>();
			statesMap.put(STATE_ENABLED, "开放");
			statesMap.put(STATE_DISABLED, "禁用");
		}
		return statesMap;
	}
	
	private Integer id;
	private Integer state;
	private Timestamp updatedAt;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
