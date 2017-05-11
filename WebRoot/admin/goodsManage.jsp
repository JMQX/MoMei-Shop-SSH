<%@ page language="java" import="java.util.*,com.momei.domain.*,com.momei.service.*,com.momei.dao.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>商品管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
  <script type="text/javascript" src="../js/jquery.js"> </script>
 
  
<script type="text/javascript">
	function checkAllGoods()
	{
		var checkList=document.getElementsByName("checkGoods");
		for(var i=0;i<checkList.length;i++)
		{
			checkList[i].checked=document.getElementById("checkAll").checked;
		}
	} 

	function delGoodsList()
	{
		var checkList=document.getElementsByName("checkGoods");
		
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
			if(confirm("确定要下架选定项吗?"))
			{
				document.form1.action="GoodsServlet?flag=delete";
				document.form1.submit();
			}
		}
		else
		{
			alert("您还没有选择任何项！");
			return false;
		}
	}
	
	function type_sub()
	{
	   document.form1.action="GoodsServlet?flag=showPageType&pageNow=1";
	   document.form1.submit();
	}
	
	function find_goods()
	{
	    document.form1.action="GoodsServlet?flag=find";
	    document.form1.submit();
	}

</script>
  <link rel="stylesheet" href="css/style2.css" type="text/css"></link>
 </head>
 
          <%
		     GoodsTypeService gts=GoodsTypeService.getInstance();
		     List<GoodsType> list=gts.getGoodsType();
		     List<Goods> result=(List)request.getAttribute("result");
		 %>
<body class="p10">

 <form action="GoodsServlet?flag=showByPage" method="post" name="form1">
 <input type="hidden" name="pageIndex"  value=<%=request.getAttribute("pageNow") %>>
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
 <tr>
 <td height="30" bgcolor="d3eaef" > &nbsp;&nbsp; &nbsp;			  	
  <select name="goodsTypeId" id="goodsTypeId" onChange="type_sub();"  style="margin-left: 15px">
			            <option value="0">商品类型 </option>
			            
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
			        &nbsp; &nbsp;商品名称:<input type="text" name="goodsName"/>&nbsp; &nbsp;<input type="button" onClick="find_goods();" value="查询"/></td>
 </tr>
 </table>
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
														id="checkAll" onclick="checkAllGoods();"
	
type="checkbox">
													全选 &nbsp;&nbsp;<img src="images/add.gif" height="10"
														width="10"> <a
													style="color: #fff; margin-left: 3px;"
													href="admin/addgoods.jsp">添加</a>
													&nbsp; <img src="images/del.gif" height="10" width="10">
													<input value="刪除" onclick="delGoodsList()";
	
style="background: none; color: #fff; border: none;"
														type="button"> &nbsp;&nbsp; &nbsp;</span><span
													class="STYLE1"> &nbsp;</span>
											</div>
											<img src="images/tb.gif" height="14" width="14"
												class="ico">
											商品基本信息列表
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
         <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">商品名称</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">当前数量</span></div></td>
        <td height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">所属分类</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">单位</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">单价</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">商品描述</span></div></td>
          <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">商品图片</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
     
      
      
       <%
           for(int i=0;i<result.size();i++)
           {
              Goods g=result.get(i);
        %>
       <tr >
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input type="checkbox" name="checkGoods" value=<%=g.getGoodsId() %> />
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=g.getGoodsName() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=g.getCount() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
        <%
            for(int j=0;j<list.size();j++)
            {
            GoodsType gt=list.get(j);
            if(gt.getGoodsTypeId()==g.getGoodsTypeId())
            {
         %>
        <%=gt.getGoodsTypeName() %>
        <%
          break;
        }
        }
         %>
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=g.getUnit() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=g.getPrice() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=g.getContent() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><img src="GoodsPic/<%=g.getPic() %>" style="width:50px;height:50px;" /></div></td>
		<td height="20" bgcolor="#FFFFFF">
			<div align="center" class="STYLE21">
				<a
					href="GoodsServlet?flag=up_get&goodsId=<%=g.getGoodsId() %>&pageNow=<%=request.getAttribute("pageNow") %>&type=0">修改
				</a>
				 | <a href="GoodsServlet?flag=delete_one&goodsId=<%=g.getGoodsId() %>&pageNow=<%=request.getAttribute("pageNow") %>&type=0"  onclick="return confirm('确定要下架吗?')"/>下架</a>
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
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong><%=request.getAttribute("rowCount") %></strong> 条记录，当前第<strong><%=request.getAttribute("pageNow") %></strong> 页，共 <strong><%=request.getAttribute("pageCount") %></strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
         
            <td width="49"><div align="center"><a href="GoodsServlet?flag=showByPage&pageNow=1"><img src="images/main_54.gif" width="40" height="15" /></a></div></td><!--首页 -->
            <%
               if(Integer.parseInt(request.getAttribute("pageNow").toString())!=1)
               {
             %>
            <td width="49"><div align="center"><a href="GoodsServlet?flag=showByPage&pageNow=<%=Integer.parseInt(request.getAttribute("pageNow").toString())-1 %>"><img src="images/main_56.gif" width="45" height="15" /></a></div></td><!--前一页 -->
           <%
           }
            %>
           
            <%
              if(Integer.parseInt(request.getAttribute("pageNow").toString())!=Integer.parseInt(request.getAttribute("pageCount").toString()))
              {
             %>
            <td width="49"><div align="center"><a href="GoodsServlet?flag=showByPage&pageNow=<%=Integer.parseInt(request.getAttribute("pageNow").toString())+1 %>"><img src="images/main_58.gif" width="45" height="15" /></a></div></td><!--后一页 -->
            <%
            }
             %>
            <td width="49"><div align="center"><a href="GoodsServlet?flag=showByPage&pageNow=<%=request.getAttribute("pageCount") %>"><img src="images/main_60.gif" width="40" height="15" /></a></div></td><!--尾页 -->
             
           
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <td width="22"><div align="center">
            <select name="pageNow" onChange="document.form1.submit()" style="width:32px; height:18px; font-size:12px; border:solid 1px #7aaebd;">
            <option value=<%=request.getAttribute("pageNow") %>><%=request.getAttribute("pageNow") %></option>
			<%
			   for(int i=1;i<=Integer.parseInt(request.getAttribute("pageCount").toString());i++)
			   {
			      if(i!=Integer.parseInt(request.getAttribute("pageNow").toString()))
			      {
			 %>	
				<option value=<%=i %>><%=i %></option>
			<%
			  }
			  }
			 %>		
			</select>
         
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
         <!-- <td width="35"><a href="UserAction?method=userManage"><img src="../images/main_62.gif" width="26" height="15" /></a></td> -->   
          </tr>
        </table></td>
      </tr>
    </table></td>
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

