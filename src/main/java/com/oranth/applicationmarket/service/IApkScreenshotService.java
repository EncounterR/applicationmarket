package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.ApkScreenshot;

import java.util.List;

/**
 * <p>
 * apk截图表 服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IApkScreenshotService extends IService<ApkScreenshot> {

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
    public Boolean addApkScreenshot(ApkScreenshot apkScreenshot);

    /**
     * 根据apk id删除该apk的应用截图
     * @param apkId
     * @return
     */
    public Boolean delApkScreenshot(Integer apkId);

    /**
     * 修改apk应用截图
     * @param apkScreenshot
     * @return
     */
    public Boolean editApkScreenshot(ApkScreenshot apkScreenshot);
}
