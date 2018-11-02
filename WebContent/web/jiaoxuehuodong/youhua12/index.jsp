<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>HTML5 3D卷纸表单填写动画特效 - 站长素材</title>
<link rel="stylesheet" href="iconfont/iconfont.css">
<style>  /* css的注释我就不写了- - */
    
	*{
		margin: 0;
		padding: 0;
		background-image: url(../img/zzpic12798.jpg);
		height: 100%;
		width: 100%;
	}
	body{
		perspective: 1400px;
		perspective-origin: 40% 50%;
		position: relative;
		top: -50px;
	}
	div{
		transform-style: preserve-3d;
		font-size: 20px;
	}
	.wrap{
		width: 800px;
		height: 800px;
		margin: -70px auto;
		position: relative;
		transform-origin: top;
		transform: rotateX(0deg) rotateY(-90deg) rotateZ(0deg) scale(0) translate3d(0px,0px,-500px);
		border: 0;
		transition: 1s;
	}
	@keyframes move{
		0%{transform: rotateX(0deg) rotateY(-90deg) rotateZ(0deg) scale(0) translate3d(0px,0px,-500px);}
		100%{transform: rotateX(0deg) rotateY(20deg) rotateZ(0deg) scale(.6) translate3d(0px,0px,0px);}
	}
	#btn1{
		width: 180px;
		height: 50px;
		display: block;
		margin: 100px auto;
		border: 0;
		outline: 0;
		background-color: skyblue;
		border-radius: 10px;
		color: #fff;
		cursor: pointer;
		transition: 1s;
	}
	.wrap .shadow{
		position: absolute;
		width: 800px;
		height: 1200px;
		transform: translateZ(-200px);
		box-shadow: 100px 100px 200px #dfdfdf,100px -100px 200px #dfdfdf,-100px 100px 200px #dfdfdf,-100px -100px 200px #dfdfdf;
		background-color: #dfdfdf;
		border: 0;
		top: -100px;
		transition: 2s 2s;
		opacity: 0;
	}
	@keyframes change1{
		0%{opacity: 0;}
		100%{opacity: 1;}
	}
	.box{
		width: 800px;
		height: 50px;
		line-height: 50px;
		background-color: #fff;
		border: 1px solid #fff;
		transform-origin: top;
		position: absolute;
		top: 51px;
		left: 0;
	}
	@keyframes change2{
		0%{background-color: #fff;}
		30%{background-color: #f5eedc;}
		100%{background-color: #87ceeb;}
	}
	.box1{
		transform: rotateX(-70deg);
	}
	.box2{
		transform: rotateX(-50deg);
	}
	.box3{
		transform: rotateX(-50deg);
	}
	.box4{
		transform: rotateX(-20deg);
	}
	.box5{
		transform: rotateX(-35deg);
	}
	.box6{
		transform: rotateX(-60deg);
	}
	.box7{
		transform: rotateX(-50deg);
	}
	.box8{
		transform: rotateX(-15deg);
	}
	.box9{
		transform: rotateX(-2deg);
	}
	.box10{
		transform: rotateX(-1deg);
	}
	.box11{
		transform: rotateX(-1deg);
	}
	.box12{
		transform: rotateX(-1deg);
	}
	.box13{
		transform: rotateX(-1deg);
	}
	.box14{
		transform: rotateX(-1deg);
	}
	.box15{
		transform: rotateX(-1deg);
	}
	.box16{
		transform: rotateX(0deg);
	}
	.box17{
		transform: rotateX(0deg);
	}
	.box18{
		transform: rotateX(-1deg);
	}
	.box19{
		transform: rotateX(-1deg);
	}
	.box20{
		transform: rotateX(-1deg);
	}
	.box21{
		transform: rotateX(-1deg);
	}
	.box22{
		transform: rotateX(-1deg);
	}
	.box23{
		transform: rotateX(-1deg);
	}
	.box24{
		transform: rotateX(-1deg);
	}
	.box25{
		transform: rotateX(-1deg);
	}
	.box26{
		transform: rotateX(-1deg);
	}
	.box27{
		transform: rotateX(-1deg);
	}
	.box28{
		transform: rotateX(-10deg);
	}
	.box29{
		transform: rotateX(-15deg);
	}
	.box30{
		transform: rotateX(-45deg);
	}
	.box10 h4{
		text-align: center;
		color: #fff;
		font-size: 30px;
	}
	.con{
		width: 800px;
		height: 50px;
		top: 0;
		cursor: pointer;
	}
	.con:hover{
		background-color: #f5eedc;
	}
	.icon2{
		opacity: 0;
	}
	.checked{
		width: 50px;
		height: 50px;
		position: absolute;
		top: 0;
		left: 20px;
	}
	.iconfont{
		font-size: 45px;
		position: absolute;
		color: deeppink;
		transition: 0.6s;
	}
	.text{
		width: 600px;
		margin-left: 100px;
		transition: 0.6s;
		text-align: left;
	}
	.con:hover .text{
		text-indent: 20px;
	}
	#all{
		display: block;
		width: 100px;
		margin-left: 80px;
	}
	p{
		font-size: larger;
	}
	a{
		font-size: larger;
	}
</style>
</head>
<body>
<button id="btn1">教学计划</button>
<!--<button id="btn1">问题及解决方案</button>-->
<div class="wrap">
	<div class="shadow"  ></div>
	<div class="box1 box">
		<div class="box2 box">
			<div class="box3 box">
				<div class="box4 box">
					<div class="box5 box">
						<div class="box6 box">
							<div class="box7 box">
								<div class="box8 box">
									<div class="box9 box">
										<div class="box10 box">
											<h4>教学计划列表</h4>
											<div class="box11 box">
									<a href="http://localhost:8080/ClassManagementSystem/web/jiaoxuehuodong/youhua12/show1.jsp"style="position: relative;left: 100px;" >添加一个计划</a>
											
												<div class="box12 box">
													<div class="con">
													<form action="http://localhost:8080/ClassManagementSystem/Plan_cha_Servlet" method="post">
													   <p class="text"><input type="submit" name="jihua" value="计划测试1"/></p>
													</form>	
													</div>
													<div class="box13 box">
														<div class="con">
															<p class="text"><a href="show1.html">计划一：</a></p>
														</div>
														<div class="box14 box">
															<div class="con">
																<p class="text"><a href="show1.html">计划一：</a></p>
															</div>
															<div class="box15 box">
																<div class="con">
																	<p class="text"><a href="show1.html">计划一：</a></p>
																</div>
																<div class="box16 box">
																	<div class="con">
																		<p class="text"><a href="show1.html">计划一：</a></p>
																	</div>
																	<div class="box17 box">
																		<div class="con">
																			<p class="text"><a href="show1.html">计划一：</a></p>
																		</div>
																		<div class="box18 box">
																			<div class="con"> 
																			  <p class="text" ><a name="ptl" href="http://localhost:8080/ClassManagementSystem/Plan_serverlet?method==plan_cha1"><%=request.getAttribute("plan_title")%></a></p>
																			</div>
							                                                    	<div class="box19 box">
																				<p class="text"><a href="show1.html">计划一：</a></p>
																				</div> 
																				<div class="box20 box">
																					<div class="box21 box">
																						<div class="con">
																							<div class="checkedsss">
																								
																								<a href="index.html"style="position: relative;left: 100px;" >上一页</a>
																								<a href="index.html"style="position: relative;left: 500px;" >上一页</a>
																							</div>
																						</div>
																						<div class="box22 box">
																							<div class="box23 box">
																								<div class="box24 box">
																									<div class="box25 box">
																										<div class="box26 box">
																											<div class="box27 box">
																												<div class="box28 box">
																													<div class="box29 box">
																														<div class="box30 box"></div>
																													</div>
																												</div>
																											</div>
																										</div>
																									</div>
																								</div>
																							</div>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	
	var oWrap = document.getElementsByClassName('wrap')[0],
		oBox = document.getElementsByClassName('box'),
		oBtn1 = document.getElementById("btn1"),
		oShadow = document.getElementsByClassName("shadow")[0],
		oCon = document.getElementsByClassName("con"),
		oIcon1 = document.getElementsByClassName('icon1'),
		oIcon2 = document.getElementsByClassName('icon2'),
		num = [0,0,0,0,0,0,0,0,0]; //控制每一行的开关


	oBtn1.onclick = function () { //点击按钮之后的入场动画
		oWrap.style.cssText = "animation: move 2s ease forwards;";
		oShadow.style.cssText = "animation: change1 1s linear forwards;";
		for(var i = 0;i < 30;i++){
			oBox[i].style.cssText = `animation: change2 0.6s ${i*0.1-0.4}s linear forwards;`;
		}
		this.style.cssText = "opacity: 0;visibility: hidden;";
	}

	
</script>
</body>
</html>
