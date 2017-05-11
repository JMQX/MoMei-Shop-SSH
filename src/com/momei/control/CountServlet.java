package com.momei.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momei.domain.Book;
import com.momei.domain.GoodsType;
import com.momei.domain.Type_Date_Count;
import com.momei.service.BookService;
import com.momei.service.GoodsTypeService;
import com.momei.service.SeqService;

public class CountServlet extends HttpServlet {

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

	      response.setCharacterEncoding("UTF-8");
	      String flag=request.getParameter("flag");
	      
	      if(flag.equals("type_day_count"))
	      {
	    	  String date_str=request.getParameter("date_str");
	    	  
	    	  //获取该日所有的订单id集合
	    	  BookService bs=BookService.getInstance();
	    	  SeqService ss=SeqService.getInstance();
	    	  List<Book> list=bs.getBookIdByDate(date_str);
	    	  
	    	  //获取所有干果类型
	    	  GoodsTypeService gts=GoodsTypeService.getInstance();
	    	  List<GoodsType> list_type=gts.getGoodsType();
	    	  HashMap<String,Integer> hm=new HashMap<String,Integer>();
	    	  for(int i=0;i<list_type.size();i++)
	    	  {
	    		  GoodsType gt=list_type.get(i);
	    		  hm.put(gt.getGoodsTypeName(), 0);
	    	  }
	    	  
	    	  //根据订单Id获取订单序列(干果类型、数量)
	    	  for(int i=0;i<list.size();i++)
	    	  {
	    		    Book b=list.get(i);
	    	        List<Type_Date_Count> res=ss.getType_Date_count(b.getBookId());
	    	        for(int j=0;j<res.size();j++)
	    	          {
	    	        	Type_Date_Count tdc=res.get(j);
	    	        	
	    	        		if(hm.containsKey(tdc.getGoodsTypeName()))
	    	        		{
	    	        			int count=hm.get(tdc.getGoodsTypeName());
	    	        			hm.put(tdc.getGoodsTypeName(), count+tdc.getCount());//累加
	    	        		}
	    	        	
	    	          }
	    	  }
	    	  
	    	  
	    	request.setAttribute("hm", hm);
	    	request.setAttribute("date_str", date_str);
	    	 request.getRequestDispatcher("chart/type_day_count.jsp").forward(request, response);
	    	  
	      }else if(flag.equals("type_month_count"))
	      {
	    	  String year=request.getParameter("year");
	    	  String month=request.getParameter("month");
	    	  
	    	  String month_str=year+"-"+month;
	    	  
	    	  //获取该月所有的订单id集合
	    	  BookService bs=BookService.getInstance();
	    	  SeqService ss=SeqService.getInstance();
	    	  List<Book> list=bs.getBookIdByMonth(month_str);
	    	  
	    	  //获取所有干果类型
	    	  GoodsTypeService gts=GoodsTypeService.getInstance();
	    	  List<GoodsType> list_type=gts.getGoodsType();
	    	  HashMap<String,Integer> hm=new HashMap<String,Integer>();
	    	  for(int i=0;i<list_type.size();i++)
	    	  {
	    		  GoodsType gt=list_type.get(i);
	    		  hm.put(gt.getGoodsTypeName(), 0);
	    	  }
	    	  
	    	  //根据订单Id获取订单序列(干果类型、数量)
	    	  for(int i=0;i<list.size();i++)
	    	  {
	    		    Book b=list.get(i);
	    	        List<Type_Date_Count> res=ss.getType_Date_count(b.getBookId());
	    	        for(int j=0;j<res.size();j++)
	    	          {
	    	        	Type_Date_Count tdc=res.get(j);
	    	        	
	    	        		if(hm.containsKey(tdc.getGoodsTypeName()))
	    	        		{
	    	        			int count=hm.get(tdc.getGoodsTypeName());
	    	        			hm.put(tdc.getGoodsTypeName(), count+tdc.getCount());//累加
	    	        		}
	    	        	
	    	          }
	    	  }
	    	  
	    	  
	    	request.setAttribute("hm", hm);
	    	request.setAttribute("month_str", month_str);
	    	 request.getRequestDispatcher("chart/type_month_count.jsp").forward(request, response);
	      }else if(flag.equals("sale_day"))
	      {
              String date_str=request.getParameter("date_str");
	    	  
	    	  //获取该日所有的订单id集合
	    	  BookService bs=BookService.getInstance();
	    	  SeqService ss=SeqService.getInstance();
	    	  List<Book> list=bs.getBookIdByDate(date_str);
	    	  
	    	  //获取所有干果类型
	    	  GoodsTypeService gts=GoodsTypeService.getInstance();
	    	  List<GoodsType> list_type=gts.getGoodsType();
	    	  HashMap<String,Double> hm=new HashMap<String,Double>();
	    	  for(int i=0;i<list_type.size();i++)
	    	  {
	    		  GoodsType gt=list_type.get(i);
	    		  hm.put(gt.getGoodsTypeName(), 0.0);//key-类型名 value-营业额
	    	  }
	    	  
	    	  //根据订单Id获取订单序列(干果类型、数量、单价)
	    	  for(int i=0;i<list.size();i++)
	    	  {
	    		    Book b=list.get(i);
	    	        List<Type_Date_Count> res=ss.getType_Date_count(b.getBookId());
	    	        for(int j=0;j<res.size();j++)
	    	          {
	    	        	Type_Date_Count tdc=res.get(j);
	    	        	
	    	        		if(hm.containsKey(tdc.getGoodsTypeName()))
	    	        		{
	    	        			double count_sale=hm.get(tdc.getGoodsTypeName());
	    	        			hm.put(tdc.getGoodsTypeName(), count_sale+(tdc.getCount()*tdc.getPrice()));//累加
	    	        		}
	    	        	
	    	          }
	    	  }
	    	  
	    	  request.setAttribute("hm", hm);
		    	request.setAttribute("date_str", date_str);
		    	 request.getRequestDispatcher("chart/sale_day.jsp").forward(request, response);
	    	  
	    	  
	      }else if(flag.equals("sale_month"))
	      {
	    	 
	    	  String year=request.getParameter("year");
	    	  String month=request.getParameter("month");
	    	  
	    	  String month_str=year+"-"+month;
	    	  
	    	  //获取该月所有的订单id集合
	    	  BookService bs=BookService.getInstance();
	    	  SeqService ss=SeqService.getInstance();
	    	  List<Book> list=bs.getBookIdByMonth(month_str);
	    	  
	    	  //获取所有干果类型
	    	  GoodsTypeService gts=GoodsTypeService.getInstance();
	    	  List<GoodsType> list_type=gts.getGoodsType();
	    	  HashMap<String,Double> hm=new HashMap<String,Double>();
	    	  for(int i=0;i<list_type.size();i++)
	    	  {
	    		  GoodsType gt=list_type.get(i);
	    		  hm.put(gt.getGoodsTypeName(), 0.0);//key-类型名 value-营业额
	    	  }
	    	  
	    	  //根据订单Id获取订单序列(干果类型、数量、单价)
	    	  for(int i=0;i<list.size();i++)
	    	  {
	    		    Book b=list.get(i);
	    	        List<Type_Date_Count> res=ss.getType_Date_count(b.getBookId());
	    	        for(int j=0;j<res.size();j++)
	    	          {
	    	        	Type_Date_Count tdc=res.get(j);
	    	        	
	    	        		if(hm.containsKey(tdc.getGoodsTypeName()))
	    	        		{
	    	        			double count_sale=hm.get(tdc.getGoodsTypeName());
	    	        			hm.put(tdc.getGoodsTypeName(), count_sale+(tdc.getCount()*tdc.getPrice()));//累加
	    	        		}
	    	        	
	    	          }
	    	  }
	    	  
	    	  request.setAttribute("hm", hm);
		    	request.setAttribute("month_str", month_str);
		    	 request.getRequestDispatcher("chart/sale_month.jsp").forward(request, response);
	      }
	    	  
	}

}
