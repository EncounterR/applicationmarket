var vm_apk_basi = new Vue({
	el: "#pro-param",
	data: {
		apk_name: "cpuInfo",
		apk_id: "",
		apk_type: '1',
		alias: "test,test2,test3",
		icon: "",
		uploadIconRate: "",
		file_url: "",
		uploadFileRate: false,
		description: "测试，测试，测试，测试，测试，测试",
		news_des: "更新说明",
		description_size: 0,
		apk_subclassification: "",
		icon_width: "",
		file_url_width: "",
		subclassification_html: "<option value=''>详细分类</option><option value='1'>影视</option><option value='2'>游戏</option><option value='3'>教育</option><option value='4'>软件</option>", //获取细分类内容
		file_name: "",
		file_md5: "",
		file_size: "",
		file_pkg: "",
		file_version: "",
		file_versionName: "",
		file_minSdk: "",
		file_targetSdk: "",
		is_hide: false
	},
	watch: {
		apk_type(newval, oldval) {
			this.apk_type = newval;
			console.log(newval+"=="+oldval);
			console.log(this.apk_type);
			//						
			/* if (newval == '0' || newval == '') {
				console.log(newval);
				this.subclassification_html = "";
			} else {
				var cc = "";
				axios.get("type/bypid?pid=" + newval)
					.then(response => {
						var json = response.data;
						console.log(json);
						var htmlContent = "";
						$.each(json, function(index, element) {
							htmlContent += "<option value='" + element.typeId + "'>" + element.typeName + "</option>";
						});
						this.subclassification_html = htmlContent;
						vm_apk_basi.apk_subclassification = json[0].typeId;
						console.log("分类ID: " + json[0].typeId +"->" + this.apk_subclassification);
					})
					.catch(function(error) {
						console.log(error);
					});
			} */
		},
		description(newval) {
			if (newval.length > 50) {
				this.description = newval.substr(0, 50);
			};
			this.description_size = newval.length;
		},
		icon(newval) {
			this.icon_width = getwidth(newval);
		},
		file_url(newval) {
			this.file_url_width = getwidth(this.file_url);
		},
		apk_subclassification(newval){
			this.apk_subclassification = newval;
			console.log("分类ID: " + this.apk_subclassification);
		}
	},
	methods: {
		logoImgButton: function() {
			$("#logoimguoload").click();
		},
		apkUpload: function() {
			$("#logoapkupload").click();
		},
		apkInfoBlock: function() {
			this.is_hide = true;
		},
		apkInfoHide: function() {
			this.is_hide = false;
		},
		logoimguoload: function(e) {
			vm_apk_basi.uploadIconRate = "";
			console.log(e.target.files[0]);
			var formData = new FormData();
			formData.append("file", e.target.files[0]);
			var config = {
				headers: {
					'Content-Type': 'multipart/form-data'
				},
				onUploadProgress: function(e) {
					console.log("进度：");
					console.log(e);
					//属性lengthComputable主要表明总共需要完成的工作量和已经完成的工作是否可以被测量
					//如果lengthComputable为false，就获取不到e.total和e.loaded
					if (e.lengthComputable) {
						var rate = e.loaded / e.total; //已上传的比例
						rate += '00.00'; 
						rate = rate.substring(0, 5);
						if (rate < 1) {
							//这里的进度只能表明文件已经上传到后台，但是后台有没有处理完还不知道
							//因此不能直接显示为100%，不然用户会误以为已经上传完毕，关掉浏览器的话就可能导致上传失败
							//等响应回来时，再将进度设为100%
							vm_apk_basi.uploadIconRate = rate * 100 + '%';
						}
					}
				}
			};
			axios.post("upload/img", formData, config)
				.then(function(data) {
					console.log(data);
					var json = data.data; //后台实际返回的结果
					if (json.url.length > 0) {
						vm_apk_basi.uploadIconRate = '100%';
						vm_apk_basi.icon = json.url;
					} else {
						alert('upload error');
					}

				})
				.catch(function(err) {
					console.log(err);
				});
		},
		logoapkupload: function(el) {
			vm_apk_basi.uploadFileRate = "";
			console.log(el.target.files[0]);
			console.log(el.target.files[0].name);
			/* 						if (el.target.files[0].name.indexOf(".apk") == -1) {
										el.target.files[0].name = "";
										vm_apk_basi.file_url = "";
										alert("不是apk");
										return;
									} */
			var formData = new FormData();
			formData.append("file", el.target.files[0]);
			var config = {
				headers: {
					'Content-Type': 'multipart/form-data'
				},
				onUploadProgress: function(e) {
					console.log("进度：");
					console.log(e);
					//属性lengthComputable主要表明总共需要完成的工作量和已经完成的工作是否可以被测量
					//如果lengthComputable为false，就获取不到e.total和e.loaded
					if (e.lengthComputable) {
						var rate = (e.loaded / e.total) * 100; //已上传的比例
						console.log("进度：" + rate);
						rate = (rate + "").substr(0, 5);
						if (rate < 100) {
							//这里的进度只能表明文件已经上传到后台，但是后台有没有处理完还不知道
							//因此不能直接显示为100%，不然用户会误以为已经上传完毕，关掉浏览器的话就可能导致上传失败
							//等响应回来时，再将进度设为100%
							vm_apk_basi.uploadFileRate = rate + '%';
						}
					}
				}
			};

			axios.post("upload/apk", formData, config)
				.then(function(data) {
					console.log(data);
					var json = data.data; //后台实际返回的结果
					if (json.fileUrl != "") {
						vm_apk_basi.uploadFileRate = 1 * 100 + '%';
						vm_apk_basi.file_url = json.fileUrl;
						console.log(vm_apk_basi.file_url);
					} else {
						alert('upload error');
						return;
					}
					if (json != null) {
						vm_apk_basi.apk_name = vm_apk_basi.file_name = json.label;
						vm_apk_basi.file_md5 = json.fileMd5;
						vm_apk_basi.file_size = json.fileSize;
						vm_apk_basi.file_pkg = json.pkgName;
						vm_apk_basi.file_version = json.versionCode;
						vm_apk_basi.file_versionName = json.versionName;
						vm_apk_basi.file_minSdk = json.minSdkVersion;
						vm_apk_basi.file_targetSdk = json.targetSdkVersion;
					}
				})
				.catch(function(err) {
					vm_apk_basi.file_url = "";
					el.target.name = "";
					console.log(err);
				});
		},
		screenshotupload: function(el) {
			console.log(el.target);
		}
	}
});
 function getParams(key) {
		        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
		        var r = window.location.search.substr(1).match(reg);
		        if (r != null) {
		            return unescape(r[2]);
		         }
		        return null;
		    };

