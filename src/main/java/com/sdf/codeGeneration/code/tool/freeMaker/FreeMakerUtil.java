package com.sdf.codeGeneration.code.tool.freeMaker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


/**
 * FreeMaker工具类
 *
 * @author: SDF
 * @dateTime: 2017-3-11 下午11:39:17
 * @version: 1.0.0
 */
public class FreeMakerUtil {

    /**
     * 获取模板文件
     *
     * @param name
     * @return
     */
    public Template getTemplate(String name) {
        try {
            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(this.getClass(), "../../../ftl");
            Template template = cfg.getTemplate(name);
            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 控制台输出
     *
     * @param templateName :模板名
     * @param templateData ：数据原型
     */
    public void print(String templateName, Map<String, Object> templateData) {

        try {
            Template template = this.getTemplate(templateName);
            template.process(templateData, new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成文件
     *
     * @param templateName :模板名
     * @param templateData ：数据原型
     * @param outFilePath  ：输出路径(全路径名)
     */
    public void generateFile(String templateName,
                             Map<String, Object> templateData, String outFilePath) {

        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            out = new FileWriter(new File(outFilePath));
            Template temp = this.getTemplate(templateName);
            temp.process(templateData, out);
            System.err.println("代码生成成功=======文件地址:" + outFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
