package com.sdf.common.pojo;

/**
 * 消息提示类
 *
 * @author SDF
 * @date 2016年9月8日
 */
public class MSG {
    private boolean isSuccess = true;
    private Object info = "执行成功";
    private int code = 0;

    /**
     * @param code      状态码
     * @param isSuccess 是否成功
     * @param info      提示内容
     * @return
     */
    public static MSG createMSG(int code, boolean isSuccess, Object info) {
        MSG msg = new MSG();
        msg.isSuccess = isSuccess;
        msg.code = code;
        msg.info = info;
        return msg;
    }

    /**
     * @param info 提示内容
     * @return
     * @time 2016年10月10日 下午11:37:39
     */
    public static MSG createErrorMSG(Object info) {
        return createMSG(0, false, info);
    }

    /**
     * @param info 提示内容
     * @return
     * @time 2016年10月10日 下午11:37:36
     */
    public static MSG createSuccessMSG(Object info) {
        return createMSG(0, true, info);
    }

    /**
     * @param code 状态码
     * @param info 提示内容
     * @return
     */
    public static MSG createErrorMSG(int code, Object info) {
        return createMSG(code, false, info);
    }

    /**
     * @param code 状态码
     * @param info 提示内容
     * @return
     */
    public static MSG createSuccessMSG(int code, Object info) {
        return createMSG(code, true, info);
    }

    /**
     * @return
     */
    public static MSG createErrorMSG() {
        return createMSG(0, false, "执行失败");
    }

    /**
     * @return
     */
    public static MSG createSuccessMSG() {
        return createMSG(0, true, "执行成功");
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MSG [isSuccess=" + isSuccess + ", info=" + info + ", code=" + code + "]";
    }

}
