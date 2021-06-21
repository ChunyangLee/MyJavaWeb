<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 下午3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
            request.setAttribute("key","sss");
        %>
        EL表达式的输出：${key} <br/>
        <hr/>

        1. 先从PageContext中找 <br>
        2.  request <br>
        3.  session <br>
        4. application <br>
        <hr>
        <br>

<%
        request.setAttribute("emptyNull","");
%>
${empty emptyNull}



</body>
</html>
