$(document).ready(function(){
 		// 趋势图
        tendencyChart = new Highcharts.Chart({
            chart: {
                renderTo: 'priceGo',
                type: 'line',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: '',
                x: -20 //center
            },
            subtitle: {
//                text: 'Source: WorldClimate.com',
//                x: -20
            },
            xAxis: {
                categories: []
            },
            yAxis: {
                title: {
                    text: '价格 (/元)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +'日: '+ this.y +'元';
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [{
                name: '价格',
                data: []
            }
            ]
        });
        
        // 加载数据
        priceGo();
	
});

// 调用次数统计
function priceGo(){
	var id= $("#itemId").val();
	$.post("appreciate/priceGo.htm",{"item.id":id},function(data){
//		var json=eval('('+data+')');
		var resNum=[];
		var resDate=[];
		tendencyChart.xAxis[0].setCategories(resDate);
		tendencyChart.series[0].setData(resNum);
		$.each(data,function(i,value){
			resNum[i]=value.price;
			resDate[i]=value.curDate.substring(0,10);
		});
		var resSeries=[];
		var v={};
		v.name="API调用次数";
		v.data=resNum;
		resSeries.push(v);
		tendencyChart.xAxis[0].setCategories(resDate);
		tendencyChart.series[0].setData(resNum);
		
		$(".sl-condition").addClass("hide");
	});
}