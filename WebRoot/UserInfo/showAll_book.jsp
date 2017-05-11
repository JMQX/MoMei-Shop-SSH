<%@ page language="java" import="java.util.*,com.momei.domain.*,java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	<link rel="stylesheet" type="text/css" href="css/personMsg.css">  
	
	<style type="text/css">
	#pageIndex
	{
	   width:200px;
	   text-overflow: ellipsis;
	   white-space:nowrap;
	   overflow: hidden;
	   float:right;
	}
	
	#pageIndex a
	{
	   font-size:14px;
	   color:#666666;
	}
	</style>
	
  </head>
  
  <%
      UserInfo user=(UserInfo)session.getAttribute("user");
      List<Book> list=(List<Book>)request.getAttribute("result");
      
   %>
  
  <body>
    <div class="my_box">
  <h3>订单记录</h3>
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
 
    <span id="pageIndex" >
   <%
   int pageNow=Integer.parseInt((String)request.getAttribute("pageNow"));

	if(pageNow!=1)
   {
	out.println("<a href=BookServlet?flag=show_booksByUser&userId="+user.getUserId()+"&pageNow="+(pageNow-1)+">上一页</a>");
	
   }
 
	 String s_pageCount=(String)request.getAttribute("pageCount");
	 int pageCount=Integer.parseInt(s_pageCount);
	  
			   for(int i=1;i<=pageCount;i++)
			   {
			     out.println("<a href=BookServlet?flag=show_booksByUser&userId="+user.getUserId()+"&pageNow="+i+">["+i+"]</a>");
			   }
		
			   if(pageNow!=pageCount)
			   {
			    out.println("<a href=BookServlet?flag=show_booksByUser&userId="+user.getUserId()+"&pageNow="+(pageNow+1)+">下一页</a>");
			    }
       %>
 </span>
  </div>
  </body>
</html>
