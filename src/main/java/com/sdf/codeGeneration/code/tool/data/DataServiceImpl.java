package com.sdf.codeGeneration.code.tool.data;

import com.sdf.codeGeneration.code.tool.db.DbService;
import com.sdf.codeGeneration.code.tool.db.DbServiceImpl;

import java.util.HashMap;
import java.util.Map;


/**
 * DataServiceImpl
 * 
 * @author: SDF
 * @dateTime: 2017-3-11 下午9:55:03
 * @version: 1.0.0
 */
public class DataServiceImpl implements DataService {

	/**
	 * 根据表名获取其他模板要用的Map型数据 (无需操作数据库,不含有列信息)
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> getTemplateDataWithOutDb(String tableNameStr, String tableName, String packageNamePrefix,
			String className,String smallClassName) {

		Map<String, Object> templateData = new HashMap<String, Object>();
		templateData.put("tableNameStr", tableNameStr);
		templateData.put("tableName", tableName);
		templateData.put("packageNamePrefix", packageNamePrefix);
		templateData.put("className", className);
		templateData.put("smallClassName", smallClassName);
		return templateData;

	}

	/**
	 * 根据表名获取模板要用的Map型数据 (带有列信息)
	 * 
	 * @return
	 */
	@Override
	public Map<String, Object> getDbTemplateData(String tableName) {
		DbService dbService = new DbServiceImpl();
		Map<String, Object> templateData = new HashMap<String, Object>();
//		templateData.put("tableNameStr", tableNameStr);
//		templateData.put("tableName", tableName);
//		templateData.put("packageNamePrefix", packageNamePrefix);
//		templateData.put("className", className);
//		templateData.put("smallClassName", smallClassName);
		templateData.put("properties", dbService.getTableInfo(tableName));
		return templateData;
	}

}
