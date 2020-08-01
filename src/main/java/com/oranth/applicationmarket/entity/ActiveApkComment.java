package com.oranth.applicationmarket.entity;

import java.util.List;

public class ActiveApkComment {
    /**
     * 评分区间
     */
    private String score;
    /**
     * 评论信息
     */
    private List<ApkComment> apkCommentList;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public List<ApkComment> getApkCommentList() {
        return apkCommentList;
    }

    public void setApkCommentList(List<ApkComment> apkCommentList) {
        this.apkCommentList = apkCommentList;
    }

    public ActiveApkComment() {
    }

    public ActiveApkComment(String score, List<ApkComment> apkCommentList) {
        this.score = score;
        this.apkCommentList = apkCommentList;
    }
}
