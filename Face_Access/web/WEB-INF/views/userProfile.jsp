<%--
  Created by IntelliJ IDEA.
  User: ACM-PC
  Date: 2018/5/21
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>UserProfile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/userProfile.js"></script>
    <style>
        body {
            background-color: #eee;
            margin-top: 15px;
        }
    </style>
</head>

<body>
<div class="layui-fluid">

    <div class="layui-row layui-col-space15">

        <div class="layui-col-md6">

            <div class="layui-card">
                <div class="layui-card-header">个人资料</div>
                <div class="layui-card-body">
                    <div class="layui-form ">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="username" value="${userEntity.userName}" disabled
                                       class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改，一般用于后台登入名</div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="realName" value="${userEntity.realName}" disabled
                                       class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改，用户身份标识</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-card">
                <div class="layui-card-header">密码修改</div>
                <div class="layui-card-body">
                    <div class="layui-form ">
                        <div class="layui-form-item">
                            <label class="layui-form-label">旧密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="oldPassword" id="oldPassword" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="newPassword" id="newPassword" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">确认密码</label>
                            <div class="layui-input-inline">
                                <input type="password" name="confirm" id="confirm" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-filter="passwordChange" id="passwordChange">提交修改</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-col-md6">

            <div class="layui-card">
                <div class="layui-card-header">修改绑定手机</div>
                <div class="layui-card-body">
                    <div class="layui-form ">
                        <div class="layui-form-item">
                            <label class="layui-form-label">旧手机号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="userTel" class="layui-input"
                                       value="${userEntity.userTelephone}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-row">
                                <div class="layui-col-xs7">
                                    <label class="layui-form-label">短信验证码</label>
                                    <input type="text" name="captcha" class="layui-input">
                                </div>
                                <div class="layui-col-xs5">
                                    <button class="layui-btn layui-btn-primary">发送验证码</button>
                                </div>
                            </div>
                        </div>


                        <div class="layui-form-item">
                            <label class="layui-form-label">新手机号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="newTel" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-filter="*" id="telChange">提交修改</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-card">
                <div class="layui-card-header">个人图片</div>
                <div class="layui-card-body">
                    <div class="layui-form ">
                        <div class="layui-form-item">
                            <label class="layui-form-label">个人图片</label>
                            <button type="button" class="layui-btn" id="image" name="image">
                                <i class="layui-icon">&#xe67c;</i>上传图片
                            </button>
                        </div>

                        <div class="layui-form-item" id="preview" hidden>
                            <div class="layui-input-block">
                                <img id="demo1" style="width: 113px">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-filter="*" id="imgSubmit">提交修改</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div id="div_preview" style=" display: none;">
        <img id="img_preview" style="width: 100%"/>
    </div>
</div>
</body>
</html>

