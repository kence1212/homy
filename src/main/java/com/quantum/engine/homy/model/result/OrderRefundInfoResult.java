package com.quantum.engine.homy.model.result;

import java.io.Serializable;

import com.quantum.engine.homy.model.BizOrderRefund;
import com.quantum.engine.homy.util.DateUtil;

public class OrderRefundInfoResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String applyTime;
	private String refundReason;
	private Double refundAmount;
	private Integer state;
	private String remark;
	private String rejectTime;
	private String rejectReason;
	
	public OrderRefundInfoResult(){}
	
	public OrderRefundInfoResult(BizOrderRefund refund){
		if(refund != null){
			this.refundReason = refund.getRefundReason();
			this.refundAmount = refund.getRefundAmount();
			this.state = refund.getState();
			this.remark = refund.getRemark();
			if(refund.getCreateTime() != null){
				this.applyTime = DateUtil.dateToString(refund.getCreateTime(), "yyyy-MM-dd HH:mm");
			}
			if(refund.getState().equals(BizOrderRefund.STATE_REJECT)){
				if(refund.getRejectTime() !=  null){
					this.rejectTime = DateUtil.dateToString(refund.getRejectTime(), "yyyy-MM-dd HH:mm");
				}
				this.rejectReason = refund.getRejectReason();
			}
		}
		
	}
	
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRejectTime() {
		return rejectTime;
	}

	public void setRejectTime(String rejectTime) {
		this.rejectTime = rejectTime;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

}
