package com.springmvc.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	public static String toJsonString(Map<String, Object> jsonMap) {
		JSONObject json = JSONObject.fromObject(jsonMap);
		return json.toString();
	}
	
	public static String toJsonString(Object jsonMap) {
		JSONObject json = JSONObject.fromObject(jsonMap);
		return json.toString();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T toJsonObject(String jsonString, Class<T> beanClazz) {
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObj, beanClazz); 
	}
	
	public static <T> T json2Bean(String json, Class<T> valueType) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//mapper.setAnnotationIntrospector(new JaxbAnnotationIntrospector(TypeFactory.defaultInstance()));
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String bean2Json(Object bean) {
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();  
		JsonGenerator gen;
		try {
			gen = new JsonFactory().createJsonGenerator(sw);
			mapper.writeValue(gen, bean);  
			gen.close();  
			return sw.toString(); 
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
     * 封装将json对象转换为java集合对象
     * 
     * @param <T>
     * @param clazz
     * @param jsons 
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> List<T> toList(String jsons, Class<T> beanClazz) {
        List<T> javaObjList = null;
        JSONArray jsonArray = JSONArray.fromObject(jsons);
        if(jsonArray!=null){
			javaObjList = new ArrayList<T>();
            
            for(int i=0; i<jsonArray.size(); i++){
            	Object jsonObj = jsonArray.get(i);
            	
            	javaObjList.add((T) jsonObj);
            }
        }
        return javaObjList;
    }
	
}
