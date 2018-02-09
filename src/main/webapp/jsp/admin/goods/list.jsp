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
</head>
<body>
<div class="main-wrap">
	<div class="page-wrap">
		<!--开始::内容-->
		<section class="page-hd">
			<header>
				<h2 class="title">商品列表</h2>
			</header>
			<hr>
		</section>
		<div class="clear mt-10 mb-10">
			<div class="fl">
				<a href="toAdd.html"><button class="btn btn-secondary"><i class="icon-plus"></i>新增</button></a>
			</div>
			<div class="fr input-group">
				<input id="keyword" type="text" class="form-control" placeholder="名称" style="width:290px;">
				<button class="btn btn-secondary-outline" onclick="search()">搜索</button>
			</div>
		</div>
		<table class="table mb-15">
			<thead>
			<tr>
				<th><input id="selectAll" type="checkbox"/></th>
				<th>序号</th>
				<th>商品名称</th>
				<th>商品类别</th>
				<th>商品风格</th>
				<th>商品编号</th>
				<th>商品型号</th>
				<th>是否有模型</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody id="tbody">
			<%-- <c:forEach items="${goodsList }" var="bean" varStatus="status">
			<tr class="cen">
				<td><input value="${bean.id }" type="checkbox"/></td>
				<td>${status.count}</td>
				<td><a href="#">${bean.goodsName}</a></td>
				<td>${bean.goodsCategoryName}</td>
				<td>${bean.goodsStyleName}</td>
				<td>${bean.goodsNo}</td>
				<td>${bean.goodsTypeNumber}</td>
				<td>
					<c:if test="${bean.have3d }">有</c:if>
					<c:if test="${!bean.have3d }">没有</c:if>
				</td>
				<td>
					<c:if test="${bean.goodsState == 2 }">
						<a title="点击上架" onclick="upGoods(${bean.id })">下架</a>
					</c:if>
					<c:if test="${bean.goodsState == 1 }">
						<a title="点击下架" onclick="downGoods(${bean.id })">正常</a>
					</c:if>
				</td>
				<td><fmt:formatDate value="${bean.createTime }" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${bean.modifyTime }" pattern="yyyy-MM-dd"/></td>
				<td>
					<a title="编辑" class="mr-5" href="toEdit.html?id=${bean.id }">编辑</a>
					<a title="编辑" class="mr-5" href="toEditGoodsSize.html?goodsId=${bean.id }">编辑规格</a>
					<a title="编辑" class="mr-5" href="toEditGoodsColor.html?goodsId=${bean.id }">编辑颜色</a>
					<a title="编辑" class="mr-5" href="toEditGoodsTexture.html?goodsId=${bean.id }">编辑材质</a>
					<a title="编辑" class="mr-5" href="toEditGoodsAttr.html?goodsId=${bean.id }">编辑扩展属性</a>
					<a title="编辑" class="mr-5" href="toEditGoodsImg.html?goodsId=${bean.id }">编辑图片</a>
					<a title="详情" class="mr-5">详情</a>
					<a title="删除" onclick="deleteData(${bean.id })">删除</a>
					<a title="配置库存" class="mr-5" href="<%=request.getContextPath() %>/amsku/index.html?id=${bean.id }">配置库存</a>
				</td>
			</tr>
			</c:forEach> --%>
			</tbody>
		</table>
		<div class="panel panel-default">
         <div class="panel-bd">
             <div class="pagination table-pagination mt-5 mb-10"></div>
         </div>
     </div>
	</div>
</div>

</body>
<script type="text/javascript">

