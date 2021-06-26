<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/26
  Time: 下午4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">订单详情</span>
    <%@include file="/pages/common/login_success_menu.jsp"%>

</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>
            <c:forEach items="${requestScope.orderItems}" var="orderItem">
                <tr>
                    <td>${orderItem.name}</td>
                    <td>${orderItem.count}</td>
                    <td>${orderItem.price}</td>
                    <td>${orderItem.price*orderItem.count}</td>
                </tr>
            </c:forEach>
    </table>

    <div class="cart_info">
        <span class="cart_span">订单中中共有<span class="b_count">${requestScope.orderItems.size()}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${requestScope.totalPrice}</span>元</span>
    </div>

</div>

<%@include file="/pages/common/footer.jsp"%>

</body>
</html>