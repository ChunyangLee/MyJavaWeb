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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<hr>
测试a标签为空则不显示出来，用于分页时显示底下的页码。
<a href="#">下一页</a>
<a href="#"></a>
<hr>
<c:forEach begin="0" end="4" var="i">
    ${i}
</c:forEach>
<%
    System.out.println(Double.parseDouble("30.0"));
%>
</body>
</html>
