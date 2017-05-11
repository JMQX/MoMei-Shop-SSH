<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>墨梅商城</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <%
     if(request.getAttribute("Msg")!=null)
     {
    request.setAttribute("Msg",request.getAttribute("Msg").toString());
    }else
    {
     request.setAttribute("Msg","");
     }
     
     Integer counter_user = (Integer)application.getAttribute("counter_user");    //先从application里面获取计数器的key的值
     if(counter_user==null){
      //如果该值为null，说明第一次访问
     application.setAttribute("counter_user",1);
     counter_user=(Integer)application.getAttribute("counter_user");
     }else {
     //如果该值不为空，取出来进行累加
     int i = counter_user.intValue();
     i++;
     application.setAttribute("counter_user",i);//累加后再放进去
     }
   %>
   <script type="text/javascript">
  var Msg='<%=request.getAttribute("Msg")%>';
  if(Msg!="")
  {
    alert(Msg);
  }
  </script>

 <frameset border=0 framespacing=0 rows="12%,*"> 
  <frame   frameborder=0 src="UserInfo/top.jsp" scrolling=no  noresize> 
  <frame   frameborder=0 name="center" src="UserInfo/GoodsMain.jsp"   noresize> 
 </frameset>
 
</html>
