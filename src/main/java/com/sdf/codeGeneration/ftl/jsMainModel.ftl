$(function() {
	${className}.loadButton();
});

/**
 * ${tableNameStr}
 */
var ${className} = {
	pageUrl : contextPath + "/${smallClassName}/manage/queryPage?t="
			+ new Date().getTime(),
	listUrl : contextPath + "/${smallClassName}/manage/queryList?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/${smallClassName}/manage/exportToExcel?",
	tableColumns : [
			{
				"fnRender" : function(obj) {
					var img = obj.aData['img'];
					var name = obj.aData['name'];
					return '<a href="'
							+ contextPathImg
							+ img
							+ '" target="_blank"><img src="'
							+ contextPathImg
							+ img
							+ '"  title="'
							+ name
							+ '" alt="${tableNameStr}图片" onError="imgError(this)" width="50px;" height="50px;" /></a>';
				},
				"sClass" : "center"
			},
			<#list properties as pro>
			<#if pro.proName == "id"||pro.proName == "createUserId"
			||pro.proName== "updateUserId"||pro.proName== "status"
			||pro.proName== "img">
			<#else>
			{
				"mDataProp" : "${pro.proName}",
				"sClass" : "center"
			},
			</#if>
			</#list>
			{
				"fnRender" : function(obj) {
					var status = obj.aData['status'];
					switch (status) {
					case '1':
						return '<span class="label label-info">正常</span>';
					case '0':
						return '<span class="label label-danger">删除</span>';
					default:
						return '<span class="label label-primary">其他</span>';
					}
				},
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['name'];
					
					name = name.length > 10 ? name.substring(0, 10) + "..."
							: name;

					var thdMenuMap = sessionStorage.getItem("thdMenuMap");
					var url = window.location.href;
					var menuCode = url.split("menuCode=")[1];

					var menu = JSON.parse(thdMenuMap)[menuCode];

					var jsHtml = '';
					for ( var i in menu) {
						var menuUrl = menu[i].menuUrl;

						if (menuUrl.indexOf("()") == -1) {
							jsHtml += '<li><a href="' + menuUrl + '(' + id
									+ ',\'' + name + '\');">'
									+ menu[i].menuName + '</a></li>';
						}
					}

					return '<div class="btn-group">'
							+ '<button data-toggle="dropdown" class="btn btn-info dropdown-toggle"'
							+ 'aria-expanded="false">'
							+ '操作 <span class="caret"></span></button>'
							+ '<ul class="dropdown-menu" style="width:50px;">'
							+ jsHtml + '</ul></div>';

				},
				"sClass" : "center"
			} ],
	// 加载权限按钮
	loadButton : function() {
		var html = '';
		var thdMenuMap = sessionStorage.getItem("thdMenuMap");
		var url = window.location.href;
		var menuCode = url.split("menuCode=")[1];

		var menu = JSON.parse(thdMenuMap)[menuCode];

		for ( var i in menu) {
			var icon = menu[i].icon;
			var menuUrl = menu[i].menuUrl;
			icon = icon.split("=");

			if (menuUrl.indexOf("()") != -1) {
				html += '&nbsp;&nbsp;<button class="' + icon[0]
						+ '" type="button" onclick="' + menuUrl
						+ '"><i class="' + icon[1] + '"></i>&nbsp;'
						+ menu[i].menuName + '</button>';
			}
		}

		$('#right-button').html(html);

	},
	// 新增${tableNameStr}
	add : function() {
		layer.open({
			type : 2,
			title : '新增${tableNameStr}',
			area : [ '800px', '600px' ],
			fix : false, // 不固定
			maxmin : true,
			scrollbar : false, // 固定浏览器滚动条
			content : contextPath + '/${smallClassName}/manage/add'
		});
	},
	// 编辑${tableNameStr}
	edit : function(id) {
		layer.open({
			type : 2,
			title : '编辑${tableNameStr}',
			area : [ '800px', '600px' ],
			fix : false,
			maxmin : true,
			scrollbar : false,
			content : contextPath + '/${smallClassName}/manage/edit/' + id
		});
	},
	// ${tableNameStr}详情
	detail : function(id, type) {
		layer.open({
			type : 2,
			title : '${tableNameStr}详细信息',
			area : [ '800px', '600px' ],
			fix : false,
			maxmin : true,
			scrollbar : false,
			content : contextPath + '/${smallClassName}/manage/detail/' + id
		});
	},
	// 删除${tableNameStr}
	del : function(id,name) {
		var selValue = [];
		selValue.push(id);
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		layer.confirm('提示：确认删除 ['+name+'] ?', {
			btn : [ '确认', '取消' ]
		}, function() {
			$.ajax({
				cache : true,
				type : "POST",
				url : contextPath + '/${smallClassName}/manage/updateStatus?',
				data :  'ids=' + JSON.stringify(selValue) + "&status=0",
				async : true,
				dataType : 'json',
				beforeSend : function() {
					layer.load(0);
				},
				complete : function() {
					layer.closeAll('loading');
				},
				error : function(request) {
					myLayer.alert("请求失败", 7);
				},
				success : function(data) {
					if (data.success) {
						myLayer.alert("操作成功", 1, function(index) {
							layer.close(index);
							dataTable.reloadData();
						});
					} else {
						myLayer.alert(data.info, 2);
					}
				}
			});
		}, function() {

		});
	},
	callBack : function() {
		location.reload();
	},
	// 验证${tableNameStr}表单
	validate : function(formObj) {
		formObj.validate({
			rules : {
			<#list properties as pro>
			<#if pro.proName == "id"||pro.proName == "createUserId"
			||pro.proName== "updateUserId"||pro.proName== "isDelete">
			<#else>
			'${pro.proName}' : {
					"required" : true,
					"maxlength" : 30
				}<#if pro_has_next>,<#else></#if>
			</#if>
			</#list>
			}
		});
	},
	// 提交${tableNameStr}
	submitForm : function(formObj, callback) {
		$.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/${smallClassName}/manage/submit?',
			data : formObj.serialize(),
			async : false,
			dataType : 'json',
			beforeSend : function() {
				layer.load(0);
			},
			complete : function() {
				layer.closeAll('loading');
			},
			error : function(request) {
				myLayer.alert("请求失败", 7);
			},
			success : function(data) {
				if (data.success) {
					myLayer.alert("操作成功", 1, function(index) {
						callback();
					});
				} else {
					myLayer.alert(data.info, 2);
				}
			}
		});
	}
};