var vm_screenshot = new Vue({
	el: "#screenshot",
	data: {
		add_screenshot_html: "",
		uploadFileRate: "",
		screenshot_index: 1
	},
	methods: {
		btnaddscreenshot: function() {
			$("#screenshotupload").click();
		},
		screenshotupload: function(el) {
			var files = el.target.files;
			var formdata = new FormData();
			formdata.append("files", files[0]);
			formdata.delete("files");
			for (var i = 0; i < files.length; ++i) {
				formdata.append("files", files[i]);
			};
			console.log(formdata);
			var config = {
				headers: {
					'Content-Type': 'multipart/form-data'
				},
				onUploadProgress: function(e) {
					console.log("进度：");
					//属性lengthComputable主要表明总共需要完成的工作量和已经完成的工作是否可以被测量
					//如果lengthComputable为false，就获取不到e.total和e.loaded
					if (e.lengthComputable) {
						var rate = (e.loaded / e.total) * 100; //已上传的比例
						console.log("进度：" + rate);
						rate = (rate + "").substr(0, 5);
						if (rate < 100) {
							//这里的进度只能表明文件已经上传到后台，但是后台有没有处理完还不知道
							//因此不能直接显示为100%，不然用户会误以为已经上传完毕，关掉浏览器的话就可能导致上传失败
							//等响应回来时，再将进度设为100%
							vm_screenshot.uploadFileRate = rate + '%';
						}
					}
				}
			};
			axios.post("upload/imgs", formdata, config)
				.then(resposne => {
					console.log(resposne);
					var json = resposne.data; //后台实际返回的结果	
					var screenshot_img_html = "";
					if (json.length > 0) {
						$.each(json, function(i, elt) {
							$(".prodepictimgdiv").append("<img class=' border-blue proimg'  src='" + elt + "' index='" +
								vm_screenshot.screenshot_index + "' style='border: 1px dotted;width: 100px;height: 80px;'>");
							++vm_screenshot.screenshot_index;
							vm_screenshot.uploadFileRate = '100%';
							console.log(vm_screenshot.screenshot_index);
						});
						proimgbtn();
					}
				})
				.catch(function(err) {
					console.log(err);
				});
		},

	}

});

var getwidth = function(content) {
	var sensor = $('<span style="font-size: 14px;">' + content + '</span>').css({
		display: 'none'
	});
	$('body').append(sensor);
	var width = sensor.width();
	if (width > 600) {
		width = 600;
	}
	return width;
}


