<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人资料</title>
    
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
       
       $("#phoneNum").blur(function(){
         var phoneNum=$("#phoneNum").val();
         $.ajax({
            url:"UserServlet",
            type:"post",
            data:{flag:"checkPhone",phoneNum:phoneNum},
             success:function(data){
                 if(data!="")
                 {
			     alert(data);
			      $("#phoneNum").val("");
			     }
			}
         });
       });
    });
    
	 $(function(){
	    $("#getYz").click(function(){
         var Email=$("#Email").val();
         $.ajax({
            url:"EmailServlet",
            type:"post",
            data:{Email:Email},
             success:function(data){
               if(data!="")
			     alert(data);
			}
         });
	    });
	});
	
	$(function(){
       
       $("#YZM").blur(function(){
         var YZM=$("#YZM").val();
         $.ajax({
            url:"EmailJudgeServlet",
            type:"post",
            data:{YZM:YZM},
             success:function(data){
                 if(data!=""&&YZM!="")
                 {
			     alert(data);
			      $("#YZM").val("");
			     }
			}
         });
       });
    });
    
     $(function(){
       
       $("#EmailNew").blur(function(){
         var Email=$("#EmailNew").val();
         $.ajax({
            url:"UserServlet",
            type:"post",
            data:{flag:"checkEmail",Email:Email},
             success:function(data){
                 if(data!="")
                 {
			     alert(data);
			     $("#EmailNew").val("");
			     }
			}
         });
       });
    });
    
	$(function(){
	    $("#getYz2").click(function(){
	    if($("#EmailNew").val()=="")
	    {
	       alert("请输入邮箱地址!");
	    }else
	    {
         var Email=$("#EmailNew").val();
         $.ajax({
            url:"EmailServlet",
            type:"post",
            data:{Email:Email},
             success:function(data){
               if(data!="")
			     alert(data);
			}
         });
         }
	    });
	});
	
	$(function(){
       
       $("#YZM_New").blur(function(){
         var YZM=$("#YZM_New").val();
         $.ajax({
            url:"EmailJudgeServlet",
            type:"post",
            data:{YZM:YZM},
             success:function(data){
                 if(data!=""&&YZM!="")
                 {
			     alert(data);
			     $("#YZM_New").val("");
			     }
			}
         });
       });
    });
    
	  $(document).ready(function(){
    
			jQuery.validator.addMethod("phn",function(value,element){
			 return this.optional(element) || /^1[34578]\d{9}$/.test(value);
			},$.validator.format("请输入有效的手机号码")
			);
			
		 
			$("#frm").validate();
		
			check_infor();

        });
        
         $(document).ready(function(){
    
			jQuery.validator.addMethod("EmailNew",function(value,element){
			 return this.optional(element) || /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(value);
			},$.validator.format("请输入有效的电子邮箱")
			);
			
		 
			$("#frm2").validate();
		
			check_infor2();

    });

   function check_infor(){
	
    $("#phoneNum").attr("class","{phn:true}");
	}
	
	 function check_infor2(){
	
     $("#EmailNew").attr("class","{EmailNew:true}");
	}
	
	 function check(){
	  var phoneNum=$("#phoneNum").val();
	  if(phoneNum=="")
	  {
      alert("请输入手机号码!");
     return false;
       }
      
	 }
	  function check2(){
	  var YZM=$("#YZM").val();
	  var EmailNew=$("#EmailNew").val();
	  var YZM_New=$("#YZM_New").val();
	  if(YZM==""||EmailNew==""||YZM_New=="")
	  {
      alert("请完善信息!");
     return false;
       }
       
	 }
	 
	 function up_pn()
	 {
	     
	     $("#up_pn").toggle(50);
	 }
	 
	 function up_em()
	 {
	     $("#up_em").toggle(50);
	 }
	
	 </script>
	 
	 <style type="text/css">
	 #up_pn
	 {
	   display:none;
       position: absolute;
       padding-top:10px;
       padding-left:10px;
       width:400px;
       height:100px;
       border-radius:6px;
       background-color:#EAE5DB;
       border:1px solid #AA9B86;
       box-shadow:0 0 10px #A09398; 
	 }
	  input{
	   width:150px;
	   height:30px;
	 }
	 #sb{
	   width:60px;
	   height:30px;
	   background-color:#0CC;
	   border-radius:6px;
	   color:white;
	   font-weight:bold;
	 }
	 
	 #up_em
	 { 
	   display:none;
       position: absolute;
        padding-top:10px;
       padding-left:10px;
       width:400px;
       height:200px;
       border-radius:6px;
       background-color:#EAE5DB;
       border:1px solid #AA9B86;
       box-shadow:0 0 10px #A09398;
	 }
	 
	  label{
       color:red;
       font-family:"楷体";
     }
     
     #getYz
     {
        width:80px;
        height:30px;
     }
     #getYz2
     {
        width:80px;
        height:30px;
     }
	 </style>
	
	
  </head>
    <%
     UserInfo user=(UserInfo)session.getAttribute("user");
   %>
  <body>
  <div class="my_box">
  <h3>个人资料</h3>
  <br/>
 <hr/>
 <table width=380px height=200px>
 <tr colspan=3>
 <td>头像
 <%
   if(user.getPic()!=null)
   {
  %>
 <a href="UserInfo/tx_upload.jsp"><img src="userTx/<%=user.getPic() %>" style="width:100px;height:100px;border:none;"/></a>
 <%
 }else
 {
  %>
   <a href="UserInfo/tx_upload.jsp"><img src="images/default.png" style="width:100px;height:100px;"/></a>
  <%
  }
   %>
 </td>
 </tr>
  <tr valign=middle>
  <td>手机号码</td>
  <td><%=user.getPhoneNum() %></td>
  <td><a href="javascript:up_pn();">[修改]</a><div id="up_pn">
  <form id="frm" name="frm" action="UserServlet?flag=update_pn&userName=<%=user.getUserName() %>" onkeydown="if(event.keyCode==13){return false;}" onsubmit="return check();" method="post">
   <table cellspacing=5>
   <tr valign=middle>
   <td>手机号码</td>
    <td><input type="text" placeholder="请输入新的手机号码" id="phoneNum" name="phoneNum" /><label></label></td>
   </tr>
   <tr>
   <td colspan=2 align="left"><input id="sb" type="submit" value="修改"/></td>
   </tr>
   </table>
   </form>
  </div></td>
  </tr>
   <tr valign=middle>
   <td> 邮箱地址</td>
   <td><%=user.getEmail() %></td>
   <td><a href="javascript:up_em();">[修改]</a><div id="up_em">
    <form id="frm2" name="frm2" action="UserServlet?flag=update_em&userName=<%=user.getUserName() %>"  onkeydown="if(event.keyCode==13){return false;}"  onsubmit="return check2();" method="post">
    <table cellspacing=5>
    <tr valign=middle>
    <td>原邮箱</td>
    <td><input type="text" value=<%=user.getEmail() %> readonly="readonly" id="Email" name="Email"/></td>
    </tr>
     <tr valign=middle>
     <td>验证码</td>
     <td><input type="text" id="YZM" name="YZM"/><input type="button" id="getYz" value="获取"/></td>
     </tr>
      <tr valign=middle>
      <td>新邮箱</td>
      <td><input type="text" placeholder="请输入新的邮箱" id="EmailNew" name="EmailNew"/><label></label></td>
      </tr>
       <tr valign=middle>
       <td>验证码</td>
       <td><input type="text" id="YZM_New" name="YZM_New"/><input type="button" id="getYz2" value="获取"/></td>
       </tr>
        <tr>
        <td colspan2 align=left><input id="sb" type="submit" value="修改"/></td>
        </tr>
    </table>
    </form>
   </div></td>
 </table>
   </div>
  </body>
</html>
