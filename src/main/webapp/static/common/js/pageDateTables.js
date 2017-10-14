/**
 * dataTable.js 分页封装
 * 
 * @version 1.0.0
 * @time  2017年4月17日11:12:45
 */
var pageDataTables = {
	aoTable : null,
	iDisplayLength : 10,
	bPaginate : true,
	tableId : '',
	ajaxUrl : '',
	exportUrl : '',
	paraData : {},
	bSort : false,
	bLengthChange : true,
	sLengthMenu : true,
	aoColumns : [],
	addButton : [],
	bStateSave : true,
	reloadData : function() {// 查询分页数据
		/*
		 * this指的是调用函数的那个对象,这里指的是pageDataTables的对象，将对象存到thiz中。
		 * 这样就可以在reloadData对象调用的方法中。取得pageDataTables的值。
		 */
		var thiz = this;
		if (this.aoTable != null) {
			this.aoTable.fnDestroy();
		}

		this.aoTable = jQuery('#' + this.tableId + '').dataTable({

			/*
			 * "sScrollX": "100%", //水平限制宽度 "sScrollXInner": "100%",
			 */
			"sPaginationType" : "full_numbers",
			"iDisplayLength" : this.iDisplayLength,
			"bPaginate" : this.bPaginate,
			"bFilter" : false,// 过滤
			"bSort" : this.bSort,// 排序
			"sLengthMenu" : this.sLengthMenu,// 显示一页几条数据
			"bProcessing" : true, // 是否显示取数据时的那个等待提示
			"bServerSide" : true,// 这个用来指明是通过服务端来取数据
			"aoColumns" : this.aoColumns,
			"bStateSave" : this.bStateSave,// 是否打开客户端状况记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或从头打开浏览器，之前的状况都是保存下来的
			"bLengthChange" : this.bLengthChange,// 改变每页显示数据数量
			"sAjaxSource" : this.ajaxUrl,// 这个是请求的地址
			"fnServerData" : function(sSource, aoData, fnCallback) {
				thiz.fnServerData(sSource, aoData, fnCallback);
			}
		});

		jQuery.each(this.addButton, function(n, data) {
			// console.log(n+' '+data.tableId+' '+data.outId+' '+data.value);
			if (typeof (data) != "undefined") {
				var tableId = data.tableId;
				// 如果按钮json传过来的tableId为空的话则默认使用当前pageDateTable的tableId
				if (typeof (tableId) == "undefined") {
					thiz.loadAddBtn2(thiz.tableId, data);
				}
				thiz.loadAddBtn2(data.tableId, data);
			}
		});

	},
	fnServerData : function(sSource, aoData, fnCallback) {
		this.beforeload();
		jQuery.ajax({
			"type" : "get",
			"contentType" : "application/json",
			"url" : sSource,
			"dataType" : "json",
			"data" : {
				aoData : JSON.stringify(aoData),
				paraData : encodeURI(JSON.stringify(this.paraData))
			}, // 以json格式传递
			"success" : function(resp) {
				fnCallback(resp);
			}
		});
	}, // 获取数据的处理函数
	exportExcel : function() {// 导出EXCEL
		this.beforeload();
		// location.href = this.exportUrl + 'paraData='
		// + JSON.stringify(this.paraData);
		window.open(this.exportUrl + 'paraData='
				+ encodeURI(encodeURI(JSON.stringify(this.paraData))));
	},
	beforeload : function() {

	},
	/*
	 * @author：xuwei “显示 xxx” 后面加载按钮 loadAddBtn (tableId,outId,value)
	 * tableId:当前页面的table的id outId：输出到前台页面的按钮的id value:按钮的显示名称是什么
	 */
	loadAddBtn2 : function(tableId, data) {
		// 在xxx_length中的xxx为PageDataTables的aoColumns的tableId
		if (data.view != 'false') {
			jQuery('#' + tableId + '_length').append(
					'<button style="margin-left: 10px;" class="addButtonStyle" onclick="'
							+ data.onclick + '">' + data.value + '</button>');
		}
	}
};

var PageDataTables = function(tables) {
	applyif(tables, pageDataTables);
	// tables.reloadData();
	return tables;
};

var applyif = function(object, config) {
	if (object) {
		for ( var property in config) {
			if (object[property] === undefined) {
				object[property] = config[property];
			}
		}
	}
	return object;
};
