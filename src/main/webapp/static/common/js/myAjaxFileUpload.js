/**
 * ajax上传封装
 * 
 * @version 1.0.0
 * @time  2016年11月19日23:27:52
 */
var AjaxFileUpload = {
	// 图片上传
	imgUpload : function(thiz, type) {
		var filepath = jQuery(thiz).val();
		var extStart = filepath.lastIndexOf(".");
		var ext = filepath.substring(extStart, filepath.length).toUpperCase();
		if (filepath != "") {
			if (ext != ".PNG" && ext != ".JPG" && ext != ".GIF") {
				myLayer.alert("只能上传png,jpg,gif图片", 5);
				jQuery(thiz).outerHTML = jQuery(thiz).outerHTML;
				jQuery(thiz).val('');
				return;
			}
			jQuery.ajaxFileUpload({
				url : contextPath + '/upload/uploadImg?type='+type,
				secureuri : false,
				fileElementId : 'imgUrlFile',
				dataType : 'json',
				beforeSend : function() {

				},
				success : function(data, status) {
					if (data.success) {
						jQuery("#imgUrl").attr("src", contextPath + data.fileUrl);
//						jQuery("#imgUrl").css("width","480px"); 
//						jQuery("#imgUrl").css("height","276px"); 
						jQuery("#img").attr("value", data.fileUrl);
					} else {
						myLayer.alert(data.info, 1);
					}
				},
				error : function(e) {
					myLayer.alert(e, 1);
				}
			});
		} else {
			myLayer.alert("请选择图片", 1);
		}
}
};
