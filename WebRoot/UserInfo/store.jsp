<%@ page language="java" import="java.util.*,com.momei.domain.*,com.momei.service.*,com.momei.dao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>我的收藏</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script src="js/jquery-1.11.3.js"></script>
	 <link rel="stylesheet" type="text/css" href="css/store.css"/>
	 
	 <script type="text/javascript">
	 function show_buy(goodsId,goodsName,price,count)
	 {
	   $("#goodsId").val(goodsId);
	   $("#goods_name").html(goodsName);
	   $("#goods_price").html(price);
	   $("#count_msg").html(count);
	   $("#show_buy").show();
	 }
	 
	 function add()
	 { 
	  var x=parseInt($("#count_msg").text());
	  var count=parseInt(document.getElementById("count").value);
	  
	  if(count<x)
	  {
	  $("#count").val(count+1);
	  }
	}
	
	function red()
	{
	   var count=parseInt(document.getElementById("count").value);
	   if(count>1)
	   {
	  $("#count").val(count-1);
	  }
	}
	
	function judge()
	{  
	    var x=parseInt($("#count_msg").text());
	    var count=parseInt(document.getElementById("count").value);
	    if(count>x)
	    {
	        $("#count").val("1");//数量非法输入
	    }
	}
	
	function exit()
	{
	     $("#show_buy").hide();
	     $("#count").val("1");
	}
	
	function add_car()
	{
	      var goodsId= $("#goodsId").val();
	      var goodsName=$("#goods_name").text();
	      var price=$("#goods_price").text();
	      var count=$("#count").val();
         var count_kc=$("#count_msg").text();
         
	     $.ajax({
            url:"ShoppingCarServlet",
            type:"post",
            data:{flag:"car_add_store",goodsId:goodsId,goodsName:goodsName,price:price,count:count,count_kc:count_kc},
             success:function(data){
                 alert(data);
                 exit();
			}
         });
	}
	 </script>
	 
	 <style type="text/css">
	 #show_buy
	 {
	   display:none;
	    top:200px;
       left:350px;
        width:250px;
	    height:150px;
       padding-left:15px;
        padding-top:10px;
	    position:absolute;
	    border-radius:6px;
       background-color:#EAE5DB;
       border:1px solid #AA9B86;
       box-shadow:0 0 10px #A09398; 
	 }
	 </style>
	
  </head>
  
    <%
     UserInfo user=(UserInfo)session.getAttribute("user");
     //查出用户所有收藏的商品
     StoreService ss=StoreService.getInstance();
     List<Store> list=ss.getAllStore(user.getUserId());
   %>
  <body>
  <div class="my_box">
  <h3>我的收藏</h3>
  <br/>
 <hr/>
 <div class="store_box">
  <%
      for(int i=0;i<list.size();i++)
      {
         Store s=list.get(i);
     %>
    <table class="store_item">
    <tr>
    <td rowspan=3><img style="width:96px;height:96px" src="GoodsPic/<%=s.getPic() %>"/></td>
    <td align=left><h4><%=s.getGoodsName() %></h4></td>
    </tr>
    <tr>
    <td align=left>￥<font style="font-size:16px;color:red;"><%=s.getPrice() %></font>
    </td>
    </tr>
    <tr>
    <td><a href="javascript:show_buy(<%=s.getGoodsId() %>,'<%=s.getGoodsName() %>',<%=s.getPrice() %>,<%=s.getCount() %>);">购买</a>&nbsp;&nbsp;<a href="StoreServlet?flag=delete&storeId=<%=s.getStoreId() %>" onclick="return confirm('确定删除吗?')">删除</a></td>
    </tr>
    </table>
     <%
     }
      %>
 </div>
 </div>
 
 <div id="show_buy">
 <input type="hidden" id="goodsId"/>
 <table>
 <tr height=35px>
 <td width=100px;><span style="font-size:16px;font-weight:bold;" id="goods_name"></span></td>
 <td>￥<span style="color:red;font-weight:bold;" id="goods_price"></span></td>
 </tr>
 <tr height=45px>
 <td colspan=2>
<button id="add" onclick="red();" style="width:30px;height:30px;">-</button>
<input type="text" onblur="judge();" id="count" name="count" value=1 style="width:40px;height:30px;text-align:center;"/>
<button id="red" onclick="add();" style="width:30px;height:30px;">+</button>
(库存量<span id="count_msg"></span>件)
 </td>
  <td>
 </td>
 </tr>
 <tr>
 <td><button onclick="add_car();" style="height:30px;">加入购物车</button></td>
 <td><button onclick="exit();" style="height:30px;">取消</button></td>
 </tr>
 </table>
 </div>
  </body>
</html>
