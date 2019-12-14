<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student Confirmation Form</title>
</head>
<body>

The student is confirmed: ${student.firstName} ${student.lastName}
<br><br>

Country: ${student.country}
<br><br>

Programming language: ${student.language}
<br><br>

<%--We use JSTL for display multiple check systems.--%>
Operating Systems:
<ul>
    <c:forEach var="temp" items="${student.operatingSystems}">
        <li> ${temp} </li>
    </c:forEach>
</ul>

</body>
</html>