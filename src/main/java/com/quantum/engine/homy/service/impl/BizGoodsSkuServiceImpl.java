package com.quantum.engine.homy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BasRegionMapper;
import com.quantum.engine.homy.dao.BizGoodsBaseMapper;
import com.quantum.engine.homy.dao.BizGoodsColorMapper;
import com.quantum.engine.homy.dao.BizGoodsSizeMapper;
import com.quantum.engine.homy.dao.BizGoodsSkuMapper;
import com.quantum.engine.homy.dao.BizGoodsSkuStockMapper;
import com.quantum.engine.homy.dao.BizGoodsTextureMapper;
import com.quantum.engine.homy.excel.GoodsSkuStockModel;
import com.quantum.engine.homy.model.BasRegion;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsColor;
import com.quantum.engine.homy.model.BizGoodsSize;
import com.quantum.engine.homy.model.BizGoodsSku;
import com.quantum.engine.homy.model.BizGoodsSkuStock;
import com.quantum.engine.homy.model.BizGoodsTexture;
import com.quantum.engine.homy.model.dto.BizGoodsSkuDto;
import com.quantum.engine.homy.model.dto.SkuPageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.service.BizGoodsSkuService;
import com.quantum.engine.homy.util.StringHelper;

@Service
public class BizGoodsSkuServiceImpl implements BizGoodsSkuService {

	@Autowired
	BizGoodsSkuMapper bizGoodsSkuMapper;
	@Autowired
	BizGoodsSkuStockMapper bizGoodsSkuStockMapper;
	@Autowired
	BizGoodsBaseMapper bizGoodsBaseMapper;
	@Autowired
	private BasRegionMapper regionMapper;
	@Autowired
	private BizGoodsColorMapper bizGoodsColorMapper;
	@Autowired
	private BizGoodsSizeMapper bizGoodsSizeMapper;
	@Autowired
	private BizGoodsTextureMapper bizGoodsTextureMapper;
	
	@Override
	public BaseResponse add(BizGoodsSkuDto dto) {

		Integer goodsId = dto.getGoodsId();
		Integer colorId = dto.getColorId();
		Integer sizeId  = dto.getSizeId();
		Integer textureId = dto.getTextureId();
		Integer stock = dto.getStock();
		Double  price = dto.getPrice();
		Integer cityId = dto.getCityId();
		Integer createId = dto.getCreateId();
		String result = insertOrUpdateStock(goodsId, colorId, sizeId, textureId, cityId, createId, stock, price,null);
		if(result == null){
			return BaseResponse.getSuccess("保存成功");
		}else{
			return BaseResponse.getWrong(result);
		}

	}
	
	@Override
	public BaseResponse update(BizGoodsSkuDto dto) {
		
		Integer goodsId = dto.getGoodsId();
		Integer colorId = dto.getColorId();
		Integer sizeId  = dto.getSizeId();
		Integer textureId = dto.getTextureId();
		/*Integer stock = dto.getStock();
		Double  price = dto.getPrice();
		Integer cityId = dto.getCityId();*/
		Integer createId = dto.getCreateId();
		
		BizGoodsSku bizGoodsSkuInDB = bizGoodsSkuMapper.getByColorAndSizeAndTex(dto.getColorId(), dto.getSizeId(),
				dto.getTextureId(),goodsId);
		if (bizGoodsSkuInDB != null) {
			// 存在sku
			BizGoodsSkuStock bizGoodsSkuStockInDB = bizGoodsSkuStockMapper.getBySkuIdAndCityId(bizGoodsSkuInDB.getId(),
					dto.getCityId());
			if (bizGoodsSkuStockInDB != null && !(bizGoodsSkuStockInDB.getId().equals(dto.getId()))) {
				// sku和stock都存在返回编辑失败
				return BaseResponse.getWrong("不能保存颜色、尺寸、材质、城市相同的库存记录");
			} else {
				// 有sku但是没有对应的库存
				updateSkuStock(dto, bizGoodsSkuInDB.getId());
				return BaseResponse.getSuccess("编辑成功");
			}
		}
		// 如果没有该sku那么sku新建
		BizGoodsSku bizGoodsSku = insertSku(null, goodsId, colorId, sizeId, textureId, createId);;
		updateSkuStock(dto, bizGoodsSku.getId());
		return BaseResponse.getSuccess("编辑成功");
	}
	
