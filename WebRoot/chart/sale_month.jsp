<%@ page language="java" import="java.util.*,com.momei.util.*,java.io.*,java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>月营业额统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
   </head>
  
  <style type="text/css">
	p{
	   margin-left:215px;
	   margin-top:30px;
	}
	</style>
	
	<%
      WebChart_Pie chart = new WebChart_Pie();
      HashMap<String,Double> hm=(HashMap<String,Double>)request.getAttribute("hm");
      String  month_str=request.getAttribute("month_str").toString();
      String data[]=month_str.split("-");
      double count_sale_all=0;//总营业额
      DecimalFormat df = new DecimalFormat("#.0");//double型数值只取一位小数
      
      Iterator it=hm.keySet().iterator();
	  		//hasNext返回一个boolean，探测是否还有下一个
	  		while(it.hasNext())
	  		{
	  			//取出key
	  			String key=it.next().toString();
	  			//通过key取出value
	  			double count_sale=hm.get(key);
	  			count_sale_all+=count_sale;
	  			chart.setValue(key,count_sale);
	  		}
	    	  
 
    String filename = chart.generatePieChart(month_str+"营业额统计", session, new PrintWriter(out));

    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" +filename;
    %>
	
  <body>
  <form action="CountServlet?flag=sale_month" method="post">
  <span style="color:#666666;">选择月份：</span> 
 <select id="year" name="year"> 
  <%
     for(int i=2016;i<=2050;i++)
     {
        if(Integer.parseInt(data[0])==i)
        {
   %>
   <option value=<%=i %> selected><%=i %></option>
   <%
       }else
       {
     %>
   <option value=<%=i %>><%=i %></option> 
     <%
       }
     }
      %>
  </select>
  <select id="month" name="month">
  <%
     for(int j=1;j<=12;j++)
     {
        if(j<10)
        {
         if(Integer.parseInt(data[1].substring(1))==j)
         {
    %>
    <option value=0<%=j %> selected>0<%=j %></option>
    <%
         }else
        {
    %>
     <option value=0<%=j %>>0<%=j %></option>
     <%
        }
        }
        else
       {
          if(Integer.parseInt(data[1])==j)
         {
     %>
      <option value=<%=j %> selected><%=j %></option>
     <%
        }else
       {
      %>
      <option value=<%=j %>><%=j %></option>
      <%
       }
       }
       }
       %>
    
  </select>
 <input type="submit" value="统计"/>
  </form>
    <P>
   <img src="<%= graphURL %>" width=550 height=350 border=0 usemap="#<%=filename %>" alt="">
   <span>月营业额：<font style="font-weight:bold;color:#FA2E43;">¥<%=df.format(count_sale_all) %></font></span>
    </P>
  </body>
</html>