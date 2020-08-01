package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.ActiveApkComment;
import com.oranth.applicationmarket.entity.ApkComment;

import java.util.List;

/**
 * <p>
 * apk评论表 服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IApkCommentService extends IService<ApkComment> {
    /**
     * 获取全部apk评论信息
     * @return
     */
    public List<ApkComment> getAll();

    /**
     * 分页查询评论信息
     * @param currentPage 当前页
     * @param pageSize 每页显示的总条数
     * @return
     */
    public List<ApkComment> getApkCommentByPage(Integer currentPage,Integer pageSize);

    /**
     * 根据id查询评论信息
     * @return
     */
    public ApkComment getApkCommentById(Integer id);

    /**
     * 获取跟评分区间的评论信息
     * @return
     */
    public List<ActiveApkComment> getApkCommentByScore();

    /**
     * 添加评论
     * @param apkComment
     * @return
     */
    public Boolean addApkComment(ApkComment apkComment);

    /**
     * 删除评论
     * @param id
     * @return
     */
    public Boolean delApkComment(Integer id);

    /**
     * 修改评论
     * @param apkComment
     * @return
     */
    public Boolean editApkComment(ApkComment apkComment);
}
