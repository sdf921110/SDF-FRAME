package ${packageNamePrefix}.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.base.BaseService;
import com.shop.dao.${className}Dao;
import com.shop.utils.Result;

 /**
 * ${tableNameStr}(${tableName})
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
 @Service
 public class ${className}Service extends BaseService{
 
 		@Autowired
		private ${className}Dao ${smallClassName}Dao;
 		
		// 新增${tableNameStr}
		public Result add(Result result, <#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>String ${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>) {
			boolean flag = ${smallClassName}Dao.add(<#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>);
	
			if (flag) {
				result.putMsg("添加成功", flag);
			} else {
				result.putMsg("添加失败", flag);
			}
	
			return result;
		}
	
		// 查询${tableNameStr}列表
		public Result queryList(Result result, String userId) {
			result.putData("data", ${smallClassName}Dao.queryList(userId));
			result.putSuccessMsg();
			return result;
		}
			
		// 分页查询${tableNameStr}列表
		public Result queryPage(Result result, String userId, int page, int pageCount) {

			int counts = ${smallClassName}Dao.queryCount(userId);
			int totalPage = counts / pageCount;
			if (counts % pageCount > 0) {
				totalPage += 1;
			}

			Map<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("currPage", page);
			pageMap.put("pageCount", pageCount);
			pageMap.put("totalPage", totalPage);
			result.putData("page", pageMap);

			result.putData("data", ${smallClassName}Dao.queryPage(userId, page, pageCount));
			return result;
		}
	
		// 查询${tableNameStr}详情
		public Result queryDetail(Result result, String id) {
			result.putData("data", ${smallClassName}Dao.queryDetail(id));
			result.putSuccessMsg();
			return result;
		}	
		
		// 编辑${tableNameStr}
		public Result update(Result result, <#list properties as pro><#if pro.bigProName == "UserId"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>String ${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>) {
			boolean flag = ${smallClassName}Dao.update(<#list properties as pro><#if pro.bigProName == "UserId"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>);
	
			if (flag) {
				result.putMsg("修改成功", flag);
			} else {
				result.putMsg("修改失败", flag);
			}
	
			return result;
		}
	
		// 删除${tableNameStr}
		public Result delete(Result result, String id) {
			boolean flag = ${smallClassName}Dao.delete(0, id);
	
			if (flag) {
				result.putMsg("删除成功", flag);
			} else {
				result.putMsg("删除失败", flag);
			}
	
			return result;
		}
 
}