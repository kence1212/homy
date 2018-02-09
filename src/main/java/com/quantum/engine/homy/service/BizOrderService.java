package com.quantum.engine.homy.service;

import java.util.List;

import com.quantum.engine.homy.clientdata.ClientDataException;
import com.quantum.engine.homy.clientdata.ClientDataRuntimeException;
import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.model.ext.BizGoodsModelExt;
import com.quantum.engine.homy.model.ext.BizOrderExt;
import com.quantum.engine.homy.model.ext.BizOrderListExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.result.BaseResponse;

public interface BizOrderService {

	List<BizOrderExt> listOrder(List<Integer> orderIds, Integer userId, String string);

	BaseResponse addOrder(Integer addressId, Integer userId, String remark, List<Integer> shoppingCartIds, Integer cityId) throws ClientDataRuntimeException ;

	void cancelOrder(Integer id, Integer userId);

	int deleteOrder(Integer id, Integer userId);
	
	String getOnlyOrderTradeNo();

	Page<BizOrderListExt> getList(Integer page, Integer pageSize, String orderNo, String outTradeNo, Integer state);

	int confirmDelivery(Integer id, int state, Integer currentUserId);

	/**
	 * 
	 * @author fzz
	 * @date 2018年1月3日
	 * @description 用户设置状态
	 */
	int confirmReceive(Integer id, Integer userId, int state);

	BizOrderExt getDetail(Integer id);

}
