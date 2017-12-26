<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/static/common/jsp/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <title>${title}</title>

</head>

<body class="childrenBody">
<form class="layui-form">
    <table class="layui-table">
        <colgroup>
            <col width="20%">
            <col width="50%">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>参数说明</th>
            <th>参数值</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>数据库类型</td>
            <td><input type="text" class="layui-input cmsName" lay-verify="required" placeholder="请输入数据库类型(mysql)"></td>
        </tr>
        <tr>
            <td>IP地址及端口号</td>
            <td><input type="text" class="layui-input version" placeholder="请输入IP地址及端口号(127.0.0.1:3306)"></td>
        </tr>
        <tr>
            <td>数据库URL</td>
            <td><input type="text" class="layui-input author" placeholder="请输入数据库URL(sdf_frame?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull)"></td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><input type="text" class="layui-input homePage" placeholder="请输入用户名(root)"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" class="layui-input server" placeholder="请输入密码(root)"></td>
        </tr>
        <tr>
            <td>业务名称</td>
            <td><input type="text" class="layui-input dataBase" placeholder="请输入业务名称(代码生成)"></td>
        </tr>
        <tr>
            <td>表名</td>
            <td><input type="text" class="layui-input maxUpload" placeholder="请输入表名(sys_code)"></td>
        </tr>
        <tr>
            <td>包名</td>
            <td><input type="text" class="layui-input userRights" placeholder="请输入包名(com.sdf.common)"></td>
        </tr>
        <tr>
            <td>项目根路径</td>
            <td><input type="text" class="layui-input keywords" placeholder="请输入项目根路径(D:/eclipse-workspace/eclipse-j2ee/SDF-FRAME/)"></td>
        </tr>
        </tbody>
    </table>
    <div class="layui-form-item" style="text-align: right;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="systemParameter">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<script type="text/javascript" src="${ctx}/static/back/js/system/code/page.js?v=1.0"></script>

</body>

</html>