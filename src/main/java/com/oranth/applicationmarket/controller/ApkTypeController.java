package com.oranth.applicationmarket.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.entity.ApkType;
import com.oranth.applicationmarket.service.IApkTypeService;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * apk分类表 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
@ApiIgnore
@RestController
@RequestMapping("/apkType")
@Api(value = "apk分类",description = "apk分类")
public class ApkTypeController {
	
	@Autowired
	IApkTypeService iApkTypeService;

	/**
	 * 获取所有父分类
	 * @return
	 */
	@ApiOperation(value = "获取apk所有类别，pid=0 父分类，其他 细分类")
	@RequestMapping(value={"/apkTypeByPid"},method = {RequestMethod.GET,RequestMethod.POST})
	public String getFatherApkType(){
		List<ApkType> list = (List<ApkType>) iApkTypeService.getAll();
		List<ApkType> apkTypeList=new ArrayList<>();
		for(ApkType apkType:list){
			if(apkType.getPid()==0){
				apkTypeList.add(apkType);
			}
		}
		return JSON.toJSONString(apkTypeList);
	}

	/**
	 * 根据id查询分类
	 * @param id
	 * @return
	 */
	@GetMapping("/query/{id}")
	public String getApkTypeById(@PathVariable (value="id")Integer id){
		ApkType apkType=iApkTypeService.getApkTypeById(id);
		return JSON.toJSONString(apkType);
	}

	/**
	 * 获取所有apk分类
	 * @return
	 */
	@RequestMapping("/allApkType")
	public String getAllApkType(){
		List<ApkType> apkTypeList=iApkTypeService.getAll();
		return JSON.toJSONString(apkTypeList);
	}

	/**
	 * 添加apk分类
	 * @param typeName
	 * @return
	 */
	@PostMapping("/save")
	public String addApkType(@RequestParam(value="typeName") String typeName){
		ApkType apkType=new ApkType(typeName,0);
		Boolean flat=iApkTypeService.addApkType(apkType);
		return ""+flat;
	}

	/**
	 * 删除指定的apk分类
	 * @param id
	 * @return
	 */
	@PostMapping("/del/{id}")
	public String delApkType(@PathVariable (value="id") Integer id){
		Boolean flat=iApkTypeService.delApkType(id);
		return ""+flat;
	}

	/**
	 * 修改分类信息
	 * @return
	 */
	@PostMapping("/edit")
	public String editApkType(@RequestParam(value="id") Integer id,@RequestParam(value="typeName") String typeName,@RequestParam(value="pid") Integer pid){
		ApkType apkType=new ApkType(id,typeName,pid);
		boolean flat=iApkTypeService.editApkType(apkType);
		return ""+flat;
	}
}
