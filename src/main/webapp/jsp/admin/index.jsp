<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>家咪家装后台管理</title>
    <meta name="keywords"  content="设置关键词..." />
    <meta name="description" content="设置描述..." />
    <meta name="author" content="DeathGhost" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <jsp:include page="common/_cssjs.jsp"></jsp:include>
</head>
<body>
<div class="main-wrap">
    <jsp:include page="common/_left_menu.jsp"></jsp:include>
    
    <div class="content-wrap">
        <header class="top-hd">
            <div class="hd-lt">
                <a class="icon-reorder"></a>
            </div>
            <div class="hd-rt">
                <ul>
                    <li>
                        <a href="#" target="_blank"><i class="icon-home"></i>前台访问</a>
                    </li>
                    <li>
                        <a><i class="icon-random"></i>清除缓存</a>
                    </li>
                    <li>
                        <a><i class="icon-user"></i>管理员:<em>DeathGhost</em></a>
                    </li>
                    <li>
                        <a><i class="icon-bell-alt"></i>系统消息</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" id="JsSignOut"><i class="icon-signout"></i>安全退出</a>
                    </li>
                </ul>
            </div>
        </header>
        <main class="main-cont content mCustomScrollbar">
            <div class="page-wrap">
                <div class="panel panel-default">
                    <!--<div class="panel-hd">按钮</div>-->
                    <div class="panel-bd">
                        <div class="card" id="mainCard">
                            <div class="card-header">
                                <i class="icon-angle-left nav-to-icon nav-to-icon-left" type="left"></i>
                                <div class="tab-nav-cont">
                                    <ul class="tab-nav" id="mainTabNav">
                                        <li class="active" canntclose="true"><span class="icon-home"></span>首页</li>
                                    </ul>
                                </div>
                                <i class="icon-refresh nav-refresh"></i>
                                <i class="icon-angle-right nav-to-icon nav-to-icon-right" type="right"></i>
                                <i class="icon-list nav-menu-icon">
                                    <ul class="nav-menu">
                                        <li id="closeOne"><i class="close"></i>关闭当前</li>
                                        <li id="closeAll"><i class="close"></i>关闭所有</li>
                                    </ul>
                                </i>
                            </div>
                            <div class="tab-cont" style="display: block;">首页</div>
                        </div>
                    </div> 
                </div>
                <!--开始::结束-->
            </div>
        </main>
        <footer class="btm-ft">
            <p class="clear">
                <span class="fl">©Copyright 2017 <a href="#" title="DeathGhost" target="_blank">homy.qe-vr.com</a></span>
            </p>
        </footer>
    </div>
</div>
<script>
function reflush(){
	var mainHeight = $('.main-cont').height(),
	iframeHeight = mainHeight - 153;
	var active = $('#mainTabNav .active');
	var index = $('#mainTabNav li').index(active);
	var tabCont = $('#mainCard .tab-cont').eq(index);
	var iframe = tabCont.find('iframe');
	var loadHtml = '<div class="iframeLoading" style="height:'+mainHeight+'px;line-height:'+mainHeight+'px;"><span class="icon-spin icon-refresh"></span></div>'
	if(iframe&&iframe.attr('src')){
		tabCont.append(loadHtml);
		var i = document.createElement("iframe");
		i.src = iframe.attr('src');
		//i.src = 'http://www.baidu.com';
		if (i.attachEvent){
			i.attachEvent("onload", function(){
				$('.iframeLoading').hide();
			});
		} else {
			i.onload = function(){
				$('.iframeLoading').hide();
			};
		}
		i.width = '100%';
		i.height = iframeHeight;
		i.scrolling = 'auto';
		i.setAttribute('frameborder', '0', 0);
		tabCont.html(i);
	}
}
</script>
</body>
</html>