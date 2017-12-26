/**
 *${tableNameStr}提交(新增，修改)
 */
var index = '';
var dataTable = '';
$(function() {

	index = parent.layer.getFrameIndex(window.name); // 获取窗口索引

	dataTable = parent.dataTable;// parent.dataTable：父页面 dataTable
	
	$('#status').chosen();
	
	// 表单验证
	${className}.validate($("#form1"));

	// 取消
	$('#layer-cancel').click(function() {
		parent.layer.close(index);
	});

	// 确定
	$('#layer-submit').on('click', function() {
		var formObj = $("#form1");
		if (formObj.valid()) {
			${className}.submitForm(formObj, callback);
		}
	});

});

var callback = function() {
	parent.layer.close(index);
	dataTable.reloadData();
};

