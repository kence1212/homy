package com.quantum.engine.homy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizSceneInitMapper;
import com.quantum.engine.homy.dao.BizSceneStyleMapper;
import com.quantum.engine.homy.model.BizSceneInit;
import com.quantum.engine.homy.model.BizSceneStyle;
import com.quantum.engine.homy.model.ext.BizSceneStyleExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.service.BizSceneStyleService;

@Service
public class BizSceneStyleServiceImpl implements BizSceneStyleService {
	
	@Autowired
	private BizSceneStyleMapper mapper;
	@Autowired
	BizSceneInitMapper bizSceneInitMapper;
	
	@Override
	public void add(BizSceneStyle sceneStyle, Integer sceneinitId){
		
		BizSceneInit sceneInitInDB = bizSceneInitMapper.selectByPrimaryKey(sceneinitId);
		sceneStyle.setSceneInfo(sceneInitInDB.getSceneInfo());
		mapper.insert(sceneStyle);
		
	}
	
	@Override
	public int delete(Integer id){
		BizSceneStyle bizSceneStyle = mapper.selectByPrimaryKey(id);
		if(bizSceneStyle == null){
			return 0;
		}else{
			
				mapper.deleteByPrimaryKey(id);
				return 1;
		}
	}
	
	@Override
	public BizSceneStyle getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<BizSceneStyleExt> getList(String keyword, Integer page, Integer pageSize) {
		
		if(keyword != null && !"".equals(keyword)){
			keyword = "%"+keyword+"%";
		}
		
		Page<BizSceneStyleExt> pageData = new Page(page,pageSize);
		
		int count = mapper.getCount(keyword);
		
		if(count == 0){
			Page<BizSceneStyleExt> nullPage = Page.getNullPage();
			return nullPage;
		}
		
		pageData.setTotalCount(count);
		
		List<BizSceneStyleExt> list = mapper.getList(keyword, page,(page-1)*pageSize,pageSize);
		
		pageData.setList(list);
		
		return pageData;
		
	}

	@Override
	public void update(BizSceneStyle bizSceneStyleInDB, Integer sceneInitId) {
		BizSceneInit bizSceneInitInDB = bizSceneInitMapper.selectByPrimaryKey(sceneInitId);
		bizSceneStyleInDB.setSceneInfo(bizSceneInitInDB.getSceneInfo());
		mapper.updateByPrimaryKeySelective(bizSceneStyleInDB);
	}

	@Override
	public int setState(Integer id, Integer state) {
		BizSceneStyle bizSceneStyle = mapper.selectByPrimaryKey(id);
		if(bizSceneStyle == null){
			return 0;
		}else{
			bizSceneStyle.setState(state);
			mapper.updateByPrimaryKeySelective(bizSceneStyle);
			return 1;
		}
	}

	@Override
	public List<BizSceneStyleExt> getStyleList() {
		return mapper.getStyleList();
	}
	

}
