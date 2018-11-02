<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>教师管理------教师档案</title>
   <base href="http://<%=request.getServerName() %>:<%=request.getServerPort() %><%=request.getContextPath() %>/">
    <script type="text/javascript" src="js/jQuery/jquery-1.7.1.min.js"></script>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<link type="text/css" rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
		<link type="text/css" rel="stylesheet" href="css/index.css">
		<link type="text/css" rel="stylesheet" href="css/irrigatedCommon.css">
		<link type="text/css" rel="stylesheet" href="css/default.css">
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap-datetimepicker.min.css" />
		<!-- dataTable -->
     
	</head>

	<body>
		<div class="container" style="padding-right: 10px !important;padding-left: 10px !important;">
	<div class="row row-margin0 row_serch">
	
		<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
		
		 <form action="manager/TeacherServlet?method=findTeacher" method="post"> 
			<div class="col-md-2 common-padding-left0">
				<div class="input-group">
					<span class="input-group-addon allQuery">姓名</span><input
						class="form-control" type="text" name="queryTeacherName" id="queryTeacherName"
						placeholder="姓名" />
				</div>
			</div>
			
			<!-- <div class="col-md-2">
				<div class="input-group">
					<span class="input-group-addon allQuery">工号</span><input
						class="form-control" type="text" name="queryJobNumber" id="queryJobNumber"
						placeholder="学号" />
				</div>
			</div> -->
			
			<div class=" col-md-1 registerStyl">
				<button type="submit" class="btn white" id="">
					&nbsp;查询
				</button>
			</div>
	   </form>
	       
			
			<div class=" col-md-9 text-right registerStyl common-padding-right0">
				<button type="button" class="btn white" id="teacher_add_btn"
					data-toggle="modal" data-target="#equipInfo_modal">
					&nbsp;新增
				</button>
				<button type="button" class="btn white" id="teacher_update_btn">
					&nbsp;修改
				</button>
				<button type="button" class="btn white" id="teacher_delete_btn">
					&nbsp;删除
				</button> 
				<button type="button" class="btn white common-margin-right0" id="">
					&nbsp;导出
				</button>
			</div>
			</div>
		</div>
		
		<div class="row row-margin0">
		<div class="col-md-12 col-xs-12 table-responsive common-padding-left-right0 common-padding-top-bottom">
			<table class="table table-bordered table-striped table-hover active "
				id="teacher_table">
				<thead>
					<tr>
					 <th class="hiddenTdTh">隐藏的主键id</th>
						<th>序号</th>
						<th>教师姓名</th>
						<th>工号</th>
						<th>职位</th>
						<th>出生日期</th>
						<th>婚姻状况</th>
						<th>联系方式</th>
						<th>电子邮箱</th>
												
					</tr>
					</thead>
					<tbody>
				 <c:forEach items="${page.date }" var="teacher" varStatus="status"> 		
						<tr>
						<td class="hiddenTdTh">${teacher.id}</td>
							<td>${status.index + 1}</td>
							<td>${teacher.teatherName}</td>
							<td>${teacher.jobNumber }</td>
							<td>${teacher.position }</td>
							<td>${teacher.birthDate }</td>
							<td>
							    <c:choose>
							    <c:when test="${teacher.isMarry==0 }">未婚</c:when>
							    <c:otherwise>已婚</c:otherwise>
							    </c:choose>
							</td>
							<td>${teacher.mobilephone }</td>
							<td>${teacher.email }</td>
							<!--<td>
							 <a class='lookModal' style='color:red; cursor:pointer' data-toggle='modal' 
						data-target='#myModalB'  href="manager/TeacherServlet?method="">详情</a></td> -->
					</tr>
					</c:forEach>  
					</tbody>	
			</table>
			
		</div  >
		<!-- 分页显示 -->
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
  
       <script type="text/javascript">	
		$("#pn_btn").click(function(){
			//获取页码值
			var pn=$("#pn_input").val();
			
			window.location="manager/TeacherServlet?method=findAll&pageNum="+pn	
		})
		</script>
    
	</div>

	<!-- 通过新增打开模态框（Modal） --> 
	<div class="modal fade" id="teacher_modal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modalwidth">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title text-center" id="myModalLabel">教师档案</h4>
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
								<img class="modalimg"  src="upload/nopic.jpg"  width="192" height="204"/>
								<div class="row equipInfoAdd_row">
									<div class="col-md-1 col-xs-1"></div>
									
									<form onsubmit="return false;" id="uploadForm" method="post" enctype="multipart/form-data">
										<div class="col-md-10 col-xs-10 equipInfoAdd_loadFile text-center">
											<label></label>&nbsp;&nbsp;<a class="text-left equipInfoAdd_file"></a> 
											<input id="upload" name="upload" class="equipInfo_upload" type="file" accept="image/png,image/jpg,image/gif,image/bmp,image/jpeg" style="display: none;" />
											<button class="btn btn-info equipInfoAdd_uploadFile btn-line-height">
												<i class="fa fa-upload"></i>&nbsp;上传图片
											</button>
										</div>
									</form>

									<div class="col-md-1 col-xs-1"></div>
								</div>
								<!--  <button type="button" class="btn btn-sm white"><i class=""></i>&nbsp;上传图片</button> -->
							</div>
							
				<form action="" method="">
							<input type="hidden" name="teacherId" id="teacherId"  value="${teacher.id }">
							
								<div class="col-md-9 col-xs-9 common-padding-right0" >
									<div class="col-md-12 col-xs-12 common-padding-top-bottom">
									
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon"><i
													class="common-formRequired">*</i>教师姓名</span><input
												class="form-control" type="text" name="teacherName" value="${teacher.teacherName}"
													id="teacherName" placeholder="" />
											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">工号</span><input
													class="form-control" type="text" name="jobNumber" value="${teacher.jobNumber}"
													id="jobNumber" placeholder="" />
											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;职位</span><input
													class="form-control" type="text" name="position" value="${teacher.position}"
													id="position" placeholder="" />
											</div>
										</div>
										
										 <div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">出生日期</span><input
													class="form-control" type="text" name="birthDate"  value="${teacher.birthDate}"
													id="birthDate" placeholder="选择日期" />
											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;婚姻状况</span>
												<select class="form-control" name="isMarry"
													id="isMarry">
													<option value="${teacher.isMarry}">-请选择-</option>
													<option value="0">未婚</option>
													<option value="1">已婚</option>
												</select>
											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">联系方式</span><input
													class="form-control" type="text" name="mobilephone" value="${teacher.mobilephone}"
													id="mobilephone" placeholder="" />
											</div>
										</div>
										
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;电子邮箱</span><input
													class="form-control" type="text" name="email" value="${teacher.email}"
													id="email" placeholder="" />
											</div>
										</div>
										
									
										
									</div>
								</div>
								
							<div class="row firstrow">
                   <div class="modalHrDiv  col-md-12 col-xs-12" style="margin-top:10px!important;"><hr></div>
					<div class="modal-footer col-md-12 col-xs-12">
						<button type="submit" class="btn white" id="teacher_save_btn">保存</button>
						<button type="button" class="btn white" data-dismiss="modal">取消</button>
					</div>
					</div>
								
							</form>
							
						</div>
					</div>
					
				</div>
			</div>
				<!-- /.modal-content -->
			</div>
		</div>
	</div>


	<!-- 通过查看详情打开模态框（Modal） -->
	<div class="modal fade" id="myModalB" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modalwidth">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title text-center" id="myModalLabelB">教师信息详情</h4>	
				</div>
				
				<div class="modal-body modalbackground">
					<div class="row firstrow">
						<div class="col-md-12 col-xs-12">
							<div class="col-md-2 modalhr">
								<strong>基本信息</strong>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
								<div class="col-md-1"><hr class="hrhr"></div>
							</div>
							
						<div class="col-md-12 col-xs-12">
							<div class="col-md-3 col-xs-3">
								<img class="modalimg" src="" />
							</div>
							
							<div class="col-md-9 col-xs-9">
								<div class="col-md-12 col-xs-12">
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon"><i
												class="common-formRequired">*</i>教师姓名</span><input
												class="form-control" type="text" name="teacherName" value="${teacher.teacherName}"
												id="teacherName" placeholder="" readonly="readonly" />
										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">工号</span><input
												class="form-control" type="text" name="jobNumber"  value="${teacher.jobNumber}"
												id="input_codeB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;职位</span><input
												class="form-control" type="text" name="position"  value="${teacher.position}"
												id="input_modelB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">出生日期</span><input
												class="form-control" type="text" name="birthDate" value="${teacher.birthDate}"
												id="input_brandB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;婚姻状况</span><input
												class="form-control" type="text" name="isMarry"  value="${teacher.isMarry}"
												id="input_brandB" placeholder="" readonly="readonly" />
										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">联系方式</span><input
												class="form-control" type="text" name="mobilephone" value="${teacher.mobilephone}"
												id="input_deviceGroupB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;电子邮箱</span>
											<input	class="form-control" type="text" name="email" value="${teacher.email}"	
											id="input_deviceLevelB" placeholder="" readonly="readonly" />
										</div>
									</div>		
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-6 col-xs-6">
								<span>二维码</span>&nbsp;&nbsp; <img id="imgqrB" name="imgqrB" src=""
									style="height: 100px; width: 100px" />&nbsp;

							</div>
						</div>
					<div class="modalHrDiv  col-md-12 col-xs-12"><hr></div>  
					<div class="modal-footer col-md-12 col-xs-12">
						<button type="button" class="btn white" data-dismiss="modal">关闭</button>
					</div>
					
					</div>
				</div>
				
				<!-- /.modal-content -->
			</div>
		</div>
	</div>
	</div>
	</div>

	</body>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/jQuery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="assets/plugins/form/jquery.form.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	 <!--datetimeepicker日期控件引用-->
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
	<!--addBirthDate  -->
	
    
	<script type="text/javascript">
		$(function() {
		
			/* 隐藏表格第1列 */
			$(".hiddenTdTh").hide();
			
			$("#birthDate").datetimepicker(
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
		});
		
		/**
		 * 行的点击事件
		 */
		$("#teacher_table").delegate("tbody tr", "click",function(){
			$(this).addClass('common_checked_tr').siblings().removeClass('common_checked_tr').end();
		});
		
		/**
		 * 新增按钮点击事件，弹出新增模态框
		 */
		$("#teacher_add_btn").click(function(){
			
			$("div.error").remove();
			$("equipInfo_upload").show();
			var trs = $("#teacher_table tbody tr.common_checked_tr");
			if(trs.length>0){
				$(trs.eq(0).closest('tr')).removeClass('common_checked_tr');
			}
			$("#myModalLabel").html("新增教师信息");
			$('#teacher_modal').modal({show:true,backdrop:'static'});
		});
		

		/**
		 * 模态框保存按钮点击事件
		 */
	    $("#teacher_save_btn").click(function(){
	    	$('#teacher_save_btn').attr("disabled",true);
	    	
	    	var trs = $("#teacher_table tbody tr.common_checked_tr");
	    	var id = trs.children().eq(0).html();
	    	alert(id);
	    	
	    	var filepath = $(".modalimg")[0].src;
			var arr = filepath.split('/');
			var relfile=arr[arr.length-1];
	    	
	    	var teacherName =$("#teacherName").val();
	    	var jobNumber =$("#jobNumber").val();
	    	var position =$("#position").val();
	    	var	birthDate=$("#birthDate").val();
	    	var isMarry =$("#isMarry").val();
	    	
	    	var mobilephone =$("#mobilephone").val();
	    	 /* var mpreg=/^[1][3,4,5,7,8][0-9]{9}$/;  
	          if (!mpreg.test(mobilephone)) {  
	              return false;  
	          } */
	    	
	    	var email =$("#email").val();
	          
	    	/* var emailReg=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
			if(!emailReg.test(email)){
				alert("请输入正确的邮箱格式");
				return false;
			} */
	    	
	    	var url= "";
	    	if(id==null||id==""||id==undefined){
	    		url= "manager/TeacherServlet?method=saveTeacher"; //添加
	    	}
	    	else{
	    		url= "manager/TeacherServlet?method=updateTeacher";//修改
	    		
	    	}
	    
	    	$.ajax({
	    		type : "post",
	    		url : url,
	    		dataType : "text",
	    		data : {
	    			 id : id,
	    			teacherName : teacherName,
	    			jobNumber : jobNumber,
	    			position : position,
	    			birthDate:birthDate,
	    			isMarry : isMarry,
	    			mobilephone : mobilephone,
	    			email : email,
	    			relfile : relfile
	    		},
	    		
	    		 success : function(data) {
	    		$('#teacher_save_btn').removeAttr("disabled");
	    			
	    				alert("操作成功!");
	    				
	    				$('#teacher_modal').modal("hide");
	    				$(".form-control").val("");//清空表单数据
	    				window.location.reload();
	    				/* $('#stationInfo_table').DataTable().ajax.reload(); */
	    			
	    	},
	    		error : function(e) {
	    			$('#teacher_save_btn').removeAttr("disabled");
	    			alert("保存异常!");
	    		}
	    	});
	    	
	    });
		
		 /**
		 * 修改按钮点击事件，弹出修改模态框
		 */
		 $("#teacher_update_btn").click(function(){
				
				$(".form-control").val("");//清空表单数据
				$("div.error").remove();	  
				$("#myModalLabel").html("修改教师信息");
				
				var trs = $("#teacher_table tbody tr.common_checked_tr");
				if(trs.length<=0){
					alert("请选择一条数据!");
				}else{
					var tr = trs.eq(0).closest('tr');
					var id = trs.children().eq(0).html();
						
					$.ajax({
						"type": 'post',	//post防止中文参数乱码
						"url": "manager/TeacherServlet?method=getTeacherById", 
						"data":{id :id},
						/* "dataType": "text",   */
						"success" :function(data){
							
							if(data == null || data =="")
							{
								return;
							}
							
							/* var score=JSON.stringify(data);  */  
						    var teacher = JSON.parse(data);   
						    /*     var score = data; */
						    
							alert(teacher.teacherName);
						
							/* var object = new Date(score.scoreDate);
							var tt = object.format("YYYY-MM-DD"); */
						    $("#teacherId").val(teacher.id);
							$("#teacherName").val(teacher.teatherName);	
							$("#jobNumber").val(teacher.jobNumber);	
							$("#position").val(teacher.position);       
							$("#birthDate").val(teacher.birthDate);
							$("#isMarry").val(teacher.isMarry);					
							$("#mobilephone").val(teacher.mobilephone);       
							$("#email").val(teacher.email);       
							
							var img = "upload/"+ teacher.imgPath;
							$(".modalimg").attr("src",img);
							
							$('#teacher_modal').modal({show:true,backdrop:'static'});
						},
						"error": function(e) {
							alert("查询异常!");
						}
					});	
					
					$("#myModalLabel").html("修改教师信息");
					$('#teacher_modal').modal({show:true,backdrop:'static'});
				}
				
			});
		
		/**
		 * 删除按钮点击事件
		 */
		$("#teacher_delete_btn").click(function(){
			
			var trs = $("#teacher_table tbody tr.common_checked_tr");
			if(trs.length<=0){
				alert("请选择一条数据!");
			}else{
				var id = trs.children().eq(0).html();
				
				var flag = confirm("确定要删除吗?");
				if(!flag){return false;}
				$.ajax({
					"type": 'post',	//post防止中文参数乱码
					"url": "manager/TeacherServlet?method=delTeacher",
					"data":{id :id},
					"dataType": "text",  
					"success": function(result){
							if(!(result == 0)){
								alert("删除成功!");
								window.location.reload();
							
							}else {
								alert("删除失败!");
								
							}
					},	
					"error": function(e) {
						alert("删除异常!");
					}
				})		    
			}
		})
		
		
		/*上传图片  */
		$(".equipInfoAdd_uploadFile").click(function(e){
			
			var thiz = e.currentTarget;
			var prev = $(thiz).prev();
			var fileInfo = $(thiz).closest(".equipInfoAdd_loadFile").find(".equipInfoAdd_file");
			// 上传附件之前  当前是否已经上传文件,存在时提示
			
			if($(fileInfo).text() == ""){
				$("#upload").click();
			}else{
				alert("已存在图片,请删除后再执行该操作!");
			}
			
		})
		
		$(".equipInfo_upload").change(function(e){
			
			var thiz = e.currentTarget;
			/*var fileSize = 0;
			var filemaxsize = 1024*2;*/	
			var file = $(thiz).val();
			var relfile = file.substring(12);
			
			var isnext = false;
			var filetypes =[".jpg",".png",".gif",".jpeg",".bmp"];
			var fileend = file.substring(file.lastIndexOf("."));
			if(file == ''){
				alert('请选择需要上传的文件');
				return;
			}
			for(var i =0; i<filetypes.length;i++){
		        if(filetypes[i]==fileend){
		            isnext = true;
		            break;
		        }
		    }
			 if(!isnext){
				 alert("只能上传.jpg .png .jpeg .gif .bmp类型的文件!");
		         return;
		     }
			 
			    var filePath = $("#upload").val();
				var relfile = file.substring(12);
				alert(filePath);
			   	$(".modalimg").attr("src",filePath);   /* 将图片路径存入src中，显示出图片 */
	            
			
			 /*fileSize = $(thiz).files[0].size; 
			 if(fileSize>filemaxsize){
				 Tools.tipsMsg("附件大小不能大于"+filemaxsize/1024+"M！");   
		         return ;
		     }*/
			/*var imagSize = document.getElementById("upload").files.size;  
			if(imagSize < 1024*1024*0.1){
				Tools.tipsMsg('图片大小在3M以内');
				return;
			}*/
			var options={   
		         url : "manager/TeacherServlet?method=uploadFileimages",
		         type :'post',
		         data : null,
		         dataType : "json",
		         success : function(result){
		        	   // 清空文件
		        	   $(thiz).val("");
		        	    
		        	     
		    	 		var img = "upload/"+result;
		    	 		$(".modalimg").attr("src",img);
		    	 		var fileInfo = $(".equipInfoAdd_loadFile").find(".equipInfoAdd_file");
		    	 		var btn = $(".equipInfoAdd_loadFile").find("button");
		    	 		var label = $(fileInfo).prev();
		    	 		$(fileInfo).attr("filePath",result);
		    	 		//$(fileInfo).attr("newName",data.newName);
		    	 		$(fileInfo).after("<button class='btn btn-info equipInfoAdd_delFile  btn-line-height'><i class='fa fa-trash-o'></i>&nbsp;删除图片</button>");
		    	 		$(btn).hide();

		    		 	alert('上传文件成功');
		    	 	
		         }
		        /*  "error": function(e){
		        	 alert('上传文件失败,服务器端发生异常!');
					} */
		    };
			
		   $("#uploadForm").ajaxSubmit(options); 
			
		})
		
					
				/**
				 * 删除图片
				 */
				 $(".equipInfoAdd_delFile").click(function(){
					 
					 
					 
				 })
				 
	</script>

     

</html>