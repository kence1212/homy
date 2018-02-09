package com.quantum.engine.homy.model.dto;

import com.quantum.engine.homy.clientdata.ClientData;
import com.quantum.engine.homy.model.result.Result;
import com.quantum.engine.homy.util.StringHelper;

public class ReviewDto {

	private Integer goodsItemId; // 订单明细Id
	private Integer score;
	private String content;
	private Integer unShowName; // 0不显示 1显示
	private Integer userId;
	private String token;
	private String timestamp;
	private String sign;

	public ClientData toData() {
		ClientData cd = new ClientData();
		cd.setValue("goodsItemId", goodsItemId);
		cd.setValue("score", score);
		cd.setValue("content", content);
		cd.setValue("unShowName", unShowName);
		cd.setValue("userId", userId);
		cd.setValue("token", token);
		cd.setValue("timestamp", timestamp);
		cd.setValue("sign", sign);
		return cd;
	}

	public Result validate() {
		Result result = new Result();
		if (unShowName == null) {
			result.wrong("40000", "匿名不能为空");
			return result;
		}
		if (content == null) {
			result.wrong("40000", "评价内容不能为空");
			return result;
		}
		if (score == null) {
			result.wrong("40000", "评价分数不能为空");
			return result;
		}
		if (goodsItemId == null) {
			result.wrong("40000", "订单明细ID不能为空");
			return result;
		}
		if (getUserId() == null) {
			result.wrong("40000", "用户ID不能为空");
			return result;
		}
		if (StringHelper.isNull(getToken())) {
			result.wrong("40000", "token不能为空");
			return result;
		}
		if (StringHelper.isNull(getTimestamp())) {
			result.wrong("40000", "时间戳不能为空");
			return result;
		}
		if (StringHelper.isNull(getSign())) {
			result.wrong("40000", "签名不能为空");
			return result;
		}
		String sign = this.toData().makeSign();
		if (!sign.equals(this.getSign())) {
			result.wrong("40000", "签名错误");
			return result;
		}
		return result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUnShowName() {
		return unShowName;
	}

	public void setUnShowName(Integer unShowName) {
		this.unShowName = unShowName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGoodsItemId() {
		return goodsItemId;
	}

	public void setGoodsItemId(Integer goodsItemId) {
		this.goodsItemId = goodsItemId;
	}

}
