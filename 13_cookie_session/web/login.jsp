<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/25
  Time: 上午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/13_cookie_session/loginServlet" method="post">
    <input type="hidden" name="action" value="login">
    <label for="">用户名：</label> <input type="text" name="username" value="${ empty cookie.get("username").value?"请输入用户名":cookie.get("username").value }">
    <label for="">密码：：</label> <input type="password" name="password" value="${cookie.password.value}"> <br>
    <input type="submit" value="提交按钮">
</form>
</body>
</html>
