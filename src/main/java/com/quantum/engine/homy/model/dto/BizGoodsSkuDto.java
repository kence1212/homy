package com.quantum.engine.homy.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.quantum.engine.homy.model.result.BaseResponse;

public class BizGoodsSkuDto implements Serializable {

	private Integer id;
	
    private Integer goodsId;

    private Integer colorId;

    private Integer sizeId;

    private Integer textureId;

    private Integer createId;
    
    private Integer modifyId;
    
    private Integer cityId;
    
    private Integer stock;
    
    private Double price;

    private String skuIcon;
    
    public BaseResponse validate() {
		if(this.goodsId == null || this.goodsId <= 0){
			return BaseResponse.getWrong("商品不能为空");
		}
		if(this.colorId == null || this.colorId <= 0){
			return BaseResponse.getWrong("颜色不能为空");
		}
		if(this.sizeId == null || this.sizeId <= 0){
			return BaseResponse.getWrong("尺寸不能为空");
		}
		if(this.textureId == null || this.textureId <= 0){
			return BaseResponse.getWrong("材质不能为空");
		}
		if(this.cityId == null || this.cityId <= 0){
			return BaseResponse.getWrong("城市不能为空");
		}
		if(this.stock == null){
			return BaseResponse.getWrong("库存不能为空");
		}
		if(this.price == null || this.price < 0){
			return BaseResponse.getWrong("价格不能小于0");
		}
		return BaseResponse.getSuccess();
	}

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModifyId() {
		return modifyId;
	}

	public void setModifyId(Integer modifyId) {
		this.modifyId = modifyId;
	}

	public String getSkuIcon() {
		return skuIcon;
	}

	public void setSkuIcon(String skuIcon) {
		this.skuIcon = skuIcon;
	}
	
	


}