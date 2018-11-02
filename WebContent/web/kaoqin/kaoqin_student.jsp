<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>出勤情况</title>
		<base href="http://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/"/>
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/irrigatedCommon.css">
		<link rel="stylesheet" href="css/default.css"> 
		<link rel="stylesheet" href="web/kaoqin/kaoqin.css">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
		<!--  <script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"type="text/javascript"></script>
	    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"rel="stylesheet">
	     <script src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"type="text/javascript"></script> 
		 -->
		<!-- dataTable -->

		<link href="">
	</head>
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
	<body>
<!-- <ul id="myTab" class="nav nav-tabs ulA">
	<li class="active">
		<a href="#student" data-toggle="tab" data-target="#student">
			 学生
		</a>
	</li>
	<li><a href="#teacherss" data-toggle="tab" data-target="#teacherss">教师</a></li>
	
</ul> -->

<div id="myTabContent" class="tab-content">
	
		<div class="container tab-pane fade in active" id="student" style="padding-right: 10px !important;padding-left: 10px !important;">
	<div class="row row-margin0 row_serch">
		<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
		<form action="AttendanceServlet?method=findAttendance" method="post">
			<div class="col-md-2 common-padding-left0">
				<div class="input-group">
				
				
					<span class="input-group-addon allQuery">姓名</span><input
						class="form-control" type="text" name="queryAttendanceName" id="queryAttendanceName"
						placeholder="姓名" />
				</div>
			</div>
			
			<div class="col-md-2 common-padding-left0">
					<div class="input-group ">
									<span class="input-group-addon allQuery">选择日期</span>
									<input onclick="chooseDate" class="form-control SystemCalculationdateinput" type="text" id="queryAttendanceDate" name="queryAttendanceDate" readonly="readonly" placeholder="选择日期" />
					</div>
			</div>
			
			<div class=" col-md-1 registerStyl">
				<button type="submit" class="btn white" id="equipInfo_search_btn">
					&nbsp;查询
				</button>
			</div>
			</form>
			
			
<!-- 			<div class=" col-md-7 text-right registerStyl common-padding-right0">
			    <button type="button" class="btn white" id="attendance_delete_btn">&nbsp;删除</button>
				<button type="button" class="btn white" id="attendance_update_btn" data-toggle="modal" data-target="#equipInfo_modal">
					&nbsp;修改
				</button>
			</div> -->
					<div class="col-md-6 registerStyl text-right common-padding-right0">
					<button type="button" class="btn white" id="attendance_add_btn" data-toggle="modal" data-target=""><img class="common-img" src="">&nbsp;新增</button>
					<button type="button" class="btn white" id="attendance_update_btn">&nbsp;修改</button>
					<button type="button" class="btn white" id="attendance_delete_btn">&nbsp;删除</button>
					<button type="button" class="btn white common-margin-right0">&nbsp;导出</button>
				</div>
			</div>
		</div>
		<div class="row row-margin0">
		<div class="col-md-12 col-xs-12 table-responsive common-padding-left-right0 common-padding-top-bottom">
			<table class="table table-bordered table-striped table-hover active "
				id="equipInfo_table">
				<thead>
					<tr>
					    <th class="hiddenTdTh">隐藏的列</th>
						<th>序号</th>
						<th>学生姓名</th>
						<th>学号</th>
						<th>联系电话</th>
						<th>出勤日期</th>
						<th>是否缺勤</th>
					</tr>
					
				</thead>
				<tbody>
				  <c:forEach items="${page.date}" var="attendance" varStatus="status">
									<tr>
									    <td class="hiddenTdTh">${attendance.attendanceId}</td>
										<td>${status.index + 1}</td>
										<td><c:out value="${attendance.attendanceName}" escapeXml="true"/></td>
										<td>${attendance.studentNumber}</td>
										<td>${attendance.studentMobilephone}</td>
										<td>${attendance.attendanceDate}</td>
										
										<td>
										<c:choose>
									<c:when test="${attendance.isabsence==0}">
					出勤
					</c:when>
									<c:otherwise>
					缺勤
					</c:otherwise>
								</c:choose>
										</td>
										
									</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
	<div class="col-md-12 text-right">
				
				<ul class="pagination">
					    <li><a href="client/AttendanceServlet?method=findAttendance&pageNum=${page.pageNum-1 }">&laquo;</a></li>
					    <c:forEach begin="1" end="${page.totalPage }" var="index">
				        <c:choose>
					    <c:when test="${index==page.pageNum }">
						         <li><a href=#>${index}</a></li>
					    </c:when>
					    <c:otherwise>
						        <li><a href="client/AttendanceServlet?method=findAttendance&pageNum=${index}">${index }</a></li>
					    </c:otherwise>
				        </c:choose>
			            </c:forEach>
					    <li><a href="client/AttendanceServlet?method=findAttendance&pageNum=${page.pageNum+1 }">&raquo;</a></li>
                </ul> 
			</div>
	</div>
	
	
		

  
  
  
	
	
	
	

   







	<!-- 模态框（Modal） -->
	<div class="modal fade" id="equipInfo_modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modalwidth">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title text-center" id="myModalLabel">学生详细档案</h4>


				</div>

				<div class="modal-body modalbackground">
				
					<div class="row firstrow">

						<div class="col-md-12 col-xs-12 ">
							<div class="col-md-2 modalhr">
								<strong>基本信息</strong>
							</div>
						</div>
						<div class="col-md-12 col-xs-12 modal-hr-updown_spacing">
								<div class="col-md-1"><hr class="hrhr"></div>
							</div>
						<div class="col-md-12 col-xs-12 common-padding-right0" >
							<div class="col-md-3 col-xs-3">
								<div class="row equipInfoAdd_row">
									<div class="col-md-1 col-xs-1"></div>
