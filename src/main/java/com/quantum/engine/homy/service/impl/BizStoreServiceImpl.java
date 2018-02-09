package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizStoreMapper;
import com.quantum.engine.homy.model.BizStore;
import com.quantum.engine.homy.service.BizStoreService;

@Service("bizStoreService")
public class BizStoreServiceImpl implements BizStoreService {
	
	@Autowired
	private BizStoreMapper mapper;

	@Override
	public BizStore getById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BizStore> getAll() {
		return mapper.listAll();
	}

	@Override
	public void update(BizStore store) {
		if(store != null && store.getId() != null){
			BizStore source = mapper.selectByPrimaryKey(store.getId());
			if(source != null){
				source.setModifyId(store.getModifyId());
				source.setModifyTime(new Date());
				source.setStoreDesc(store.getStoreDesc());
				source.setStoreIcon(store.getStoreIcon());
				source.setStoreName(store.getStoreName());
				mapper.updateByPrimaryKey(source);
			}
		}
	}

	@Override
	public void add(BizStore store) {
		mapper.insert(store);
	}

	@Override
	public int delete(Integer id) {
		if(id == null){
			return 0;
		}
		BizStore store = mapper.selectByPrimaryKey(id);
		if(store == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(id);
			return 1;
		}
	}

}
