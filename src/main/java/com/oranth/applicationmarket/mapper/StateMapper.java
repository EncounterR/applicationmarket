package com.oranth.applicationmarket.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oranth.applicationmarket.entity.State;

/**
 * <p>
 * apk表 Mapper 接口
 * </p>
 * SELECT id,label,pkg_name,cla_name,file_size,version_code,version_name,min_sdk_version,target_sdk_version,icon,file_url,file_md5,
 * des,type_id,creator_id,create_time,edit_time,update_time,news,is_forbidden FROM apk WHERE id=?
 * @author linsiteng
 * @since 2020-04-21
 */
//@CacheNamespace(implementation = MybatisRedisCache.class)
public interface StateMapper extends BaseMapper<State> {


}
