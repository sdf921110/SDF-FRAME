<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="renderer" content="webkit"/>
    <title>百度Uploader上传控件</title>
    <script type="text/javascript">
        var contextPath = '${ctx}';
    </script>

    <link rel="stylesheet" type="text/css"
          href="${ctx}/static/plugins/webuploader-0.1.5/css/webuploader.css?v=100">
    <link rel="stylesheet" type="text/css"
          href="${ctx}/static/plugins/webuploader-0.1.5/css/my_style.css?v=100">
    <%-- <link href="${ctx}/adminHtml/css/bootstrap.min.css?v=3.3.6"
           rel="stylesheet">--%>
</head>
<body>
<input type="hidden" id="pathType" name="pathType"
       value="${param.pathType }"/>
<input type="hidden" id="fileType" name="fileType"
       value="<%String str = request.getParameter("fileType");
			str = java.net.URLDecoder.decode(str, "GB2312");
			str = new String(str.getBytes("ISO-8859-1"));
			out.print(str);%>"/>
<input type="hidden" id="tableName" name="tableName"
       value="${param.tableName }"/>
<input type="hidden" id="tableId" name="tableId"
       value="${param.tableId }"/>
<input type="hidden" id="isThumb" name="isThumb" value="0"/>
<input type="hidden" id="isImg" name="isImg" value="1"/>
<div id="uploader">
    <div class="queueList">
        <div id="dndArea" class="placeholder">
            <div id="filePicker"></div>
            <p>或将照片拖到这里，单次最多可选300张</p>
        </div>
    </div>

    <div class="statusBar" style="display: none;">
        <div class="progress">
            <span class="text">0%</span> <span class="percentage"></span>
        </div>
        <div class="info"></div>
        <div class="btns">
            <div id="filePicker2"></div>
            <div class="uploadBtn">开始上传</div>
        </div>
    </div>
</div>

<!-- layer-fixed 固定底部操作栏 -->
<div class="layer-fixed">
    <div class="layer-bottom-sure">
        <div class="layer-sure">
            <button class="btn btn-primary btn-sm" id="layer-submit">确 定</button>
        </div>
    </div>
</div>
<!-- layer-fixed 固定底部操作栏 -->

<script src="${ctx}/static/plugins/jquery-1.8.3/jquery.min.js?v=100"></script>

<%--<script type="text/javascript"
        src="${ctx}/static/plugins/jquery-3.2.1/jquery.js?v=100"></script>--%>
<!-- layer弹层组件 -->
<link rel="stylesheet"
      href="${ctx}/static/plugins/layer-v3.1.0/layer/theme/default/layer.css?v=3.1.0"
      type="text/css"/>
<script src="${ctx}/static/plugins/layer-v3.1.0/layer/layer.js?v=3.1.0"></script>
<script src="${ctx}/static/common/js/myLayer.js?v=1.0.0"></script>
<!-- layer弹层组件 -->



<script type="text/javascript"
        src="${ctx}/static/plugins/webuploader-0.1.5/js/webuploader.js?v=100"></script>
<script type="text/javascript"
        src="${ctx}/static/plugins/webuploader-0.1.5/js/my_uploadImages.js?v=100"></script>

</body>
</html>