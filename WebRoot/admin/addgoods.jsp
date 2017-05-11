<%@ page language="java" import="java.util.*,com.momei.domain.*,com.momei.service.*,com.momei.dao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加商品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

	
	<script type="text/javascript" src="js/jquery.js"></script> 
        <script type="text/javascript" src="js/num.util.js"></script> 
        
		<script type="text/javascript">
		
		function validateInput()
		{
		var result=true;
		document.getElementById("goodsName_msg").innerHTML="*";
		document.getElementById("unit_msg").innerHTML=""; 
		document.getElementById("price_msg").innerHTML=""; 
		document.getElementById("count_msg").innerHTML="";
		document.getElementById("goodsTypeId_msg").innerHTML="";
		document.getElementById("lblMsg").innerHTML="";
		

		if(form1.goodsName.value==""){
			document.getElementById("goodsName_msg").innerHTML="商品名称不能为空!";
			result=false;		
		}
		
		if((form1.unit.value)==""){
			document.getElementById("unit_msg").innerHTML="商品单位不能为空!";
			result=false;		
		}
		
		if((form1.count.value)==""){
			  document.getElementById("count_msg").innerHTML="商品数量不能为空!";
				result=false;		
		}else
		{
		   if(IsNoEmptyInteger(form1.count.value)==false){
				document.getElementById("count_msg").innerHTML="请输入正确的数字!";
				result=false;		
			}
		}
		
		 

         if((form1.price.value)==""){ //价格
			document.getElementById("price_msg").innerHTML="商品单价不可为空!";
			result=false;		
		}
		
		if(isNaN(form1.price.value)){ //价格
			document.getElementById("price_msg").innerHTML="请输入正确的价格!";
			result=false;		
		}
		
		if(document.getElementById("goodsTypeId").selectedIndex==0){
			document.getElementById("goodsTypeId_msg").innerHTML="请选择所属分类!";
			result=false;	
		}
	
		
		if(result==true){
			result=confirm("确定提交吗?");
		}
		
		return result;
	}
	
	//重置(清除页面信息)
	function clearInfo()
	{
		document.getElementById("goodsName_msg").innerHTML="*";
		document.getElementById("unit_msg").innerHTML="";
		document.getElementById("count_msg").innerHTML="";
		document.getElementById("price_msg").innerHTML="";
		document.getElementById("goodsTypeId_msg").innerHTML="";
		
		document.getElementById("lblMsg").innerHTML="";
	
		document.form1.goodsName.value="";
		document.form1.unit.value="";
		document.form1.price.value="";
		document.form1.count.value="";
	}
	
	   
	   </script>

         
		<link rel="stylesheet" href="css/style2.css" type="text/css"></link>
		<%
		     GoodsTypeService gts=GoodsTypeService.getInstance();
		     List<GoodsType> list=gts.getGoodsType();
		 %>
	</head>

	<body class="p10">
		

		<form name="form1" id="form1"
			action="GoodsServlet?flag=add"
			method="post" enctype="multipart/form-data">
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="100%">
				<tbody>
					<tr>
						<td class="title2">
							<img src="images/tb.gif" height="14" width="14" class="ico">
							添加商品信息
						</td>
					</tr>
				</tbody>
			</table>
			<div style="margin-top: 8px"></div>

			<table border="0" cellpadding="0" cellspacing="1" width="100%"
				class="table2">

				<tr>
					<td align="right">
						商品名称：
					</td>
					<td>
						<input type="text" style="margin-left: 15px" name="goodsName" id="goodsName"
							value='' />
						<label id="goodsName_msg">*</label>
							
						
					</td>
				</tr>
				<tr>
					<td align="right">
						所属分类：
					</td>
					<td>
						<select name="goodsTypeId" id="goodsTypeId" style="margin-left: 15px">
							<option value="0">
								商品类型
							</option>
							<%
							   for(int i=0;i<list.size();i++)
							   {
							      GoodsType gt=list.get(i);
							 %>
								<option value=<%=gt.getGoodsTypeId() %>>
									<%=gt.getGoodsTypeName() %>
								</option>
							<%
							}
							 %>		
						</select>
						<label id="goodsTypeId_msg">*</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						计量单位：
					</td>
					<td>
						<input type="text" style="margin-left: 15px" name="unit" id="unit"
							value='' />
							<label id="unit_msg">*</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						商品数量：
					</td>
					<td>
						<input type="text" style="margin-left: 15px" name="count" id="count"
							value='' />
						<label id="count_msg"></label>
					</td>
				</tr>

				<tr>
					<td align="right">
						商品单价：
					</td>
					<td>
						<input type="text" style="margin-left: 15px" name="price" id="price"
							value='' />
							<label id="price_msg"></label>
					</td>
				</tr>
				<tr>
					<td align="right">
						商品描述：
					</td>
					<td>
						<input type="text" style="margin-left: 15px" name="content" id="content"
							value='' />
					</td>
				</tr>
				<tr>
					<td align="right">
						商品图片：
					</td>
					<td>
						<input type="file" name="pic" style="margin-left: 15px"
							value='' />
					</td>  
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" style="margin-left: 50px" class="bt"
							value="提交" onclick="return validateInput()" />
						<input type="button" value="重置" class="bt" onclick="clearInfo();" />
						<label id="lblMsg">	
						</label>
					</td>
				</tr>
			</table>
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
