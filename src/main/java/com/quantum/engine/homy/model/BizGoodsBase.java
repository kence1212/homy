package com.quantum.engine.homy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.quantum.engine.homy.util.DateUtil;

public class BizGoodsBase implements Serializable {
	
	
	public static final int GOODS_STATE_INIT = 0;
	/**正常*/
	public static final int GOODS_STATE_NORMAL = 1;
	/**下架*/
	public static final int GOODS_STATE_DOWN = 2;
	
    private Integer id;

    private String goodsName;

    private String goodsNo;

    private String goodsTypeNumber;

    private String goodsIcon;

    private Boolean have3d;
    
    private String goodsDesc;

    private Date createTime;

    private Integer createId;

    private Date modifyTime;

    private Integer modifyId;

    private Integer goodsCategoryId;
    
    private Integer goodsState;
    
    private Integer goodsStyleId;
    
    private Double defaultPrice;
    
    private Integer brandId;

    private static final long serialVersionUID = 1L;
    
    //ext
    private String goodsCategoryName;
    private String goodsStyleName;
    private String brandName;
    
    List<BizGoodsSize> sizes;
    List<BizGoodsColor> colors;
    List<BizGoodsTexture> textures;
    List<BizGoodsAttrExt> attrs;
    List<BasImage> imgs;
    
    private String createTimeStr;
    private String modifyTimeStr;

    public List<BizGoodsAttrExt> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<BizGoodsAttrExt> attrs) {
		this.attrs = attrs;
	}

	public List<BizGoodsTexture> getTextures() {
		return textures;
	}

	public void setTextures(List<BizGoodsTexture> textures) {
		this.textures = textures;
	}

	public List<BizGoodsColor> getColors() {
		return colors;
	}

	public void setColors(List<BizGoodsColor> colors) {
		this.colors = colors;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    public String getGoodsTypeNumber() {
        return goodsTypeNumber;
    }

    public void setGoodsTypeNumber(String goodsTypeNumber) {
        this.goodsTypeNumber = goodsTypeNumber == null ? null : goodsTypeNumber.trim();
    }

    public String getGoodsIcon() {
        return goodsIcon;
    }

    public void setGoodsIcon(String goodsIcon) {
        this.goodsIcon = goodsIcon == null ? null : goodsIcon.trim();
    }

    public Boolean getHave3d() {
        return have3d;
    }

    public void setHave3d(Boolean have3d) {
        this.have3d = have3d;
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

    public Integer getGoodsCategoryId() {
        return goodsCategoryId;
    }

    public void setGoodsCategoryId(Integer goodsCategoryId) {
        this.goodsCategoryId = goodsCategoryId;
    }

	public Integer getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(Integer goodsState) {
		this.goodsState = goodsState;
	}

	public Integer getGoodsStyleId() {
		return goodsStyleId;
	}

	public void setGoodsStyleId(Integer goodsStyleId) {
		this.goodsStyleId = goodsStyleId;
	}

	public Double getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getGoodsCategoryName() {
		return goodsCategoryName;
	}

	public void setGoodsCategoryName(String goodsCategoryName) {
		this.goodsCategoryName = goodsCategoryName;
	}

	public String getGoodsStyleName() {
		return goodsStyleName;
	}

	public void setGoodsStyleName(String goodsStyleName) {
		this.goodsStyleName = goodsStyleName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<BizGoodsSize> getSizes() {
		return sizes;
	}

	public void setSizes(List<BizGoodsSize> sizes) {
		this.sizes = sizes;
	}

	public List<BasImage> getImgs() {
		return imgs;
	}

	public void setImgs(List<BasImage> imgs) {
		this.imgs = imgs;
	}
	
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getCreateTimeStr() {
		return DateUtil.dateToString(getCreateTime(), "yyyy-MM-dd");
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getModifyTimeStr() {
		return DateUtil.dateToString(getModifyTime(), "yyyy-MM-dd");
	}

	public void setModifyTimeStr(String modifyTimeStr) {
		this.modifyTimeStr = modifyTimeStr;
	}

	public List<BasImage> getGoodsImages(){
		return getByType(BasImage.IMAGE_TYPE_BANNER);
	}
	public List<BasImage> getDescImages(){
		return getByType(BasImage.IMAGE_TYPE_DESC);
	}
	
	private List<BasImage> getByType(Short type){
		List<BasImage> result = new ArrayList<BasImage>();
		List<BasImage> list = this.getImgs();
		if(list != null && list.size() > 0){
			for(BasImage img:list){
				if(img.getType() != null && img.getType() == type){
					result.add(img);
				}
			}
		}
		return result;
	}
}