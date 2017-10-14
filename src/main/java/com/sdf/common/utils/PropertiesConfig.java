package com.sdf.common.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 读取Config文件工具类
 *
 * @author SDF
 * @version 1.0
 * @date 2016年9月8日
 * @since JDK 1.6
 */
public class PropertiesConfig extends BaseUtil {

    private static String UPLOAD_IMG_PATH = "";

    static {
        System.err.println("====================>>>开始读取configure.properties文件");

        Properties properties = readData("configure.properties");
        setUPLOAD_IMG_PATH(properties.getProperty("upload.img.path"));

        System.err.println("图片上传=====UPLOAD_IMG_PATH: " + UPLOAD_IMG_PATH);

        System.err.println("====================>>>结束读取configure.properties文件");
    }

    /**
     * 获取整个配置文件中的属性
     *
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties
     */
    @SuppressWarnings("deprecation")
    public static Properties readData(String filePath) {
        // 服务器上空格文件夹会转换成%20导致找不到配置文件 2017年5月6日17:40:57
        filePath = URLDecoder.decode(getRealPath(filePath));
        Properties props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            in.close();
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getRealPath(String filePath) {
        // 获取绝对路径 并截掉路径的”file:/“前缀
        return PropertiesConfig.class.getResource("/" + filePath).toString().substring(6);
    }

    public static void main(String[] args) {
        Properties readData = readData("jdbc.properties");
        System.err.println(readData.getProperty("jdbc.initialSize"));
    }

    /**
     * 获取图片保存地址前缀
     *
     * @return
     * @time 2017年5月7日 上午1:56:33
     */
    public static String getUPLOAD_IMG_PATH() {
        return UPLOAD_IMG_PATH;
    }

    public static void setUPLOAD_IMG_PATH(String uPLOAD_IMG_PATH) {
        UPLOAD_IMG_PATH = uPLOAD_IMG_PATH;
    }
}
