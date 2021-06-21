<%--
  Created by IntelliJ IDEA.
  User: lichunyang
  Date: 2021/6/21
  Time: 上午11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table{
            border: 1px red solid;
            width: 600px;
            border-collapse: collapse;
        }
        /*td{*/
        /*    border: 1px red solid;*/
        /*}*/
    </style>
</head>
<body>
<table align="center">
    <%
        for (int i = 1; i <= 9; i++) {
     %>
            <tr>
    <%
            for (int j = 1; j <= i; j++) {
    %>
                <td>
                    <%= i+"x"+j+"="+i*j %>
                </td>
    <%
            }
     %>
            </tr>
            <br/>
    <%
        }
    %>
</table>
</body>
</html>