	private String insertOrUpdateStock(Integer goodsId,Integer colorId,Integer sizeId,Integer textureId,Integer cityId,Integer createId,Integer stock,Double price,Integer stockId){
		BizGoodsSku bizGoodsSkuInDB = bizGoodsSkuMapper.getByColorAndSizeAndTex(colorId, sizeId,textureId, goodsId);
		if (bizGoodsSkuInDB != null) {
			// 存在sku
			BizGoodsSkuStock bizGoodsSkuStockInDB = bizGoodsSkuStockMapper.getBySkuIdAndCityId(bizGoodsSkuInDB.getId(),cityId);
			if (bizGoodsSkuStockInDB != null && !(bizGoodsSkuStockInDB.getId().equals(stockId))) {
				// sku和stock都存在返回保存失败
				return "不能保存颜色、尺寸、材质、城市相同的库存记录";
			} else {
				// 有sku但是没有对应的库存
				insertSkuStock(cityId, createId, bizGoodsSkuInDB.getId(), price, stock);
				return null;
			}
		}

		// 如果没有该sku那么sku和stock都要新建
		BizGoodsSku bizGoodsSku = insertSku(null, goodsId, colorId, sizeId, textureId, createId);
		insertSkuStock(cityId, createId, bizGoodsSku.getId(), price, stock);
		return null;
	}
	
	private BizGoodsSku insertSku(String skuIcon,Integer goodsId,Integer colorId,Integer sizeId,Integer textureId,Integer createId){
		if(StringHelper.isNull(skuIcon)){
			BizGoodsBase goods = bizGoodsBaseMapper.selectByPrimaryKey(goodsId);
			skuIcon = goods.getGoodsIcon();
		}
		BizGoodsSku bizGoodsSku = new BizGoodsSku();
		bizGoodsSku.setColorId(colorId);
		bizGoodsSku.setCreateId(createId);
		bizGoodsSku.setCreateTime(new Date());
		bizGoodsSku.setGoodsId(goodsId);
		bizGoodsSku.setSizeId(sizeId);
		bizGoodsSku.setTextureId(textureId);
		bizGoodsSku.setSkuIcon(skuIcon);
		bizGoodsSkuMapper.insert(bizGoodsSku);
		return bizGoodsSku;
	}
	
	private BizGoodsSkuStock insertSkuStock(Integer cityId,Integer createId,Integer skuId,Double price,Integer stock){
		BizGoodsSkuStock bizGoodsSkuStock = new BizGoodsSkuStock();
		bizGoodsSkuStock.setCityId(cityId);
		bizGoodsSkuStock.setCreateId(createId);
		bizGoodsSkuStock.setCreateTime(new Date());
		bizGoodsSkuStock.setPrice(price);
		bizGoodsSkuStock.setSkuId(skuId);
		bizGoodsSkuStock.setStock(stock);
		bizGoodsSkuStockMapper.insert(bizGoodsSkuStock);
		return bizGoodsSkuStock;
	}

	@Override
	public Page<BizGoodsSkuStockExt> getList(SkuPageListQueryDto dto) {

		String keyword = dto.getKeyword();

		if (keyword != null && keyword != "") {
			keyword = "%" + keyword + "%";
		}

		Page<BizGoodsSkuStockExt> page = new Page<BizGoodsSkuStockExt>(dto.getPage(), dto.getPageSize());

		int count = bizGoodsSkuStockMapper.getTotalCount(keyword, dto.getGoodsId());
		
		page.setTotalCount(count);
		
		if(count == 0){
			
			page.setList(new ArrayList<BizGoodsSkuStockExt>());
			return page;
					
		}
		
		List<BizGoodsSkuStockExt> list = bizGoodsSkuStockMapper.getList(dto.getPage(),(dto.getPage()-1)*dto.getPageSize(),dto.getPageSize(),keyword, dto.getGoodsId());

		page.setList(list);
		
		return page;
	}
	
	public List<BizGoodsSkuStockExt> getByGoodsIdAndCity(Integer goodsId,Integer cityId){
		return bizGoodsSkuStockMapper.getByGoodsAndCity(goodsId, cityId);
	}
	
	public List<BizGoodsSkuStockExt> getByGoodsId(Integer goodsId){
		return bizGoodsSkuStockMapper.getByGoodsAndCity(goodsId, null);
	}

	@Override
	public int delete(Integer id) {
		
		BizGoodsSkuStock selectByPrimaryKey = bizGoodsSkuStockMapper.selectByPrimaryKey(id);
		
		int deleteByPrimaryKey = bizGoodsSkuStockMapper.deleteByPrimaryKey(id);
		
		int count = bizGoodsSkuStockMapper.getTotalCountBySkuId(selectByPrimaryKey.getSkuId());
		
		if(count == 0){
			bizGoodsSkuMapper.deleteByPrimaryKey(selectByPrimaryKey.getSkuId());
		}
		
		return deleteByPrimaryKey;
	}

