<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
	
	<script type="text/javascript">
	$(function(){
       $("#passwd").blur(function(){
        var passwd=$("#passwd").val();
            $.ajax({
            url:"AdminServlet",
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
    
    function validateInput()
	{
		var result=true;
		document.getElementById("lbl_pswd").innerHTML="*";	
		document.getElementById("lbl_pswd_now").innerHTML="*";		
		document.getElementById("lbl_pswd_rep").innerHTML="*";			
		if(form1.passwd_new.value=="")
		{
			document.getElementById("lbl_pswd").innerHTML="新密码不能为空";
			result=false;		
		}
		if(form1.passwd_new.value.length<6)
		{
		  document.getElementById("lbl_pswd").innerHTML="新密码不能小于6个字符";
		  result=false;	
		}
		if(form1.passwd.value=="")
		{
			document.getElementById("lbl_pswd_now").innerHTML="原密码不能为空";
			result=false;		
		}
		if(form1.passwdNewRep.value=="")
		{
			document.getElementById("lbl_pswd_rep").innerHTML="重复密码不能为空";
			result=false;		
		}
		if(form1.passwdNewRep.value!=form1.passwd_new.value)
		{
		    document.getElementById("lbl_pswd_rep").innerHTML="两次密码不一致";
			result=false;
		}
		
		if(result==true)
		{
			result=confirm("确定提交吗?");
		}
		
		return result;
			
	}
	
	//重置(清除页面信息)
	function clearInfo()
	{
		document.getElementById("lbl_pswd").innerHTML="*";
		document.getElementById("lbl_pswd_now").innerHTML="*";		
		document.getElementById("lbl_pswd_rep").innerHTML="*";		
		document.form1.passwd_new.value="";
		document.form1.passwdNewRep.value="";
		document.form1.passwd.value="";
	}
	
	 
	</script>
	<style type="text/css">
		table label {color:red; font-size:12px;margin-left:5px } 
	</style>
	  <link rel="stylesheet" href="css/style2.css" type="text/css"></link>
	
  </head>
  
  <body>
      <form  name="form1" action="AdminServlet?flag=update_pswd" onkeydown="if(event.keyCode==13){return false;}"   method="post" >
  <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
	 	<tr>
	 		<td class="title2" > <img src="images/tb.gif" height="14" width="14" class="ico">修改口令</td>
	 	</tr>
 </table>
  <div style="margin-top: 8px"></div>
  <table  border="0" cellpadding="0" cellspacing="1" width="100%" class="table2">
  <tbody>
  <tr><td align="right" width="100">原密码：</td><td><input type="text" style="margin-left: 15px" id="passwd" name="passwd" /><label id="lbl_pswd_now">*</label></td></tr>
  <tr><td align="right">新密码：</td><td><input type="text" style="margin-left: 15px" id="passwd_new" name="passwd_new"  /><label id="lbl_pswd">*</label></td></tr>
 <tr><td align="right">重复密码：</td><td><input type="text" style="margin-left: 15px" id="passwdNewRep" name="passwdNewRep"  /><label id="lbl_pswd_rep">*</label></td></tr>
  <tr><td colspan="2"><input type="submit" style="margin-left: 50px"  class="bt" value="修改" onclick="return validateInput()" /> <input type="button" value="重置" class="bt" onclick="clearInfo();"/><label  id="lblMsg"></label></td></tr>
  </tbody>
  </table>
  </form>
  </body>
  <%
     if(request.getAttribute("Msg")!=null)
     {
    request.setAttribute("Msg",request.getAttribute("Msg").toString());
    }else
    {
     request.setAttribute("Msg","");
     }
   %>
   <script type="text/javascript">
  var Msg='<%=request.getAttribute("Msg")%>';
  if(Msg!="")
  {
    alert(Msg);
  }
  </script>
</html>
