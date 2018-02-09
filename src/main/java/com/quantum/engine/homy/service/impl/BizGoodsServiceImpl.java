package com.quantum.engine.homy.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BasImageMapper;
import com.quantum.engine.homy.dao.BizGoodsAttrExtMapper;
import com.quantum.engine.homy.dao.BizGoodsBaseMapper;
import com.quantum.engine.homy.dao.BizGoodsColorMapper;
import com.quantum.engine.homy.dao.BizGoodsModelMapper;
import com.quantum.engine.homy.dao.BizGoodsSizeMapper;
import com.quantum.engine.homy.dao.BizGoodsSkuMapper;
import com.quantum.engine.homy.dao.BizGoodsSkuStockMapper;
import com.quantum.engine.homy.dao.BizGoodsTextureMapper;
import com.quantum.engine.homy.model.BasImage;
import com.quantum.engine.homy.model.BizGoodsAttrExt;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsColor;
import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.BizGoodsSize;
import com.quantum.engine.homy.model.BizGoodsTexture;
import com.quantum.engine.homy.model.dto.GetCityDto;
import com.quantum.engine.homy.model.dto.GetColorDto;
import com.quantum.engine.homy.model.dto.GetSizeDto;
import com.quantum.engine.homy.model.dto.GetTextureDto;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.GoodsListItemResult;
import com.quantum.engine.homy.model.result.Option;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.util.ListHelper;
import com.quantum.engine.homy.util.StringHelper;

@Service("bizGoodsService")
public class BizGoodsServiceImpl implements BizGoodsService {
	
	@Autowired
	private BizGoodsBaseMapper mapper;
	@Autowired
	private BasImageMapper basImageMapper;
	@Autowired
	private BizGoodsTextureMapper bizGoodsTextureMapper;
	@Autowired
	private BizGoodsSizeMapper bizGoodsSizeMapper;
	@Autowired
	private BizGoodsColorMapper bizGoodsColorMapper;
	@Autowired
	private BizGoodsAttrExtMapper bizGoodsAttrExtMapper;
	@Autowired
	private BizGoodsModelMapper bizGoodsModelMapper;
	@Autowired
	private BizGoodsSkuMapper bizGoodsSkuMapper;
	@Autowired
	private BizGoodsSkuStockMapper bizGoodsSkuStockMapper;
	
	@Override
	public List<Map<String, Object>> getCityList(GetCityDto dto) {
	
		List<Map<String,Object>> skus = bizGoodsSkuMapper.getCityList(dto.getGoodsId(),dto.getColorId(),dto.getTextureId(),dto.getSizeId());
		
		List<Map<String, Object>> citys = new ArrayList<>();
		for (Map<String, Object> map : skus) {
			Map<String, Object> city = new HashMap<>();
			city.put("id", map.get("cityId"));
			city.put("name", map.get("name"));
			int stock = Integer.parseInt(map.get("stock")+"");
			if(stock > 0){
				city.put("hasStock", true);	
			}else{
				city.put("hasStock", false);
			}
			citys.add(city);
		}
		return citys;
	}
	
	@Override
	public List<Map<String, Object>> getSizeList(GetSizeDto dto) {
	
		List<Map<String,Object>> skus = bizGoodsSkuMapper.getSizeList(dto.getGoodsId(),dto.getColorId(),dto.getTextureId(),dto.getCityId());
		
		List<Map<String, Object>> sizes = new ArrayList<>();
		for (Map<String, Object> map : skus) {
			Map<String, Object> size = new HashMap<>();
			size.put("id", map.get("sizeId"));
			size.put("name", map.get("name"));
			int stock = Integer.parseInt(map.get("stock")+"");
			if(stock > 0 || stock == -1){
				size.put("hasStock", true);	
			}else{
				size.put("hasStock", false);
			}
			sizes.add(size);
		}
		
		return sizes;
	}
	
	@Override
	public List<Map<String, Object>> getColorList(GetColorDto dto) {
		
		List<Map<String,Object>> skus = bizGoodsSkuMapper.getColorList(dto.getGoodsId(),dto.getSizeId(),dto.getTextureId(),dto.getCityId());
		
		List<Map<String, Object>> sizes = new ArrayList<>();
		for (Map<String, Object> map : skus) {
			Map<String, Object> size = new HashMap<>();
			size.put("id", map.get("colorId"));
			size.put("name", map.get("name"));
			int stock = Integer.parseInt(map.get("stock")+"");
			if(stock > 0 || stock == -1){
				size.put("hasStock", true);	
			}else{
				size.put("hasStock", false);
			}
			sizes.add(size);
		}
		
		return sizes;
	}
	
