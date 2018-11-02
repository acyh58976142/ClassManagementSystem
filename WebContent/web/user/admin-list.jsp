<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico">
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<base
	href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<link rel="stylesheet" type="text/css"
	href="moban2/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="moban2/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="moban2/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="moban2/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="moban2/static/h-ui.admin/css/style.css" />
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>管理员列表</title>
<style type="text/css">
td {
	text-align: center !important;
}
</style>
</head>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="text-c">
			<!-- 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;"> -->
		<form action="client/UserInfoServlet?method=findBook" method="post" name="queryFrom">
			<input type="text" class="input-text" style="width: 250px"
				placeholder="输入管理员名称" id="queryUserName" name="queryUserName">
			<button type="submit" class="btn btn-success" id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜用户
			</button> 
			</form>
		</div>
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><!-- <a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> --> <a href="javascript:;" id="add_user"
				onclick="admin_add('添加管理员','web/user/admin-add.jsp','800','500')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加管理员</a></span> <span class="r">共有数据：<strong>${page.totalRecode }</strong> 条
			</span>
		</div>
		<table class="table table-border table-bordered table-bg"
			style="text-align: center !important;">
			<thead>
				<tr>
					<th scope="col" colspan="10">管理员列表</th>
				</tr>
				<tr class="text-c">
					<!-- <th width="25"><input type="checkbox" name="" value=""></th> -->
					
					<th width="40">序号</th>
					<th width="150">登录名</th>
					<th width="90">密码</th>
					<th width="90">手机</th>
					<th width="150">邮箱</th>
					<th>角色权限</th>
					<th width="130">加入时间</th>
					<th width="100">是否已启用</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.page.date}" var="user" varStatus="status">
					<tr>
						<!-- <th width="25"><input type="checkbox" name="" value=""></th> -->
						
						<td style="text-align: center;">${status.index + 1}</td>
						<td style="text-align: center;">${user.userName}</td>
						<td style="text-align: center;">${user.password}</td>
						<td style="text-align: center;">${user.mobilePhone}</td>
						<td style="text-align: center;">${user.email}</td>
						<td style="text-align: center;"><c:choose>
								<c:when test="${user.role==0}">
									<p>超级管理员</p>
								</c:when>
								<c:when test="${user.role==1}">
									<p>学生账号</p>
								</c:when>
								<c:when test="${user.role==2}">
									<p>教师账号</p>
								</c:when>
								<c:otherwise>
									<p>宿管账号</p>
								</c:otherwise>
							</c:choose></td>

						<td style="text-align: center;">${user.joinTime}</td>
						<td class="td-status" style="text-align: center;"><span
							class="label radius"> <c:choose>
									<c:when test="${user.isUsing==0}">
					未启用 
					</c:when>
									<c:otherwise>
					已启用
					</c:otherwise>
								</c:choose></span></td>

						<td class="td-manage"><a style="text-decoration: none"
							onClick="admin_start(this,'10001')" href="javascript:;"
							title="启用"><i class="Hui-iconfont">&#xe615;</i></a> <a title="编辑"
							href="javascript:;"
							onclick="admin_edit('管理员编辑','client/UserInfoServlet?method=toUpdatePage&userId=${user.id}','2','800','500')"
							class="ml-5" style="text-decoration: none"><i
								class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" class="del_btn ml-5" id="delBtn"
							href="client/UserInfoServlet?method=delUser&userId=${user.id}"  
							style="text-decoration: none" > <i class="Hui-iconfont">&#xe6e2;</i></a></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<div class="col-md-12 text-right" id="page_nav">
				
			 	<ul class="pagination">
					    <li><a href="client/UserInfoServlet?method=findBook&pageNum=${page.pageNum-1 }">&laquo;</a></li>
					    <c:forEach begin="1" end="${page.totalPage }" var="index">
				        <c:choose>
					    <c:when test="${index==page.pageNum }">
						         <li><a href=#>${index}</a></li>
					    </c:when>
					    <c:otherwise>
						        <li><a href="client/UserInfoServlet?method=findBook&pageNum=${index}">${index }</a></li>
					    </c:otherwise>
				        </c:choose>
			            </c:forEach>
					    <li><a href="client/UserInfoServlet?method=findBook&pageNum=${page.pageNum+1 }">&raquo;</a></li>
                </ul> 
                
            <%-- <ul class="pagination">
			<li><a href="client/UserInfoServlet?method=findBook&pageNum=1">首页</a></li> <li><a
				href="client/UserInfoServlet?method=findBook&pageNum=${page.pageNum-1 }">上一页</a></li>
			<c:forEach begin="1" end="${page.totalPage }" var="index">
				<c:choose>
					<c:when test="${index==page.pageNum }">
						<li><a href="#">${index}</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="client/UserInfoServlet?method=findBook&pageNum=${index}">${index }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<li><a
				href="client/UserInfoServlet?method=findBook&pageNum=${page.pageNum+1 }">下一页</a></li>
			<li><a
				href="client/UserInfoServlet?method=findBook&pageNum=${page.totalPage }">末页</a></li>
			</ul>
			      共${page.totalPage }页，${page.totalRecode }条记录 到第<input
				value="${page.pageNum }" name="pn" id="pn_input" />页 <input
				id="pn_btn" type="button" value="确定">
			</div> --%>
	</div>
	<!--_footer 作为公共模版分离出去-->
	
	
	
	<script type="text/javascript"
		src="moban2/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="moban2/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="moban2/static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="moban2/static/h-ui.admin/js/H-ui.admin.js"></script>
		<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="moban2/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="moban2/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="moban2/lib/laypage/1.2/laypage.js"></script>

	<script type="text/javascript">
	
		
		$("#delBtn").click(function(){
		   	  alert("2333");
		   	
		       		 var userName= $(this).parent("tr").find(td.eq(1));
		       		 if(!confirm("确认删除"+userName+"吗？") ){
		       			 return false;
		       		 }
		       	 })
			$("#pn_btn").click(
					function() {

						//获取页码值
						var pn = $("#pn_input").val();

						window.location = "client/UserInfoServlet?method=findBook&pageNum="
								+ pn

					})
	
	 
			

     
         
         
		/*
		 参数解释：
		 title	标题
		 url		请求的url
		 id		需要操作的数据id
		 w		弹出层宽度（缺省调默认值）
		 h		弹出层高度（缺省调默认值）
		 */
		/*管理员-增加*/
		function admin_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	
		/*管理员-删除*/
		/* function adminDel(id){
			if(layer.confirm('确认要删除吗？')){
				window.event.returnValue = true;
			}else{
				window.event.returnValue = false;
			}
			
			if(window.event.returnValue == true){
				
				$.ajax({
					type : 'POST',
					url : 'client/UserInfoServlet?method=delUser',
					dateType : "text",
					date : {userId : id},
					success : function(data){  
						layer.msg('已删除!', {
						icon : 1,
						time : 1000
					}); 
						
						},
						error : function(data) {
							console.log(data.msg);
						},
			});
			}
		}  */
		
		


		/*管理员-编辑*/
		function admin_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		/*管理员-停用*/
		function admin_stop(obj, id) {
			layer
					.confirm(
							'确认要停用吗？',
							function(index) {
								//此处请求后台程序，下方是成功后的前台处理……

								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-default radius">已禁用</span>');
								$(obj).remove();
								layer.msg('已停用!', {
									icon : 5,
									time : 1000
								});
							});
		}

		/*管理员-启用*/
		function admin_start(obj, id) {
			layer
					.confirm(
							'确认要启用吗？',
							function(index) {
								//此处请求后台程序，下方是成功后的前台处理……

								$(obj)
										.parents("tr")
										.find(".td-manage")
										.prepend(
												'<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
								$(obj)
										.parents("tr")
										.find(".td-status")
										.html(
												'<span class="label label-success radius">已启用</span>');
								$(obj).remove();
								layer.msg('已启用!', {
									icon : 6,
									time : 1000
								});
							});
		}
	</script>
</body>
</html>