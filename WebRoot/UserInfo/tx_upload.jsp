<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传头像</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    


  </head>
    <%
     UserInfo user=(UserInfo)session.getAttribute("user");
   %>
  <body>
  <form action="UserServlet?flag=addTx&userName=<%=user.getUserName() %>" method="post" enctype="multipart/form-data">
  <input type="file" id="pic" name="pic"/>
  <input type="submit" value="保存"/>
  </form>
  </body>
</html>
