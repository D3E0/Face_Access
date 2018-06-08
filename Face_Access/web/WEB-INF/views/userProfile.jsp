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
                    <div class="layui-form " style="width: 378px;">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-block">
                                <input type="text" name="username" value="${userEntity.userName}" disabled
                                       class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-card">
                <div class="layui-card-header">密码修改</div>
                <div class="layui-card-body">
                    <div class="layui-form " style="width: 378px;">
                        <input type="text" name="userId" value=${userEntity.userId} hidden>

                        <div class="layui-form-item">
                            <label class="layui-form-label">旧密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="oldPassword" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">新密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="password" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">确认密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="confirm" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="updatePassword">提交修改</button>
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
                    <div class="layui-form " style="width: 378px;">
                        <input type="text" name="userId" value=${userEntity.userId} hidden>

                        <div class="layui-form-item">
                            <label class="layui-form-label">旧手机号</label>
                            <div class="layui-input-block">
                                <input type="text" disabled name="userTel" class="layui-input"
                                       value="${userEntity.userTelephone}">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">验证码</label>
                                <div class="layui-input-inline " style="width: 150px;">
                                    <input type="text" name="verifyCode" lay-verify="required"
                                           placeholder="验证码" class="layui-input">
                                </div>
                                <div class="layui-input-inline" style="width: 50px">
                                    <button type="button" id="getCode" class="layui-btn layui-btn-primary">
                                        获取验证码
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">新手机号</label>
                            <div class="layui-input-block">
                                <input type="text" name="telephone" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="updateTelephone">提交修改</button>
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
                        <input type="text" name="userId" value=${userEntity.userId} hidden>

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
</div>
</body>
</html>

