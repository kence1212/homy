package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BasImage implements Serializable {
	
	public static final Short IMAGE_TYPE_BANNER = 1;
	public static final Short IMAGE_TYPE_DESC = 2;
	public static final Short IMAGE_TYPE_REVIEW = 3;
	public static final Short IMAGE_TYPE_REFUND = 4;
	
    private Integer id;

    private Short type;

    private String imagePath;

    private Long imageSize;

    private String suffix;

    private Date createTime;

    private Integer createId;
    
    private Integer goodsId;
    
    private Integer reviewId; // review_id
    
    private Integer refundId; // refund_id

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }

    public Long getImageSize() {
        return imageSize;
    }

    public void setImageSize(Long imageSize) {
        this.imageSize = imageSize;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
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

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getRefundId() {
		return refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}
	
}