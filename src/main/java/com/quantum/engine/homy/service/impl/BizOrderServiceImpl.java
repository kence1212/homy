package com.quantum.engine.homy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantum.engine.homy.clientdata.ClientDataException;
import com.quantum.engine.homy.clientdata.ClientDataRuntimeException;
import com.quantum.engine.homy.dao.BizGoodsSkuStockMapper;
import com.quantum.engine.homy.dao.BizOrderHandlerLogMapper;
import com.quantum.engine.homy.dao.BizOrderItemMapper;
import com.quantum.engine.homy.dao.BizOrderMapper;
import com.quantum.engine.homy.dao.BizShoppingCartMapper;
import com.quantum.engine.homy.dao.UserAddressMapper;
import com.quantum.engine.homy.model.BizGoodsSkuStock;
import com.quantum.engine.homy.model.BizOrder;
import com.quantum.engine.homy.model.BizOrderHandlerLog;
import com.quantum.engine.homy.model.BizOrderItem;
import com.quantum.engine.homy.model.BizShoppingCart;
import com.quantum.engine.homy.model.UserAddress;
import com.quantum.engine.homy.model.ext.BizGoodsSkuStockExt;
import com.quantum.engine.homy.model.ext.BizOrderExt;
import com.quantum.engine.homy.model.ext.BizOrderItemExt;
import com.quantum.engine.homy.model.ext.BizOrderListExt;
import com.quantum.engine.homy.model.page.Page;
import com.quantum.engine.homy.model.request.RequestUtil;
import com.quantum.engine.homy.model.result.BaseResponse;
import com.quantum.engine.homy.service.BizOrderService;
import com.quantum.engine.homy.service.BizShoppingCartService;
import com.quantum.engine.homy.util.OrderUtil;

@Service
public class BizOrderServiceImpl implements BizOrderService {

	@Autowired
	private BizOrderMapper bizOrderMapper;
	@Autowired
	BizOrderItemMapper bizOrderItemMapper;
	@Autowired
	BizShoppingCartMapper bizShoppingCartMapper; 
	@Autowired
	BizOrderHandlerLogMapper bizOrderHandlerLogMapper;
	@Autowired
	BizGoodsSkuStockMapper bizGoodsSkuStockMapper;
	@Autowired
	UserAddressMapper userAddressMapper;
	@Autowired
	BizShoppingCartService bizShoppingCartService;
	
