package com.sdf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 文件与base64字符之间的相互转换
 *
 * @author SDF
 * @date 2016年10月19日
 */
public class Base64FileUtil {

    /**
     * 将文件转成base64 字符串
     *
     * @param path 文件路径
     * @return *
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);

    }

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void decoderBase64File(String base64Code, String targetPath) throws Exception {
        base64Code = base64Code.split("data:image/jpeg;base64,")[1];

        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();

    }

    /**
     * 将base64字符保存文本文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */
    public static void toFile(String base64Code, String targetPath) throws Exception {

        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 将base64字符解码保存到（项目/upload）并返回url
     *
     * @param base64Code base64值
     * @param pathType   文件夹
     * @param extendName 后缀名
     * @param request
     * @return 保存url地址
     * @throws Exception
     * @time 2016年10月20日 下午2:44:45
     */
    public static String decoderBase64FileToUpload(String base64Code, String pathType, String extendName,
                                                   HttpServletRequest request) throws Exception {
        // base64Code = base64Code.split("data:image/jpeg;base64,")[1];

        ServletContext sc = request.getSession().getServletContext();
        if (StringUtils.isNotBlank(pathType)) {
            pathType = pathType + "/";
        }
        // String newFileName = DateUtil.getCurDateTimeStr() + "_" +
        // UUID.randomUUID() + "." + extendName;
        String newFileName = DateUtil.getCurrentDateYMDHMS() + "_" + StringUtil.getSmsCode() + "." + extendName;
        String filePath = "/upload/base64/" + pathType + DateUtil.getCurrentDateY_M_D() + "/";
        String dir = sc.getRealPath(filePath);

        File targetDir = new File(dir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        try {
            byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
            FileOutputStream out = new FileOutputStream(dir + "\\" + newFileName);
            out.write(buffer);
            out.close();
            String fileUrl = filePath + newFileName;
            return fileUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 将base64字符解码保存到（本地其他目录）并返回url
     *
     * @param base64Code base64值
     * @param pathType   文件夹
     * @param extendName 后缀名
     * @param request
     * @return 保存url地址
     * @throws Exception
     * @time 2017年3月24日 上午11:16:49
     */
    public static String decoderBase64FileToOtherUpload(String base64Code, String pathType, String extendName,
                                                        HttpServletRequest request) throws Exception {

        if (StringUtils.isNotBlank(pathType)) {
            pathType = pathType + "/";
        }
        String newFileName = DateUtil.getNowDate() + "_" + StringUtil.getSmsCode() + "." + extendName;
        String filePath = "base64/" + pathType + DateUtil.getCurrentDateY_M_D() + "/";
        String dir = PropertiesConfig.getUPLOAD_IMG_PATH() + filePath;

        File targetDir = new File(dir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        try {

            byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
            FileOutputStream out = new FileOutputStream(dir + "\\" + newFileName);
            out.write(buffer);
            out.close();
            return filePath + newFileName;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            // String base64Code = encodeBase64File("D:/banner_1.jpg");
            // System.out.println(base64Code);
            // decoderBase64File(base64Code, "D:/2.tif");
            // toFile(base64Code, "D:\\three.txt");

            decoderBase64File(
                    "data:image/jpeg;base64,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G31fQtTvtG1W080W2o6bcy2l5AJ4Xt51jnhZWCTwSSQzRkmOWJ2SRWUkUAQ6hqF/q9/d6pq19d6pqeoTfaL7UdQuJby9vJ9qxiW5uZ3eWZljRI03sRHFHHFGFjRFUAqUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFABQAUAFAH//Z",
                    "D:/31.png");

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}