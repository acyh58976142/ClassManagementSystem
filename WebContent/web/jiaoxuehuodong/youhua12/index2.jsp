<%@page import="cn.kgc.entity.Plan"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
 <script type="text/javascript" src="web/jiaoxuehuodong/js/jquery-1.11.0.min.js" ></script>
 <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="row">
    <!-- 定义导航条的部分 -->
    <!-- navbar-inverse 反色的导航条 -->
    <!-- navbar-static-top 圆角变为方角 -->
    <nav class="navbar navbar-default navbar-static-top">
        <!-- container容器，左右留有余地 -->
        <div class="container">
 
            <div class="navbar-header"> 
                <!--
                    在浏览器的宽度很小时，buttion出现。
                    图案为方框内有三道杠。
                    data-target="#TaoistsNavBar" 与 div id="TaoistsNavBar" 相对应
                    -->
                <button type="button" class="navbar-toggle collapsed"
                      data-toggle="collapse" data-target="#TaoistsNavBar"
                      aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <!--
                        icon-bar有一个，那个图标就有一道杠。
                        这里有三个，那么图标就有三道杠
                      -->
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
 
            <div class="collapse navbar-collapse" id="TaoistsNavBar">
                <ul class="nav navbar-nav">
 <li><a href="Plan_serverlet?method=Plan_ads&findstyle=${findstyle}">添加</a></li>
                    <!-- 一个下拉列表 -->
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">筛选<span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="Plan_serverlet?method=page_fen&pagenum=1&findstyle=">所有</a></li>
                        <li class="divider"></li>
                        <li><a id="dates" href="Plan_serverlet?method=page_fen&pagenum=1&findstyle=1">日期</a></li>
                        <li class="divider"></li>
                       <li><a id="jobnum" href="Plan_serverlet?method=page_fen&pagenum=1&findstyle=2">工号</a></li>
                        <li class="divider"></li>
                        </li>
                        <li class="divider"></li>
           
                      </ul>
                    </li>
                </ul>
                 <script type="text/javascript">
                 
           </script>
                <!-- navbar-right:通过 CSS 设置特定方向的浮动样式。[节选自bootstrap官网]-->
                <form class="navbar-form navbar-right" action="Plan_serverlet?method=plan_sousuo&findstyle=0&sousuo="+ss>
                    <div class="input-group">                       
                        <input  type="search" name="information" class="form-control" placeholder="输入搜索的内容" value=""/>              
                        <span class="input-group-btn" >
                            <button type="button" id="sousuo" class="btn btn-default" >
                               <!--  <span class="glyphicon glyphicon-search"></span> -->搜索
                            </button>
                        </span>
                    </div>
                </form>              
            </div>
        </div>
    </nav>

<script type="text/javascript">
$(".input-group-btn").click(function(){
	var ss=$(".form-control").val();
	window.location="Plan_serverlet?method=plan_sousuo&sousuo="+ss+"&findstyle=${findstyle}";
	
})
</script>

</div>







<!--主体部分  -->
		<div class="row">
			<div class="col-md-12" >
				
				<table class="table table-bordered table-hover">
					<tr style="background-color: #57CFDF;">
					    <th class="col-md-2 text-center">教师工号</th>
						<th class="col-md-2 text-center">日期</th>
						<th class="col-md-2 text-center">教师</th>
						<th class="col-md-4 text-center">计划标题</th>
						<th class="col-md-2 text-center">操作选项</th>
					</tr>
					<!--第一个计划-->
					<c:forEach items="${planss}" var="pl">
					     
					     <tr style="background-color:#D4D4D4;">
					     <input type="text" style="display: none;" name="ids" value="${pl.id}"/>
					     <td ><input class="text-center" style="background-color:transparent;border:none;" readonly="readonly" name="ids" type="text" value="${pl.job_number}"/></td>
						<td><input class="text-center" style="background-color:transparent;border:none;" readonly="readonly" name="plan_date" type="text" value="${pl.plan_date}"/></td>
						<td><input class="text-center" style="background-color:transparent;border:none;" readonly="readonly" name="plan_titles" type="text" value="${pl.teacher_names}"/></td>
						<td><input  class="text-center" style="background-color:transparent;border:none;" readonly="readonly" name="plan_concent" type="text" value="${pl.plan_titles}"/></td>
						<td class="text-center">
							<a href="Plan_serverlet?method=Plan_cha_Servlet&ids=${pl.id}&findstyle=${findstyle}" class="btn btn-primary btn-xs active" role="button" style="border:0.5;">查看编辑</a>
							<a id="del" href="Plan_serverlet?method=Plan_del_Servlet&ids=${pl.id}&pagenum=${page.pageNum}&findstyle=${findstyle}" class="btn btn-primary btn-xs active" role="button" style="border:0.5;" >删除</a>
						</td>
					</tr>
					</c:forEach>
					
				</table>
				<div class="row">
				<div  id="page_nav" style="position:absolute;left: 400px">

		<a href="Plan_serverlet?method=page_fen&pagenum=1&findstyle=${findstyle}">首页</a>
		<a href="Plan_serverlet?method=page_fen&pagenum=${page.pageNum-1 }&findstyle=${findstyle}">上一页</a>
		
		<c:forEach begin="1" end="${page.totalPage }" var="index">
		
		 
		    <c:choose>
		    	<c:when test="${index==page.pageNum }">
		    	    
		    	  <span style="color:red">[${index}]</span>
					
		    	</c:when>
		    	<c:otherwise>
		    	<a href="Plan_serverlet?method=page_fen&pagenum=${index}&findstyle=${findstyle}">${index }</a>
		    	    
		    	</c:otherwise>
		    
		    
		    </c:choose>
		</c:forEach> 
		
		<a  href="Plan_serverlet?method=page_fen&pagenum=${page.pageNum+1}&findstyle=${findstyle}">下一页</a>
		<a href="Plan_serverlet?method=page_fen&pagenum=${page.totalPage }&findstyle=${findstyle}">末页</a>
		共${page.totalPage }页，${page.totalRecode }条记录        跳转到第<input value="${page.pageNum}" name="pn" id="pn_input" style="width: 20px"/>页
		<input id="pn_btn" type="button"  value="确定">
		
		</div>
		
		<script type="text/javascript">
		
		$("#pn_btn").click(function(){
			//获取页码值
			var pn=$("#pn_input").val();
			
			window.location="Plan_serverlet?method=page_fen&pagenum="+pn+"&findstyle=${findstyle}"
			
			
		})
		   
		$("#del").click(function(){
			//获取页码值
			if(confirm("确定要删除此条计划吗？")){
				return true;
			}else{
				return false;
			}
			
		    
			
			
		})
		
		</script>
	</div>
				
				
				
				
			</div>
			
		</div>
		
</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>