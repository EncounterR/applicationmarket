package com.oranth.applicationmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * apk表
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Apk implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * apk名称
     */
    private String label;

    /**
     * apk 别名 方便搜索
     */
    private String alias;
    /**
     * 包名
     */
    private String pkgName;

    /**
     * 类名
     */
    private String clsName;

    /**
     * apk 大小
     */
    private Integer fileSize;

    /**
     * 版本
     */
    private Integer versionCode;

    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 最小sdk
     */
    private String minSdkVersion;

    /**
     * 当前sdk
     */
    private String targetSdkVersion;

    /**
     * apk图标
     */
    private String icon;

    /**
     * apk 下载路径
     */
    private String fileUrl;

    /**
     * apk MD5值
     */
    private String fileMd5;

    /**
     * apk简介
     */
    private String des;

    /**
     * 分类
     */
    private Integer typeId;

    /**
     * 创建人
     */
    private Integer creatorId = 1;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime editTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 升级提示
     */
    private String news;

    /**
     * apk当前状态 0下架，1上架
     */
    private Integer isForbidden;

    public Apk(String label, String alias, String pkgName, String clsName, Integer fileSize, Integer versionCode, String versionName, String minSdkVersion, String targetSdkVersion, String icon, String fileUrl, String fileMd5, String des, Integer typeId, Integer creatorId, LocalDateTime createTime, LocalDateTime editTime, LocalDateTime updateTime, String news, Integer isForbidden) {
        this.label = label;
        this.alias = alias;
        this.pkgName = pkgName;
        this.clsName = clsName;
        this.fileSize = fileSize;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.minSdkVersion = minSdkVersion;
        this.targetSdkVersion = targetSdkVersion;
        this.icon = icon;
        this.fileUrl = fileUrl;
        this.fileMd5 = fileMd5;
        this.des = des;
        this.typeId = typeId;
        this.creatorId = creatorId;
        this.createTime = createTime;
        this.editTime = editTime;
        this.updateTime = updateTime;
        this.news = news;
        this.isForbidden = isForbidden;
    }

    public Apk(Integer id, String label, String alias, String pkgName, String clsName, Integer fileSize, Integer versionCode, String versionName, String minSdkVersion, String targetSdkVersion, String icon, String fileUrl, String fileMd5, String des, Integer typeId, Integer creatorId, String news, Integer isForbidden) {
        this.id = id;
        this.label = label;
        this.alias = alias;
        this.pkgName = pkgName;
        this.clsName = clsName;
        this.fileSize = fileSize;
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.minSdkVersion = minSdkVersion;
        this.targetSdkVersion = targetSdkVersion;
        this.icon = icon;
        this.fileUrl = fileUrl;
        this.fileMd5 = fileMd5;
        this.des = des;
        this.typeId = typeId;
        this.creatorId = creatorId;
        this.news = news;
        this.isForbidden = isForbidden;
    }

    public Apk() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getMinSdkVersion() {
        return minSdkVersion;
    }

    public void setMinSdkVersion(String minSdkVersion) {
        this.minSdkVersion = minSdkVersion;
    }

    public String getTargetSdkVersion() {
        return targetSdkVersion;
    }

    public void setTargetSdkVersion(String targetSdkVersion) {
        this.targetSdkVersion = targetSdkVersion;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public void setEditTime(LocalDateTime editTime) {
        this.editTime = editTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public Integer getIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
    }

}
