package com.oranth.applicationmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oranth.applicationmarket.entity.Apk;
import com.oranth.applicationmarket.entity.State;
import com.oranth.applicationmarket.mapper.ApkMapper;
import com.oranth.applicationmarket.mapper.StateMapper;
import com.oranth.applicationmarket.service.IStateService;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl extends ServiceImpl<StateMapper, State> implements IStateService {
}
