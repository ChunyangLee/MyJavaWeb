<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 下午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setAttribute("arr", new String[]{"123","ppp","abc"});
%>
<c:forEach items="${requestScope.arr}" var="item">
    ${item}
</c:forEach>
<hr>
<%--<c:forEach begin="0" end="${requestScope.arr.length}" var="i">--%>
<%--&lt;%&ndash;    ${i}&ndash;%&gt; 没有length属性--%>
<%--</c:forEach>--%>
<%
    Map<String,Object> map = new HashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");
    request.setAttribute("m", map);
%>
<c:forEach items="${requestScope.m}" var="item">
        ${item} <br>
        ${item.key} .运算会找到map中响应的getKey方法和getvalue方法
</c:forEach>
</body>
</html>


