package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.model.BizShoppingCart;
import com.quantum.engine.homy.model.ext.BizShoppingCartExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuGroupByBrandExt;
import com.quantum.engine.homy.model.result.ShoppingCartBrandResult;

public interface BizShoppingCartService {

	void addShoppingCart(BizShoppingCartExt bizShoppingCartExt);

	void delete(String id, Integer userId);

	int clearShoppingCart(Integer userId);

	List<ShoppingCartBrandResult>  listShoppingCart(Integer userId , List<Integer> cartIds, Integer cityId);
	
	int updateShoppingCart(Integer id, Integer userId, Integer num);

	void addModelShoppingCart(String modelIds, Integer userId);

	int addByNowShopCart(BizShoppingCartExt bizShoppingCartExt);


}
