<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/31
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>404</title>
</head>
<body>
Page Not Found
<c:out value="${user.username}  ${user.password}"/><br>
You Have Authority Of
<c:forEach var="i" items="${user.authorities}">
    <c:out value="${i}"/>
</c:forEach>
<a href="<c:url value="/logout"/>">Logout</a>

</body>
</html>
