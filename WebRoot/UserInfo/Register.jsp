<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
   <script type="text/javascript" src="js/jquery.validate.js"></script>
   <script type="text/javascript" src="js/jquery.metadata.js"></script>
    <script type="text/javascript" src="js/messages_zh.js"></script>
    <script src="js/placeholder.js"></script>
    <link rel="stylesheet" type="text/css" href="css/Register.css"></style>
    
    <script type="text/javascript">
  
    $(function(){

       $("#userName").blur(function(){
         var userName=$("#userName").val();
         $.ajax({
            url:"UserServlet",
            type:"post",
            data:{flag:"checkName",userName:userName},
             success:function(data){
                 if(data!="")
                 {
			     alert(data);
			     $("#userName").val("");
			     }
			}
         });
       });
    });
    
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
       
       $("#Email").blur(function(){
         var Email=$("#Email").val();
         $.ajax({
            url:"UserServlet",
            type:"post",
            data:{flag:"checkEmail",Email:Email},
             success:function(data){
                  if(data!="")
                 {
			     alert(data);
			     $("#Email").val("");
			     }
			}
         });
       });
    });
    
    
    $(function(){
	    $("#getyz").click(function(){
	    if($("#Email").val()=="")
	    {
	       alert("请输入邮箱地址!");
	    }else
	    {
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
         }
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
    
   function check(){
   var userName=$("#userName").val();
   var Name=$("#Name").val();
   var phoneNum=$("#phoneNum").val();
    var Email=$("#Email").val();
    var YZM=$("#YZM").val();
   var passwd=$("#passwd").val();
  
  if(userName==""||Name==""||phoneNum==""||Email==""||YZM==""||passwd==""){
   alert("请完善信息!");
  return false;
  }
  
  }

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
  
			jQuery.validator.addMethod("un",function(value,element){
			 return this.optional(element) || /^[a-zA-Z]*$/.test(value);
			},$.validator.format("请输入有效的用户名")
			);
			
			jQuery.validator.addMethod("NameVar",function(value,element){
			 return this.optional(element) || /^[\u4E00-\u9FA5]{2,5}$/.test(value);
			},$.validator.format("请输入有效的姓名")
			);
			
			
			jQuery.validator.addMethod("phn",function(value,element){
			 return this.optional(element) || /^1[34578]\d{9}$/.test(value);
			},$.validator.format("请输入有效的手机号码")
			);
			
			jQuery.validator.addMethod("Email",function(value,element){
			 return this.optional(element) || /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(value);
			},$.validator.format("请输入有效的电子邮箱")
			);
			
			  jQuery.validator.addMethod("numFormatPasswd",function(value,element,param){
			 return this.optional(element) ||  /^\w{6,}$/.test(value);
			},$.validator.format("密码至少为6位")
			);
			
			$("#frm").validate();
		
			check_infor();

});

   function check_infor(){
	
	 $("#userName").attr("class","{byteMaxLength:30,un:true}");
     $("#Name").attr("class","{byteMaxLength:10,NameVar:true}");
   
    $("#phoneNum").attr("class","{phn:true}");
    $("#Email").attr("class","{Email:true}");
	 
	  $("#passwd").attr("class","{byteMaxLength:30,numFormatPasswd:true}");
	
	
	}
    </script>
    

  </head>
  
  <body>
   <h1><Img src="images/mm.gif" width=180px height=110px align=bottom />用户注册</h1>
  <div class="div1">
  <span><font>已经注册过?</font>&nbsp;&nbsp;&nbsp;&nbsp;<br></br><font>请点击</font><a href="UserInfo/login.jsp">直接登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
  <form id="frm" name="frm"  onsubmit="return check();" onkeydown="if(event.keyCode==13){return false;}" action="UserServlet?flag=add" method="post">
  <table cellspacing=8>
  <tr valign=middle>
  <td><font>用户名</font></td>
   <td><input type="text"  placeholder="用户名由字母组成" style="width:250px;height:38px;" id="userName" name="userName"/><label></label> </td>
  </tr>
   <tr valign=middle>
  <td><font>姓名</font></td>
   <td><input type="text"  placeholder="真实姓名由汉字组成" style="width:250px;height:38px;" id="Name" name="Name"/><label></label></td>
  </tr>
  <tr valign=middle>
  <td><font>手机号</font></td>
   <td><input type="text" style="width:250px;height:38px;" id="phoneNum" name="phoneNum"/><label></label></td>
  </tr>
  <tr valign=middle>
  <td>
  <font>电子邮箱</font>
  </td>
  <td>
  <input type="text" style="width:250px;height:38px;" id="Email" name="Email"/>
  <label></label>
  </td>
  </tr>
   <tr valign=middle>
  <td><font>邮箱验证码</font></td>
   <td><input type="text" class="ts" id="YZM"  name="YZM"/><input type="button" id="getyz" value="获取"/><label></label></td>
  </tr>
 <tr valign=middle>
 <td><font>学校</font></td>
 <td><input type="text" class="ts" class="tt" id="School" name="School"/></td>
 </tr>
  <tr valign=middle>
  <td><font>设置密码</font></td>
  <td><input type="password"  placeholder="密码不少于6位" style="width:250px;height:38px;" id="passwd" name="passwd"/><label></label></td>
  </tr>
  </table>
  <input type="submit" class="btn" value="同意协议并注册"/>&nbsp;&nbsp;<a href="">《使用条款和协议》</a>
  </form>
  </div>
  </body>
</html>