	@Override
	public List<Map<String, Object>> getTextureList(GetTextureDto dto) {
		
		List<Map<String,Object>> skus = bizGoodsSkuMapper.getTextureList(dto.getGoodsId(),dto.getSizeId(),dto.getColorId(),dto.getCityId());
		
		List<Map<String, Object>> sizes = new ArrayList<>();
		for (Map<String, Object> map : skus) {
			Map<String, Object> size = new HashMap<>();
			size.put("id", map.get("textureId"));
			size.put("name", map.get("name"));
			int stock = Integer.parseInt(map.get("stock")+"");
			if(stock > 0 || stock == -1){
				size.put("hasStock", true);	
			}else{
				size.put("hasStock", false);
			}
			sizes.add(size);
		}
		
		return sizes;
	}
	
	@Override
	public List<Option> getAttrsByGoodsId(Integer goodsId){
		return bizGoodsAttrExtMapper.getAttrsByGoodsId(goodsId);
	}
	
	@Override
	public List<BasImage> getByGoodsId(Integer goodsId){
		return basImageMapper.getByGoodsId(goodsId);
	}

	@Override
	public List<BizGoodsBase> listAll(){
		return mapper.getAll();
	}
	
	@Override
	public String downGoods(Integer goodsId){
		//TODO
		String result = null;
		BizGoodsBase goods = mapper.selectByPrimaryKey(goodsId);
		if(goods != null){
			if(goods.getGoodsState() == BizGoodsBase.GOODS_STATE_DOWN){
				result = "商品是下架状态";
			}else{
				goods.setGoodsState(BizGoodsBase.GOODS_STATE_DOWN);
				mapper.updateByPrimaryKey(goods);
			}
		}else{
			result = "请选择商品";
		}
		return result;
				
	}
	
	@Override
	public String upGoods(Integer goodsId){
		String result = null;
		BizGoodsBase goods = mapper.selectByPrimaryKey(goodsId);
		if(goods != null){
			if(goods.getGoodsState() == BizGoodsBase.GOODS_STATE_NORMAL){
				result = "商品是上架状态";
			}else{
				goods.setGoodsState(BizGoodsBase.GOODS_STATE_NORMAL);
				mapper.updateByPrimaryKey(goods);
			}
		}else{
			result = "请选择商品";
		}
		return result;
	}
	
	@Override
	public List<BizGoodsBase> listAllExt(){
		return mapper.getAllExt();
	}

	@Override
	public void addGoods(BizGoodsBase base,String[] descImages, String[] images, String[] textures,
			String[] sizes,String[] colors,List<BizGoodsAttrExt> list) {
		mapper.insert(base);
		Integer goodsId = base.getId();
		addImage(images, base.getCreateId(), goodsId ,BasImage.IMAGE_TYPE_BANNER);
		addImage(descImages, base.getCreateId(), goodsId ,BasImage.IMAGE_TYPE_DESC);
		addTextures(textures, base.getCreateId(), goodsId);
		addSizes(sizes, base.getCreateId(), goodsId);
		addColors(colors, base.getCreateId(), goodsId);
		addAttrExt(list, base.getCreateId(), goodsId);
	}
	
	
	private void addAttrExt(List<BizGoodsAttrExt> list,Integer createId,Integer goodsId){
		if(list != null && list.size() > 0){
			for(BizGoodsAttrExt ext:list){
				ext.setGoodsId(goodsId);
				bizGoodsAttrExtMapper.insert(ext);
			}
		}
	}
	
	private void addImage(String[] images,Integer createId,Integer goodsId,Short type){
		if(images != null && images.length > 0){
			for(String img:images){
				if(StringHelper.isNotNull(img)){
					BasImage basImage = new BasImage();
					basImage.setCreateId(createId);
					basImage.setCreateTime(new Date());
					basImage.setImagePath(img);
					basImage.setType(type);
					basImage.setSuffix(StringHelper.getSuffix(img));
					basImage.setGoodsId(goodsId);
					basImageMapper.insert(basImage);
				}
			}
		}
	}
	
	private void addTextures(String[] textures,Integer createId,Integer goodsId){
		if(textures != null && textures.length > 0){
			for(String texture:textures){
				if(StringHelper.isNotNull(texture)){
					BizGoodsTexture goodsTexture = new BizGoodsTexture();
					goodsTexture.setCreateId(createId);
					goodsTexture.setCreateTime(new Date());
					goodsTexture.setGoodsId(goodsId);
					goodsTexture.setTextureName(texture);
					bizGoodsTextureMapper.insert(goodsTexture);
				}
			}
		}
	}
	
