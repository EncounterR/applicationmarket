package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.ApkCount;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IApkCountService extends IService<ApkCount> {

    int updataBySn(int id, int downSum, int clickSum);
}
