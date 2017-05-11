package com.momei.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {
	
    private static DataSource dataSource=null;
	
	static {
		 /**
		  * 获取c3p0数据源
		  * */
		dataSource=new ComboPooledDataSource("oracle_c3p0");
	  }
	
	/**
	 * 得到连接
	 */
	public static Connection getConnection(){
		
		Connection con=null;
		try {
			con=dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 开启事务
	 * @param conn
	 */
	
	public static void beginTransaction(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new ServiceException("Can not begin transaction", e);
		}
	}
	
	/**
	 * 提交事物
	 * @param conn
	 */
	
	public static void commit(Connection conn){
		try{
			conn.commit();
			conn.setAutoCommit(true);
		
		}catch(SQLException e){
			throw new ServiceException("Can not commit transaction", e);
		}
		
		
	}
	
	/**
	 * 回滚事务
	 * 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new ServiceException("Can not rollback transaction", e);
		}
	}
	/**
	 * 释放回连接池
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new ServiceException("Can not close connection", e);
		}
	}

	/**
	 * 关闭statement对象
	 * 
	 * @param stmt
	 */
	public static void closeStatement(ResultSet rs, Statement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			throw new ServiceException("Can not close statement", e);
		}
	}
	

}
