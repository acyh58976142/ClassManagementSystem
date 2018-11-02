<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>学生管理------学生档案</title>
		 <base
	    href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="css/index.css">
		<link rel="stylesheet" href="css/irrigatedCommon.css">
		<link rel="stylesheet" href="css/default.css">
		<!-- dataTable -->

		<link href="">

	</head>

	<body>
		<div class="container" style="padding-right: 10px !important;padding-left: 10px !important;">
	<div class="row row-margin0 row_serch">
		<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
		<form action="client/StudentServlet?method=findStudent" method="post">
			<div class="col-md-2 common-padding-left0">
				<div class="input-group">
					<span class="input-group-addon allQuery">姓名</span><input
						class="form-control" type="text" name="queryStudentName" id="queryStudentName"
						placeholder="姓名" />
				</div>
			</div>
			<!--<div class="col-md-2">
				<div class="input-group">
					<span class="input-group-addon allQuery" >设备类型</span> <select
						class="form-control" id="select_querydeviceType">
						<option value="">-请选择-</option>
						<option value="1">电动机1</option>
						<option value="2">电动机2</option>
						<option value="3">电动机3</option>
						<option value="4">电动机4</option>
						<option value="5">电动机5</option>
					</select>
				</div>
			</div>-->
			<div class="col-md-2">
				<div class="input-group">
					<span class="input-group-addon allQuery">学号</span><input
						class="form-control" type="text" name="queryStudentNumber" id="queryStudentNumber"
						placeholder="学号" />
				</div>
			</div>
			<div class=" col-md-1 registerStyl">
				<button type="submit" class="btn white" id="">
					&nbsp;查询
				</button>
			</div>
			</form>
			<c:if test="${sessionScope.role==0||sessionScope.role==2}">
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
			</div>
		</div>
		<div class="row row-margin0">
		<div class="col-md-12 col-xs-12 table-responsive common-padding-left-right0 common-padding-top-bottom">
			<table class="table table-bordered table-striped table-hover active "
				id="student_table">
				<thead>
					<tr>
					    <th class="hiddenTdTh">隐藏的主键id</th>
						<th>序号</th>
						<th>学生姓名</th>
						<th>学号</th>
						<th>班级</th>
						<th>出生日期</th>
						<th>毕业学校</th>
						<th>所学专业</th>
						<th>毕业时间</th>
						<th>手机号码</th>
						<th>邮箱</th>
						<th>详细资料</th>
					</tr>
				</thead>
				<tbody>
                    <c:forEach items="${page.date}" var="student" varStatus="status">
					<tr>
					    <td class="hiddenTdTh">${student.id}</td>
						<td>${status.index + 1}</td>
						<td><c:out value="${student.studentName}" escapeXml="true"/></td>
						<td>${student.studentNumber}</td>
						<td>${student.team}</td>
						<td>${student.birthDate}</td>
						<td>${student.graduationStudy}</td>
						<td>${student.profession}</td>
						<td>${student.graduationTime}</td>
						<td>${student.studentMobilephone}</td>
						<td>${student.studentEmail}</td>
						<td><a class='lookModal' id="lookstudent" style='color:red; cursor:pointer' data-toggle='modal' 
						data-target=''>详情</a></td>
					</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="col-md-12 text-right">
				
				<ul class="pagination">
					    <li><a href="client/StudentServlet?method=findStudent&pageNum=${page.pageNum-1 }">&laquo;</a></li>
					    <c:forEach begin="1" end="${page.totalPage }" var="index">
				        <c:choose>
					    <c:when test="${index==page.pageNum }">
						         <li><a href=#>${index}</a></li>
					    </c:when>
					    <c:otherwise>
						        <li><a href="client/StudentServlet?method=findStudent&pageNum=${index}">${index }</a></li>
					    </c:otherwise>
				        </c:choose>
			            </c:forEach>
					    <li><a href="client/StudentServlet?method=findStudent&pageNum=${page.pageNum+1 }">&raquo;</a></li>
                </ul> 
			</div>

	</div>


	<!-- 模态框（Modal） -->
	<div class="modal fade" id="student_modal" tabindex="-1"
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
							<form id="modalForm">
							<input type="hidden" name="studentId" id="studentId" value="${student.id }"/>
								<div class="col-md-9 col-xs-9 common-padding-right0" >
									<div class="col-md-12 col-xs-12 common-padding-top-bottom">
										<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
												<span class="input-group-addon"><i
													class="common-formRequired">*</i>学生姓名</span><input
													class="form-control" type="text" name="studentName"
													id="studentName" placeholder="" />
											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号</span><input
													class="form-control" type="text" name="studentNumber"
													id="studentNumber" placeholder="" />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级</span><input
													class="form-control" type="text" name="team"
													id="team" placeholder="" />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">出生日期</span><input
													class="form-control" type="text" name="birthDate"
													id="birthDate" placeholder="" />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;毕业学校</span><input
													class="form-control" type="text" name="graduationStudy"
													id="graduationStudy" placeholder="" />

											</div>
										</div>
										<!--<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;设备类型</span>
												<select class="form-control" name="select_deviceType"
													id="select_deviceType">
													<option value="">-请选择-</option>
													<option value="1">电动机1</option>
													<option value="2">电动机2</option>
													<option value="3">电动机3</option>
													<option value="4">电动机4</option>
													<option value="5">电动机5</option>
												</select>
											</div>
										</div>-->
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">所学专业</span><input
													class="form-control" type="text" name="profession"
													id="profession" placeholder="" />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;毕业日期</span><input
													class="form-control" type="text" name="graduationTime"
													id="graduationTime" placeholder="" />

											</div>
										</div>
										<!-- <div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;婚姻状况</span>
												<select class="form-control" name="select_deviceType"
													id="select_deviceType">
													<option value="">-请选择-</option>
													<option value="1">有</option>
													<option value="2">无</option>
													
												</select>
											</div>
										</div> -->
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">联系方式</span><input
													class="form-control" type="text" name="studentMobilephone"
													id="studentMobilephone" placeholder="" />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮件</span><input
													class="form-control" type="text" name="studentEmail"
													id="studentEmail" placeholder="" />

											</div>
										</div>
										
										
										
									</div>
								</div>
							</form>
						</div>

					</div>
			


					<div class="row firstrow">
                    <div class="modalHrDiv  col-md-12 col-xs-12" style="margin-top:10px!important;"><hr></div>  
					<div class="modal-footer col-md-12 col-xs-12">
						<button type="button" class="btn white" id="student_save_btn"><img src="">保存</button>
						<button type="button" class="btn white" data-dismiss="modal"><img src="">取消</button>
					</div>
					</div>
				</div>
			
				<!-- /.modal-content -->
			</div>
		</div>
	</div>


	<!-- 通过查看详情打开模态框（Modal） -->
	<div class="modal fade" id="student_modalB" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabelB" aria-hidden="true">
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
								<img class="modalimgB" src="upload/nopic.png"  width="192" height="204"/>
								<div class="row equipInfoAdd_row">
									<div class="col-md-1 col-xs-1"></div>
									<!-- <form onsubmit="return false;" id="uploadForm">
										<div class="col-md-10 col-xs-10 equipInfoAdd_loadFile text-center">
											<label></label>&nbsp;&nbsp;<a class="text-left equipInfoAdd_file"></a> 
											<input id="upload" name="upload" class="equipInfo_upload" type="file" accept="image/png,image/jpg,image/gif,image/bmp,image/jpeg" style="display: none;">
											<button class="btn btn-info equipInfoAdd_uploadFile btn-line-height">
												<i class="fa fa-upload"></i>&nbsp;上传图片
											</button>

										</div>
									</form> -->

									<div class="col-md-1 col-xs-1"></div>
								</div>
								<!--  <button type="button" class="btn btn-sm white"><i class=""></i>&nbsp;上传图片</button> -->
							</div>
							<form id="modalForm">
								<div class="col-md-9 col-xs-9 common-padding-right0" >
									<div class="col-md-12 col-xs-12 common-padding-top-bottom">
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon"><i
													class="common-formRequired">*</i>学生姓名</span><input
													class="form-control" type="text" name="studentNameB"
													id="studentNameB" placeholder="" disabled="disabled"/>
											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学号</span><input
													class="form-control" type="text" name="studentNumberB"
													id="studentNumberB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;班级</span><input
													class="form-control" type="text" name="teamB"
													id="teamB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">出生日期</span><input
													class="form-control" type="text" name="birthDateB"
													id="birthDateB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;毕业学校</span><input
													class="form-control" type="text" name="graduationStudyB"
													id="graduationStudyB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										<!--<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;设备类型</span>
												<select class="form-control" name="select_deviceType"
													id="select_deviceType">
													<option value="">-请选择-</option>
													<option value="1">电动机1</option>
													<option value="2">电动机2</option>
													<option value="3">电动机3</option>
													<option value="4">电动机4</option>
													<option value="5">电动机5</option>
												</select>
											</div>
										</div>-->
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">所学专业</span><input
													class="form-control" type="text" name="professionB"
													id="professionB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">毕业日期</span><input
													class="form-control" type="text" name="graduationTimeB"
													id="graduationTimeB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">联系方式</span><input
													class="form-control" type="text" name="studentMobilephoneB"
													id="studentMobilephoneB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">邮件</span><input
													class="form-control" type="text" name="studentEmailB"
													id="studentEmailB" placeholder="" disabled="disabled"/>

											</div>
										</div>
										
										
									</div>
								</div>
							</form>
						</div>

					</div>
			


					<div class="row firstrow">
                    <div class="modalHrDiv  col-md-12 col-xs-12" style="margin-top:10px!important;"><hr></div>  
					<div class="modal-footer col-md-12 col-xs-12">
						
						<button type="button" class="btn white" data-dismiss="modal"><img src="">取消</button>
					</div>
					</div>
				</div>
			
				<!-- /.modal-content -->
			</div>
		</div>
	</div>

	</body>
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
	
    <script type="text/javascript" src="web/student/Student.js"></script>
	<script>
		$(function() {

			/* 隐藏表格第1列 */
			$(".hiddenTdTh").hide();
			
			$('#myTab li:eq(1) a').tab('show');
		});
	</script>

</html>