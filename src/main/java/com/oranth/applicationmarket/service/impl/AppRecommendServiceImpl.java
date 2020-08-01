package com.oranth.applicationmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.AppRecommend;
import com.oranth.applicationmarket.entity.custom.ApkRecommendList;
import com.oranth.applicationmarket.mapper.AppRecommendMapper;
import com.oranth.applicationmarket.service.IAppRecommendService;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 推荐表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class AppRecommendServiceImpl implements IAppRecommendService {

    @Autowired
    AppRecommendMapper arm;

    @Override
    public int savaList(String jsonlist) {
//        JSONObject jsonObj =  JSON.parseObject(jsonlist);
        JSONArray jsonArray = JSONArray.parseArray(jsonlist);
        List<AppRecommend> recommendList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            AppRecommend appRecommend = new AppRecommend();
            appRecommend.setSortOrder(Integer.valueOf(obj.get("sortOrder").toString()));
            appRecommend.setCategory(Integer.valueOf(obj.get("category").toString()));
            appRecommend.setImg(obj.get("img").toString());
            appRecommend.setApkId(Integer.valueOf(obj.get("apkId").toString()));
            recommendList.add(appRecommend);
        }
        Object b = arm.saveList(recommendList);
        return recommendList.size();
    }

    @Override
    public int updateByCat(String s) {
        return 0;
    }

    /*@Override
    public int updateByCat(String s) {
        JSONArray jsonArray = JSONArray.parseArray(s);
        List<AppRecommend> recommendList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            AppRecommend appRecommend = new AppRecommend();
            appRecommend.setSortOrder(Integer.valueOf(obj.get("sortOrder").toString()));
            appRecommend.setCategory(Integer.valueOf(obj.get("category").toString()));
            appRecommend.setImg(obj.get("img").toString());
            appRecommend.setApkId(Integer.valueOf(obj.get("apkId").toString()));
            UpdateWrapper<AppRecommend> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("category", appRecommend.getCategory());
            updateWrapper.eq("sort_order", appRecommend.getSortOrder());
            arm.update(appRecommend, updateWrapper);
        }

        return 0;
    }*/

    @Override
    public List<AppRecommend> getAll() {
        return arm.getAll();
    }

    @Override
    public List<AppRecommend> getAppRecommendByCategory(Integer category) {
        return arm.getAppRecommendByCategory(category);
    }

    @Override
    public AppRecommend getAppRecommendById(Integer id) {
        return arm.getAppRecommendById(id);
    }

    @Override
    public AppRecommend getAppRecommendByApkId(Integer apkId) {
        return arm.getAppRecommendByApkId(apkId);
    }

    @Override
    public Boolean addAppRecommend(AppRecommend appRecommend) {
        int row = arm.addAppRecommend(appRecommend);
        return (row == 1) ? true : false;
    }

    @Override
    public Boolean delAppRecommend(Integer id) {
        int row = arm.delAppRecommend(id);
        return (row == 1) ? true : false;
    }

    @Override
    public Boolean editAppRecommend(AppRecommend appRecommend) {
        int row = arm.editAppRecommend(appRecommend);
        return (row == 1) ? true : false;
    }

    @Override
    public List<ApkRecommendList> getApkRecommendByCategory() {
        List<ApkRecommendList> apkRecommendLists=new ArrayList<>();
        apkRecommendLists.add(new ApkRecommendList(1,arm.getAppRecommendByCategory(1)));
        apkRecommendLists.add(new ApkRecommendList(2,arm.getAppRecommendByCategory(2)));
        apkRecommendLists.add(new ApkRecommendList(3,arm.getAppRecommendByCategory(3)));
        apkRecommendLists.add(new ApkRecommendList(4,arm.getAppRecommendByCategory(4)));
        apkRecommendLists.add(new ApkRecommendList(5,arm.getAppRecommendByCategory(5)));
        return apkRecommendLists;
    }
}
