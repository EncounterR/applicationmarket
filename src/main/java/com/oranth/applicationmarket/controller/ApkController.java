package com.oranth.applicationmarket.controller;


import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.cache.MybatisRedisCache;
import com.oranth.applicationmarket.entity.*;
import com.oranth.applicationmarket.entity.custom.ApkDet;
import com.oranth.applicationmarket.entity.custom.ResultData;
import com.oranth.applicationmarket.exception.pojo.ExceptionInfo;
import com.oranth.applicationmarket.service.IApkScreenshotService;
import com.oranth.applicationmarket.service.IApkService;
import com.oranth.applicationmarket.service.IApkTypeService;
import com.oranth.applicationmarket.utils.DateUtil;
import com.oranth.applicationmarket.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * apk表 前端控制器
 * </p>
 *
 * @author linsiteng
 * @since 2019-11-13
 */
@Controller
@RequestMapping("/core")
@Api(value = "APK接口类",description = "APK接口类")
public class ApkController {

	private final Integer creator_id = 1;

	private Logger logger = LoggerFactory.getLogger(ApkController.class);
	@Autowired
	IApkService apkService;
	@Autowired
	IApkScreenshotService apkScreenshotService;
	@Autowired
	IApkTypeService apkTypeService;


	@GetMapping("/all")
	public @ResponseBody String getAll(){
		List<Apk> apkList=apkService.getAll();
		return JSON.toJSONString(apkList);
	}

	@GetMapping("/query1/{id}")
	public @ResponseBody ApkDet apkDet(@ApiParam(name = "id",value = "路径上的id")@PathVariable(value="id") Integer id){
		Apk apk=apkService.queryApkById(id);
		List<ApkScreenshot> apkScreenshotList=apkScreenshotService.getApkScreenshotByApkid(id);
		ApkDet apkDet=new ApkDet(apk ,apkScreenshotList);
		return apkDet;
	}

	/**
	 * 获取全部父分类
	 * @return
	 */
	@GetMapping("/apktype")
	public @ResponseBody String getApkType(){
		List<ApkType> apkTypeList=apkTypeService.getFatherApkType();
		return JSON.toJSONString(apkTypeList);
	}

	/**
	 * 跳转到apk修改页面
	 * @return
	 */
	@RequestMapping("/update/{id}")
	public ModelAndView apkEdit(@ApiParam(name = "id",value = "路径上的id")@PathVariable(value="id") Integer id, HttpSession session){
		ModelAndView mv=new ModelAndView("apkEdit");
		Apk apk=apkService.queryApkById(id);
		List<ApkScreenshot> apkScreenshotList=apkScreenshotService.getApkScreenshotByApkid(apk.getId());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("=============="+df.format(DateUtil.asDate(apk.getCreateTime())));
		mv.addObject("createTime",df.format(DateUtil.asDate(apk.getCreateTime())));
		mv.addObject("editTime",df.format(DateUtil.asDate(apk.getEditTime())));
		mv.addObject("updateTime",df.format(DateUtil.asDate(apk.getUpdateTime())));
		mv.addObject("apk",apk);
		mv.addObject("apkScreenshots",apkScreenshotList);
		List<ApkType> apkTypeList=apkTypeService.getFatherApkType();
		System.out.println("==========================="+apkTypeList);
		mv.addObject("apkTypes",apkTypeList);
		return mv;
	}

