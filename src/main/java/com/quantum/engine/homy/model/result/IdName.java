package com.quantum.engine.homy.model.result;

/**
 * 
 * @author nicya
 * @date 2017年12月14日 下午7:16:36
 */
public class IdName {
	
	private Integer id;
	private String  name;
	
	public IdName(){}
	public IdName(Integer id,String name){
		super();
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
