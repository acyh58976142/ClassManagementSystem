<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<base href="http://<%= request.getServerName() %>:<%= request.getServerPort() %><%= request.getContextPath() %>/"> 
<link rel="stylesheet" href="css/style1.css" type="text/css" media="all"/>
<script src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
//图片滚动 调用方法 imgscroll({speed: 30,amount: 1,dir: "up"});
$.fn.imgscroll = function(o){
	var defaults = {
		speed: 40,
		amount: 0,
		width: 1,
		dir: "left"
	};
	o = $.extend(defaults, o);
	
	return this.each(function(){
		var _li = $("li", this);
		_li.parent().parent().css({overflow: "hidden", position: "relative"}); //div
		_li.parent().css({margin: "0", padding: "0", overflow: "hidden", position: "relative", "list-style": "none"}); //ul
		_li.css({position: "relative", overflow: "hidden"}); //li
		if(o.dir == "left") _li.css({float: "left"});
		
		//初始大小
		var _li_size = 0;
		for(var i=0; i<_li.size(); i++)
			_li_size += o.dir == "left" ? _li.eq(i).outerWidth(true) : _li.eq(i).outerHeight(true);
		
		//循环所需要的元素
		if(o.dir == "left") _li.parent().css({width: (_li_size*3)+"px"});
		_li.parent().empty().append(_li.clone()).append(_li.clone()).append(_li.clone());
		_li = $("li", this);

		//滚动
		var _li_scroll = 0;
		function goto(){
			_li_scroll += o.width;
			if(_li_scroll > _li_size)
			{
				_li_scroll = 0;
				_li.parent().css(o.dir == "left" ? { left : -_li_scroll } : { top : -_li_scroll });
				_li_scroll += o.width;
			}
				_li.parent().animate(o.dir == "left" ? { left : -_li_scroll } : { top : -_li_scroll }, o.amount);
		}
		
		//开始
		var move = setInterval(function(){ goto(); }, o.speed);
		_li.parent().hover(function(){
			clearInterval(move);
		},function(){
			clearInterval(move);
			move = setInterval(function(){ goto(); }, o.speed);
		});
	});
};
//友情链接下拉框跳转弹出新窗口
function frlink(selObj){
window.open(selObj.options[selObj.selectedIndex].value);}
</script>
</head>

<body>
<div id="hearder2" class="clearfix">
	<div class="logo">
   	  <img src="img/log.png">
  </div>
  <div class="logo-name">北大青鸟南京中博</div>
  <div class="form-tools">
   	  <!-- <form id="search" name="search" method="post">
      	<div class="tools-link">
        
        </div>
        <div class="search-box">
            <input type="text" name="textfield" id="textfield" class="input-text" placeholder="请输入搜索关键词">
            <input type="image" src="img/search.png" class="input-submit" />
        </div>
  	  </form> -->
    </div>
</div>
<div id="menu-box" class="clearfix">
	<ul>
    	<li><a href="index.html">首页</a></li>
        <li><a href="NewsSeverlet?method=newFen1&pagenum=1">公告中心</a></li>
       
    	<li><a class="gao" href="NewsSeverlet?method=newFen1&pagenum=1&yinCang=1" >公告管理</a></li>
        
    
    </ul>
</div>
<div id="slider-box" style="background:url(img/slider4.jpg) center center;"></div>
<!-- index1-div1 -->
<div id="index1-div1" class="clearfix">
	<div class="index1-left675">
    	<div class="title-box">中心简介<a href="detail1.html" class="title-more" title="查看更多"><img src="img/12.gif"></a></div>
        <p><img src="img/school.jpg" align="left">深圳大学1983年经国家教育部批准设立。中央、教育部和地方高度重视特区大学建设。北大援建中文、外语类学科，清华援建电子、建筑类学科，人大援建经济、法律类学科，一大批知名学者云集深圳大学。建校伊始，学校在高校管理体制,北大援建中文、外语类学科，清华援建电子、建筑类学科，人大援建经济北大援建中文、外语类学科，建筑类学科，人大援建经济北大援建中文、外语类学科，清华援建电子...<a href="detail1.html">查看更多...</a></p>
    </div>
    <div class="index1-right300">
    	<div class="title-box">通知公告<a href="NewsSeverlet?method=newFen1&pagenum=1" class="title-more" title="查看更多"><img src="img/12.gif"></a></div>
          <ul>
              <li><a href="detail1.html">通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告</a><span>2017-02-23</span></li>
              <li><a href="detail1.html">通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告</a><span>2017-02-23</span></li>
              <li><a href="detail1.html">通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告</a><span>2017-02-23</span></li>
              <li><a href="detail1.html">通知公告通知公告通知公告通知公告通知公告通知公告通知公告通知公告</a><span>2017-02-23</span></li>
          </ul>
    </div>
</div>
<!-- index1-div2 -->

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

</body>
</html>

