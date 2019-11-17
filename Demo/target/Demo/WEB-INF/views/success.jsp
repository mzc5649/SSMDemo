<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/28 0028
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>springmvc</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type="text/javascript">
        function set_account(original,target) {
            var originalName= original.value;
            if(originalName=="请选择") return;
            var i=0;
            <c:forEach items="${list}" var="c" varStatus="cSt">
            if(originalName!="${c.id}"){
                target.options[i]=new Option();
                target.options[i].value="${c.id}";
                target.options[i].text="${c.name}";
                i++;
            }
            </c:forEach>
        }
    </script>
</head>
<body>
<h1 align="center">入门成功</h1>
<div align="center">
<table border="1" bgcolor="#add8e6">
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>金额</th>
        <th colspan="2">操作</th>
    </tr>
<c:forEach items="${list}" var="c" varStatus="cSt">
    <tr>
        <td><c:out value="${c.id}"></c:out></td>
        <td><c:out value="${c.name}"></c:out></td>
        <td><c:out value="${c.money}"></c:out></td>
        <td><a href="deleteAccount?id=${c.id}" style="text-decoration: none; color: dimgray" >删除</a> </td>
        <td><a href="editAccount?id=${c.id}" style="text-decoration: none;color: dimgray">编辑</a> </td>
    </tr>
</c:forEach>
</table>
</div>
<hr color="pink" size="10px">
<div align="center">
    <form action="addAccount" method="post" >
        姓名：<input type="text" name="name"><br>
        金额：<input type="text" name="money"><br>
        <input type="submit" value="添加"/>
        <input type="reset" value="重置"/>
    </form>
</div>
<hr color="pink" size="10px">
<div align="center">
    <form action="transferAccountById" method="post">
    源账户姓名： <select name="originalName" onchange="set_account(this,this.form.targetName)">
                 <option  value="请选择">请选择</option>
                <c:forEach items="${list}" var="c" varStatus="cSt">
                    <option  value="${c.id}">${c.name}</option>
                </c:forEach>
                </select>
    目标账户姓名：<select name="targetName">
                  <option  value="请选择">请选择</option>
                 </select>
        <br>
        转账金额：<input type="text" name="money">
        <br>
        <input type="submit" value="开始转账">

    </form>
</div>






</body>
</html>
