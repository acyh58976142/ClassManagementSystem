<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->

<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>登录</title>
<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
<link href="moban2/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="moban2/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="moban2/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="moban2/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
<meta name="keywords" content="">
<meta name="description" content="">
<style type="text/css">
#vcode{
				height:35px;
				width:40%;
				font-size:15pt;
				
				border-radius:5px;
				border:0;
				padding-left:8px;
			}
#code{
				color:#ffffff;/*字体颜色白色*/
				background-color:#000000;	
				font-size:20pt;
				font-family:"华康娃娃体W5";
				padding:0px 15px 0px 15px;
						
				cursor:pointer;
			}
</style>
</head>
<body onload="changeImg()">
<input type="hidden" id="TenantId" name="TenantId" value="" />
<!--<div class="header"></div>-->

<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="client/UserInfoServlet?method=login" method="post">
    <div class="col-xs-12">
    <div class="col-xs-3"></div>
    <div class="col-xs-8">
      <span class="errorMsg" style="color:red"><%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg") %></span>
    </div>
    </div>
    
   
      <div class="row cl">
       
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        
         <%--  <div class="formControls col-xs-8">
          <span class="errorMsg" style="color:red"><%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg") %></span>
          </div> --%>
        <div class="formControls col-xs-8">
          <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
         <input type="text" id="vcode" placeholder="验证码" value="验证码" onfocus="this.value=''" onblur="if(this.value=='')this.value='验证码'"/>
         <span id="code" title="看不清，换一张"></span>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态&nbsp;&nbsp;|&nbsp;&nbsp;<!-- <a href="">注册</a></label> -->
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="submit" id="login-btn" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;" >
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
    
  </div>
 
</div>

 <div class="shengming">Copyright kd22班 by 陈雨豪组</div>
<!--<div class="footer">Copyright kd22班 by 陈雨豪组</div>-->
<script type="text/javascript" src="moban2/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="moban2/static/h-ui/js/H-ui.min.js"></script>
<!--此乃百度统计代码，请自行删除-->
<script>

	
	var code;//声明一个变量用于存储生成的验证码
	document.getElementById("code").onclick=changeImg;
	function changeImg(){
		//alert("换图片");
		var arrays=new Array(
			'1','2','3','4','5','6','7','8','9','0',
			'a','b','c','d','e','f','g','h','i','j',
			'k','l','m','n','o','p','q','r','s','t',
			'u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T',
			'U','V','W','X','Y','Z'				
		);
		code='';//重新初始化验证码
		//alert(arrays.length);
		//随机从数组中获取四个元素组成验证码
		for(var i=0;i<4;i++){
		//随机获取一个数组的下标
			var r=parseInt(Math.random()*arrays.length);
			code+=arrays[r];
			//alert(arrays[r]);
		}
		//alert(code);
		document.getElementById('code').innerHTML=code;//将验证码写入指定区域
	}	
    




$("#login-btn").click(function(){
	      
	//获取用户输入的验证码
	var input_code=document.getElementById('vcode').value;
	//alert(input_code+"----"+code);
	if(input_code.toLowerCase()==code.toLowerCase()&&input_code.toLowerCase()!=null)
	{
		//验证码正确(表单提交)
		return true;
	}
	alert("请输入正确的验证码!");
	//验证码不正确,表单不允许提交
	return false;
	      
})
	
/* var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})(); */
</script>

</body>
</html>