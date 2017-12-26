package ${packageNamePrefix}.controller.${smallClassName};

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sdf.common.model.SessionUserPO;
import com.sdf.common.page.PageHelp;
import com.sdf.common.page.PageResults;
import com.sdf.common.util.Base64FileUtil;
import com.sdf.common.util.DateUtil;
import com.sdf.common.util.JSONTools;
import com.sdf.common.util.MSG;
import com.sdf.common.util.StringUtil;

import ${packageNamePrefix}.controller.BaseController;
import ${packageNamePrefix}.service.${smallClassName}.${className}Service;
import ${packageNamePrefix}.model.${smallClassName}.${className};

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

 /**
 * ${tableNameStr}(${tableName})对应controller操作:实现简单的增删改查,分页等基本操作
 * 
 * @author SDF
 * @version: 1.0.0
 * @time: ${.now}
 */
@Scope("prototype")
@Controller
@RequestMapping("/${smallClassName}/manage/")
 public class ${className}Controller extends BaseController{
	
		@Autowired
		private ${className}Service ${smallClassName}Service;
	
		/**
		 * 分页加载${tableNameStr}列表
		 * 
		 * @param aoData
		 * @param paraData
		 * @return
		 * @time ${.now}
		 */
		@RequestMapping("queryPage")
		@ResponseBody
		public PageResults queryPage(String aoData, String paraData) {
			JSONObject jsonObject = JSONTools.getJsonPara(paraData);
			PageHelp pageHelp = JSONTools.toPageHelp(aoData);
			PageResults pageResults = ${smallClassName}Service.queryPage(jsonObject, pageHelp.getiDisplayLength(),
					pageHelp.getiDisplayStart());
			pageResults.setsEcho(pageHelp.getsEcho());
			return pageResults;
		}
		
		/**
		 * 动态加载${tableNameStr}列表(下拉)
		 * 
		 * @param paraData
		 * @param session
		 * @return
		 * @time ${.now}
		 */
		@RequestMapping("queryList")
		@ResponseBody
		public List<Map<String, Object>> queryList(String paraData, HttpSession session) {
			JSONObject jsonObject = JSONTools.getJsonPara(paraData);
			return ${smallClassName}Service.queryList(jsonObject);
		}
		
		/**
		 * 导出列表到Excel表格
		 * 
		 * @param paraData
		 * @param response
		 * @return
		 * @throws Exception
		 * @time ${.now}
		 */
		@RequestMapping("exportToExcel")
		public ModelAndView exportToExcel(String paraData, HttpServletResponse response) throws Exception {
			JSONObject jsonObject = JSONTools.getJsonPara(paraData);
			${smallClassName}Service.exportToExcel(response, jsonObject);
			return null;
		}
	
		/**
		 * 新增${tableNameStr}
		 * 
		 * @return
		 * @time ${.now}
		 */
		@RequestMapping(value = "add")
		public String add() {
			return BACK_PREFIX + "/${smallClassName}/${smallClassName}Add";
		}
	
		/**
		 * ${tableNameStr}详情
		 * 
		 * @param model
		 * @param id
		 * @return
		 * @time ${.now}
		 */
		@RequestMapping(value = "detail/{id}")
		public String detail(Model model, @PathVariable("id") Integer id) {
			${className} modelMap = ${smallClassName}Service.queryById(id);
			model.addAttribute("modelMap", modelMap);
			return BACK_PREFIX + "/${smallClassName}/${smallClassName}Detail";
		}
	
		/**
		 * 编辑${tableNameStr}
		 * 
		 * @param model
		 * @param id
		 * @return
		 * @time ${.now}
		 */
		@RequestMapping(value = "edit/{id}")
		public String update(Model model, @PathVariable("id") Integer id) {
			${className} modelMap = ${smallClassName}Service.queryById(id);
			model.addAttribute("modelMap", modelMap);
			return BACK_PREFIX + "/${smallClassName}/${smallClassName}Edit";
		}
	
		/**
		 * 提交${tableNameStr}（新增，修改）
		 * 
		 * @param ${smallClassName}
		 * @param session
		 * @return
		 * @throws Exception
		 * @time ${.now}
		 */
		@RequestMapping("submit")
		@ResponseBody
		public MSG submit(${className} ${smallClassName}, HttpSession session, HttpServletRequest request) throws Exception {

		    // -------------------- base64图片上传 -------------------- //		
			String baseImg = ${smallClassName}.getBaseImg();
			String img = ${smallClassName}.getImg();
	
			if (StringUtil.isNotBlank(baseImg)) {
				// Base64转存文件返回url
				img = Base64FileUtil.decoderBase64FileToOtherUpload(baseImg, "${tableName}", "jpg", request);
				if (img == null) {
					return MSG.createErrorMSG("图片上传失败，请重试");
				}
				${smallClassName}.setImg(img);
			} else {
				${smallClassName}.setImg(img);
			}
			// -------------------- base64图片上传 -------------------- //		
	
			SessionUserPO sessionUserPO = this.getSesseion(session);
			
			if (${smallClassName}.getId() == null) {
				${smallClassName}.setCreateTime(DateUtil.currrentDate());
				${smallClassName}.setCreateUser(sessionUserPO.getName());
				${smallClassName}.setCreateUserId(sessionUserPO.getId());
				return ${smallClassName}Service.add(${smallClassName});
				
			} else {
				${smallClassName}.setUpdateUser(sessionUserPO.getName());
				${smallClassName}.setUpdateUserId(sessionUserPO.getId());
				return ${smallClassName}Service.update(${smallClassName});
			}
		}
			
		/**
		 * ${tableNameStr}状态修改
		 * 
		 * @param ids
		 * @param status
		 * @return
		 * @time ${.now}
		 */
		@RequestMapping("updateStatus")
		@ResponseBody
		public MSG updateStatus(String ids, Integer status) {
			JSONArray jsonArray = JSONArray.fromObject(ids);
			return ${smallClassName}Service.updateStatus(jsonArray, status);
		}
 
}