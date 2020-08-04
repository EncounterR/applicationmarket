var editPassword=new Vue({
    el:"#editpwd",
    data:{
        oldPassword:"",
        password:"",
        repassword:""
    },
    methods:{
        submit:function () {
            console.log(this.oldPassword);
            console.log(this.password);
            console.log(this.repassword);
            if(this.password==this.repassword){
                var data = new FormData();
                data.append("oldPassword",this.oldPassword);
                data.append("password",this.password);
                axios.put("web-user/update", data)
                    .then(response => {
                        var jsonData = response.data;
                        if (jsonData) {
                            alert("修改成功！！！");
                            window.location.href = "webUsers.html";
                        } else {
                            alert("旧密码错误！！！");
                        }
                    })
            }else {
                alert("两次密码不同！")
            }
        }
    }
})
