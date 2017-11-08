package com.sdf.core.controller.system;

import com.sdf.common.constant.SysConstant;
import com.sdf.common.pojo.MSG;
import com.sdf.common.utils.*;
import com.sdf.core.controller.BaseController;
import com.sdf.core.pojo.system.SysFileUrl;
import com.sdf.core.service.system.ISysFileUrlService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * 文件上传
 *
 * @author SDF
 * @date 2016年10月15日
 */
@Controller
@RequestMapping("/upload/")
public class UploadController extends BaseController {

    @Autowired
    private ISysFileUrlService sysFileUrlService;

    /**
     * WebUploader图片上传
     *
     * @param file
     * @param request
     * @return
     * @time 2016年10月15日 上午11:59:37
     */
    @RequestMapping(value = "addWebUploaderImg")
    @ResponseBody
    public MSG addWebUploaderImg(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                 HttpSession session) {
        MSG msg = new MSG();
        String pathType = StringUtil.decodeMethod(request.getParameter("pathType"));
        String fileType = StringUtil.decodeMethod(request.getParameter("fileType"));
        Integer tableId = StringUtil.decodeInteger(request.getParameter("tableId"));
        String tableName = StringUtil.decodeMethod(request.getParameter("tableName"));
        Integer isThumb = StringUtil.decodeInteger(request.getParameter("isThumb"));
        Integer isImg = StringUtil.decodeInteger(request.getParameter("isImg"));
        try {
            String fileUrl = copyFile(file, "images/" + pathType, request);
            logger.info("------------------->WebUploader上传图片: " + file.getOriginalFilename() + "  目标地址：" + fileUrl);

            SysFileUrl fileUrls = new SysFileUrl();
            fileUrls.setFileType(fileType);
            fileUrls.setFileName(file.getOriginalFilename());
            fileUrls.setTableId(tableId);
            fileUrls.setTableName(tableName);
            fileUrls.setIsThumb(isThumb);
            fileUrls.setIsImg(isImg);
            fileUrls.setFileUrl(fileUrl);
            fileUrls.setCreate_user(this.getSesseionUserName(session));
            // 保存图片
            msg.setCode(sysFileUrlService.insert(fileUrls));

        } catch (Exception e) {
            e.printStackTrace();
            msg.setSuccess(false);
            msg.setInfo("上传图片保存失败");
        }
        return msg;
    }

