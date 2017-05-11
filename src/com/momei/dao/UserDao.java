package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.momei.domain.UserInfo;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;
import com.momei.util.ServiceException;

public class UserDao {
	
private Connection connection;

private int pageSize=5;
private int rowCount=0;
private int pageCount=0;
	
	
	public  UserDao(Connection connection){
		this.connection = connection;
	}
	
	/**
	 * 增加用户
	 * user用户信息对象
	 */
	public void addUser(UserInfo user){
		String sql="insert into userInfo (userId,userName,Name,phoneNum,Email,School,passwd,score) values(user_seq.nextval,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPhoneNum());
			pstmt.setString(4,user.getEmail());
			pstmt.setString(5,user.getSchool());
			pstmt.setString(6, user.getPasswd());
			pstmt.setInt(7, 0);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on adding user", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 添加用户头像
	 */
	
	public void addUserTx(String userName,String pic){
		String sql="update userInfo set pic=? where userName=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, pic);
			pstmt.setString(2, userName);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on adding user", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 增加用户积分
	 */
	public void addScore(int userId)
	{
		String sql="update userInfo set score=score+100 where userId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on adding user", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	

	/*
	 * 修改用户手机号
	 */
	
	public void UpdatePh(String userName,String phoneNum){
		
		String sql="update userInfo set phoneNum=? where userName=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, phoneNum);
			pstmt.setString(2, userName);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update user", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 修改用户绑定邮箱
	 */
	
	public void UpdateEmail(String userName,String EmailNew){
		
		String sql="update userInfo set Email=? where userName=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, EmailNew);
			pstmt.setString(2, userName);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update user", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	
	/*
	 * 修改密码
	 */
	
	public void UpdatePasswd(String userName,String passwdNew){
		
		String sql="update userInfo set passwd=? where userName=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, passwdNew);
			pstmt.setString(2, userName);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update user", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
		
	}
	
	
	/*
	 * 验证用户名是否存在
	 */
	
	public boolean findUser(String username){
		
		 String sql = "select * from userInfo where username=?";
		 boolean b=false;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, username);
		  
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			   b=true;
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting user", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return b;
		}
	
	 /*
	 * 验证手机号是否存在
	 */
	
	public boolean findPhoneNum(String phoneNum){
		
		 String sql = "select * from userInfo where phoneNum=?";
		 boolean b=false;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, phoneNum);
		  
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			   b=true;
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting user", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return b;
		}
	
	/*
	 * 验证邮箱是否存在
	 */
	
	public boolean findEmail(String Email){
		
		 String sql = "select * from userInfo where Email=?";
		 boolean b=false;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, Email);
		  
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			   b=true;
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting User", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return b;
		}
	
	/*
	 * 登陆验证---用户名、手机、邮箱
	 */
	public UserInfo Login(String uName,String passwd){
		  
		 String sql ="select * from userinfo where userid=(select userid from userinfo where username=? or phonenum=? or email=?) and passwd=?";
		 UserInfo user=null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, uName);
			 pstmt.setString(2, uName);
			 pstmt.setString(3, uName);
			 pstmt.setString(4, passwd);
		  
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			   user=new UserInfo();
			   user.setUserId(rs.getInt("userId"));
			   user.setUserName(rs.getString("userName"));
			   user.setName(rs.getString("Name"));
			   user.setPhoneNum(rs.getString("phoneNum"));
			   user.setEmail(rs.getString("Email"));
			   user.setSchool(rs.getString("School"));
			   user.setPasswd(rs.getString("passwd"));
			   user.setScore(rs.getInt("score"));
			   user.setPic(rs.getString("pic"));
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting user", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return user;
	}
	
	
	/*
	 * 密码验证(修改密码前)
	 */
	public boolean JudgePasswd(String userName,String passwd){
		
		 String sql ="select * from userinfo where userName=? and passwd=?";
		 boolean b=false;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1,userName);
			 pstmt.setString(2, passwd);
			
		  
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			     b=true;
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting user", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return b;
	}
	
	/*
	 * 获取rowCount和pageCount
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		String sql="select * from userInfo";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting userInfo", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * 用户分页查询
	 */
	public  List<UserInfo> getUsersByPage(int pageNow)
	{
		List<UserInfo> list=new ArrayList<UserInfo>();
		String sql="select * from (select a1.*,rownum rn from (select * from userInfo) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 UserInfo user=new UserInfo();
				 user.setUserId(rs.getInt("userId"));
				 user.setUserName(rs.getString("userName"));
				 user.setName(rs.getString("name"));
				 user.setPhoneNum(rs.getString("phoneNum"));
				 user.setEmail(rs.getString("email"));
				 user.setPic(rs.getString("pic"));
				 user.setScore(rs.getInt("score"));
				
				 list.add(user);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting userInfo", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		return list;
	}
	
	/*
	 * 根据用户名获取用户
	 */
	
	public UserInfo findUserByName(String userName){
		String sql = "select * from userInfo where userName=?";
		UserInfo user=null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, userName);
		  
			 rs = pstmt.executeQuery();
			 if(rs.next()){
             user=new UserInfo();
             user.setUserId(rs.getInt("userId"));
			 user.setUserName(rs.getString("userName"));
			 user.setName(rs.getString("name"));
			 user.setPhoneNum(rs.getString("phoneNum"));
			 user.setEmail(rs.getString("email"));
			 user.setPic(rs.getString("pic"));
			 user.setScore(rs.getInt("score"));
			 
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting user", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return user;
	}

}
