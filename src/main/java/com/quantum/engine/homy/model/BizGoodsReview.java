package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizGoodsReview implements Serializable {
	
	public static final int SHOWNAME = 1;
	public static final int UNSHOWNAME = 0;
	
    private Integer id;

    private Integer goodsItemId;

    private Integer score;

    private String content;

    private Date reviewTime;

    private Integer userId;

    private Integer isValid;

    private Integer pid;

    private Integer unShowName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsItemId() {
        return goodsItemId;
    }

    public void setGoodsItemId(Integer goodsItemId) {
        this.goodsItemId = goodsItemId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUnShowName() {
        return unShowName;
    }

    public void setUnShowName(Integer unShowName) {
        this.unShowName = unShowName;
    }
}