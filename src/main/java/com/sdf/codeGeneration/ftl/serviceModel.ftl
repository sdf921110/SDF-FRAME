package ${packageNamePrefix}.service.${smallClassName};

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdf.common.aop.annotation.Log;
import com.sdf.common.aop.annotation.Log.OpType;
import com.sdf.common.page.PageResults;
import com.sdf.common.util.MSG;

import ${packageNamePrefix}.service.BaseService;
import ${packageNamePrefix}.export.${smallClassName}.${className}Export;
import ${packageNamePrefix}.dao.${smallClassName}.${className}Dao;
import ${packageNamePrefix}.model.${smallClassName}.${className};

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

 /**
 * ${tableNameStr}(${tableName})对应service操作:实现简单的增删改查,分页等基本操作
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
 @Service
 public class ${className}Service extends BaseService{
 
 		@Autowired
		private ${className}Dao ${smallClassName}Dao;
 		
		/**
		 * 查询${tableNameStr}
		 * 
		 * @param id
		 * @return	${className}
		 * @time ${.now}
		 */
		public ${className} queryById(Integer id) {
			return ${smallClassName}Dao.queryById(id);
		}
		
		/**
		 * 查询${tableNameStr}
		 * 
		 * @param name
		 * @return	${className}
		 * @time ${.now}
		 */
		public ${className} queryByName(String name) {
			return ${smallClassName}Dao.queryByName(name);
		}
		
		/**
		 * 查询${tableNameStr}
		 * 
		 * @param id		 
		 * @return	Map<String,Object>
		 * @time ${.now}
		 */
		public Map<String,Object> querMapById(Integer id) {
			return ${smallClassName}Dao.querMapById(id);
		}
	
		/**
		 * 查询所有${tableNameStr}
		 * 
		 * @param jsonObject	 
		 * @return	List<Map<String, Object>>
		 * @time ${.now}
		 */
		public List<Map<String, Object>> queryList(JSONObject jsonObject) {
			return ${smallClassName}Dao.queryList(jsonObject);
		}
	
		/**
		 * 分页查询${tableNameStr}列表
		 * 
		 * @return	PageResults
		 * @time ${.now}
		 */
		public PageResults queryPage(JSONObject jsonObject, int pageSize, int pageStart) {
			return ${smallClassName}Dao.queryPage(jsonObject,pageSize,pageStart);
		}
		
		/**
		 * 导出${tableNameStr}列表
		 * 
		 * @return	PageResults
		 * @time ${.now}
		 */
		 @Log(type = OpType.导出Excel, tableName = "${tableName}", tableNameStr = "${tableNameStr}")
		public void exportToExcel(HttpServletResponse response, JSONObject jsonObject) {
			try {
				List<Map<String, Object>> list = queryList(jsonObject);
				${className}Export.createExcel(response, list);
			} catch (RuntimeException e) {
				logger.error("导出${tableNameStr}列表到Excel表格失败");
				e.printStackTrace();
			}
		}
		
		/**
		 * 检测name是否已存在
		 * 
		 * @param name	当前操作记录name
		 * @param id	当前操作记录ID
		 * @return	true:不存在	false:存在
		 * @time ${.now}
		 */
		public boolean checkName(String name, Integer id) {
			${className} model = queryByName(name);
			if (model == null) {
				return true;
			}
			if (model.getId().equals(id)) {
				return true;
			}
			return false;
		}
	
		/**
		 * 新增${tableNameStr}
		 * 
		 * @param ${smallClassName}
		 * @return		
		 * @time ${.now}
		 */
		@Log(type = OpType.新增, tableName = "${tableName}", tableNameStr = "${tableNameStr}")
		public MSG add(${className} ${smallClassName}) {
			try {
			    int addId =  ${smallClassName}Dao.add(${smallClassName});
				if ( addId > 0) {
					return MSG.createSuccessMSG();
				}
				return MSG.createErrorMSG("新增${tableNameStr}信息失败，请重试");
			} catch (RuntimeException e) {
				logger.error("新增${tableNameStr}信息失败");
				e.printStackTrace();
			}
			return MSG.createErrorMSG("新增${tableNameStr}信息失败");
	
		}
	
		/**
		 * 编辑${tableNameStr}
		 * 
		 * @param ${smallClassName}
		 * @return
		 * @time ${.now}
		 */
		 @Log(type = OpType.编辑, tableName = "${tableName}", tableNameStr = "${tableNameStr}")
		public MSG update(${className} ${smallClassName}) {
	
			try {
				if (${smallClassName}Dao.edit(${smallClassName})) {
					return MSG.createSuccessMSG();
				}
				return MSG.createErrorMSG("编辑${tableNameStr}信息失败，请重试");
			} catch (RuntimeException e) {
				logger.error("编辑${tableNameStr}信息失败");
				e.printStackTrace();
			}
			return MSG.createErrorMSG("编辑${tableNameStr}信息失败");
		}
		
		/**
		 * ${tableNameStr}状态修改
		 * 
		 * @param jsonArray
		 * @param status
		 * @return
		 * @time 2017年4月21日 上午11:04:37
		 */
		@Transactional
		@Log(type = OpType.状态修改, tableName = "${tableName}", tableNameStr = "${tableNameStr}")
		public MSG updateStatus(JSONArray jsonArray, Integer status) {
			try {
				for (Object id : jsonArray) {
					${smallClassName}Dao.updateStatus(id, status);
				}
				return MSG.createSuccessMSG();
			} catch (RuntimeException e) {
				logger.error("修改${tableNameStr}状态失败");
				e.printStackTrace();
			}
			return MSG.createErrorMSG(1, "修改${tableNameStr}状态失败");
		}
	
 
}