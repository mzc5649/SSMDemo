<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div style="margin:0px auto; width:500px">
    <form action="updateAccount" method="post">
        姓名: <input name="name" value="${account.name}"> <br>
        金额: <input name="money" value="${account.money}"> <br>
        <input name="id" type="hidden" value="${account.id}">
        <button type="submit">提交</button>
    </form>
</div>
</body>
</html>