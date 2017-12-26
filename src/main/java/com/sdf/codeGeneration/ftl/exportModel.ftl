package ${packageNamePrefix}.export.${smallClassName};

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.sdf.common.export.BaseExportExcel;
import com.sdf.common.util.DateUtil;

import jxl.write.Label;

/**
 * ${tableNameStr}导出
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
public class ${className}Export extends BaseExportExcel {

	public static void createExcel(HttpServletResponse response, List<Map<String, Object>> list) throws Exception {
		BaseExportExcel be = new ${className}Export();
		be.toExcel(response, list);
	}

	@Override
	protected void writeTile() throws Exception {
		int column = 0;
		<#list properties as pro>
		<#if pro.bigProName == "Id"||pro.bigProName == "CreateUserId"||pro.bigProName == "UpdateUserId">
		<#else>
		wsheet.setColumnView(column++, 20);
		</#if>
		</#list>
		wsheet.mergeCells(0, 0, --column, 0);
		Label label = new Label(0, 0, "${tableNameStr}列表(导出时间：" + DateUtil.getCurDateTime() + ")", wffBold);
		wsheet.addCell(label);
	}

	@Override
	protected void writeHeader() throws Exception {
		int column = 0;
		wsheet.setRowView(1, 400);
		<#list properties as pro>
		<#if pro.bigProName == "Id"||pro.bigProName == "CreateUserId"||pro.bigProName == "UpdateUserId">
		<#else>
		wsheet.addCell(new Label(column++, 1, "${pro.fieldDesc}", wctB));
		</#if>
		</#list>
	}

	@Override
	protected void writeBody(List<Map<String, Object>> list) throws Exception {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int column = 0;
				Map<String, Object> map = (Map<String, Object>) list.get(i);
				wsheet.setRowView(i + 2, 400);
				<#list properties as pro>
				<#if pro.bigProName == "Id"||pro.bigProName == "CreateUserId"||pro.bigProName == "UpdateUserId">
				<#else>
				wsheet.addCell(new Label(column++, i + 2, toString(map.get("${pro.proName}")), wcsB));
				</#if>
				</#list>
			}
		}

	}

	@Override
	protected String fileName() {
		String filename = "${tableNameStr}列表.xls";
		try {
			filename = new String(filename.getBytes("gb2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}

}
