package com.momei.service;

import java.sql.Connection;
import java.util.Calendar;

import com.momei.dao.CommentDao;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class CommentService {
	
private static final CommentService instance = new CommentService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static CommentService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private CommentService() {
	
	}
	
	//添加评价
	
	public void add(int userId,int bookId,String content)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			CommentDao cd=new CommentDao(conn);
			DBUtils.beginTransaction(conn);
			cd.add(userId, bookId,content);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加订单评价错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}

}
