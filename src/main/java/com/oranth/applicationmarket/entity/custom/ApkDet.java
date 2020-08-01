package com.oranth.applicationmarket.entity.custom;

import com.oranth.applicationmarket.entity.Apk;
import com.oranth.applicationmarket.entity.ApkScreenshot;

import java.util.List;

/**
 * apk详细信息类，包含了apk截图
 */
public class ApkDet {
    /**
     * apk实体
     */
    private Apk apk;
    /**
     * apk对应的截图
     */
    private List<ApkScreenshot> apkScreenshotList;

    public ApkDet(Apk apk, List<ApkScreenshot> apkScreenshotList) {
        this.apk = apk;
        this.apkScreenshotList = apkScreenshotList;
    }

    public ApkDet() {
    }

    public Apk getApk() {
        return apk;
    }

    public void setApk(Apk apk) {
        this.apk = apk;
    }

    public List<ApkScreenshot> getApkScreenshotList() {
        return apkScreenshotList;
    }

    public void setApkScreenshotList(List<ApkScreenshot> apkScreenshotList) {
        this.apkScreenshotList = apkScreenshotList;
    }
}
