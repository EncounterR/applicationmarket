package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.ApkCount;
import com.oranth.applicationmarket.mapper.ApkCountMapper;
import com.oranth.applicationmarket.service.IApkCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class ApkCountServiceImpl extends ServiceImpl<ApkCountMapper, ApkCount> implements IApkCountService {

    @Autowired
    ApkCountMapper countMapper;

    @Override
    public int updataBySn(int id, int downSum, int clickSum) {

        ApkCount apkCount = new ApkCount();
        apkCount.setApkId(id);
        apkCount.setDownloadNum(downSum);
        apkCount.setClickCount(clickSum);

        return countMapper.updateByIdAdd(apkCount);
    }
}
