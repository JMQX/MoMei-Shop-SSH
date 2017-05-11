<%@ page language="java" import="java.util.*,com.momei.domain.*,com.momei.dao.*,com.momei.service.*,java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>right</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	<script type="text/javascript" src="js/jquery-1.6.1.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/right.css"/>
	
  </head>
  <%
     UserInfo user=(UserInfo)session.getAttribute("user");
     //查询出最新订单数据(前三条)
     BookService bs=BookService.getInstance();
     List<Book> list=bs.getBook_Recent(user.getUserId());
     
   %>
  <body>
  <div class="my_box">
  <table>
  <tr>
  <td width=150px>
   <div id="tx">
     <%
      if(user.getPic()!=null)
      {
      %>
    <a href="UserInfo/personMsg.jsp"><img src="userTx/<%=user.getPic() %>" style="border:none;"></a>
    <%
    }else
    {
     %>
      <a href="UserInfo/personMsg.jsp"><img src="images/default.png" style="border:none;"></a>
     <%
     }
      %>
  </div>
  </td>
  <td>
  <table>
  <tr>
  <td>你好,<span style="font-weight:bold;"><%=user.getUserName() %></span></td>
  </tr>
   <tr>
 <td><br></td>
  </tr>
  </table>
  </td>
  </tr>
  </table>
  </div>
  <div class="my_box_book">
  <h3>最近订单</h3>
  <br/>
 <hr/>
 <table>
  <%
     for(int i=0;i<list.size();i++)
     {
        Book b=list.get(i);
        String date_orserStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(b.getDates_order()); 
   %>
   <tr>
   <td width=400px>
   <div style="width:1000px;color:#767686;font-size:14px;border-bottom:1px solid #C6C6D3">
   <span><img src="images/logo.png" style="width:115px;height:75px;" align=middle /><font style="color:#0F0F0F;font-size:16px;">共<%=b.getCount() %>件干果</font></span>
   <span style="position:absolute;margin-left:130px;margin-top:20px;"><%=date_orserStr %></span>
    <span style="position:absolute;margin-left:330px;margin-top:20px;"><%=b.getPay_way() %><font style="color:#0F0F0F;font-size:16px;">￥<%=b.getPay() %></font></span>
    <span style="position:absolute;margin-left:500px;margin-top:20px;"><%=b.getStatu() %></span>
    <span style="position:absolute;margin-left:620px;margin-top:20px;"><a style="color:#0089DC;text-decoration:none;" href="SeqServlet?flag=query&bookId=<%=b.getBookId() %>" target="right">订单详情</a></span>
     </div>
     </td>
   </tr>
   <%
   }
    %>
 </table>
  </div>
  </body>
</html>
