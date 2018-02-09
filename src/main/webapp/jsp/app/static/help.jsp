<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>帮助我们</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>
        @media screen and (min-width: 320px) {html{font-size:312.5%;}}
        @media screen and (min-width: 360px) {html{font-size:351.5625%;}}
        @media screen and (min-width: 375px) {html{font-size:366.211%;}}
        @media screen and (min-width: 400px) {html{font-size:390.625%;}}
        @media screen and (min-width: 414px) {html{font-size:404.2969%;}}
        @media screen and (min-width: 440px) {html{font-size:429.6875%;}}
        @media screen and (min-width: 480px) {html{font-size:468.75%;}}
        @media screen and (min-width: 520px) {html{font-size:507.8125%;}}
        @media screen and (min-width: 560px) {html{font-size:546.875%;}}
        @media screen and (min-width: 600px) {html{font-size:585.9375%;}}
        @media screen and (min-width: 640px) {html{font-size:625%;}}
        @media screen and (min-width: 680px) {html{font-size:664.0625%;}}
        @media screen and (min-width: 720px) {html{font-size:703.125%;}}
        @media screen and (min-width: 760px) {html{font-size:742.1875%;}}
        @media screen and (min-width: 800px) {html{font-size:781.25%;}}
        @media screen and (min-width: 960px) {html{font-size:937.5%;}}
        html,body,div,h1,h2,h3,p,a{
            padding:0;
            margin:0;
        }
        body{
            background-color: #fafafa;
            font-size: 14px;
            font-family: Helvetica,SimHei;
        }
        h1{
            line-height: 52px;
            font-size: 14px;
            font-weight: 400;
            color: #828282;
            padding: 8px 0 0 20px;
            border-bottom: 1px solid #ececec;
        }
        h2{
            font-size: 14px;
            font-weight: 500;
        }
        .title{
            position: relative;
            overflow: hidden;
            padding: 5px 20px 5px 20px;
            line-height: 48px;
            border-bottom: 1px solid #ececec;
            background-color: #fff;
        }
       .content{
           font-size: 13px;
           line-height: 2em;
           color: #828282;
           text-indent: 2em;
           display: none;
       }
        .toright{
            height:20px;
            width:12px;
            display:block;
            position:absolute;
            top:15px;
            right: 15px;
            overflow:hidden;
        }
        .toright:before{
            content:'';
            height:10px;
            width:10px;
            display:block;
            border:1px solid #828282;
            border-left-width:0;
            border-top-width:0;
            transform:rotate(-45deg);
            -webkit-transform:rotate(-45deg);
            -moz-transform:rotate(-45deg);
            -o-transform:rotate(-45deg);
            -ms-transform:rotate(-45deg);
            position:absolute; top:7px; right:7px;}
        .todown{
            height:9px;
            width:20px;
            display:block;
            position:absolute;
            top:24px;
            right: 14px;
            overflow:hidden;
        }
        .todown:before{
            content:'';
            height:10px;
            width:10px;
            display:block;
            border:1px solid #828282;
            border-right-width:0;
            border-top-width:0;
            transform:rotate(-45deg);
            -webkit-transform:rotate(-45deg);
            -moz-transform:rotate(-45deg);
            -o-transform:rotate(-45deg);
            -ms-transform:rotate(-45deg);
            position:absolute;
            bottom:3px;
            left:3px;
        }
    </style>
