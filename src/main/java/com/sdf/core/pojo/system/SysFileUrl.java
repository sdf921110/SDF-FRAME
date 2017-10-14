package com.sdf.core.pojo.system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sdf.common.pojo.BaseEntity;

/**
 * 系统文件管理(sys_file_urls)
 *
 * @author SDF
 * @version: 1.0.0
 * @time: 2017-5-6 23:55:19
 */
public class SysFileUrl extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名称
     **/
    private String fileName;

    /**
     * 文件类型
     **/
    private String fileType;

    /**
     * 表ID
     **/
    private Integer tableId;

    /**
     * 表名称
     **/
    private String tableName;

    /**
     * 文件地址
     **/
    private String fileUrl;

    /**
     * 缩略图(1:是 0:否)
     **/
    private Integer isThumb;

    /**
     * 图片(1:是 0:否)
     **/
    private Integer isImg;

    /**
     * 无参构造方法
     **/
    public SysFileUrl() {
    }

    public SysFileUrl(ResultSet rs) throws SQLException {
        super.id = rs.getInt("id");
        super.status = rs.getInt("status");
        super.isDelete = rs.getInt("isDelete");
        super.createTime = rs.getTimestamp("createTime");
        super.updateTime = rs.getTimestamp("updateTime");
        super.createUser = rs.getString("createUser");
        super.updateUser = rs.getString("updateUser");
        super.createId = rs.getInt("createId");
        super.updateId = rs.getInt("updateId");

        this.fileName = rs.getString("fileName");
        this.fileType = rs.getString("fileType");
        this.tableId = rs.getInt("tableId");
        this.tableName = rs.getString("tableName");
        this.fileUrl = rs.getString("fileUrl");
        this.isThumb = rs.getInt("isThumb");
        this.isImg = rs.getInt("isImg");
    }

    /**
     * get,set方法
     **/
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getIsThumb() {
        return isThumb;
    }

    public void setIsThumb(Integer isThumb) {
        this.isThumb = isThumb;
    }

    public Integer getIsImg() {
        return isImg;
    }

    public void setIsImg(Integer isImg) {
        this.isImg = isImg;
    }

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return "SysFileUrl{" +
                "fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", tableId=" + tableId +
                ", tableName='" + tableName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", isThumb=" + isThumb +
                ", isImg=" + isImg +
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