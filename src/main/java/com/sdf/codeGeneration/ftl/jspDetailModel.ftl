<!DOCTYPE PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../../static/back/jsp/taglibs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="renderer" content="webkit" />
<meta name="keywords" content="${r'${keywords}'}" />
<meta name="description" content="${r'${description}'}" />
<title>${r'${title}'}</title>
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-content">
						<span class="text-muted small pull-right">最后更新：<i
								class="fa fa-clock-o"></i> <fmt:formatDate
									value="${r'${modelMap.updateTime}'}" pattern="yyyy-MM-dd HH:mm:ss" />
							</span>
							<h2>
								<c:if test="${r'${fn:length(modelMap.content) > 10 }'}">${r'${fn:substring(modelMap.content, 0, 10)}'}...</c:if>
								<c:if test="${r'${fn:length(modelMap.content) <= 10 }'}">${r'${modelMap.content}'}</c:if>
								&nbsp;&nbsp;
								<c:if test="${r'${modelMap.status== 1}'}">
									<span class="label label-primary">正常</span>
								</c:if>
								<c:if test="${r'${modelMap.status== 0 }'}">
									<span class="label label-danger">删除</span>
								</c:if>
							</h2>
						<div class="clients-list">
						<ul class="nav nav-tabs">
								<span class="pull-right small text-muted">第${r'${modelMap.id}'}
									个${tableNameStr}</span>
								<li class="active"><a data-toggle="tab" href="#tab-1"><i
										class="fa fa-user"></i>基本信息</a></li>
								<li class=""><a data-toggle="tab" href="#tab-2"><i
										class="fa fa-briefcase"></i> 其他信息</a></li>
							</ul>
							<div class="tab-content">
								<!-- 基本信息 start-->
								<div id="tab-1" class="tab-pane active">
								<div class="table-responsive">
										<table class="table table-striped table-hover">
											<tbody>
												<tr>
													<th>图片</th>
													<td><img alt="image" src="${r'${ctxImg}'}${r'${modelMap.img}'}"
															onerror="imgError(this);"
															style="max-width: 600px; max-height: 200px;"></td>
												</tr>
												
												<!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 数据库各字段信息 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
												<#list properties as pro>  
												<#if pro.proName == "id"||pro.proName == "createTime"||pro.proName == "updateTime"
												||pro.proName == "createUser"||pro.proName == "updateUser"||pro.proName == "createUserId"
												||pro.proName == "updateUserId">
												<#else>
    											<tr>
													<th>${pro.fieldDesc}</th>
													<td>${r'${modelMap.'}${pro.proName}}</td>
												</tr>
												</#if>
												</#list>
												<!--	↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 数据库各字段信息 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->
												
											</tbody>
										</table>
									</div>
								</div>
								<!-- 基本信息 end-->
								
								<!-- 其他信息 start-->
								<div id="tab-2" class="tab-pane">
									<div class="table-responsive">
										<table class="table table-striped table-hover">
											<tbody>
												<tr>
													<th>主键ID</th>
													<td>${r'${modelMap.id}'}</td>
												</tr>
												<tr>
													<th>创建时间</th>
													<td><fmt:formatDate
															value="${r'${modelMap.createTime}'}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												</tr>
												<tr>
													<th>创建人</th>
													<td>${r'${modelMap.createUser}'}</td>
												</tr>
												<tr>
													<th>更新时间</th>
													<td><fmt:formatDate
															value="${r'${modelMap.updateTime}'}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
												</tr>
												<tr>
													<th>更新人</th>
													<td>${r'${modelMap.updateUser}'}</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<!-- 其他信息 end-->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>