function search(){
	 $('.pagination').unbind('click');
	 var keyword = $("#keyword").val();
	 $.post("<%=request.getContextPath()%>"+"/amgoods/getList.html",
			 {"key":keyword,"pageSize":30},
			function(data){
				 $('#tbody').html('');
				    var listPageCount = 30,length = data.totalCount; //每页数量，总共记录数
				    var pageCount = Math.ceil(length/listPageCount); //页数
				    var html2 = '';
				    var json = data.list;
				    for(var x in json){
				        html2 += '<tr class="cen">'
				                +'<td><input value="'+json[x].id+'" type="checkbox"/></td>'
				                +'<td>'+ (1+ parseInt(x)) +'</td>'
				                +'<td>'+json[x].goodsName+'</td>'
				                +'<td>'+json[x].goodsCategoryName+'</td>'
				                +'<td>'+json[x].goodsStyleName+'</td>'
				                +'<td>'+json[x].goodsNo+'</td>'
				                +'<td>'+json[x].goodsTypeNumber+'</td>';
				       if(json[x].have3d){
				    	   html2 += '<td>有</td>';
				       }else{
				    	   html2 += '<td>没有</td>';
				       }   
				       if(json[x].goodsState == 2){
				    	   html2 += '<td><a title="点击上架" onclick="upGoods('+json[x].id+')">下架</a></td>';
				       }else if(json[x].goodsState == 1){
				    	   html2 += '<td><a title="点击下架" onclick="downGoods('+json[x].id+')">正常</a></td>';
				       }else{
				    	   html2 += '<td>&nbsp;</td>';
				       }
				       html2 += '<td>'+json[x].createTimeStr+'</td>'
		               			+'<td>'+json[x].modifyTimeStr+'</td>'
				               +'<td>'
				               +'<a title="编辑" class="mr-5" href="toEdit.html?id='+json[x].id+'">编辑</a>'
				               +'<a title="编辑" class="mr-5" href="toEditGoodsSize.html?goodsId='+json[x].id+'">编辑规格</a>'
				               +'<a title="编辑" class="mr-5" href="toEditGoodsColor.html?goodsId='+json[x].id+'">编辑颜色</a>'
				               +'<a title="编辑" class="mr-5" href="toEditGoodsTexture.html?goodsId='+json[x].id+'">编辑材质</a>'
				               +'<a title="编辑" class="mr-5" href="toEditGoodsAttr.html?goodsId='+json[x].id+'">编辑扩展属性</a>'
				               +'<a title="编辑" class="mr-5" href="toEditGoodsImg.html?goodsId='+json[x].id+'">编辑图片</a>'
				               +'<a title="详情" class="mr-5">详情</a>'
				               +'<a title="删除" onclick="deleteData('+json[x].id+')">删除</a> '
				               +'<a title="配置库存" class="mr-5" href="<%=request.getContextPath() %>/amsku/index.html?id='+json[x].id+'">配置库存</a>'
				               +'</td></tr>';
				    }
				    $('#tbody').html(html2);
				    $(".pagination").createPage({
				        pageCount:pageCount,
				        current:1,
				        backFn:function(p){
				        	 var keyword = $("#keyword").val();
						 $.post("<%=request.getContextPath()%>"+"/amgoods/getList.html",
								 {keyword:keyword,
							 	page:p,
							 	pageSize:30},
								 function(data){
									 var html = '';
									 var json = data.list;
									 for(var x in json){
									        html += '<tr class="cen">'
									                +'<td><input value="'+json[x].id+'" type="checkbox"/></td>'
									                +'<td>'+ (1+parseInt(x))  +'</td>'
									                +'<td>'+json[x].goodsName+'</td>'
									                +'<td>'+json[x].goodsCategoryName+'</td>'
									                +'<td>'+json[x].goodsStyleName+'</td>'
									                +'<td>'+json[x].goodsNo+'</td>'
									                +'<td>'+json[x].goodsTypeNumber+'</td>';
									       if(json[x].have3d){
									    	   html += '<td>有</td>';
									       }else{
									    	   html += '<td>没有</td>';
									       }   
									       if(json[x].goodsState == 2){
									    	   html += '<td><a title="点击上架" onclick="upGoods('+json[x].id+')">下架</a></td>';
									       }else if(json[x].goodsState == 1){
									    	   html += '<td><a title="点击下架" onclick="downGoods('+json[x].id+')">正常</a></td>';
									       }else{
									    	   html += '<td>&nbsp;</td>';
									       }
									       html += '<td>'+json[x].createTimeStr+'</td>'
									               +'<td>'+json[x].modifyTimeStr+'</td>'
									               +'<td>'
									               +'<a title="编辑" class="mr-5" href="toEdit.html?id='+json[x].id+'">编辑</a>'
									               +'<a title="编辑" class="mr-5" href="toEditGoodsSize.html?goodsId='+json[x].id+'">编辑规格</a>'
									               +'<a title="编辑" class="mr-5" href="toEditGoodsColor.html?goodsId='+json[x].id+'">编辑颜色</a>'
									               +'<a title="编辑" class="mr-5" href="toEditGoodsTexture.html?goodsId='+json[x].id+'">编辑材质</a>'
									               +'<a title="编辑" class="mr-5" href="toEditGoodsAttr.html?goodsId='+json[x].id+'">编辑扩展属性</a>'
									               +'<a title="编辑" class="mr-5" href="toEditGoodsImg.html?goodsId='+json[x].id+'">编辑图片</a>'
									               +'<a title="详情" class="mr-5">详情</a>'
									               +'<a title="删除" onclick="deleteData('+json[x].id+')">删除</a> '
									               +'<a title="配置库存" class="mr-5" href="<%=request.getContextPath() %>/amsku/index.html?id='+json[x].id+'">配置库存</a>'
									               +'</td></tr>';
									    }
								     $('#tbody').html(html);
								 },"json"
								 )
									        }
									    });
									    
									    
								 },"json"	 
						 )
	
}  


	function upGoods(id){
		$.post(
			"goodsUp.html",
			{"id":id},
			function(data){
				if(data.resultCode == "10000"){
					parent.layer.msg("上架成功");
					reflush();
				}else{
					parent.layer.msg(data.msg,{"icon":2});
				}
			},
			"json"
		);
	} 
	
	function downGoods(id){
		$.post(
			"goodsDown.html",
			{"id":id},
			function(data){
				if(data.resultCode == "10000"){
					parent.layer.msg("下架成功");
					reflush();
				}else{
					parent.layer.msg(data.msg,{"icon":2});
				}
			},
			"json"
		);
	} 
	
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
	$(function(){
		search();
	});
</script>
</html>