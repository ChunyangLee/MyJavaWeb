<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/23
  Time: 上午8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div>
        <span>欢迎<span class="um_span">${sessionScope.username}</span>光临尚硅谷书城</span>
        <a href="pages/order/order.jsp">我的订单</a>
        <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        <c:if test="${sessionScope.username=='admin'}">
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>
        <a href="index.jsp">返回</a>
    </div>