var proimgObj;
var proimgbtn = function() {
	$(".proimgdiv img").unbind('click');
	$(".proimgdiv img").click(function() {
		var X = $(this).position().left;
		var Y = $(this).position().top;
		proimgObj = $(this);
		var c = proimgObj.attr("src");
		$("#imgsrcinput").val($(this).attr("src"));
		$("#imgorderinput").val($(this).attr("index"));
		$(".imgInfo").css({
			"top": Y + 50 + "px",
			"left": X + 50 + "px",
			"display": "block"
		});
	});

	$(".prodepictimgdiv img").click(function() {
		var X = $(this).position().left;
		var Y = $(this).position().top;
		proimgObj = $(this);
		$("#imgsrcinput").val($(this).attr("src"));
		$("#imgorderinput").val($(this).attr("index"));
		$(".imgInfo").css({
			"top": Y + 50 + "px",
			"left": X + 50 + "px",
			"display": "block"
		});
	});
};
$("#imgInfobtn-mit").click(function() {
	var src = $("#imgsrcinput").val();
	var index = $("#imgorderinput").val();

	if (src != "")
		proimgObj.attr("src", src);
	var cindex = proimgObj.attr("index");
	if (index != cindex) {
		var tarimg = proimgObj.parent().find("img");
		for (var i = 0; i < tarimg.length; i++) {
			if ($(tarimg[i]).attr("index") == index) {
				$(tarimg[i]).attr("index", cindex);
				break;
			}
		}
	}
	proimgObj.attr("index", index);
	$(".imgInfo").css("display", "none");
	proimgObj = "";
	$("#imgsrcinput").val("");
	$("#imgorderinput").val("");
});
$("#imgInfobtn-cancel").click(function() {
	$(".imgInfo").css("display", "none");
	proimgObj = "";
	$("#imgsrcinput").val("");
	$("#imgorderinput").val("");
});
$("#imgInfobtn-remove").click(function() {
	$(".imgInfo").css("display", "none");
	proimgObj.remove();
	proimgObj = "";
});
$("#back").click(function(){
	window.location.href="apk_list.html";
})
$("#submit").click(function() {
	var dimgPath = new Array();
	var dimg_Param = $("div.prodepictimgdiv img");
	$.each(dimg_Param, function(i, elt) {
		dimgPath[i] = {"imgUrl":$(elt).attr("src"),"sequence":$(elt).attr("index")};
	});
	var formData = new FormData();
 	var cc = ajax_encode(JSON.stringify({
		'id': vm_apk_basi.apk_id,
		'label': vm_apk_basi.apk_name,
		'alias':  vm_apk_basi.alias,
		'typeId': vm_apk_basi.apk_type,
		'icon': vm_apk_basi.icon,
		'fileUrl': vm_apk_basi.file_url,
		'fileMd5': vm_apk_basi.file_md5,
		'fileSize': vm_apk_basi.file_size,
		'des': vm_apk_basi.description,
		'pkgName': vm_apk_basi.file_pkg,
		'versionCode': vm_apk_basi.file_version,
		'versionName': vm_apk_basi.file_versionName,
		'minSdkVersion': vm_apk_basi.file_minSdk,
		'targetSdkVersion': vm_apk_basi.file_targetSdk,
		'news' : vm_apk_basi.news_des
	})); 
	formData.append('apkInfo',cc);
	console.log(vm_apk_basi.icon);
	formData.append('screenshot', ajax_encode(JSON.stringify(dimgPath)));
	var config = {
		headers:{
			'Content-type': 'application/json;charset=UTF-8'
//			'Content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
		}
	}
	axios.post("core/update",formData,config)
	.then(response => {
		alert("成功");
		console.log(response)
	})
	.catch(function(error){
		console.log(error)
	})
});
function strMapToObj(strMap){
		    let obj= Object.create(null);
		    for (let[k,v] of strMap) {
		      obj[k] = v;
		    }
		    return obj;
		  }
function ajax_encode(str){
	if(str != "" && str.length > 0){
	    str = str.replace(/%/g,"{@bai@}");
	    str = str.replace(/ /g,"{@kong@}");
	    str = str.replace(/</g,"{@zuojian@}");
	    str = str.replace(/>/g,"{@youjian@}");
	    str = str.replace(/&/g,"{@and@}");
	    str = str.replace(/\"/g,"{@shuang@}");
	    str = str.replace(/\'/g,"{@dan@}");
	    str = str.replace(/\t/g,"{@tab@}");
	    str = str.replace(/\+/g,"{@jia@}");
	    return str;
	}
}
var init = function(){
	var data = JSON.parse(decodeURI(getParams("data")));
//		vm_apk_basi.apk_id = data.id;
		vm_apk_basi.apk_name = data.label;
		vm_apk_basi.alias = data.label;
		vm_apk_basi.apk_type = getType(data.type);
		vm_apk_basi.icon = data.icon;
		vm_apk_basi.file_url = data.fileUrl;
		vm_apk_basi.file_size = data.fileSize;
		vm_apk_basi.description = data.des;
		vm_apk_basi.file_pkg = data.pkgName;
		console.log(vm_apk_basi.file_pkg);
		vm_apk_basi.file_version = data.versionCode;
		vm_apk_basi.file_versionName = data.versionName;
		vm_apk_basi.news_des = data.news;
		$.each(data.screenShot, function(i, elt) {
			$(".prodepictimgdiv").append("<img class=' border-blue proimg'  src='" + elt + "' index='" +
				vm_screenshot.screenshot_index + "' style='border: 1px dotted;width: 100px;height: 80px;'>");
			++vm_screenshot.screenshot_index;
			console.log(vm_screenshot.screenshot_index);
		});
		proimgbtn();
}
	/**
	 * @param {Object} str<option value="1">video</option>
								<option value="2">other</option>
								<option value="3">game</option>
								<option value="5">tools</option>
	 */
var getType = function(str){
	if(str == 'video'){
		return 1;
	}else if(str == 'other'){
		return 2;
	}else if(str == 'game'){
		return 3;
	}else if(str == 'tools'){
		return 4;
	}
}
init();
