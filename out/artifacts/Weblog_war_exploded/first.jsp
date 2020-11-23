<%@ page import="com.cuiuc.servlet.BlogTypeServlet" %>
<%@ page import="com.cuiuc.servlet.BlogServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        //首次进来访问的页面 作用：跳转到后台BlogServlet
        response.sendRedirect("blogServlet?way=getBlogsList");
    %>
</body>
</html>
