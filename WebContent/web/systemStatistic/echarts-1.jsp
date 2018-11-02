<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
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
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/irrigatedCommon.css">
		<link rel="stylesheet" href="css/default.css">

<link rel="stylesheet" type="text/css" href="moban2/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="moban2/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="moban2/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="moban2/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="moban2/static/h-ui.admin/css/style.css" />


<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>java成绩柱状图</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 成绩分析<span class="c-gray en">&gt;</span> 柱状图 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="container" style="padding-right: 10px !important;padding-left: 10px !important;">
		<div class="row row-margin0 row_serch">
		<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
		<form action="" method="post">
			<!-- <div class="col-md-2 common-padding-left0">
				<div class="input-group">
					<span class="input-group-addon allQuery">年份</span><input
						class="form-control" type="text" name="scoreDate" id="scoreDate"
						placeholder="姓名" />
				</div>
			</div> -->
			<div class="col-md-2">
				<div class="input-group">
					<span class="input-group-addon allQuery" >选择年份</span> <select
						class="form-control" id="queryScoreDate">
						<option value="2018">-2018-</option>
						<option value="2017">2017</option>
						<option value="2016">2016</option>
						<option value="2015">2015</option>
						<option value="2014">2014</option>
		
					</select>
				</div>
			</div>
			<!-- <div class="col-md-2">
				<div class="input-group">
					<span class="input-group-addon allQuery">学号</span><input
						class="form-control" type="text" name="queryStudentNumber" id="queryStudentNumber"
						placeholder="学号" />
				</div>
			</div> -->
			<div class=" col-md-1 registerStyl">
				<button type="submit" class="btn white" id="query_btn">
					&nbsp;查询
				</button>
			</div>
			
			</form>
			</div>
<%-- 			<c:if test="${sessionScope.role==0||sessionScope.role==2}">
			<div class=" col-md-7 text-right registerStyl common-padding-right0">
				<button type="button" class="btn white" id="student_add_btn"
					data-toggle="modal" data-target="">
					&nbsp;新增
				</button>
				<button type="button" class="btn white" id="student_update_btn">
					&nbsp;修改
				</button>
				<button type="button" class="btn white" id="student_delete_btn">
					&nbsp;删除
				</button>
				<button type="button" class="btn white common-margin-right0" id="student_excel">
					&nbsp;导出
				</button>
			</div>
			</c:if>
			</div> --%>
		</div>
		</div>
	<div id="containerB" style="min-width:700px;height:400px"></div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="moban2/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="moban2/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="moban2/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="moban2/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->

	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	 <!--datetimeepicker日期控件引用-->
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/echarts.simple.min.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>

  <script type="text/javascript">
  var dom = document.getElementById("containerB");
  var myChart = echarts.init(dom);
  var app = {};
  option = null;
  app.title = '坐标轴刻度与标签对齐';

     

      
       myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
       
      
       
       $.ajax({
           type : "post",
           async : true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
           url : "client/ScoreServlet?method=queryListMonthScore",    //请求发送到ScoreServlet里的queryListMonthScore方法通过beanServlet
           data : {},
           dataType : "json",        //返回数据形式为json
           success : function(result) {
               //请求成功时执行该函数内容，result即为服务器返回的json对象
               
  	        	
  			       /* if (result) {
  						for (var i = 0; i < result.length; i++) {
  							console.log(result[i].num);
  							scores.push(result[i].num);
  						}
  					} */
  					myChart.hideLoading(); //隐藏加载动画
  					myChart.setOption({

  						color : [ '#3398DB' ],
  						tooltip : {
  							trigger : 'axis',
  							axisPointer : { // 坐标轴指示器，坐标轴触发有效
  								type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
  							}
  						},
  						grid : {
  							left : '3%',
  							right : '4%',
  							bottom : '3%',
  							containLabel : true
  						},
  						xAxis : [ {
  							type : 'category',
  							data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月',
  									'8月', '9月', '10月', '11月', '12月' ],
  							axisTick : {
  								alignWithLabel : true
  							}
  						} ],
  						yAxis : [ {
  							type : 'value'
  						} ],
  						series : [ {
  							name : '分数',
  							type : 'bar',
  							barWidth : '60%',
  							data : result
  						} ]

  					});

  					/*  for(var i=0;i<jsonJavaScore.length;i++){       
  					 	scores.push(jsonJavaScore[i].name);    //挨个取出类别并填入类别数组
  					  }
  					
  					 myChart.hideLoading();    //隐藏加载动画
  					 myChart.setOption({        //加载数据图表
  					     yAxis: {
  					         data: scores
  					     },
  					     series: [{
  					         // 根据名字对应到相应的系列
  					         name: '分数',
  					         data: scores
  					     }]
  					 }); */

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