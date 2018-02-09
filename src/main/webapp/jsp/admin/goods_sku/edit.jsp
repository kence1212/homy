<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String contextPath = request.getContextPath();%>
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
                <a href="<%=request.getContextPath() %>/amsku/index.html?id=${goodsBase.id}"><button class="btn btn-secondary"><i class="icon-chevron-left"></i>返回</button></a>
            </div>
        </div>
        <!--开始::内容-->
        <section class="page-hd">
        <input type="hidden" name="goodsId" value="${goodsBase.id }">
            <header>
                <h2 class="title">编辑库存</h2>
            </header>
            <hr>
        </section>
        <form id="saveForm" action="add.html" class="layui-form" method="POST">
        
        <input type="hidden" name="id" value="${sku.id }" >
        
        <div class="form-group-col-2">
            <div class="form-label">商品名称：</div>
            <div class="form-cont">
            	<input type="hidden" value="${goodsBase.id }" name="goodsId">
                <input type="text" placeholder="商品名称" value="${goodsBase.goodsName }" disabled="disabled" form-control layui-input form-boxed" style="width: 200px;">
            </div>
        </div>
         <div class="form-group-col-2">
            <div class="form-label">商品颜色：</div>
            <div class="form-cont">
                <select id="colorId" style="width:auto;" name="colorId">
                <c:forEach items="${colors }" var="cate">
                    <option value="${cate.id }">${cate.name }</option>
               	</c:forEach>
                </select>
            </div>
        </div>
         <div class="form-group-col-2">
            <div class="form-label">商品尺寸：</div>
            <div class="form-cont">
                <select id="sizeId" style="width:auto;" name="sizeId">
               	 <c:forEach items="${sizes }" var="cate">
                    <option value="${cate.id }">${cate.name }</option>
               	</c:forEach>
                </select>
            </div>
        </div>
         <div class="form-group-col-2">
            <div class="form-label">商品材质：</div>
            <div class="form-cont">
                <select id="textureId" style="width:auto;" name="textureId">
             		 <c:forEach items="${textures }" var="cate">
                    	<option value="${cate.id }">${cate.texture_name }</option>
               		 </c:forEach>
                </select>
            </div>
        </div>
      
       <div class="form-group-col-2">
            <div class="form-label">地址：</div>
            <div class="layui-input-inline">
		      <select id="provinceSelect" lay-filter='provinceId'>
		        <option value="">请选择省份</option>
		        	<c:forEach items="${regions }" var="cate">
                    	<option value="${cate.id }">${cate.name }</option>
               		 </c:forEach>
		      </select>
		    </div>
		    <div class="layui-input-inline">
		      <select id="citySelect" style="width:auto;" name="cityId">
		      	 <option value="">请选择城市</option>
		      </select>
		    </div>
        </div>
      
     	 <div class="form-group-col-2">
            <div class="form-label">商品库存：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品库存" value="${sku.stock }" name="stock"  lay-verify="required" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
        
         <div class="form-group-col-2">
            <div class="form-label">商品价格：</div>
            <div class="form-cont">
                <input type="text" placeholder="商品价格" value="${sku.price }" name="price"  lay-verify="required" class="form-control form-boxed" style="width: 200px;">
            </div>
        </div>
      
        <div class="form-group-col-2">
            <div class="form-label"></div>
            <div class="form-cont">
                <input type="button" lay-submit lay-filter="*" class="btn btn-primary" value="编辑" />
            </div>
        </div>
        </form>
        <!--开始::结束-->
    </div>
</div>

<script>


$("#colorId option[value='${sku.colorId}']").attr("selected",true);
$("#sizeId option[value='${sku.sizeId}']").attr("selected",true);
$("#textureId option[value='${sku.textureId}']").attr("selected",true);

//选择省份时渲染城市
layui.use(['form','jquery'],function(){
	var form = layui.form,$=layui.jquery;
	
	//加载页面的时候加载省份和城市
	$.post("<%=request.getContextPath()%>/amregion/getParentRegionByPid.do",
			{id:"${sku.cityId}"},
			function(data){
				if(data){
					$("#provinceSelect option[value='"+data.id+"']").attr("selected",true);
					 $.post("<%=request.getContextPath()%>/amregion/getRegion.do",
							 {pid: data.id},
							function(region){
								$("#citySelect").html('');
								var option = "";
								for(var index in region){
									option += '<option value='+region[index].id+'>'+region[index].name+'</option>';
								}
								$("#citySelect").html(option);
								$("#citySelect option[value='"+"${sku.cityId}"+"']").attr("selected",true);
								form.render('select');
							},"json" 
						) 
				}
			},
			"json"
			)

	
	form.on('select(provinceId)',function(data){
		var v = data.value;
		if(v==""){
			$("#citySelect").html('<option value="">请选择城市</option>');
			form.render('select');
			return ;
		}
		 $.post("<%=request.getContextPath()%>/amregion/getRegion.do",
			 {pid: v},
			function(data){
				$("#citySelect").html('');
				var option = "";
				for(var index in data){
					option += '<option value='+data[index].id+'>'+data[index].name+'</option>';
				}
				$("#citySelect").html(option);
				form.render('select');
			},"json" 
		) 
		
	});
});

    $(function(){
    	layui.use('form', function(){
   		  var form = layui.form;
   		  form.verify({
   			  goodsName:function(value,item){
   				  if(value.length>20){
   					  return '商品名称长度不能超过20个字符';
   				  }
   			  }
   		  });
   		  form.on('submit(*)', function(data){   
   			 
   			 /* $('.layui-form').submit(); */
   			parent.layer.confirm("确定编辑？",{},function(){
   			 $.post("<%=request.getContextPath()%>"+"/amsku/edit.html",
   				  $("#saveForm").serialize(),
   				  function(data){
   					   if(data.resultCode == "10000"){
   						 parent.layer.closeAll();
   						  location.href="index.html?id=${goodsBase.id }";
   					  } else{
   						  parent.layer.alert(data.errorMsg)
   					  } 
   				  },"json")
   			})
   		  });  
   		});
    
    })
</script>
</body>
</html>