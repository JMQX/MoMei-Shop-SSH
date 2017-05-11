<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品分类管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  <link rel="stylesheet" href="css/style2.css" type="text/css"></link></head>
  
  <%
     List<GoodsType> list=(List)request.getAttribute("list");
   %>
<body class="p10">
  
  
		
   <form action="/shop-admin/RoleServlet?method=update" method="post" >
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr>
										<td class="title1" height="24">
											<div class="f_right">
												<span class="White"> 
												<img src="images/add.gif" height="10"
														width="10"> 
														<a
													style="color: #fff; margin-left: 3px;"
													href="admin/addType.jsp">添加</a>
													&nbsp; 
													
	
 &nbsp;&nbsp; &nbsp;</span><span
													class="STYLE1"> &nbsp;</span>
											</div>
											<img src="images/tb.gif" height="14" width="14"
												class="ico">
											商品分类管理
										</td>
									</tr>
								</tbody>
							</table>
								<div style="margin-top: 8px"></div>
					</td>
				</tr>
				<tr>
    <td>
			 <table class="table1" width="100%" border="0" cellpadding="0" cellspacing="1">
			 <tr align=middle>
			 <td width=100px>商品分类名称</td>
			  <td width=200px>商品分类描述</td>
			   <td width=50px>修改</td>
			    <td width=50px>删除</td>
			 </tr>
			 <%
			     for(int i=0;i<list.size();i++)
			     {
			        GoodsType gt=list.get(i);
			  %>
				<tr align=middle> 
								<td width=100px>
								
									<%=gt.getGoodsTypeName() %>
						 		</td>
						 			<td width=200px>
								
									<%=gt.getContent() %>
						 		</td width=50px>
						 		<td>
						 			<a href="admin/uptype.jsp?goodsTypeId=<%=gt.getGoodsTypeId() %>&goodsTypeName=<%=gt.getGoodsTypeName() %>&content=<%=gt.getContent() %>">修改</a>
						 			</td>
						 			<td width=50px>
						 			<a href="GoodsTypeServlet?flag=del&goodsTypeId=<%=gt.getGoodsTypeId() %>" onclick="return confirm('确定要下架吗')">下架</a>
						 		</td>
					 	  </tr>
					 	  	<%
						}
						 %>
				         </table>
							</tr>
						
						</table>
	
<br/>
&nbsp;&nbsp;<label style="color:red"></label>
   </form>
  </body>
  <%
     if(request.getAttribute("Msg")!=null)
     {
    request.setAttribute("Msg",request.getAttribute("Msg").toString());
    }else
    {
     request.setAttribute("Msg","");
     }
   %>
   <script type="text/javascript">
  var Msg='<%=request.getAttribute("Msg")%>';
  if(Msg!="")
  {
    alert(Msg);
  }
  </script>
</html>