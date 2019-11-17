<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/5 0005
  Time: 12:09
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
   <script>

</script>
    <title>我的网盘</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="<%=basePath%>js/jquery.min.3.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/jquery.form.js" type="text/javascript"></script>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.bootcss.com/popper.js/1.15.0/esm/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="<%=basePath%>js/disk.js?<%=Math.random()%>" type="text/javascript"></script>
    <link href="<%=basePath%>css/disk.css?<%=Math.random()%>" rel="stylesheet"/>
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
                <a class="nav-link dropdown-toggle"id="user" href="" data-toggle="dropdown">
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
        <button onclick="openUpFrame()" class="btn btn-primary" data-toggle="modal" data-target="#myModal">上传文件</button>
        <button onclick="listAllFile()" class="btn btn-primary">刷新</button>
        <nav class="breadcrumb" id="pathStr"></nav>
        <table class="table table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>文件名</th>
                    <th>文件大小</th>
                    <th>上传时间</th>
                    <th >操作</th>
                    <th >操作</th>
                </tr>
            </thead>
            <tbody id="listFileBody">

            </tbody>
        </table>
        <!-- 遮罩层-->
        <div id="mask">321321321312</div>

        <!--模态框-->
        <div class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                            <h4 class="modal-title">上传文件</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <div id="progress" class="progress">
                            <div id="percentBar" class="progress-bar progress-bar-striped progress-bar-animated">
                                <span id="percent">0%</span>
                            </div>
                        </div>
                        <form  id="uploadForm" enctype="multipart/form-data" class="mt-3">
                            <input onchange="checkName()" id="fileInput" type="file" name="uploads" >
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" onclick="up()">
                            上传
                        </button>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>



</body>
</html>
