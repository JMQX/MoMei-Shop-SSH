package com.momei.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momei.domain.Address;
import com.momei.service.AddressService;

public class AddressServlet extends HttpServlet {

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
		String s_userId=request.getParameter("userId");
		String addressName=request.getParameter("addressName");
		String phoneNum=request.getParameter("phoneNum");
		String s_addressId=request.getParameter("addressId");
		

		if(flag.equals("add_address"))
		{
			int userId=Integer.parseInt(s_userId);
			AddressService as=AddressService.getInstance();
			as.addAddress(userId, addressName,phoneNum);
			List<Address> list=as.getAllAddressByUser(userId);
			request.setAttribute("res", list);
			request.getRequestDispatcher("UserInfo/AddressMain.jsp").forward(request, response);
		}else if(flag.equals("up_address"))
		{
			int addressId=Integer.parseInt(s_addressId);
			int userId=Integer.parseInt(s_userId);
			AddressService as=AddressService.getInstance();
			as.updateAddress(addressId, addressName,phoneNum);
			List<Address> list=as.getAllAddressByUser(userId);
			request.setAttribute("res", list);
			request.getRequestDispatcher("UserInfo/AddressMain.jsp").forward(request, response);
			
		}else if(flag.equals("del_address"))
		{
			int userId=Integer.parseInt(s_userId);
			int addressId=Integer.parseInt(s_addressId);
			AddressService as=AddressService.getInstance();
			as.deleteAddress(addressId);
			List<Address> list=as.getAllAddressByUser(userId);
			request.setAttribute("res", list);
			request.getRequestDispatcher("UserInfo/AddressMain.jsp").forward(request, response);
		}else if(flag.equals("show"))
		{
			AddressService as=AddressService.getInstance();
			int userId=Integer.parseInt(s_userId);
			List<Address> list=as.getAllAddressByUser(userId);
			request.setAttribute("res", list);
			request.getRequestDispatcher("UserInfo/AddressMain.jsp").forward(request, response);
		}
		
	}

}
