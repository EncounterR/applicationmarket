package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.entity.ApkVersionOld;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * apk历史版本表 Mapper 接口
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
public interface ApkVersionOldMapper extends BaseMapper<ApkVersionOld> {

    @Select("insert into apk_version_old select 0,id,version_code,version_name,file_url from apk where id = #{apkId}")
    Object backupOldApk(@Param("apkId") int apkId);
}