	@Override
	public BizGoodsSkuStockExt getById(Integer id) {
		return bizGoodsSkuStockMapper.getById(id);
	}

	/**
	 * 导入保存到库 
	 */
	public int importSku(List<GoodsSkuStockModel> list,Integer goodsId){
		int result = 0;
		Map<Integer, BizGoodsBase> goodsList = new HashMap<Integer, BizGoodsBase>();
		Map<String, BasRegion> citys = new HashMap<String, BasRegion>();
		Map<String, BizGoodsColor> colors = new HashMap<String, BizGoodsColor>();
		Map<String, BizGoodsTexture> textures = new HashMap<String, BizGoodsTexture>();
		Map<String, BizGoodsSize> sizes = new HashMap<String, BizGoodsSize>();
		if(list != null && list.size() > 0 ){
			for(GoodsSkuStockModel model : list){
				if(insertByImport(model,goodsList, citys, colors, textures, sizes,goodsId)){
					result ++ ;
				}
			}
		}
		return result;
	}
	
	private boolean insertByImport(GoodsSkuStockModel model,
			Map<Integer, BizGoodsBase> goodsList,
			Map<String, BasRegion> citys,
			Map<String, BizGoodsColor> colors,
			Map<String, BizGoodsTexture> textures,
			Map<String, BizGoodsSize> sizes,Integer goodsId){
		BizGoodsBase goods = handlerGoods(goodsId, model, goodsList);
		BasRegion city = handlerCitys(goodsId, model.getCityName(), citys);
		BizGoodsColor color = handlerColor(goodsId, model.getColorName(), colors);
		BizGoodsTexture texture = handlerTexture(goodsId, model.getTextureName(), textures);
		BizGoodsSize size = handlerSize(goodsId, model.getSizeName(), sizes);
		String country = model.getCountry();
		String province = model.getProvince();
		
		if("全国".equals(country)){
			
			//全国
			List<BasRegion> list = regionMapper.getAllCity();
			boolean countryInsert = false;
			for (BasRegion basRegion : list) {
				String insertOneRecord = insertOneRecord(goods,basRegion,color,texture,size,model,goodsId);
				if(insertOneRecord == null){
					countryInsert = true ;
				}
			}
			return countryInsert;
			
		}else if(StringHelper.isNotNull(province)){
			
			//全省
			BasRegion provinceBean = regionMapper.getByCityNameAndGrade(province, 2);
			boolean provinceInsert = false;
			if(provinceBean == null){
				String insertOneRecord = insertOneRecord(goods,city,color,texture,size,model,goodsId);
				if(insertOneRecord == null){
					provinceInsert = true;
				} 
			}else{
				List<BasRegion> list = regionMapper.getCityByProvince(provinceBean.getId());
				for (BasRegion basRegion : list) {
					String insertOneRecord = insertOneRecord(goods,basRegion,color,texture,size,model,goodsId);
					if(insertOneRecord == null){
						provinceInsert = true ;
					}
				}
			}
			return provinceInsert;
			
		} else{
			
			//城市
			boolean cityInsert = false;
			String insertOneRecord = insertOneRecord(goods,city,color,texture,size,model,goodsId);
			if(insertOneRecord == null){
				cityInsert = true;
			}
			return cityInsert;
			
		}
		
	}
	
	private String insertOneRecord(BizGoodsBase goods, BasRegion city, BizGoodsColor color, BizGoodsTexture texture, BizGoodsSize size, GoodsSkuStockModel model, Integer goodsId){
		String result = null;
		if(goods != null && city != null && color != null && texture != null && size != null){
			Integer cityId = city.getId();
			Integer colorId = color.getId();
			Integer textureId = texture.getId();
			Integer sizeId = size.getId();
			Integer stock = model.getStock();
			Double  price = model.getPrice();
			 result = insertOrUpdateStock(goodsId, colorId, sizeId, textureId, cityId, null, stock, price,null);
		}
		return result;
	}
	
