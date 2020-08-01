package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.ApkCount;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
//@CacheNamespace(implementation = MybatisRedisCache.class)
public interface ApkCountMapper extends BaseMapper<ApkCount> {

    @Update("UPDATE apk_count set click_count=click_count+#{clickCount},download_num=download_num+#{downloadNum} where apk_id = #{apkId}")
    int updateByIdAdd(ApkCount apkCount);
}
