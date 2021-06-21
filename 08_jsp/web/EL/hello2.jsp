<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 下午4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    pageContext.setAttribute("key1", "pageContext1");
    pageContext.setAttribute("key2", "pageContext2");
    request.setAttribute("key2", "request");
    session.setAttribute("key2", "session");
    application.setAttribute("key2", "application");

%>

4个域对象的使用，
${pageScope.key2}
${requestScope.key2}
${sessionScope.key2}
${applicationScope.key2} <br>
<hr>

pageContext对象的使用，
<hr>
不使用EL的话: <%= request.getScheme()%>， 使用的话: ${pageContext.request.scheme}
<br>
${pageContext.request.contextPath} <br>
${pageContext.request.pathInfo} <br>
${pageContext.request.servletPath} <br>

输出请求参数的值： ${param}
<br>
<hr>
${header} <br>
${header.Cookie} <br>

</body>
</html>
