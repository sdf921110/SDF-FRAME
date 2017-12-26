package com.sdf.codeGeneration.code.tool.db;

import com.sdf.codeGeneration.code.tool.model.FieldBean;

import java.util.List;


public interface DbService {
	
	/**根据表名获取所有的列信息
	 * @param tableName
	 * @return
	 */
	public List<FieldBean> getAllColums(String tableName);
	
	/**根据表名获取所有的列信息
	 * @param tableName
	 * @return
	 */
	public List<FieldBean> getTableInfo(String tableName);
	
}
