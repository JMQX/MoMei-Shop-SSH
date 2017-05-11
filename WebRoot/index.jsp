<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>墨梅</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 

  <script src="js/jquery-1.11.3.js"></script>
   <script src="js/placeholder.js"></script>
   <link rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=9de162860c1b7848d95b89021f82c99f&plugin=AMap.Geocoder"></script> 
<%
 UserInfo user=(UserInfo)session.getAttribute("user");
 %>
<body>
	<div class="header">
		<div class="logo"></div>
		<div class="nav">
			<ul>
				<li><a href="#">玉枣</a></li>
				<li><a href="#">核桃</a></li>
				<li><a href="#">坚果</a></li>
				<li><a href="#">葡萄干</a></li>
			</ul>
		</div>
		<div class="user">
		    <%
		      if(user==null)
		      {
		     %>
			<div class="index_register"><a href="UserInfo/Register.jsp">注册</a></div>
			<div class="index_login"><a href="UserInfo/login.jsp">登录</a></div>
			<%
			}else
			{
			 %>
			  <div class="index_register"><a href="UserInfo/Main.jsp"><%=user.getUserName() %></a></div>
			 <%
			 }
			  %>
		</div>
	</div>
	<div class="search_box">
		<div class="search_main">
				<div class="search_div">
					<div class="search_select">
						<p>哈尔滨</p>
						<span></span>
					</div>
					<input type="text" id="search" placeholder="请输入你的收货地址（写字楼，小区，街道或者学校）"></input>
				</div>
				<a href="javascript:search_request()">搜索</a>

		</div>
	</div>
	<div class="content">
		<div id="map_container">
			<span></span>
			<div id="map_close"></div>
			<div id="container"></div>
		</div>
		<div class="title"></div>
		<div id="content_main">
			<span class="jrsj"></span>
			<a href="javascript:turn_left()" id="btn_left"></a>
			<div id="pic_box">
				<a href="#"><img src="./images/p1.jpg"></a>
				<a href="#"><img src="./images/p2.jpg"></a>
				<a href="#"><img src="./images/p3.jpg"></a>
			</div>
			<a href="javascript:turn_right()" id="btn_right"></a>
		</div>
	</div>
	<div id="footer">
		<div id="footer_nav">
			<ul>
				<li><a href="javascript:void(0);">手机版下载</a></li>
				<li><a href="javascript:void(0);">关注公众号</a></li>
				<li><a href="javascript:void(0);">我要开店</a></li>
				<li><a href="javascript:void(0);">加入我们</a></li>
			</ul>
		</div>
		<div class="message"></div>
		<div class="qrcode" alt="二维码"></div>
	</div>

<script src="js/main.js"></script>
</body>
</html>