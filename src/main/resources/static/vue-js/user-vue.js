var vm_apklist = new Vue({
    el: "#pro-param", //注册对象使用{{}}
    data: {
        username: "",
        userpassword: ""
    },
    watch: {},
    mounted: function () {

    }

});
var jsonData = "";
var readyData = function () {
    axios.post("web-user/query")
        .then(response => {
            jsonData = response.data;
            var dataHtml = "";
            if (jsonData.length > 0) {
                $.each(jsonData, function (i, elt) {
                    var wu = "web-user/query/" + elt.userId;
                    console.log(wu)
                    dataHtml += "<tr>";
                    dataHtml += "<td>" + elt.userId + "</td>";
                    dataHtml += "<td>" + elt.userName + "</td>";
                    dataHtml += "<td>" + elt.userPassword + "</td>";
                    dataHtml += "<td>" + elt.permissionsId + "</td>";
                    dataHtml += "<td><a class='btn btn-default buttonstate' href='web-user/query/" + elt.userId + "' code_id='" + elt.userId + "'>修改</a>";
                    dataHtml += "<a class='btn btn-default buttonstate' id='del' code_id='" + elt.userId + "'>删除</a></td>";
                    dataHtml += "<tr>";
                    console.log(elt)
                });
                $("#table_connect").html(dataHtml);
                createButtonclick();
            }
            console.log(response)
        })
        .catch(function (error) {
            console.log(error)
        })
};
$(document).ready(readyData());

var createButtonclick = function () {
    $(".buttonstate").click(function () {
        var id = $(this).attr("id");
        console.log(id);
        var code_id = $(this).attr("code_id");
        if (id == 'del') {
            axios.post("web-user/del/" + code_id)
                .then(response => {
                    var data = response.data;
                    if (data) {
                        alert("删除成功！！！");
                        readyData();
                    } else {
                        alert("失败！！！");
                    }
                })
        } else if (id == 'doEdit') {
            editUser();
        }
    });
};

var pagingpPlugin = function (page) {
    var pagenum = parseInt(page);
    var pageul = $("#pagination");
    var pageH = "";
    var pagecount = parseInt(count);
    if (pagecount < 1) {
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
        if (pagecount > 1) {
            $("span.prev").removeClass("hover");
            $("span.prev").addClass("is-disabled");
            $("span.next").attr("data-page", pagenum + 1);
            $("span.prev").attr("data-page", 1);
            $("span.next").removeClass("is-disabled");
            $("span.next").addClass("hover");
        } else {
            $("span.prev").removeClass("hover");
            $("span.prev").addClass("is-disabled");
            $("span.next").removeClass("hover");
            $("span.next").addClass("is-disabled");
        }
    }
    if (0 < pagecount) {
        $(".paging").attr("displa", "block");
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
    } else {
        $(".paging").attr("displa", "none");
    }
    pageul.html(pageH);
    $(".hover").click(function () {
        var page = $(this).attr("data-page");
        console.log(">>>>>>>>>" + page)
        if (page.length > 0) {
            thispage = page;
            /*				var data = "num="+num+ "&page="+page;
                            thispage = page;*/
            //				ajax("../../news/data",data,0,false);
            readyData();
            pagingpPlugin(page);
        }
    });
};
/* var userAll = function(){
	axios.get("http://192.168.199.153:10001/test/web-user/query)
	.then(response => {
		var data = response.data;
		pagingpPlugin(1);
	});
}
userAll(); */

$("#add").click(function () {
    var data = new FormData();
    data.append("user", vm_apklist.username);
    data.append("pwd", vm_apklist.userpassword);
    axios.post("web-user/save", data)
        .then(response => {
            var jsonData = response.data;
            if (jsonData) {
                alert("添加成功！！！");
                window.location.href = "webUsers.html";
                readyData();
            } else {
                alert("添加失败！！！");
            }
            var dataHtml = "";
        })
})
