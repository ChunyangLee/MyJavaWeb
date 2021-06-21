<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 下午8:17
  To change this template use File | Settings | File Templates.
--%>
<%--<%@page isELIgnored="false" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"  %>

<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table{
            border: 1px red solid;
            border-collapse: collapse;
        }
        tr,td{
            border: 1px blue solid;
        }
    </style>
</head>
<body>
<c:set scope="page" var="key" value="value" />
<c:set scope="page" var="key2" value="20" />
${pageScope.key}
<hr>

<c:if test="${12==12}">
    \${}里面可以写运算
</c:if>
<hr>
<%
    int c= Integer.parseInt((String) (pageContext.getAttribute("key2")));
%>
    <%=Integer.parseInt((String) pageContext.getAttribute("key2"))==c%>
JSTL set标签不能赋值数值，只能字符串， <br>
EL的可以， <br>
<%
    pageContext.setAttribute("key3",20);
%> <br>
输出key3的值：${pageScope.key3} <br>
key3的值和数值20比较：${pageScope.key3==20} <br>

<hr>
<c:choose>
    <c:when test="${pageScope.key == 'value'}">
        key=value;
</c:when>
    <c:when test="${pageScope.key !='value'}">
        key!=value
    </c:when>
</c:choose>

<hr>
<table>
    <th colspan="5">循环的写法比JSP代码脚本和表达式输出合用简单了</th>
<c:forEach begin="1" end="10" var="i">
    <tr>
    <c:forEach begin="1" end="5" var="j">
        <td>${i*j}</td>
    </c:forEach>
    </tr>
</c:forEach>
</table>
<hr>
</body>
</html>
