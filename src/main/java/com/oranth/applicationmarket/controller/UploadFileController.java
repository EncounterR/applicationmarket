package com.oranth.applicationmarket.controller;

import com.alibaba.fastjson.JSON;
import com.oranth.applicationmarket.entity.Apk;
import com.oranth.applicationmarket.exception.pojo.ExceptionInfo;
import com.oranth.applicationmarket.utils.DateUtil;
import com.oranth.applicationmarket.utils.ExecuteCommand;
import com.oranth.applicationmarket.utils.ParsingApkUtil;
import com.oranth.applicationmarket.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController()
@RequestMapping("/upload/")
@Api(value = "上传文件操作类",description = "上传文件操作类")
public class UploadFileController {
	private final static String APK_ICON_PATH = "D:\\javaDev\\tomcat\\apache-tomcat-8.5.45\\webapps\\ApplicationMarket\\icon\\";
	private final static String APK_SCREENSHOT_PATH = "D:\\javaDev\\tomcat\\apache-tomcat-8.5.45\\webapps\\ApplicationMarket\\screenshot\\";
	private final static String APK_PATH = "D:\\javaDev\\tomcat\\apache-tomcat-8.5.45\\webapps\\ApplicationMarket\\apk\\";
	/*private final static String APK_ICON_HTTP_URL = "http://192.168.199.192:8000/ApplicationMarket/icon/";
	private final static String APK_SCREENSHOT_HTTP_URL = "http://192.168.199.192:8000/ApplicationMarket/screenshot/";
	private final static String APK_HTTP_URL = "http://192.168.199.192:8000/ApplicationMarket/apk/";*/
	private final static String APK_ICON_HTTP_URL = "http://127.0.0.1:8000/ApplicationMarket/icon/";
	private final static String APK_SCREENSHOT_HTTP_URL = "http://127.0.0.1:8000/ApplicationMarket/screenshot/";
	private final static String APK_HTTP_URL = "http://127.0.0.1:8000/ApplicationMarket/apk/";

	private static Logger logger = LoggerFactory.getLogger(UploadFileController.class);

	@PostMapping("apk")
	@ApiOperation(value = "单个文件（apk）上传")
	public String uploadApk(@ApiParam(name="apkFile",value = "文件流") @RequestParam("file") MultipartFile apkFile){
		String fileFormatSuffix  = Util.getFileFormatSuffix(apkFile.getOriginalFilename());
		String apkName = DateUtil.getTime()/2 + fileFormatSuffix;
		
		if(!".apk".equalsIgnoreCase(fileFormatSuffix)){
			throw new ExceptionInfo("444","上传文件不是apk","uploadApk()","");
		}
		File file = new File(APK_PATH+apkName);
		BufferedReader br = null;
		try {
			while (true) {
				if (file.exists()) {
					apkName = (DateUtil.getTime() / 2) + fileFormatSuffix;
					file = new File(APK_PATH + apkName);
				}else{
					break;
				}
			}
			apkFile.transferTo(file);
			ParsingApkUtil apkUtil = new ParsingApkUtil();
			Apk apkInfo = apkUtil.getApkInfo(file.getPath());
			String md5 = "";
			Long size = file.length();
			br = new ExecuteCommand().windowsCommand("certutil","-hashfile",APK_PATH+apkName,"MD5");
			String line;
			int i = 0;
		    while ((line = br.readLine()) != null) {
		    	if(i == 1){
		    		md5 += line.replace(" ","");	
		    		break;
		    	}	
		    	++i;
			}
			apkInfo.setFileMd5(md5);
			apkInfo.setFileSize(size.intValue());
			apkInfo.setFileUrl(APK_HTTP_URL+file.getName());
			logger.debug(apkInfo.toString());
			return JSON.toJSONString(apkInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return "";
	}
	@PostMapping("imgs")
	@ApiOperation(value = "多个图片文件（img）上传")
	public String uploadImgs(@RequestParam("files") MultipartFile[] files){
		List<String> listPath = new ArrayList<>();
		System.out.println("------------------------------------------");
		try {
			for(MultipartFile mtfile: files){
				String fileFormatSuffix  = Util.getFileFormatSuffix(mtfile.getOriginalFilename());
				File file = new File(APK_SCREENSHOT_PATH +"/"+ DateUtil.getTime()/2+fileFormatSuffix);
				listPath.add(APK_SCREENSHOT_HTTP_URL+file.getName());
				System.out.println("------------------------------------------"+listPath);
				while (true) {
					if (!file.exists()) {
						if (!file.isDirectory()) {
							mtfile.transferTo(file);						
						/*String path = ficon.getAbsolutePath();
						System.out.println(path);							
							ficon.createNewFile();	*/
							break;
						}
					}else{
						file = new File(APK_SCREENSHOT_PATH +"/"+ DateUtil.getTime()/2+fileFormatSuffix);
					}
				}
			}
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//保存文件
		return JSON.toJSONString(listPath);
	}
	@PostMapping("img")
	@ApiOperation(value = "单个图片文件（img）上传")
	public String uploadImg(@RequestParam("file") MultipartFile imgFile){
		try {
			String fileFormatSuffix  = Util.getFileFormatSuffix(imgFile.getOriginalFilename());
			File file = new File(APK_ICON_PATH+ DateUtil.getTime()/2+fileFormatSuffix);
			while (true) {
				if (!file.exists()) {
					imgFile.transferTo(file);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("url", APK_ICON_HTTP_URL + file.getName());
					return JSON.toJSONString(map);
				}else{
					file = new File(APK_ICON_PATH+ DateUtil.getTime()/2+fileFormatSuffix);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "";
	}


	@PostMapping("shootimg")
	@ApiOperation(value = "单个图片文件（img）上传")
	public String uploadShootImg(@RequestParam("file") MultipartFile imgFile){
		try {
			String fileFormatSuffix  = Util.getFileFormatSuffix(imgFile.getOriginalFilename());
			File file = new File(APK_SCREENSHOT_PATH+ DateUtil.getTime()/2+fileFormatSuffix);
			while (true) {
				if (!file.exists()) {
					imgFile.transferTo(file);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("url", APK_SCREENSHOT_HTTP_URL + file.getName());
					return JSON.toJSONString(map);
				}else{
					file = new File(APK_SCREENSHOT_PATH+ DateUtil.getTime()/2+fileFormatSuffix);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
