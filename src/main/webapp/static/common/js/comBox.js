/**
 * combox 分页查询封装
 * 
 * @version 1.0.0
 * @time  2017年4月17日11:15:38
 */
var combox = {
	id : '',
	url : '',
	paraData : {},
	cText : 'name',
	cValue : 'id',
	emptyText : '请选择',
	emptyValue : '',
	datas : {},
	requestData : function() {
		var thiz = this;
		this.beforeload();
		jQuery.ajax({
			type : "GET",
			url : this.url,
			data : 'paraData=' + encodeURI(encodeURI(JSON.stringify(this.paraData))),
			async : true,
			dataType : 'json',
			success : function(data) {
				var obj = jQuery('#' + thiz.id);
				obj.html('<option value="">' + thiz.emptyText + '</option>');
				thiz.datas = {};
				jQuery.each(data, function(index, item) {
					var value = item[thiz.cValue];
					thiz.datas[value] = item;
					if (thiz.emptyValue == value) {
						obj.append('<option value="' + value
								+ '" selected="selected">' + item[thiz.cText]
								+ '</option>');
					} else {
						obj.append('<option value="' + value + '">'
								+ item[thiz.cText] + '</option>');
					}
				});
				thiz.chosen();
			},
			error : function() {
				//alert("请求异常");
			}
		});
	},
	beforeload : function() {

	},
	chosen : function() {
		var thiz = this;
		jQuery("#" + this.id).chosen();
		jQuery("#" + this.id).change(function() {
			var value = jQuery("#" + this.id).val();
			var obj = thiz.datas[value];
			thiz.change(value, obj);
		});
	},
	change : function(v, obj) {

	},

};

var Combox = function(tables) {
	applyif(tables, combox);
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
