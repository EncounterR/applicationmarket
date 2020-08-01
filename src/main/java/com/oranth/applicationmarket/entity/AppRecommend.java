package com.oranth.applicationmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 推荐表
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AppRecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 推荐 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 界面的位置,所属的哪个推荐类，比如是recommend,video,other,game,toos
     */
    private Integer category;

    /**
     * apk的坑位的位置
     */
    private Integer sortOrder;

    /**
     * apk ID
     */
    private Integer apkId;

    /**
     * 推荐封面
     */
    private String img;

//    /**
//     * apk
//     */
//    @TableField(exist=false)
//    Apk apk;
//
//    /**
//     *  下载记录
//     */
//    @TableField(exist=false)
//    ApkCount apkCount;
//
//    /**
//     * 截图
//     */
//    List<ApkScreenshot> screenshot;


    public AppRecommend(Integer id, Integer category, Integer sortOrder, Integer apkId, String img) {
        this.id = id;
        this.category = category;
        this.sortOrder = sortOrder;
        this.apkId = apkId;
        this.img = img;
    }

    public AppRecommend(Integer category, Integer sortOrder, Integer apkId, String img) {
        this.category = category;
        this.sortOrder = sortOrder;
        this.apkId = apkId;
        this.img = img;
    }

    public AppRecommend() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getApkId() {
        return apkId;
    }

    public void setApkId(Integer apkId) {
        this.apkId = apkId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
