package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.AppRecommend;
import com.oranth.applicationmarket.entity.custom.ApkRecommendList;

import java.util.List;

/**
 * <p>
 * 推荐表 服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IAppRecommendService{
    int savaList(String str);

    int updateByCat(String s);


    /**
     * 获取全部apk推荐信息
     * @return
     */
    public List<AppRecommend> getAll();

    /**
     * 根据类别id获取apk推荐信息
     * @param category
     * @return
     */
    public List<AppRecommend> getAppRecommendByCategory(Integer category);

    /**
     * 根据推荐表id获取推荐信息
     * @param id
     * @return
     */
    public AppRecommend getAppRecommendById(Integer id);

    /**
     * 根据apkID获取信息推荐信息
     * @param apkId
     * @return
     */
    public AppRecommend getAppRecommendByApkId(Integer apkId);

    /**
     * 添加apk推荐信息
     * @param appRecommend
     * @return
     */
    public Boolean addAppRecommend(AppRecommend appRecommend);

    /**
     * 删除apk推荐信息
     * @param id
     * @return
     */
    public Boolean delAppRecommend(Integer id);

    /**
     * 修改apk推荐信息
     * @param appRecommend
     * @return
     */
    public Boolean editAppRecommend(AppRecommend appRecommend);

    /**
     * 根据分类id获取各分类下的apk推荐信息
     * @return
     */
    public List<ApkRecommendList> getApkRecommendByCategory();
}
