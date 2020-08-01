package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.entity.ApkComment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * apk评论表 Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Mapper
public interface ApkCommentMapper extends BaseMapper<ApkComment> {
    /**
     * 获取全部apk评论信息
     * @return
     */
    public List<ApkComment> getAll();

    /**
     * 根据id查询评论信息
     * @return
     */
    public ApkComment getApkCommentById(Integer id);

    /**
     * 添加评论
     * @param apkComment
     * @return
     */
    public Integer addApkComment(ApkComment apkComment);

    /**
     * 删除评论
     * @param id
     * @return
     */
    public Integer delApkComment(Integer id);

    /**
     * 修改评论
     * @param apkComment
     * @return
     */
    public Integer editApkComment(ApkComment apkComment);
}
