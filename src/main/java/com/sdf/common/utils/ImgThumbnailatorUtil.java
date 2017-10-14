package com.sdf.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 图片处理组件thumbnailator
 *
 * @author SDF
 * @date 2016年10月15日
 */
public class ImgThumbnailatorUtil extends BaseUtil {

    /**
     * 测试方法
     *
     * @param args
     * @throws IOException
     * @time 2016年10月15日 上午11:38:58
     */
    public static void main(String[] args) throws IOException {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        test6();
        // test7();
        // test8();
        // test9();
    }

    /**
     * 裁剪图片返回 thumb + 原图地址
     *
     * @param fileUrl
     * @throws IOException
     * @time 2017年5月17日 下午11:41:38
     */
    public static void createThumbImg(String fileUrl) throws IOException {
        String thumDir = PropertiesConfig.getUPLOAD_IMG_PATH() + "thumb/"
                + fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1);

        File targetDir = new File(thumDir);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        Thumbnails.of(PropertiesConfig.getUPLOAD_IMG_PATH() + fileUrl).sourceRegion(Positions.CENTER, 400, 400)
                .size(200, 200).keepAspectRatio(false)
                .toFile(thumDir + fileUrl.substring(fileUrl.lastIndexOf("/") + 1, fileUrl.length()));
    }

    /**
     * 按比例缩放返回 原图地址 + 比例
     *
     * @param fileUrl 原图片地址
     * @param scale   比例 默认 0.25
     * @return
     * @throws IOException
     * @time 2017年5月19日 下午4:01:16
     */
    public static String createZoomImg(String suffix, String fileUrl, float scale) throws IOException {

        int index = fileUrl.lastIndexOf(".");

        String zoomUrl = fileUrl.substring(0, index) + "_" + scale + fileUrl.substring(index, fileUrl.length());

        Thumbnails.of(suffix + fileUrl).scale(scale).toFile(suffix + zoomUrl);

        return zoomUrl;
    }

    /**
     * 指定大小进行缩放
     *
     * @param fileUrl   原文件地址
     * @param width     缩放宽度
     * @param height    缩放高度
     * @param targetUrl 目标地址
     * @throws IOException
     * @time 2016年10月15日 下午5:40:34
     */
    public static void test1(String fileUrl, Integer width, Integer height, String targetUrl) throws IOException {
        /*
		 * size(width,height) 若图片横比200小，高比300小，不变
		 * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
		 * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
		 */
        try {
            Thumbnails.of(fileUrl).size(width, height).toFile(targetUrl);
            logger.info("------------------->指定大小进行缩放图片: " + fileUrl + "  目标地址：" + targetUrl);

        } catch (Exception e) {
            logger.error("------------------->Exception: " + e);
            e.printStackTrace();
        }

    }

    /**
     * 按照比例进行缩放
     *
     * @throws IOException
     */
    public static void test2() throws IOException {
        /**
         * scale(比例)
         */
        Thumbnails.of("C:/test.jpg").scale(0.25f).toFile("C:/image_25%.jpg");
        Thumbnails.of("C:/test.jpg").scale(1.10f).toFile("C:/image_110%.jpg");
    }

    /**
     * 不按照比例，指定大小进行缩放
     *
     * @throws IOException
     */
    public static void test3() throws IOException {
        /**
         * keepAspectRatio(false) 默认是按照比例缩放的
         */
        Thumbnails.of("C:/test.jpg").size(120, 120).keepAspectRatio(false).toFile("C:/image_120x120.jpg");
    }

    /**
     * 旋转
     *
     * @throws IOException
     */
    public static void test4() throws IOException {
        /**
         * rotate(角度),正数：顺时针 负数：逆时针
         */
        Thumbnails.of("C:/test.jpg").size(1280, 1024).rotate(90).toFile("C:/image+90.jpg");
        Thumbnails.of("C:/test.jpg").size(1280, 1024).rotate(-90).toFile("C:/iamge-90.jpg");
    }

    /**
     * 水印
     *
     * @throws IOException
     */
    public static void test5() throws IOException {
        /**
         * watermark(位置，水印图，透明度)
         */
        Thumbnails.of("C:/test.jpg").size(1280, 1024)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")), 0.5f)
                .outputQuality(0.8f).toFile("C:/image_watermark_bottom_right.jpg");
        Thumbnails.of("C:/test.jpg").size(1280, 1024)
                .watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f).outputQuality(0.8f)
                .toFile("C:/image_watermark_center.jpg");
    }

    /**
     * 裁剪
     *
     * @throws IOException
     */
    public static void test6() throws IOException {
        /**
         * 裁剪图片中心400*400的区域
         */
        Thumbnails.of("C:/image_25%.jpg").size(200, 200).keepAspectRatio(false).toFile("C:/image_region_center11.jpg");
        /**
         * 裁剪图片右下400*400的区域
         */
        Thumbnails.of("C:/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 400, 400).size(200, 200)
                .keepAspectRatio(false).toFile("C:/image_region_bootom_right.jpg");
        /**
         * 裁剪指定坐标
         */
        Thumbnails.of("C:/test.jpg").sourceRegion(600, 500, 400, 400).size(200, 200).keepAspectRatio(false)
                .toFile("C:/image_region_coord.jpg");
    }

    /**
     * 转化图像格式
     *
     * @throws IOException
     */
    public static void test7() throws IOException {
        /**
         * outputFormat(图像格式)
         */
        Thumbnails.of("C:/test.jpg").size(1280, 1024).outputFormat("png").toFile("C:/image_1280x1024.png");
        Thumbnails.of("C:/test.jpg").size(1280, 1024).outputFormat("gif").toFile("C:/image_1280x1024.gif");
    }

    /**
     * 输出到OutputStream
     *
     * @throws IOException
     */
    public static void test8() throws IOException {
        /**
         * toOutputStream(流对象)
         */
        OutputStream os = new FileOutputStream("C:/image_1280x1024_OutputStream.png");
        Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
    }

    /**
     * 输出到BufferedImage
     *
     * @throws IOException
     */
    public static void test9() throws IOException {
        /**
         * asBufferedImage() 返回BufferedImage
         */
        BufferedImage thumbnail = Thumbnails.of("images/test.jpg").size(1280, 1024).asBufferedImage();
        ImageIO.write(thumbnail, "jpg", new File("C:/image_1280x1024_BufferedImage.jpg"));
    }
}
