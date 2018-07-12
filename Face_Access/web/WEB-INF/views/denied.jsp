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
    <title>访问被拒绝</title>
    <style>
        body {
            background: #fff;
            color: #1d2129;
            line-height: 1.34;
            margin: 0;
            padding: 0;
            font-family: Helvetica, Arial, sans-serif;
            font-size: 13px;
        }

        h2 {
            font-size: 25px;
            line-height: 28px;
            margin: 40px 0 20px;
        }

        h3 {
            font-size: 17px;
            line-height: 28px;
            margin: 20px 0;
        }

        .content {
            margin: 0;
            text-align: center;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        img {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        a {
            color: #365899;
            cursor: pointer;
            text-decoration: none;
            font-size: 13px;
            line-height: 20px;
        }
    </style>
</head>
<body>
<div class="content">
    <h2>访问被拒绝</h2>
    <h3>确认您是否有该权限，或重新登陆</h3>
    <img src="<c:url value="/static/images/error.png"/>" width="282" height="250"><br>
    <div>
        <a href="#" onclick="history.back();">返回上一页</a>
        <span role="presentation" aria-hidden="true"> · </span>
        <a href="<c:url value="/home"/>">返回主页</a>
    </div>
</div>

</body>
</html>
