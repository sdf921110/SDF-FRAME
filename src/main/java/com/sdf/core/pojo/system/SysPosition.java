package com.sdf.core.pojo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdf.common.pojo.BaseEntity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 系统地址（position_all）
 *
 * @Date: 2017/11/10 16:45
 * @Author: SDF
 * @Version: 1.0
 */
public class SysPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     **/
    private BigInteger id;

    /**
     * 父ID
     **/
    private BigInteger parent_id;

    /**
     * 名称
     **/
    private String name;

    /**
     * 级别
     **/
    private Integer level;

    /**
     * 排序
     **/
    private Integer sort;

    /**
     * 无参构造方法
     **/
    public SysPosition() {
    }

    /**
     * 带参构造方法
     */
    public SysPosition(BigInteger id, BigInteger parent_id, String name, Integer level, Integer sort) {
        this.id = id;
        this.parent_id = parent_id;
        this.name = name;
        this.level = level;
        this.sort = sort;
    }

    public SysPosition(ResultSet rs) throws SQLException {
//        this.id = rs.getBigInt("id");
//        this.parent_id = rs.getBigInt("parent_id");

        this.name = rs.getString("name");
        this.level = rs.getInt("level");
        this.sort = rs.getInt("sort");
    }

    /**
     * get,set方法
     **/
    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigInteger getParent_id() {
        return parent_id;
    }

    public void setParent_id(BigInteger parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return "SysPosition{" +
                "id=" + id +
                ", parent_id=" + parent_id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", sort=" + sort +
                '}';
    }
}