package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.Apk;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.CacheNamespaceRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * apk表 Mapper 接口
 * </p>
 * SELECT id,label,pkg_name,cla_name,file_size,version_code,version_name,min_sdk_version,target_sdk_version,icon,file_url,file_md5,
 * des,type_id,creator_id,create_time,edit_time,update_time,news,is_forbidden FROM apk WHERE id=?
 * @author linsiteng
 * @since 2020-04-21
 */
//@CacheNamespaceRef(ResponseDataMapper.class)
@Mapper
public interface ApkMapper {
    /**
     * 获取全部apk信息
     * @return
     */
    public List<Apk> getAll();

    /**
     * 根据id获取ak信息
     * @param id
     * @return
     */
    public Apk getApkById(Integer id);

    /**
     * 根据apk分类id查询
     * @param typeId
     * @return
     */
    public List<Apk> getApkByTypeId(Integer typeId);

    /**
     * 添加apk
     * @param apk
     * @return
     */
    public Integer addApk(Apk apk);

    /**
     * 删除apk
     * @param id
     * @return
     */
    public Integer delApk(Integer id);

    /**
     * 修改apk
     * @param apk
     * @return
     */
    public Integer editApk(Apk apk);
}