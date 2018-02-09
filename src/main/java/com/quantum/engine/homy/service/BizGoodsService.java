package com.quantum.engine.homy.service;

import java.util.List;
import java.util.Map;

import com.quantum.engine.homy.model.BasImage;
import com.quantum.engine.homy.model.BizGoodsAttrExt;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsColor;
import com.quantum.engine.homy.model.BizGoodsSize;
import com.quantum.engine.homy.model.BizGoodsTexture;
import com.quantum.engine.homy.model.dto.GetCityDto;
import com.quantum.engine.homy.model.dto.GetColorDto;
import com.quantum.engine.homy.model.dto.GetSizeDto;
import com.quantum.engine.homy.model.dto.GetTextureDto;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.GoodsListItemResult;
import com.quantum.engine.homy.model.result.Option;

public interface BizGoodsService {
	
	public List<BizGoodsBase> listAll();
	
	public String downGoods(Integer goodsId);
	
	public String upGoods(Integer goodsId);
	
	public List<BizGoodsBase> listAllExt();
	
	public void addGoods(BizGoodsBase base,String[] descImages,String[] images,String[] textures,String[] sizes,String[] colors,List<BizGoodsAttrExt>  list);

	public Page<BizGoodsBase> queryList(int page, int pageSize, String keyword);
	
	public Page<BizGoodsBase> page(Integer page, Integer pageSize, Map<String, Object> params);
	
	public void setHash3d(int id);

	public void removeHash3d(int id);

	public BizGoodsBase getById(int id);
	
	public int delete(Integer goodsId,String path);

	public List<Map<String, Object>> getColorById(Integer id);

	public List<Map<String, Object>> getSizeById(Integer id);

	public List<Map<String, Object>> getTextureById(Integer id);
	
	public void update(BizGoodsBase bizGoodsBase);

	public List<Map<String, Object>> getSizeList(GetSizeDto dto);

	public List<Map<String, Object>> getColorList(GetColorDto dto);

	public List<Map<String, Object>> getTextureList(GetTextureDto dto);
	
	public List<Option> getAttrsByGoodsId(Integer goodsId);
	
	public List<BasImage> getByGoodsId(Integer goodsId);
	
	public List<Map<String, Object>> getCityList(GetCityDto dto);
	
	public List<String> searchAsso(String searchStr);
	
	public List<GoodsListItemResult> searchGoods(Map<String, Object> params);
	
	public List<String> searchGoodsTexture(Map<String, Object> params);

	public BizGoodsBase getDetailById(Integer goodsId);
	
	public void addGoodsSize(BizGoodsSize bizGoodsSize);
	
	public String deleteGoodsSize(Integer id);
	
	public void addGoodsColor(BizGoodsColor bizGoodsColor);
	
	public String deleteGoodsColor(Integer id);
	
	public void addGoodsTexture(BizGoodsTexture bizGoodsTexture);
	
	public String deleteGoodsTexture(Integer id);
	
	public List<String> searchTexture(Map<String, Object> params);
	
	public List<BasImage> getImageByGoodsId(Integer goodsId);
	
	public void deleteImage(Integer id);
	
	public void addImage(BasImage image);
	
	public void deleteGoodsAttr(Integer id);
	
	public void addGoodsAttr(BizGoodsAttrExt attr);

	public GoodsListItemResult getByGoodsIdAndCityId(Integer goodsId, Integer cityId);
	
	public String goodsNames(String orderIds);

}
