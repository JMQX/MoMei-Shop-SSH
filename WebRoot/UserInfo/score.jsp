<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的积分</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	 <link rel="stylesheet" type="text/css" href="css/personMsg.css">
	 
	 <style type="text/css">
	 .score_box
	 {
	    margin-top:20px;
	    width:980px;
	    height:100px;
	    border:1px solid #EEEEEE;
	    background-color:#F7F7F7;
	    border-radius:6px;
	 }
	 #score
	 {
	    color:#F86542;
	    font-size:25px;
	    font-weight:bold;
	 }
	 </style>
	
  </head>
  
  <%
     UserInfo user=(UserInfo)session.getAttribute("user");
   %>
  
  <body>
  <div class="my_box">
  <h3>我的积分</h3>
  <br/>
 <hr/>
 <div class="score_box">
 <table style="margin-top:30px;margin-left:30px;">
 <tr>
 <td>当前账户积分:&nbsp;</td>
 <td><span id="score"><%=user.getScore() %></span>&nbsp;</td>
 <td width=50px>分</td>
 <td><a href="">如何获得积分?</a></td>
 </tr>
 </table>
 </div>
 </div>
  </body>
</html>
