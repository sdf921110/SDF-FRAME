package com.sdf.codeGeneration.code.tool.data;

import java.util.Map;

/**
 * DataService
 * 
 * @author: SDF
 * @dateTime: 2017-3-11 下午9:55:09
 * @version: 1.0.0
 */
public interface DataService {

	/**
	 * 根据表名获取其他模板要用的Map型数据 (无需操作数据库)
	 * 
	 * @return
	 */
	public Map<String, Object> getTemplateDataWithOutDb(String tableNameStr, String tableName,
                                                        String packageNamePrefix, String className, String smallClassName);

	/**
	 * 根据表名获取entity模板要用的Map型数据
	 * @param tableName
	 * 
	 * @return
	 */
	public Map<String, Object> getDbTemplateData(String tableName);

}
