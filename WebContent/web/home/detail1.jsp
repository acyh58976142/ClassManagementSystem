<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
<title>公告具体内容</title>
<script type="text/javascript" src="WebContent/js/jQuery/jquery-2.2.3.min.js" ></script>
<link rel="stylesheet" href="css/style1.css" type="text/css" media="all"/>
</head>

<body>
<div id="hearder2" class="clearfix">
	<div class="logo">
   	  <img src="img/log.png">
  </div>
  <div class="logo-name">北大青鸟南京中博</div>
  <div class="form-tools">
   	  <form id="search" name="search" method="post">
      	<div class="tools-link">
        	
        </div>
        <div class="search-box">
            <input type="text" name="textfield" id="textfield" class="input-text" placeholder="请输入搜索关键词">
            <input type="image" src="img/search.png" class="input-submit" />
        </div>
  	  </form>
    </div>
</div>
<div id="menu-box" class="clearfix">
	<ul>
    	<li><a href="web/home/index.jsp">返回首页</a></li>
    </ul>
</div>
<div id="slider-box" style="background:url(img/slider4.jpg) center center;"></div>
<!-- detail -->
<div id="detail2-box" class="clearfix">
	<div class="tit-80"><a href="web/home/index.jsp">新闻中心</a> - 查看详情</div>
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
    	<h1>${news.new_title}</h1>
        <div class="content">
        	<br /><br />
        	${news.new_content}
            <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </div>
        <div class="n-p-box clearfix">
        	<!-- <div class="prv"><a href="#">上一篇：11新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻</a></div>
            <div class="next"><a href="#">下一篇：22新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题新闻</a></div> -->
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

</body>
</html>
