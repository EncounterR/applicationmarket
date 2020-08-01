var content = new Vue({
    el: "#pro-param",
    data: {
        recommend: 0,
        video: 0,
        other: 0,
        game: 0,
        tools: 0,
        recommend_connect: "",
        icon_width: "100",
        uploadIconRate: "",
        icon: "",
        uploadIconRate: ""

    },
    watch: {
        recommend(newval, oldval) {
            sethtml("recommend_connect", newval, arrayrecommend);
            //			this.recommend_connect = htmlcon;
        },
        video(newval, oldval) {
            sethtml("video_connect", newval, arrayvideo);

        },
        other(newval, oldval) {
            sethtml("other_connect", newval, arrayother);

        },
        game(newval, oldval) {
            sethtml("game_connect", newval, arraygame);

        },
        tools(newval, oldval) {
            sethtml("tools_connect", newval, arraytools);

        },
        icon(newval) {
            this.icon_width = getwidth(newval);

        }

    },
    methods: {
        click_recommend: function (e) {
            hideorshow("recommend_connect", e.currentTarget);
        },
        click_video: function (e) {
            hideorshow("video_connect", e.currentTarget);
        },
        click_other: function (e) {
            hideorshow("other_connect", e.currentTarget);
        },
        click_game: function (e) {
            hideorshow("game_connect", e.currentTarget);
        },
        click_tools: function (e) {
            hideorshow("tools_connect", e.currentTarget);
        },
        logoImgButton: function (e) {
            console.log("wuhaiqiang")
            $("#logoimguoload").click();
        },

        logoimguoload: function (e) {
            content.uploadIconRate = "";
            console.log(e.target.files[0]);
            var formData = new FormData();
            formData.append("file", e.target.files[0]);
            var config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                onUploadProgress: function (e) {
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
                            content.uploadIconRate = rate * 100 + '%';
                        }
                    }
                }
            };
            axios.post("upload/img", formData, config)
                .then(function (data) {
                    console.log(data);
                    var json = data.data; //后台实际返回的结果
                    if (json.url.length > 0) {
                        content.uploadIconRate = '100%';
                        content.icon = json.url;
                        document.getElementById("apkImg").src = json.url;
                        console.log(json.url);
                    } else {
                        alert('upload error');
                    }

                })
                .catch(function (err) {
                    console.log(err);
                });
        },
        doCommit:function () {
            console.log("wuhaiqiang");
            var data = new FormData();
            var id1 = document.getElementById("id").value;
            var category1 = document.getElementById("category").value;
            var sortOrder1 = document.getElementById("sortOrder").value;
            var apkId1 = document.getElementById("apkId").value;
            var apkImg1 = document.getElementById("apkImg").src;
            console.log(apkImg1)
            data.append("id", id1);
            data.append("category", category1);
            data.append("sortOrder", sortOrder1);
            data.append("apkId", apkId1);
            data.append("apkImg", apkImg1);
            console.log(data)
            axios.post("app-recommend/update1", data)
                .then(response => {
                    var data = response.data;
                    if (data) {
                        window.location.href = "recommend1.html";
                    } else {
                        alert("失败！！！");
                    }
                })
        }
    }
});

