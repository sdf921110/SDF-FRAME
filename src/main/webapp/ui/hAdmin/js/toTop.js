/**
 * 返回顶部封装
 * 
 * @version 1.0.0
 * @time  2016年11月19日23:27:52
 */
jQuery(document)
		.ready(
				function($) {
					if ($("meta[name=toTop]").attr("content") == "true") {
						$(
								"<div id='toTop'><img src='"
										+ contextPath
										+ "/adminHtml/img/top.png' title='返回顶部'></div>")
								.appendTo('body');
						$("#toTop").css({
							width : '50px',
							height : '50px',
							bottom : '15px',
							right : '15px',
							position : 'fixed',
							cursor : 'pointer',
							zIndex : '999999',
						});
						if ($(this).scrollTop() == 0) {
							$("#toTop").hide();
						}
						$(window).scroll(function(event) {
							/* Act on the event */
							if ($(this).scrollTop() == 0) {
								$("#toTop").hide();
							}
							if ($(this).scrollTop() != 0) {
								$("#toTop").show();
							}
						});
						$("#toTop").click(function(event) {
							/* Act on the event */
							$("html,body").animate({
								scrollTop : "0px"
							}, 666)
						});
					}
				});