	private BizGoodsSize handlerSize(Integer goodsId,String sizeName,Map<String, BizGoodsSize> sizes){
		BizGoodsSize size = null;
		if(StringHelper.isNull(sizeName)){
			return size;
		}
		size = sizes.get(sizeName);
		if(size == null){
			size = bizGoodsSizeMapper.getByGoodsIdAndName(goodsId, sizeName);
			if(size == null){
				size = new BizGoodsSize();
				size.setCreateTime(new Date());
				size.setGoodsId(goodsId);
				size.setIsValid((short)1);
				size.setName(sizeName);
				bizGoodsSizeMapper.insert(size);
			}
			sizes.put(sizeName, size);
		}
		return size;
	}
	
	private BizGoodsTexture handlerTexture(Integer goodsId,String textureName,Map<String, BizGoodsTexture> textures){
		BizGoodsTexture texture = null;
		if(StringHelper.isNull(textureName)){
			return texture;
		}
		texture = textures.get(textureName);
		if(texture == null){
			texture = bizGoodsTextureMapper.getByGoodsIdAndName(goodsId,textureName);
			if(texture == null){
				texture = new BizGoodsTexture();
				texture.setCreateTime(new Date());
				texture.setGoodsId(goodsId);
				texture.setTextureName(textureName);
				bizGoodsTextureMapper.insert(texture);
			}
			textures.put(textureName, texture);
		}
		
		return texture;
	}
	
	private BizGoodsColor handlerColor(Integer goodsId,String colorName,Map<String, BizGoodsColor> colors){
		BizGoodsColor color = null;
		if(StringHelper.isNull(colorName)){
			return color;
		}
		color = colors.get(colorName);
		if(color == null){
			color = bizGoodsColorMapper.getByGoodsIdAndName(colorName, goodsId);
			if(color == null){
				color = new BizGoodsColor();
				color.setGoodsId(goodsId);
				color.setName(colorName);
				color.setCreateTime(new Date());
				bizGoodsColorMapper.insertSelective(color);
			}
			colors.put(colorName, color);
		}
		return color;
	}
	
	private BasRegion handlerCitys(Integer goodsId,String city,Map<String, BasRegion> citys){
		BasRegion region = null;
		if(StringHelper.isNull(city)){
			return region;
		}
		region = citys.get(city);
		if(region == null){
			region = regionMapper.getByCityNameAndGrade(city, 3);
			if(region != null){
				citys.put(city, region);
			}
		}
		return region;
	}
	
	private BizGoodsBase handlerGoods(Integer goodsId,GoodsSkuStockModel model,Map<Integer, BizGoodsBase> goodsList){
		BizGoodsBase goods = null;
		if(goodsId == null){
			goodsId = model.getGoodsId();
		}
		if(goodsId != null){
			goods =  goodsList.get(goodsId);
			if(goods == null){
				goods = bizGoodsBaseMapper.selectByPrimaryKey(goodsId);
				if(goods != null){
					goodsList.put(goodsId, goods);
				}
			}
		}
		return goods;
	}

	private void updateSkuStock(BizGoodsSkuDto dto, Integer skuId) {
		BizGoodsSkuStock bizGoodsSkuStock = new BizGoodsSkuStock();
		bizGoodsSkuStock.setId(dto.getId());
		bizGoodsSkuStock.setCityId(dto.getCityId());
		bizGoodsSkuStock.setModifyId(dto.getModifyId());
		bizGoodsSkuStock.setModifyTime(new Date());
		bizGoodsSkuStock.setPrice(dto.getPrice());
		bizGoodsSkuStock.setSkuId(skuId);
		bizGoodsSkuStock.setStock(dto.getStock());
		bizGoodsSkuStockMapper.updateByPrimaryKeySelective(bizGoodsSkuStock);
	}

	@Override
	public int getStockByCityIdAndAttrs(Integer cityId, Integer goodsId, Integer colorId, Integer sizeId, Integer textureId) {
		
		BizGoodsSku goodsSku = bizGoodsSkuMapper.getByGoodsIdAndProperties(goodsId, colorId, sizeId, textureId);
		if(goodsSku == null){
			return 0;
		}
		BizGoodsSkuStock goodsSkuStock = bizGoodsSkuStockMapper.getBySkuIdAndCityId(goodsSku.getId(), cityId);
		if(goodsSkuStock == null){
			return 0;
		}
		return goodsSkuStock.getStock();
	}

	@Override
	public BizGoodsSkuStockExt getGoodsByGoodsIdAndCity(Integer goodsId, Integer cityId, Integer colorId,
			Integer sizeId, Integer textureId) {
		
		return bizGoodsSkuStockMapper.getGoodsByGoodsIdAndCity(goodsId, cityId,colorId,sizeId,textureId);
		
	}

}
