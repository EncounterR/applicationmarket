package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.oranth.applicationmarket.entity.ActiveApkComment;
import com.oranth.applicationmarket.entity.ApkComment;
import com.oranth.applicationmarket.entity.custom.PageBean;
import com.oranth.applicationmarket.mapper.ApkCommentMapper;
import com.oranth.applicationmarket.service.IApkCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * apk评论表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class ApkCommentServiceImpl extends ServiceImpl<ApkCommentMapper, ApkComment> implements IApkCommentService {
    @Autowired
    private ApkCommentMapper apkCommentMapper;

    @Override
    public List<ApkComment> getAll() {
        return apkCommentMapper.getAll();
    }

    @Override
    public List<ApkComment> getApkCommentByPage(Integer currentPage, Integer pageSize) {
        //设置分页信息
        PageHelper.startPage(currentPage,pageSize);
        //全部评论信息
        List<ApkComment> apkCommentList=apkCommentMapper.getAll();
        //总记录数
        int countNums=apkCommentList.size();
        PageBean<ApkComment> apkCommentPageBean=new PageBean<>(countNums,pageSize,countNums);
        apkCommentPageBean.setItems(apkCommentList);
        System.out.println("=============="+apkCommentPageBean);
        return apkCommentPageBean.getItems();
    }

    @Override
    public ApkComment getApkCommentById(Integer id) {
        return apkCommentMapper.getApkCommentById(id);
    }

    @Override
    public List<ActiveApkComment> getApkCommentByScore() {
        List<ApkComment> apkCommentList = apkCommentMapper.getAll();
        List<ApkComment> apkCommentList1 = new ArrayList<>();
        List<ApkComment> apkCommentList2 = new ArrayList<>();
        List<ApkComment> apkCommentList3 = new ArrayList<>();
        List<ActiveApkComment> activeApkComments = new ArrayList<>();
        for (ApkComment apkComment : apkCommentList) {
            if (apkComment.getScore() >= 0 && apkComment.getScore() < 6) {
                apkCommentList1.add(apkComment);
            } else if (apkComment.getScore() >= 6 && apkComment.getScore() < 8) {
                apkCommentList2.add(apkComment);
            } else if (apkComment.getScore() >= 8 && apkComment.getScore() <= 10) {
                apkCommentList3.add(apkComment);
            }
        }
        activeApkComments.add(new ActiveApkComment("0-6", apkCommentList1));
        activeApkComments.add(new ActiveApkComment("6-8", apkCommentList2));
        activeApkComments.add(new ActiveApkComment("8-10", apkCommentList3));
        return activeApkComments;
    }

    @Override
    public Boolean addApkComment(ApkComment apkComment) {
        int row = apkCommentMapper.addApkComment(apkComment);
        return (row > 0) ? true : false;
    }

    @Override
    public Boolean delApkComment(Integer id) {
        int row = apkCommentMapper.delApkComment(id);
        return (row > 0) ? true : false;
    }

    @Override
    public Boolean editApkComment(ApkComment apkComment) {
        int row = apkCommentMapper.editApkComment(apkComment);
        return (row > 0) ? true : false;
    }
}
