package com.sdf.common.converter;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局日期处理类
 * Convert<T,S>
 * 泛型T:代表客户端提交的参数 String
 * 泛型S:通过convert转换的类型
 *
 * @Date: 2017/11/8 20:56
 * @Author: SDF
 * @Version: 1.0
 */
public class DateConvert implements Converter<String, Date> {

    @Override
    public Date convert(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
