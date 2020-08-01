package com.oranth.applicationmarket.entity.custom;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * <p>
 * apk表
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
//@Data
//@EqualsAndHashCode(callSuper = false)
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ResultData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 包名
     */
    @TableField("pkg_name")
    private String pkgName;

    /**
     *  界面标识
     */
    private String category;
    /**
     *  详细位置
     */
    private Integer sortOrder;

    /**
     * 类名
     */
    @TableField("cls_name")
    private String clsName="null";

    /**
     * apk 名称
     */
 //   private String title;
    /**
     * apk名称
     */
    private String label;
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
     * apk图标
     */
    private String icon;

    /**
     * apk 下载路径
     */
    private String fileUrl;


    /**
     * apk简介
     */
    private String des;

    /**
     * 分类
     */
    private String type;

    /**
     * 升级提示
     */
    private String news = "null";

    /**
     *  screenShot 截图
     */
    ArrayList<String> screenShot;

    /**
     * 下载次数
     */
    int downloadNum;
    /**
     * img 推荐图片
     */
    String img;

    /**
     * star 评分
     */
    String star  = "0";

    /**
     * 隐藏 or 显示
     */
    private Integer isForbidden = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public ArrayList<String> getScreenShot() {
        return screenShot;
    }

    public void setScreenShot(ArrayList<String> screenShot) {
        this.screenShot = screenShot;
    }

    public int getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(int downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public Integer getIsForbidden() {
        return isForbidden;
    }

    public void setIsForbidden(Integer isForbidden) {
        this.isForbidden = isForbidden;
    }
}
