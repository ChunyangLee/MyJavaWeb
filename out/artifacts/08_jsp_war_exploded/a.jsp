<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 上午9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" errorPage="/error500.jsp" %>
<%--         autoFlush="true"  默认 --%>
<%--         buffer="8kb"     默认--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--        <% int a = 12/0;  %>--%>
<table border="3">
    <%
        for (int i = 0; i < 10; i++) {
    %>
    <tr>
        <td><%="第"+i+"行"%></td>
    </tr>

    <%
        }
    %>
</table>

</body>
</html>
