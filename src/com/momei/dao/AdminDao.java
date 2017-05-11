package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.momei.domain.Admin;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class AdminDao {
	
	private Connection connection;
	
	
	public  AdminDao(Connection connection){
		this.connection = connection;
	}
	
	/*
	 * µÇÂ½ÑéÖ¤
	 */
	public Admin Login(String userName,String passwd){
		  
		 String sql ="select * from admin where userName=? and passwd=?";
		 Admin admin=null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, userName);
			 pstmt.setString(2, passwd);
		
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			   admin=new Admin();
			   admin.setUserName(rs.getString("userName"));
			   admin.setPasswd(rs.getString("passwd"));
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting admin", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return admin;
	}
	

	/*
	 * ÐÞ¸ÄÃÜÂë
	 */
   public void UpdatePasswd(String userName,String passwdNew){
	   String sql="update admin set passwd=? where userName=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, passwdNew);
			pstmt.setString(2, userName);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update admin", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
   }

}
