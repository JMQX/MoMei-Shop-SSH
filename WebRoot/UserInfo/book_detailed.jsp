<%@ page language="java" import="java.util.*,com.momei.domain.*,com.momei.dao.*,com.momei.service.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<script src="js/jquery-1.11.3.js"></script>
	<link rel="stylesheet" type="text/css" href="css/book_detailed.css"/>
	
	<script type="text/javascript">
	function comment(userId,bookId)
	{
	   $("#userId").val(userId);
	    $("#bookId").val(bookId);
	   $("#comment_div").show();
	}
	
	function exit()
	{
	     $("#comment_div").hide();
	}
	
	function comment_res()
	{
	   var userId=$("#userId").val(); 
	   var bookId=$("#bookId").val(); 
	   var index=0;
	   var temp=document.getElementsByName("content");
       for(var i=0;i<temp.length;i++)
       {
           if(temp[i].checked)
           {
           var content = temp[i].value;
           
            $.ajax({
            url:"CommentServlet",
            type:"post",
            data:{flag:"add_comment",userId:userId,content:content,bookId:bookId},
             success:function(data){
                 alert(data);
                 exit();
                 $("#comment").hide();
			}
            });
            index++;
             break;
           }
       }
	  
	  if(index==0)
	  {
	      alert("亲，您还没选择呢!");
	      return false;
	  }
	   
	}
	</script>
	
	<style type="text/css">
	#comment_div
	{
	   display:none;
	    top:160px;
       left:300px;
        width:300px;
	    height:140px;
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
      List<BookSeqence> list=(List<BookSeqence>)request.getAttribute("result");
      int bookId=Integer.parseInt(request.getAttribute("bookId").toString());
      //根据订单号查询订单
      BookService bs=BookService.getInstance();
      Book b=bs.getBookById(bookId);
   %>
  
  <body>
   <div class="my_box">
  <h3>订单详情</h3>
  <br/>
 <hr/><br/>
 <div class="des">
 <table style="margin-top:25px;margin-left:15px;">
 <tr>
 <td width=500px><%=b.getStatu() %></td>
 <%
    if(!b.getStatu().equals("订单已完成")&&!b.getStatu().equals("订单已取消"))
    {
  %>
 <td width=150px><a onclick="return confirm('确定要退单吗?');" href="BookServlet?flag=give_up&bookId=<%=b.getBookId() %>">申请退单</a></td>
 <%
 }
  %>
 <td width=150px><a href="UserInfo/Main.jsp" target="_parent">再来一单</a></td>
 <%
   if(b.getStatu().equals("订单已完成")&&b.getComment_statu().equals("待评价"))
   {
  %>
 <td><button onclick="comment(<%=user.getUserId() %>,<%=b.getBookId() %>);" id="comment">评价得100积分</button></td>
 <%
   }
  %>
 </tr>
 </table>
 </div>
 
 <div class="book_detailed">
  <table style="margin-left:15px;">
  <tr>
  <td colspan=4> <img src="images/logo.png" style="width:115px;height:75px;" align=middle />订单号:<%=bookId %>&nbsp;&nbsp;商家电话:155-4665-3997</td>
  </tr>
  <tr valign=middle>
  <td width=220px height=40px ><strong>干果名</strong></td>
   <td width=180px height=40px ><strong>数量</strong></td>
   <td width=150px height=40px ><strong>小计(元)</strong></td>
   <td rowspan=<%=list.size()+2 %>>
   <div class="send_msg">
   <table>
   <tr height=30px>
   <td><font style="font-size:16px;font-weight:bold;">配送信息</font></td>
   </tr>
   <tr height=30px>
   <td>支付方式：<%=b.getPay_way() %></td>
   </tr>
   <tr height=30px>
   <td>
                配送时间：<%=b.getSend_time() %>
   </td>
   </tr>
   <tr height=30px>
   <td>联系人：<%=user.getName() %></td>
   </tr>
   <tr height=30px>
   <td>
   联系电话：<%=b.getPhoneNum() %>
   </td>
   </tr>
   <tr height=30px>
   <td>收获地址：<%=b.getAddress() %></td>
   </tr>
   <tr height=30px>
   <td>
   备注：
   <%
     if(b.getMessage()==null||b.getMessage()=="")
     {
    %>
    无备注
    <%
    }else
    {
     %>
   <%=b.getMessage() %>
   <%
   }
    %>
   </td>
   </tr>
   </table>
   </div>
   </td>
  </tr>
  <%
    for(int i=0;i<list.size();i++)
    {  
       BookSeqence bsq=list.get(i);
   %>
   <tr valign=middle>
    <td width=220px height=40px><%=bsq.getGoodsName() %></td>
   <td width=180px height=40px ><%=bsq.getCount() %></td>
   <td width=150px height=40px ><%=bsq.getCount()*bsq.getPrice() %></td>
   </tr>
   <%
   }
    %>
  <tr>
  <td colspan=3 height=80px align=right><strong>实际支付 </strong><font style="font-size:25px;color:#F74342;font-weight:bold;"><%=b.getPay() %></font></td>
  </tr> 
  </table>
 </div>
 
 </div>
 
 <div id="comment_div">
 <input type="hidden" name="userId" id="userId" />
 <input type="hidden" name="bookId" id="bookId" />
 <table>
 <tr>
 <td><h4>评价</h4></td>
 </tr>
 <tr height=60px>
 <td>
 <input type=radio id="content" name="content" value="非常满意" >非常满意
 <input type=radio id="content" name="content" value="满意" >满意
 <input type=radio id="content" name="content" value="不满意" >不满意
 <input type=radio id="content" name="content" value="很不满意" >很不满意
 </td>
 </tr>
 <tr>
 <td align=right><button onclick="return comment_res();">点评</button>&nbsp;&nbsp;<button onclick="exit();">放弃</button></td>
 </tr>
 </table>
 </div>
  </body>
</html>
