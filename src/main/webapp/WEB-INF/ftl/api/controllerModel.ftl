package ${packageNamePrefix}.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.anno.UserToken;
import com.shop.enu.TokenType;
import com.shop.utils.AuthUtil;
import com.shop.utils.MSG;
import com.shop.utils.Result;
import com.shop.utils.URLUtil;

import ${packageNamePrefix}.base.BaseController;
import ${packageNamePrefix}.service.${className}Service;

 /**
 * ${tableNameStr}(${tableName})
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
@Scope("prototype")
@Controller
@RequestMapping("/api/${smallClassName}")
 public class ${className}Controller extends BaseController{
	
		@Autowired
		private ${className}Service ${smallClassName}Service;
	
		/**
		 * 查询${tableNameStr}列表
		 * 
		 * @param request
		 * @param userId
		 * @return
		 * @throws Exception
		 * @time ${.now}
		 */
		@UserToken(TokenType.NoValidate)
		@RequestMapping(value = "/queryList.htm")
		@ResponseBody
		public Map<String, Object> queryList(HttpServletRequest request, String userId) throws Exception {
	
			// 系统参数和签名校验.
			MSG msg = AuthUtil.verifySign(URLUtil.extractParam(request), false);
			Result result = new Result();
			if (!msg.isSuccess()) {
				Map<String, Object> map = result.getResult();
				map.put("msg", msg);
				return result.getResult();
			}
	
			// 业务参数校验.
			msg = checkParams(new String[] { "userId" }, request);
			if (!msg.isSuccess()) {
				result.putMsg(msg.getInfo(), msg.isSuccess());
				return result.getResult();
			}
	
			result = ${smallClassName}Service.queryList(result, userId);
	
			return result.getResult();
		}
		
		/**
		 * 分页查询${tableNameStr}列表
		 * 
		 * @param request
		 * @param curPage
		 * @param pageSize
		 * @return
		 * @throws Exception
		 * @time ${.now}
		 */
		@UserToken(TokenType.NoValidate)
		@RequestMapping(value = "/queryPage.htm")
		@ResponseBody
		public Map<String, Object> queryPage(HttpServletRequest request,
				@RequestParam(value = "page", required = false) String page,
				@RequestParam(value = "pageCount", required = false) String pageCount,
				@RequestParam(value = "userId", required = false) String userId) throws IOException {
	
			MSG msg = AuthUtil.verifySign(URLUtil.extractParam(request), true);
			Result result = new Result();
			if (!msg.isSuccess()) {
				Map<String, Object> map = result.getResult();
				map.put("msg", msg);
				return result.getResult();
			}
			result = ${smallClassName}Service.queryPage(result, userId, Integer.parseInt(page),
					Integer.parseInt(pageCount));
	
			return result.getResult();
		}
		
		 /**
		 * 查询${tableNameStr}详情
		 * 
		 * @param request
		 * @param userId
		 * @return
		 * @throws Exception
		 * @time ${.now}
		 */
		@UserToken(TokenType.Validate)
		@RequestMapping(value = "/queryDetail.htm")
		@ResponseBody
		public Map<String, Object> queryDetail(HttpServletRequest request, String id) throws Exception {
	
			// 系统参数和签名校验.
			MSG msg = AuthUtil.verifySign(URLUtil.extractParam(request), false);
			Result result = new Result();
			if (!msg.isSuccess()) {
				Map<String, Object> map = result.getResult();
				map.put("msg", msg);
				return result.getResult();
			}
	
			// 业务参数校验.
			msg = checkParams(new String[] { "id" }, request);
			if (!msg.isSuccess()) {
				result.putMsg(msg.getInfo(), msg.isSuccess());
				return result.getResult();
			}
	
			result = ${smallClassName}Service.queryDetail(result, id);
	
			return result.getResult();
		}
		
		/**
		 * 新增${tableNameStr}
		 * 
		 * @return
		 * @throws IOException
		 * @time ${.now}
		 */
		@UserToken(TokenType.Validate)
		@RequestMapping(value = "/add.htm")
		@ResponseBody
		public Map<String, Object> add(HttpServletRequest request,
				<#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>String ${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>
				)
				throws Exception {
	
			// 系统参数和签名校验.
			MSG msg = AuthUtil.verifySign(URLUtil.extractParam(request), false);
			Result result = new Result();
			if (!msg.isSuccess()) {
				Map<String, Object> map = result.getResult();
				map.put("msg", msg);
				return result.getResult();
			}
	
			// 业务参数校验.
			msg = checkParams(new String[] { <#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>"${pro.proName}"<#if pro_has_next>,<#else></#if></#if></#list> }, request);
			if (!msg.isSuccess()) {
				result.putMsg(msg.getInfo(), msg.isSuccess());
				return result.getResult();
			}
	
			result = ${smallClassName}Service.add(result, <#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>);
	
			return result.getResult();
		}
		
		/**
		 * 编辑${tableNameStr}
		 * 
		 * @return
		 * @throws IOException
		 * @time ${.now}
		 */
		@UserToken(TokenType.Validate)
		@RequestMapping(value = "/update.htm")
		@ResponseBody
		public Map<String, Object> update(HttpServletRequest request,
				<#list properties as pro><#if pro.bigProName == "UserId"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>String ${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>
				)
				throws Exception {
	
			// 系统参数和签名校验.
			MSG msg = AuthUtil.verifySign(URLUtil.extractParam(request), false);
			Result result = new Result();
			if (!msg.isSuccess()) {
				Map<String, Object> map = result.getResult();
				map.put("msg", msg);
				return result.getResult();
			}
	
			// 业务参数校验.
			msg = checkParams(new String[] { <#list properties as pro><#if pro.bigProName == "UserId"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>"${pro.proName}"<#if pro_has_next>,<#else></#if></#if></#list> }, request);
			if (!msg.isSuccess()) {
				result.putMsg(msg.getInfo(), msg.isSuccess());
				return result.getResult();
			}
	
			result = ${smallClassName}Service.update(result, <#list properties as pro><#if pro.bigProName == "UserId"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>);
	
			return result.getResult();
		}
		
		
		/**
		 * 删除${tableNameStr}
		 * 
		 * @return
		 * @throws IOException
		 * @time ${.now}
		 */
		@UserToken(TokenType.Validate)
		@RequestMapping(value = "/delete.htm")
		@ResponseBody
		public Map<String, Object> delete(HttpServletRequest request, String id) throws Exception {
	
			// 系统参数和签名校验.
			MSG msg = AuthUtil.verifySign(URLUtil.extractParam(request), false);
			Result result = new Result();
			if (!msg.isSuccess()) {
				Map<String, Object> map = result.getResult();
				map.put("msg", msg);
				return result.getResult();
			}
	
			// 业务参数校验.
			msg = checkParams(new String[] { "id" }, request);
			if (!msg.isSuccess()) {
				result.putMsg(msg.getInfo(), msg.isSuccess());
				return result.getResult();
			}
	
			result = ${smallClassName}Service.delete(result, id);
	
			return result.getResult();
		}

	 
}