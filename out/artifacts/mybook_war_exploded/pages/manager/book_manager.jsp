<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
	<script src="static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function () {
			$("a.deleteclass").click(function () {
				return confirm("你确定要删除["+$(this).parent().parent().children(":first").val()+"]么？");
			});

			$("#searchPageButton").click(function () {
				var pageValue = $("#pn_input").val();
				location.href="manager/bookServlet?action=page&pageNo="+pageValue;
			});
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%@include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.bookList}" var="book" varStatus="status" >
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&update_bookName=${book.name}&
					totalPageNoForAdd=${requestScope.page.pageTotalNo}">修改</a></td>
					<td><a class="deleteclass" href="manager/bookServlet?action=delete&delete_book_name=${book.name}
					&totalPageNoForAdd=${requestScope.page.pageTotalNo}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?totalPageNoForAdd=${requestScope.page.pageTotalNo}">添加图书</a></td>
			</tr>	
		</table>
		<br>
		<%@include file="/pages/common/page.jsp"%>
	</div>


	<%@include file="/pages/common/footer.jsp"%>

</body>
</html>