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
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
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
<title>班级管理系统</title>
<meta name="keywords" content="H-ui.admin v3.1,H-ui">
<meta name="description" content="H-ui.admin v3.1">

 <script type="text/javascript" src="js/jQuery/jquery-1.7.1.min.js"></script>
  <script type="text/javascript">
    window.onload=function(){
   var a=document.getElementsByTagName('a');
     for(var i=0;i<a.length;i++){
        a[i].onclick=function() {
      for(var i=0;i<a.length;i++){
        a[i].style.background="";
       }
     this.style.background='pink';
   }
   }
   }
</script>





</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">班级管理系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">班级管理系统</a> 
			<!--<span class="logo navbar-slogan f-l mr-10 hidden-xs">v3.1</span> -->
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','moban2/article-add.jsp')"><i class="Hui-iconfont">&#xe616;</i>班级公告</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','moban2/picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 班级图片</a></li>
							<!--<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>-->
							<li><a href="javascript:;" onclick="member_add('添加用户','moban2/member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
			<ul class="cl">
				<li>欢迎</li>
					 <c:choose>
					 <c:when test="${sessionScope.role==0}">
					 <li class="dropDown dropDown_hover">
					    <a href="#" class="dropDown_A"><p>超级管理员 ${sessionScope.user}</p><i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a class="out_btn" href="client/UserInfoServlet?method=out">切换账户</a></li>
						<li><a class="out_btn" href="client/UserInfoServlet?method=out">退出</a></li>
				        </ul>
			         </li>
								</c:when>
								<c:when test="${sessionScope.role==1}">
								 <li class="dropDown dropDown_hover">
					    <a href="#" class="dropDown_A"><p>学生账号 ${sessionScope.user}</p><i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a class="out_btn" href="client/UserInfoServlet?method=out">切换账户</a></li>
						<li><a class="out_btn" href="client/UserInfoServlet?method=out">退出</a></li>
				        </ul>
			         </li>
								</c:when>
								<c:when test="${sessionScope.role==2}">
									 <li class="dropDown dropDown_hover">
					    <a href="#" class="dropDown_A"><p>教师账号 ${sessionScope.user}</p><i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
						<li><a class="out_btn" href="client/UserInfoServlet?method=out">切换账户</a></li>
						<li><a class="out_btn" href="client/UserInfoServlet?method=out">退出</a></li>
				        </ul>
			         </li>
								</c:when>
								<c:when test="${sessionScope.role==3}">
									 <li class="dropDown dropDown_hover">
					    <a href="#" class="dropDown_A"><p>宿管账号 ${sessionScope.user}</p><i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
					<li><a class="out_btn" href="client/UserInfoServlet?method=out">切换账户</a></li>
						<li><a class="out_btn" href="client/UserInfoServlet?method=out">退出</a></li>
				        </ul>
			         </li>
								</c:when>
								<c:otherwise>
									 <li class="dropDown dropDown_hover">
					    <a href="client/UserInfoServlet?method=out" class="dropDown_A"><p>请登陆</p><i class="Hui-iconfont">&#xe6d5;</i></a>
						
			         </li>
								</c:otherwise>
							</c:choose>
					
				
				<!-- <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li> -->
				<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
					<ul class="dropDown-menu menu radius box-shadow">
						<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
						<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
						<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
						<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
						<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
						<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-article">
			<dt><i class="Hui-iconfont .Hui-iconfont-face">&#xe668;</i> 学生管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="client/StudentServlet?method=findStudent" data-title="学生档案" href="javascript:void(0)">学生档案</a></li>
					<li><a data-href="client/ScoreServlet?method=findScore" data-title="学生成绩" href="javascript:void(0)">学生成绩</a></li>
			</ul>
		</dd>
	</dl>
		<!--<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 图片管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="picture-list.html" data-title="图片管理" href="javascript:void(0)">图片管理</a></li>
			</ul>
		</dd>
	</dl>-->
		
		<!--<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 评论管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="http://h-ui.duoshuo.com/admin/" data-title="评论列表" href="javascript:;">评论列表</a></li>
					<li><a data-href="feedback-list.html" data-title="意见反馈" href="javascript:void(0)">意见反馈</a></li>
			</ul>
		</dd>
	</dl>-->
	  <c:if test="${sessionScope.role==0||sessionScope.role==2||sessionScope.role==null||sessionScope.role==''}">
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 教师管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="manager/TeacherServlet?method=findAll" data-title="教学档案" href="javascript:;">教学档案</a></li>
				    <li><a data-href="Plan_serverlet?method=page_fen1&pagenum=1&findstyle=0" data-title="教学计划" href="javascript:;">教学计划</a></li>
			</ul>
		</dd>
	</dl>
	</c:if> 
	<c:if test="${!(sessionScope.role==4)}">
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont .Hui-iconfont-hetong">&#xe636;</i> 班级管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="client/AttendanceServlet?method=findAttendance" data-title="考勤" href="javascript:;">考勤</a></li>
					<li><a data-href="HomeworkServlet?method=lookingforHomework&pagenum=1" data-title="作业情况" href="javascript:void(0)">作业情况</a></li>
					<!-- <li><a data-href="feedback-list.html" data-title="班级问题" href="javascript:void(0)">班级问题</a></li> -->
			</ul>
		</dd>
	</dl>
	</c:if>
	  <!--  <dl id="menu-comments">
			<dt><i class="Hui-iconfont .Hui-iconfont-face-mogui">&#xe65a;</i> 班级费用<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="http://h-ui.duoshuo.com/admin/" data-title="交纳班费" href="javascript:;">交纳班费</a></li>
					
			</ul>
		</dd>
	</dl> -->
	<c:if test="${!(sessionScope.role==4)}">
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 成绩分析<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					
					
					<li><a data-href="web/systemStatistic/echarts-1.jsp" data-title="柱状图" href="javascript:void(0)">月平均分图</a></li>
					<li><a data-href="web/systemStatistic/echarts-2.jsp" data-title="饼状图" href="javascript:void(0)">分数段分布图</a></li>
					
			</ul>
		</dd>
	</dl>
	</c:if>
	<c:if test="${sessionScope.role==0||sessionScope.role==''}">
	<dl id="menu-admin">
			<dt><i class="Hui-iconfont .Hui-iconfont-manage">&#xe61d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="client/UserInfoServlet?method=findBook" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
					<!-- <li><a data-href="web/user/admin-permission.jsp" data-title="权限管理" href="javascript:void(0)">权限管理</a></li> -->
					<!-- <li><a data-href="web/ muser/admin-list.jsp" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li> -->
			</ul>
		</dd>
	</dl>
	</c:if>
		<!--<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="system-base.html" data-title="系统设置" href="javascript:void(0)">系统设置</a></li>
					
					<li><a data-href="system-log.html" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
			</ul>
		</dd>
	</dl>-->
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="首页" data-href="web/home/index.jsp">首页</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="web/home/index.jsp"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="moban2/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="moban2/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="moban2/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="moban2/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->n
<script type="text/javascript" src="moban2/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">

$(function(){
	 $(".out_btn").click(function(){
		
		 if(!confirm("确认退出或切换账户吗？") ){
			 return false;
		 }
	 })
})

/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


</script> 


</body>
</html>