package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.ActiveApkDownloadLog;
import com.oranth.applicationmarket.entity.Apk;
import com.oranth.applicationmarket.entity.ApkDownloadLog;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * <p>
 * 下载记录表 服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IApkDownloadLogService extends IService<ApkDownloadLog> {
    int downStat(ApkDownloadLog apkDownloadLog);

    /**
     * 获取全部下载记录
     * @return
     */
    public List<ApkDownloadLog> getAll();

    /**
     * 获取每个月份的下载记录总量
     * @return
     */
    public List<ActiveApkDownloadLog> getApkDownloadLogByDownloadTime();

    /**
     * 添加下载记录
     * @param apkDownloadLog
     * @return
     */
    public Boolean addApkDownloadLog(ApkDownloadLog apkDownloadLog);

    /**
     * 删除下载记录
     * @param id
     * @return
     */
    public Boolean delApkDownloadLog(Integer id);

    /**
     * 修改下载记录
     * @param apkDownloadLog
     * @return
     */
    public Boolean editApkDownloadLog(ApkDownloadLog apkDownloadLog);
}
