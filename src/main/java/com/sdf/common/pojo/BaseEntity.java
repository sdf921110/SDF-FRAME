package com.sdf.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 基础实体类
 * @Date: 2017/10/12 13:56
 * @Author: SDF
 * @Version: 1.0
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected Integer create_id;
    protected Integer update_id;
    protected String create_user;
    protected String update_user;
    protected Date create_time;
    protected Date update_time;
    protected Integer is_delete;// 是否删除(1:是 0:否)
    protected Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreate_id() {
        return create_id;
    }

    public void setCreate_id(Integer create_id) {
        this.create_id = create_id;
    }

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
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
