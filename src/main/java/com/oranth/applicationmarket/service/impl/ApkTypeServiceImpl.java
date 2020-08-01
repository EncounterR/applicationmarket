package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.ApkType;
import com.oranth.applicationmarket.mapper.ApkTypeMapper;
import com.oranth.applicationmarket.service.IApkTypeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * apk分类表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class ApkTypeServiceImpl implements IApkTypeService {

    @Autowired
    private ApkTypeMapper apkTypeMapper;

    @Override
    public List<ApkType> getAll() {
        return apkTypeMapper.getAll();
    }

    @Override
    public List<ApkType> getFatherApkType() {
        List<ApkType> apkTypeList=apkTypeMapper.getAll();
        List<ApkType> apkTypes=new ArrayList<>();
        for(ApkType apkType:apkTypeList){
            if(apkType.getPid()==0){
                apkTypes.add(apkType);
            }
        }
        return apkTypes;
    }

    @Override
    public ApkType getApkTypeById(Integer id) {
        return apkTypeMapper.getApkTypeById(id);
    }

    @Override
    public Boolean addApkType(ApkType apkType) {
        int row=apkTypeMapper.addApkType(apkType);
        return (row>0)?true:false;
    }

    @Override
    public Boolean delApkType(Integer id) {
        int row=apkTypeMapper.delApkType(id);
        return (row>0)?true:false;
    }

    @Override
    public Boolean editApkType(ApkType apkType) {
        int row=apkTypeMapper.editApkType(apkType);
        return (row>0)?true:false;
    }
}
