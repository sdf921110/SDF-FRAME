package com.sdf.codeGeneration.code.tool.model;

/**
 * 数据库表对象
 *
 * @author: SDF
 * @dateTime: 2017-3-11 下午9:21:33
 * @version: 1.0.0
 */
public class FieldBean {

    private String fieldName; // 数据库字段名(user_id)
    private String fieldDesc; // 数据库字段名描述
    private String proName; // 变量名(userId)
    private String bigProName; // 数据库字段名(UserId)
    private String proType; // 变量类型(Integer)
    private String intProType; // 变量类型(Int)

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getBigProName() {
        return bigProName;
    }

    public void setBigProName(String bigProName) {
        this.bigProName = bigProName;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getIntProType() {
        return intProType;
    }

    public void setIntProType(String intProType) {
        this.intProType = intProType;
    }

    @Override
    public String toString() {
        return "FieldBean [fieldName=" + fieldName + ", fieldDesc=" + fieldDesc + ", proName=" + proName
                + ", bigProName=" + bigProName + ", proType=" + proType + ", intProType=" + intProType + "]";
    }

}
