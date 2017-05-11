<%@ page language="java" import="java.util.*,com.momei.domain.*,com.momei.service.*,com.momei.dao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
	<script src="js/jquery-1.11.3.js"></script>
	<script src="js/placeholder.js"></script>
	<link rel="stylesheet" type="text/css" href="css/content.css">
	
	<script type="text/javascript">
	function search_goods()
	{
	   var goodsName=document.getElementById("goodsName").value;
	   document.form1.action="GoodsServlet?flag=find_blur&goodsName="+goodsName+""
	   document.form1.submit();
	   
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
	     $("#goods_msg").hide();
	     $("#count").val("1");
	 }
	 
	 function show_goodsMsg(goodsId,goodsName,price,pic,unit,count,content)
	 {
	     var src="GoodsPic/"+pic
	     var unit_msg=unit+"(库存量";
	     $("#pic_msg").attr("src",src);
	     $("#goods_msg h3").html(goodsName);
	      $("#price_msg").html(price);
	      $("#content_msg").html(content);
	      $("#unit_msg").html(unit_msg);
	      $("#count_msg").html(count);
	      $("#goodsId").val(goodsId);
	     $("#goods_msg").show();
	 }
	 
	 $(function(){

       $("#shop").click(function(){
         var goodsId=$("#goodsId").val();
         var goodsName=$("#goods_msg h3").text();
         var price=$("#price_msg").text();
         var count=$("#count").val();
         var count_kc=$("#count_msg").text();
            
         $.ajax({
            url:"ShoppingCarServlet",
            type:"post",
            dataType:"json",
            data:{flag:"car_add",goodsId:goodsId,goodsName:goodsName,price:price,count:count,count_kc:count_kc},
             success:function(carList){
                  $("#carList").empty();
                  var count_num=0;//总件数
                  var total_RMB=0;//应付金额
                 $.each(carList,function(key,obj){
                   count_num+=obj.count;
                   total_RMB+=(obj.count)*(obj.price);
                    var str="<tr><td>"+obj.goodsName+"</td><td><button onclick='car_red("+obj.goodsId+");' id='"+obj.goodsId+"red'>-</button>"+obj.count+"<button onclick='car_add("+obj.goodsId+");' id='"+obj.goodsId+"add'>+</button></td><td id='"+obj.goodsId+"price'>￥"+obj.price+"</td></tr>";
                    $("#carList").append(str);
                     $("#"+obj.goodsId+"price").css({"color":"#F27C3A","font-size":"13px","font-weight":"bold"});
                    $("#"+obj.goodsId+"red").css({"width":"20px","height":"20px"});
                   $("#"+obj.goodsId+"add").css({"width":"20px","height":"20px"});
                   
                   $("#msg").css("background-color","#51D862");
                  $("#msg").html("<a href='BookServlet?flag=add' target='_parent'>去结算</a>");
                   
                   
                 }) 
                 
                 $("#total").css("color","#FFFFFF");
                $("#total").html("X"+count_num+"￥"+total_RMB);
			}
         });
       });
    });
	
	function setNull()
	{
	    $.ajax({
            url:"ShoppingCarServlet",
            type:"post",
            data:{flag:"car_setNull"},
             success:function(carList){
                  $("#carList").empty();
                   $("#msg").html("购物车是空的");
                    $("#msg").css({"background-color":"#E4E4E4","color":"#424242"});
                     $("#total").html("");
			}
         });
	}  
	
	function car_add(goodsId)
	{
	   $.ajax({
            url:"ShoppingCarServlet",
            type:"post",
            dataType:"json",
            data:{flag:"car_up_add",goodsId:goodsId},
             success:function(carList){
                  $("#carList").empty();
                   var count_num=0;//总件数
                  var total_RMB=0;//应付金额
                   $.each(carList,function(key,obj){
                    count_num+=obj.count;
                   total_RMB+=(obj.count)*(obj.price);
                    var str="<tr><td>"+obj.goodsName+"</td><td><button onclick='car_red("+obj.goodsId+");' id='"+obj.goodsId+"red'>-</button>"+obj.count+"<button onclick='car_add("+obj.goodsId+");' id='"+obj.goodsId+"add'>+</button></td><td id='"+obj.goodsId+"price'>￥"+obj.price+"</td></tr>";
                    $("#carList").append(str);
                     $("#"+obj.goodsId+"price").css({"color":"#F27C3A","font-size":"13px","font-weight":"bold"});
                    $("#"+obj.goodsId+"red").css({"width":"20px","height":"20px"});
                   $("#"+obj.goodsId+"add").css({"width":"20px","height":"20px"});
                   
                    $("#msg").css("background-color","#51D862");
                  $("#msg").html("<a href='BookServlet?flag=add' target='_parent'>去结算</a>");
                 }) 
                  
                    $("#total").css("color","#FFFFFF");
                $("#total").html("X"+count_num+"￥"+total_RMB);
			}
         });
	}
	function car_red(goodsId)
	{
	   $.ajax({
            url:"ShoppingCarServlet",
            type:"post",
            dataType:"json",
            data:{flag:"car_up_red",goodsId:goodsId},
             success:function(carList){
                  $("#carList").empty();
                    var count_num=0;//总件数
                  var total_RMB=0;//应付金额
                   $.each(carList,function(key,obj){
                      count_num+=obj.count;
                   total_RMB+=(obj.count)*(obj.price);
                    var str="<tr><td>"+obj.goodsName+"</td><td><button onclick='car_red("+obj.goodsId+");' id='"+obj.goodsId+"red'>-</button>"+obj.count+"<button onclick='car_add("+obj.goodsId+");' id='"+obj.goodsId+"add'>+</button></td><td id='"+obj.goodsId+"price'>￥"+obj.price+"</td></tr>";
                    $("#carList").append(str);
                     $("#"+obj.goodsId+"price").css({"color":"#F27C3A","font-size":"13px","font-weight":"bold"});
                    $("#"+obj.goodsId+"red").css({"width":"20px","height":"20px"});
                   $("#"+obj.goodsId+"add").css({"width":"20px","height":"20px"});
                   
                    $("#msg").css("background-color","#51D862");
                  $("#msg").html("<a href='BookServlet?flag=add' target='_parent'>去结算</a>");
                 }) 
                 
                     $("#total").css("color","#FFFFFF");
                $("#total").html("X"+count_num+"￥"+total_RMB); 
			}
         });
	}
	
	function show_car()
	{
	    $.ajax({
            url:"ShoppingCarServlet",
            type:"post",
             dataType:"json",
            data:{flag:"show_car"},
             success:function(carList){
                  $("#carList").empty();
                 
                  if(carList!=null&&carList.length>0)
                  {
                   var count_num=0;//总件数
                  var total_RMB=0;//应付金额
                   $.each(carList,function(key,obj){
                      count_num+=obj.count;
                   total_RMB+=(obj.count)*(obj.price);
                    var str="<tr><td>"+obj.goodsName+"</td><td><button onclick='car_red("+obj.goodsId+");' id='"+obj.goodsId+"red'>-</button>"+obj.count+"<button onclick='car_add("+obj.goodsId+");' id='"+obj.goodsId+"add'>+</button></td><td id='"+obj.goodsId+"price'>￥"+obj.price+"</td></tr>";
                    $("#carList").append(str);
                     $("#"+obj.goodsId+"price").css({"color":"#F27C3A","font-size":"13px","font-weight":"bold"});
                    $("#"+obj.goodsId+"red").css({"width":"20px","height":"20px"});
                   $("#"+obj.goodsId+"add").css({"width":"20px","height":"20px"});
                   
                    $("#msg").css("background-color","#51D862");
                 $("#msg").html("<a href='BookServlet?flag=add' target='_parent'>去结算</a>");
                 }) 
                 
                     $("#total").css("color","#FFFFFF");
                $("#total").html("X"+count_num+"￥"+total_RMB); 
                  }else
                  {
                     $("#msg").html("购物车是空的");
                    $("#msg").css({"background-color":"#E4E4E4","color":"#424242"});
                     $("#total").html("");
                  }
			}
         });
	}
	
	function store_goods()
	{
	  var goodsId=$("#goodsId").val();
	  
	  $.ajax({
	       url:"StoreServlet",
            type:"post",
            data:{flag:"add",goodsId:goodsId},
             success:function(data){  
                  alert(data);
			}
	     
	  })
	}
	
	</script>
	
	<style type="text/css">
	#goods_msg
	{
	   display:none;
       position: fixed;
       top:235px;
       left:420px;
       padding-left:10px;
       padding-top:10px;
       padding-right:10px;
       padding-bottom:10px;
       width:500px;
       hieght:300px;
       border-radius:6px;
       background-color:#EAE5DB;
       border:1px solid #AA9B86;
       box-shadow:0 0 10px #A09398; 
	}
	
	#pic_msg
	{
	   width:200px;
	   height:200px;
	}
	
	#price_msg
	{
	   color:#FF4400;
	   font-size:18px;
	}
	
	#exit
	{
	  float:right;
	}
	
	#shopping_car
	{
       position: fixed;
       right:0px;
       bottom:0px;
       width:250px;
       hieght:auto;
       background-color:#FFFFFF;
       border:2px solid #0089DB;
       border-right:0px;
       border-left:0px;
       border-bottom:0px;
       cursor: pointer;
       box-shadow:0 0 10px #A09398; 
	}
	
	#carList
	{
	  margin-left:5px;
	  font-size:13px;
	}
	
	#carList td
	{
	   width:100px;
	}
	
	</style>

  </head>
  <%
       GoodsTypeService gts=GoodsTypeService.getInstance();
       List<GoodsType> list2=gts.getGoodsType();
       String address_now="当前未定位";
       if(session.getAttribute("address_now")!=null)
       {
          address_now=session.getAttribute("address_now").toString();
       }
   %>
  <body>
 <div class="banner">
	<div class="position">
		<div class="position_now">当前位置：</div>  
		<div class="current"><%=address_now %></div>
		<div class="change_btn"><a href="index.jsp" target="_parent">[切换位置]</a></div>
	</div>
	<div id="search">
		<input type="text" id="goodsName" name="goodsName" placeholder="搜索商品"></input>
		<a href="javascript:search_goods()" id="search_good"></a>
	</div>
