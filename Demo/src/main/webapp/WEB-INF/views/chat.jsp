<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/10 0010
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>聊天室</title>
    <link href="<%=basePath%>css/chat.css?<%=Math.random()%>" rel="stylesheet"/>
    <script src="<%=basePath%>js/jquery.min.3.js" type="text/javascript"></script>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.bootcss.com/popper.js/1.15.0/esm/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>

<body>
<nav class="navbar bg-light navbar-expand-sm navbar-light fixed-top" style="opacity: 0.9;height: 80px">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav ">
            <a class="navbar-brand">LOGO</a>
            <li class="nav-item "><a class="nav-link" href="toHome">首页</a></li>
            <li class="nav-item "><a class="nav-link" href="toDisk">网盘</a></li>
            <li class="nav-item "><a class="nav-link" href="toChat">聊天室</a></li>
        </ul>
        </div>
        <ul class="navbar-nav">
            <li class="nav-item ">
                <img  title="暂不支持更换头像" src="<%=basePath%>img/img1.png" class="rounded-circle" style="max-height: 40px">
            </li>
            <li class="nav-item dropdown ">
                <a class="nav-link dropdown-toggle" id="user" href="" data-toggle="dropdown">
                </a>
                <div class="dropdown-menu dropdown-menu-right" style="height: 200px;min-width:100%;">
                    <a class="dropdown-item" href="#">个人信息</a>
                    <a class="dropdown-item" href="#">设置</a>
                    <a class="dropdown-item" href="exit">退出</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="main">
        <div id="textarea" class="border border-primary" style="padding:15px;height:500px;width:100%;overflow: auto">
        </div>
        <div class="input-group mt-3">
            <input id="userMsg" type="text" class="form-control" placeholder="Something clever..">
            <div class="input-group-append">
                <button class="btn btn-primary" type="button" onclick="sendUserMsg()" >OK</button>
                <button class="btn btn-danger" type="button" onclick="cleanTextarea()">Cancel</button>
            </div>
        </div>
    </div>
</div>
</div>

    </div>
</div>
</body>
<script type="text/javascript" src="<%=basePath%>js/chat.js?<%=Math.random()%>"></script>

</html>
