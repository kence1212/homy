package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class UserAddrListDto extends GetDto{

	private Integer page;
	private Integer pageSize;
	private Integer userId;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public ClientData toData(){
		ClientData cd = new ClientData();
		cd.setValue("token", super.getToken());
		cd.setValue("timestamp", super.getTimestamp());
		cd.setValue("sign", super.getSign());
		cd.setValue("page", page);
		cd.setValue("pageSize", pageSize);
		cd.setValue("userId", userId);
		
		return cd;
	}

	@Override
	public Result validate() {
		Result result = new Result();
		
		if(page == null || page < 0){
			result.wrong("40000" , "页数不能为空");
			return result;
		}
		
		if(pageSize == null || pageSize <= 0){
			result.wrong("40000" , "每页记录数不能为空");
			return result;
		}
		if(StringHelper.isNull(getToken())){
			result.wrong("40000" , "token不能为空");
			return result;
		}
		if(StringHelper.isNull(getTimestamp())){
			result.wrong("40000" , "时间戳不能为空");
			return result;
		}
		if(StringHelper.isNull(getSign())){
			result.wrong("40000" , "签名不能为空");
			return result;
		}
		String sign = this.toData()
				.makeSign();
		if(!sign.equals(this.getSign())){
			result.wrong("40000" , "签名错误");
			return result;
		}
		return result;
	}
	
}
