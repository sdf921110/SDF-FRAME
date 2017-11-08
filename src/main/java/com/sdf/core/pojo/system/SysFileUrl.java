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

    public SysFileUrl(ResultSet rs) throws SQLException {
        super.id = rs.getInt("id");
        super.status = rs.getInt("status");
        super.is_delete = rs.getInt("is_delete");
        super.create_time = rs.getTimestamp("create_time");
        super.update_time = rs.getTimestamp("update_time");
        super.create_user = rs.getString("create_user");
        super.update_user = rs.getString("update_user");
        super.create_id = rs.getInt("create_id");
        super.update_id = rs.getInt("update_id");

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

}