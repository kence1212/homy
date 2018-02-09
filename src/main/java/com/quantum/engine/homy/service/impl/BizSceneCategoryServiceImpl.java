package com.quantum.engine.homy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizSceneInitMapper;
import com.quantum.engine.homy.dao.BizSceneStyleMapper;
import com.quantum.engine.homy.constants.Constants;
import com.quantum.engine.homy.dao.BizSceneCategoryMapper;
import com.quantum.engine.homy.model.BizSceneInit;
import com.quantum.engine.homy.model.BizSceneCategory;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.service.BizSceneCategoryService;

@Service
public class BizSceneCategoryServiceImpl implements BizSceneCategoryService {
	
	@Autowired
	private BizSceneCategoryMapper mapper;
	@Autowired
	private BizSceneStyleMapper styleMapper;
	
	@Override
	public void add(BizSceneCategory sceneStyle){
		
		mapper.insert(sceneStyle);
		
	}
	
	@Override
	public int delete(Integer id){
		BizSceneCategory bizSceneCategory = mapper.selectByPrimaryKey(id);
		if(bizSceneCategory == null){
			return 0;
		}else{
			
			//如果风格中有关联该分类不可以删除
			int count = styleMapper.getCountByCategoryId(bizSceneCategory.getId());
			if(count > 0){
				return 1;
			}
					
			bizSceneCategory.setIsValid(Constants.UNVALID);
				mapper.updateByPrimaryKeySelective(bizSceneCategory);
				return 2;
		}
	}
	
	@Override
	public BizSceneCategory getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<BizSceneCategory> getList(String keyword, Integer page, Integer pageSize) {
		
		if(keyword != null && !"".equals(keyword)){
			keyword = "%"+keyword+"%";
		}
		
		Page<BizSceneCategory> pageData = new Page(page,pageSize);
		
		int count = mapper.getCount(keyword);
		
		if(count == 0){
			Page<BizSceneCategory> nullPage = Page.getNullPage();
			return nullPage;
		}
		
		pageData.setTotalCount(count);
		
		List<BizSceneCategory> list = mapper.getList(keyword, page,(page-1)*pageSize,pageSize);
		
		pageData.setList(list);
		
		return pageData;
		
	}

	@Override
	public void update(BizSceneCategory bizSceneStyle) {
		
		BizSceneCategory sceneCategoryInDB = mapper.selectByPrimaryKey(bizSceneStyle.getId());
		bizSceneStyle.setCreateId(sceneCategoryInDB.getCreateId());
		bizSceneStyle.setCreateTime(sceneCategoryInDB.getCreateTime());
		mapper.updateByPrimaryKeySelective(bizSceneStyle);
		
	}


}
