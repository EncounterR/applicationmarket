package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.entity.AppRecommend;
import com.oranth.applicationmarket.mapper.sqlprovider.RecommendProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <p>
 * 推荐表 Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Mapper
public interface AppRecommendMapper{

    @Select("")
    public List<AppRecommend> selectAll();

    @SelectProvider(value = RecommendProvider.class,method = "saveList")
    Object saveList(@Param("recommendList") List<AppRecommend> recommendList);


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
    public Integer addAppRecommend(AppRecommend appRecommend);

    /**
     * 删除apk推荐信息
     * @param id
     * @return
     */
    public Integer delAppRecommend(Integer id);

    /**
     * 修改apk推荐信息
     * @param appRecommend
     * @return
     */
    public Integer editAppRecommend(AppRecommend appRecommend);
}