</div>
<div id="container">
	<ul>
		<li><a href="#"><img src="images/pic.png"></a></li>
		<li><a href="#"><img src="images/pic1.png"></a></li>
		<li><a href="#"><img src="images/pic2.png"></a></li>
	</ul>
	<div id="list">
		<ul>
			<li>1</li>
			<li>2</li>
			<li>3</li>
		</ul>
	</div>	
</div>
<%
   if(request.getAttribute("goodsTypeId")==null&request.getAttribute("find")==null)
   {
       GoodService gs=GoodService.getInstance();
       List<Goods> list=gs.getAllGoods();
 %>
<div class="content_class">
	<div class="good_class">
		<span>商品分类:</span>
		<ul>
		   <%
		       for(int i=0;i<list2.size();i++)
		       {
		          GoodsType gt=list2.get(i);
		    %>
			<li><a  href="GoodsServlet?flag=show_type&goodsTypeId=<%=gt.getGoodsTypeId() %>" class="good_class_no_active"><%=gt.getGoodsTypeName() %></a></li>
			<%
			}
			 %>
		</ul>
	</div>
	<div class="sort">
		<a  href="UserInfo/GoodsMain.jsp"><span>默认排序</span></a>
		<ul>
			<li>销量高</li>
		</ul>
	</div>
</div>
<div class="goods_box">
 <%
    for(int i=0;i<list.size();i++)
    {
       Goods g=list.get(i);
  %>
	<a href="javascript:show_goodsMsg(<%=g.getGoodsId() %>,'<%=g.getGoodsName() %>',<%=g.getPrice() %>,'<%=g.getPic() %>','<%=g.getUnit() %>',<%=g.getCount() %>,'<%=g.getContent() %>');" class="goods_item">
		<div class="goods_item_left">
				<img src="GoodsPic/<%=g.getPic() %>">
		</div>
		<div class="goods_item_right">
			<div class="goods_item_right_main">
				<span><%=g.getGoodsName() %></span>
				<div class="score">
					<ul>
						<li></li>
					</ul>
					<div class="sales">￥<font color=red style="font-size:16px"><%=g.getPrice() %></font></div>
				</div>

			</div>
		</div>
	</a>
	<%
	}
	 %>
	
	<div style="clear:both;"></div>
</div>
<%
}else if(request.getAttribute("goodsTypeId")!=null&request.getAttribute("find")==null)
{ 
   int goodsTypeId=Integer.parseInt(request.getAttribute("goodsTypeId").toString());
   List<Goods> list=(List)request.getAttribute("result");
 %>
 
 <div class="content_class">
	<div class="good_class">
		<span>商品分类:</span>
		<ul>
		   <%
		       for(int i=0;i<list2.size();i++)
		       {
		          GoodsType gt=list2.get(i);
		          if(gt.getGoodsTypeId()==goodsTypeId)
		          {
		    %>
			<li><a  href="GoodsServlet?flag=show_type&goodsTypeId=<%=gt.getGoodsTypeId() %>" class="good_class_active"><%=gt.getGoodsTypeName() %></a></li>
			<%
			}else
			{
			 %>
			<li><a  href="GoodsServlet?flag=show_type&goodsTypeId=<%=gt.getGoodsTypeId() %>" class="good_class_no_active"><%=gt.getGoodsTypeName() %></a></li>
			 <%
			 }
			 }
			  %>
			 
		</ul>
	</div>
	<div class="sort">
		<a href="UserInfo/GoodsMain.jsp"><span>默认排序</span></a>
		<ul>
			<li>销量高</li>
		</ul>
	</div>
</div>
<div class="goods_box">
 <%
    for(int i=0;i<list.size();i++)
    {
       Goods g=list.get(i);
  %>
	<a href="javascript:show_goodsMsg(<%=g.getGoodsId() %>,'<%=g.getGoodsName() %>',<%=g.getPrice() %>,'<%=g.getPic() %>','<%=g.getUnit() %>',<%=g.getCount() %>,'<%=g.getContent() %>');" class="goods_item">
		<div class="goods_item_left">
				<img src="GoodsPic/<%=g.getPic() %>">
		</div>
		<div class="goods_item_right">
			<div class="goods_item_right_main">
				<span><%=g.getGoodsName() %></span>
				<div class="score">
					<ul>
						<li></li>
					</ul>
					<div class="sales">￥<font color=red style="font-size:16px"><%=g.getPrice() %></font></div>
				</div>

			</div>
		</div>
	</a>
	<%
	}
	}else if(request.getAttribute("goodsTypeId")==null&request.getAttribute("find")!=null)
	{
	    List<Goods> list=(List)request.getAttribute("result");
	 %>
	  
	  <div class="content_class">
	<div class="good_class">
		<span>商品分类:</span>
		<ul>
		   <%
		       for(int i=0;i<list2.size();i++)
		       {
		          GoodsType gt=list2.get(i);
		    %>
			<li><a  href="GoodsServlet?flag=show_type&goodsTypeId=<%=gt.getGoodsTypeId() %>" class="good_class_no_active"><%=gt.getGoodsTypeName() %></a></li>
			<%
			}
			 %>
		</ul>
	</div>
	<div class="sort">
		<a href="UserInfo/GoodsMain.jsp"><span>默认排序</span></a>
		<ul>
			<li>销量高</li>
		</ul>
	</div>
</div>
<div class="goods_box">
 <%
    for(int i=0;i<list.size();i++)
    {
       Goods g=list.get(i);
  %>
	<a href="javascript:show_goodsMsg(<%=g.getGoodsId() %>,'<%=g.getGoodsName() %>',<%=g.getPrice() %>,'<%=g.getPic() %>','<%=g.getUnit() %>',<%=g.getCount() %>,'<%=g.getContent() %>');" class="goods_item">
		<div class="goods_item_left">
				<img src="GoodsPic/<%=g.getPic() %>">
		</div>
		<div class="goods_item_right">
			<div class="goods_item_right_main">
				<span><%=g.getGoodsName() %></span>
				<div class="score">
					<ul>
						<li></li>
					</ul>
					<div class="sales">￥<font color=red style="font-size:16px"><%=g.getPrice() %></font></div>
				</div>

			</div>
		</div>
	</a>
	<%
	}
	}
	 %>
	<div style="clear:both;"></div>
