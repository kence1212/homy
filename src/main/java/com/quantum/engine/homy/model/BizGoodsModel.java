package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.Date;

public class BizGoodsModel implements Serializable {
	
	/**下架*/
	public static final int MODEL_STATE_DOWN = 0;
	/**正常*/
	public static final int MODEL_STATE_NORMAL = 1;
	
    private Integer id;

    private String modelPath;

    private Integer modelHeight;

    private Integer modelWidth;

    private Integer modelLength;

    private Integer goodsId;

    private String modelIcon;

    private String modelVersion;

    private String modelTag;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modifyId;
    
    private Integer type;
    
    private Integer colorId;
    
    private Integer sizeId;
    
    private Integer textureId;
    
    private Integer state;//0表示下架 1表示正常
    
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath == null ? null : modelPath.trim();
    }

    public Integer getModelHeight() {
        return modelHeight;
    }

    public void setModelHeight(Integer modelHeight) {
        this.modelHeight = modelHeight;
    }

    public Integer getModelWidth() {
        return modelWidth;
    }

    public void setModelWidth(Integer modelWidth) {
        this.modelWidth = modelWidth;
    }

    public Integer getModelLength() {
        return modelLength;
    }

    public void setModelLength(Integer modelLength) {
        this.modelLength = modelLength;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getModelIcon() {
        return modelIcon;
    }

    public void setModelIcon(String modelIcon) {
        this.modelIcon = modelIcon == null ? null : modelIcon.trim();
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion == null ? null : modelVersion.trim();
    }

    public String getModelTag() {
        return modelTag;
    }

    public void setModelTag(String modelTag) {
        this.modelTag = modelTag == null ? null : modelTag.trim();
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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifyId() {
        return modifyId;
    }

    public void setModifyId(Integer modifyId) {
        this.modifyId = modifyId;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getSizeId() {
		return sizeId;
	}

	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	public Integer getTextureId() {
		return textureId;
	}

	public void setTextureId(Integer textureId) {
		this.textureId = textureId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
    
    
}