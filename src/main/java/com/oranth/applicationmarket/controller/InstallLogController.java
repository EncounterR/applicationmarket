package com.oranth.applicationmarket.controller;


import com.oranth.applicationmarket.entity.InstallLog;
import com.oranth.applicationmarket.service.IInstallLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 安装记录 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
@Api(value = "应用商店安装记录",description = "应用商店安装记录")
@RestController
@RequestMapping("/install")
public class InstallLogController {

    @Autowired
    IInstallLogService installLogService;

    @ApiOperation("记录应用商店安装设备信息")
    @PostMapping("/appstore")
    public boolean record(
            @ApiParam(name = "postcode",value = "行政区代码")@RequestParam(value = "postcode",required = false) String postcode,
            @ApiParam(name = "device",value = "芯片 device")@RequestParam("device") String device,
            @ApiParam(name = "model",value = "型号")@RequestParam("model") String model,
            @ApiParam(name = "ip",value = "ip ##http://pv.sohu.com/cityjson?ie=utf-8")@RequestParam("ip") String ip,
            @ApiParam(name = "id",value = "id")@RequestParam(value = "id",required = false) int id
    ){
        InstallLog installLog = new InstallLog();
        installLog.setDevice(device);
        installLog.setModel(model);
        installLog.setIp(ip);
        installLogService.save(installLog);
        return false;
    }
}