<!-- 									<form onsubmit="return false;" id="uploadForm">
										<div class="col-md-10 col-xs-10 equipInfoAdd_loadFile text-center">
											<label></label>&nbsp;&nbsp;<a class="text-left equipInfoAdd_file"></a> 
											<input id="upload" name="upload" class="equipInfo_upload" type="file" accept="image/png,image/jpg,image/gif,image/bmp,image/jpeg" style="display: none;">
										

										</div>
									</form>

 -->



									<div class="col-md-1 col-xs-1"></div>
								</div>
								<!--  <button type="button" class="btn btn-sm white"><i class=""></i>&nbsp;上传图片</button> -->
							</div>
							
							
							
							
							<form action="" method="post" id="modalForm" name="modalForm">
								<div class="col-md-9 col-xs-9 common-padding-right0" >
									<div class="col-md-12 col-xs-12 common-padding-top-bottom">
										
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<!-- <span class="input-group-addon">学生姓名</span><input
													class="form-control" type="text" name="studentName"
													id="studentName"  /> -->
													
											   <span class="input-group-addon">学生姓名</span><input
													class="form-control" type="text" name="studentName"
													id="studentName" />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon " >是否缺勤</span>
												 <select class="form-control" id="isabsence">
														<option value="0">出勤</option>
														<option value="1">缺勤</option>
														
					                            </select>

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											
										</div>
										<div class="modalHrDiv  col-md-12 col-xs-12" style="margin-top:10px!important;"><hr></div>  
					<div class="modal-footer col-md-12 col-xs-12">
						<button type="submit" class="btn white" id="attendance_save_btn">保存</button>
						<button type="button" class="btn white" data-dismiss="modal">取消</button>
					
					</div>
									
										
									</div>
								</div>
								
								
								
						</form>	
						</div>
						
					</div>
			
				
					
                    
					
					</div>
						
				</div>
			</div>
				<!-- /.modal-content -->
			</div>
		</div>
		
		
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		


	

	</body>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
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
				        	isabsence:{
				        		required : true,
				        		maxlength:50
						    },
						   
				        },
				    });
			
			return validator;
			}
			
			/* 隐藏表格第1列 */
			$(".hiddenTdTh").hide();
			
			$("#queryAttendanceDate").datetimepicker(
					{
				        language: 'zh-CN',//显示中文
				        format: 'yyyy-mm-dd',//显示格式
				        minView: "month",//设置只显示到月份
				        initialDate: new Date(),
				        autoclose: true,//选中自动关闭
				        todayBtn: true,//显示今日按钮
					}		
			)
			
			$('#myTab li:eq(1) a').tab('show');
			
			   /**
			 * 行的点击事件
			 */
			$("#equipInfo_table").delegate("tbody tr", "click",function(){
				$(this).addClass('common_checked_tr').siblings().removeClass('common_checked_tr').end();
			});
			
			/**
			 * 新增按钮点击事件，弹出新增模态框
			 */
			$("#attendance_add_btn").click(function(){
				$(".form-control").val("");//清空表单数据
				$("div.error").remove();
				var trs = $("#equipInfo_table tbody tr.common_checked_tr");
				if(trs.length>0){
					$(trs.eq(0).closest('tr')).removeClass('common_checked_tr');
				}
				$("#myModalLabel").html("新增考勤");
				$('#equipInfo_modal').modal({show:true,backdrop:'static'});
			});
			
			
			/**
			 * 模态框保存按钮点击事件
			 */
		    $("#attendance_save_btn").click(function(){
		    	var isSuccess = formValidate().form();
		    	if(!isSuccess){return;}
		    	
		    	$('#attendance_save_btn').attr("disabled",true);
		    	/* var score = "${score.id}";
		    	var id = score.id;//ID */
		    	var trs = $("#equipInfo_table tbody tr.common_checked_tr");
		    	var id = trs.children().eq(0).html();
		    	
		    	
		    	var studentName =$("#studentName").val();
		    	var isabsence=$("#isabsence").val();
		    	
		    	var url= "";
		    	if(id==null||id==""||id==undefined){
		    		url= "AttendanceServlet?method=saveAttendance"; //添加
		    	}
		    	else{
		    		url= "AttendanceServlet?method=updateAttendance";//修改
		    	}
		    
		    	
		    	$.ajax({
		    		type : "post",
		    		url : url,
		    		dataType : "text",
		    		data : {
		    			    id : id,
		    			    studentName : studentName,
		    			    
		    			    isabsence : isabsence
		    		},
		    		success : function(data) {
		    		$('#attendance_save_btn').removeAttr("disabled");
		    			
		    				alert("操作成功!");
		    				$('#equipInfo_modal').modal("hide");
		    				$(".form-control").val("");//清空表单数据
		    				window.location.reload();
		    				/* $('#stationInfo_table').DataTable().ajax.reload(); */
		    			
		    	},
		    		error : function(e) {
		    			$('#attendance_save_btn').removeAttr("disabled");
		    			alert("保存异常!");
		    		}
		    	});
		    	
		    });
			
			
		    /**
			 * 修改按钮点击事件，弹出修改模态框
			 */
			$("#attendance_update_btn").click(function(){
				
				$(".form-control").val("");//清空表单数据
				$("div.error").remove();	  
				$("#myModalLabel").html("修改考勤");
				
				var trs = $("#equipInfo_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var tr = trs.eq(0).closest('tr');
					var id = trs.children().eq(0).html();
					
				    
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "AttendanceServlet?method=getAttendanceById",
						"data":{id :id},
						// "dataType": "json",   
						success : function(jsonattendance){
							
							
							if(jsonattendance == null || jsonattendance =="")
							{
								return;
							}
							
							
							/* var score=JSON.stringify(data);  */  
						    var attendance = JSON.parse(jsonattendance);   
						    /*     var score = data; */
							alert(attendance.attendanceId);
						    
						    
							/* var object = new Date(score.scoreDate);
							var tt = object.format("YYYY-MM-DD"); */
							$("#studentName").val(attendance.attendanceName);		 
							$("#isabsence").val(attendance.isabsence);   
							/* if(tt == "1970-01-01"){ $("#scoreDate").val("");}else{		$("#scoreDate").val(tt);}  */       //修改或创建日期
							
							$('#equipInfo_modal').modal({show:true,backdrop:'static'});
						},
						"error": function(e) {
							alert("查询异常!");
						}
					});	
					
					$("#myModalLabel").html("修改考勤");
					$('#equipInfo_modal').modal({show:true,backdrop:'static'});
				}
				
			});
		    
		    
			/**
			 * 删除按钮点击事件
			 */
			$("#attendance_delete_btn").click(function(){
				
				var trs = $("#equipInfo_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var id = trs.children().eq(0).html();
					/* var data = $('#score_table').dataTable().fnGetData(tr); */
					
					var flag = confirm("确定要删除吗?");
					if(!flag){return false;}
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "AttendanceServlet?method=delAttendance",
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