<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/3 0003
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>登录</title>
    <script type="text/javascript" src="./js/login.js?version=<%=Math.random()%>"></script>
    <link rel="stylesheet" href="css/login.css?version=<%=Math.random()%>"/>
    <script type="text/javascript" src="./js/jquery.min.3.js?version=<%=Math.random()%>"></script>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link href="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap.bundle.min.js 用于弹窗、提示、下拉菜单，包含了 popper.min.js -->
    <script src="https://cdn.bootcss.com/popper.js/1.15.0/esm/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body >
<nav class="navbar bg-light navbar-expand-md navbar-light fixed-top" style="opacity: 0.9;height: 60px">
    <div class="container-fluid">
        <ul class="navbar-nav ">
        <a class="navbar-brand">LOGO</a>
            <li class="nav-item "><a class="nav-link" href="toHome">首页</a></li>
      </ul>
        <ul class="navbar-nav ">
            <li id="l_login" class="nav-item active"><a id="a_login" class="nav-link "  >登录</a></li>
            <li id="l_register" class="nav-item "><a id="a_register" class="nav-link "  href="javascript:goRegister()">注册</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div id="message">
        <div id="alert-danger" class="alert alert-danger" style="display: none">
            该用户不存在或密码错误
        </div>
        <div id="alert-reg-danger" class="alert alert-danger" style="display: none">
            不好意思，注册失败了，可能由于系统问题敬请谅解
        </div>
        <div id="alert-reg-exists" class="alert alert-danger" style="display: none">
            不好意思，用户名已被注册
        </div>
        <div id="alert-success" class="alert alert-success" style="display: none">
            登录成功
        </div>
        <div id="alert-reg-success" class="alert alert-success" style="display: none">
            恭喜！注册成功 1秒后将返回登录
        </div>
        <div id="alert-nullusername" class="alert alert-warning" style="display: none">
            请输入用户名
        </div>
        <div id="alert-nullpassword" class="alert alert-warning" style="display: none">
            请输入密码
        </div>
    </div>
    <div class="form-signin">
        <h2>欢迎登录</h2>
            <input class="form-control" type="text" placeholder="Username" id="username" name="username" autofocus>

        <div class="form-group">
            <input class="form-control" type="password" placeholder="Password" id="password" name="password">
        </div>
        <button class="btn btn-primary" onclick="validate1()">登录</button>
    </div>
    <div class="form-register" style="display: none">
        <h2>欢迎注册</h2>
        <input class="form-control" type="text" placeholder="Username" id="reg-username" name="reg-username" autofocus>

        <div class="form-group">
            <input class="form-control" type="password" placeholder="Password" id="reg-password" name="reg-password">
        </div>
        <button class="btn btn-primary " onclick="register()">注册</button>
    </div>
</div>

</body>
</html>
