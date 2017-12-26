package ${packageNamePrefix}.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shop.base.BaseDao;

 /**
 * ${tableNameStr}(${tableName})
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
 @Repository
 public class ${className}Dao extends BaseDao{
 	
		// 查询订单数量
		public int queryCount(String userId) {
			String sql = " SELECT COUNT(*) FROM `${tableName}` t WHERE t.`userId` = ? ";
			return queryForInteger(sql, userId);
		}
		
		// 分页查询订单列表
		public List<Map<String, Object>> queryPage(String userId, int page, int pageCount) {
			int start = (page - 1) * pageCount;
			String sql = " SELECT <#list properties as pro>t.${pro.proName}<#if pro_has_next>,<#else></#if></#list> FROM `${tableName}` AS t WHERE t.`status` = 1 "
					+ "AND t.`userId` = ? ORDER BY t.sort limit ?,? ";
			return queryForList(sql, new Object[] { userId, start, pageCount });
		}
	
		// 查询${tableNameStr}列表
		public List<Map<String, Object>> queryList(String userId) {
			String sql = " SELECT <#list properties as pro>t.${pro.proName}<#if pro_has_next>,<#else></#if></#list> FROM ${tableName} AS t WHERE t.`status`=1 and t.userId = ?";
			return queryForList(sql, new Object[] { userId });
		}
	
		// 查询${tableNameStr}信息
		public Map<String, Object> queryDetail(String id) {
			String sql = " SELECT <#list properties as pro>t.${pro.proName}<#if pro_has_next>,<#else></#if></#list> FROM ${tableName} AS t WHERE t.id = ?";
			return queryForMap(sql, new Object[] { id });
		}
	
		// 添加${tableNameStr}
		public boolean add(<#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>String ${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>) {
			String sql = "insert into ${tableName} (<#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "UpdateUser"><#else><#if pro.proName == "desc">`${pro.proName}`<#else>${pro.proName}</#if><#if pro_has_next>,<#else></#if></#if></#list>) values (<#list properties as pro><#if pro.proName == "id"||pro.proName == "updateTime"||pro.proName == "updateUser"><#else>?<#if pro_has_next>,<#else></#if></#if></#list>)";
			return jdbcTemplate.update(sql, <#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>) > 0;
		}
	
		// 修改${tableNameStr}
		public boolean update(<#list properties as pro><#if pro.bigProName == "UserId"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>String ${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>) {
			String sql = " update ${tableName} set <#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "CreateTime"||pro.bigProName == "CreateUser"||pro.bigProName == "UpdateTime"><#else><#if pro.proName == "desc">`${pro.proName}`=?<#else>${pro.proName}=?</#if><#if pro_has_next>,<#else></#if></#if></#list> where id = ? ";
			return jdbcTemplate.update(sql, <#list properties as pro><#if pro.bigProName == "UserId"||pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "CreateTime"||pro.bigProName == "Status"><#else>${pro.proName}<#if pro_has_next>,<#else></#if></#if></#list>,
					id) > 0;
		}
	
		// 删除${tableNameStr}
		public boolean delete(int status, String id) {
			String sql = " update ${tableName} set `status` = ? where id=? ";
			return jdbcTemplate.update(sql, status, id) > 0;
		}
			
}