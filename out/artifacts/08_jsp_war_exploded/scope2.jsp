<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 上午11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    pageContext域中是否有值： <%= pageContext.getAttribute("key")%> <br>
    request域中是否有值： <%= request.getAttribute("key")%> <br>
    application域中是否有值： <%= application.getAttribute("key")%> <br>
    session域中是否有值： <%= session.getAttribute("key")%> <br>
</body>
</html>
