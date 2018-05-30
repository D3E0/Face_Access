<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/20
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserForm</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/adduser.js"></script>
    <style>

        body {
            background-color: #eee;
        }

        .father {
            background-color: white;
            padding: 20px;
            height: 400px;
            width: 350px;
            /*center*/
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            /*shadow*/
            box-shadow: 0 9px 30px -6px rgba(0, 0, 0, .2), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 18px 20px -10px rgba(0, 0, 0, .04), 0 10px 20px -10px rgba(0, 0, 0, .04);
            border: 1px solid #dadada;
            border-radius: 10px;
        }

        .son {
            position: absolute;
            top: 50%;
            transform: translate(0%, -50%);
            margin-left: 20px;
        }

        #keywords {
            border: 1px solid #cfcfcf;
            border-radius: 8px;
            box-shadow: 0 10px 55px 0 rgba(0, 0, 0, .3);
            background-color: white;
            width: 220px;
            position: absolute;
            z-index: 100;
            margin-left: 110px;
            margin-top: -5px;
        }

        #result-list {
            list-style: none;
        }

        .resultitem {
            padding: 10px 20px;
            color: #666;
            font-size: 14px;
            border-top: 1px solid rgba(0, 0, 0, .1);
        }

    </style>
</head>
<body>
<div class="father">
    <form class="layui-form layui-form-pane son" action="/processuser">

        <div class="layui-form-item">
            <label class="layui-form-label">人员姓名</label>
            <div class="layui-input-inline">
                <select name="username" id="username" lay-search>
                </select>
            </div>
        </div>

        <%--<div class="layui-form-item" id="keywords">--%>
        <%--<ul id="result-list">--%>
        <%--&lt;%&ndash;<li class="resultitem">坚果 TNT</li>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<li class="resultitem">坚果 R1</li>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<li class="resultitem">坚果 PRO 2</li>&ndash;%&gt;--%>
        <%--&lt;%&ndash;&lt;%&ndash;<li class="resultitem">畅呼吸净化器</li>&ndash;%&gt;&ndash;%&gt;--%>
        <%--&lt;%&ndash;<li class="resultitem">畅呼吸净化器</li>&ndash;%&gt;--%>
        <%--</ul>--%>
        <%--</div>--%>

        <div class="layui-form-item">
            <label class="layui-form-label">人员 ID</label>
            <div class="layui-input-inline">
                <input disabled type="text" name="userid" class="layui-input" autocomplete="false">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-inline">
                <input disabled type="text" name="usertel" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">授权日期</label>
            <div class="layui-inline">
                <input disabled type="text" name="startDate" class="layui-input" id="startDate">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">失效日期</label>
            <div class="layui-inline">
                <input type="text" name="endDate" class="layui-input" id="endDate">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="*">立即提交</button>
            </div>
        </div>

    </form>
</div>
</body>
</html>
