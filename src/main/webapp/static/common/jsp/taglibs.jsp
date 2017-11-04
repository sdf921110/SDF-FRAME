<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- <%
	String path = request.getContextPath();
	String cssJsPath = path + "/common/front";
%> --%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- <c:set var="ctxImg"
	value="${pageContext.request.contextPath}/download.img?path=" /> --%>
<c:set var="ctxImg" value="${pageContext.request.contextPath}"/>

<c:set var="title" value="layuiCMS UI - 后台管理系统"/>
<c:set var="keywords" value="layuiCMS UI - 后台管理系统"/>
<c:set var="description" value="layuiCMS UI - 后台管理系统"/>

<link rel="shortcut icon" href="${ctx}/favicon.ico">

<link rel="stylesheet" href="${ctx}/ui/layuiCMS/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all"/>
<link rel="stylesheet" href="${ctx}/ui/layuiCMS/css/main.css" media="all"/>

<script src="${ctx}/static/plugins/jquery-1.8.3/jquery.min.js"></script>

<script type="text/javascript" src="${ctx}/ui/layuiCMS/layui/layui.js"></script>

<%--GET http://localhost/Frame/index/js/bodyTab.js 404--%>
<%--<script type="text/javascript" src="${ctx}/ui/layuiCMS/js/bodyTab.js"></script>--%>

<script type="text/javascript" src="${ctx}/ui/layuiCMS/js/leftNav.js"></script>

<!-- layer弹层组件 -->
<link rel="stylesheet"
      href="${ctx}/static/plugins/layer-v3.1.0/layer/theme/default/layer.css?v=3.1.0"
      type="text/css"/>
<script type="text/javascript"
        src="${ctx}/static/plugins/layer-v3.1.0/layer/layer.js?v=3.1.0"></script>
<script type="text/javascript"
        src="${ctx}/static/common/js/myLayer.js?v=1.0.0"></script>
<!-- layer弹层组件 -->

<%--
<script src="${ctx}/static/common/js/ajaxfileupload.js?v=1.0.0"></script>
<script src="${ctx}/static/common/js/myAjaxFileUpload.js?v=1.0.0"></script>

<!-- blueimp gallery -->
<script
        src="${ctx}/ui/hAdmin/js/plugins/blueimp/jquery.blueimp-gallery.min.js?v=2.11.1"></script>--%>

<script type="text/javascript">
    $(function () {
        try {
            // 单选，多选框样式
            $('.i-checks').iCheck({
                checkboxClass: 'icheckbox_square-green',
                radioClass: 'iradio_square-green',
            });
        } catch (e) {

        }
    });
    var contextPath = '${ctx}';
    //var contextPathImg = '${ctx}/download.img?path='; // 图片存在其他路径（不在项目中）
    var contextPathImg = '${ctx}'; // 图片存在其他路径（在项目中）
    var systemName = '${sessionScope.session_user.systemName}';
    var imgError = function (thiz) {
        thiz.src = contextPath
            + '/static/common/img/default_loadError.png?v=100';
    };
    var imgUpload = function (thiz) {
        thiz.src = contextPath + '/static/common/img/default_upload.png?v=100';
    };
</script>

<!-- 若页面需默认用极速核，增加标签：<meta name="renderer" content="webkit">
若页面需默认用ie兼容内核，增加标签：<meta name="renderer" content="ie-comp">
若页面需默认用ie标准内核，增加标签：<meta name="renderer" content="ie-stand"> -->
