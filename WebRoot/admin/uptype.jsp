<%@ page language="java" import="java.util.*,com.momei.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更改商品分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
<style type="text/css">
		table label {color:red; font-size:12px;margin-left:5px } 
	</style>
	  <link rel="stylesheet" href="css/style2.css" type="text/css"></link>
	<script type="text/javascript">
	function validateInput()
	{
		var result=true;
		document.getElementById("lblCateName").innerHTML="*";		
		if(form1.goodsTypeName.value=="")
		{
			document.getElementById("lblCateName").innerHTML="分类名称不能为空";
			result=false;		
		}
		
		if(result==true)
		{
			result=confirm("确定提交吗?");
		}
		
		return result;
			
	}
	
	//重置(清除页面信息)
	function clearInfo()
	{
		document.getElementById("lblCateName").innerHTML="*";
		document.form1.goodsTypeName.value="";
		document.form1.content.value="";
	}
	</script>
	
	<%

	    String goodsTypeName=Tools.getNewString(request.getParameter("goodsTypeName"));
	     String content=Tools.getNewString(request.getParameter("content"));    
	 %>
 </head>
  
  <body class="p10">
 
  <form  name="form1" action="GoodsTypeServlet?flag=up&goodsTypeId=<%=request.getParameter("goodsTypeId") %>"  method="post" >
  <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
	 	<tr>
	 		<td class="title2" > <img src="images/tb.gif" height="14" width="14" class="ico">更改商品分类</td>
	 	</tr>
 </table>
  <div style="margin-top: 8px"></div>
  <table  border="0" cellpadding="0" cellspacing="1" width="100%" class="table2">
  <tbody>
  <tr><td align="right" width="100">商品分类名称：</td><td><input type="text" style="margin-left: 15px" name="goodsTypeName" value=<%=goodsTypeName %>  /><label id="lblCateName">*</label></td></tr>
  <tr><td align="right">商品分类描述：</td><td><input type="text" style="margin-left: 15px" name="content"  value=<%=content %> /></td></tr>

  <tr><td colspan="2"><input type="submit" style="margin-left: 50px"  class="bt" value="提交" onclick="return validateInput()"/>  <input type="button" value="重置" class="bt" onclick="clearInfo();"/><label  id="lblMsg"></label></td></tr>
  </tbody>
  </table>
  </form>
  </body>
</html>