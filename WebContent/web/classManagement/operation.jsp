<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>


	<head>
		<meta charset="utf-8" />
		<title>班级管理------作业情况</title>
		<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/irrigatedCommon.css">
		<link rel="stylesheet" href="css/default.css">
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap-datetimepicker.min.css" />
		<!-- dataTable -->

		<link href="">
		

	</head>

	<body>
		<div class="container" style="padding-left:0px !important;padding-right: 0px !important;">
		<div class="row row-margin0 row_serch">
			<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
			<form action="HomeworkServlet?method=lookingforHomework" method="post">
				<div class="col-md-2 common-padding-left0">
					<div class="input-group"><span class="input-group-addon allQuery">姓名</span><input class="form-control" type="text" name="queryStudentName" id="queryStudentName" placeholder="请输入学生姓名" /> </div>
				</div>
				<div class="col-md-2 common-padding-left0">
					<div class="input-group input-group-sm">
									<span class="input-group-addon">选择日期</span>
									<input onclick="chooseDate" class="form-control SystemCalculationdateinput" type="text" id="queryHomeworkDate" name="queryHomeworkDate" readonly="readonly" placeholder="选择日期" />
									
					            </div>
				</div>
				<div class="col-md-1 registerStyl">
					<button type="submit" class="btn white" id="Homework_search_btn"><img src="">&nbsp;查询</button>
				</div>
				</form>
				<div class="col-md-6 registerStyl text-right common-padding-right0">
					<button type="button" class="btn white" id="Homework_add_btn" data-toggle="modal" data-target=""><img class="common-img" src="">&nbsp;新增</button>
					<button type="button" class="btn white" id="Homework_update_btn">&nbsp;修改</button>
					<button type="button" class="btn white" id="Homework_delete_btn">&nbsp;删除</button>
					<button type="button" class="btn white common-margin-right0">&nbsp;导出</button>
				</div>
			</div>
			</div>
			<div class="row row-margin0">
			<div class="col-md-12 col-xs-12 table-responsive common-padding-left-right0 common-padding-top-bottom">
				<table class="table table-bordered table-striped table-hover active" id="Homework_table">
					<thead>
					<tr>
					   <th class="hiddenTdTh">隐藏的主键id</th>
					   <th rowspan="2">序号</th>
					   <th rowspan="2">姓名</th>
					   <th >预习笔记</th>
					   <th colspan="2">课堂代码</th>
					   <th >课后练习</th>
					   <th rowspan="2">问题收集</th>
					   
					</tr>
					<tr>
						<th >评分</th>
						<th>数量</th>
						<th>注释</th>
						<th>500道题库正确率</th>
					</tr>
					
					
					</thead>
					<tbody>
					<c:forEach items="${page.date }" var="homework" varStatus="status">
				    	<tr> 
				    	   <td class="hiddenTdTh">${homework.id}</td>
					       <td>${status.index+1 }</td>
					       <td>${homework.studentName }</td>  
					       <td>${homework.notescore }</td>  
					       <td>${homework.code_number }</td>  
					       <td>${homework.isannotate }</td>  
					       <td>${homework.correct_rate }%</td>  
					       <td>${homework.question }</td>  
					   </tr>
					
					
					</c:forEach>
					</tbody>
					
				</table>
			</div>
			</div>
			
			<div class="col-md-12 text-right">
				
				<ul class="pagination">
					    <li><a href="HomeworkServlet?method=findHomework&pagenum=${page.pageNum-1 }">&laquo;</a></li>
					    <c:forEach begin="1" end="${page.totalPage }" var="index">
				        <c:choose>
					    <c:when test="${index==page.pageNum }">
						         <li><a href=#>${index}</a></li>
					    </c:when>
					    <c:otherwise>
						        <li><a href="HomeworkServlet?method=findHomework&pagenum=${index}&findstyle=${findstyle}">${index }</a></li>
					    </c:otherwise>
				        </c:choose>
			            </c:forEach>
					    <li><a href="HomeworkServlet?method=findHomework&pagenum=${page.pageNum+1}">&raquo;</a></li>
                </ul> 
			</div>
			<%-- <div style="position: absolute;left:650px">
		<a href="HomeworkServlet?method=findHomework&pagenum=1">首页</a>
		<a href="HomeworkServlet?method=findHomework&pagenum=${page.pageNum-1 }">上一页</a>
		
		<c:forEach begin="1" end="${page.totalPage }" var="index">
		
		 
		    <c:choose>
		    	<c:when test="${index==page.pageNum }">
		    	    
		    	  <span style="color:red">[${index}]</span>
					
		    	</c:when>
		    	<c:otherwise>
		    	<a href="HomeworkServlet?method=findHomework&pagenum=${index}&findstyle=${findstyle}">${index }</a>
		    	    
		    	</c:otherwise>
		    
		    
		    </c:choose>
		</c:forEach> 
		
		<a  href="HomeworkServlet?method=findHomework&pagenum=${page.pageNum+1}">下一页</a>
		<a href="HomeworkServlet?method=findHomework&pagenum=${page.totalPage }&findstyle=${findstyle}">末页</a>
		共${page.totalPage }页，${page.totalRecode }条记录        跳转到第<input value="${page.pageNum}" name="pn" id="pn_input" style="width: 20px"/>页
		<input id="pn_btn" type="button"  value="确定">
		
		
		
		<script type="text/javascript">
		
		$("#pn_btn").click(function(){
			//获取页码值
			var pn=$("#pn_input").val();
			
			window.location="HomeworkServlet?method=findHomework&pagenum="+pn+"&findstyle=${findstyle}"
			
			
		})		   	
		
		</script>
			
		</div> --%>
		</div>
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="Homework_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modalwidth">
				<div class="modal-content ">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					     
						<h4 class="modal-title text-center" id="myModalLabel">学生作业</h4>
					    
				
					</div>
					<div class="modal-body modalbackground">
						<div class="row firstrow">
						
							<div class="col-md-12 col-xs-12">
								<div class="col-md-2 modalhr"><strong>基本信息</strong></div>
							</div>
							<div class="col-md-12 col-xs-12 modal-hr-updown_spacing">
								<div class="col-md-1"><hr class="hrhr"></div>
							</div>
							 <form action="" method="" id="modalForm">
							<div class="col-md-12 col-xs-12 common-padding-top-bottom">
								<div class="col-md-4 col-xs-4">
								
								<input type="hidden" name="HomeworkId" id="HomeworkId" value="${homework.id }">
								<div class="input-group input-group-sm">
										<span class="input-group-addon" >学生姓名</span><input class="form-control" type="text" name="studentName" id="studentName" placeholder="" />
									</div>
								</div>
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学生班级</span><input class="form-control" type="text" name="team" id="team" placeholder="" />
									</div>
								</div>
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
									</div>
								</div>
							 </div>
							 <div class="col-md-12 col-xs-12 common-padding-top-bottom">
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon" >代码复习</span><input class="form-control" type="text" name="code_number" id="code_number" placeholder="" />
									</div>
								</div>
								
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是否注释</span><input class="form-control" type="text" name="isannotate" id="isannotate" placeholder="" />
									</div>
								</div>
								</div>
								<div class="col-md-12 col-xs-12 common-padding-top-bottom">
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">预习笔记</span><input class="form-control" type="text" name="notescore" id="notescore" placeholder="" />
									</div>
								</div>
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题库正确率</span><input class="form-control" type="text" name="correct_rate" id="correct_rate" placeholder="" />
									</div>
								</div>								
								
							 </div>
							 <div class="col-md-12 col-xs-12 common-padding-top-bottom">
							    <div class="col-md-8 col-xs-8">
							    <div class="input-group input-group-sm">
										<span class="input-group-addon">问题收集</span><input class="form-control" type="text" name="question" id="question" placeholder="" />
									</div>
							    </div>
							 </div> 
							<div class="modalHrDiv  col-md-12 col-xs-12"><hr></div>
					<div class="modal-footer  col-md-12 col-xs-12">
						<button type="submit" class="btn white" id="Homework_save_btn">
						<img src="resource/images/irrigationmanager/button/save.png">
					保存
				</button>
				        <button type="button" class="btn white" data-dismiss="modal">
				        <img src="resource/images/irrigationmanager/button/return.png">
				              取消
				</button>
					</div>
							</form>
							 
				
					
					
				<!-- 	<div class="modalHrDiv  col-md-12 col-xs-12"><hr></div>
					<div class="modal-footer  col-md-12 col-xs-12">
						<button type="button" class="btn white" id="sulice_save_btn">
						<img src="../../resource/images/irrigationmanager/button/save.png">
					保存
				</button>
				        <button type="button" class="btn white" data-dismiss="modal">
				        <img src="../../resource/images/irrigationmanager/button/return.png">
				              取消
				</button>
					</div> -->
				</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
</div>

	</body>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
    <!--<script type="text/javascript" src="homeWork.js"></script>-->
    <!--datetimeepicker日期控件引用-->
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
    	<!-- 表单验证 -->
<script type="text/javascript" src="assets/plugins/validate/jquery.metadata.js"></script>
<script type="text/javascript" src="assets/plugins/validate/jquery.validate.min.js"></script>
<script type="text/javascript" src="assets/plugins/validate/jquery.validate.message.cn.js"></script>
<script type="text/javascript" src="assets/plugins/form/jquery.form.js"></script>
	<script type="text/javascript">
	
	
	
		
			function formValidate(){
				  $.validator.setDefaults({  
			    		 errorPlacement :	function(error, element) { 
			    		     error.appendTo(element.parent()); 
			    		 }
			    		 }); 
				 validator = $("#modalForm").validate({//"验证"
						focusInvalid:false,
				        rules: {
				        	studentName:{
				        		required: true,
				        		maxlength:32
				        	},
				        	
						   
				        },
				    });
			
			return validator;
			}

		
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
				$(".form-control").val("");//清空表单数据
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
		    	var isSuccess = formValidate().form();
		    	if(!isSuccess){return;}
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
			

    
    
    
   
	</script>
	

</html>
