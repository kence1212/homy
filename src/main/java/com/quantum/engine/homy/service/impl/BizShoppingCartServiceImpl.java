package com.quantum.engine.homy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.dao.BizGoodsBaseMapper;
import com.quantum.engine.homy.dao.BizGoodsModelMapper;
import com.quantum.engine.homy.dao.BizGoodsSkuMapper;
import com.quantum.engine.homy.dao.BizShoppingCartMapper;
import com.quantum.engine.homy.model.BizGoodsBase;
import com.quantum.engine.homy.model.BizGoodsSku;
import com.quantum.engine.homy.model.BizShoppingCart;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuGroupByBrandExt;
import com.quantum.engine.homy.model.result.ShoppingCartBrandItem;
import com.quantum.engine.homy.model.result.ShoppingCartBrandResult;
import com.quantum.engine.homy.service.BizShoppingCartService;
import com.quantum.engine.homy.util.ListHelper;

@Service
public class BizShoppingCartServiceImpl implements BizShoppingCartService {

	@Autowired
	private BizShoppingCartMapper bizShoppingCartMapper;
	@Autowired
	private BizGoodsSkuMapper bizGoodsSkuMapper;
	@Autowired
	private BizGoodsBaseMapper bizGoodsBaseMapper;
	@Autowired
	private BizGoodsModelMapper BizGoodsModelMapper;
	@Value("#{dataSourceProps['base.url']}")
	private String baseUrl;
	
	@Override
	public int addByNowShopCart(BizShoppingCartExt bizShoppingCartExt) {
		
		//生成一条购物车项  TODO 如果购物车中有同条sku那么直接把数量修改成立即购买的数量
		//1.根据userId、skuId查询是否在购物车中是否已经有了
		BizGoodsSku goodsSku = bizGoodsSkuMapper.getByGoodsIdAndProperties(bizShoppingCartExt.getGoodsId(), bizShoppingCartExt.getColorId(), bizShoppingCartExt.getSizeId(), bizShoppingCartExt.getTextureId());
		if(goodsSku == null){
			return 0;
		}
		BizShoppingCart shoppingCartItem = bizShoppingCartMapper.getBySkuAndUserId(goodsSku.getId(), bizShoppingCartExt.getUserId());
		if(shoppingCartItem != null){
			//2.如果购物车中存在则把数量修改为立即购买的数量
			shoppingCartItem.setNum(bizShoppingCartExt.getNum());
			bizShoppingCartMapper.updateByPrimaryKeySelective(shoppingCartItem);
			return shoppingCartItem.getId();
			
		} else{
			
			//3.生成一条购物车记录
			BizGoodsBase goodsInDB = bizGoodsBaseMapper.selectByPrimaryKey(bizShoppingCartExt.getGoodsId());
			int cartId = insertShoppingCart(bizShoppingCartExt, goodsSku, goodsInDB);
			return cartId;
			
		}
		
		
	}

	
	
	@Override
	public void addShoppingCart(BizShoppingCartExt bizShoppingCartExt) {
		
		BizGoodsSku bizGoodsSku = bizGoodsSkuMapper.getByGoodsIdAndProperties(bizShoppingCartExt.getGoodsId(),bizShoppingCartExt.getColorId(),bizShoppingCartExt.getSizeId(),bizShoppingCartExt.getTextureId());
		
		BizGoodsBase goodsInDB = bizGoodsBaseMapper.selectByPrimaryKey(bizShoppingCartExt.getGoodsId());
		
		BizShoppingCart bizShoppingCartInDB = bizShoppingCartMapper.getBySkuAndUserId(bizGoodsSku.getId(),bizShoppingCartExt.getUserId());
		
		if(bizShoppingCartInDB == null || bizShoppingCartInDB.getIsValid() == 0){
			
			insertShoppingCart(bizShoppingCartExt, bizGoodsSku, goodsInDB);
			
		} else{
			bizShoppingCartInDB.setNum(bizShoppingCartInDB.getNum()+bizShoppingCartExt.getNum());
			bizShoppingCartMapper.updateByPrimaryKeySelective(bizShoppingCartInDB);
		}
	}



	private int insertShoppingCart(BizShoppingCartExt bizShoppingCartExt, BizGoodsSku bizGoodsSku,
			BizGoodsBase goodsInDB) {
		BizShoppingCart bizShoppingCart = new BizShoppingCart();
		bizShoppingCart.setBrandId(goodsInDB.getBrandId());
		bizShoppingCart.setCreateTime(new Date());
		bizShoppingCart.setGoodsId(bizShoppingCartExt.getGoodsId());
		bizShoppingCart.setGoodsSkuId(bizGoodsSku.getId());
		bizShoppingCart.setIsValid(BizShoppingCart.VALID);
		bizShoppingCart.setNum(bizShoppingCartExt.getNum());
		bizShoppingCart.setUserId(bizShoppingCartExt.getUserId());
		bizShoppingCartMapper.insert(bizShoppingCart);
		return bizShoppingCart.getId();
	}

	@Override
	public void delete(String id, Integer userId) {
	
		List<Integer> idList = ListHelper.getIdList(id);
		
		bizShoppingCartMapper.deleteByPrimaryKeyAndUserId(idList,userId);
		
	}

	@Override
	public int clearShoppingCart(Integer userId) {
		
		return bizShoppingCartMapper.updateShoppingCartByUserId(userId);
		
	}