</div>
  

<div id="footer">
		<div id="footer_nav">
			<ul>
				<li><a href="javascript:void(0);">手机版下载</a></li>
				<li><a href="javascript:void(0);">关注公众号</a></li>
				<li><a href="javascript:void(0);">我要开店</a></li>
				<li><a href="javascript:void(0);">加入我们</a></li>
			</ul>
		</div>
		<div class="message"></div>
		<div class="qrcode" alt="二维码"></div>
</div>
<form action="" name="form1" id="form1" method="post"></form>

<div id="goods_msg">
<input type="hidden" id="goodsId"/>
<span id="exit"><a href="javascript:exit();"><img src="images/close.png" style="width:12px;height:12px;"/></a></span>
<table>
<tr>
<td rowspan=6 width=215px;><img id="pic_msg" /></td>
</tr>
<tr>
<td><h3></h3></td>
</tr>
<tr>
<td>价格&nbsp;&nbsp;<span style="color:#FF4400;font-size:18px;">￥</span><span id="price_msg"></span></td>
</tr>
<tr>
<td id="content_msg"></td>
</tr>
<tr>
<td>数量&nbsp;&nbsp;
<table><tr>
<td><button id="red" onclick="red();" style="width:30px;height:30px;">-</button></td>
<td><input type="text" onblur="judge();" id="count" name="count" value=1 style="width:40px;height:30px;text-align:center;"/></td>
<td><button id="add" onclick="add();" style="width:30px;height:30px;">+</button></td>
<td><span id="unit_msg"></span><span id="count_msg"></span>件)</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>
<table>
<tr>
<td>
<button id="shop"  style="width:100px;height:30px;border:0px;cursor: pointer;"><img src="images/shopping.png" style="width:100px;height:30px;"/></button>
</td>
<td>
<button onclick="store_goods();" id="store" style="width:60px;height:30px;cursor: pointer;">收藏</button>
</td>
</tr>
</table>
</td>
</tr>
</table>
</div>

<div onclick="show_car();" id="shopping_car">
<table style="margin-left:5px;margin-top:5px;border-bottom:1px solid #E4E4E4;" bgcolor="#FAFAFA">
<tr>
<td width=210px height=35px><font style="color:#424242;font-size:13px;font-weight:bold;">购物车</font><a style="color:#0089DB;font-size:13px;" href="javascript:setNull();">[清空]</a>
</td>
<td><img style="width:25px;height:25px;" src="images/car_add.png"/></td>
</tr>
</table>

<table id="carList">
</table>

<table cellspacing="0px" cellpadding="0px">
<tr>
<td style="width:150px;height:35px;" bgcolor="#2C2C2C">&nbsp;&nbsp;<img style="width:25px;height:25px;" src="images/car.jpg"/><span id="total"></span></td>
<td id="msg" style="width:100px;height:35px;text-align:center;font-size:13px;font-weight:bold;color:#424242;"  bgcolor="#E4E4E4">点击查看</td>
</tr>
</table>
</div>
  </body>
<script src="js/content.js"></script>
</html>