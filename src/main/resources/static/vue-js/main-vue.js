axios.get("core/tomain")
    .then(response => {
        var apktypeArry=[];
        var apkArry=[];
        jsonData=response.data;
        if (jsonData.length > 0){
            $.each(jsonData,function(i,elt){
                apktypeArry[i]=elt.apkType.typeName;
                apkArry[i]=elt.apkList.length;
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('main'));
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '各分类的App数量'
                    },
                    tooltip: {},
                    legend: {
                        data:['个数']
                    },
                    xAxis: {
                        data: apktypeArry
                    },
                    yAxis: {},
                    series: [{
                        name: '个数',
                        type: 'bar',
                        data: apkArry
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            });
        }
    })

axios.get("apk-download-log/tomain")
    .then(response => {
        var arry1=[];
        var arry2=[];
        jsonData=response.data;
        if (jsonData.length > 0){
            $.each(jsonData,function(i,elt){
                arry1[i]=elt.month;
                arry2[i]=elt.apkDownloadLogList.length;
                // 基于准备好的dom，初始化echarts实例
                var myChart = echarts.init(document.getElementById('downloadLog'));
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: '今年每月下载总数'
                    },
                    tooltip: {},
                    legend: {
                        data:['次数']
                    },
                    xAxis: {
                        data: arry1
                    },
                    yAxis: {},
                    series: [{
                        name: '次数',
                        type: 'line',
                        data: arry2
                    }]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            });
        }
    })

axios.get("comment/tomain")
    .then(response => {
        var arry1=[];
        var arry2=[];
        var arry=[];
        jsonData=response.data;
        if (jsonData.length > 0){
            $.each(jsonData,function(i,elt){
                var wu={value:elt.apkCommentList.length,name:elt.score};
                arry.push(wu);
            });
        }
        console.log(arry)
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('apkcomment'));
        // 指定图表的配置项和数据
        option = {
            title : {
                text: 'App评论分数区间',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['0-6', '6-8', '8-10']
            },
            series : [
                {
                    name: '分数',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:arry,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    })

