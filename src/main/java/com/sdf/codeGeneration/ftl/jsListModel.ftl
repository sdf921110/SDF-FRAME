/**
 * ${tableNameStr}列表
 */
$(function() {

	$('#status').chosen();
	$('.dateTime').datetimepicker();

	dataTable.reloadData();

	// 搜索
	$('#search-button').click(function() {
		dataTable.reloadData();
	});

	// 下拉搜索
	$('#status').change(function() {
		dataTable.reloadData();
	});

});

// 增加
var add = function() {
	${className}.add();
};

// 编辑
var edit = function(id, name) {
	${className}.edit(id);
};

// 详情
var detail = function(id, name) {
	${className}.detail(id);
};

//删除
var del = function(id, name) {
	${className}.del(id, name);
};

// 导出excel
var exportToExcel = function() {
	dataTable.exportExcel();
};

// 数据列表
var dataTable = new PageDataTables({
	tableId : 'dataTable',
	ajaxUrl : ${className}.pageUrl,
	exportUrl : ${className}.exportUrl,
	aoColumns : ${className}.tableColumns,
	beforeload : function() {
		this.paraData = {
			"beginTime" : $('#beginTime').val(),
			"endTime" : $('#endTime').val(),
			<#list properties as pro>
			<#if pro.proName != "createTime"&&pro.proName != "updateTime"&&pro.proName != "id"&&pro.proName != "createUserId"&&pro.proName != "updateUserId"&&pro.proName != "img">
				<#if pro.intProType == "Int">
			"${pro.proName}" : $('#${pro.proName}').val()<#if pro_has_next>,<#else></#if>
					<#else>
			"${pro.proName}" : $('#${pro.proName}').val().trim() == '' ? '' : '%'
			+ $('#${pro.proName}').val().trim() + '%'<#if pro_has_next>,<#else></#if>		
				</#if>
			<#else>
			</#if>
			</#list>
		};
	}
});
