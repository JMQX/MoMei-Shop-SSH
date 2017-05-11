package com.momei.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momei.domain.Store;
import com.momei.domain.UserInfo;
import com.momei.service.StoreService;

public class StoreServlet extends HttpServlet {

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
	          
	          if(flag.equals("add"))
	          {
	          response.setContentType("text/html;charset=utf-8");	  
	          int goodsId=Integer.parseInt(request.getParameter("goodsId"));
	          HttpSession session=request.getSession();
	          UserInfo user=(UserInfo) session.getAttribute("user");
	          
	          StoreService ss=StoreService.getInstance();
	          if(user==null)
	          {
	        	  response.getWriter().print("请先登录!");
	          }else
	          {
	        	  
	           Store store=ss.getJudge(goodsId, user.getUserId());
	           
	           if(store==null)
	           {
	        	   ss.addStore(goodsId, user.getUserId());
	 	          response.getWriter().print("收藏成功!");
	           }else
	           {
	        	   response.getWriter().print("已经收藏过了!");
	           }
	        
	          }
	          }else if(flag.equals("delete"))  
	          {
	        	  int storeId=Integer.parseInt(request.getParameter("storeId"));
	        	  StoreService ss=StoreService.getInstance();
	        	  ss.deleteStore(storeId);
	        	 
	        	  request.getRequestDispatcher("UserInfo/store.jsp").forward(request, response);
	          }
	}

}
