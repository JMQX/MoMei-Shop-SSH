<%@ page language="java" import="java.util.*,com.momei.util.*,java.io.*,java.text.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>日营业额统计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
 <link rel="stylesheet" type="text/css" href="css/calendar.css" media="all" />
   <script type="text/javascript" src="js/calendar.js" charset="UTF-8"></script>  
   
   <script type="text/javascript">
    function check()
    {
       var date_str=document.getElementById("date_str").value;
       if(date_str=="")
       {
          alert("请选择日期!");
       }
    }
   </script>
	
	<style type="text/css">
	p{
	   margin-left:215px;
	   margin-top:30px;
	}
	</style>
	
  </head>
  
  <%
      WebChart_Pie chart = new WebChart_Pie();
      HashMap<String,Double> hm=(HashMap<String,Double>)request.getAttribute("hm");
      String date_str="2016-01-01";
      if(request.getAttribute("date_str")!=null)
      {
         date_str=request.getAttribute("date_str").toString();
      }
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
	    	  
 
    String filename = chart.generatePieChart(date_str+"营业额统计", session, new PrintWriter(out));

    String graphURL = request.getContextPath() + "/servlet/DisplayChart?filename=" +filename;
    %>
  
  <body>
  <form action="CountServlet?flag=sale_day" method="post" onsubmit="return check();">
  <span style="color:#666666;">选择日期：</span><input type="text" value=<%=date_str %> name="date_str" id="date_str" readonly="readonly" value="" style="width:90px;height:22px"/>
            <script type="text/javascript">
			Calendar.setup( {
				inputField :"date_str", 
				ifFormat :"%Y-%m-%d", 
				showsTime :false, 
				onUpdate :null
			});
			</script>
	<input type="submit" value="统计"/>
	</form>
  <P>
<img src="<%= graphURL %>" width=550 height=350 border=0 usemap="#<%=filename %>" alt="">
 <span>日营业额：<font style="font-weight:bold;color:#FA2E43;">¥<%=df.format(count_sale_all) %></font></span>
</P>
  </body>
</html>
