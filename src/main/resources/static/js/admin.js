$(function(){
	var hel = $(window).height();
	$(".b-left").height(hel-63);
	$(".b-right").height(hel-110);
/* 	$(window).resize(function() { //浏览器宽度改变
		rightwid();
	}); */
	var rightwid = function(){
		 var wwid = $("body").width();//获取浏览器窗口宽度
		 var lwid = $(".b-left").width();
		 var rwid = wwid - lwid - 30;
		 $(".b-right").width(rwid);
		 
		 var ifbody = $("iframe").find("body");
		 $("iframe").width(ifbody.width());
		 $("iframe").height(ifbody.height());
	}
//	rightwid();
	
	
	$("div.left-twonav").click(function(){
		$("div.left-twonav").css("color","#0a67b7");
		var src = $(this).attr("src");
		$("iframe").attr("src",src);
		$(this).css("color","#f69214");
	});
	$("#login_out").click(function () {
		$.ajax({
			url: "web-user/loginout",
            type: "post",
            success:function(res){
                window.location.href = "index.html";
			}
		})
    });
});
function getParams(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
};
$("#user").text(decodeURI(getParams("user")))