	@PostMapping("/update1")
	public @ResponseBody String update(
			@ApiParam(name = "id",value = "id")@RequestParam(value="id") Integer id,
			@ApiParam(name = "label",value = "名称")@RequestParam(value="label") String label,
			@ApiParam(name = "alias",value = "别名")@RequestParam(value="alias") String alias,
			@ApiParam(name = "pkgName",value = "包名")@RequestParam(value="pkgName") String pkgName,
			@ApiParam(name = "clsName",value = "类名")@RequestParam(value="clsName") String clsName,
			@ApiParam(name = "fileSize",value = "文件大小")@RequestParam(value="fileSize") Integer fileSize,
			@ApiParam(name = "versionCode",value = "版本号")@RequestParam(value="versionCode") Integer versionCode,
			@ApiParam(name = "versionName",value = "版本名")@RequestParam(value="versionName") String versionName,
			@ApiParam(name = "minSdkVersion",value = "最小SDK")@RequestParam(value="minSdkVersion") String minSdkVersion,
			@ApiParam(name = "targetSdkVersion",value = "当前SDK")@RequestParam(value="targetSdkVersion") String targetSdkVersion,
			@ApiParam(name = "icon",value = "图标路径")@RequestParam(value="icon") String icon,
			@ApiParam(name = "fileUrl",value = "下载路径")@RequestParam(value="fileUrl") String fileUrl	,
			@ApiParam(name = "fileMd5",value = "MD5")@RequestParam(value="fileMd5") String fileMd5,
			@ApiParam(name = "des",value = "简介")@RequestParam(value="des") String des,
			@ApiParam(name = "typeId",value = "类型")@RequestParam(value="typeId") Integer typeId,
			@ApiParam(name = "creatorId",value = "创建人id")@RequestParam(value="creatorId") Integer creatorId,
			@ApiParam(name = "createTime",value = "创建时间")@RequestParam(value="createTime") String createTime,
			@ApiParam(name = "editTime",value = "修改时间")@RequestParam(value="editTime") String editTime,
			@ApiParam(name = "updateTime",value = "更新时间")@RequestParam(value="updateTime") String updateTime	,
			@ApiParam(name = "news",value = "更新描述")@RequestParam(value="news") String news,
			@ApiParam(name = "isForbidden",value = "当前状态")@RequestParam(value="isForbidden") Integer isForbidden) throws ParseException {
		Apk apk=new Apk();
		apk.setId(id);
		apk.setLabel(label);
		apk.setAlias(alias);
		apk.setPkgName(pkgName);
		apk.setClsName(clsName);
		apk.setFileSize(fileSize);
		apk.setVersionCode(versionCode);
		apk.setVersionName(versionName);
		apk.setMinSdkVersion(minSdkVersion);
		apk.setTargetSdkVersion(targetSdkVersion);
		apk.setIcon(icon);
		apk.setFileUrl(fileUrl);
		apk.setFileMd5(fileMd5);
		apk.setDes(des);
		apk.setTypeId(typeId);
		apk.setCreatorId(creatorId);
		apk.setNews(news);
		apk.setIsForbidden(isForbidden);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		apk.setCreateTime(DateUtil.asLocalDateTime(sf.parse(createTime)));
		apk.setEditTime(DateUtil.asLocalDateTime(new Date()));
		apk.setUpdateTime(DateUtil.asLocalDateTime(sf.parse(updateTime)));
		boolean b=apkService.editApk(apk);
		return ""+b;
	}

