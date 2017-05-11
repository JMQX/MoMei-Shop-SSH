<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
	 <script src="js/placeholder.js"></script>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	
	<script type="text/javascript">
	function changeImg(){
   var imgSrc = $("#imgObj");
   var src = imgSrc.attr("src");
    imgSrc.attr("src",chgUrl(src));
   }
   //时间戳
   //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
   function chgUrl(url){
   var timestamp = (new Date()).valueOf();
   url = url.substring(0,17);
   if((url.indexOf("&")>=0)){
    url = url + "×tamp=" + timestamp;
   }else{
    url = url + "?timestamp=" + timestamp;
   }
   return url;
  }
  
   function deal()
   {
       var uName=document.getElementById("uName").value;
       var passwd=document.getElementById("passwd").value;
       var YZM=document.getElementById("YZM").value;
       if(uName=="")
       {
         alert("请输入用户名!");
         return false;
       }
       else if(passwd=="")
       {
         alert("请输入密码!");
         return false;
       }else if(YZM=="")
       {
         alert("请输入验证码!");
         return false;
       }
   }
   
   $(function(){

       $("#YZM").blur(function(){
        if($("#YZM").val()!="")
        {
         var YZM=$("#YZM").val();
         $.ajax({
            url:"UserServlet",
            type:"post",
            data:{flag:"login_checkYZM",YZM:YZM},
             success:function(data){
                 if(data!="")
                 {
			     alert(data);
			     $("#YZM").val("");
			     }
			}
         });
         }
       });
    });

	</script>
	
  </head>
  
  <%
       String login_flag="init";
       if(request.getAttribute("login_flag")!=null)
       {
          login_flag=request.getAttribute("login_flag").toString();
       }
   %>
  
  <body>
<div class="box">
         <form action="UserServlet?flag=login&login_flag=<%=login_flag %>" onkeydown="if(event.keyCode==13){return false;}"  onsubmit="return deal()" method="post">
		<div class="login_content"></div>
		<div class="login_box">
			<div class="login_title left">登录</div>
			<div class="user_box left">
				<span></span>
				<input type="text" id="uName" name="uName" placeholder="用户名"></input>
			</div>
			<div class="password_box left">
				<span></span>
				<input type="password" id="passwd" name="passwd" placeholder="密码"></input>
			</div>
			<div class="auth_box left">
				<input type="text" id="YZM" name="YZM" placeholder="验证码"></input>
				<img src="ValidateServlet" title="验证码" id="imgObj">	
			</div>
			<a href="javascript:changeImg();" id="re_auth">看不清楚</a>
			<input type="submit" id="login_btn" value="登陆"/>
			<a href="UserInfo/Register.jsp" id="login_register">新用户注册</a>
			<a href="" id="login_forget">忘记密码</a>
		</div>
		</form>
	</div>
<script src="js/jquery-1.11.3.js"></script>
<script src="js/main.js"></script>
  </body>
  <%
     if(request.getAttribute("ErrorMsg")!=null)
     {
    request.setAttribute("ErrorMsg",request.getAttribute("ErrorMsg").toString());
    }else
    {
     request.setAttribute("ErrorMsg","");
     }
   %>
  <script type="text/javascript">
  var Msg='<%=request.getAttribute("ErrorMsg")%>';
  if(Msg!="")
  {
    alert(Msg);
  }
  </script>
</html>
