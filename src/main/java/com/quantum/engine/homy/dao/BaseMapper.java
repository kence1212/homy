package com.quantum.engine.homy.dao;

import java.util.List;


public abstract interface BaseMapper {
	
	/**
	 * 功能：从数据库获取所有的
	 * @param <T>
	 * @return
	 */
	public <T> List<T> getAll();
	
	/**
	 * 功能：从数据库获取总个数
	 * @return
	 */
	public Long getAllCount();
	
	/**
	 *
	 * @param <T>
	 * @return
	 */
	public <T> T getById(int id);
	
	/**
	 * 功能：新增到数据库
	 * @param <T>
	 * @param obj
	 */
	public <T> void insert(T obj);
	
	/**
	 * 功能：更新到数据库
	 * @param <T>
	 * @param obj
	 */
	public <T> void update(T obj);
	
	/**
	 * 功能：删除
	 * @param id
	 */
	public void delete(int id);
	
}
