<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h3> Spring REST Demo</h3>

    <hr>

    <a href="${pageContext.request.contextPath}/test/hello">Hello</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/api/students">Students</a>
    <br><br>
    <a href="${pageContext.request.contextPath}/api/mapstudents">Map of students</a>
</body>
</html>