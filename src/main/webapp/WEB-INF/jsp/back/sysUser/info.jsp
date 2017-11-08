<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/static/common/jsp/taglibs.jsp" %>

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

    <link rel="stylesheet" href="${ctx}/ui/layuiCMS/css/user.css" media="all"/>
</head>

<body class="childrenBody">
<form class="layui-form" id="form-data">
    <div class="user_left">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" id="code" name="code" value="${modelMap.code}" disabled
                       class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block">
                <input type="text" id="roleId" name="roleId" value="${modelMap.roleId}" disabled
                       class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">真实姓名</label>
            <div class="layui-input-block">
                <input type="text" id="name" name="name" value="${modelMap.name}" placeholder="请输入真实姓名"
                       lay-verify="required" class="layui-input realName">
            </div>
        </div>
        <div class="layui-form-item" pane="">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block userSex">
                <input type="radio" name="sex" value="1" title="男" <c:if test="${modelMap.sex==1}">checked</c:if>>
                <input type="radio" name="sex" value="2" title="女" <c:if test="${modelMap.sex==2}">checked</c:if>>
                <input type="radio" name="sex" value="0" title="保密" <c:if test="${modelMap.sex==0}">checked</c:if>>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="tel" id="phone" name="phone" value="${modelMap.phone}" placeholder="请输入手机号码"
                       lay-verify="required|phone" class="layui-input userPhone">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生年月</label>
            <div class="layui-input-block">
                <input type="text" id="birstday" name="birstday" value="<fmt:formatDate value="${modelMap.birstday}"
															pattern="yyyy-MM-dd" />" placeholder="请输入出生年月"
                       lay-verify="required|date" onclick="layui.laydate({elem: this,max: laydate.now()})"
                       class="layui-input userBirthday">
            </div>
        </div>
        <div class="layui-form-item userAddress">
            <label class="layui-form-label">家庭住址</label>
            <div class="layui-input-inline">
                <select name="province" lay-filter="province">
                    <option value="${modelMap.province}">请选择省</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="city" lay-filter="city" disabled>
                    <option value="${modelMap.city}">请选择市</option>
                </select>
            </div>
            <div class="layui-input-inline">
                <select name="area" lay-filter="area" disabled>
                    <option value="${modelMap.county}">请选择县/区</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">兴趣爱好</label>
            <div class="layui-input-block userHobby">
                <input type="checkbox" name="hobby" value="Java" title="Java" <c:if test="${fn:contains(modelMap.hobby,'Java')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="Javascript" title="Javascript" <c:if test="${fn:contains(modelMap.hobby,'Javascript')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="HTML5" title="HTML5" <c:if test="${fn:contains(modelMap.hobby,'HTML5')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="CSS3" title="CSS3" <c:if test="${fn:contains(modelMap.hobby,'CSS3')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="PHP" title="PHP" <c:if test="${fn:contains(modelMap.hobby,'PHP')}">checked</c:if>>
                <input type="checkbox" name="hobby" value=".net" title=".net" <c:if test="${fn:contains(modelMap.hobby,'.net')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="C#" title="C#" <c:if test="${fn:contains(modelMap.hobby,'C#')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="Angular" title="Angular" <c:if test="${fn:contains(modelMap.hobby,'Angular')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="VUE" title="VUE" <c:if test="${fn:contains(modelMap.hobby,'VUE')}">checked</c:if>>
                <input type="checkbox" name="hobby" value="XML" title="XML" <c:if test="${fn:contains(modelMap.hobby,'XML')}">checked</c:if>>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" id="email" name="email" value="${modelMap.email}" placeholder="请输入邮箱"
                       lay-verify="required|email" class="layui-input userEmail">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">自我评价</label>
            <div class="layui-input-block">
                <textarea placeholder="请输入内容" id="desc" name="desc"
                          class="layui-textarea myself">${modelMap.desc}</textarea>
            </div>
        </div>
    </div>
    <div class="user_right">
        <input type="file" name="uploadFile" class="layui-upload-file imgUpload" lay-title="掐指一算，我要换一个头像了">
        <p><%--由于是纯静态页面，所以只能显示一张随机的图片--%></p>
        <img src="${ctxImg}${modelMap.img}" class="layui-circle" id="base" onerror="imgError(this);"/>
        <%--<input type="hidden" name="baseImg" id="uploadFile" />--%>
        <input type="hidden" name="img" id="img" value="${modelMap.img}" />
    </div>
    <div class="layui-form-item" style="margin-left: 5%;">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="changeUser">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <input type="hidden" value="${modelMap.id}" id="id" name="id">
    <input type="text" name="code" value="${modelMap.code}" hidden>
    <input type="text" name="roleId" value="${modelMap.roleId}" hidden>
</form>

<script type="text/javascript" src="${ctx}/static/common/js/address.js?v=1.0"></script>
<script type="text/javascript" src="${ctx}/static/back/js/user/user.js?v=1.0"></script>

</body>

</html>