</head>
<body>
    <h1>关于优惠券</h1>
    <div class="title">
        <h2>优惠券如何获取？ </i> <i class="icon toright"></i></h2>
        <div class="content">
            <p>1.注册HOMY，即可获得新人礼包券</p>
            <p>2.预约体验生活圈，体验结束并完成评价即可获得体验红包。</p>
            <p>3.通过已注册好友分享的邀请链接领取专属优惠券</p>
            <p>4.留意HOMY官网，不定时会根据活动赠送优惠券，可直接在页面领取。</p>
        </div>
    </div>
    <div class="title">
        <h2> 优惠券使用规则 <i class="icon toright"></i></h2>
        <div class="content">
            <p>1.适用范围：每张优惠券有指定可用的范围，在我的礼券中，可查看优惠券使用范围。</p>
            <p>2.有效期：在优惠券的适用范围下方，会显示有效期，仅在有效期内可使用。</p>
            <p>3.每个订单仅限使用一张通用券，或一张使用范围相同的品牌券，平台券和品牌券可叠加使用。</p>
            <p>4.限时促销、每周小物不可使用优惠券。</p>
            <p>5.使用优惠券的订单，若产生退款退货，优惠券均不退回，退款金额按优惠券后的金额退回。</p>
        </div>
    </div>
    <h1>关于物流</h1>
    <div class="title">
        <h2>订单支付成功后，多久可以发货？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>因不同用户对颜色、款式和面料的需求不同，家具较少有现货;不同品牌和不同材质的家具生产周期不同，分别在15-90天不等，具体可查询你购买产品的详情页或咨询在线客服。</p>
        </div>
    </div>
    <div class="title">
        <h2>是否可以指定快递公司？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>非常抱歉，目前暂不提供指定快递配送的服务，系统会根据当前的派送地址选择配置最优的物流公司。</p>
        </div>
    </div>
    <div class="title">
        <h2>是否可送货入户包安装？<i class="icon toright"></i></h2>
        <div class="content">
            <p>目前共开放服务168个城市，可提供物流、安装、送货入户服务。送装城市会根据实际情况收取费用，可在下单前详询客服。</p>
        </div>
    </div>
    <div class="title">
        <h2>为何物流一直没有更新？<i class="icon toright"></i></h2>
        <div class="content">
            <p>一般情况下，物流只有到了站点才会更新，72小时内更新物流信息是正常现象，建议您耐心等待。如果订单已发货，无跟踪物流，原因可能是物流信息暂时未录入。物流不更超过3天，您可咨询在线客服，工作人员会为您核实最新动态。</p>
        </div>
    </div>
    <h1>关于售后</h1>
    <div class="title">
        <h2>售后服务保障有哪些？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>HOMY家居直供平台销售的标准产品未确定下单生产之前可享受7天无理由退换货与3年质保服务，由平台监督经销商或工厂进行保修。超过保质期后，HOMY提供终身有偿维修服务。更多售后服务相关问题，请咨询客服。</p>
        </div>
    </div>
    <div class="title">
        <h2> 如何申请售后？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>在我的-我的订单，选择需要申请售后的订单，进入“订单详情”点击“申请退款”。申请时候需要填写：退货数量、退货原因。发起申请之后，HOMY客服中心于1-3个工作日进行审核，订单页面会视情况更新退款状态，如有疑问，可咨询在线客服。</p>
        </div>
    </div>
    <div class="title">
        <h2>收到商品损坏如何处理? <i class="icon toright"></i></h2>
        <div class="content">
            <p>若收到的商品破损，请第一时间拍照并联系客服，客服会尽快帮助核实处理。</p>
        </div>
    </div>
    <div class="title">
        <h2>商品寄回运费谁来承担？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>因为平台原因退货，运费由平台承担;因个人原因退货（如尺寸拍错），运费由个人承担。</p>
        </div>
    </div>
    <div class="title">
        <h2> 售后商品何时能收到退款？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>申请售后之后，客服人员审核通过之后，退款于3-5个工作日退回原支付账户。</p>
        </div>
    </div>
    <h1>关于支付</h1>
    <div class="title">
        <h2>HOMY家居直供平台有哪些支付方式？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>目前HOMY只支持支付宝、微信支付和银行转账</p>
        </div>
    </div>
    <div class="title">
        <h2>已经支付成功，为什么我的订单仍显示“待支付”？<i class="icon toright"></i></h2>
        <div class="content">
            <p>一般来说，网上支付是即时到账的。但是有时会因为网络等原因，可能会有几分钟延迟，请耐心等待，若订单状态还是“等待付款”，请联系客服处理。</p>
        </div>
    </div>
    <div class="title">
        <h2>订单重复支付怎么办？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>重复扣款是因为网络延迟产生，重复扣款金额会于1-7个工作日退回原支付账号。若超时未收到，请联系客服处理。</p>
        </div>
    </div>
    <div class="title">
        <h2>付款时提示超过支付限额怎么办？ <i class="icon toright"></i></h2>
        <div class="content">
            <p>如果您的订单金额超过您所支付的额度，建议你在支付的时候选择“分多次支付”，可以根据支付额度，分多次支付。</p>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/zepto/1.2.0/zepto.js"></script>
    <script>
        $("h2").on("click",function(){
           var title = $(this).parents(".title"),
               content = title.find(".content"),
               i = $(this).find('.icon');
            content.toggle();
            if(i.hasClass('toright')){
                i.removeClass('toright').addClass('todown');
                title.css({'padding':'5px 20px 15px 20px'});
            }else{
                i.removeClass('todown').addClass('toright');
                title.css({'padding':'5px 20px 5px 20px'});
            }

        });
    </script>
</body>
</html>