	@Override
	public BaseResponse addOrder(Integer addressId, Integer userId, String remark, List<Integer> shoppingCartIds, Integer cityId) throws ClientDataRuntimeException {
		
		List<Map<String,Object>> orderNos = new ArrayList<>();
		
		List<BizShoppingCart> carts = bizShoppingCartMapper.listByIds(shoppingCartIds, userId);
		Set<Integer> brandIds = new HashSet<>();
		for (BizShoppingCart bizShoppingCart : carts) {
			brandIds.add(bizShoppingCart.getBrandId());
		}
		
		for (Integer bid : brandIds) {
			
			//创建订单
			BizOrder bizOrder = new BizOrder();
			bizOrder.setAddressId(addressId);
			bizOrder.setCreateTime(new Date());
			
			bizOrder.setOrderNo(getOnlyOrderNo());
			
			bizOrder.setState(OrderUtil.ORDER_STATE_INIT);
			bizOrder.setUserId(userId);
			bizOrder.setRemark(remark);
			bizOrder.setIsValid(true);
			bizOrderMapper.insert(bizOrder);
			Integer orderId = bizOrder.getId();
			Map<String,Object> map = new HashMap<>();
			map.put("ono", bizOrder.getOrderNo());
			orderNos.add(map);
			Double totalPrice = 0D;
			
			//操作日志
			BizOrderHandlerLog bizOrderHandlerLog = new BizOrderHandlerLog();
			bizOrderHandlerLog.setFinOrderState(OrderUtil.ORDER_STATE_INIT);
			bizOrderHandlerLog.setOrderId(orderId);
			bizOrderHandlerLog.setRecordTime(new Date());
			bizOrderHandlerLog.setUserId(userId);
			bizOrderHandlerLogMapper.insert(bizOrderHandlerLog);
			
			
			//往订单中添加明细
			for (BizShoppingCart cart : carts) {
				if(bid == cart.getBrandId()){
					BizGoodsSkuStockExt bySkuIdAndCityId = bizGoodsSkuStockMapper.getBySkuIdAndCityIdWithSku(cart.getGoodsSkuId(),cityId);
					if(bySkuIdAndCityId != null && bySkuIdAndCityId.getStock() != null && (bySkuIdAndCityId.getStock() >= cart.getNum()||bySkuIdAndCityId.getStock() == -1)){
						//库存足够
							
							//生成订单项
							BizOrderItem item = new BizOrderItem();
							item.setCreateTime(new Date());
							item.setGoodsId(cart.getGoodsId());
							item.setGoodsSkuId(cart.getGoodsSkuId());
							item.setNum(cart.getNum());
							item.setOrderId(orderId);
							item.setPrice(bySkuIdAndCityId.getPrice());
							item.setState(OrderUtil.ORDER_STATE_INIT);
							item.setUserId(userId);
							bizOrderItemMapper.insert(item);
							totalPrice += item.getPrice()*item.getNum();
							
							if(bySkuIdAndCityId.getStock() != -1){
								
								//库存数不为-1时更新库存
								BizGoodsSkuStock bizGoodsSkuStock = new BizGoodsSkuStock();
								bizGoodsSkuStock.setId(bySkuIdAndCityId.getId());
								bizGoodsSkuStock.setStock(bySkuIdAndCityId.getStock() - cart.getNum());
								bizGoodsSkuStockMapper.updateByPrimaryKeySelective(bizGoodsSkuStock);
						
							}
							
							//清空购物车对应项
							bizShoppingCartService.delete(cart.getId()+"", userId);
							
					} else{
						//库存不足够  
						throw new ClientDataRuntimeException("抱歉,"+bySkuIdAndCityId.getGoodsName()+bySkuIdAndCityId.getColorName()+
								bySkuIdAndCityId.getSizeName()+bySkuIdAndCityId.getTextureName()+"库存不足");
					}
				}
			}
			
			//把明细中的总价累加在赋值到订单上
			bizOrder.setTotalPrice(totalPrice);
			bizOrderMapper.updateByPrimaryKeySelective(bizOrder);
			
		}
		BaseResponse br = BaseResponse.getSuccess("提交成功");
		br.setObj(orderNos);
		return br;
	}
	
	@Override
	public List<BizOrderExt> listOrder(List<Integer> orderIds, Integer userId, String state) {
		if(orderIds != null && orderIds.size() == 0){
			orderIds = null;
		}
		List<Integer> stateList = null;
		if(state != null){
			String[] split = state.split(",");
			stateList = new ArrayList<>();
			for (String s : split) {
				stateList.add(Integer.parseInt(s));
			}
		}
		
		List<BizOrderExt> listOrder = bizOrderMapper.listOrder(orderIds,userId, stateList);
		
		return listOrder;
		
	}
	
	public String getOnlyOrderNo(){
		String genOrderNo = OrderUtil.genOrderNo();
		while(true){
				int count  = bizOrderMapper.getCountByOno(genOrderNo);
				if(count == 0){
					return genOrderNo;
				} else{
					genOrderNo = OrderUtil.genOrderNo();
				}
		}
	}
	public String getOnlyOrderTradeNo(){
		String genOrderTradeNo = OrderUtil.genOrderTradeNo();
		while(true){
			int count  = bizOrderMapper.getCountByTno(genOrderTradeNo);
			if(count == 0){
				return genOrderTradeNo;
			} else{
				genOrderTradeNo =  OrderUtil.genOrderTradeNo();
			}
		}
	}