    /**
     * ajax图片上传（单张预览，app上传）
     *
     * @param uploadFile
     * @param request
     * @param type       类型
     * @param scale      比例 默认：0.25 为空表示不缩放
     * @return
     * @time 2017年5月19日 下午4:16:18
     */
    @RequestMapping(value = "uploadImg")
    @ResponseBody
    public HashMap<String, Object> uploadImg(@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile, // input输入框 name="uploadFile"
                                             HttpServletRequest request, String type, String scale) {
        HashMap<String, Object> result = new HashMap<>();

        try {
            String fileName = uploadFile.getOriginalFilename();
//			String fileUrl = copyFileToOther(uploadFile, suffix, type, request);
            String fileUrl = copyFile(uploadFile, type, request);
            logger.info("------------------->ajax图片上传: " + fileName + "  目标地址：" + fileUrl);

            String zoomUrl = "";
            if (null != scale && !"".equals(scale)) {
//				zoomUrl = ImgThumbnailatorUtil.createZoomImg(suffix, fileUrl, Float.valueOf(scale));
                logger.info("------------------->ajax图片上传: " + fileName + "  缩略图地址：" + zoomUrl);
            }

            result.put("imgServer", SysConstant.UPLOAD_IMAGE_SERVER);
            result.put("zoomUrl", zoomUrl);
            result.put("fileUrl", fileUrl);
            result.put("fileName", fileName);
            result.put("info", "图片保存成功");
            result.put("success", true);
        } catch (Exception e) {
            result.put("info", "图片保存失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * base64图片上传（单张预览，app上传）
     *
     * @param baseImg
     * @param request
     * @param dir
     * @return
     * @time 2017年5月7日 上午12:12:10
     */
    @RequestMapping(value = "addBase64Img")
    @ResponseBody
    public HashMap<String, Object> addBase64Img(String baseImg, HttpServletRequest request, String dir,
                                                boolean isThumb) {
        HashMap<String, Object> result = new HashMap<>();
        try {

            String fileUrl = Base64FileUtil.decoderBase64FileToUpload(baseImg, dir, "jpg", request);

            logger.info("------------------->base64上传图片--目标地址：" + fileUrl);

            // 裁剪图片
            if (isThumb) {
                ImgThumbnailatorUtil.createThumbImg(fileUrl);
            }

            result.put("fileUrl", fileUrl);
            // result.put("fileName", fileName);
            result.put("success", true);
        } catch (Exception e) {
            result.put("info", "上传图片保存失败");
            result.put("success", false);
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 复制文件
     *
     * @param file
     * @param pathType
     * @param request
     * @return
     * @time 2016年10月15日 下午5:34:45
     */
    private String copyFile(MultipartFile file, String pathType, HttpServletRequest request) {
        ServletContext sc = request.getSession().getServletContext();
        if (StringUtils.isNotBlank(pathType)) {
            pathType = pathType + "/";
        }
        String filePath = "/upload/ajax/" + pathType + DateUtil.getCurrentDateY_M_D() + "/";
        String dir = sc.getRealPath(filePath);
        String fileName = file.getOriginalFilename();
        String extendName = fileName.substring(fileName.lastIndexOf("."));
        // String newFileName = DateUtil.getCurDateTimeStr() + "_" +
        // UUID.randomUUID() + extendName;
        String newFileName = DateUtil.getCurrentDateYMDHMS() + "_" + StringUtil.getSmsCode() + extendName;
        File targetDir = new File(dir);
        File targetFile = new File(dir, newFileName);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileUrl = filePath + newFileName;

        return fileUrl;
    }

    /**
     * 复制文件
     *
     * @param file
     * @param suffix   D:/kmUpload/img/
     * @param pathType jobType
     * @param request
     * @return
     * @time 2016年10月15日 下午5:34:45
     */
    @SuppressWarnings("unused")
    private String copyFileToOther(MultipartFile file, String suffix, String pathType, HttpServletRequest request) {

        String fileName = file.getOriginalFilename();
        String extendName = fileName.substring(fileName.lastIndexOf("."));

        String newFileName = DateUtil.getCurrentDateYMDHMS() + "_" + StringUtil.getSmsCode() + extendName;
        String filePath = "/" + pathType + "/" + DateUtil.getCurrentDateY_M_D() + "/";
        String dir = suffix + filePath;

        File targetDir = new File(dir);
        File targetFile = new File(dir, newFileName);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileUrl = filePath + newFileName;

        return fileUrl;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * ckeditor上传图片
     */
    @RequestMapping("uploadImg.htm")
    public void uplodaImg(@RequestParam("upload") MultipartFile file, //
                          HttpServletRequest request, //
                          HttpServletResponse response, //
                          @RequestParam("CKEditorFuncNum") String CKEditorFuncNum)//
            throws IllegalStateException, IOException {

        PrintWriter out = response.getWriter();
        String uploadContentType = file.getContentType();
        if (!uploadContentType.equals("image/pjpeg") && !uploadContentType.equals("image/jpeg")
                && !uploadContentType.equals("image/png") && !uploadContentType.equals("image/x-png")
                && !uploadContentType.equals("image/gif") && !uploadContentType.equals("image/bmp")) {
            out.println("<script type=\"text/javascript\">");
            out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'',"
                    + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
            out.println("</script>");
            return;
        }
        // if (file.getSize() > 600 * 1024) {
        // out.println("<script type=\"text/javascript\">");
        // out.println("window.parent.CKEDITOR.tools.callFunction(" +
        // CKEditorFuncNum + ",''," + "'文件大小不得大于600k');");
        // out.println("</script>");
        // return;
        // }
        String fileUrl = copyFile(file, "ckeditor/goodsDetail", request);

        SysFileUrl fileUrls = new SysFileUrl();
        fileUrls.setFileName(file.getOriginalFilename());
        // fileUrlsPO.setFileType(fileType);
        // fileUrlsPO.setTableId(tableId);
        // fileUrlsPO.setTableName(tableName);
        fileUrls.setFileUrl(fileUrl);

        // DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
        // fileName = df.format(new Date())+expandedName;
        // file.transferTo(new File(PROJECT_PATH+UPLOAD_PATH +fileName));
        // 返回"图像"选项卡并显示图片 request.getContextPath()为web项目名

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();

        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + CKEditorFuncNum + ",'" + url + fileUrl + "','')");
        out.println("</script>");
        return;
    }

}
