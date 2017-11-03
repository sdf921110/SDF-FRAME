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
    <meta name="keywords" content="layuiCMS UI - 后台管理系统"/>
    <meta name="description" content="layuiCMS UI - 后台管理系统"/>
    <title>layuiCMS UI - 后台管理系统 - 登录</title>

    <link rel="shortcut icon" href="${ctx }/ui/layuiCMS/favicon.ico">

    <link rel="stylesheet" href="${ctx }/ui/layuiCMS/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${ctx }/ui/layuiCMS/css/login.css" media="all" />

    <script src="${ctx }/static/plugins/jquery-1.8.3/jquery.min.js"></script>

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

<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
    <source src="${ctx}/ui/layuiCMS/page/login/login.mp4" type="video/mp4">
    <!-- 此视频文件为支付宝所有，在此仅供样式参考，如用到商业用途，请自行更换为其他视频或图片，否则造成的任何问题使用者本人承担，谢谢 -->
</video>
<div class="video_mask"></div>
<div class="login">
    <h1>layuiCMS-管理登录</h1>
    <form class="layui-form">
        <div class="layui-form-item">
            <input class="layui-input" id="loginname" name="loginname" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
        </div>
        <div class="layui-form-item">
            <input class="layui-input" id="password" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
        </div>
        <div class="layui-form-item form_code">
            <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
            <div class="code"><img src="${ctx }/ui/layuiCMS/images/code.jpg" width="116" height="36"></div>
        </div>
        <a class="layui-btn login_btn"  id="severCheck">登录</a>
    </form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        layui.config({
            base : "js/"
        }).use(['form','layer'],function(){
            var form = layui.form(),
                layer = parent.layer === undefined ? layui.layer : parent.layer,
                $ = layui.jquery;
            //video背景
            $(window).resize(function(){
                if($(".video-player").width() > $(window).width()){
                    $(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
                }else{
                    $(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
                }
            }).resize();

            //登录按钮事件
//            form.on("submit(login)",function(data){
//                window.location.href = "../../index.html";
//                return false;
//            })
        })


        $('#severCheck').click(function () {
            severCheck();
        })

    });

    //登录按钮事件
    function severCheck() {
        var contextPath = '${ctx}';
        var loginname = $("#loginname").val();
        var password = $("#password").val();
        $.ajax({
            type: "POST",
            url: contextPath + '/login/submit',
            data: {
                code: loginname,
                password: password
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
                console.dir(msg.success);
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

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            $("#to-recover").trigger("click");
        }
    });

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


<script type="text/javascript" src="${ctx }/ui/layuiCMS/layui/layui.js?v=1.0.9"></script>
<%--<script type="text/javascript" src="${ctx }/ui/layuiCMS/page/login/login.js"></script>--%>
<script type="text/javascript"
        src="${ctx }/static/plugins/login/js/jquery.tips.js"></script>
<script type="text/javascript"
        src="${ctx }/static/plugins/jquery-cookie-1.4.1/jquery.cookie.js?v=1.4.1"></script>
</body>

</html>