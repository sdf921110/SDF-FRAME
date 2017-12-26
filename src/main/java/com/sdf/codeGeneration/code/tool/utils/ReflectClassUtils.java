package com.sdf.codeGeneration.code.tool.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectClassUtils {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Person p = new Person();
        List<Map<String, String>> reflect = getReflect(p);

        for (Map<String, String> map : reflect) {

            System.err.println(map.get("type"));
            System.err.println(map.get("name"));
        }

    }

    public static List<Map<String, String>> getReflect(Object obj) {
        if (obj == null) {
            return null;
        }

        List<Map<String, String>> list = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {

            Map<String, String> map = new HashMap<>();
            // 字段类型
            String type = fields[j].getType().getSimpleName();
            // 字段名
            String name = fields[j].getName();

            if ("Integer".equals(type)) {
                type = "Int";
            }

            if ("Character".equals(type)) {
                type = "String";
            }

            map.put("type", type);
            map.put("name", name);

            list.add(map);
        }

        return list;
    }
}
