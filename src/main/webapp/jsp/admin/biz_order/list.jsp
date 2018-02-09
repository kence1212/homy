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
				<h2 class="title">订单列表</h2>
				<p class="title-description">
					订单list
				</p>
			</header>
			<hr>
		</section>
		<div class="clear mt-10 mb-10">
			<div class="fr input-group">
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
			</div>
		</div>
		<table class="table mb-15">
			<thead>
			<tr>
				<th><input type="checkbox"/></th>
				<th>用户ID</th>
				<th>用户名</th>
				<th>总金额</th>
				<th>状态</th>
				<th>下单时间</th>
				<th>支付方式</th>
				<th>支付时间</th>
				<th>订单号</th>
				<th>交易号</th>
				<th>备注</th>
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
	 var orderNo = $("#orderNo").val();
	 var outTradeNo = $("#outTradeNo").val();
	 var state = $("#state").val();
	 $.post("<%=request.getContextPath()%>"+"/amorder/getList.html",
			 {orderNo:orderNo,
			 outTradeNo:outTradeNo,
			 state:state
			 },
			function(data){
				 $('#tbody').html('');
				    var listPageCount = 10,length = data.totalCount; //每页数量，总共记录数
				    var pageCount = Math.ceil(length/listPageCount); //页数
				    var html2 = '';
				    var json = data.list;
				    for(var x in json){
				    	var stateStr = "";
				    	if(json[x].state == 0){
				    		 stateStr = "订单取消";
				    	} else if(json[x].state == 1){
				    		 stateStr = "订单生成";
				    	} else if(json[x].state == 2){
				    		 stateStr = "订单已支付";
				    	} else if(json[x].state == 3){
				    		 stateStr = "订单已发货";
				    	} else if(json[x].state == 4){
				    		 stateStr = "订单已收货";
				    	} else if(json[x].state == 5){
				    		 stateStr = "订单已评价";
				    	} else if(json[x].state == 6){
				    		 stateStr = "订单退货";
				    	} else if(json[x].state == 7){
				    		 stateStr = "订单已退货";
				    	}
				    	var payTypeStr = "未支付";
				    	if(json[x].payType == 0){
				    		payTypeStr = "支付宝支付"
				    	} else if (json[x].payType == 1){
				    		payTypeStr = "微信支付"
				    	} 
				    	var payTimeStr = json[x].payTime;
				    	if(!json[x].payTime){
				    		payTimeStr = "";
				    	}
				    	var remarkStr = json[x].remark;
				    	if(!json[x].remark){
				    		remarkStr = "";
				    	}
				        html2 += '<tr class="cen">'
				                +'<td><input type="checkbox"/></td>'
				                +'<td>'+json[x].userId+'</td>'
				                +'<td>'+json[x].userName+'</td>'
				                +'<td>'+json[x].totalPrice+'</td>'
				                +'<td>'+stateStr+'</td>'
				                +'<td>'+json[x].createTime+'</td>'
				                +'<td>'+payTypeStr+'</td>'
				                +'<td>'+payTimeStr+'</td>'
				                +'<td>'+json[x].orderNo+'</td>'
				                +'<td>'+json[x].outTradeNo+'</td>'
				                +'<td>'+remarkStr+'</td>';
				        html2 +='<td><a title="详情" class="mr-5" href="toDetail.html?id='+json[x].id+'">详情</a><a title="确认发货" onclick="confirmGoods('+json[x].id+','+json[x].state+','+json[x].isValid+')">确认发货</a></td>'
				                +'</tr>';
				    }
				    $('#tbody').html(html2);
				    $(".pagination").createPage({
				        pageCount:pageCount,
				        current:1,
				        backFn:function(p){
				        	 var orderNo = $("#keyword").val();
				        	 var outTradeNo = $("#outTradeNo").val();
				        	 var state = $("#state").val();
						 $.post("<%=request.getContextPath()%>"+"/amorder/getList.html",
								 {orderNo:orderNo,
									 outTradeNo:outTradeNo,
									 state:state,
									 page:p,
									 pageSize:10
									 },
								 function(data){
									 var html = '';
									 var json = data.list;
								     for(var x in json){
							    		var stateStr = "";
								    	if(json[x].state == 0){
								    		 stateStr = "订单取消";
								    	} else if(json[x].state == 1){
								    		 stateStr = "订单生成";
								    	} else if(json[x].state == 2){
								    		 stateStr = "订单已支付";
								    	} else if(json[x].state == 3){
								    		 stateStr = "订单已发货";
								    	} else if(json[x].state == 4){
								    		 stateStr = "订单已收货";
								    	} else if(json[x].state == 5){
								    		 stateStr = "订单已评价";
								    	} else if(json[x].state == 6){
								    		 stateStr = "订单退货";
								    	} else if(json[x].state == 7){
								    		 stateStr = "订单已退货";
								    	}
								    	var payTypeStr = "未支付";
								    	if(json[x].payType == 0){
								    		payTypeStr = "支付宝支付"
								    	} else if (json[x].payType == 1){
								    		payTypeStr = "微信支付"
								    	} 
								    	var payTimeStr = json[x].payTime;
								    	if(!json[x].payTime){
								    		payTimeStr = "";
								    	}
								    	var remarkStr = json[x].remark;
								    	if(!json[x].remark){
								    		remarkStr = "";
								    	}
								         html +=  '<tr class="cen">'
								                +'<td><input type="checkbox"/></td>'
								                +'<td>'+json[x].userId+'</td>'
								                +'<td>'+json[x].userName+'</td>'
								                +'<td>'+json[x].totalPrice+'</td>'
								                +'<td>'+stateStr+'</td>'
								                +'<td>'+json[x].createTime+'</td>'
								                +'<td>'+payTypeStr+'</td>'
								                +'<td>'+payTimeStr+'</td>'
								                +'<td>'+json[x].orderNo+'</td>'
								                +'<td>'+json[x].outTradeNo+'</td>'
								                +'<td>'+remarkStr+'</td>';
								        html +='<td><a title="详情" class="mr-5" href="toDetail.html?id='+json[x].id+'">详情</a><a title="确认发货" onclick="confirmGoods('+json[x].id+','+json[x].state+','+json[x].isValid+')">确认发货</a></td>'
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
	
	function deleteData(id){
		parent.layer.confirm("确定删除么？",{},function(){
			$.post(
				"delete.html",
				{"id":id},
				function(data){
					if(data.success){
						parent.layer.msg("删除成功");
						reflush();
					}else{
						parent.layer.msg(data.msg,{"icon":2});
					}
				},
				"json"
			);
			
		});
	}
	
	function confirmGoods(id,state,isValid){
		if(isValid){
			if(state == 2){
				parent.layer.confirm("确定操作吗？",{},function(){
					$.post(
						"confirmDelivery.html",
						{"id":id},
						function(data){
							if(data.success){
								parent.layer.msg("操作成功");
								reflush();
							}else{
								parent.layer.msg(data.msg,{"icon":2});
							}
						},
						"json"
					);
					
				});
			}else{
				parent.layer.msg("订单状态为已支付状态才能执行确认发货操作",{"icon":2});
			}
		} else{
			parent.layer.msg("该订单已被用户删除",{"icon":2});
		}
	}
</script>
</html>