package com.sdf.common.constant;

/**
 * 消息模板
 * 
 * @author SDF
 * @date 2017年6月13日
 */
public class UserMessageConstant {

	/**
	 * 提现未通过模板
	 */
	public final static String DRAW_MONEY_NO = "您于 [这里填申请提现时间] 申请提现 [这里填提现金额] 由于 [这里填审核未通过原因] 后台未通过，如有疑问请联系客服。";
	/**
	 * 提现通过模板
	 */
	public final static String DRAW_MONEY_YES = "您于 [这里填申请提现时间] 申请提现 [这里填提现金额] 后台审核通过，请注意查收。如48小时内还未到账，请联系客服。";
	/**
	 * 认证未通过模板
	 */
	public final static String CHECK_USER_NO = "您于 [这里填提交实名认证的时间] 提交的实名认证，由于 [这里填审核未通过原因] 原因，您的资料未能通过后台审核，建议您提交正确格式的资料，如有疑问请联系客服。";
	/**
	 * 认证通过模板
	 */
	public final static String CHECK_USER_YES = "您于 [这里填提交实名认证的时间] 提交的实名认证，后台已审核通过，赶紧开启您的咖忙之旅吧！";
}
