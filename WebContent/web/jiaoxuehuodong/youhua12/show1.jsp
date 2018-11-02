<%@page import="java.io.IOException"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>计划详情页</title>
    <base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
	<link rel="stylesheet" type="text/css" href="web/jiaoxuehuodong/youhua12/iconfont/zzsc.css">
	<link rel='stylesheet' href='web/jiaoxuehuodong/youhua12/iconfont/jquery.cardtabs.css'>
	<link rel='stylesheet' href='web/jiaoxuehuodong/youhua12/iconfont/demo.css'>
	
	
	
	
	<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/irrigatedCommon.css">
		<link rel="stylesheet" href="css/default.css">
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap-datetimepicker.min.css" />

<style type="text/css">
				p{
				color:red;
				text-align:center;
			line-height:40px;
				}
				#update_Teacherinfo div{
				position:relative;
				left:50px;
				
				}
				#update_Teacherinfo button{
				position:absolute;
				left:120px;
				}
		
		</style>


</head>
<body class='markdown-body'  >

<div class="container" style="width:960px;margin:10px auto;padding:10px;position: relative;top: 50px;background: rgba(255,255,255,0.5);">
	<h2>计划具体内容</h2>
	<div class='example'>
	
	<div >
				
			
					
				
	
	
	
	
	
	
	
	<br/>
<div class='tabsholder1'>
					<form action="Plan_serverlet?method=plan_add&findstyle=${findstyle}" method="post">	
					<span class="">姓名</span>
					<input class="form-control" type="text" name="name" value="${yi.teacher_names}" id="queryStudentName" placeholder="请输入学生姓名" />
					<span class="">选择日期</span>
					<input onclick="chooseDate" class="form-control SystemCalculationdateinput" type="text" id="queryHomeworkDate" name="plan_date" value="${yi.plan_date}" placeholder="选择日期" />							
					<span style="font-size: 25px; color:#0080C0;">标题</span>
					<br/>
					<input type="hidden" name="ids" value="${yi.id}" />
	
					<!-- <span style="font-size: 25px; color:#0080C0; position: absolute;right: 230px">日期：</span>
					<input name="plan_date" type="date" style="position: absolute;top: 160px;right: 90px; font-size: large;border: none;"></input>
					<br/> -->
					<textarea style="background-color:transparent;border:0.5;" name="plan_titles"    rows="2" cols="100" value="${yi.plan_titles}" >${yi.plan_titles}</textarea><br/>
					<span style="font-size: 25px; color:#0080C0;">具体内容</span><br/>
					<textarea style="background-color:transparent;border:0.5;" name="plan_concent" rows="10" cols="100" value="${yi.plan_concent}" >${yi.plan_concent}</textarea>
                    <input id="tijiaoss" class="btn btn-default" type="submit"  style="position: absolute;bottom: 25px;right: 150px; font-size: large;border: none;" value="提交"></input>
                    
			</form> 
				</div>
			
			</div>
	</div>
	
</div>

 
<script src="web/jiaoxuehuodong/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script type='text/javascript' src='web/jiaoxuehuodong/js/jquery.cardtabs.js'></script>
<script type='text/javascript'>
		$('.tabsholder1').cardTabs();
		$('.tabsholder2').cardTabs({theme: 'inset'});
		$('.tabsholder3').cardTabs({theme: 'graygreen'});
		$('.tabsholder4').cardTabs({theme: 'wiki'});
</script>
</body>
 <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>

  <!--datetimeepicker日期控件引用-->
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
    
