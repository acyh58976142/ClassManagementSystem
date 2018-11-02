<%@page import="java.io.IOException"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<ul id="myTab" class="nav nav-tabs ulA">
	<li class="active">
		<a href="#student" data-toggle="tab" data-target="#student">
			 学生
		</a>
	</li>
	<li><a href="#teacherss" data-toggle="tab" data-target="#teacherss">教师</a></li>
	
</ul>

<div id="myTabContent" class="tab-content">
	
		<div class="container tab-pane fade in active" id="student" style="padding-right: 10px !important;padding-left: 10px !important;">
	<div class="row row-margin0 row_serch">
		<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
			<div class="col-md-2 common-padding-left0">
				<div class="input-group">
				
				
					<span class="input-group-addon allQuery">姓名</span><input
						class="form-control" type="text" name="select_querylocation" id="select_querylocation"
						placeholder="姓名" />
				</div>
			</div>
			<div class="col-md-2">
				<div class="input-group">
					<span class="input-group-addon allQuery">学号</span><input
						class="form-control" type="text" name="queryname" id="queryname"
						placeholder="学号" />
				</div>
			</div>
			<div class=" col-md-1 registerStyl">
				<button type="button" class="btn white" id="equipInfo_search_btn">
					<img class="common-img" src="/resource/images/irrigationmanager/button/query.png"></img>&nbsp;查询
				</button>
			</div>
			
			
			
			<div class=" col-md-7 text-right registerStyl common-padding-right0">
				<button type="button" class="btn white" id="equipInfo_update_btn" data-toggle="modal" data-target="#equipInfo_modal">
					<img class="common-img"
						src="/resource/images/irrigationmanager/button/update.png"></img>&nbsp;修改
				</button>
			</div>
			</div>
		</div>
		<div class="row row-margin0">
		<div class="col-md-12 col-xs-12 table-responsive common-padding-left-right0 common-padding-top-bottom">
			<table class="table table-bordered table-striped table-hover active "
				id="equipInfo_table">
				<thead>
					<tr>
						<th>序号</th>
						<th>学生姓名</th>
						<th>学号</th>
						<th>班级</th>
						<th>联系电话</th>
						<th>是否缺勤</th>
					</tr>
							
					<tr>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
						<td>1</td>
					
	
					</tr>
					
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>

	</div>
	</div>
	
	
		
		<div class="container tab-pane fade " id="teacherss" style="padding-right: 10px !important;padding-left: 10px !important;">
	<div class="row row-margin0 row_serch">
		
		<div class="col-md-12 col-xs-12 common-padding-left-right0 common-padding-top-bottom">
		
		
		<form action="client/teacherAttendance1?method=teacherAttendance11" method="post">
			<div class="col-md-2 common-padding-left0">
				<div class="input-group">
					<span class="input-group-addon allQuery">教师姓名</span><input
						class="form-control" type="text" name="select_querylocation1" id="select_querylocation"
						placeholder="姓名" />
				</div>
			</div>
			<div class="col-md-2">
				<div class="input-group">
					<span class="input-group-addon allQuery">出勤日期</span><input
						class="form-control" type="text" name="queryname1" id="queryname"
						placeholder="出勤日期" />
				</div>
			</div>
			<div class=" col-md-1 registerStyl">
				<button type="submit" class="btn white" id="equipInfo_search_btn">
					<img class="common-img" src="/resource/images/irrigationmanager/button/query.png"></img>&nbsp;查询
				</button>
			<span>${requestScope.msg }</span>
			</div>
			</form>
					<div class=" col-md-7 text-right registerStyl common-padding-right0">
				<button type="button" class="btn white" id="equipInfo_update_btn"data-toggle="modal" data-target="#equipInfo_modal1">
					<img class="common-img" 
						src="/resource/images/irrigationmanager/button/update.png"></img>&nbsp;修改
				</button>
				<span>${requestScope.msg1 }</span>
			</div>
		</div>	
		<!--修改的模态框  -->
		<%-- 
		<%teacherAttendance kk =(teacherAttendance)session.getAttribute("kkk");%>
			<%if(kk!=null){%>
				<script type="text/javascript">
				var succeed=document.
				window.onload=function(){
					
				}
				</script>
			<div class="modal fade" id="update_Teacherinfo_result" style="position:absolute;left:500px;top:200px; width:700px; height:700px; background:rgba(255,255,255,0.7)">
				<p>修改信息</p>
				<form action="" method="post">
				<div>岗位<input type="text" name=""/></div><br/>
				<div>联系电话<input type="text"style="margin-top:20px" name=""/></div><br/>
				<div>是否缺勤<input type="text"style="margin-top:20px" name=""/></div><br/>
				<button type="submit" >完成</button>
				</form>
			</div>
			<%}else{%>
			<div>${sessionScope.msg}</div>
			<%} %>
			 --%>
			
			
			
			
		<!-- <div class="modal fade" id="update_Teacherinfo" style="position:absolute;left:500px;top:200px; width:300px; height:300px; background:rgba(255,255,255,0.7)">
				<p>修改信息</p>
				<form action="client/teacherAttendance1?method=update_teacherinfo" method="post" target="post_irfame">
				<div>姓名<input type="text" name="select_querylocation11"/></div><br/>
				<div>工号<input type="text"style="margin-top:20px" name="queryname11"/></div><br/>
				<button type="submit" data-toggle="modal" data-target="#update_Teacherinfo_result" id="succeed">完成</button>
					</form>
		</div> -->
		
										
		
		
		
		<div class="row row-margin0">
		<div class="col-md-12 col-xs-12 table-responsive common-padding-left-right0 common-padding-top-bottom">
			<table class="table table-bordered table-striped table-hover active "
				id="equipInfo_table">
				<thead>
					<tr>
						<th>序号</th>
						<th>教师姓名</th>
						<th>工号</th>
						<th>岗位</th>
						<th>联系电话</th>
						<th>考勤日期</th>
						<th>是否缺勤</th>
						
					</tr>
			
					<tr>
					
						<td>${kk.id ==null?"1":kk.id}</td>
						<td>${kk.teather_name==null?"null":kk.teather_name}</td>
						<td>${kk.job_number }</td>
						<td>${kk.position }</td>
						<td>${kk.teacher_mobilephone }</td>
						<td>${kk.attendance_date }</td>
						<td>${kk.isabsence}</td>
					</tr>
				
				</thead>
				<tbody>

				</tbody>
			</table>
		</div>

	</div>
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
								<img class="modalimg" src="/images/nopic.png" />
								<div class="row equipInfoAdd_row">
									<div class="col-md-1 col-xs-1"></div>
									<form onsubmit="return false;" id="uploadForm">
										<div class="col-md-10 col-xs-10 equipInfoAdd_loadFile text-center">
											<label></label>&nbsp;&nbsp;<a class="text-left equipInfoAdd_file"></a> 
											<input id="upload" name="upload" class="equipInfo_upload" type="file" accept="image/png,image/jpg,image/gif,image/bmp,image/jpeg" style="display: none;">
											<button class="btn btn-info equipInfoAdd_uploadFile btn-line-height">
												<i class="fa fa-upload"></i>&nbsp;上传图片
											</button>

										</div>
									</form>





									<div class="col-md-1 col-xs-1"></div>
								</div>
								<!--  <button type="button" class="btn btn-sm white"><i class=""></i>&nbsp;上传图片</button> -->
							</div>
							
							
							
							
							<form action="client/teacherInfo_Servlet?method=teacherInfo" method="post" id="modalForm">
								<div class="col-md-9 col-xs-9 common-padding-right0" >
									<div class="col-md-12 col-xs-12 common-padding-top-bottom">
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon"><i
													class="common-formRequired">*</i>序号</span><input
													class="form-control" type="text" name="input_name"
													id="input_name"  />
											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">学生姓名</span><input
													class="form-control" type="text" name="input_code"
													id="input_code"  />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;学号</span><input
													class="form-control" type="text" name="input_model"
													id="input_model"  />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">班级</span><input
													class="form-control" type="text" name="input_brand"
													id="input_brand"  />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">联系电话</span><input
													class="form-control" type="text" name="input_birth"
													id="input_brand"  />

											</div>
										</div>										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">是否缺勤</span><input
													class="form-control" type="text" name="input_deviceGroup"
													id="input_deviceGroup" />

											</div>
										</div>
										<div class="modalHrDiv  col-md-12 col-xs-12" style="margin-top:10px!important;"><hr></div>  
					<div class="modal-footer col-md-12 col-xs-12">
						<button type="submit" class="btn white" id="equipInfo_save_btn"><img src="../../resource/images/irrigationmanager/button/save.png">保存</button>
						<button type="button" class="btn white" data-dismiss="modal"><img src="../../resource/images/irrigationmanager/button/return.png">取消</button>
					
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
		
		
		
		
		
		
		
		
		<!-- 模态框（Modal） -->
	<div class="modal fade" id="equipInfo_modal1" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modalwidth">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title text-center" id="myModalLabel">教师详细档案</h4>


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
								<img class="modalimg" src="/images/nopic.png" />
								<div class="row equipInfoAdd_row">
									<div class="col-md-1 col-xs-1"></div>
									<form onsubmit="return false;" id="uploadForm">
										<div class="col-md-10 col-xs-10 equipInfoAdd_loadFile text-center">
											<label></label>&nbsp;&nbsp;<a class="text-left equipInfoAdd_file"></a> 
											<input id="upload" name="upload" class="equipInfo_upload" type="file" accept="image/png,image/jpg,image/gif,image/bmp,image/jpeg" style="display: none;">
											<button class="btn btn-info equipInfoAdd_uploadFile btn-line-height">
												<i class="fa fa-upload"></i>&nbsp;上传图片
											</button>

										</div>
									</form>





									<div class="col-md-1 col-xs-1"></div>
								</div>
								<!--  <button type="button" class="btn btn-sm white"><i class=""></i>&nbsp;上传图片</button> -->
							</div>
							
							
							
							
							<form action="client/teacherAttendance1?method=teacherAttendance_update" method="post" id="modalForm">
								<div class="col-md-9 col-xs-9 common-padding-right0" >
									<div class="col-md-12 col-xs-12 common-padding-top-bottom">
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">教师姓名</span><input
													class="form-control" type="text" name="input_name1"
													id="input_code"  />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">&nbsp;&nbsp;&nbsp;工号</span><input
													class="form-control" type="text" name="input_code1"
													id="input_model"  />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">是否缺勤</span><input
													class="form-control" type="text" name="input_attendance"
													id="input_location" />

											</div>
										</div>
										<div class="col-md-6 col-xs-6">
											<div class="input-group input-group-sm">
												<span class="input-group-addon">出勤日期</span><input
													class="form-control" type="text" name="input_attendanceDate"
													id="input_location" />

											</div>
										</div>
										<div class="modalHrDiv  col-md-12 col-xs-12" style="margin-top:10px!important;"><hr></div>  
					<div class="modal-footer col-md-12 col-xs-12">
						<button type="submit" class="btn white" id="equipInfo_save_btn"><img src="../../resource/images/irrigationmanager/button/save.png">保存</button>
						<button type="button" class="btn white" data-dismiss="modal"><img src="../../resource/images/irrigationmanager/button/return.png">取消</button>
					
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
		
		
		
		
		
		
		
		
		


	<!-- 通过查看详情打开模态框（Modal） -->
	<div class="modal fade" id="myModalB" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modalwidth">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title text-center" id="myModalLabelB">设备信息详情</h4>


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
												class="common-formRequired">*</i>设备名称</span><input
												class="form-control" type="text" name="input_nameB"
												id="input_nameB" placeholder="" readonly="readonly" />
										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">设备编码</span><input
												class="form-control" type="text" name="input_codeB"
												id="input_codeB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;设备型号</span><input
												class="form-control" type="text" name="input_modelB"
												id="input_modelB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">设备品牌</span><input
												class="form-control" type="text" name="input_brandB"
												id="input_brandB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;设备类型</span>
											<select class="form-control" name="select_deviceTypeB"
												id="select_deviceTypeB" readonly="readonly">
												<option value="">-请选择-</option>
												<option value="1">电动机1</option>
												<option value="2">电动机2</option>
												<option value="3">电动机3</option>
												<option value="4">电动机4</option>
											</select>
										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">设备分组</span><input
												class="form-control" type="text" name="input_deviceGroupB"
												id="input_deviceGroupB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;设备等级</span><input
												class="form-control" type="text" name="input_deviceLevelB"
												id="input_deviceLevelB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">安装位置</span><input
												class="form-control" type="text" name="input_locationB"
												id="input_locationB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;主管部门</span><input
												class="form-control" type="text" name="input_departmentB"
												id="input_departmentB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;负责人</span><input
												class="form-control" type="text" name="input_charge_personB"
												id="input_charge_personB" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div class="col-md-6 col-xs-6">
										<div class="input-group input-group-sm">
											<span class="input-group-addon">&nbsp;&nbsp;&nbsp;生命状态</span>
											<select class="form-control" name="select_stateB"
												id="select_stateB" readonly="readonly">
												<option value="">-请选择-</option>
												<option value="1"></option>
												<option value="2"></option>
												<option value="3"></option>
												<option value="4"></option>
											</select>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row firstrow">
						<div class="col-md-12 col-xs-12">
							<div class="col-md-2 modalhr">
								<strong>商家信息</strong>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
								<div class="col-md-1"><hr class="hrhr"></div>
							</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-6 col-xs-6">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">制造商</span><input
										class="form-control" type="text"
										name="input_manufacturer_nameB" id="input_manufacturer_nameB"
										placeholder="" readonly="readonly" />

								</div>
							</div>
							<div class="col-md-3 col-xs-3">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">联系电话</span><input
										class="form-control" type="text"
										name="input_manufacturer_phoneB"
										id="input_manufacturer_phoneB" placeholder=""
										readonly="readonly" />

								</div>
							</div>
							<div class="col-md-3 col-xs-3">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">联系人</span><input
										class="form-control" type="text"
										name="input_manufacturer_contactsB"
										id="input_manufacturer_contactsB" placeholder=""
										readonly="readonly" />

								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-12 col-xs-12">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;地址</span><input
										class="form-control" type="text"
										name="input_manufacturer_addressB"
										id="input_manufacturer_addressB" placeholder=""
										readonly="readonly" />

								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-6 col-xs-6">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">供应商</span><input
										class="form-control" type="text" name="input_supplier_nameB"
										id="input_supplier_nameB" placeholder="" readonly="readonly" />

								</div>
							</div>
							<div class="col-md-3 col-xs-3">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">联系电话</span><input
										class="form-control" type="text" name="input_supplier_phoneB"
										id="input_supplier_phoneB" placeholder="" readonly="readonly" />

								</div>
							</div>
							<div class="col-md-3 col-xs-3">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">联系人</span><input
										class="form-control" type="text"
										name="input_supplier_contactsB" id="input_supplier_contactsB"
										placeholder="" readonly="readonly" />

								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-12 col-xs-12">
								<div class="input-group input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;地址</span><input
										class="form-control" type="text"
										name="input_supplier_addressB" id="input_supplier_addressB"
										placeholder="" readonly="readonly" />

								</div>
							</div>
						</div>
					</div>
					<div class="row firstrow">
						<div class="col-md-12 col-xs-12">
							<div class="col-md-2 modalhr">
								<strong>生命周期</strong>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
								<div class="col-md-1"><hr class="hrhr"></div>
							</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">采购日期</span> <input
										class="form-control SystemCalculationdateinput" type="text"
										id="input_purchase_dateB" name="input_purchase_date"
										readonly="readonly" />
								</div>
							</div>
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;保质期开始</span>
									<input class="form-control SystemCalculationdateinput"
										type="text" id="input_guaranteestart_dateB"
										name="input_guaranteestart_date" readonly="readonly" />
								</div>
							</div>
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;保质期结束</span>
									<input class="form-control SystemCalculationdateinput"
										type="text" id="input_guaranteeend_dateB"
										name="input_guaranteeend_date" readonly="readonly" />
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">安装日期</span> <input
										class="form-control SystemCalculationdateinput" type="text"
										id="input_install_dateB" name="input_install_date"
										readonly="readonly" />
								</div>
							</div>
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;启用日期</span>
									<input class="form-control SystemCalculationdateinput"
										type="text" id="input_usestart_dateB"
										name="input_usestart_date" readonly="readonly" />
								</div>
							</div>
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;建档日期</span>
									<input class="form-control SystemCalculationdateinput"
										type="text" id="input_record_dateB" name="input_record_date"
										readonly="readonly" />
								</div>
							</div>
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">维保开始</span> <input
										class="form-control SystemCalculationdateinput" type="text"
										id="input_maintenstart_dateB" name="input_maintenstart_date"
										readonly="readonly" />
								</div>
							</div>
							<div class="col-md-4 col-xs-4">
								<div class="input-group  input-group-sm">
									<span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维保结束</span>
									<input class="form-control SystemCalculationdateinput"
										type="text" id="input_maintenend_dateB"
										name="input_maintenend_date" readonly="readonly" />
								</div>
							</div>
							<div class="col-md-4 col-xs-4"></div>
						</div>
						<div class="col-md-12 col-xs-12">
							<div class="col-md-6 col-xs-6">
								<span>二维码</span>&nbsp;&nbsp; <img id="imgqrB" name="imgqrB" src=""
									style="height: 100px; width: 100px" />&nbsp;

							</div>
						</div>
					<div class="modalHrDiv  col-md-12 col-xs-12"><hr></div>  
					<div class="modal-footer col-md-12 col-xs-12">
						<button type="button" class="btn white" data-dismiss="modal"><img src="../../resource/images/irrigationmanager/button/return.png">关闭</button>
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
    <script type="text/javascript" src="Student.js"></script>
	<script>
		$(function() {
			
			$('#myTab li:eq(1) a').tab('show');
		});
	</script>

</html>