package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.momei.domain.Address;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class AddressDao {
	
	
	private Connection connection;
		
		
		public  AddressDao(Connection connection){
			this.connection = connection;
		}
		
		/**
		 * 增加用户地址
		 */
		public void addAddress(int userId,String addressName,String phoneNum){
			
			String sql="insert into address  values(address_seq.nextval,?,?,?)";
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, addressName);
				pstmt.setString(2, phoneNum);
				pstmt.setInt(3, userId);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new DaoException("Error on adding address", e);
				
			} finally {
				DBUtils.closeStatement(null, pstmt);
			}
		}
		
		/**
		 * 修改用户地址
		 */
		public void updateAddress(int addressId,String addressName,String phoneNum){
			

			String sql="update address set addressName=?,phoneNum=? where addressId=?";
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, addressName);
				pstmt.setString(2, phoneNum);
				pstmt.setInt(3, addressId);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new DaoException("Error on updating address", e);
				
			} finally {
				DBUtils.closeStatement(null, pstmt);
			}
		}
		
		/**
		 * 删除用户地址
		 */
		public void deleteAddress(int addressId){
			

			String sql="delete from address  where addressId=?";
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, addressId);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new DaoException("Error on deleting address", e);
				
			} finally {
				DBUtils.closeStatement(null, pstmt);
			}
		}
		
		/*
		 * 根据用户id获取所有地址
		 */
		
		public List<Address> getAllAddressByUser(int userId)
		{
			List<Address> list=new ArrayList<Address>();
			
			String sql="select * from address where userId=? order by addressId";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				 pstmt.setInt(1, userId);
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Address ad=new Address();
					ad.setAddressId(rs.getInt("addressId"));
					ad.setAddressName(rs.getString("addressName"));
					ad.setPhoneNum(rs.getString("phoneNum"));
					ad.setUserId(rs.getInt("userId"));
					 
					 list.add(ad);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting address", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
		}
}
