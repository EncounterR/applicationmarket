//import util from util

var offset = 20;
//var page = 1;
var thispage = 1;
var count = 1;
var vm_apklist = new Vue({
	el:  "#pro-param", //注册对象使用{{}}
	data: {
		apk_type : 0,
        apk_type_html: "<option value='0'>type</option><option value='1'>video</option><option value='2'>other</option><option value='3'>game</option><option value='4'>tools</option>"
	},
	watch: {
		apk_type(newval,oldval){
			this.apk_type = newval;
			readyData();
			apksum();
		}
	},
	mounted: function(){
		
	}
		
});
var jsonData = "";
var readyData = function(){
		axios.get("core/all/")
		//axios.get("core/ad/query/type?offset="+offset+"&page="+thispage+"&type="+vm_apklist.apk_type)
		.then(response => {
			jsonData = response.data;
			var dataHtml = "";
			if (jsonData.length > 0){
				$.each(jsonData,function(i,elt){
					var type;
					if(elt.typeId==1){
						type="video";
					}else if (elt.typeId==2){
						type="other";
					}else if (elt.typeId==3){
						type="game";
					}else if (elt.typeId==4){
						type="tools";
					}
					var isForbidden;
					console.log(elt.isForbidden);
					if(elt.isForbidden==1){
						isForbidden="上架";
					}else{
						isForbidden="下架";
					}
					dataHtml += "<tr>";
					dataHtml += "<td>" + elt.id + "</td>";
					dataHtml += "<td>" + elt.label + "</td>";
					dataHtml += "<td>" + elt.pkgName + "</td>";
					dataHtml += "<td>" + elt.versionCode + "</td>";
					dataHtml += "<td>" + type + "</td>";
					dataHtml += "<td>" + isForbidden + "</td>";
					dataHtml += "<td><a onclick='apkDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>详情</a>";
					dataHtml += "<a class='btn btn-default' href='core/update/" + elt.id + "' code_id='" + elt.userId + "'>修改</a>";
					dataHtml += "<a class='btn btn-default buttonstate' id='del' code_id='" + elt.id + "'>删除</a></td>";
					dataHtml += "<tr>";
				});
				$("#table_connect").html(dataHtml);
				createButtonclick();
			}
			console.log(response)
		})
		.catch(function(error){
			console.log(error)
		})
};
$(document).ready(readyData());


var createButtonclick = function(){
	$(".buttonstate").click(function(){
		var id = $(this).attr("id");
		var code_id = $(this).attr("code_id");
		if(id == 'del'){
			axios.get("core/del/"+code_id)
			.then(response => {
				var data = response.data;
				if(data == '0'){
					alert("删除失败!!! apk在推荐位上！！！");
				}else{
					alert("删除成功！！！");
					readyData();
				}
			});
		}else if(id == 'up'){			
			var cc = jsonData[code_id];
			console.log(JSON.stringify(cc));
			window.location.href="apkupdate.html?data="+encodeURI(encodeURI(JSON.stringify(cc)));
		}else{
			if(confirm("是否更改状态")){
				var i= 0;
				if(id == 'X')
					i = 1;
				else if(id == 'S')
					i = 0;
				axios.get("core/forbidden/"+code_id + "?offon="+i)
				.then(response => {
					var data = response.data;
					readyData();
				});
				
			}
		}		
//				alert(id);	
	});		
};

var pagingpPlugin = function(page) {
		var pagenum = parseInt(page);
		var pageul = $("#pagination");
		var pageH = "";
		var pagecount = parseInt(count);
		if(pagecount <1){
			pagecount = 1;
		}
		$(".hover").unbind("click");
		if (pagenum > 1) {	
			$("span.prev").attr("data-page", pagenum - 1);
			$("span.prev").removeClass("is-disabled");
			$("span.prev").removeClass("hover");
			$("span.prev").addClass("hover");
			if (pagenum < pagecount) {						
				$("span.next").attr("data-page", pagenum + 1);
				$("span.next").removeClass("is-disabled");
				$("span.next").removeClass("hover");
				$("span.next").addClass("hover");				
			} else {
				$("span.next").removeClass("hover");
				$("span.next").addClass("is-disabled");
				$("span.next").attr("data-page", pagenum);
			}
		} else {
			if(pagecount > 1){
				$("span.prev").removeClass("hover");
				$("span.prev").addClass("is-disabled");
				$("span.next").attr("data-page", pagenum + 1);
				$("span.prev").attr("data-page", 1);
				$("span.next").removeClass("is-disabled");
				$("span.next").addClass("hover");
			}else{
				$("span.prev").removeClass("hover");
				$("span.prev").addClass("is-disabled");
				$("span.next").removeClass("hover");
				$("span.next").addClass("is-disabled");
			}
		}
		if (0 < pagecount) {
			$(".paging").attr("displa","block");
			if (7 >= pagecount) { //小于7
				if ((pagecount - pagenum) > 2) {
					for (var i = 1; i <= (pagenum + 2); ++i) {
						if (i == pagenum)
							pageH += "<li data-page='" + i + "' class='is-disabled'>" + i + "</li>";
						else
							pageH += "<li data-page='" + i + "' class='hover'>" + i + "</li>";
					}
					pageH += "<li class='omit'>···</li>";
				} else {
					for (var i = 1; i <= pagecount; ++i) {
						if (i == pagenum)
							pageH += "<li data-page='" + i + "' class='is-disabled'>" + i + "</li>";
						else
							pageH += "<li data-page='" + i + "' class='hover'>" + i + "</li>";
					}
				}
			} else { //总页数大于 7
				if (pagenum <= 5) { //当前页小于5
					for (var i = 1; i <= (pagenum + 2); ++i) {
						if (i == pagenum)
							pageH += "<li data-page='" + i + "' class='is-disabled'>" + i + "</li>";
						else
							pageH += "<li data-page='" + i + "' class='hover'>" + i + "</li>";
					}
					pageH += "<li class='omit'>···</li>";
				} else { //当前页大于 5
					pageH += "<li data-page='1' class='hover'>1</li>";
					pageH += "<li data-page='2' class='hover'>2</li>";
					pageH += "<li class='omit'>···</li>";
					if ((pagecount - pagenum) > 2) {
						for (var i = (pagenum - 2); i <= (pagenum + 2); ++i) {
							if (i == pagenum)
								pageH += "<li data-page='" + i + "' class='is-disabled'>" + i + "</li>";
							else
								pageH += "<li data-page='" + i + "' class='hover'>" + i + "</li>";
						}
						pageH += "<li class='omit'>···</li>";
					} else {
						for (var i = (pagenum - 2); i <= pagecount; ++i) {
							if (i == pagenum)
								pageH += "<li data-page='" + i + "' class='is-disabled'>" + i + "</li>";
							else
								pageH += "<li data-page='" + i + "' class='hover'>" + i + "</li>";
						}
					}
				}
			}
		}else{
			$(".paging").attr("displa","none");
		}
		pageul.html(pageH);
		$(".hover").click(function() {
			var page = $(this).attr("data-page");
			console.log(">>>>>>>>>"+page)
			if(page.length > 0){
				thispage = page;				
/*				var data = "num="+num+ "&page="+page;
				thispage = page;*/
//				ajax("../../news/data",data,0,false);
				readyData();
				pagingpPlugin(page);
			}			
		});
	};
