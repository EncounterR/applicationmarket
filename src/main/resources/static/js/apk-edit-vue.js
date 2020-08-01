var vm_apk_basi = new Vue({
    el: "#wuhai",
    data: {

    },
    methods: {
        logoImgButton: function() {
            $("#logoimguoload").click();
        },
        logoimguoload: function(e) {
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
            axios.post("../../upload/shootimg", formData, config)
                .then(resposne => {
                    console.log(resposne);
                    var json = resposne.data;//后台实际返回的结果
                    var screenshot_img_html = "";
                    if (json!=null) {
                        $.each(json, function(i, elt) {
                            screenshot_img_html="<div id='"+elt+"'><img src='"+elt+"' width='150px' height='100px' style='margin-bottom: 10px'><button type='button' class='btn btn-default' onclick='apkScreenshotDel('"+elt+"')' >删除</button></div>";
                            $("#wuhaiqiang").append(screenshot_img_html);
                        });
                    } else {
                        alert('upload error');
                    }

                })
                .catch(function(err) {
                    console.log(err);
                });
        },
    }
});



$("#doEdit").click( function () {
    var imgslist=new Array();
    var imgs_list = $("#wuhai img");
    $.each(imgs_list, function(i, elt) {
        imgslist[i] = {"imgUrl":$(elt).attr("src")};
    });
    console.log(imgslist);
    var data = new FormData();
    var id = document.getElementById("id").value;
    var label = document.getElementById("label").value;
    var pkgName = document.getElementById("pkgName").value;
    var clsName = document.getElementById("clsName").value;
    var alias = document.getElementById("alias").value;
    var fileSize = document.getElementById("fileSize").value;
    var versionCode = document.getElementById("versionCode").value;
    var versionName = document.getElementById("versionName").value;
    var minSdkVersion = document.getElementById("minSdkVersion").value;
    var targetSdkVersion = document.getElementById("targetSdkVersion").value;
    var icon = document.getElementById("icon").value;
    var fileUrl = document.getElementById("fileUrl").value;
    var fileMd5 = document.getElementById("fileMd5").value;
    var des = document.getElementById("des").value;
    var myselect=document.getElementById("typeId");
    var typeId = myselect.options[myselect.selectedIndex].value;
    console.log("typeId----"+typeId);
    var creatorId = document.getElementById("creatorId").value;
    var createTime = document.getElementById("createTime").value;
    var editTime = document.getElementById("editTime").value;
    var updateTime = document.getElementById("updateTime").value;
    var news = document.getElementById("news").value;
    var myselect1=document.getElementById("apk_isForbidden");
    var isForbidden = myselect1.options[myselect1.selectedIndex].value;
    /*data.append("id", id);
    data.append("label", label);
    data.append("pkgName", pkgName);
    data.append("clsName", clsName);
    data.append("alias", alias);
    data.append("fileSize", fileSize);
    data.append("versionCode", versionCode);
    data.append("versionName", versionName);
    data.append("minSdkVersion", minSdkVersion);
    data.append("targetSdkVersion", targetSdkVersion);
    data.append("icon", icon);
    data.append("fileUrl", fileUrl);
    data.append("fileMd5", fileMd5);
    data.append("des", des);
    data.append("typeId", typeId);
    data.append("creatorId", creatorId);
    data.append("createTime", createTime);
    data.append("editTime", editTime);
    data.append("updateTime", updateTime);
    data.append("news", news);
    data.append("isForbidden", isForbidden);*/
    var cc = ajax_encode(JSON.stringify({
        'id':id,
        'label':label,
        'pkgName':pkgName,
        'clsName':clsName,
        'alias':alias,
        'fileSize':fileSize,
        'versionCode':versionCode,
        'versionName':versionName,
        'minSdkVersion':minSdkVersion,
        'targetSdkVersion':targetSdkVersion,
        'icon':icon,
        'fileUrl':fileUrl,
        'fileMd5':fileMd5,
        'des':des,
        'typeId':typeId,
        'creatorId':creatorId,
        'createTime':createTime,
        'editTime':editTime,
        'updateTime':updateTime,
        'news':news,
        'isForbidden':isForbidden
    }));
    data.append("apkInfo",cc);
    data.append('screenshot', ajax_encode(JSON.stringify(imgslist)));
    console.log(data);
    if(confirm("是否确认修改？")){
        axios.post("../update", data)
            .then(response => {
                var data = response.data;
                if (data) {
                    alert("修改成功！！！");
                    window.location.href = "../../apkList.html";
                } else {
                    alert("失败！！！");
                }
            })
    }

})


function apkScreenshotDel(id) {
    console.log("wuhaiqiang"+id);
    $("#apkScreenshot"+id).remove();
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