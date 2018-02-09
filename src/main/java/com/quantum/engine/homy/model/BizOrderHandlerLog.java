package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizOrderHandlerLog implements Serializable {
    private Integer id;

    private Integer orderId;

    private Integer preOrderState;

    private Integer finOrderState;

    private Integer userId;

    private Date recordTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPreOrderState() {
        return preOrderState;
    }

    public void setPreOrderState(Integer preOrderState) {
        this.preOrderState = preOrderState;
    }

    public Integer getFinOrderState() {
        return finOrderState;
    }

    public void setFinOrderState(Integer finOrderState) {
        this.finOrderState = finOrderState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}