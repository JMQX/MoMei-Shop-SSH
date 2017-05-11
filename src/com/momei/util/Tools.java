package com.momei.util;

import java.io.UnsupportedEncodingException;


public class Tools {
	
	//提供一个方法，将字符按gb2312或UTF-8或gbk转成中文
	public static String getNewString(String input)
	{
		String result="";
		try {
			result=new String(input.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
