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
						<div class="clients-list">
							<div class="tab-content">
								<!-- 基本信息 -->
								<div class="table-responsive">
									<form id="form1">
										<table class="table table-striped table-hover">
											<tbody>
											
												<!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 固定模板 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
												<tr>
													<th>图片</th>
													<td>
														<div>
															<input type="file" name="files" class="imgUpload" /> <img
																class="base" alt="${tableNameStr}图片" title="${tableNameStr}图片"
																src="" onerror="imgUpload(this)"
																style="max-width: 600px; max-height: 200px;" /> 
																<!-- 使用base64 上传时,是把base64 编码的字符串传入后台 -->
																<input type="hidden" name="baseImg" class="uploadFile" /> 
																<input type="hidden" name="img" id="img" />
														</div>
													</td>
												</tr>
												<tr>
													<th>名称</th>
													<td><input type="text" id="name" name="name"
														class="form-control-row-350" /></td>
												</tr>
												<tr>
													<th>描述</th>
													<td><textarea id="desc" name="desc"
															class="form-control-row"></textarea></td>
												</tr>
												<tr>
													<th>状态</th>
													<td><select id="status" name="status"
														style="width: 200px;" tabindex="2">
															<option value="1">正常</option>
															<option value="0">删除</option>
													</select></td>
												</tr>
												<tr>
													<th>是否删除</th>
													<td>
														<div class="radio i-checks">
															<label> <input type="radio" value="1"
																name="isDelete" checked="checked"> <i></i> 正常
															</label><label> <input type="radio" value="0"
																name="isDelete"> <i></i> 删除
															</label>
														</div>
													</td>
												</tr>
												<!-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 固定模板 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->
												
												<!-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 数据库各字段信息 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ -->
												<#list properties as pro>  
												<#if pro.proName == "id"||pro.proName == "status"||pro.proName == "isDelete"||
												pro.proName == "createTime"||pro.proName == "updateTime"||pro.proName == "createUser"||
												pro.proName == "updateUser"||pro.proName == "createUserId"||pro.proName == "updateUserId"||
												pro.proName == "img"||pro.proName == "desc">
												<#else>
    											<tr>
													<th>${pro.fieldDesc}</th>
													<td><input type="text" id="${pro.proName}" name="${pro.proName}"
														class="form-control-row-350" /></td>
												</tr>
												</#if>
												</#list>
												<!--	↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 数据库各字段信息 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ -->
												
											</tbody>
										</table>
										<input type="hidden" name="status" value="1" />
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!-- layer-fixed 固定底部操作栏 -->
	<div class="layer-fixed">
		<div class="layer-bottom">
			<div class="layer-left">
				<button class="btn btn-primary btn-sm" id="layer-submit">
					<i class="fa fa-check"></i>&nbsp;&nbsp;确 定
				</button>
			</div>
			<div class="layer-right">
				<button class="btn btn-info btn-sm" id="layer-cancel">
					<i class="fa fa-reply"></i>&nbsp;&nbsp;取 消
				</button>
			</div>
		</div>
	</div>
	<!-- layer-fixed 固定底部操作栏 -->

	<script src="${r'${ctx}'}/static/back/js/common/${'${smallClassName}'?cap_first}.js?v=1.0.0"></script>
	<script src="${r'${ctx}'}/static/back/js/${smallClassName}/${smallClassName}Submit.js?v=1.0.0"></script>

</body>

</html>