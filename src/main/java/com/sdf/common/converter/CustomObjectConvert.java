package com.sdf.common.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * Spring JSON输出格式化转换
 * Created by zhangjian on 2017/06/05.
 */
public class CustomObjectConvert extends ObjectMapper {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean camelCaseToLowerCaseWithUnderscores = false;
    private String dateFormatPattern;

    public void setCamelCaseToLowerCaseWithUnderscores(
            boolean camelCaseToLowerCaseWithUnderscores) {
        this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;
    }

    public void setDateFormatPattern(String dateFormatPattern) {
        this.dateFormatPattern = dateFormatPattern;
    }

    public void init() {

        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 排除值为空属性
        //setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 空值处理为空串
         /*
         this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() { 
             @Override 
             public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException { 
                 	jg.writeString(""); 
             } 
         }); 
         */
        // 进行缩进输出
        configure(SerializationFeature.INDENT_OUTPUT, true);
        // 将驼峰转为下划线
        if (camelCaseToLowerCaseWithUnderscores) {
            setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        }
        // 进行日期格式化
        if (!StringUtils.isEmpty(dateFormatPattern)) {
            DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
            setDateFormat(dateFormat);
        }
    }

}
