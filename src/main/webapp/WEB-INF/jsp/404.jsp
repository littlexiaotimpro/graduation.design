<%--
  Created by IntelliJ IDEA.
  User: XiaoSi
  Date: 2019/2/23
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath %>"/>
    <title>404</title>
</head>
<body>
<h1>404 Not Found</h1>
</body>
</html>
