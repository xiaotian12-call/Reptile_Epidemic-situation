<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 引入 ECharts 文件 -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/echarts.js"></script>
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
</head>
<body>
	<!-- 为 ECharts 准备一个具备大小（宽高）的Dom -->
	<div id="main" style="width: 100%; height: 450px;"></div>
	<table class="table table-striped">
		<thead>
			<tr>
				<th >时间</th>
				<th >省份</th>
				<th >确诊人数</th>
				<th >疑似人数</th>
				<th >治愈人数</th>
				<th>死亡人数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="item">
				<tr>
					<td >${item.lastUpdateTime}</td>
					<td >${item.name}</td>
					<td >${item.confirm}</td>
					<td >${item.suspect}</td>
					<td >${item.heal}</td>
					<td >${item.dead}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script type="text/javascript">
	var dt;
	$(function(){
		$.ajax({
			url : "info?method=yiqing",
			async : false,
			type : "POST",
			success : function(data) {
				dt = data;
				//alert(dt[0].name);
			},
			error : function() {
				alert("请求失败");
			},
			dataType : "json"
		});
	
		var myChart = echarts.init(document.getElementById('main'));
		alert(dt);
		var xd = new Array(0)//长度为33
		var yd = new Array(0)//长度为33
		for (var i = 0; i < 34; i++) {
			xd.push(dt[i].name);
			yd.push(dt[i].confirm);
		}
		alert(xd);
		alert(yd);
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : '全国各省的确诊人数'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '确诊人数' ]
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				axisLabel : {
					//横坐标上的文字斜着显示 文字颜色 begin 
					interval : 0,
					rotate : 45,
					margin : 10,
					textStyle : {
						color : "#ec6869"
					}
				//横坐标上的文字换行显示 文字颜色end
				},
				data : xd
			},
			yAxis : {
				type : 'value'
			},
			series : [ {
				name : '确诊人数',
				type : 'line',
				stack : '总量',
				data : yd
			} ]
		};

		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	});
</script>
</html>
