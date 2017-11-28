package com.sdf.core.pojo.sysMenu;

import com.sdf.common.pojo.BaseEntity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 系统菜单管理(sys_menu)
 *
 * @Date: 2017/11/28 15:54
 * @Author: SDF
 * @Version: 1.0
 */
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     **/
    private Integer id;

    /**
     * 菜单名称
     **/
    private String title;

    /**
     * 菜单地址
     **/
    private String href;

    /**
     * 菜单图标
     **/
    private String icon;

    /**
     * 菜单级别
     **/
    private Integer level;

    /**
     * 父菜单ID
     **/
    private Integer pid;

    /**
     * 菜单排序
     **/
    private Integer sort;

    /**
     * 是否展开(1:是 0:否)
     **/
    private Integer spread;

    /**
     * 打开方式("_blank")
     **/
    private String target;

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return "SysMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", level=" + level +
                ", pid=" + pid +
                ", sort=" + sort +
                ", spread=" + spread +
                ", target=" + target +
                '}';
    }

    /**
     * get,set方法
     **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSpread() {
        return spread;
    }

    public void setSpread(Integer spread) {
        this.spread = spread;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}