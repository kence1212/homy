<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>关于我们-家咪科技</title>
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
        html,body,div,h2,h3,p,a,img,h1{
            padding:0;
            margin:0;
        }
        /*h2{*/
            /*font-size: 20px;*/
            /*font-weight: 600;*/
            /*text-align: center;*/
            /*color:#333;*/
        /*}*/
        /*h3{*/
            /*font-size: 16px;*/
            /*padding-bottom: 0.1rem;*/
            /*margin-bottom: 0.1rem;*/
            /*border-bottom: solid 0.01rem rgba(204,204,204,0.6);*/
        /*}*/
        /*div{*/
            /*padding:0.1rem 0.05rem;*/
            /*border: solid 0.01rem rgba(204,204,204,0.4);*/
            /*margin-top: 0.1rem;*/
            /*padding: 0.1rem;*/
        /*}*/
        /*p{*/
            /*font-size: 14px;*/
            /*line-height: 0.36rem;*/
            /*color:#aaa;*/
            /*text-indent: 2em;*/
        /*}*/
        /*.topText p{*/
            /*color: #333;*/
        /*}*/
        .header{
            font-size: 0;
        }
        .line{
            position: relative;
            width: 100%;
            height: 0.01rem;
            border-bottom: solid 0.01rem #ccc;
            margin-top: 0.5rem;
        }
        h1{
            position: absolute;
            top:0;
            left: 50%;
            margin-left: -1.75rem;
            margin-top: -0.2rem;
            width: 3.5rem;
            height: 0.4rem;
            text-align: center;
            font-size: 16px;
            color: #333;
            background-color: #fff;
        }
        .title{
            position: relative;
        }
        .content{
            margin: 0.5rem auto 0 auto;
            width: 5.8rem;
        }
        .content p{
            font-size: 14px;
            color: #333;
            text-indent: 2em;
            line-height: 1.5;
        }
    </style>
</head>
<body>
    <div class="header">
        <img width="100%" src="<%=contextPath %>/resource/images/aboutusbg.jpg">
    </div>
    <div class="title">
        <div class="line"></div>
        <h1>深圳家咪科技有限公司</h1>
    </div>
    <div class="content">
        <p>
            深圳家咪科技有限公司致力于打造最落地的正品、特价一站式家居建材直供平台，以SAAS管理系统为核心，集移动互联网、
            云计算、大数据为依托，率先开辟B2B、B2C 、C2B家居新零售新电商模式，解决供应链F2C物流管理的AR智能家居新电商平台。
        </p>
    </div>
</body>
</html>