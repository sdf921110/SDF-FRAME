package com.sdf.common.constant;

/**
 * 系统参数配置类
 * 
 * @author SDF
 * @date 2016年11月9日
 */
public class SysConstant {

	/**
	 * 用户登录的对象
	 */
	public static final String SESSION_USER = "webUser";
	public static final String PASSWORD = "123456";

	/**
	 * UTF-8编码
	 */
	public static final String UTF8 = "UTF-8";
	public static final String ENCODING_PREFIX = "encoding";
	public static final String NOCACHE_PREFIX = "no-cache";
	public static final boolean NOCACHE_DEFAULT = true;

	/************************** 系统代码	start ********************************/

	// session超时
	public static final int CODE_SYS_OUT_TIME = 100000;
	// 登录密码错误
	public static final int CODE_ERROR_LOGIN_PASSWORD = 100001;


	/************************** 系统代码	end **********************************/

	/**
	 * APP图片上传
	 */
	public static final String UPLOAD_IMAGE_URL = "http://comeon.group/upload/uploadImg";// APP上传图片地址

	public static final String UPLOAD_IMAGE_SERVER = "http://comeon.group";// 图片服务器地址

}
