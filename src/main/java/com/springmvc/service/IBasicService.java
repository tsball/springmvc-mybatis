package com.springmvc.service;


public interface IBasicService<T> {
	
    /**
     * 根据ID返回指定的实体类
     * @return
     */
    public T getById(Integer id);
    
	/**
	 * 根据ID删除表中指定行记录
	 * @param id， Column id对应的值
	 * 
	 * @return affect row
	 */
	public int delete(Integer id);
	
	/**
	 * 保存对象
	 * @param saveModel
	 * 
	 * @return affect row
	 */
	public int save(T saveModel);
	
	/**
	 * 更新对象
	 * @param updateModel
	 * 
	 * @return affect row
	 */
	public int update(T updateModel);
	
}