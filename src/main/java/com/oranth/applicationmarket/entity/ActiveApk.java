package com.oranth.applicationmarket.entity;

import java.util.List;

public class ActiveApk {
    private ApkType apkType;
    private List<Apk> apkList;

    public ActiveApk(ApkType apkType, List<Apk> apkList) {
        this.apkType = apkType;
        this.apkList = apkList;
    }

    public ApkType getApkType() {
        return apkType;
    }

    public void setApkType(ApkType apkType) {
        this.apkType = apkType;
    }

    public List<Apk> getApkList() {
        return apkList;
    }

    public void setApkList(List<Apk> apkList) {
        this.apkList = apkList;
    }

    public ActiveApk() {
    }
}
