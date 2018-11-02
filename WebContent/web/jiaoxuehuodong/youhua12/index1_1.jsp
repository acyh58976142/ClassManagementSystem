<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
 <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="row" style="height: 150px;"></div>
		   <div class="row">
		   	<div class="text-center" >
		   		<a class="text-uppercase" href="Plan_serverlet?method=page_fen&pagenum=1">教师计划</a>
		   		<input type="hidden" name="pagenum" value="1">
		   	</div>
		   	</div>
		   	<div class="row">
		   	</div>
</body>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>