package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.AddressDao;
import com.momei.domain.Address;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class AddressService {
	
private static final AddressService instance = new AddressService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static AddressService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private AddressService() {
	
	}

	/**
	 * 增加用户地址
	 */
	public void addAddress(int userId,String addressName,String phoneNum){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AddressDao adDao=new AddressDao(conn);
			DBUtils.beginTransaction(conn);
			adDao.addAddress(userId, addressName,phoneNum);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加用户地址错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * 修改用户地址
	 */
	public void updateAddress(int addressId,String addressName,String phoneNum){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AddressDao adDao=new AddressDao(conn);
			DBUtils.beginTransaction(conn);
			adDao.updateAddress(addressId, addressName,phoneNum);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改用户地址错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * 删除用户地址
	 */
	public void deleteAddress(int addressId){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			AddressDao adDao=new AddressDao(conn);
			DBUtils.beginTransaction(conn);
			adDao.deleteAddress(addressId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("删除用户地址错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 根据用户id获取所有地址
	 */
	
	public List<Address> getAllAddressByUser(int userId)
	{
		List<Address> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				AddressDao adDao=new AddressDao(conn);
				DBUtils.beginTransaction(conn);
				list=adDao.getAllAddressByUser(userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询用户地址错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
		
		return list;
	}
}
