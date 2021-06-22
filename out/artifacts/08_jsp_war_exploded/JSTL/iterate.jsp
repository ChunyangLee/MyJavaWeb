<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lichunyang.bean.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/22
  Time: 上午8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Student> studentList = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
        studentList.add(new Student(i,"username"+i,"password"+i,"phone"+i,18+i));
    }
    request.setAttribute("stus", studentList);
%>

<table border="1px red solid">
    <tr align="center"><h2>学生信息</h2></tr>
    <tr><th>ID</th><th>用户名</th><th>密码</th><th>年龄</th><th>电话</th><th>varStatus</th></tr>
<c:forEach items="${requestScope.stus}" var="stu" varStatus="status">
    <tr>
        <td>${stu.id}</td>
        <td>${stu.username}</td>
        <td>${stu.password}</td>
        <td>${stu.age}</td>
        <td>${stu.phone}</td>
        <td>${status.index}</td>
    </tr>
</c:forEach>
    <hr>


</table>
</body>
</html>


