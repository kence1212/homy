package com.quantum.engine.homy.model.result;

import java.util.List;

/**
 * 
 * @author nicya
 * @date 2017年12月12日 下午2:50:58
 */
public class OptionList {
	
	private String  name;
	private List    list;
	public OptionList(){}
	public OptionList(String name,List list){
		this.name = name;
		this.list = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
