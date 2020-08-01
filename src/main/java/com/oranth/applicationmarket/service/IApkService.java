package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.Apk;
import com.oranth.applicationmarket.entity.custom.ResultData;

import java.util.List;

/**
 * <p>
 * apk表 服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IApkService {
    /**
     * 获取全部apk信息
     * @return
     */
    public List<Apk> getAll();

    /**
     * 根据apk分类id查询
     * @param typeId
     * @return
     */
    public List<Apk> getApkByTypeId(Integer typeId);

    /**
     * 根据id获取apk信息，获取apk详情
     * @param id
     * @return
     */
    public Apk queryApkById(Integer id);

    /**
     * 修改apk信息
     * @param apk
     * @return
     */
    public Boolean editApk(Apk apk);

    String queryByType(String type,int page,int offset);

    List<ResultData> queryRecommend();

    boolean saveApk(String s, String s1);

    boolean updateApk(String s, String s1);

    String queryById(Integer id);

    int queryByTypeCount(String type,int offset);

    Integer del(Integer id);

    String queryByTypeAd(String type, int page, int offset);

    Integer queryByTypeCountAd(String type, int offset);
}
