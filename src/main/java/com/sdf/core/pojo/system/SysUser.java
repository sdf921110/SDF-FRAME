package com.sdf.core.pojo.system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sdf.common.pojo.BaseEntity;

/**
 * 后台用户(sys_user)
 *
 * @author SDF
 * @version: 1.0.0
 * @time: 2017-4-14 16:58:19
 */
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账号状态账号状态（1：正常 2：冻结 3: 注销）
     */
    public static enum sysUserStatus {
        正常, 冻结, 注销
    }

    /**
     * 描述
     **/
    private String desc;

    /**
     * 姓名
     **/
    private String name;

    /**
     * 手机
     **/
    private String phone;

    /**
     * 登陆账号
     **/
    private String code;

    /**
     * 登录密码
     **/
    private String password;

    /**
     * 角色ID
     **/
    private Integer roleId;

    /**
     * 角色
     **/
    private SysRole sysRole;

    /**
     * 无参构造方法
     **/
    public SysUser() {
    }

    /**
     * 带参构造方法
     */
    public SysUser(String desc, String name, String phone, String code, String password, Integer roleId, SysRole sysRole) {
        this.desc = desc;
        this.name = name;
        this.phone = phone;
        this.code = code;
        this.password = password;
        this.roleId = roleId;
        this.sysRole = sysRole;
    }

    public SysUser(ResultSet rs) throws SQLException {
        super.id = rs.getInt("id");
        super.status = rs.getInt("status");
        super.isDelete = rs.getInt("is_delete");
        super.createTime = rs.getTimestamp("create_time");
        super.updateTime = rs.getTimestamp("update_time");
        super.createUser = rs.getString("create_user");
        super.updateUser = rs.getString("update_user");
        super.createId = rs.getInt("create_id");
        super.updateId = rs.getInt("update_id");

        this.desc = rs.getString("desc");
        this.name = rs.getString("name");
        this.status = rs.getInt("status");
        this.phone = rs.getString("phone");
        this.code = rs.getString("code");
        this.password = rs.getString("password");
        this.roleId = rs.getInt("roleId");

        this.sysRole = new SysRole();
        sysRole.setName(rs.getString("roleName"));
        sysRole.setIs_admin(rs.getInt("is_admin"));
    }

    /**
     * get,set方法
     **/
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return "SysUser{" +
                "desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", sysRole=" + sysRole +
                ", id=" + id +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", createUser='" + createUser + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", status=" + status +
                '}';
    }
}