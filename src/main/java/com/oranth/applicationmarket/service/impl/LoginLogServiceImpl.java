package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.LoginLog;
import com.oranth.applicationmarket.mapper.LoginLogMapper;
import com.oranth.applicationmarket.service.ILoginLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员操作记录表 服务实现类
 * </p>
 *
 * @author linsiteng
 * @since 2020-04-21
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
