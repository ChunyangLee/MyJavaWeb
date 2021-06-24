<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/23
  Time: 上午10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    ${empty requestScope.key == true ? "a":"b" }--%>
<%--    <hr>--%>
<%
    List list = new ArrayList();
    list.add(123);
    list.add(13);
    list.add(23);
    request.setAttribute("list", list);
%>
${requestScope.list.get(1)}
<hr>
数组的长度可用jstl标签fn函数库，${fn:length(list)}。 fn函数库主要用来处理字符串

</body>
</html>