	private void addSizes(String[] sizes,Integer createId,Integer goodsId){
		if(sizes != null && sizes.length > 0){
			for(String size:sizes){
				if(StringHelper.isNotNull(size)){
					BizGoodsSize goodsSize = new BizGoodsSize();
					goodsSize.setCreateId(createId);
					goodsSize.setCreateTime(new Date());
					goodsSize.setGoodsId(goodsId);
					goodsSize.setName(size);
					goodsSize.setIsValid((short)1);
					bizGoodsSizeMapper.insert(goodsSize);
				}
			}
		}
	}
	
	private void addColors(String[] colors,Integer createId,Integer goodsId){
		if(colors != null && colors.length > 0){
			for(String color:colors){
				if(StringHelper.isNotNull(color)){
					BizGoodsColor bean = new BizGoodsColor();
					bean.setCreateId(createId);
					bean.setCreateTime(new Date());
					bean.setGoodsId(goodsId);
					bean.setName(color);
					bizGoodsColorMapper.insert(bean);
				}
			}
		}
	}

	@Override
	public Page<BizGoodsBase> queryList(int page, int pageSize, String keyword) {
		
		if(keyword != null && keyword != ""){
			keyword = "%"+keyword+"%";
		}
		
		int total = mapper.queryTotal(keyword);
		
		List<BizGoodsBase> data = mapper.queryList((page-1)*pageSize,pageSize,keyword);
		
		Page<BizGoodsBase> pageRes = new Page<BizGoodsBase>(page,pageSize,total);
		
		pageRes.setList(data);
		
		return pageRes;
	}
	
	@Override
	public Page<BizGoodsBase> page(Integer page, Integer pageSize, Map<String, Object> params){
		Page<BizGoodsBase> pageList = new Page<BizGoodsBase>();
		if(page != null && page.intValue() > 0 
				&& pageSize != null && pageSize.intValue() > 0){
			if(params == null){
				params = new HashMap<String, Object>();
			}
			params.put("page", (page-1)*pageSize);
			params.put("pageSize", pageSize);
			List<BizGoodsBase> list = mapper.list(params);
			int totalCount = mapper.countTotal(params);
			pageList.setList(list);
			pageList.setTotalCount(totalCount);
			pageList.setPageNum(page);
			pageList.setPageSize(pageSize);
		}
		return pageList;
	}

	@Override
	public void setHash3d(int id) {
		
		BizGoodsBase bizGoodsBaseInDB = mapper.selectByPrimaryKey(id);
		
		bizGoodsBaseInDB.setHave3d(true);
		
		mapper.updateByPrimaryKeySelective(bizGoodsBaseInDB);
		
	}

	@Override
	public void removeHash3d(int id) {

		BizGoodsBase bizGoodsBaseInDB = mapper.selectByPrimaryKey(id);
		
		bizGoodsBaseInDB.setHave3d(false);
		
		mapper.updateByPrimaryKeySelective(bizGoodsBaseInDB);
		
	}

	@Override
	public BizGoodsBase getById(int id) {
		
		BizGoodsBase selectByPrimaryKey = mapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}
	
	@Override
	public int delete(Integer goodsId,String path){
		BizGoodsBase base = mapper.selectByPrimaryKey(goodsId);
		List<String> deleteFileList = new ArrayList<String>();
		if(base != null){
			//删除图片 
			List<BasImage> list = basImageMapper.getByGoodsId(goodsId);
			if(list != null && list.size() > 0 ){
				for(BasImage img:list){
					deleteFileList.add(img.getImagePath());
				}
			}
			basImageMapper.deleteByGoodsId(goodsId);
			//删除材质
			bizGoodsTextureMapper.deleteByGoodsId(goodsId);
			//删除尺寸
			bizGoodsSizeMapper.deleteByGoodsId(goodsId);
			//删除颜色
			bizGoodsColorMapper.deleteByGoodsId(goodsId);
			//删除属性
			bizGoodsAttrExtMapper.deleteByGoodsId(goodsId);
			//删除基本表
			deleteFileList.add(base.getGoodsIcon());
			mapper.deleteByPrimaryKey(goodsId);
			List<BizGoodsModel> modelList = bizGoodsModelMapper.getByGoodsId(goodsId);
			if(modelList != null && modelList.size() > 0){
				for(BizGoodsModel model:modelList){
					deleteFileList.add(model.getModelIcon());
					deleteFileList.add(model.getModelPath());
				}
			}
			bizGoodsModelMapper.deleteByGoodsId(goodsId);
			deleteFile(deleteFileList, path);
			return 1;
		}else{
			return 0;
		}
	}
	
	private void deleteFile(List<String> filePathList,String path){
		if(filePathList != null && filePathList.size() > 0){
			for(String imgPath:filePathList){
				File f = new File(path + imgPath);
				if(f.exists() && f.isFile()){
					try{
						f.delete();
					}catch(Exception e){}
				}
			}
		}
	}