function ajax_encode(str) {
    if (str != "" && str.length > 0) {
        str = str.replace(/%/g, "{@bai@}");
        str = str.replace(/ /g, "{@kong@}");
        str = str.replace(/</g, "{@zuojian@}");
        str = str.replace(/>/g, "{@youjian@}");
        str = str.replace(/&/g, "{@and@}");
        str = str.replace(/\"/g, "{@shuang@}");
        str = str.replace(/\'/g, "{@dan@}");
        str = str.replace(/\t/g, "{@tab@}");
        str = str.replace(/\+/g, "{@jia@}");
        return str;
    }
}

var datastr = function (elt, category) {

    var sortOrder = $(elt).attr("index");
    var img = $(elt).find("img").attr("src");
    var apkId = $(elt).find("span").attr("apk_id");
    if (apkId <= 0 || img.length <= 0) {
        return "";
    }

    return {
        'category': category,
        'sortOrder': sortOrder,
        'img': img,
        'apkId': apkId
    };
}
var hhtarger = $("#recommend_li");
var hideorshow = function (targer, litarger) {
    $(hhtarger).removeClass("licolor");
    hhtarger = litarger;
    $("#video_connect").hide();
    $("#other_connect").hide();
    $("#game_connect").hide();
    $("#tools_connect").hide();
    $("#recommend_connect").hide();
    $("#" + targer).show();
    $(litarger).addClass("licolor");
}

var getwidth = function (content) {
    var sensor = $('<span style="font-size: 12px;">' + content + '</span>').css({
        display: 'none'
    });
    $('body').append(sensor);
    var width = sensor.width();
    if (width > 600) {
        width = 600;
    }
    if (width < 100) {
        width = 100;
    }
    return width;
}
var sethtml = function (target_id, newval, array) {
    var htmlcon = "";
    console.log(target_id);
    if (array.length > 0) {
        for (var i = 1; i <= newval; i++) {
            htmlcon += "<div index=" + i + " class='apkrconnect'><img src='" + array[i - 1].img +
                "' width='200px' height='200px'><span apk_id='" + array[i - 1].id + "'>" + array[i - 1].label + "</span></div>";
        }
    } else {
        for (var i = 1; i <= newval; i++) {
            htmlcon += "<div index=" + i +
                " class='apkrconnect'><img src='' width='200px' height='200px'><span apk_id='0'></span></div>";
        }
    }
    $("#" + target_id).html(htmlcon);
    proimgbtn();
}
var proimgObj;
var proimgbtn = function () {
    $(".apkrconnect").unbind('click');
    $(".apkrconnect").click(function () {
        var X = $(this).position().left;
        var Y = $(this).position().top;
        proimgObj = $(this);
        var c = $(proimgObj).find("img").attr("src");
        content.icon = c;
        $("#imgorderinput").val($(proimgObj).find("span").attr("apk_id"));
        //		$("#imgorderinput").val($(this).attr("index"));
        $(".recmm_info").css({
            "top": Y + 50 + "px",
            "left": X + 50 + "px",
            "display": "block"
        });
    });
}
$("#imgInfobtn-cmit").click(function () {
    var proimgdiv = proimgObj;
    var src = content.icon;
    var index = $("#imgorderinput").val();
    var label = "";
    axios.get("core/query/" + index)
        .then(response => {
            var data = response.data;
            if (data != "") {
                var type = data.type;
                label = data.label;
                if (src != "") {
                    var cc = $(proimgdiv).find("img");
                    $(proimgdiv).find("img").attr("src", src);
                    $(proimgdiv).find("span").text(label);
                    $(proimgdiv).find("span").attr("apk_id", data.id);
                }
            } else {
                content.icon = src;
                $("#imgorderinput").val($(proimgdiv).find("span").attr("apk_id"));
                $(".recmm_info").css("display", "block");
                proimgObj = proimgdiv;
            }

            /* 		pagingpPlugin(1); */
        })
        .catch(function (err) {
            alert("apk不存在！！！");
            content.icon = src;
            $("#imgorderinput").val($(proimgdiv).find("span").attr("apk_id"));
            $(".recmm_info").css("display", "block");
            proimgObj = proimgdiv;
        });

    //	var cindex = proimgObj.attr("index");

    //	proimgObj.attr("index", index);
    $(".recmm_info").css("display", "none");
    proimgObj = "";
    content.icon = "";
    $("#imgorderinput").val("");

});

$("#imgInfobtn-cancel").click(function () {
    $(".recmm_info").css("display", "none");
    proimgObj = "";
    $("#imgsrcinput").val("");
    $("#imgorderinput").val("");
});


$("#imgInfobtn-cancel").click(function () {

});

var arrayrecommend = new Array();
var arrayvideo = new Array();
var arrayother = new Array();
var arraygame = new Array();
var arraytools = new Array();

var recserverdata = "";
var apksum = function () {
    axios.post("app-recommend/query2")
        .then(response => {
            var data = response.data; //resultData
            /*if (data != "") {
                recserverdata = data.resultData;
                if (recserverdata != null || recserverdata.length > 0) {
                    $.each(recserverdata, function (i, elt) {
                        if (elt.category == 1) {
                            arrayrecommend[elt.sortOrder - 1] = {
                                'img': elt.img,
                                'label': elt.label,
                                'id': elt.id
                            }
                        } else if (elt.category == 2) {
                            arrayvideo[elt.sortOrder - 1] = {
                                'img': elt.img,
                                'label': elt.label,
                                'id': elt.id
                            }
                        } else if (elt.category == 3) {
                            arrayother[elt.sortOrder - 1] = {
                                'img': elt.img,
                                'label': elt.label,
                                'id': elt.id
                            }
                        } else if (elt.category == 4) {
                            arraygame[elt.sortOrder - 1] = {
                                'img': elt.img,
                                'label': elt.label,
                                'id': elt.id
                            }
                        } else {
                            arraytools[elt.sortOrder - 1] = {
                                'img': elt.img,
                                'label': elt.label,
                                'id': elt.id
                            }
                        }
                    });
                }
        

                content.recommend = data.recommendParam.recommend;
                content.video = data.recommendParam.video;
                content.other = data.recommendParam.other;
                content.game = data.recommendParam.game;
                content.tools = data.recommendParam.tools;
            }*/
            $.each(data, function (i, elt) {
                if(elt.category==1){
                    var dataHtml = "";
                    $.each(elt.appRecommendList, function (i, elt){
                        if(elt.sortOrder==1){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==2){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==3){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==4){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==5){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==6){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                    })
                    $("#table_connect_recommend").html(dataHtml);
                }
                if(elt.category==2){
                    var dataHtml = "";
                    $.each(elt.appRecommendList, function (i, elt){
                        if(elt.sortOrder==1){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==2){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==3){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==4){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==5){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==6){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                    })
                    $("#table_connect_video").html(dataHtml);
                }
                if(elt.category==3){
                    var dataHtml = "";
                    $.each(elt.appRecommendList, function (i, elt){
                        if(elt.sortOrder==1){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==2){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==3){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==4){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==5){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==6){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==7){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==8){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                    })
                    $("#table_connect_other").html(dataHtml);
                }
                if(elt.category==4){
                    var dataHtml = "";
                    $.each(elt.appRecommendList, function (i, elt){
                        if(elt.sortOrder==1){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==2){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==3){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==4){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==5){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==6){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                    })
                    $("#table_connect_game").html(dataHtml);
                }
                if(elt.category==5){
                    var dataHtml = "";
                    $.each(elt.appRecommendList, function (i, elt){
                        if(elt.sortOrder==1){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==2){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==3){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==4){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==5){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==6){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                        if (elt.sortOrder==7){
                            if(elt.apkId!=null){
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>"+elt.apkId+"</td>";
                                dataHtml+="<td>kodi</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='"+elt.img+"'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a onclick='appRecommendDel("+elt.id+")' class='btn btn-default' href='#'>删除</a></td></tr>";
                            }else{
                                dataHtml+="<tr><td>"+elt.sortOrder+"</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td>null</td>";
                                dataHtml+="<td><img height='100px' width='100px' src='img/ic_kodi.png'></td>";
                                dataHtml+="<td><a onclick='appRecommendDet("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a><a class='btn btn-default' href='#'>删除</a></td></tr>";
                            }
                        }
                    })
                    $("#table_connect_tools").html(dataHtml);
                }
            })
        });
}
apksum();

jsonData.appRecommendList = "";
function appRecommendDet(id) {
    axios.get("app-recommend/query/"+id)
        .then(response => {
            var data = response.data;
            console.log(data.img);
            if(data.apkId==null){
                $("#apkId").val("null");
                document.getElementById("apkImg").src = "null";
            }else{
                $("#apkId").val(data.apkId);
                document.getElementById("apkImg").src = data.img;
            }
            $("#id").val(data.id);
            $("#category").val(data.category);
            $("#sortOrder").val(data.sortOrder);
        });
}

function appRecommendDel(id) {
    if(confirm("是否确认删除该推荐信息？")) {
        axios.post("app-recommend/update/" + id)
            .then(response => {
                var data = response.data;
                if (data) {
                    alert("删除成功！！！");
                    window.location.href = "recommend1.html";
                } else {
                    alert("失败！！！");
                }
            });
    }
}