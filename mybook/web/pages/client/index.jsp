<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/24
  Time: 下午4:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@include file="/pages/common/head.jsp"%>
    <script src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#searchPageButton").click(function () {
                var pageValue = $("#pn_input").val();
                location.href="bookServlet?action=page&pageNo="+pageValue+"&min="+"${min}"+"&max="+"${max}";
            });

            // $("div.book_add button").click(function () {
            $("button.add_cart").click(function () {
                //添加购物车绑定点击事件，跳到Cartservlet程序操作购物车模块
                // alert("test!")

                //判断库存大于0才行
                //将图书信息传过去，
                var name=$(this).parent().parent().children(":first").children(".sp2").html();
                var price=$(this).parent().parent().children(":eq(2)").children(".sp2").html();
                var reg_g = /\d+\.\d+$/;
                var result = price.match(reg_g);
                var book_id = $(this).attr("bookId");
                // alert(result);
                location.href="${basePath}cartServlet?action=addItem"+"&name="+name+"&price="+result
                                +"&pageId="+${requestScope.page.pageNo}+"&id="+book_id;
            });
        })
    </script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
<%--    ${pageContext.session.id}--%>
<%--    ${empty sessionScopeion.username}--%>
    <c:choose>
        <c:when test="${empty sessionScope.username}">
            <div>
                <a href="pages/user/login.jsp">登录</a> |
                <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
                <a href="pages/cart/cart.jsp">购物车</a>
<%--                <a href="pages/manager/manager.jsp">后台管理</a>--%>
            </div>
        </c:when>
        <c:otherwise>
            <%@include file="/pages/common/login_success_menu.jsp"%>
        </c:otherwise>
    </c:choose>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="bookServlet" method="get">
<%--                查询得到的值，要传到点分页码时，访问的client的bookservlet程序冲，--%>
<%--                现在是过不去，所以每次点页码min都是null值--%>
                <input type="hidden" name="action" value="page">
                价格：<input id="min" type="text" name="min" value="${requestScope.min}"> 元 -
                <input id="max" type="text" name="max" value="${requestScope.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <span>您的购物车中有3件商品</span>
            <div>
                您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
            </div>
        </div>
        <c:forEach items="${requestScope.page.bookList}" var="book">
        <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="static/img/default.jpg" />
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${book.name}</span>
                </div>
                <div class="book_author">
                    <span class="sp1">作者:</span>
                    <span class="sp2">${book.author}</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">￥${book.price}</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${book.sales}</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${book.stock}</span>
                </div>
                <div  class="book_add">
                    <button bookId="${book.id}" class="add_cart">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>


    <%@include file="/pages/common/page.jsp"%>

</div>


<%@include file="/pages/common/footer.jsp"%>

</body>
</html>