package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizGoodsStyleMapper;
import com.quantum.engine.homy.model.BizGoodsStyle;
import com.quantum.engine.homy.service.GoodsStyleService;

@Service
public class GoodsStyleServiceImpl implements GoodsStyleService {
	
	@Autowired
	private BizGoodsStyleMapper mapper;

	@Override
	public List<BizGoodsStyle> listAll() {
		return mapper.getAll();
	}
	
	@Override
	public void add(BizGoodsStyle goodsStyle){
		goodsStyle.setCreateTime(new Date());
		mapper.insert(goodsStyle);
	}
	
	@Override
	public int delete(Integer id){
		BizGoodsStyle goodsStyle = mapper.selectByPrimaryKey(id);
		if(goodsStyle == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(id);
			return 1;
		}
	}
	
	@Override
	public BizGoodsStyle getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Map<String, Object>> getOption() {
		return mapper.getOption();
	}

	@Override
	public List<Map<String, Object>> getOptionWithOutSelf(Integer id) {
		List<Map<String,Object>> option = getOption();
		for (Iterator<Map<String,Object>> it = option.iterator(); it.hasNext();) {
			Map<String, Object> next = it.next();
			if((next.get("id")+"").equals(id+"")){
				it.remove();
			}
		}
		return option;
	}

	@Override
	public void update(BizGoodsStyle goodsStyle) {
		BizGoodsStyle goodsStyleInDB = getById(goodsStyle.getId());
		
		goodsStyle.setCreateTime(goodsStyleInDB.getCreateTime());
		goodsStyle.setCreateId(goodsStyleInDB.getCreateId());
		goodsStyle.setModifyTime(new Date());
		
		mapper.updateByPrimaryKeyWithBLOBs(goodsStyle);
	}
	

}
