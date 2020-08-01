package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.entity.AppRecommend;
import com.oranth.applicationmarket.entity.RecommendParam;
import com.oranth.applicationmarket.entity.custom.ApkRecommendList;
import com.oranth.applicationmarket.entity.custom.ResultData;
import com.oranth.applicationmarket.service.IApkService;
import com.oranth.applicationmarket.service.IAppRecommendService;
import com.oranth.applicationmarket.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐表 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
//@ApiIgnore
@RestController
@RequestMapping("/app-recommend")
@Api(value = "推荐操作类", description = "推荐操作类")
public class AppRecommendController {

    @Autowired
    IAppRecommendService appRecommendService;

    @PostMapping("/save")
    public String save(
            @ApiParam(name = "index_code", value = "界面标识") @RequestParam("index_code") Integer indexCode,
            @ApiParam(name = "sort_order", value = "界面位置") @RequestParam("sort_order") Integer sortOrder,
            @ApiParam(name = "cover", value = "封面logo") @RequestParam("cover") String cover,
            @ApiParam(name = "sn", value = "界面标识") @RequestParam("sn") Long sn,
            @ApiParam(name = "token", value = "令牌") @RequestParam("token") String token
    ) {
        AppRecommend appRecommend = new AppRecommend();
        //       appRecommend.setCover(cover);
//        appRecommend.setIndexCode(indexCode);
        appRecommend.setSortOrder(sortOrder);
        //       appRecommend.setSn(sn);
        //boolean b = appRecommendService.save(appRecommend);
        //return b+"";
        return null;
    }

    @PostMapping("/save/list")
    public String saveList(
            @ApiParam(name = "data", value = "数据") @RequestParam("data") String data,
            @ApiParam(name = "token", value = "令牌") @RequestParam(value = "token", required = false) String token
    ) {
        int i = appRecommendService.savaList(Util.ajax_decode(data));
        return "";
    }

    @PostMapping("/update")
    public String update(
            @ApiParam(name = "data", value = "数据") @RequestParam("data") String data,
            @ApiParam(name = "token", value = "令牌") @RequestParam(value = "token", required = false) String token
    ) {
        int i = appRecommendService.updateByCat(Util.ajax_decode(data));
        return "";
    }


    @Autowired
    RecommendParam recommendParam;

    @Autowired
    IApkService apkService;

    @PostMapping("/query")
    public String select() {
        List<ResultData> list = apkService.queryRecommend();
        Map<String, Object> map = new HashMap<>();
        map.put("recommendParam", recommendParam);
        map.put("resultData", list);
        return JSON.toJSONString(map);
    }

    @PostMapping("query1")
    public String selectAll() {
        List<ResultData> list = apkService.queryRecommend();
        Map<String, Object> map = new HashMap<>();
        map.put("recommendParam", recommendParam);
        map.put("resultData", list);
        return JSON.toJSONString(map);
    }

    /**
     * 根据分类获取全部apk推荐信息
     *
     * @return
     */
    @PostMapping("/query2")
    public String selectAllByCategory() {
        List<ApkRecommendList> apkRecommendLists = appRecommendService.getApkRecommendByCategory();
        return JSON.toJSONString(apkRecommendLists);
    }

    /**
     * 根据id获取单个apk推荐信息
     *
     * @param id
     * @return
     */
    @ApiIgnore
    @GetMapping("/query/{id}")
    public String selectAppRecommendById(@ApiParam(name = "id", value = "路径上的id") @PathVariable(value = "id") Integer id) {
        AppRecommend appRecommend = appRecommendService.getAppRecommendById(id);
        return JSON.toJSONString(appRecommend);
    }

    /**
     * 对apk推荐信息修改或者新增apk推荐信息
     * @param id
     * @param category
     * @param sortOrder
     * @param apkId
     * @param apkImg
     * @return
     */
    @ApiIgnore
    @PostMapping("/update1")
    public String update1(
            @ApiParam(name = "id", value = "推荐id") @RequestParam(value = "id") Integer id,
            @ApiParam(name = "category", value = "apk分类") @RequestParam(value = "category") Integer category,
            @ApiParam(name = "sortOrder", value = "展示位置") @RequestParam(value = "sortOrder") Integer sortOrder,
            @ApiParam(name = "apkId", value = "apk id") @RequestParam(value = "apkId") Integer apkId,
            @ApiParam(name = "apkImg", value = "展示图片") @RequestParam(value = "apkImg") String apkImg) {
        AppRecommend appRecommend=new AppRecommend(id,category,sortOrder,apkId,apkImg);
        boolean flat=appRecommendService.editAppRecommend(appRecommend);
        return ""+flat;
    }

    /**
     * 删除指定坑位的apk推荐信息
     * @param id
     * @return
     */
    @ApiIgnore
    @PostMapping("update/{id}")
    public String updateById(@ApiParam(name = "id", value = "推荐id") @PathVariable(value = "id") Integer id){
        AppRecommend appRecommend = appRecommendService.getAppRecommendById(id);
        appRecommend.setApkId(null);
        appRecommend.setImg("null");
        boolean flat=appRecommendService.editAppRecommend(appRecommend);
        return ""+flat;
    }
}
