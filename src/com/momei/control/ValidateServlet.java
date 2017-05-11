package com.momei.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	//生成动态验证码
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//创建图像
		int width=95;
		int height=43;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//创建图层
		Graphics g = image.getGraphics();
		
		//确定画笔颜色
		g.setColor(Color.gray);
		
		//填充矩形
		g.fillRect(0, 0, width, height);
		
		//在矩形立方小矩形，在小矩形里放验证码
		g.setColor(Color.white);
		
		//填充矩形
		g.fillRect(1, 1, width-2, height-2);
		
		// 设置干扰线
		Random random = new Random();
        //颜色设为白色
		g.setColor(Color.blue);
		 for (int i = 0; i < 20; i++) {
		  int xs = random.nextInt(width);
		  int ys = random.nextInt(height);
		  int xe = xs + random.nextInt(width);
	      int ye = ys + random.nextInt(height);
		  g.drawLine(xs, ys, xe, ye);
		  }

		
		//填充字符
		String data="abcdefghejklmnopqrstuvwxyzABCDEFGHEJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer buff = new StringBuffer();
		
		//随机抽取字符
		for (int i = 0; i<5; i++) {
			//从62个字符中随机取字符
			int index = random.nextInt(62);
			//截取一个字符
			String str = data.substring(index, index+1);
			
			if(index%2==0)
			{
			g.setColor(Color.green);
			}
			else if(index%3==0)
			{
				g.setColor(Color.yellow);	
			}
			else if(index%5==0)
			{
				g.setColor(Color.blue);	
			}else
			{
				g.setColor(Color.red);	
			}
			g.setFont(new Font("宋体",Font.BOLD,38));
			
			//把字符画到图片中
			g.drawString(str, 18*i, 33);//10*i是为了防止将字符重叠在一起
			
			buff.append(str);
			}
		
		//把StringBuffer中的验证码放到session里面，目的是让LoginServlet.Java获取验证码
		HttpSession session = request.getSession();
		session.setAttribute("number", buff.toString());
		
	
		//发送图片到浏览器
		response.setContentType("image/jpeg");
		ImageIO.write(image, "jpg", response.getOutputStream());
		
	}

}
