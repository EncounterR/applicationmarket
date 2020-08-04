$("#doEdit").click( function () {
    var data = new FormData();
    var userId = document.getElementById("userId").value;
    var userName = document.getElementById("userName").value;
    var userPassword = document.getElementById("userPassword").value;
    var permissionsId = document.getElementById("permissionsId").value;
    data.append("userId", userId);
    data.append("userName", userName);
    data.append("userPassword", userPassword);
    data.append("permissionsId", permissionsId);
    if(confirm("是否确认修改？")){
        axios.post("../update", data)
            .then(response => {
                var data = response.data;
                if (data) {
                    alert("修改成功！！！");
                    window.location.href = "../webUsers.html";
                } else {
                    alert("失败！！！");
                }
            })
    }
})