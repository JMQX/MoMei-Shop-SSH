<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>积分商城</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0"> 
	<script src="js/jquery-1.11.3.js"></script>
    <script src="js/content.js"></script>
    <script src="js/shop.js"></script>
	 <script src="js/placeholder.js"></script>   
<link rel="stylesheet" type="text/css" href="css/shop.css">

</head>
 <%
     UserInfo user=(UserInfo)session.getAttribute("user");
     String userName="";
     int score=0;
     if(user!=null)
     {
        userName=user.getUserName();
        score=user.getScore();
     }
     
      String address_now="当前未定位";
       if(session.getAttribute("address_now")!=null)
       {
          address_now=session.getAttribute("address_now").toString();
       }
   %>
<body>
<div class="header">
	<div class="header_line">
		<div class="logo"></div>
		<ul>
			<li><a href="javascript:void(0);">首页</a></li>
			<li><a href="javascript:void(0);">品牌商家</a></li>
			<li><a href="javascript:void(0);">我的订单</a></li>
			<li><a href="javascript:void(0);">加盟合作</a></li>
			<li><a href="UserInfo/score_market.jsp">积分商城</a></li>
		</ul>
	</div>
	<div id="user">
	 <%
	    if(user==null)
	    {
	  %>
	    <div class="user_pic"></div>
		<a href="UserInfo/login.jsp?login_flag=score_market" target="_parent" class="login">登录</a>

		<a href="UserInfo/Register.jsp" target="_parent" class="register">注册</a>
		<%
	  }else
	  {
	   %>
		<span><%=userName %></span>
		<%
		}
		 %>
	</div>
</div>

<div class="banner">
	<div class="position">
		<div class="position_now">当前位置：</div>
		<div class="current"><%=address_now %></div>
		<div class="change_btn"><a href="index.jsp">[切换位置]</a></div>	
	</div>
	<span style="">&gt;积分商城</span>
	<div id="search">
		<input type="text" placeholder="搜索商品"></input>
		<a href="javascript:search_goods()" id="search_good"></a>
	</div>
</div>


<div class="content">
    <%
       if(user==null)
       {
     %>
     <p>亲爱的，你当前还未登录喔！</p>
     <%
     }else
     {
      %>
      <p>欢迎你，亲爱的「<font style="font-weight:bold;color:red;"><%=userName %></font>」，你在墨梅商城已经有了 <font style="font-weight:bold;color:red;"><%=score %></font> 个积分。</p>
      <%
      }
       %>
	<p>积分商城服务电话：155-4665-3997 ，联系邮箱：1978220880@qq.com。</p>
</div>
<div class="select">
	<div id="jfhl" class="active">积分好礼</div>
	<div id="dhjl">兑换记录</div>
</div>
<div class="sort_content">
	<div class="sort_box">
		<span>排序:</span>
		<div class="sort">
			<a href="" class="sort_active">默认排序</a>
			<a href="" class="sort_no_active sort_right">积分升序</a>
		</div>
	</div>
	<div class="select_avg">
		<span>筛选:</span>
		<select>
		  <option value ="全部积分">全部积分</option>
		  <option value ="1-1000">1-1000</option>
		  <option value="1001-3000">1001-3000</option>
		  <option value="3001-10000">3001-10000</option>
		  <option value="10001-50000">10001-50000</option>
		  <option value="50001以上">50001以上</option>
		</select>
	</div>
	<p><input type="checkbox" name="vehicle" value="Bike" />只看我能兑换</p>
</div>


<div class="goods_box">
	<a href="" class="goods_item">
		<img src="./images/goods.jpg" class="goods_item_img">
		<div class="goods_title">黑胶折叠创意三折星空小黑伞晴雨伞</div>
		<div class="goods_content">售价79元，兑换价28元</div>
		<div class="goods_msg">
			<div class="goods_avg">99积分</div>
			<div class="goods_zhifu">0元兑换</div>
		</div>
	</a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<a href="" class="goods_item"></a>
	<div style="clear:both;"></div>
</div>
<div class="footer_box">
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
</div>

</body>
</html>