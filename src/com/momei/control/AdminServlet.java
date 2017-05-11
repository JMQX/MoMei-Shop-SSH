package com.momei.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momei.domain.Admin;
import com.momei.service.AdminService;

public class AdminServlet extends HttpServlet {

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
	     String userName=request.getParameter("userName");
	     String passwd=request.getParameter("passwd");
	    
	    if(flag.equals("login"))
	    {
	    	HttpSession session = request.getSession();
	    	AdminService as=AdminService.getInstance();
	    	Admin admin=as.Login(userName, passwd);
	    	if(admin!=null)
	    	{
	    		session.setAttribute("admin", admin); //admin存进session
	    		request.getRequestDispatcher("admin/Main.jsp").forward(request, response);
	    	}
	    	else
	    	{
	    		request.setAttribute("ErrorMsg", "用户名或密码错误!");
				 request.getRequestDispatcher("admin/login.jsp").forward(request, response);
	    	}
	    }else if(flag.equals("exit"))
	    {
	    	//退出系统
	    	request.getSession().invalidate();
	    	request.getRequestDispatcher("admin/login.jsp").forward(request, response);
	    }else if(flag.equals("judgePswd"))
	    {
	    	response.setContentType("text/html;charset=utf-8");
			//验证原密码
			Admin admin=(Admin) request.getSession().getAttribute("admin");
			if(admin.getPasswd().equals(passwd))
			{
				response.getWriter().print("");
			}else
			{
				response.getWriter().print("原密码输入错误!");
			}
	    }else if(flag.equals("update_pswd"))
	    {
	    	String passwd_new=request.getParameter("passwd_new");
	    	Admin admin=(Admin) request.getSession().getAttribute("admin");
	    	//修改密码
	    	AdminService as=AdminService.getInstance();
	    	as.UpdatePasswd(admin.getUserName(), passwd_new);
	    	//修改session中的密码
	    	 HttpSession session=request.getSession();
	         admin.setPasswd(passwd_new);
	         session.setAttribute("admin", admin);
	         request.setAttribute("Msg", "修改成功!");
	        request.getRequestDispatcher("admin/update_pswd.jsp").forward(request, response);
	    	
	    }
		  
	}
}
