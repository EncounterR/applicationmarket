package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.custom.ResultData;
import com.oranth.applicationmarket.mapper.sqlprovider.ResponseDataProvider;
import org.apache.ibatis.annotations.*;

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
//@CacheNamespace(implementation = MybatisRedisCache.class,readWrite=false,flushInterval=10000)
//@CacheNamespaceRef
public interface ResponseDataMapper {

    @Select("SELECT a.id as id,a.is_forbidden as isForbidden,a.type_id as type,ar.category,ar.sort_order,ar.img,a.label,a.pkg_name,a.cls_name,a.file_size,a.des,a.version_code,a.version_name,a.icon,a.file_url,a.news,ac.download_num\n" +
            " FROM app_recommend ar LEFT JOIN apk a ON ar.apk_id = a.id LEFT JOIN apk_count ac ON a.id = ac.apk_id")
    @Results({
            @Result(column="id",property = "id"),
            @Result(property = "screenShot",column = "id",many = @Many(select = "com.oranth.applicationmarket.mapper.ApkScreenshotMapper.listScreenshot"))
            })
    List<ResultData> rSelectAll();

/*    @Select("select is_forbidden as isForbidden,id as id from apk where id = 1")
    ResultData getForbidden();*/

    @SelectProvider(value = ResponseDataProvider.class,method = "selectByTypeId")
    @Results({
            @Result(column="id",property = "id"),
            @Result(property = "screenShot",column = "id",many = @Many(select = "com.oranth.applicationmarket.mapper.ApkScreenshotMapper.listScreenshot"))
    })
    List<ResultData>selectByTypeId(@Param("typeId")int typeId, @Param("begin")int begin, @Param("offset")int offset);

    @SelectProvider(value = ResponseDataProvider.class,method = "selectByTypeIdAd")
    @Results({
            @Result(column="id",property = "id"),
            @Result(property = "screenShot",column = "id",many = @Many(select = "com.oranth.applicationmarket.mapper.ApkScreenshotMapper.listScreenshot"))
    })
    List<ResultData>selectByTypeIdAd(@Param("typeId")int typeId, @Param("begin")int begin, @Param("offset")int offset);

    @SelectProvider(value = ResponseDataProvider.class,method = "selectByTypeIdCount")
     Integer selectByTypeIdCount(@Param("typeId")int typeId);

    @SelectProvider(value = ResponseDataProvider.class,method = "selectByTypeIdCountAdmin")
    Integer selectByTypeIdCountAd(@Param("typeId")int typeId);


    @Select("SELECT at.type_name as type,a.is_forbidden as isForbidden,a.id as id,a.label,a.pkg_name,a.cls_name,a.file_size,a.des,a.version_code,a.version_name,a.icon,a.file_url,a.news,ac.download_num\n" +
            " FROM apk_type at LEFT JOIN apk a ON at.id = a.type_id LEFT JOIN apk_count ac ON a.id = ac.apk_id limit #{begin},#{offset}")
    @Results({
            @Result(column="id",property = "id"),
            @Result(property = "screenShot",column = "id",many = @Many(select = "com.oranth.applicationmarket.mapper.ApkScreenshotMapper.listScreenshot"))
    })
    List<ResultData>selectAll(@Param("begin")int begin, @Param("offset")int offset);


    @Select("SELECT a.is_forbidden as isForbidden,a.type_id as type,a.id as id,a.label,a.pkg_name,a.cls_name,a.file_size,a.des,a.version_code,a.version_name,a.icon,a.file_url,a.news,ac.download_num FROM apk a left join apk_count ac on a.id = ac.apk_id where a.id = #{apkId}")
    @Results({
            @Result(column="id",property = "id"),
            @Result(property = "screenShot",column = "id",many = @Many(select = "com.oranth.applicationmarket.mapper.ApkScreenshotMapper.listScreenshot"))
    })
    ResultData selectById(@Param("apkId")int apkId);
}
