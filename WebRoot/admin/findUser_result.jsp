<%@ page language="java" import="java.util.*,com.momei.domain.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查找用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
 <script type="text/javascript">
     function find_user()
	 {
	    var bookId=document.getElementById("userName").value;
	    if(bookId=="")
	    {
	       alert("请输入用户名");
	    }else
	    {
	        document.form1.action="UserServlet?flag=findUser";
	       document.form1.submit();
	     }
	 }
    </script>
    
  <link rel="stylesheet" href="css/style2.css" type="text/css"></link>
 </head>
 
          <%
		     UserInfo user=(UserInfo)request.getAttribute("userInfo");
		 %>
<body class="p10">

 <form action="UserServlet?flag=showByPage" method="post" name="form1">
 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
 <tr>
 <td height="30" bgcolor="d3eaef" >&nbsp;&nbsp;&nbsp; 用户名:<input type="text" id="userName" name="userName"/>&nbsp; &nbsp;<input type="button" onClick="find_user();" value="查询"/></td>
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
										
											<img src="images/tb.gif" height="14" width="14"
												class="ico">
											用户基本信息列表
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
         <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
         <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">姓名</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">联系电话</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">邮箱</span></div></td>
        <td height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">积分</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">头像</span></div></td>
        <td  height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
     
      <%
         if(user!=null)
         {
       %>
       <tr >
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=user.getUserName() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=user.getName() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=user.getPhoneNum() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=user.getEmail() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=user.getScore() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">
        <%
       if(user.getPic()!=null||user.getPic().length()>0)
       {
        %>
        <img src="userTx/<%=user.getPic() %>" style="width:60px;height:60px;border:none;"/>
       <%
       }else
       {
       %>
        <img src="images/default.png" style="width:60px;height:60px;"/>
      <%
       }
       %>
       </div>
        </td>
		<td height="20" bgcolor="#FFFFFF">
			<div align="center" class="STYLE21">
				  <a href="javascript:void(0);"  onclick="return confirm('确定拉黑吗?')"/>拉黑</a>
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
        <%
           if(user!=null)
           {
         %>
         1
         <%
         }else
         {
          %>
          0
          <%
          }
           %>
        </strong> 条记录</span></div></td>
         <!-- <td width="35"><a href="UserAction?method=userManage"><img src="../images/main_62.gif" width="26" height="15" /></a></td> -->   
          </tr>
        </table></td>
      </tr>
    </table>
</form>
</body>
</html>