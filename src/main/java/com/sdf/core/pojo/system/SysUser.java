package com.sdf.core.pojo.system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdf.common.pojo.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Integer role_id;

    /**
     * 邮箱
     **/
    private String email;

    /**
     * 省
     **/
    private String province;

    /**
     * 市
     **/
    private String city;

    /**
     * 区县
     **/
    private String county;

    /**
     * 街道/乡镇
     **/
    private String town;

    /**
     * 社区/村
     **/
    private String village;

    /**
     * 生日
     **/
    private Date birstday;

    /**
     * 图片
     **/
    private String img;

    /**
     * 性别(1:男 2:女 0:保密)
     **/
    private Integer sex;

    /**
     * 兴趣爱好
     **/
    private String hobby;

    /**
     * 角色对象
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
    public SysUser(String desc, String name, String phone, String code, String password, Integer role_id, String email, String province, String city, String county, String town, String village, Date birstday, String img, Integer sex, String hobby, SysRole sysRole) {
        this.desc = desc;
        this.name = name;
        this.phone = phone;
        this.code = code;
        this.password = password;
        this.role_id = role_id;
        this.email = email;
        this.province = province;
        this.city = city;
        this.county = county;
        this.town = town;
        this.village = village;
        this.birstday = birstday;
        this.img = img;
        this.sex = sex;
        this.hobby = hobby;
        this.sysRole = sysRole;
    }

    public SysUser(ResultSet rs) throws SQLException {
        super.id = rs.getInt("id");
        super.status = rs.getInt("status");
        super.is_delete = rs.getInt("is_delete");
        super.create_time = rs.getTimestamp("create_time");
        super.update_time = rs.getTimestamp("update_time");
        super.create_user = rs.getString("create_user");
        super.update_user = rs.getString("update_user");
        super.create_id = rs.getInt("create_id");
        super.update_id = rs.getInt("update_id");

        this.desc = rs.getString("desc");
        this.name = rs.getString("name");
        this.status = rs.getInt("status");
        this.phone = rs.getString("phone");
        this.code = rs.getString("code");
        this.password = rs.getString("password");
        this.role_id = rs.getInt("role_id");

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
        return role_id;
    }

    public void setRoleId(Integer role_id) {
        this.role_id = role_id;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public Date getBirstday() {
        return birstday;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setBirstday(Date birstday) {
        this.birstday = birstday;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
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
                ", role_id=" + role_id +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", birstday=" + birstday +
                ", img='" + img + '\'' +
                ", sex=" + sex +
                ", hobby='" + hobby + '\'' +
                ", sysRole=" + sysRole +
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