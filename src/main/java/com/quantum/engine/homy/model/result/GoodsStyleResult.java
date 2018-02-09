package com.quantum.engine.homy.model.result;

import com.quantum.engine.homy.model.BizGoodsStyle;
import com.quantum.engine.homy.util.StringHelper;

public class GoodsStyleResult {
	
	private Integer id;
    private String styleName;
    private String styleCode;
    private String styleDesc;
    private String icon;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getStyleCode() {
		return styleCode;
	}
	public void setStyleCode(String styleCode) {
		this.styleCode = styleCode;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStyleDesc() {
		return styleDesc;
	}
	public void setStyleDesc(String styleDesc) {
		this.styleDesc = styleDesc;
	}
	
	public static GoodsStyleResult init(BizGoodsStyle style,String baseurl){
		if(style != null){
			GoodsStyleResult result = new GoodsStyleResult();
			if(StringHelper.isNotNull(style.getIcon())){
				result.setIcon(baseurl + style.getIcon());
			}
			result.setId(style.getId());
			result.setStyleCode(style.getStyleCode());
			result.setStyleDesc(style.getStyleDesc());
			result.setStyleName(style.getStyleName());
			return result;
		}else{
			return null;
		}
	}

}
