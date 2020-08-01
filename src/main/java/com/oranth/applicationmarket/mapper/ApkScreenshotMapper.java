package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.ApkScreenshot;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * apk截图表 Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
//@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ApkScreenshotMapper extends BaseMapper<ApkScreenshot> {
    @Select("SELECT img_url FROM apk_screenshot WHERE apk_id=#{apk_id}")
    List<String> listScreenshot(@Param("apk_id")int apk_id);

    /**
     * 根据apk id获取apk所有截图
     * @param apkId
     * @return
     */
    public List<ApkScreenshot> getApkScreenshotByApkid(Integer apkId);

    /**
     * 新增apk应用截图
     * @param apkScreenshot
     * @return
     */
    public Integer addApkScreenshot(ApkScreenshot apkScreenshot);

    /**
     * 根据apk id删除该apk的应用截图
     * @param apkId
     * @return
     */
    public Integer delApkScreenshot(Integer apkId);

    /**
     * 修改apk应用截图
     * @param apkScreenshot
     * @return
     */
    public Integer editApkScreenshot(ApkScreenshot apkScreenshot);
}
