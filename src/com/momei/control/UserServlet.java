package com.momei.control;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.momei.domain.ShoppingCar_seq;
import com.momei.domain.UserInfo;
import com.momei.service.UserService;

public class UserServlet extends HttpServlet {

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
		String Name=request.getParameter("Name");
		String phoneNum=request.getParameter("phoneNum");
		String Email=request.getParameter("Email");
		String School=request.getParameter("School");
		String passwd=request.getParameter("passwd");
		String passwdNew=request.getParameter("passwdNew");
		String EmailNew=request.getParameter("EmailNew");
		String search_text=request.getParameter("search_text");
		
		if(flag.equals("address_now"))
		{
			//将搜索地址存于session中
			HttpSession session = request.getSession();
			session.setAttribute("address_now", search_text);
			
		}
		
		if(flag.equals("add"))
		{
			UserInfo user=new UserInfo();
			user.setUserName(userName);
			user.setName(Name);
			user.setPhoneNum(phoneNum);
			user.setEmail(Email);
			user.setSchool(School);
			user.setPasswd(passwd);
			
			UserService us=UserService.getInstance();
			us.addUser(user);
			
			request.setAttribute("userName", userName);
			//自动转到登陆页面
			request.getRequestDispatcher("UserInfo/login.jsp").forward(request, response);
			
		}
		else if(flag.equals("checkName"))
		{
			response.setContentType("text/html;charset=utf-8");
			UserService us=UserService.getInstance();
			boolean b=us.findUser(userName);
			if(b==true)
			{
				response.getWriter().print("该用户名已存在!");
			}
		}else if(flag.equals("checkPhone"))
		{
			response.setContentType("text/html;charset=utf-8");
			UserService us=UserService.getInstance();
			boolean b=us.findPhoneNum(phoneNum);
			if(b==true)
			{
				response.getWriter().print("该手机号已被绑定!");
			}
		}else if(flag.equals("checkEmail"))
		{
			response.setContentType("text/html;charset=utf-8");
			UserService us=UserService.getInstance();
			boolean b=us.findEmail(Email);
			if(b==true)
			{
				response.getWriter().print("该邮箱已被绑定!");
			}
		}
		else if(flag.equals("login_checkYZM"))
		{
			response.setContentType("text/html;charset=utf-8");
			//2.获取HttpSession.java保存在session中的验证码
			HttpSession session = request.getSession();
			String number = (String) session.getAttribute("number");
			String YZM=request.getParameter("YZM");
			if(!YZM.equalsIgnoreCase(number))
			{
				response.getWriter().print("验证码错误!");
			}
		}
		else if(flag.equals("login"))
		{
			String uName=request.getParameter("uName");
			String login_flag=request.getParameter("login_flag");
			UserService us=UserService.getInstance();
			UserInfo user=us.Login(uName, passwd);
			if(user!=null)
			{
				HttpSession session = request.getSession();
				 session.setAttribute("user", user);
				 if(login_flag.equals("init"))
				 { 
				 request.getRequestDispatcher("UserInfo/Main.jsp").forward(request, response);
				 }else if(login_flag.equals("check"))
				 {
					 request.getRequestDispatcher("UserInfo/Main_Personal.jsp").forward(request, response);
				 }else if(login_flag.equals("check_pay"))
				 {
					 ArrayList<ShoppingCar_seq> car_list=(ArrayList<ShoppingCar_seq>) session.getAttribute("car_list");
					  
					  request.setAttribute("car_list", car_list);
					  request.getRequestDispatcher("UserInfo/book.jsp").forward(request, response);
				 }else if(flag.equals("score_market")){
					  
				 }
			}else
			{
				 request.setAttribute("ErrorMsg", "用户名或密码错误!");
				 request.getRequestDispatcher("UserInfo/login.jsp").forward(request, response);
			}
			 
		}else if(flag.equals("addTx"))
		{
			String pic="";

			 String path=request.getRealPath("/userTx");
		       
		        DiskFileItemFactory factory=new DiskFileItemFactory();
		        ServletFileUpload sfu=new ServletFileUpload(factory);
		        sfu.setHeaderEncoding("UTF-8");  //处理中文问题
		        sfu.setSizeMax(1024*1024);   //限制文件大小
		        
		        try {
		            List<FileItem> fileItems= sfu.parseRequest(request);  //解码请求 得到所有表单元素
		            int i=0;
		            for (FileItem fi : fileItems) {
		                //有可能是 文件，也可能是普通文字 
		                if (fi.isFormField()) { //这个选项是 文字 
		                  i++;
		                }else{
		                    // 是文件
		                    String fn_flag=fi.getName();
		                    String[] str=fn_flag.split("\\.");
		                    String fn=userName+"."+str[1];//头像名称为用户名
		                     pic=fn;
		                    // fn 是可能是这样的 c:\abc\de\tt\fish.jpg
		                    fi.write(new File(path,fn));
		                   
		                }  
		                
		            }    
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		       
		        UserService us=UserService.getInstance();
		        us.addUserTx(userName, pic); 
		        //修改session中user头像
		        HttpSession session=request.getSession();
		         UserInfo user=(UserInfo) session.getAttribute("user");
		         user.setPic(pic);
		         session.setAttribute("user", user);
		        request.getRequestDispatcher("UserInfo/personMsg.jsp").forward(request, response);
		        
		        
		}else if(flag.equals("update_pn"))
		{
			 UserService us=UserService.getInstance();
			 us.UpdatePh(userName, phoneNum);
			 //修改session中user手机号
		        HttpSession session=request.getSession();
		         UserInfo user=(UserInfo) session.getAttribute("user");
		         user.setPhoneNum(phoneNum);
		         session.setAttribute("user", user);
		        request.getRequestDispatcher("UserInfo/personMsg.jsp").forward(request, response);
			 
		}else if(flag.equals("update_em"))
		{
			 UserService us=UserService.getInstance();
			 us.UpdateEmail(userName, EmailNew);
			 //修改session中user邮箱地址号
		        HttpSession session=request.getSession();
		         UserInfo user=(UserInfo) session.getAttribute("user");
		         user.setEmail(EmailNew);
		         session.setAttribute("user", user);
		        request.getRequestDispatcher("UserInfo/personMsg.jsp").forward(request, response);
			 
		}else if(flag.equals("judgePswd"))
		{
			response.setContentType("text/html;charset=utf-8");
			//验证原密码
			UserInfo user=(UserInfo) request.getSession().getAttribute("user");
			if(user.getPasswd().equals(passwd))
			{
				response.getWriter().print("");
			}else
			{
				response.getWriter().print("原密码输入错误!");
			}
		}
		else if(flag.equals("up_passwd"))
		{
			UserService us=UserService.getInstance();
			us.UpdatePasswd(userName, passwdNew);
			 //修改session中user的密码
	        HttpSession session=request.getSession();
	         UserInfo user=(UserInfo) session.getAttribute("user");
	         user.setPasswd(passwdNew);
	         session.setAttribute("user", user);
	        request.getRequestDispatcher("UserInfo/personMsg.jsp").forward(request, response);
		}else if(flag.equals("exit"))
		{

			 //销毁session
			 request.getSession().invalidate();
			 request.getRequestDispatcher("UserInfo/Main.jsp").forward(request, response);
		}else if(flag.equals("showByPage"))
		{
			int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			UserService us=UserService.getInstance();
			int res[]=us.getPageCountAndRowCount();
			int rowCount=res[0];
			int pageCount=res[1];
			List<UserInfo> list=us.getUsersByPage(pageNow);
			request.setAttribute("result", list);
			request.setAttribute("rowCount", rowCount+"");
			 request.setAttribute("pageCount", pageCount+"");
			 request.setAttribute("pageNow", pageNow+"");
			 request.getRequestDispatcher("admin/userManage.jsp").forward(request, response);
		}else if(flag.equals("findUser"))
		{
			//根据用户名查出用户
			UserService us=UserService.getInstance();
			UserInfo user=us.findUserByName(userName);
			request.setAttribute("userInfo", user);
			request.getRequestDispatcher("admin/findUser_result.jsp").forward(request, response);
		}
	}

}
