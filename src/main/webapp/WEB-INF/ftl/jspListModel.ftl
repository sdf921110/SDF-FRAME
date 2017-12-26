<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="renderer" content="webkit" />
<meta name="keywords" content="${r'${keywords}'}" />
<meta name="description" content="${r'${description}'}" />
<title>${r'${title}'}</title>
</head>

<body class="gray-bg">
	<!-- 查询条件 -->
	<div class="row wrapper border-bottom white-bg page-heading">
		<br />
		<div class="col-sm-12">
			<div class="chzn-select-div">
				<select id="status" style="width: 200px;" tabindex="2">
					<option value="">请选择状态</option>
					<option value="1">正常</option>
					<option value="0">删除</option>
				</select>
			</div>
		<#list properties as pro>
		<#if pro.proName != "id"&&pro.proName != "isDelete"&&pro.proName != "createUserId"&&pro.proName != "updateUserId"&&pro.proName != "createTime"&&pro.proName != "updateTime"&&pro.proName != "img">
		${pro.fieldDesc}：<input placeholder="输入${pro.fieldDesc}" class="form-control-row" type="text" id="${pro.proName}"><#if pro_has_next>&nbsp; &nbsp;<#else></#if>
		<#else>
		</#if>
		</#list>
		 <br /> <br />
		创建时间：<input placeholder="创建时间（启）" class="form-control-row dateTime"
				type="text" id="beginTime">&nbsp; 到 &nbsp;<input
				placeholder="创建时间（止）" class="form-control-row dateTime" type="text"
				id="endTime">&nbsp; &nbsp;
			<button class="btn btn-info" type="button" id="search-button">
				<i class="fa fa-search"></i>&nbsp;查询
			</button>
		</div>
	</div>
	<!-- 查询条件 -->

	<div class="wrapper wrapper-content animated fadeInRight">
		<!-- 权限按钮 -->
		<div id="right-button"></div>
		<br />
		<!-- 权限按钮 -->
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<table
							class="table table-striped table-bordered table-hover dataTables-example"
							id="dataTable">
							<thead>
								<tr>
									<th class="head0">图片</th>
									<#list properties as pro>
									<#if pro.proName == "id"||pro.proName == "createUserId"||
									pro.proName== "updateUserId"||pro.proName== "status"||pro.proName== "img">
									<#else>
									<th class="head0">${pro.fieldDesc}</th>
									</#if>
									</#list>
									<th class="head0">状态</th>
									<th class="head0">更多操作</th>
								</tr>
							</thead>
							<!-- 数据列表开始 -->
							<tbody></tbody>
							<!-- 数据列表结束 -->
							<tfoot>
								<tr>
									<th class="head0">图片</th>
									<#list properties as pro>
									<#if pro.proName == "id"||pro.proName == "createUserId"||
									pro.proName== "updateUserId"||pro.proName== "status"||pro.proName== "img">
									<#else>
									<th class="head0">${pro.fieldDesc}</th>
									</#if>
									</#list>
									<th class="head0">状态</th>
									<th class="head0">更多操作</th>
								</tr>
							</tfoot>
						</table>

					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- 自定义js -->
	<script src="${r'${staticPath}'}/js/content.js?v=100"></script>
	<script src="${r'${ctx}'}/static/back/js/common/${'${smallClassName}'?cap_first}.js?v=1.0.0"></script>
	<script src="${r'${ctx}'}/static/back/js/${smallClassName}/${smallClassName}List.js?v=1.0.0"></script>

</body>

</html>