package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.InstallLog;
import com.oranth.applicationmarket.mapper.InstallLogMapper;
import com.oranth.applicationmarket.service.IInstallLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用商城apk安装记录 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class InstallLogServiceImpl extends ServiceImpl<InstallLogMapper, InstallLog> implements IInstallLogService {

}
