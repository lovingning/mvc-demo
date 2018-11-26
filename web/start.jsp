<%--
  Created by IntelliJ IDEA.
  User: mnlin
  Date: 2018/11/7
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title><%=basePath.substring(0, 1)%>
    </title>
</head>
<body>
静止的首页信息:<br><br>
<%=basePath%>
</body>
</html>
