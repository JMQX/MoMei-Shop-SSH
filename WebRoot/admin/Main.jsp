<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
<%
   Admin admin=(Admin)session.getAttribute("admin");
   if(admin==null)
   {
       //非法登陆
       response.sendRedirect("login.jsp");
   }
   
  Integer counter = (Integer)application.getAttribute("counter");    //先从application里面获取计数器的key的值
  if(counter==null){
      //如果该值为null，说明第一次访问
     application.setAttribute("counter",1);
     counter=(Integer)application.getAttribute("counter");
     }else {
     //如果该值不为空，取出来进行累加
     int i = counter.intValue();
     i++;
     application.setAttribute("counter",i);//累加后再放进去
     }
  %>	

</HEAD>
<FRAMESET border=0 frameSpacing=0 rows="60, *" frameBorder=0>
<FRAME name=header src="admin/header.jsp" frameBorder=0 noResize scrolling=no>
<FRAMESET cols="170, *">
<FRAME name=menu src="admin/menu.jsp" frameBorder=0 noResize>
<FRAME name=msg src="admin/msg.jsp" frameBorder=0 noResize scrolling=yes>
</FRAMESET>
</FRAMESET>
<noframes>
</noframes>
</HTML>
 