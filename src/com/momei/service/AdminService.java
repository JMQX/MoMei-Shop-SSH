package com.momei.service;

import java.sql.Connection;

import com.momei.dao.AdminDao;
import com.momei.domain.Admin;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class AdminService {
	
private static final AdminService instance = new AdminService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static AdminService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private AdminService() {
	
	}
	
	/*
	 * 登陆验证
	 */
	public Admin Login(String userName,String passwd){
         Admin admin=null;
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			AdminDao ad = new AdminDao(conn);
			DBUtils.beginTransaction(conn);
			admin=ad.Login(userName, passwd);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询用户错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return admin;
		
	}
	
	/*
	 * 修改密码
	 */
   public void UpdatePasswd(String userName,String passwdNew){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AdminDao ad=new AdminDao(conn);
			DBUtils.beginTransaction(conn);
		    ad.UpdatePasswd(userName, passwdNew);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改密码错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}

}
