package ${packageNamePrefix}.dao.${smallClassName};

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sdf.common.page.PageResults;
import com.sdf.common.sql.SqlHelp;
import com.sdf.common.sql.JSONSqlMapping;
import ${packageNamePrefix}.dao.BaseDao;
import ${packageNamePrefix}.model.${smallClassName}.${className};
import ${packageNamePrefix}.db.extractor.${smallClassName}.${className}Extractor;
import ${packageNamePrefix}.db.creator.${smallClassName}.${className}Creator;

import net.sf.json.JSONObject;

 /**
 * ${tableNameStr}(${tableName})对应dao操作:实现简单的增删改查,分页等基本操作
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
 @Repository
 public class ${className}Dao extends BaseDao{
	
		@Resource(name = "${'${tableName}'?upper_case}_SELECT_SQL")
		private JSONSqlMapping ${smallClassName}SelectSql;
		
		@Resource(name = "${'${tableName}'?upper_case}_INSERT_SQL")
		private JSONSqlMapping ${smallClassName}InsertSql;
	
		// 新增${tableNameStr}的SQL
		private static final String INSERT_SQL = "insert into ${tableName} (<#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"||pro.bigProName == "UpdateUser"||pro.bigProName == "UpdateUserId"><#else><#if pro.proName == "desc">`${pro.proName}`<#else>${pro.proName}</#if><#if pro_has_next>,<#else></#if></#if></#list>) values (<#list properties as pro><#if pro.proName == "id"||pro.proName == "updateTime"||pro.proName == "updateUser"||pro.proName == "updateUserId"><#else>?<#if pro_has_next>,<#else></#if></#if></#list>)";
		// 修改${tableNameStr}的SQL
		private static final String UPDATE_SQL = "update ${tableName} set <#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "CreateTime"||pro.bigProName == "CreateUser"||pro.bigProName == "CreateUserId"||pro.bigProName == "UpdateTime"><#else><#if pro.proName == "desc">`${pro.proName}`=?<#else>${pro.proName}=?</#if><#if pro_has_next>,<#else></#if></#if></#list> where id = ?";
		// 根据ID查找${tableNameStr}的SQL
		private static final String QUERY_BY_ID_SQL = "select <#list properties as pro>t.${pro.proName}<#if pro_has_next>,<#else></#if></#list> from ${tableName} t where t.id=?";
		// 根据name查找${tableNameStr}的SQL
		private static final String QUERY_BY_NAME_SQL = "select <#list properties as pro>t.${pro.proName}<#if pro_has_next>,<#else></#if></#list> from ${tableName} t where t.`name`=?";
		// 删除${tableNameStr}的SQL
		private static final String DELETE_SQL = "delete from ${tableName} t where t.id = ?";
		// 修改${tableNameStr}状态的SQL
		private static final String UPDATE_STATUS_SQL = "update ${tableName} set `status` = ? where id = ?";
	
		/**
		 * 分页查询${tableNameStr}列表
		 * 
		 * @param jsonObject
		 * @param pageSize
		 * @param pageStart
		 * @return
		 * @time ${.now}
		 */
		public PageResults queryPage(JSONObject jsonObject, int pageSize, int pageStart) {
			return this.findPageByJSONSqlMapping(${smallClassName}SelectSql, jsonObject, pageSize, pageStart);
		}
	
		/**
		 * 根据ID得到${tableNameStr}对象
		 * 
		 * @param id
		 * @return
		 * @time ${.now}
		 */
		public ${className} queryById(Integer id) {
			return this.getJdbcTemplate().query(QUERY_BY_ID_SQL, new Object[] { id }, new ${className}Extractor());
		}
			
		/**
		 * 根据name得到${tableNameStr}对象
		 * 
		 * @param name
		 * @return
		 * @time ${.now}
		 */
		public ${className} queryByName(String name) {
			return this.getJdbcTemplate().query(QUERY_BY_NAME_SQL, new Object[] { name }, new ${className}Extractor());
		}
		
		/**
		 * 根据ID得到${tableNameStr}Map
		 * 
		 * @param id
		 * @return
		 * @time ${.now}
		 */		
		public Map<String, Object> querMapById(Integer id) {
			return queryForMap(QUERY_BY_ID_SQL, id);
		}
		
		/**
		 * 得到${tableNameStr}
		 * 
		 * @param jsonObject
		 * @return
		 * @time ${.now}
		 */		
		public List<Map<String, Object>> queryList(JSONObject jsonObject) {
			return this.findListByJSONSqlMapping(${smallClassName}SelectSql, jsonObject);
		}
		
		/**
		 * 新增${tableNameStr}
		 * 
		 * @param ${smallClassName}
		 * @return
		 * @time ${.now}
		 */
		public Integer add(final ${className} ${smallClassName}) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			this.getJdbcTemplate().update(new ${className}Creator(INSERT_SQL, ${smallClassName}),
				keyHolder);
			return keyHolder.getKey().intValue();
		}
		
		/**
		 * 新增${tableNameStr}
		 * 
		 * @param ${smallClassName}
		 * @return
		 * @time ${.now}
		 */
		public Integer addSql(final ${className} ${smallClassName}) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			this.getJdbcTemplate().update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con)
						throws SQLException {
					PreparedStatement ps = con.prepareStatement(new SqlHelp(
							${smallClassName}InsertSql).getSql(),
							PreparedStatement.RETURN_GENERATED_KEYS);
					int i = 1;
					<#list properties as pro>  	
					<#if pro.bigProName == "Id"||pro.bigProName == "UpdateTime"
						||pro.bigProName == "UpdateUser"||pro.bigProName == "UpdateUserId">
					<#else>
					ps.set${pro.intProType}(i++, ${smallClassName}.get${pro.bigProName}());
					</#if>
					</#list>
					return ps;
				}
			}, keyHolder);
			return keyHolder.getKey().intValue();
		}
		
		/**
		 * 编辑${tableNameStr}
		 * 
		 * @param ${smallClassName}
		 * @return
		 * @time ${.now}
		 */
		public boolean edit(${className} ${smallClassName}) {
			return this.getJdbcTemplate().update(UPDATE_SQL, <#list properties as pro><#if pro.bigProName == "Id"||pro.bigProName == "CreateTime"||pro.bigProName == "CreateUser"||pro.bigProName == "CreateUserId"||pro.bigProName == "UpdateTime"><#else>${smallClassName}.get${pro.bigProName}(),</#if></#list>
					${smallClassName}.getId()) > 0;
		}
				
		/**
		 * 删除${tableNameStr}
		 * 
		 * @param ${smallClassName}
		 * @return
		 * @time ${.now}
		 */
		public boolean delete(Integer id) {
			return this.getJdbcTemplate().update(DELETE_SQL, id) > 0;
		}
		
		/**
		 * ${tableNameStr}状态修改
		 * 
		 * @param id
		 * @param status
		 * @time ${.now}
		 */
		public boolean updateStatus(Object id, Integer status) {
			return this.getJdbcTemplate().update(UPDATE_STATUS_SQL, status, id) > 0;
		}
		
}