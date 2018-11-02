<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html style="height: 100%">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
<link rel="stylesheet" type="text/css" href="moban2/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="moban2/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="moban2/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="moban2/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="moban2/static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>java成绩饼状图</title>
</head>
   <body style="height: 100%; margin: 0">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 成绩分析<span class="c-gray en">&gt;</span> 饼状图 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	
	<div id="container" style="min-width:700px;height:400px"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="moban2/lib/jquery/1.9.1/jquery.min.js"></script> 
<!-- <script type="text/javascript" src="moban2/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="moban2/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="moban2/static/h-ui.admin/js/H-ui.admin.js"></script> /_footer 作为公共模版分离出去 -->

<!--请在下方写此页面业务相关的脚本-->
<div id="container" style="height: 100%"></div>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="js/echarts.simple.min.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>

<script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
var weatherIcons = {
    'Sunny': './data/asset/img/weather/sunny_128.png',
    'Cloudy': './data/asset/img/weather/cloudy_128.png',
    'Showers': './data/asset/img/weather/showers_128.png'
};


myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画

var score = [];
$.ajax({
    type : "post",
    async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    url : "client/ScoreServlet?method=queryListPercentageScore",    //请求发送到ScoreServlet里的queryListMonthScore方法通过beanServlet
    data : {},
    dataType : "json",        //返回数据形式为json
    success : function(jsonJavaScore) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
                 
             
       	
		       
				myChart.hideLoading(); //隐藏加载动画
				myChart.setOption({
                          
					
					title: {
				        text: 'java分数段统计',
				        subtext: '实时数据',
				        left: 'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				       // orient: 'vertical',
				       // top: 'middle',
				        bottom: 10,
				        left: 'center',
				        data: ['小于60', '60-70','70-80','80-90','大于90']
				    },
				    series : [
				        {
				            type: 'pie',
				            radius : '70%',
				            center: ['50%', '50%'],
				            selectedMode: 'single',
				            data:[
				                {
				                   
				                    label: {
				                        normal: {
				                            formatter: [
				                                '{title|{b}}{abg|}',
				                              '  {weatherHead|天气}{valueHead|天数}{rateHead|占比}', 
				                                '{hr|}',
				                                '  {Sunny|}{value|202}{rate|55.3%}',
				                                '  {Cloudy|}{value|142}{rate|38.9%}',
				                                '  {Showers|}{value|21}{rate|5.8%}'
				                            ].join('\n'), 
				                            backgroundColor: '#eee',
				                            borderColor: '#777',
				                            borderWidth: 1,
				                            borderRadius: 4,
				                            rich: {
				                                title: {
				                                    color: '#eee',
				                                    align: 'center'
				                                },
				                                abg: {
				                                    backgroundColor: '#333',
				                                    width: '100%',
				                                    align: 'right',
				                                    height: 25,
				                                    borderRadius: [4, 4, 0, 0]
				                                },
				                                Sunny: {
				                                    height: 30,
				                                    align: 'left',
				                                    backgroundColor: {
				                                        image: weatherIcons.Sunny
				                                    }
				                                },
				                                Cloudy: {
				                                    height: 30,
				                                    align: 'left',
				                                    backgroundColor: {
				                                        image: weatherIcons.Cloudy
				                                    }
				                                },
				                                Showers: {
				                                    height: 30,
				                                    align: 'left',
				                                    backgroundColor: {
				                                        image: weatherIcons.Showers
				                                    }
				                                },
				                                weatherHead: {
				                                    color: '#333',
				                                    height: 24,
				                                    align: 'left'
				                                },
				                                hr: {
				                                    borderColor: '#777',
				                                    width: '100%',
				                                    borderWidth: 0.5,
				                                    height: 0
				                                },
				                                value: {
				                                    width: 50,
				                                    padding: [0, 20, 0, 30],
				                                    align: 'left'
				                                },
				                                valueHead: {
				                                    color: '#333',
				                                    width: 20,
				                                    padding: [0, 20, 0, 30],
				                                    align: 'center'
				                                },
				                                rate: {
				                                    width: 40,
				                                    align: 'right',
				                                    padding: [0, 10, 0, 0]
				                                },
				                                rateHead: {
				                                    color: '#333',
				                                    width: 40,
				                                    align: 'center',
				                                    padding: [0, 10, 0, 0]
				                                }
				                            }
				                        }
				                    }
				                },
				                {value:jsonJavaScore[0], name: '小于60'},
				                {value:jsonJavaScore[1], name: '60-70'},
				                {value:jsonJavaScore[2], name: '70-80'},
				                {value:jsonJavaScore[3], name: '80-90'},
				                {value:jsonJavaScore[4], name: '大于90'}
				               
				                
				            ],
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
					

				});
                },
				    error : function(errorMsg) {
						//请求失败时执行该函数
						alert("图表请求数据失败!");
						myChart.hideLoading();
					}
				});



if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
</script>
</body>
</html>