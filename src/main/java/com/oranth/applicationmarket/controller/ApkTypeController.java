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
	@ApiOperation(value = "查询指定分类信息",notes = "根据分类id查询指定的分类信息")
	@GetMapping("/query/{id}")
	public String getApkTypeById(@ApiParam(name = "id",value = "分类id")@PathVariable (value="id")Integer id){
		ApkType apkType=iApkTypeService.getApkTypeById(id);
		return JSON.toJSONString(apkType);
	}

	/**
	 * 获取所有apk分类
	 * @return
	 */
	@ApiOperation(value = "获取全部分类信息")
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
	@ApiOperation(value = "添加分类信息")
	@PostMapping("/save")
	public String addApkType(@ApiParam(name = "typeName",value = "分类名称")@RequestParam(value="typeName") String typeName){
		ApkType apkType=new ApkType(typeName,0);
		Boolean flat=iApkTypeService.addApkType(apkType);
		return ""+flat;
	}

	/**
	 * 删除指定的apk分类
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除分类信息",notes = "根据分类id删除分类信息")
	@PostMapping("/del/{id}")
	public String delApkType(@ApiParam(name = "id",value = "分类id")@PathVariable (value="id") Integer id){
		Boolean flat=iApkTypeService.delApkType(id);
		return ""+flat;
	}

	/**
	 * 修改分类信息
	 * @return
	 */
	@ApiOperation(value = "修改分类信息")
	@PostMapping("/edit")
	public String editApkType(@ApiParam(name = "id",value = "分类id")@RequestParam(value="id") Integer id,@ApiParam(name = "typeName",value = "分类名称")@RequestParam(value="typeName") String typeName,@ApiParam(name = "pid",value = "分类层级")@RequestParam(value="pid") Integer pid){
		ApkType apkType=new ApkType(id,typeName,pid);
		boolean flat=iApkTypeService.editApkType(apkType);
		return ""+flat;
	}
}
