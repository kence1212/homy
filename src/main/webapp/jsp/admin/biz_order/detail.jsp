<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台管理系统-HTML5后台管理系统</title>
<meta name="keywords"  content="设置关键词..." />
<meta name="description" content="设置描述..." />
<meta name="author" content="DeathGhost" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<jsp:include page="../common/_cssjs.jsp"></jsp:include>
<jsp:include page="../common/_validformcssjs.jsp"></jsp:include>
</head>
<body>
<div class="main-wrap">
	<div class="page-wrap">
		<div class="clear mb-10">
			<div class="fl">
				<a href="index.html"><button class="btn btn-secondary" ><i class="icon-chevron-left"></i>返回</button></a>
			</div>
		</div>
		<!--开始::内容-->
		<section class="page-hd">
			<header>
				<h2 class="title">订单详情</h2>
			</header>
			<hr>
		</section>
		<table class="table mb-15" style="width: 600px">
			<tbody>
			<tr>
				<td>用户ID：${order.userId }</td>
				<td>订单号：${order.orderNo}</td>
			</tr>
			<tr>
				<td>交易号：${order.outTradeNo }</td>
				<td>下单时间：<fmt:formatDate value="${order.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td>订单总金额：${order.totalPrice}</td>
				<td>收货地址：${address.provinceName}${address.cityName}${address.areaName}${address.addressDetail}</td>
			</tr>
			<tr>
				<td>订单状态：${order.stateStr}</td>
				<td>支付方式：<c:if test="${order.payType == null}">未支付</c:if><c:if test="${order.payType == 0 }">支付宝支付</c:if><c:if test="${order.payType == 1}">微信支付</c:if></td>
			</tr>
			<tr>
				<td>支付时间：<fmt:formatDate value="${order.payTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>订单备注：${order.remark}</td>
			</tr>
			<tr>
				<td>用户是否删除：<c:if test="${order.isValid == false}">是</c:if><c:if test="${order.isValid == true}">否</c:if></td>
				<td>品牌：${order.items[0].bizGoodsSku.brandName }</td>
			</tr>
			</tbody>
		</table>
		<table class="table mb-15">
			<thead>
			<tr>
				<th>商品</th>
				<th>数量</th>
				<th>单价</th>
				<th>小计</th>
			</tr>
			</thead>
			<tbody id="tbody">
			<c:forEach items="${order.items }" var="bean">
				<tr class="cen">
					<td>${bean.bizGoodsSku.goodsName }  ${bean.bizGoodsSku.colorName }  ${bean.bizGoodsSku.sizeName }  ${bean.bizGoodsSku.textureName }</td>
					<td>${bean.num}</td>
					<td>${bean.price}</td>
					<td>
						${bean.num*bean.price}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<table class="table mb-15">
			<thead>
			<tr>
				<th>订单状态</th>
				<th>操作时间</th>
				<th>操作人ID</th>
				<th>操作人</th>
				<th>备注</th>
			</tr>
			</thead>
			<tbody id="tbody">
			<c:forEach items="${logs }" var="bean">
			<tr class="cen">
				<td>${bean.finOrderStateStr }</td>
				<td><fmt:formatDate value="${bean.recordTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${bean.userId }</td>
				<td>${bean.userName }</td>
				<td>${bean.remark }</td>
			<tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div class="mask"></div>
</body>
<script type="text/javascript">
</script>
</html>