package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;

import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class CommentDao {
	
	private Connection connection;

		
		public  CommentDao(Connection connection){
			this.connection = connection;
		}
		
		//Ìí¼ÓÆÀ¼Û
		
		public void add(int userId,int bookId,String content)
		{
			String sql = "insert into commentt (commentId,userId,bookId,content) values(comment_seq.nextval,?,?,?)";
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(sql);
			    pstmt.setInt(1,userId );
			    pstmt.setInt(2,bookId );
			    pstmt.setString(3,content);
			 
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new DaoException("Error on add", e);
				
			} finally {
				DBUtils.closeStatement(null, pstmt);
			}
		}

}
