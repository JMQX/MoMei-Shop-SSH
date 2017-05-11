package com.momei.util;

//产生随机6位数字验证码
public class GetYZM {
	
	//产生验证码方法
	public static String Gy()
	{
		StringBuffer sb=new StringBuffer();
		int temp_num;
		for(int i=0;i<6;i++)
		{
			temp_num=(int)(Math.random()*10);
			sb.append(temp_num);
		}
		
		return sb.toString();
	}
	
}
