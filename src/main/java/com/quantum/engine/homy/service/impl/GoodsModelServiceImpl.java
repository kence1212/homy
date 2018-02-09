package com.quantum.engine.homy.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizGoodsModelMapper;
import com.quantum.engine.homy.img.Base64Image;
import com.quantum.engine.homy.model.BizGoodsModel;
import com.quantum.engine.homy.model.dto.PageListQueryDto;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.service.BizGoodsService;
import com.quantum.engine.homy.service.GoodsModelService;

@Service
public class GoodsModelServiceImpl implements GoodsModelService {

	@Autowired
	private BizGoodsModelMapper mapper;

	@Autowired
	private BizGoodsService bizGoodsService;

	@Override
	public List<BizGoodsModel> listAll() {
		return mapper.getAll();
	}

	@Override
	public BaseResponse add(BizGoodsModel goodsModel, HttpServletRequest request) {
		goodsModel.setState(BizGoodsModel.MODEL_STATE_NORMAL);
		if(goodsModel.getType() == 2){
			//如果type是2的话把颜色、尺寸、材质设置为null
			goodsModel.setColorId(null);
			goodsModel.setSizeId(null);
			goodsModel.setTextureId(null);
		} else{
			//如果type是1的话去数据库查询是否有同样的记录
			BizGoodsModel bizGoodsModel = mapper.getByProperties(goodsModel.getGoodsId(),goodsModel.getColorId(),goodsModel.getSizeId(),goodsModel.getTextureId(),null);
			if(bizGoodsModel != null){
				return BaseResponse.getWrong("相同的商品、颜色、尺寸、材质只能有一个模型");
			}
		}
		
		// 处理Base64Img
		String photoPath = Base64Image.getPhotoPath("/goods_model_icon", request, goodsModel.getModelIcon());
		System.out.println(Base64Image.GenerateImage(
				goodsModel.getModelIcon().substring(goodsModel.getModelIcon().indexOf(",") + 1), photoPath));

		String newPath = Base64Image.scale(photoPath, 362, 268);
		File file = new File(photoPath);
		file.delete();

		int indexOf2 = newPath.indexOf("/upload");
		String moldelIconPath = newPath.substring(indexOf2);
		goodsModel.setModelIcon(moldelIconPath);

		goodsModel.setCreateTime(new Date());
		mapper.insert(goodsModel);
		bizGoodsService.setHash3d(goodsModel.getGoodsId());
		return BaseResponse.getSuccess("保存成功");
	}

	@Override
	public int delete(Integer id) {
		 BizGoodsModelExt goodsModel = mapper.selectByPrimaryKey(id);
		if (goodsModel == null) {
			return 0;
		} else {
			mapper.deleteByPrimaryKey(id);
			int count = mapper.selectCountByGoodId(goodsModel.getGoodsId());
			if(count == 0){
				bizGoodsService.removeHash3d(goodsModel.getGoodsId());
			}
			return 1;
		}
	}

	@Override
	public BizGoodsModelExt getById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public void updateState(BizGoodsModel goodsModel){
		mapper.updateState(goodsModel);
	}

	@Override
	public BaseResponse update(BizGoodsModel goodsModel, HttpServletRequest request) {

		if(goodsModel.getType() == 2){
			//如果type是2的话把颜色、尺寸、材质设置为null
			goodsModel.setColorId(null);
			goodsModel.setSizeId(null);
			goodsModel.setTextureId(null);
		} else{
			//如果type是1的话去数据库查询是否有同样的记录
			BizGoodsModel bizGoodsModel = mapper.getByProperties(goodsModel.getGoodsId(),goodsModel.getColorId(),goodsModel.getSizeId(),goodsModel.getTextureId(),goodsModel.getId());
			if(bizGoodsModel != null){
				return BaseResponse.getWrong("相同的商品、颜色、尺寸、材质只能有一个模型");
			}
		}
		
		if (goodsModel.getModelIcon().startsWith("data:image")) {
			String photoPath = Base64Image.getPhotoPath("/goods_model_icon", request, goodsModel.getModelIcon());
			System.out.println(Base64Image.GenerateImage(
					goodsModel.getModelIcon().substring(goodsModel.getModelIcon().indexOf(",") + 1), photoPath));

			String newPath = Base64Image.scale(photoPath, 362, 268);
			File file = new File(photoPath);
			file.delete();

			int indexOf2 = newPath.indexOf("/upload");
			String moldelIconPath = newPath.substring(indexOf2);
			goodsModel.setModelIcon(moldelIconPath);
		}

		BizGoodsModelExt goodsModelInDB = getById(goodsModel.getId());

		goodsModel.setCreateTime(goodsModelInDB.getCreateTime());
		goodsModel.setCreateId(goodsModelInDB.getCreateId());
		goodsModel.setModifyTime(new Date());

		if (goodsModel.getGoodsId().equals(goodsModelInDB.getGoodsId())) {

		} else {
			int count = mapper.selectCountByGoodId(goodsModelInDB.getGoodsId());
			if(count == 0){
				bizGoodsService.removeHash3d(goodsModelInDB.getGoodsId());
			}
			bizGoodsService.setHash3d(goodsModel.getGoodsId());
		}

		
		goodsModel.setState(goodsModelInDB.getState());
		
		mapper.updateByPrimaryKey(goodsModel);
		return BaseResponse.getSuccess("编辑成功");
	}

	@Override
	public List<BizGoodsModelExt> listAllWithGood() {
		return mapper.getList(0, 0, 0, "");
	}

	@Override
	public List<BizGoodsModel> getByGoodsId(Integer goodsId){
		List<BizGoodsModel> list =  mapper.getByGoodsId(goodsId);
		return list;
	}

	public static void main(String[] args) {
		String photoPath = "/usr/local/tomcat/apache-tomcat-7.0.82/webapps/ROOT/upload/goods_model_icon/2017/11/29/885a5f0d-bb61-4c3a-9953-4264eb4c9c5c.jpeg";
		int indexOf2 = photoPath.indexOf("/upload");
		String moldelIconPath = photoPath.substring(indexOf2);
		System.out.println(moldelIconPath);
	}

	@Override
	public Page<BizGoodsModelExt> getList(PageListQueryDto dto) {
		
		String keyword = dto.getKeyword();
		
		if(keyword != null && keyword != ""){
			keyword = "%"+keyword+"%";
		}
		
		Page<BizGoodsModelExt> page = new Page<>(dto.getPage(), dto.getPageSize());
		
		int totalCount = mapper.getTotalCount(keyword);
		
		page.setTotalCount(totalCount);
		
		if(totalCount == 0){
			
			page.setList(new ArrayList<BizGoodsModelExt>());
			
			return page;
			
		}
		
		List<BizGoodsModelExt> list = mapper.getList(dto.getPage(),(dto.getPage()-1)*dto.getPageSize(),dto.getPageSize(),keyword);
		
		page.setList(list);
		
		return page;
		
	}

}
