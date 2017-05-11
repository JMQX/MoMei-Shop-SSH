<%@ page language="java" import="java.util.*,com.momei.domain.*,com.momei.dao.*,com.momei.service.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>墨梅订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script src="js/jquery-1.11.3.js"></script>
	<link rel="stylesheet" type="text/css" href="css/book.css"/>
	
	<script type="text/javascript">
	function hdfk()
	{
	   $("#hdfk").css("background-color","#E3D6D6");
	   $("#send_way_hdfk").val("货到付款");
	   
	}
	
	$(function(){
	
	    $(".text input").focus(function(){
	        $(".text").animate({width:'500px'},"fast");
	    })
	})
	
	$(function(){
	
	    $(".text input").blur(function(){
	        $(".text").animate({width:'300px'},"fast");
	    })
	})
	
	function check()
	{
	    var address=$("#address").val();
	    var send_way_hdfk=$("#send_way_hdfk").val();
	    if(address=="")
	    {
	       alert("亲,你还没给定送达地址喔!");
	        return false;
	    }
	    else if(send_way_hdfk=="")
	    {
	       alert("亲,你还没有选择支付方式喔!");
	       return false;
	    }else
	    {
	       document.f1.submit();
	    }
	}
	
	</script>

  </head>
  
  <%
      String address_now="";
      if(session.getAttribute("address_now")!=null)
      {
        address_now=session.getAttribute("address_now").toString();
      }
     UserInfo user=(UserInfo)session.getAttribute("user");
     ArrayList<ShoppingCar_seq> car_list=( ArrayList<ShoppingCar_seq>)request.getAttribute("car_list");
     AddressService as=AddressService.getInstance();
      List<Address> list=as.getAllAddressByUser(user.getUserId());
     int count_total=0;
     double pay_total=0;
   %>
   
  <body>
  <form id="f1" name="f1" action="BookServlet?flag=add_data&userId=<%=user.getUserId() %>" onkeydown="if(event.keyCode==13){return false;}" method="post">
  <span id="title_msg">墨梅订单</span>
  <div class="book_content"></div>
<div class="my_box">
<div class="goods_box">
<table cellspacing="0px" cellpadding="0px" id="goods_table">
<tr style="font-weight:bold;height:35px;" bgcolor="#F4F4F4">
<td>美食篮子</td>
<td>单价</td>
<td>数量</td>
<td>总计</td>
</tr>
<%
   for(int i=0;i<car_list.size();i++)
   {
       ShoppingCar_seq se=car_list.get(i);
       count_total+=se.getCount();
       pay_total+=se.getCount()*se.getPrice();
 %>
 <tr height=30px>
 <td><%=se.getGoodsName() %></td>
  <td>￥<%=se.getPrice() %></td>
   <td><%=se.getCount() %></td>
    <td><%=se.getPrice()*se.getCount() %></td>
 </tr>
 <%
 }
  %>
</table>
<table cellspacing="0px" cellpadding="0px" id="goods_total">
<tr>
<td align=center>
<div class="text">
<input type="text" placeholder="给商家留言" name="message" id="message" />
</div>
</td>
</tr>
<tr bgcolor="#F4F4F4">
<td align=right>
共<%=count_total %>件干果&nbsp;&nbsp;应付金额<span style="font-weight:bold;font-size:18px;color:#EF983A;">￥<%=pay_total %></span>
</td>
</tr>
</table>
</div>

<div class="msg_box">
<table>
<tr width=520px height=50px>
<td><img src="images/contact.jpg" />
姓名/联系电话：<%=user.getName() %>&nbsp;&nbsp;
<select name="phoneNum" id="phoneNum">
<option value=<%=user.getPhoneNum() %>><%=user.getPhoneNum() %></option>
<%
   for(int i=0;i<list.size();i++)
   {
     Address adr=list.get(i);
     if(!adr.getPhoneNum().equals(user.getPhoneNum()))
     {
 %>
 <option value=<%=adr.getPhoneNum() %>><%=adr.getPhoneNum() %></option>
 <%
 }
 }
  %>
</select>
</td>
</tr>
<tr width=520px height=50px>
<td>
<img src="images/address.jpg" />
送达地址：
<select name="address" id="address">
<option value=<%=address_now %>><%=address_now %></option>
<%
   for(int i=0;i<list.size();i++)
   {
     Address adr=list.get(i);
      if(!adr.getAddressName().equals(address_now))
      {
 %>
 <option value=<%=adr.getAddressName() %>><%=adr.getAddressName() %></option>
 <%
 }
 }
  %>
</select>
</td>
</tr>
<tr width=520px height=50px>
<td>
<img src="images/time.jpg" />
送达时间：
<select name="send_time" id="send_time">
<option value="立即送出">立即送出</option>
<option value="1小时内送达">1小时内送达</option>
<option value="3小时内送达">3小时内送达</option>
<option value="今日内送达">今日内送达</option>
</select>
</td>
</tr>
<tr width=520px height=50px>
<td>
<img src="images/pay.jpg" />
支付方式：<input type="hidden" name="send_way_hdfk" id="send_way_hdfk" />
<span id="hdfk" style="width:80px;height:25px;border:2px solid #868383"><a style="color:#625A5A;font-size:14px;text-decoration:none;" href="javascript:hdfk();">货到付款</a></span>
<span style="width:80px;height:25px;border:2px solid #EAA039"><a style="color:#625A5A;font-size:14px;text-decoration:none;" href="">在线支付</a></span>
</td>
</tr>
<tr width=520px height=50px>
<td><button id="book_bt" onclick="return check()">确认下单</button>
<a href="UserInfo/Main.jsp" style="color:#625A5A;font-size:15px;text-decoration:none;">返回商城</a>
</td>
</tr>
</table>
</div>
</div>
</form>
  </body>
</html>
