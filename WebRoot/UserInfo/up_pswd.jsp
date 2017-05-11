<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
   <script type="text/javascript" src="js/jquery.validate.js"></script>
   <script type="text/javascript" src="js/jquery.metadata.js"></script>
    <script type="text/javascript" src="js/messages_zh.js"></script>
    <link rel="stylesheet" type="text/css" href="css/personMsg.css">
    
    <script type="text/javascript">
    $(function(){
       $("#passwd").blur(function(){
        var passwd=$("#passwd").val();
            $.ajax({
            url:"UserServlet",
            type:"post",
            data:{flag:"judgePswd",passwd:passwd},
             success:function(data){
               if(data!=""&&passwd!="")
               {
			     alert(data);
			     $("#passwd").val("");
			   }
			}
         });
       });
    });
    
     $(document).ready(function(){
    
           jQuery.validator.addMethod("byteMaxLength", function(value,
					element, param) {
				var length = value.length;
				for ( var i = 0; i < value.length; i++) {
					if (value.charCodeAt(i) > 127) {
						length++;
					}
				}
				return this.optional(element) || (length <= param);
			}, $.validator.format("不能超过{0}个字节(一个中文字算2个字节)"));
  
			
			  jQuery.validator.addMethod("numFormatPasswd",function(value,element,param){
			 return this.optional(element) ||  /^\w{6,}$/.test(value);
			},$.validator.format("密码至少为6位")
			);
			
			$("#frm").validate();
		
			check_infor();

});

   function check_infor(){
	 
	  $("#passwdNew").attr("class","{byteMaxLength:30,numFormatPasswd:true}");
	
	}
	
	 function check(){
	  var passwd=$("#passwd").val();
	  var passwdNew=$("#passwdNew").val();
	  var passwdNewRep=$("#passwdNewRep").val();
	  if(passwd==""||passwdNew==""||passwdNewRep=="")
	  {
      alert("请完善信息!");
     return false;
       }
       if(passwdNewRep!=passwdNew)
       {
           alert("两次密码不一致!");
           return false;
       }
    
	 }
    </script>
    
    <style type="text/css">
     label{
       color:red;
        font-family:"楷体";
     }
     input
     {
       width:150px;
       height:30px;
     }
     #sb
     {
       width:60px;
	   height:30px;
	   background-color:#0CC;
	   border-radius:6px;
	   color:white;
	   font-weight:bold;
     }
    </style>
	
  </head>
  <%
    UserInfo user=(UserInfo)session.getAttribute("user");
   %>
  <body>
  <div class="my_box">
  <h3>修改密码</h3>
  <br/>
 <hr/><br/>
     <form id="frm" name="frm" action="UserServlet?flag=up_passwd&userName=<%=user.getUserName() %>" onkeydown="if(event.keyCode==13){return false;}" onsubmit="return check();" method="post">
     <table cellspacing=10>
     <tr valign=middle>
     <td>原密码</td>
     <td><input type="password" placeholder="请输入原密码" id="passwd" name="passwd"/></td>
     </tr>
     <tr valign=middle>
      <td>新密码</td>
       <td><input type="password" placeholder="请输入新密码" id="passwdNew" name="passwdNew"/><label></label></td>
     </tr>
     <tr valign=middle>
      <td>重复密码</td>
       <td><input type="password" id="passwdNewRep" name="passwdNewRep"/></td>
     </tr>
     <tr>
      <td colspan=2 align=left><input type="submit" id="sb" value="修改"/></td>
     </tr>
     </table>
     </form>
     </div>
  </body>
</html>
