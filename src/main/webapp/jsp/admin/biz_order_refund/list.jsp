<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="test" uri="http://edu.study.struts/permissionUtil" %>  
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
</head>
<body>
<div class="main-wrap">
	<div class="page-wrap">
		<!--开始::内容-->
		<section class="page-hd">
			<header>
				<h2 class="title">退货订单列表</h2>
				<p class="title-description">
				</p>
			</header>
			<hr>
		</section>
		<div class="clear mt-10 mb-10">
			<!-- <div class="fr input-group">
			状态：<select id="state" style="width:120px;">
				<option value="">--请选择--</option>
				<option value="0">订单取消</option>
				<option value="1">订单生成</option>
				<option value="2">订单已支付</option>
				<option value="3">订单已发货</option>
				<option value="4">订单已收货</option>
				<option value="5">订单已评价</option>
				<option value="6">订单退货</option>
				<option value="7">订单已退货</option>
			</select>
			<input id="orderNo" type="text" class="form-control" placeholder="订单号..." style="width:290px;">
			<input id="outTradeNo" type="text" class="form-control" placeholder="交易号..." style="width:290px;">
			<button class="btn btn-secondary-outline" onclick="search()">搜索</button>
			</div> -->
		</div>
		<table class="table mb-15">
			<thead>
			<tr>
				<th><input type="checkbox"/></th>
				<th>订单号</th>
				<th>金额</th>
				<th>状态</th>
				<th>申请时间</th>
				<th>退款原因</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
		</table>
	</div>
	 <div class="panel panel-default">
         <div class="panel-bd">
             <div class="pagination table-pagination mt-5 mb-10"></div>
         </div>
     </div>
</div>

</body>
<script type="text/javascript">
	
function search(){
	 $('.pagination').unbind('click');
	 $.post("<%=request.getContextPath()%>"+"/amorderRefund/getList.html",
			 {},
			function(data){
				 $('#tbody').html('');
				    var listPageCount = 10,length = data.totalCount; //每页数量，总共记录数
				    var pageCount = Math.ceil(length/listPageCount); //页数
				    var html2 = '';
				    var json = data.list;
				    for(var x in json){
				    	var stateStr = "";
				    	if(json[x].state == 0){
				    		stateStr = "退款申请";
				    	}else if(json[x].state == 1){
				    		stateStr = "同意退款";
				    	}else if(json[x].state == 2){
				    		stateStr = "驳回";
				    	}else if(json[x].state == 3){
				    		stateStr = "退款成功";
				    	}else if(json[x].state == 10){
				    		stateStr = "申请取消";
				    	}
				        html2 += '<tr class="cen">'
				                +'<td><input type="checkbox"/></td>'
				                +'<td>'+json[x].orderNo+'</td>'
				                +'<td>'+json[x].refundAmount+'</td>'
				                +'<td>'+stateStr+'</td>'
				                +'<td>'+json[x].createTime+'</td>'
				                +'<td>'+json[x].refundReason+'</td>'
				                html2 +='<td><a title="详情" class="mr-5" href="">详情</a></td>'
					                +'</tr>';
				    }
				    $('#tbody').html(html2);
				    $(".pagination").createPage({
				        pageCount:pageCount,
				        current:1,
				        backFn:function(p){
						 $.post("<%=request.getContextPath()%>"+"/amorderRefund/getList.html",
								 {
									 page:p,
									 pageSize:10
									 },
								 function(data){
									 var html = '';
									 var json = data.list;
								     for(var x in json){
								    	 var stateStr = "";
								    	if(json[x].state == 0){
								    		stateStr = "退款申请";
								    	}else if(json[x].state == 1){
								    		stateStr = "同意退款";
								    	}else if(json[x].state == 2){
								    		stateStr = "驳回";
								    	}else if(json[x].state == 3){
								    		stateStr = "退款成功";
								    	}else if(json[x].state == 10){
								    		stateStr = "申请取消";
								    	}
								         html +=  '<tr class="cen">'
								        	 +'<td><input type="checkbox"/></td>'
								                +'<td>'+json[x].orderId+'</td>'
								                +'<td>'+json[x].refundAmount+'</td>'
								                +'<td>'+stateStr+'</td>'
								                +'<td>'+json[x].createTime+'</td>'
								                +'<td>'+json[x].refundReason+'</td>'
								        html +='<td><a title="详情" class="mr-5" href="">详情</a></td>'
								                +'</tr>';
								     }
								     $('#tbody').html(html);
								 },"json"
								 )
									        }
									    });
									    
									    
								 },"json"	 
						 )
	
}   

search();
	
</script>
</html>