/**
 * base64预览图片
 * 
 * @version 2.0.0
 * @time 2017年4月17日11:16:18
 * 
 * 2017年4月27日22:23:42 修改一个页面多个input file上传 v2.0.0
 */
$(function() {

	// 前端只需要给input file绑定这个change事件即可（下面两个方法不需要修改）获取到图片
	$('.imgUpload').bind('change', function(event) {

		var thiz = this;
		var file = $(this)[0].files[0];

		if (file.size > 2097152) {
			myLayer.msg("上传图片请小于2M！", -1, 1000);
			return false;
		}
		if (!/image\/\w+/.test(file.type)) {
			myLayer.msg("上传文件必须为图片！", -1, 1000);
			return false;
		}

		var imageUrl = getObjectURL(file);

		convertImgToBase64(imageUrl, function(base64Img) {

			// base64Img为转好的base64,放在img src直接前台展示(<img
			// src="data:image/jpg;base64,/9j/4QMZRXh...." />)
			// alert(base64Img);

			// $(".base").attr("src", base64Img);

			$(thiz).next().attr("src", imageUrl);
			// base64转图片不需要base64的前缀data:image/jpg;base64
			// alert(base64Img.split(",")[1]);

			// $(".uploadFile").val(base64Img.split(",")[1]);
			$(thiz).next().next().val(base64Img.split(",")[1]);
		}, thiz.name);
		event.preventDefault();
	});
});

// 生成图片的base64编码
function convertImgToBase64(url, callback, outputFormat) {
	// html5 的convas画布
	var canvas = document.createElement('CANVAS');
	var ctx = canvas.getContext('2d');
	var img = new Image;
	img.crossOrigin = 'Anonymous';
	img.onload = function() {
		var width = img.width;
		var height = img.height;
		// 按比例压缩2倍
		// var rate = (width<height ? width/height : height/width)/2;
		// 原比例生成画布图片
		var rate = 1;
		canvas.width = width * rate;
		canvas.height = height * rate;
		ctx.drawImage(img, 0, 0, width, height, 0, 0, width * rate, height
				* rate);
		// canvas.toDataURL 返回的是一串Base64编码的URL，当然,浏览器自己肯定支持

		if (outputFormat == "files" || outputFormat == ""
				|| outputFormat == undefined) {
			outputFormat = "image/jpeg";
		}
		// image/png
		var dataURL = canvas.toDataURL(outputFormat, 0.8); // 包括图片质量
		callback.call(this, dataURL);
		canvas = null;
	};
	img.src = url;
}

// createobjecturl()静态方法创建一个包含了DOMString代表参数对象的URL。该url的声明周期是在该窗口中.也就是说创建浏览器创建了一个代表该图片的Url.
function getObjectURL(file) {
	var url = null;
	if (window.createObjectURL != undefined) {
		// basic
		url = window.createObjectURL(file);
	} else if (window.URL != undefined) {
		// mozilla(firefox)
		url = window.URL.createObjectURL(file);
	} else if (window.webkitURL != undefined) {
		// web_kit or chrome
		url = window.webkitURL.createObjectURL(file);
	}
	return url;
}
