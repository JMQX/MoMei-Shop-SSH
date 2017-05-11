<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    


  </head>
  <%
     if(session.getAttribute("user")==null)
     {
        //非法登陆，返回登陆页面
      //  response.sendRedirect("login.jsp");
         request.setAttribute("login_flag","check");
         request.getRequestDispatcher("login.jsp").forward(request,response);
     }
   %>
  <frameset border=0 framespacing=0 rows="12%,*"> 
  <frame   frameborder=0 src="UserInfo/top_personal.jsp" scrolling=no  noresize> 
  <frameset  border=0 framespacing=0 cols="20%,*"> 
  <frame  frameborder=0  src="UserInfo/left.jsp"  noresize> 
  <frame  frameborder=0 name="right" src="UserInfo/right.jsp"  noresize> 
  </frameset>
  </frameset>
</html>
