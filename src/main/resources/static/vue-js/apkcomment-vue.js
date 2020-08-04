var jsonData = "";
var readyData = function () {
    var currentPage = document.getElementById("currentPage").value;
    axios.get("comment/apkcomment/" + currentPage)
        .then(response => {
            pagejsonData = response.data.score;
            $("#nextPage").val(pagejsonData);
            jsonData = response.data.apkCommentList;
            var dataHtml = "";
            if (jsonData.length > 0) {
                $.each(jsonData, function (i, elt) {
                    dataHtml += "<tr>";
                    dataHtml += "<td>" + elt.id + "</td>";
                    dataHtml += "<td>" + elt.apkId + "</td>";
                    dataHtml += "<td>" + elt.score + "</td>";
                    dataHtml += "<td>" + elt.createTime + "</td>";
                    dataHtml += "<td><a onclick='detApkComment(" + elt.id + ")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>详情</a>";
                    dataHtml += "<a class='btn btn-default buttonstate' id='del' onclick='deleteApkComment(" + elt.id + ")'>删除</a></td>";
                    dataHtml += "<tr>";
                    console.log(elt)
                });
                $("#table_connect").html(dataHtml);
            }
            console.log(response)
        })
        .catch(function (error) {
            console.log(error)
        })
};
$(document).ready(readyData());

$("#shangPage").click(function () {
    var currentPage1 = document.getElementById("currentPage").value;
    var currentPage2;
    if (currentPage1 > 1) {
        currentPage2 = Number(currentPage1) - 1;
        pageData(currentPage2)
        $("#currentPage").text(currentPage2);
        $("#currentPage").val(currentPage2);

    } else {
        alert("当前已经是首页！")
    }
})

$("#nextPage").click(function () {
    var currentPage1 = document.getElementById("currentPage").value;
    var nextPage = document.getElementById("nextPage").value;
    var currentPage2;
    if (currentPage1 < nextPage) {
        currentPage2 = Number(currentPage1) + 1;
        pageData(currentPage2);
        $("#currentPage").text(currentPage2);
        $("#currentPage").val(currentPage2);
    } else {
        alert("当前已经是尾页！")
    }

})

function pageData(currentPage) {
    axios.get("comment/apkcomment/" + currentPage)
        .then(response => {
            pagejsonData = response.data.score;
            $("#nextPage").val(pagejsonData);
            jsonData = response.data.apkCommentList;
            console.log(jsonData)
            var dataHtml = "";
            if (jsonData.length > 0) {
                $.each(jsonData, function (i, elt) {
                    dataHtml += "<tr>";
                    dataHtml += "<td>" + elt.id + "</td>";
                    dataHtml += "<td>" + elt.apkId + "</td>";
                    dataHtml += "<td>" + elt.score + "</td>";
                    dataHtml += "<td>" + elt.createTime + "</td>";
                    dataHtml += "<td><a onclick='detApkComment(" + elt.id + ")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>详情</a>";
                    dataHtml += "<a class='btn btn-default buttonstate' id='del' onclick='deleteApkComment(" + elt.id + ")'>删除</a></td>";
                    dataHtml += "<tr>";
                    console.log(elt)
                });
                $("#table_connect").html(dataHtml);
            }
            console.log(response)
        })
        .catch(function (error) {
            console.log(error)
        })
}

function deleteApkComment(id) {
    var currentPage = document.getElementById("currentPage").value;
    var nextPage = document.getElementById("nextPage").value;
    axios.post("comment/del/" + id)
        .then(response => {
            jsonData = response.data;
            if (jsonData) {
                if (document.getElementById("table_connect").rows.length == 2) {
                    pageData(Number(currentPage) - 1);
                    $("#currentPage").text(Number(currentPage) - 1);
                    $("#currentPage").val(Number(currentPage) - 1);
                } else {
                    pageData(currentPage);
                    $("#currentPage").text(currentPage);
                    $("#currentPage").val(currentPage);
                }

            } else {
                alert("删除失败！");
            }
        })
}

function detApkComment(id) {
    axios.get("comment/query/" + id)
        .then(response => {
            data = response.data;
            console.log(data)
            var dataHtml = "";
            dataHtml += "<table class='table table-bordered'>";
            dataHtml += "<tr><td>编号</td><td>" + data.id + "</td></tr>";
            dataHtml += "<tr><td>apkID</td><td>" + data.apkId + "</td></tr>";
            dataHtml += "<tr><td>评论分数</td><td>" + data.score + "</td></tr>";
            dataHtml += "<tr><td>评论内容</td><td>" + data.content + "</td></tr>";
            dataHtml += "<tr><td>评论时间</td><td>" + data.createTime + "</td></tr></table>";
            $("#modal_body").html(dataHtml);
        });
}