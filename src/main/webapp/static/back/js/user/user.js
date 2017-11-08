var areaData = address;
var $form;
var form;
var $;
layui.config({
	base : contextPath + "/ui/layuiCMS/js/"
}).use(['form','layer','upload','laydate'],function(){
	form = layui.form();
	var layer = parent.layer === undefined ? layui.layer : parent.layer;
		$ = layui.jquery;
		$form = $('form');
		laydate = layui.laydate;
        loadProvince(); //加载省信息

        // loadUserInfo(); //加载用户信息

    layui.upload({

        // AjaxFileUpload.imgUpload(this,"user");

    	// url : contextPath + "/ui/layuiCMS/json/userface.json",
    	url : contextPath + "/upload/uploadImg?type=userImg",
    	success: function(res){
    	    // console.dir(res);

    		// var num = parseInt(4*Math.random());  //生成0-4的随机数
    		// //随机显示一个头像信息
	    	// userFace.src = contextPath + res.data[num].src;
	    	// window.sessionStorage.setItem('userFace',res.data[num].src);

            $('#base').attr("src",contextPath + res.fileUrl)
            $('#img').val(res.fileUrl)

	    }
    });

    //添加验证规则
    form.verify({
        oldPwd : function(value, item){
            if(value != "123456"){
                return "密码错误，请重新输入！";
            }
        },
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if(!new RegExp($("#oldPwd").val()).test(value)){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    //提交个人资料
    form.on("submit(changeUser)",function(data){
        var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        $.ajax({
            type: "POST",
            url: contextPath + '/sys-user/submit',
            data: $('#form-data').serialize(),
            dataType: 'json',
            cache: false,
            // ajax请求失败
            error: function (request) {
                layer.close(index);
                myLayer.alert("请求失败", 7);
            },
            // ajax请求成功
            success: function (data) {
                layer.close(index);
                if (data.msg.success) {
                    myLayer.msg("修改成功", -1, 1000,function(){
                        location.reload();
                    });
                } else {
                    myLayer.alert("请求失败", 7);
                }
            }
        });


    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    //修改密码
    form.on("submit(changePwd)",function(data){
    	var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        },2000);
    	return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})

// 加载用户信息
function loadUserInfo() {
    $.ajax({
        type: "POST",
        url: contextPath + '/sys-user/getInfo',
        data: {},
        dataType: 'json',
        cache: false,
        // ajax请求之前
        beforeSend: function () {
            layer.load(0);
        },
        // ajax请求完成，不管成功失败
        complete: function () {
            layer.closeAll('loading');
        },
        // ajax请求失败
        error: function (request) {
            myLayer.alert("请求失败", 7);
        },
        // ajax请求成功
        success: function (data) {
            if (data.msg.success) {

                var info = data.result;

                $('#code').val(info.code);
                $('#roleId').val(info.roleId);
                $('#name').val(info.name);
                $('#phone').val(info.phone);
                $('#birstday').val(info.birstday);
                $('#email').val(info.email);
                $('#desc').val(info.desc);
                $('#userFace').val(info.img);

                $("input:radio").each(function(){
                    if(Number($(this).val())==info.sex){
                        $(this).attr("checked",true);
                    }
                });
                // $("input[name=sex][value=" + info.sex + "]").attr("checked", true); //设置当前性别选中项

            } else {
                myLayer.alert("请求失败", 7);
            }
        }
    });
}

//加载省数据
function loadProvince() {
    var proHtml = '';
    for (var i = 0; i < areaData.length; i++) {
        proHtml += '<option value="' + areaData[i].provinceCode + '_' + areaData[i].mallCityList.length + '_' + i + '">' + areaData[i].provinceName + '</option>';
    }
    //初始化省数据
    $form.find('select[name=province]').append(proHtml);
    form.render();
    form.on('select(province)', function(data) {
        $form.find('select[name=area]').html('<option value="">请选择县/区</option>');
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadCity(areaData[index].mallCityList);
        } else {
            $form.find('select[name=city]').attr("disabled","disabled");
        }
    });
}
//加载市数据
function loadCity(citys) {
    var cityHtml = '<option value="">请选择市</option>';
    for (var i = 0; i < citys.length; i++) {
        cityHtml += '<option value="' + citys[i].cityCode + '_' + citys[i].mallAreaList.length + '_' + i + '">' + citys[i].cityName + '</option>';
    }
    $form.find('select[name=city]').html(cityHtml).removeAttr("disabled");
    form.render();
    form.on('select(city)', function(data) {
        var value = data.value;
        var d = value.split('_');
        var code = d[0];
        var count = d[1];
        var index = d[2];
        if (count > 0) {
            loadArea(citys[index].mallAreaList);
        } else {
            $form.find('select[name=area]').attr("disabled","disabled");
        }
    });
}
//加载县/区数据
function loadArea(areas) {
    var areaHtml = '<option value="">请选择县/区</option>';
    for (var i = 0; i < areas.length; i++) {
        areaHtml += '<option value="' + areas[i].areaCode + '">' + areas[i].areaName + '</option>';
    }
    $form.find('select[name=area]').html(areaHtml).removeAttr("disabled");
    form.render();
    form.on('select(area)', function(data) {});
}