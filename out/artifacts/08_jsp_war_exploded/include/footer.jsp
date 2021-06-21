<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 上午11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    这里是页脚的内容。单独一个jsp，一边在很多个页面中维护。
    <%= request.getParameter("username")%> <br>
    <%= request.getParameter("password")%>
</body>
</html>
