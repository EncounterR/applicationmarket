package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.ApkType;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * apk分类表 Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Mapper
public interface ApkTypeMapper extends BaseMapper<ApkType> {

    @Select("select * from apk_type")
    ArrayList<ApkType> selectAll();

    /**
     * 获取全部apk分类信息
     * @return
     */
    public List<ApkType> getAll();

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
    public Integer addApkType(ApkType apkType);

    /**
     * 删除apk分类
     * @param id
     * @return
     */
    public Integer delApkType(Integer id);

    /**
     * 修改apk分类
     * @param apkType
     * @return
     */
    public Integer editApkType(ApkType apkType);
}
