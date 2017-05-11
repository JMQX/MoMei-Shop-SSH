<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script src="js/jquery-1.6.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/content.css">
	
	<script type="text/javascript">
	function my_show()
	{
	    $("#person_msg").show();
	}
	function my_hide()
	{
	   $("#person_msg").hide();
	}
	</script>
	
	<style type="text/css">
	#person_msg
	{
	   top:5px;
	   display:none;
	   position:absolute;
	   width:80px;
	   height:50px;
	   border:1px solid white;
	   background-color:white;
	}
	
	#person_msg ul{
	position: absolute;
	width: 80px;
	height: 50px;
	background:rgba(255, 255, 255, 0.98);
	box-shadow:rgba(0, 0, 0, 0.5) 0px 1px 2px 0px;
	}
	
	#person_msg ul li a{
	display: block;
	width: 80px;
	height: 22px;
	margin-bottom: 1px;
	font-size: 12px;
	line-height: 22px;
	text-align: center; 
	background: #f7f7f7;
	color:rgb(153, 153, 153);
	}
	
	#person_msg ul li a:hover{
	background: #1e89e0;
	}
	
	#person_t
	{
	   top:15px;
	   position:absolute;
	   cursor:pointer;
	}
	</style>
	
	</head>
   <%
     UserInfo user=(UserInfo)session.getAttribute("user");
     String userName="";
     if(user!=null)
     {
        userName=user.getUserName();
     }
   %>
  
<body>
<div class="header">
	<div class="header_line">
		<div class="logo"></div>
		<ul>
			<li><a href="UserInfo/GoodsMain.jsp" target="center">首页</a></li>
			<li><a href="javascript:void(0);">品牌商家</a></li>
			<li><a href="UserInfo/Main_Personal.jsp" target="_parent">我的订单</a></li>
			<li><a href="javascript:void(0);">加盟合作</a></li>
			<li><a href="UserInfo/score_market.jsp" target="_blank">积分商城</a></li>
		</ul>
	</div>
	<div class="user">
	   <%
	      if(user==null)
	      {
	    %>
		<div class="user_pic"></div>
		<a href="UserInfo/login.jsp" target="_parent" class="login">登录</a>
		<span>/</span>
		<a href="UserInfo/Register.jsp" target="_parent" class="register">注册</a>
		<%
		}else
		{
		 %>
		 <table onmouseover="my_show();" onmouseout="my_hide();" id="person_t">
		 <tr>
		 <td>
		 <font color=white><strong><%=user.getUserName() %></strong></font>
		 </td>
		 <td>
		  <div id="person_msg">
		 <ul>
		 <li><a href="UserInfo/Main_Personal.jsp" target="_parent">我的中心</a></li>
		 <li><a href="UserServlet?flag=exit" target="_parent">退出系统</a></li>
		 </ul>
		 </div>
		 </td>
		 </tr>
		 </table>
		 <%
		 }
		  %>
	</div>
	</div>
  </body>

</html>
