package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.ApkScreenshot;
import com.oranth.applicationmarket.mapper.ApkScreenshotMapper;
import com.oranth.applicationmarket.service.IApkScreenshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * apk截图表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class ApkScreenshotServiceImpl extends ServiceImpl<ApkScreenshotMapper, ApkScreenshot> implements IApkScreenshotService {
    @Autowired
    ApkScreenshotMapper apkScreenshotMapper;

    @Override
    public List<ApkScreenshot> getApkScreenshotByApkid(Integer apkId) {
        return apkScreenshotMapper.getApkScreenshotByApkid(apkId);
    }

    @Override
    public Boolean addApkScreenshot(ApkScreenshot apkScreenshot) {
        int row = apkScreenshotMapper.addApkScreenshot(apkScreenshot);
        return (row > 0) ? true : false;
    }

    @Override
    public Boolean delApkScreenshot(Integer apkId) {
        int row = apkScreenshotMapper.delApkScreenshot(apkId);
        return (row > 0) ? true : false;
    }

    @Override
    public Boolean editApkScreenshot(ApkScreenshot apkScreenshot) {
        int row = apkScreenshotMapper.editApkScreenshot(apkScreenshot);
        return (row > 0) ? true : false;
    }
}
