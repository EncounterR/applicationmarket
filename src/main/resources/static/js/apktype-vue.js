var jsonData = "";
var readyData = function () {
    axios.post("apkType/allApkType")
        .then(response => {
            jsonData = response.data;
            var dataHtml = "";
            if (jsonData.length > 0) {
                $.each(jsonData, function (i, elt) {
                    var wu = "apkType/allApkType/" + elt.userId;
                    console.log(wu)
                    dataHtml += "<tr>";
                    dataHtml += "<td>" + elt.id + "</td>";
                    dataHtml += "<td>" + elt.typeName + "</td>";
                    dataHtml += "<td>" + elt.pid + "</td>";
                    dataHtml += "<td><a onclick='editApkType("+elt.id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'>修改</a>";
                    dataHtml += "<a class='btn btn-default buttonstate' id='del' onclick='deleteApkType("+elt.id+")'>删除</a></td>";
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

$("#add").click(function () {
    var data = new FormData();
    var type_name=document.getElementById("typeName").value;
    data.append("typeName", type_name);
    axios.post("apkType/save", data)
        .then(response => {
            var jsonData = response.data;
            if (jsonData) {
                alert("添加成功！！！");
                window.location.href = "apkType.html";
                readyData();
            } else {
                alert("添加失败！！！");
            }
        })

})

function deleteApkType(id){
    console.log("-----"+id);
    axios.post("apkType/del/"+ id)
        .then(response => {
            var jsonData = response.data;
            if (jsonData) {
                alert("删除成功！！！");
                readyData();
            } else {
                alert("删除失败！！！");
            }
        })
}

function editApkType(id) {
    axios.get("apkType/query/"+id)
        .then(response => {
            var data = response.data;
            var dataHtml = "";
            dataHtml += "<table class='table table-bordered'>";
            dataHtml += "<tr><td>编号</td><td><input type='text' id='id' class='layui-input layui-disabled' value='"+data.id+"'></td></tr>";
            dataHtml += "<tr><td>分类名</td><td><input type='text' id='typeName' class='layui-input' value='"+data.typeName+"'></td></tr>";
            dataHtml += "<tr><td>组属</td><td><input type='text' id='pid' class='layui-input layui-disabled' value='"+data.pid+"'></td></tr>";
            $("#modal_body").html(dataHtml);
        });
}

$("#submit").click(function () {
    var data = new FormData();
    var id=document.getElementById("id").value;
    var type_name=document.getElementById("typeName").value;
    var pid=document.getElementById("pid").value;
    data.append("id", id);
    data.append("typeName", type_name);
    data.append("pid", pid);
    axios.post("apkType/edit", data)
        .then(response => {
            var jsonData = response.data;
            if (jsonData) {
                window.location.href = "apkType.html";
                readyData();
            } else {
                alert("修改失败！！！");
            }
        })

})