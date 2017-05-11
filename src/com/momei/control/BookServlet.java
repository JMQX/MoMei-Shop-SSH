package com.momei.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.momei.domain.Book;
import com.momei.domain.BookSeqence;
import com.momei.domain.ShoppingCar_seq;
import com.momei.domain.UserInfo;
import com.momei.service.BookService;
import com.momei.service.GoodService;
import com.momei.service.SeqService;

public class BookServlet extends HttpServlet {

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

		  request.setCharacterEncoding("UTF-8");
		  String flag=request.getParameter("flag");
		  
		  if(flag.equals("add"))
		  {
			  //获取session中的购物车集合和用户信息
			  HttpSession session=request.getSession();
			  UserInfo user=(UserInfo) session.getAttribute("user");
			  
			  if(user==null)
			  {
				  //非法操作,退回登陆页面
				  //response.sendRedirect("UserInfo/login.jsp");
				  request.setAttribute("login_flag","check_pay");
			      request.getRequestDispatcher("UserInfo/login.jsp").forward(request,response);
				  
			  }else
			  {
			  
			  ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
			  
			  request.setAttribute("car_list", car_list);
			  request.getRequestDispatcher("UserInfo/book.jsp").forward(request, response);
			  }
		  }else if(flag.equals("add_data"))
		  {

			  int userId=Integer.parseInt(request.getParameter("userId"));
			  String message=request.getParameter("message");
			  String phoneNum=request.getParameter("phoneNum");
			  String address=request.getParameter("address");
			  String send_time=request.getParameter("send_time");
			  String pay_way=request.getParameter("send_way_hdfk");
			  
			   //获取当前系统时间
		      Calendar cl=Calendar.getInstance();
		      
		      //从session中取出购物车子序列集合
		      HttpSession session=request.getSession();
		      ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
		      
		      //遍历统计count和pay
		      int count=0;
		      double pay=0;
		      for(int i=0;i<car_list.size();i++)
		      {
		    	  ShoppingCar_seq sce=car_list.get(i);
		    	  count+=sce.getCount();
		    	  pay+=sce.getPrice()*sce.getCount();
		      }
		      
		     /*
		  	 * 1: 查询订单Id号的最大值
		  	 * 2：添加订单
		  	 * 3：根据订单号添加订单序列
		  	 */
		      
		      synchronized(this)
		      {
			      BookService bs=BookService.getInstance();
			      
			      //查询订单号最大值
			      int max_bookId=bs.find_Max_bookId();
			      
			      int bookId=max_bookId+1;
				  //添加订单
			      bs.addBook(bookId,userId, message, send_time, pay_way, phoneNum, address, cl,count,pay);
			      
			      //遍历添加订单序列
			      SeqService se=SeqService.getInstance();
			      for(int i=0;i<car_list.size();i++)
			      {
			    	  ShoppingCar_seq sce=car_list.get(i);
			    	  se.addSeq(sce.getGoodsId(), bookId, sce.getCount());
			      }
		      }
		     
		      //订单已提交成功，清空购物车
		      session.removeAttribute("car_list");
		      
		      request.setAttribute("Msg","订单已成功提交,请耐心等待您的干果!");
		      
		      request.getRequestDispatcher("UserInfo/Main.jsp").forward(request, response);
		  }else if(flag.equals("check_new"))
		  {
			  //分页获取新订单(按下订时间降序)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllNewBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
			  
		  }else if(flag.equals("recieve"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  //已接单
			  BookService bs=BookService.getInstance();
			  bs.upbook_recieve(bookId);
			  
				int res[]=bs.getPageCountAndRowCount();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllNewBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
			  
		  }else if(flag.equals("check_recieve"))
		  {
			//分页获取已接订单(按下订时间降序)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount_recieve();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllRecieveBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
		  }else if(flag.equals("showSeq_msg"))
		  {
			  response.setContentType("text/html;charset=utf-8");
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  //获取订单序列
			  SeqService ss=SeqService.getInstance();
			  List<BookSeqence> list=ss.getSeq(bookId);
			  
			  //用json打包列表数据传回客户端
				JSONArray jsonobj=JSONArray.fromObject(list);
				
				response.getWriter().print(jsonobj);
		  }else if(flag.equals("finish"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  //已完成,修改订单状态为已完成，同时修改商品库存量(减少)
			  BookService bs=BookService.getInstance();
			  bs.upbook_finish(bookId);
			  
			  //根据订单号查询出所有子序列的商品id和购买数量
			  SeqService seqs=SeqService.getInstance();
			  List<BookSeqence> list_bsq=seqs.getSeq(bookId);
			  //遍历修改商品数量
			  GoodService gs=GoodService.getInstance();
			  for(int i=0;i<list_bsq.size();i++)
			  {
				  BookSeqence bsq=list_bsq.get(i);
				  gs.update_count(bsq.getGoodsId(), bsq.getCount());
			  }
			
				int res[]=bs.getPageCountAndRowCount_recieve();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllRecieveBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
		  }else if(flag.equals("check_finish"))
		  {
			//分页获取已完成订单(按下订时间降序)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount_finish();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllFinishBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_finish.jsp").forward(request, response);
		  }else if(flag.equals("find"))
		  {
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  String statu_index=request.getParameter("statu");
			  String statu="";
			  if(statu_index.equals("1"))
			  {
				  statu="订单已提交";
			  }else if(statu_index.equals("2"))
			  {
				  statu="商家已接单";
			  }else if(statu_index.equals("3"))
			  {
				  statu="订单已完成";
			  }else if(statu_index.equals("4"))
			  {
				  statu="订单已取消";
			  }
			 
			  //查询订单
			  BookService bs=BookService.getInstance();
			  Book b=bs.getBookById(bookId, statu);
			  
			  if(b==null)
			  {
				  request.setAttribute("count", 0);
			  }else
			  {
				  request.setAttribute("count", 1);
			  }
			  
			  request.setAttribute("result", b);
			 
			  request.setAttribute("statu_index", statu_index);
			  
			  request.getRequestDispatcher("admin/findBook_result.jsp").forward(request, response);
			
		  }else if(flag.equals("delete"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  String statu=request.getParameter("statu");//订单状态，区别页面回转
			  //删除订单
			  //先删除订单对应序列
			  SeqService ss=SeqService.getInstance();
			  ss.deleteSeqByBookId(bookId);
			  //再删除订单
			  BookService bs=BookService.getInstance();
			  bs.deleteBook(bookId);
			  
			  if(statu.equals("new"))
			  {
				  int res[]=bs.getPageCountAndRowCount();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllNewBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
			  }else if(statu.equals("recieve"))
			  {
				  int res[]=bs.getPageCountAndRowCount_recieve();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllRecieveBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
			  }else if(statu.equals("finish"))
			  {
				  int res[]=bs.getPageCountAndRowCount_finish();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllFinishBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_finish.jsp").forward(request, response);
			  }else if(statu.equals("giveup"))
			  {
				  int res[]=bs.getPageCountAndRowCount_giveup();
					int rowCount=res[0];
					int pageCount=res[1];
				  List<Book> list=bs.getAllGiveupBooks(pageNow);
					request.setAttribute("result", list);
					request.setAttribute("rowCount", rowCount+"");
					 request.setAttribute("pageCount", pageCount+"");
					 request.setAttribute("pageNow", pageNow+"");
					 request.getRequestDispatcher("admin/book_giveup.jsp").forward(request, response);
			  }
			  else if(statu.equals("find"))
			  {
				  //转回空数据页面
				  request.setAttribute("count", 0);
				  
				  request.setAttribute("result", null);
					 
				  request.getRequestDispatcher("admin/findBook_result.jsp").forward(request, response);
			  }
		  }else if(flag.equals("delete_some"))
		  {
			  //批量删除
			  int pageNow=Integer.parseInt(request.getParameter("pageIndex"));
			  String statu=request.getParameter("statu");//订单状态，区别页面回转
			  String checkBooks[]=request.getParameterValues("checkBooks");//所有待删除订单的订单号数组
				 int int_checkBooks[]=new int[checkBooks.length];
				 for(int i=0;i<checkBooks.length;i++)
				 {
					 int_checkBooks[i]=Integer.parseInt(checkBooks[i]);
				 }
				 
				 //先删除订单序列
				  SeqService ss=SeqService.getInstance();
				  ss.deleteSeqBySomeBooks(int_checkBooks);
				  
				  //再删除订单
				  BookService bs=BookService.getInstance();
				  bs.deleteSomeBooks(int_checkBooks);
				  
				  if(statu.equals("new"))
				  {
					  int res[]=bs.getPageCountAndRowCount();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllNewBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_new.jsp").forward(request, response);
				  }else if(statu.equals("recieve"))
				  {
					  int res[]=bs.getPageCountAndRowCount_recieve();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllRecieveBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_recieve.jsp").forward(request, response);
				  }else if(statu.equals("finish"))
				  {
					  int res[]=bs.getPageCountAndRowCount_finish();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllFinishBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_finish.jsp").forward(request, response);
				  }else if(statu.equals("giveup"))
				  {
					  int res[]=bs.getPageCountAndRowCount_giveup();
						int rowCount=res[0];
						int pageCount=res[1];
					  List<Book> list=bs.getAllGiveupBooks(pageNow);
						request.setAttribute("result", list);
						request.setAttribute("rowCount", rowCount+"");
						 request.setAttribute("pageCount", pageCount+"");
						 request.setAttribute("pageNow", pageNow+"");
						 request.getRequestDispatcher("admin/book_giveup.jsp").forward(request, response);
				  }
				  else if(statu.equals("find"))
				  {
					  //转回空数据页面
					  request.setAttribute("count", 0);
					  
					  request.setAttribute("result", null);
						 
					  request.getRequestDispatcher("admin/findBook_result.jsp").forward(request, response);
				  }
				 
		  }else if(flag.equals("give_up"))
		  {
			  int bookId=Integer.parseInt(request.getParameter("bookId"));
			  BookService bs=BookService.getInstance();
			  bs.up_give_up(bookId);//取消订单
			  request.getRequestDispatcher("UserInfo/right.jsp").forward(request, response);
		  }else if(flag.equals("check_giveup"))
		  {
			//分页获取已取消订单(按下订时间降序)
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  BookService bs=BookService.getInstance();
				int res[]=bs.getPageCountAndRowCount_giveup();
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllGiveupBooks(pageNow);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("admin/book_giveup.jsp").forward(request, response);
		  }else if(flag.equals("show_booksByUser"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int userId=Integer.parseInt(request.getParameter("userId"));
			  BookService bs=BookService.getInstance();
			  int res[]=bs.getPageCountAndRowCount_user(userId);
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllBooks_user(pageNow, userId);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("UserInfo/showAll_book.jsp").forward(request, response);
			  
		  }else if(flag.equals("show_booksGiveupByUser"))
		  {
			  int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			  int userId=Integer.parseInt(request.getParameter("userId"));
			  BookService bs=BookService.getInstance();
			  int res[]=bs.getPageCountAndRowCountGiveup_user(userId);
				int rowCount=res[0];
				int pageCount=res[1];
			  List<Book> list=bs.getAllBooksGiveup_user(pageNow, userId);
				request.setAttribute("result", list);
				request.setAttribute("rowCount", rowCount+"");
				 request.setAttribute("pageCount", pageCount+"");
				 request.setAttribute("pageNow", pageNow+"");
				 request.getRequestDispatcher("UserInfo/showAllGiveup_book.jsp").forward(request, response);
		  }
	}
 
}
