package com.sdf.common.config;

/**
 * 系统参数配置类
 * 
 * @author SDF
 * @date 2016年11月9日
 */
public class SysConfig {

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

	/**
	 * openIM
	 */
	public static final String IM_SYSTEM_ACCOUNT = "admin001"; // 系统通知账号
	public static final String IM_SERVICE_ACCOUNT = "admin002"; // 客服账号
	public static final String IM_PROJECT_ACCOUNT = "admin003"; // 创客账号

	public static final String IM_SYSTEM_ACCOUNT_NAME = "系统通知"; // 系统通知昵称
	public static final String IM_SERVICE_ACCOUNT_NAME = "客服"; // 客服账号昵称
	public static final String IM_PROJECT_ACCOUNT_NAME = "创客"; // 创客账号昵称

	public static final String IM_ACCOUNT_PASSWORD = "IM_ACCOUNT_PASSWORD";// 共用密码

	/**
	 * APP图片上传
	 */
	public static final String UPLOAD_IMAGE_URL = "http://comeon.group/upload/uploadImg";// APP上传图片地址

	//public static final String UPLOAD_IMAGE_SERVER = "http://comeon.group/download.img?path=";// 图片服务器地址
	
	public static final String UPLOAD_IMAGE_SERVER = "http://comeon.group";// 图片服务器地址

}
