/**
 * layer.js 封装
 *
 * @version 1.0.0
 * @time  2016年9月22日15:52:40
 */
var myLayer = {

    // ********************************PC版********************************//

    /**
     * alert弹出框
     *
     * @param content
     *            提示内容
     * @param options
     *            eg:{icon: 1} 1:成功 2:失败 3:询问 4:锁 5:不高兴 6:高兴 7:警告
     * @param yes
     *            回调函数
     */
    alert: function (content, options, yes) {
        layer.alert("提示：" + content, {
            icon: options
        }, yes);
    },
    /**
     * msg弹出框
     *
     * @param content
     *            提示内容
     * @param options
     *            eg:{icon: -1} -1:默认 0:警告 1:成功 2:失败 3:询问 4:锁 5:不高兴 6:高兴 7:警告
     * @param time
     *            关闭时间(毫秒)
     * @param yes
     *            回调函数
     */
    msg: function (content, options, time, yes) {
        layer.msg("提示：" + content, {
            icon: options,
            time: time
        }, yes);
    },
    /**
     * 确认框
     *
     * @param content
     *            提示内容
     * @param successFun
     *            成功方法
     * @param cancelFun
     *            取消方法
     */
    confrim: function (content, successFun, cancelFun) {
        layer.confirm('提示：' + content, {
            btn: ['确认', '取消']
        }, function () {
            successFun
        }, function () {
            cancelFun
        });
    },
    /**
     * tips层
     *
     * @param content
     *            提示内容
     * @param selector
     *            #id或者.class
     * @param options
     *            eg:{tips: 2} 1:上 2:右（默认） 3:下 4:左
     * @param tipsMore
     *           true:不销毁之前的
     */
    tips: function (content, selector, options, tipsMore) {
        layer.tips('提示：' + content, selector, {
            tips: options,
            tipsMore: tipsMore
        });
    },

    // ********************************PC版********************************//

    // *****************************Mobile版*****************************//

    /**
     * 底部提示
     *
     * @param content
     *            提示内容
     * @param time
     *            关闭时间(秒)
     */
    m_msgFootButton: function (content, time) {
        layer.open({
            content: "提示：" + content,
            skin: 'footer',
            time: time
        });
    },
    /**
     * 底部对话
     *
     * @param content
     *            提示内容
     */
    m_alertFootButton: function (content) {
        layer.open({
            content: content,
            btn: ['删除', '取消'],
            skin: 'footer',
            yes: function (index) {
                layer.open({
                    content: '执行删除操作'
                })
            }
        });
    },
    /**
     * alert弹出框
     *
     * @param content
     *            提示内容
     */
    m_alert: function (content) {
        layer.open({
            content: '提示：' + content,
            btn: ['我知道了']
        });
    },
    /**
     * msg弹出框
     *
     * @param content
     *            提示内容
     * @param time
     *            关闭时间(秒)
     */
    m_msg: function (content, time, callback) {
        layer.open({
            content: "提示：" + content,
            skin: 'msg',
            time: time,
        });
        if (callback != undefined) {
            callback(time);
        }
    },
    /**
     * 页面层
     */
    m_page: function () {
        layer
            .open({
                type: 1,
                content: '可传入任何内容，支持html。一般用于手机页面中',
                anim: 'up',
                style: 'position:fixed; bottom:0; left:0; width: 100%; height: 200px; padding:10px 0; border:none;'
            });
    },
    /**
     * 确认框
     *
     * @param content
     *            提示内容
     * @param successFun
     *            成功方法
     * @param cancelFun
     *            取消方法
     */
    m_confrim: function (content, successFun, cancelFun) {
        layer.open({
            content: '提示：' + content,
            btn: ['确认', '取消'],
            yes: function (index) {
                successFun
            },
            no: function (index) {
                cancelFun
            }
        });
    },
    /**
     * loading带文字
     */
    m_loading: function (content) {
        layer.open({
            shadeClose: false,
            type: 2,
            content: content != undefined ? content : '加载中'
        });
    }

// *****************************Mobile版*****************************//

};
