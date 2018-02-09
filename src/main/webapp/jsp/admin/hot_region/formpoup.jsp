<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>list</title>
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
        <div class="clear mb-10">
            <div class="fr input-group">
                <input type="text" id="keyword" class="form-control" placeholder="搜索..." style="width:290px;"/>
                <button class="btn btn-secondary-outline" onclick="search()">搜索</button>
            </div>
        </div>
        <div class="panel-bd">
            <div class="select-table">
                <table class="table mb-15">
                    <tbody class="formpoupList">
                    <tr class="cen">
                        <td><input type="checkbox" value="1"/></td>
                        <td class="lt"><a href="#">正宗陕西洛川红富士 新鲜水果特惠</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="panel panel-default">
                <div class="panel-bd">
                    <div class="pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

 function search(){
	 $('.pagination').unbind('click');
	 var keyword = $("#keyword").val();
	 $.post("<%=request.getContextPath()%>"+"/amregion/pageJson.html",
			 {keyword:keyword},
			function(data){
				 $('.formpoupList').html('');
				    var listPageCount = 5,length = data.totalCount; //每页数量，总共记录数
				    var pageCount = Math.ceil(length/listPageCount); //页数
				    var html2 = '';
				    var json = data.list;
				    for(var x in json){
				        html2 += '<tr class="cen">'+
				                '<td class="lt"><input type="checkbox" style="display: none;" value="'+json[x].id+'"/>'+json[x].name+'</td>'+
				                '<td class="lt"><input type="checkbox" style="display: none;" />'+json[x].parentRegion.name+'</td>'+
				                '</tr>';
				    }
				    $('.formpoupList').html(html2);
				    $(".pagination").createPage({
				        pageCount:pageCount,
				        current:1,
				        backFn:function(p){
				            var keyword = $("#keyword").val();
						 $.post("<%=request.getContextPath()%>"+"/amregion/pageJson.html",
								 {keyword:keyword,
							 	page:p,
							 	pageSize:5},
								 function(data){
									 var html = '';
									 var json = data.list;
								     for(var x in json){
								         html +='<tr class="cen">'+
								         '<td class="lt"><input type="checkbox" style="display: none;" value="'+json[x].id+'"/>'+json[x].name+'</td>'+
							                '<td class="lt"><input type="checkbox" style="display: none;" />'+json[x].parentRegion.name+'</td>'+
								                 '</tr>';
								     }
								     $('.formpoupList').html(html);
								 },"json"
								 )
									        }
									    });
									    
									    
								 },"json"	 
						 )
	
 }   
 
 search();
 
   /*  var listPageCount = 5,length = json.length;
    var pageCount = Math.ceil(length/listPageCount);
    var html2 = '';
    for(var i= 0;i<5;i++){
        html2 += '<tr class="cen">'+
                '<td class="lt"><input type="checkbox" style="display: none;" value="'+json[i].value+'"/>'+json[i].title+'</td>'+
                '</tr>';
    }
    $('.formpoupList').html(html2);
    $(".pagination").createPage({
        pageCount:pageCount,
        current:1,
        backFn:function(p){
//            console.log(p);
            var html = '';
            var l=0;
            if(p*listPageCount<length){
                l = p*listPageCount;
            }else{
                l = length;
            }
            for(var i= (p-1)*listPageCount;i<l;i++){
                html +='<tr class="cen">'+
                        '<td class="lt"><input type="checkbox" style="display: none;" value="'+json[i].value+'"/>'+json[i].title+'</td>'+
                        '</tr>';
            }
            $('.formpoupList').html(html);
        }
    }); */
    formpoupListClick();
    function  formpoupListClick(){
        $('.formpoupList').on('click','tr', function () {
            var t = $(this).find('td').eq(0).text();
            var v = $(this).find('td').eq(0).find("input").val();
         	var iframe = parent.$('.tab-cont[style="display: block;"]').find('iframe');
         	iframe[0].contentWindow.setTextAndValue(t,v);
            parent.layer.closeAll('iframe');
        });
    }

</script>
</body>
</html>