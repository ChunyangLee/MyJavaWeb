<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script src="static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function () {
			$("a.delete_but").click(function () {
				if(confirm("您确定要删除【"+$(this).parent().parent().children(":first").html()+"】么?")){
					//true，会自动跳转到a标签的href处。
				}else {
					return false;
				}
			});

			$("input.update_cart_item").change(function () {
				var count = $(this).val();
				// alert(count);
				location.href="cartServlet?action=updateItem&count="+count+"&id="+$(this).attr("item_id");
			})
		});
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
		<%@include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.items}" var="item">
				<tr>
					<td>${item.value.name}</td>
					<td><input type="text" width="20px" item_id="${item.value.id}" class="update_cart_item" value="${item.value.count}"></td>
					<td>${item.value.price}</td>
					<td>${item.value.price*item.value.count}</td>
					<td><a class="delete_but" href="cartServlet?action=deleteItem&itemId=${item.value.id}">删除</a></td>
				</tr>
			</c:forEach>

		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
			<span class="cart_span"><a href="cartServlet?action=clearCart">清空购物车</a></span>
			<span class="cart_span"><a href="pages/cart/checkout">去结账</a></span>
		</div>
	
	</div>

	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>