<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${ctx }/js/echarts.min.js"></script>
<title>数据报表</title>
</head>
<body>
	<h3>公司员工人数统计表</h3>
	<div id="company" style="width: 600px;height: 400px;"></div>
	
	<h3>公寓员工人数统计表</h3>
	<div id="flats" style="width: 600px;height: 400px;"></div>
	
</body>
<script type="text/javascript">
	var companyEle = document.getElementById("company");
	var myChart1 = echarts.init(companyEle);
	
	myChart1.setOption({
		series : [ {
			name : '公司人数统计图',
			type : 'pie', // 设置图表类型为饼图
			radius : '55%', // 饼图的半径，外半径为可视区尺寸（容器高宽中较小一项）的 55% 长度。
			data : ${data1 } , // 数据数组，name 为数据项名称，value 为数据项值
		    label:{ 
		    	show: true, 
		        formatter: '{b} : {c} ({d}%)' 
		    }
		} ]
	});
	
	
	var flatsEle = document.getElementById("flats");
	var myChart2 = echarts.init(flatsEle);
	
	myChart2.setOption({
		 xAxis: {
		        type: 'category',
		        data: ${data2 }
		    },
		    yAxis: {
		        type: 'value'
		    },
		    series: [{
		        data: ${data3 },
		        type: 'bar',
		        showBackground: true,
		        backgroundStyle: {
		            color: 'rgba(180, 180, 180, 0.2)'
		        }
		    }]
	});
	
</script>
</html>