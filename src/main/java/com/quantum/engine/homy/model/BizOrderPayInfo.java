package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizOrderPayInfo implements Serializable {
	
	public static final int PAY_STATE_CANCEL = 0;
	public static final int PAY_STATE_INIT = 1;
	public static final int PAY_STATE_PAYED = 2;
	
    private Integer id;

    private String orderIds;

    private String outTradeNo;

    private String tradeNo;

    private Date createTime;

    private Date payedTime;

    private Double totalAmount;

    private Integer userId;

    private String payAppUserId;

    private Integer state;

    private Integer payType;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(String orderIds) {
        this.orderIds = orderIds == null ? null : orderIds.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayedTime() {
        return payedTime;
    }

    public void setPayedTime(Date payedTime) {
        this.payedTime = payedTime;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPayAppUserId() {
        return payAppUserId;
    }

    public void setPayAppUserId(String payAppUserId) {
        this.payAppUserId = payAppUserId == null ? null : payAppUserId.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}