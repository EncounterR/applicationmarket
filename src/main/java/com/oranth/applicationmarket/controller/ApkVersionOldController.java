package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.oranth.applicationmarket.entity.ApkComment;
import com.oranth.applicationmarket.entity.ApkVersionOld;
import com.oranth.applicationmarket.service.IApkVersionOldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>
 * apk历史版本表 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-14
 */
@RestController
@RequestMapping("/apk-version-old")
@Api(value = "apk历史版本类",description = "apk历史版本类")
public class ApkVersionOldController {

    @Autowired
    IApkVersionOldService apkVersionOldService;

    @PostMapping("query_By_Sn")
    @ApiOperation("获取历史版本")
    public String selectBySn(
            @ApiParam(name = "token",value = "令牌") @RequestParam("token") String token,
            @ApiParam(name = "sn",value = "apk标识") @RequestParam("sn") String sn ){
        QueryWrapper<ApkVersionOld> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn",sn);
        queryWrapper.orderByDesc("version_code");
        List<ApkVersionOld> apkVersionOldList = apkVersionOldService.list(queryWrapper);
        return JSON.toJSONString(apkVersionOldList);
    }
}
