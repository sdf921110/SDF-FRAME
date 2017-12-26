package com.sdf.codeGeneration.code.main;

import java.util.Map;

public class RunEntrance {

	public static void main(String[] args) {
		manager();
//		 api();
	}

	public static void manager() {
		// mysql
		generateManageFile("用户搜索记录", "user_search_record", "com.sdf.main",
				"D:/eclipse-workspace/eclipse-j2ee/SeaShop/");
//		 generateManageFile("创客参与用户", "project_like", "com.sdf.kamang",
//		 "E:/eclipse-workspace/eclipse-j2ee/kamang/");

		// oracle
		// generateManageFile("公司信息", "DEMO_COMPANY", "com.sdf.xcc",
		// "E:/Eclipse/eclipse-jee-mars-1-win32-x86_64/workspace/xcc/src/code/");
	}

	private static void api() {
		// mysql
		generateApiFile("订单", "order", "com.shop",
				"E:/eclipse-workspace/eclipse-j2ee/SeaShopApi/");
	}

	/**
	 * 生成后台代码
	 * 
	 * @param tableNameStr
	 *            表名（中文）
	 * @param tableName
	 *            表名（英文）
	 * @param packageNamePrefix
	 *            包名
	 * @param projectRoot
	 *            项目根路径
	 */
	private static void generateManageFile(String tableNameStr, String tableName, String packageNamePrefix,
			String projectRoot) {

		// 生成entity
		Map<String, Object> dbMap = CodeMeachine.generateEntityFile("entityModel.ftl", tableNameStr, tableName,
				packageNamePrefix, projectRoot);

		// 生成controller
		CodeMeachine.generateCSDFile("controllerModel.ftl", "controller", tableNameStr, tableName, packageNamePrefix,
				projectRoot, null);
		// 生成service
		CodeMeachine.generateCSDFile("serviceModel.ftl", "service", tableNameStr, tableName, packageNamePrefix,
				projectRoot, null);
		// 生成dao
		CodeMeachine.generateCSDFile("daoModel.ftl", "dao", tableNameStr, tableName, packageNamePrefix, projectRoot,
				dbMap);

		// 生成creator
		CodeMeachine.generateDbFile("creatorModel.ftl", "creator", tableNameStr, tableName, packageNamePrefix,
				projectRoot, dbMap);
		// 生成extractor
		CodeMeachine.generateDbFile("extractorModel.ftl", "extractor", tableNameStr, tableName, packageNamePrefix,
				projectRoot, null);

		// 生成export
		CodeMeachine.generateCSDFile("exportModel.ftl", "export", tableNameStr, tableName, packageNamePrefix,
				projectRoot, dbMap);

		// 生成sqlMapping
		CodeMeachine.generateSqlFile("jsonSqlMappingModel.ftl", "sql", tableNameStr, tableName, packageNamePrefix,
				projectRoot, dbMap);

		// 生成jspAdd
		CodeMeachine.generateJspFile("jspAddModel.ftl", "Add", tableNameStr, tableName, null, projectRoot, dbMap);
		// 生成jspEdit
		CodeMeachine.generateJspFile("jspEditModel.ftl", "Edit", tableNameStr, tableName, null, projectRoot, dbMap);
		// 生成jspDetail
		CodeMeachine.generateJspFile("jspDetailModel.ftl", "Detail", tableNameStr, tableName, null, projectRoot, dbMap);
		// 生成jspList
		CodeMeachine.generateJspFile("jspListModel.ftl", "List", tableNameStr, tableName, null, projectRoot, dbMap);

		// 生成js
		CodeMeachine.generateJsFile("jsMainModel.ftl", "Main", tableNameStr, tableName, null, projectRoot, dbMap);
		// 生成jsList
		CodeMeachine.generateJsFile("jsListModel.ftl", "List", tableNameStr, tableName, null, projectRoot, dbMap);
		// 生成jsSubmit
		CodeMeachine.generateJsFile("jsSubmitModel.ftl", "Submit", tableNameStr, tableName, null, projectRoot, null);

	}

	/**
	 * 生成接口代码
	 * 
	 * @param tableNameStr
	 *            表名（中文）
	 * @param tableName
	 *            表名（英文）
	 * @param packageNamePrefix
	 *            包名
	 * @param projectRoot
	 *            项目根路径
	 */
	private static void generateApiFile(String tableNameStr, String tableName, String packageNamePrefix,
			String projectRoot) {

		// 生成entity
		Map<String, Object> dbMap = CodeMeachine.generateApiEntityFile("api/entityModel.ftl", tableNameStr, tableName,
				packageNamePrefix, projectRoot);

		// 生成controller
		CodeMeachine.generateApiCSDFile("api/controllerModel.ftl", "controller", tableNameStr, tableName,
				packageNamePrefix, projectRoot, dbMap);
		// 生成service
		CodeMeachine.generateApiCSDFile("api/serviceModel.ftl", "service", tableNameStr, tableName, packageNamePrefix,
				projectRoot, dbMap);
		// 生成dao
		CodeMeachine.generateApiCSDFile("api/daoModel.ftl", "dao", tableNameStr, tableName, packageNamePrefix,
				projectRoot, dbMap);

	}

}
