<%--
  Created by IntelliJ IDEA.
  User: yan
  Date: 2018/5/31
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/records.js"></script>
    <script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
    <script src="https://img.hcharts.cn/highcharts/modules/oldie.js"></script>
    <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>

    <meta http-equiv="Pragma" content="no-cache">
    <style>
        .content {
            background-color: #eee;
            margin: 15px;
            border-radius: 2px;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, .05);
        }

        body {
            background-color: #eee;
        }
        #search{
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            display: inline-block;
            background: white;
            padding: 0;
        }
        #searchtxt{
            border: none;
            height: 38px;
        }
        #searchbtn{
            margin: auto;
        }
    </style>
</head>
<body class="layui-layout-body">
<div id="container" style="min-width:400px;height:400px"></div>
<script>
    var chart = Highcharts.chart('container', {
        "xAxis": [
            {
                "type": "category",
                "categories": [
                    "一月",
                    "二月",
                    "三月",
                    "四月",
                    "五月",
                    "六月",
                    "七月",
                    "八月",
                    "九月",
                    "十月",
                    "十一月",
                    "十二月"
                ],
                "index": 0,
                "isX": true
            }
        ],
        "series": [
            {
                "name": "进门失败",
                "data": [
                    20,
                    27,
                    10,
                    25,
                    24,
                    35,
                    39,
                    20,
                    34,
                    54,
                    25,
                    35
                ],
                "animation": true,
                "connectEnds": true,
                "enableMouseTracking": false,
                "negativeColor": "#0088FF",
                "color": "#FF0000"
            },
            {
                "name": "进门成功",
                "data": [
                    300,
                    341,
                    338,
                    390,
                    340,
                    300,
                    410,
                    310,
                    240,
                    280,
                    290,
                    328
                ],
                "enableMouseTracking": false,
                "color": "#5FB878"
            }
        ],
        "yAxis": [
            {
                "title": {
                    "text": "开门数"
                },
                "index": 0
            }
        ],
        "chart": {
            "style": {
                "fontFamily": "\"微软雅黑\", Arial, Helvetica, sans-serif",
                "color": "#333",
                "fontSize": "12px",
                "fontWeight": "normal",
                "fontStyle": "normal"
            },
            "type": "column"
        },
        "title": {
            "text": "月平均开门"
        },
        "plotOptions": {
            "line": {
                "animation": false,
                "allowPointSelect": false
            },
            "series": {
                "dataLabels": {
                    "enabled": true
                }
            }
        }
    });
</script>
<div class="content" align="right">
    <div id="search" ><input type="text" id="searchtxt" size="25px"  placeholder="根据位置或用户名搜索"><button id="searchbtn" class="layui-btn">搜索</button></div>
    <div id="recordTable" lay-filter="recordTable"></div>
</div>
<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn-sm" lay-event="showpic">查看开门照片</a>
</script>

</body>
</html>