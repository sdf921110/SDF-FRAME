package com.sdf.codeGeneration.code.main;

import com.sdf.codeGeneration.code.tool.data.DataService;
import com.sdf.codeGeneration.code.tool.data.DataServiceImpl;
import com.sdf.codeGeneration.code.tool.freeMaker.FreeMakerUtil;
import com.sdf.codeGeneration.code.tool.utils.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * CodeMeachine
 * 
 * @author: SDF
 * @dateTime: 2017-3-11 下午10:02:43
 * @version: 1.0.0
 */
public class CodeMeachine {

	// 默认生成文件的路径
	// private static String rootPath = "E:\\";

	// 本项目根目录
	// private static String rootPath =
	// (RunEntrance.class.getResource("/").getFile().toString()).replace("bin/",
	// "");

	private static FreeMakerUtil freeMakerUtil = new FreeMakerUtil();
	private static DataService dataService = new DataServiceImpl();

	/**
	 * 根据entity模板生成文件 （操作数据库，包含列数据）
	 * 
	 * @param templateName
	 *            模板文件 eg:entityModel.ftl
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 */
	public static Map<String, Object> generateEntityFile(String templateName, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot) {

		// DemoCompany
		String className = StringUtils.convertClassField(tableName);
		// demoCompany
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		// 不含数据字段信息
		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		// 含数据字段信息
		Map<String, Object> dbData = dataService.getDbTemplateData(tableName);

		templateData.putAll(dbData);

		String dir = projectRoot + "src/code/" + packageNamePrefix.replaceAll("\\.", "/") + "/model/" + smallClassName
				+ "/";

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + className + ".java");
		// freeMakerUtil.print(templateName, templateData);

		return dbData;
	}

	/**
	 * 根据controller, service, dao模板生成文件 （无需操作数据库，列数据根据已生成实体类体现）
	 * 
	 * @param templateName
	 *            模板文件 eg:controllerModel.ftl
	 * @param type
	 *            模板文件 eg:controller
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 * @param dbMap
	 */
	public static void generateCSDFile(String templateName, String type, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot, Map<String, Object> dbMap) {

		String bigType = StringUtils.convertBigField(type);

		String className = StringUtils.convertClassField(tableName);
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		if (null != dbMap) {
			templateData.putAll(dbMap);
		}

		String dir = projectRoot + "src/code/" + packageNamePrefix.replaceAll("\\.", "/") + "/" + type + "/"
				+ smallClassName + "/";

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + className + bigType + ".java");
	}

	/**
	 * 生成creator, extractor文件
	 * 
	 * @param templateName
	 *            模板文件 eg:creatorModel.ftl
	 * @param type
	 *            模板文件 eg:creator
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 * @param dbMap
	 */
	public static void generateDbFile(String templateName, String type, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot, Map<String, Object> dbMap) {

		String bigType = StringUtils.convertBigField(type);

		String className = StringUtils.convertClassField(tableName);
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		if (null != dbMap) {
			templateData.putAll(dbMap);
		}

		String dir = projectRoot + "src/code/" + packageNamePrefix.replaceAll("\\.", "/") + "/db/" + type + "/"
				+ smallClassName + "/";

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + className + bigType + ".java");
	}

	/**
	 * 生成sqlMapping
	 * 
	 * @param templateName
	 *            模板文件 eg:creatorModel.ftl
	 * @param type
	 *            模板文件 eg:creator
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 */
	public static void generateSqlFile(String templateName, String type, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot, Map<String, Object> dbMap) {

		String className = StringUtils.convertClassField(tableName);
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		if (null != dbMap) {
			templateData.putAll(dbMap);
		}

		String dir = projectRoot + "src/config/sql/" + smallClassName + "/";

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + tableName + ".xml");
	}

	/**
	 * 生成jsp
	 * 
	 * @param templateName
	 *            模板文件 eg:creatorModel.ftl
	 * @param type
	 *            模板文件 eg:creator
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 */
	public static void generateJspFile(String templateName, String type, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot, Map<String, Object> dbMap) {

		String className = StringUtils.convertClassField(tableName);
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		if (null != dbMap) {
			templateData.putAll(dbMap);
		}

		String dir = projectRoot + "WebContent/WEB-INF/page/back/" + smallClassName + "/";
		if ("List".equals(type)) {
			dir = projectRoot + "WebContent/static/back/jsp/" + smallClassName + "/";
		}

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + smallClassName + type + ".jsp");
	}

	/**
	 * 生成js
	 * 
	 * @param templateName
	 *            模板文件 eg:creatorModel.ftl
	 * @param type
	 *            模板文件 eg:creator
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 */
	public static void generateJsFile(String templateName, String type, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot, Map<String, Object> dbMap) {

		String className = StringUtils.convertClassField(tableName);
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		if (null != dbMap) {
			templateData.putAll(dbMap);
		}

		String dir = projectRoot + "WebContent/static/back/js/" + smallClassName + "/";
		if ("Main".equals(type)) {
			dir = projectRoot + "WebContent/static/back/js/common/";
			smallClassName = className;
			type = "";
		}

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + smallClassName + type + ".js");
	}

	// **********************************************接口*************************************************************//

	/**
	 * 根据entity模板生成文件 （操作数据库，包含列数据）
	 * 
	 * @param templateName
	 *            模板文件 eg:entityModel.ftl
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 */
	public static Map<String, Object> generateApiEntityFile(String templateName, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot) {

		// DemoCompany
		String className = StringUtils.convertClassField(tableName);
		// demoCompany
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		// 不含数据字段信息
		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		// 含数据字段信息
		Map<String, Object> dbData = dataService.getDbTemplateData(tableName);

		templateData.putAll(dbData);

		String dir = projectRoot + "src/" + packageNamePrefix.replaceAll("\\.", "/") + "/pojo/";

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + className + ".java");
		// freeMakerUtil.print(templateName, templateData);

		return dbData;
	}

	/**
	 * 根据controller, service, dao模板生成文件 （无需操作数据库，列数据根据已生成实体类体现）
	 * 
	 * @param templateName
	 *            模板文件 eg:controllerModel.ftl
	 * @param type
	 *            模板文件 eg:controller
	 * @param tableNameStr
	 *            表名 eg:我是表名
	 * @param tableName
	 *            表名 eg:DEMO_COMPANY
	 * @param packageNamePrefix
	 *            包名前缀
	 * @param projectRoot
	 *            项目根路径
	 * @param dbMap
	 */
	public static void generateApiCSDFile(String templateName, String type, String tableNameStr, String tableName,
			String packageNamePrefix, String projectRoot, Map<String, Object> dbMap) {

		String bigType = StringUtils.convertBigField(type);

		String className = StringUtils.convertClassField(tableName);
		String smallClassName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		Map<String, Object> templateData = dataService.getTemplateDataWithOutDb(tableNameStr, tableName,
				packageNamePrefix, className, smallClassName);

		if (null != dbMap) {
			templateData.putAll(dbMap);
		}

		String dir = projectRoot + "src/" + packageNamePrefix.replaceAll("\\.", "/") + "/" + type + "/";

		File targetDir = new File(dir);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		freeMakerUtil.generateFile(templateName, templateData, dir + className + bigType + ".java");
	}
	
	// **********************************************接口*************************************************************//
}
