package com.sdf.codeGeneration.code.tool.utils;

/**
 * 字符串工具类
 *
 * @author: SDF
 * @dateTime: 2017-3-11 下午10:48:19
 * @version: 1.0.0
 */
public class StringUtils {

    /**
     * 字符串格式修改 如（user_id ----> userId）
     *
     * @param str
     * @return
     */
    public static String convertColumnField(String str) {
        // 分隔符
        char separator = '_';
        // 转化为小写
        String variable = str.toLowerCase();

        if (variable.indexOf(separator) > -1) {
            char[] varArray = variable.toCharArray();
            for (int i = 0; i < varArray.length; i++) {
                if (varArray[i] == separator && i < varArray.length - 1) {
                    varArray[i + 1] = Character.toUpperCase(varArray[i + 1]);
                }
            }
            variable = new String(varArray).replaceAll("_", "");
        }

        return variable;

    }

    /**
     * 字符串格式修改 如（DEMO_COMPANY ----> DemoCompany）
     *
     * @param str
     * @return
     */
    public static String convertClassField(String str) {

        String variable = convertColumnField(str);

        variable = variable.substring(0, 1).toUpperCase() + variable.substring(1, variable.length());

        return variable;

    }

    public static void main(String[] args) {
        System.err.println(convertClassField("DEMO_COMPANY"));
    }

    /**
     * 字符串格式修改 如（controller ----> Controller）
     *
     * @param str
     * @return
     * @time 2017年3月14日 上午10:52:51
     */
    public static String convertBigField(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

}
