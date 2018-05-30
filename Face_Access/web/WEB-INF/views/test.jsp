<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/25
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${authorityEntityList}" var="i">
    <c:out value="${i.toString()}"></c:out><br>
</c:forEach>

<p>------------</p>
<c:forEach var="i" items="${entity}">
    <c:out value="${i.toString()}"></c:out><br>
</c:forEach>
</body>
</html>
