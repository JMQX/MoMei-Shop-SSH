package com.momei.control;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.momei.util.GetYZM;

public class EmailServlet extends HttpServlet {

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

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String toU=request.getParameter("Email");
		
		/*
		 * 向目标邮箱发送邮件
		 */
		String uTopic="墨梅注册";
		String ucontenet=GetYZM.Gy();//产生随机6位数验证码
		HttpSession session = request.getSession();
		session.setAttribute("YZM", ucontenet);//临时存储
		
		//自己的邮箱，做测试
		String uname="18617245418";
		String password="momei2016";
		
		//组织邮件
		Properties props=new Properties();//用来读取配置文件
		props.put("mail.transport.protocol", "smtp");//邮件传送协议
		props.put("mail.host","smtp.163.com");//邮件主机传送协议服务器
		props.put("mail.smtp.auth", "true");//密码安全认证
		
        Session session2=Session.getInstance(props);//定义一个邮件基本会话
		
		//组织邮件
		MimeMessage msg=new MimeMessage(session2);
		
		//组织邮件内容
		Address toAddress=new InternetAddress("18617245418@163.com","momei");
		
		try {
			msg.addRecipient(Message.RecipientType.TO, toAddress);
			msg.setFrom(toAddress);
			
			//发送对象
			msg.addRecipients(Message.RecipientType.TO, toU);
			
			//发送主题
			msg.setSubject(uTopic);
			
			//发送内容
			msg.setText(ucontenet);
			
			//保存邮件
			msg.saveChanges();
			
			//取得发送的工具
			Transport ts=session2.getTransport();
			ts.connect(uname,password);
			ts.sendMessage(msg, msg.getAllRecipients());
			
			//关闭连接
			ts.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().print("邮件发送成功,请注意查收!");
	}

}
