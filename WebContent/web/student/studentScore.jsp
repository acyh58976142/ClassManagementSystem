<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>

	<head>
	    <base
	    href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<meta charset="utf-8" />
		<title>成绩管理------学生成绩</title>
		<link href="assets/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet">
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/irrigatedCommon.css">
		<link rel="stylesheet" href="css/default.css">
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap-datetimepicker.min.css" />
		<!-- dataTable -->
		
		<link rel="stylesheet" href="assets/css/font-awesome/css/font-awesome.min.css">
        
		

	</head>

	<body>
		<div class="container" style="padding-left:0px !important;padding-right: 0px !important;">
		<div class="row row-margin0 row_serch">
			<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
			   <form action="client/ScoreServlet?method=findScore" method="post">
				<div class="col-md-2 common-padding-left0">
					<div class="input-group"><span class="input-group-addon allQuery">姓名</span><input class="form-control" type="text" name="queryStudentName" id="queryStudentName" placeholder="请输入学生姓名" /> </div>
				</div>
				<!-- <div class="col-md-2 common-padding-left0">
					<div class="input-group"><span class="input-group-addon allQuery">学号</span><input class="form-control" type="text" name="queryStudentNumber" id="queryStudentNumber" placeholder="请输入学生学号" /> </div>
				</div> -->
				<div class="col-md-2 common-padding-left0">
					<div class="input-group ">
									<span class="input-group-addon allQuery">选择日期</span>
									<input onclick="chooseDate" class="form-control SystemCalculationdateinput" type="text" id="queryScoreDate" name="queryScoreDate" readonly="readonly" placeholder="选择日期" />
					</div>
				    </div>
				
				<div class="col-md-1 registerStyl">
					<button type="submit" class="btn white" id="sluice_search_btn">&nbsp;查询</button>
				</div>
				</form>	
				<div class="col-md-7 registerStyl text-right common-padding-right0">
					<button type="button" class="btn white" id="score_add_btn" data-toggle="modal" data-target="#sluice_modal"><img class="common-img" src="">&nbsp;新增</button>
					<button type="button" class="btn white" id="score_update_btn">&nbsp;修改</button>
					<button type="button" class="btn white" id="score_delete_btn">&nbsp;删除</button>
					<button type="button" class="btn white common-margin-right0">&nbsp;导出</button>
				</div>
			</div>
			</div>
			<div class="row row-margin0">
			<div class="col-md-12 col-xs-12 table-responsive common-padding-left-right0 common-padding-top-bottom">
				<table class="table table-bordered table-striped table-hover active" id="score_table">
					<thead>
					<tr>
					   <th class="hiddenTdTh">隐藏的主键id</th>
					   <th>序号</th>
					   <th>学生姓名</th>
					   <th>学号</th>
					   <th>java</th>
					   <th>javascript</th>
					   <th>HTML</th>
					   <th>数据库管理</th>
					   <th>日期</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${page.date }" var="score" varStatus="status">
					<tr>
					    <td class="hiddenTdTh">${score.id}</td>
						<td>${status.index + 1}</td>
						<td>${score.studentName}</td>
						<td>${score.studentNumber}</td>
						<td>
						<c:choose>
						<c:when test="${score.javaScore==null}">
					       缺考
					    </c:when>
						<c:otherwise>
					    ${score.javaScore}
					    </c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${score.javascriptScore==null}">
					       缺考
					    </c:when>
						<c:otherwise>
					    ${score.javascriptScore}
					    </c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${score.HTMLScore==null}">
					       缺考
					    </c:when>
						<c:otherwise>
					    ${score.HTMLScore}
					    </c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${score.SQLScore==null}">
					       缺考
					    </c:when>
						<c:otherwise>
					    ${score.SQLScore}
					    </c:otherwise>
						</c:choose>
						</td>
						<td>
						<c:choose>
						<c:when test="${score.SQLScore==null}">
					       缺考
					    </c:when>
						<c:otherwise>
					    ${score.SQLScore}
					    </c:otherwise>
						</c:choose>
						</td>
					    <td>
						<c:choose>
						<c:when test="${score.scoreDate==null}">
					       缺考
					    </c:when>
						<c:otherwise>
					    ${score.scoreDate}
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
					    <li><a href="client/ScoreServlet?method=findScore&pageNum=${page.pageNum-1 }">&laquo;</a></li>
					    <c:forEach begin="1" end="${page.totalPage }" var="index">
				        <c:choose>
					    <c:when test="${index==page.pageNum }">
						         <li><a href=#>${index}</a></li>
					    </c:when>
					    <c:otherwise>
						        <li><a href="client/ScoreServlet?method=findScore&pageNum=${index}">${index }</a></li>
					    </c:otherwise>
				        </c:choose>
			            </c:forEach>
					    <li><a href="client/ScoreServlet?method=findScore&pageNum=${page.pageNum+1 }">&raquo;</a></li>
                </ul> 
			</div>
			<div class="page-container">
	
	<div id="container" style="min-width:700px;height:400px"></div>
