package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.ApkDownloadLog;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 下载记录表 Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Mapper
public interface ApkDownloadLogMapper extends BaseMapper<ApkDownloadLog> {
    /**
     * 获取全部下载记录
     * @return
     */
    public List<ApkDownloadLog> getAll();

    /**
     * 根据id查询下载记录
     * @param id
     * @return
     */
    public ApkDownloadLog getApkDownloadLogById(Integer id);

    /**
     * 添加下载记录
     * @param apkDownloadLog
     * @return
     */
    public Integer addApkDownloadLog(ApkDownloadLog apkDownloadLog);

    /**
     * 删除下载记录
     * @param id
     * @return
     */
    public Integer delApkDownloadLog(Integer id);

    /**
     * 修改下载记录
     * @param apkDownloadLog
     * @return
     */
    public Integer editApkDownloadLog(ApkDownloadLog apkDownloadLog);
}
