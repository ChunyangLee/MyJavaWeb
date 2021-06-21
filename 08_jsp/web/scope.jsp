<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 上午10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <%
            pageContext.setAttribute("key", "PageContext对象，pageContext");
            request.setAttribute("key", "request");
            application.setAttribute("key", "ServletContext对象，application");
            session.setAttribute("key", "HttpSession对象session");
        %>
        pageContext域中是否有值： <%= pageContext.getAttribute("key")%> <br>
        request域中是否有值： <%= request.getAttribute("key")%> <br>
        application域中是否有值： <%= application.getAttribute("key")%> <br>
        session域中是否有值： <%= session.getAttribute("key")%> <br>

        请求转发， <br/>
        <jsp:forward page="/scope2.jsp"></jsp:forward>


</body>
</html>
