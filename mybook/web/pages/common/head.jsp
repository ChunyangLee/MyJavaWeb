<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/23
  Time: 上午9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
   String basePath =  request.getScheme() + "://"+request.getServerName()+":"
                    +request.getServerPort()+request.getContextPath()+"/";

   request.setAttribute("basePath", basePath);
%>
<%--<%=basePath%>--%>
<%--${requestScope.basePath}--%>
<base href=<%=basePath%>>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<%--<base href=${requestScope.basePath}>--%>
