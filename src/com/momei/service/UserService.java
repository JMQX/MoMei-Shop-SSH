package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.UserDao;
import com.momei.domain.UserInfo;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class UserService {
	
private static final UserService instance = new UserService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static UserService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private UserService() {
	
	}
	
	/**
	 * 增加用户
	 * user 用户信息实体
	 */
	public void addUser(UserInfo user){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.addUser(user);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加用户错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 添加用户头像
	 */
	
	public void addUserTx(String userName,String pic){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.addUserTx(userName, pic);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加用户头像错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 增加用户积分
	 */
	public void addScore(int userId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.addScore(userId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加用户积分错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	
	/*
	 * 修改用户手机号
	 */
	
	public void UpdatePh(String userName,String phoneNum){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.UpdatePh(userName, phoneNum);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改用户手机号错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 修改用户绑定邮箱
	 */
	
	public void UpdateEmail(String userName,String EmailNew){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.UpdateEmail(userName, EmailNew);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改用户绑定邮箱错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 修改密码
	 */
	
	public void UpdatePasswd(String userName,String passwdNew){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			UserDao userDao=new UserDao(conn);
			DBUtils.beginTransaction(conn);
			userDao.UpdatePasswd(userName, passwdNew);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改用户密码错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	
	/*
	 * 验证用户名是否存在
	 */
	
	public boolean findUser(String username){
        boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			 if(ud.findUser(username)==true)
			 {
				 b=true;
			 }else
			 {
				 b=false;
			 }
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * 验证绑定手机号是否存在
	 */
	
	public boolean findPhoneNum(String phoneNum){
        boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			 if(ud.findPhoneNum(phoneNum)==true)
			 {
				 b=true;
			 }else
			 {
				 b=false;
			 }
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * 验证注册邮箱是否存在
	 */
	
	public boolean findEmail(String Email){
        boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			 if(ud.findEmail(Email)==true)
			 {
				 b=true;
			 }else
			 {
				 b=false;
			 }
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * 登陆验证---用户名、手机、邮箱
	 */
	public UserInfo Login(String uName,String passwd){
        UserInfo user=null;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			user=ud.Login(uName, passwd);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return user;
		
	}
	
	/*
	 * 密码验证(修改密码前)
	 */
	public boolean JudgePasswd(String userName,String passwd){
        
		boolean b=false;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			b=ud.JudgePasswd(userName, passwd);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
		
	}
	
	/*
	 * 获取rowCount和pageCount
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			 UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			res=ud.getPageCountAndRowCount();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * 用户分页查询
	 */
	public  List<UserInfo> getUsersByPage(int pageNow)
	{
		 List<UserInfo> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				 UserDao ud = new UserDao(conn);
				DBUtils.beginTransaction(conn);
				list=ud.getUsersByPage(pageNow);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询用户错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * 根据用户名获取用户
	 */
	
	public UserInfo findUserByName(String userName){
        UserInfo user=null;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			UserDao ud = new UserDao(conn);
			DBUtils.beginTransaction(conn);
			user=ud.findUserByName(userName);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return user;
		
	}
	
}