	@Override
	public void cancelOrder(Integer id, Integer userId) {
		
		BizOrder orderInDB = bizOrderMapper.selectByPrimaryKey(id);
		List<Integer> ids = new ArrayList<>();
		ids.add(id);
		List<BizOrderExt> listOrder = listOrder(ids,userId,OrderUtil.ORDER_STATE_INIT+"");
		BizOrderExt order = listOrder.get(0);
		UserAddress address = userAddressMapper.selectByPrimaryKey(order.getAddressId());
		Integer cityId = address.getCityId(); //城市id
		
		//设置订单的状态
		bizOrderMapper.setState(id,OrderUtil.ORDER_STATE_CANCEL);
		
		for (BizOrderItemExt item : order.getItems()) {
			//设置订单明细的状态
			bizOrderItemMapper.setState(item.getId(),OrderUtil.ORDER_STATE_CANCEL);
			
			//查询该明细对应的库存量是否为-1，如果为-1那么不用减少对应的库存
			BizGoodsSkuStock itemStock = bizGoodsSkuStockMapper.getBySkuIdAndCityId(item.getGoodsSkuId(), cityId);
			if( itemStock!=null && itemStock.getStock() != -1){
				//设置对应的库存恢复
				bizGoodsSkuStockMapper.setStock(item.getNum(),cityId,item.getGoodsSkuId());
			}
		}
		
		//操作日志
		BizOrderHandlerLog log = new BizOrderHandlerLog();
		log.setFinOrderState(OrderUtil.ORDER_STATE_CANCEL);
		log.setOrderId(id);
		log.setPreOrderState(orderInDB.getState());
		log.setRecordTime(new Date());
		log.setUserId(userId);
		bizOrderHandlerLogMapper.insert(log);
		
	}

	@Override
	public int deleteOrder(Integer id, Integer userId) {
		int i = bizOrderMapper.deleteOrder(id,userId);
		return i;
		
	}

	@Override
	public Page<BizOrderListExt> getList(Integer page, Integer pageSize, String orderNo, String outTradeNo, Integer state) {
		
		if(orderNo != null && !"".equals(orderNo) ){
			orderNo = "%"+orderNo+"%";
		}
		if(outTradeNo != null && !"".equals(outTradeNo) ){
			outTradeNo = "%"+outTradeNo+"%";
		}
		
		Page<BizOrderListExt> pageData = new Page<BizOrderListExt>(page,pageSize);
		
		int count = bizOrderMapper.getTotalCount(orderNo,outTradeNo,state);
		
		pageData.setTotalCount(count);
		
		if(count == 0){
			pageData.setList(new ArrayList<BizOrderListExt>());
			return pageData;
		}
		
		List<BizOrderListExt> list = bizOrderMapper.getList(page,(page-1)*pageSize,pageSize,orderNo,outTradeNo,state);

		pageData.setList(list);
		
		return pageData;
		
	}

	@Override
	public int confirmDelivery(Integer id, int state, Integer currentUserId) {
		
		BizOrder orderInDB = bizOrderMapper.selectByPrimaryKey(id);	
		
		int i = bizOrderMapper.setState(id, state);
		
		if(i == 1){
			//操作日志
			BizOrderHandlerLog log = new BizOrderHandlerLog();
			log.setFinOrderState(state);
			log.setOrderId(id);
			log.setPreOrderState(orderInDB.getState());
			log.setRecordTime(new Date());
			log.setUserId(currentUserId);
			bizOrderHandlerLogMapper.insert(log);
		}
		
		return i;
		
	}

	@Override
	public int confirmReceive(Integer id, Integer userId, int state) {
		BizOrder orderInDB = bizOrderMapper.selectByPrimaryKey(id);	
		int i = bizOrderMapper.setStateByUser(id, userId, state);
		
		if(i == 1){
			//操作日志
			BizOrderHandlerLog log = new BizOrderHandlerLog();
			log.setFinOrderState(state);
			log.setOrderId(id);
			log.setPreOrderState(orderInDB.getState());
			log.setRecordTime(new Date());
			log.setUserId(userId);
			bizOrderHandlerLogMapper.insert(log);
		}
		
		return i;
	}

	@Override
	public BizOrderExt getDetail(Integer id) {
		return bizOrderMapper.getDetail(id);
	}


}
