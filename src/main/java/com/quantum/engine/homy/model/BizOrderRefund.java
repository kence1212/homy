package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizOrderRefund implements Serializable {
	
	//新建申请
	public static final int STATE_INIT = 0 ;
	//同意退款
	public static final int STATE_AGREE = 1 ;
	//驳回
	public static final int STATE_REJECT = 2;
	//退款成功
	public static final int STATE_REFUND = 3;
	//审核中
	public static final int STATE_CHECK = 4;
	//取消申请
	public static final int STATE_CANCEL = 10;
	
    private Integer id;

    private String outRefundNo;

    private Integer orderId;

    private Double refundAmount;

    private Date createTime;

    private Integer createId;

    private Integer state;

    private String remark;
    
    private String refundReason;
    
    private String goodsState;

    private Double outTradeTotalFee;
    
    private String rejectReason;
    
    private Integer rejectUserId;
    
    private Date rejectTime;
    
    private Integer isValid;

    //EXT
    private String orderNo;
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo == null ? null : outRefundNo.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
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
        this.remark = remark == null ? null : remark.trim();
    }

    public Double getOutTradeTotalFee() {
        return outTradeTotalFee;
    }

    public void setOutTradeTotalFee(Double outTradeTotalFee) {
        this.outTradeTotalFee = outTradeTotalFee;
    }

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public Integer getRejectUserId() {
		return rejectUserId;
	}

	public void setRejectUserId(Integer rejectUserId) {
		this.rejectUserId = rejectUserId;
	}

	public Date getRejectTime() {
		return rejectTime;
	}

	public void setRejectTime(Date rejectTime) {
		this.rejectTime = rejectTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}