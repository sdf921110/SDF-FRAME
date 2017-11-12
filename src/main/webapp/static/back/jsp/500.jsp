<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="keywords" content="${keywords }" />
<meta name="description" content="${description }" />
<title>${title }</title>
<link href="${staticPath }/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${staticPath }/css/font-awesome.css?v=4.4.0"
	rel="stylesheet">

<link href="${staticPath }/css/animate.css" rel="stylesheet">
<link href="${staticPath }/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">

	<div class="middle-box text-center animated fadeInDown">
		<h1>500</h1>
		<h3 class="font-bold">服务器内部错误</h3>

		<div class="error-desc">
			服务器好像出错了，蓝瘦香菇~ <br /> <a href="${ctx }/index/manage/page"
				class="btn btn-primary m-t">主页</a>
		</div>
	</div>

	<!-- 全局js -->
	<script src="${staticPath }/js/jquery.min.js?v=2.1.4"></script>
	<script src="${staticPath }/js/bootstrap.min.js?v=3.3.6"></script>

</body>

</html>
