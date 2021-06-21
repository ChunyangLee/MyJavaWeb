<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 上午10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        头部信息 <br>
        主题内容 <br>
        <%@include file="/include/footer.jsp"%>

        动态包含，传递参数给footer使用 <br>
        <jsp:include page="/include/footer.jsp">
            <jsp:param name="username" value="root"/>
            <jsp:param name="password" value="123456"/>
        </jsp:include>

</body>
</html>
