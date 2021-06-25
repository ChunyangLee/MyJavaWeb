<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%@include file="/pages/common/head.jsp"%>

	<script src="static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
			$(function () {
				$("#username").blur(function () {
					// alert("失去焦点！")
					var patt=/^\w{5,12}$/;
					if(!patt.test($("#username").val())){
						$("span.errorMsg").html("用户名不合法！");
					}else{
						$("span.errorMsg").html("");
					}
				})

				$("#codeImg").click(function () {
					// alert("点击验证码，刷新！")
					//后面带的参数是解决部分浏览器缓存的问题，缓存判断的是资源路径和参数都匹配才行
					this.src="${basePath}/kaptcha.jpg?d="+ new Date();
				})
				//下面的代码是没有用jsp，最早一版用js弄的
				// $("#sub_btn").click(function () {
				// 	// alert("提交按钮！")
				// 	//验证用户名
				// 	var patt=/^\w{5,12}$/;
				// 	if(!patt.test($("#username").val())){
				// 		$("span.errorMsg").html("用户名不合法！");
				// 		// return false;
				// 	}
				// 	if(!patt.test($("#password").val())){
				// 		$("span.errorMsg").html("密码不合法！");
				// 		// return false;
				// 	}
				// 	if($("#repwd").val()!=$("#password").val()){
				// 		$("span.errorMsg").html("两次密码需相同！");
				// 		// return false;
				// 	}
				//
				// 	var emailText = $("#email").val();
				// 	var pattEmail=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
				// 	if(!pattEmail.test(emailText)){
				// 		$("span.errorMsg").html("邮箱不合法！");
				// 		// return false;
				// 	}
				//
				// 	var codeText=$("#code").var();
				// 	codeText = $.trim(codeText);  //去掉空格！
				// 	if(codeText==null || codeText==""){
				// 		$("span.errorMsg").html("验证码错误！");
				// 		// return false;
				// 	}
				//
				// 	$("span.errorMsg").html();
				// })



			});

	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">
									${empty requestScope.msg==true?"":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${ empty requestScope.username==true?"":requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
										   name="password" id="password"  />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email"
										   id="email" value=" ${empty requestScope.email==true?"":requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 150px;" id="code" name="code"
											value="${empty requestScope.code==true?"":requestScope.code}"/>
									<img id="codeImg" alt="" src="kaptcha.jpg" style="float: right; margin-right: 20px; width: 80px; height: 35px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>

</body>
</html>