package com.oranth.applicationmarket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oranth.applicationmarket.entity.ApkType;

import java.util.List;

/**
 * <p>
 * apk分类表 服务类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface IApkTypeService{
    /**
     * 获取全部apk分类信息
     * @return
     */
    public List<ApkType> getAll();

    /**
     * 获取全部父分类
     * @return
     */
    public List<ApkType> getFatherApkType();

    /**
     * 根据id查询apk分类信息
     * @param id
     * @return
     */
    public ApkType getApkTypeById(Integer id);

    /**
     * 新增apk分类
     * @param apkType
     * @return
     */
    public Boolean addApkType(ApkType apkType);

    /**
     * 删除apk分类
     * @param id
     * @return
     */
    public Boolean delApkType(Integer id);

    /**
     * 修改apk分类
     * @param apkType
     * @return
     */
    public Boolean editApkType(ApkType apkType);
}
