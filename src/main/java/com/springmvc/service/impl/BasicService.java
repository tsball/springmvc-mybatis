package com.springmvc.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import tk.mybatis.mapper.common.Mapper;

import com.springmvc.service.IBasicService;
import com.springmvc.util.CamelCaseUtil;
import com.springmvc.util.SpringContextHolder;

/**
 * 如需使用BasicService的基本业务方法，则要保证存在以泛型开头的Dao类，
 * 如GoodsService extends BasicService<Brand>,则要保证BrandDao存在才行
 * 
 * @param <T> 对应的表模型对象
 */
public abstract class BasicService<T> implements IBasicService<T> {
	
	private static final String SUFFIX_MAPPER_CLASS = "Mapper";
	
	private Mapper<T> basicDao;
	
	@SuppressWarnings("unchecked")
	public Mapper<T> getBasicMapper() {
		if(this.basicDao == null) {
			Type genType = getClass().getGenericSuperclass();  
	        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
	        
	        Class<T> entityClass = (Class<T>) params[0]; //实体类型
	        String daoName = CamelCaseUtil.toCamelCase(entityClass.getSimpleName()) + SUFFIX_MAPPER_CLASS;
	        
	        this.basicDao = SpringContextHolder.getBean(daoName);
		}
		
		return this.basicDao;
	}
	
    /**
     * 根据ID返回指定的实体类
     * @return
     */
    public T getById(Integer id) {
    	return getBasicMapper().selectByPrimaryKey(id);
    }
    
	/**
	 * 根据ID删除表中指定行记录
	 * @param id， Column id对应的值
	 * 
	 * @return affect row
	 */
	public int delete(Integer id) {
		return getBasicMapper().deleteByPrimaryKey(id);
	}
	
	/**
	 * 保存对象
	 * @param saveModel
	 * 
	 * @return affect row
	 */
	public int save(T saveModel) {
		return getBasicMapper().insertSelective(saveModel);
	}
	
	/**
	 * 更新对象
	 * @param updateModel
	 * 
	 * @return affect row
	 */
	public int update(T updateModel) {
		return getBasicMapper().updateByPrimaryKeySelective(updateModel);
	}
	
}
