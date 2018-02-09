package com.quantum.engine.homy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.quantum.engine.homy.model.BizShoppingCart;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuExt;
import com.quantum.engine.homy.model.ext.BizShoppingCartWithSkuGroupByBrandExt;

public interface BizShoppingCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BizShoppingCart record);

    int insertSelective(BizShoppingCart record);

    BizShoppingCart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BizShoppingCart record);

    int updateByPrimaryKey(BizShoppingCart record);

	void deleteByPrimaryKeyAndUserId(@Param("idList")List<Integer> idList, @Param("userId")Integer userId);

	int updateShoppingCartByUserId(@Param("userId")Integer userId);

	List<BizShoppingCartWithSkuExt> listShoppingCart(@Param("userId")Integer userId,@Param("cartIds")List<Integer> cartIds, @Param("cityId")Integer cityId);

	int updateShoppingCart(@Param("id")Integer id, @Param("userId")Integer userId, @Param("num")Integer num);

	List<BizShoppingCart> listByIds(@Param("shoppingCartIds")List<Integer> shoppingCartIds, @Param("userId")Integer userId);

	BizShoppingCart getBySkuAndUserId(@Param("skuId")Integer skuId, @Param("userId")Integer userId);

}