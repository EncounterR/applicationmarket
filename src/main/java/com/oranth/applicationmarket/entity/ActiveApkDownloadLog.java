package com.oranth.applicationmarket.entity;

import java.util.List;

public class ActiveApkDownloadLog {
    /**
     * 月份
     */
    private String month;
    /**
     * 该月的下载记录
     */
    private List<ApkDownloadLog> apkDownloadLogList;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<ApkDownloadLog> getApkDownloadLogList() {
        return apkDownloadLogList;
    }

    public void setApkDownloadLogList(List<ApkDownloadLog> apkDownloadLogList) {
        this.apkDownloadLogList = apkDownloadLogList;
    }

    public ActiveApkDownloadLog() {
    }

    public ActiveApkDownloadLog(String month, List<ApkDownloadLog> apkDownloadLogList) {
        this.month = month;
        this.apkDownloadLogList = apkDownloadLogList;
    }

    @Override
    public String toString() {
        return "ActiveApkDownloadLog{" +
                "month='" + month + '\'' +
                ", apkDownloadLogList=" + apkDownloadLogList +
                '}';
    }
}