<script type="text/javascript" >
//		var homeWork = new HomeWork();
		$(function(){
			$("#tijiaoss").click(function(){
				//获取页码值
				if(confirm("确定提交并保存吗？")){
					return true;
				}else{
					return false;
				}
				
			    
				
				
			})
			
			
			$(".hiddenTdTh").hide();
			/*日期插件input_queryDate  */
			$("#queryHomeworkDate ").datetimepicker(
					{
				        language: 'zh-CN',//显示中文
				        format: 'yyyy-mm-dd',//显示格式
				        minView: "month",//设置只显示到月份
				        initialDate: new Date(),
				        autoclose: true,//选中自动关闭
				        todayBtn: true,//显示今日按钮
					}		
			)
			/*type页面  */
			$('#myTab li:eq(1) a').tab('show');
			
			   /**
			 * 行的点击事件
			 */
			$("#Homework_table").delegate("tbody tr", "click",function(){
				$(this).addClass('common_checked_tr').siblings().removeClass('common_checked_tr').end();
			});
			
			/**
			 * 新增按钮点击事件，弹出新增模态框
			 */
			$("#Homework_add_btn").click(function(){
				
				$("div.error").remove();
				var trs = $("#Homework_table tbody tr.common_checked_tr");
				if(trs.length>0){
					$(trs.eq(0).closest('tr')).removeClass('common_checked_tr');
				}
				$("#myModalLabel").html("新增作业");
				$('#Homework_modal').modal({show:true,backdrop:'static'});
			});
			
			
			/**
			 * 模态框保存按钮点击事件
			 */
		    $("#Homework_save_btn").click(function(){
		    	$('#Homework_save_btn').attr("disabled",true);
		    	/* var score = "${score.id}";
		    	var id = score.id;//ID */
		    	var trs = $("#Homework_table tbody tr.common_checked_tr");
		    	var id = trs.children().eq(0).html();
		    	alert(id);
		    	
		    	
		    	var studentName =$("#studentName").val();
		    	var notescore =$("#notescore").val();
		    	var code_number =$("#code_number").val();
		    	var isannotate =$("#isannotate").val();
		    	var correct_rate =$("#correct_rate").val();
		    	var question =$("#question").val();
		    	var team =$("#team").val();
		    	
		    	var url= "";
		    	if(id==null||id==""||id==undefined){
		    		url= "HomeworkServlet?method=saveHomework"; //添加
		    	}
		    	else{
		    		url= "HomeworkServlet?method=updateHomework";//修改
		    	}
		    
		    	
		    	$.ajax({
		    		type : "post",
		    		url : url,
		    		dataType : "text",
		    		data : {
		    			    id : id,
		    			    studentName:studentName,
		    			    notescore : notescore,
		    			    code_number : code_number,
		    			    isannotate : isannotate,
		    			    correct_rate : correct_rate,
		    			    question : question,
		    			    team : team
		    		},
		    		success : function(data) {
		    		$('#Homework_save_btn').removeAttr("disabled");
		    			
		    				alert("操作成功!");
		    				$('#Homework_modal').modal("hide");
		    				$(".form-control").val("");//清空表单数据
		    				window.location.reload();
		    				/* $('#stationInfo_table').DataTable().ajax.reload(); */
		    			
		    	},
		    		error : function(e) {
		    			$('#Homework_save_btn').removeAttr("disabled");
		    			alert("保存异常!");
		    		}
		    	});
		    	
		    });
			
			
		    /**
			 * 修改按钮点击事件，弹出修改模态框
			 */
			$("#Homework_update_btn").click(function(){
				
				$(".form-control").val("");//清空表单数据
				$("div.error").remove();	  
				$("#myModalLabel").html("修改作业");
				
				var trs = $("#Homework_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var tr = trs.eq(0).closest('tr');
					var id = trs.children().eq(0).html();
							
				    
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "HomeworkServlet?method=getHomeworkById",
						"data":{id :id},
						/* "dataType": "text",   */
						success : function(data){
							
							
							if(data == null || data =="")
							{
								return;
							}
							
							
							/* var score=JSON.stringify(data);  */  
						    var homework = JSON.parse(data);   
						    /*     var score = data; */
							alert(homework.id);
						    
						    
							/* var object = new Date(score.scoreDate);
							var tt = object.format("YYYY-MM-DD"); */
						    $("#homeworkId").val(homework.id);
							$("#studentName").val(homework.studentName);		 //学生姓名
							$("#notescore").val(homework.notescore);        //java成绩
							$("#code_number").val(homework.code_number);		//javascript成绩
							$("#isannotate").val(homework.isannotate);
							$("#correct_rate").val(homework.correct_rate);
							$("#question").val(homework.question);
							$("#team").val(homework.team);
							/* if(tt == "1970-01-01"){ $("#scoreDate").val("");}else{		$("#scoreDate").val(tt);}  */       //修改或创建日期
							
							$('#Homework_modal').modal({show:true,backdrop:'static'});
						},
						"error": function(e) {
							alert("查询异常!");
						}
					});	
					
					$("#myModalLabel").html("修改成绩");
					$('#Homework_modal').modal({show:true,backdrop:'static'});
				}
				
			});
		    
		    
			/**
			 * 删除按钮点击事件
			 */
			$("#Homework_delete_btn").click(function(){
				
				var trs = $("#Homework_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var id = trs.children().eq(0).html();
					/* var data = $('#score_table').dataTable().fnGetData(tr); */
					
					var flag = confirm("确定要删除吗?");
					if(!flag){return false;}
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "HomeworkServlet?method=delHomework",
						"data":{id :id},
						"dataType": "text",  
						"success": function(result){
								if(!(result == 0)){
									alert("删除成功!");
									window.location.reload();
								/* 	$('#score_table').DataTable().ajax.reload(); */
								}else {
									alert("删除失败!");
									/* $('#score_table').DataTable().ajax.reload(); */
								}
						},
						"error": function(e) {
							alert("删除成功!");
						}
					});			    
				}
				
				/* $.confirm("确定要删除吗?",function(){
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "client/ScoreServlet?method=delScore",
						"data":{id :id},
						"dataType": "text",  
						"success": function(result){
								if(result == "1"){
									alert("删除成功!");
									$('#score_table').DataTable().ajax.reload(); 
							/*	}else {
									alert("删除失败!");
									 $('#score_table').DataTable().ajax.reload(); 
								}
						},
						"error": function(e) {
							alert("删除异常!");
						}
					});
				},true); */
				
			});
			
    });
    
    
    
   
	</script>
	
</html>
