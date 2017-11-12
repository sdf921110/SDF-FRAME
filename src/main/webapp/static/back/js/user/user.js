var areaData = address;
var $form;
var form;
var $;
layui.config({
    base: contextPath + "/ui/layuiCMS/js/"
}).use(['form', 'layer', 'upload', 'laydate'], function () {
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
        url: contextPath + "/upload/uploadImg?type=userImg",
        success: function (res) {
            // console.dir(res);

            // var num = parseInt(4*Math.random());  //生成0-4的随机数
            // //随机显示一个头像信息
            // userFace.src = contextPath + res.data[num].src;
            // window.sessionStorage.setItem('userFace',res.data[num].src);

            $('#base').attr("src", contextPath + res.fileUrl)
            $('#img').val(res.fileUrl)

        }
    });

    //添加验证规则
    form.verify({
        oldPwd: function (value, item) {
            if (value != "123456") {
                return "密码错误，请重新输入！";
            }
        },
        newPwd: function (value, item) {
            if (value.length < 6) {
                return "密码长度不能小于6位";
            }
        },
        confirmPwd: function (value, item) {
            if (!new RegExp($("#oldPwd").val()).test(value)) {
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    //提交个人资料
    form.on("submit(changeUser)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
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
                    myLayer.msg("修改成功", -1, 1000, function () {
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
    form.on("submit(changePwd)", function (data) {
        var index = layer.msg('提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        setTimeout(function () {
            layer.close(index);
            layer.msg("密码修改成功！");
            $(".pwd").val('');
        }, 2000);
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

                $("input:radio").each(function () {
                    if (Number($(this).val()) == info.sex) {
                        $(this).attr("checked", true);
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
    $.ajax({
        type: "POST",
        url: contextPath + '/api/address/select',
        data: {"level": 1},
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
                var proHtml = '';
                for (var i = 0; i < info.length; i++) {
                    if ($('#province-hid').val() == info[i].name) {
                        proHtml += '<option value="' + info[i].id + '_' + info[i].name + '" selected>' + info[i].name + '</option>';
                    } else {
                        proHtml += '<option value="' + info[i].id + '_' + info[i].name + '">' + info[i].name + '</option>';
                    }
                }
                //初始化省数据
                $form.find('select[id=province]').append(proHtml);
                form.render();
                form.on('select(province)', function (data) {
                    var arr = data.value.split("_");
                    $('#province-hid').val(arr[1]);
                    loadCity(arr[0]);
                });

            } else {
                myLayer.alert("请求失败", 7);
            }
        }
    });
}

//加载市数据
function loadCity(parent_id) {
    $.ajax({
        type: "POST",
        url: contextPath + '/api/address/select',
        data: {"level": 2, "parent_id": parent_id},
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
                var cityHtml = '<option value="">请选择市</option>';
                for (var i = 0; i < info.length; i++) {
                    cityHtml += '<option value="' + info[i].id + '_' + info[i].name + '">' + info[i].name + '</option>';
                }
                //初始化市数据
                $form.find('select[id=city]').html(cityHtml).removeAttr("disabled");
                form.render();
                form.on('select(city)', function (data) {
                    var arr = data.value.split("_");
                    $('#city-hid').val(arr[1]);
                    loadCounty(arr[0]);
                });
            } else {
                myLayer.alert("请求失败", 7);
            }
        }
    });
}

//加载区/县数据
function loadCounty(parent_id) {
    $.ajax({
        type: "POST",
        url: contextPath + '/api/address/select',
        data: {"level": 3, "parent_id": parent_id},
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
                var countyHtml = '<option value="">请选择区/县</option>';
                for (var i = 0; i < info.length; i++) {
                    countyHtml += '<option value="' + info[i].id + '_' + info[i].name + '">' + info[i].name + '</option>';
                }
                //初始化区/县数据
                $form.find('select[id=county]').html(countyHtml).removeAttr("disabled");
                form.render();
                form.on('select(county)', function (data) {
                    var arr = data.value.split("_");
                    $('#county-hid').val(arr[1]);
                    loadTown(arr[0]);
                });
            } else {
                myLayer.alert("请求失败", 7);
            }
        }
    });
}

//加载街道/乡镇数据
function loadTown(parent_id) {
    $.ajax({
        type: "POST",
        url: contextPath + '/api/address/select',
        data: {"level": 4, "parent_id": parent_id},
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
                var townHtml = '<option value="">请选择街道/乡镇</option>';
                for (var i = 0; i < info.length; i++) {
                    townHtml += '<option value="' + info[i].id + '_' + info[i].name + '">' + info[i].name + '</option>';
                }
                //初始化街道/乡镇数据
                $form.find('select[id=town]').html(townHtml).removeAttr("disabled");
                form.render();
                form.on('select(town)', function (data) {
                    var arr = data.value.split("_");
                    $('#town-hid').val(arr[1]);
                    loadVillage(arr[0]);
                });
            } else {
                myLayer.alert("请求失败", 7);
            }
        }
    });
}

//加载社区/村数据
function loadVillage(parent_id) {
    $.ajax({
        type: "POST",
        url: contextPath + '/api/address/select',
        data: {"level": 5, "parent_id": parent_id},
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
                var villageHtml = '<option value="">请选择社区/村</option>';
                for (var i = 0; i < info.length; i++) {
                    villageHtml += '<option value="' + info[i].id + '_' + info[i].name + '">' + info[i].name + '</option>';
                }
                //初始化社区/村数据
                $form.find('select[id=village]').html(villageHtml).removeAttr("disabled");
                form.render();
                form.on('select(village)', function (data) {
                    var arr = data.value.split("_");
                    $('#village-hid').val(arr[1]);
                });
            } else {
                myLayer.alert("请求失败", 7);
            }
        }
    });
}
