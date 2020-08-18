package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.oranth.applicationmarket.entity.ActiveApkComment;
import com.oranth.applicationmarket.entity.ApkComment;
import com.oranth.applicationmarket.service.IApkCommentService;
import com.oranth.applicationmarket.service.IApkCountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.json.Json;

import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * apk评论表 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/comment")
@Api(value = "APK评价类",description = "apk评价")
public class ApkCommentController {

    @Autowired
    IApkCommentService apkCommentService;

    /**
     * @param token   令牌
     * @param sn
     * @param score   评分
     * @param content
     * @return
     */
    @GetMapping("save")
    @ApiOperation(value = "增加")
    public String save(
            @ApiParam(name = "token", value = "令牌") @RequestParam("token") String token,
            @ApiParam(name = "sn", value = "apk唯一标识") @RequestParam("sn") String sn,
            @ApiParam(name = "score", value = "评分") @RequestParam("score") Integer score,
            @ApiParam(name = "content", value = "内容") @RequestParam("content") String content) {

        //redis 记录平均分

        //       apkCommentService.save(content,sn,score);
        return "";
    }

    @PostMapping("comment")
    @ApiOperation(value = "获取评论内容")
    public String getBySn(@ApiParam(name = "token", value = "令牌") @RequestParam("token") String token,
                          @ApiParam(name = "sn", value = "apk唯一标识") @RequestParam("sn") String sn,
                          @ApiParam(name = "limit", value = "每次显示数量") @RequestParam("limit") Integer limit,
                          @ApiParam(name = "offse", value = "页码") @RequestParam("offse") Integer offse) {
        PageHelper.startPage(offse, limit);
        QueryWrapper<ApkComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sn", sn);
        queryWrapper.orderByDesc("create_time");
        List<ApkComment> apkCommentList = apkCommentService.list(queryWrapper);
        return JSON.toJSONString(apkCommentList);
    }

    /**
     * 为首页准备数据
     *
     * @return
     */
    @GetMapping("/tomain")
    public String readyMain() {
        List<ActiveApkComment> activeApkComments = apkCommentService.getApkCommentByScore();
        return JSON.toJSONString(activeApkComments);
    }

    /**
     * 分页获取apk评论信息
     *
     * @param currentPage 当前页数
     * @param pageSize    每页显示记录数
     * @return
     */
    @GetMapping("/apkcomment/{currentPage}")
    public ActiveApkComment apkCommentPage(@PathVariable("currentPage") Integer currentPage, Integer pageSize) {
        List<ApkComment> apkComments = apkCommentService.getApkCommentByPage(currentPage, 10);
        int wu = apkCommentService.getAll().size();
        int page = (wu % 10 == 0) ? (int) wu / 10 : (int) wu / 10 + 1;
        ActiveApkComment activeApkComment = new ActiveApkComment(page + "", apkComments);
        return activeApkComment;
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @PostMapping("del/{id}")
    public String delApkComment(@PathVariable("id") Integer id) {
        boolean flat = apkCommentService.delApkComment(id);
        return flat + "";
    }

    /**
     * id查询评论
     * @param id
     * @return
     */
    @GetMapping("query/{id}")
    public ApkComment queryApkCommentById(@PathVariable("id") Integer id) {
        ApkComment  apkComment= apkCommentService.getApkCommentById(id);
        return  apkComment;
    }
}
