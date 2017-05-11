package com.momei.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momei.domain.BookSeqence;
import com.momei.service.SeqService;

public class SeqServlet extends HttpServlet {

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
          
          if(flag.equals("query"))
          {
        	  int bookId=Integer.parseInt(request.getParameter("bookId"));
        	  SeqService ss=SeqService.getInstance();
        	  List<BookSeqence> list=ss.getSeq(bookId);
        	  request.setAttribute("result", list);
        	  request.setAttribute("bookId", bookId);
        	  
        	  request.getRequestDispatcher("UserInfo/book_detailed.jsp").forward(request, response);
          }
        
	}
}
