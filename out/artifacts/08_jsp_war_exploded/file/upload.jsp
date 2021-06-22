<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/22
  Time: 上午9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/08_jsp/uploadServlet" method="post" enctype="multipart/form-data">
    用户名: <input type="text" name="username" value="abc168">
    <input type="file"  name="photo" > <br>
    <input type="submit" value="提交按钮">
</form>
</body>
</html>
