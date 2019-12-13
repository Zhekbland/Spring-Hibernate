<%--
  Created by IntelliJ IDEA.
  User: Жека
  Date: 13.12.2019
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World - Input Form</title>
</head>
<body>
    <form action="processForm" method="get">
        <input type="text" name="studentName" placeholder="What's your name?"/>
        <input type="submit"/>
    </form>
<%--    <form action="${pageContext.servletContext.contextPath}/processForm" method="get">--%>
<%--        <input type="text" name="studentName" placeholder="What's your name?"/>--%>
<%--        <input type="submit"/>--%>
<%--    </form>--%>
</body>
</html>
