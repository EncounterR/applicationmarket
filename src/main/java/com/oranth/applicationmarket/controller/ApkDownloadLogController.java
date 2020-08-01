package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.entity.ActiveApkDownloadLog;
import com.oranth.applicationmarket.entity.ApkDownloadLog;
import com.oranth.applicationmarket.service.IApkCountService;
import com.oranth.applicationmarket.service.IApkDownloadLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import java.util.List;

/**
 * <p>
 * 下载记录表 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/apk-download-log")
@Api(value = "下载记录",description = "下载记录")
public class ApkDownloadLogController {

     private Logger logger = LoggerFactory.getLogger(ApkDownloadLogController.class);
    @Autowired
    IApkDownloadLogService apkDownloadLogService;

    /**
     *
     * @param postcode
     * @param device
     * @param ip
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(value = "down_record",method = {RequestMethod.POST})
    @ApiOperation(value = "下载apk记录")
    public int dawnLog(
           @ApiParam(name = "postcode",value = "行政区代码 可为空")@RequestParam(value = "postcode",required = false) String postcode,
           @ApiParam(name = "device",value = "芯片 device")@RequestParam("device") String device,
           @ApiParam(name = "model",value = "型号")@RequestParam("model") String model,
           @ApiParam(name = "ip",value = "ip ##http://pv.sohu.com/cityjson?ie=utf-8")@RequestParam(value = "ip",required = false) String ip,
           @ApiParam(name = "id",value = "id")@RequestParam("id") int id,
           @ApiParam(name = "token",value = "令牌")@RequestParam(value = "token",required = false) String token
            ){
        ApkDownloadLog apkDownloadLog = new ApkDownloadLog();
 //       apkDownloadLog.setIp(ip);
        apkDownloadLog.setDevice(device);
        apkDownloadLog.setModel(model);
        logger.debug("===="+id);
        apkDownloadLog.setApkId(id);
 //       apkDownloadLog.setSn(sn);
        int b = apkDownloadLogService.downStat(apkDownloadLog);
        return b;
    }

    /**
     * 给首页图标提供数据
     * @return
     */
    @GetMapping("tomain")
    public String readyMain(){
        List<ActiveApkDownloadLog> activeApkDownloadLogs=apkDownloadLogService.getApkDownloadLogByDownloadTime();
        return JSON.toJSONString(activeApkDownloadLogs);
    }
}