	@Override
	public List<ShoppingCartBrandResult>  listShoppingCart(Integer userId, List<Integer> cartIds, Integer cityId) {
		if(cartIds != null && cartIds.size() == 0){
			cartIds = null;
		}
		
		
		List<BizShoppingCartWithSkuExt> listShoppingCart = bizShoppingCartMapper.listShoppingCart(userId,cartIds,cityId);
		Set<Integer> brandIds = new HashSet<>();
		for (BizShoppingCartWithSkuExt cartItem : listShoppingCart) {
			brandIds.add(cartItem.getBrandId());
		}
		
		List<ShoppingCartBrandResult> cartGropByBrandId = new ArrayList<>();
		for (Integer bid : brandIds) {
			ShoppingCartBrandResult bizShoppingCartWithSkuGroupByBrandExt = new ShoppingCartBrandResult();
			bizShoppingCartWithSkuGroupByBrandExt.setBrandId(bid);
			List<ShoppingCartBrandItem> items = new ArrayList<>();
			Double brandTotalPrice = 0D;
			for (BizShoppingCartWithSkuExt cartItem : listShoppingCart) {
				if(bid.equals(cartItem.getBrandId())){
					bizShoppingCartWithSkuGroupByBrandExt.setBrandName(cartItem.getBrandName());
					ShoppingCartBrandItem shoppingCartBrandItem = new ShoppingCartBrandItem();
					
					shoppingCartBrandItem.setColorId(cartItem.getBizGoodsSku().getColorId());
					shoppingCartBrandItem.setColorName(cartItem.getBizGoodsSku().getColorName());
					shoppingCartBrandItem.setGoodsId(cartItem.getGoodsId());
					shoppingCartBrandItem.setGoodsName(cartItem.getBizGoodsSku().getGoodsName());
					String skuIcon = cartItem.getBizGoodsSku().getSkuIcon();
					if(skuIcon != null){
						skuIcon = baseUrl + skuIcon ; 
					}
					shoppingCartBrandItem.setIcon(skuIcon);
					shoppingCartBrandItem.setNum(cartItem.getNum());
					if(cartItem.getNum() > cartItem.getStock() && cartItem.getStock() != -1){
						shoppingCartBrandItem.setNum(cartItem.getStock());
						//跟新数据库中对应item的数量
						updateShoppingCart(cartItem.getId(),userId,cartItem.getStock());
					}
					shoppingCartBrandItem.setPrice(cartItem.getPrice());
					shoppingCartBrandItem.setSizeId(cartItem.getBizGoodsSku().getSizeId());
					shoppingCartBrandItem.setSizeName(cartItem.getBizGoodsSku().getSizeName());
					shoppingCartBrandItem.setTextureId(cartItem.getBizGoodsSku().getTextureId());
					shoppingCartBrandItem.setTextureName(cartItem.getBizGoodsSku().getTextureName());
					shoppingCartBrandItem.setStock(cartItem.getStock());
					shoppingCartBrandItem.setId(cartItem.getId());
					
					brandTotalPrice += shoppingCartBrandItem.getPrice()*shoppingCartBrandItem.getNum();
					
					items.add(shoppingCartBrandItem);
				}
			}
			bizShoppingCartWithSkuGroupByBrandExt.setItems(items);
			bizShoppingCartWithSkuGroupByBrandExt.setBrandTotalPrice(brandTotalPrice);
			cartGropByBrandId.add(bizShoppingCartWithSkuGroupByBrandExt);
		}
		
		
		
		return cartGropByBrandId;
		
	}

	
	@Override
	public int updateShoppingCart(Integer id, Integer userId, Integer num) {
		
		return bizShoppingCartMapper.updateShoppingCart(id,userId,num);
		
	}

	@Override
	public void addModelShoppingCart(String modelIds, Integer userId) {
		
		String[] split = modelIds.split(",");
		Set<Integer> uniqueIds = new HashSet<>();
		List<Integer> arrayIds = new ArrayList<>();
		for (String string : split) {
			int id = Integer.parseInt(string);
			uniqueIds.add(id);
			arrayIds.add(id);
		}
		
		for (Integer id : uniqueIds) {
			int num = 0;
			for (Integer integer2 : arrayIds) {
				if(id.equals(integer2)){
					++ num;
				}
			}
			BizGoodsModelExt goodModel = BizGoodsModelMapper.selectByPrimaryKey(id);
			if(goodModel != null){
				
				BizGoodsSku bizGoodsSku = bizGoodsSkuMapper.getByGoodsIdAndProperties(goodModel.getGoodsId(), goodModel.getColorId(), goodModel.getSizeId(), goodModel.getTextureId());
				if(bizGoodsSku != null){
					
					BizShoppingCart bySkuAndUserId = bizShoppingCartMapper.getBySkuAndUserId(bizGoodsSku.getId(), userId);
					if(bySkuAndUserId != null){
						bySkuAndUserId.setNum(bySkuAndUserId.getNum() + num);
						bizShoppingCartMapper.updateByPrimaryKeySelective(bySkuAndUserId);
					} else{
						
						BizGoodsBase goods = bizGoodsBaseMapper.selectByPrimaryKey(goodModel.getGoodsId());
						BizShoppingCart bizShoppingCart = new BizShoppingCart();
						bizShoppingCart.setBrandId(goods.getBrandId());
						bizShoppingCart.setCreateTime(new Date());
						bizShoppingCart.setGoodsId(goods.getId());
						bizShoppingCart.setGoodsSkuId(bizGoodsSku.getId());
						bizShoppingCart.setIsValid(1);
						bizShoppingCart.setNum(num);
						bizShoppingCart.setUserId(userId);
						
						bizShoppingCartMapper.insert(bizShoppingCart);
					}
					
				}
					
					
			}	
		}
		
	}

	
}
