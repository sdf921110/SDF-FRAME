<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.lucksoft.utils.*,java.util.Calendar" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学校选择</title>
<script type="text/javascript" src="${ctx }/static/plugins/school/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/plugins/school/js/school.js"></script>
</head>
<body>

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}
.demo{width:600px;margin:20px auto;}
.demo th,.demo td{font-size:14px;padding-bottom:17px;line-height:28px;color:#666;font-family:"新宋体";font-weight:normal;}
.demo th em{color:#ff0000;font-style:normal;}
.demo td .stext{border:1px solid #ccc;font-size:14px;height:26px;line-height:26px;padding:0 3px;width:214px;color:#666;}
/* choose-box-wrapper */
#choose-box-wrapper{/* width:652px; *//* background:#000;background-color:rgba(0, 0, 0, 0.5); */padding:10px;border-radius:5px;display:none;}
#choose-box{border:1px solid #005EAC;width:100%/* 650px */;background:#fff;}
#choose-box-title{background:#3777BC;color:white;padding:4px 10px 5px;font-size:14px;font-weight:700;margin:0;}
#choose-box-title span{font-family:Tahoma, Verdana, STHeiTi, simsun, sans-serif;}
#choose-a-province, #choose-a-school{margin:5px 8px 10px 8px;border:1px solid #C3C3C3;}
#choose-a-province a{display:inline-block;height:18px;line-height:18px;color:#005EAC;text-decoration:none;font-size:9pt;font-family:Tahoma, Verdana, STHeiTi, simsun, sans-serif;margin:2px 5px;padding:1px;text-align:center;}
#choose-a-province a:hover{text-decoration:underline;cursor:pointer;}
#choose-a-province .choosen{background:#005EAC;color:white;}
#choose-a-school{overflow-x:hidden;overflow-y:auto;height:200px;}
#choose-a-school a{height:18px;line-height:18px;color:#005EAC;text-decoration:none;font-size:9pt;font-family:Tahoma, Verdana, STHeiTi, simsun, sans-serif;float:left;width:160px;margin:4px 12px;padding-left:10px;background:url(http://pic002.cnblogs.com/images/2012/70278/2012072500060712.gif) no-repeat 0 9px;}
#choose-a-school a:hover{background:#005EAC;color:#fff;}
#choose-box-bottom{background:#F0F5F8;padding:8px;text-align:right;border-top:1px solid #CCC;height:40px;}
#choose-box-bottom input{vertical-align:middle;text-align:center;background:#005EAC;color:white;border-top:1px solid #B8D4E8;border-left:1px solid #B8D4E8;border-right:1px solid #114680;border-bottom:1px solid #114680;cursor:pointer;width:60px;height:25px;margin-top:6px;margin-right:6px;}
</style>
<!-- 
<div class="demo">
	
	<table width="100%">
		<tr>
			<th align="right">学校类型：</th>
			<td>大学</td>
		</tr>
		<tr>
			<th align="right"><em>*</em> 学校名称：</th>
			<td><input type="text" class="stext" name="school" id="school-name" value="请选择大学" onblur="if(this.value==''){this.value='请选择大学'}" onfocus="if(this.value=='请选择大学'){this.value=''}" onclick="pop()" /></td>
		</tr>	
	</table>
</div>
 -->
<div id="choose-box-wrapper">
	<div id="choose-box">
		<div id="choose-box-title">
			<span>选择学校</span>
		</div>
		<div id="choose-a-province"></div>
		<div id="choose-a-school"></div>
		<div id="choose-box-bottom">
			<input type="botton" onclick="clearData()" value="清空" />
			<input type="botton" onclick="hide()" value="关闭" />
		</div>
	</div>
</div><!--choose-box-wrapper end-->
  
<script type="text/javascript">
//弹出窗口
function pop(){
	//将窗口居中
	makeCenter();

	//初始化省份列表
	initProvince();

	//默认情况下, 给第一个省份添加choosen样式
	$('[province-id="1"]').addClass('choosen');

	//初始化大学列表
	initSchool(1);
}

//隐藏窗口
function hide(){
	//$('#choose-box-wrapper').css("display","none");
	
	var dropDown = window.parent.$id("${dropdownname}").objects[0];
	var return_userData = {
		id : ""
		,name : ""
		,type : 0
	};
	dropDown.close(return_userData);
}

//清空窗口
function clearData(){
	var dropDown = window.parent.$id("${dropdownname}").objects[0];
	var return_userData = {
		id : ""
		,name : ""
		,type : 1
	};
	dropDown.close(return_userData);
}

function initProvince(){
	
	//原先的省份列表清空
	$('#choose-a-province').html('');
	
	for(i=0;i<schoolList.length;i++){
		$('#choose-a-province').append('<a href="javascript:void(0);" class="province-item" province-id="'+schoolList[i].id+'">'+schoolList[i].name+'</a>');
	}
	
	//添加省份列表项的click事件
	$('.province-item').bind('click',function(){
		var item=$(this);
		var province = item.attr('province-id');
		var choosenItem = item.parent().find('.choosen');
		if(choosenItem)
		$(choosenItem).removeClass('choosen');
		item.addClass('choosen');
		
		//更新大学列表
		initSchool(province);
	});
}

function initSchool(provinceID){

	//原先的学校列表清空
	$('#choose-a-school').html('');
	var schools = schoolList[provinceID-1].school;
	for(i=0;i<schools.length;i++){
		$('#choose-a-school').append('<a href="javascript:void(0);" class="school-item" school-id="'+schools[i].id+'">'+schools[i].name+'</a>');
	}
	
	//添加大学列表项的click事件
	$('.school-item').bind('click', function(){
		var item=$(this);
		var school = item.attr('school-id');

		//更新选择大学文本框中的值
		//$('#school-name').val(item.text());
		//关闭弹窗
		//hide();
       	var dropDown = window.parent.$id("${dropdownname}").objects[0];
		var return_userData = {
			id : school
			,name : item.text()
			,type : 1
		};
		dropDown.close(return_userData);
	});
}

function makeCenter(){
	$('#choose-box-wrapper').css("display","block");
	$('#choose-box-wrapper').css("position","absolute");
	//$('#choose-box-wrapper').css("top", Math.max(0, (($(window).height() - $('#choose-box-wrapper').outerHeight()) / 2) + $(window).scrollTop()) + "px");
	//$('#choose-box-wrapper').css("left", Math.max(0, (($(window).width() - $('#choose-box-wrapper').outerWidth()) / 2) + $(window).scrollLeft()) + "px");
}
pop();
</script>
</body>
</html>