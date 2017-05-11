<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>网站访问量统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
  <%
  Integer counter_user = (Integer)application.getAttribute("counter_user");
  Integer counter = (Integer)application.getAttribute("counter");
   %>
  </head>
  
  <body>
    <span style="font-family:楷体;color:#02369B;">前台被访问次数</span>：共 <span style="font-weight:bold;font-size:15px;color:red;"><%=counter_user.intValue()%></span> 次
    <br/>
   <span style="font-family:楷体;color:#02369B;">后台被访问次数</span>：共 <span style="font-weight:bold;font-size:15px;color:red;"><%=counter.intValue()%></span> 次
  </body>
</html>
