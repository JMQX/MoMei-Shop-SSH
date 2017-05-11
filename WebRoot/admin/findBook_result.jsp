<%@ page language="java" import="java.util.*,com.momei.domain.*,java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询订单结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
   <script type="text/javascript" src="js/jquery.js"> </script>
 
  
<script type="text/javascript">
	function checkAllbooks()
	{
		var checkList=document.getElementsByName("checkBooks");
		for(var i=0;i<checkList.length;i++)
		{
			checkList[i].checked=document.getElementById("checkAll").checked;
		}
	} 

	function delbooksList()
	{
		var checkList=document.getElementsByName("checkBooks");
		
		for(var i=0;i<checkList.length;i++)
		{
			var isSelected=false;
			if(checkList[i].checked==true)
			{
				isSelected=true;
				break;
			}			
		}
		
		if(isSelected==true)
		{
			if(confirm("确定要删除选定项吗?"))
			{
				document.form1.action="BookServlet?flag=delete_some&statu=find";
				document.form1.submit();
			}
		}
		else
		{
			alert("您还没有选择任何项！");
			return false;
		}
	}
	
	function showBookSeq_msg(bookId,name,phoneNum,address,message)
	{
          $.ajax({
            url:"BookServlet",
            type:"post",
            dataType:"json",
            data:{flag:"showSeq_msg",bookId:bookId},
             success:function(seqlist){
             
                  $("#seq_msg").empty();//订单序列清空
                  $("#con_msg").empty();//联系人信息清空
                  
                  $("#bookSeq_msg span").html("订单号："+bookId);
                   
                   var str="<tr valign=middle><td>干果名</td><td>数量</td><td>单价</td></tr>";
                    $("#seq_msg").append(str);
               
                 $.each(seqlist,function(key,obj){
                   
                    var str="<tr valign=middle><td>"+obj.goodsName+"</td><td>"+obj.count+"</td><td>￥"+obj.price+"</td></tr>";
                    $("#seq_msg").append(str);
                    
                 }) 
                 
                 var str_con1="<tr valign=middle><td>姓名：</td><td>"+name+"</td></tr>";
                 $("#con_msg").append(str_con1);
                  var str_con2="<tr valign=middle><td>联系电话：</td><td>"+phoneNum+"</td></tr>";
                 $("#con_msg").append(str_con2);
                  var str_con3="<tr valign=middle><td>收货地址：</td><td>"+address+"</td></tr>";
                 $("#con_msg").append(str_con3);
                  var str_con4="<tr valign=middle><td>备注：</td><td>"+message+"</td></tr>";
                 $("#con_msg").append(str_con4);
                 
                $("#bookSeq_msg").show();
			}
         });
	}
	
	function hide_msg()
	{
	   $("#bookSeq_msg").hide();
	}
	

</script>
    <style type="text/css">
 
   #bookSeq_msg
	{
	   display:none;
       position: fixed;
       top:110px;
       left:720px;
       padding-left:10px;
       padding-top:10px;
       padding-right:10px;
       padding-bottom:10px;
       width:300px;
       hieght:auto;
       background-color:#ffffff;
       border:1px solid #246ABF;
       box-shadow:0 0 10px #ADCEF1; 
	 }

</style>
  <link rel="stylesheet" href="css/style2.css" type="text/css"></link>
 </head>
 
<body class="p10">

 <form action="BookServlet?flag=check_new" method="post" name="form1">
  <input type="hidden" name="pageIndex"  value=0 >
 	<div style="margin-top: 5px"></div>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30">

    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr>
										<td class="title1" height="24">
											<div class="f_right">
												<span class="White"> <input name="checkAll"
														id="checkAll" onclick="checkAllbooks();"
	
type="checkbox">
													全选 &nbsp;&nbsp;
													&nbsp; <img src="images/del.gif" height="10" width="10">
													<input value="刪除" onclick="delbooksList()";
	
style="background: none; color: #fff; border: none;"
														type="button"> &nbsp;&nbsp; &nbsp;</span><span
													class="STYLE1"> &nbsp;</span>
											</div>
											<img src="images/tb.gif" height="14" width="14"
												class="ico">
											订单基本信息列表
										</td>
									</tr>
								</tbody>
							</table>
								<div style="margin-top: 8px"></div>
    
    </td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="4%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
         选择
        </div></td>
         <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">订单号</span></div></td>
         <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">干果数量</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">支付金额</span></div></td>
        <td height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">支付方式</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">配送时间</span></div></td>
         <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">下订时间</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">订单状态</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
     
      
      
       <%
              if(request.getAttribute("result")!=null)
              {
                   Book b=(Book)request.getAttribute("result");
               String date_orserStr ="";
                String message="无备注";
            
                date_orserStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(b.getDates_order()); 
                if(b.getMessage()!=null&&b.getMessage().length()>0)
               {
                  message=b.getMessage();
               }   
        %>
       <tr >
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="checkBooks" value=<%=b.getBookId() %> />
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=b.getBookId() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=b.getUserName() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=b.getCount() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=b.getPay() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=b.getPay_way() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=b.getSend_time() %></div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=date_orserStr %></div></td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=b.getStatu() %></div></td>
		<td height="20" bgcolor="#FFFFFF">
			<div align="center" class="STYLE21">
			<%
			   if(request.getAttribute("statu_index").equals("1"))
			   {
			 %>
			    <a href="BookServlet?flag=recieve&bookId=<%=b.getBookId() %>&pageNow=1"  />接单</a>
			    |
			    <%
			    }else if(request.getAttribute("statu_index").equals("2"))
			    {
			     %>
			      <a href="BookServlet?flag=finish&bookId=<%=b.getBookId() %>&pageNow=1"  />完成</a>
			      |
			     <%
			     }
			      %>
				<a
				    onmouseover="showBookSeq_msg(<%=b.getBookId() %>,'<%=b.getName() %>','<%=b.getPhoneNum() %>','<%=b.getAddress() %>','<%=message %>');"
				    onmouseout="hide_msg();"
					href="javascript:void(0);">订单详情
				</a>
				 | <a href="BookServlet?flag=delete&bookId=<%=b.getBookId() %>&pageNow=0&statu=find"  onclick="return confirm('确定删除吗?')"/>删除</a>
			</div>
		</td>
	</tr>
    
    <%
    }
     %>
   
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>
        <%=request.getAttribute("count") %>
        </strong> 条记录</span></div></td>  
          </tr>
        </table></td>
      </tr>
    </table>
</form>

<div id="bookSeq_msg">
<span></span>
<table style="width:200px;" id="seq_msg">
</table>
<table id="con_msg">
</table>
</div>

</body>
</html>