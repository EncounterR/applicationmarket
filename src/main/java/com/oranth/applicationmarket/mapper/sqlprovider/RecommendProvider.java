package com.oranth.applicationmarket.mapper.sqlprovider;

import com.oranth.applicationmarket.entity.AppRecommend;

import java.util.List;
import java.util.Map;

public class RecommendProvider {

    public String saveList(Map<String,Object> map) {
        List<AppRecommend> list = (List<AppRecommend>) map.get("recommendList");
        String  str = "";
        for (AppRecommend appRecommend:list){
            str += "("+appRecommend.getCategory()+","+appRecommend.getSortOrder()+","+appRecommend.getApkId()+",'"+appRecommend.getImg()+"'),";
        }
        str = str.substring(0,str.length()-1);
        String sql = "INSERT INTO app_recommend ( category, sort_order, apk_id, img ) VALUES "+str;
        return sql;
    }
}
