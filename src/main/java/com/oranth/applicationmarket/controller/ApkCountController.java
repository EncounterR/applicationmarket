package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.entity.ApkCount;
import com.oranth.applicationmarket.service.IApkCountService;
import com.oranth.applicationmarket.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-26
 */
@RestController
@RequestMapping("/click")
@Api(value = "apk点击记录",description = "apk点击记录")
public class ApkCountController {

    @Autowired
    IApkCountService apkCountService;
    /**
     * 下载 点击排序 -》 用redis有序集合 sorted set
     * @param id
     * @return
     */
    @ApiOperation(value = "记录")
    @RequestMapping(value = "/record",method = RequestMethod.POST)
    public int record(@ApiParam(name = "id",value = "apk唯一标识")@RequestParam("id") int id){
//        int clickSum = 0;
//        if(!Util.isEmpty(click)){
//            clickSum = 1;
//        }else{
//            return JSON.toJSONString( "参数为空");
//        }

        return apkCountService.updataBySn(id,0,1);
    }
}
