 package com.momei.control;

import java.awt.Color;  
import java.awt.Font;  
import java.io.IOException;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.CategoryAxis;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.renderer.category.BarRenderer;  
import org.jfree.chart.title.TextTitle;  
import org.jfree.data.category.DefaultCategoryDataset;  

import com.momei.domain.Book;
import com.momei.domain.Goods;
import com.momei.domain.Type_Date_Count;
import com.momei.service.BookService;
import com.momei.service.GoodService;
import com.momei.service.SeqService;
import com.momei.util.Sort_deal;
/** 
 * 柱状分布统计图 
 * 干果销量最佳统计
 * @说明  
 * @author cuisuqiang 
 * @version 1.0 
 * @since 
 */  
@SuppressWarnings("serial") 
  

public class WebChart_Bar extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		 response.setContentType("text/html");  
		 
		      DefaultCategoryDataset Data = new DefaultCategoryDataset();  
		      
		      //获取数据
		      /*
		       * 关联表：订单、序列、干果、干果类型
		       * 1:获取所有已完成订单的订单号
		       * 2：根据订单号获取该订单号对应序列的商品名和数量、所属类型名
		       */
		      
		      //获取所有的订单id集合
	    	  BookService bs=BookService.getInstance();
	    	  SeqService ss=SeqService.getInstance();
	    	  List<Book> list=bs.getBookId();
	    	  
	    
	    	  //获取所有的干果
	    	  GoodService gs=GoodService.getInstance();
	    	  List<Goods> list_g=gs.getAll_Goods();
	    	  
	    	  //key-干果名 value-销量
	    	  HashMap<String,Integer> hm=new HashMap<String,Integer>();
	    	  //key-干果名 value-类型名
	    	  HashMap<String,String> hm2=new HashMap<String,String>();
	    	  
	    	  for(int i=0;i<list_g.size();i++)
	    	  {
	    		  Goods g=list_g.get(i);
	    		  hm.put(g.getGoodsName(), 0);
	    		  //根据干果id获取干果类型名
	    		  hm2.put(g.getGoodsName(), gs.getTypeName(g.getGoodsId()));
	    	  }
	    	  
	    	  //根据订单Id获取订单序列的干果和数量
	    	  for(int i=0;i<list.size();i++)
	    	  {  
	    		    Book b=list.get(i);
	    	        List<Type_Date_Count> res=ss.getType_Date_count(b.getBookId());
	    	        for(int j=0;j<res.size();j++)
	    	          {
	    	        	Type_Date_Count tdc=res.get(j);
	    	        	
	    	        		if(hm.containsKey(tdc.getGoodsName()))
	    	        		{
	    	        			int count=hm.get(tdc.getGoodsName());
	    	        			hm.put(tdc.getGoodsName(), count+tdc.getCount());//累加
	    	        		}
	    	        	
	    	          }
	    	  }
	    	  
	    	 
	    	  /*
	    	   * 遍历取销量前10的干果(销量不为0)
	    	   */
	    	  ArrayList<Integer> count_list=new ArrayList<Integer>();
	    	  for(int i=0;i<list_g.size();i++)
	    	  {
	    		  Goods g=list_g.get(i);
	    		  if(hm.containsKey(g.getGoodsName()))
	    		  {
	    			  int count=hm.get(g.getGoodsName());
	    			  count_list.add(count);
	    		  }
	    	  }  
	    	  
	    	  int sort_temp[]=null;
	    	  int count_judge=0; //销量第10名(销量不为0)
	    	  int sort[]=Sort_deal.sort(count_list);
	    	   sort_temp=new int[sort.length];
	    	   for(int i=sort.length-1;i>=0;i--)
		       sort_temp[sort.length-1-i]=sort[i];
	    	   
	    	   if(sort_temp.length<=10)
	    	   {
	    		   for(int i=sort_temp.length-1;i>=0;i--)
	    		   {
	    			   if(sort_temp[i]!=0)
	    			   {
	    				   count_judge=sort_temp[i];
	    				   break;
	    			   }
	    		   }
	    	   }else
	    	   {
	    		   for(int i=9;i>=0;i--)
	    		   {
	    			   if(sort_temp[i]!=0)
	    			   {
	    				   count_judge=sort_temp[i];
	    				   break;
	    			   }
	    		   }
	    	   }
	    	 
	    	  //数据赋值
	    	  Iterator it=hm.keySet().iterator();
		  		//hasNext返回一个boolean，探测是否还有下一个
		  		while(it.hasNext())
		  		{
		  			//取出key
		  			String key=it.next().toString();
		  			//通过key取出value
		  			int count=hm.get(key);
		  			if(count>=count_judge)
		  			{
		  			String goodsTypeName=null;
		  			
		  			//根据key(干果名)获取干果类型
		  			if(hm2.containsKey(key))
		  			{
		  				goodsTypeName=hm2.get(key);
		  			}
		  			
		  		   Data.addValue(count, goodsTypeName,key);
		  			}
		  			
		  		}
	    	  
		         try {  
		             DefaultCategoryDataset data = Data;  
		             // 使用ChartFactory创建3D柱状图，不想使用3D，直接使用createBarChart  
		            JFreeChart chart = ChartFactory.createBarChart3D(  
		            		"干果销量最佳前十统计",   
		                     "销量/类型/干果名",   
		                    "销量",   
		                     data,  
		                     PlotOrientation.VERTICAL,   
		                   true,   
		                   false,   
		                    false  
		             );  
		             // 设置整个图片的背景色  
		             chart.setBackgroundPaint(Color.white);  
		             // 设置图片有边框  
		            chart.setBorderVisible(true);  
		            Font kfont = new Font("宋体", Font.PLAIN, 12);    // 底部  
		            Font titleFont = new Font("宋体", Font.BOLD, 20); // 图片标题  
		            // 图片标题  
		             chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));  
		            // 底部  
		             chart.getLegend().setItemFont(kfont);  
		             // 得到坐标设置字体解决乱码  
		             CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();  
		             categoryplot.setDomainGridlinesVisible(true);  
		             categoryplot.setRangeCrosshairVisible(true);  
		             categoryplot.setRangeCrosshairPaint(Color.blue);  
		           NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();  
		            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
		            BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();  
		             barrenderer.setBaseItemLabelFont(new Font("宋体", Font.PLAIN, 12));  
		            barrenderer.setSeriesItemLabelFont(1, new Font("宋体", Font.PLAIN, 12));  
		           CategoryAxis domainAxis = categoryplot.getDomainAxis();           
		            /*------设置X轴坐标上的文字-----------*/  
		            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));  
		             /*------设置X轴的标题文字------------*/  
		             domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
		             /*------设置Y轴坐标上的文字-----------*/  
		           numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));  
		             /*------设置Y轴的标题文字------------*/  
		            numberaxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));  
		             /*------这句代码解决了底部汉字乱码的问题-----------*/  
		             chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));    
		             ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,  
		                    chart, 900, 400, null);  
		        } catch (Exception es) {  
		            es.printStackTrace();  
		        }   
	}

}
