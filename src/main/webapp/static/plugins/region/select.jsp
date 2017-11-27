<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${ctx }/static/plugins/region/css/SG_area_select.css">
	<script type="text/javascript" src="${ctx }/static/plugins/region/js/jquery.min.js"></script>
	<script type="text/javascript" src='${ctx }/static/plugins/region/js/iscroll.js'></script>   
	<script type="text/javascript" src='${ctx }/static/plugins/region/js/SG_area_select.js'></script>
	<style type="text/css">
		.code{
				padding: 2px 4px;
    			font-size: 14px;
    			color: #c7254e;
    			background-color: #f9f2f4;
    			border-radius: 4px;
		 	 }
	</style>
	<title>地区选择</title>
</head>
<body> 
	<!-- <div style="height: 200px;width: 450px;float: left;padding: 20px">
		<h4>组件演示</h4>
		<div style="display: flex;margin-top: 50px; ">
		<div style="line-height: 30px;height: 30px">地区选择：</div>
		<div><input class="sg-area-result" type="" name="" style="width: 220px;height: 30px;border: 1px solid #ccc;padding-left: 10px"></div>
		<div id="selectBtn" style="width: 30px;height: 30px;border: 1px solid #ccc;cursor: pointer; text-align: center">&gt;</div>  
		</div>
	</div> -->

	<script type="text/javascript">
		$(document).ready(function(){
			$('#selectBtn').on('click',function(){
				$.areaSelect(); 
			});	
			$.areaSelect();
			$.dropdownname = "${dropdownname}";
		});
	</script>
</body>
</html>