	@Override
	public List<Map<String, Object>> getColorById(Integer id) {
		return bizGoodsColorMapper.getColorById(id);
	}

	@Override
	public List<Map<String, Object>> getSizeById(Integer id) {
		return bizGoodsSizeMapper.getSizeById(id);
	}

	@Override
	public List<Map<String, Object>> getTextureById(Integer id) {
		return bizGoodsTextureMapper.getTextureById(id);
	}
	
	@Override
	public void update(BizGoodsBase bizGoodsBase){
		mapper.updateByPrimaryKey(bizGoodsBase);
	}

	
	@Override
	public List<String> searchAsso(String searchStr){
		return mapper.searchAsso(searchStr);
	}
	
	@Override
	public List<String> searchTexture(Map<String, Object> params){
		return bizGoodsTextureMapper.getNames(params);
	}
	
	@Override
	public List<BasImage> getImageByGoodsId(Integer goodsId){
		return basImageMapper.getByGoodsId(goodsId);
	}
	
	@Override
	public void deleteImage(Integer id){
		BasImage image = basImageMapper.selectByPrimaryKey(id);
		if(image != null){
			basImageMapper.deleteByPrimaryKey(id);
		}
	}
	
	@Override
	public void addImage(BasImage image){
		basImageMapper.insert(image);
	}
	
	public void deleteGoodsAttr(Integer id){
		BizGoodsAttrExt attr = bizGoodsAttrExtMapper.selectByPrimaryKey(id);
		if(attr != null){
			bizGoodsAttrExtMapper.deleteByPrimaryKey(id);
		}
	}
	
	public void addGoodsAttr(BizGoodsAttrExt attr){
		bizGoodsAttrExtMapper.insert(attr);
	}

	@Override
	public List<GoodsListItemResult> searchGoods(Map<String, Object> params){
		if(params == null){
			params = new HashMap<String, Object>();
		}
		Integer page = (Integer)params.get("page");
		Integer size = (Integer)params.get("size");
		if(page != null && size != null && page > 0 && size > 0){
			page = (page-1)*size;
			params.put("pageNum", page);
			params.put("pageSize", size);
		}
		params.put("goodsState", BizGoodsBase.GOODS_STATE_NORMAL);
		return mapper.search(params);
	}
	
	public List<String> searchGoodsTexture(Map<String, Object> params){
		params.put("goodsState", BizGoodsBase.GOODS_STATE_NORMAL);
		return mapper.searchGoodsTexture(params);
	}
	
	@Override
	public BizGoodsBase getDetailById(Integer goodsId){
		return mapper.getDetailById(goodsId);
	}
	
	@Override
	public void addGoodsSize(BizGoodsSize bizGoodsSize){
		bizGoodsSizeMapper.insert(bizGoodsSize);
	}
	
	@Override
	public String deleteGoodsSize(Integer id){
		int count = bizGoodsSkuStockMapper.countBySizeId(id);
		if(count > 0){
			return "该规格已使用，不能删除";
		}else{
			bizGoodsSkuMapper.deleteBySizeId(id);
			bizGoodsSizeMapper.deleteByPrimaryKey(id);
			return null;
		}
		
		
	}
	
	@Override
	public void addGoodsColor(BizGoodsColor bizGoodsColor){
		bizGoodsColorMapper.insert(bizGoodsColor);
	}
	
	@Override
	public String deleteGoodsColor(Integer id){
		int count = bizGoodsSkuStockMapper.countByColorId(id);
		if(count > 0){
			return "该颜色已使用，不能删除";
		}else{
			bizGoodsSkuMapper.deleteByColorId(id);
			bizGoodsColorMapper.deleteByPrimaryKey(id);
			return null;
		}
	}
	
	@Override
	public void addGoodsTexture(BizGoodsTexture bizGoodsTexture){
		bizGoodsTextureMapper.insert(bizGoodsTexture);
	}
	
	@Override
	public String deleteGoodsTexture(Integer id){
		int count = bizGoodsSkuStockMapper.countByTextureId(id);
		if(count > 0){
			return "该材质已使用，不能删除";
		}else{
			bizGoodsSkuMapper.deleteByTextureId(id);
			bizGoodsTextureMapper.deleteByPrimaryKey(id);
			return null;
		}
	}

	@Override
	public GoodsListItemResult getByGoodsIdAndCityId(Integer goodsId, Integer cityId) {
		
		return mapper.getByGoodsIdAndCityId(goodsId,cityId);
		
	}
	
	@Override
	public String goodsNames(String orderIds){
		List<Integer> orderIdList = ListHelper.getIdList(orderIds);
		List<String> names = mapper.getGoodsNameByOrderIds(orderIdList);
		return ListHelper.listIdToString(names);
	}

}
