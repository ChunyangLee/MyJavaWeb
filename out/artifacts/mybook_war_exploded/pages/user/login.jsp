<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>
	<%@include file="/pages/common/head.jsp"%>

	<script src="static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function () {
				//设置点击用户名时，调回来，否则一直显示用户名错误。
			$("#login_username").click(function () {
				// alert("点击了登陆界面中的用户名");
				$("span.errorMsg").html("请输入用户名和密码");
			})
				//还得设置返回登陆界面时，不显示用户名错误
			$("div.tit a").click(function () {
				// alert("test");
				$("span.errorMsg").html("请输入用户名和密码");
				//设置request域中的msg值为空，否则返回时，域中有值，还是显示用户名错误
				//下面这样设置不行， 这样一直为空了
				//点注册，那一瞬间事变过来了，但是返回的时候，还是显示用户名错误，以后优化。
<%--				<%--%>
<%--					request.setAttribute("msg", "");--%>
<%--				%>--%>

			})
		});

	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">${ (empty requestScope.msg)==true?"请输入用户名和密码":requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="login">
									<label>用户名称：</label>
									<input id="login_username" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
										   name="username" value="${username }" /> ${sessionScope.username}
<%--									这里能用，index里就不行，index里，判断为空，一--%>
									<br />
									<br />
									<label>用户密码：</label>
									<input id="login_password" class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
										   name="password" value="${sessionScope.password}"/>
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>

</body>
</html>