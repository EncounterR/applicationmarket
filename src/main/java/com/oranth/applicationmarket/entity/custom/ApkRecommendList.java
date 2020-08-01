package com.oranth.applicationmarket.entity.custom;

import com.oranth.applicationmarket.entity.AppRecommend;

import java.util.List;

public class ApkRecommendList {
    /**
     * apk所属分类
     */
    private Integer category;
    /**
     * 属于该分类写的所有apk推荐信息
     */
    private List<AppRecommend> appRecommendList;

    public ApkRecommendList(Integer category, List<AppRecommend> appRecommendList) {
        this.category = category;
        this.appRecommendList = appRecommendList;
    }

    public ApkRecommendList() {
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public List<AppRecommend> getAppRecommendList() {
        return appRecommendList;
    }

    public void setAppRecommendList(List<AppRecommend> appRecommendList) {
        this.appRecommendList = appRecommendList;
    }
}