	@RequestMapping(value = "/save",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String save(
			@RequestParam(value="apkInfo") String jsonApkInfo,
			@RequestParam(value="screenshot") String jsonScreenshot
	){
		try{
			System.out.println("====="+jsonApkInfo+"====="+jsonScreenshot);
			Apk apk = new Apk();
			apk.setCreatorId(creator_id);
			apk.setIsForbidden(1);
			boolean type = apkService.saveApk(Util.ajax_decode(jsonApkInfo),Util.ajax_decode(jsonScreenshot));
			return "1";
		}catch (Exception e){
			e.printStackTrace();
			logger.debug(e.toString());
			throw new ExceptionInfo("0000","参数为空！||参数错误！","controller()","");
		}
	}
	@ApiParam
	@RequestMapping(value = "/update",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String update(
			@RequestParam(value="apkInfo") String jsonApkInfo,
			@RequestParam(value="screenshot") String jsonScreenshot
	){
//		Apk apk = new Apk();
//		apk.setCreatorId(creator_id);
		boolean type = apkService.updateApk(Util.ajax_decode(jsonApkInfo),Util.ajax_decode(jsonScreenshot));
		return type+"";
	}

	@ApiParam
	@RequestMapping(value = "/forbidden/{id}",method = {RequestMethod.GET})
	@ResponseBody
	public String update(@PathVariable(value = "id") Integer id,@RequestParam("offon") int isForbidden){
		Apk apk=new Apk();
		apk.setId(id);
		apk.setIsForbidden(isForbidden);
		//boolean b = apkService.updateById(apk);
//		new MybatisRedisCache("").clear();
		//return b+"";
		return null;
	}

	@ApiOperation("根据apk类型分页查询所有数据")
	@GetMapping("/query/type")
	@ResponseBody
	public String queryByTypeAd(
			@ApiParam(name = "type",value = "类型")@RequestParam(value="type",required = false) String type,
			@ApiParam(name = "page",value = "页码")@RequestParam(value="page") int page,
			@ApiParam(name = "offset",value = "每页数量")@RequestParam(value="offset") int offset,
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
		String resultData = apkService.queryByType(type,page,offset);
		return resultData;
	}

	@ApiOperation("根据apk类型分页查询所有数据")
	@GetMapping("/ad/query/type")
	@ResponseBody
	public String queryByType(
			@ApiParam(name = "type",value = "类型")@RequestParam(value="type",required = false) String type,
			@ApiParam(name = "page",value = "页码")@RequestParam(value="page") int page,
			@ApiParam(name = "offset",value = "每页数量")@RequestParam(value="offset") int offset,
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
		String resultData = apkService.queryByTypeAd(type,page,offset);
		return resultData;
	}


	@ApiOperation("根据apk类型分页查询所有数据量")
	@GetMapping("/query/type/count")
	@ResponseBody
	public String queryByTypeCount(
			@ApiParam(name = "type",value = "类型")@RequestParam(value="type",required = false) String type,
			@ApiParam(name = "offset",value = "每页数量")@RequestParam(value="offset") int offset,
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
		Integer resultData = apkService.queryByTypeCount(type,offset);
		return resultData +"";
	}

	@ApiOperation("根据apk类型分页查询所有数据量")
	@GetMapping("/ad/query/type/count")
	@ResponseBody
	public String queryByTypeCountAd(
			@ApiParam(name = "type",value = "类型")@RequestParam(value="type",required = false) String type,
			@ApiParam(name = "offset",value = "每页数量")@RequestParam(value="offset") int offset,
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
		Integer resultData = apkService.queryByTypeCountAd(type,offset);
		return resultData +"";
	}

	@ApiOperation("删除apk")
	@GetMapping("/del/{id}")
	@ResponseBody
	public String del(
			@ApiParam(name = "id",value = "apkId")@PathVariable(value="id") Integer id,
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
		try {
			Integer resultData = apkService.del(id);
			return resultData +"";
		}catch (Exception e){
			return "0";
		}

	}
/*	@ApiOperation("根据apk类型分页查询所有数据")
	@GetMapping("/query/all")
	@ResponseBody
	public String query(
			@ApiParam(name = "page",value = "页码")@RequestParam(value="page") int page,
			@ApiParam(name = "offset",value = "每页数量")@RequestParam(value="offset") int offset,
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
		String resultData = apkService.queryByType(null,page,offset);
		return resultData;
	}*/


	@ApiOperation("查询推荐表所有数据")
	@GetMapping("/query/recommend")
	@ResponseBody
	public String query(
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
		System.out.println("-----------token"+token);
		List<ResultData> list = apkService.queryRecommend();
		logger.debug(JSON.toJSONString(list));
		return JSON.toJSONString(list);
	}

	@ApiOperation("查询单个数据")
	@GetMapping("/query/{id}")
	@ResponseBody
	public String queryById(
			@ApiParam(name = "id",value = "apkId")@PathVariable(value="id") Integer id,
			@ApiParam(name = "token",value = "token")@RequestParam(value="token",required = false) String token
	){
//		String resultData = apkService.queryRecommend();
//		ModelAndView mode = new ModelAndView(new RedirectView("http://127.0.0.1:8848/oranth_application_market/apkupdate.html"));
/*		try {
			mode.addObject("name","张三".getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
//		mode.setViewName();
		String resultDate = apkService.queryById(id);
		return resultDate;
	}

	/**
	 * 给main页面的图表查询数据
	 * @return
	 */
	@GetMapping("/tomain")
	public @ResponseBody String readyMain(){
		List<ApkType> apkTypeList=apkTypeService.getFatherApkType();
		List<ActiveApk> activeApks=new ArrayList<>();
		for(ApkType apkType:apkTypeList){
			List<Apk> apkList=apkService.getApkByTypeId(apkType.getId());
			activeApks.add(new ActiveApk(apkType,apkList));
		}
		return JSON.toJSONString(activeApks);
	}
}
