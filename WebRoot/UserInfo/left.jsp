<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
   <link rel="stylesheet" type="text/css" href="css/left.css">
   <script type="text/javascript">
   function show_active(index)
   {
       for(var i=0;i<=7;i++)
       {
          if(index==i)
          {
             $("#"+index+"").css("color","#0089DC");
          }else
          {
             $("#"+i+"").css("color","#666666");
          }
       }
   }
   </script>
   
    <style type="text/css">
     a:hover 
	 { 
	   color:#F0BCCB;
	 }
   </style>
   	
  </head>
  <%
    UserInfo user=(UserInfo)session.getAttribute("user");
   %>
  
  <body>
    <ul class="ul_style">
  <li><h3><img src="images/gs_left.jpg"></img>&nbsp;<a id="0" style="color:#0089DC;" onclick="show_active(0);" href="UserInfo/Main_Personal.jsp" target="_parent">个人中心</a></h3></li>
    <li><h3><img src="images/dd_left.jpg"></img>&nbsp;我的订单</h3></li>
      <li><a id="1" onclick="show_active(1);" href="BookServlet?flag=show_booksByUser&userId=<%=user.getUserId() %>&pageNow=1" target="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单记录</a></li>
       <li><a id="2" onclick="show_active(2);" href="BookServlet?flag=show_booksGiveupByUser&userId=<%=user.getUserId() %>&pageNow=1" target="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;退单记录</a></li>
          <li><h3><img src="images/cc_left.jpg"></img>&nbsp;我的资产</h3></li>
            <li><a id="3" onclick="show_active(3);" href="UserInfo/score.jsp" target="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我的积分</a></li>
              <li><h3><img src="images/ps_left.jpg"></img>&nbsp;我的资料</h3></li>
              <li><a id="4" onclick="show_active(4);" href="UserInfo/personMsg.jsp" target="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;个人资料</a></li>
              <li><a id="5" onclick="show_active(5);" href="AddressServlet?flag=show&userId=<%=user.getUserId() %>" target="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地址管理</a></li>
                <li><a id="6" onclick="show_active(6);" href="UserInfo/up_pswd.jsp" target="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修改密码</a></li>
                <li><h3><img src="images/sc_left.jpg"></img>&nbsp;我的收藏</h3></li>
                <li><a id="7" onclick="show_active(7);" href="UserInfo/store.jsp" target="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我的收藏</a></li>
  </ul>
  </body>
</html>
