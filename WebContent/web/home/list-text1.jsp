<%@page import="cn.kgc.entity.News"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有新闻公告</title>
<script type="text/javascript" src="WebContent/js/jQuery/jquery-2.2.3.min.js" ></script>
<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
<link rel="stylesheet" href="css/style1.css" type="text/css" media="all"/>
<script type="text/javascript">
  var a=$(".input-text").value();
</script>
</head>

<body>

<div id="hearder2" class="clearfix">
	<div class="logo">
   	  <img src="img/log.png">
  </div>
  <div class="logo-name">北大青鸟南京中博</div>
  <div class="form-tools">
   	  <form id="search" name="search" method="post" action="NewsSeverlet?method=news_mohu&pagenum=1">
      	<div class="tools-link">
        	
        </div>
        <div class="search-box">
            <input type="text" name="textfield" id="textfield" class="input-text" >
            <input  type="image" src="img/search.png" class="input-submit"  id="timit" href="NewsSeverlet?method=news_mohu&pagenum=1"/>
          
        </div>
  	  </form>
    </div>
</div>
<div id="menu-box" class="clearfix">
	<ul>
    	<li><a href="web/home/index.jsp">返回首页</a></li>
    	<li><a href="NewsSeverlet?method=adode&add=a&titles=">新加公告</a></li>
      
    </ul>
   
</div>
<div id="slider-box" style="background:url(img/slider4.jpg) center center;"></div>
<!-- detail -->
<div id="detail2-box" class="clearfix">
	<div class="tit-80"><a href="list-text1.html">新闻中心</a> - 查看详情</div>
    <div class="tabula-box">
    	<div class="max-tit">新闻中心</div>
        <ul>
            <li><a href="NewsSeverlet?method=newFen1&pagenum=1">全部</a></li>
            <li><a href="NewsSeverlet?method=news_fenByType&type=<%="表彰公告"%>&pagenum=1">表彰公告</a></li>
            <li><a href="NewsSeverlet?method=news_fenByType&type=<%="惩罚公告"%>&pagenum=1">惩罚公告</a></li>
            <li><a href="NewsSeverlet?method=news_fenByType&type=<%="校园趣事"%>&pagenum=1">校园趣事</a></li>
            <li><a href="NewsSeverlet?method=news_fenByType&type=<%="体育活动"%>&pagenum=1">体育活动</a></li>
           
        </ul>
    </div>
    
    <div class="content-box">
    	<h1>这里是新闻类型列表标题</h1>
        <ul>
           <c:forEach items="${News}" var="news">
            <div style="position:relative;right:25px;top:40px;"><a href="NewsSeverlet?method=new_del&title=${news.new_title}&pagenum=${page.pageNum}&yinCang=${yinCang}">删除<span id="dele" style="display: none">${yinCang}</span></a><br/>
            <a href="NewsSeverlet?method=adode&title=${news.new_title}&pagenum=${page.pageNum}&yinCang=${yinCang}&add=b">更改<span id="change" style="display: none">${yinCang}</span></a></div>
           <li class="text"><a href="NewsSeverlet?method=news_cha1&title=${news.new_title}&pagenum=${page.pageNum}&chose=${chose}&type=${News[0].new_type}">${news.new_title}</a>
           <span>${news.new_date}</span>
          
           </li>
         
           </c:forEach>
            </ul>
        <div class="tcdPageCode">
        <a href="NewsSeverlet?method=new_chose&pagenum=1&chose=${chose}&type=${News[0].new_type}">首页</a>
        <a href="NewsSeverlet?method=new_chose&pagenum=${page.pageNum-1}&chose=${chose}&type=${News[0].new_type}">上一页</a>
        <c:forEach begin="1" end="${page.totalPage }" var="index">
		
		 
		    <c:choose>
		    	<c:when test="${index==page.pageNum }">
		    	    
		    	  <span style="color:red">[${index}]</span>
					
		    	</c:when>
		    	<c:otherwise>
		    	<a href="NewsSeverlet?method=new_chose&pagenum=${index}&chose=${chose}&type=${News[0].new_type}">${index}</a>
		    	    
		    	</c:otherwise>
		    
		    
		    </c:choose>
		</c:forEach> 
         <a href="NewsSeverlet?method=new_chose&pagenum=${page.pageNum+1}&chose=${chose}&type=${News[0].new_type}">下一页</a>
         
         <a href="NewsSeverlet?method=new_chose&pagenum=${page.totalPage}&chose=${chose}&type=${News[0].new_type}">末页</a>
		共${page.totalPage }页，${page.totalRecode }条记录        跳转到第<input value="${page.pageNum}" name="pn" id="pn_input" style="width: 20px"/>页
		<input id="pn_btn" type="button"  value="确定">
		
		</div>
		
		<script type="text/javascript">
		
		$("#pn_btn").click(function(){
			//获取页码值
			var pn=$("#pn_input").val();
			
			window.location="NewsSeverlet?method=new_chose&pagenum="+pn+"&chose=${chose}&type=${News[0].new_type}";
		})
		   $("#dele").click(function(){
				//获取页码值
				if(confirm("确认删除这条公告吗？")){
					return true;
				}else{
					return false;
				}
				
			    
				
				
			})
		
		
		</script>
        </div>
    </div>
</div>
<!-- div5 -->
<div id="div5" class="clearfix">
    <div class="link-box">
    	<span>友情链接：</span>
        <select onChange="frlink(this)" name="select" class="select-link">
            <option selected>--- 教育类网站 ---</option>
            <option value="http://www.veryhuo.com">烈火网</option>
            <option value="http://www.sina.com.cn">新浪网</option>
            <option value="http://www.163.com">网易</option>
        </select>
        <select onChange="frlink(this)" name="select" class="select-link">
            <option selected>--- 政府类网站 ---</option>
            <option value="http://www.china.com">中华网</option>
            <option value="http://www.sina.com.cn">新浪网</option>
            <option value="http://www.163.com">网易</option>
        </select>
        <select onChange="frlink(this)" name="select" class="select-link">
            <option selected>--- 校园类网站 ---</option>
            <option value="http://www.veryhuo.com">烈火网</option>
            <option value="http://www.sina.com.cn">新浪网</option>
            <option value="http://www.163.com">网易</option>
        </select>
        <select onChange="frlink(this)" name="select" class="select-link">
            <option selected>--- 其它类网站 ---</option>
            <option value="http://www.veryhuo.com">烈火网</option>
            <option value="http://www.sina.com.cn">新浪网</option>
            <option value="http://www.163.com">网易</option>
        </select>
    </div>
</div>
<div id="div6">Copyright &copy; 2016 物理与核工程虚拟仿真教学中心 All rights reserved.</div>
<script src="js/jquery-1.8.3.min.js"></script>
<script src="js/jquery.page.js"></script>
<script>
    $(".tcdPageCode").createPage({
        pageCount:100,
        current:1,
        backFn:function(p){
            //console.log(p);
        }
    });
<!-- </script>
<script type="text/javascript">
     $(function(){
	      var a=$("#spansss").text();
	      alert(a)
	
})
</script> -->
</body>
</html>
