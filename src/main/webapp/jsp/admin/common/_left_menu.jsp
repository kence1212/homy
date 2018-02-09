<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();%>
<%String contextPath = request.getContextPath();%>
<div class="side-nav">
    <div class="side-logo">
        <div class="logo">
			<span class="logo-ico">
				<i class="i-l-1"></i>
				<i class="i-l-2"></i>
				<i class="i-l-3"></i>
			</span>
            <strong>家咪家装后台管理</strong>
        </div>
    </div>

    <nav class="side-menu content mCustomScrollbar" data-mcs-theme="minimal-dark">
        <h2>
            <a href="" onclick="javascript:void(0);" class="InitialPage"><i class="icon-dashboard"></i>数据概况</a>
        </h2>
        <ul>
            <li>
                <dl>
                    <dt>
                        <i class="icon-columns"></i>商品管理<i class="icon-angle-right"></i>
                    </dt>
                    <dd>
                        <a href="<%=contextPath%>/ambrand/index.html">品牌管理</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/amstore/index.html">店铺管理</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/amgoodscate/index.html">商品分类</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/amgoods/index.html">商品列表</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/ammodel/index.html">商品模型</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/ampaint/index.html">油漆类型</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/amstyle/index.html">商品风格</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/silder/index.html">APP轮播图</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/boutique/index.html">商品推荐</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/amregion/index.html">热门城市</a>
                    </dd>
                </dl>
            </li>
            <li>
                <dl>
                    <dt>
                        <i class="icon-columns"></i>订单管理<i class="icon-angle-right"></i>
                    </dt>
                    <dd>
                        <a href="<%=contextPath%>/amorder/index.html">订单列表</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/amorderRefund/index.html">退货订单列表</a>
                    </dd>
                </dl>
            </li>
            <li>
                <dl>
                    <dt>
                        <i class="icon-columns"></i>用户管理<i class="icon-angle-right"></i>
                    </dt>
                    <dd>
                        <a href="<%=contextPath%>/aduser/index.html">用户列表</a>
                    </dd>
                </dl>
            </li>
            <li>
                <dl>
                    <dt>
                        <i class="icon-sitemap"></i>权限管理<i class="icon-angle-right"></i>
                    </dt>
                     <dd>
                        <a href="<%=contextPath%>/ampmodel/index.html">权限模块配置</a>
                    </dd>
                     <dd>
                        <a href="<%=contextPath%>/amrole/index.html">角色配置</a>
                    </dd>
                </dl>
            </li>
            <li>
                <dl>
                    <dt>
                        <i class="icon-sitemap"></i>系统管理<i class="icon-angle-right"></i>
                    </dt>
                    <dd>
                        <a href="<%=contextPath%>/adapp/index.html">APP列表</a>
                    </dd>
                </dl>
            </li>
            <li>
                <dl>
                    <dt>
                        <i class="icon-sitemap"></i>场景管理<i class="icon-angle-right"></i>
                    </dt>
                    <dd>
                        <a href="<%=contextPath%>/amscenecategory/index.html">场景分类管理</a>
                    </dd>
                    <dd>
                        <a href="<%=contextPath%>/amscenestyle/index.html">场景风格管理</a>
                    </dd>
                </dl>
            </li>
        </ul>
    </nav>

    <!-- <footer class="side-footer"></footer> -->

</div>