</div>
			
			
		</div>
		
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="score_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog modalwidth">
				<div class="modal-content ">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					     
						<h4 class="modal-title text-center" id="myModalLabel">学生分数</h4>
					
				
					</div>
					<div class="modal-body modalbackground">
						<div class="row firstrow">
						  <form action="" id="modalForm">
							<!--<div class="col-md-12 col-xs-12">
								<div class="col-md-2 modalhr"><strong>基本信息</strong></div>
							</div>-->
							<!--<div class="col-md-12 col-xs-12 modal-hr-updown_spacing">
								<div class="col-md-1"><hr class="hrhr"></div>
							</div>-->
							<input type="hidden" name="scoreId" id="scoreId" value="${score.id }"/>
							<div class="col-md-12 col-xs-12 common-padding-top-bottom">
								<div class="col-md-6 col-xs-6">
								<%-- <div class="input-group input-group-sm">
										<span class="input-group-addon" >学生姓名</span><input class="form-control" type="text" name="studentName" id="studentName" value="${score.studentName}" placeholder="" />
									</div> --%>
									<div class="input-group">
										<span class="input-group-addon " >学生姓名</span>
										 <select class="form-control" id="studentName" name="studentName" value="${score.studentName}">
												  <c:forEach items="${listName }" var="stu" varStatus="status">
												       <option value="${stu.studentName}">${stu.studentName}</option>
												</c:forEach> 
										</select>
									</div>
								</div>
								<!-- <div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="common-formRequired">*</i>学号</span><input class="form-control" type="text" name="input_name" id="input_name" placeholder="" />
									</div>
								</div> -->
								
							 </div>
							 
							 <div class="col-md-12 col-xs-12 common-padding-top-bottom">
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;java</span><input class="form-control" type="text" name="javaScore" id="javaScore" value="${score.javaScore }" placeholder="" />
									</div>
								</div>
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;javascript</span><input class="form-control" type="text" name="javascriptScore" id="javascriptScore" value="${score.javascriptScore }" placeholder="" />
									</div>
								</div>
								<div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;HTML</span><input class="form-control" type="text" name="HTMLScore" id="HTMLScore" value="${score.HTMLScore}" placeholder="" />
									</div>
								</div>
							</div>	
							<div class="col-md-12 col-xs-12 common-padding-top-bottom">
								<!-- <div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;数据库</span><input class="form-control" type="text" name="SQLScore" id="input_name" placeholder="" />
									</div> 
								</div> -->
								 <div class="col-md-4 col-xs-4">
								<div class="input-group input-group-sm">
										<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;SQL</span><input class="form-control" type="text" name="SQLScore" id="SQLScore" value="${score.SQLScore }" placeholder="" />
									</div>
								</div>
								<div class="col-md-4 col-xs-4">
					            <div class="input-group input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;选择日期</span>
									<input onclick="chooseDate" class="form-control SystemCalculationdateinput" type="text" id="scoreDate" name="scoreDate" value="${score.scoreDate}" readonly="readonly" placeholder="选择日期" />
									
					            </div>
				                </div>
				                </div>
							</form> 
					
					
					<div class="modalHrDiv  col-md-12 col-xs-12"><hr></div>
					<div class="modal-footer  col-md-12 col-xs-12">
						<button type="submit" class="btn white" id="score_save_btn">
						<img src="">
					保存
				</button>
				        <button type="button" class="btn white" data-dismiss="modal">
				        <img src="">
				              取消
				</button>
					</div>
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
  
    <!--datetimeepicker日期控件引用-->
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
    
    <!-- datatable -->
	<script type="text/javascript" src="assets/plugins/datatables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
	<!-- 表单验证 -->
	<script type="text/javascript" src="assets/plugins/validate/jquery.metadata.js"></script>
	<script type="text/javascript" src="assets/plugins/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="assets/plugins/validate/jquery.validate.message.cn.js"></script>
	<script type="text/javascript" src="assets/plugins/form/jquery.form.js"></script>
    
     
	
   
	<script type="text/javascript" >
		
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
			
			/* 隐藏表格第1列 */
			$(".hiddenTdTh").hide();
			
			/*日期插件input_queryDate  */
			$("#queryScoreDate ").datetimepicker(
					{
				        language: 'zh-CN',//显示中文
				        format: 'yyyy-mm-dd',//显示格式
				        minView: "month",//设置只显示到月份
				        initialDate: new Date(),
				        autoclose: true,//选中自动关闭
				        todayBtn: true,//显示今日按钮
					}		
			)
			$("#scoreDate").datetimepicker(
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
			$("#score_table").delegate("tbody tr", "click",function(){
				$(this).addClass('common_checked_tr').siblings().removeClass('common_checked_tr').end();
			});
			
			/**
			 * 新增按钮点击事件，弹出新增模态框
			 */
			$("#score_add_btn").click(function(){
				
				$("div.error").remove();
				var trs = $("#score_table tbody tr.common_checked_tr");
				if(trs.length>0){
					$(trs.eq(0).closest('tr')).removeClass('common_checked_tr');
				}
				$("#myModalLabel").html("新增成绩");
				$('#score_modal').modal({show:true,backdrop:'static'});
			});
			
			
			/**
			 * 模态框保存按钮点击事件
			 */
		    $("#score_save_btn").click(function(){
		    	var isSuccess = formValidate().form();
		    	if(!isSuccess){return;}
		    	
		    	
		    	$('#score_save_btn').attr("disabled",true);
		    	/* var score = "${score.id}";
		    	var id = score.id;//ID */
		    	var trs = $("#score_table tbody tr.common_checked_tr");
		    	var id = trs.children().eq(0).html();
		    	
		    	
		    	
		    	var studentName =$("#studentName").val();
		    	var javaScore =$("#javaScore").val();
		    	var javascriptScore =$("#javascriptScore").val();
		    	var HTMLScore =$("#HTMLScore").val();
		    	var SQLScore =$("#SQLScore").val();
		    	var scoreDate = $("#scoreDate").val();
		    	
		    	
		    	var url= "";
		    	if(id==null||id==""||id==undefined){
		    		url= "client/ScoreServlet?method=saveScore"; //添加
		    	}
		    	else{
		    		url= "client/ScoreServlet?method=updateScore";//修改
		    	}
		    
		    	
		    	$.ajax({
		    		type : "post",
		    		url : url,
		    		dataType : "text",
		    		data : {
		    			    id : id,
		    			    studentName:studentName,
		    			    javaScore : javaScore,
		    			    javascriptScore : javascriptScore,
		    			    HTMLScore : HTMLScore,
		    			    SQLScore : SQLScore,
		    			    scoreDate : scoreDate
		    		},
		    		success : function(result) {
		    			
		    				 $('#stationInfo_save_btn').removeAttr("disabled");
			    				alert("操作成功!");
			    				$('#score_modal').modal("hide");
			    				$(".form-control").val("");//清空表单数据
			    				window.location.reload();
			    				/* $('#stationInfo_table').DataTable().ajax.reload(); */
		    			
		    			},
		    		       
		    			
		    	
		    		error : function() {
		    			$('#score_save_btn').removeAttr("disabled");
		    			alert("保存异常!");
		    		}
		    	});
		    	
		    });
			
			
		    /**
			 * 修改按钮点击事件，弹出修改模态框
			 */
			$("#score_update_btn").click(function(){
				
				$(".form-control").val("");//清空表单数据
				$("div.error").remove();	  
				$("#myModalLabel").html("修改成绩");
				
				var trs = $("#score_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var tr = trs.eq(0).closest('tr');
					var id = trs.children().eq(0).html();
							
				    
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "client/ScoreServlet?method=toUpdatePage",
						"data":{id :id},
						/* "dataType": "text",   */
						"success" :function(data){
							
							
							if(data == null || data =="")
							{
								return;
							}
							
							
							/* var score=JSON.stringify(data);  */  
						    var score = JSON.parse(data);   
						    /*     var score = data; */
							alert(new Date(score.scoreDate));
						    
						    
							/* var object = new Date(score.scoreDate);
							var tt = object.format("YYYY-MM-DD"); */
						    $("#scoreId").val(score.id);
							$("#studentName").val(score.studentName);		 //学生姓名
							$("#javaScore").val(score.javaScore);        //java成绩
							$("#javascriptScore").val(score.javascriptScore);		//javascript成绩
							/* if(tt == "1970-01-01"){ $("#scoreDate").val("");}else{		$("#scoreDate").val(tt);}  */       //修改或创建日期
							
							$("#HTMLScore").val(score.HTMLScore);        //html成绩
							$("#scoreDate").val(score.scoreDate);        //修改或创建日期
							$("#SQLScore").val(score.SQLScore);        //数据库成绩
							$('#score_modal').modal({show:true,backdrop:'static'});
						},
						"error": function(e) {
							alert("查询异常!");
						}
					});	
					
					$("#myModalLabel").html("修改成绩");
					$('#score_modal').modal({show:true,backdrop:'static'});
				}
				
			});
		    
		    
			/**
			 * 删除按钮点击事件
			 */
			$("#score_delete_btn").click(function(){
				
				var trs = $("#score_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var id = trs.children().eq(0).html();
					/* var data = $('#score_table').dataTable().fnGetData(tr); */
					
					var flag = confirm("确定要删除吗?");
					if(!flag){return false;}
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "client/ScoreServlet?method=delScore",
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
							alert("删除异常!");
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