var apksum = function(){
	axios.get("core/ad/query/type/count?type="+vm_apklist.apk_type+"&offset="+offset)
	.then(response => {
		var data = response.data;
		count = data;
		console.log(">>>>>>>count"+count);
		pagingpPlugin(1);
	});
}
apksum();

data.creatorId = undefined;
data.createTime = undefined;
data.editTime = undefined;
data.updateTime = undefined;
data.isForbidden=undefined;
function apkDet(id) {
	axios.get("core/query1/"+id)
		.then(response => {
			var data = response.data;
			var type;
			if(data.apk.typeId==1){
				type="video";
			}else if (data.apk.typeId==2){
				type="other";
			}else if (data.apk.typeId==3){
				type="game";
			}else if (data.apk.typeId==4){
				type="tools";
			}
			var apk_isForbidden;
			if(data.apk.isForbidden==1){
				apk_isForbidden="上架";
			}else{
				apk_isForbidden="下架";
			}
			var dataHtml = "";
			dataHtml += "<table class='table table-bordered'>";
			dataHtml += "<tr><td>编号</td><td>" + data.apk.id + "</td></tr>";
			dataHtml += "<tr><td>名称</td><td>" + data.apk.label + "</td></tr>";
			dataHtml += "<tr><td>别名</td><td>" + data.apk.alias + "</td></tr>";
			dataHtml += "<tr><td>包名</td><td>" + data.apk.pkgName + "</td></tr>";
			dataHtml += "<tr><td>类名</td><td>" + data.apk.clsName + "</td></tr>";
			dataHtml += "<tr><td>apk大小</td><td>" + data.apk.fileSize + "</td></tr>";
			dataHtml += "<tr><td>version_code</td><td>" + data.apk.versionCode + "</td></tr>";
			dataHtml += "<tr><td>version</td><td>" + data.apk.versionName + "</td></tr>";
			dataHtml += "<tr><td>min_sdk_version</td><td>" + data.apk.minSdkVersion + "</td></tr>";
			dataHtml += "<tr><td>target_sdk_version</td><td>" + data.apk.targetSdkVersion + "</td></tr>";
			dataHtml += "<tr><td>图标</td><td><div class='gallery cf'><div><img height='100px' width='100px' src='" + data.apk.icon + "'></div></div></td></tr>";
			dataHtml += "<tr><td>apk应用截图</td><td>"
			for(var i=0;i<data.apkScreenshotList.length;i++){
				dataHtml +="<div class='gallery cf'><div><img height='100px' width='200px' src='" + data.apkScreenshotList[i].imgUrl + "'></div></div>";
			}
			dataHtml += "</td></tr>";
			dataHtml += "<tr><td>MD5</td><td>" + data.apk.fileMd5 + "</td></tr>";
			dataHtml += "<tr><td>简介</td><td>" + data.apk.des + "</td></tr>";
			dataHtml += "<tr><td>news</td><td>" + data.apk.news + "</td></tr>";
			dataHtml += "<tr><td>类型</td><td>" + type + "</td></tr>";
			dataHtml += "<tr><td>创建者id</td><td>" + data.apk.creatorId + "</td></tr>";
			dataHtml += "<tr><td>创建时间</td><td>" + data.apk.createTime + "</td></tr>";
			dataHtml += "<tr><td>修改时间</td><td>" + data.apk.editTime + "</td></tr>";
			dataHtml += "<tr><td>更新时间</td><td>" + data.apk.updateTime + "</td></tr>";
			dataHtml += "<tr><td>当前状态</td><td>" + apk_isForbidden + "</td></tr></table>";
			$("#modal_body").html(dataHtml);
		});
}

