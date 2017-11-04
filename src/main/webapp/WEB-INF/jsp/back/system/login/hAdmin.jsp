<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <meta name="keywords" content="hAdmin - 后台管理系统"/>
    <meta name="description" content="hAdmin - 后台管理系统"/>
    <title>hAdmin - 后台管理系统 - 登录</title>

    <link rel="shortcut icon" href="../../../../../favicon.ico">
    <link rel="stylesheet"
          href="${ctx}/static/plugins/login/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="${ctx}/static/plugins/login/css/camera.css"/>
    <%-- <link rel="stylesheet"
        href="${ctx}/static/plugins/login/bootstrap-responsive.min.css" /> --%>
    <link rel="stylesheet"
          href="${ctx}/static/plugins/login/matrix-login.css"/>
    <link rel="stylesheet"
          href="${ctx}/static/plugins/login/font-awesome.css"/>
    <script src="${ctx}/static/plugins/jquery-1.8.3/jquery.min.js"></script>

    <!-- layer弹层组件 -->
    <link rel="stylesheet"
          href="${ctx}/static/plugins/layer-v3.1.0/layer/theme/default/layer.css?v=3.1.0"
          type="text/css"/>
    <script type="text/javascript"
            src="${ctx}/static/plugins/layer-v3.1.0/layer/layer.js?v=3.1.0"></script>
    <script type="text/javascript"
            src="${ctx}/static/common/js/myLayer.js?v=1.0.0"></script>
    <!-- layer弹层组件 -->
</head>
<body>

<div
        style="width: 100%; text-align: center; margin: 0 auto; position: absolute;">
    <div id="loginbox">
        <form action="" method="post" name="loginForm" id="loginForm">
            <div class="control-group normal_text">
                <h4>
                    <img src="${ctx}/static/plugins/login/logo.png" alt="Logo"/>
                    <p style="font-size: 15px; padding-top: 10px;">hAdmin - 后台管理系统</p>
                </h4>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div class="main_input_box">
							<span class="add-on bg_lg"> <i><img height="37"
                                                                src="${ctx}/static/plugins/login/user.png"/></i>
							</span><input type="text" name="loginname" id="loginname" value=""
                                          placeholder="请输入用户名"/>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <div class="main_input_box">
							<span class="add-on bg_ly"> <i><img height="37"
                                                                src="${ctx}/static/plugins/login/suo.png"/></i>
							</span><input type="password" name="password" id="password"
                                          placeholder="请输入密码" value=""/>
                    </div>
                </div>
            </div>
            <div style="float: right; padding-right: 10%;"></div>
            <div class="form-actions">
                <div style="width: 86%; padding-left: 8%;">

                    <div style="float: left;">
                        <input name="form-field-checkbox" id="saveid" type="checkbox"
                               onclick="savePaw();" style="padding-top: 0px;"/>
                    </div>
                    <div style="float: left;">
                        <div style="float: left; margin-top: 3px; margin-right: 2px;">
                            <font color="white">记住密码</font>
                        </div>
                    </div>

                    <span class="pull-right"><a
                            href="javascript:quxiao();" class="btn btn-success">取消</a></span> <span
                        class="pull-right"><a onclick="severCheck();"
                                              class="flip-link btn btn-info" id="to-recover">登录</a></span>

                </div>
            </div>

        </form>

        <div class="controls">
            <div class="main_input_box">
                <font color="white"><span id="nameerr">Copyright ©
							SDF-FRAME 2017</span></font>
            </div>
        </div>
    </div>
</div>
<div id="templatemo_banner_slide" class="container_wapper">
    <div class="camera_wrap camera_emboss" id="camera_slide">
        <div
                data-src="${ctx}/static/plugins/login/images/banner_slide_01.jpg"></div>
        <div
                data-src="${ctx}/static/plugins/login/images/banner_slide_02.jpg"></div>
        <div
                data-src="${ctx}/static/plugins/login/images/banner_slide_03.jpg"></div>
    </div>
    <!-- #camera_wrap_3 -->
</div>

<script type="text/javascript">
    //服务器校验
    function severCheck() {
        if (check()) {
            var contextPath = '${ctx}';

            var loginname = $("#loginname").val();
            var password = $("#password").val();
            $.ajax({
                type: "POST",
                url: contextPath + '/login/submit',
                data: {
                    code: loginname,
                    password: password,
                    loginUrl: "/login/hAdmin" // 多个登录页时，进入系统后点击退出登录跳转此页面
                },
                dataType: 'json',
                cache: false,
                // ajax请求之前
                beforeSend: function () {
                    layer.load(0);
                },
                // ajax请求完成，不管成功失败
                complete: function () {
                    layer.closeAll('loading');
                },
                // ajax请求失败
                error: function (request) {
                    myLayer.alert("请求失败", 7);
                },
                success: function (msg) {
                    if (msg.success) {
                        saveCookie();
                        window.location.href = contextPath + msg.info;
                    } else {
                        $("#loginname").tips({
                            side: 3,
                            msg: msg.info,
                            bg: '#FF5080',
                            time: 15
                        });
                        $("#loginname").focus();
                    }
                }
            });
        }
    }

    $(document).ready(function () {

    });

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#to-recover").trigger("click");
        }
    });

    //客户端校验
    function check() {

        if ($("#loginname").val() == "") {

            $("#loginname").tips({
                side: 3,
                msg: '用户名不得为空',
                bg: '#AE81FF',
                time: 3
            });

            $("#loginname").focus();
            return false;
        } else {
            $("#loginname").val(jQuery.trim($('#loginname').val()));
        }

        if ($("#password").val() == "") {

            $("#password").tips({
                side: 3,
                msg: '密码不得为空',
                bg: '#AE81FF',
                time: 3
            });

            $("#password").focus();
            return false;
        }

        $("#loginbox").tips({
            side: 1,
            msg: '正在登录 , 请稍后 ...',
            bg: '#68B500',
            time: 10
        });

        return true;
    }

    function savePaw() {
        if (!$("#saveid").attr("checked")) {
            $.cookie('loginname', '', {
                expires: -1
            });
            $.cookie('password', '', {
                expires: -1
            });
            $("#loginname").val('');
            $("#password").val('');
        }
    }

    function saveCookie() {
        if ($("#saveid").attr("checked")) {
            $.cookie('loginname', $("#loginname").val(), {
                expires: 30
            });
            $.cookie('password', $("#password").val(), {
                expires: 30
            });
        }
    }

    function quxiao() {
        $("#loginname").val('');
        $("#password").val('');
    }

    jQuery(function () {
        var loginname = $.cookie('loginname');
        var password = $.cookie('password');
        if (typeof (loginname) != "undefined"
            && typeof (password) != "undefined") {
            $("#loginname").val(loginname);
            $("#password").val(password);
            $("#saveid").attr("checked", true);
        }
    });
</script>

<script src="${ctx}/static/plugins/login/js/jquery.easing.1.3.js"></script>
<%-- 	<script
    src="${ctx}/static/plugins/login/js/jquery.mobile.customized.min.js"></script> --%>
<script src="${ctx}/static/plugins/login/js/camera.min.js"></script>
<script src="${ctx}/static/plugins/login/js/templatemo_script.js"></script>
<script type="text/javascript"
        src="${ctx}/static/plugins/login/js/jquery.tips.js"></script>
<script type="text/javascript"
        src="${ctx}/static/plugins/jquery-cookie-1.4.1/jquery.cookie.js"></script>
</body>

</html>