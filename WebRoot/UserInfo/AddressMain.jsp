<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>地址管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
   <script type="text/javascript" src="js/jquery.validate.js"></script>
   <script type="text/javascript" src="js/jquery.metadata.js"></script>
    <script type="text/javascript" src="js/messages_zh.js"></script>
    <link rel="stylesheet" type="text/css" href="css/address.css"/>
  
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=9de162860c1b7848d95b89021f82c99f&plugin=AMap.Geocoder"></script> 
    
    <script type="text/javascript">
     $(document).ready(function(){
    
			jQuery.validator.addMethod("phn",function(value,element){
			 return this.optional(element) || /^1[34578]\d{9}$/.test(value);
			},$.validator.format("请输入有效的手机号码")
			);
			
		 
			$("#frm").validate();
			$("#frm2").validate();
		
			check_infor();
			check_infor2();

});

   function check_infor(){
	
    $("#phoneNum").attr("class","{phn:true}");
	}
	function check_infor2(){
	
    $("#phoneNum_u").attr("class","{phn:true}");
	}
	
	 function check(){
	  var addressName=$("#addressName").val();
	  var phoneNum=$("#phoneNum").val();
	  if(addressName==""||phoneNum=="")
	  {
      alert("请完善信息!");
     return false;
       }
	 }
	 
	  function check2(){
	  var addressName=$("#addressName_u").val();
	  var phoneNum=$("#phoneNum_u").val();
	  if(addressName==""||phoneNum=="")
	  {
      alert("请完善信息!");
     return false;
       }
	 }
	 
	 function exit()
	 {
	     $("#up_ad").hide();
	     $("#addressId").val("");
	     $("#addressName_u").val("");
	     $("#phoneNum_u").val("");
	 }
	 
	 function up_address(addressId,addressName,phoneNum)
	 {
	     $("#addressId").val(addressId);
	     $("#addressName_u").val(addressName);
	     $("#phoneNum_u").val(phoneNum);
	     $("#up_ad").show();
	 }
    </script>
    
    <style type="text/css">
    #up_ad
    {
       display:none;
       position: absolute;
       top:200px;
       left:250px;
       padding-left:20px;
       width:500px;
       hieght:220px;
        border-radius:6px;
       background-color:#EAE5DB;
       border:1px solid #AA9B86;
       box-shadow:0 0 10px #A09398; 
    }
     #up_ad span{
       float:right;
     }
     
    </style>
    
  </head>
  <%
    UserInfo user=(UserInfo)session.getAttribute("user");
  List<Address> list=(List)request.getAttribute("res");
   %>
  <body>
  <div class="my_box">
  <h3>地址管理</h3>
  <br/>
 <hr/><br/>
     <form id="frm" name="frm" action="AddressServlet?flag=add_address&userId=<%=user.getUserId() %>" onkeydown="if(event.keyCode==13){return false;}" onsubmit="return check();" method="post">
     <table cellspacing=10>
     <tr valign=middle>
     <td>地址</td>
     <script src="js/main.js"></script>
      <td><input type="text" placeholder="请输入地址" onblur="address_request();" id="addressName" name="addressName"/></td>
     </tr>
     <tr valign=middle>
      <td>联系电话</td>
       <td><input type="text" placeholder="请输入联系电话" id="phoneNum" name="phoneNum" ><label></label></td>
     </tr>
     <tr>
      <td colspan=2 align=left> <input id="sb" type="submit" value="添加"/></td>
     </tr>
     </table>
    </form> 
    
    <div class="address_box">
     <%
      for(int i=0;i<list.size();i++)
      {
         Address ad=list.get(i);
     %>
    <table class="address_item">
    <tr>
    <td>
    <%=ad.getAddressName() %>
    </td>
    </tr>
    <tr>
    <td>
     <%=ad.getPhoneNum() %>
    </td>
    </tr>
    <tr>
    <td>
    <a href="javascript:up_address(<%=ad.getAddressId() %>,'<%=ad.getAddressName() %>',<%=ad.getPhoneNum() %>);">修改</a>
    <a href="AddressServlet?flag=del_address&addressId=<%=ad.getAddressId() %>&userId=<%=user.getUserId() %>"  onclick="return confirm('确定删除吗?')">删除</a>
      </td>
    </tr>
    </table>
     <%
     }
      %>
    </div>
      </div>
      <div id="up_ad">
      <span><a href="javascript:exit();"><img src="images/close.png" style="width:12px;height:12px;border:none;"/></a></span>
      <form id="frm2" name="frm2" action="AddressServlet?flag=up_address" onkeydown="if(event.keyCode==13){return false;}" onsubmit="return check2();" method="post">
      <input type="hidden" id="addressId" name="addressId"/>
      <input type="hidden" id="userId" name="userId" value=<%=user.getUserId() %>>
      <table style="margin-top:15px;" cellspacing=5>
      <tr>
      <td>地址</td>
      <td><input type="text" onblur="address_request2();"  id="addressName_u" name="addressName"/></td>
      </tr>
       <tr>
       <td>联系电话</td>
      <td><input type="text" id="phoneNum_u" name="phoneNum"/><label></label></td>
      </tr>
       <tr>
       <td colspan=2 align=left><input id="sb" type="submit" value="修改"/></td>
      </tr>
      </table>
      </form>
      </div>
  </body>
</html>