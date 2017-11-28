function navBar(strData) {
    var data;
    if (typeof(strData) == "string") {
        var data = JSON.parse(strData); //部分用户解析出来的是字符串，转换一下
    } else {
        data = strData;
    }
    var ulHtml = '<ul class="layui-nav layui-nav-tree">';
    for (var i = 0; i < data.length; i++) {
        var icon = data[i].icon;
        if (data[i].spread) {
            ulHtml += '<li class="layui-nav-item layui-nav-itemed">';
        } else {
            ulHtml += '<li class="layui-nav-item">';
        }
        var children = data[i].children;
        if (children != undefined && children.length > 0) {
            ulHtml += '<a href="javascript:;">';
            if (icon != undefined && icon != '') {
                if (icon.indexOf("icon-") != -1) {
                    ulHtml += '<i class="iconfont ' + icon + '" data-icon="' + icon + '"></i>';
                } else {
                    ulHtml += '<i class="layui-icon" data-icon="' + icon + '">' + icon + '</i>';
                }
            }
            ulHtml += '<cite>' + data[i].title + '</cite>';
            ulHtml += '<span class="layui-nav-more"></span>';
            ulHtml += '</a>';
            ulHtml += '<dl class="layui-nav-child">';
            for (var j = 0; j < children.length; j++) {
                if (children[j].target == "_blank") {
                    ulHtml += '<dd><a href="javascript:;" data-url="' + contextPath + children[j].href + '" target="' + children[j].target + '">';
                } else {
                    ulHtml += '<dd><a href="javascript:;" data-url="' + contextPath + children[j].href + '">';
                }
                if (children[j].icon != undefined && children[j].icon != '') {
                    if (children[j].icon.indexOf("icon-") != -1) {
                        ulHtml += '<i class="iconfont ' + children[j].icon + '" data-icon="' + children[j].icon + '"></i>';
                    } else {
                        ulHtml += '<i class="layui-icon" data-icon="' + children[j].icon + '">' + children[j].icon + '</i>';
                    }
                }
                ulHtml += '<cite>' + children[j].title + '</cite></a></dd>';
            }
            ulHtml += "</dl>";
        } else {
            if (data[i].target == "_blank") {
                ulHtml += '<a href="javascript:;" data-url="' + contextPath + data[i].href + '" target="' + data[i].target + '">';
            } else {
                ulHtml += '<a href="javascript:;" data-url="' + contextPath + data[i].href + '">';
            }
            if (icon != undefined && icon != '') {
                if (icon.indexOf("icon-") != -1) {
                    ulHtml += '<i class="iconfont ' + icon + '" data-icon="' + icon + '"></i>';
                } else {
                    ulHtml += '<i class="layui-icon" data-icon="' + icon + '">' + icon + '</i>';
                }
            }
            ulHtml += '<cite>' + data[i].title + '</cite></a>';
        }
        ulHtml += '</li>';
    }
    ulHtml += '</ul>';
    return ulHtml;
}
