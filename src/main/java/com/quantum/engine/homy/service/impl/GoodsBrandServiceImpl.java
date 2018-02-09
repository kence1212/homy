package com.quantum.engine.homy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BasGoodsBrandMapper;
import com.quantum.engine.homy.model.BasGoodsBrand;
import com.quantum.engine.homy.service.GoodsBrandService;

@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {
	
	@Autowired
	private BasGoodsBrandMapper mapper;

	@Override
	public List<BasGoodsBrand> listAll() {
		return mapper.getAll();
	}
	
	public List<BasGoodsBrand> listIndexHot(){
		return mapper.listIndexHot();
	}
	
	@Override
	public void add(BasGoodsBrand goodsBrand){
		goodsBrand.setCreateTime(new Date());
		mapper.insert(goodsBrand);
	}
	
	@Override
	public int delete(Integer id){
		BasGoodsBrand brand = mapper.selectByPrimaryKey(id);
		if(brand == null){
			return 0;
		}else{
			mapper.deleteByPrimaryKey(id);
			return 1;
		}
	}
	
	@Override
	public BasGoodsBrand getById(Integer id){
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void update(BasGoodsBrand brand){
		if(brand!=null && brand.getBrandId() != null){
			BasGoodsBrand source = mapper.selectByPrimaryKey(brand.getBrandId());
			if(source != null){
				source.setModifyId(brand.getCreateId());
				source.setModifyTime(new Date());
				source.setBrandCode(brand.getBrandCode());
				source.setBrandDesc(brand.getBrandDesc());
				source.setBrandName(brand.getBrandName());
				source.setIcon(brand.getIcon());
				source.setRemark(brand.getRemark());
				source.setTsort(brand.getTsort());
				source.setAppClientIcon(brand.getAppClientIcon());
				mapper.updateByPrimaryKey(source);
			}
		}
		
	}
	
	@Override
	public List<BasGoodsBrand> listHave3d(Map<String, Object> params){
		return mapper.listHave3d(params);
	}

}
