package com.quantum.engine.homy.model.result;

import java.io.Serializable;

public class Option implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String attrKey;
	private Object attrVal;
	
	public Option(){}
	
	public Option(Object value){
		this.attrVal = value;
	}
	
	public Option(String key,Object value){
		this.attrKey = key;
		this.attrVal = value;
	}
	
	public String getAttrKey() {
		return attrKey;
	}
	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}
	public Object getAttrVal() {
		return attrVal;
	}
	public void setAttrVal(Object attrVal) {
		this.attrVal = attrVal;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

}
