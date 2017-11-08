package com.sdf.core.pojo.system;

import com.sdf.common.pojo.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 后台用户角色(sys_role)
 *
 * @author SDF
 * @version: 1.0.0
 * @time: 2017-4-19 16:46:11
 */
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名
     **/
    private String name;

    /**
     * 角色描述
     **/
    private String desc;

    /**
     * 是否平台管理员（1：是 0：否）
     **/
    private Integer is_admin;

    /**
     * 无参构造方法
     **/
    public SysRole() {
    }

    /**
     * 带参构造方法
     */
    public SysRole(String name, String desc, Integer is_admin) {
        this.name = name;
        this.desc = desc;
        this.is_admin = is_admin;
    }

    public SysRole(ResultSet rs) throws SQLException {
        super.id = rs.getInt("id");
        super.status = rs.getInt("status");
        super.is_delete = rs.getInt("is_delete");
        super.create_time = rs.getTimestamp("create_time");
        super.update_time = rs.getTimestamp("update_time");
        super.create_user = rs.getString("create_user");
        super.update_user = rs.getString("update_user");
        super.create_id = rs.getInt("create_id");
        super.update_id = rs.getInt("update_id");

        this.name = rs.getString("name");
        this.desc = rs.getString("desc");
        this.is_admin = rs.getInt("is_admin");
    }

    /**
     * get,set方法
     **/
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Integer is_admin) {
        this.is_admin = is_admin;
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return "SysRole{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", is_admin=" + is_admin +
                ", id=" + id +
                ", create_id=" + create_id +
                ", update_id=" + update_id +
                ", create_user='" + create_user + '\'' +
                ", update_user='" + update_user + '\'' +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", is_delete=" + is_delete +
                ", status=" + status +
                '}';
    }
}