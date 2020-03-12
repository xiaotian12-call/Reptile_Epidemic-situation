<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html style="height: 100%">

<head>
<meta charset="utf-8">
<base>
<title>地图阶段二</title>
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script type="text/javascript" src="js/china.js"></script>
</head>
<body style="height: 100%; margin: 0">
	<div id="main" style="height: 100%"></div>
</body>
<script type="text/javascript">
	var dt;
	var mydata1 = new Array(0);
	$(function(){
		$.ajax({
			url : "info?method=yiqing",
			async : false,
			type : "POST",
			success : function(data) {
				dt = data;
				for (var i = 0; i < 33; i++) {
					var d = {
						
					};
					
					d["name"] = dt[i].name;//.substring(0, 2);
					d["value"] = dt[i].confirm;
					d["yisi_num"] = dt[i].suspect;
					d["cured_num"] = dt[i].heal;
					d["dead_num"] = dt[i].dead;
					mydata1.push(d);
				}
				
				//var mdata = JSON.stringify(mydata1);
				var optionMap = {
					backgroundColor : '#FFFFFF',
					title : {
						text : '全国地图大数据',
						subtext : '',
						x : 'center'
					},
					tooltip : {
						formatter : function(params) {
							return params.name + '<br/>' + '确诊人数 : '
									+ params.value + '<br/>' + '死亡人数 : '
									+ params['data'].dead_num + '<br/>' + '治愈人数 : '
									+ params['data'].cured_num + '<br/>'+ '疑似患者人数 : '
									+ params['data'].yisi_num;
						}//数据格式化
					},

					//左侧小导航图标
					visualMap : {
						min : 0,
						max : 70000,
						text : [ '多', '少' ],
						realtime : false,
						calculable : true,
						inRange : {
							color : [ 'lightskyblue', 'yellow', 'orangered' ]
						}
					},

					//配置属性
					series : [ {
						type : 'map',
						mapType : 'china',
						label : {
							show : true
						},
						data : mydata1
					} ]
				};
				//初始化echarts实例
				var myChart = echarts.init(document.getElementById('main'));
				myChart.on('click', function (params) {
			         var url = "info?method=city2&province=" + params.name;
			         window.location.href = url;
				});
				//使用制定的配置项和数据显示图表
				myChart.setOption(optionMap);
			},
			error : function() {
				alert("请求失败");
			},
			dataType : "json"
		});
	});
</script>
</html>
